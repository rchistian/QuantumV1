package com.modulo.compras;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilString;
import com.modulo.deposito.tblDeposito;
import com.modulo.producto.tblProducto;
import com.modulo.proveedor.tblProveedor;
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

public class moduloBuscarCompras extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    public Window subwindow;
    private tblProducto TblProducto;

    public VerticalLayout layout = new VerticalLayout();
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    private VerticalLayout vlayout01 = new VerticalLayout();
    
    public ByosBoton btoAceptar =  new ByosBoton();
    public ByosBoton btoCancelar = new ByosBoton();
    
    ByosForm ByosFormulario = new ByosForm();
    public buscarCompras ClaseCompras;
    ByosVerticalLayout Main = new ByosVerticalLayout("Compras","100%");    
    
    String DatosVisibles[] = {
    		"estado",
        	"codigocompra01",
        	"codigocompra02",
            "fechacompra01", 
            "fechacompra02", 
        	"codigoproveedor",
            "codigodeposito",
            "tipocompras",
            "tipodocumento",
            "planillaimportacion",
            "expedienteimportacion",            
            "codigodocumento",
            "numerocontrol",
            "documentoafectado",
            "fechadocumento01",
            "fechadocumento02",
            "fechaaplicacion01",
            "fechaaplicacion02",
            "codigocontable",
            "codigoislr",
            "porcentajeislr",
            "descripcion01",
            "codigoproducto",
            "descripcionproducto"
    };      
        
    String DatosTitulos[] = {
    		    "Estado de Documento",
                "Codigo Compra Inicial",
                "Codigo Compra Final",
                "Fecha Compra Inicial",
                "Fecha Compra Final",
                "Codigo Proveedor",
                "Codigo Deposito",
                "Tipo Compras",
                "Tipo Documento",
                "Planilla Importacion",
                "Expediente Importacion",         
                "Codigo Documento",
                "Numero Control",
                "Documento Afectado",
                "Fecha Documento Inicial",
                "Fecha Documento Final",
                "Fecha Aplicacion Inicial",
                "Fecha Aplicacion Final",    		    
                "Codigo Contable",
                "Codigo ISLR",
                "Porcentaje ISLR",
                "Descripcion",
                "Codigo Producto",
                "Descripcion del Producto"
        };    
        
   
    
    public moduloBuscarCompras(){
        btoAceptar.setBoton(32,"Aceptar","Aceptar");        
        btoCancelar.setBoton(33,"Cancelar","Cancelar"); 
        setMargin(false);
        
        layout.setMargin(false);
        layout.setSpacing(true);
        subwindow = new Window("Buscar Compras");
        subwindow.setWidth("45em");
        subwindow.setHeight("48em");
        subwindow.setModal(true);       
        subwindow.setContent(layout);
        
        layout.setSizeFull();
        layout.setSpacing(true);
        layout.setMargin(true);
        
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
    	
    	
        ClaseCompras = new buscarCompras(); 
        ByosFormulario.setMargin(false);
        ByosFormulario.setClase(ClaseCompras, DatosVisibles, DatosTitulos, null);         
        Main.Contenido.addComponent(ByosFormulario);
        Main.Contenido.setMargin(false);
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
    	ByosFormulario.getCampo("fechacompra01").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario.getCampo("fechacompra02").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario.getCampo("fechadocumento01").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario.getCampo("fechadocumento02").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario.getCampo("fechaaplicacion01").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario.getCampo("fechaaplicacion02").setTipoCampo(utilString.CAMPO_FECHAHORA);

        ByosFormulario.setTipoCampo("estado", "ComboBox");    
        ByosFormulario.getCampo("estado").CboxItem.setContainerDataSource(ByosContenedores.getEstadoDocumento());  
    	
    	ByosFormulario.getCampo("codigodeposito").btoBoton1.setBoton(49, "Listar", "");
    	ByosFormulario.getCampo("codigodeposito").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("codigodeposito").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarDeposito();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
    	
    	ByosFormulario.getCampo("codigoproveedor").btoBoton1.setBoton(49, "Listar", "");
    	ByosFormulario.getCampo("codigoproveedor").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("codigoproveedor").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarProveedor();                    
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
    
	public void procesoListarDeposito(){
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
	                           ((buscarCompras)ByosFormulario.Clase).setCodigodeposito(xCodigo);    					   
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
 
	public void procesoListarProveedor(){
	  	   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);   
		   tblProveedor TblProveedorListar = new tblProveedor();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigoproveedor", String.class,"Codigo",      "", new LikeFilter("codigoproveedor", ""));
		   ByosColumnas[1] = new ByosColumna("descripcion",     String.class,"Descripicon", "", new LikeFilter("descripcion",     ""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblProveedorListar.BuscarArray(new tblProveedor()), TblProveedorListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	            	        	String xCodigo = ((tblProveedor)AL.get(0)).getCodigoproveedor(); 
	            	        	System.out.println(xCodigo);
	    					   ((buscarCompras)ByosFormulario.Clase).setCodigoproveedor(xCodigo); 
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
