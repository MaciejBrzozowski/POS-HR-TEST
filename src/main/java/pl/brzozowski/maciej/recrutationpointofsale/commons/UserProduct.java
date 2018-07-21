package pl.brzozowski.maciej.recrutationpointofsale.commons;

public class UserProduct extends Product {

    public UserProduct(Product product) {
        super(product.getName(), product.getPrice(), 1);
    }

    public UserProduct(Product product, int quantity) {
        super(product.getName(), product.getPrice(), quantity);
    }
}
