package com.jrm3.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jrm3.model.Cliente;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Clientes() {
		// TODO Auto-generated constructor stub
	}

	public Clientes(EntityManager manager) {
		this.manager = manager;
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

	public List<Cliente> pesquisar(String nome) {
		String jpql = "from Cliente where nome like : nome";

		TypedQuery<Cliente> query = manager.createQuery(jpql, Cliente.class);

		query.setParameter("nome", Cliente.class);

		return query.getResultList();
	}

	public List<Cliente> todas() {
		return manager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = porId(cliente.getId());
		manager.remove(cliente);
	}
}
