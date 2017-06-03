package repository.impl;

import db.Query;
import domain.User;
import exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.UserRepository;
import util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static util.Constants.Attributes.User.*;

/**
 * JDBC implementation of {@link UserRepository}
 */
public class JDBCUserRepository implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUserRepository.class);
    private static final String ERROR_MESSAGE = "Cannot handle sql ['{}']; Message: ";
    private static final String ADD = "user.add";
    private static final String EXIST = "user.exist";
    private static final String LOGIN = "user.login";

    @Override
    public boolean add(Connection connection, User user) {
        String sql = Query.get(ADD);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int k = 1;

            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getLastName());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k++, user.getAvatar());

            if (preparedStatement.executeUpdate() > 0) {
                LOGGER.info("User '{}' '{}' was added", user.getFirstName(), user.getLastName());
                return true;
            }
            return false;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public boolean exist(Connection connection, User user) {
        String sql = Query.get(EXIST);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getEmail());
            return preparedStatement.executeQuery().next();

        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public User login(Connection connection, String email, String password) {
        String sql = Query.get(LOGIN);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt(ID));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPassword(resultSet.getString(PASSWORD));
        try {
            user.setAvatar(resultSet.getString(AVATAR));
        } catch (SQLException e) {
            LOGGER.warn("Avatar not found for user: {} {}", user.getFirstName(), user.getLastName());
        }
        return user;
    }
}
