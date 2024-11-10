package store.dto;

import static store.Product.products;

import store.Product;
import store.Promotion;

public class ProductWithPromoDTO extends ProductToCalculateDTO {
    public ProductWithPromoDTO(ProductDTO productToBuy) {
        super();
        this.name = productToBuy.getName();
        Product product = products.get(productToBuy.getName());
        Promotion promotion = product.getPromotion();
        this.promotionQuantity = Math.min(productToBuy.getQuantity(), product.getPromotionQuantity());
            this.promotionGetQuantity = (promotionQuantity / promotion.getPromoPack()) *promotion.getGet();
            this.quantity = productToBuy.getQuantity() - (promotionQuantity / promotion.getPromoPack() * promotion.getPromoPack()) - (promotionQuantity % promotion.getPromoPack());
            this.unAppliedPromotionQuantity = promotionQuantity % promotion.getPromoPack();
        this.price = product.getPrice();
    }
}
