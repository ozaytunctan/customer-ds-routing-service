package tr.otunctan.customer.service.container.config.datasource;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceFactory {
    public DataSource create(DataSourceProperties.DataSourceConfig config, String poolName) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setDriverClassName(config.getDriverClassName());


        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setPoolName("Hikari-" + poolName);
        hikariConfig.setAutoCommit(false);
        hikariConfig.setJdbcUrl(config.getUrl());


//        int cpuCores = Runtime.getRuntime().availableProcessors();
//        hikariConfig.setMaximumPoolSize(cpuCores * 4);
        return new HikariDataSource(hikariConfig);
    }
}
