package repository;

import domain.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Provides working with product's storage.
 */
public interface ProductRepository {

    /**
     * Returns all products from storage.
     *
     * @param connection to database.
     * @return {@link List} of products
     */
    List<Product> getAll(Connection connection);

    /**
     * Returns specific product by id.
     *
     * @param connection to database.
     * @param id         of product.
     * @return product if exists,
     */
    Product getById(Connection connection, int id);

    /**
     * Returns all products which fit by condition.
     *
     * @param connection to database.
     * @param params     params for filtration.
     * @return list of products.
     */
    List<Product> get(Connection connection, Map<String, Object> params);

    /**
     * Counts quantity of products in storage.
     *
     * @param connection to database.
     * @return quantity of products
     */
    int count(Connection connection);

}
