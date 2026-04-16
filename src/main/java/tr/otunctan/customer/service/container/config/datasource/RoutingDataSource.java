package tr.otunctan.customer.service.container.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * otunctan
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

//    @Override
//    protected Object determineCurrentLookupKey() {
//
//        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
//            return DataSourceType.READ_WRITE;
//        }
//
//        return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
//                ? DataSourceType.READ_ONLY
//                : DataSourceType.READ_WRITE;
//    }


    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DataSourceContextHolder.get();
        if (dataSourceType == null) {
            return DataSourceType.READ_WRITE;
        }
        return dataSourceType;
    }
}