package controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import entidades.Agenda;
import entidades.Contato;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import utils.Alerts;
import utils.Utils;

public class TelaAgendaController implements Initializable {

    @FXML
    private TableView<Contato> TabelaContatos;

    @FXML
    private Button botaoAdicionarContato;

    @FXML
    private Button botaoBuscar;

    @FXML
    private Button botaoCancelar;

    @FXML
    private TextField bucaInput;

    @FXML
    private TableColumn<Contato, Contato> colunaEditar;
    
    @FXML
    private TableColumn<Contato, Contato> colunaRemover;

    @FXML
    private TableColumn<Contato, String> colunaNome;

    @FXML
    private TableColumn<Contato, String> colunaTelefone;

    @FXML
    private TextField nomeInput;

    @FXML
    private TextField telefoneInput;
    
    private Agenda agenda = new Agenda();
    
    private ObservableList<Contato> obsLista;
    
    private Contato contatoEdicao;

    @FXML
    void adicionarContato(ActionEvent event) {
    	String nome = nomeInput.getText();
    	String telefone = telefoneInput.getText();
    	if(contatoEdicao == null) {    		
        	agenda.adicionaContato(nome, telefone);        	
    	}else {    		
    		agenda.editarContato(nome, telefone,contatoEdicao);  
    		contatoEdicao = null;
    	}
    	atualizaTabela(agenda.buscarTodos());    	
    	limpaFormulario();

    }   

	@FXML
    void buscaContato(ActionEvent event) {
		busca();
    }	
	
	@FXML
    void buscaContato2(KeyEvent event) {
		busca();
    }

    @FXML
    void cancelarEdicao(ActionEvent event) {
    	Utils.palcoCorrente(event).close();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		List<Contato> lista = agenda.buscarTodos();
		atualizaTabela(lista);
		
	}
	
	private void limpaFormulario() {
		nomeInput.setText("");
		telefoneInput.setText("");		
	}

	private void atualizaTabela(List<Contato> lista) {
		obsLista = FXCollections.observableList(lista);
		TabelaContatos.setItems(obsLista);
		inicializaBotaoEditar();
		inicalizaBotaoDeletar();
		contatoEdicao = null;
	}	

	private void inicializaBotaoEditar() {
		colunaEditar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		colunaEditar.setCellFactory(param -> new TableCell<Contato, Contato>(){
			private final Button botao = new Button("editar");
			@Override
			protected void updateItem(Contato obj, boolean vazio) {
				super.updateItem(obj, vazio);
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(botao);
				botao.setOnAction(event -> inseriCotatoEdicao(obj));
			}
			private Object inseriCotatoEdicao(Contato obj) {
				contatoEdicao = obj;
				nomeInput.setText(contatoEdicao.getNome());
				telefoneInput.setText(contatoEdicao.getTelefone());
				return contatoEdicao;
			}
		});		
		
	}
	
	private void inicalizaBotaoDeletar() {
		colunaRemover.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		colunaRemover.setCellFactory(param -> new TableCell<Contato, Contato>(){
			private final Button botao = new Button("remover");
			@Override			
			protected void updateItem(Contato obj, boolean vazio) {
				super.updateItem(obj, vazio);
				if(obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(botao);
				botao.setOnAction(event -> removerContato(obj));
			}			
		});
		
	}
	
	private void removerContato(Contato obj) {
		Optional<ButtonType> resultado = Alerts.mostarConfirmacao("Confirmação",
				"Você tem certeza que deseja remover o Contato");
		
		if(resultado.get() == ButtonType.OK) {
			agenda.removeContato(obj);
			atualizaTabela(agenda.buscarTodos());
		}
	}

	private void busca() {
		String busca = bucaInput.getText();
		List<Contato> contatosBusca = agenda.buscarContato(busca);
		atualizaTabela(contatosBusca);
	}

}
