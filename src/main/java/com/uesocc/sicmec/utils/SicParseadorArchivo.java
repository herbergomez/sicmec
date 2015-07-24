/**
 * 
 */
package com.uesocc.sicmec.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.uesocc.sicmec.framework.general.InvalidDocumentException;
import com.uesocc.sicmec.model.dto.SicContactoPacienteDto;
import com.uesocc.sicmec.model.dto.SicImportarPacienteObj;
import com.uesocc.sicmec.model.dto.SicPacienteDto;
import com.uesocc.sicmec.model.dto.SicPersonaDto;
import com.uesocc.sicmec.model.serviceImpl.SicEstadoPacienteServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicMunicipioServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTipoPatologiaServiceImpl;

/**
 * @author pportillo
 *
 */
@Component
public class SicParseadorArchivo 
{	 
	  @Autowired
	  private SicMunicipioServiceImpl sicMunicipioServiceImpl;
	  @Autowired
	  private SicEstadoPacienteServiceImpl sicEstadoPacienteServiceImpl;
	  @Autowired
	  private SicTipoPatologiaServiceImpl sicTipoPatologiaServiceImpl;
	 
	  static Logger log4j = Logger.getLogger(SicParseadorArchivo.class);
	 
	  
	  public SicImportarPacienteObj importarPacientes(MultipartFile file) throws InvalidDocumentException 
	  {	
		  
		validateDocType(file.getContentType());  
		SicImportarPacienteObj obj = new SicImportarPacienteObj(); 
		String log = "",error = "";
		int count = 0;
		int ignorados = 0;
		List<SicPacienteDto> serverPairList = new ArrayList<SicPacienteDto>();
		try 
		{
				log += "INFO: Inicio de proceso de migraci&oacute;n "+new Date()+" \n";
				Iterator<Row> rowIterator = null;
				
				if(file.getContentType().equals("application/vnd.ms-excel"))
				{
					HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
					HSSFSheet sheet = workbook.getSheetAt(0);
					rowIterator = sheet.iterator();
					log += "INFO: Tipo de archivo, xls \n";
				}
				else if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				{
					XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
					XSSFSheet sheet = workbook.getSheetAt(0);
					rowIterator = sheet.iterator();
					log += "INFO: Tipo de archivo, xlsx \n";
				}
				
				Row row;
				while (rowIterator.hasNext())
				{	
					count++;
					SicPacienteDto paciente = new SicPacienteDto();
					SicPersonaDto persona = new SicPersonaDto();
					SicContactoPacienteDto contacto = new SicContactoPacienteDto();
					
					row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();	
					Cell celda;
					while (cellIterator.hasNext())
					{
						celda = cellIterator.next();
						String value=celda.getStringCellValue();
						switch (celda.getColumnIndex()) 
						{
							case 0:
								paciente.setNumExpediente(value);
								break;
							case 1:
								paciente.setDocumentoIdentidad(value);
								break;
							case 2:
								paciente.setFkSicMunicipio(sicMunicipioServiceImpl.findOneBynombreMunicipio(value));
								break;
							case 3:
								persona.setNombre(value);
								break;
							case 4:
								persona.setApellido(value);
								break;
							case 5:
								persona.setEmail(value);
								break;
							case 6:
								paciente.setTelefonoPaciente(value);
								break;
							case 7:
								paciente.setDireccionPaciente(value);
								break;
							case 8:
								paciente.setFxNacimiento(value);
								break;
							case 9:
								paciente.setFkSicEstadoPaciente(sicEstadoPacienteServiceImpl.findOneByDescripcion(value));
								break;
							case 10:
								paciente.setSexoPaciente(value);
								break;
							case 11:
								
								int id = 0;
								
								if(value.equals("Diabetes Tipo 1"))
								{
									id = 1;
								}
								else if(value.equals("Diabetes Tipo 2"))
								{
									id = 2;
								}	
								else if(value.equals("Hipertencion Baja"))
								{
									id = 4;
								}
								else if(value.equals("Hipertencion Alta"))
								{
									id = 3;
								}
								paciente.setFkSicTipoPatologia(sicTipoPatologiaServiceImpl.findById(id));
								break;
							case 12:
								contacto.setNombreContacto(value);
								break;
							case 13:
								contacto.setApellidoContacto(value);
								break;
							case 14:
								contacto.setDui(value);
								break;
							case 15:
								contacto.setTelefono(value);
								break;
							default:
								break;
						}
						
					}
					if(contacto.getNombreContacto()!=null && contacto.getApellidoContacto()!=null)
					{
						paciente.setFkSicContactoPaciente(contacto);
					}
					paciente.setFkSicPersona(persona);
					
					if(!serverPairList.contains(paciente))
					{
						serverPairList.add(paciente);
						log += "INFO: Paciente agregado a la lista "+paciente+" \n";
						log4j.debug("Paciente agregado a la lista "+paciente);
					}
					else
					{
						log4j.debug("Se ha encontrado un paciente repetido en el archivo, se ignorara. "+paciente+" \n");
						log += "ERROR: Se ha encontrado un paciente repetido en el archivo, se ignorara. "+paciente+" \n";
						ignorados++;
					}
					
					
				}
				
				
		} 
		catch (Exception e) 
		{
			log4j.error(e.getMessage());
			e.printStackTrace();
			throw new InvalidDocumentException("Error al parsear el archivo de pacientes. "+e.getMessage());
		}
		
		log = "INFO: Numero de pacientes en el documento "+count +"\n" + log;
		obj.setData(serverPairList);
		obj.setLog(log);
		obj.setError(error);
		obj.setIgnorados(ignorados);
		return obj;
	  }
	  
	  public static void validateDocType(String mimeType) throws InvalidDocumentException
		{
			List<String> supportedTypes = new ArrayList<String>();
			supportedTypes.add("application/vnd.ms-excel");
			supportedTypes.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			
				if(!supportedTypes.contains(mimeType))
				{
					throw new InvalidDocumentException("Tipo de archivo no soportado, por favor utilize .xls o .xlsx");
				}
			
		}
	  
	 
}
