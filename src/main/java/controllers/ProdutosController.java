package controllers;

import entidades.Carnes;
import entidades.Graos;
import entidades.Hortalicas;
import entidades.Usuario;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ProdutosController {

    @FXML
    private Button adicionar;

    @FXML
    private HBox adicionarProduto;

    @FXML
    private HBox boxLists;

    @FXML
    private Button cancelar;

    @FXML
    private Button carnes;

    @FXML
    private Button graos;

    @FXML
    private Button hortalicas;

    @FXML
    private TextField saldo;

    @FXML
    private Label lblUsuario;

    private ObservableList<Carnes> carnesList;
    private ObservableList<Graos> graosList;
    private ObservableList<Hortalicas> hortalicasList;


}
