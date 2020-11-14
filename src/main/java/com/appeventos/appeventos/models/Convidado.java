package com.appeventos.appeventos.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="convidados")
public class Convidado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigoConvidado;
	
	@NotEmpty
	private String cpfConvidado;
	
	@NotEmpty
	private String nomeConvidado;
	
	// muitos convidados
	// para um evento
	@ManyToOne
	private Evento evento;
	
	public long getCodigoConvidado() {
		return codigoConvidado;
	}

	public void setCodigoConvidado(long codigoConvidado) {
		this.codigoConvidado = codigoConvidado;
	}
	
	public String getCpfConvidado() {
		return cpfConvidado;
	}

	public void setCpfConvidado(String cpfConvidado) {
		this.cpfConvidado = cpfConvidado;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
