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

//	@OneToMany(mappedBy = "graos")
//	private List<Graos> graos;
//
//	@OneToMany(mappedBy = "carnes")
//	private List<Carnes> carnes;
//
//	@OneToMany(mappedBy = "hotalicas")
//	private List<Hortalicas> hotalicas;
//
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

//	public List<Graos> getGraos() {
//		return graos;
//	}
//
//	public void setGraos(List<Graos> graos) {
//		this.graos = graos;
//	}
//
//	public List<Carnes> getCarnes() {
//		return carnes;
//	}
//
//	public void setCarnes(List<Carnes> carnes) {
//		this.carnes = carnes;
//	}

//	public List<Hortalicas> getHotalicas() {
//		return hotalicas;
//	}

//	public void setHotalicas(List<Hortalicas> hotalicas) {
//		this.hotalicas = hotalicas;
//	}

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
