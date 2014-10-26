package com.uesocc.sicmec.controller;
import java.text.ParseException;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.dto.SicPersonaDto;
import com.uesocc.sicmec.model.serviceImpl.SicDepartamentoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicMunicipioServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPersonaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicRolServiceImpl;

/**
 * @author Herber Gómez
 * @date 12/10/2014
 */
@RequestMapping("/admin/pacientes")
@Controller
public class SicAdministracionPacientesController {
	Logger LOGGER = Logger.getLogger(SicAdministracionPacientesController.class);
	@Autowired
	private SicMunicipioServiceImpl sicMunicipioServiceImpl;
	@Autowired
	private SicDepartamentoServiceImpl sicDepartamentoServiceImpl;
	@Autowired
	private SicRolServiceImpl sicRolServiceImpl;
	@Autowired
	private SicPersonaServiceImpl sicPersonaServiceImpl;
	@Autowired
	private SicPacienteServiceImpl sicPacienteServiceImpl;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model){
		model.addAttribute("departamentos", sicDepartamentoServiceImpl.findAll());
		model.addAttribute("municipios",sicMunicipioServiceImpl.findAll() );
		model.addAttribute("pacientes", sicPacienteServiceImpl.findAll());
		return "/admin/administracionPacientes";		
	}
	
	@RequestMapping(value="/addPaciente",method=RequestMethod.POST)
	public String addUser(
			@RequestParam(value="nombres")String nombres,
			@RequestParam(value="apellidos")String apellidos,
			//@RequestParam(value="edad")BigInteger edad,
			@RequestParam(value="sexo")String sexo,
			@RequestParam(value="municipio")int idmunicipio,
			@RequestParam(value="departamento")int iddepartamento,
			@RequestParam(value="direccion")String direccion,
			@RequestParam(value="mail")String mail,
			@RequestParam(value="telefono")String telefono,
			@RequestParam(value="fnacimiento")String fechaNacimiento,
			RedirectAttributes redirectAttributes) throws ParseException {
		
		LOGGER.debug("Creando nuevo paciente");
		
		String sex="";
		if (sexo.equals("Masculino")){
			sex="M";
		} else if(sexo.equals("Femenino")){
			sex="F";
		}
		
		SicPersonaDto persona = new SicPersonaDto();
		persona.setNombre(nombres);
		persona.setApellido(apellidos);
		persona.setEmail(mail);
		
		SicPacienteDto paciente = new SicPacienteDto();
		paciente.setDireccionPaciente(direccion);
		paciente.setSexoPaciente(sex);
		paciente.setTelefonoPaciente(telefono);
		paciente.setFxNacimiento(fechaNacimiento);

		paciente.setFkSicPersona(sicPersonaServiceImpl.insert(persona));
		paciente.setFkSicMunicipio(sicMunicipioServiceImpl.findById(idmunicipio));
				
		LOGGER.info(paciente);
		sicPacienteServiceImpl.insert(paciente);
		
		redirectAttributes.addFlashAttribute("success",true);
		
		return "redirect:/admin/pacientes";
	}
	
	@RequestMapping(value="/upPaciente",method=RequestMethod.POST)
	public String upPaciente(
			@RequestParam(value="idUp")int id,
			@RequestParam(value="nombresUp")String nombres,
			@RequestParam(value="apellidosUp")String apellidos,
			@RequestParam(value="sexoUp")String sexo,
			@RequestParam(value="municipioUp")int idmunicipio,
			@RequestParam(value="departamentoUp")int iddepartamento,
			@RequestParam(value="direccionUp")String direccion,
			@RequestParam(value="mailUp")String mail,
			@RequestParam(value="telefonoUp")String telefono,
			@RequestParam(value="fnacimientoUp")String fechaNacimiento,
			RedirectAttributes redirectAttributes) throws ParseException {
		
		LOGGER.debug("Actualizando paciente");
		
		SicPacienteDto pac_search = sicPacienteServiceImpl.findById(id);
		
		if (pac_search!=null) {
			String sex="";
			if (sexo.equals("Masculino")){
				sex="M";
			} else if(sexo.equals("Femenino")){
				sex="F";
			}
			SicPersonaDto per_search = pac_search.getFkSicPersona();
			per_search.setNombre(nombres);
			per_search.setApellido(apellidos);
			per_search.setEmail(mail);
			
			pac_search.setDireccionPaciente(direccion);
			pac_search.setSexoPaciente(sex);
			pac_search.setTelefonoPaciente(telefono);
			pac_search.setFxNacimiento(fechaNacimiento);
	
		    pac_search.setFkSicPersona(sicPersonaServiceImpl.insert(per_search));
			pac_search.setFkSicMunicipio(sicMunicipioServiceImpl.findById(idmunicipio));
					
			LOGGER.info(pac_search);
			sicPacienteServiceImpl.insert(pac_search);
			redirectAttributes.addFlashAttribute("Upsuccess",true);
		} else {
			LOGGER.info("Error al actualizar paciente");
		}
		
		return "redirect:/admin/pacientes";
	}
	
	
	@RequestMapping(value="/getPaciente/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SicPacienteDto getPaciente(@PathVariable(value="id")int id)
	{
		return sicPacienteServiceImpl.findById(id);
	}

	@RequestMapping(value="/getMunicipios/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicMunicipioDto> getMunicipiosPorDepartamento(@PathVariable(value="id")int id)
	{
		List<SicMunicipioDto> lst = sicMunicipioServiceImpl.getMunicipiosPorDepartamento(id);
		return lst;
	}
}
