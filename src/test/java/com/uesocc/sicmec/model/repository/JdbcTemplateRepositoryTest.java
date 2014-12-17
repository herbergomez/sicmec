package com.uesocc.sicmec.model.repository;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.uesocc.sicmec.model.dto.SicGraficosDto;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=com.uesocc.sicmec.config.ApplicationContext.class)
public class JdbcTemplateRepositoryTest 
{
	
	@Autowired
	private JdbcRepository jdbcTemplateRepository;

	@Test
	public void test() 
	{
		List<SicGraficosDto> list = jdbcTemplateRepository.findExamsForGraphicByPaciente(1,3);
		
		for (SicGraficosDto sicGraficosDto : list) 
		{
			System.out.println(sicGraficosDto);
		}
	}

}
