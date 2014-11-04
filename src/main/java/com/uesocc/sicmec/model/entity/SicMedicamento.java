/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uesocc.sicmec.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_medicamento", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicMedicamento.findAll", query = "SELECT s FROM SicMedicamento s"),
    @NamedQuery(name = "SicMedicamento.findByIdSicMedicamento", query = "SELECT s FROM SicMedicamento s WHERE s.idSicMedicamento = :idSicMedicamento"),
    @NamedQuery(name = "SicMedicamento.findByNombreMedicamento", query = "SELECT s FROM SicMedicamento s WHERE s.nombreMedicamento = :nombreMedicamento"),
    @NamedQuery(name = "SicMedicamento.findByDescripcionMedicamento", query = "SELECT s FROM SicMedicamento s WHERE s.descripcionMedicamento = :descripcionMedicamento")})
public class SicMedicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_medicamento", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicMedicamento;
    @Column(name = "nombre_medicamento", length = 100)
    private String nombreMedicamento;
    @Column(name = "descripcion_medicamento", length = 300)
    private String descripcionMedicamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicMedicamento")
    private List<SicTratamientoMedicamento> sicTratamientoMedicamentoList;
    @Column(name = "estado_medicamento")
    private String estadoMedicamento;
    
    public SicMedicamento() {
    }

    public SicMedicamento(Integer idSicMedicamento) {
        this.idSicMedicamento = idSicMedicamento;
    }

    public Integer getIdSicMedicamento() {
        return idSicMedicamento;
    }

    public void setIdSicMedicamento(Integer idSicMedicamento) {
        this.idSicMedicamento = idSicMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getDescripcionMedicamento() {
        return descripcionMedicamento;
    }

    public void setDescripcionMedicamento(String descripcionMedicamento) {
        this.descripcionMedicamento = descripcionMedicamento;
    }

    @XmlTransient
    public List<SicTratamientoMedicamento> getSicTratamientoMedicamentoList() {
        return sicTratamientoMedicamentoList;
    }

    public void setSicTratamientoMedicamentoList(List<SicTratamientoMedicamento> sicTratamientoMedicamentoList) {
        this.sicTratamientoMedicamentoList = sicTratamientoMedicamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicMedicamento != null ? idSicMedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicMedicamento)) {
            return false;
        }
        SicMedicamento other = (SicMedicamento) object;
        if ((this.idSicMedicamento == null && other.idSicMedicamento != null) || (this.idSicMedicamento != null && !this.idSicMedicamento.equals(other.idSicMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicMedicamento[ idSicMedicamento=" + idSicMedicamento + " ]";
    }

	/**
	 * @return the estadoMedicamento
	 */
	public String getEstadoMedicamento() {
		return estadoMedicamento;
	}

	/**
	 * @param estadoMedicamento the estadoMedicamento to set
	 */
	public void setEstadoMedicamento(String estadoMedicamento) {
		this.estadoMedicamento = estadoMedicamento;
	}
    
}
