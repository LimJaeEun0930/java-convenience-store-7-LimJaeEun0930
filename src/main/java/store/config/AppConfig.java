package store.config;

import static store.Constants.PRODUCTS_FILE;
import static store.Constants.PROMOTIONS_FILE;

import store.view.OutputView;

public class AppConfig {

    public static void run() {
        setPromotions();
        setProducts();
        OutputView.getInstance();
    }

    private static void setPromotions() {
        PromotionManager.loadPromotionsFromFile(PROMOTIONS_FILE);
    }

    private static void setProducts() {
        ProductManager.loadProductsFromFile(PRODUCTS_FILE);
    }

}
