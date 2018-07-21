package pl.brzozowski.maciej.recrutationpointofsale.exception;

public class ProductNotFoundException extends IllegalArgumentException {
    public ProductNotFoundException(String s) {
        super("Product not found " + s);
    }

    public ProductNotFoundException() {
        super("Product not found!");
    }
}
