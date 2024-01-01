package models;

import java.util.Date;

public class Airpods extends Electronics{
    public Airpods() {
    }

    public Airpods(long id, Categorie categories, Date createdAt, String model, int price, boolean status) {
        super(id, categories, createdAt, model, price, status);
    }
}
