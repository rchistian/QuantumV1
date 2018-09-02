package com.modulo.componentes;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.io.Serializable;

/**
 *
 * @author Chistian
 */
public class moduloLeerCodigo extends VerticalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public Window subwindow;
    public ByosCampo Codigos = new ByosCampo("TextField");
    
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    private VerticalLayout layout = new VerticalLayout();
    public ByosBoton btoAceptar = new ByosBoton( hlayout01, 1,"Aceptar","Aceptar");
    public ByosBoton btoCancelar = new ByosBoton(hlayout01, 5,"Cancelar","Cancelar");
    
    public moduloLeerCodigo(){
    	Codigos.lblNombreCampo.setVisible(false);
        hlayout01.addComponent(btoAceptar);
        hlayout01.setComponentAlignment(btoAceptar,Alignment.BOTTOM_CENTER);
        hlayout01.addComponent(btoCancelar);
        hlayout01.setComponentAlignment(btoCancelar, Alignment.BOTTOM_CENTER);        
        subwindow = new Window("Codigo");
        subwindow.setWidth("20em");
        subwindow.setModal(true);
        subwindow.setContent(layout);    
        layout.setMargin(true);
        layout.setSpacing(true);  
        layout.addComponent(Codigos);
        layout.setComponentAlignment(Codigos, Alignment.TOP_CENTER);
        layout.addComponent(hlayout01);
        layout.setComponentAlignment(hlayout01, Alignment.BOTTOM_CENTER);        
        Codigos.txtTexto.focus();
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
