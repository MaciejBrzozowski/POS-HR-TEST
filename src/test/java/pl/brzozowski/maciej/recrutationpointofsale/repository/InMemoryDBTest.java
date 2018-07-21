package pl.brzozowski.maciej.recrutationpointofsale.repository;

import org.junit.Before;
import org.junit.Test;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;

import static org.junit.Assert.*;

public class InMemoryDBTest {

    private String name;
    private Double price;
    private int quantity;
    private String barcode;
    private Product product;
    private String barcodeUpdate;
    private InMemoryDB inMemoryDB;

    @Before
    public void setUp() throws Exception {
        inMemoryDB = new InMemoryDB();
        barcode = "12341";
        product = new Product("kawusia", 123.22, 111);
    }

    @Test
    public void create() {
        assertEquals(0, inMemoryDB.create(barcode, product));
    }

    @Test
    public void read() {
        Product result = inMemoryDB.read("1234");
        assertEquals(result.getName(), "milk");
    }

    @Test
    public void update() {

        barcodeUpdate = "3333";
        name = "buter2";
        price = 22.39;
        quantity = 1;
        inMemoryDB.update(barcodeUpdate, new Product(name, price, quantity));
        Product result = inMemoryDB.read(barcodeUpdate);

        assertEquals(result.getName(), name);
        assertEquals(result.getPrice(), price);
        assertEquals(result.getQuantity(), 2);
    }

    @Test
    public void update2() {
        String barcode = "abcd";
        inMemoryDB.update(barcode, new Product("buter2", 22.39, 23222));
        Product result = inMemoryDB.read(barcode);
        assertNull(result);
    }

}