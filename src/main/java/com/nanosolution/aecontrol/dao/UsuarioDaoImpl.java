/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nanosolution.aecontrol.dao;


import com.nanosolution.aecontrol.model.Cliente;
import com.nanosolution.aecontrol.model.Usuario;
import com.nanosolution.aecontrol.util.HibernateUtil;
import java.util.List;

import org.hibernate.HibernateException;


/**
 *
 * @author Diego Luis Hernandez Angulo
 */
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario,Integer> implements UsuarioDao {
    
     public Cliente getClienteId(int id){
       
        List <Cliente> list;
        Cliente c;
       
        try{
            session= HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=(session.createQuery("from usuario where id_usuario="+id+"")).list();
            tx.commit();
        }catch(HibernateException e){
            
            tx.rollback();
            throw e;
        }       
        c=list.get(0);        
        return c;
    }
    
   
    
}
