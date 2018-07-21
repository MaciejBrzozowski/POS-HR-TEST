package pl.brzozowski.maciej.recrutationpointofsale.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    private String name;
    private Double price;
    private int quantity;

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
