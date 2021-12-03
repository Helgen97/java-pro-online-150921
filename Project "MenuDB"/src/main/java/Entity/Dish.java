package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private String dishName;

    private int price;
    private int weight;
    private boolean discount;

    public Dish() {

    }

    public Dish(String dishName, int price, int weight, boolean discount) {
        this.dishName = dishName;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Dish: " + dishName +
                ", price: " + price +
                ", weight: " + weight +
                ", Discount: " + (discount ? "yes" : "no");
    }
}
