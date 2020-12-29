package com.appeventos.appeventos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appeventos.appeventos.models.Convidado;
import com.appeventos.appeventos.models.Evento;
import com.appeventos.appeventos.repository.IConvidadoRepository;

@Controller
public class ConvidadoController {
	
	@Autowired
	private IConvidadoRepository cr;
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(long codigoConvidado) {
		Convidado convidado = cr.findByCodigoConvidado(codigoConvidado);
		cr.delete(convidado);
		Evento evento = convidado.getEvento();
		long codigoEvento = evento.getCodigoEvento();
		String codigo = "" + codigoEvento;
		return "redirect:/" + codigo;
	}

}
