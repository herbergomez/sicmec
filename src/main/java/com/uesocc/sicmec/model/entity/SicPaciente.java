/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uesocc.sicmec.model.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
   // @NamedQuery(name = "SicPaciente.findByFkExpediente", query = "SELECT s FROM SicPaciente s WHERE s.fkExpediente = :fkExpediente"),
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
    @Column(name = "numero_expediente")
    private String numeroExpediente;
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
    @JoinColumn(name = "fk_sic_estado_paciente", referencedColumnName = "id_sic_estado_paciente", nullable = false)
    @ManyToOne(optional = false)
    private SicEstadoPaciente fkSicEstadoPaciente;
    @Column(name = "dui_paciente")
    private String duiPaciente;
    @JoinColumn(name = "fk_sic_contacto_paciente", referencedColumnName = "id_sic_contacto_paciente")
    @ManyToOne
    private SicContactoPaciente fkSicContactoPaciente;
    @Column(name = "fx_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxCreacion;
    @Transient
    private Integer edad;
    
    public SicPaciente() {
    }

    public SicPaciente(Integer idSicPaciente) {
        this.idSicPaciente = idSicPaciente;
    }

    public SicPaciente(Integer idSicPaciente, String telefonoPaciente) {
        this.idSicPaciente = idSicPaciente;
        this.telefonoPaciente = telefonoPaciente;
    }

    public Integer getIdSicPaciente() {
        return idSicPaciente;
    }

    public void setIdSicPaciente(Integer idSicPaciente) {
        this.idSicPaciente = idSicPaciente;
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

	/**
	 * @return the fkSicEstadoPaciente
	 */
	public SicEstadoPaciente getFkSicEstadoPaciente() {
		return fkSicEstadoPaciente;
	}

	/**
	 * @param fkSicEstadoPaciente the fkSicEstadoPaciente to set
	 */
	public void setFkSicEstadoPaciente(SicEstadoPaciente fkSicEstadoPaciente) {
		this.fkSicEstadoPaciente = fkSicEstadoPaciente;
	}

	/**
	 * @return the numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * @param numeroExpediente the numeroExpediente to set
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * @return the duiPaciente
	 */
	public String getDuiPaciente() {
		return duiPaciente;
	}

	/**
	 * @param duiPaciente the duiPaciente to set
	 */
	public void setDuiPaciente(String duiPaciente) {
		this.duiPaciente = duiPaciente;
	}

	/**
	 * @return the fkSicContactoPaciente
	 */
	public SicContactoPaciente getFkSicContactoPaciente() {
		return fkSicContactoPaciente;
	}

	/**
	 * @param fkSicContactoPaciente the fkSicContactoPaciente to set
	 */
	public void setFkSicContactoPaciente(SicContactoPaciente fkSicContactoPaciente) {
		this.fkSicContactoPaciente = fkSicContactoPaciente;
	}

	/**
	 * @return the edad
	 */
	public Integer getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	/**
	 * Metodo encargado de cargar de inicio la edad d eun paciente.
	 */
    @PostLoad
    public void calcularEdad(){
    	 Date fNac= null;
         try {
             fNac = new SimpleDateFormat("yyyy-MM-dd").parse(fxNacimiento.toString());
         } catch (Exception ex){
             System.out.println("Error al dar formato a fecha."); 
         }
         Calendar fechaNacimiento = Calendar.getInstance();
         Calendar fechaActual = Calendar.getInstance();
         fechaNacimiento.setTime(fNac);
         int anios = fechaActual.get(Calendar.YEAR)-fechaNacimiento.get(Calendar.YEAR);
         int meses = fechaActual.get(Calendar.MONTH)-fechaNacimiento.get(Calendar.MONTH);
         int dias = fechaActual.get(Calendar.DATE)-fechaNacimiento.get(Calendar.DATE);
         
         if (meses < 0||meses==0&&dias<0){
        	 anios--;
         }
         this.setEdad(anios);
    }

	/**
	 * @return the fxCreacion
	 */
	public Date getFxCreacion() {
		return fxCreacion;
	}

	/**
	 * @param fxCreacion the fxCreacion to set
	 */
	public void setFxCreacion(Date fxCreacion) {
		this.fxCreacion = fxCreacion;
	}
}