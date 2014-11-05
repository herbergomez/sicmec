/**
 * 
 */
package com.uesocc.sicmec.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;

/**
 * @author xtiyo
 *
 */
@Controller
public class SicDetallePacientesController 
{
	@Autowired
	private SicPacienteServiceImpl sicPacienteServiceImpl;
	
	Logger LOGGER = Logger.getLogger(SicDetallePacientesController.class);
	@RequestMapping(value="/admin/detallePaciente/{exp}",method=RequestMethod.GET)
	public String defaultRequest(@PathVariable(value="exp")String exp)
	{
		
		return "/admin/detallePaciente";
	}
}
