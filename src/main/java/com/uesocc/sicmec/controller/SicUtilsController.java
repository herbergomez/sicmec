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
import com.uesocc.sicmec.model.serviceImpl.SicPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicDrugServiceImpl;
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
    @Autowired
    private SicPatologiaServiceImpl sicPatologiaServiceImpl;
    @Autowired
    private SicTipoPatologiaServiceImpl sicTipoPatologiaServiceImpl;
    
	private SicDrugServiceImpl sicDrugServiceImpl;
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
	/**
	 * @param nombre de patologia
	 * @return Verdadero si la patologia esta libre y 
	 * falso si la patologia ya esta en uso
	 */
	@RequestMapping(value="/validarPatologia",method=RequestMethod.POST)
	public @ResponseBody boolean validacionPatologia
						(@RequestParam(value="nombrePatologia")String nombre)
	{
		
		return sicPatologiaServiceImpl.validacionPatologia(nombre);
		
	}
	@RequestMapping(value="/validarPatologiaUp",method=RequestMethod.POST)
	public @ResponseBody boolean validacionPatologiaUp
						(@RequestParam(value="nombrePatologiaUp")String nombre)
	{
		
		return sicPatologiaServiceImpl.validacionPatologiaUp(nombre);
		
	}
	@RequestMapping(value="/validarTipoPatologia",method=RequestMethod.POST)
	public @ResponseBody boolean validacionTipoPatologia
						(@RequestParam(value="nombreTipoPatologia")String nombre)
	{
		
		return sicTipoPatologiaServiceImpl.validacionTipoPatologia(nombre);
	}
	
	/**
	 * Check if the drug name already exist in the DB
	 * @param sNombre The name to check in the DB
	 * @return true if the drug DOESN'T exist, false if the drug EXIST
	 */
	@RequestMapping(value="/validarNombreDroga", method = RequestMethod.POST)
	public @ResponseBody boolean validacionNombreMedicamento (@RequestParam(value="nombre")String sNombre ) {
		return sicDrugServiceImpl.nameExist( sNombre );
	}
}
