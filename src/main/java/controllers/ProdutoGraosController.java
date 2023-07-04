package controllers;


import application.Main;
import dao.GraosDAO;
import dao.UsuarioDAO;
import entidades.Graos;
import entidades.Usuario;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Alerts;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoGraosController extends ProdutosController {

    @FXML
    private TableColumn<Graos, Timestamp> colunaDataFinal;

    @FXML
    private TableColumn<Graos, Timestamp> colunaDataIncial;

    @FXML
    private TableColumn<Graos, Graos> colunaEditar;

    @FXML
    private TableColumn<Graos, String> colunaNome;

    @FXML
    private TableColumn<Graos, Double> colunaPreco;

    @FXML
    private TableColumn<Graos, Graos> colunaRemover;

    @FXML
    private TableColumn<Graos, Double> colunaUnidade;

    @FXML
    private TableView<Graos> tabelaProdutos;

    private GraosDAO graosDAO = new GraosDAO();
    private ObservableList<Graos> obsLista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaUnidade.setCellValueFactory(new PropertyValueFactory<>("sacas"));
        colunaDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataColheita"));
        colunaDataIncial.setCellValueFactory(new PropertyValueFactory<>("plantio"));
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
            graosDAO.createNewGrao(nome, preco, unidade, dataInicial, dataFinal, usuario);
        } catch (Exception e) {
            Alerts.mostrarNotificacao("Erro ao adicionar produto, verifique os campos", Alert.AlertType.WARNING);
        }
        atualizaTabela();
    }

    @Override
    void atualizaTabela() {
        List<Graos> MINHALISTADEMONIO = graosDAO.getAllGraos(Main.getInstance().getUsuarioLogado());
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

        colunaEditar.setCellFactory(param -> new TableCell<Graos, Graos>() {
            private final Button button = new Button("Vender");

            @Override
            protected void updateItem(Graos graos, boolean empty) {
                super.updateItem(graos, empty);
                if (graos == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> {
                            graosDAO.venderGraos(graos);
                            atualizaTabela();
                        }
                );
            }
        });
    }

    private void inicializaBotaoDeletar() {
        colunaRemover.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        colunaRemover.setCellFactory(param -> new TableCell<Graos, Graos>() {
            private final Button button = new Button("Deletar");

            @Override
            protected void updateItem(Graos graos, boolean empty) {
                super.updateItem(graos, empty);
                if (graos == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> {
                            graosDAO.remover(graos);
                            atualizaTabela();
                        }
                );
            }
        });
    }

}
