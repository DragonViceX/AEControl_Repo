/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.bean;

import com.nanosolution.aecontrol.dao.VehiculoDaoImpl;
import com.nanosolution.aecontrol.model.Vehiculo;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Jessica
 */
@ManagedBean
@RequestScoped

public class VehiculoBean {

    private Vehiculo vehiculo;
    private List<Vehiculo> LVehiculo;
    private List<Vehiculo> filteredVehiculo;

    public VehiculoBean() {
        vehiculo = new Vehiculo();

    }

    @PostConstruct
    public void init() {
        VehiculoDaoImpl vehiculodao = new VehiculoDaoImpl();
        LVehiculo = vehiculodao.findAll();

    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Vehiculo> getLVehiculo() {
        return LVehiculo;
    }

    public void setLVehiculo(List<Vehiculo> LVehiculo) {
        this.LVehiculo = LVehiculo;
    }

    public List<Vehiculo> getFilteredVehiculo() {
        return filteredVehiculo;
    }

    public void setFilteredVehiculo(List<Vehiculo> filteredVehiculo) {
        this.filteredVehiculo = filteredVehiculo;
    }

    public void registrar() {
        if (validar(vehiculo.getPlaca())) { 
            FacesMessage msg = new FacesMessage("El vehiculo ya existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            VehiculoDaoImpl vehiculodao = new VehiculoDaoImpl();
            vehiculodao.create(vehiculo);
            vehiculo = null;
            init();
            FacesMessage msg = new FacesMessage("Vehiculo creado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Valida que no se repita la placa un Vehiculo, ya que debe ser unico.
     *
     * @param n String placa del vehiculo.
     * @return boolean devuelve una bandera.
     *
     */
    public boolean validar(String n) {
        boolean b = false;
        for (Iterator iterador = LVehiculo.listIterator(); iterador.hasNext();) {
            Vehiculo v = (Vehiculo) iterador.next();
            if (v.getPlaca().toLowerCase().equals(n.toLowerCase())) {
                b = true;
            }
        }
        return b;
    }

    public void actualizar() {
        
        VehiculoDaoImpl vehiculodao = new VehiculoDaoImpl();
        vehiculodao.update(vehiculo);
        init();
        FacesMessage msg = new FacesMessage("Vehiculo actualizado");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void eliminar(Vehiculo v) {
        VehiculoDaoImpl vehiculodao = new VehiculoDaoImpl();
        vehiculodao.delete(v);
        init();
        FacesMessage msg = new FacesMessage("Vehiculo eliminado");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowEdit(RowEditEvent event) {
        vehiculo = ((Vehiculo) event.getObject());
        actualizar();
        vehiculo = new Vehiculo();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
