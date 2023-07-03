package dao;

import entidades.Carnes;
import entidades.Graos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class GraosDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    private EntityManager entityManager = emf.createEntityManager();

    public void createNewGrao(String nome, Double preco, Double sacas, Date plantio, Date dataColheita, String usuario) {
        Graos grao = new Graos();
        grao.setNome(nome);
        grao.setPreco(preco);
        grao.setSacas(sacas);
        grao.setDataColheita(dataColheita);
        grao.setPlantio(plantio);
        grao.setUsuario(usuario);
        entityManager.getTransaction().begin();
        entityManager.persist(grao);
        entityManager.getTransaction().commit();
    }

    public List<Graos> getAllGraos(String usuario) {
        return entityManager.createQuery("SELECT g FROM Graos g WHERE g.usuario = :usuario")
                .setParameter("usuario", usuario)
                .getResultList();
    }
}
