/**
 * 
 */
package com.uesocc.sicmec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Pablo Portillo
 * @date 26/12/2014
 */

@Controller

public class SicLoginController 
{
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String defaultRequest()
	{
		return "login";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public ModelAndView invalidUserRquest()
	{
		ModelAndView model = new ModelAndView("error/403");
		model.addObject("errCode", "Permiso denegado");
		model.addObject("errMsg", "");
		
		return model;
		
	}

}
