package com.uesocc.sicmec.model.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.entity.SicEntregaTratamiento;

@Repository
public interface SicEntregaTratamientoRepository extends JpaRepository<SicEntregaTratamiento, Integer> 
{


	@Query(value="SELECT COUNT(et) FROM SicEntregaTratamiento et "
			+ "WHERE et.fkSicTratamiento.fkSicCitaMedica.fkSicPaciente.idSicPaciente = (:paciente) "
			+ "AND et.fxEntregaTratamiento BETWEEN (:fxOne) AND (:fxTwo)")
	Long countAllByPacienteAndFecha(@Param("paciente")int paciente,@Param("fxOne")Date fxOne,@Param("fxTwo")Date fxTwo);
	
	
}
