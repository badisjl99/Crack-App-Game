package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller implements Initializable {

    Random rand = new Random();
    @FXML
    private TextField input1, input2, input3;
    @FXML
    private Button checkButton;
    @FXML
    private Label hint1;
    @FXML
    private Label label1, label2, label3;
  
    private ArrayList<String> prevInput = new ArrayList<>(3);

    private void verify() {
        ArrayList<String> input = getInputNUmbers();
        ArrayList<String> randInt = getGeneratedRandom();
        System.out.println(randInt.get(0)+randInt.get(1)+randInt.get(0));
        if (input.equals(prevInput)) {
            // If input hasn't changed since last check, show the previous result
            return;
        }
        prevInput = input;

        // Compare input and random numbers and set hint labels
        for (int i = 0; i < input.size(); i++) {
            String inputDigit = input.get(i);
            String randDigit = randInt.get(i);
            if (inputDigit.equals(randDigit)) {
                // Digit is in the correct position
                setLabel(i, inputDigit);
            } else if (randInt.contains(inputDigit)) {
                // Digit is not in the correct position but exists in the generated string
                setLabel(i, "_");
            } else {
                // Digit is not in the generated string
                setLabel(i, "X");
            }
        }
    }

    private void lockCorrectLabels() {
        label1.setDisable(false);
        label2.setDisable(false);
        label3.setDisable(false);
    }

    private void setLabel(int index, String text) {
        switch (index) {
            case 0:
                label1.setText(text);
                if (text.equals("_")) {
                    label1.setTextFill(Color.YELLOW);
                } else {
                    label1.setTextFill(Color.BLACK);
                }
                break;
            case 1:
                label2.setText(text);
                if (text.equals("_")) {
                    label2.setTextFill(Color.YELLOW);
                } else {
                    label2.setTextFill(Color.BLACK);
                }
                break;
            case 2:
                label3.setText(text);
                if (text.equals("_")) {
                    label3.setTextFill(Color.YELLOW);
                } else {
                    label3.setTextFill(Color.BLACK);
                }
                break;
            default:
                break;
        }
    }

    private void buttonsActions() {
        checkButton.setOnMouseClicked(e -> {
            verify();
        });
    }

    private ArrayList<String> getInputNUmbers() {
        String in1 = input1.getText();
        String in2 = input2.getText();
        String in3 = input3.getText();
        ArrayList<String> input = new ArrayList<>(3);
        input.add(0, in1);
        input.add(1, in2);
        input.add(2, in3);
        return input;
    }

    public ArrayList<String> getGeneratedRandom() {
        ArrayList<String> local = new ArrayList<>(3);
        int num1 = rand.nextInt(9);
        int num2 = rand.nextInt(9);
        int num3 = rand.nextInt(9);
        local.add(0, Integer.toString(num1));
        local.add(1, Integer.toString(num2));
        local.add(2, Integer.toString(num3));
        return local;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        buttonsActions();
        lockCorrectLabels();
    }

}
