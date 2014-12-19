package com.uesocc.sicmec.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uesocc.sicmec.config.ApplicationContext;
import com.uesocc.sicmec.model.repository.SicPacienteRepository;

@Controller
public class ReportAdminController {

	Logger LOGGER = Logger.getLogger(ReportController.class);
	@Autowired
	private SicPacienteRepository sicPacienteRepository;
	
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

}
