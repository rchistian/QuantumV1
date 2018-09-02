package com.byos.sys.ui.ByosAccordion;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ByosAccordion extends Accordion {

	public ByosAccordion(){
		super();
		initComponentsPrimero();
		initComponentsSegundo();
		this.setSizeFull();
	}
	
	//Esta funcion se ejecuta al final solo para agregar el modulo de Usuarios
	public void initComponentsSegundo(){
		//ByosAccordionLabel labelUsuarios = new ByosAccordionLabel("Usuarios","Usuarios");
		//ByosAccordionLabel labelPerfil = new ByosAccordionLabel("Usuarios","Perfil de Usuarios");
		//ByosAccordionLabel labelAutorizaciones = new ByosAccordionLabel("Usuarios","Autorizaciones");
		
		Button labelUsuarios = new Button("Usuarios");
		Button labelPerfil = new Button("Perfil");
		Button labelAutorizaciones = new Button("Autorizaciones");
		
		labelUsuarios.setSizeFull();
		labelPerfil.setSizeFull();
		labelAutorizaciones.setSizeFull();
		
		VerticalLayout ver = new VerticalLayout();
		ver.setSizeFull();
		ver.addComponent(labelUsuarios);
		ver.addComponent(labelPerfil);
		ver.addComponent(labelAutorizaciones);
		
		agregarItem(ver,"Usuarios");
	}
	
	public void agregarItem(VerticalLayout modulo,String nombre){
		modulo.addLayoutClickListener(new LayoutClickListener(){

			@Override
			public void layoutClick(LayoutClickEvent event) {
				// TODO Auto-generated method stub
				
				System.out.println(event.getChildComponent().toString());
				//ByosAccordionLabel B = (ByosAccordionLabel)event.getChildComponent();
				//System.out.println(B.toString());
			}});
		
	this.addTab(modulo,nombre);
	}
	
	//Sobreescribir esta funcion en Programas
	public void initComponentsPrimero(){
	
		
	}
	
	
	
}
