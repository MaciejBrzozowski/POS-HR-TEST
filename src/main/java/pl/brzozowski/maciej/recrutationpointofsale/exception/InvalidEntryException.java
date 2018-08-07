package pl.brzozowski.maciej.recrutationpointofsale.exception;

public class InvalidEntryException extends IllegalArgumentException {
    public InvalidEntryException() {
        super("Invalid bar-code");
    }
}
