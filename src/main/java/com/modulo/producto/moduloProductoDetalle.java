package com.modulo.producto;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosVisorImegenes;
import com.modulo.historicoprecios.rptEstadisticaCompraVenta;
import com.modulo.precioproducto.tblPrecioProducto;
import com.modulo.precioproducto.tblTablePrecios;
import com.modulo.productodeposito.moduloDepositoDetalle;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.byos.sys.ui.ByosMenu.ByosMenu;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class moduloProductoDetalle extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    
    private tblProducto TblProducto;
    public moduloEstadisticaProducto ModuloEstadisticaProducto;
    public moduloDepositoDetalle ModuloDepositoDetalle;
    public moduloPrecioDetalle ModuloPrecioDetalle;
    public rptEstadisticaCompraVenta RptEstadisticaCompraVenta;
    public ByosVisorImegenes ByosVisorImegenes01;
    
    public moduloProductoDetalle(final tblProducto TblProducto){ 
    	this.TblProducto = TblProducto;
    	
    	setSpacing(true);
    	setMargin(false);
    	
    	ModuloPrecioDetalle = new moduloPrecioDetalle(TblProducto);
        ModuloEstadisticaProducto = new moduloEstadisticaProducto(TblProducto);
        ModuloDepositoDetalle = new moduloDepositoDetalle(TblProducto,"");
        RptEstadisticaCompraVenta = new rptEstadisticaCompraVenta(TblProducto);
        
        String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblproducto\\" + TblProducto.getId() + "\\";
        String DirUrl="../dbsimagenes/tblproducto/" + TblProducto.getId() + "/";
       
        //VaadinService.getCurrent().getBaseDirectory().getAbsoluteFile();
        //VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        
        ByosVisorImegenes01 = new ByosVisorImegenes(DirUrl, DirFile, "Imagen del Producto", TblProducto.getCodigoproducto());
        addComponent(ByosVisorImegenes01);
    	addComponent(RptEstadisticaCompraVenta);
    	addComponent(ModuloPrecioDetalle);
    	addComponent(ModuloDepositoDetalle);
    	
    			 
        //addComponent(ModuloEstadisticaProducto);
    }
    
    public void setTblProducto(final tblProducto TblProducto){
    	this.TblProducto = TblProducto;

    	ModuloPrecioDetalle.setTblProducto(TblProducto);
    	ModuloDepositoDetalle.setTblProducto(TblProducto);
    	RptEstadisticaCompraVenta.setEstadisticas(TblProducto);
    	String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblproducto\\" + TblProducto.getId() + "\\";
        //String DirFile="\\VAADIN\\themes\\" + Byosquantumv7UI.getCurrent().getTheme() + "\\dbsimagenes\\tblproducto\\" + TblProducto.getId() + "\\";
        //String DirFile="dbsimagenes\\tblproducto\\" + TblProducto.getId() + "\\";
        String DirUrl="../dbsimagenes/tblproducto/" + TblProducto.getId() + "/";
        ByosVisorImegenes01.init(DirUrl, DirFile, TblProducto.getCodigoproducto());
    	ActualizarEstadisticas(TblProducto);
    	
    }
    
    public void ActualizarEstadisticas(tblProducto TblProducto){
    	ModuloEstadisticaProducto.Actualizar(TblProducto);
    }
    
   
   
}
