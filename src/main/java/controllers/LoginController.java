package controllers;

import java.io.IOException;

import application.Main;
import dao.UsuarioDAO;
import exception.CadastroErroException;
import exception.LoginErroException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.Alerts;

public class LoginController {

    @FXML
    private Button cadastro;

    @FXML
    private Button entrar;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();


    @FXML
    void fazerLogin(ActionEvent event) {
        try {
            validaLogin();
        } catch (LoginErroException e) {
            Alerts.mostrarNotificacao(e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    void validaLogin() throws LoginErroException {

        String usuarioLogin = usuario.getText();
        String senhaLogin = senha.getText();
        if (usuarioDAO.validaUser(usuarioLogin, senhaLogin)) {
            Main.getInstance().setUsuarioLogado(usuarioLogin);
            setJanela("/janelas/ProdutoHortalica.fxml");
        } else {
            throw new LoginErroException();
        }
    }

    @FXML
    void verificaEnter(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                validaLogin();
            }
        } catch (LoginErroException e) {
            Alerts.mostrarNotificacao(e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    @FXML
    void fazerCadastro() {
        try {
            String usuarioLogin = usuario.getText();
            String senhaLogin = senha.getText();
            if (usuarioDAO.existeUser(usuarioLogin)) {
                throw new CadastroErroException();
            } else {
                usuarioDAO.createNewUser(usuarioLogin, senhaLogin);
                Main.getInstance().setUsuarioLogado(usuarioLogin);
                setJanela("/janelas/ProdutoHortalica.fxml");
            }
        } catch (CadastroErroException e) {
            Alerts.mostrarNotificacao(e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private synchronized void setJanela(String nomeJanela) {

        try {
            Main.alteraScena(nomeJanela, 653, 768);

        } catch (IOException e) {
            Alerts.mostrarNotificacao(e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
