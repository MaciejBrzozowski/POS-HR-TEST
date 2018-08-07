package pl.brzozowski.maciej.recrutationpointofsale.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.tests.utils.impl.PowerMockIgnorePackagesExtractorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;
import pl.brzozowski.maciej.recrutationpointofsale.exception.ProductNotFoundException;
import pl.brzozowski.maciej.recrutationpointofsale.repository.InMemoryDB;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringRunner.class)
public class BarcodeServiceTest {

    private BarcodeService barcodeService;
    @MockBean
    private InMemoryDB inMemoryDB;
    private String barcode = "1234";
    private Product product;

    @Before
    public void setUp() throws Exception {
        barcodeService = new BarcodeService(inMemoryDB);
        product = mock(Product.class);
    }

    @Test
    public void shouldFindProductIfExistsInDB() {
        //given
        when(inMemoryDB.read(anyString())).thenReturn(product);
        // when
        Product result = barcodeService.findProduct(barcode);
        // then
        assertEquals(product, result);
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrowExceptionIfProductDontExistsInDB() {
        //given
        when(inMemoryDB.read(anyString())).thenReturn(null);
        // when
        barcodeService.findProduct("abcd");
    }
}