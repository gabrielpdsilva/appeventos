package com.appeventos.appeventos.repository;

import org.springframework.data.repository.CrudRepository;
import com.appeventos.appeventos.models.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, String> {
	
	Usuario findByLogin(String login);

}
