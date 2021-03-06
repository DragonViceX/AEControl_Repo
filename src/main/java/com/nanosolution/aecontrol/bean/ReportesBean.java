/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nanosolution.aecontrol.bean;

import com.nanosolution.aecontrol.dao.KardexDaoImpl;
import com.nanosolution.aecontrol.model.Kardex;
import com.nanosolution.aecontrol.model.Obra;
import com.nanosolution.aecontrol.util.GenerarReportes;
import com.nanosolution.aecontrol.util.Parametros;
import com.nanosolution.aecontrol.util.ParametrosWeb;
import com.nanosolution.aecontrol.util.UtilidadesWeb;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import com.nanosolution.aecontrol.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.model.SelectItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Diego Luis Hernandez
 */
@ManagedBean
@RequestScoped
public class ReportesBean {

    private Date fechaDesde;
    private Date fechaHasta;
    private String obra;
    private ArrayList<SelectItem> listObras;

    public ReportesBean() {
        listObras = new ArrayList<SelectItem>();
        KardexDaoImpl obraDao = new KardexDaoImpl();

        obraDao.getKardexObraAlquilados();
        
        for (Obra lista : obraDao.getKardexObraAlquilados()) {
            listObras.add(new SelectItem(lista.getIdObra(), lista.getNombre()));
        }

    }

    @SuppressWarnings("unchecked")
    public void generarReporteEquipoAlquiladoPorObra() throws Exception {

        System.err.println("paso por aca");
        ParametrosWeb param = (ParametrosWeb) UtilidadesWeb.getManagedBean("Parametros");
        @SuppressWarnings("rawtypes")
        HashMap parametros = new HashMap();

        parametros.put("fechaInicio", fechaDesde);
        parametros.put("fechaFin", fechaHasta);
        parametros.put("obra", obra);
        System.out.println("gnerando reportes");
        GenerarReportes.generarReporteEquipoAlquiladoPorObra(FacesContext.getCurrentInstance(), "1", Parametros.FORMATO_PDF,
                parametros, param.getCONTEXTO_APP(), Parametros.RUTA_JASPER, Parametros.RUTA_PDF,
                Parametros.RUTA_IMG);
    }

    @SuppressWarnings("unchecked")
    public void generarReporteEquiposDisponible() throws Exception {
        System.err.println("paso por aca");
        ParametrosWeb param = (ParametrosWeb) UtilidadesWeb.getManagedBean("Parametros");
        @SuppressWarnings("rawtypes")
        HashMap parametros = new HashMap();

        //parametros.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,  session);
        GenerarReportes.generarReporteEquiposDisponible(FacesContext.getCurrentInstance(), "1", Parametros.FORMATO_PDF,
                parametros, param.getCONTEXTO_APP(), Parametros.RUTA_JASPER, Parametros.RUTA_PDF,
                Parametros.RUTA_IMG);

    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public ArrayList<SelectItem> getListObras() {
        return listObras;
    }

    public void setListObras(ArrayList<SelectItem> listObras) {
        this.listObras = listObras;
    }

}
