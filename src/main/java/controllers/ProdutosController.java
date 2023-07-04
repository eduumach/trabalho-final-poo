package controllers;

import application.Main;
import dao.UsuarioDAO;
import entidades.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utils.Alerts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class ProdutosController implements Initializable {

    @FXML
    private TableView<?> tabelaProdutos;

    @FXML
    private Button adicionar;

    @FXML
    private Button cancelar;

    @FXML
    private Button carnes;

    @FXML
    private TableColumn<?, String> colunaDataFinal;

    @FXML
    private TableColumn<?, String> colunaDataIncial;

    @FXML
    private TableColumn<?, ?> colunaEditar;

    @FXML
    private TableColumn<?, String> colunaNome;

    @FXML
    private TableColumn<?, String> colunaPreco;

    @FXML
    private TableColumn<?, ?> colunaRemover;

    @FXML
    private TableColumn<?, String> colunaUnidade;

    @FXML
    protected DatePicker dataFinalInput;

    @FXML
    protected Label dataFinalTextLabel;

    @FXML
    protected DatePicker dataIncialInput;

    @FXML
    private Label dataIncialTextLabel;

    @FXML
    private Button graos;

    @FXML
    private Button hortalicas;

    @FXML
    protected TextField nomeInput;

    @FXML
    protected TextField precoInput;

    @FXML
    protected Label saldoTextLabel;

    @FXML
    protected TextField unidadeInput;

    @FXML
    private Label unidadeTextLabel;

    private ObservableList<?> obsLista;

    protected Usuario usuario;

    protected UsuarioDAO usuarioDAO = new UsuarioDAO();




    @FXML
    void adicionarClick(ActionEvent event) {
    }

    @FXML
    void cancelarClick(ActionEvent event) {
        limparCampos();
    }

    @FXML
    void carnesClick(ActionEvent event) {
        setJanela("/janelas/ProdutoCarne.fxml");
    }

    @FXML
    void graosClick(ActionEvent event) {
        setJanela("/janelas/ProdutoGraos.fxml");
    }

    @FXML
    void hortalicasClick(ActionEvent event) {
        setJanela("/janelas/ProdutoHortalica.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizaTabela();
    }

    private void limparCampos() {
        nomeInput.setText("");
        precoInput.setText("");
        unidadeInput.setText("");
        dataIncialInput.setValue(null);
        dataFinalInput.setValue(null);
    }

    void atualizaTabela() {
        
    }

    protected synchronized void setJanela(String nomeJanela) {

        try {
            Main.alteraScena(nomeJanela,  653, 768);

        } catch (IOException e) {
            Alerts.mostrarNotificacao(e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


}
