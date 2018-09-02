package com.modulo.sustanciascontrolada;

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

public class moduloSustanciasControladas extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    ByosForm ByosFormulario = new ByosForm();
    ByosVerticalLayout Main = new ByosVerticalLayout("Mantenimiento de SustanciasControladas","100%");
    
    public tblSustanciasControladas ClaseSustanciasControladas;

    public ByosMenu menu = new ByosMenu();
    
    String DatosVisibles[] = {
                       "codigocontrol", 
                       "descripcion",
                       "exigirrecipe",
                       "gradoseguridad"
                    };

    String DatosTitulos[] = {
                       "Codigo", 
                       "Descripcion",
                       "Exigir Recipe",
                       "Grado Seguridad"                       
                    };
   
    

    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");
    
    
    
    public moduloSustanciasControladas() {
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");
    	ByosFormulario.setWidth("100%");
    	ByosFormulario.addComponent(menu);
        ClaseSustanciasControladas = new tblSustanciasControladas(); 
        ByosFormulario.setClase(ClaseSustanciasControladas, DatosVisibles, DatosTitulos, null);
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
         
         ByosFormulario.getCampo("codigocontrol").btoBoton1.setBoton(3, "Listar","");
         ByosFormulario.getCampo("codigocontrol").btoBoton1.setVisible(true);
         ByosFormulario.getCampo("codigocontrol").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListarMedidas();       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
         ByosFormulario.getCampo("codigocontrol").btoBoton2.setVisible(true);
         ByosFormulario.getCampo("codigocontrol").btoBoton2.setBoton(18, "Auto Codigo","");
         ByosFormulario.getCampo("codigocontrol").btoBoton2.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     ProximoCodigo();                    
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
        
         //ByosFormulario.setTipoCampo("gradoseguridad", ByosCampo.CAMPO_NUMBERFIELD);
         ByosFormulario.getCampo("gradoseguridad").setNumerico(true);
         
     	 ByosFormulario.setTipoCampo("exigirrecipe", "ComboBox");             
     	 ByosFormulario.getCampo("exigirrecipe").CboxItem.setContainerDataSource(ByosContenedores.getContainerSiNo());           
    }

    
    public void asignacion(){
    	try{
			((tblSustanciasControladas)ByosFormulario.Clase).buscarCodigo(((tblSustanciasControladas)ByosFormulario.Clase).getCodigocontrol());	
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
		ByosFormulario.setArrayClase(((tblSustanciasControladas)ByosFormulario.Clase).Buscar(((tblSustanciasControladas)ByosFormulario.Clase)));  
        if (ByosFormulario.ArrayClase == null || ByosFormulario.ArrayClase.size()==0){
            ((tblSustanciasControladas)ByosFormulario.Clase).limpiar();
            ByosFormulario.refrescar();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
        ByosFormulario.refrescar();
    }
    
    
    public void procesoNuevo(){
        ((tblSustanciasControladas)ByosFormulario.Clase).limpiar();
        ByosFormulario.refrescar();
        ByosFormulario.setArrayClase(null);
        asignacion();
        
    }    

    public void procesoGuardar(){
      if(ByosFormulario.DatosRequeridos(DatosVisibles)){	
    	boolean Existe=false;
    	try{
			Existe=((tblSustanciasControladas)ByosFormulario.Clase).existeCodigo(((tblSustanciasControladas)ByosFormulario.Clase).getCodigocontrol());
		}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	
    	if(Existe){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.MARCA_MODIFICAR){			
			  public void procesoDefinicion(){
				try{
					String Resultado=((tblSustanciasControladas)ByosFormulario.Clase).guardar(((tblSustanciasControladas)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	 	      	             ((tblSustanciasControladas) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion)).setSustanciasControladas(((tblSustanciasControladas)ByosFormulario.Clase));
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
 					String Resultado=((tblSustanciasControladas)ByosFormulario.Clase).guardar(((tblSustanciasControladas)ByosFormulario.Clase));
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
					//String Resultado=((tblSustanciasControladas)Clase).desincorporar(null);
					String Resultado=((tblSustanciasControladas)ByosFormulario.Clase).eliminar(((tblSustanciasControladas)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_ELIMINADO)){
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	                    	 
	 	      	             ByosFormulario.ArrayClase.remove(ByosFormulario.Posicion);
	 	      	             ByosFormulario.Posicion--;
	 	      	             if(ByosFormulario.Posicion>=0){
	 	      	               ((tblSustanciasControladas)ByosFormulario.Clase).setSustanciasControladas((tblSustanciasControladas) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion));
	 	      	             }else{
	 	      	               procesoNuevo(); 
	 	      	             }
	 	      	             ByosFormulario.refrescar();
	                      }
	                   }                      
	  	               Notification.show("SustanciasControladas Eliminada",Notification.TYPE_TRAY_NOTIFICATION);
                    }else{
                       Notification.show("Esta SustanciasControladas No se Pudo Eliminar",Notification.TYPE_TRAY_NOTIFICATION);	
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
		   tblSustanciasControladas TblMedidaListar = new tblSustanciasControladas();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigocontrol",   String.class, "Codigo",  "", new LikeFilter("codigocontrol",""));
		   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.Buscar(new tblSustanciasControladas()), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblSustanciasControladas)AL.get(0)).getCodigocontrol();
	                           procesoNuevo();
	                           ((tblSustanciasControladas)ByosFormulario.Clase).setCodigocontrol(xCodigo);
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
        String xProximoCodigo=ByosSql.getProximoCodigo("Select max(codigocontrol) From tblsustanciascontroladas Where abs(codigocontrol)>0",3);
        ((tblSustanciasControladas)ByosFormulario.Clase).setCodigocontrol(xProximoCodigo);
        ByosFormulario.refrescar(); 
    }    
    

}
