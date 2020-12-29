package com.appeventos.appeventos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appeventos.appeventos.models.Usuario;
import com.appeventos.appeventos.repository.IUsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepository ur;
	
	@RequestMapping(value="/cadastrar-novo-usuario", method=RequestMethod.GET)
	public String formUsuario() {
		return "/cadastrar-usuario";
	}
	
	@RequestMapping(value="/cadastrar-novo-usuario", method=RequestMethod.POST)
	public String formUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Todos os campos devem ser preenchidos.");
			return "redirect:/cadastrar-novo-usuario";
		}
		
		if(usuario.getSenha().length() < 6) {
			attributes.addFlashAttribute("mensagem", "A senha deve ter pelo menos 6 caracteres.");
			return "redirect:/cadastrar-novo-usuario";
		}
		
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		ur.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuario adicionado!");
		return "redirect:/";
	}

}
