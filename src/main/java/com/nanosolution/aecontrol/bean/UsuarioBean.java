/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.bean;

import com.nanosolution.aecontrol.dao.UsuarioDaoImpl;
import com.nanosolution.aecontrol.model.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Diego Luis Hernandez
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    private Usuario usuario;
    private List<Usuario> LUsuario;
    private List<Usuario> filteredUsuarios;

    public UsuarioBean() {
        usuario = new Usuario();
    }

    @PostConstruct
    public void init() {
        UsuarioDaoImpl Usuariodao = new UsuarioDaoImpl();
        LUsuario = Usuariodao.findAll();

    }

    public List<Usuario> getFilteredUsuarios() {
        return filteredUsuarios;
    }

    public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
        this.filteredUsuarios = filteredUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLUsuario() {
        return LUsuario;
    }

    public void setLUsuario(List<Usuario> LUsuario) {
        this.LUsuario = LUsuario;
    }

    /**
     * Permite registrar un Usuario en la base de datos
     */
    public void registrar() {
        UsuarioDaoImpl Usuariodao = new UsuarioDaoImpl();
        Usuariodao.create(usuario);
        usuario = new Usuario();
        init();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Permite actualizar un Usuario
     */
    public void actualizar() {
        UsuarioDaoImpl Usuariodao = new UsuarioDaoImpl();
        Usuariodao.update(usuario);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Usuario actualizado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * actualiza un Usuario
     *
     * @param Usuario Objeto Usuario.
     *
     */
    public void actualizarTable(Usuario usuario) {
        UsuarioDaoImpl Usuariodao = new UsuarioDaoImpl();
        Usuariodao.update(usuario);
    }

    /**
     * Elimina un Usuario.
     *
     * @param c Objeto Usuario.
     *
     */
    public void eliminar(Usuario c) {
        UsuarioDaoImpl Usuariodao = new UsuarioDaoImpl();
        Usuariodao.delete(c);
        init();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    /**
     * Evento para editar Usuario en la fila de una tabla.
     *
     * @param event Evento.
     *
     */
    public void onRowEdit(RowEditEvent event) {
        usuario = ((Usuario) event.getObject());
        FacesMessage msg = new FacesMessage("Usuario Actualizado", ((Usuario) event.getObject()).getNombre() + " " + ((Usuario) event.getObject()).getApellidos());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        actualizarTable(usuario);
        usuario = new Usuario();
    }

    /**
     * Evento para cancelar la edicion de un Usuario en una fila de una tabla.
     *
     * @param event Evento.
     *
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
