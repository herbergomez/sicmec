/**
 * 
 */
package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.dto.DataTableObject;
import com.uesocc.sicmec.model.dto.SicCitaMedicaDto;
import com.uesocc.sicmec.model.dto.SicExamenDto;
import com.uesocc.sicmec.model.dto.SicGraficosDto;
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
 * @author Pablo Portillo
 * @date 10/02/2015
 */

@Controller
@RequestMapping("/control")
public class SicControlCitasController {

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
	
	private Logger LOGGER = Logger.getLogger(SicControlCitasController.class);
	
	@RequestMapping("")
	public String defaultRequest(Model model)
	{
		
		return "/control/busquedaPacientes";
	}
	
	@RequestMapping(value="/expediente",method=RequestMethod.POST)
	public String detailRequest(@RequestParam(value="exp")String exp, Model model)
	{
		SicPacienteDto paciente = sicPacienteServiceImpl.findOneBynumeroExpediente(exp);
		
		model.addAttribute("paqMedList",sicPaqMedServiceImpl.findAll());
		model.addAttribute("tipoExamsList", sicTipoExamenServiceImpl.findAll());
		
		if(paciente!=null)
		{
			model.addAttribute("paciente", paciente);
		}
		
		return "/control/expediente";
	}
	

	/**
	 * @param cita
	 * @return Lista de examenes que le corresponden a una cita en particular
	 */
	@RequestMapping(value="/exams",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicExamenDto> getExamByCita(@RequestParam(value="cita")int cita)
	{
		LOGGER.info("Get the exams from the bd...");
		
		return sicExamenServiceImpl.findAllByfkSicCitaMedica_idSicCitaMedica(cita);
	}
	
	/**
	 * @param exam
	 * @return Resultado de la operacion de elminar un examen de una cita
	 */
	@RequestMapping(value="/eliminarExam",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean delExam(@RequestParam(value="exam")int exam)
	{
		LOGGER.info("Delete the exam from the bd...");
		
		return sicExamenServiceImpl.delete(exam);
	}
	
	/**
	 * @param tipoExam
	 * @param cita
	 * @param resultado
	 * @param comentario
	 * @return Resultado de la operacion de guardar un examen a una cita
	 */
	@RequestMapping(value="/guardarExam",method=RequestMethod.POST)
	public @ResponseBody String guardarExam
	(
			@RequestParam(value="tipoExam")int tipoExam,
			@RequestParam(value="cita")int cita,
			@RequestParam(value="result")String resultado,
			@RequestParam(value="cmt",defaultValue="",required=false)String comentario,
			HttpServletRequest httpServletRequest)
	{
		
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			LOGGER.info("Make the insert of exam for the date "+cita);
			
			SicExamenDto exam = new SicExamenDto();
			exam.setResultado(resultado);
			exam.setComentario(comentario);
			exam.setFkSicCitaMedica(sicCitaMedicaServiceImpl.findById(cita));
			exam.setFkSicTipoExamen(sicTipoExamenServiceImpl.findById(tipoExam));
			exam.setFxCreado(format.format(new Date()));
			exam.setFxModificado(format.format(new Date()));
			exam.setCreadoPor(httpServletRequest.getRemoteUser());
			exam.setModifcadoPor(httpServletRequest.getRemoteUser());
			sicExamenServiceImpl.insert(exam);
			
			return "ok";
		}
		catch (Exception ex)
		{
			LOGGER.error("ERROR saving exams");
			LOGGER.error("ERROR saving exams... "+ex.getMessage());
			return "Error "+ex.getMessage();
		}
	}
	
	
	/**
	 * @param pac
	 * @return Historico de citas de un paciente determinado
	 */
	@RequestMapping(value="/historialPac",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicTratamientoDto> getHistoryOfPac(@RequestParam(value="pac")int pac)
	{
		Pageable pageable = new PageRequest(0,20);
		
		return sicTratamientoServiceImpl.findAllBySicPaciente(pac,pageable);
	}
	
	@RequestMapping(value="/busquedaPacientes",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTableObject<SicPacienteDto> pacientes(
			HttpServletRequest httpServletRequest,
			@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength,
            @RequestParam(value="tipoBusqueda",required=false,defaultValue="") String tipoBusqueda,
            @RequestParam String sEcho,
            @RequestParam String sSearch,
            @RequestParam String sSortDir_0,
            @RequestParam int iSortCol_0,
            Locale locale)
	{
		LOGGER.info(iDisplayStart);
		LOGGER.info(iDisplayLength);
		LOGGER.info(sEcho);
		LOGGER.info(sSearch);
		LOGGER.info(sSortDir_0);
		LOGGER.info(iSortCol_0);
		
		Integer pageNumber = 0;
		
		if(iDisplayStart != 0)
		{
			pageNumber = (iDisplayStart/iDisplayLength);
		}
		
    	LOGGER.info(pageNumber);
    	
		DataTableObject<SicPacienteDto> dataTableObject = new DataTableObject<SicPacienteDto>();
		
		Pageable pageable = 
				new PageRequest(pageNumber,iDisplayLength,new Sort(((sSortDir_0.equals("asc")) ? Direction.ASC: Direction.DESC),sortField(iSortCol_0)));
		
		List<SicPacienteDto> list ;
		
		int total;
		
		if(sSearch.trim().equals(""))
		{
			list = sicPacienteServiceImpl.findAll(pageable);
			
			total = list.size();
			dataTableObject.setAaData(list);
			dataTableObject.setiTotalRecords(total);
			dataTableObject.setiTotalDisplayRecords(total);
			
		}
		else
		{
			list = sicPacienteServiceImpl.findAll(pageable,tipoBusqueda,sSearch);
			
			total = list.size();
			dataTableObject.setAaData(list);
			dataTableObject.setiTotalRecords(total);
			dataTableObject.setiTotalDisplayRecords(total);
			
		}
		
		dataTableObject.setsEcho(sEcho);
		
		return dataTableObject;
	}
	
	/**
	 * @param paciente
	 * @param diagnostico
	 * @param cmt
	 * @param paqMed
	 * @param dosis
	 * @param periodisidad
	 * @return Resultado de la operacion de guardar una nueva cita para un paciente
	 * @throws ParseException
	 */
	@RequestMapping(value="/guardarCita",method=RequestMethod.POST)
	public @ResponseBody String guardarCita(
			@RequestParam(value="pac")int paciente,
			@RequestParam(value="diag")String diagnostico,
			@RequestParam(value="signosSintomas",defaultValue="",required=false)String signosSintomas,
			@RequestParam(value="cmt",defaultValue="",required=false)String cmt,
			@RequestParam(value="peso",defaultValue="",required=false)String peso,
			@RequestParam(value="estatura",defaultValue="",required=false)String estatura,
			@RequestParam(value="paqMed")int paqMed,
			@RequestParam(value="dosis")String dosis,
			@RequestParam(value="per")String periodisidad,
			HttpServletRequest httpServletRequest) throws ParseException
	{
		try
		{
			SicCitaMedicaDto citaMed =  new SicCitaMedicaDto();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			citaMed.setComentario(cmt);
			citaMed.setDiagnostico(diagnostico);
			citaMed.setSignosSintomas(signosSintomas);
			citaMed.setPeso(peso);
			citaMed.setEstatura(estatura);
			citaMed.setFxCitaMedica(format.format(new Date()));
			citaMed.setFkSicPaciente(sicPacienteServiceImpl.findById(paciente));
			citaMed.setFkSicUsuario(sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser()));
			
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
	
	@RequestMapping(value="/grafico",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicGraficosDto> graficoPorTipoDeExamen(@RequestParam(value="pac")int paciente,@RequestParam(value="tipo")int tipo)
	{
		return sicExamenServiceImpl.findAllExamsResultsByPaciente(tipo,paciente);
	}
	
	public String sortField(int sortColumn)
	{
		String sortingField;
		
		//Sorting by food group name
		if(sortColumn == 1)
		{
			sortingField = "numeroExpediente";
		}
		//Sorting by is enabled ingredient 
		else if(sortColumn == 2)
		{
			sortingField = "fkSicPersona.nombre";
		}
		else if(sortColumn == 3)
		{
			sortingField = "fkSicPersona.apellido";
		}
		else if(sortColumn == 4)
		{
			sortingField = "duiPaciente";
		}
		else if(sortColumn == 5)
		{
			sortingField = "fxNacimiento";
		}
		else if(sortColumn == 6)
		{
			sortingField = "fkSicMunicipio.fkSicDepartamento.fkSicPais.nombrePais";
		}
		else if(sortColumn == 7)
		{
			sortingField = "fkSicMunicipio.fkSicDepartamento.nombreDepartamento";
		}
		else if(sortColumn == 8)
		{
			sortingField = "fkSicMunicipio.nombreMunicipio";
		}
		else if(sortColumn == 9)
		{
			sortingField = "fkSicEstadoPaciente.idSicEstadoPaciente";
		}
		else
		{
			sortingField = "fkSicPersona.nombre";
		}
		
		return sortingField;
	}
	
}
