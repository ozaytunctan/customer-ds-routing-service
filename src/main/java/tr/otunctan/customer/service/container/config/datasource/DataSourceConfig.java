package tr.otunctan.customer.service.container.config.datasource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(
        basePackages = "tr.otunctan",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"

)
public class DataSourceConfig {
    private final DataSourceProperties dataSourceProperties;
    private final DataSourceFactory dataSourceFactory;

    public DataSourceConfig(DataSourceProperties dataSourceProperties, DataSourceFactory dataSourceFactory) {
        this.dataSourceProperties = dataSourceProperties;
        this.dataSourceFactory = dataSourceFactory;
    }

//    @Bean
//    @Primary
//    public DataSource readWriteDataSource() {
//        return dataSourceFactory.create(dataSourceProperties.getMaster(),"master");
//    }

//    @Bean
//    public DataSource readOnlyDataSource() {
//        return dataSourceFactory.create(dataSourceProperties.getReplica(),"replica");
//    }

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {

        RoutingDataSource routing=new RoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        DataSource masterDs = dataSourceFactory.create(dataSourceProperties.getMaster(),"master");
        DataSource replicaDs = dataSourceFactory.create(dataSourceProperties.getReplica(),"replica");
        dataSourceMap.put(
                DataSourceType.READ_WRITE,
                masterDs
        );
        dataSourceMap.put(
                DataSourceType.READ_ONLY,
                replicaDs
        );


        routing.setTargetDataSources(dataSourceMap);
        routing.setDefaultTargetDataSource(masterDs);

        routing.afterPropertiesSet(); //KRİTİK
        return routing;
    }




    @Bean()
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("dataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("tr.otunctan");
        em.setPersistenceUnitName("customer-unit");


        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        em.setJpaProperties(hibernateProps());

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }

    private Properties hibernateProps() {

        Properties props = new Properties();

        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");

        props.put("hibernate.connection.provider_disables_autocommit", "true");
        props.put("hibernate.transaction.coordinator_class", "jdbc");
        props.put("hibernate.current_session_context_class", "jta");

        return props;
    }

}
