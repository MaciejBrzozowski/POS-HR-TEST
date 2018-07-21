package pl.brzozowski.maciej.recrutationpointofsale.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ResponseStatus(value = UNPROCESSABLE_ENTITY)
public class InvalidEntryException extends IllegalArgumentException {
    public InvalidEntryException() {
        super("Invalid bar-code");
    }
}
