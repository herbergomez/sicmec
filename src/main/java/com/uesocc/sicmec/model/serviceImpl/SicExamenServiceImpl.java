/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicExamenAdapter;
import com.uesocc.sicmec.model.dto.SicExamenDto;
import com.uesocc.sicmec.model.dto.SicGraficosDto;
import com.uesocc.sicmec.model.entity.SicExamen;
import com.uesocc.sicmec.model.repository.JdbcRepository;
import com.uesocc.sicmec.model.repository.SicExamenRepository;
import com.uesocc.sicmec.model.service.SicExamenService;

/**
 * @autor pablo portillo
 * @fecha 17/11/2014
 */
@Service
public class SicExamenServiceImpl implements SicExamenService {

	@Autowired
	private SicExamenRepository SicExamenRepository;
	@Autowired
	private JdbcRepository jdbcRepository;
	
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
	public SicExamenDto insert(SicExamenDto entity) throws ParseException {
		// TODO Auto-generated method stub
		SicExamenAdapter adp = new SicExamenAdapter();
		SicExamen exm = adp.dtoToEntity(entity);
		
		return adp.entityToDto(SicExamenRepository.save(exm));
	}

	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.framework.general.BaseService#delete(java.io.Serializable)
	 */
	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(SicExamenRepository.exists(id))
		{
			SicExamenRepository.delete(id);
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
	public SicExamenDto findById(Integer id) {
		// TODO Auto-generated method stub
		SicExamenAdapter adp = new SicExamenAdapter();
		if(SicExamenRepository.exists(id))
		{
			return adp.entityToDto(SicExamenRepository.findOne(id));
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
	public List<SicExamenDto> findAll() {
		// TODO Auto-generated method stub
		SicExamenAdapter adp = new SicExamenAdapter();
		List<SicExamen> list = SicExamenRepository.findAll();
		List<SicExamenDto> list_dto = new ArrayList<SicExamenDto>();
		
		for (SicExamen sicExamen : list) 
		{
			list_dto.add(adp.entityToDto(sicExamen));
		}
		
		return list_dto;
	}

	@Override
	public List<SicExamenDto> findAllByfkSicCitaMedica_idSicCitaMedica(int id) {
		// TODO Auto-generated method stub
		SicExamenAdapter adp = new SicExamenAdapter();
		List<SicExamen> list = SicExamenRepository.findAllByfkSicCitaMedica_idSicCitaMedica(id);
		List<SicExamenDto> list_dto = new ArrayList<SicExamenDto>();
		
		for (SicExamen sicExamen : list) 
		{
			list_dto.add(adp.entityToDto(sicExamen));
		}
		
		return list_dto;
	}

	@Override
	public List<SicGraficosDto> findAllExamsResultsByPaciente(int tipoExam,int paciente) {
		// TODO Auto-generated method stub
		if(tipoExam != 0)
		{
			return jdbcRepository.findExamsForGraphicByPaciente(tipoExam,paciente);
		}
		else
		{
			return jdbcRepository.findBMIForGraphicByPaciente(tipoExam,paciente);
		}
		
	}

}
