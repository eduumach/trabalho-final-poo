package entidades;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Carnes extends Produto{

        private Date dataAbate;

        private Double kilos;

        private Date compraNascimento;

    public Carnes(Integer id, String nome, Date dataAbate, Double kilos, Date compraNascimento) {
        super(id, nome);
        this.dataAbate = dataAbate;
        this.kilos = kilos;
        this.compraNascimento = compraNascimento;
    }

    public Carnes() {

    }

    public Date getDataAbate() {
        return dataAbate;
    }

    public void setDataAbate(Date dataAbate) {
        this.dataAbate = dataAbate;
    }

    public Double getKilos() {
        return kilos;
    }

    public void setKilos(Double kilos) {
        this.kilos = kilos;
    }

    public Date getCompraNascimento() {
        return compraNascimento;
    }

    public void setCompraNascimento(Date compraNascimento) {
        this.compraNascimento = compraNascimento;
    }
}
