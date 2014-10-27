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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_estado_paciente", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicEstadoPaciente.findAll", query = "SELECT s FROM SicEstadoPaciente s"),
    @NamedQuery(name = "SicEstadoPaciente.findByIdSicEstadoPaciente", query = "SELECT s FROM SicEstadoPaciente s WHERE s.idSicEstadoPaciente = :idSicEstadoPaciente"),
    @NamedQuery(name = "SicEstadoPaciente.findByDescripcion", query = "SELECT s FROM SicEstadoPaciente s WHERE s.descripcion = :descripcion")})
public class SicEstadoPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_estado_paciente", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicEstadoPaciente;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "fkSicEstadoPaciente")
    private List<SicPaciente> sicPacienteList;
    
    public SicEstadoPaciente() {
    }

    public SicEstadoPaciente(Integer idSicEstadoPaciente) {
        this.idSicEstadoPaciente = idSicEstadoPaciente;
    }

    public SicEstadoPaciente(Integer idSicEstadoPaciente, String descripcion) {
        this.idSicEstadoPaciente = idSicEstadoPaciente;
        this.descripcion = descripcion;
    }

    public Integer getIdSicEstadoPaciente() {
        return idSicEstadoPaciente;
    }

    public void setIdSicEstadoPaciente(Integer idSicEstadoPaciente) {
        this.idSicEstadoPaciente = idSicEstadoPaciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicEstadoPaciente != null ? idSicEstadoPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicEstadoPaciente)) {
            return false;
        }
        SicEstadoPaciente other = (SicEstadoPaciente) object;
        if ((this.idSicEstadoPaciente == null && other.idSicEstadoPaciente != null) || (this.idSicEstadoPaciente != null && !this.idSicEstadoPaciente.equals(other.idSicEstadoPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicEstadoPaciente[ idSicEstadoPaciente=" + idSicEstadoPaciente + " ]";
    }
    
}
