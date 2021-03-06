package com.appeventos.appeventos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appeventos.appeventos.models.Convidado;
import com.appeventos.appeventos.models.Evento;
import com.appeventos.appeventos.models.Usuario;
import com.appeventos.appeventos.repository.IConvidadoRepository;
import com.appeventos.appeventos.repository.IEventoRepository;
import com.appeventos.appeventos.repository.IUsuarioRepository;

@Controller
public class EventoController {
	
	@Autowired
	private IEventoRepository er;
	
	@Autowired
	private IConvidadoRepository cr;
	
	@RequestMapping(value="/cadastrar-novo-evento", method=RequestMethod.GET) //get, vai retornar o formulario
	public String form() {
		return "eventos/cadastrar-evento";
	}
	
	@RequestMapping(value="/cadastrar-novo-evento", method=RequestMethod.POST) //post pra quando for salvar no BD
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			System.out.println(result.getErrorCount());
			System.out.println(result.getFieldErrorCount());
			System.out.println(result.hasErrors());
			
			attributes.addFlashAttribute("mensagem", "Verifique os campos.");
			return "redirect:/cadastrar-novo-evento";
		}
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento adicionado!");
		
		//apos salvar, vai redirecionar para o formulario
		return "redirect:/cadastrar-novo-evento";
	}
	
	@RequestMapping("/lista-de-eventos")
	public ModelAndView listarEventos() {
		
		// passamos qual pagina ele vai renderizar de acordo com os dados do evento
		ModelAndView mv = new ModelAndView("eventos/lista-de-eventos");
		
		// lista de eventos
		Iterable<Evento> eventos = er.findAll();
		
		// passando pra view. O primeiro parametro
		// eh aquele definido na view, ${}		
		mv.addObject("eventos", eventos);
		
		return mv;	
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET) //retorna o codigo de cada evento
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigoEvento(codigo);
		ModelAndView mv = new ModelAndView("eventos/detalhes-evento");
		mv.addObject("evento", evento);
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo) {
		Evento evento = er.findByCodigoEvento(codigo);
		er.delete(evento);
		return "redirect:/lista-de-eventos";
	}
	
}
