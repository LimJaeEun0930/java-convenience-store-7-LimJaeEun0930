package store.Controller;

import static store.Product.products;
import static store.view.InputView.inputView;
import static store.view.OutputView.outputView;

import java.util.ArrayList;
import store.Product;
import store.dto.ProductDTO;

public class Controller {

    public void run() {
        outputView.printProducts();
        ArrayList<ProductDTO> shoppingCart = inputView.inputPurchaseProducts();
        searchProductPromotion(shoppingCart);
        inputView.inputAdditionalPromoOption(shoppingCart);


    }

    private void searchProductPromotion(ArrayList<ProductDTO> shoppingCart) {
        for (ProductDTO dto : shoppingCart) {
            Product wishProduct = products.get(dto.getName());
            if (wishProduct.getPromotion() != null) {
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
