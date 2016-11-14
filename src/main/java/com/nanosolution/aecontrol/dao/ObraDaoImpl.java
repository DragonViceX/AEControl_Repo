/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nanosolution.aecontrol.dao;


import com.nanosolution.aecontrol.model.Obra;
import com.nanosolution.aecontrol.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;


/**
 *
 * @author Jessica
 */
public class ObraDaoImpl extends GenericDaoImpl<Obra,Integer> implements ObraDao {
    
     public Obra getObraId(int id){
       List <Obra> list;
       Obra p;
       
        try{
            session= HibernateUtil.getSession();
            tx=session.beginTransaction();
            list=(session.createQuery("from Obra where id_obra="+id+"")).list();
            tx.commit();
        }catch(HibernateException e){
            
            tx.rollback();
            throw e;
        } 
        p=list.get(0);        
        return p;
    }
    
    
   
    
   
    
}
