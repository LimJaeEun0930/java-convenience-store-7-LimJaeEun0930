package store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProductManager {

    public static void loadProductsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Product.readProduct(line);
            }
        } catch (IOException e) {
            System.out.println("에러가 났어요~");
        }
    }
}
