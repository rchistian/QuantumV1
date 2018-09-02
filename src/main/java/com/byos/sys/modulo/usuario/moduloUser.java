package com.byos.sys.modulo.usuario;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import com.byos.sys.ui.ByosMenu.*;
import com.byos.sys.app.ByosApp;
import com.byos.sys.imagenes.ByosImagenes;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.ui.ByosBoton.ByosBoton;
import com.byos.sys.ui.ByosCampo.ByosCampo;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableCombo;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.byos.sys.ui.ByosForm.ByosForm;
import com.byos.sys.ui.ByosMenu.ByosMenu;
import com.vaadin.data.Container.Filter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.byos.sys.filters.ByosFilter;
import com.byos.sys.filters.LikeFilter;

public class moduloUser extends VerticalLayout {	
	public ByosForm formUser;
	final String[] camposVisibles = new String[]{"usuLogin","usuPassword","usuStatus"};
    final String[] camposDescripcion = new String[]{"Login","Password","Status"};
    final String[] camposTipo = new String[]{"TextField","PasswordField","TextField"};
 
    public ByosMenu menu = new ByosMenu(); 
    final ByosBoton btoNuevo = new ByosBoton(menu.MenuPrincipal,0,"Nuevo",null);
    final ByosBoton btoGuardar = new ByosBoton(menu.MenuPrincipal,1,"Guardar",null);
    
	tblUsuarios u = new tblUsuarios();
	
	public moduloUser(){

	final ByosCampo txtConfirmacion = new ByosCampo();
	txtConfirmacion.setTipoCampo("PasswordField");
    formUser = new ByosForm(u,camposVisibles,camposDescripcion,camposTipo);
    formUser.addComponent(menu,0);
    txtConfirmacion.lblNombreCampo.setCaption("Confirmación"); 
    formUser.addComponent(txtConfirmacion, 4);
    this.setStyleName("backColorBeige");
    this.setWidth("100%");
    this.setHeight("100%");
    this.setSpacing(false);
    this.addComponent(formUser);
    
    final ByosCampo tcbLogin = formUser.getCampo("usuLogin");
    tcbLogin.btoBoton1.setVisible(true);
    tcbLogin.btoBoton1.setIcon(ByosImagenes.icon[3]);
    
    final ByosCampo tcbPassword = formUser.getCampo("usuPassword");
    final ByosCampo tcbStatus = formUser.getCampo("usuStatus");
    tcbStatus.btoBoton1.setVisible(true);
    tcbStatus.btoBoton1.setIcon(ByosImagenes.icon[0]);
    tcbStatus.txtTexto.setEnabled(false);
    
    btoNuevo.addClickListener(new Button.ClickListener(){
    	@Override
    	public void buttonClick(ClickEvent event){
    		ProcesoClass procesoNuevoUsuario = new ProcesoClass(Proceso.USUARIOS_NUEVO){
    			public void procesoDefinicion(){
    				formUser.Clase = new tblUsuarios();
    				formUser.refrescar();
    				txtConfirmacion.txtTexto.setValue("");
    			}
    		};
    	
    		procesoNuevoUsuario.procesoEjecucion();
    		btoNuevo.setEnabled(true);
    	}
    });
    
    
    
    //Buscar Usuario
    tcbLogin.btoBoton1.addClickListener(new Button.ClickListener(){
    	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
	    
		ProcesoClass procesoShowUser = new ProcesoClass(Proceso.USUARIOS_BUSCAR){
			
			ByosDatagridFiltrableTextbox dwb; 
		    
			@Override
			public void procesoDefinicion(){
				try{
					dwb = new ByosDatagridFiltrableTextbox(true,440,200);
					ArrayList usuArray = u.getArrayUsuarios();
				    String[] columnasVisibles = new String[]{"usuLogin","usuStatus"};
				    String[] columnasCabecera = new String[]{"Login","Status"};
				    int[] filtrables = new int[]{0,1};
				    
				    
				    ByosFilter[] tipoFiltro = new ByosFilter[]{new LikeFilter(columnasVisibles[0],""),null};
					Class[] columnasTipo = new Class[]{String.class,String.class};   
				    //dwb.getDatagrid().initDatagridByos(usuArray, u, columnasVisibles,tipoFiltro,columnasCabecera,columnasTipo,false);
				    dwb.addPropertyChangeListener(this);
				}
					catch(Exception e){
						e.printStackTrace();
					}
			}
			
			@Override
			public void propertyChange(PropertyChangeEvent evt){
				if (evt.getNewValue().equals("datagridProcesar")){
					ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
					formUser.setArrayClase(AL);
					txtConfirmacion.txtTexto.setValue("");
					dwb.cerrarWindow();
				}
			}
			
		};
		procesoShowUser.procesoEjecucion();
		tcbLogin.btoBoton1.setEnabled(true);
	}
	
	  
  });
    
