package com.uesocc.sicmec.controller;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uesocc.sicmec.model.entity.SicPaciente;
import com.uesocc.sicmec.model.repository.SicPacienteRepository;

@Controller
public class ReportAdminController {

	Logger LOGGER = Logger.getLogger(ReportController.class);
	@Autowired
	private SicPacienteRepository sicPacienteRepository;
	
	@RequestMapping( value = "/admin/reportes/reportePacientes",method = RequestMethod.GET)
	public ModelAndView generateReportPaciente(ModelAndView modelAndView,
	    		       HttpServletRequest request, HttpServletResponse response,
	    		       @RequestParam(value="fdesde")String fdesde,
	    			@RequestParam(value="fhasta")String fhasta) {
	 
		 try {
		    LOGGER.debug("--------------generate PDF report----------");
		    List<SicPaciente> listPac= sicPacienteRepository.findAll();
		    JRBeanCollectionDataSource JRdataSource = new JRBeanCollectionDataSource(listPac);
		    
		    JasperReport jasperReport;
	        Map<String,Object> parameterMap = new HashMap<String, Object>();
	        parameterMap.put("usuarios", JRdataSource);
	        LOGGER.debug("--------------generate PDF report----------"+JRdataSource);
	       // parameterMap.put("fhasta", fhasta);
	        
	        jasperReport = JasperCompileManager.compileReport("test.jrxml");
	        byte[] reporte = null;
	 
	        reporte = JasperRunManager.runReportToPdf(jasperReport,parameterMap,new JREmptyDataSource());
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
	        modelAndView = new ModelAndView("/admin/reportes/ReportePacientes");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
	        return modelAndView;
	 
	    }//generatePdfReport

}
