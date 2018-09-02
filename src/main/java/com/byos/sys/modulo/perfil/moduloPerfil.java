package com.byos.sys.modulo.perfil;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;

import com.byos.sys.imagenes.ByosImagenes;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.ui.ByosBoton.ByosBoton;
import com.byos.sys.ui.ByosDatagrid.ByosDatagrid;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableCombo;
import com.byos.sys.ui.ByosForm.ByosForm;


import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class moduloPerfil extends VerticalLayout {
	//Byos Form
	public ByosForm formPerfil;
	final String[] camposVisibles = new String[]{"perfilCodigo","perfilDesc"};
    final String[] camposDescripcion = new String[]{"Código","Descripción"};
    final String[] camposTipo = new String[]{"TextField","TextField"};
 
   
    //Byos Menu
    public CssLayout menu = new CssLayout(); 
    
    final ByosBoton btoNuevo = new ByosBoton(0,"Nuevo",null);
    final ByosBoton btoGuardar = new ByosBoton(1,"Buscar",null);
    final ByosBoton btoEliminar = new ByosBoton(2,"Eliminar",null);
    final ByosBoton btoClonar = new ByosBoton(4,"Clonar",null);
    
    //Componentes adicionales
    ByosDatagrid datagrid = new ByosDatagrid();
    final HorizontalLayout hgrid = new HorizontalLayout();
    final ByosBoton bgridAgregar = new ByosBoton(0,"Agregar",null);
    final ByosBoton bgridEliminar = new ByosBoton(2,"Eliminar",null);
    
    //Clase Principal
    tblPerfil per = new tblPerfil();
  
	public moduloPerfil(){
		
		menu.addComponent(btoNuevo);
		menu.addComponent(btoGuardar);
		menu.addComponent(btoEliminar);
		menu.addComponent(btoClonar);
		
		
		formPerfil = new ByosForm(per,camposVisibles,camposDescripcion,camposTipo);
		
		try{
		
		initComponentes();
		initComponentesAdicionales();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		formPerfil.addComponent(hgrid,4);
		this.addComponent(formPerfil);
	}
	
	
	public void initComponentes(){
		formPerfil.addComponent(menu,0);
		formPerfil.getCampo("perfilCodigo").txtTexto.setEnabled(false);
		formPerfil.getCampo("perfilCodigo").btoBoton1.setIcon(ByosImagenes.icon[3]);
		formPerfil.getCampo("perfilCodigo").btoBoton1.setVisible(true);
		
		formPerfil.getCampo("perfilCodigo").btoBoton1.addClickListener(new Button.ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				
				ProcesoClass procesoBuscarPerfil = new ProcesoClass(Proceso.PERFIL_BUSCAR){
					ByosDatagridFiltrableCombo dwb;
					
					public void procesoDefinicion(){
						try{
							dwb = new ByosDatagridFiltrableCombo(true,520,200);
							tblPerfil p = new tblPerfil();
							ArrayList pd = p.getPerfiles();
							String[] columnasVisibles = new String[]{"perfilCodigo","perfilDesc"};
						    String[] columnasCabecera = new String[]{"Código","Descripción"};
							Class[] columnasTipo = new Class[]{Integer.class,String.class};   
						    dwb.getDatagrid().initDatagridByos(pd, p, columnasVisibles,columnasCabecera,columnasTipo,false);
						    dwb.addPropertyChangeListener(this);
						    
						    
						    dwb.botProcesar.addClickListener(new Button.ClickListener(){
								@Override
								public void buttonClick(ClickEvent event) {
									try{
										ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
										formPerfil.setArrayClase(AL);
										tblPerfil per = (tblPerfil)formPerfil.Clase;
										ArrayList ALPer= new ArrayList();
										tblPerfilProceso pp = new tblPerfilProceso();
										ALPer = new tblPerfil().getPerfilProcesosAsignados(per.getPerfilCodigo());
										datagrid.refrescar(ALPer);
										dwb.cerrarWindow();
									}
									catch(Exception e){
										//this.procesoLogException(e);
									}		
								}
						    });
						    
						    
						}
							catch(Exception e){
								this.procesoLogException(e);
							}
							formPerfil.getCampo("perfilCodigo").btoBoton1.setEnabled(true);
				}
					
					public void propertyChange(PropertyChangeEvent evt){
						if (evt.getPropertyName().equals("datagridProcesar")){
							
						}
					}
				};
				
				procesoBuscarPerfil.procesoEjecucion();
				}
			
		});
		
		
		btoNuevo.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
				formPerfil.Clase = new tblPerfil();
				formPerfil.refrescar();
				datagrid.beanContainer.removeAllItems();
				btoNuevo.setEnabled(true);
			}});
					
		btoGuardar.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
				ProcesoClass procesoGuardarPerfil = new ProcesoClass(Proceso.PERFIL_GUARDAR){
	    			
	    			public void procesoDefinicion(){
	    				try{
	    					
	    				tblPerfil per = new tblPerfil();
	    				per = (tblPerfil)formPerfil.Clase;
	    				
	    				ArrayList<tblPerfilProceso> AL = datagrid.getFilasAll();
	    				
	    				int i = per.guardarPerfil(per,AL);
	    				if (i>0){
	    					
	    					Notification.show("Perfil Guardado");
	    					formPerfil.Clase = new tblPerfil();
	    					formPerfil.refrescar();
	    					datagrid.beanContainer.removeAllItems();
	    					//datagrid.txtTexto.setValue("");
	    				}
	    				}
	    				catch(Exception e){
	    					this.procesoLogException(e);
	    				}
	    			}	
	    			
	    			
	    			public String procesoValidacion(){
	    				String s="";
	    				boolean seguir = false;
	    				try{
	    					tblPerfil p = (tblPerfil)formPerfil.Clase;
	    					
	    					if (p.getPerfilDesc().equals("")){
	    						s = "Ingrese descripción de perfil";
	    					}
	    					
	    				}
	    				catch(Exception e){
	    					this.procesoLogException(e);
	    				}
	    				return s;
	    			}
	    			};
	    		
	    		procesoGuardarPerfil.procesoEjecucion();	
	    		btoGuardar.setEnabled(true);
	    	}
			
		});
		
		btoEliminar.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
				ProcesoClass procesoEliminarPerfil = new ProcesoClass(Proceso.PERFIL_ELIMINAR){
			    public void procesoDefinicion(){
			    try{
			    	tblPerfil p = (tblPerfil)formPerfil.Clase;
			    	
			    	int i = p.eliminarPerfil(p);
			    	if (i>0){
			    	formPerfil.Clase = new tblPerfil();
					formPerfil.refrescar();
					datagrid.beanContainer.removeAllItems();
					Notification.show("Perfil eliminado");
					}
			    }
			    catch(Exception e){
			    	this.procesoLogException(e);
			    }
			    } 
				};
				procesoEliminarPerfil.procesoEjecucion();
				btoEliminar.setEnabled(true);
			    
			}});
		
		
		btoClonar.addClickListener(new Button.ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				
				ProcesoClass procesoClonarPerfil = new ProcesoClass(Proceso.PERFIL_CLONAR){
					ByosDatagridFiltrableCombo dwb;
					
					public void procesoDefinicion(){
						try{
							dwb = new ByosDatagridFiltrableCombo(true,520,200);
							tblPerfil p = new tblPerfil();
							ArrayList pd = p.getPerfiles();
							String[] columnasVisibles = new String[]{"perfilCodigo","perfilDesc"};
						    String[] columnasCabecera = new String[]{"Código","Descripción"};
							Class[] columnasTipo = new Class[]{Integer.class,String.class};   
						    dwb.getDatagrid().initDatagridByos(pd, p, columnasVisibles,columnasCabecera,columnasTipo,false);
						    dwb.addPropertyChangeListener(this);
						}
							catch(Exception e){
								this.procesoLogException(e);
							}
						btoClonar.setEnabled(true);
				}
					
					public void propertyChange(PropertyChangeEvent evt){
						if (evt.getPropertyName().equals("datagridProcesar")){
							try{
							formPerfil.Clase = new tblPerfil();
							formPerfil.refrescar();
							datagrid.beanContainer.removeAllItems();
							ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
							tblPerfil per = (tblPerfil)AL.get(0);
							ArrayList ALPer= new ArrayList();
							tblPerfilProceso pp = new tblPerfilProceso();
							ALPer = new tblPerfil().getPerfilProcesosAsignados(per.getPerfilCodigo());
							datagrid.refrescar(ALPer);
							dwb.cerrarWindow();
							}
							catch(Exception e){
								this.procesoLogException(e);
							}
						}
					}
				};
				
				procesoClonarPerfil.procesoEjecucion();
				btoClonar.setEnabled(true);
				}
			
		});
		
		
	}
	
	public void initComponentesAdicionales(){
		hgrid.addComponent(datagrid,0);
		VerticalLayout vBotones = new VerticalLayout();
		vBotones.addComponent(bgridAgregar,0);
		vBotones.addComponent(bgridEliminar,1);
		hgrid.addComponent(vBotones,1);
		hgrid.setSpacing(true);
		tblPerfilProceso perPro = new tblPerfilProceso();
		
		ArrayList ALPer= new ArrayList();
		
		String[] columnasVisibles = new String[]{"procesoCodigo"};
	    Class[] columnasTipo = new Class[]{String.class};
	    String[] columnasCabecera = new String[]{"Proceso"};
		datagrid.initDatagridByos(ALPer, perPro,columnasVisibles,columnasCabecera,columnasTipo,true);
		datagrid.setWidth(255,Unit.PIXELS);
		datagrid.setHeight(200,Unit.PIXELS);
		
		bgridAgregar.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
				
				ProcesoClass procesoGuardarPerfil = new ProcesoClass(Proceso.PERFILPROCESO_BUSCAR){
				ByosDatagridFiltrableCombo dbp;
				public void procesoDefinicion(){
					try{
					dbp = new ByosDatagridFiltrableCombo(true,520,200);
					tblPerfil p = (tblPerfil)formPerfil.Clase;
					tblPerfilProceso pp = new tblPerfilProceso();
					
					ArrayList pa = datagrid.getFilasAll();
					ArrayList pd = p.getPerfilProcesosDisponibles(pa);
					String[] columnasVisibles = new String[]{"procesoCodigo"};
				    String[] columnasCabecera = new String[]{"Proceso"};
					Class[] columnasTipo = new Class[]{String.class};   
				    dbp.getDatagrid().initDatagridByos(pd, pp, columnasVisibles,columnasCabecera,columnasTipo,true);
				    dbp.addPropertyChangeListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					}
				
				public void propertyChange(PropertyChangeEvent evt){
					if (evt.getPropertyName().equals("datagridProcesar")){
						ArrayList AL = dbp.getDatagrid().getFilasSeleccionadas();
						Iterator<tblPerfilProceso> ite = AL.iterator();
						
						while (ite.hasNext()){
							tblPerfilProceso per = ite.next();
							datagrid.addItem(per);
						}
						dbp.cerrarWindow();
					}
				}
				
				
				};
				
				procesoGuardarPerfil.procesoEjecucion();
				bgridAgregar.setEnabled(true);
			}});
		
		
		bgridEliminar.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
				ProcesoClass procesoEliminarPerfilProceso = new ProcesoClass(Proceso.PERFILPROCESO_ELIMINAR){
					
					public void procesoDefinicion(){
					ArrayList AL = datagrid.getFilasSeleccionadas();
					Iterator ite = AL.iterator();
					
					while (ite.hasNext()){
						Object o = ite.next();
						datagrid.beanContainer.removeItem(o);
					}
					}
				};
				procesoEliminarPerfilProceso.procesoEjecucion();
				bgridEliminar.setEnabled(true);
			}});
		
		
	}
	
	public int hashCode(){
		return 200;
	}
	
}
