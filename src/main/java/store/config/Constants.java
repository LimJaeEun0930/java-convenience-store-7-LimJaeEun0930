package store.config;

public class Constants {
    public static final String PROMOTIONS_FILE = "src/main/resources/promotions.md";
    public static final String PRODUCTS_FILE = "src/main/resources/products.md";

    public static final String OUT_WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    public static final String OUT_PRODUCT_SHOW_MESSAGE = "현재 보유하고 있는 상품입니다";
    public static final String OUT_PRODUCT_DISPLAY_FORMAT = "- %s %s원 %s %s";

    public static final String IN_PRODUCT_NAME_QUANTITY = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    public static final String IN_ADDITIONAL_PROMO_QUESTION = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    public static final String IN_BUY_WITH_REGULAR_PRICE_OR_NOT = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    public static final String IN_WRONG_INPUT_ETC = "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.";
}