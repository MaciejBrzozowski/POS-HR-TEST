package pl.brzozowski.maciej.recrutationpointofsale.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.repository.UserOrder;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PrintService {

    @Autowired
    private UserOrder userOrder;

    public Product[] printReceiptAndClose() {
        Product[] allproducts = userOrder.readAll().clone();
        userOrder.clear();
        return allproducts;
    }

}
