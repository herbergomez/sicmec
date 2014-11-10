package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	/**
	* @param exp
	* @return Lista de pacientes/paciente que se corresponden con este expediente.
	*/
	List<SicPaciente> findAllBynumeroExpediente(String numeroExpediente);
	
	/**
	 * @param exp
	 * @return Lista de pacientes cuyo expediente sea similar al criterio de busqueda.
	 */
	@Query(value="SELECT s FROM SicPaciente s WHERE s.numeroExpediente LIKE %:exp%")
	List<SicPaciente> findAllByexp(@Param("exp") String exp);
	
}
