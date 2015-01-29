package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uesocc.sicmec.model.dto.SicAuditoriaDto;
import com.uesocc.sicmec.model.dto.SicPatologiaDto;
import com.uesocc.sicmec.model.dto.SicTipoPatologiaDto;
import com.uesocc.sicmec.model.dto.SicUsuarioDto;
import com.uesocc.sicmec.model.serviceImpl.SicAuditoriaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;

/**
 * @author Herber GÃ³mez
 * @date 12/10/2014
 */
@RequestMapping("/admin/patologias")
@Controller
public class SicAdministracionPatologiasController {
	 
	Logger LOGGER = Logger.getLogger(SicAdministracionPatologiasController.class);
    @Autowired
	private SicTipoPatologiaServiceImpl sicTipoPatologiaServiceImpl;
    @Autowired
	private SicPatologiaServiceImpl sicPatologiaServiceImpl;
    @Autowired
	private SicAuditoriaServiceImpl sicAuditoriaServiceImpl;
	@Autowired
	private SicUsuarioServiceImpl sicUsuarioServiceImpl;
	
	//Variable que almacena mensajes de error.
	private String msjError;
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model){
		model.addAttribute("tiposPatologias", sicTipoPatologiaServiceImpl.findAll());
		model.addAttribute("patologias", sicPatologiaServiceImpl.findAll());
		return "/admin/administracionPatologias";		
	}
		
	/*
	 * TODO LO REFERNTE A ADMINISTRACIÃ“N DE PATOLOGIAS
	 */
	@RequestMapping(value="/addPatologia",method=RequestMethod.POST)
	public String addPatologia(
			@RequestParam(value="nombrePatologia")String nombre,
			@RequestParam(value="descripcionPatologia")String descripcion,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes) throws ParseException{
	try{
		msjError="";
		LOGGER.debug("Creando nueva Patología");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicPatologiaDto patologia = new SicPatologiaDto();
		patologia.setNombrePatologia(nombre);
		patologia.setDescripcionPatologia(descripcion);
		patologia.setFxCreado(format.format(new Date()));
		//patologia.setFxModicado(format.format(new Date()));
		SicUsuarioDto user=sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser());
		patologia.setCreadoPor(user.getNombreUsuario());
		LOGGER.info(patologia);
		sicPatologiaServiceImpl.insert(patologia);
		//AUDITORIA
		sicAuditoriaServiceImpl.insert
				(new SicAuditoriaDto
						("","Administración de Patologia"
						,"Creación de Patología "+nombre+", "+descripcion
						,format.format(new Date()),
						sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser())));
				
        redirectAttributes.addFlashAttribute("successPatologia",true);
	} catch (Exception ex) {
		msjError="Error al agregar Patología: Comuniquese con el Administrador del Sistema.";
		LOGGER.error("Error al agregar Patología: Comuniquese con el Administrador del Sistema." + ex.getStackTrace());
	}
	
	return "redirect:/admin/patologias";
	}
	
	@RequestMapping(value="/upPatologia",method=RequestMethod.POST)
	public String upPatologia(
			@RequestParam(value="idPatologiaUp")int id,
			@RequestParam(value="nombrePatologiaUp")String nombre,
			@RequestParam(value="descripcionPatologiaUp")String descripcion,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes) throws ParseException{
		
        LOGGER.debug("Actualizando Patologia");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicPatologiaDto pat_search = sicPatologiaServiceImpl.findById(id);
		if (pat_search != null) {
			pat_search.setNombrePatologia(nombre);
			pat_search.setDescripcionPatologia(descripcion);
			pat_search.setFxModicado(format.format(new Date()));
			SicUsuarioDto user=sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser());
			pat_search.setModifcadoPor(user.getNombreUsuario());
			LOGGER.info(pat_search);
			sicPatologiaServiceImpl.insert(pat_search);
			//AUDITORIA
			sicAuditoriaServiceImpl.insert
			(new SicAuditoriaDto
					("","Administración de Patologia"
					,"Modificación de Patología "+nombre+", "+descripcion
					,format.format(new Date()),
					sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser())));
			
	       redirectAttributes.addFlashAttribute("successPatologiaUp",true);
		}
		return "redirect:/admin/patologias";
	}
	
	@RequestMapping(value="/getPatologia/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SicPatologiaDto getPatologia(@PathVariable(value="id")int id)
	{
		return sicPatologiaServiceImpl.findById(id);
	}
	
	/*
	 * TODO LO REFERENTE A LA ADMINISTRACION DE LAS CLASIFICACIONES DE PATOLOGIAS
	 */
	@RequestMapping(value="/addTipoPatologia",method=RequestMethod.POST)
	public String addTipoPatologia(
			@RequestParam(value="nombreTipoPatologia")String nombre,
			@RequestParam(value="descripcionTipoPatologia")String descripcion,
			@RequestParam(value="patologia")int id,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes) throws ParseException{
		
		LOGGER.debug("Creando nueva ClasificaciÃ³n de PatologÃ­a");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicTipoPatologiaDto tipoPatologia = new SicTipoPatologiaDto();
		tipoPatologia.setNombreTipoPatologia(nombre);
		tipoPatologia.setDescripcionTipoPatologia(descripcion);
		tipoPatologia.setFkSicPatologia(sicPatologiaServiceImpl.findById(id));
		
		LOGGER.info(tipoPatologia);
		sicTipoPatologiaServiceImpl.insert(tipoPatologia);
		//AUDITORIA
		sicAuditoriaServiceImpl.insert
		(new SicAuditoriaDto
				("","Administración de Patologia"
				,"Creación de Clasificacón de Patología "+nombre+", "+descripcion
				,format.format(new Date()),
				sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser())));
		
       redirectAttributes.addFlashAttribute("successTipoPatologia",true);
		
		return "redirect:/admin/patologias";
	}
	
	@RequestMapping(value="/upTipoPatologia",method=RequestMethod.POST)
	public String upTipoPatologia(
			@RequestParam(value="idTipoPatologiaUp")int id,
			@RequestParam(value="nombreTipoPatologiaUp")String nombre,
			@RequestParam(value="descripcionTipoPatologiaUp")String descripcion,
			@RequestParam(value="patologiaUp")int idPatologia,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes) throws ParseException{
		
        LOGGER.debug("Actualizando Patologia");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SicTipoPatologiaDto tipPat_search = sicTipoPatologiaServiceImpl.findById(id);
		if (tipPat_search != null) {
			tipPat_search.setNombreTipoPatologia(nombre);;
			tipPat_search.setDescripcionTipoPatologia(descripcion);
			tipPat_search.setFkSicPatologia(sicPatologiaServiceImpl.findById(idPatologia));
			LOGGER.info(tipPat_search);
			sicTipoPatologiaServiceImpl.insert(tipPat_search);
			//AUDITORIA
			sicAuditoriaServiceImpl.insert
			(new SicAuditoriaDto
					("","Administración de Patologia"
					,"Modificación de Clasificacón de Patología "+nombre+", "+descripcion
					,format.format(new Date()),
					sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser())));
			
	       redirectAttributes.addFlashAttribute("successTipoPatologiaUp",true);
		}
		return "redirect:/admin/patologias";
	}
	
	
	@RequestMapping(value="/getTipoPatologia/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SicTipoPatologiaDto getTipoPatologia(@PathVariable(value="id")int id)
	{
		return sicTipoPatologiaServiceImpl.findById(id);
	}

	public String getMsjError() {
		return msjError;
	}

	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}
	
}
