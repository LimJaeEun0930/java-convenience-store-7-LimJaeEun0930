package store.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import store.model.Promotion;

public class PromotionManager {

    static void loadPromotionsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine(); // 첫 번째 줄 (헤더) 무시
            while ((line = reader.readLine()) != null) {
                Promotion.readPromtion(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
