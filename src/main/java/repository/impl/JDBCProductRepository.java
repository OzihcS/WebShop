package repository.impl;


import db.Query;
import domain.Product;
import domain.ProductCategory;
import exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.ProductRepository;
import util.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static util.Constants.Attributes.Product.*;

/**
 * JDBC implementation of {@link ProductRepository}.
 */
public class JDBCProductRepository implements ProductRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCProductRepository.class);
    private static final String ERROR_MESSAGE = "Cannot handle sql ['{}']; Message: ";
    private static final String GET_ALL = "product.get.all";
    private static final String GET = "product.get";
    private static final String COUNT = "product.count";

    @Override
    public List<Product> getAll(Connection connection) {
        String sql = Query.get(GET_ALL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(extractFromResultSet(resultSet));
            }

            return products;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Product getById(Connection connection, int id) {
        String sql = Query.get(GET);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Product product = null;
            if (resultSet.next()) {
                product = extractFromResultSet(resultSet);
            }
            return product;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public int count(Connection connection) {
        String sql = Query.get(COUNT);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
        return 0;
    }


    @Override
    public List<Product> get(Connection connection, Map<String, Object> params) {
        String sql = buildQuery(params);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = prepareStatement(params, sql, preparedStatement);
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(extractFromResultSet(resultSet));
            }

            return products;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    private ResultSet prepareStatement(Map<String, Object> params, String sql, PreparedStatement preparedStatement) {
        int k = 1;
        try {
            if (params.containsKey(NAME)) {
                preparedStatement.setString(k++, String.valueOf(params.get(NAME)));
            }
            if (params.containsKey(MANUFACTURER)) {
                preparedStatement.setString(k++, String.valueOf(params.get(MANUFACTURER)));
            }
            if (params.containsKey(MIN_PRICE)) {
                preparedStatement.setString(k++, String.valueOf(params.get(MIN_PRICE)));
            }
            if (params.containsKey(MAX_PRICE)) {
                preparedStatement.setString(k++, String.valueOf(params.get(MAX_PRICE)));
            }
            if (params.containsKey(CATEGORY)) {
                Object[] categories = (Object[]) params.get(CATEGORY);
                for (Object category : categories) {
                    preparedStatement.setString(k++, String.valueOf(category));
                }
            }
            if (params.containsKey(ORDER)) {
                preparedStatement.setString(k++, String.valueOf(params.get(ORDER)));
            }

            preparedStatement.setInt(k++, (Integer.parseInt(String.valueOf(params.get(PAGINATION))) - 1) *
                    Integer.parseInt(String.valueOf(params.get(GRID))));
            preparedStatement.setInt(k++, Integer.parseInt(String.valueOf(params.get(GRID))));

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }


    private String buildQuery(Map<String, Object> params) {
        QueryBuilder builder = new QueryBuilder(TABLE_NAME);

        if (params.containsKey(NAME)) {
            builder.addAndParam(NAME, "=");
        }
        if (params.containsKey(MANUFACTURER)) {
            builder.addAndParam(MANUFACTURER, "=");
        }
        if (params.containsKey(MIN_PRICE)) {
            builder.addAndParam(PRICE, ">=");
        }
        if (params.containsKey(MAX_PRICE)) {
            builder.addAndParam(PRICE, "<=");
        }
        if (params.containsKey(CATEGORY)) {
            Object[] categories = (Object[]) params.get(CATEGORY);
            for (Object category : categories) {
                builder.addOrParam(CATEGORY, "=");
            }
        }
        if (params.containsKey(ORDER)) {
            builder.addOrderByParam(String.valueOf(params.get(ORDER)));
        }

        builder.addLimit();
        return builder.build();
    }

    private Product extractFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt(ID));
        product.setName(resultSet.getString(NAME));
        product.setPrice(resultSet.getDouble(PRICE));
        product.setManufacturer(resultSet.getString(MANUFACTURER));
        product.setCategory(ProductCategory.valueOf(resultSet.getString(CATEGORY).toUpperCase()));
        product.setDescription(resultSet.getString(DESCRIPTION));
        product.setImage(resultSet.getString(IMAGE));

        return product;
    }
}
