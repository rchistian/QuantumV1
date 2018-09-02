package com.modulo.departamento;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
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

public class moduloDepartamento extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    ByosForm ByosFormulario = new ByosForm();
    ByosVerticalLayout Main = new ByosVerticalLayout("Mantenimiento de Departamento","100%");
    
    public tblDepartamento ClaseDepartamento;

    public ByosMenu menu = new ByosMenu();
    
    String DatosVisibles[] = {
                       "codigodepartamento", 
                       "descripcion",
                       "responsable"
                    };

    String DatosTitulos[] = {
                       "Codigo", 
                       "Descripcion",
                       "Responsable"
                    };
   
    String DatosRequeridos[] = {
            "codigodepartamento", 
            "descripcion"
    };  

    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");
    
    
    
    public moduloDepartamento() {
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");
    	ByosFormulario.setWidth("100%");
    	ByosFormulario.addComponent(menu);
        ClaseDepartamento = new tblDepartamento(); 
        ByosFormulario.setClase(ClaseDepartamento, DatosVisibles, DatosTitulos, null);
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
         
         ByosFormulario.getCampo("codigodepartamento").btoBoton1.setBoton(3, "Listar","");
         ByosFormulario.getCampo("codigodepartamento").btoBoton1.setVisible(true);
         ByosFormulario.getCampo("codigodepartamento").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListarMedidas();       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
         ByosFormulario.getCampo("codigodepartamento").btoBoton2.setVisible(true);
         ByosFormulario.getCampo("codigodepartamento").btoBoton2.setBoton(18, "Auto Codigo","");
         ByosFormulario.getCampo("codigodepartamento").btoBoton2.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     ProximoCodigo();                    
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });      	
    }

    
    public void asignacion(){
    	try{
			((tblDepartamento)ByosFormulario.Clase).buscarCodigo(((tblDepartamento)ByosFormulario.Clase).getCodigodepartamento());	
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
		ByosFormulario.setArrayClase(((tblDepartamento)ByosFormulario.Clase).Buscar(((tblDepartamento)ByosFormulario.Clase)));  
        if (ByosFormulario.ArrayClase == null || ByosFormulario.ArrayClase.size()==0){
            ((tblDepartamento)ByosFormulario.Clase).limpiar();
            ByosFormulario.refrescar();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
        ByosFormulario.refrescar();
    }
    
    
    public void procesoNuevo(){
        ((tblDepartamento)ByosFormulario.Clase).limpiar();
        ByosFormulario.refrescar();
        ByosFormulario.setArrayClase(null);
        asignacion();
        
    }    

    public void procesoGuardar(){
      if(ByosFormulario.DatosRequeridos(DatosRequeridos)){
    	boolean Existe=false;
    	try{
			Existe=((tblDepartamento)ByosFormulario.Clase).existeCodigo(((tblDepartamento)ByosFormulario.Clase).getCodigodepartamento());
		}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	
    	if(Existe){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.MARCA_MODIFICAR){			
			  public void procesoDefinicion(){
				try{
					String Resultado=((tblDepartamento)ByosFormulario.Clase).guardar(((tblDepartamento)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	 	      	             ((tblDepartamento) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion)).setDepartamento(((tblDepartamento)ByosFormulario.Clase));
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
					this.procesoLogException(e);
				}
			  }	
			  public String procesoValidacion(){
				  return "";
			  }
		   };
		   procesoGuardarPassword.procesoEjecucion();
    	}else{
 		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.MARCA_CREAR){			
 			  public void procesoDefinicion(){
 				try{
 					String Resultado=((tblDepartamento)ByosFormulario.Clase).guardar(((tblDepartamento)ByosFormulario.Clase));
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
    
    public void procesoEliminar(){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.MARCA_ELIMINAR){			
			  public void procesoDefinicion(){
				try{
					//String Resultado=((tblDepartamento)Clase).desincorporar(null);
					String Resultado=((tblDepartamento)ByosFormulario.Clase).eliminar(((tblDepartamento)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_ELIMINADO)){
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	                    	 
	 	      	             ByosFormulario.ArrayClase.remove(ByosFormulario.Posicion);
	 	      	             ByosFormulario.Posicion--;
	 	      	             if(ByosFormulario.Posicion>=0){
	 	      	               ((tblDepartamento)ByosFormulario.Clase).setDepartamento((tblDepartamento) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion));
	 	      	             }else{
	 	      	               procesoNuevo(); 
	 	      	             }
	 	      	             ByosFormulario.refrescar();
	                      }
	                   }                      
	  	               Notification.show("Departamento Eliminada",Notification.TYPE_TRAY_NOTIFICATION);
                    }else{
                       Notification.show("Esta Departamento No se Pudo Eliminar",Notification.TYPE_TRAY_NOTIFICATION);	
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
		   tblDepartamento TblMedidaListar = new tblDepartamento();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigodepartamento",    String.class, "Codigo",      "", new LikeFilter("codigodepartamento",""));
		   ByosColumnas[1] = new ByosColumna("descripcion",           String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.Buscar(new tblDepartamento()), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblDepartamento)AL.get(0)).getCodigodepartamento();
	                           procesoNuevo();
	                           ((tblDepartamento)ByosFormulario.Clase).setCodigodepartamento(xCodigo);
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
        String xProximoCodigo=ByosSql.getProximoCodigo("Select max(codigodepartamento) From tbldepartamentos Where abs(codigodepartamento)>0",4);
        ((tblDepartamento)ByosFormulario.Clase).setCodigodepartamento(xProximoCodigo);
        ByosFormulario.refrescar(); 
    }    
    

}
