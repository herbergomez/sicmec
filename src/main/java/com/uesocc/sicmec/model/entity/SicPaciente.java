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
@Table(name = "sic_paciente", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicPaciente.findAll", query = "SELECT s FROM SicPaciente s"),
    @NamedQuery(name = "SicPaciente.findByIdSicPaciente", query = "SELECT s FROM SicPaciente s WHERE s.idSicPaciente = :idSicPaciente"),
    @NamedQuery(name = "SicPaciente.findByFkExpediente", query = "SELECT s FROM SicPaciente s WHERE s.fkExpediente = :fkExpediente"),
    @NamedQuery(name = "SicPaciente.findByTelefonoPaciente", query = "SELECT s FROM SicPaciente s WHERE s.telefonoPaciente = :telefonoPaciente"),
    @NamedQuery(name = "SicPaciente.findByDireccionPaciente", query = "SELECT s FROM SicPaciente s WHERE s.direccionPaciente = :direccionPaciente"),
    @NamedQuery(name = "SicPaciente.findBySexoPaciente", query = "SELECT s FROM SicPaciente s WHERE s.sexoPaciente = :sexoPaciente"),
    @NamedQuery(name = "SicPaciente.findByFxNacimiento", query = "SELECT s FROM SicPaciente s WHERE s.fxNacimiento = :fxNacimiento")})
public class SicPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_paciente", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicPaciente;
    @Basic(optional = false)
    @Column(name = "fk_expediente", nullable = false)
    private int fkExpediente;
    @Basic(optional = false)
    @Column(name = "telefono_paciente", nullable = false, length = 10)
    private String telefonoPaciente;
    @Column(name = "direccion_paciente", length = 100)
    private String direccionPaciente;
    @Column(name = "sexo_paciente", length = 2)
    private String sexoPaciente;
    @Column(name = "fx_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fxNacimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicPaciente")
    private List<SicCitaMedica> sicCitaMedicaList;
    @JoinColumn(name = "fk_sic_tipo_patologia", referencedColumnName = "id_sic_tipo_patologia")
    @ManyToOne
    private SicTipoPatologia fkSicTipoPatologia;
    @JoinColumn(name = "fk_sic_persona", referencedColumnName = "id_sic_persona", nullable = false)
    @ManyToOne(optional = false)
    private SicPersona fkSicPersona;
    @JoinColumn(name = "fk_sic_municipio", referencedColumnName = "id_sic_municipio",nullable = false)
    @ManyToOne(optional = false)
    private SicMunicipio fkSicMunicipio;
    @JoinColumn(name = "id_sic_paciente", referencedColumnName = "id_sic_estado_paciente", nullable = false)
    @ManyToOne(optional = false)
    private SicEstadoPaciente sicEstadoPaciente;

    public SicPaciente() {
    }

    public SicPaciente(Integer idSicPaciente) {
        this.idSicPaciente = idSicPaciente;
    }

    public SicPaciente(Integer idSicPaciente, int fkExpediente, String telefonoPaciente) {
        this.idSicPaciente = idSicPaciente;
        this.fkExpediente = fkExpediente;
        this.telefonoPaciente = telefonoPaciente;
    }

    public Integer getIdSicPaciente() {
        return idSicPaciente;
    }

    public void setIdSicPaciente(Integer idSicPaciente) {
        this.idSicPaciente = idSicPaciente;
    }

    public int getFkExpediente() {
        return fkExpediente;
    }

    public void setFkExpediente(int fkExpediente) {
        this.fkExpediente = fkExpediente;
    }

    public String getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

    public String getDireccionPaciente() {
        return direccionPaciente;
    }

    public void setDireccionPaciente(String direccionPaciente) {
        this.direccionPaciente = direccionPaciente;
    }

    public String getSexoPaciente() {
        return sexoPaciente;
    }

    public void setSexoPaciente(String sexoPaciente) {
        this.sexoPaciente = sexoPaciente;
    }

    public Date getFxNacimiento() {
        return fxNacimiento;
    }

    public void setFxNacimiento(Date fxNacimiento) {
        this.fxNacimiento = fxNacimiento;
    }

    @XmlTransient
    public List<SicCitaMedica> getSicCitaMedicaList() {
        return sicCitaMedicaList;
    }

    public void setSicCitaMedicaList(List<SicCitaMedica> sicCitaMedicaList) {
        this.sicCitaMedicaList = sicCitaMedicaList;
    }

    public SicTipoPatologia getFkSicTipoPatologia() {
        return fkSicTipoPatologia;
    }

    public void setFkSicTipoPatologia(SicTipoPatologia fkSicTipoPatologia) {
        this.fkSicTipoPatologia = fkSicTipoPatologia;
    }

    public SicPersona getFkSicPersona() {
        return fkSicPersona;
    }

    public void setFkSicPersona(SicPersona fkSicPersona) {
        this.fkSicPersona = fkSicPersona;
    }

    public SicMunicipio getFkSicMunicipio() {
        return fkSicMunicipio;
    }

    public void setFkSicMunicipio(SicMunicipio fkSicMunicipio) {
        this.fkSicMunicipio = fkSicMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicPaciente != null ? idSicPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicPaciente)) {
            return false;
        }
        SicPaciente other = (SicPaciente) object;
        if ((this.idSicPaciente == null && other.idSicPaciente != null) || (this.idSicPaciente != null && !this.idSicPaciente.equals(other.idSicPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicPaciente[ idSicPaciente=" + idSicPaciente + " ]";
    }
    
}
