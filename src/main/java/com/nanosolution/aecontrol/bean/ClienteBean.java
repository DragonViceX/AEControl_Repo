/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.bean;



import com.nanosolution.aecontrol.dao.ClienteDaoImpl;
import com.nanosolution.aecontrol.model.Cliente;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;


import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Jessica
 */
@ManagedBean
@RequestScoped
public class ClienteBean {

    private Cliente cliente;
    private List<Cliente> LCliente;
    private List<Cliente> filteredClientes;

    public ClienteBean() {
        cliente = new Cliente();
    }

    @PostConstruct
    public void init() {
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        LCliente = clientedao.findAll();

    }

    public List<Cliente> getFilteredClientes() {
        return filteredClientes;
    }

    public void setFilteredClientes(List<Cliente> filteredClientes) {
        this.filteredClientes = filteredClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getLCliente() {
        return LCliente;
    }

    public void setLCliente(List<Cliente> LCliente) {
        this.LCliente = LCliente;
    }

    /**
     * Permite registrar un cliente en la base de datos
     */
    public void registrar() {
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        clientedao.create(cliente);
        cliente = new Cliente();
        init();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente creado", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Permite actualizar un cliente
     */
    public void actualizar() {
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        clientedao.update(cliente);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Cliente actualizado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * actualiza un cliente
     *
     * @param cliente Objeto Cliente.
     *
     */
    public void actualizarTable(Cliente cliente) {
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        clientedao.update(cliente);
    }

    /**
     * Elimina un cliente.
     *
     * @param c Objeto Cliente.
     *
     */
    public void eliminar(Cliente c) {
        ClienteDaoImpl clientedao = new ClienteDaoImpl();        
        clientedao.delete(c);
        init();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente eliminado","");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }

    /**
     * Evento para editar cliente en la fila de una tabla.
     *
     * @param event Evento.
     *
     */
    public void onRowEdit(RowEditEvent event) {
        cliente = ((Cliente) event.getObject());
        FacesMessage msg = new FacesMessage("Cliente Actualizado", ((Cliente) event.getObject()).getNombre() + " " + ((Cliente) event.getObject()).getApellidos());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        actualizarTable(cliente);
        cliente = new Cliente();
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

}
