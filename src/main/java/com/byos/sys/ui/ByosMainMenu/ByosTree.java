package com.byos.sys.ui.ByosMainMenu;

import java.io.Serializable;
import java.util.Iterator;

import com.byos.sys.app.ByosApp;
import com.byos.sys.modulo.autorizaciones.moduloAutorizaciones;
import com.byos.sys.modulo.perfil.moduloPerfil;
import com.byos.sys.modulo.usuario.moduloUser;
import com.byos.sys.procesos.Proceso;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.Reindeer;
import com.byos.sys.procesos.*;



public class ByosTree extends Tree implements Serializable,ByosTreeInterface {


	public ByosTree(){
		super();
		this.setImmediate(true);
		this.setStyleName(Reindeer.LABEL_SMALL);
		initComponents();
		initComponentsUsuarios();
		
		this.addItemClickListener(new ItemClickEvent.ItemClickListener(){
		public void itemClick(ItemClickEvent event) {
		
			Proceso p = (Proceso)event.getItemId();
			loadProcesosUsuarios(p);
			loadProcesosAdicionales(p);
		
		}
		});
		
		}	
	
	public void initComponents(){
		
	}
	
	public void initComponentsUsuarios(){
		String modulo = "";
		modulo = "Usuarios";
		
		agregarItem(modulo,Proceso.USUARIOS_ABRIR);
		
		agregarItem(modulo,Proceso.PERFIL_ABRIR);
		
		agregarItem(modulo,Proceso.AUTORIZACIONES_ABRIR);
	}
	
	public void loadProcesosUsuarios(Proceso p){
		ProcesoClass pc = null;
		boolean f = false;
		switch (p){
		
		case USUARIOS_ABRIR:
		
			pc = new ProcesoClass(Proceso.USUARIOS_ABRIR){
				public void procesoDefinicion(){
					moduloUser MU = new moduloUser();
					ByosApp BA = (ByosApp)ByosApp.getCurrent();
					BA.getTabControl().agregarTab(MU,this.getProceso().getDescripcionCorta());
					
				}
			};
		f = true;	
		break;
			
		case PERFIL_ABRIR:
	
		pc = new ProcesoClass(Proceso.PERFIL_ABRIR){
				public void procesoDefinicion(){
					moduloPerfil MP = new moduloPerfil();
					ByosApp BA = (ByosApp)ByosApp.getCurrent();
					BA.getTabControl().agregarTab(MP,this.getProceso().getDescripcionCorta());
				}
			};
		f=true;
		break;
		
		case AUTORIZACIONES_ABRIR:
			
			pc = new ProcesoClass(Proceso.AUTORIZACIONES_ABRIR){
				public void procesoDefinicion(){
					moduloAutorizaciones MA = new moduloAutorizaciones();
					ByosApp BA = (ByosApp)ByosApp.getCurrent();
					BA.getTabControl().agregarTab(MA,this.getProceso().getDescripcionCorta());
				}
			};
		f=true;
		break;	
		}
			
		if (pc != null && f==true){
			ejecutarProceso(pc);
		}
		
		
		
	}
	
	
	public void loadProcesosAdicionales(Proceso p){
		
	}
	
	public void agregarItem(String modulo,Object submodulo){
		this.addItem(modulo);
		this.addItem(submodulo);
		this.setParent(submodulo,modulo);
		this.setChildrenAllowed(submodulo,false);
		this.expandItemsRecursively(modulo);
	}
	
	
	
	public void ejecutarProceso(ProcesoClass p){
		p.procesoEjecucion();
	}
	
	
	
	
	
	
}
