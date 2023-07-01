package utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage palcoCorrente(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

}
