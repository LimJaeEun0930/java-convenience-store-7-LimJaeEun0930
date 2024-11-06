package store.view;

import static store.Constants.PRODUCT_DISPLAY_FORMAT;
import static store.Constants.PRODUCT_SHOW_MESSAGE;
import static store.Constants.WELCOME_MESSAGE;

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
        System.out.println(WELCOME_MESSAGE);
        System.out.println(PRODUCT_SHOW_MESSAGE);
        System.out.println();ㅜ가
        for (Product product : Product.products) {
            System.out.printf(PRODUCT_DISPLAY_FORMAT + "%n", product.getName(), product.getPrice(), product.getQuantity(), product.getPromotion());
        }
        System.out.println();
    }

}
