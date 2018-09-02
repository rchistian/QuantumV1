package com.modulo.unidadmedida;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosVerticalLayout;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.byos.sys.util.utilString;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;

public class moduloUnidadMedida extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    ByosForm ByosFormulario = new ByosForm();
    ByosVerticalLayout Main = new ByosVerticalLayout("Mantenimiento de Medidas","100%");
    
    public tblUnidadMedida ClaseUnidadMedida;

    public ByosMenu menu = new ByosMenu();
    
    String DatosVisibles[] = {
                       "codigomedida", 
                       "descripcion",
                       "pesable",
                       "estado"
                    };

    String DatosTitulos[] = {
                       "Codigo", 
                       "Descripcion",
                       "Pesable",
                       "Estado"
                    };
   
    

    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");
    
    
    
    public moduloUnidadMedida() {
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");
    	ByosFormulario.setWidth("100%");
    	ByosFormulario.addComponent(menu);
        ClaseUnidadMedida = new tblUnidadMedida(); 
        ByosFormulario.setClase(ClaseUnidadMedida, DatosVisibles, DatosTitulos, null);
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
                      procesoDesincorporar();
                      
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
         
         ByosFormulario.getCampo("codigomedida").btoBoton1.setBoton(3, "Listar","");
         ByosFormulario.getCampo("codigomedida").btoBoton1.setVisible(true);
         ByosFormulario.getCampo("codigomedida").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListarMedidas();       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
         ByosFormulario.getCampo("codigomedida").btoBoton2.setVisible(true);
         ByosFormulario.getCampo("codigomedida").btoBoton2.setBoton(18, "Auto Codigo","");
         ByosFormulario.getCampo("codigomedida").btoBoton2.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     ProximoCodigo();                    
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });         
         
    	ByosFormulario.setTipoCampo("pesable", "ComboBox");             
    	ByosFormulario.getCampo("pesable").CboxItem.setContainerDataSource(ByosContenedores.getContainerSiNo());  
    	
    	ByosFormulario.setTipoCampo("estado", "ComboBox");             
    	ByosFormulario.getCampo("estado").CboxItem.setContainerDataSource(ByosContenedores.getContainerActivo());    	
    }
    
    public void asignacion(){
    	try{
			((tblUnidadMedida)ByosFormulario.Clase).buscarCodigo(((tblUnidadMedida)ByosFormulario.Clase).getCodigomedida());	
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
		ByosFormulario.setArrayClase(((tblUnidadMedida)ByosFormulario.Clase).Buscar(((tblUnidadMedida)ByosFormulario.Clase)));  
        if (ByosFormulario.ArrayClase == null || ByosFormulario.ArrayClase.size()==0){
            ((tblUnidadMedida)ByosFormulario.Clase).limpiar();
            ByosFormulario.refrescar();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
        ByosFormulario.refrescar();
    }
    
    
    public void procesoNuevo(){
        ((tblUnidadMedida)ByosFormulario.Clase).limpiar();
        ByosFormulario.refrescar();
        ByosFormulario.setArrayClase(null);
        asignacion();
        
    }    

    public void procesoGuardar(){
      if(ByosFormulario.DatosRequeridos(DatosVisibles)){	
    	boolean Existe=false;
    	try{
			Existe=((tblUnidadMedida)ByosFormulario.Clase).existeCodigo(((tblUnidadMedida)ByosFormulario.Clase).getCodigomedida());
		}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	
    	if(Existe){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.UNIDADMEDIDA_MODIFICAR){			
			  public void procesoDefinicion(){
				try{
					String Resultado=((tblUnidadMedida)ByosFormulario.Clase).guardar(((tblUnidadMedida)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	 	      	             ((tblUnidadMedida) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion)).setUnidadMedida(((tblUnidadMedida)ByosFormulario.Clase));
	 	      	             ByosFormulario.refrescar();
	                      }
	                   }                      
	  	               Notification.show("Registro Modificado", Notification.TYPE_TRAY_NOTIFICATION);
	                }else{
		 			   Notification.show("El Registro no se pudo Modificar",Notification.TYPE_TRAY_NOTIFICATION);	
		 			}					    
				}
				catch(Exception e){
					e.printStackTrace();
					this.procesoLogException(e);
				}
			  }	
			  public String procesoValidacion(){
				  return "";
			  }
		   };
		   procesoGuardarPassword.procesoEjecucion();
    	}else{
 		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.UNIDADMEDIDA_MODIFICAR){			
 			  public void procesoDefinicion(){
 				try{
 					String Resultado=((tblUnidadMedida)ByosFormulario.Clase).guardar(((tblUnidadMedida)ByosFormulario.Clase));
 				    if(Resultado.equals(utilString.SQL_INSERTADO)){
 					   Notification.show("Registro Guardado",Notification.TYPE_TRAY_NOTIFICATION);
 				    }else{
 	 				   Notification.show("El Registro no se pudo Guardar",Notification.TYPE_TRAY_NOTIFICATION);	
 	 				}	
 				}
 				catch(Exception e){
 					e.printStackTrace();
 					this.procesoLogException(e);
 				}
 			  }	
 			  public String procesoValidacion(){
 				  return "";
 			  }
 		   };
 		   procesoGuardarPassword.procesoEjecucion();    		
    	}
      }
    }
    
    public void procesoDesincorporar(){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.UNIDADMEDIDA_DESINCORPORAR){			
			  public void procesoDefinicion(){
				try{
					//String Resultado=((tblUnidadMedida)Clase).desincorporar(null);
					String Resultado=((tblUnidadMedida)ByosFormulario.Clase).desincorporar(((tblUnidadMedida)ByosFormulario.Clase).getCodigomedida());
                    if(Resultado.equals(utilString.SQL_DESINCORPORAR)){
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	 	      	             ((tblUnidadMedida) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion)).setUnidadMedida(((tblUnidadMedida)ByosFormulario.Clase));
	 	      	             ByosFormulario.refrescar();
	                      }
	                   }                      
	  	               Notification.show("Registro Desincorporado o Inactivo",Notification.TYPE_TRAY_NOTIFICATION);
	                }else{
	                   Notification.show("El Registro No se Pudo Desincorporar",Notification.TYPE_TRAY_NOTIFICATION);	
	                }				    
				}
				catch(Exception e){
					this.procesoLogException(e);
				}
			  }	
			  public String procesoValidacion(){
				  return "";
			  }
		   };
		   procesoGuardarPassword.procesoEjecucion();                       
    }
    
	public void procesoListarMedidas(){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
		   tblUnidadMedida TblMedidaListar = new tblUnidadMedida();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigomedida",   String.class, "Codigo",  "", new LikeFilter("codigomedida",""));
		   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.Buscar(new tblUnidadMedida()), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblUnidadMedida)AL.get(0)).getCodigomedida();
	                           procesoNuevo();
	                           ((tblUnidadMedida)ByosFormulario.Clase).setCodigomedida(xCodigo);
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
        String xProximoCodigo=ByosSql.getProximoCodigo("Select max(codigomedida) From tblunidadmedida Where abs(codigomedida)>0",3);
        ((tblUnidadMedida)ByosFormulario.Clase).setCodigomedida(xProximoCodigo);
        ByosFormulario.refrescar(); 
    }	
}
