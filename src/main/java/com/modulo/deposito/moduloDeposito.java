package com.modulo.deposito;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.deposito.tblDeposito;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.byos.sys.util.utilString;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;

public class moduloDeposito extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    ByosForm ByosFormulario = new ByosForm();

    
    public tblDeposito ClaseDeposito;

    public ByosMenu menu = new ByosMenu();
    
    String DatosVisibles[] = {
    		           "id",
                       "codigodeposito", 
                       "descripcion",
                       "responsable",
                       "tipodeposito"
                    };

    String DatosTitulos[] = {
    		           "ID",
                       "Codigo", 
                       "Descripcion",
                       "Responsable",
                       "Tipo Deposito"
                    };
    
    String DatosRequeridos[] = {
            "codigodeposito", 
            "descripcion",
            "responsable",
            "tipodeposito"
    };
    
    ByosVerticalLayout Main = new ByosVerticalLayout("Mantenimiento de Deposito","100%");
    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");
    
    
    
    public moduloDeposito() {
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");
    	ByosFormulario.setWidth("100%");
    	ByosFormulario.addComponent(menu);
        ClaseDeposito = new tblDeposito(); 
        ByosFormulario.setClase(ClaseDeposito, DatosVisibles, DatosTitulos, null);
        initComponentes();       
        Main.setMargin(false);
        Main.setWidth("100%");
        Main.Contenido.addComponent(ByosFormulario); 
        
        addComponent(Main);
    }
    
    @SuppressWarnings({ "deprecation", "serial" })
	public void initComponentes() {
        BtoNuevo.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
               procesoNuevo();
               
            }
         });

         BtoBuscar.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
               try {
 				procesoBuscar();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            }
         });
         
         BtoEliminar.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                      procesoEliminar();
                      
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
                 
             }
         });
         BtoGuardar.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                     procesoGuardar();
                     
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         BtoProximo.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                 	procesoTblProximo();
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         BtoAnterior.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                 	procesoTblAnterior();    
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         
         ByosFormulario.getCampo("id").txtTexto.setReadOnly(true);
         ByosFormulario.getCampo("codigodeposito").btoBoton1.setBoton(3, "Listar","");
         ByosFormulario.getCampo("codigodeposito").btoBoton1.setVisible(true);
         ByosFormulario.getCampo("codigodeposito").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListarDepositos();       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
         ByosFormulario.getCampo("codigodeposito").btoBoton2.setVisible(true);
         ByosFormulario.getCampo("codigodeposito").btoBoton2.setBoton(18, "Auto Codigo","");
         ByosFormulario.getCampo("codigodeposito").btoBoton2.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     ProximoCodigo();                    
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         
     	ByosFormulario.setTipoCampo("tipodeposito", "ComboBox");             
     	ByosFormulario.getCampo("tipodeposito").CboxItem.setContainerDataSource(ByosContenedores.getContainerTipoDepositos());          
    }

    
    public void asignacion(){
    	try{
			((tblDeposito)ByosFormulario.Clase).buscarCodigo(((tblDeposito)ByosFormulario.Clase).getCodigodeposito());	
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void procesoTblProximo(){
    	ByosFormulario.procesoProximo();
    	asignacion();
    	ByosFormulario.refrescar();      	
    }
    
    public void procesoTblAnterior(){
    	ByosFormulario.procesoAnterior();
    	asignacion();
    	ByosFormulario.refrescar();      	
    }    
      
    
	public void procesoBuscar() throws Exception {	
		ByosFormulario.setArrayClase(((tblDeposito)ByosFormulario.Clase).Buscar(((tblDeposito)ByosFormulario.Clase)));  
        if (ByosFormulario.ArrayClase == null || ByosFormulario.ArrayClase.size()==0){
            ((tblDeposito)ByosFormulario.Clase).limpiar();
            ByosFormulario.refrescar();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
        ByosFormulario.refrescar();
    }
    
    
    public void procesoNuevo(){
        ((tblDeposito)ByosFormulario.Clase).limpiar();
        ByosFormulario.refrescar();
        ByosFormulario.setArrayClase(null);
        asignacion();
        
    }    

    public void procesoGuardar(){
      if(ByosFormulario.DatosRequeridos(DatosRequeridos)){
    	boolean Existe=false;
    	try{
			Existe=((tblDeposito)ByosFormulario.Clase).existeCodigo(((tblDeposito)ByosFormulario.Clase).getCodigodeposito());
		}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	
    	if(Existe){
		

				try{
					String Resultado=((tblDeposito)ByosFormulario.Clase).guardar(((tblDeposito)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	 	      	             ((tblDeposito) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion)).setDeposito(((tblDeposito)ByosFormulario.Clase));
	 	      	             ByosFormulario.refrescar();
	                      }
	                   }                      
	  	               Notification.show("Registro Modificada", Notification.TYPE_TRAY_NOTIFICATION);
	                }else{
	 				   Notification.show("El Registro no se pudo Modificar",Notification.TYPE_TRAY_NOTIFICATION);	
	 				}				    
				}
				catch(Exception e){
					e.printStackTrace();
				}


    	}else{

 				try{
 					String Resultado=((tblDeposito)ByosFormulario.Clase).guardar(((tblDeposito)ByosFormulario.Clase));
 				    if(Resultado.equals(utilString.SQL_INSERTADO)){
 					   Notification.show("Registro Guardada",Notification.TYPE_TRAY_NOTIFICATION);
 				    }else{
 				       Notification.show("El Registro no se pudo Guardar",Notification.TYPE_TRAY_NOTIFICATION);	
 				    }
 				}
 				catch(Exception e){
 					e.printStackTrace();
 				}
 			  }	

      }
    }
    
    public void procesoEliminar(){

				
					//String Resultado=((tblDeposito)Clase).desincorporar(null);
					String Resultado=((tblDeposito)ByosFormulario.Clase).eliminar(((tblDeposito)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_ELIMINADO)){
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	                    	 
	 	      	             ByosFormulario.ArrayClase.remove(ByosFormulario.Posicion);
	 	      	             ByosFormulario.Posicion--;
	 	      	             if(ByosFormulario.Posicion>=0){
	 	      	               ((tblDeposito)ByosFormulario.Clase).setDeposito((tblDeposito) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion));
	 	      	             }else{
	 	      	               procesoNuevo(); 
	 	      	             }
	 	      	             ByosFormulario.refrescar();
	                      }
	                   }                      
	  	               Notification.show("Deposito Eliminada",Notification.TYPE_TRAY_NOTIFICATION);
                    }else{
                       Notification.show("Esta Deposito No se Pudo Eliminar",Notification.TYPE_TRAY_NOTIFICATION);	
                    }
				
                     
    }
    
	public void procesoListarDepositos(){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
		   tblDeposito TblMedidaListar = new tblDeposito();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigodeposito",   String.class, "Codigo",  "", new LikeFilter("codigodeposito",""));
		   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.Buscar(new tblDeposito()), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblDeposito)AL.get(0)).getCodigodeposito();
	                           procesoNuevo();
	                           ((tblDeposito)ByosFormulario.Clase).setCodigodeposito(xCodigo);
	                           procesoBuscar();
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
	
    void ProximoCodigo() throws Exception {
        String xProximoCodigo=ByosSql.getProximoCodigo("Select max(codigodeposito) From tbldepositos Where abs(codigodeposito)>0",3);
        ((tblDeposito)ByosFormulario.Clase).setCodigodeposito(xProximoCodigo);
        ByosFormulario.refrescar(); 
    }    
    
}
