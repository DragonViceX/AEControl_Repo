/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nanosolution.aecontrol.dao;


import com.nanosolution.aecontrol.model.Cliente;
import com.nanosolution.aecontrol.util.HibernateUtil;
import java.util.List;

import org.hibernate.HibernateException;


/**
 *
 * @author Jessica
 */
public class ClienteDaoImpl extends GenericDaoImpl<Cliente,Integer> implements ClienteDao {
    
     public Cliente getClienteId(int id){
       
        List <Cliente> list;
        Cliente c;
       
        try{
            session= HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=(session.createQuery("from Cliente where id_cliente="+id+"")).list();
            tx.commit();
        }catch(HibernateException e){
            
            tx.rollback();
            throw e;
        }       
        c=list.get(0);        
        return c;
    }
    
   
    
}
