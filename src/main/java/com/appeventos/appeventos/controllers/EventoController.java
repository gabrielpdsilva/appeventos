package com.appeventos.appeventos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.appeventos.appeventos.models.Evento;
import com.appeventos.appeventos.repository.IEventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private IEventoRepository er;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET) //get, vai retornar o formulario
	public String form() {
		return "eventos/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST) //post pra quando for salvar no BD
	public String form(Evento evento) {
		er.save(evento);
		return "redirect:/cadastrarEvento"; //apos salvar, vai redirecionar para o formulario
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listarEventos() {
		
		// passamos qual pagina ele vai renderizar de acordo com os dados do evento
		ModelAndView mv = new ModelAndView("index");
		
		// lista de eventos
		Iterable<Evento> eventos = er.findAll();
		
		// passando pra view. O primeiro parametro
		// eh aquele definido na view, ${}		
		mv.addObject("eventos", eventos);
		
		return mv;
		
	}

}
