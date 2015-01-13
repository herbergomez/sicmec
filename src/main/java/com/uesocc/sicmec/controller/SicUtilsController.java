/**
 * 
 */
package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.dto.SicAuditoriaDto;
import com.uesocc.sicmec.model.dto.SicUsuarioDto;
import com.uesocc.sicmec.model.serviceImpl.SicAuditoriaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicDrugServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;
import com.uesocc.sicmec.utils.SicEncriptUtil;

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
    @Autowired
    private SicDrugServiceImpl sicDrugServiceImpl;
    @Autowired
    private SicAuditoriaServiceImpl sicAuditoriaServiceImpl;
    
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
	public @ResponseBody boolean validacionNombreMedicamento (@RequestParam(value="nombre")String sNombre ) 
	{
		return sicDrugServiceImpl.nameExist( sNombre );
	}
	
	/**
	 * @param httpServletRequest
	 * @param oldPass
	 * @param newPass
	 * @return "true" si la clave fue cambiada con exito o
	 * el error en una cadena string si hubo algun problema
	 * al modificar la clave
	 * @throws ParseException
	 */
	@RequestMapping(value="/cambiarClave", method = RequestMethod.POST)
	public @ResponseBody String cambiarPass(
			HttpServletRequest httpServletRequest,
			@RequestParam(value="oldPass")String oldPass,
			@RequestParam(value="newPass")String newPass) throws ParseException 
	{
		SicUsuarioDto usuario = sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			//Revisa si el password introducido es igual al que ya tiene el usuario asignado
			if(usuario.getClave().equals(SicEncriptUtil.getStringMessageDigest(oldPass,SicEncriptUtil.MD5)))
			{
				//si es el mismo, se procede a setear la nueva contraseña
				usuario.setClave(SicEncriptUtil.getStringMessageDigest(newPass,SicEncriptUtil.MD5));
				sicUsuarioServiceImpl.insert(usuario);
				
				//AUDITORIA
				sicAuditoriaServiceImpl.insert
				(new SicAuditoriaDto
						("","Cambio de contraseña"
						,"El usuario "+usuario.getSicPersona().getNombre()+", "+usuario.getSicPersona().getApellido()
						+" modifico su contraseña de acceso"
						,format.format(new Date()),
						usuario));
				
				return "true";
			}
			else
			{
				return "Contrase&ntilde;a actual incorrecta";
			}
		
	}
}
