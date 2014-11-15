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
@Table(name = "sic_tipo_examen", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicTipoExamen.findAll", query = "SELECT s FROM SicTipoExamen s"),
    @NamedQuery(name = "SicTipoExamen.findByIdSicTipoExamen", query = "SELECT s FROM SicTipoExamen s WHERE s.idSicTipoExamen = :idSicTipoExamen"),
    @NamedQuery(name = "SicTipoExamen.findByDescripcion", query = "SELECT s FROM SicTipoExamen s WHERE s.descripcion = :descripcion")})
public class SicTipoExamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_tipo_examen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicTipoExamen;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicTipoExamen")
    private List<SicExamen> sicExamenList;

    public SicTipoExamen() {
    }

    public SicTipoExamen(Integer idSicTipoExamen) {
        this.idSicTipoExamen = idSicTipoExamen;
    }

    public Integer getIdSicTipoExamen() {
        return idSicTipoExamen;
    }

    public void setIdSicTipoExamen(Integer idSicTipoExamen) {
        this.idSicTipoExamen = idSicTipoExamen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SicExamen> getSicExamenList() {
        return sicExamenList;
    }

    public void setSicExamenList(List<SicExamen> sicExamenList) {
        this.sicExamenList = sicExamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicTipoExamen != null ? idSicTipoExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicTipoExamen)) {
            return false;
        }
        SicTipoExamen other = (SicTipoExamen) object;
        if ((this.idSicTipoExamen == null && other.idSicTipoExamen != null) || (this.idSicTipoExamen != null && !this.idSicTipoExamen.equals(other.idSicTipoExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicTipoExamen[ idSicTipoExamen=" + idSicTipoExamen + " ]";
    }
    
}
