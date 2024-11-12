package store.model;

import java.util.ArrayList;
import store.dto.ProductToCalculateDTO;

public class ConfirmedPurchaseProducts {
    private boolean hasMembershipDiscount;
    private ArrayList<ProductToCalculateDTO> products;

    private int quantitySum;
    private int totalConsumePrice;
    private int promotionDiscountPrice;
    private int membershipDiscountPrice;
    private int totalPayment;

    public ConfirmedPurchaseProducts(boolean hasMembershipDiscount, ArrayList<ProductToCalculateDTO> products) {
        this.hasMembershipDiscount = hasMembershipDiscount;
        this.products = products;
        setPayment();
    }

    public boolean HasMembershipDiscount() {
        return hasMembershipDiscount;
    }

    public ArrayList<ProductToCalculateDTO> getProducts() {
        return products;
    }

    public void setPayment() {
        int promotionUnappliedPriceSum = 0;
        for (ProductToCalculateDTO product : products) {
            totalConsumePrice += product.getTotalPriceWithoutDiscount();
            quantitySum += product.getTotalQuantity();
            promotionDiscountPrice += product.getPromotionDiscountPrice();
            promotionUnappliedPriceSum += product.getPromotionUnappliedPrice();
            membershipDiscountPrice = calculateMembershipDiscount(promotionUnappliedPriceSum);
            totalPayment = totalConsumePrice - promotionDiscountPrice - membershipDiscountPrice;
        }
    }

    private int calculateMembershipDiscount(int promotionUnappliedPriceSum) {
        if (hasMembershipDiscount) {
            return (int) Math.min(promotionUnappliedPriceSum * 0.3, 8000);
        }
        return 0;
    }

    public int getQuantitySum() {
        return quantitySum;
    }

    public int getTotalConsumePrice() {
        return totalConsumePrice;
    }

    public int getPromotionDiscountPrice() {
        return promotionDiscountPrice;
    }

    public int getMembershipDiscountPrice() {
        return membershipDiscountPrice;
    }

    public int getTotalPayment() {
        return totalPayment;
    }
}
