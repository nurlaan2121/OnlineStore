package models;

import java.util.Date;

public abstract class Electronics {
    private long id;
    private Categorie categories;
    private Date createdAt;
    private String model;
    private int price;
    private boolean status;

    public Electronics() {
    }

    public Electronics(long id, Categorie categories, Date createdAt, String model, int price, boolean status) {
        this.id = id;
        this.categories = categories;
        this.createdAt = createdAt;
        this.model = model;
        this.price = price;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Categorie getCategories() {
        return categories;
    }

    public void setCategories(Categorie categories) {
        this.categories = categories;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "id=" + id +
                ", categories=" + categories +
                ", createdAt=" + createdAt +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
