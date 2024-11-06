package store.view;

import static store.config.Constants.IN_PRODUCT_NAME_QUANTITY;

import camp.nextstep.edu.missionutils.Console;
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

    public List<ProductDTO> inputPurchaseProducts() {
        while (true) {
            try {
                System.out.println(IN_PRODUCT_NAME_QUANTITY);
                return ProductParser.pareseProducts(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println("예외를 던져요~");
            }
        }
    }

}

