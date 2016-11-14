/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.bean;


import com.nanosolution.aecontrol.dao.ClienteDaoImpl;
import com.nanosolution.aecontrol.dao.ObraDaoImpl;
import com.nanosolution.aecontrol.model.Cliente;
import com.nanosolution.aecontrol.model.Obra;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Jessica
 */
@ManagedBean
@RequestScoped

public class ObraBean {

    private Obra obra;
    private List<Obra> LObra;
    private List<Obra> filteredObras;
    private List<SelectItem> cliente;
    private int id_cliente;
    
    
    

    public ObraBean() {
        obra = new Obra();
        cliente= new ArrayList();
   
    }

    @PostConstruct
    public void init() {
        ObraDaoImpl obradao = new ObraDaoImpl();
        LObra = obradao.findAll();
        cliente= new ArrayList();

        for (Iterator iterador = listaCliente().listIterator(); iterador.hasNext();) {
            Cliente p = (Cliente) iterador.next();
            cliente.add(new SelectItem(p.getIdCliente(), p.getEmpresa()));
        }

    }

    

   

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public List<Obra> getLObra() {
        return LObra;
    }

    public void setLObra(List<Obra> LObra) {
        this.LObra = LObra;
    }

    public List<Obra> getFilteredObras() {
        return filteredObras;
    }

    public void setFilteredObras(List<Obra> filteredObras) {
        this.filteredObras = filteredObras;
    }

      

    /**
     * Registra una obra en la base de datos.
     *
     */
    public void registrar() {
        if (validar(obra.getNombre())) { 
            FacesMessage msg = new FacesMessage("El vehiculo ya existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
        ObraDaoImpl obradao = new ObraDaoImpl();
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        obra.setCliente(clientedao.getClienteId(id_cliente));
        obradao.create(obra);
        obra = new Obra();
        init();
        FacesMessage msg = new FacesMessage("Obra creada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

    /**
     * Actualiza una obra en la base de datos.
     *
     */
    public void actualizar() {
        ObraDaoImpl obradao = new ObraDaoImpl();
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        obra.setCliente(clientedao.getClienteId(id_cliente));
        obradao.update(obra);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Obra actualizada");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    /**
     * Actualiza una obra en la base de datos.
     *
     * @param obra objeto de tipo Obra a actualizar
     */
    public void actualizarTable(Obra obra) {
        ObraDaoImpl obradao = new ObraDaoImpl();
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        obra.setCliente(clientedao.getClienteId(id_cliente));
        obradao.update(obra);
    }

    /**
     * Elimina una obra en la base de datos.
     *
     * @param o de tipo Obra a eliminar
     */
    public void eliminar(Obra o) {
        ObraDaoImpl obradao = new ObraDaoImpl();
        obradao.delete(o);
        init();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Obra eliminada");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowEdit(RowEditEvent event) {
        obra = ((Obra) event.getObject());
        FacesMessage msg = new FacesMessage("Obra Actualizada", ((Obra) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        actualizarTable(obra);
        obra = new Obra();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * devuelve una lista de todos los clientes de la BD.
     *
     * @return List<Cliente> retorna una lista de TipoEquipo.
     */
    public List<Cliente> listaCliente() {
        ClienteDaoImpl clientedao = new ClienteDaoImpl();
        return clientedao.findAll();
    }
    
    /**
     * Retorna el nombre de un cliente
     *
     * @param id identificador del cliente.
     * @return String retorna una cadena con el nombre del cliente
     *
     */
    public String seleccion(int id) {
        String select;
        Cliente obj = new Cliente();
        for (Iterator iterador = listaCliente().listIterator(); iterador.hasNext();) {
            Cliente p = (Cliente) iterador.next();
            if (p.getIdCliente().equals(id)) {
                obj = p;
            }
        }
        select = obj.getEmpresa();
        return select;
    }

    public List<SelectItem> getCliente() {
        return cliente;
    }

    public void setCliente(List<SelectItem> cliente) {
        this.cliente = cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
      /**
     * Valida que no se repita el nombre de una Obra, ya que debe ser unico.
     *
     * @param n String nombre de la obra.
     * @return boolean devuelve una bandera.
     *
     */
    public boolean validar(String n) {
        boolean b = false;
        for (Iterator iterador = LObra.listIterator(); iterador.hasNext();) {
            Obra v = (Obra) iterador.next();
            if (v.getNombre().toLowerCase().equals(n.toLowerCase())) {
                b = true;
            }
        }
        return b;
    }

    
    
    
}




