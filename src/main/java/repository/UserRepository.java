package repository;

import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides storage of users.
 */
public interface UserRepository {

    /**
     * Adds new user to user's storage.
     *
     * @param user to add.
     */
    boolean add(Connection connection, User user);

    /**
     * Checks existence specified user in users storage.
     *
     * @param user to check.
     * @return true if user exists and false in otherwise.
     */
    boolean exist(Connection connection, User user);

    /**
     * Checks user availability and return all data of user which tried to login.
     *
     * @param connection
     * @param email      user's email.
     * @param password   user's password.
     * @return user if exists, otherwise null.
     */
    User login(Connection connection, String email, String password);
}
