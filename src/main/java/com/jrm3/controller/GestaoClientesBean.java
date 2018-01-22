package com.jrm3.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.jrm3.model.Cliente;
import com.jrm3.model.TipoPessoa;
import com.jrm3.repository.Clientes;
import com.jrm3.service.CadastroClienteService;
import com.jrm3.util.FacesMassages;

@Named
@ViewScoped
public class GestaoClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	@Inject
	private FacesMassages messages;

	@Inject
	private CadastroClienteService cadastroClienteService;

	private List<Cliente> listaClientes;

	private String termoPesquisa;

	private Cliente cliente;

	public void prepararNovoCliente() {
		cliente = new Cliente();
	}

	public void salvar() {
		cadastroClienteService.salvar(cliente);

		atualizarRegitros();

		messages.info("Cliente salvo com sucesso!");

		RequestContext.getCurrentInstance().update(Arrays.asList("frm:clienteDataTable", "frm:messages"));
	}


	public void excluir() {
		cadastroClienteService.excluir(cliente);
		
		cliente = null;
		
		atualizarRegitros();
		
		messages.info("Cliente excluido com sucesso!");
	}

	public void pesquisar() {
		listaClientes = clientes.pesquisar(termoPesquisa);

		if (listaClientes.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
	}

	public void todosClientes() {
		listaClientes = clientes.todas();
	}

	private void atualizarRegitros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todosClientes();
		}
		
	}
	
	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isClienteSeleciondo() {
		return cliente != null && cliente.getId() != null;
	}

}
