package com.uesocc.sicmec.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uesocc.sicmec.model.dto.SicDepartamentoDto;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.serviceImpl.SicDepartamentoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicMunicipioServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPaisServiceImpl;


/**
 * @author Herber Gómez
 * @date 12/10/2014
 */
@RequestMapping("/admin/reportes")
@Controller
public class ReportController {
	
	Logger LOGGER = Logger.getLogger(ReportController.class);
	

	@Autowired
	private SicMunicipioServiceImpl sicMunicipioServiceImpl;
	@Autowired
	private SicDepartamentoServiceImpl sicDepartamentoServiceImpl;
	@Autowired
	private SicPaisServiceImpl sicPaisServiceImpl;
	/**
	 * Carga la pantalla donde se encuentra la lista de reportes
	 * @param model
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model)
	{
			
		return "/admin/reportesSicmec";
	}
	/**
	 * Carga la pantalla de parametros para obtener un reporte de pacientes con
	 * patologias, todo ordenado por departamento
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/parametersReportPatPorDep",method=RequestMethod.GET)
	public String parametersReportPatPorDep(Model model)
	{
			
		return "/admin/ReportPatPorDep";
	}	
	/**
	 * Carga la pantalla de parametros para obtener un reporte con el resumen de
	 * pacientes por departamento.
	 * Reporte:
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/parametersReportPacPorDep",method=RequestMethod.GET)
	public String parametersReportPacPorDep(Model model)
	{
			
		return "/admin/ReportPacPorDep";
	}
	
	/**
	 * Carga la pantalla de parametros para obtener un reporte detallado de los pacientes 
	 * o casos nuevos que se han añadido en un rango de tiempo
	 * Reporte:Reporte de Insidencia
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/parametersReportInsidPac",method=RequestMethod.GET)
	public String parametersReportInsidPac(Model model)
	{
			
		return "/admin/reportes/ReportInsidenciaPac";
	}
	
	/**
	 * Carga la pantalla de parametros para obtener un reporte con los detalles de pacientes
	 * segun ubicacion.
	 * Reporte:Reporte Pacientes por Ubicacion.jasper
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/parametersReportPacPorUbic",method=RequestMethod.GET)
	public String parametersReportPacPorUbic(Model model)
	{
			
		return "/admin/ReportPacPorUbic";
	}
	
	
	

	
	@RequestMapping(value="/getDepartamentos/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicDepartamentoDto> getDepartamentosPorPais(@PathVariable(value="id")int id)
	{
		List<SicDepartamentoDto> lst = sicDepartamentoServiceImpl.getDepartamentosPorPais(id);
		return lst;
	}

	@RequestMapping(value="/getMunicipios/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicMunicipioDto> getMunicipiosPorDepartamento(@PathVariable(value="id")int id)
	{
		List<SicMunicipioDto> lst = sicMunicipioServiceImpl.getMunicipiosPorDepartamento(id);
		return lst;
	}
}
