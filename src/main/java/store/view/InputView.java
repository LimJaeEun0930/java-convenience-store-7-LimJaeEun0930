package store.view;

import static store.Product.products;
import static store.Promotion.promotions;
import static store.config.Constants.IN_ADDITIONAL_PROMO_QUESTION;
import static store.config.Constants.IN_BUY_WITH_REGULAR_PRICE_OR_NOT;
import static store.config.Constants.IN_PRODUCT_NAME_QUANTITY;
import static store.config.Constants.IN_WRONG_INPUT_ETC;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import store.Product;
import store.dto.ProductDTO;
import store.parser.ProductParser;

public class InputView {
    public static InputView inputView;

    public static InputView getInstance() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    private InputView() {
    }

    public ArrayList<ProductDTO> inputPurchaseProducts() {
        while (true) {
            try {
                System.out.println(IN_PRODUCT_NAME_QUANTITY);
                return ProductParser.pareseProducts(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println("예외를 던져요~");
            }
        }
    }

    public void inputAdditionalPromoOption(ArrayList<ProductDTO> shoppingCart) {
        for (ProductDTO wishProduct : shoppingCart) {
            if (wishProduct.isBonusItemEligible()) {
                inputGetPromoItemOrNot(wishProduct);
            }
            if (wishProduct.isNotEnoughQuantityForPromo()) {
                inputExceptQuantityOrNot(wishProduct);
            }
        }
    }

    private boolean inputExceptQuantityOrNot(ProductDTO wishProduct) {
        while (true) {
            String input = input(IN_BUY_WITH_REGULAR_PRICE_OR_NOT, wishProduct.getName(), calculateNoPromotionCount(wishProduct));
            if (input.equals("Y")) {
                return false;
            }
            if (input.equals('N')) {
                return wishProduct.decreaseQuantity(calculateNoPromotionCount(wishProduct));
            }
            System.out.println(IN_WRONG_INPUT_ETC);
        }
    }

    private static int calculateNoPromotionCount(ProductDTO wishProduct) {
        return wishProduct.getQuantity() % products.get(wishProduct.getName()).getPromotion().getPromoPack()
                + products.get(wishProduct.getName()).getPromotion().getPromoPack();
    }

    public boolean inputGetPromoItemOrNot(ProductDTO wishItem) {
        while (true) {
            String input = input(IN_ADDITIONAL_PROMO_QUESTION, wishItem.getName(), products.get(wishItem.getName()).getPromotion().getGet());
            if (input.equals("Y")) {
                return wishItem.addPromoQuantity();
            }
            if (input.equals("N")) {
                return false;
            }
            System.out.println(IN_WRONG_INPUT_ETC);
        }
    }

    private String input(String displayMessage, Object... args) {
        System.out.printf(displayMessage + "%n", args);
        String input = Console.readLine();
        return input;
    }

}
feat: Add additional Question

1.입력한 상품이 프로모션 적용 가능한 상품이고, 고객이 해당수량보다 적게 가져온 경우
2.프로모션 재고가 부족해 일부 수량을 프로모션 혜택없이 결제해야 하는 경우
에 추가적인 질문을 하여 상품들의 총 구매 수량을 결정한다.
