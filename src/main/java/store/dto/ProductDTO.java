package store.dto;

public class ProductDTO {
    private String name;
    private int quantity;

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
}
