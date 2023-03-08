package Model;

import java.util.Objects;

public class Item {
    private int id;
    private String name;

    private String description;
    private double price;
    private int storeId;

    public Item(int id, String name, String description, double price, int storeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.storeId = storeId;
    }

    public Item(String name, String description, double price, int storeId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.storeId = storeId;
    }

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Double.compare(item.price, price) == 0 && storeId == item.storeId && Objects.equals(name, item.name) && Objects.equals(description, item.description);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", storeId=" + storeId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, storeId);
    }
}
