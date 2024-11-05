package store;

import static store.FileConstants.PRODUCTS_FILE;
import static store.FileConstants.PROMOTIONS_FILE;

public class AppConfig {

    public static void setPromotions() {
        PromotionManager.loadPromotionsFromFile(PROMOTIONS_FILE);
    }

    public static void setProducts() {
        ProductManager.loadProductsFromFile(PRODUCTS_FILE);
    }

}
