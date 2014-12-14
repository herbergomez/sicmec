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

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_persona", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicPersona.findAll", query = "SELECT s FROM SicPersona s"),
    @NamedQuery(name = "SicPersona.findByIdSicPersona", query = "SELECT s FROM SicPersona s WHERE s.idSicPersona = :idSicPersona"),
    @NamedQuery(name = "SicPersona.findByNombre", query = "SELECT s FROM SicPersona s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SicPersona.findByApellido", query = "SELECT s FROM SicPersona s WHERE s.apellido = :apellido"),
    @NamedQuery(name = "SicPersona.findByEmail", query = "SELECT s FROM SicPersona s WHERE s.email = :email")})
public class SicPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_persona", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicPersona;
    @Column(name = "nombre", length = 30)
    private String nombre;
    @Column(name = "apellido", length = 30)
    private String apellido;
    @Column(name = "email", length = 30)
    private String email;
    @OneToMany(mappedBy = "fkSicPersona")
    @JsonManagedReference
    private List<SicUsuario> sicUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicPersona")
    private List<SicPaciente> sicPacienteList;

    public SicPersona() {
    }

    public SicPersona(Integer idSicPersona) {
        this.idSicPersona = idSicPersona;
    }

    public SicPersona(Integer idSicPersona, String email) {
        this.idSicPersona = idSicPersona;
        this.email = email;
    }

    public Integer getIdSicPersona() {
        return idSicPersona;
    }

    public void setIdSicPersona(Integer idSicPersona) {
        this.idSicPersona = idSicPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<SicUsuario> getSicUsuarioList() {
        return sicUsuarioList;
    }

    public void setSicUsuarioList(List<SicUsuario> sicUsuarioList) {
        this.sicUsuarioList = sicUsuarioList;
    }

    @XmlTransient
    public List<SicPaciente> getSicPacienteList() {
        return sicPacienteList;
    }

    public void setSicPacienteList(List<SicPaciente> sicPacienteList) {
        this.sicPacienteList = sicPacienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicPersona != null ? idSicPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicPersona)) {
            return false;
        }
        SicPersona other = (SicPersona) object;
        if ((this.idSicPersona == null && other.idSicPersona != null) || (this.idSicPersona != null && !this.idSicPersona.equals(other.idSicPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicPersona[ idSicPersona=" + idSicPersona + " ]";
    }
    
}
