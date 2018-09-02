package com.modulo.reserva;

import java.io.Serializable;

import com.modulo.huesped.tblHuespedes;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.modulo.componentes.utilDate;

public class moduloReserva01 extends VerticalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public Window subwindow;
    
    public moduloReserva01(String NumeroDocuemnto, String EstiloCSS, String Titulo) {
    	tblHuespedes Huesped = new tblHuespedes();
    	tblEstadisticasHuesped  Estadisticas = new tblEstadisticasHuesped();
    	
    	subwindow = new Window(Titulo);
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        subwindow.setStyleName(EstiloCSS + "WindowDetalle");
                
        
        
        setStyleName(EstiloCSS + "LayoutWindowDetalle");      
        setWidth("100%");
    	setHeight("100%");
    	
    	try {
    		Huesped.buscarCodigo(NumeroDocuemnto);
    		Estadisticas.setDataHuesped(Huesped);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(EstiloCSS.equals("GV")) {
    	   moduloGolpeVista Modulo = new moduloGolpeVista(NumeroDocuemnto, this, EstiloCSS);
           addComponent(Modulo);
           setComponentAlignment(Modulo, Alignment.TOP_CENTER);
    	}
    	if(EstiloCSS.equals("RS")) {
     	   moduloReserva Modulo = new moduloReserva(NumeroDocuemnto, this, EstiloCSS);
           addComponent(Modulo);
           setComponentAlignment(Modulo, Alignment.TOP_CENTER);
     	}
    	if(EstiloCSS.equals("CF")) {
      	   moduloConfirmacion Modulo = new moduloConfirmacion(NumeroDocuemnto, this, EstiloCSS);
           addComponent(Modulo);
           setComponentAlignment(Modulo, Alignment.TOP_CENTER);
      	}  
    	
    	if(EstiloCSS.equals("LLG")) {
       	   moduloLlegada Modulo = new moduloLlegada(NumeroDocuemnto, this, EstiloCSS);
           addComponent(Modulo);
           setComponentAlignment(Modulo, Alignment.TOP_CENTER);
       	}  
        
    	closeWindows();
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

