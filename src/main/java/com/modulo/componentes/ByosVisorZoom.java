package com.modulo.componentes;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ByosVisorZoom extends VerticalLayout {
	public Window subwindow;
	private String DirFile;
	private String DirUrl;
	private String Registro;
	private ByosVisorImegenes ByosVisorImegenes01;
	

	public ByosVisorZoom() {
        subwindow = new Window("Zoom");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setClosable(true);
        subwindow.setModal(true);        		
	}
	
	public void ActivarZoom(String DirUrl, String DirFile, String Titulo, String Registro){
		this.DirFile=DirFile;
		this.DirUrl=DirUrl;
		this.Registro=Registro;

		
        ByosVisorImegenes01 = new ByosVisorImegenes(this.DirUrl, this.DirFile, "", this.Registro, false, "650px");
               
        addComponent(ByosVisorImegenes01); 
        
        subwindow.setContent(this);
        ByosBoton BtoCerrar = new ByosBoton(this, 110,"Cerrar","Cerrar");
        BtoCerrar.setWidth("100%");
        BtoCerrar.setHeight("45px");
        BtoCerrar.setStyleName("botonobalado");
        BtoCerrar.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
               try {
            	   closeWindows();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            }
         });
        
        openWindows();
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
