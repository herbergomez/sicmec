/**
 * 
 */
package com.uesocc.sicmec.model.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicTratamientoDto;

/**
 * @author xtiyo
 *
 */
public interface SicTratamientoService extends
		BaseService<SicTratamientoDto, Integer> {
	
	List<SicTratamientoDto> findAllBySicPaciente(@Param("pac")int pac,Pageable pageable);
	List<SicTratamientoDto> findAllBySicPacienteWhithMeds(@Param("pac")String pac,Pageable pageable) throws ParseException;
}
