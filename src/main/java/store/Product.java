package store;

import static store.Promotion.promotions;

import java.util.ArrayList;
import java.util.Optional;

public class Product {
    public static final ArrayList<Product> products = new ArrayList<>();
    private String name;
    private int price;
    private int quantity;
    private Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String promotionStr = "null";
        if (this.promotion != null) {
            promotionStr = this.promotion.getPromotionName();
        }
        sb.append(name).append(',').append(price).append(',').append(quantity).append(',').append(promotionStr);
        return sb.toString();
    }

    public static void readProduct(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        int price = Integer.parseInt(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        Promotion promozion = promotions.stream().filter(promotion -> promotion.getPromotionName()
                .equals(parts[3])).findFirst().orElse(null);
        products.add(new Product(name, price, quantity, promozion));
         }
}
