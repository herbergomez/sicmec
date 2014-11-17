package com.uesocc.sicmec.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author Herber Gómez
 * @date 12/10/2014
 */
@RequestMapping("/admin/reportes")
@Controller
public class ReportController {
	
	Logger LOGGER = Logger.getLogger(ReportController.class);
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model)
	{
			
		return "/admin/reportesSicmec";
	}
	@RequestMapping(value="/parameters",method=RequestMethod.GET)
	public String parameterPacientes(Model model)
	{
			
		return "/admin/ReportePacientes";
	}	
}
