package entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
