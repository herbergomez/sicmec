package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uesocc.sicmec.model.serviceImpl.SicAuditoriaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;

@Controller
@RequestMapping(value="/admin/auditoria")
public class SicAuditoriaController 
{
	@Autowired
	private SicAuditoriaServiceImpl sicAuditoriaServiceImpl;
	@Autowired
	private SicUsuarioServiceImpl sicUsuarioServiceImpl;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model)
	{
		
		model.addAttribute("listUser", sicUsuarioServiceImpl.findAllByEstado("Activo"));
		
		return "/admin/auditoria";
	}
	 
	@RequestMapping(value="",method=RequestMethod.POST)
	public String search(
			@RequestParam(value="desde") String desde,
			@RequestParam(value="hasta") String hasta,
			@RequestParam(value="user") int user,
			Model model,
			RedirectAttributes redirectAttributes) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		redirectAttributes.addFlashAttribute("listAudit", sicAuditoriaServiceImpl.findAllByDateAndUser(user, format.parse(desde),format.parse( hasta)));
		redirectAttributes.addFlashAttribute("desde",desde);
		redirectAttributes.addFlashAttribute("hasta",hasta);
		redirectAttributes.addFlashAttribute("user",user);
		return "redirect:/admin/auditoria";
	}
	
}
