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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_usuario", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicUsuario.findAll", query = "SELECT s FROM SicUsuario s"),
    @NamedQuery(name = "SicUsuario.findByIdSicUsuario", query = "SELECT s FROM SicUsuario s WHERE s.idSicUsuario = :idSicUsuario"),
    @NamedQuery(name = "SicUsuario.findByNombreUsuario", query = "SELECT s FROM SicUsuario s WHERE s.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "SicUsuario.findByFxActivacion", query = "SELECT s FROM SicUsuario s WHERE s.fxActivacion = :fxActivacion"),
    @NamedQuery(name = "SicUsuario.findByFxDesactivacion", query = "SELECT s FROM SicUsuario s WHERE s.fxDesactivacion = :fxDesactivacion"),
    @NamedQuery(name = "SicUsuario.findByFxCreado", query = "SELECT s FROM SicUsuario s WHERE s.fxCreado = :fxCreado"),
    @NamedQuery(name = "SicUsuario.findByCreadoPor", query = "SELECT s FROM SicUsuario s WHERE s.creadoPor = :creadoPor"),
    @NamedQuery(name = "SicUsuario.findByFxModicado", query = "SELECT s FROM SicUsuario s WHERE s.fxModicado = :fxModicado"),
    @NamedQuery(name = "SicUsuario.findByModicadoPor", query = "SELECT s FROM SicUsuario s WHERE s.modicadoPor = :modicadoPor")})
public class SicUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_usuario", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicUsuario;
    @Column(name = "nombre_usuario", length = 50)
    private String nombreUsuario;
    @Column(name = "fx_activacion")
    @Temporal(TemporalType.DATE)
    private Date fxActivacion;
    @Column(name = "fx_desactivacion")
    @Temporal(TemporalType.DATE)
    private Date fxDesactivacion;
    @Column(name = "fx_creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxCreado;
    @Column(name = "creado_por", length = 50)
    private String creadoPor;
    @Column(name = "fx_modicado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxModicado;
    @Column(name = "modicado_por", length = 50)
    private String modicadoPor;
    @JoinColumn(name = "fk_sic_rol", referencedColumnName = "id_sic_rol", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private SicRol fkSicRol;
    @JoinColumn(name = "fk_sic_estado_usuario", referencedColumnName = "id_sic_estado_usuario", nullable = false)
    @ManyToOne(optional = false)
    private SicEstadoUsuario fkSicEstadoUsuario;
    @JoinColumn(name = "fk_sic_persona", referencedColumnName = "id_sic_persona", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference
    private SicPersona fkSicPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicUsuario")
    private List<SicCitaMedica> sicCitaMedicaList;

    public SicUsuario() {
    }

    public SicUsuario(Integer idSicUsuario) {
        this.idSicUsuario = idSicUsuario;
    }

    public Integer getIdSicUsuario() {
        return idSicUsuario;
    }

    public void setIdSicUsuario(Integer idSicUsuario) {
        this.idSicUsuario = idSicUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFxActivacion() {
        return fxActivacion;
    }

    public void setFxActivacion(Date fxActivacion) {
        this.fxActivacion = fxActivacion;
    }

    public Date getFxDesactivacion() {
        return fxDesactivacion;
    }

    public void setFxDesactivacion(Date fxDesactivacion) {
        this.fxDesactivacion = fxDesactivacion;
    }

    public Date getFxCreado() {
        return fxCreado;
    }

    public void setFxCreado(Date fxCreado) {
        this.fxCreado = fxCreado;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFxModicado() {
        return fxModicado;
    }

    public void setFxModicado(Date fxModicado) {
        this.fxModicado = fxModicado;
    }

    public String getModicadoPor() {
        return modicadoPor;
    }

    public void setModicadoPor(String modicadoPor) {
        this.modicadoPor = modicadoPor;
    }

    public SicRol getFkSicRol() {
        return fkSicRol;
    }

    public void setFkSicRol(SicRol fkSicRol) {
        this.fkSicRol = fkSicRol;
    }

    public SicPersona getFkSicPersona() {
        return fkSicPersona;
    }

    public void setFkSicPersona(SicPersona fkSicPersona) {
        this.fkSicPersona = fkSicPersona;
    }

    @XmlTransient
    public List<SicCitaMedica> getSicCitaMedicaList() {
        return sicCitaMedicaList;
    }

    public void setSicCitaMedicaList(List<SicCitaMedica> sicCitaMedicaList) {
        this.sicCitaMedicaList = sicCitaMedicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicUsuario != null ? idSicUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicUsuario)) {
            return false;
        }
        SicUsuario other = (SicUsuario) object;
        if ((this.idSicUsuario == null && other.idSicUsuario != null) || (this.idSicUsuario != null && !this.idSicUsuario.equals(other.idSicUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicUsuario[ idSicUsuario=" + idSicUsuario + " ]";
    }

	/**
	 * @return the fkSicEstadoUsuario
	 */
	public SicEstadoUsuario getFkSicEstadoUsuario() {
		return fkSicEstadoUsuario;
	}

	/**
	 * @param fkSicEstadoUsuario the fkSicEstadoUsuario to set
	 */
	public void setFkSicEstadoUsuario(SicEstadoUsuario fkSicEstadoUsuario) {
		this.fkSicEstadoUsuario = fkSicEstadoUsuario;
	}
    
}
