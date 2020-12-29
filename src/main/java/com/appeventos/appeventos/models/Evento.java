package com.appeventos.appeventos.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="eventos")
public class Evento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigoEvento;
	
	@NotEmpty
	private String nomeEvento;
	
	@NotEmpty
	private String localEvento;

	@NotEmpty
	private String dataEvento;

	@NotEmpty
	private String horarioEvento;

	// necessario adicionar essa notacao, caso
	// contrario na hora de deleter um evento
	// havera um erro por causa das constraints
	@OneToMany(mappedBy="evento", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Convidado> convidados;

	public long getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(long codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getLocalEvento() {
		return localEvento;
	}

	public void setLocalEvento(String localEvento) {
		this.localEvento = localEvento;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getHorarioEvento() {
		return horarioEvento;
	}

	public void setHorarioEvento(String horarioEvento) {
		this.horarioEvento = horarioEvento;
	}

	public List<Convidado> getConvidados() {
		return convidados;
	}

	public void setConvidados(List<Convidado> convidados) {
		this.convidados = convidados;
	}
	
	
}