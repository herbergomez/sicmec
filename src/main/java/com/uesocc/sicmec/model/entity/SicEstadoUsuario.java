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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_estado_usuario", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicEstadoUsuario.findAll", query = "SELECT s FROM SicEstadoUsuario s"),
    @NamedQuery(name = "SicEstadoUsuario.findByIdSicEstadoUsuario", query = "SELECT s FROM SicEstadoUsuario s WHERE s.idSicEstadoUsuario = :idSicEstadoUsuario"),
    @NamedQuery(name = "SicEstadoUsuario.findByDescripcion", query = "SELECT s FROM SicEstadoUsuario s WHERE s.descripcion = :descripcion")})
public class SicEstadoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_estado_usuario", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicEstadoUsuario;
    @Column(name = "descripcion", length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "fkSicEstadoUsuario")
    private List<SicUsuario> sicUsuarioList;

    public SicEstadoUsuario() {
    }

    public SicEstadoUsuario(Integer idSicEstadoUsuario) {
        this.idSicEstadoUsuario = idSicEstadoUsuario;
    }

    public Integer getIdSicEstadoUsuario() {
        return idSicEstadoUsuario;
    }

    public void setIdSicEstadoUsuario(Integer idSicEstadoUsuario) {
        this.idSicEstadoUsuario = idSicEstadoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SicUsuario> getSicUsuarioList() {
        return sicUsuarioList;
    }

    public void setSicUsuarioList(List<SicUsuario> sicUsuarioList) {
        this.sicUsuarioList = sicUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicEstadoUsuario != null ? idSicEstadoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicEstadoUsuario)) {
            return false;
        }
        SicEstadoUsuario other = (SicEstadoUsuario) object;
        if ((this.idSicEstadoUsuario == null && other.idSicEstadoUsuario != null) || (this.idSicEstadoUsuario != null && !this.idSicEstadoUsuario.equals(other.idSicEstadoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicEstadoUsuario[ idSicEstadoUsuario=" + idSicEstadoUsuario + " ]";
    }
    
}
