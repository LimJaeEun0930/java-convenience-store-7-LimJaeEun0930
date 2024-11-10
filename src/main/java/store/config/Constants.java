package store.config;
//[콜라-3],[에너지바-5]
public class Constants {
    public static final String PROMOTIONS_FILE = "src/main/resources/promotions.md";
    public static final String PRODUCTS_FILE = "src/main/resources/products.md";

    public static final String OUT_WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    public static final String OUT_PRODUCT_SHOW_MESSAGE = "현재 보유하고 있는 상품입니다";
    public static final String OUT_PRODUCT_DISPLAY_FORMAT = "- %s %s원 %s %s";
    public static final String OUT_RECEIPT_HEADER =                         "==============W 편의점================";
    public static final String OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE =    "상품명\t\t\t\t수량\t\t  금액";
    //public static final String OUT_RECEIPT_PRODUCT_NAME_QUANTITY_PRICE_IMPL = "%-20s%-10d%s\t\t\t%d \t%s";
    public static final String OUT_RECEIPT_PRODUCT_NAME = "%s              "; //1글자 2스페이스. (2,18) (4,14)
    public static final String OUT_RECEIPT_PRODUCT_QUANTITY = "%d\t\t";
    public static final String OUT_RECEIPT_PRODUCT_PRICE = "%s";
    public static final String OUT_RECEIPT_GIFT = "=============증\t    정===============";
    public static final String OUT_RECEIPT_GIFT_NAME_QUANTITY = "%s\t\t\t\t\t%d";

public static final String OUT_RECEIPT_SEPARATION_LINE = "====================================";
public static final String OUT_RECEIPT_TOTAL_PRICE = "총구매액\t\t\t\t%d\t\t%s";
public static final String OUT_RECEIPT_PROMOTION_DISCOUNT = "행사할인\t\t\t\t\t\t  -%s";
public static final String OUT_RECEIPT_MEMBERSHIP_DISCOUNT = "멤버십할인\t\t\t\t\t\t  -%s";
public static final String OUT_RECEIPT_PAY_MONEY = "내실돈\t\t\t\t\t\t%s";

    public static final String IN_PRODUCT_NAME_QUANTITY = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    public static final String IN_ADDITIONAL_PROMO_OR_NOT = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    public static final String IN_BUY_WITH_REGULAR_PRICE_OR_NOT = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    public static final String IN_MEMBERSHIP_DISCOUNT_OR_NOT = "멤버십 할인을 받으시겠습니까? (Y/N)";
    public static final String IN_WRONG_INPUT_ETC = "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.";
    public static final String IN_BUY_AGAIN = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    public static final String NON_EXIST_PRODUCT = "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.";
    public static final String CANNOT_READ_FILE = "[ERROR] 파일을 불러들이지 못했습니다.";
    public static final String CANNOT_BUY_PRODUCT_OVER_QUANTITY = "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";
}
//=======
//콜라                  3         3,000
//에너지바                5