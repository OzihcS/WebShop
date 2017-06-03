package db.transaction;

import db.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Used to handle transactions.
 */
public class TransactionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);
    private int connectionIsolation = Connection.TRANSACTION_READ_COMMITTED;

    /**
     * Provides functionality of transaction.
     *
     * @param operator method which invoke with transaction.
     * @param <T>      return type of method
     * @return result of transaction invocation.
     */
    public <T> T doInTransaction(TransactionOperator<T> operator) {
        T result = null;
        Connection connection = null;

        try {
            connection = DataSourceFactory.getDataSource().getConnection();

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connectionIsolation);

            result = operator.operation();

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                LOGGER.error("Occurred exception when tried rollback transaction with cause: ", e.getCause());
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Occurred exception when tried close connection with cause: ", e.getCause());
            }
        }
        return result;
    }

    public void setConnectionIsolation(int connectionIsolation) {
        this.connectionIsolation = connectionIsolation;
    }
}
