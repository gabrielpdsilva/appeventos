package com.appeventos.appeventos.repository;

import com.appeventos.appeventos.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface IEventoRepository extends CrudRepository<Evento, String> {
	
	Evento findByCodigo(long codigo);

}
