package com.modulo.inicio;

import java.io.Serializable;

import com.byos.sys.ui.ByosMenu.ByosMenuInicio;
import com.modulo.componentes.ByosImagenes;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class moduloEncendido extends VerticalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    
    public Window subwindow;
    private Embedded ImagenFondo = new Embedded();
    
    @SuppressWarnings("deprecation")
	public moduloEncendido() {
    	
        setSizeFull();
    	subwindow = new Window("Inicio Sistema");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        
        subwindow.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
            	ImagenFondo_Click();
            }
        });
        ImagenFondo = new Embedded(null, ByosImagenes.icon[112]);	     
        ImagenFondo.setSizeFull();

        
        addComponent(ImagenFondo);       
    }
    
    private void ImagenFondo_Click(){
    	if(!moduloMenuSistemas.EstadoForm) {
    		closeWindows();
    	   new moduloMenuSistemas().openWindows();
    	}
    }

    public static boolean EstadoForm = false;
    public void openWindows(){
        if(subwindow.getParent() != null) {
           Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
        }else {
           //EstadoForm = true;
     	   UI.getCurrent().addWindow(subwindow);                  
        }        
    }
 
    public void closeWindows(){
    	EstadoForm = false;
        subwindow.close();
    }
}
