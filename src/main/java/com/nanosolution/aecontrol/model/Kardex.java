package com.nanosolution.aecontrol.model;
// Generated 5/11/2016 05:13:32 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Kardex generated by hbm2java
 */
@Entity
@Table(name="kardex"
    ,catalog="aecontrol"
)
public class Kardex  implements java.io.Serializable {


     private Integer id;
     private Equipo equipo;
     private Obra obra;
     private Vehiculo vehiculo;
     private char tipoMov;
     private int consecutivo;
     private Date fecha;
     private Date hora;
     private int cantidad;
     private String observacion;

    public Kardex() {
    }

	
    public Kardex(Equipo equipo, Obra obra, Vehiculo vehiculo, char tipoMov, int consecutivo, Date fecha, Date hora, int cantidad) {
        this.equipo = equipo;
        this.obra = obra;
        this.vehiculo = vehiculo;
        this.tipoMov = tipoMov;
        this.consecutivo = consecutivo;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidad = cantidad;
    }
    public Kardex(Equipo equipo, Obra obra, Vehiculo vehiculo, char tipoMov, int consecutivo, Date fecha, Date hora, int cantidad, String observacion) {
       this.equipo = equipo;
       this.obra = obra;
       this.vehiculo = vehiculo;
       this.tipoMov = tipoMov;
       this.consecutivo = consecutivo;
       this.fecha = fecha;
       this.hora = hora;
       this.cantidad = cantidad;
       this.observacion = observacion;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_equipo", nullable=false)
    public Equipo getEquipo() {
        return this.equipo;
    }
    
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_obra", nullable=false)
    public Obra getObra() {
        return this.obra;
    }
    
    public void setObra(Obra obra) {
        this.obra = obra;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_vehiculo", nullable=false)
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    
    @Column(name="tipo_mov", nullable=false, length=1)
    public char getTipoMov() {
        return this.tipoMov;
    }
    
    public void setTipoMov(char tipoMov) {
        this.tipoMov = tipoMov;
    }

    
    @Column(name="consecutivo", nullable=false)
    public int getConsecutivo() {
        return this.consecutivo;
    }
    
    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", nullable=false, length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="hora", nullable=false, length=8)
    public Date getHora() {
        return this.hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }

    
    @Column(name="cantidad", nullable=false)
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    @Column(name="observacion", length=65535)
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }




}


