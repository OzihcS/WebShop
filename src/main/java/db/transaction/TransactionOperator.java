package db.transaction;

import java.sql.SQLException;

/**
 * Describes the method method invoke with transaction.
 *
 * @param <T> method invocation return type.
 */
public interface TransactionOperator<T> {

    T operation() throws SQLException;

}
