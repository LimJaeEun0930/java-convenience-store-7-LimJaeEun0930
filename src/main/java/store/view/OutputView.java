package store.view;

import static store.config.Constants.OUT_PRODUCT_DISPLAY_FORMAT;
import static store.config.Constants.OUT_PRODUCT_SHOW_MESSAGE;
import static store.config.Constants.OUT_RECEIPT_GIFT;
import static store.config.Constants.OUT_RECEIPT_GIFT_NAME_QUANTITY;
import static store.config.Constants.OUT_RECEIPT_HEADER;
import static store.config.Constants.OUT_RECEIPT_MEMBERSHIP_DISCOUNT;
import static store.config.Constants.OUT_RECEIPT_PAY_MONEY;
import static store.config.Constants.OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE;
import static store.config.Constants.OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE_IMPL;
import static store.config.Constants.OUT_RECEIPT_PROMOTION_DISCOUNT;
import static store.config.Constants.OUT_RECEIPT_SEPARATION_LINE;
import static store.config.Constants.OUT_RECEIPT_TOTAL_PRICE;
import static store.config.Constants.OUT_WELCOME_MESSAGE;

import store.model.ConfirmedPurchaseProducts;
import store.model.Product;
import store.dto.ProductToCalculateDTO;

public class OutputView {
    public static OutputView outputView;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public void printProducts() {
        System.out.println(OUT_WELCOME_MESSAGE);
        System.out.println(OUT_PRODUCT_SHOW_MESSAGE);
        System.out.println();
        for (Product product : Product.products.values()) {
            printProduct(product);
        }
        System.out.println();
    }

    public void printReceipt(final ConfirmedPurchaseProducts products) {
        printTotalProducts(products);
        printGifts(products);
        printPayment(products);
    }

    private void printPayment(final ConfirmedPurchaseProducts products) {
        System.out.println(OUT_RECEIPT_SEPARATION_LINE);
    System.out.printf(OUT_RECEIPT_TOTAL_PRICE + "%n", products.getQuantitySum(), String.format("%,8d", products.getTotalConsumePrice()));
    System.out.printf(OUT_RECEIPT_PROMOTION_DISCOUNT + "%n", String.format("%,d", products.getPromotionDiscountPrice()));
    System.out.printf(OUT_RECEIPT_MEMBERSHIP_DISCOUNT + "%n",String.format("%,d", products.getMembershipDiscountPrice()));
    System.out.printf(OUT_RECEIPT_PAY_MONEY + "%n",String.format("%,8d", products.getTotalPayment())
    );

    }

    private static void printGifts(final ConfirmedPurchaseProducts products) {
        System.out.println(OUT_RECEIPT_GIFT);
        for (ProductToCalculateDTO product : products.getProducts()) {
            if (product.getPromotionDiscountPrice() != 0) {
                System.out.printf(OUT_RECEIPT_GIFT_NAME_QUANTITY + "%n", product.getName(), product.getPromotionGetQuantity());
            }
        }
    }

    private void printTotalProducts(final ConfirmedPurchaseProducts products) {
        System.out.println(OUT_RECEIPT_HEADER);
        System.out.println(OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE);
        for (ProductToCalculateDTO product : products.getProducts()) {
            printProducts(product);
        }
    }

    private void printProducts(ProductToCalculateDTO product) {
        StringBuffer sb = new StringBuffer(product.getName());
        int productNameLength = product.getName().length();
        int tabCount = 3 - (productNameLength) / 4;
        for (int i = 0; i < tabCount; ++i) {
            sb.append("\t");
        }
        System.out.printf(OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE_IMPL + "%n", sb, product.getTotalQuantity(),
                String.format("%,d", product.getTotalPriceWithoutDiscount()));
    }

    private void printProduct(final Product product) {
        if (product.getPromotionName() == "") {
            toStringWithNoPromtion(product);
            return;
        }
        toStringWithPromotion(product);
        toStringWithNoPromtion(product);
    }

    private void toStringWithNoPromtion(final Product product) {
        System.out.printf(OUT_PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), String.format("%,d", product.getPrice()), getFormattedQuantity(product.getQuantity()), "");
    }
    private void toStringWithPromotion(final Product product) {
        System.out.printf(OUT_PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), String.format("%,d", product.getPrice()), getFormattedQuantity(product.getPromotionQuantity()), product.getPromotionName());
    }

    private String getFormattedQuantity(final int quantity) {
        if (quantity == 0) {
            return "재고 없음";
        }
        return quantity + "개";
    }
}


