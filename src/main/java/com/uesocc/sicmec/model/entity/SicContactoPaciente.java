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
@Table(name = "sic_contacto_paciente", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicContactoPaciente.findAll", query = "SELECT s FROM SicContactoPaciente s"),
    @NamedQuery(name = "SicContactoPaciente.findByIdSicContactoPaciente", query = "SELECT s FROM SicContactoPaciente s WHERE s.idSicContactoPaciente = :idSicContactoPaciente"),
    @NamedQuery(name = "SicContactoPaciente.findByNombreContacto", query = "SELECT s FROM SicContactoPaciente s WHERE s.nombreContacto = :nombreContacto"),
    @NamedQuery(name = "SicContactoPaciente.findByApellidoContacto", query = "SELECT s FROM SicContactoPaciente s WHERE s.apellidoContacto = :apellidoContacto"),
    @NamedQuery(name = "SicContactoPaciente.findByDui", query = "SELECT s FROM SicContactoPaciente s WHERE s.dui = :dui"),
    @NamedQuery(name = "SicContactoPaciente.findByTelefono", query = "SELECT s FROM SicContactoPaciente s WHERE s.telefono = :telefono")})
public class SicContactoPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_contacto_paciente")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicContactoPaciente;
    @Basic(optional = false)
    @Column(name = "nombre_contacto")
    private String nombreContacto;
    @Basic(optional = false)
    @Column(name = "apellido_contacto")
    private String apellidoContacto;
    @Basic(optional = false)
    @Column(name = "dui")
    private String dui;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "fkSicContactoPaciente")
    private List<SicPaciente> sicPacienteList;

    public SicContactoPaciente() {
    }

    public SicContactoPaciente(Integer idSicContactoPaciente) {
        this.idSicContactoPaciente = idSicContactoPaciente;
    }

    public SicContactoPaciente(Integer idSicContactoPaciente, String nombreContacto, String apellidoContacto, String dui, String telefono) {
        this.idSicContactoPaciente = idSicContactoPaciente;
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.dui = dui;
        this.telefono = telefono;
    }

    public Integer getIdSicContactoPaciente() {
        return idSicContactoPaciente;
    }

    public void setIdSicContactoPaciente(Integer idSicContactoPaciente) {
        this.idSicContactoPaciente = idSicContactoPaciente;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (idSicContactoPaciente != null ? idSicContactoPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicContactoPaciente)) {
            return false;
        }
        SicContactoPaciente other = (SicContactoPaciente) object;
        if ((this.idSicContactoPaciente == null && other.idSicContactoPaciente != null) || (this.idSicContactoPaciente != null && !this.idSicContactoPaciente.equals(other.idSicContactoPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicContactoPaciente[ idSicContactoPaciente=" + idSicContactoPaciente + " ]";
    }
    
}
