package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.util.Date;

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

import com.uesocc.sicmec.model.dto.SicPatologiaDto;
import com.uesocc.sicmec.model.dto.SicTipoPatologiaDto;
import com.uesocc.sicmec.model.serviceImpl.SicPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoPatologiaServiceImpl;

/**
 * @author Herber Gómez
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
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model){
		model.addAttribute("tiposPatologias", sicTipoPatologiaServiceImpl.findAll());
		model.addAttribute("patologias", sicPatologiaServiceImpl.findAll());
		return "/admin/administracionPatologias";		
	}
		
	/*
	 * TODO LO REFERNTE A ADMINISTRACIÓN DE PATOLOGIAS
	 */
	@RequestMapping(value="/addPatologia",method=RequestMethod.POST)
	public String addPatologia(
			@RequestParam(value="nombrePatologia")String nombre,
			@RequestParam(value="descripcionPatologia")String descripcion,
			RedirectAttributes redirectAttributes) throws ParseException{
		
		LOGGER.debug("Creando nueva Patología");
		SicPatologiaDto patologia = new SicPatologiaDto();
		patologia.setNombrePatologia(nombre);
		patologia.setDescripcionPatologia(descripcion);
		patologia.setFxCreado(new Date().toString());
		
		LOGGER.info(patologia);
		sicPatologiaServiceImpl.insert(patologia);
        redirectAttributes.addFlashAttribute("successPatologia",true);
		
		return "redirect:/admin/patologias";
	}
	
	@RequestMapping(value="/upPatologia",method=RequestMethod.POST)
	public String upPatologia(
			@RequestParam(value="idPatologiaUp")int id,
			@RequestParam(value="nombrePatologiaUp")String nombre,
			@RequestParam(value="descripcionPatologiaUp")String descripcion,
			RedirectAttributes redirectAttributes) throws ParseException{
		
        LOGGER.debug("Actualizando Patologia");
		
		SicPatologiaDto pat_search = sicPatologiaServiceImpl.findById(id);
		if (pat_search != null) {
			pat_search.setNombrePatologia(nombre);
			pat_search.setDescripcionPatologia(descripcion);
			pat_search.setFxModicado(new Date().toString());
			LOGGER.info(pat_search);
			sicPatologiaServiceImpl.insert(pat_search);
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
			RedirectAttributes redirectAttributes) throws ParseException{
		
		LOGGER.debug("Creando nueva Clasificación de Patología");
		SicTipoPatologiaDto tipoPatologia = new SicTipoPatologiaDto();
		tipoPatologia.setNombreTipoPatologia(nombre);;
		tipoPatologia.setDescripcionTipoPatologia(descripcion);
		tipoPatologia.setFkSicPatologia(sicPatologiaServiceImpl.findById(id));
		
		LOGGER.info(tipoPatologia);
		sicTipoPatologiaServiceImpl.insert(tipoPatologia);
       redirectAttributes.addFlashAttribute("successTipoPatologia",true);
		
		return "redirect:/admin/patologias";
	}
	
	@RequestMapping(value="/upTipoPatologia",method=RequestMethod.POST)
	public String upTipoPatologia(
			@RequestParam(value="idTipoPatologiaUp")int id,
			@RequestParam(value="nombreTipoPatologiaUp")String nombre,
			@RequestParam(value="descripcionTipoPatologiaUp")String descripcion,
			@RequestParam(value="patologiaUp")int idPatologia,
			RedirectAttributes redirectAttributes) throws ParseException{
		
        LOGGER.debug("Actualizando Patologia");
		
		SicTipoPatologiaDto tipPat_search = sicTipoPatologiaServiceImpl.findById(id);
		if (tipPat_search != null) {
			tipPat_search.setNombreTipoPatologia(nombre);;
			tipPat_search.setDescripcionTipoPatologia(descripcion);
			tipPat_search.setFkSicPatologia(sicPatologiaServiceImpl.findById(idPatologia));
			LOGGER.info(tipPat_search);
			sicTipoPatologiaServiceImpl.insert(tipPat_search);
	       redirectAttributes.addFlashAttribute("successTipoPatologiaUp",true);
		}
		return "redirect:/admin/patologias";
	}
	
	
	@RequestMapping(value="/getTipoPatologia/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SicTipoPatologiaDto getTipoPatologia(@PathVariable(value="id")int id)
	{
		return sicTipoPatologiaServiceImpl.findById(id);
	}
}
