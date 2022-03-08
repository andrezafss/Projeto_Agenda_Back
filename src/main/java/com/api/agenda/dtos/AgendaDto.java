package com.api.agenda.dtos;

import javax.validation.constraints.NotBlank;



public class AgendaDto {
	
	@NotBlank
	private String tarefa;
	
	@NotBlank
	private String data;
	
	@NotBlank
	private String hora;

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}	
	
	
	

		

}
