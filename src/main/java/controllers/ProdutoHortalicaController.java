package controllers;


import application.Main;
import dao.HortalicasDAO;
import dao.UsuarioDAO;
import entidades.Hortalicas;
import entidades.Usuario;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Alerts;
import utils.RestricoesTela;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoHortalicaController extends ProdutosController {

    @FXML
    private TableColumn<Hortalicas, Timestamp> colunaDataFinal;

    @FXML
    private TableColumn<Hortalicas, Timestamp> colunaDataIncial;

    @FXML
    private TableColumn<Hortalicas, Hortalicas> colunaEditar;

    @FXML
    private TableColumn<Hortalicas, String> colunaNome;

    @FXML
    private TableColumn<Hortalicas, Double> colunaPreco;

    @FXML
    private TableColumn<Hortalicas, Hortalicas> colunaRemover;

    @FXML
    private TableColumn<Hortalicas, Double> colunaUnidade;

    @FXML
    private TableView<Hortalicas> tabelaProdutos;

    private HortalicasDAO hortalicasDAO = new HortalicasDAO();
    private ObservableList<Hortalicas> obsLista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaUnidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataColheita"));
        colunaDataIncial.setCellValueFactory(new PropertyValueFactory<>("plantio"));

        RestricoesTela.setTextFieldDouble(precoInput);
        RestricoesTela.setTextFieldDouble(unidadeInput);
        atualizaTabela();
    }

    @Override
    void adicionarClick(ActionEvent event) {
        try {
            String nome = nomeInput.getText();
            Double preco = Double.parseDouble(precoInput.getText());
            Double unidade = Double.parseDouble(unidadeInput.getText());
            Date dataInicial = Date.from(dataIncialInput.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
            Date dataFinal = Date.from(dataFinalInput.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
            String usuario = Main.getInstance().getUsuarioLogado();
            hortalicasDAO.createNewHortalica(nome, preco, unidade, dataInicial, dataFinal, usuario);
        } catch (Exception e) {
            Alerts.mostrarNotificacao("Erro ao adicionar produto, verifique os campos", Alert.AlertType.WARNING);
        }


        atualizaTabela();
    }

    @Override
    void atualizaTabela() {
        List<Hortalicas> MINHALISTADEMONIO = hortalicasDAO.getAllHortalicas(Main.getInstance().getUsuarioLogado());
        obsLista = FXCollections.observableList(MINHALISTADEMONIO);
        tabelaProdutos.setItems(obsLista);
        inicializaBotaoVender();
        inicializaBotaoDeletar();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getUsuario(Main.getInstance().getUsuarioLogado());
        saldoTextLabel.setText("R$ " + usuario.getSaldo().toString());
    }

    private void inicializaBotaoVender() {
        colunaEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        colunaEditar.setCellFactory(param -> new TableCell<Hortalicas, Hortalicas>(){
            private final Button button = new Button("Vender");

            @Override
            protected void updateItem(Hortalicas hortalica, boolean empty) {
                super.updateItem(hortalica, empty);
                if(hortalica == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> {
                            hortalicasDAO.venderHortalica(hortalica);
                            atualizaTabela();
                        }
                );
            }
        });
    }

    private void inicializaBotaoDeletar() {
        colunaRemover.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        colunaRemover.setCellFactory(param -> new TableCell<Hortalicas, Hortalicas>(){
            private final Button button = new Button("Deletar");

            @Override
            protected void updateItem(Hortalicas hortalica, boolean empty) {
                super.updateItem(hortalica, empty);
                if(hortalica == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> {
                            hortalicasDAO.remover(hortalica);
                            atualizaTabela();
                        }
                );
            }
        });
    }
}
