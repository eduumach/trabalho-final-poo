package dao;

import entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class UsuarioDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    private EntityManager entityManager = emf.createEntityManager();
    

    public boolean validaUser(String usuario, String senha) {
        return entityManager.createQuery("Select a from Usuario a where a.usuario = :usuario and a.senha = :senha")
                .setParameter("usuario", usuario)
                .setParameter("senha", senha).getResultList().size() > 0;
    }

    public boolean existeUser(String usuario) {
        return entityManager.createQuery("Select a from Usuario a where a.usuario = :usuario")
                .setParameter("usuario", usuario).getResultList().size() > 0;
    }

    public Usuario getUsuario(String usuario) {
        return (Usuario) entityManager.createQuery("Select a from Usuario a where a.usuario = :usuario")
                .setParameter("usuario", usuario).getSingleResult();
    }

    public void createNewUser(String usuarioLogin, String senhaLogin) {
        Usuario user = new Usuario();
        user.setUsuario(usuarioLogin);
        user.setSenha(senhaLogin);
        user.setSaldo(0.0);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void depositar(String usuario, Double valor) {
        Usuario user = getUsuario(usuario);
        user.setSaldo(user.getSaldo() + valor);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
}
