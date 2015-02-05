package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	/**
	 * @param pageable
	 * @return Lista de pacientes ordenados
	 */
	@Query(value="SELECT s FROM SicPaciente s")
	List<SicPaciente> findAllPacientes(Pageable pageable);
	
	/**
	 * @param pageable
	 * @param nombre
	 * @return Lista de pacientes que respondan a este nombre
	 */
	@Query(value="SELECT s FROM SicPaciente s WHERE UPPER(s.fkSicPersona.nombre) LIKE %:nombre% ")
	List<SicPaciente> findAllPacientesByNombre(@Param(value = "nombre") String nombre,Pageable pageable);
	
	/**
	 * @param pageable
	 * @param apellido
	 * @return Lista de pacientes que respondan a este apellido o apellidos
	 */
	@Query(value="SELECT s FROM SicPaciente s WHERE UPPER(s.fkSicPersona.apellido) LIKE %:apellido% ")
	List<SicPaciente> findAllPacientesByApellido(@Param(value = "apellido") String apellido,Pageable pageable);
	
	/**
	 * @param pageable
	 * @param dui
	 * @return Lista de pacientes con este DUI.
	 */
	@Query(value="SELECT s FROM SicPaciente s WHERE UPPER(s.duiPaciente) = :dui ")
	List<SicPaciente> findAllPacientesByDUI(@Param(value = "dui") String dui,Pageable pageable);
	
	/**
	 * @param exp
	 * @return Lista de pacientes cuyo expediente sea similar al criterio de busqueda.
	 */
	@Query(value="SELECT s FROM SicPaciente s WHERE s.numeroExpediente LIKE %:exp%")
	List<SicPaciente> findAllPacientesByexp(@Param("exp") String exp,Pageable pageable);
	
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
