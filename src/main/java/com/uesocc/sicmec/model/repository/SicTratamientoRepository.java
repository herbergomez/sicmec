/**
 * 
 */
package com.uesocc.sicmec.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicTratamiento;

/**
 * @author xtiyo
 *
 */
@Repository
public interface SicTratamientoRepository extends
		JpaRepository<SicTratamiento, Integer> 
{
	
	/**
	 * @param id de sic paciente 
	 * @return Lista de tratamientos por id de paciente
	 */
	@Query(value="SELECT s FROM SicTratamiento s WHERE s.fkSicCitaMedica.fkSicPaciente.idSicPaciente = (:pac) ORDER BY s.fkSicCitaMedica.fxCitaMedica DESC")
	List<SicTratamiento> findAllBySicPaciente(@Param("pac")int pac,Pageable pageable);
	
	/**
	 * @param id de sic paciente 
	 * @return Lista de tratamientos por id de paciente
	 */
	@Query(value="SELECT s FROM SicTratamiento s WHERE s.fkSicCitaMedica.fkSicPaciente.numeroExpediente = (:pac) ORDER BY s.fkSicCitaMedica.fxCitaMedica DESC")
	List<SicTratamiento> findAllBySicPacienteExp(@Param("pac")String pac,Pageable pageable);
}
