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
