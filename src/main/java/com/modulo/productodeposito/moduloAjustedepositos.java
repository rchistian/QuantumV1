package com.modulo.productodeposito;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilString;
import com.modulo.producto.tblProducto;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class moduloAjustedepositos extends ByosVerticalLayout  implements Serializable{
	public Window subwindow;
    public VerticalLayout layout = new VerticalLayout();
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    private VerticalLayout vlayout01 = new VerticalLayout();
    public ByosBoton btoAceptar = new ByosBoton( hlayout01, 1,"Aceptar","Aceptar");
    public ByosBoton btoCancelar = new ByosBoton(hlayout01, 5,"Cancelar","Cancelar");
    public moduloDepositoDetalle ModuloDepositoAjuste;
    
	public moduloAjustedepositos(final tblProducto TblProducto){
       ModuloDepositoAjuste = new moduloDepositoDetalle(TblProducto, utilString.TIPO_AJUSTE);
       layout.setMargin(true);
       layout.setSpacing(true);
       subwindow = new Window("Ajuste Por Deposito");
       subwindow.setWidth("670px");
       //subwindow.setHeight("250px");
       subwindow.setModal(true);       
       subwindow.setContent(layout);
       
       vlayout01.setMargin(false);
       vlayout01.setSpacing(false);
       vlayout01.setImmediate(true);
       vlayout01.addComponent(ModuloDepositoAjuste);
       
       hlayout01.setMargin(false);
       hlayout01.setSpacing(false);
       hlayout01.setImmediate(true);
       hlayout01.addComponent(btoAceptar);
       hlayout01.addComponent(btoCancelar);
       
       hlayout01.setComponentAlignment(btoAceptar,Alignment.MIDDLE_CENTER);
       hlayout01.setComponentAlignment(btoCancelar, Alignment.MIDDLE_CENTER);  
       
       layout.addComponent(vlayout01);
       layout.addComponent(hlayout01);
       layout.setComponentAlignment(hlayout01, Alignment.MIDDLE_CENTER);  
       
	}
	
    public void closeWindows(){
        subwindow.close();
    }
     
    public void openWindows(){
       if(subwindow.getParent() != null) {
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
       }else {
    	   UI.getCurrent().addWindow(subwindow);   
          
       }        
    }

}
