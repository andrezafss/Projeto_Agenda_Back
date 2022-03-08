package com.api.agenda.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.agenda.models.AgendaModel;



public interface AgendaRepository extends JpaRepository<AgendaModel, UUID> {
	

}
