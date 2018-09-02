package com.modulo.componentes;


import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ByosVerticalLayout extends VerticalLayout {
	
	public HorizontalLayout Cabezera = new HorizontalLayout();
	public Panel panelContenido = new Panel();
	public VerticalLayout ContenidoMain = new VerticalLayout();
	public VerticalLayout Contenido = new VerticalLayout();
	public VerticalLayout Main = new VerticalLayout();
	public Label Titulo01 = new Label();
	Button Imagen = new Button();
	
	public ByosVerticalLayout(){
	   InitLayout();	
	}
	
	public ByosVerticalLayout(String Titulo, String Ancho){
	   InitLayout();
   	   setByosLayout(Titulo, Ancho);
	}
	
	public void InitLayout(){
	   Titulo01.setSizeUndefined();
		   
	   //Cabezera.setStyleName("v-horizontallayout-Titulo");
	   Cabezera.setSizeUndefined();
	   Cabezera.setWidth("90%");
	   Cabezera.addComponent(Titulo01);
	   //Cabezera.addComponent(Imagen);
	   Cabezera.setHeight("40px");
	   Cabezera.setComponentAlignment(Titulo01, Alignment.MIDDLE_LEFT);
	   //Cabezera.setComponentAlignment(Imagen, Alignment.MIDDLE_RIGHT);
	   MarginInfo Margenes = new MarginInfo(false,false,false,true);
	   Cabezera.setMargin(Margenes);
	   	   
	   Imagen.setIcon(ByosImagenes.icon[24]);
	   Imagen.setStyleName("link");
	   Imagen.setWidth("10%");
	   
	   panelContenido.setContent(Contenido);
	   ContenidoMain.setStyleName("v-verticallayout-panel");
	   Contenido.setStyleName("v-verticallayout-contenido");
	   ContenidoMain.addComponent(Cabezera);
	   ContenidoMain.addComponent(panelContenido);
	   Main.addComponent(ContenidoMain); 
	   addComponent(Main);		
	}
	
	public void setByosLayout(String Titulo, String Ancho){
		Ancho="100%";
		Cabezera.setWidth(Ancho);
		Titulo01.setValue(Titulo);
		if(Ancho!=null && !Ancho.equals("")){
	      Titulo01.setWidth(Ancho);
	      Cabezera.setWidth(Ancho);
       }
	}
	
}
