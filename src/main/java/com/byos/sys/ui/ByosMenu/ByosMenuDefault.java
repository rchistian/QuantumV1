/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byos.sys.ui.ByosMenu;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.io.Serializable;
import com.byos.sys.imagenes.*;

/**
 *
 * @author Chistian
 */
public class ByosMenuDefault extends HorizontalLayout implements Serializable{
    public Button btoNuevo  = new Button();
    public Button btoBuscar = new Button();
    public Button btoEliminar = new Button();
    public Button btoGuardar = new Button();
    public Button btoAnterior = new Button();
    public Button btoProximo = new Button();
    public HorizontalLayout MenuPrinsipal = new HorizontalLayout();
    public HorizontalLayout MenuAdicional = new HorizontalLayout();

    
    /*public Button btoNuevo  = new Button("Nuevo");
    public Button btoBuscar = new Button("Buscar");
    public Button btoEliminar = new Button("Eliminar");
    public Button btoGuardar = new Button("Guardar");
    public Button btoAnterior = new Button("Anterior");
    public Button btoProximo = new Button("Proximo");*/

   
    public ByosMenuDefault(){
        //setStyleName(com.vaadin.ui.themes.Reindeer.LAYOUT_BLUE);
        //setWidth("100%");
        //setHeight("8em");
        setMargin(false);
        setSpacing(true);
        setStyleName(com.vaadin.ui.themes.Reindeer.BUTTON_SMALL);
        //setImmediate(true);
        
        //PanelPrinsipal.setImmediate(true);
        //PanelAdicional.setImmediate(true);
        
        

        MenuPrinsipal.setSpacing(true);
        MenuPrinsipal.setMargin(false);
        MenuPrinsipal.setStyleName(com.vaadin.ui.themes.Reindeer.LAYOUT_BLUE);
        MenuPrinsipal.setWidth("100%");
        MenuPrinsipal.setHeight("100%");
        

        MenuAdicional.setMargin(false);
        MenuAdicional.setSpacing(true);
        MenuAdicional.setVisible(false);     
        MenuAdicional.setStyleName(com.vaadin.ui.themes.Reindeer.LAYOUT_BLUE);
        MenuAdicional.setWidth("100%");
        MenuAdicional.setHeight("100%");

        btoNuevo.setStyleName(com.vaadin.ui.themes.Reindeer.BUTTON_SMALL);
        btoNuevo.setIcon(ByosImagenes.icon[1]);
        MenuPrinsipal.addComponent(btoNuevo);
        MenuPrinsipal.setComponentAlignment(btoNuevo, Alignment.TOP_CENTER);
 
       
        btoBuscar.setStyleName(com.vaadin.ui.themes.Reindeer.BUTTON_SMALL);
        btoBuscar.setIcon(ByosImagenes.icon[2]);
        MenuPrinsipal.addComponent(btoBuscar);
        MenuPrinsipal.setComponentAlignment(btoBuscar, Alignment.TOP_CENTER);        

        btoGuardar.setStyleName(com.vaadin.ui.themes.Reindeer.BUTTON_SMALL);
        btoGuardar.setIcon(ByosImagenes.icon[4]);
        
        MenuPrinsipal.addComponent(btoGuardar);
        MenuPrinsipal.setComponentAlignment(btoGuardar, Alignment.TOP_CENTER);
        
        btoEliminar.setStyleName(com.vaadin.ui.themes.Reindeer.BUTTON_SMALL);
        btoEliminar.setIcon(ByosImagenes.icon[3]);
        MenuPrinsipal.addComponent(btoEliminar);
        MenuPrinsipal.setComponentAlignment(btoEliminar, Alignment.TOP_CENTER);
       
        btoAnterior.setStyleName(com.vaadin.ui.themes.Reindeer.BUTTON_SMALL);
        btoAnterior.setIcon(ByosImagenes.icon[5]);
        MenuPrinsipal.addComponent(btoAnterior);
        MenuPrinsipal.setComponentAlignment(btoAnterior, Alignment.TOP_CENTER);
        
        btoProximo.setStyleName(com.vaadin.ui.themes.Reindeer.BUTTON_SMALL);
        btoProximo.setIcon(ByosImagenes.icon[6]);
        MenuPrinsipal.addComponent(btoProximo);
        MenuPrinsipal.setComponentAlignment(btoProximo, Alignment.TOP_CENTER); 
        

        addComponent(MenuPrinsipal);
        addComponent(MenuAdicional);
    }
}
