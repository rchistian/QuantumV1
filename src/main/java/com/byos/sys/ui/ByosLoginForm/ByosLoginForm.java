package com.byos.sys.ui.ByosLoginForm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.byos.sys.modulo.usuario.tblUsuarios;
import com.byos.sys.util.utilString;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ByosLoginForm extends Window implements PropertyChangeListener{
	
	private TextField loginField;
	private PasswordField passwordField;
	private Button submitButton;
	public PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public ByosLoginForm(){
		loginField = new TextField("Login");
		passwordField = new PasswordField("Password");
		submitButton = new Button("Submit",new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	
            	String s = validacion();
            	
            	if (s.equals("")){
            	support.firePropertyChange(utilString.CREDENCIALES, loginField.getValue(),passwordField.getValue());
            	}
            	else{
            		Notification.show(s);
            	}
            
            }}
		);
		
		
		
		setCaption("Login");
		this.setClosable(false);
		this.setHeight(5,Unit.CM);
		this.setWidth(6,Unit.CM);
		this.setModal(true);
		this.setResizable(false);
		//this.setScrollable(false);
		VerticalLayout formLayout = new VerticalLayout();
		/*formLayout.setSizeUndefined();
		formLayout.setHeight(5,UNITS_CM);
		formLayout.setWidth(8,UNITS_CM);
		*/
		formLayout.setSpacing(false);
		formLayout.setMargin(true);
		formLayout.addComponent(loginField);
		formLayout.addComponent(passwordField);
		formLayout.addComponent(submitButton);
		this.setContent(formLayout);
		//addComponent(formLayout);
	}
	
	
	public String validacion(){
		String s="";
		
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
