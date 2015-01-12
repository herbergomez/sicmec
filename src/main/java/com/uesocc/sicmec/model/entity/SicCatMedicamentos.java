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

/**
 *
 * @author xtiyo
 */
@Entity
@Table(name = "sic_cat_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicCatMedicamentos.findAll", query = "SELECT s FROM SicCatMedicamentos s"),
    @NamedQuery(name = "SicCatMedicamentos.findByIdSicCatMedicamentos", query = "SELECT s FROM SicCatMedicamentos s WHERE s.idSicCatMedicamentos = :idSicCatMedicamentos"),
    @NamedQuery(name = "SicCatMedicamentos.findByNombre", query = "SELECT s FROM SicCatMedicamentos s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SicCatMedicamentos.findByDescripcion", query = "SELECT s FROM SicCatMedicamentos s WHERE s.descripcion = :descripcion")})
public class SicCatMedicamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_cat_medicamentos")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicCatMedicamentos;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicCatMedicamentos")
    private List<SicAsignacionMedicamento> sicAsignacionMedicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSicCatMedicamentos")
    private List<SicTratamiento> sicTratamientoList;

    public SicCatMedicamentos() {
    }

    public SicCatMedicamentos(Integer idSicCatMedicamentos) {
        this.idSicCatMedicamentos = idSicCatMedicamentos;
    }

    public SicCatMedicamentos(Integer idSicCatMedicamentos, String nombre) {
        this.idSicCatMedicamentos = idSicCatMedicamentos;
        this.nombre = nombre;
    }

    public Integer getIdSicCatMedicamentos() {
        return idSicCatMedicamentos;
    }

    public void setIdSicCatMedicamentos(Integer idSicCatMedicamentos) {
        this.idSicCatMedicamentos = idSicCatMedicamentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SicAsignacionMedicamento> getSicAsignacionMedicamentoList() {
        return sicAsignacionMedicamentoList;
    }

    public void setSicAsignacionMedicamentoList(List<SicAsignacionMedicamento> sicAsignacionMedicamentoList) {
        this.sicAsignacionMedicamentoList = sicAsignacionMedicamentoList;
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
        hash += (idSicCatMedicamentos != null ? idSicCatMedicamentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicCatMedicamentos)) {
            return false;
        }
        SicCatMedicamentos other = (SicCatMedicamentos) object;
        if ((this.idSicCatMedicamentos == null && other.idSicCatMedicamentos != null) || (this.idSicCatMedicamentos != null && !this.idSicCatMedicamentos.equals(other.idSicCatMedicamentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicCatMedicamentos[ idSicCatMedicamentos=" + idSicCatMedicamentos + " ]";
    }

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
}
