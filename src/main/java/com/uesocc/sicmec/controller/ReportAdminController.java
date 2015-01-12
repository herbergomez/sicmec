package com.uesocc.sicmec.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.repository.SicPacienteRepository;
import com.uesocc.sicmec.model.serviceImpl.SicContactoPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicDepartamentoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicEstadoPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicMunicipioServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPaisServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPersonaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicRolServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoPatologiaServiceImpl;

@Controller
public class ReportAdminController {

	Logger LOGGER = Logger.getLogger(ReportAdminController.class);
	@Autowired
	private SicMunicipioServiceImpl sicMunicipioServiceImpl;
	@Autowired
	private SicDepartamentoServiceImpl sicDepartamentoServiceImpl;
	@Autowired
	private SicPaisServiceImpl sicPaisServiceImpl;
	@Autowired
	private ApplicationContext applicationContext;
	
	
	
	@RequestMapping( value = "/admin/reportes/reportPatPorDep",method = RequestMethod.GET)
	public ModelAndView generateReportPatPacPorDep(ModelAndView modelAndView,
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

	        jasperReport = JasperCompileManager.compileReport("Reporte Patologias Pacientes.jrxml");
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
	        modelAndView = new ModelAndView("/admin/reportes/ReportPatPorDep");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return modelAndView;
	 
	    }//generatePdfReport
	
	@RequestMapping( value = "/admin/reportes/reportPacPorDep",method = RequestMethod.GET)
	public ModelAndView generateReportPacPorDep(ModelAndView modelAndView,
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

	        jasperReport = JasperCompileManager.compileReport("Reporte Adherencia Pacientes.jrxml");
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
	        modelAndView = new ModelAndView("/admin/reportes/ReportPacPorDep");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return modelAndView;
	 
	    }//generatePdfReport
	
	/**
	 * Genera el reporte de Prevalencia de Pacientes.
	 * @param modelAndView
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping( value = "/admin/reportes/reportPrevalPac",method = RequestMethod.GET)
	public ModelAndView generateReportPrevalPac(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	        jasperReport = JasperCompileManager.compileReport("Reporte de Prevalencia.jrxml");
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
	        modelAndView = new ModelAndView("/admin/reportes/reportes");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return modelAndView;
	 
	    }//generatePdfReport
	
	@RequestMapping( value = "/admin/reportes/reportInsidPac",method = RequestMethod.GET)
	public ModelAndView generateReportInsidPac(ModelAndView modelAndView,
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

	        jasperReport = JasperCompileManager.compileReport("Reporte de Insidencia.jrxml");
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
	        modelAndView = new ModelAndView("/admin/reportes/ReportInsidenciaPac");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return modelAndView;
	 
	    }//generatePdfReport
	
	@RequestMapping( value = "/admin/reportes/reportPacEstAban",method = RequestMethod.GET)
	public ModelAndView generateReportPacEstAban(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");

		    JasperReport jasperReport;
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
	        
	        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
	        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        
	      	parameterMap.put("fDesde", new Date("01/01/1989"));
	        parameterMap.put("fHasta", new Date("10/10/2016"));

	        jasperReport = JasperCompileManager.compileReport("Reporte Pacientes en Estado de Abandono.jrxml");
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
	        modelAndView = new ModelAndView("/admin/reportes/reportes");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return modelAndView;
	 
	    }//generatePdfReport
	
/**
	@RequestMapping( value = "/admin/reportes/reportPacPorUbic",method = RequestMethod.GET)
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
	        jasperReport = JasperCompileManager.compileReport("Reporte Pacientes por Ubicacion.jrxml");
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
		 return "/admin/reportes/ReportPacPorDep";
	 
	    }//generatePdfReport
**/	

}
