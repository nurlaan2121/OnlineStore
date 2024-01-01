package models;

import java.util.Date;

public class Telephones extends Electronics{
    public Telephones() {
    }

    public Telephones(long id, Categorie categories, Date createdAt, String model, int price, boolean status) {
        super(id, categories, createdAt, model, price, status);
    }
}
