/**
 * 
 */
package com.uesocc.sicmec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;

/**
 * @author xtiyo
 *
 */

@Controller
@RequestMapping(value="/Utils")
public class SicUtilsController 
{
	@Autowired
	private SicUsuarioServiceImpl sicUsuarioServiceImpl;
	@Autowired
	private SicPacienteServiceImpl sicPacienteServiceImpl;


	/**
	 * @param nombreUsuario
	 * @return Verdadero si el nombre de usuario esta libre y 
	 * falso si el nombre de usuario ya esta en uso
	 */
	@RequestMapping(value="/validarUsername",method=RequestMethod.POST)
	public @ResponseBody boolean validacionUsuario
						(@RequestParam(value="usuario")String nombreUsuario)
	{
		
		return sicUsuarioServiceImpl.validacionDenombreUsuario(nombreUsuario);
		
	}
	/**
	 * @param número de expediente
	 * @return Verdadero si el número de expediente esta libre y 
	 * falso si el número de expediente ya esta en uso
	 */
	@RequestMapping(value="/validarExpediente",method=RequestMethod.POST)
	public @ResponseBody boolean validacionExpediente
						(@RequestParam(value="expediente")String expediente)
	{
		
		return sicPacienteServiceImpl.validacionExpedientePaciente(expediente);
		
	}
}
