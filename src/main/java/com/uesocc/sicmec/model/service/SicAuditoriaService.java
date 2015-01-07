package com.uesocc.sicmec.model.service;

import java.util.Date;
import java.util.List;
import com.uesocc.sicmec.framework.general.BaseService;
import com.uesocc.sicmec.model.dto.SicAuditoriaDto;
public interface SicAuditoriaService extends BaseService<SicAuditoriaDto, Integer>
{
	List<SicAuditoriaDto> findAllByDateAndUser(int user, Date from, Date to);

}
