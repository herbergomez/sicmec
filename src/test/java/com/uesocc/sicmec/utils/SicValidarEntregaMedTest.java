package com.uesocc.sicmec.utils;

import static org.junit.Assert.assertFalse;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=com.uesocc.sicmec.config.ApplicationContext.class)
public class SicValidarEntregaMedTest 
{

	@Autowired
	private SicValidarEntregaMed sicValidarEntregaMed;
	
	@Test
	public void test() throws ParseException 
	{

		assertFalse(sicValidarEntregaMed.validar(3));
		
	}

}
