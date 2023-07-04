package utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RestricoesTela {

    public static void setTextFieldDouble(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
                txt.setText(oldValue);
            }
        });
    }
}
