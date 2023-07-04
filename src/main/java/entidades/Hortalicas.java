package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Hortalicas extends Produto implements Serializable{
    private static final long serialVersionUID = 1L;

    private Date dataColheita;
    private Date plantio;

    private Double quantidade;

    public Hortalicas() {
    }

    public Hortalicas(Integer id, String nome, Double preco, String usuario, Date dataColheita, Date plantio, Double quantidade) {
        super(id, nome, preco, usuario);
        this.dataColheita = dataColheita;
        this.plantio = plantio;
        this.quantidade = quantidade;
    }

    public Date getDataColheita() {
        return dataColheita;
    }

    public void setDataColheita(Date dataColheita) {
        this.dataColheita = dataColheita;
    }

    public Date getPlantio() {
        return plantio;
    }

    public void setPlantio(Date plantio) {
        this.plantio = plantio;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
