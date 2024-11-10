package store.dto;

import static store.Product.products;

import store.Product;
import store.Promotion;

public class ProductWithoutPromoDTO extends ProductToCalculateDTO {

    public ProductWithoutPromoDTO(ProductDTO productDTO) {
        super();
        this.name = productDTO.getName();
        Product product = products.get(productDTO.getName());
        this.promotionQuantity = 0;
        this.promotionGetQuantity = 0;
        this.quantity = productDTO.getQuantity();
        this.unAppliedPromotionQuantity = 0;
        this.price = product.getPrice();
    }
}