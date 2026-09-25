package leo.typingtutor;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        Button next = new Button("Next");
        
        Map<String, Button> keyMap = new HashMap<>();
        
        String[] r1Keys = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
        String[] r2Keys = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
        String[] r3Keys = {"Z", "X", "C", "V", "B", "N", "M", "Shift"};
        
        for (String key : r1Keys) {
            Button keyBtn = new Button(key);
            keyBtn.setFocusTraversable(false);
            keyMap.put(key, keyBtn);
            r1.getChildren().add(keyBtn);
        }
        
        for (String key : r2Keys) {
            Button keyBtn = new Button(key);
            keyBtn.setFocusTraversable(false);
            keyMap.put(key, keyBtn);
            r2.getChildren().add(keyBtn);
        }
        
        for (String key : r3Keys) {
            Button keyBtn = new Button(key);
            keyBtn.setFocusTraversable(false);
            keyMap.put(key, keyBtn);
            r3.getChildren().add(keyBtn);
        }
        
        TextField expectedText = new TextField("This is the text that is "
                + "expected to be typed");
        
        expectedText.setEditable(false);
        expectedText.setFocusTraversable(false);
        expectedText.setMouseTransparent(true);
        
        TextField typedResponse = new TextField();
        
        vb.getChildren().addAll
        (expectedText, typedResponse, keyTyped, r1, r2, r3, next);

        Scene scene = new Scene(vb, 600, 400);
        stage.setScene(scene);
        stage.show();
        typedResponse.requestFocus();
        
        // Displays the key typed
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            KeyCode keyCode = event.getCode();
            keyTyped.setText(keyCode.getName());
                        
            // The key becomes green when pressed
            Button btn = keyMap.get(keyCode.getName());
            if (btn != null) {
                keyTyped.setStyle("");
                btn.setStyle("-fx-background-color: lightgreen;");
            } else {
                keyTyped.setText("Not handled");
                keyTyped.setStyle("-fx-text-fill: red;");     
            }
        });
        
        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            KeyCode keyCode = event.getCode();
            
            Button btn = keyMap.get(keyCode.getName());
            if (btn != null) {
                btn.setStyle("");
                keyTyped.setStyle("");
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}