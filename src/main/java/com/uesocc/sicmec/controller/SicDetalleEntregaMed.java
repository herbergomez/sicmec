package com.uesocc.sicmec.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uesocc.sicmec.model.dto.SicEntregaTratamientoDto;
import com.uesocc.sicmec.model.serviceImpl.SicAsignacionMedPaqServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicEntregaTratamientoServiceImpl;

/**
 * @author pportillo
 * @date 07/01/2015.08:11:00 
 */

@Controller
public class SicDetalleEntregaMed 
{
	Logger LOGGER = Logger.getLogger(SicDetalleEntregaMed.class);
	
	@Autowired
	private SicEntregaTratamientoServiceImpl sicEntregaTratamientoServiceImpl;
	@Autowired
	private SicAsignacionMedPaqServiceImpl sicAsignacionMedPaqServiceImpl;
	
	@RequestMapping(value="/farm/detalleEntregaMed/{id}",method=RequestMethod.GET)
	public String defaultRequest(@PathVariable(value="id")int id,Model model)
	{
		SicEntregaTratamientoDto entrega = sicEntregaTratamientoServiceImpl.findById(id);
			
		if(entrega != null)
		{
			model.addAttribute("entrega", entrega);
			model.addAttribute("listMed",
					sicAsignacionMedPaqServiceImpl
					.getMedsOfPaq(Integer.parseInt(entrega.getFkSicTratamiento().getFkSicCatMedicamentos().getIdPaq())));
			
		}
		
		return "/farm/detalleEntregaMed";
	}
}
