package com.uesocc.sicmec.model.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uesocc.sicmec.model.adapter.SicEstadoPacienteAdapter;
import com.uesocc.sicmec.model.dto.SicEstadoPacienteDto;
import com.uesocc.sicmec.model.entity.SicEstadoPaciente;
import com.uesocc.sicmec.model.repository.SicEstadoPacienteRepository;
import com.uesocc.sicmec.model.service.SicEstadoPacienteService;
@Service
public class SicEstadoPacienteServiceImpl implements SicEstadoPacienteService{

	@Autowired
	private SicEstadoPacienteRepository sicEstadoPacienteRepository; 
	
	@Override
	public void setupService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SicEstadoPacienteDto insert(SicEstadoPacienteDto entity)
			throws ParseException {
		// TODO Auto-generated method stub
		SicEstadoPacienteAdapter adp = new SicEstadoPacienteAdapter();
		SicEstadoPaciente obj = adp.dtoToEntity(entity);		
		return adp.entityToDto(this.sicEstadoPacienteRepository.save(obj));
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		if(sicEstadoPacienteRepository.exists(id))
		{
		  sicEstadoPacienteRepository.delete(id);
		  return true;
		}
		else
			{
		  return false;
		}
	}

	@Override
	public SicEstadoPacienteDto findById(Integer id) {
		// TODO Auto-generated method stub
        SicEstadoPacienteAdapter adp = new SicEstadoPacienteAdapter();
		
		if(sicEstadoPacienteRepository.exists(id))
		{
			return adp.entityToDto(sicEstadoPacienteRepository.findOne(id));
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<SicEstadoPacienteDto> findAll() {
		// TODO Auto-generated method stub
		SicEstadoPacienteAdapter adp = new SicEstadoPacienteAdapter();
		List<SicEstadoPaciente> list = sicEstadoPacienteRepository.findAll();
		List<SicEstadoPacienteDto> list_dto = new ArrayList<SicEstadoPacienteDto>();
		
		for (SicEstadoPaciente sicEstado : list) 
		{
			list_dto.add(adp.entityToDto(sicEstado));
		}
		
		return list_dto;
	}
	/* (non-Javadoc)
	 * @see com.uesocc.sicmec.model.service.SicEstadoUsuarioService#findOneByDescripcion(java.lang.String)
	 */
	@Override
	public SicEstadoPacienteDto findOneByDescripcion(String Descripcion) {
		// TODO Auto-generated method stub
		SicEstadoPacienteAdapter adp = new SicEstadoPacienteAdapter();
		
		return adp.entityToDto(sicEstadoPacienteRepository.findOneBydescripcion(Descripcion));
	}

}
