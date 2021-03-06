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
public class KardexBean {

    private Kardex nuevo;
    private Equipo selectEquipo;
    private Obra selectObra;
    private Vehiculo selectVehiculo;
    private DetalleEquipo detalleEqui;
    private List<DetalleEquipo> listDetalle;
    private List<DetalleEquipo> listDetalleEntrada;
    private List<Obra> listObra;
    private List<Vehiculo> listVehiculo;
    KardexDaoImpl kardexdao;

    @PostConstruct
    public void init() {
        nuevo = new Kardex();
        selectEquipo = new Equipo();
        selectObra = new Obra();
        selectVehiculo = new Vehiculo();
        detalleEqui = new DetalleEquipo();
        kardexdao = new KardexDaoImpl();

        DetalleEquipoDaoImpl detalleDao = new DetalleEquipoDaoImpl();
        ObraDaoImpl obraDao = new ObraDaoImpl();
        VehiculoDaoImpl vehiculoDao = new VehiculoDaoImpl();

        listObra = obraDao.getObrasActivas();
        listDetalle = detalleDao.getDetalleNoAlquilados();
        listDetalleEntrada = detalleDao.getDetalleAlquilados();
        listVehiculo = vehiculoDao.findAll();

    }

    public void registrar() {

        EquipoDaoImpl equipodao = new EquipoDaoImpl();
        selectEquipo = detalleEqui.getEquipo();
        if (selectEquipo.getCantidadStock() == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay equipos para alquilar", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

            selectEquipo.setCantidadStock(selectEquipo.getCantidadStock() - 1);
            selectEquipo.setCantidad(selectEquipo.getCantidad() + 1);

            equipodao.update(selectEquipo);
            
            kardexdao=new KardexDaoImpl();
            
            
            nuevo.setEquipo(selectEquipo);
            nuevo.setObra(selectObra);
            nuevo.setVehiculo(selectVehiculo);
            nuevo.setTipoMov('S');

            nuevo.setFecha(new Date());
            nuevo.setHora(new Date());
          
            kardexdao.create(nuevo);
            nuevo = new Kardex();
            init();

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salida creada", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void actualizarEntradas() {
        
    }
    
    /**
     * Permite actualizar un cliente
     */
    public void actualizar() {
//        ClienteDaoImpl clientedao = new ClienteDaoImpl();
//        clientedao.update(cliente);
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Cliente actualizado");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * actualiza un cliente
     *
     * @param cliente Objeto Cliente.
     *
     */
    public void actualizarTable(Cliente cliente) {
//        ClienteDaoImpl clientedao = new ClienteDaoImpl();
//        clientedao.update(cliente);
    }

    /**
     * Elimina un cliente.
     *
     * @param c Objeto Cliente.
     *
     */
    public void eliminar(Cliente c) {
//        ClienteDaoImpl clientedao = new ClienteDaoImpl();
//        clientedao.delete(c);
//        init();
//        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente eliminado", "");
//        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    /**
     * Evento para editar cliente en la fila de una tabla.
     *
     * @param event Evento.
     *
     */
    public void onRowEdit(RowEditEvent event) {
//        cliente = ((Cliente) event.getObject());
//        FacesMessage msg = new FacesMessage("Cliente Actualizado", ((Cliente) event.getObject()).getNombre() + " " + ((Cliente) event.getObject()).getApellidos());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        actualizarTable(cliente);
//        cliente = new Cliente();
    }

    /**
     * Evento para cancelar la edicion de un cliente en una fila de una tabla.
     *
     * @param event Evento.
     *
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado", "");
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

    public List<DetalleEquipo> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(List<DetalleEquipo> listDetalle) {
        this.listDetalle = listDetalle;
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

    public List<DetalleEquipo> getListDetalleEntrada() {
        return listDetalleEntrada;
    }

    public void setListDetalleEntrada(List<DetalleEquipo> listDetalleEntrada) {
        this.listDetalleEntrada = listDetalleEntrada;
    }

}