    //Crear usuario
    btoGuardar.addClickListener(new Button.ClickListener(){
    	@Override
    	public void buttonClick(ClickEvent event) {
    		ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.USUARIOS_CREAR){
    			
    			public void procesoDefinicion(){
    				try{
 
    				int i = u.guardarUsuario(u);
    				if (i==1){
    					Notification.show("Usuario Guardado");
    					formUser.Clase = new tblUsuarios();
    					formUser.refrescar();
    					txtConfirmacion.txtPassword.setValue("");
    				}
    				}
    				catch(Exception e){
    					e.printStackTrace();
    				}
    			}	
    			
    			
    			public String procesoValidacion(){
    				String s="";
    				boolean seguir = false;
    				try{
    				//revisar si login existe	
    				tblUsuarios tempUser = new tblUsuarios();
    				tempUser = tempUser.getUsuario((String)formUser.getCampo("usuLogin").txtTexto.getValue());
    				
    				//Usuario existe
    				if (!tempUser.getusuLogin().equals("")){
    					ByosApp BA = (ByosApp)ByosApp.getCurrent();
    					
    					if (!tempUser.getusuLogin().equals(BA.getUsuario()) && !BA.getUsuario().equals("byos")){
    						s = "Usuario ya existe";	
    					}
    					
    				}
    				}
    				catch(Exception e){
    					e.printStackTrace();
    				}
    				if (formUser.getCampo("usuPassword").txtPassword.getValue().equals(txtConfirmacion.txtPassword.getValue())){
    				}else{
    					s = "Password y Confirmacion no son iguales";	
    				}
    				
    				if (!formUser.getCampo("usuPassword").txtPassword.getValue().equals(formUser.getCampo("usuLogin").txtTexto.getValue())){
    				}else{
    					s = "Login y Password no pueden ser iguales";	
    				}
    				
    				if (formUser.getCampo("usuPassword").txtPassword.getValue().equals("")){
    					s = "Ingrese Password";
    				}
    				
    				if (formUser.getCampo("usuLogin").txtTexto.getValue().equals("")){
    					s = "Ingrese Login";
    				}
    				return s;
    				
    			}
    		};
    		
    		procesoGuardarPassword.procesoEjecucion();	
    		btoGuardar.setEnabled(true);
    	}
    	
    });
    
    
    tcbStatus.btoBoton1.addClickListener(new Button.ClickListener(){
    	@Override
    	public void buttonClick(ClickEvent event){
    		ProcesoClass ActivarDesactivar = new ProcesoClass(Proceso.USUARIOS_ACTIVAR_DESACTIVAR){
    			@Override
    			public void procesoDefinicion(){
    				try{
    				if (tcbStatus.txtTexto.getValue().equals("Inactivo")){
    					tblUsuarios u = new tblUsuarios();
    				
    			    
    			        String r = u.activarUsuario((String)tcbLogin.txtTexto.getValue());
    					Notification.show(r);
    				}
    				
    				if (tcbStatus.txtTexto.getValue().equals("Activo")){
        				tblUsuarios u = new tblUsuarios();
        				
        				String r = u.desactivarUsuario((String)tcbLogin.txtTexto.getValue());
        				Notification.show(r);
        				}
    				
    				}
    				catch(Exception e){
    					this.procesoLogException(e);
    				}
    				}
    			
    			public String procesoValidacion(){
    				String s = "";
    				tblUsuarios u = (tblUsuarios)formUser.Clase;
    				if (u.getusuLogin().equals("")){
    					s = "Seleccione usuario";
    				}
    				return s;
    			};
    				
    				
    			};
    			ActivarDesactivar.procesoEjecucion();
    			
    		}
    	
    });
    
    
    
	}	
	
	
}
