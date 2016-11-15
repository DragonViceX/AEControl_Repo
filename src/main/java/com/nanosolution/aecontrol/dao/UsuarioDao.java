/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nanosolution.aecontrol.dao;


import com.nanosolution.aecontrol.model.Usuario;



/**
 *
 * @author Jessica
 */
public interface UsuarioDao extends GenericDao <Usuario,Integer>{
    
     
     public Usuario getUsuarioId(int id);
     public Usuario getUsuIdentificacion(String id);
     
}
