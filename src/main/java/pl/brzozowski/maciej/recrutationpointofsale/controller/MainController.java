package pl.brzozowski.maciej.recrutationpointofsale.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Barcode;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.commons.SimpleResponse;
import pl.brzozowski.maciej.recrutationpointofsale.exception.InvalidEntryException;
import pl.brzozowski.maciej.recrutationpointofsale.exception.ProductNotFoundException;
import pl.brzozowski.maciej.recrutationpointofsale.service.BarcodeService;
import pl.brzozowski.maciej.recrutationpointofsale.service.PrintService;
import pl.brzozowski.maciej.recrutationpointofsale.service.ReceiptService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static pl.brzozowski.maciej.recrutationpointofsale.configuration.UrlTemplate.*;

@RestController
@RequestMapping(path = POS)
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private Logger logger;
    @Autowired
    private BarcodeService barcodeService;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private PrintService printService;

    @PostMapping(path = CHECK_BARCODE)
    public SimpleResponse checkBarcode(@RequestBody Barcode productBarcode) {
        if (barcodeService.findProduct(productBarcode.getBarcode()) != null) {
            return new SimpleResponse("Product exists in DB");
        }
        return null;
    }

    @PostMapping(path = ADD_PRODUCT)
    @ResponseStatus(code = NO_CONTENT)
    public void addProduct(@RequestBody Barcode productBarcode) {
        if (productBarcode.getBarcode() == null || productBarcode.getBarcode().isEmpty()) {
            throw new InvalidEntryException();
        }
        logger.info("BARCODE: " + productBarcode.toString());
        receiptService.addProduct(productBarcode.getBarcode());
    }

    @GetMapping(path = GET_PRODUCTS)
    public Product[] getProducts() {
        return receiptService.getUserOrder();
    }

    @GetMapping(value = PRINT)
    public Product[] closeReceipt() {
        return printService.printReceiptAndClose();
    }


    @ExceptionHandler({ProductNotFoundException.class})
    void handleNotFound(HttpServletResponse response, ProductNotFoundException ex) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler({InvalidEntryException.class})
    void handleInvalidEntry(HttpServletResponse response, InvalidEntryException ex) throws IOException {
        response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
    }

}
