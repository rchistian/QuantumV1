package com.byos.sys.ui.ByosDatagrid;

import java.io.Serializable;


import com.byos.sys.filters.LikeFilter;
import com.modulo.usuarios.tblUsuarios;
import com.vaadin.data.Property;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;



public class ByosBusqueda  extends VerticalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public Window subwindow = new Window();
    public TextField Criterio01 = new TextField();
    public ByosDatagridProtoDos Grid = new ByosDatagridProtoDos();

	ByosColumna[] ByosColumnas = new ByosColumna[2];

    public ByosBusqueda() {
       subwindow.setWidth("800px");
       subwindow.setHeight("640px");
       
       subwindow.setModal(true);       
       subwindow.setContent(this);

       
       //setWidth("800px");
       //setWidth("640px");
    	
	   ByosColumnas[0] = new ByosColumna("login", String.class, "Login", "", new LikeFilter("login",""));
	   ByosColumnas[1] = new ByosColumna("nombres", String.class, "Nombres", "", new LikeFilter("nombres",""));
	   //ByosColumnas[2] = new ByosColumna("apellidos", String.class, "Apellidos", "",  new LikeFilter("apellidos",""));
       Filtrado("","");
       Criterio01.addValueChangeListener(new Property.ValueChangeListener() {
           @Override
           public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
        	   Filtrado(Criterio01.getValue(), "");
           }
       });
       Grid.setWidth("800px");
       Grid.setWidth("500px");
       
   	   try {
		   Grid.initDatagridByos(new tblUsuarios().BuscarArray("",""), new tblUsuarios(), ByosColumnas, false);		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
       
       addComponent(Criterio01);
       addComponent(Grid);
       
       openWindows();
       
    }
    
    public void Filtrado(String Texto, String Numero) {
    	try {
   		    Grid.refrescar(new tblUsuarios().BuscarArray(Texto,Numero));
   		    Grid.refreshRowCache();
   		   
   		    
 	   } catch (Exception e) {
 		   e.printStackTrace();
 	   }
    }
    
    public void closeWindows(){
        subwindow.close();
    }
     
    public void openWindows(){
       if(subwindow.getParent() != null) {
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
       }else {
    	   UI.getCurrent().getCurrent().addWindow(subwindow);                  
       }        
     }
}
