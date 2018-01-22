package com.jrm3.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jrm3.model.Cliente;
import com.jrm3.model.TipoPessoa;
import com.jrm3.repository.Clientes;
import com.jrm3.util.FacesMassages;

@Named
@ViewScoped
public class GestaoClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	@Inject
	private FacesMassages messages;
	
	private List<Cliente> listaClientes;
	
	private String termoPesquisa;
	
	@Inject
	private Cliente cliente;
	
	public void pesquisar() {
		listaClientes = clientes.pesquisar(termoPesquisa);
		
		if(listaClientes.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
	}
	
	public void todosClientes() {
		listaClientes = clientes.todas();
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoPessoa[] getTiposCliente() {
		return TipoPessoa.values();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
 	
}
