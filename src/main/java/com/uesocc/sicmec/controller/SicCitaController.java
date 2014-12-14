/**
* @autor pablo portillo
 * @fecha 10/11/2014
 */
package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.dto.SicCitaMedicaDto;
import com.uesocc.sicmec.model.dto.SicExamenDto;
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.dto.SicTratamientoDto;
import com.uesocc.sicmec.model.serviceImpl.SicCitaMedicaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicExamenServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPaqMedServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoExamenServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTratamientoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;

/**
 * @autor pablo portillo
 * @fecha 10/11/2014
 */
@Controller
@RequestMapping("/control/cita")
public class SicCitaController 
{
	Logger LOGGER = Logger.getLogger(SicCitaController.class);
	
	@Autowired
	private SicPacienteServiceImpl sicPacienteServiceImpl;
	@Autowired
	private SicPaqMedServiceImpl sicPaqMedServiceImpl;
	@Autowired
	private SicCitaMedicaServiceImpl sicCitaMedicaServiceImpl;
	@Autowired
	private SicUsuarioServiceImpl sicUsuarioServiceImpl;
	@Autowired
	private SicTratamientoServiceImpl sicTratamientoServiceImpl; 
	@Autowired
	private SicTipoExamenServiceImpl sicTipoExamenServiceImpl;
	@Autowired
	private SicExamenServiceImpl sicExamenServiceImpl;
	
	@RequestMapping("")
	public String defaultRequest(Model model)
	{
		model.addAttribute("paqMedList",sicPaqMedServiceImpl.findAll());
		model.addAttribute("tipoExamsList", sicTipoExamenServiceImpl.findAll());
		
		return "/control/citaPaciente";
	}
	
	@RequestMapping(value="/buscar",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicPacienteDto> getPatients(@RequestParam(value="exp")String exp)
	{
		return sicPacienteServiceImpl.findAllByExp(exp);
	}
	
	@RequestMapping(value="/historialPac",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicTratamientoDto> getHistoryOfPac(@RequestParam(value="pac")int pac)
	{
		Pageable pageable = new PageRequest(0,20);
		
		return sicTratamientoServiceImpl.findAllBySicPaciente(pac,pageable);
	}
	
	@RequestMapping(value="/exams",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicExamenDto> getExamByCita(@RequestParam(value="cita")int cita)
	{
		return sicExamenServiceImpl.findAllByfkSicCitaMedica_idSicCitaMedica(cita);
	}
	
	@RequestMapping(value="/guardarCita",method=RequestMethod.POST)
	public @ResponseBody String guardarCita(
			@RequestParam(value="pac")int paciente,
			@RequestParam(value="diag")String diagnostico,
			@RequestParam(value="cmt")String cmt,
			@RequestParam(value="paqMed")int paqMed,
			@RequestParam(value="dosis")String dosis,
			@RequestParam(value="per")String periodisidad) throws ParseException
	{
		try
		{
			SicCitaMedicaDto citaMed =  new SicCitaMedicaDto();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			citaMed.setComentario(cmt);
			citaMed.setDiagnostico(diagnostico);
			citaMed.setFxCitaMedica(format.format(new Date()));
			citaMed.setFkSicPaciente(sicPacienteServiceImpl.findById(paciente));
			citaMed.setFkSicUsuario(sicUsuarioServiceImpl.findById(1));
			
			SicCitaMedicaDto citaMedRet = sicCitaMedicaServiceImpl.insert(citaMed);
		
			SicTratamientoDto tratamiento = new SicTratamientoDto();
			tratamiento.setPeriodisidad(periodisidad);
			tratamiento.setDosis(dosis);
			tratamiento.setFxTratamiento(format.format(new Date()));
			tratamiento.setFkSicCatMedicamentos(sicPaqMedServiceImpl.findById(paqMed));
			tratamiento.setFkSicCitaMedica(citaMedRet);
			sicTratamientoServiceImpl.insert(tratamiento);
		
			return "ok";
		}
		catch (Exception ex)
		{
			LOGGER.error("Error al agregar cita "+ex.getMessage());
			return "Error al agregar cita "+ex.getMessage();
		}
		
	}

}
