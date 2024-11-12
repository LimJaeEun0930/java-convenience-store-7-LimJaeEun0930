package store.config;

import static store.config.Constants.PRODUCTS_FILE;
import static store.config.Constants.PROMOTIONS_FILE;

import store.view.InputView;
import store.view.OutputView;

public class AppConfig {

    public static void run() {
        setPromotions();
        setProducts();
        OutputView.getInstance();
        InputView.getInstance();
    }

    private static void setPromotions() {
        PromotionManager.loadPromotionsFromFile(PROMOTIONS_FILE);
    }

    private static void setProducts() {
        ProductManager.loadProductsFromFile(PRODUCTS_FILE);
    }

}
