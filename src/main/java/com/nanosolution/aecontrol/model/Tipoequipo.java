package com.nanosolution.aecontrol.model;
// Generated 5/11/2016 05:13:32 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Tipoequipo generated by hbm2java
 */
@Entity
@Table(name="tipoequipo"
    ,catalog="aecontrol"
    , uniqueConstraints = @UniqueConstraint(columnNames="nombre") 
)
public class Tipoequipo  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private Set<Equipo> equipos = new HashSet<Equipo>(0);

    public Tipoequipo() {
    }

	
    public Tipoequipo(String nombre) {
        this.nombre = nombre;
    }
    public Tipoequipo(String nombre, Set<Equipo> equipos) {
       this.nombre = nombre;
       this.equipos = equipos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="nombre", unique=true, nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tipoequipo")
    public Set<Equipo> getEquipos() {
        return this.equipos;
    }
    
    public void setEquipos(Set<Equipo> equipos) {
        this.equipos = equipos;
    }




}


