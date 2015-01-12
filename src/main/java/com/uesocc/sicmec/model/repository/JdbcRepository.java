/**
 * 
 */
package com.uesocc.sicmec.model.repository;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uesocc.sicmec.model.dto.SicEntregaTratamientoDto;
import com.uesocc.sicmec.model.dto.SicGraficosDto;


/**
 * @autor pablo portillo
 * @fecha 17/12/2014
 */

@Repository
public class JdbcRepository 
{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate =new JdbcTemplate(dataSource);
	}
	
	
	private String examenesPorPaciente = 
			 " SELECT sictexm.descripcion as tipoExamen,sicexm.resultado,to_char(siccm.fx_cita_medica, 'YYYY-MM-DD') as fx_cita_medica,sicexm.comentario"
			+" FROM sic_examen sicexm"
			+" INNER JOIN sic_cita_medica siccm ON siccm.id_sic_cita_medica = sicexm.fk_sic_cita_medica"
			+" INNER JOIN sic_tipo_examen sictexm ON sictexm.id_sic_tipo_examen = sicexm.fk_sic_tipo_examen"
			+" WHERE sictexm.id_sic_tipo_examen = ? AND siccm.fk_sic_paciente = ? ORDER BY siccm.fx_cita_medica ASC LIMIT ?";
	
	private String indiceMasaCorporal =	
			 "SELECT cm.peso, cm.estatura,cm.fx_cita_medica,cm.comentario from sic_cita_medica cm" 
			+" WHERE cm.fk_sic_paciente = ? AND cm.peso IS NOT NULL AND cm.estatura IS NOT NULL ORDER BY cm.fx_cita_medica ASC LIMIT ?";
	
	private String entregasDeMedPorPaciente =
			"SELECT et.* FROM sic_entrega_tratamiento et "+
			" INNER JOIN sic_tratamiento tn ON tn.id_sic_tratamiento = et.fk_sic_tratamiento"+
			" INNER JOIN sic_cita_medica cm ON cm.id_sic_cita_medica = tn.fk_sic_cita_medica"+
			" INNER JOIN sic_paciente pm ON pm.id_sic_paciente = cm.fk_sic_paciente"+
			" WHERE pm.numero_expediente = ? ORDER BY et.fx_entrega_tratamiento DESC LIMIT ?";
	
	public List<SicGraficosDto> findExamsForGraphicByPaciente(int tipo, int id)
	{
		List<SicGraficosDto> results = jdbcTemplate.query(
                this.examenesPorPaciente, new Object[] {tipo,id,20},
                new RowMapper<SicGraficosDto>() 
                {
                    @Override
                    public SicGraficosDto mapRow(ResultSet rs, int rowNum) throws SQLException 
                    {
                        return new SicGraficosDto(rs.getString("tipoExamen"), rs.getString("fx_cita_medica"),
                                rs.getString("comentario"),rs.getString("resultado"));
                    }
                });
		
		return results;
	}
	
	public List<SicGraficosDto> findBMIForGraphicByPaciente(int tipo, int id)
	{
		List<SicGraficosDto> results = jdbcTemplate.query(
                this.indiceMasaCorporal, new Object[] {id,20},
                new RowMapper<SicGraficosDto>() 
                {
                    @Override
                    public SicGraficosDto mapRow(ResultSet rs, int rowNum) throws SQLException 
                    {
                    	BigDecimal peso = rs.getBigDecimal("peso");
                    	BigDecimal estatura = rs.getBigDecimal("estatura");
                    	
                    	Double result = (peso.doubleValue() / 2.2) / Math.pow(estatura.doubleValue(),2);
                    	
                        return new SicGraficosDto("Indice de masa corporal",rs.getString("fx_cita_medica"),
                                rs.getString("comentario"),result.toString());
                    }
                });
		
		return results;
	}
	
	public List<SicEntregaTratamientoDto> findAllTreatmentDeliveryByPaciente(String id)
	{
		List<SicEntregaTratamientoDto> result = jdbcTemplate.query(this.entregasDeMedPorPaciente,new Object[] {id,10},
				new RowMapper<SicEntregaTratamientoDto>() 
	                {
	                    @Override
	                    public SicEntregaTratamientoDto mapRow(ResultSet rs, int rowNum) throws SQLException 
	                    {
	                        return new SicEntregaTratamientoDto(rs.getObject("id_sic_entrega_tratamiento").toString(),
	                        		rs.getString("comentario"),rs.getString("fx_entrega_tratamiento"),rs.getString("tipo"));
	                    }
	                });
	                return result;
	}
}
