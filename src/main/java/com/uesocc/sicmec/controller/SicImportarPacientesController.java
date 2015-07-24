/**
 * 
 */
package com.uesocc.sicmec.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uesocc.sicmec.framework.general.InvalidDocumentException;
import com.uesocc.sicmec.model.dto.SicContactoPacienteDto;
import com.uesocc.sicmec.model.dto.SicImportarPacienteObj;
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.dto.SicPersonaDto;
import com.uesocc.sicmec.model.serviceImpl.SicContactoPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicPersonaServiceImpl;
import com.uesocc.sicmec.utils.SicParseadorArchivo;

/**
 * @author pportillo
 *
 */

@Controller
@RequestMapping("/admin/importar")
public class SicImportarPacientesController 
{
	Logger LOGGER = Logger.getLogger(SicImportarPacientesController.class);
	
	@Autowired
	private SicParseadorArchivo sicParseadorArchivo;
	@Autowired
	private SicPacienteServiceImpl sicPacienteServiceImpl;
	@Autowired
	private SicPersonaServiceImpl sicPersonaServiceImpl;
	@Autowired
	private SicContactoPacienteServiceImpl sicContactoPacienteServiceImpl;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String defaultRequest()
	{
		return "/admin/importarPacientes";
	}
	
	@RequestMapping(value="/subir",method=RequestMethod.POST)
	public String subirArchivo(@RequestParam(value="archivoCsv",required = false) MultipartFile archivoCsv,RedirectAttributes redirectAttributes)
	{
		int totalGuardados = 0;
		int totalIgnorados = 0;
		int totalError = 0;
		String log = "";
		String exp = "";
		try
		{
			LOGGER.debug("Obteniendo lista de pacientes a migrar");
			SicImportarPacienteObj obj = sicParseadorArchivo.importarPacientes(archivoCsv);
			List<SicPacienteDto> list = obj.getData();
			log = obj.getLog();
			LOGGER.debug("Iniciando proceso de migracion de pacientes");
			totalIgnorados = obj.getIgnorados();
			
			for (SicPacienteDto sicPacienteDto : list) 
			{
				exp = sicPacienteDto.getNumExpediente();
				LOGGER.debug("Validando paciente por expediente: "+sicPacienteDto.getNumExpediente());
				
				if(sicPacienteServiceImpl.validacionExpedientePaciente(sicPacienteDto.getNumExpediente()))
				{
					LOGGER.debug("Paciente valido para ser insertado ");
					
					try
					{
						SicPersonaDto persona = sicPersonaServiceImpl.insert(sicPacienteDto.getFkSicPersona());
						
						sicPacienteDto.setFkSicPersona(persona);
						if(sicPacienteDto.getFkSicContactoPaciente()!=null)
						{
							SicContactoPacienteDto contacto = sicContactoPacienteServiceImpl.insert(sicPacienteDto.getFkSicContactoPaciente());
							sicPacienteDto.setFkSicContactoPaciente(contacto);
						}
						
						sicPacienteServiceImpl.insert(sicPacienteDto);
						log += "INFO: Paciente guardado "+sicPacienteDto+" \n";
						totalGuardados++;
						
					}
					catch(Exception ex)
					{
						log += "ERROR: Ha ocurrido un problema en el proceso de migrado. "+ex.getMessage()+" \n";
						log += "ERROR: Error al guardar el paciente con el expediente Exp:"+exp+" \n";
						totalError++;
					}
					
				
				}
				else
				{
					
					LOGGER.debug("Paciente ya se encuentra en el sistema: "+sicPacienteDto);
					log += "ERROR: Paciente ya se encuentra en el sistema: "+sicPacienteDto+" \n";
					totalIgnorados++;
				}
			}
		}
		catch(Exception ex)
		{
			log += "ERROR: Ha ocurrido un problema en el proceso de migrado. "+ex.getMessage();
		}
		
		redirectAttributes.addFlashAttribute("totalMigrados",totalGuardados);
		redirectAttributes.addFlashAttribute("totalIgnorados",totalIgnorados);
		redirectAttributes.addFlashAttribute("totalError",totalError);
		redirectAttributes.addFlashAttribute("log",log);
		return "redirect:/admin/importar";
	}
}
