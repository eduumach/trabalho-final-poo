package dao;

import entidades.Carnes;
import entidades.Graos;
import entidades.Hortalicas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class CarnesDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    private EntityManager entityManager = emf.createEntityManager();

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void createNewCarne(String nome, Double preco, Double kilos, Date compraNascimento, Date dataAbate, String usuario) {
        Carnes carne = new Carnes();
        carne.setNome(nome);
        carne.setPreco(preco);
        carne.setDataAbate(dataAbate);
        carne.setKilos(kilos);
        carne.setCompraNascimento(compraNascimento);
        carne.setUsuario(usuario);
        entityManager.getTransaction().begin();
        entityManager.persist(carne);
        entityManager.getTransaction().commit();
    }

    public List<Carnes> getAllCarnes(String usuario) {
        return entityManager.createQuery("SELECT h FROM Carnes h WHERE h.usuario = :usuario", Carnes.class)
                .setParameter("usuario", usuario).getResultList();
    }


    public void venderCarnes(Carnes carnes) {
        usuarioDAO.depositar(carnes.getUsuario(), carnes.getPreco() * carnes.getKilos());
        entityManager.getTransaction().begin();
        entityManager.remove(carnes);
        entityManager.getTransaction().commit();
    }

    public void remover(Carnes carnes) {
        entityManager.getTransaction().begin();
        entityManager.remove(carnes);
        entityManager.getTransaction().commit();
    }
}
