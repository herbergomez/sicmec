/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name = "sic_cita_medica", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicCitaMedica.findAll", query = "SELECT s FROM SicCitaMedica s"),
    @NamedQuery(name = "SicCitaMedica.findByIdSicCitaMedica", query = "SELECT s FROM SicCitaMedica s WHERE s.idSicCitaMedica = :idSicCitaMedica"),
    @NamedQuery(name = "SicCitaMedica.findByFxCitaMedica", query = "SELECT s FROM SicCitaMedica s WHERE s.fxCitaMedica = :fxCitaMedica"),
    @NamedQuery(name = "SicCitaMedica.findByDiagnostico", query = "SELECT s FROM SicCitaMedica s WHERE s.diagnostico = :diagnostico"),
    @NamedQuery(name = "SicCitaMedica.findByComentario", query = "SELECT s FROM SicCitaMedica s WHERE s.comentario = :comentario")})
public class SicCitaMedica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sic_cita_medica")
    private Integer idSicCitaMedica;
    @Column(name = "fx_cita_medica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxCitaMedica;
    private String diagnostico;
    @Basic(optional = false)
    private String comentario;
    @JoinColumn(name = "fk_sic_usuario", referencedColumnName = "id_sic_usuario")
    @ManyToOne(optional = false)
    private SicUsuario fkSicUsuario;
    @JoinColumn(name = "fk_sic_paciente", referencedColumnName = "id_sic_paciente")
    @ManyToOne(optional = false)
    private SicPaciente fkSicPaciente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicCitaMedica")
    private List<SicTratamiento> sicTratamientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicCitaMedica")
    private List<SicExamenCita> sicExamenCitaList;

    public SicCitaMedica() {
    }

    public SicCitaMedica(Integer idSicCitaMedica) {
        this.idSicCitaMedica = idSicCitaMedica;
    }

    public SicCitaMedica(Integer idSicCitaMedica, String comentario) {
        this.idSicCitaMedica = idSicCitaMedica;
        this.comentario = comentario;
    }

    public Integer getIdSicCitaMedica() {
        return idSicCitaMedica;
    }

    public void setIdSicCitaMedica(Integer idSicCitaMedica) {
        this.idSicCitaMedica = idSicCitaMedica;
    }

    public Date getFxCitaMedica() {
        return fxCitaMedica;
    }

    public void setFxCitaMedica(Date fxCitaMedica) {
        this.fxCitaMedica = fxCitaMedica;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public SicUsuario getFkSicUsuario() {
        return fkSicUsuario;
    }

    public void setFkSicUsuario(SicUsuario fkSicUsuario) {
        this.fkSicUsuario = fkSicUsuario;
    }

    public SicPaciente getFkSicPaciente() {
        return fkSicPaciente;
    }

    public void setFkSicPaciente(SicPaciente fkSicPaciente) {
        this.fkSicPaciente = fkSicPaciente;
    }

    @XmlTransient
    public List<SicTratamiento> getSicTratamientoList() {
        return sicTratamientoList;
    }

    public void setSicTratamientoList(List<SicTratamiento> sicTratamientoList) {
        this.sicTratamientoList = sicTratamientoList;
    }

    @XmlTransient
    public List<SicExamenCita> getSicExamenCitaList() {
        return sicExamenCitaList;
    }

    public void setSicExamenCitaList(List<SicExamenCita> sicExamenCitaList) {
        this.sicExamenCitaList = sicExamenCitaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicCitaMedica != null ? idSicCitaMedica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicCitaMedica)) {
            return false;
        }
        SicCitaMedica other = (SicCitaMedica) object;
        if ((this.idSicCitaMedica == null && other.idSicCitaMedica != null) || (this.idSicCitaMedica != null && !this.idSicCitaMedica.equals(other.idSicCitaMedica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.model.entity.SicCitaMedica[ idSicCitaMedica=" + idSicCitaMedica + " ]";
    }
    
}
