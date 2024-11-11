package store.dto;

import static store.Product.isInPromotionDuration;
import static store.Product.products;

import store.Product;
import store.Promotion;

public abstract class ProductToCalculateDTO {
    protected String name;
    protected int promotionQuantity;
    protected int quantity;
    protected int unAppliedPromotionQuantity;
    protected int price;
    protected int promotionGetQuantity; // 여기로부터 행사할인 금액

    protected ProductToCalculateDTO() {
    }

    public static ProductToCalculateDTO getInstance(final ProductDTO productDTO) {
        Product product = products.get(productDTO.getName());
        Promotion promotion = product.getPromotion();
        if (promotion != null) {
            return new ProductWithPromoDTO(productDTO);
        }
        return new ProductWithoutPromoDTO(productDTO);

    }
    public String getName() {
        return name;
    }

    public int getPromotionGetQuantity() {
        return promotionGetQuantity;
    }

    public int getPromotionUnappliedPrice() { // 멤버십 할인에 사용
        if (!isInPromotionDuration(products.get(this.name))) {
            unAppliedPromotionQuantity = promotionQuantity;
        }
        return (quantity + unAppliedPromotionQuantity) * price;
    }

    public int getTotalPriceWithoutDiscount() { //영수증 상단에 쓸 금액
        return (quantity + promotionQuantity) * price;
    }

    public int getTotalQuantity() {
        return quantity + promotionQuantity;
    }

    public int getPromotionDiscountPrice() { //프로모션 할인 금액
        if (isInPromotionDuration(products.get(this.name))) {
            return promotionGetQuantity * price;
        }
        return 0;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
