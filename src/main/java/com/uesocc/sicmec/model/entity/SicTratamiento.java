/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uesocc.sicmec.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_tratamiento", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicTratamiento.findAll", query = "SELECT s FROM SicTratamiento s"),
    @NamedQuery(name = "SicTratamiento.findByIdSicTratamiento", query = "SELECT s FROM SicTratamiento s WHERE s.idSicTratamiento = :idSicTratamiento"),
    @NamedQuery(name = "SicTratamiento.findByComentario", query = "SELECT s FROM SicTratamiento s WHERE s.comentario = :comentario"),
    @NamedQuery(name = "SicTratamiento.findByPeriodisidad", query = "SELECT s FROM SicTratamiento s WHERE s.periodisidad = :periodisidad"),
    @NamedQuery(name = "SicTratamiento.findByFxTratamiento", query = "SELECT s FROM SicTratamiento s WHERE s.fxTratamiento = :fxTratamiento")})
public class SicTratamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_tratamiento", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicTratamiento;
    @Basic(optional = false)
    @Column(name = "comentario", nullable = false, length = 300)
    private String comentario;
    @Column(name = "periodisidad", length = 50)
    private String periodisidad;
    @Column(name = "fx_tratamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxTratamiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicTratamiento")
    private List<SicEntregaTratamiento> sicEntregaTratamientoList;
    @JoinColumn(name = "fk_sic_cita_medica", referencedColumnName = "id_sic_cita_medica", nullable = false)
    @ManyToOne(optional = false)
    private SicCitaMedica fkSicCitaMedica;
    @Basic(optional = false)
    @Column(name = "dosis")
    private String dosis;
    @JoinColumn(name = "fk_sic_cat_medicamentos", referencedColumnName = "id_sic_cat_medicamentos")
    @ManyToOne(optional = false)
    private SicCatMedicamentos fkSicCatMedicamentos;

    public SicTratamiento() {
    }

    public SicTratamiento(Integer idSicTratamiento) {
        this.idSicTratamiento = idSicTratamiento;
    }

    public SicTratamiento(Integer idSicTratamiento, String comentario) {
        this.idSicTratamiento = idSicTratamiento;
        this.comentario = comentario;
    }

    public Integer getIdSicTratamiento() {
        return idSicTratamiento;
    }

    public void setIdSicTratamiento(Integer idSicTratamiento) {
        this.idSicTratamiento = idSicTratamiento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getPeriodisidad() {
        return periodisidad;
    }

    public void setPeriodisidad(String periodisidad) {
        this.periodisidad = periodisidad;
    }

    public Date getFxTratamiento() {
        return fxTratamiento;
    }

    public void setFxTratamiento(Date fxTratamiento) {
        this.fxTratamiento = fxTratamiento;
    }

    @XmlTransient
    public List<SicEntregaTratamiento> getSicEntregaTratamientoList() {
        return sicEntregaTratamientoList;
    }

    public void setSicEntregaTratamientoList(List<SicEntregaTratamiento> sicEntregaTratamientoList) {
        this.sicEntregaTratamientoList = sicEntregaTratamientoList;
    }

    public SicCitaMedica getFkSicCitaMedica() {
        return fkSicCitaMedica;
    }

    public void setFkSicCitaMedica(SicCitaMedica fkSicCitaMedica) {
        this.fkSicCitaMedica = fkSicCitaMedica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicTratamiento != null ? idSicTratamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicTratamiento)) {
            return false;
        }
        SicTratamiento other = (SicTratamiento) object;
        if ((this.idSicTratamiento == null && other.idSicTratamiento != null) || (this.idSicTratamiento != null && !this.idSicTratamiento.equals(other.idSicTratamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicTratamiento[ idSicTratamiento=" + idSicTratamiento + " ]";
    }

	/**
	 * @return the dosis
	 */
	public String getDosis() {
		return dosis;
	}

	/**
	 * @param dosis the dosis to set
	 */
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	/**
	 * @return the fkSicCatMedicamentos
	 */
	public SicCatMedicamentos getFkSicCatMedicamentos() {
		return fkSicCatMedicamentos;
	}

	/**
	 * @param fkSicCatMedicamentos the fkSicCatMedicamentos to set
	 */
	public void setFkSicCatMedicamentos(SicCatMedicamentos fkSicCatMedicamentos) {
		this.fkSicCatMedicamentos = fkSicCatMedicamentos;
	}
    
}
