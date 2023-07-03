package controllers;

import java.io.IOException;

import application.Main;
import exception.LoginErroException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.Alerts;
import utils.Utils;

public class TelaLoginController {

	@FXML
	private Button botaoCancelar;

	@FXML
	private Button botaoLogin;

	@FXML
	private PasswordField senha;

	@FXML
	private TextField usuario;

	@FXML
	void fazerLogin(ActionEvent event) {
		try {
			String usuarioLogin = usuario.getText();
			String senhaLogin = senha.getText();
			if (usuarioLogin.equals("roberto") && senhaLogin.equals("betinho123")) {
				setJanela("/janelas/telaAgenda.fxml");
			} else {
				throw new LoginErroException();
			}
		} catch (LoginErroException e) {
			Alerts.mostrarNotificacao(e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	
	@FXML
	void cancelarLogin(ActionEvent event) {
		Utils.palcoCorrente(event).close();
	}

	private synchronized void setJanela(String nomeJanela) {

		try {
			Main.alteraScena(nomeJanela, 500, 450);

		} catch (IOException e) {
			Alerts.mostrarNotificacao(e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	
}
