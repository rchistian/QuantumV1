package com.byos.sys.ui.ByosDialog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.byos.sys.util.utilString;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class ByosDialog extends Window implements PropertyChangeListener {
	
	private Label lblMensaje;
	public Button btoSi;
	public Button btoNo;
	public String respuesta = "";
	public PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public ByosDialog(String mensaje){
		
		lblMensaje = new Label(mensaje);
		btoSi = new Button("Sí",new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	support.firePropertyChange(utilString.CONFIRMACION, false,true);
            	respuesta = "Si";
           }}
		);
		
		btoNo = new Button("No",new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	support.firePropertyChange(utilString.CONFIRMACION, true,false);
            	respuesta = "No";
            }}
		);
		
		setCaption("Confirmación");
		this.setClosable(false);
		this.setHeight(4,Unit.CM);
		this.setWidth(8,Unit.CM);
		this.setModal(true);
		this.setResizable(false);
		//this.setScrollable(false);
		this.setVisible(true);
		VerticalLayout formLayout = new VerticalLayout();
		HorizontalLayout hbotones = new HorizontalLayout();
		formLayout.setSpacing(false);
		formLayout.setMargin(false);
		formLayout.addComponent(lblMensaje);
		hbotones.addComponent(btoSi);
		hbotones.addComponent(btoNo);
		VerticalLayout v = new VerticalLayout();
		v.addComponent(formLayout);
		v.addComponent(hbotones);
		//addComponent(formLayout);
		//addComponent(hbotones);
		this.setContent(v);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener actionListener ) {
		this.support.addPropertyChangeListener( actionListener );
	 }

	 public void removePropertyChangeListener(PropertyChangeListener listener ) {
	    this.support.removePropertyChangeListener( listener );
	 }

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getPropertyName().equals("Invalido")){
			System.out.println("Invalido");
		}
	}

}
