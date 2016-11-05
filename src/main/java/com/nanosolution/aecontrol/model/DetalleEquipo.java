package com.nanosolution.aecontrol.model;
// Generated 5/11/2016 05:13:32 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DetalleEquipo generated by hbm2java
 */
@Entity
@Table(name="detalle_equipo"
    ,catalog="aecontrol"
)
public class DetalleEquipo  implements java.io.Serializable {


     private Integer idDetalleEquipo;
     private Equipo equipo;
     private String consecutivo;
     private String numSerie;
     private char estadoAlquiler;

    public DetalleEquipo() {
    }

	
    public DetalleEquipo(Equipo equipo, char estadoAlquiler) {
        this.equipo = equipo;
        this.estadoAlquiler = estadoAlquiler;
    }
    public DetalleEquipo(Equipo equipo, String consecutivo, String numSerie, char estadoAlquiler) {
       this.equipo = equipo;
       this.consecutivo = consecutivo;
       this.numSerie = numSerie;
       this.estadoAlquiler = estadoAlquiler;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_detalle_equipo", unique=true, nullable=false)
    public Integer getIdDetalleEquipo() {
        return this.idDetalleEquipo;
    }
    
    public void setIdDetalleEquipo(Integer idDetalleEquipo) {
        this.idDetalleEquipo = idDetalleEquipo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_equipo", nullable=false)
    public Equipo getEquipo() {
        return this.equipo;
    }
    
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    
    @Column(name="consecutivo", length=20)
    public String getConsecutivo() {
        return this.consecutivo;
    }
    
    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    
    @Column(name="num_serie", length=20)
    public String getNumSerie() {
        return this.numSerie;
    }
    
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    
    @Column(name="estado_alquiler", nullable=false, length=1)
    public char getEstadoAlquiler() {
        return this.estadoAlquiler;
    }
    
    public void setEstadoAlquiler(char estadoAlquiler) {
        this.estadoAlquiler = estadoAlquiler;
    }




}


