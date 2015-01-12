/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uesocc.sicmec.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pportillo
 */
@Entity
@Table(name = "sic_auditoria", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicAuditoria.findAll", query = "SELECT s FROM SicAuditoria s"),
    @NamedQuery(name = "SicAuditoria.findByIdSicAuditoria", query = "SELECT s FROM SicAuditoria s WHERE s.idSicAuditoria = :idSicAuditoria"),
    @NamedQuery(name = "SicAuditoria.findByAccion", query = "SELECT s FROM SicAuditoria s WHERE s.accion = :accion"),
    @NamedQuery(name = "SicAuditoria.findByDescripcion", query = "SELECT s FROM SicAuditoria s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SicAuditoria.findByFxAuditoria", query = "SELECT s FROM SicAuditoria s WHERE s.fxAuditoria = :fxAuditoria")})
public class SicAuditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_auditoria", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicAuditoria;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String accion;
    @Basic(optional = false)
    @Column(nullable = false, length = 300)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fx_auditoria", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxAuditoria;
    @JoinColumn(name = "fk_sic_usuario", referencedColumnName = "id_sic_usuario", nullable = false)
    @ManyToOne(optional = false)
    private SicUsuario fkSicUsuario;

    public SicAuditoria() {
    }

    public SicAuditoria(Integer idSicAuditoria) {
        this.idSicAuditoria = idSicAuditoria;
    }

    public SicAuditoria(Integer idSicAuditoria, String accion, String descripcion, Date fxAuditoria) {
        this.idSicAuditoria = idSicAuditoria;
        this.accion = accion;
        this.descripcion = descripcion;
        this.fxAuditoria = fxAuditoria;
    }

    public Integer getIdSicAuditoria() {
        return idSicAuditoria;
    }

    public void setIdSicAuditoria(Integer idSicAuditoria) {
        this.idSicAuditoria = idSicAuditoria;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFxAuditoria() {
        return fxAuditoria;
    }

    public void setFxAuditoria(Date fxAuditoria) {
        this.fxAuditoria = fxAuditoria;
    }

    public SicUsuario getFkSicUsuario() {
        return fkSicUsuario;
    }

    public void setFkSicUsuario(SicUsuario fkSicUsuario) {
        this.fkSicUsuario = fkSicUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicAuditoria != null ? idSicAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicAuditoria)) {
            return false;
        }
        SicAuditoria other = (SicAuditoria) object;
        if ((this.idSicAuditoria == null && other.idSicAuditoria != null) || (this.idSicAuditoria != null && !this.idSicAuditoria.equals(other.idSicAuditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.model.entity.SicAuditoria[ idSicAuditoria=" + idSicAuditoria + " ]";
    }
    
}
