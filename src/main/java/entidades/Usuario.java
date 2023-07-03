package entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String usuario;
	private String senha;

	private Double saldo;

	public Usuario() {}

	public Usuario(Integer id, String usuario, String senha, Double saldo) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
