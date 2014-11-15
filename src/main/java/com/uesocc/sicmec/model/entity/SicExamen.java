/*
 * To change this template, choose Tools | Templates
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
 * @author xtiyo
 */
@Entity
@Table(name = "sic_examen", catalog = "sicmec_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SicExamen.findAll", query = "SELECT s FROM SicExamen s"),
    @NamedQuery(name = "SicExamen.findByIdSicExamen", query = "SELECT s FROM SicExamen s WHERE s.idSicExamen = :idSicExamen"),
    @NamedQuery(name = "SicExamen.findByFxCreado", query = "SELECT s FROM SicExamen s WHERE s.fxCreado = :fxCreado"),
    @NamedQuery(name = "SicExamen.findByCreadoPor", query = "SELECT s FROM SicExamen s WHERE s.creadoPor = :creadoPor"),
    @NamedQuery(name = "SicExamen.findByFxModificado", query = "SELECT s FROM SicExamen s WHERE s.fxModificado = :fxModificado"),
    @NamedQuery(name = "SicExamen.findByModifcadoPor", query = "SELECT s FROM SicExamen s WHERE s.modifcadoPor = :modifcadoPor"),
    @NamedQuery(name = "SicExamen.findByResultado", query = "SELECT s FROM SicExamen s WHERE s.resultado = :resultado"),
    @NamedQuery(name = "SicExamen.findByComentario", query = "SELECT s FROM SicExamen s WHERE s.comentario = :comentario")})
public class SicExamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sic_examen")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSicExamen;
    @Basic(optional = false)
    @Column(name = "fx_creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxCreado;
    @Basic(optional = false)
    @Column(name = "creado_por")
    private String creadoPor;
    @Basic(optional = false)
    @Column(name = "fx_modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxModificado;
    @Basic(optional = false)
    @Column(name = "modifcado_por")
    private String modifcadoPor;
    @Basic(optional = false)
    @Column(name = "resultado")
    private String resultado;
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "fk_sic_tipo_examen", referencedColumnName = "id_sic_tipo_examen")
    @ManyToOne(optional = false)
    private SicTipoExamen fkSicTipoExamen;
    @JoinColumn(name = "fk_sic_cita_medica", referencedColumnName = "id_sic_cita_medica")
    @ManyToOne(optional = false)
    private SicCitaMedica fkSicCitaMedica;

    public SicExamen() {
    }

    public SicExamen(Integer idSicExamen) {
        this.idSicExamen = idSicExamen;
    }

    public SicExamen(Integer idSicExamen, Date fxCreado, String creadoPor, Date fxModificado, String modifcadoPor, String resultado) {
        this.idSicExamen = idSicExamen;
        this.fxCreado = fxCreado;
        this.creadoPor = creadoPor;
        this.fxModificado = fxModificado;
        this.modifcadoPor = modifcadoPor;
        this.resultado = resultado;
    }

    public Integer getIdSicExamen() {
        return idSicExamen;
    }

    public void setIdSicExamen(Integer idSicExamen) {
        this.idSicExamen = idSicExamen;
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

    public Date getFxModificado() {
        return fxModificado;
    }

    public void setFxModificado(Date fxModificado) {
        this.fxModificado = fxModificado;
    }

    public String getModifcadoPor() {
        return modifcadoPor;
    }

    public void setModifcadoPor(String modifcadoPor) {
        this.modifcadoPor = modifcadoPor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public SicTipoExamen getFkSicTipoExamen() {
        return fkSicTipoExamen;
    }

    public void setFkSicTipoExamen(SicTipoExamen fkSicTipoExamen) {
        this.fkSicTipoExamen = fkSicTipoExamen;
    }

    public SicCitaMedica getFkSicCitaMedica() {
        return fkSicCitaMedica;
    }

    public void setFkSicCitaMedica(SicCitaMedica fkSicCitaMedica) {
        this.fkSicCitaMedica = fkSicCitaMedica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSicExamen != null ? idSicExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SicExamen)) {
            return false;
        }
        SicExamen other = (SicExamen) object;
        if ((this.idSicExamen == null && other.idSicExamen != null) || (this.idSicExamen != null && !this.idSicExamen.equals(other.idSicExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uesocc.sicmec.model.entity.SicExamen[ idSicExamen=" + idSicExamen + " ]";
    }
    
}
