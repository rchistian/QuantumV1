package com.byos.sys.ui.ByosLoginFormInit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.byos.sys.modulo.usuario.tblUsuarios;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class ByosLoginFormInit extends Window implements PropertyChangeListener {
	private TextField loginField;
	private PasswordField passwordField;
	private PasswordField confirmationField;
	private Button submitButton;
	public PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public ByosLoginFormInit(){
		loginField = new TextField("Login");
		loginField.setValue("byos");
		loginField.setEnabled(false);
		confirmationField = new PasswordField("Confirmación");
		passwordField = new PasswordField("Password");
		submitButton = new Button("Submit",new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	            	
            	String s = validacion();
            	if (s.equals("")){
            	support.firePropertyChange("usuarioByos", loginField.getValue() , passwordField.getValue());
            	}
            	else{
            		Notification.show(s);
            	}
            }});
            	
		setCaption("Login");
		this.setClosable(false);
		this.setHeight(7,Unit.CM);
		this.setWidth(6,Unit.CM);
		this.setModal(true);
		this.setResizable(false);
		//this.setScrollable(false);
		VerticalLayout formLayout = new VerticalLayout();
		formLayout.setSpacing(false);
		formLayout.setMargin(false);
		formLayout.addComponent(loginField);
		formLayout.addComponent(passwordField);
		formLayout.addComponent(confirmationField);
		formLayout.addComponent(submitButton);
		this.setContent(formLayout);
		//addComponent(formLayout);
	}
		
		
		public String validacion(){
			String s="";
			boolean seguir = false;
			tblUsuarios tempUser = new tblUsuarios();
			
			//Password y Confirmacion no son iguales
			if (!passwordField.getValue().equals(confirmationField.getValue())){
				s = "Password y Confirmacion no son iguales";	
			}
			
			//
			if (passwordField.getValue().equals(loginField.getValue())){
				s = "Login y Password no pueden ser iguales";	
			}
			
			if (passwordField.getValue().equals("")){
				s = "Ingrese Password";
			}
			
			if (loginField.getValue().equals("")){
				s = "Ingrese Login";
			}
			return s;
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
