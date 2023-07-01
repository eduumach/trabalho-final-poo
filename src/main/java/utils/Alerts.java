package utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {
	
	public static void mostrarNotificacao(String mensagem, AlertType tipo) {

		Alert notificacao = new Alert(tipo);
		notificacao.setTitle("Notificação");
		notificacao.setHeaderText(null);
		notificacao.setContentText(mensagem);
		notificacao.show();
	}
	
	public static Optional<ButtonType> mostarConfirmacao(String titulo, String conteudo){
		Alert confirmacao = new Alert(AlertType.CONFIRMATION);
		confirmacao.setTitle(titulo);
		confirmacao.setHeaderText(null);
		confirmacao.setContentText(conteudo);
		return confirmacao.showAndWait();
	}

	
}
