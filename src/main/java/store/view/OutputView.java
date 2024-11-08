package store.view;

import static store.config.Constants.OUT_PRODUCT_DISPLAY_FORMAT;
import static store.config.Constants.OUT_PRODUCT_SHOW_MESSAGE;
import static store.config.Constants.OUT_WELCOME_MESSAGE;

import store.Product;

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


    private void printProduct(Product product) {
        if (product.getPromotionName() == "") {
            toStringWithNoPromtion(product);
            return;
        }
        toStringWithPromotion(product);
        toStringWithNoPromtion(product);
    }

    private void toStringWithNoPromtion(Product product) {
        System.out.printf(OUT_PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), product.getPrice(), getFormattedQuantity(product.getQuantity()), "");
    }
    private void toStringWithPromotion(Product product) {
        System.out.printf(OUT_PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), product.getPrice(), getFormattedQuantity(product.getPromotionQuantity()), product.getPromotionName());
    }

    private String getFormattedQuantity(int quantity) {
        if (quantity == 0) {
            return "재고없음";
        }
        return quantity + "개";
    }
}


