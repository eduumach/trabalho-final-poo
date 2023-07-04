package controllers;


import application.Main;
import dao.CarnesDAO;
import dao.UsuarioDAO;
import entidades.Carnes;
import entidades.Usuario;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoCarnesController extends ProdutosController{

    @FXML
    private TableColumn<Carnes, Timestamp> colunaDataFinal;

    @FXML
    private TableColumn<Carnes, Timestamp> colunaDataIncial;

    @FXML
    private TableColumn<Carnes, Carnes> colunaEditar;

    @FXML
    private TableColumn<Carnes, String> colunaNome;

    @FXML
    private TableColumn<Carnes, Double> colunaPreco;

    @FXML
    private TableColumn<Carnes, Carnes> colunaRemover;

    @FXML
    private TableColumn<Carnes, Double> colunaUnidade;

    @FXML
    private TableView<Carnes> tabelaProdutos;

    private CarnesDAO CarnesDAO = new CarnesDAO();
    private ObservableList<Carnes> obsLista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaUnidade.setCellValueFactory(new PropertyValueFactory<>("kilos"));
        colunaDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataAbate"));
        colunaDataIncial.setCellValueFactory(new PropertyValueFactory<>("compraNascimento"));
        atualizaTabela();
    }

    @Override
    void adicionarClick(ActionEvent event) {
        String nome = nomeInput.getText();
        Double preco = Double.parseDouble(precoInput.getText());
        Double unidade = Double.parseDouble(unidadeInput.getText());
        Date dataInicial = Date.from(dataIncialInput.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        Date dataFinal = Date.from(dataFinalInput.getValue().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        String usuario = Main.getInstance().getUsuarioLogado();
        CarnesDAO.createNewCarne(nome, preco, unidade, dataInicial, dataFinal, usuario);
        atualizaTabela();
    }

    @Override
    void atualizaTabela() {
        List<Carnes> MINHALISTADEMONIO = CarnesDAO.getAllCarnes(Main.getInstance().getUsuarioLogado());
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

        colunaEditar.setCellFactory(param -> new TableCell<Carnes, Carnes>(){
            private final Button button = new Button("Vender");

            @Override
            protected void updateItem(Carnes Carnes, boolean empty) {
                super.updateItem(Carnes, empty);
                if(Carnes == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> {
                            CarnesDAO.venderCarnes(Carnes);
                            atualizaTabela();
                        }
                );
            }
        });
    }

    private void inicializaBotaoDeletar() {
        colunaRemover.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        colunaRemover.setCellFactory(param -> new TableCell<Carnes, Carnes>(){
            private final Button button = new Button("Deletar");

            @Override
            protected void updateItem(Carnes Carnes, boolean empty) {
                super.updateItem(Carnes, empty);
                if(Carnes == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> {
                            CarnesDAO.remover(Carnes);
                            atualizaTabela();
                        }
                );
            }
        });
    }

}
