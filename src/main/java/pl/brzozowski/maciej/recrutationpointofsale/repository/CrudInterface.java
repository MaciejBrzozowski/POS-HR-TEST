package pl.brzozowski.maciej.recrutationpointofsale.repository;

import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;

public interface CrudInterface<T, E> {

    int create(E barcode, Product product);

    T read(E barcode);

    int update(E barcode, Product product);

    int delete(E barcode);
}
