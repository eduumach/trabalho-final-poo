package entidades;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Agenda {
	
	private List<Contato> contatos;
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
	private EntityManager em = emf.createEntityManager();
	
	
	public Agenda() {
		this.contatos = new ArrayList<>();			
	}
	

	public void removeContato(Contato contato) {
		Contato c = em.find(Contato.class, contato.getId());
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}

	public void adicionaContato(String nome, String telefone) {
		Contato c = new Contato(nome, telefone);
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		//contatos.add(c);		
	}

	/*private void listarAgenda() {
		System.out.println("Lista de Contatos");
		for (Contato contato : this.agenda) {
			System.out.println(contato);
		}
	}*/

	public void editarContato(String nome, String telefone, Contato contatoEdicao) {		
		contatoEdicao.setNome(nome);
		contatoEdicao.setTelefone(telefone);
		em.getTransaction().begin();
		em.merge(contatoEdicao);
		em.getTransaction().commit();		
	}	

	@SuppressWarnings("unchecked")
	public List<Contato> buscarContato(String nomeOuTelefone) {
		return em.createQuery("Select a from Contato a where a.nome like :nome or a.telefone like :telefone ")
				.setParameter("nome", "%"+nomeOuTelefone+"%")
				.setParameter("telefone", "%"+nomeOuTelefone+"%").getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> buscarTodos(){
		return em.createQuery("FROM " + Contato.class.getName()).getResultList();
	}

	public List<Contato> getContatos() {
		return contatos;
	}


	
	
}
