package com.uesocc.sicmec.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
		
		if(!tratList.isEmpty())
		{
			SicTratamientoDto trat = tratList.get(0);
			Date fxTratamiento = format.parse(trat.getFxTratamiento());
			Date fxActual = new Date();
			Long diferencia = fxActual.getTime() - fxTratamiento.getTime();
			
					
			switch (trat.getPeriodisidad())
			{
				case "Quincenal":
					break;
				case "Mensual":
					break;
				case "Bimensual":
					break;
				case "Trimestral":
					
					int multiplo = diferencia.intValue()/90;
					
					if (multiplo == 0)
					{
						return calculoDeEntregasPorRangoDeFecha(paciente,fxTratamiento,fxActual);
					}
					else
					{
						Calendar myCal = new GregorianCalendar();
						myCal.setTime(fxTratamiento);
						myCal.add(Calendar.DAY_OF_YEAR, 90*multiplo);
						Date fxOff = myCal.getTime();
						return calculoDeEntregasPorRangoDeFecha(paciente,fxOff,fxActual);
					}	
					
				case "6 meses":
					break;
				default:
					break;
			}
			
			long dias = TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS);
			
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean calculoDeEntregasPorRangoDeFecha(int paciente,Date fxOff, Date fxActual)
	{
		if(sicEntregaTratamientoServiceImpl.countAllByPacienteAndFecha(paciente, fxOff,fxActual) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
