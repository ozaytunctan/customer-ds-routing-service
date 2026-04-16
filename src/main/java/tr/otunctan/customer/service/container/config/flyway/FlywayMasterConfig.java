package tr.otunctan.customer.service.container.config.flyway;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayMasterConfig {

//    @Bean(initMethod = "migrate")
//    public Flyway flyway(@Qualifier("readWriteDataSource") DataSource masterDataSource) {
//        return Flyway.configure()
//                .dataSource(masterDataSource) //ONLY MASTER
//                .schemas("app_live")
//                .baselineOnMigrate(true)
//                .load();
//    }


}