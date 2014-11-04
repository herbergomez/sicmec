/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uesocc.sicmec.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_pais", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicPais.findAll", query = "SELECT s FROM SicPais s"),
    @NamedQuery(name = "SicPais.findByIdSicPais", query = "SELECT s FROM SicPais s WHERE s.idSicPais = :idSicPais"),
    @NamedQuery(name = "SicPais.findByNombrePais", query = "SELECT s FROM SicPais s WHERE s.nombrePais = :nombrePais")})
public class SicPais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_pais")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicPais;
    @Basic(optional = false)
    @Column(name = "nombre_pais")
    private String nombrePais;
    @OneToMany(mappedBy = "fkSicPais")
    private List<SicDepartamento> sicDepartamentoList;
    public SicPais() {
    }

    public SicPais(Integer idSicPais) {
        this.idSicPais = idSicPais;
    }

    public SicPais(Integer idSicPais, String nombrePais) {
        this.idSicPais = idSicPais;
        this.nombrePais = nombrePais;
    }

    public Integer getIdSicPais() {
        return idSicPais;
    }

    public void setIdSicPais(Integer idSicPais) {
        this.idSicPais = idSicPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicPais != null ? idSicPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicPais)) {
            return false;
        }
        SicPais other = (SicPais) object;
        if ((this.idSicPais == null && other.idSicPais != null) || (this.idSicPais != null && !this.idSicPais.equals(other.idSicPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicPais[ idSicPais=" + idSicPais + " ]";
    }

	/**
	 * @return the sicDepartamentoList
	 */
	public List<SicDepartamento> getSicDepartamentoList() {
		return sicDepartamentoList;
	}

	/**
	 * @param sicDepartamentoList the sicDepartamentoList to set
	 */
	public void setSicDepartamentoList(List<SicDepartamento> sicDepartamentoList) {
		this.sicDepartamentoList = sicDepartamentoList;
	}
    
}
