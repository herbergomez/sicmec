/**
* @autor pablo portillo
 * @fecha 10/11/2014
 */
package com.uesocc.sicmec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;

/**
 * @autor pablo portillo
 * @fecha 10/11/2014
 */
@Controller
@RequestMapping("/control/cita")
public class SicCitaController 
{
	@Autowired
	private SicPacienteServiceImpl sicPacienteServiceImpl;
	
	@RequestMapping("")
	public String defaultRequest(Model model)
	{
		
		return "/control/citaPaciente";
	}
	
	@RequestMapping(value="/buscar",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicPacienteDto> getPatients(@RequestParam(value="exp")String exp)
	{
		return sicPacienteServiceImpl.findAllByExp(exp);
	}

}
