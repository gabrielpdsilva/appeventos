package com.appeventos.appeventos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appeventos.appeventos.models.Convidado;
import com.appeventos.appeventos.models.Evento;
import com.appeventos.appeventos.repository.IConvidadoRepository;
import com.appeventos.appeventos.repository.IEventoRepository;

@Controller
public class ConvidadoController {
	
	@Autowired
	private IConvidadoRepository cr;
	
	@Autowired
	private IEventoRepository er;
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(long codigoConvidado) {
		Convidado convidado = cr.findByCodigoConvidado(codigoConvidado);
		cr.delete(convidado);
		Evento evento = convidado.getEvento();
		long codigoEvento = evento.getCodigoEvento();
		String codigo = "" + codigoEvento;
		return "redirect:/" + codigo;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST) //retorna o codigo de cada evento
	public String adicionarConvidado(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		
		// validando dados lancados
		// pelo usuario
		if(result.hasErrors()) {
			// importante: o primeiro atributo, chamado 'mensagem',
			// é o que será passado na mensagemValida.html
			attributes.addFlashAttribute("mensagem", "Verifique os campos.");
			return "redirect:/{codigo}";
		}
			
		Evento evento = er.findByCodigoEvento(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado!");
		return "redirect:/{codigo}";	
		
	}

}
