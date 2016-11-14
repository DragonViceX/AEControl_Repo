/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.dao;

import com.nanosolution.aecontrol.model.Cliente;
import com.nanosolution.aecontrol.model.Kardex;
import com.nanosolution.aecontrol.model.Obra;
import com.nanosolution.aecontrol.model.Usuario;
import com.nanosolution.aecontrol.util.HibernateUtil;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Diego Luis Hernandez
 */
public class KardexDaoImpl extends GenericDaoImpl<Kardex, Integer> implements KardexDao {

    public Kardex getKardexObraId(int id) {

        List<Kardex> list;
        Kardex c;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = (session.createQuery("from Kardex u where u.obra.idObra= " + id + "").list());
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        c = list.get(0);
        return c;
    }

    public List<Kardex> getKardexObraAlquilados() {

        List<Kardex> list;
        Kardex c;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = (session.createQuery("from Kardex u where u.tipoMov='S'").list());

            System.out.println("-->> " + list.toString());
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }

        return list;
    }

}
