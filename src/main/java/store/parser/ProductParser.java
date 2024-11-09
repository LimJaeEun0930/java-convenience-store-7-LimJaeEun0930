package store.parser;

import static store.Product.products;

import java.util.ArrayList;
import java.util.List;
import store.Product;
import store.dto.ProductDTO;

public class ProductParser {

    public static ArrayList<ProductDTO> pareseProducts(String str) throws IllegalArgumentException{
        ArrayList<ProductDTO> productsToBuy = new ArrayList<>();
        String[] parts = str.split(",");
        for (String part : parts) {
            productsToBuy.add(getProductDTO(part));
            }
        return productsToBuy;
    }

    private static ProductDTO getProductDTO(String fragmentaion) throws IllegalArgumentException {
        String name =  fragmentaion.substring(1,fragmentaion.indexOf('-'));
        int quantity = Integer.parseInt(fragmentaion.substring(fragmentaion.indexOf('-') + 1, fragmentaion.length() - 1));
        if (products.get(name) != null && (products.get(name).getQuantity() + products.get(name).getPromotionQuantity()) >= quantity && quantity > 0) {
            return new ProductDTO(name, quantity);
        }
        throw new IllegalArgumentException();
    }
}
