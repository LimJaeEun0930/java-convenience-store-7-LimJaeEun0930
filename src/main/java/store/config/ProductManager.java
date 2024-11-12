package store.config;

import static store.config.Constants.ERROR_CANNOT_READ_FILE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import store.model.Product;

public class ProductManager {

    static void loadProductsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Product.readProduct(line);
            }
        } catch (IOException e) {
            System.out.println(ERROR_CANNOT_READ_FILE);
        }
    }
}
