package com.modulo.inicio;

import java.io.Serializable;
import com.byos.sys.ui.ByosMenu.ByosMenuInicio;
import com.modulo.componentes.ByosImagenes;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class moduloMenuSistemas  extends AbsoluteLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public Window subwindow;
    private Image ImagenFondo = new Image();
    private Image ImagenLogo = new Image();
    private VerticalLayout LayoutImagen = new VerticalLayout();
    private VerticalLayout LayoutFondo = new VerticalLayout();
    

    private Button btoOP01 = new Button();
    private Button btoOP02 = new Button();
    private Button btoOP03 = new Button();
    private Button btoOP04 = new Button();
    private Button btoOP05 = new Button();
    private Button btoOP06 = new Button();
    private Button btoOP07 = new Button();
    private Button btoOP08 = new Button();
    public String EstiloBoton = "botonobalado";
    
    public void IniciarComponentes() {

        ImagenFondo.setIcon(ByosImagenes.icon[115]);
        LayoutFondo.addComponent(ImagenFondo);
        addComponent(LayoutFondo, "left:0px; top:0px;");
    	
    	btoOP01.setWidth("145px");
    	btoOP01.setHeight("145px");
    	btoOP01.setStyleName(EstiloBoton);
    	btoOP01.setImmediate(true);
    	btoOP01.setIcon(ByosImagenes.icon[117]);
    	btoOP01.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP01_Click();
            }
        });
    	
    	    	
    	btoOP02.setWidth("145px");
    	btoOP02.setHeight("145px");
    	btoOP02.setStyleName(EstiloBoton);
    	btoOP02.setIcon(ByosImagenes.icon[118]);
    	btoOP02.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP02_Click();
            }
        });
    	
    	btoOP03.setWidth("145px");
    	btoOP03.setHeight("145px");
    	btoOP03.setStyleName(EstiloBoton);
    	btoOP03.setIcon(ByosImagenes.icon[119]);
    	btoOP03.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP03_Click();
            }
        });
    	
    	btoOP04.setWidth("145px");
    	btoOP04.setHeight("145px");
    	btoOP04.setStyleName(EstiloBoton);
    	btoOP04.setIcon(ByosImagenes.icon[81]);
    	btoOP04.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP04_Click();
            }
        });
    	
    	btoOP05.setWidth("145px");
    	btoOP05.setHeight("145px");
    	btoOP05.setStyleName(EstiloBoton);
    	btoOP05.setIcon(ByosImagenes.icon[81]);
    	btoOP05.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP05_Click();
            }
        });
    	
    	btoOP06.setWidth("145px");
    	btoOP06.setHeight("145px");
    	btoOP06.setStyleName(EstiloBoton);
    	btoOP06.setIcon(ByosImagenes.icon[81]);
    	btoOP06.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP06_Click();
            }
        });
    	
    	btoOP07.setWidth("145px");
    	btoOP07.setHeight("145px");
    	btoOP07.setStyleName(EstiloBoton);
    	btoOP07.setIcon(ByosImagenes.icon[81]);
    	btoOP07.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP07_Click();
            }
        });
    	
    	btoOP08.setWidth("145px");
    	btoOP08.setHeight("145px");
    	btoOP08.setStyleName(EstiloBoton);
    	btoOP08.setIcon(ByosImagenes.icon[81]);
    	btoOP08.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP08_Click();
            }
        });
    	
    	int MargenDerecho01=0;
    	int MargenDerecho02=39;
    	int MargenTop=0;
    	
    	addComponent(btoOP01, "left:" + (70 + MargenDerecho01) + "px; top:" + (370 + MargenTop) + "px;");
    	addComponent(btoOP02, "left:" + (70 + MargenDerecho01) + "px; top:" + (517 + MargenTop) + "px;");
    	addComponent(btoOP03, "left:" + (217 + MargenDerecho01) + "px; top:" + (370 + MargenTop) + "px;");
    	addComponent(btoOP04, "left:" + (217 + MargenDerecho01) + "px; top:" + (517 + MargenTop) + "px;");
    	
    	addComponent(btoOP05, "left:" + (620 + MargenDerecho02) + "px; top:" + (370 + MargenTop) + "px;");
    	addComponent(btoOP06, "left:" + (620 + MargenDerecho02) + "px; top:" + (517 + MargenTop) + "px;");
    	addComponent(btoOP07, "left:" + (767 + MargenDerecho02) + "px; top:" + (370 + MargenTop) + "px;");
    	addComponent(btoOP08, "left:" + (767 + MargenDerecho02) + "px; top:" + (517 + MargenTop) + "px;");
    }
    
    @SuppressWarnings("deprecation")
	public moduloMenuSistemas() {
    	
        subwindow = new Window("Inicio Sistema");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        subwindow.setClosable(false);
        //subwindow.setIcon(ByosImagenes.icon[11]);
        subwindow.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
            	ImagenFondo_Click();
            }
        });
             
        IniciarComponentes();
             
    }
    
    private void btoOP01_Click(){
    	if(!ByosMenuInicio.EstadoForm) {
    		closeWindows();
    	    new moduloLogin().openWindows();
    	}
    }

    private void btoOP02_Click(){
        Notification.show("Permiso Denegado",Notification.TYPE_TRAY_NOTIFICATION);	
    }

    private void btoOP03_Click(){
        Notification.show("Permiso Denegado",Notification.TYPE_TRAY_NOTIFICATION);	
    }

    private void btoOP04_Click(){
        Notification.show("Permiso Denegado",Notification.TYPE_TRAY_NOTIFICATION);	
    }

    private void btoOP05_Click(){
        Notification.show("Permiso Denegado",Notification.TYPE_TRAY_NOTIFICATION);	
    }

    private void btoOP06_Click(){
        Notification.show("Permiso Denegado",Notification.TYPE_TRAY_NOTIFICATION);	
    }

    private void btoOP07_Click(){
        Notification.show("Permiso Denegado",Notification.TYPE_TRAY_NOTIFICATION);	
    }

    private void btoOP08_Click(){
        Notification.show("Permiso Denegado",Notification.TYPE_TRAY_NOTIFICATION);	
    }
 
    
    private void ImagenFondo_Click(){
    	/*if(!ByosMenuInicio.EstadoForm) {
    		closeWindows();
    	    new ByosMenuInicio().openWindows();
    	}*/
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

}
