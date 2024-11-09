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
        ArrayList<ProductDTO> wishList = inputView.inputPurchaseProducts();
        searchProductPromotion(wishList);

    }

    private void searchProductPromotion(ArrayList<ProductDTO> wishList) {
        for (ProductDTO dto : wishList) {
            Product wishProduct = products.get(dto.getName());
            if (dto.getQuantity() % wishProduct.getPromotion().getPromoPack() == wishProduct.getPromotion().getBuy()) {
                evaluatePromotionEligibility(dto);
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
