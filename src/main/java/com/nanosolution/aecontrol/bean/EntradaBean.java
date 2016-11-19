/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.bean;

import com.nanosolution.aecontrol.dao.DetalleEquipoDaoImpl;
import com.nanosolution.aecontrol.dao.EquipoDaoImpl;
import com.nanosolution.aecontrol.dao.KardexDaoImpl;
import com.nanosolution.aecontrol.dao.ObraDaoImpl;
import com.nanosolution.aecontrol.dao.VehiculoDaoImpl;
import com.nanosolution.aecontrol.model.Cliente;
import com.nanosolution.aecontrol.model.DetalleEquipo;
import com.nanosolution.aecontrol.model.Equipo;
import com.nanosolution.aecontrol.model.Kardex;
import com.nanosolution.aecontrol.model.Obra;
import com.nanosolution.aecontrol.model.Vehiculo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Jessica
 */
@ManagedBean
@RequestScoped
public class EntradaBean {

    private Kardex nuevo;
    private Equipo selectEquipo;
    private Obra selectObra;
    private Vehiculo selectVehiculo;
    private DetalleEquipo detalleEqui;
    private List<Kardex> listSalidas;
    private List<Obra> listObra;
    private List<Vehiculo> listVehiculo;
    KardexDaoImpl kardexdao;

    public EntradaBean() {

    }

    @PostConstruct
    public void init() {
        nuevo = new Kardex();
        selectEquipo = new Equipo();
        selectObra = new Obra();
        selectVehiculo = new Vehiculo();
        detalleEqui = new DetalleEquipo();
        kardexdao = new KardexDaoImpl();
        System.out.println("Entro");
        KardexDaoImpl kDao = new KardexDaoImpl();
        ObraDaoImpl obraDao = new ObraDaoImpl();
        VehiculoDaoImpl vehiculoDao = new VehiculoDaoImpl();

        listObra = obraDao.getObrasActivas();
        listSalidas = kDao.getKardexAlquilados();
        listVehiculo = vehiculoDao.findAll();
    }

    public void actualizarEntradas() {

    }

    public void actualizar() {

        EquipoDaoImpl equipodao = new EquipoDaoImpl();
        selectEquipo = nuevo.getEquipo();

        selectEquipo.setCantidadStock(selectEquipo.getCantidadStock() +1);
        selectEquipo.setCantidad(selectEquipo.getCantidad() - 1);

        equipodao.update(selectEquipo);

        kardexdao = new KardexDaoImpl();
        nuevo.setEquipo(selectEquipo);
        nuevo.setTipoMov('D');

        kardexdao.update(nuevo);
        nuevo = new Kardex();
        init();

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo fue devuelto exitosamente", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Kardex getNuevo() {
        return nuevo;
    }

    public void setNuevo(Kardex nuevo) {
        this.nuevo = nuevo;
    }

    public Equipo getSelectEquipo() {
        return selectEquipo;
    }

    public void setSelectEquipo(Equipo selectEquipo) {
        this.selectEquipo = selectEquipo;
    }

    public Obra getSelectObra() {
        return selectObra;
    }

    public void setSelectObra(Obra selectObra) {
        this.selectObra = selectObra;
    }

    public Vehiculo getSelectVehiculo() {
        return selectVehiculo;
    }

    public void setSelectVehiculo(Vehiculo selectVehiculo) {
        this.selectVehiculo = selectVehiculo;
    }

    public DetalleEquipo getDetalleEqui() {
        return detalleEqui;
    }

    public void setDetalleEqui(DetalleEquipo detalleEqui) {
        this.detalleEqui = detalleEqui;
    }

    public List<Obra> getListObra() {
        return listObra;
    }

    public void setListObra(List<Obra> listObra) {
        this.listObra = listObra;
    }

    public List<Vehiculo> getListVehiculo() {
        return listVehiculo;
    }

    public void setListVehiculo(List<Vehiculo> listVehiculo) {
        this.listVehiculo = listVehiculo;
    }

    public List<Kardex> getListSalidas() {
        return listSalidas;
    }

    public void setListSalidas(List<Kardex> listSalidas) {
        this.listSalidas = listSalidas;
    }

}
