package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicAsignacionMedPaqAdapter;
import com.uesocc.sicmec.model.adapter.SicDrugAdapter;
import com.uesocc.sicmec.model.adapter.SicMunicipioAdapter;
import com.uesocc.sicmec.model.dto.SicAsignacionMedPaqDto;
import com.uesocc.sicmec.model.dto.SicDrugDto;
import com.uesocc.sicmec.model.dto.SicMunicipioDto;
import com.uesocc.sicmec.model.entity.SicAsignacionMedicamento;
import com.uesocc.sicmec.model.entity.SicMedicamento;
import com.uesocc.sicmec.model.entity.SicMunicipio;
import com.uesocc.sicmec.model.repository.SicAsignacionMedPaqRepository;
import com.uesocc.sicmec.model.repository.SicDrugRepository;
import com.uesocc.sicmec.model.service.SicAsignacionMedPaqService;

@Service
public class SicAsignacionMedPaqServiceImpl implements SicAsignacionMedPaqService{

	@Autowired
	private SicAsignacionMedPaqRepository sicAsignacionMedPaq;
	
	@Autowired
	private SicDrugRepository sicDrugRepository;
	
	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicAsignacionMedPaqDto insert(SicAsignacionMedPaqDto entity)
			throws ParseException {
		SicAsignacionMedPaqAdapter adp = new SicAsignacionMedPaqAdapter();
		SicAsignacionMedicamento obj = adp.dtoToEntity(entity);
		return adp.entityToDto(this.sicAsignacionMedPaq.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		if (this.sicAsignacionMedPaq.exists(id))
		{
			this.sicAsignacionMedPaq.delete(id);
			return true;
		}
		else
		{
			return false;
		}		
	}

	@Override
	public SicAsignacionMedPaqDto findById(Integer id) {
		SicAsignacionMedPaqAdapter adp = new SicAsignacionMedPaqAdapter();
		
		if (this.sicAsignacionMedPaq.exists(id))
		{
			return adp.entityToDto(this.sicAsignacionMedPaq.findOne(id));
		}
		else
		{
			return null;
		}		
	}

	@Override
	public List<SicAsignacionMedPaqDto> findAll() {
		// TODO Auto-generated method stub
		SicAsignacionMedPaqAdapter adp = new SicAsignacionMedPaqAdapter();
		List<SicAsignacionMedicamento> list = this.sicAsignacionMedPaq.findAll();
		List<SicAsignacionMedPaqDto> list_dto = new ArrayList<SicAsignacionMedPaqDto>();
		
		for (SicAsignacionMedicamento med : list) 
		{
			list_dto.add(adp.entityToDto(med));
		}
		
		return list_dto;
	}
	
	/**
	 * Funcion para desactivar todas las referencias a los medicamentos del paquete
	 * @param iPaqMedId Medicamento del paquete a desactivar
	 * @return boolean true si se desactivan con exito los paquetes, false de lo contrario
	 */
	public boolean deactivateAllRecords( Integer iPaqMedId )
	{
		List<SicAsignacionMedicamento> list = this.sicAsignacionMedPaq.findAllByfkSicCatMedicamentos_idSicCatMedicamentos(iPaqMedId);
		
		//Recorremor los datos obtenidos
		for (SicAsignacionMedicamento med : list) 
		{
			//Establecemos 0 para los medicamentos encontrados
			med.setEstado("0");
			//Guardamos la informacions
			this.sicAsignacionMedPaq.save(med);
		}
		//Devolvemos true
		return true;
	}
	
	/**
	 * Funcion para obtener los paquetes de los medicamentos
	 * @param iPaqMedId id del paquete de medicamento a devolver
	 * @return La lista de medicamentos que pertenecen al paquete
	 */
	public List<SicDrugDto> getMedsOfPaq ( int iPaqMedId ) {
		// TODO Auto-generated method stub
		SicDrugAdapter adp = new SicDrugAdapter();

		List<SicMedicamento> list = this.sicAsignacionMedPaq.findAllDrugsOfPaq(iPaqMedId);

		List<SicDrugDto> list_dto = new ArrayList<SicDrugDto>();
		
		for (SicMedicamento med : list) 
		{
			list_dto.add(adp.entityToDto(med));
		}
		
		return list_dto;
	}
	
	/**
	 * Funcion para obtener medicamentos que no estan en el paquete
	 * @param iPaqMedId id del paquete de medicamento a devolver
	 * @return La lista de medicamentos que NO pertenecen al paquete
	 */
	public List<SicDrugDto> getMedsNotInPaq ( int iPaqMedId ) {
		// TODO Auto-generated method stub
		SicDrugAdapter adp = new SicDrugAdapter();

		List<SicMedicamento> list = this.sicAsignacionMedPaq.findAllDrugsOfNotInPaq(iPaqMedId);

		List<SicDrugDto> list_dto = new ArrayList<SicDrugDto>();
		
		for (SicMedicamento med : list) 
		{
			list_dto.add(adp.entityToDto(med));
		}
		
		return list_dto;
	}

}
