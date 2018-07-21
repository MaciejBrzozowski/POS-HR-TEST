package pl.brzozowski.maciej.recrutationpointofsale.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.repository.UserOrder;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptService {

    @Autowired
    private UserOrder userOrder;
    @Autowired
    private BarcodeService barcodeService;

    public Product[] getUserOrder() {
        return userOrder.readAll();
    }

    public void addProduct(String barcode) {
        if (userOrder.read(barcode) == null) {
            userOrder.create(barcode, barcodeService.findProduct(barcode));
        } else {
            userOrder.update(barcode, barcodeService.findProduct(barcode));
        }
    }
}
