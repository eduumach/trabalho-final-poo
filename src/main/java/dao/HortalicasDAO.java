package dao;

import entidades.Hortalicas;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;


public class HortalicasDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    private EntityManager entityManager = emf.createEntityManager();

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void createNewHortalica(String nome, Double preco, Double quantidade, Date dataColheita, Date plantio, String usuario) {
        Hortalicas hortalica = new Hortalicas();
        hortalica.setNome(nome);
        hortalica.setPreco(preco);
        hortalica.setQuantidade(quantidade);
        hortalica.setDataColheita(dataColheita);
        hortalica.setPlantio(plantio);
        hortalica.setUsuario(usuario);
        entityManager.getTransaction().begin();
        entityManager.persist(hortalica);
        entityManager.getTransaction().commit();
    }

    public List<Hortalicas> getAllHortalicas(String usuario) {
        return entityManager.createQuery("SELECT h FROM Hortalicas h WHERE h.usuario = :usuario", Hortalicas.class)
                .setParameter("usuario", usuario).getResultList();
    }


    public void venderHortalica(Hortalicas hortalica) {
        usuarioDAO.depositar(hortalica.getUsuario(), hortalica.getPreco() * hortalica.getQuantidade());
        entityManager.getTransaction().begin();
        entityManager.remove(hortalica);
        entityManager.getTransaction().commit();
    }

    public void remover(Hortalicas hortalica) {
        entityManager.getTransaction().begin();
        entityManager.remove(hortalica);
        entityManager.getTransaction().commit();
    }
}
