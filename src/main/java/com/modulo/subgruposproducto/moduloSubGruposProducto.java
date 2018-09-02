package com.modulo.subgruposproducto;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.gruposproducto.tblGruposProducto;
import com.modulo.subgruposproducto.tblSubGruposProducto;
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

public class moduloSubGruposProducto extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    ByosForm ByosFormulario = new ByosForm();
    ByosVerticalLayout Main = new ByosVerticalLayout("Mantenimiento de SubGrupos Producto","100%");
    
    public tblSubGruposProducto ClaseSubGruposProducto;

    public ByosMenu menu = new ByosMenu();
    
    String DatosVisibles[] = {
    		           "codigogrupo",
                       "codigosubgrupo", 
                       "descripcion" 
                    };

    String DatosTitulos[] = {
    		           "Codigo Grupo",
                       "Codigo SubGrupo", 
                       "Descripcion" 
                    };
   
    

    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");
    
    
    
    public moduloSubGruposProducto() {
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");
    	ByosFormulario.setWidth("100%");
    	ByosFormulario.addComponent(menu);
        ClaseSubGruposProducto = new tblSubGruposProducto(); 
        ByosFormulario.setClase(ClaseSubGruposProducto, DatosVisibles, DatosTitulos, null);
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
         
         ByosFormulario.getCampo("codigogrupo").btoBoton1.setBoton(3, "Listar","");
         ByosFormulario.getCampo("codigogrupo").btoBoton1.setVisible(true);
         ByosFormulario.getCampo("codigogrupo").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListarGrupos();       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });          
         
         ByosFormulario.getCampo("codigosubgrupo").btoBoton1.setBoton(3, "Listar","");
         ByosFormulario.getCampo("codigosubgrupo").btoBoton1.setVisible(true);
         ByosFormulario.getCampo("codigosubgrupo").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListarSubGrupo();       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
         ByosFormulario.getCampo("codigosubgrupo").btoBoton2.setVisible(true);
         ByosFormulario.getCampo("codigosubgrupo").btoBoton2.setBoton(18, "Auto Codigo","");
         ByosFormulario.getCampo("codigosubgrupo").btoBoton2.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     ProximoCodigo();                    
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         ByosFormulario.getCampo("codigogrupo").setSelectLink(true, "select descripcion from tblGruposProductos where codigogrupo=?");
         ByosFormulario.setisLink("codigogrupo", true, "TblGruposProducto", "descripcion");
    }

    
    public void asignacion(){
    	try{
			((tblSubGruposProducto)ByosFormulario.Clase).buscarCodigo(((tblSubGruposProducto)ByosFormulario.Clase).getCodigogrupo(),((tblSubGruposProducto)ByosFormulario.Clase).getCodigosubgrupo());	
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
		ByosFormulario.setArrayClase(((tblSubGruposProducto)ByosFormulario.Clase).Buscar(((tblSubGruposProducto)ByosFormulario.Clase)));  
        if (ByosFormulario.ArrayClase == null || ByosFormulario.ArrayClase.size()==0){
            ((tblSubGruposProducto)ByosFormulario.Clase).limpiar();
            ByosFormulario.refrescar();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
        ByosFormulario.refrescar();
    }
    
    
    public void procesoNuevo(){
        ((tblSubGruposProducto)ByosFormulario.Clase).limpiar();
        ByosFormulario.refrescar();
        //ByosFormulario.getCampo("codigogrupo").lblDescripcion.setValue("");
        ByosFormulario.setArrayClase(null);
        asignacion();
        
    }    

    public void procesoGuardar(){
      if(ByosFormulario.DatosRequeridos(DatosVisibles)){	
    	boolean Existe=false;
    	try{
			Existe=((tblSubGruposProducto)ByosFormulario.Clase).existeCodigo(((tblSubGruposProducto)ByosFormulario.Clase).getCodigogrupo(),((tblSubGruposProducto)ByosFormulario.Clase).getCodigosubgrupo());
		}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	
    	if(Existe){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.MARCA_MODIFICAR){			
			  public void procesoDefinicion(){
				try{
					String Resultado=((tblSubGruposProducto)ByosFormulario.Clase).guardar(((tblSubGruposProducto)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	 	      	             ((tblSubGruposProducto) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion)).setSubGruposProducto(((tblSubGruposProducto)ByosFormulario.Clase));
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
 					String Resultado=((tblSubGruposProducto)ByosFormulario.Clase).guardar(((tblSubGruposProducto)ByosFormulario.Clase));
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
					//String Resultado=((tblSubGruposProducto)Clase).desincorporar(null);
					String Resultado=((tblSubGruposProducto)ByosFormulario.Clase).eliminar(((tblSubGruposProducto)ByosFormulario.Clase));
                    if(Resultado.equals(utilString.SQL_ELIMINADO)){
	  	               if(ByosFormulario.ArrayClase!=null){		
	                      if(ByosFormulario.Posicion>=0){
	                    	 
	 	      	             ByosFormulario.ArrayClase.remove(ByosFormulario.Posicion);
	 	      	             ByosFormulario.Posicion--;
	 	      	             if(ByosFormulario.Posicion>=0){
	 	      	               ((tblSubGruposProducto)ByosFormulario.Clase).setSubGruposProducto((tblSubGruposProducto) ByosFormulario.ArrayClase.get(ByosFormulario.Posicion));
	 	      	             }else{
	 	      	               procesoNuevo(); 
	 	      	             }
	 	      	             ByosFormulario.refrescar();
	                      }
	                   }                      
	  	               Notification.show("SubGruposProducto Eliminada",Notification.TYPE_TRAY_NOTIFICATION);
                    }else{
                       Notification.show("Esta SubGruposProducto No se Pudo Eliminar",Notification.TYPE_TRAY_NOTIFICATION);	
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
    
	public void procesoListarSubGrupo(){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
		   tblSubGruposProducto TblMedidaListar = new tblSubGruposProducto();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigosubgrupo",   String.class, "Codigo",  "", new LikeFilter("codigosubgrupo",""));
		   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	    	   tblSubGruposProducto SubGrupo = new tblSubGruposProducto();
	    	   
	    	   SubGrupo.setCodigogrupo(((tblSubGruposProducto)ByosFormulario.Clase).getCodigogrupo());
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.Buscar(SubGrupo), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblSubGruposProducto)AL.get(0)).getCodigosubgrupo();
	                           procesoNuevo();
	                           ((tblSubGruposProducto)ByosFormulario.Clase).setCodigosubgrupo(xCodigo);
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
	
	public void procesoListarGrupos(){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
		   tblGruposProducto TblMedidaListar = new tblGruposProducto();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigogrupo",   String.class, "Codigo",  "", new LikeFilter("codigogrupo",""));
		   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.Buscar(new tblGruposProducto()), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblGruposProducto)AL.get(0)).getCodigogrupo();
	    					   ((tblSubGruposProducto)ByosFormulario.Clase).setCodigogrupo(xCodigo);
	                           ((tblSubGruposProducto)ByosFormulario.Clase).TblGruposProducto.buscarCodigo(xCodigo);
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
        String xProximoCodigo=ByosSql.getProximoCodigo("Select max(codigosubgrupo) From tblsubgruposproductos Where abs(codigosubgrupo)>0 and Codigogrupo='" + ((tblSubGruposProducto)ByosFormulario.Clase).getCodigogrupo() + "'",3);
        ((tblSubGruposProducto)ByosFormulario.Clase).setCodigosubgrupo(xProximoCodigo);
        ByosFormulario.refrescar(); 
    }    
    

}
