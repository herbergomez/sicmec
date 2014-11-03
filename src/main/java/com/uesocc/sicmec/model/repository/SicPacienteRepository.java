package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicPaciente;

/**
 * @author Herber
 * @date 16/10/2014
 */
@Repository
public interface SicPacienteRepository extends 
				JpaRepository<SicPaciente, Integer> {
	
	List<SicPaciente> findAllByfkSicEstadoPaciente_descripcion(String descripcion);
}
