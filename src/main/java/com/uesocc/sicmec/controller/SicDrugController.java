package com.uesocc.sicmec.controller;

import java.text.ParseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;

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
	
	/**
	 * Add a gruf to the DB using the servImplementation dependency injection
	 * @param sNombre The name of the drug
	 * @param sDescripcion A descrition related to the drug
	 * @param redirectAttributes The response
	 * @return A redirection to the main url for drugs
	 * @throws ParseException Exception in the procces
	 */
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
		//Valor por defecto para un medicamento creado, 1 significa activo y 0 falso
		oDrug.setEstado("1");
		//Loging
		LOGGER.info("Agregando medicamento: "+oDrug);
		//save		
		sicDrugServiceImpl.insert(oDrug);
		//answering
		redirectAttributes.addFlashAttribute("success",true);
		//redirecting
		return "redirect:/admin/drugs";
	}
	
	/**
	 * Update the drug info
	 * @param iDrug Id of the drug to update
	 * @param sDrugName new name of the drug
	 * @param sDescription Description of the drug
	 * @param iActive Status of the drug
	 * @param redirectAttributes Response of the procedure
	 * @return Redirectin to the home admin/drugs page
	 * @throws ParseException 
	 */
	@RequestMapping(value="/updateDrug",method=RequestMethod.POST)
	public String updateDrug (
				@RequestParam(value="idUpdate")int iDrug,
				@RequestParam(value="nombreUpdate")String sDrugName,
				@RequestParam(value="descripcionUpdate")String sDescription,
				@RequestParam(value="radio")String iActive,
				RedirectAttributes redirectAttributes
			) throws ParseException {		
		//Logging
		LOGGER.info("Actualizando medicamento: "+iDrug);
		//Obtain the reference
		SicDrugDto oDrugDto = sicDrugServiceImpl.findById(iDrug);
		//Set new values
		oDrugDto.setDrugName(sDrugName);
		oDrugDto.setDrugDescription(sDescription);
		oDrugDto.setEstado( iActive );
		//Save
		sicDrugServiceImpl.insert(oDrugDto);
		//Sending response
		redirectAttributes.addFlashAttribute("upSuccess",true);
		//Redirecting
		return "redirect:/admin/drugs";
	}
	
	/**
	 * Get the drug info by id
	 * @param id Drug to get
	 * @return the drug dto info
	 */
	@RequestMapping(value="/getDrug/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SicDrugDto getUser(@PathVariable(value="id")int id)
	{
		return sicDrugServiceImpl.findById(id);
	}
}