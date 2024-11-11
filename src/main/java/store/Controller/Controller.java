package store.Controller;

import static store.Product.isInPromotionDuration;
import static store.Product.products;
import static store.view.InputView.inputView;
import static store.view.OutputView.outputView;

import java.util.ArrayList;
import store.ConfirmedPurchaseProducts;
import store.Product;
import store.dto.ProductDTO;
import store.dto.ProductToCalculateDTO;

public class Controller {

    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
        outputView.printProducts();
        ArrayList<ProductDTO> shoppingCart = inputView.inputPurchaseProducts();
        checkPromotionWithPromptIfNecessary(shoppingCart);
        ArrayList<ProductToCalculateDTO> productsToApply = getProductsToApply(shoppingCart);
        applyDecreasementToQuantity(productsToApply);
        ConfirmedPurchaseProducts confirmedPurchaseProducts = inputView.inputGetMemberShipDiscountOrNot(productsToApply);
        keepGoing = lastProcess(confirmedPurchaseProducts);
        }
    }

    private void applyDecreasementToQuantity(ArrayList<ProductToCalculateDTO> productsToApply) {
        for (ProductToCalculateDTO productDelta : productsToApply) {
            Product product = products.get(productDelta.getName());
            product.applyDecreasement(productDelta);
        }
    }
    private static boolean lastProcess(ConfirmedPurchaseProducts confirmedPurchaseProducts) {
        boolean keepGoing;
        outputView.printReceipt(confirmedPurchaseProducts);
        keepGoing = inputView.inputBuyAgainOrNot();
        return keepGoing;
    }

    private void checkPromotionWithPromptIfNecessary(ArrayList<ProductDTO> shoppingCart) {
        searchProductPromotion(shoppingCart);
        inputView.inputAdditionalPromoOption(shoppingCart);
    }

    private ArrayList<ProductToCalculateDTO> getProductsToApply(ArrayList<ProductDTO> shoppingCart) {
        ArrayList<ProductToCalculateDTO> productsToApply = new ArrayList<>();
        for (ProductDTO productToBuy : shoppingCart) {
            ProductToCalculateDTO productToApply = ProductToCalculateDTO.getInstance(productToBuy);
            productsToApply.add(productToApply);
        }
        return productsToApply;
    }

    private void searchProductPromotion(ArrayList<ProductDTO> shoppingCart) {
        for (ProductDTO dto : shoppingCart) {
            Product wishProduct = products.get(dto.getName());
            if (wishProduct.getPromotion() != null && isInPromotionDuration(wishProduct)) {
                if (dto.getQuantity() % wishProduct.getPromotion().getPromoPack() == wishProduct.getPromotion().getBuy() || dto.getQuantity() > wishProduct.getPromotionQuantity()) {
                    evaluatePromotionEligibility(dto);
                }
            }
        }
    }


    private void evaluatePromotionEligibility(ProductDTO dto) {
        Product wishProduct = products.get(dto.getName());
        if (dto.getQuantity() + wishProduct.getPromotion().getGet() <= wishProduct.getPromotionQuantity()) {
            dto.setBonusItemEligible();
            return;
        }
        if (dto.getQuantity() + wishProduct.getPromotion().getGet() > wishProduct.getPromotionQuantity()) {
            dto.setNotEnoughQuantityForPromo();
        }
    }

}
