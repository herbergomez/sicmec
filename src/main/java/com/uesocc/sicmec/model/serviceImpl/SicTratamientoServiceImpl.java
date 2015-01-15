/**
 * 
 */
package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicDrugAdapter;
import com.uesocc.sicmec.model.adapter.SicTratamientoAdapter;
import com.uesocc.sicmec.model.dto.SicDrugDto;
import com.uesocc.sicmec.model.dto.SicTratamientoDto;
import com.uesocc.sicmec.model.entity.SicMedicamento;
import com.uesocc.sicmec.model.entity.SicTratamiento;
import com.uesocc.sicmec.model.repository.SicAsignacionMedPaqRepository;
import com.uesocc.sicmec.model.repository.SicTratamientoRepository;
import com.uesocc.sicmec.model.service.SicTratamientoService;
import com.uesocc.sicmec.utils.SicValidarEntregaMed;

/**
 * @author xtiyo
 *
 */
@Service
public class SicTratamientoServiceImpl implements SicTratamientoService {

	@Autowired
	private SicTratamientoRepository sicTratamientoRepository;
	@Autowired
	private SicAsignacionMedPaqRepository sicAsignacionMedPaqRepository;
	@Autowired
	private SicValidarEntregaMed sicValidarEntregaMed;
	
	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#setupService()
	 */
	@Override
	public void setupService() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#insert(java.lang.Object)
	 */
	@Override
	public SicTratamientoDto insert(SicTratamientoDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		SicTratamiento obj = adp.dtoToEntity(entity);
		return adp.entityToDto(sicTratamientoRepository.save(obj));
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicTratamientoRepository.exists(id))
		{
			sicTratamientoRepository.delete(id);
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#findById(java.io.Serializable)
	 */
	@Override
	public SicTratamientoDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		if(sicTratamientoRepository.exists(id))
		{
			return adp.entityToDto(sicTratamientoRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#findAll()
	 */
	@Override
	public List<SicTratamientoDto> findAll() {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		List<SicTratamiento> list = sicTratamientoRepository.findAll();
		List<SicTratamientoDto> list_dto = new ArrayList<SicTratamientoDto>();
		
		for (SicTratamiento sicTratamiento : list) 
		{
			list_dto.add(adp.entityToDto(sicTratamiento));
		}
		
		return list_dto;
	}

	@Override
	public List<SicTratamientoDto> findAllBySicPaciente(int pac,Pageable pageable) {
		// TODO Auto-generated method stub
		
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		List<SicTratamiento> list = sicTratamientoRepository.findAllBySicPaciente(pac,pageable);
		List<SicTratamientoDto> list_dto = new ArrayList<SicTratamientoDto>();
		
		for (SicTratamiento sicTratamiento : list) 
		{
			list_dto.add(adp.entityToDto(sicTratamiento));
		}
		
		return list_dto;
	}

	@Override
	public List<SicTratamientoDto> findAllBySicPacienteWhithMeds(String pac,
			Pageable pageable) throws ParseException {
		// TODO Auto-generated method stub
		SicTratamientoAdapter adp = new SicTratamientoAdapter();
		SicDrugAdapter adpp = new SicDrugAdapter();
		
		List<SicTratamiento> list = sicTratamientoRepository.findAllBySicPacienteExp(pac,pageable);
		List<SicTratamientoDto> list_dto = new ArrayList<SicTratamientoDto>();	
		List<SicMedicamento> listMed = null;
		List<SicDrugDto> listMedDto = new ArrayList<SicDrugDto>();
		SicTratamientoDto treatment = null;
		
		for (SicTratamiento sicTratamiento : list) 
		{
			treatment = adp.entityToDto(sicTratamiento);
			
			listMed = sicAsignacionMedPaqRepository.findAllDrugsOfPaq(sicTratamiento.getFkSicCatMedicamentos().getIdSicCatMedicamentos());
			
			for (SicMedicamento sicMedicamento : listMed) 
			{
				listMedDto.add(adpp.entityToDto(sicMedicamento));
			}
			
			treatment.setListMeds(listMedDto);
			treatment.setEntregaValida(sicValidarEntregaMed.validar(sicTratamiento.getFkSicCitaMedica().getFkSicPaciente().getIdSicPaciente()));
			list_dto.add(treatment);
		}
		
		return list_dto;
	}

	
}
