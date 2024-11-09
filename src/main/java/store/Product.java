package store;

import static store.Promotion.promotions;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Product {
    public static final HashMap<String, Product> products = new LinkedHashMap<>();
    private String name;
    private int price;
    private int quantity;
    private int promotionQuantity;
    private Promotion promotion;

    public Product(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        if (promotion != null) {
            this.promotionQuantity = quantity;
        }
        if (promotion == null) {
            this.quantity = quantity;
        }
    }

    public void setAnotherQuantity(int quantity, Promotion promotion) {
        if (promotion == null) {
            this.quantity = quantity;
        }
        if (promotion != null) {
            this.promotionQuantity = quantity;
        }
    }
    @Override
    public String toString() {
        if (promotion == null) {
            return toStringWithNoPromtion();
        }
        String toStringWithPromo = toStringWithPromotion() + System.lineSeparator() + toStringWithNoPromtion();
        return toStringWithPromo;
    }

    private String toStringWithNoPromtion() {
        StringBuffer sb = new StringBuffer();
        sb.append(name).append(',').append(price).append(',').append(quantity);
        return sb.toString();
    }
    private String toStringWithPromotion() {
        StringBuffer sb = new StringBuffer();
        sb.append(name).append(',').append(price).append(',').append(promotionQuantity).append(',').append(promotion.getName());
        return sb.toString();
    }

    public static void readProduct(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        int price = Integer.parseInt(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        Promotion promozion = promotions.get(parts[3]);
        if (products.get(name) != null) {
            products.get(name).setAnotherQuantity(quantity, promozion);
            return;
        }
        products.put(name, new Product(name, price, quantity, promozion));
         }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public String getPromotionName() {
        if (promotion != null) {
            return promotion.getName();
        }
        return "";
    }
}
