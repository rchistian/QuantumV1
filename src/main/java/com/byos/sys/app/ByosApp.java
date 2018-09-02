package com.byos.sys.app;

import java.beans.PropertyChangeEvent;

import com.byos.sys.modulo.usuario.tblUsuarios;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.ui.ByosAccordion.ByosAccordion;
import com.byos.sys.ui.ByosLoginFormInit.ByosLoginFormInit;
import com.byos.sys.ui.ByosMainMenu.ByosTree;
import com.byos.sys.ui.ByosTabSheet.ByosTabSheet;
import com.byos.sys.ui.ByosWindows.*;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;


/**
 * Main UI class
 */
@SuppressWarnings("serial")
@Theme("vaadinbyos7theme")

public class ByosApp extends UI {
	private String usuario;
	private ByosTabSheet tabControl = new ByosTabSheet();
	private ByosTree MT;
	private ByosAccordion BA;
	final ByosWindow BW = new ByosWindow();
	
	@Override
	protected void init(VaadinRequest request) {
		
		initApp();
	}

	
	public void initApp(){
		initLayout();
		//initMainMenu();
		usuario="";
		setContent(BW);
		
		
		final ProcesoClass procesoAplicacionLogin = new ProcesoClass(Proceso.APLICACION_LOGIN){
			@Override
			public void procesoDefinicion(){
				try{
					initMainMenu();
				}
				
				catch(Exception e){
					e.printStackTrace();
				}
			}
			};
			
			
		
	final ProcesoClass procesoAplicationInit = new ProcesoClass(Proceso.APLICACION_INIT){
		ByosLoginFormInit loginInit = new ByosLoginFormInit();
		
		@Override
			public void procesoDefinicion(){
				tblUsuarios u = new tblUsuarios();
						
				try{
				//Revisar si existe usuario byos en base de datos si no existe abrir ventana de creacion de usuario
				u = u.getUsuario("byos");
				if (u.getusuLogin().equals("")){
				loginInit.addPropertyChangeListener(this);
				getCurrent().addWindow(loginInit);
				}
				else{
					System.out.println("Ejecutando Proceso Login");
				procesoAplicacionLogin.procesoEjecucion();	
				}
				}
				catch(Exception e){
					//this.procesoLogException(e);
				e.printStackTrace();
				}
			}
			
			public void propertyChange(PropertyChangeEvent evt){
				//Ventana de creacion de usuario validad nuevo password y confirmacion y envia mensaje de usuarioByos
				//Guardar usuario y password byos en db
				try{
				if (evt.getPropertyName().equals("usuarioByos")){
					
					String usuario = (String)evt.getOldValue();
					String pass = (String)evt.getNewValue();
					
					tblUsuarios u = new tblUsuarios();
					u.setusuLogin(usuario);
					u.setusuPassword(pass);
					int i = u.guardarUsuario(u);
					if (i==1){	
						setUsuario(usuario);
						Notification.show("Usuario Guardado");	
						getCurrent().removeWindow(loginInit);
						procesoAplicacionLogin.procesoEjecucion();
					}
					}
				}
				catch(Exception e){
					this.procesoLogException(e);
				}
					
					
				}
			};
		
		procesoAplicationInit.procesoEjecucion();
	}
	
	
	public void setUsuario(String usuario){
		this.usuario=usuario;
	}
	
	public String getUsuario(){
		return usuario;
	}
	
	public ByosWindow getWindowByos(){
		return this.BW;
	}

	public ByosTabSheet getTabControl(){
		return this.tabControl;
	}

	public ByosTree getByosTree(){
		return this.MT;
	}
	
	public void setByosTree(ByosTree BT){
		this.MT=BT;
	}
	
	public void initMainMenu(){
		if (this.MT==null){
		this.MT = new ByosTree();
		}
		BW.izquierda.addComponent(MT);
	}

	private void initLayout() {
	    
		//HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
	    //WB.addComponent(splitPanel);
		//BW.setWidth("400px");
		//BW.setHeight("200px");
		//moduloUser MU = new moduloUser();
		//moduloPerfil MP = new moduloPerfil();
		
		//WB.derecha.addComponent(MP);
		
		BW.derecha.addComponent(tabControl);
		BW.setVisible(true);
		
		
	   
	}

	
	
	
}