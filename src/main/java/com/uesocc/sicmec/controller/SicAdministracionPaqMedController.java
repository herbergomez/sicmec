package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.uesocc.sicmec.model.dto.SicAsignacionMedPaqDto;
import com.uesocc.sicmec.model.dto.SicDrugDto;
import com.uesocc.sicmec.model.dto.SicPaqMedDto;
import com.uesocc.sicmec.model.serviceImpl.SicAsignacionMedPaqServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicDrugServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPaqMedServiceImpl;

@RequestMapping("/admin/paq")
@Controller
public class SicAdministracionPaqMedController {
	//Checking the init in log
	Logger LOGGER = Logger.getLogger(SicDrugController.class);
	
	@Autowired
	private SicAsignacionMedPaqServiceImpl sicAsignacionMedPaqServiceImpl;
	
	@Autowired
	private SicPaqMedServiceImpl sicPacMedServiceImpl;
	
	@Autowired
	private SicDrugServiceImpl sicDrugServiceImpl;
	
	/**
	 * Default request for controller SicDrugManagement
	 * @param model Used to access the data layer
	 * @return the view attached to the drugs
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model){			
		model.addAttribute("paqs", sicPacMedServiceImpl.findAll());		
		return "admin/administracionPaqMed";
	}
	
	
	/**
	 * Funcion que devuelve los medicamentos que pertenecen a un paquete
	 * @param idPaq Id del paquete a verificar
	 * @param redirectAttributes Variable de retorno
	 * @return JSON value
	 */
	@RequestMapping(value="/medsIn/{idPaq}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicDrugDto> loadMedsIn(
				@PathVariable(value="idPaq")String idPaq,
				RedirectAttributes redirectAttributes
			)
	{
		List<SicDrugDto> lst = this.sicAsignacionMedPaqServiceImpl.getMedsOfPaq(Integer.parseInt(idPaq));
		return lst;
	}
	
	/**
	 * Funcion que devuelve los medicamentos que pertenecen a un paquete
	 * @param idPaq Id del paquete a verificar
	 * @param redirectAttributes Variable de retorno
	 * @return JSON value
	 */
	@RequestMapping(value="/medsOut/{idPaq}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicDrugDto> loadMedsOut(
				@PathVariable(value="idPaq")String idPaq,
				RedirectAttributes redirectAttributes
			)
	{
		List<SicDrugDto> lst = this.sicAsignacionMedPaqServiceImpl.getMedsNotInPaq(Integer.parseInt(idPaq));
		return lst;
	}
	
	/**
	 * Funcion que guarda un paquete de medicamentos
	 * @param sNombre Nombre del paquete
	 * @param sDescripcion Descripcion del paquete
	 * @param redirectAttributes Variables de retorno
	 * @return Recireccion a /admin/paq
	 * @throws ParseException Exceptions
	 */
	@RequestMapping(value="/addPaq",method=RequestMethod.POST)
	public String addPaq(
			@RequestParam(value="nombre")String sNombre,
			@RequestParam(value="descripcion")String sDescripcion,
			RedirectAttributes redirectAttributes
			) throws ParseException
	{
		LOGGER.debug("Creando nuevo paquete de medicamento");
		//Creando objeto
		SicPaqMedDto dto = new SicPaqMedDto();
		//Estableciendo variables
		dto.setName(sNombre);
		dto.setDescription(sDescripcion);
		dto.setActive("1");
		
		LOGGER.debug("Almacenando objeto"+dto);
				
		sicPacMedServiceImpl.insert(dto);
		
		redirectAttributes.addFlashAttribute("success",true);

		return "redirect:/admin/paq";
	}
	
	@RequestMapping(value="/addMedPaq", method=RequestMethod.POST)
	public String addPaqMed(
			@RequestParam(value="idPaq")String iPaqId,
			@RequestParam(value="idMeds[]")String[] aMeds,
			RedirectAttributes redirectAttributes
			) throws ParseException
	{
		//Desactivamos los medicamentos relacionados al paquete
		boolean bDeactivate = sicAsignacionMedPaqServiceImpl.deactivateAllRecords(Integer.parseInt(iPaqId));		
		//Validamos que el arreglo exista y tenga datos
		boolean bMeds = aMeds[0].equals("false");
		if ( bMeds == false )
		{
			//Recorremos con un for el arreglo de medicamentos a insertar
			for ( int iTemp = 0; iTemp < aMeds.length; iTemp++ )
			{
				//Declaramos el objeto para insertar el nuevo registro		
				SicAsignacionMedPaqDto medPaq = new SicAsignacionMedPaqDto();
				//Establecemos los valores
				medPaq.setIdMedicamento(sicDrugServiceImpl.findById(Integer.parseInt(aMeds[iTemp])));
				medPaq.setIdMedPaq(sicPacMedServiceImpl.findById(Integer.parseInt(iPaqId)));
				medPaq.setMedPaqStatus("1");
				//Almacenamos al logger
				LOGGER.info("Guardando medicamento "+aMeds[iTemp]);
				//Guardamos			
				sicAsignacionMedPaqServiceImpl.insert(medPaq);
			}
		}
		//Retornamos OK
		redirectAttributes.addFlashAttribute("success", true);
		//Redireccionamos
		return "redirect:/admin/paq";
	}
	
	/**
	 * Funcion para obtner los datos de un paquete de medicamentos	
	 * @param iId Id del paquete a modificar
	 * @return DTO del objeto encontrado
	 */
	@RequestMapping(value="/getPack/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SicPaqMedDto getPack(
			@PathVariable(value="id")int id
			)
	{
		return sicPacMedServiceImpl.findById(id);
	}
	
	/**
	 * Funcion utilizada para actualizar un registro de paquete de medicamentos 
	 * @param sDescripcion Nueva descripcion
	 * @param sId Id del paquete a modificar
	 * @param redirectAttributes Variables de retorno
	 * @return URL a redireccionar
	 * @throws ParseException 
	 */
	@RequestMapping(value="/updatePaq", method=RequestMethod.POST)
	public String updatePaq(
				@RequestParam(value="descripcionUp")String sDescripcion,
				@RequestParam(value="radio")String sActivo,
				@RequestParam(value="idUpdate")String sId,
				RedirectAttributes redirectAttributes
			) throws ParseException
	{
		
		//Logging
		LOGGER.info("Actualizando paquete: "+sId);
		//Obtain the reference
		SicPaqMedDto oDto = sicPacMedServiceImpl.findById(Integer.parseInt(sId));
		//Set new values
		oDto.setDescription(sDescripcion);
		oDto.setActive( sActivo );
		//Save
		sicPacMedServiceImpl.insert(oDto);
		//Sending response
		redirectAttributes.addFlashAttribute("upSuccess",true);
		//Redirecting
		return "redirect:/admin/paq";
	}
}
