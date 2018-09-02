package com.modulo.componentes;

import java.io.Serializable;

import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ByosTitulo extends HorizontalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    Label Titulo = new Label();
    Button Imagen = new Button();
    
    public ByosTitulo(){
    	
    }
    
    public ByosTitulo(String Titulo, String Tamanio){
    	initTitulo(Titulo, Tamanio);
    }

    public void initTitulo(String Titulo, String Tamanio){
    	this.setMargin(true);
    	this.setWidth("100%");
    	this.setHeight("10%");
    	MarginInfo Margenes = new MarginInfo(false,false,false,true);
    	setMargin(Margenes);
    	//setStyleName("v-horizontallayout-Titulo");
    	Imagen.setIcon(ByosImagenes.icon[24]);
    	Imagen.setStyleName("link");
    	Imagen.setWidth("10%");
    	this.Titulo.setValue(Titulo);
    	this.Titulo.setWidth("90%");
    	addComponent(this.Titulo);
    	this.setComponentAlignment(this.Titulo, Alignment.MIDDLE_LEFT);
    	addComponent(Imagen);
    	this.setComponentAlignment(this.Imagen, Alignment.MIDDLE_RIGHT);
    }
    
    public void setTitulo(String Titulo, String Tamanio){
    	initTitulo(Titulo, "100%");
    	this.Titulo.setValue(Titulo);	
    }    

}
