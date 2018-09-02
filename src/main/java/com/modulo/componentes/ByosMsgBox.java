package com.modulo.componentes;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ByosMsgBox  {

	private static final long serialVersionUID = 1L;
	private Label lblMensaje;
	public ByosBoton btoSi  = new ByosBoton();
	public ByosBoton btoNo = new ByosBoton();
	public String respuesta = "";
	public Window MsgBox;
	
	public ByosMsgBox(String Mensaje, String Titulo){
		
		lblMensaje = new Label(Mensaje);
		
		MsgBox = new Window(Titulo);
		MsgBox.setClosable(false);
		MsgBox.setHeight("10em");
		MsgBox.setWidth("20em");
		MsgBox.setModal(true);
		MsgBox.setResizable(false);
		MsgBox.setVisible(true); 
		
		//System.out.println("Bandera...");
		VerticalLayout formLayout = new VerticalLayout();
		HorizontalLayout hbotones = new HorizontalLayout();
		formLayout.setSpacing(false);
		formLayout.setMargin(false);
		
		btoSi.setBoton(32, "Si","Si");
		btoNo.setBoton(33, "No","No");
		
		formLayout.addComponent(lblMensaje);
		hbotones.addComponent(btoSi);
		hbotones.addComponent(btoNo);
		VerticalLayout v = new VerticalLayout();
		
		v.addComponent(formLayout);
		v.addComponent(hbotones);
		v.setComponentAlignment(hbotones, Alignment.MIDDLE_CENTER);
		MsgBox.setContent(v);
	}
	
    public void closeWindows(){
    	MsgBox.close();
    }
     
    public void openWindows(){
       if(MsgBox.getParent() != null) {
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_ERROR_MESSAGE);
       }else {
           UI.getCurrent().addWindow(MsgBox);   
          
       }        
    }

}
