package service;

import db.DataSourceFactory;
import db.transaction.TransactionHandler;
import domain.User;
import exception.DataAccessException;
import repository.UserRepository;
import repository.impl.JDBCUserRepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User service.
 */
public class UserService {

    private TransactionHandler handler;
    private UserRepository repository;

    public UserService(TransactionHandler handler) {
        this.handler = handler;
        this.repository = new JDBCUserRepository();
    }

    /**
     * Gets as parameter user and tried to add to users storage..
     *
     * @param user to add.
     * @return true if user was added, otherwise false.
     */
    public boolean add(User user) {
        return handler.doInTransaction(() -> repository.add(DataSourceFactory.getDataSource().getConnection(), user));
    }

    /**
     * Checks existence specified user in users storage.
     *
     * @param user to check.
     * @return true if user contains in storage, otherwise false.
     */
    public boolean exist(User user) {
        return handler.doInTransaction(() -> repository.exist(DataSourceFactory.getDataSource().getConnection(), user));
    }

    /**
     * Checks user availability and return all data of user which tried to login.
     *
     * @param email    user's email.
     * @param password user's password.
     * @return user if exists, otherwise null.
     */
    public User login(String email, String password) {
        return handler.doInTransaction(() -> repository.login(DataSourceFactory.getDataSource().getConnection(),
                email, password));
    }
}
