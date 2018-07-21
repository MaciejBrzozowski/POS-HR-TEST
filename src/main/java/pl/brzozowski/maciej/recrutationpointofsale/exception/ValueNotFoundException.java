package pl.brzozowski.maciej.recrutationpointofsale.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class ValueNotFoundException extends IllegalArgumentException {
    public ValueNotFoundException() {
        super("Value not found");
    }
}
