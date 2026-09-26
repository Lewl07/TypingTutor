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
 * Typing tutor: an application that helps users learn to “touch type”
 * – i.e., type correctly without looking at the keyboard.
 * 
 * @author Léo Ho
 * Git Repo link: https://github.com/Lewl07/TypingTutor.git
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
        VBox root = new VBox(10);
        
        HBox r0 = new HBox(10);
        HBox r1 = new HBox(10);
        HBox r2 = new HBox(10);
        HBox r3 = new HBox(10);
        HBox rowNext = new HBox(10);
        HBox rowReset = new HBox(10);
        
        Label keyTyped = new Label();
        Label stats = new Label();
        
        Button next = new Button("Next");
        Label progress = new Label();
        rowNext.getChildren().addAll(next, progress);
        
        Button reset = new Button("Reset");
        rowReset.getChildren().add(reset);
        
        // Map is used to retrieve the initialized keys 
        Map<String, Button> keyMap = new HashMap<>();
        
        // Every necessary keys for the virtual keyboard
        String[] nums = {"1", "2", "3", "4", "5", "6", "7" , "8", "9", "0"};
        String[] r1Keys = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
        String[] r2Keys = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
        String[] r3Keys = {"Z", "X", "C", "V", "B", "N", "M", ",", ".", "Shift"};
        
        // for loops to initialize every keys of each row of the keyboard
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
        
        // Here comma and period are in row 3 and require if statements
        for (String key : r3Keys) {
            if (",".equals(key)) {
                Button commaBtn = new Button(",");
                commaBtn.setFocusTraversable(false);
                keyMap.put(KeyCode.COMMA.getName(), commaBtn);
                r3.getChildren().add(commaBtn);
            }
            
            if (".".equals(key)) {
                Button periodBtn = new Button(".");
                periodBtn.setFocusTraversable(false);
                keyMap.put(KeyCode.PERIOD.getName(), periodBtn);
                r3.getChildren().add(periodBtn);
            }
            
            if ((!".".equals(key)) && (!",".equals(key))) {
            Button keyBtn = new Button(key);
            keyBtn.setFocusTraversable(false);
            keyMap.put(key, keyBtn);
            r3.getChildren().add(keyBtn);
            }
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
        
        // Space is added at the bottom of the keyboard, hence
        // it has its own row
        HBox space = new HBox(10);
        space.getChildren().add(spaceBtn);
        r0.getChildren().add(backSpaceBtn);
     
        // Sample text to be written by user
        TextField expectedText = new TextField(sampleTexts[idx]);
        
        // User cannot change the content from "expectedText" nor click on it
        expectedText.setEditable(false);
        expectedText.setFocusTraversable(false);
        expectedText.setMouseTransparent(true);
        
        // The text field in which the user types
        TextField typedResponse = new TextField();
        
        // A listener paired with updateKeystrokeStats() used in order 
        // to display the correctness of the words typed by the user
        typedResponse.textProperty().addListener((obs, oldVal, newVal) ->
                updateKeystrokeStats(stats, newVal, expectedText.getText()));
        
        // Adding every HBox (rows with keys, next and reset buttons, and stats)
        // in VBox (which is the root)
        root.getChildren().addAll
        (expectedText, typedResponse, keyTyped, r0, r1, r2, r3, space, rowNext,
                rowReset, stats);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
        typedResponse.requestFocus();
        
        // Shows how close the user is from finishing the sample texts
        progress.setText((idx + 1) + " of " + sampleTexts.length);
        
        // Triggered as user click on "Next" button
        // Clears everything typed and displays the new text to be typed
        next.setOnAction(e -> {
            idx = (idx + 1) % sampleTexts.length;
            expectedText.setText(sampleTexts[idx]);
            typedResponse.clear();
            progress.setText((idx + 1) + " of " + sampleTexts.length);
            keyTyped.setText("");
            typedResponse.requestFocus();
        });
        
        // Triggered as user click on "Reset" button
        // Lands back to the first sample text and clears everything typed
        reset.setOnAction(e -> {
            idx = 0;
            expectedText.setText(sampleTexts[idx]);
            typedResponse.clear();
            progress.setText((idx + 1) + " of " + sampleTexts.length);
            keyTyped.setText("");
            typedResponse.requestFocus();
        });
        
        // Whenever user types, key changes color
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            // The character typed gets displayed live
            KeyCode keyCode = event.getCode();
            keyTyped.setText(keyCode.getName());
                        
            Button btn = keyMap.get(keyCode.getName());
            if (btn != null) {
                // The key becomes green when pressed
                keyTyped.setStyle("");
                btn.setStyle("-fx-background-color: lightgreen;");
            } else {
                // A key that is not handled will display a red text
                keyTyped.setText("Not handled");
                keyTyped.setStyle("-fx-text-fill: red;");     
            }
        });
        
        // Upon key released, key turns back to its original color
        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            KeyCode keyCode = event.getCode();
            
            Button btn = keyMap.get(keyCode.getName());
            if (btn != null) {
                btn.setStyle("");
                keyTyped.setStyle("");
            }
        });
    }
    
    /**
     * Displays the correctness of the words as the user types the sample text.
     * @param stats the stats displayed as the user types (correct and incorrect)
     * @param typedText the typed text by user
     * @param expectedText the text to be written by user
     */
    private void updateKeystrokeStats(Label stats, String typedText, String expectedText) {
        int correct = 0;
        int incorrect = 0;
 
        for (int i = 0; i < typedText.length(); i++) {
            if (i < expectedText.length()
                    && typedText.charAt(i) == expectedText.charAt(i)) {
                correct++;
            } else {
                incorrect++;
            }
        }
 
        stats.setText("Correct: " + correct + "   Incorrect: " + incorrect);
    }

    public static void main(String[] args) {
        launch();
    }

}