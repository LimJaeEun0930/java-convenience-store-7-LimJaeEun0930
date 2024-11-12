package store.view;

import static store.model.Product.products;
import static store.config.Constants.IN_ADDITIONAL_PROMO_OR_NOT;
import static store.config.Constants.IN_BUY_AGAIN;
import static store.config.Constants.IN_BUY_WITH_REGULAR_PRICE_OR_NOT;
import static store.config.Constants.IN_MEMBERSHIP_DISCOUNT_OR_NOT;
import static store.config.Constants.IN_PRODUCT_NAME_QUANTITY;
import static store.config.Constants.ERROR_IN_WRONG_INPUT_ETC;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import store.model.ConfirmedPurchaseProducts;
import store.dto.ProductDTO;
import store.dto.ProductToCalculateDTO;
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
                return ProductParser.pareseProducts(input(IN_PRODUCT_NAME_QUANTITY));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ERROR_IN_WRONG_INPUT_ETC);
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

    public boolean inputBuyAgainOrNot() {
        while (true) {
            String input = input(IN_BUY_AGAIN);
            if (input.equals("Y")) {
                return true;
            }
            if (input.equals("N")) {
                return false;
            }
            System.out.println(ERROR_IN_WRONG_INPUT_ETC);
        }
    }

    private boolean inputExceptQuantityOrNot(final ProductDTO wishProduct) {
        while (true) {
            String input = input(IN_BUY_WITH_REGULAR_PRICE_OR_NOT, wishProduct.getName(), calculateNoPromotionCount(wishProduct));
            if (input.equals("Y")) {
                return false;
            }
            if (input.equals("N")) {
                return wishProduct.decreaseQuantity(calculateNoPromotionCount(wishProduct));
            }
            System.out.println(ERROR_IN_WRONG_INPUT_ETC);
        }
    }

    private static int calculateNoPromotionCount(final ProductDTO wishProduct) {
        return wishProduct.getQuantity() % products.get(wishProduct.getName()).getPromotion().getPromoPack()
                + products.get(wishProduct.getName()).getPromotion().getPromoPack();
    }

    public boolean inputGetPromoItemOrNot(ProductDTO wishItem) {
        while (true) {
            String input = input(IN_ADDITIONAL_PROMO_OR_NOT, wishItem.getName(), products.get(wishItem.getName()).getPromotion().getGet());
            if (input.equals("Y")) {
                return wishItem.addPromoQuantity();
            }
            if (input.equals("N")) {
                return false;
            }
            System.out.println(ERROR_IN_WRONG_INPUT_ETC);
        }
    }

    private String input(String displayMessage, Object... args) {
        System.out.printf(displayMessage + "%n", args);
        String input = Console.readLine();
        return input;
    }

    public ConfirmedPurchaseProducts inputGetMemberShipDiscountOrNot(final ArrayList<ProductToCalculateDTO> productsToCalculate) {
        while (true) {
            String input = input(IN_MEMBERSHIP_DISCOUNT_OR_NOT);
            if (input.equals("Y")) {
                return new ConfirmedPurchaseProducts(true, productsToCalculate);
            }
            if (input.equals("N")) {
                return new ConfirmedPurchaseProducts(false, productsToCalculate);
            }
            System.out.println(ERROR_IN_WRONG_INPUT_ETC);
        }
    }
}
