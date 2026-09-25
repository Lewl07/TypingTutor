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

    private final String[] sampleTexts = {
        "Try typing this text. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over white zinc boxes."
    };
    
    private int idx = 0;
    
    @Override
    public void start(Stage stage) {
        VBox vb = new VBox(10);
        
        HBox r0 = new HBox(10);
        HBox r1 = new HBox(10);
        HBox r2 = new HBox(10);
        HBox r3 = new HBox(10);
        
        Label keyTyped = new Label();
        Button next = new Button("Next");
        Label progress = new Label();
        
        Map<String, Button> keyMap = new HashMap<>();
        
        String[] nums = {"1", "2", "3", "4", "5", "6", "7" , "8", "9", "0"};
        String[] r1Keys = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
        String[] r2Keys = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
        String[] r3Keys = {"Z", "X", "C", "V", "B", "N", "M", "Shift"};
        
        for (String key : nums) {
            Button keyBtn = new Button(key);
            keyBtn.setFocusTraversable(false);
            keyMap.put(key, keyBtn);
            r0.getChildren().add(keyBtn);
        }
        
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
        
        // Space button
        Button spaceBtn = new Button("Space");
        spaceBtn.setFocusTraversable(false);
        spaceBtn.setPrefWidth(250);
        keyMap.put(KeyCode.SPACE.getName(), spaceBtn);
        
        // Backspace button
        Button backSpaceBtn = new Button("<---");
        backSpaceBtn.setFocusTraversable(false);
        backSpaceBtn.setPrefWidth(50);
        keyMap.put(KeyCode.BACK_SPACE.getName(), backSpaceBtn);
        
        HBox space = new HBox(10);
        space.getChildren().add(spaceBtn);
        r0.getChildren().add(backSpaceBtn);
     
        TextField expectedText = new TextField(sampleTexts[idx]);
        
        expectedText.setEditable(false);
        expectedText.setFocusTraversable(false);
        expectedText.setMouseTransparent(true);
        
        TextField typedResponse = new TextField();
        
        vb.getChildren().addAll
        (expectedText, typedResponse, keyTyped, r0, r1, r2, r3, space, next);

        Scene scene = new Scene(vb, 600, 400);
        stage.setScene(scene);
        stage.show();
        typedResponse.requestFocus();
        
        progress.setText((idx + 1) + " of " + sampleTexts.length);
        
        next.setOnAction(e -> {
            idx = (idx + 1);
            expectedText.setText(sampleTexts[idx]);
            typedResponse.clear();
            progress.setText((idx + 1) + " of " + sampleTexts.length);
            keyTyped.setText("");
            typedResponse.requestFocus();
        });
        
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