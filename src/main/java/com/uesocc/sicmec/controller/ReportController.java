package com.uesocc.sicmec.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

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
import org.springframework.web.servlet.ModelAndView;

import com.uesocc.sicmec.config.ApplicationContext;
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
	private ApplicationContext applicationContext;
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
			
		return "/admin/reportes/ReportPatPorDep";
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
			
		return "/admin/reportes/ReportPacPorDep";
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
			
		return "/admin/reportes/ReportPacPorUbic";
	}
	@RequestMapping(value="/parametersReportCitasDoctors",method=RequestMethod.GET)
	public String parametersReportCitasDoctors(Model model)
	{
			
		return "/admin/reportes/ReportCitasDoctors";
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
	
	@RequestMapping( value = "/reportPatPorDep",method = RequestMethod.GET)
	public String generateReportPatPacPorDep(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response,
	    		       @RequestParam(value="fdesde")String fdesde,
	    			@RequestParam(value="fhasta")String fhasta) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
	        parameterMap.put("fDesde", new Date(outputFormat.format(inputFormat.parse(fdesde))));
	        parameterMap.put("fHasta", new Date(outputFormat.format(inputFormat.parse(fhasta))));
	        ServletContext context = request.getServletContext();
			String basePath = context.getRealPath("/");
			String ruta=basePath+"WEB-INF/reportes/";
			parameterMap.put("ruta", ruta);
	        jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/Reporte Patologias Pacientes.jrxml");
	        byte[] reporte = null;
	 
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,applicationContext.dataSource().getConnection());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
	        response.setHeader("Cache-Control", "max-age=30");
	        response.setHeader("Pragma", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentLength(reporte.length);
	        ServletOutputStream out = response.getOutputStream();
	        out.write(reporte, 0, reporte.length);
	        out.flush();
	        out.close();
	      //  modelAndView = new ModelAndView("/admin/reportes/ReportPatPorDep");
		 } 
		 catch (Exception ex) 
		 {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return "/admin/reportes/reportes";
	 
	    }//generatePdfReport
	
	@RequestMapping( value = "/reportPacPorDep",method = RequestMethod.GET)
	public String generateReportPacPorDep(
			ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response,
	    		       @RequestParam(value="fdesde")String fdesde,
	    			@RequestParam(value="fhasta")String fhasta) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	       // SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
	      	parameterMap.put("fDesde", inputFormat.parse(fdesde));
	        parameterMap.put("fHasta", inputFormat.parse(fhasta));
	        ServletContext context = request.getServletContext();
			String basePath = context.getRealPath("/");
	        jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/Reporte Adherencia Pacientes.jrxml");
	        byte[] reporte = null;
	 
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,applicationContext.dataSource().getConnection());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
	        response.setHeader("Cache-Control", "max-age=30");
	        response.setHeader("Pragma", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentLength(reporte.length);
	        response.getOutputStream().write(reporte,0,reporte.length);
	        response.flushBuffer();

	//        modelAndView = new ModelAndView("/admin/reportes/ReportPacPorDep");
	        
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return "/admin/reportes/reportes";
	 
	    }//generatePdfReport
	
	/**
	 * Genera el reporte de Prevalencia de Pacientes.
	 * @param modelAndView
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping( value = "/reportPrevalPac",method = RequestMethod.GET)
	public String generateReportPrevalPac(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response) {
	 
		 try 
		 {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	        ServletContext context = request.getServletContext();
			String basePath = context.getRealPath("/");
			String ruta=basePath+"WEB-INF/reportes/";
			parameterMap.put("ruta", ruta);
			jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/Reporte de Prevalencia.jrxml");
	    //    jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/reporte.jrxml");
	        LOGGER.debug(basePath+"WEB-INF/reportes/Reporte de Prevalencia.jrxml");
	        
	        byte[] reporte = null;
	 
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,applicationContext.dataSource().getConnection());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
	        response.setHeader("Cache-Control", "max-age=30");
	        response.setHeader("Pragma", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentLength(reporte.length);
	        ServletOutputStream out = response.getOutputStream();
	        out.write(reporte, 0, reporte.length);
	        out.flush();
	        out.close();
	        //modelAndView = new ModelAndView("/admin/reportes/reportes");
	        
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        //return modelAndView;
		 return "/admin/reportes/reportes";
		 
	    }//generatePdfReport
	
	@RequestMapping( value = "/reportInsidPac",method = RequestMethod.GET)
	public String generateReportInsidPac(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response,
	    		       @RequestParam(value="fdesde")String fdesde,
	    			@RequestParam(value="fhasta")String fhasta) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
	      	parameterMap.put("fDesde", new Date(outputFormat.format(inputFormat.parse(fdesde))));
	        parameterMap.put("fHasta", new Date(outputFormat.format(inputFormat.parse(fhasta))));
	        
	        ServletContext context = request.getServletContext();
			String basePath = context.getRealPath("/");
			String ruta=basePath+"WEB-INF/reportes/";
			parameterMap.put("ruta", ruta);
	        jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/Reporte de Insidencia.jrxml");
	        byte[] reporte = null;
	 
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,applicationContext.dataSource().getConnection());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
	        response.setHeader("Cache-Control", "max-age=30");
	        response.setHeader("Pragma", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentLength(reporte.length);
	        ServletOutputStream out = response.getOutputStream();
	        out.write(reporte, 0, reporte.length);
	        out.flush();
	        out.close();
	       // modelAndView = new ModelAndView("/admin/reportes/ReportInsidenciaPac");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
		 return "/admin/reportes/reportes"; 
	    }//generatePdfReport
	
	@RequestMapping( value = "/reportPacEstAban",method = RequestMethod.GET)
	public String generateReportPacEstAban(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
	      	parameterMap.put("fDesde", new Date("01/01/1989"));
	        parameterMap.put("fHasta", new Date("10/10/2016"));
	        ServletContext context = request.getServletContext();
			String basePath = context.getRealPath("/");
			String ruta=basePath+"WEB-INF/reportes/";
			parameterMap.put("ruta", ruta);
	        jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/Reporte Pacientes en Estado de Abandono.jrxml");
	        byte[] reporte = null;
	 
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,applicationContext.dataSource().getConnection());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
	        response.setHeader("Cache-Control", "max-age=30");
	        response.setHeader("Pragma", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentLength(reporte.length);
	        ServletOutputStream out = response.getOutputStream();
	        out.write(reporte, 0, reporte.length);
	        out.flush();
	        out.close();
	      //  modelAndView = new ModelAndView("/admin/reportes/reportes");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return "/admin/reportes/reportes";
	 
	    }//generatePdfReport
	
	
	@RequestMapping( value = "/reportCitasDoctors",method = RequestMethod.GET)
	public String generateReportCitasReports(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response,
	    		       @RequestParam(value="fdesde")String fdesde,
	    			@RequestParam(value="fhasta")String fhasta) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
	      	parameterMap.put("fDesde", new Date(outputFormat.format(inputFormat.parse(fdesde))));
	        parameterMap.put("fHasta", new Date(outputFormat.format(inputFormat.parse(fhasta))));
	        
	        ServletContext context = request.getServletContext();
			String basePath = context.getRealPath("/");
	        jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/Reporte Citas por Doctores.jrxml");
	        byte[] reporte = null;
	 
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,applicationContext.dataSource().getConnection());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
	        response.setHeader("Cache-Control", "max-age=30");
	        response.setHeader("Pragma", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentLength(reporte.length);
	        ServletOutputStream out = response.getOutputStream();
	        out.write(reporte, 0, reporte.length);
	        out.flush();
	        out.close();
	       // modelAndView = new ModelAndView("/admin/reportes/ReportInsidenciaPac");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
		 return "/admin/reportes/reportes"; 
	    }//generatePdfReport

	@RequestMapping( value = "/reportPacPorUbic",method = RequestMethod.GET)
	public String executeReportPacPorUbic(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response,
	    		       @RequestParam(value="pais")String pais,
	    			   @RequestParam(value="departamento")String departamento,
	    			   @RequestParam(value="municipio")String municipio) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");
		    
		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        parameterMap.put("PaisOrigen", new Integer("1"));
	        parameterMap.put("DepartamentoOrigen", new Integer("1"));
	        parameterMap.put("MunicipioOrigen",new Integer("1"));
	        ServletContext context = request.getServletContext();
			String basePath = context.getRealPath("/");
	        jasperReport = JasperCompileManager.compileReport(basePath+"WEB-INF/reportes/Reporte Pacientes por Ubicacion.jrxml");
	        byte[] reporte = null;
	        
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,applicationContext.dataSource().getConnection());
	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "inline; filename=informeDemo.pdf");
	        response.setHeader("Cache-Control", "max-age=30");
	        response.setHeader("Pragma", "No-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentLength(reporte.length);
	        ServletOutputStream out = response.getOutputStream();
	        out.write(reporte, 0, reporte.length);
	        out.flush();
	        out.close();
	      //  modelAndView = new ModelAndView("/admin/reportes/ReportPacPorDep");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
		 return "/admin/reportes/reportes";
	 
	    }//generatePdfReport

}
