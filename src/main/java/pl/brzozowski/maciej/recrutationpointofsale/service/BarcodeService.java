package pl.brzozowski.maciej.recrutationpointofsale.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.exception.ProductNotFoundException;
import pl.brzozowski.maciej.recrutationpointofsale.repository.InMemoryDB;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BarcodeService {

    @Autowired
    private InMemoryDB inMemoryDB;

    public Product findProduct(String barcode) {
        Product product = inMemoryDB.read(barcode);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return product;
    }

}
