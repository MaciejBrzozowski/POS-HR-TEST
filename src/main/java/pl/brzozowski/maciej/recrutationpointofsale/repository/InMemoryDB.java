package pl.brzozowski.maciej.recrutationpointofsale.repository;

import pl.brzozowski.maciej.recrutationpointofsale.commons.Product;

import java.util.HashMap;


public class InMemoryDB {

    private static HashMap<String, Product> internalDB = new HashMap<>();

    // created only to insert products into DB
    static {
        initializeDB();
    }

    public int create(String barcode, Product product) {
        Product readProduct = internalDB.get(barcode);
        if (barcode != null &&
                readProduct == null &&
                product != null &&
                barcode.matches("[0-9]+")) {
            internalDB.put(barcode, product);
            return 0;
        }
        return -1;
    }

    public Product read(String barcode) {
        return internalDB.get(barcode);
    }

    public int update(String barcode, Product product) {
        Product productRead = internalDB.get(barcode);
        if (productRead != null) {
            productRead.setName(product.getName());
            productRead.setPrice(product.getPrice());
            productRead.setQuantity(product.getQuantity() + productRead.getQuantity());
            return 0;
        }

        return -1;
    }

    public int delete(String barcode) {
        // why it is not implemented? Only administrator can delete entries in DB :P :D
        return 0;
    }

    //method created only to insert products into DB
    private static void initializeDB() {
        internalDB.put("1234", new Product("milk", 22.33, 100));
        internalDB.put("4321", new Product("coffe", 2.33, 123));
        internalDB.put("1111", new Product("bread", 29.23, 155));
        internalDB.put("2222", new Product("tea", 12.32, 34));
        internalDB.put("3333", new Product("buter", 2.39, 1));

    }


}
