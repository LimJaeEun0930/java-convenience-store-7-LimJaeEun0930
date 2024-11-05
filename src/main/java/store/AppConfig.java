package store;

import static store.FileConstants.PROMOTION_FILE;

public class AppConfig {

    public static void setPromotions() {
        PromotionManager.loadPromotionsFromFile(PROMOTION_FILE);
    }

}
