package com.modulo.usuarios;

import java.io.Serializable;
import com.byos.sys.ui.ByosMenu.ByosMenuInicio;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosVisorImegenes;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class moduloMostrarUsuario extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    
    private tblUsuarios TblUsuarios;
    public ByosVisorImegenes ByosVisorImegenes01;
    public Window subwindow;
    Label Usuario = new Label();
    Label Descripcion = new Label();
    Label NumeroEntradas = new Label(); 
    String EstiloLabel = "loginLabel";
    
    public moduloMostrarUsuario(final tblUsuarios TblUsuarios){ 
    	this.TblUsuarios = TblUsuarios;
    	
    	subwindow = new Window("Datos del Usuario");
        subwindow.setWidth("600px");
        subwindow.setHeight("550px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        subwindow.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
            	closeWindows();
            	AbrirMenuInicial();	
            }
        });
        
    	setSpacing(true);
    	setMargin(false);
    	      
        String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblUsuarios\\Fotos\\" + TblUsuarios.getID() + "\\";
        String DirUrl="../dbsimagenes/tblUsuarios/Fotos/" + TblUsuarios.getID() + "/";
       
        ByosVisorImegenes01 = new ByosVisorImegenes(DirUrl, DirFile, "", TblUsuarios.getLogin(),false, "");
        
        
        Embedded reindeerImage = null;
        if (ByosVisorImegenes01.getImagenes() !=  null && ByosVisorImegenes01.getImagenes().size()>0) {
        	reindeerImage = new Embedded( null, ByosVisorImegenes01.getImagenes().get(0) );

        }else{
        	reindeerImage = new Embedded( null, ByosImagenes.icon[126]);
        }
        
        reindeerImage.setWidth( "480px" ); 
        reindeerImage.setHeight( "360px" );
        
        Usuario.setWidth("100%");
        Descripcion.setWidth("100%");
        NumeroEntradas.setWidth("100%");
        
        Usuario.setStyleName(EstiloLabel);
        Descripcion.setStyleName(EstiloLabel);
        NumeroEntradas.setStyleName(EstiloLabel);      
        
        Usuario.setValue("Bienvenido: " + TblUsuarios.getNombres());
        Descripcion.setValue(TblUsuarios.getDescripcion());
        NumeroEntradas.setValue("Numero de Entradas (1)");
        
        if(reindeerImage != null) {
           addComponent(reindeerImage);
           setComponentAlignment(reindeerImage,Alignment.TOP_CENTER);
        }
        addComponent(Usuario);
        addComponent(Descripcion);
        addComponent(NumeroEntradas);
        
        setComponentAlignment(Usuario,Alignment.TOP_CENTER);
        setComponentAlignment(Descripcion,Alignment.TOP_CENTER);
        setComponentAlignment(NumeroEntradas,Alignment.TOP_CENTER);
        openWindows();  
    }
    
    public void setTblUsuarios(final tblUsuarios TblUsuarios){
    	this.TblUsuarios = TblUsuarios;
    	String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblUsuarios\\Fotos\\" + TblUsuarios.getID() + "\\";
        String DirUrl="../dbsimagenes/tblUsuarios/Fotos/" + TblUsuarios.getID() + "/";
        ByosVisorImegenes01.init(DirUrl, DirFile, TblUsuarios.getLogin());
    }

    public static boolean EstadoForm = false;
    public void openWindows(){
        if(subwindow.getParent() != null) {
           Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
        }else {
           EstadoForm = true;
     	   UI.getCurrent().addWindow(subwindow);                  
        }        
    }

    public void closeWindows(){
    	EstadoForm = false;
        subwindow.close();
    }

    private void AbrirMenuInicial(){
    	if(!ByosMenuInicio.EstadoForm) {
    		closeWindows();
    	    new ByosMenuInicio().openWindows();
    	}
    }

}
