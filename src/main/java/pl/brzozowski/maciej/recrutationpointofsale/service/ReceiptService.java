package pl.brzozowski.maciej.recrutationpointofsale.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.repository.UserOrder;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final UserOrder userOrder;
    private final BarcodeService barcodeService;

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
