/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.dao;

import com.nanosolution.aecontrol.model.DetalleEquipo;
import com.nanosolution.aecontrol.model.Kardex;
import com.nanosolution.aecontrol.util.HibernateUtil;
import java.util.List;

import org.hibernate.HibernateException;

/**
 *
 * @author Diego Luis Hernandez
 */
public class DetalleEquipoDaoImpl extends GenericDaoImpl<DetalleEquipo, Integer> implements DetalleEquipoDao {

    public List<DetalleEquipo> getDetalleNoAlquilados() {

        List<DetalleEquipo> list;
        DetalleEquipo c;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = (session.createQuery("from DetalleEquipo d where d.estadoAlquiler='D'").list());
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return list;
    }

    public List<DetalleEquipo> getDetalleAlquilados() {

        List<DetalleEquipo> list;
        DetalleEquipo c;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = (session.createQuery("from DetalleEquipo d where d.estadoAlquiler='A'").list());
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        
        return list;
    }

}
