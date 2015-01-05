package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.dto.SicEntregaTratamientoDto;
import com.uesocc.sicmec.model.dto.SicTratamientoDto;
import com.uesocc.sicmec.model.dto.SicUsuarioDto;
import com.uesocc.sicmec.model.serviceImpl.SicEntregaTratamientoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTratamientoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;


/**
 * @author pportillo
 * @date 30/12/2014.08:58:34
 */
@Controller
@RequestMapping("/farm/entregaMed")
public class SicEntregaMedController 
{

	@Autowired
	private SicEntregaTratamientoServiceImpl sicEntregaTratamientoServiceImpl;
	@Autowired
	private SicTratamientoServiceImpl sicTratamientoServiceImpl;
	@Autowired
	private SicUsuarioServiceImpl sicUsuarioServiceImpl;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest()
	{
		
		return "/farm/entregaMed";
	}
	
	@RequestMapping(value="/entregarTrat",method=RequestMethod.POST)
	public @ResponseBody String realizarEntrega(
			@RequestParam(value="tratamiento") int tratamiento)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			SicEntregaTratamientoDto entrega = new SicEntregaTratamientoDto();
			entrega.setComentario("Entregada realizada");
			entrega.setFkSicTratamiento(sicTratamientoServiceImpl.findById(tratamiento));
			entrega.setFxEntregaTratamiento(format.format(new Date()));
			sicEntregaTratamientoServiceImpl.insert(entrega);
		
			return "ok";
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
	
	@RequestMapping(value="/entregarTratAutenticada",method=RequestMethod.POST)
	public @ResponseBody String realizarEntregaAutenticada(
			@RequestParam(value="usuario") String usuario,
			@RequestParam(value="pass") String pass,
			@RequestParam(value="tratamiento") int tratamiento,
			@RequestParam(value="cmt",defaultValue="",required=false) String comentario)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			SicUsuarioDto user = sicUsuarioServiceImpl.findByNombreUsuario(usuario);
				
				if(user != null)
				{
					if(user.getSicRol().getNombreRol().equals("Admin_Farmacia"))
					{
						if(user.getClave().equals(pass))
						{
							SicEntregaTratamientoDto entrega = new SicEntregaTratamientoDto();
							entrega.setComentario(comentario);
							entrega.setFkSicTratamiento(sicTratamientoServiceImpl.findById(tratamiento));
							entrega.setFxEntregaTratamiento(format.format(new Date()));
							sicEntregaTratamientoServiceImpl.insert(entrega);
							return "ok";
						}
						else
						{
							return "Usuario o contrase&ntilde;a incorrecta";
						}
					}
					else
					{
						return "El usuario no tiene permisos para realizar esta acci&oacute;n";
					}	
					
				}
				else
				{
					return "Usuario o contrase&ntilde;a incorrecta";	
				}	

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
	
	/**
	 * @param id
	 * @return Lista con el ultimo tratamiento
	 * asignado a este paciente.
	 * @throws ParseException 
	 */
	@RequestMapping(value="/tratamiento/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicTratamientoDto> getTreatment(@PathVariable String id) throws ParseException
	{
		Pageable page = new PageRequest(0,1);
		
		return sicTratamientoServiceImpl.findAllBySicPacienteWhithMeds(id,page);
	}
	
	/**
	 * @param id
	 * @return Lista de entrega de tratamientos
	 * por pacientes ordenados por fecha de entrega
	 */
	@RequestMapping(value="/historial/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicEntregaTratamientoDto> getHistory(@PathVariable String id)
	{
		return sicEntregaTratamientoServiceImpl.findAllTreatmentDeliveryByPaciente(id);
	}
}
