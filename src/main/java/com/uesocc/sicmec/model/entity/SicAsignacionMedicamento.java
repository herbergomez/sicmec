/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uesocc.sicmec.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_asignacion_medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicAsignacionMedicamento.findAll", query = "SELECT s FROM SicAsignacionMedicamento s"),
    @NamedQuery(name = "SicAsignacionMedicamento.findByIdSicAsignacionMedicamento", query = "SELECT s FROM SicAsignacionMedicamento s WHERE s.idSicAsignacionMedicamento = :idSicAsignacionMedicamento")})

@NamedNativeQueries
({
	@NamedNativeQuery(name="SicAsignacionMedicamento.findAllDrugsOfPaq",
			query="SELECT m.* FROM sic_asignacion_medicamento s INNER JOIN sic_medicamento m ON m.id_sic_medicamento = s.fk_sic_medicamento INNER JOIN sic_cat_medicamentos c ON c.id_sic_cat_medicamentos = s.fk_sic_cat_medicamentos WHERE c.id_sic_cat_medicamentos = ? AND s.estado = '1'",resultClass=SicMedicamento.class),
	@NamedNativeQuery(name="SicAsignacionMedicamento.findAllDrugsOfNotInPaq",
	query="SELECT m.* FROM sic_medicamento m WHERE m.id_sic_medicamento NOT IN (SELECT s.fk_sic_medicamento FROM sic_asignacion_medicamento s WHERE s.fk_sic_cat_medicamentos = ? AND s.estado = '1')",resultClass=SicMedicamento.class)
})
public class SicAsignacionMedicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_asignacion_medicamento")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicAsignacionMedicamento;
    @JoinColumn(name = "fk_sic_medicamento", referencedColumnName = "id_sic_medicamento")
    @ManyToOne(optional = false)
    private SicMedicamento fkSicMedicamento;
    @JoinColumn(name = "fk_sic_cat_medicamentos", referencedColumnName = "id_sic_cat_medicamentos")
    @ManyToOne(optional = false)
    private SicCatMedicamentos fkSicCatMedicamentos;
    @Column(name = "estado", length = 100)
    private String estado;
    
    public SicAsignacionMedicamento() {
    }

    public SicAsignacionMedicamento(Integer idSicAsignacionMedicamento) {
        this.idSicAsignacionMedicamento = idSicAsignacionMedicamento;
    }

    public Integer getIdSicAsignacionMedicamento() {
        return idSicAsignacionMedicamento;
    }

    public void setIdSicAsignacionMedicamento(Integer idSicAsignacionMedicamento) {
        this.idSicAsignacionMedicamento = idSicAsignacionMedicamento;
    }

    public SicMedicamento getFkSicMedicamento() {
        return fkSicMedicamento;
    }

    public void setFkSicMedicamento(SicMedicamento fkSicMedicamento) {
        this.fkSicMedicamento = fkSicMedicamento;
    }

    public SicCatMedicamentos getFkSicCatMedicamentos() {
        return fkSicCatMedicamentos;
    }

    public void setFkSicCatMedicamentos(SicCatMedicamentos fkSicCatMedicamentos) {
        this.fkSicCatMedicamentos = fkSicCatMedicamentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicAsignacionMedicamento != null ? idSicAsignacionMedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicAsignacionMedicamento)) {
            return false;
        }
        SicAsignacionMedicamento other = (SicAsignacionMedicamento) object;
        if ((this.idSicAsignacionMedicamento == null && other.idSicAsignacionMedicamento != null) || (this.idSicAsignacionMedicamento != null && !this.idSicAsignacionMedicamento.equals(other.idSicAsignacionMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicAsignacionMedicamento[ idSicAsignacionMedicamento=" + idSicAsignacionMedicamento + " ]";
    }

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
}
