package controllers;


import application.Main;
import dao.GraosDAO;
import dao.HortalicasDAO;
import entidades.Graos;
import entidades.Hortalicas;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Date;

public class ProdutoGraosController extends ProdutosController{

    @FXML
    private TableView<Graos> TabelaProdutos;

    private GraosDAO graosDAO = new GraosDAO();
    private ObservableList<Graos> obsLista;

    @Override
    void adicionarClick(ActionEvent event) {
        String nome = nomeInput.getText();
        Double preco = Double.parseDouble(precoInput.getText());
        Double unidade = Double.parseDouble(unidadeInput.getText());
        Date dataInicial = new Date(String.valueOf(dataIncialInput));
        Date dataFinal = new Date(String.valueOf(dataFinalInput));
        String usuario = Main.getInstance().getUsuarioLogado();
        graosDAO.createNewGrao(nome, preco, unidade, dataInicial, dataFinal, usuario);
    }

    @Override
    void atulizaTabela() {
        obsLista = (ObservableList<Graos>) graosDAO.getAllGraos(Main.getInstance().getUsuarioLogado());
        TabelaProdutos.setItems(obsLista);
    }

}
