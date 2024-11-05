package store;

public class Application {
    public static void main(String[] args) {
        AppConfig.setPromotions();
        for (Promotion p : Promotion.promotions) {
            System.out.println(p);
        }
    }
}
