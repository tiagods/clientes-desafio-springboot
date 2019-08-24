package com.desafio.clientes.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.clientes.model.Cliente;
import com.desafio.clientes.model.Risco;
import com.desafio.clientes.repository.filter.ClienteFilter;
import com.desafio.clientes.service.ClienteService;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {
	private static final String CLIENTE_PESQUISA = "cliente/ClientePesquisa";
	private static final String CLIENTE_CADASTRO = "cliente/ClienteCadastro";

	@Autowired
	private ClienteService clientes;

	// requisicao passando id como parametro e recuperando objeto
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView(CLIENTE_CADASTRO);
		Cliente cliente = clientes.buscar(id);
		mav.addObject(cliente);
		return mav;
	}

	// metodo de exclusao
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo) {
		clientes.remover(codigo);
		return "redirect:/clientes";
	}

	// area de cadastro
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mdv = new ModelAndView(CLIENTE_CADASTRO);
		mdv.addObject(new Cliente());
		return mdv;
	}

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") ClienteFilter filtro) {
		List<Cliente> cliList = clientes.filtrar(filtro);
		ModelAndView mdv = new ModelAndView(CLIENTE_PESQUISA);
		mdv.addObject("clientes", cliList);
		return mdv;
	};

	// requisição de salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes redirect) {
		if (errors.hasErrors()) {
			return CLIENTE_CADASTRO;
		}
		redirect.addFlashAttribute("mensagem", "Salvo com sucesso!");
		clientes.salvar(cliente);
		return "redirect:/clientes/novo";
	}

	// retorna um arrays de status disponivel para a tela de pesquisa e cadastro
	@ModelAttribute("riscos")
	public List<Risco> listarRiscos() {
		return Arrays.asList(Risco.values());
	}
}
