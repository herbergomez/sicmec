package com.uesocc.sicmec.controller.reportes;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uesocc.sicmec.config.ApplicationContext;
import com.uesocc.sicmec.controller.ReportAdminController;
import com.uesocc.sicmec.model.serviceImpl.SicDepartamentoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicMunicipioServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPaisServiceImpl;

@RequestMapping("/admin/reportes/pacientesPorUbicacion")
@Controller
public class ReportePacientesPorUbicacionController {
	Logger LOGGER = Logger.getLogger(ReportAdminController.class);
	@Autowired
	private SicMunicipioServiceImpl sicMunicipioServiceImpl;
	@Autowired
	private SicDepartamentoServiceImpl sicDepartamentoServiceImpl;
	@Autowired
	private SicPaisServiceImpl sicPaisServiceImpl;
	
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model){
		model.addAttribute("departamentos", sicDepartamentoServiceImpl.findAll());
		model.addAttribute("municipios",sicMunicipioServiceImpl.findAll() );
		model.addAttribute("paises", sicPaisServiceImpl.findAll());
		return "/admin/reportes/ReportPacPorUbic";		
	}
	
	@RequestMapping( value = "/executeReportPacPorUbic",method = RequestMethod.GET)
	public String generateReportPacPorUbic(ModelAndView modelAndView,
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
	       
	      //  modelAndView = new ModelAndView("/admin/reportes/ReportPacPorUbic");
		 } catch (Exception ex) {
			 LOGGER.debug("Error al generar el Reporte:" +ex.getMessage());
		 }
		 return "/admin/reportes/ReportPacPorUbic";
	 
	    }//generatePdfReport
	
}
