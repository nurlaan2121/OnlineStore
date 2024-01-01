package models;

import java.util.Date;

public class Tvs extends Electronics{
    public Tvs() {
    }

    public Tvs(long id, Categorie categories, Date createdAt, String model, int price, boolean status) {
        super(id, categories, createdAt, model, price, status);
    }
}
