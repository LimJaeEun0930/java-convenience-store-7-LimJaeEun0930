package store;

import store.controller.Controller;
import store.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig.run();
        Controller controller = new Controller();
        controller.run();
//
    }
}
