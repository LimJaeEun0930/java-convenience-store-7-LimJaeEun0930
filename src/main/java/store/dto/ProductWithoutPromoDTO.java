package store.dto;

import static store.model.Product.products;

import store.model.Product;

public class ProductWithoutPromoDTO extends ProductToCalculateDTO {

    public ProductWithoutPromoDTO(final ProductDTO productDTO) {
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
