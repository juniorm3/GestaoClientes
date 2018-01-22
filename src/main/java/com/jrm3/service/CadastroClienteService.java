package com.jrm3.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.jrm3.model.Cliente;
import com.jrm3.repository.Clientes;
import com.jrm3.util.Transacional;

public class CadastroClienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;

	@Transacional
	public void salvar(Cliente cliente) {
		clientes.guardar(cliente);
	}

	@Transacional
	public void excluir(Cliente cliente) {
		clientes.remover(cliente);
	}
}
