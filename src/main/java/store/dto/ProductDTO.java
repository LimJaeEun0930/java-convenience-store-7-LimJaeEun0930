package store.dto;

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

    public void setBonusItemEligible() {
        isBonusItemEligible = true;
    }

    public void setNotEnoughQuantityForPromo() {
        this.notEnoughQuantityForPromo = true;
    }
}
