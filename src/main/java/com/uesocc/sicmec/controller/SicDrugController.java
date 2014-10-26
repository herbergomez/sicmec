package com.uesocc.sicmec.controller;

import java.text.ParseException;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uesocc.sicmec.model.dto.SicDrugDto;
import com.uesocc.sicmec.model.serviceImpl.SicDrugServiceImpl;

@RequestMapping("/admin/drugs")
@Controller
public class SicDrugController {
	//Checking the init in log
	Logger LOGGER = Logger.getLogger(SicDrugController.class);
	
	@Autowired
	private SicDrugServiceImpl sicDrugServiceImpl;
	
	/**
	 * Default request for controller SicDrugManagement
	 * @param model Used to access the data layer
	 * @return the view attached to the drugs
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model){			
		model.addAttribute("drugs", sicDrugServiceImpl.findAll());
		return "admin/administracionMedicamentos";
	}
	
	@RequestMapping(value="/addDrug",method=RequestMethod.POST)
	public String addDrug (
			@RequestParam(value="nombre")String sNombre,
			@RequestParam(value="descripcion")String sDescripcion,
			RedirectAttributes redirectAttributes
			) throws ParseException{
		//Check the logger
		LOGGER.debug("Creando nuevo medicamento");
		//Creating the Medicamento object
		SicDrugDto oDrug = new SicDrugDto();
		oDrug.setDrugName(sNombre);
		oDrug.setDrugDescription(sDescripcion);
		
		LOGGER.info("Agregando: "+oDrug);
		
		sicDrugServiceImpl.insert(oDrug);
		
		return "redirect:/admin/drugs";
	}
}
