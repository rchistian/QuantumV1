package com.modulo.producto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosForm;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class moduloEstadisticaProducto extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;

    private tblProducto TblProducto;
    public tblEstadisticaProducto TblEstadiscticaProducto;
    public Label[] LblEstadisticas = new Label[7];

    public moduloEstadisticaProducto(final tblProducto TblProducto){
    	this.TblProducto = TblProducto;
    	this.setMargin(false);
    	this.setSpacing(false);
    	this.setImmediate(true);
    	this.setStyleName("backColorBlack");
    	
    	TblEstadiscticaProducto = new tblEstadisticaProducto(TblProducto); 
        inicializar();
    }
    
    public void inicializar(){
    	for(int f=0;f<LblEstadisticas.length;f++){
    		LblEstadisticas[f] = new Label();
    		LblEstadisticas[f].setWidth("630px");
    	    addComponent(LblEstadisticas[f]);

    	}
    	Actualizar(TblProducto);
    }
    
    public void Actualizar(tblProducto TblProducto){
    	this.TblProducto = TblProducto;
    	TblEstadiscticaProducto.setEstadiscticaProducto(TblProducto);
    	LblEstadisticas[0].setCaption("Codigo: " + TblEstadiscticaProducto.getCodigoproducto());
    	LblEstadisticas[1].setCaption("Descripcion: " + TblEstadiscticaProducto.getDescripcion());
    	LblEstadisticas[2].setCaption("Cantidad Comprada: " + ByosConversores.BigDecimalToString(TblEstadiscticaProducto.getCantidadcomprada(),3));
    	LblEstadisticas[3].setCaption("Cantidad Vendida: " + ByosConversores.BigDecimalToString(TblEstadiscticaProducto.getCantidadvendida(),3));
    	LblEstadisticas[4].setCaption("Ultima Compra: " + TblEstadiscticaProducto.getUltimacompra());
    	LblEstadisticas[5].setCaption("Ultima Venta: " + TblEstadiscticaProducto.getUltimaventa());
    	LblEstadisticas[6].setCaption("Ultimo Cambio de Precio: " + TblEstadiscticaProducto.getUltimocambioprecio());
    }

}
