package store.view;

import static store.config.Constants.OUT_PRODUCT_DISPLAY_FORMAT;
import static store.config.Constants.OUT_PRODUCT_SHOW_MESSAGE;
import static store.config.Constants.OUT_RECEIPT_GIFT;
import static store.config.Constants.OUT_RECEIPT_GIFT_NAME_QUANTITY;
import static store.config.Constants.OUT_RECEIPT_HEADER;
import static store.config.Constants.OUT_RECEIPT_MEMBERSHIP_DISCOUNT;
import static store.config.Constants.OUT_RECEIPT_PAY_MONEY;
import static store.config.Constants.OUT_RECEIPT_PRODUCT_NAME;
import static store.config.Constants.OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE;
//import static store.config.Constants.OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE_IMPL;
import static store.config.Constants.OUT_RECEIPT_PRODUCT_PRICE;
import static store.config.Constants.OUT_RECEIPT_PRODUCT_QUANTITY;
import static store.config.Constants.OUT_RECEIPT_PROMOTION_DISCOUNT;
import static store.config.Constants.OUT_RECEIPT_SEPARATION_LINE;
import static store.config.Constants.OUT_RECEIPT_TOTAL_PRICE;
import static store.config.Constants.OUT_WELCOME_MESSAGE;

import java.text.DecimalFormat;
import java.util.ArrayList;
import store.ConfirmedPurchaseProducts;
import store.Product;
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

    public void printReceipt(ConfirmedPurchaseProducts products) {
        printTotalProducts(products);
        printGifts(products);
        printPayment(products);
    }

    private void printPayment(ConfirmedPurchaseProducts products) {
        System.out.println(OUT_RECEIPT_SEPARATION_LINE);
    System.out.printf(OUT_RECEIPT_TOTAL_PRICE + "%n", products.getQuantitySum(), String.format("%,8d", products.getTotalConsumePrice()));
    System.out.printf(OUT_RECEIPT_PROMOTION_DISCOUNT + "%n", String.format("%,d", products.getPromotionDiscountPrice()));
    System.out.printf(OUT_RECEIPT_MEMBERSHIP_DISCOUNT + "%n",String.format("%,d", products.getMembershipDiscountPrice()));
    System.out.printf(OUT_RECEIPT_PAY_MONEY + "%n",String.format("%,8d", products.getTotalPayment())
    );

    }

    private static void printGifts(ConfirmedPurchaseProducts products) {
        System.out.println(OUT_RECEIPT_GIFT);
        for (ProductToCalculateDTO product : products.getProducts()) {
            if (product.getPromotionDiscountPrice() != 0) {
                System.out.printf(OUT_RECEIPT_GIFT_NAME_QUANTITY + "%n", product.getName(), product.getPromotionGetQuantity());
            }
        }
    }

    private void printTotalProducts(ConfirmedPurchaseProducts products) {
        System.out.println(OUT_RECEIPT_HEADER);
        System.out.println(OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE);
        DecimalFormat formatter = new DecimalFormat("###,###");
        for (ProductToCalculateDTO product : products.getProducts()) {
            System.out.printf(OUT_RECEIPT_PRODUCT_NAME, product.getName());
            System.out.printf(OUT_RECEIPT_PRODUCT_QUANTITY, product.getTotalQuantity());
            System.out.printf(OUT_RECEIPT_PRODUCT_PRICE + "%n", formatter.format(product.getTotalPriceWithoutDiscount()));
        }
    }

    private void printProduct(Product product) {
        if (product.getPromotionName() == "") {
            toStringWithNoPromtion(product);
            return;
        }
        toStringWithPromotion(product);
        toStringWithNoPromtion(product);
    }

    private void toStringWithNoPromtion(Product product) {
        System.out.printf(OUT_PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), String.format("%,d", product.getPrice()), getFormattedQuantity(product.getQuantity()), "");
    }
    private void toStringWithPromotion(Product product) {
        System.out.printf(OUT_PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), String.format("%,d", product.getPrice()), getFormattedQuantity(product.getPromotionQuantity()), product.getPromotionName());
    }

    private String getFormattedQuantity(int quantity) {
        if (quantity == 0) {
            return "재고 없음";
        }
        return quantity + "개";
    }
}


