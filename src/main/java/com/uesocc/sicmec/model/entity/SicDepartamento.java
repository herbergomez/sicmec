/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_departamento", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicDepartamento.findAll", query = "SELECT s FROM SicDepartamento s"),
    @NamedQuery(name = "SicDepartamento.findByIdSicDepartamento", query = "SELECT s FROM SicDepartamento s WHERE s.idSicDepartamento = :idSicDepartamento"),
    @NamedQuery(name = "SicDepartamento.findByNombreDepartamento", query = "SELECT s FROM SicDepartamento s WHERE s.nombreDepartamento = :nombreDepartamento")})
public class SicDepartamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sic_departamento")
    private Integer idSicDepartamento;
    @Column(name = "nombre_departamento")
    private String nombreDepartamento;
    @OneToMany(mappedBy = "fkSicDepartamento")
    private List<SicMunicipio> sicMunicipioList;

    public SicDepartamento() {
    }

    public SicDepartamento(Integer idSicDepartamento) {
        this.idSicDepartamento = idSicDepartamento;
    }

    public Integer getIdSicDepartamento() {
        return idSicDepartamento;
    }

    public void setIdSicDepartamento(Integer idSicDepartamento) {
        this.idSicDepartamento = idSicDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @XmlTransient
    public List<SicMunicipio> getSicMunicipioList() {
        return sicMunicipioList;
    }

    public void setSicMunicipioList(List<SicMunicipio> sicMunicipioList) {
        this.sicMunicipioList = sicMunicipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicDepartamento != null ? idSicDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicDepartamento)) {
            return false;
        }
        SicDepartamento other = (SicDepartamento) object;
        if ((this.idSicDepartamento == null && other.idSicDepartamento != null) || (this.idSicDepartamento != null && !this.idSicDepartamento.equals(other.idSicDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.model.entity.SicDepartamento[ idSicDepartamento=" + idSicDepartamento + " ]";
    }
    
}
