/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uesocc.sicmec.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @Basic(optional = false)
    @Column(name = "id_sic_cita_medica", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicCitaMedica;
    @Column(name = "fx_cita_medica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxCitaMedica;
    @Basic(optional = false)
    @Column(name = "diagnostico", length = 500,nullable = false)
    private String diagnostico;
    @Column(name = "signos_sintomas", length = 500)
    private String signosSintomas;
    @Column(name = "estatura", precision = 10, scale = 3)
    private BigDecimal estatura;
    @Column(name = "peso", precision = 10, scale = 3)
    private BigDecimal peso;
    @Basic(optional = false)
    @Column(name = "comentario", length = 500)
    private String comentario;
    @JoinColumn(name = "fk_sic_usuario", referencedColumnName = "id_sic_usuario")
    @ManyToOne
    @JsonBackReference
    private SicUsuario fkSicUsuario;
    @JoinColumn(name = "fk_sic_paciente", referencedColumnName = "id_sic_paciente", nullable = false)
    @ManyToOne(optional = false)
    private SicPaciente fkSicPaciente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicCitaMedica")
    private List<SicTratamiento> sicTratamientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicCitaMedica")
    private List<SicExamen> sicExamenList;

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
        return "com.uesocc.sicmec.model.entity.SicCitaMedica[ idSicCitaMedica=" + idSicCitaMedica + " ]";
    }

	/**
	 * @return the sicExamenList
	 */
	public List<SicExamen> getSicExamenList() {
		return sicExamenList;
	}

	/**
	 * @param sicExamenList the sicExamenList to set
	 */
	public void setSicExamenList(List<SicExamen> sicExamenList) {
		this.sicExamenList = sicExamenList;
	}

	/**
	 * @return the signosSintomas
	 */
	public String getSignosSintomas() {
		return signosSintomas;
	}

	/**
	 * @param signosSintomas the signosSintomas to set
	 */
	public void setSignosSintomas(String signosSintomas) {
		this.signosSintomas = signosSintomas;
	}

	/**
	 * @return the estatura
	 */
	public BigDecimal getEstatura() {
		return estatura;
	}

	/**
	 * @param estatura the estatura to set
	 */
	public void setEstatura(BigDecimal estatura) {
		this.estatura = estatura;
	}

	/**
	 * @return the peso
	 */
	public BigDecimal getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
    
}
