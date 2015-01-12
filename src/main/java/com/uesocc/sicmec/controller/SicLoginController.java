/**
 * 
 */
package com.uesocc.sicmec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Pablo Portillo
 * @date 26/12/2014
 */

@Controller
@RequestMapping(value="/login")
public class SicLoginController 
{
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String defaultRequest()
	{
		return "login";
	}

}
