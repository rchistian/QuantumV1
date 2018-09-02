package com.byos.sys.ui.ByosMenu;
import java.io.Serializable;

import com.modulo.componentes.ByosImagenes;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ByosMenuInicio extends VerticalLayout implements Serializable{
	public Window subwindow;

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
    public Button btoOP11 = new Button();
    public Button btoOP12 = new Button();
    public Button btoOP13 = new Button();
    public Button btoOP14 = new Button();
    public Button btoOP15 = new Button();
    public Button btoOP16 = new Button();
    public Button btoOP17 = new Button();
    public Button btoOP18 = new Button();
    public Button btoOP19 = new Button();
    public Button btoOP20 = new Button();
    
    public Button btoOP21 = new Button();
    public Button btoOP22 = new Button();
    public Button btoOP23 = new Button();
    public Button btoOP24 = new Button();
    
    public int TamanoTop  = 5;
    public int TamanoLeft = 25;
    public int MargenTop  = -5;
    public int MargenLeft = -5;
    
    public Label Titulo = new Label();
    public Label TituloFecha = new Label();
    public Label TituloHora = new Label();
    public int PosLeft = 0;
    public int PosTop = 0;
    public String EstiloBoton = "botonmenu";
    
    public int PosTopArribaCentro=0;
    public int PosLeftArribaCentro=0;
    
    public int PosTopDerechaCentro=0;
    public int PosLeftDerechaCentro=0;
    
    public int PosTopAbajoCentro=0;
    public int PosLeftAbajoCentro=0;
    
    public int PosTopIzquierdaCentro=0;
    public int PosLeftIzquierdaCentro=0;
    
    public ByosMenuInicio(){
        
    	subwindow = new Window("Menu Principal");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);    	 	
    	
        PanelMenu.setWidth("1024px");
        PanelMenu.setHeight("768px");
        PanelMenu.setContent(Layout);
        
        Titulo.setWidth("1024px");
        Titulo.setHeight("70px");
        Titulo.setStyleName("estilotitulo");
        Titulo.setCaption("DATOS DEL HUESPED");
        
        TituloFecha.setWidth("200px");
        TituloFecha.setHeight("70px");
        TituloFecha.setStyleName("estilotitulofechahora");      
        
        TituloHora.setWidth("200px");
        TituloHora.setHeight("70px");
        TituloHora.setStyleName("estilotitulofechahora");        
        
        Layout.setWidth("100%");
        Layout.setHeight("100%");
        
        
        //Layout.addComponent(Titulo,      "left: 0px; top: 0px;");
        //Layout.addComponent(TituloFecha, "left: 0px; top: 0px;");
        //Layout.addComponent(TituloHora,  "left: 824px; top: 0px;");
        
        
        Layout.addComponent(btoOP01, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft+=210 + MargenLeft;   
        Layout.addComponent(btoOP02, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft+=110 + MargenLeft;
        
        Layout.addComponent(btoOP03, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft+=110 + MargenLeft;
        PosLeftArribaCentro=PosLeft;
        PosTopArribaCentro=PosTop;
        
        Layout.addComponent(btoOP04, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft+=110 + MargenLeft;

        
        Layout.addComponent(btoOP05, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft+=110 + MargenLeft;
        
        Layout.addComponent(btoOP06, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft+=110 + MargenLeft;
        Layout.addComponent(btoOP07, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft+=100 + MargenLeft;
        PosTop+=210 + MargenTop;
        PosLeftDerechaCentro=PosLeft;
        PosTopDerechaCentro=PosTop;
        
        Layout.addComponent(btoOP08, "left: " + (PosLeft + TamanoLeft - 50) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop+=110 + MargenTop;
        Layout.addComponent(btoOP09, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop+=110 + MargenTop;
        Layout.addComponent(btoOP10, "left: " + (PosLeft + TamanoLeft - 50) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop+=110 + MargenTop;
        PosLeft-=100 + MargenLeft;
        Layout.addComponent(btoOP11, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop+=50 + MargenTop;
        PosLeft-=110 + MargenLeft;
        Layout.addComponent(btoOP12, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop+=50 + MargenTop;
        PosLeft-=110 + MargenLeft;

        PosLeftAbajoCentro=PosLeft;
        PosTopAbajoCentro=PosTop;
        Layout.addComponent(btoOP13, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft-=110 + MargenLeft;
        Layout.addComponent(btoOP14, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosLeft-=110 + MargenLeft;
        Layout.addComponent(btoOP15, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop-=50 + MargenTop;
        PosLeft-=110 + MargenLeft;
        Layout.addComponent(btoOP16, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop-=50 + MargenTop;
        PosLeft-=210 + MargenLeft;
        Layout.addComponent(btoOP17, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");        
        PosTop-=110 + MargenTop;
        Layout.addComponent(btoOP18, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop-=110 + MargenTop;
        
        PosTopIzquierdaCentro=PosTop;
        PosLeftIzquierdaCentro=PosLeft;
        Layout.addComponent(btoOP19, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        PosTop-=110 + MargenTop;
        Layout.addComponent(btoOP20, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");

        PosTop=PosTopArribaCentro+150 + MargenTop;
        PosLeft=PosLeftArribaCentro - 25 + MargenLeft;
        Layout.addComponent(btoOP21, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");        
        
        PosTop=PosTopDerechaCentro+85 + MargenTop;
        PosLeft=PosLeftDerechaCentro - 200 + MargenLeft;
        Layout.addComponent(btoOP22, "left: " + (PosLeft + TamanoLeft - 50) + "px; top: " + (PosTop + TamanoTop) + "px;");
        
        PosTop=PosTopAbajoCentro-200 + MargenTop;
        PosLeft=PosLeftArribaCentro - 25 + MargenLeft;
        Layout.addComponent(btoOP23, "left: " + (PosLeft + TamanoLeft) + "px; top: " + (PosTop + TamanoTop) + "px;");
        
        PosTop=PosTopDerechaCentro+85;
        PosLeft=PosLeftIzquierdaCentro + 150 + MargenLeft;
        Layout.addComponent(btoOP24, "left: " + (PosLeft + TamanoLeft + 50) + "px; top: " + (PosTop + TamanoTop) + "px;");

        
        
        btoOP01.setWidth("200px");
        btoOP01.setHeight("200px");
        //btoOP01.setCaption("Ficha");
        btoOP01.setStyleName(EstiloBoton);
        btoOP01.setIcon(ByosImagenes.icon[81]);
        
        btoOP02.setWidth("100px");
        btoOP02.setHeight("150px");
        //btoOP02.setCaption("Recerba");
        btoOP02.setStyleName(EstiloBoton);
        btoOP02.setIcon(ByosImagenes.icon[81]);
        
        
        btoOP03.setWidth("100px");
        btoOP03.setHeight("100px");
        //btoOP03.setCaption("OP03");
        btoOP03.setStyleName(EstiloBoton);
        btoOP03.setIcon(ByosImagenes.icon[81]);
        
        btoOP04.setWidth("100px");
        btoOP04.setHeight("100px");
        //btoOP04.setCaption("OP04");
        btoOP04.setStyleName(EstiloBoton);
        btoOP04.setIcon(ByosImagenes.icon[81]);
    	btoOP24.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP24_Click();
            }
        });
        
        btoOP05.setWidth("100px");
        btoOP05.setHeight("100px");
        //btoOP05.setCaption("OP05");
        btoOP05.setStyleName(EstiloBoton);
        btoOP05.setIcon(ByosImagenes.icon[81]);
        
        btoOP06.setWidth("100px");
        btoOP06.setHeight("150px");
        //btoOP06.setCaption("OP06");
        btoOP06.setStyleName(EstiloBoton);
        btoOP06.setIcon(ByosImagenes.icon[81]);
        
        btoOP07.setWidth("200px");
        btoOP07.setHeight("200px");
        //btoOP07.setCaption("OP07");
        btoOP07.setStyleName(EstiloBoton);
        btoOP07.setIcon(ByosImagenes.icon[81]);
        
        btoOP08.setWidth("150px");
        btoOP08.setHeight("100px");
        //btoOP08.setCaption("OP08");
        btoOP08.setStyleName(EstiloBoton);
        btoOP08.setIcon(ByosImagenes.icon[121]);
    	btoOP08.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP08_Click();
            }
        });
        
        btoOP09.setWidth("100px");
        btoOP09.setHeight("100px");   
        //btoOP09.setCaption("OP09");
        btoOP09.setStyleName(EstiloBoton);
        btoOP09.setIcon(ByosImagenes.icon[81]);
        
        btoOP10.setWidth("150px");
        btoOP10.setHeight("100px");
        btoOP10.setStyleName(EstiloBoton);
        btoOP10.setIcon(ByosImagenes.icon[124]);
    	btoOP10.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP10_Click();
            }
        });
    	
        btoOP11.setWidth("200px");
        btoOP11.setHeight("200px");
        btoOP11.setStyleName(EstiloBoton);
        btoOP11.setIcon(ByosImagenes.icon[81]);

        btoOP12.setWidth("100px");
        btoOP12.setHeight("150px");
        btoOP12.setStyleName(EstiloBoton);
        btoOP12.setIcon(ByosImagenes.icon[81]);

        btoOP13.setWidth("100px");
        btoOP13.setHeight("100px");
        btoOP13.setStyleName(EstiloBoton);
        btoOP13.setIcon(ByosImagenes.icon[81]);
 
        btoOP14.setWidth("100px");
        btoOP14.setHeight("100px");
        btoOP14.setStyleName(EstiloBoton);
        btoOP14.setIcon(ByosImagenes.icon[81]);
        
        btoOP15.setWidth("100px");
        btoOP15.setHeight("100px");
        btoOP15.setStyleName(EstiloBoton);
        btoOP15.setIcon(ByosImagenes.icon[81]);        
        
        btoOP16.setWidth("100px");
        btoOP16.setHeight("150px");
        btoOP16.setStyleName(EstiloBoton);
        btoOP16.setIcon(ByosImagenes.icon[81]);

        btoOP17.setWidth("200px");
        btoOP17.setHeight("200px");
        btoOP17.setStyleName(EstiloBoton);
        btoOP17.setIcon(ByosImagenes.icon[81]);
        
        btoOP18.setWidth("150px");
        btoOP18.setHeight("100px");
        btoOP18.setStyleName(EstiloBoton);
        btoOP18.setIcon(ByosImagenes.icon[81]); 
        
        btoOP19.setWidth("100px");
        btoOP19.setHeight("100px");
        btoOP19.setStyleName(EstiloBoton);
        btoOP19.setIcon(ByosImagenes.icon[81]); 
        
        btoOP20.setWidth("150px");
        btoOP20.setHeight("100px");
        btoOP20.setStyleName(EstiloBoton);
        btoOP20.setIcon(ByosImagenes.icon[81]); 

        btoOP21.setWidth("150px");
        btoOP21.setHeight("150px");
        btoOP21.setStyleName(EstiloBoton);
        btoOP21.setIcon(ByosImagenes.icon[81]);

        btoOP22.setWidth("150px");
        btoOP22.setHeight("150px");
        btoOP22.setStyleName(EstiloBoton);
        btoOP22.setIcon(ByosImagenes.icon[81]);

        btoOP23.setWidth("150px");
        btoOP23.setHeight("150px");
        btoOP23.setStyleName(EstiloBoton);
        btoOP23.setIcon(ByosImagenes.icon[81]);

        btoOP24.setWidth("150px");
        btoOP24.setHeight("150px");
        btoOP24.setStyleName(EstiloBoton);
        btoOP24.setIcon(ByosImagenes.icon[123]);

        this.addComponent(PanelMenu);
    }
    
    private void btoOP24_Click() {
    	if(!ByosMenuCircularHuespedes.EstadoForm)
    	    new ByosMenuCircularHuespedes().openWindows();
    }
    
    private void btoOP08_Click() {
    	if(!ByosMenuCircularUsuarios.EstadoForm)
    	    new ByosMenuCircularUsuarios().openWindows();
    }
 
    private void btoOP10_Click() {
    	if(!ByosMenuCircularProveedor.EstadoForm)
      	    new ByosMenuCircularProveedor().openWindows();
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
    	EstadoForm = false;
        subwindow.close();
    }
    
    private void btoOP07_Click() {
    	closeWindows();
    }

}
