package entidades;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Hortalicas extends Produto{

    private Date dataColheita;
    private Date plantio;

    private Double quantidade;

    public Hortalicas() {

    }

    public Hortalicas(Integer id, String nome, Double quantidade, Date dataColheita, Date plantio) {
        super(id, nome);
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
}