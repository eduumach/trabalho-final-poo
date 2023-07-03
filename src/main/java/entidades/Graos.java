package entidades;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
public class Graos extends Produto{

    private Double sacas;

    private Date dataColheita;

    private Date plantio;

    public Graos() {

    }

    public Graos(Integer id, String nome, Double preco, String usuario, Double sacas, Date dataColheita, Date plantio) {
        super(id, nome, preco, usuario);
        this.sacas = sacas;
        this.dataColheita = dataColheita;
        this.plantio = plantio;
    }

    public Double getSacas() {
        return sacas;
    }

    public void setSacas(Double sacas) {
        this.sacas = sacas;
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
}
