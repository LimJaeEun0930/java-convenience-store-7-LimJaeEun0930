package store.Controller;

import static store.Product.products;
import static store.view.InputView.inputView;
import static store.view.OutputView.outputView;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.ArrayList;
import store.ConfirmedPurchaseProducts;
import store.Product;
import store.config.ProductManager;
import store.dto.ProductDTO;
import store.dto.ProductToCalculateDTO;

public class Controller {

    public void run() { //////////////////TODO: ProductManager.updateProductFile 구현해야함!!!!
        boolean keepGoing = true;
        while (keepGoing) {
        outputView.printProducts();
        ArrayList<ProductDTO> shoppingCart = inputView.inputPurchaseProducts();
        checkPromotionWithPromptIfNecessary(shoppingCart);
        ArrayList<ProductToCalculateDTO> productsToApply = getProductsToApply(shoppingCart);
        ProductManager.updateProductFile(productsToApply);
        ConfirmedPurchaseProducts confirmedPurchaseProducts = inputView.inputGetMemberShipDiscountOrNot(productsToApply);
        keepGoing = lastProcess(confirmedPurchaseProducts);
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

    public static boolean isInPromotionDuration(Product wishProduct) {
        if (wishProduct.getPromotion() != null) {
            return DateTimes.now().isAfter(wishProduct.getPromotion().getStartDate()) && DateTimes.now()
                    .isBefore(wishProduct.getPromotion().getEndDate());
        }
        return false;
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
