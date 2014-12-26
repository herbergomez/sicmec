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
}
