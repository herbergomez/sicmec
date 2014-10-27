package com.uesocc.sicmec.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicEstadoPaciente;
@Repository
public interface SicEstadoPacienteRepository extends JpaRepository<SicEstadoPaciente, Integer> {

	 SicEstadoPaciente findOneBydescripcion(String descripcion);
}
