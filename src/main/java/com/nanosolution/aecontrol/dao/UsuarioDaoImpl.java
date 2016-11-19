
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nanosolution.aecontrol.dao;



import com.nanosolution.aecontrol.model.Usuario;
import com.nanosolution.aecontrol.util.HibernateUtil;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;


/**
 *
 * @author Jessica
 */

//@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario,Integer> implements UsuarioDao {
  //  @Autowired
	private SessionFactory sessionFactory;
    
     @Override
     public Usuario getUsuarioId(int id){
       
        List <Usuario> list;
        Usuario c;
       
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
     
     
    @SuppressWarnings("unchecked")
     public Usuario getUsuIdentificacion(String id){
       
        List <Usuario> list;
        Usuario c;
       
        try{
        
            list=( sessionFactory.getCurrentSession().createQuery("from usuario where identificacion="+id+"")).list();
            
        }catch(HibernateException e){
            
             throw e;
        }       
        if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
    }
     
     
     
    
    
   
    
}
