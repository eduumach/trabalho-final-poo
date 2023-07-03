package controllers;


import application.Main;
import dao.HortalicasDAO;
import entidades.Hortalicas;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;

public class ProdutoHoralicaController extends ProdutosController{

    @FXML
    private TableColumn<Hortalicas, DatePicker> colunaDataFinal;

    @FXML
    private TableColumn<Hortalicas, DatePicker> colunaDataIncial;

    @FXML
    private TableColumn<Hortalicas, Hortalicas> colunaEditar;

    @FXML
    private TableColumn<Hortalicas, String> colunaNome;

    @FXML
    private TableColumn<Hortalicas, String> colunaPreco;

    @FXML
    private TableColumn<Hortalicas, Hortalicas> colunaRemover;

    @FXML
    private TableColumn<Hortalicas, String> colunaUnidade;

    @FXML
    private TableView<Hortalicas> TabelaProdutos;

    private HortalicasDAO hortalicasDAO = new HortalicasDAO();
    private ObservableList<Hortalicas> obsLista;

    @Override
    void adicionarClick(ActionEvent event) {
        String nome = nomeInput.getText();
        Double preco = Double.parseDouble(precoInput.getText());
        Double unidade = Double.parseDouble(unidadeInput.getText());
        Date dataInicial = Date.from(dataIncialInput.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        Date dataFinal = Date.from(dataFinalInput.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        String usuario = Main.getInstance().getUsuarioLogado();
        hortalicasDAO.createNewHortalica(nome, preco, unidade, dataInicial, dataFinal, usuario);
        atulizaTabela();
    }

    @Override
    void atulizaTabela() {
        obsLista = FXCollections.observableList(hortalicasDAO.getAllHortalicas(Main.getInstance().getUsuarioLogado()));
        TabelaProdutos.setItems(obsLista);
        inicializaBotaoVender();
        inicializaBotaoDeletar();
        usuario = usuarioDAO.getUsuario(Main.getInstance().getUsuarioLogado());
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
                            hortalicasDAO.remover(hortalica);
                            atulizaTabela();
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
                            atulizaTabela();
                        }
                );
            }
        });
    }


}
