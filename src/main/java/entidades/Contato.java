package entidades;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contato implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;		
	private String nome;
	private String telefone;
	private String email;
	//private Date datanascimento;
	GregorianCalendar dataNascimento;
	private String endereco;
	private Boolean estaBloqueado;
	
	public Contato() {}
	
	//metodo construtor simplificado
	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
		this.estaBloqueado = false;		
	}


	//metodo construtor completo
	public Contato(String nome, String telefone, String email, GregorianCalendar dataNascimento, String endereco) {		
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.estaBloqueado = false;
	}


	//metodo getter e setter
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public GregorianCalendar getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(GregorianCalendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Boolean getEstaBloqueado() {
		return this.estaBloqueado;
	}
	
	public void setEstaBloqueado(Boolean estaBloqueado) {
		this.estaBloqueado = estaBloqueado;
	}
	
	public Integer getId() {
		return this.id;
	}


	@Override
	public String toString() {
		return "Contato [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", endereco=" + endereco + ", estaBloqueado=" + estaBloqueado + "]";
	}


	
	
	
	
	
	
	
	
	

}
