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
            System.out.printf(OUT_PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), product.getPrice(), product.getQuantity(), product.getPromotion());
        }
        System.out.println();
    }

}
