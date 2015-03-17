package com.uesocc.sicmec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Herber Gomez
 * @date 10/02/2015
 */
@RequestMapping("/admin/manualAdministracion")
@Controller
public class ayudaAdministracionController {


	/**
	 * Carga la pantalla por defecto para el manual de usuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model,HttpServletRequest request, HttpServletResponse response)
	{	
		model.addAttribute("ruta", request.getServletContext().getRealPath("/")+"WEB-INF/resources/images/manual/");
		return "/admin/ayudaAdministracion";
	}
}
