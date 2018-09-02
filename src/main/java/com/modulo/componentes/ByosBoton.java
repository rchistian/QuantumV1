/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modulo.componentes;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;


/**
 *
 * @author Chistian
 */
public class ByosBoton extends Button  implements Serializable,PropertyChangeListener{
    
	
	public ByosBoton(){
		super();
	}
    
    public ByosBoton(Integer Icono, String Descripcion, String Caption){
        super();
    	setBoton(Icono, Descripcion, Caption); 
    }
    
   /* public ByosBoton(HorizontalLayout Layout, int Icono, String nombre, String posicion){
    	setDisableOnClick(true);
        setIcon(ByosImagenes.icon[Icono]);
        this.setCaption(nombre);
        if (posicion.equals("")){}
        else{this.addStyleName(posicion);}
        Layout.addComponent(this);
    }*/
    
    
    public ByosBoton(VerticalLayout Layout, Integer Icono, String Descripcion,String Caption){
        super();
    	setBoton(Icono, Descripcion, Caption);
        Layout.addComponent(this); 
    }
    
    public ByosBoton(HorizontalLayout Layout, Integer Icono, String Descripcion, String Caption){
        super();
    	setBoton(Icono, Descripcion, Caption);
        Layout.addComponent(this); 
        
    }
    
    public void setBoton(Integer Icono, String Descripcion, String Caption){
        //setDisableOnClick(true);
    	this.setStyleName("link");;
    	//setStyleName("ribbon-button");
    	if(Icono != null){
           setIcon(ByosImagenes.icon[Icono]);
    	}
        if(Descripcion != null){
           setDescription(Descripcion);
        }
        if(Caption != null){
           setCaption(Caption);
        }
    }

    public void setAlto(String Alto) {
    	setWidth(Alto);
    }
    
    public void setAncho(String Ancho) {
    	setHeight(Ancho);
    }
    
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getPropertyName().equals("procesoTerminado")){
			this.setEnabled(true);
		}
	}
}
