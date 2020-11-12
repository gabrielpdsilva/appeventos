package com.appeventos.appeventos.repository;

import org.springframework.data.repository.CrudRepository;

import com.appeventos.appeventos.models.Convidado;
import com.appeventos.appeventos.models.Evento;

public interface IConvidadoRepository extends CrudRepository<Convidado, String> {

}
