package com.uesocc.sicmec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.dto.SicEntregaTratamientoDto;
import com.uesocc.sicmec.model.dto.SicTratamientoDto;
import com.uesocc.sicmec.model.serviceImpl.SicEntregaTratamientoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTratamientoServiceImpl;


/**
 * @author pportillo
 * @date 30/12/2014.08:58:34
 */
@Controller
@RequestMapping("/farm/entregaMed")
public class SicEntregaMedController 
{

	@Autowired
	private SicEntregaTratamientoServiceImpl sicEntregaTratamientoServiceImpl;
	@Autowired
	private SicTratamientoServiceImpl sicTratamientoServiceImpl;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest()
	{
		
		return "/farm/entregaMed";
	}
	
	
	/**
	 * @param id
	 * @return Lista con el ultimo tratamiento
	 * asignado a este paciente.
	 */
	@RequestMapping(value="/tratamiento/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicTratamientoDto> getTreatment(@PathVariable String id)
	{
		Pageable page = new PageRequest(0,1);
		
		return sicTratamientoServiceImpl.findAllBySicPacienteWhithMeds(id,page);
	}
	
	/**
	 * @param id
	 * @return Lista de entrega de tratamientos
	 * por pacientes ordenados por fecha de entrega
	 */
	@RequestMapping(value="/historial/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicEntregaTratamientoDto> getHistory(@PathVariable String id)
	{
		return sicEntregaTratamientoServiceImpl.findAllTreatmentDeliveryByPaciente(id);
	}
}
