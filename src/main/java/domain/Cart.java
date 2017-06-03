package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Cart {

    private Map<Product, Integer> products;

    public Cart() {
        products = new LinkedHashMap<>();
    }

    public void add(Product id) {
        if (products.containsKey(id)) {
            products.put(id, products.get(id) + 1);
            return;
        }
        products.put(id, 1);
    }

    public int quantity() {
        return products.size();
    }

    public double getResultPrice() {
        final double[] price = {0};
        products.forEach((product, quantity) -> {
            price[0] += product.getPrice() * quantity;
        });
        return price[0];
    }

    public boolean remove(Product id) {
        return products.remove(id) != null;
    }

    public Map<Product, Integer> getCart() {
        return products;
    }

    public void clear() {
        products.clear();
    }
}
