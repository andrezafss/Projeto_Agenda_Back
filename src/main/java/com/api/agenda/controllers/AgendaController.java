package com.api.agenda.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.agenda.dtos.AgendaDto;
import com.api.agenda.models.AgendaModel;
import com.api.agenda.services.AgendaService;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agendas")
public class AgendaController {
	
	
final AgendaService agendaService;
	
	public AgendaController(AgendaService agendaService) {
		this.agendaService = agendaService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveAgenda(@RequestBody @Valid AgendaDto agendaDto){
			
		var agendaModel = new AgendaModel();
		BeanUtils.copyProperties(agendaDto, agendaModel);
		agendaModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.save(agendaModel));
	}
	
	@GetMapping
	public ResponseEntity<List<AgendaModel>> getAllAgendas(){
		return ResponseEntity.status(HttpStatus.OK).body(agendaService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneAgenda(@PathVariable(value = "id") UUID id){
		Optional<AgendaModel> agendaModelOptional = agendaService.findById(id);
		if(!agendaModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda not found");
			}
		return ResponseEntity.status(HttpStatus.OK).body(agendaModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAgenda(@PathVariable(value = "id") UUID id){
		Optional<AgendaModel> agendaModelOptional = agendaService.findById(id);
		if(!agendaModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda not found");
			}
		agendaService.delete(agendaModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluida com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAgenda(@PathVariable(value = "id") UUID id, @RequestBody @Valid AgendaDto agendaDto){
		Optional<AgendaModel> agendaModelOptional = agendaService.findById(id);
		if(!agendaModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda not found");
			}
		
		var agendaModel = new AgendaModel();
		BeanUtils.copyProperties(agendaDto, agendaModel);
		agendaModel.setId(agendaModelOptional.get().getId());
		agendaModel.setRegistrationDate(agendaModelOptional.get().getRegistrationDate());		
		
		return ResponseEntity.status(HttpStatus.OK).body(agendaService.save(agendaModel));
	
	
	}

}
