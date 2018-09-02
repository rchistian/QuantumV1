package com.byos.sys.ui.ByosMenu;

import java.io.Serializable;
import com.modulo.componentes.ByosImagenes;
import com.modulo.proveedor.moduloProveedor;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ByosMenuCircularProveedor  extends VerticalLayout implements Serializable{   
    //public HorizontalLayout MenuPrincipal = new HorizontalLayout();
    //public HorizontalLayout MenuAdicional = new HorizontalLayout();
   
    public Panel PanelMenu = new Panel();
    public AbsoluteLayout Layout = new AbsoluteLayout();
	public Button btoOP01 = new Button();
    public Button btoOP02 = new Button();
    public Button btoOP03 = new Button();
    public Button btoOP04 = new Button();
    public Button btoOP05 = new Button();
    public Button btoOP06 = new Button();
    public Button btoOP07 = new Button();
    public Button btoOP08 = new Button();
    public Button btoOP09 = new Button();
    public Button btoOP10 = new Button();
    
    public int TamanoTop  = 0;
    public int TamanoLeft = 0;
    public Label Titulo = new Label();
    public Label TituloFecha = new Label();
    public Label TituloHora = new Label();
    public String EstiloBoton = "botonobalado";
    
    public Window subwindow;
    
    public ByosMenuCircularProveedor(){
    	    	
        subwindow = new Window("Menu Proveedores");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        
        PanelMenu.setWidth("1024px");
        PanelMenu.setHeight("748px");
        PanelMenu.setContent(Layout);
        
        Titulo.setWidth("1024px");
        Titulo.setHeight("60px");
        Titulo.setStyleName("estilotitulo");
        Titulo.setCaption("DATOS DEL PROVEEDOR");
        
        TituloFecha.setWidth("200px");
        TituloFecha.setHeight("60px");
        TituloFecha.setStyleName("estilotitulofechahora");      
        
        TituloHora.setWidth("200px");
        TituloHora.setHeight("60px");
        TituloHora.setStyleName("estilotitulofechahora");        
        
        Layout.setWidth("100%");
        Layout.setHeight("100%");
        
        
        Layout.addComponent(Titulo,      "left: 0px; top: 0px;");
        Layout.addComponent(TituloFecha, "left: 0px; top: 0px;");
        Layout.addComponent(TituloHora,  "left: 824px; top: 0px;");
        
        
        Layout.addComponent(btoOP01, "left: " + (110 + TamanoLeft) + "px; top: " + (310 + TamanoTop) + "px;");
        Layout.addComponent(btoOP02, "left: " + (180 + TamanoLeft) + "px; top: " + (140 + TamanoTop) + "px;");
        
        Layout.addComponent(btoOP03, "left: " + (350 + TamanoLeft) + "px; top: " + (80 + TamanoTop) + "px;");
        Layout.addComponent(btoOP04, "left: " + (530 + TamanoLeft) + "px; top: " + (80 + TamanoTop) + "px;");
        
        Layout.addComponent(btoOP05, "left: " + (700 + TamanoLeft) + "px; top: " + (140 + TamanoTop) + "px;");
        Layout.addComponent(btoOP06, "left: " + (760 + TamanoLeft) + "px; top: " + (310 + TamanoTop) + "px;");
        
        Layout.addComponent(btoOP07, "left: " + (700 + TamanoLeft) + "px; top: " + (480 + TamanoTop) + "px;");
        
        Layout.addComponent(btoOP08, "left: " + (530 + TamanoLeft) + "px; top: " + (540 + TamanoTop) + "px;");
        Layout.addComponent(btoOP09, "left: " + (350 + TamanoLeft) + "px; top: " + (540 + TamanoTop) + "px;");
        
        Layout.addComponent(btoOP10, "left: " + (180 + TamanoLeft) + "px; top: " + (480 + TamanoTop) + "px;");
        
        btoOP01.setWidth("150px");
        btoOP01.setHeight("150px");
        //btoOP01.setCaption("Ficha");
        btoOP01.setStyleName(EstiloBoton);
        btoOP01.setIcon(ByosImagenes.icon[124]);
    	btoOP01.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP01_Click();
            }
        });
    	
        btoOP02.setWidth("150px");
        btoOP02.setHeight("150px");
        //btoOP02.setCaption("Recerba");
        btoOP02.setStyleName(EstiloBoton);
        btoOP02.setIcon(ByosImagenes.icon[78]);
    	btoOP02.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	
            }
        });
        
        btoOP03.setWidth("150px");
        btoOP03.setHeight("150px");
        //btoOP03.setCaption("OP03");
        btoOP03.setStyleName(EstiloBoton);

        btoOP04.setWidth("150px");
        btoOP04.setHeight("150px");
        //btoOP04.setCaption("OP04");
        btoOP04.setStyleName(EstiloBoton);
        
        btoOP05.setWidth("150px");
        btoOP05.setHeight("150px");
        //btoOP05.setCaption("OP05");
        btoOP05.setStyleName(EstiloBoton);
        
        btoOP06.setWidth("150px");
        btoOP06.setHeight("150px");
        //btoOP06.setCaption("OP06");
        btoOP06.setStyleName(EstiloBoton);
        
        btoOP07.setWidth("150px");
        btoOP07.setHeight("150px");
        //btoOP07.setCaption("Salir");
        btoOP07.setStyleName(EstiloBoton);
    	btoOP07.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP07_Click();
            }
        });
    	btoOP07.setIcon(ByosImagenes.icon[83]);
        
        btoOP08.setWidth("150px");
        btoOP08.setHeight("150px");
        //btoOP08.setCaption("OP08");
        btoOP08.setStyleName(EstiloBoton);
        
        btoOP09.setWidth("150px");
        btoOP09.setHeight("150px");   
        //btoOP09.setCaption("OP09");
        btoOP09.setStyleName(EstiloBoton);
        
        btoOP10.setWidth("150px");
        btoOP10.setHeight("150px");
        //btoOP10.setCaption("Buscar");
        btoOP10.setStyleName(EstiloBoton);
        btoOP10.setIcon(ByosImagenes.icon[80]);
    	btoOP10.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP10_Click();
            }
        });        
        this.addComponent(PanelMenu);
    }
    
    public static boolean EstadoForm = false;
    public void openWindows(){
        if(subwindow.getParent() != null) {
           Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
        }else {
           //EstadoForm = true;
     	   UI.getCurrent().addWindow(subwindow);                  
        }        
    }
 
    public void closeWindows(){
    	//EstadoForm = false;
        subwindow.close();
    }
    
    private void btoOP07_Click() {
    	closeWindows();
    }

    private void btoOP01_Click() {
    	new moduloProveedor().openWindows();
    }

    private void btoOP10_Click() {
    	moduloProveedor Proveedor = new moduloProveedor();
    	Proveedor.procesoListar(true);
    }



}
