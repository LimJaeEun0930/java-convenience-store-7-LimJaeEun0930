package store.config;

import static store.FileConstants.PRODUCTS_FILE;
import static store.FileConstants.PROMOTIONS_FILE;

public class AppConfig {

    public static void run() {
        setPromotions();
        setProducts();
    }

    private static void setPromotions() {
        PromotionManager.loadPromotionsFromFile(PROMOTIONS_FILE);
    }

    private static void setProducts() {
        ProductManager.loadProductsFromFile(PRODUCTS_FILE);
    }

}
