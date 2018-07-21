package pl.brzozowski.maciej.recrutationpointofsale.exception;

public class ProductAlreadyExistsException extends IllegalArgumentException {
    public ProductAlreadyExistsException() {
        super("Can't create new entry. Element already exists.");
    }
}
