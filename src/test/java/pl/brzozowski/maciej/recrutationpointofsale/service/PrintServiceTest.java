package pl.brzozowski.maciej.recrutationpointofsale.service;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.repository.UserOrder;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.math.RandomUtils.nextDouble;
import static org.apache.commons.lang.math.RandomUtils.nextInt;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class PrintServiceTest {

    private UserOrder userOrder;
    private PrintService printService;
    private Product[] products;

    @Before
    public void setUp() throws Exception {
        products = new Product[2];
        products[0] = getProduct();
        products[1] = getProduct();
        userOrder = mock(UserOrder.class);
        when(userOrder.readAll()).thenReturn(products);
    }

    @Test
    public void printReceiptAndClose() {
        //given
        printService = new PrintService(userOrder);
        //When
        Product[] result = printService.printReceiptAndClose();
        //then
        assertEquals(result.length, 2);
        verify(userOrder).clear();
    }

    private Product getProduct() {
        return new Product(randomAlphabetic(6), nextDouble(), nextInt());
    }
}