package com.jrm3.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jrm3.model.Cliente;
import com.jrm3.repository.Clientes;

@Named
@ViewScoped
public class GestaoClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	private List<Cliente> listaClientes;
	
	public void todosClientes() {
		listaClientes = clientes.todas();
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
}
