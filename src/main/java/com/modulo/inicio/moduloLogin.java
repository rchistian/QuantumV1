package com.modulo.inicio;

import java.io.Serializable;
import com.byos.sys.ui.ByosMenu.ByosMenuInicio;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosTecladoNumerico;
import com.modulo.usuarios.moduloMostrarUsuario;
import com.modulo.usuarios.tblUsuarios;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class moduloLogin extends AbsoluteLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public Window subwindow;
    private Image ImagenFondo = new Image();
    private Image ImagenLogo = new Image();
    private VerticalLayout LayoutImagen = new VerticalLayout();
    private VerticalLayout LayoutFondo = new VerticalLayout();
    private ByosTecladoNumerico TecladoNumerico = new ByosTecladoNumerico();

    private Button btoOP01 = new Button();
    private Button btoOP02 = new Button();
    private int CantidadIntentos=0; 

    public String EstiloBoton = "boton3d";
    
    public void IniciarComponentes() {

        ImagenFondo.setIcon(ByosImagenes.icon[120]);
        LayoutFondo.addComponent(ImagenFondo);
        addComponent(LayoutFondo, "left:0px; top:0px;");
    	
    	btoOP01.setWidth("200px");
    	btoOP01.setHeight("100px");
    	btoOP01.setStyleName(EstiloBoton);
    	btoOP01.setImmediate(true);
    	btoOP01.setCaption("Salir");
    	//btoOP01.setIcon(ByosImagenes.icon[117]);
    	btoOP01.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP01_Click();
            }
        });
    	
    	    	
    	btoOP02.setWidth("200px");
    	btoOP02.setHeight("100px");
    	btoOP02.setStyleName(EstiloBoton);
    	btoOP02.setCaption("Entrar");
    	//btoOP02.setIcon(ByosImagenes.icon[118]);
    	btoOP02.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
            	btoOP02_Click();
            }
        });
    	    	
    	int MargenDerecho01=0;
    	int MargenDerecho02=39;
    	int MargenTop=0;
    	
    	addComponent(TecladoNumerico, "left:" + (430 + MargenDerecho01) + "px; top:" + (350 + MargenTop) + "px;");
    	addComponent(btoOP01, "left:" + (70 + MargenDerecho01) + "px; top:" + (480 + MargenTop) + "px;");
    	addComponent(btoOP02, "left:" + (715 + MargenDerecho02) + "px; top:" + (480 + MargenTop) + "px;");
    	    
    }
    
    @SuppressWarnings("deprecation")
	public moduloLogin() {
    	
        subwindow = new Window("Inicio Sistema");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        subwindow.setClosable(false);
        //subwindow.setIcon(ByosImagenes.icon[11]);
        /*
        subwindow.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
            	ImagenFondo_Click();
            }
        });
        */   
        IniciarComponentes();
             
    }
    
    private void btoOP01_Click(){
    	EstadoForm = false;
        subwindow.close();        
    }

    private void btoOP02_Click(){
    	if(!ByosMenuInicio.EstadoForm) {
    		tblUsuarios Usuario = new tblUsuarios();
    		try {
    			
				if(Usuario.buscarClave(TecladoNumerico.getPasswordTexto().getValue().toString())) {
				   closeWindows();
				   Notification.show("Bienvenido " + Usuario.getNombres(),Notification.TYPE_TRAY_NOTIFICATION);	
				   if(!moduloMostrarUsuario.EstadoForm) {
				       new moduloMostrarUsuario(Usuario);
				   }
				}else {
					CantidadIntentos++;
                    Notification.show("Clave Erronea",Notification.TYPE_TRAY_NOTIFICATION);					    
				}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CantidadIntentos++;
				e.printStackTrace();
				Notification.show("Clave Erronea",Notification.TYPE_TRAY_NOTIFICATION);	
			}
    	}
    	if(CantidadIntentos>3) {
    	   Notification.show("Ud. a Superado la Cantidad de Intentos Permitidos",Notification.TYPE_TRAY_NOTIFICATION);	
    	   closeWindows();
    	}
    }
    
    private void ImagenFondo_Click(){
    	if(!ByosMenuInicio.EstadoForm) {
    		closeWindows();
    	    new ByosMenuInicio().openWindows();
    	}
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
