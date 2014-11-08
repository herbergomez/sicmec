
package com.uesocc.sicmec.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;


/**
 * @autor pablo portillo
 * @fecha 8/11/2014
 */
@Controller
public class SicDetallePacientesController 
{
	@Autowired
	private SicPacienteServiceImpl sicPacienteServiceImpl;
	
	Logger LOGGER = Logger.getLogger(SicDetallePacientesController.class);
	
	@RequestMapping(value="/admin/detallePaciente/{exp}",method=RequestMethod.GET)
	public String defaultRequest(@PathVariable(value="exp")String exp,Model model)
	{
		SicPacienteDto paciente = sicPacienteServiceImpl.findOneBynumeroExpediente(exp);
		
		if(paciente!=null)
		{
			model.addAttribute("paciente", paciente);
		}
		
		return "/admin/detallePaciente";
	}
}
