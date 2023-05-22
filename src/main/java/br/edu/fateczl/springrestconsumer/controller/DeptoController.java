package br.edu.fateczl.springrestconsumer.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.edu.fateczl.springrestconsumer.model.Depto;
import br.edu.fateczl.springrestconsumer.uteis.HttpConnection;

@Controller
public class DeptoController {

	private final String sURL = "http://localhost:8080/springrest/api/depto";

	@Autowired
	HttpConnection httpCon;

	@RequestMapping(name = "depto", value = "/depto", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("depto");
	}

	@RequestMapping(name = "depto", value = "/depto", method = RequestMethod.POST)
	public ModelAndView alunos(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cod = allRequestParam.get("codigo");
		String nome = allRequestParam.get("nome");
		String botao = allRequestParam.get("botao");

		List<Depto> lista = new ArrayList<>();
		Depto depto = new Depto();
		String erro = "";
		String saida = "";

		if (!cod.equals("")) {
			depto.setCodigo(Integer.parseInt(cod));
			depto.setNome(nome);
		}

		try {
			if (botao.equalsIgnoreCase("listar")) {
				String json = httpCon.getOp(sURL, "");
				Gson gson = new Gson();
				Type deptoTipo = new TypeToken<ArrayList<Depto>>() {}.getType();
				lista = gson.fromJson(json, deptoTipo);
				depto = null;
			}
			if (botao.equalsIgnoreCase("inserir")) {
				saida = httpCon.sendOp(sURL, RequestMethod.POST, depto);
				depto = null;
			}
			if (botao.equalsIgnoreCase("atualizar")) {
				saida = httpCon.sendOp(sURL, RequestMethod.PUT, depto);
				depto = null;
			}
			if (botao.equalsIgnoreCase("excluir")) {
				saida = httpCon.sendOp(sURL, RequestMethod.DELETE, depto);
				depto = null;
			}
			if (botao.equalsIgnoreCase("buscar")) {
				String json = httpCon.getOp(sURL, "/" + depto.getCodigo());
				Gson gson = new Gson();
				depto = gson.fromJson(json, Depto.class);
			}
		} catch (IOException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("saida", saida);
			model.addAttribute("depto", depto);
			model.addAttribute("lista", lista);
		}
		return new ModelAndView("depto");

	}

}
