package controllers;


import application.Main;
import dao.CarnesDAO;
import dao.HortalicasDAO;
import entidades.Carnes;
import entidades.Hortalicas;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Date;

public class ProdutoCarnesController extends ProdutosController{

    @FXML
    private TableView<Carnes> TabelaProdutos;

    private CarnesDAO carnesDAO = new CarnesDAO();
    private ObservableList<Carnes> obsLista;

    @Override
    void adicionarClick(ActionEvent event) {
        String nome = nomeInput.getText();
        Double preco = Double.parseDouble(precoInput.getText());
        Double unidade = Double.parseDouble(unidadeInput.getText());
        Date dataInicial = new Date(String.valueOf(dataIncialInput));
        Date dataFinal = new Date(String.valueOf(dataFinalInput));
        String usuario = Main.getInstance().getUsuarioLogado();
        carnesDAO.createNewCarne(nome, preco, unidade, dataInicial, dataFinal, usuario);
    }

    @Override
    void atulizaTabela() {
        obsLista = (ObservableList<Carnes>) carnesDAO.getAllCarnes(Main.getInstance().getUsuarioLogado());
        TabelaProdutos.setItems(obsLista);
    }

}
