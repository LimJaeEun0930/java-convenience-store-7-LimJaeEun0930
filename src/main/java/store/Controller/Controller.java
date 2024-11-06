package store.Controller;

import static store.view.InputView.inputView;
import static store.view.OutputView.outputView;

import java.util.List;
import store.Product;
import store.dto.ProductDTO;

public class Controller {

    public void run() {
        outputView.printProducts();
        List<ProductDTO> wishList = inputView.inputPurchaseProducts();
    }
}
