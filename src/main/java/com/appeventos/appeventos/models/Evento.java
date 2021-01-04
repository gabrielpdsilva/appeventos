package com.appeventos.appeventos.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(name = "codigo_evento")
	private long codigoEvento;
	
	@NotEmpty
	@Column(name = "nome_evento")
	private String nomeEvento;
	
	@NotEmpty
	@Column(name = "local_evento")
	private String localEvento;

	@NotEmpty
	@Column(name = "data_evento")
	private String dataEvento;

	@NotEmpty
	@Column(name = "horario_evento")
	private String horarioEvento;

	// necessario adicionar essa notacao, caso
	// contrario na hora de deletar um evento
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