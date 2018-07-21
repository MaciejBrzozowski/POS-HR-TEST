package pl.brzozowski.maciej.recrutationpointofsale.repository;

import pl.brzozowski.maciej.recrutationpointofsale.commons.UserProduct;
import pl.brzozowski.maciej.recrutationpointofsale.exception.ProductAlreadyExistsException;
import pl.brzozowski.maciej.recrutationpointofsale.exception.ValueNotFoundException;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;

import java.util.HashMap;

import static java.util.Optional.ofNullable;

public class UserOrder implements CrudInterface<Product, String> {

    private static HashMap<String, Product> userOrder = new HashMap<>();

    @Override
    public int create(String barcode, Product product) {
        if (userOrder.containsKey(barcode)) {
            throw new ProductAlreadyExistsException();
        } else {
            UserProduct userProduct = new UserProduct(product);
            userOrder.put(barcode, userProduct);
        }
        return 0;
    }

    @Override
    public Product read(String barcode) {
        return userOrder.get(barcode);
    }

    public Product[] readAll() {
        return userOrder.values().toArray(new Product[0]);
    }

    @Override
    public int update(String barcode, Product product) {
        ofNullable(userOrder.get(barcode)).ifPresent(p -> userOrder.put(barcode, p.setQuantity(p.getQuantity() + 1)));
        return 0;
    }

    @Override
    public int delete(String barcode) {
        ofNullable(userOrder.remove(barcode)).orElseThrow(ValueNotFoundException::new);
        return 0;
    }

    public int clear() {
        userOrder.clear();
        return 0;
    }


}
