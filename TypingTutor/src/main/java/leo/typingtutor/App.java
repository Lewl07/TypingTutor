package leo.typingtutor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
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
        
        Label keyTyped = new Label();
        
        String[] r1Keys = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
        String[] r2Keys = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
        String[] r3Keys = {"Z", "X", "C", "V", "B", "N", "M", "Shift"};
        
        for (int i = 0; i < r1Keys.length; i++) {
            Button key = new Button("" + r1Keys[i]);
            r1.getChildren().add(key);
        }
        
        for (int i = 0; i < r2Keys.length; i++) {
            Button key = new Button("" + r2Keys[i]);
            r2.getChildren().add(key);
        }
        
        for (int i = 0; i < r3Keys.length; i++) {
            Button key = new Button("" + r3Keys[i]);
            r3.getChildren().add(key);
        }
        
        TextField expectedText = new TextField("This is the text that is "
                + "expected to be typed");
        TextField typedResponse = new TextField();
        
        vb.getChildren().addAll(expectedText, typedResponse, keyTyped, r1, r2, r3);
        
        Scene scene = new Scene(vb, 600, 400);
        stage.setScene(scene);
        stage.show();
        
        // Displays the key typed
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            keyTyped.setText("" + keyCode);
            
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void main(String[] args) {
        launch();
    }

}