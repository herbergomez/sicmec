/**
 * 
 */
package com.uesocc.sicmec.model.service;

import java.util.List;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicUsuarioDto;

/**
 * @author pablo portillo
 * @date 5/10/2014
 */
public interface SicUsuarioService extends BaseService<SicUsuarioDto, Integer> 
{

	/**
	 * @param nombreUsuario
	 * @return True si el nombre usuario esta libre y false si el nombre de usuario a sido usado
	 */
	boolean validacionDenombreUsuario(String nombreUsuario);
	
	List<SicUsuarioDto> findAllByEstado(String descripcion);
}
