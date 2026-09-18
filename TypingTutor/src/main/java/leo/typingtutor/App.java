package leo.typingtutor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox vb = new VBox(10);
        
        HBox r1 = new HBox(10);
        HBox r2 = new HBox(10);
        HBox r3 = new HBox(10);
        
        String[] r1Keys = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
        String[] r2Keys = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
        String[] r3Keys = {"Z", "X", "C", "V", "B", "N", "M"};
    }

    public static void main(String[] args) {
        launch();
    }

}