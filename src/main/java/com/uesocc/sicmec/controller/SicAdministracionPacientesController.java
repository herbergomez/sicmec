package com.uesocc.sicmec.controller;
import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uesocc.sicmec.model.dto.DataTableObject;
import com.uesocc.sicmec.model.dto.SicAuditoriaDto;
import com.uesocc.sicmec.model.dto.SicContactoPacienteDto;
import com.uesocc.sicmec.model.dto.SicDepartamentoDto;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.dto.SicPersonaDto;
import com.uesocc.sicmec.model.serviceImpl.SicAuditoriaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicContactoPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicDepartamentoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicEstadoPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicMunicipioServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPaisServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPersonaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicRolServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoPatologiaServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicUsuarioServiceImpl;

/**
 * @author Herber GÃ³mez
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
	@Autowired
	private SicEstadoPacienteServiceImpl sicEstadoPacienteServiceImpl;
	@Autowired
	private SicPaisServiceImpl sicPaisServiceImpl;
	@Autowired
	private SicContactoPacienteServiceImpl sicContactoPacienteServiceImpl;
	@Autowired
	private SicTipoPatologiaServiceImpl sicTipoPatologiaServiceImpl;
	@Autowired
	private SicAuditoriaServiceImpl sicAuditoriaServiceImpl;
	@Autowired
	private SicUsuarioServiceImpl sicUsuarioServiceImpl;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest(Model model){
		model.addAttribute("departamentos", sicDepartamentoServiceImpl.findAll());
		model.addAttribute("municipios",sicMunicipioServiceImpl.findAll() );
		
		//model.addAttribute("pacientes", sicPacienteServiceImpl.findAll());
		
		model.addAttribute("estados", sicEstadoPacienteServiceImpl.findAll());
		model.addAttribute("paises", sicPaisServiceImpl.findAll());
		model.addAttribute("patologias", sicTipoPatologiaServiceImpl.findAll());
		return "/admin/administracionPacientes";		
	}
	
	@RequestMapping(value="/pacientes",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
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
	
	
	@RequestMapping(value="/addPaciente",method=RequestMethod.POST)
	public String addPac(
			@RequestParam(value="expediente")String expediente,
			@RequestParam(value="nombres")String nombres,
			@RequestParam(value="apellidos")String apellidos,
			@RequestParam(value="dui")String dui,
			@RequestParam(value="estado")int estado,
			@RequestParam(value="patologia")int idPatologia,
			@RequestParam(value="sexo")String sexo,
			@RequestParam(value="municipio")int idmunicipio,
			@RequestParam(value="direccion")String direccion,
			@RequestParam(value="mail")String mail,
			@RequestParam(value="telefono")String telefono,
			@RequestParam(value="fnacimiento")String fechaNacimiento,
			@RequestParam(value="fcreacion")String fechaRegistro,
			//Datos de Responsable de Paciente
			@RequestParam(value="nomContact")String nomContact,
			@RequestParam(value="apContact")String apContact,
			@RequestParam(value="duiContact")String duiContact,
			@RequestParam(value="telContact")String telContact,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes) throws ParseException {
		
		
		LOGGER.debug("Creando nuevo paciente");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
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
		paciente.setNumExpediente(expediente);
		paciente.setDireccionPaciente(direccion);
		paciente.setSexoPaciente(sex);
		paciente.setTelefonoPaciente(telefono);
		paciente.setFxNacimiento(outputFormat.format(inputFormat.parse(fechaNacimiento)));
		paciente.setFxCreacion(outputFormat.format(inputFormat.parse(fechaRegistro)));
		paciente.setDocumentoIdentidad(dui);
		paciente.setFkSicEstadoPaciente(sicEstadoPacienteServiceImpl.findById(estado));
		paciente.setFkSicPersona(sicPersonaServiceImpl.insert(persona));					
		paciente.setFkSicMunicipio(sicMunicipioServiceImpl.findById(idmunicipio));
		paciente.setFkSicTipoPatologia(sicTipoPatologiaServiceImpl.findById(idPatologia));
		
		if (!(nomContact.trim().equals("")&& !apContact.trim().equals(""))){
			SicContactoPacienteDto contacto = new SicContactoPacienteDto();
			contacto.setNombreContacto(nomContact);
			contacto.setApellidoContacto(apContact);
			contacto.setDui(duiContact);
			contacto.setTelefono(telContact);
			paciente.setFkSicContactoPaciente(sicContactoPacienteServiceImpl.insert(contacto));
		}
		LOGGER.info(paciente);
		sicPacienteServiceImpl.insert(paciente);
		
		//AUDITORIA
		sicAuditoriaServiceImpl.insert
		(new SicAuditoriaDto
				("","Administración de Pacientes"
				,"Creación de paciente "+nombres+", "+apellidos
				,format.format(new Date()),
				sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser())));
		
		redirectAttributes.addFlashAttribute("success",true);
		
		return "redirect:/admin/pacientes";
	}
	
	@RequestMapping(value="/upPaciente",method=RequestMethod.POST)
	public String upPaciente(
			@RequestParam(value="idUp")int id,
			@RequestParam(value="nombresUp")String nombres,
			@RequestParam(value="apellidosUp")String apellidos,
			@RequestParam(value="duiUp")String dui,
			@RequestParam(value="estadoUp")int estado,
			@RequestParam(value="patologiaUp")int idPatologia,
			@RequestParam(value="sexoUp")String sexo,
			@RequestParam(value="municipioUp")int idmunicipio,
			@RequestParam(value="departamentoUp")int iddepartamento,
			@RequestParam(value="direccionUp")String direccion,
			@RequestParam(value="mailUp")String mail,
			@RequestParam(value="telefonoUp")String telefono,
			@RequestParam(value="fnacimientoUp")String fechaNacimiento,
			@RequestParam(value="fcreacionUp")String fechaRegistro,
			//Datos de Responsable de Paciente
			@RequestParam(value="nomContactUp")String nomContact,
			@RequestParam(value="apContactUp")String apContact,
			@RequestParam(value="duiContactUp")String duiContact,
			@RequestParam(value="telContactUp")String telContact,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes) throws ParseException {
		
		LOGGER.debug("Actualizando paciente");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//	SimpleDateFormat formatPac = new SimpleDateFormat("yyyy-MM-dd");
		
		DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
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
			pac_search.setFxNacimiento(outputFormat.format(inputFormat.parse(fechaNacimiento)));
			pac_search.setFxCreacion(outputFormat.format(inputFormat.parse(fechaRegistro)));
		
			
			pac_search.setFkSicEstadoPaciente(sicEstadoPacienteServiceImpl.findById(estado));
		    pac_search.setFkSicPersona(sicPersonaServiceImpl.insert(per_search));
			pac_search.setFkSicMunicipio(sicMunicipioServiceImpl.findById(idmunicipio));
			pac_search.setFkSicTipoPatologia(sicTipoPatologiaServiceImpl.findById(idPatologia));	
			SicContactoPacienteDto contact_search = pac_search.getFkSicContactoPaciente();
			
			//Si el contacto existe, entonces lo actualiza.
			//Si el contacto no existe, lo inserta en caso que se hallan llenado los campos especificados.
			//Es obligatorio ingresar Nombres y Apellidos de contacto
			if (contact_search != null) {
				if (!(nomContact.trim().equals("")&& !apContact.trim().equals(""))){
					contact_search.setNombreContacto(nomContact);
					contact_search.setApellidoContacto(apContact);
					contact_search.setDui(duiContact);
					contact_search.setTelefono(telContact);
					pac_search.setFkSicContactoPaciente(sicContactoPacienteServiceImpl.insert(contact_search));
				} else {
				if (!(nomContact.trim().equals("")&& !apContact.trim().equals(""))){
					SicContactoPacienteDto contacto = new SicContactoPacienteDto();
					contacto.setNombreContacto(nomContact);
					contacto.setApellidoContacto(apContact);
					contacto.setDui(duiContact);
					contacto.setTelefono(telContact);
					pac_search.setFkSicContactoPaciente(sicContactoPacienteServiceImpl.insert(contacto));
				}				  
			   }
		   } else {
			LOGGER.info("Error al actualizar paciente");
		  }		
			LOGGER.info(pac_search);
		    sicPacienteServiceImpl.insert(pac_search);
		  //AUDITORIA
			sicAuditoriaServiceImpl.insert
			(new SicAuditoriaDto
					("","AdministraciÃ³n de Pacientes"
					,"ModificaciÃ³n de paciente "+nombres+", "+apellidos
					,format.format(new Date()),
					sicUsuarioServiceImpl.findByNombreUsuario(httpServletRequest.getRemoteUser())));
		    redirectAttributes.addFlashAttribute("upSuccess",true);	
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
	
	@RequestMapping(value="/getDepartamentos/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SicDepartamentoDto> getDepartamentosPorPais(@PathVariable(value="id")int id)
	{
		List<SicDepartamentoDto> lst = sicDepartamentoServiceImpl.getDepartamentosPorPais(id);
		return lst;
	}
/**
	@RequestMapping(value="/delPaciente/{id}",method=RequestMethod.GET)
	public String delPaciente(@PathVariable(value="id")int id,RedirectAttributes redirectAttributes)
	{
		
		try
		{
			SicPacienteDto paciente = sicPacienteServiceImpl.findById(id); 
				if(paciente.getFkSicEstadoPaciente().getDescripcion().equals("Activo"))
				{
					paciente.setFkSicEstadoPaciente(sicEstadoPacienteServiceImpl.findOneByDescripcion("Muerte"));
					sicPacienteServiceImpl.insert(paciente);
					redirectAttributes.addFlashAttribute("deleteSuccess",true);
				}
				else
				{
					redirectAttributes.addFlashAttribute("deleteError",true);
					redirectAttributes.addFlashAttribute("deleteMessage","Este usuario ya se encuentra Muerto");
				}
				
				return "redirect:/admin/pacientes";
		}
		catch (Exception ex)
		{
			
			LOGGER.error("Error al dar de baja al Paciente ");
			LOGGER.error("ERROR "+ex.getMessage());
			ex.printStackTrace();
			redirectAttributes.addFlashAttribute("deleteError",true);
			redirectAttributes.addFlashAttribute("deleteMessage","Error al bloquear usuario");
			
			return "redirect:/admin/pacientes";
			
		}
	}
	**/
	
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
	};
	
}
