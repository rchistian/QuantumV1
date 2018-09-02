package com.modulo.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.byos.sys.util.utilString;
import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.usuarios.moduloUsuariosDetalle;
import com.modulo.usuarios.tblUsuarios;
import com.modulo.main.TabMenu;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class moduloUsuarios  extends VerticalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public Window subwindow;
    
    public TabMenu TabSistema = new TabMenu();
    private VerticalLayout layout = new VerticalLayout();
    
    private VerticalLayout layoutLeftTab01_01 = new VerticalLayout();
    private VerticalLayout layoutCenterTab01_01 = new VerticalLayout();
    private VerticalLayout layoutRightTab01_01 = new VerticalLayout();
    
    private HorizontalLayout layout01 = new HorizontalLayout();    
     
    ByosForm ByosFormularioTab01_01 = new ByosForm();
    ByosForm ByosFormularioTab01_02 = new ByosForm();
    ByosForm ByosFormularioTab01_03 = new ByosForm();
    
    ByosVerticalLayout MainTab01_01 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab01_02 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab01_03 = new ByosVerticalLayout("","100%");
        
    public tblUsuarios ClaseUsuarios;
    moduloUsuariosDetalle ModuloUsuariosDetalleTab01;


    public ByosMenu menu = new ByosMenu();
   
    
    /* Inicio Variables Tab 01 Ficha */

    
    
    /* Datos Izquierda */
    String DatosVisiblesTab01_01[] = {
       "ID",
       "Codigo",
       "Descripcion",
    };

    String DatosTitulosTab01_01[] = { 
       "ID",
       "Codigo",
       "Descripcion",
    };

    /* Datos Centro */
    String DatosVisiblesTab01_02[] = {
       "Login",
       "Clave",
       "Nombres"
    };

    String DatosTitulosTab01_02[] = {
       "Login",
       "Clave",
       "Nombres"
    };
    
    /* Datos Derecha */
    String DatosVisiblesTab01_03[] = {
       "Grupo",
       "Tipo"
    };

    String DatosTitulosTab01_03[] = {
       "Grupo",
       "Tipo"
    };  
    /* Fin Variables Tab 01 Ficha */
    

   
    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");                 //0
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");               //3
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");           //2
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");             //1
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior",""); //14
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");   //15
    ByosBoton BtoSalir = new ByosBoton(menu.MenuPrincipal, 110,"Salir","");               //115
    
    
    
    public moduloUsuarios() {
    	subwindow = new Window("Datos del Usuario");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);

        ClaseUsuarios = new tblUsuarios(); 
    	    	
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	
     	
    	menu.setWidth("100%");
    	menu.setHeight("20%");
    	menu.setSpacing(false);
    	menu.setMargin(false);
    	    	
    	ByosFormularioTab01_01.setWidth("100%");
    	ByosFormularioTab01_02.setWidth("100%");
    	ByosFormularioTab01_03.setWidth("100%");

    	
        ByosFormularioTab01_01.setTipoLayout("Vertical");
        ByosFormularioTab01_02.setTipoLayout("Vertical");
        ByosFormularioTab01_03.setTipoLayout("Vertical");

        
        ByosFormularioTab01_01.setMostrarEstado(false);
        ByosFormularioTab01_02.setMostrarEstado(false);
        ByosFormularioTab01_03.setMostrarEstado(false);

        ByosFormularioTab01_01.setLabelTexto(true);
        ByosFormularioTab01_02.setLabelTexto(true);
        ByosFormularioTab01_03.setLabelTexto(true);

        ByosFormularioTab01_01.setClase(ClaseUsuarios, DatosVisiblesTab01_01, DatosTitulosTab01_01, null);
        ByosFormularioTab01_02.setClase(ClaseUsuarios, DatosVisiblesTab01_02, DatosTitulosTab01_02, null);
        ByosFormularioTab01_03.setClase(ClaseUsuarios, DatosVisiblesTab01_03, DatosTitulosTab01_03, null);

        
        initComponentes();       
        MainTab01_01.setMargin(false);
        MainTab01_01.setWidth("100%");
        MainTab01_02.setMargin(false);
        MainTab01_02.setWidth("100%");        
        MainTab01_03.setMargin(false);
        MainTab01_03.setWidth("100%");
        

        ModuloUsuariosDetalleTab01 = new moduloUsuariosDetalle(ClaseUsuarios,true,"200px");
        ModuloUsuariosDetalleTab01.setWidth("100%");
        
        
        MainTab01_01.Contenido.addComponent(ByosFormularioTab01_01); 
        MainTab01_02.Contenido.addComponent(ByosFormularioTab01_02); 
        MainTab01_03.Contenido.addComponent(ByosFormularioTab01_03); 
        
        TabSistema.setWidth("100%");
        TabSistema.setHeight("90%");
        TabSistema.t.setStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        
        /* Tab 01  Ficha*/
        layoutCenterTab01_01.setSpacing(false);
        layoutCenterTab01_01.setMargin(false);

        layoutLeftTab01_01.setWidth("100%");
        layoutCenterTab01_01.setWidth("100%");
        layoutRightTab01_01.setWidth("100%");

        layout01.setSizeFull();
        layout01.addComponent(layoutLeftTab01_01);
        layout01.addComponent(layoutCenterTab01_01);
        layout01.addComponent(layoutRightTab01_01);
        
        layoutLeftTab01_01.addComponent(MainTab01_01);
        layoutCenterTab01_01.addComponent(ModuloUsuariosDetalleTab01);
        layoutCenterTab01_01.addComponent(MainTab01_02);
        layoutRightTab01_01.addComponent(MainTab01_03);
        Tab Tab01 = TabSistema.t.addTab(layout01, "Ficha", ByosImagenes.icon[84]);
        TabSistema.vectorTab.add(Tab01);
        Tab01.setClosable(false);
        
        layout.setSpacing(true);
        layout.addComponent(menu);
        layout.addComponent(TabSistema);
        
        ByosFormularioTab01_02.setTipoCampo("Clave", "PasswordField"); 
        
        addComponent(layout);        
    }
    
    @SuppressWarnings({ "deprecation", "serial" })
	public void initComponentes() {
    	
    	BtoSalir.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                closeWindows();
             }
          });
    	
    	
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
         
         ByosFormularioTab01_02.getCampo("Login").btoBoton1.setBoton(3, "Listar","");
         ByosFormularioTab01_02.getCampo("Login").btoBoton1.setVisible(true);
         ByosFormularioTab01_02.getCampo("Login").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                	 //new ByosBusqueda();
                     procesoListar(false);       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
    }
 
    public void asignacion(){
    	try{
    		AsignarRegistro();
			((tblUsuarios)ByosFormularioTab01_02.Clase).buscarCodigo(((tblUsuarios)ByosFormularioTab01_02.Clase).getLogin());
			((tblUsuarios)ByosFormularioTab01_01.Clase).setTblUsuarios(((tblUsuarios)ByosFormularioTab01_02.Clase));
			((tblUsuarios)ByosFormularioTab01_03.Clase).setTblUsuarios(((tblUsuarios)ByosFormularioTab01_02.Clase));
			
			ModuloUsuariosDetalleTab01.setTblUsuarios((tblUsuarios)ByosFormularioTab01_01.Clase);

    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void procesoRefrescarTbl(){
    	ByosFormularioTab01_01.refrescar();
    	ByosFormularioTab01_02.refrescar();
    	ByosFormularioTab01_03.refrescar();

    }
    
    public void procesoLimpiarTbl() {
        ((tblUsuarios)ByosFormularioTab01_01.Clase).limpiar();
        ((tblUsuarios)ByosFormularioTab01_02.Clase).limpiar();
        ((tblUsuarios)ByosFormularioTab01_03.Clase).limpiar();
    }
    
    public void procesoBorrarArrayTbl(){
        ByosFormularioTab01_01.setArrayClase(null);
        ByosFormularioTab01_02.setArrayClase(null);
        ByosFormularioTab01_03.setArrayClase(null);
    }
    
    public void procesoTblProximo(){
    	ByosFormularioTab01_01.procesoProximo();
    	ByosFormularioTab01_02.procesoProximo();
    	ByosFormularioTab01_03.procesoProximo();

    	asignacion();
    	procesoRefrescarTbl();
    }
    
    public void procesoTblAnterior(){
    	ByosFormularioTab01_01.procesoAnterior();
    	ByosFormularioTab01_02.procesoAnterior();
    	ByosFormularioTab01_03.procesoAnterior();

    	asignacion();
    	procesoRefrescarTbl();
    }    
      
    
	public void procesoBuscar() throws Exception {
		AsignarRegistro();
		ByosFormularioTab01_02.setArrayClase(ClaseUsuarios.BuscarArray(ClaseUsuarios)); 		
		ByosFormularioTab01_01.setArrayClase(ByosFormularioTab01_02.getArrayClase());  
		ByosFormularioTab01_03.setArrayClase(ByosFormularioTab01_02.getArrayClase());  
				
		if (ByosFormularioTab01_01.ArrayClase == null || ByosFormularioTab01_01.ArrayClase.size()==0){
            procesoLimpiarTbl();
            procesoRefrescarTbl();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
		procesoRefrescarTbl();
    }
    
    
    public void procesoNuevo(){
        procesoLimpiarTbl();
        procesoRefrescarTbl();
        procesoBorrarArrayTbl();
        asignacion();
    }    

    public void procesoGuardar(){
      if(ByosFormularioTab01_01.DatosRequeridos(DatosVisiblesTab01_01)){	
    	boolean Existe=false;
    	try{
			Existe=((tblUsuarios)ByosFormularioTab01_01.Clase).existeCodigo(((tblUsuarios)ByosFormularioTab01_01.Clase).getLogin());
		}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	System.out.println("");
    	if(Existe){		   
    		try{
    			AsignarRegistro();
			    String Resultado=((tblUsuarios)ByosFormularioTab01_01.Clase).guardar(ClaseUsuarios);
			    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	           if(ByosFormularioTab01_01.ArrayClase!=null){		
	                  if(ByosFormularioTab01_01.Posicion>=0){
	 	      	         ((tblUsuarios) ByosFormularioTab01_02.ArrayClase.get(ByosFormularioTab01_02.Posicion)).setTblUsuarios(((tblUsuarios)ByosFormularioTab01_01.Clase));
	 	      	         ((tblUsuarios) ByosFormularioTab01_01.ArrayClase.get(ByosFormularioTab01_01.Posicion)).setTblUsuarios(((tblUsuarios)ByosFormularioTab01_02.Clase));
	 	      	         ((tblUsuarios) ByosFormularioTab01_03.ArrayClase.get(ByosFormularioTab01_03.Posicion)).setTblUsuarios(((tblUsuarios)ByosFormularioTab01_03.Clase));
	 	      	         
	 	      	         procesoRefrescarTbl();
	                   }
	               }                      
	  	           Notification.show("Registro Modificada", Notification.TYPE_TRAY_NOTIFICATION);
	            }else{
		 		   Notification.show("El Registro no se pudo Modificar",Notification.TYPE_TRAY_NOTIFICATION);	
		 		}					    
		    
		   }catch(Exception e){
					e.printStackTrace();
		   }
			  	
    	}else{
 		   try{
 			    AsignarRegistro();
 			    String Resultado=((tblUsuarios)ByosFormularioTab01_01.Clase).guardar(ClaseUsuarios);
 				if(Resultado.equals(utilString.SQL_INSERTADO)){
 				   Notification.show("Registro Guardado",Notification.TYPE_TRAY_NOTIFICATION);
 				}else{
 	 			   Notification.show("El Registro no se pudo Guardar",Notification.TYPE_TRAY_NOTIFICATION);	
 	 		    }	
 		   }catch(Exception e){
 				e.printStackTrace();
 		   }
 	    }	  		
      }
    }
    
    public void procesoEliminar(){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.MARCA_ELIMINAR){			
			  public void procesoDefinicion(){
				try{
					String Resultado=((tblUsuarios)ByosFormularioTab01_01.Clase).eliminar(((tblUsuarios)ByosFormularioTab01_01.Clase));
                    if(Resultado.equals(utilString.SQL_ELIMINADO)){
	  	               if(ByosFormularioTab01_01.ArrayClase!=null){		
	                      if(ByosFormularioTab01_01.Posicion>=0){
	                    	 
	 	      	             ByosFormularioTab01_01.ArrayClase.remove(ByosFormularioTab01_01.Posicion);
	 	      	             ByosFormularioTab01_02.ArrayClase.remove(ByosFormularioTab01_02.Posicion);
	 	      	             ByosFormularioTab01_03.ArrayClase.remove(ByosFormularioTab01_03.Posicion);
	 	      	             
	 	      	             ByosFormularioTab01_01.Posicion--;
	 	      	             ByosFormularioTab01_02.Posicion--;
	 	      	             ByosFormularioTab01_03.Posicion--;

	 	      	             if(ByosFormularioTab01_01.Posicion>=0){
	 	      	               ((tblUsuarios)ByosFormularioTab01_01.Clase).setTblUsuarios((tblUsuarios) ByosFormularioTab01_01.ArrayClase.get(ByosFormularioTab01_01.Posicion));
	 	      	               ((tblUsuarios)ByosFormularioTab01_02.Clase).setTblUsuarios((tblUsuarios) ByosFormularioTab01_02.ArrayClase.get(ByosFormularioTab01_02.Posicion));
	 	      	               ((tblUsuarios)ByosFormularioTab01_03.Clase).setTblUsuarios((tblUsuarios) ByosFormularioTab01_03.ArrayClase.get(ByosFormularioTab01_03.Posicion));
	 	      	             }else{
	 	      	               procesoNuevo(); 
	 	      	             }
                             procesoRefrescarTbl();
	                      }
	                   }                      
	  	               Notification.show("Usuarios Eliminada",Notification.TYPE_TRAY_NOTIFICATION);
                    }else{
                       Notification.show("Esta Usuarios No se Pudo Eliminar",Notification.TYPE_TRAY_NOTIFICATION);	
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
    
	public void procesoListar(final boolean AbrirWindows){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,800,640);    
		   tblUsuarios TblListar = new tblUsuarios();
		   ByosColumna[] ByosColumnas = new ByosColumna[3];

		   ByosColumnas[0] = new ByosColumna("login", String.class, "Login", "", new LikeFilter("Login",""));
		   ByosColumnas[1] = new ByosColumna("nombres", String.class, "Nombres", "", new LikeFilter("Nombre",""));
		   ByosColumnas[2] = new ByosColumna("descripcion", String.class, "Descripcion", "", new LikeFilter("Descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblListar.BuscarArray(new tblUsuarios()), TblListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblUsuarios)AL.get(0)).getLogin();
	                           procesoNuevo();
	                           ((tblUsuarios)ByosFormularioTab01_01.Clase).setLogin(xCodigo);
	                           ((tblUsuarios)ByosFormularioTab01_02.Clase).setLogin(xCodigo);
	                           ((tblUsuarios)ByosFormularioTab01_03.Clase).setLogin(xCodigo);
	                           procesoBuscar();
                               procesoRefrescarTbl();
	                           dwb.cerrarWindow();
	                           if(AbrirWindows) {
	                        	   openWindows();
	                           }
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
        String xProximoCodigo=ByosSql.getProximoCodigo("Select max(Login) From tblUsuarios Where abs(Login)>0",2);
        ((tblUsuarios)ByosFormularioTab01_01.Clase).setLogin(xProximoCodigo);
        ((tblUsuarios)ByosFormularioTab01_02.Clase).setLogin(xProximoCodigo);
        ((tblUsuarios)ByosFormularioTab01_03.Clase).setLogin(xProximoCodigo);
        
        procesoRefrescarTbl();
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
    
    private void btoOP07_Click() {
    	closeWindows();
    }
    
    private void AsignarRegistro() {
    	tblUsuarios ClaseUsuarios01 = new tblUsuarios();
    	
    	ClaseUsuarios.setTblUsuarios((tblUsuarios) ByosFormularioTab01_01.Clase); 
    	ClaseUsuarios01.setTblUsuarios(ClaseUsuarios);
    	
    	ClaseUsuarios.setCodigoEmpresa(ClaseUsuarios01.getCodigoEmpresa());
    	ClaseUsuarios.setCodigo(ClaseUsuarios01.getCodigo()); 
    	ClaseUsuarios.setID(ClaseUsuarios01.getID()); 
    	ClaseUsuarios.setNombres(ClaseUsuarios01.getNombres());
    	ClaseUsuarios.setLogin(ClaseUsuarios01.getLogin());
    	ClaseUsuarios.setUsuario(ClaseUsuarios01.getUsuario());
    	ClaseUsuarios.setFecha(ClaseUsuarios01.getFecha());
    	ClaseUsuarios.setEstadoRegistro(ClaseUsuarios01.getEstadoRegistro());
    	ClaseUsuarios.setEstado(ClaseUsuarios01.getEstado());
    	ClaseUsuarios.setGrupo(ClaseUsuarios01.getGrupo());
    	ClaseUsuarios.setClave(ClaseUsuarios01.getClave());
    	ClaseUsuarios.setDescripcion(ClaseUsuarios01.getDescripcion());
    	ClaseUsuarios.setTipo(ClaseUsuarios01.getTipo());
    	
    }

}