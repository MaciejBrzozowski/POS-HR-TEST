package pl.brzozowski.maciej.recrutationpointofsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.repository.UserOrder;

@Service
public class PrintService {

    @Autowired
    public PrintService(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    private UserOrder userOrder;

    public Product[] printReceiptAndClose() {
        Product[] allproducts = userOrder.readAll().clone();
        userOrder.clear();
        return allproducts;
    }

}
