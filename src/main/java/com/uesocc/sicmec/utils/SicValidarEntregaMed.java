package com.uesocc.sicmec.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.uesocc.sicmec.model.dto.SicTratamientoDto;
import com.uesocc.sicmec.model.serviceImpl.SicEntregaTratamientoServiceImpl;
import com.uesocc.sicmec.model.serviceImpl.SicTratamientoServiceImpl;

/**
 * @author pportillo
 * @date 30/12/2014.15:41:51
 */

@Component
public class SicValidarEntregaMed
{

	@Autowired
	private SicTratamientoServiceImpl sicTratamientoServiceImpl;
	@Autowired
	private SicEntregaTratamientoServiceImpl sicEntregaTratamientoServiceImpl;
	
	/**
	 * @param paciente
	 * @return Este metodo retorna TRUE si el paciente esta apto para 
	 * recibir su medicamento.
	 * y FALSE si por algun motivo el paciente ya recibio su medicamento 
	 * en el periodo de tiempo estipulado en la cita con el medico.
	 * @throws ParseException
	 */
	public boolean validar (int paciente) throws ParseException
	{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Pageable page = new PageRequest(0, 1);
	
		List<SicTratamientoDto> tratList = 
				sicTratamientoServiceImpl.findAllBySicPaciente(paciente,page);
		
		Map<String, Integer> aMap = new HashMap<String, Integer>();
		aMap.put("Quincenal" , Integer.valueOf(15));
		aMap.put("Mensual" , Integer.valueOf(30));
		aMap.put("Bimensual" , Integer.valueOf(60));
		aMap.put("Trimestral" , Integer.valueOf(90));
		aMap.put("6 meses" , Integer.valueOf(180));
		
		
		if(!tratList.isEmpty())
		{
			SicTratamientoDto trat = tratList.get(0);
			Date fxTratamiento = format.parse(trat.getFxTratamiento());
			Date fxActual = new Date();
			//Date fxActual = format.parse("2015-04-20 08:40:00");
			Long diferencia = fxActual.getTime() - fxTratamiento.getTime();
			Long dias = TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS);
			int diasPeriodo = aMap.get(trat.getPeriodisidad());
			int multiplo = dias.intValue()/ diasPeriodo;
					
			if (multiplo == 0)
			{
				return calculoDeEntregasPorRangoDeFecha(paciente,fxTratamiento,fxActual);
			}
			else
			{
				Calendar myCal = new GregorianCalendar();
				myCal.setTime(fxTratamiento);
				myCal.add(Calendar.DAY_OF_YEAR,diasPeriodo*multiplo);
				Date fxOff = myCal.getTime();
				return calculoDeEntregasPorRangoDeFecha(paciente,fxOff,fxActual);
			}
			
		}
		else
		{
			return false;
		}
	}
	
	
	
	private boolean calculoDeEntregasPorRangoDeFecha(int paciente,Date fxOff, Date fxActual)
	{
		System.out.println(fxOff);
		System.out.println(fxActual);
		
		Long numeroDeEntregas = sicEntregaTratamientoServiceImpl.countAllByPacienteAndFecha(paciente, fxOff,fxActual);
		
		if(numeroDeEntregas == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
