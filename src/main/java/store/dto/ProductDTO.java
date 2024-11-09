package store.dto;

import static store.Product.products;

public class ProductDTO {
    private String name;
    private int quantity;
    private boolean isBonusItemEligible = false;
    private boolean notEnoughQuantityForPromo = false;
    public ProductDTO(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isBonusItemEligible() {
        return isBonusItemEligible;
    }

    public boolean isNotEnoughQuantityForPromo() {
        return notEnoughQuantityForPromo;
    }

    public void setBonusItemEligible() {
        isBonusItemEligible = true;
    }

    public void setNotEnoughQuantityForPromo() {
        this.notEnoughQuantityForPromo = true;
    }

    public boolean addPromoQuantity() {
        this.quantity += products.get(this.name).getPromotion().getGet();
        return true;
    }

    public boolean decreaseQuantity(int quantity) {
        this.quantity -= quantity;
        return true;
    }
}
