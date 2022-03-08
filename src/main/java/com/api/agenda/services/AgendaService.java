package com.api.agenda.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.agenda.models.AgendaModel;
import com.api.agenda.repositories.AgendaRepository;



@Service
public class AgendaService {

final AgendaRepository agendaRepository;
	
	public AgendaService(AgendaRepository agendaRepository) {
		this.agendaRepository = agendaRepository;
	}

	@Transactional
	public AgendaModel save(AgendaModel agendaModel) {
		return agendaRepository.save(agendaModel)  ;
	}

	public List<AgendaModel> findAll() {
		return agendaRepository.findAll();
	}

	public Optional<AgendaModel> findById(UUID id) {
		return agendaRepository.findById(id);
	}

	@Transactional
	public void delete(AgendaModel agendaModel) {
		agendaRepository.delete(agendaModel);
		
	}
	
}
