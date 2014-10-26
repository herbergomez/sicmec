/**
 * 
 */
package com.uesocc.sicmec.model.service;

import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicEstadoUsuarioDto;

/**
 * @author xtiyo
 *
 */
public interface SicEstadoUsuarioService extends
		BaseService<SicEstadoUsuarioDto, Integer> 
{
	SicEstadoUsuarioDto findOneByDescripcion(String Descripcion);
}
