package pl.brzozowski.maciej.recrutationpointofsale.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.repository.UserOrder;

import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.when;

public class ReceiptServiceTest {

    private UserOrder userOrder;
    private BarcodeService barcodeService;
    private ReceiptService receiptService;
    private Product[] products;
    private String barcode;
    private Product product;


    @Before
    public void setUp() throws Exception {
        userOrder = mock(UserOrder.class);
        barcodeService = mock(BarcodeService.class);
        products = new Product[1];
        product = mock(Product.class);

        when(userOrder.readAll()).thenReturn(products);
        when(barcodeService.findProduct(anyString())).thenReturn(product);
    }

    @Test
    public void shouldGetUserOrder() {
        //given
        receiptService = new ReceiptService(userOrder, barcodeService);
        //when
        Product[] result = receiptService.getUserOrder();
        //then
        verify(userOrder).readAll();
        assertNotNull(result);
        assertEquals(result.length, 1);
    }

    @Test
    public void shouldAddProductToUserOrderWhenProductExistsInUserOrder() {
        //given
        when(userOrder.read(anyString())).thenReturn(product);
        receiptService = new ReceiptService(userOrder, barcodeService);
        barcode = "1234";
        //when
        receiptService.addProduct(barcode);
        //then
        verify(userOrder).update(eq(barcode), eq(product));

    }

    @Test
    public void shouldAddProductToUserOrderWhenProductNotExistsInUserOrder() {
        //given
        when(userOrder.read(anyString())).thenReturn(null);
        receiptService = new ReceiptService(userOrder, barcodeService);
        barcode = "1234";
        //when
        receiptService.addProduct(barcode);
        //then
        verify(userOrder).create(eq(barcode), eq(product));

    }
}