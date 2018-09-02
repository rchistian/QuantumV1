package com.modulo.traslado;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilString;
import com.modulo.deposito.tblDeposito;
import com.modulo.producto.tblProducto;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class moduloBuscarTraslado extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    public Window subwindow;
    private tblProducto TblProducto;

    public VerticalLayout layout = new VerticalLayout();
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    private VerticalLayout vlayout01 = new VerticalLayout();
    
    public ByosBoton btoAceptar =  new ByosBoton();
    public ByosBoton btoCancelar = new ByosBoton();
    
    ByosForm ByosFormulario = new ByosForm();
    public buscarTraslado ClaseTraslado;
    ByosVerticalLayout Main = new ByosVerticalLayout("Traslado de Productos","100%");    
    
    String DatosVisibles[] = {
            "codigotraslado01",
            "codigotraslado02",
            "fechatraslado01",
            "fechatraslado02",
            "depositoorigen",
            "depositodestino",
            "codigoproducto",
            "descripcionproducto"
         };

    String DatosTitulos[] = {
            "Codigo Traslado Inicial",
            "Codigo Traslado Final",
            "Fecha Traslado Inical",
            "Fecha Traslado Final",
            "Deposito Origen",
            "Deposito Destino",
            "Codigo Producto",
            "Descripcion Producto"            
         };    
    
    public moduloBuscarTraslado(){
        btoAceptar.setBoton(32,"Aceptar","Aceptar");        
        btoCancelar.setBoton(33,"Cancelar","Cancelar"); 
       
        layout.setMargin(true);
        layout.setSpacing(true);
        subwindow = new Window("Buscar Traslado");
        subwindow.setWidth("45em");
        subwindow.setHeight("48em");
        subwindow.setModal(true);       
        subwindow.setContent(layout);
        
        layout.setSizeFull();
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setStyleName("v-verticallayout-main");
        
        Main.setMargin(false);
        Main.setWidth("100%");
        


        hlayout01.setMargin(false);
        hlayout01.setSpacing(false);
        hlayout01.setImmediate(true);
        hlayout01.addComponent(btoAceptar);
        hlayout01.setComponentAlignment(btoAceptar,Alignment.BOTTOM_CENTER);
        hlayout01.addComponent(btoCancelar);
        hlayout01.setComponentAlignment(btoCancelar, Alignment.BOTTOM_CENTER); 
        


    	ByosFormulario.setWidth("100%");
    	
        ClaseTraslado = new buscarTraslado(); 
        ByosFormulario.setClase(ClaseTraslado, DatosVisibles, DatosTitulos, null);         
        Main.Contenido.addComponent(ByosFormulario);
        ByosFormulario.addComponent(hlayout01);
        ByosFormulario.setComponentAlignment(hlayout01, Alignment.TOP_CENTER);         
        initComponentes();
        vlayout01.setMargin(false);
        vlayout01.setSpacing(false);
        vlayout01.setImmediate(true);
        vlayout01.addComponent(Main); 
 
        
        layout.addComponent(vlayout01);
        layout.setComponentAlignment(vlayout01, Alignment.TOP_RIGHT);
    }

    public void initComponentes(){
    	ByosFormulario.getCampo("fechatraslado01").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario.getCampo("fechatraslado02").setTipoCampo(utilString.CAMPO_FECHAHORA);

    	
    	ByosFormulario.getCampo("depositoorigen").btoBoton1.setBoton(3, "Listar", "");
    	ByosFormulario.getCampo("depositoorigen").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("depositoorigen").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarDeposito("depositoorigen");                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
    	
    	ByosFormulario.getCampo("depositodestino").btoBoton1.setBoton(3, "Listar", "");
    	ByosFormulario.getCampo("depositodestino").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("depositodestino").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarDeposito("depositodestino");                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });      	
    }
    
    public void closeWindows(){
        subwindow.close();
    }
     
    public void openWindows(){
       if(subwindow.getParent() != null) {
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
       }else {
    	   UI.getCurrent().addWindow(subwindow);   
          
       }        
    }    
    
	public void procesoListarDeposito(final String Campo){
	  	   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);   
		   tblDeposito TblDepositosListar = new tblDeposito();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigodeposito", String.class,"Codigo","", new LikeFilter("codigodeposito", ""));
		   ByosColumnas[1] = new ByosColumna("descripcion",    String.class,"Descripicon","", new LikeFilter("descripcion",    ""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblDepositosListar.Buscar(new tblDeposito()), TblDepositosListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblDeposito)AL.get(0)).getCodigodeposito();
	    					   if(Campo.equals("depositoorigen")){
	                              ((buscarTraslado)ByosFormulario.Clase).setDepositoorigen(xCodigo);
	                              //((buscarTraslado)ByosFormulario.Clase).TblDepositoorigen.buscarCodigo(xCodigo);
	    					   }
	    					   if(Campo.equals("depositodestino")){
		                          ((buscarTraslado)ByosFormulario.Clase).setDepositodestino(xCodigo);
		                          //((buscarTraslado)ByosFormulario.Clase).TblDepositodestino.buscarCodigo(xCodigo);
		    				   }	    					   
	                           ByosFormulario.refrescar();  
	                           dwb.cerrarWindow();
	            	        }
	      		
	                  }catch (Exception e) {
	                      e.printStackTrace();
	                  }   
	               }
	  	       });
	       }catch (Exception e) {
	            e.printStackTrace();
	       }  	
	    }      

}
