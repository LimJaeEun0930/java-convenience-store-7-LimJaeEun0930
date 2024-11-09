package store;

import static store.view.OutputView.outputView;

import store.Controller.Controller;
import store.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig.run();
        Controller controller = new Controller();
        controller.run();
//
    }
}
