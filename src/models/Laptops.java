package models;

import java.util.Date;

public class Laptops extends Electronics{
    public Laptops() {
    }

    public Laptops(long id, Categorie categories, Date createdAt, String model, int price, boolean status) {
        super(id, categories, createdAt, model, price, status);
    }
}
