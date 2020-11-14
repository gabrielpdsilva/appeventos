package com.appeventos.appeventos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appeventos.appeventos.models.Convidado;
import com.appeventos.appeventos.models.Evento;
import com.appeventos.appeventos.repository.IConvidadoRepository;
import com.appeventos.appeventos.repository.IEventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private IEventoRepository er;
	
	@Autowired
	private IConvidadoRepository cr;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET) //get, vai retornar o formulario
	public String form() {
		return "eventos/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST) //post pra quando for salvar no BD
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos.");
			return "redirect:/cadastrarEvento";
		}
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento adicionado!");
		
		//apos salvar, vai redirecionar para o formulario
		return "redirect:/cadastrarEvento";
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
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET) //retorna o codigo de cada evento
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("eventos/detalhesEvento");
		mv.addObject("evento", evento);
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST) //retorna o codigo de cada evento
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		
		// validando dados lancados
		// pelo usuario
		if(result.hasErrors()) {
			// importante: o primeiro atributo, chamado 'mensagem',
			// é o que será passado na mensagemValida.html
			attributes.addFlashAttribute("mensagem", "Verifique os campos.");
			return "redirect:/{codigo}";
		}
			
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado!");
		return "redirect:/{codigo}";	
		
	}
	
	
}
