package com.byos.sys.modulo.autorizaciones;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;

import com.byos.sys.imagenes.ByosImagenes;
import com.byos.sys.modulo.perfil.tblPerfil;
import com.byos.sys.modulo.usuario.tblUsuarios;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.procesos.tblProceso;
import com.byos.sys.ui.ByosBoton.ByosBoton;
import com.byos.sys.ui.ByosDatagrid.ByosDatagrid;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableCombo;
import com.byos.sys.ui.ByosForm.ByosForm;
import com.byos.sys.ui.ByosMenu.ByosMenu;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class moduloAutorizaciones extends VerticalLayout {
	    public ByosForm FormAuto;
	    final String[] camposVisibles = new String[]{"usuLogin"};
	    final String[] camposDescripcion = new String[]{"Usuario"};
	    final String[] camposTipo = new String[]{"TextField"};
	 
		public ByosMenu menu = new ByosMenu(); 
	    final ByosBoton btoNuevo = new ByosBoton(menu.MenuPrincipal,0,"Nuevo",null);
	    final ByosBoton btoGuardar = new ByosBoton(menu.MenuPrincipal,1,"Guardar",null);
	    final ByosBoton btoEliminar = new ByosBoton(menu.MenuPrincipal,2,"Eliminar",null);
	    final ByosBoton btoClonar = new ByosBoton(menu.MenuPrincipal,4,"Clonar",null);
	    
	    private HorizontalLayout hgrid = new HorizontalLayout();
	    private ByosDatagrid dataUsuPerfil = new ByosDatagrid();
	    private ByosBoton btoAgUsuPerfil = new ByosBoton(0,"Agregar",null);
	    private ByosBoton btoEliUsuPerfil = new ByosBoton(2,"Eliminar",null);
	    
	    private ByosDatagrid dataUsuProceso = new ByosDatagrid();
	    private ByosBoton btoAgUsuProceso = new ByosBoton(0,"Agregar",null);
	    private ByosBoton btoEliUsuProceso = new ByosBoton(2,"Eliminar",null);
	    
	    private tblAutorizaciones tblAuto = new tblAutorizaciones();
	    
	    public moduloAutorizaciones(){
	    	FormAuto = new ByosForm(tblAuto,camposVisibles,camposDescripcion,camposTipo);
	    	initComponentes();
	    	initComponentesAdicionales();
	    	this.addComponent(FormAuto);
	    }
	    
	    
	    
	    public void initComponentes(){
	    	
	    	FormAuto.addComponent(menu,0);
	    	FormAuto.getCampo("usuLogin").btoBoton1.setIcon(ByosImagenes.icon[3]);
	    	FormAuto.getCampo("usuLogin").btoBoton1.setVisible(true);
	    	FormAuto.getCampo("usuLogin").btoBoton1.addClickListener(new Button.ClickListener(){
	    		
	    		public void buttonClick(ClickEvent e){
	    			ProcesoClass pc = new ProcesoClass(Proceso.USUARIOS_BUSCAR){
	    				ByosDatagridFiltrableCombo dwb; 
	    				
	    				public void procesoDefinicion(){
	    					
	    					try{
	    						dwb = new ByosDatagridFiltrableCombo(true,520,200);
	    						tblUsuarios u = new tblUsuarios();
	    						ArrayList usuArray = u.getArrayUsuarios();
	    					    String[] columnasVisibles = new String[]{"usuLogin"};
	    					    String[] columnasCabecera = new String[]{"Login"};
	    						Class[] columnasTipo = new Class[]{String.class};   
	    					    dwb.getDatagrid().initDatagridByos(usuArray, u, columnasVisibles,columnasCabecera,columnasTipo,false);
	    					    dwb.addPropertyChangeListener(this);
	    					}
	    						catch(Exception e){
	    							e.printStackTrace();
	    						}
	    					
	    				}
	    				
	    				public void propertyChange(PropertyChangeEvent evt){
	    					if (evt.getNewValue().equals("datagridProcesar")){
	    						ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	    						tblUsuarios usuTemp = (tblUsuarios)AL.get(0);
	    						tblAuto.setUsuLogin(usuTemp.getusuLogin());
	    						FormAuto.getCampo("usuLogin").txtTexto.setValue(usuTemp.getusuLogin());
	    						dwb.cerrarWindow();
	    						
	    						ArrayList<tblPerfil> ALPerfilPorUsuario = new ArrayList();
	    						ArrayList<tblProceso> ALProcesoPorUsuario = new ArrayList();
	    						
	    						try{
	    						ALPerfilPorUsuario = new tblUsuarioPerfil().getUsuarioPerfil(usuTemp.getusuLogin());
	    						ALProcesoPorUsuario = new tblUsuarioProceso().getUsuarioProceso(usuTemp.getusuLogin());
	    						dataUsuPerfil.refrescar(ALPerfilPorUsuario);
	    						dataUsuProceso.refrescar(ALProcesoPorUsuario);
	    						
	    						}
	    						catch(Exception e){
	    							this.procesoLogException(e);
	    						}
	    					}
	    				}
	    				
	    			};
	    			pc.procesoEjecucion();
	    		}
	    	}); 
	    	
	    	
	    	btoNuevo.addClickListener(new Button.ClickListener(){
	    		public void buttonClick(ClickEvent e){
	    			FormAuto.getCampo("usuLogin").btoBoton1.setVisible(true);
	    			FormAuto.Clase = new tblAutorizaciones();
	    			FormAuto.refrescar();
	    			dataUsuPerfil.removeAllItems();
	    			dataUsuProceso.removeAllItems();
	    			
	    		}
	    		
	    	});
	    	
	    	btoGuardar.addClickListener(new Button.ClickListener(){

				@Override
				public void buttonClick(ClickEvent event) {
					ProcesoClass procesoAutorizacionGuardar = new ProcesoClass(Proceso.AUTORIZACIONES_GUARDAR){
						
						public void procesoDefinicion(){
							ArrayList<tblPerfil> ALPerfiles = new ArrayList();
							ALPerfiles = dataUsuPerfil.getFilasAll();
							ArrayList<tblProceso> ALProcesos = new ArrayList();
							ALProcesos = dataUsuProceso.getFilasAll();
							
							tblAutorizaciones auto = new tblAutorizaciones();
							try{
							String resultado = auto.autorizacionGuardar(tblAuto.getUsuLogin(), ALPerfiles, ALProcesos);
							Notification.show(resultado);
							}
							catch(Exception e){
								this.procesoLogException(e);
							}
						
						}
						
						public String procesoValidacion(){
							String valido="";
							if (tblAuto.getUsuLogin().equals("")){
								valido = "Seleccione usuario";
							}
							return valido;
						}
						
					};
					procesoAutorizacionGuardar.procesoEjecucion();
					
					
				}
	    		
	    	});
	    	
	    	
	    	
	    	btoEliminar.addClickListener(new Button.ClickListener(){
	    		public void buttonClick(ClickEvent evt){
	    			ProcesoClass pc = new ProcesoClass(Proceso.AUTORIZACIONES_ELIMINAR){
	    				public void procesoDefinicion(){
	    					try{
	    					String r = new tblAutorizaciones().autorizacionEliminar(tblAuto.getUsuLogin());
	    					Notification.show(r);
	    					FormAuto.Clase = new tblAutorizaciones();
	    					FormAuto.refrescar();
	    					dataUsuPerfil.removeAllItems();
	    					dataUsuProceso.removeAllItems();
	    					}
	    					catch(Exception e){
	    						this.procesoLogException(e);
	    					}
	    				}
	    				
	    				public String procesoValidacion(){
	    					String s = "";
	    					if (tblAuto.getUsuLogin().equals("")){
	    						s = "Seleccion usuario";
	    					}
	    					return s;
	    				}
	    			};
	    			pc.procesoEjecucion();
	    			
	    		}
	    	});
	    	
	    	
	    	
	    	
	    	btoClonar.addClickListener(new Button.ClickListener(){
	    		public void buttonClick(ClickEvent e){
	    			ProcesoClass pc = new ProcesoClass(Proceso.AUTORIZACIONES_CLONAR){
	    				ByosDatagridFiltrableCombo dwb;
	    				public void procesoDefinicion(){
	    					try{
	    						dwb = new ByosDatagridFiltrableCombo(true,520,200);
	    						tblUsuarios u = new tblUsuarios();
	    						ArrayList usuArray = u.getArrayUsuarios();
	    					    String[] columnasVisibles = new String[]{"usuLogin"};
	    					    String[] columnasCabecera = new String[]{"Login"};
	    						Class[] columnasTipo = new Class[]{String.class};   
	    					    dwb.getDatagrid().initDatagridByos(usuArray, u, columnasVisibles,columnasCabecera,columnasTipo,false);
	    					    dwb.addPropertyChangeListener(this);
	    					}
	    						catch(Exception e){
	    							this.procesoLogException(e);
	    						}
	    				}
	    				
	    				
	    				public void propertyChange(PropertyChangeEvent evt){
	    					if (evt.getNewValue().equals("datagridProcesar")){
	    						ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	    						tblUsuarios usuTemp = (tblUsuarios)AL.get(0);
	    						dwb.cerrarWindow();
	    						
	    						ArrayList<tblPerfil> ALPerfilPorUsuario = new ArrayList();
	    						ArrayList<tblProceso> ALProcesoPorUsuario = new ArrayList();
	    						
	    						try{
	    						ALPerfilPorUsuario = new tblUsuarioPerfil().getUsuarioPerfil(usuTemp.getusuLogin());
	    						ALProcesoPorUsuario = new tblUsuarioProceso().getUsuarioProceso(usuTemp.getusuLogin());
	    						dataUsuPerfil.refrescar(ALPerfilPorUsuario);
	    						dataUsuProceso.refrescar(ALProcesoPorUsuario);
	    						
	    						}
	    						catch(Exception e){
	    							this.procesoLogException(e);
	    						}
	    					}
	    				}
	    				
	    				
	    			};
	    			pc.procesoEjecucion();
	    			
	    		}
	    	});
	    	}
	    
	    
	    public void initComponentesAdicionales(){
	    	hgrid.addComponent(dataUsuPerfil);
	    	VerticalLayout vBotonesDataUsuPerfil = new VerticalLayout();
	    	vBotonesDataUsuPerfil.addComponent(btoAgUsuPerfil,0);
	    	vBotonesDataUsuPerfil.addComponent(btoEliUsuPerfil,1);
	    	
	    	//hgrid.addComponent(btoAgUsuPerfil);
	    	//hgrid.addComponent(btoEliUsuPerfil);
	    	hgrid.addComponent(vBotonesDataUsuPerfil);
	    	
	    	tblPerfil perfil = new tblPerfil();
			ArrayList ALUsuPer = new ArrayList();
			String[] columnasVisibles = new String[]{"perfilDesc"};
		    Class[] columnasTipo = new Class[]{String.class};
		    String[] columnasCabecera = new String[]{"Perfil"};
			dataUsuPerfil.initDatagridByos(ALUsuPer, perfil,columnasVisibles,columnasCabecera,columnasTipo,true);
			dataUsuPerfil.setWidth(255,Unit.PIXELS);
			dataUsuPerfil.setHeight(200,Unit.PIXELS);
			
			btoAgUsuPerfil.addClickListener(new Button.ClickListener(){
				public void buttonClick(ClickEvent event){
				
					ProcesoClass procesoAgregarPerfil = new ProcesoClass(Proceso.USUARIOSPERFIL_BUSCAR){
						ByosDatagridFiltrableCombo dwb;
						
						public void procesoDefinicion(){
							try{
								dwb = new ByosDatagridFiltrableCombo(true,520,200);
								tblPerfil p = new tblPerfil();
								ArrayList pd = p.getPerfiles();
								String[] columnasVisibles = new String[]{"perfilCodigo","perfilDesc"};
							    String[] columnasCabecera = new String[]{"Código","Descripción"};
								Class[] columnasTipo = new Class[]{Integer.class,String.class};   
							    dwb.getDatagrid().initDatagridByos(pd, p, columnasVisibles,columnasCabecera,columnasTipo,true);
							    dwb.addPropertyChangeListener(this);
							    
							    
							    dwb.botProcesar.addClickListener(new Button.ClickListener(){
									@Override
									public void buttonClick(ClickEvent event) {
										try{
											ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
											dataUsuPerfil.agregarFilas(AL,false);
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
							
					}
						
						public void propertyChange(PropertyChangeEvent evt){
							if (evt.getPropertyName().equals("datagridProcesar")){
								
							}
						}
					};
					
					procesoAgregarPerfil.procesoEjecucion();
					btoAgUsuPerfil.setEnabled(true);
					}
				
			});
			
			
			btoEliUsuPerfil.addClickListener(new Button.ClickListener(){
				public void buttonClick(ClickEvent event){
				
					ProcesoClass procesoEliminarPerfil = new ProcesoClass(Proceso.USUARIOSPERFIL_ELIMINAR){
						
						
						public void procesoDefinicion(){
							try{
								ArrayList AL = dataUsuPerfil.getFilasSeleccionadas();
								Iterator ite = AL.iterator();
								
								while (ite.hasNext()){
									Object o = ite.next();
									dataUsuPerfil.beanContainer.removeItem(o);
								}
								}
							    
								catch(Exception e){
									this.procesoLogException(e);
								}
							
					}
						
					};
					
					procesoEliminarPerfil.procesoEjecucion();
					btoEliUsuPerfil.setEnabled(true);
					}
				
			});
			
			
			hgrid.addComponent(dataUsuProceso);
			VerticalLayout vBotonesDataUsuProceso = new VerticalLayout();
	    	vBotonesDataUsuProceso.addComponent(btoAgUsuProceso,0);
	    	vBotonesDataUsuProceso.addComponent(btoEliUsuProceso,1);
	    	hgrid.addComponent(vBotonesDataUsuProceso);
	    	hgrid.setSpacing(true);
			//hgrid.addComponent(btoAgUsuProceso);
	    	//hgrid.addComponent(btoEliUsuProceso);
	    	
			ArrayList ALUsuPro = new ArrayList();
			Proceso pro = Proceso.APLICACION_INIT;
			tblProceso procesosTemp = new tblProceso();
			String[] columnasVisiblesPro = new String[]{"proceso"};
		    Class[] columnasTipoPro = new Class[]{String.class};
		    String[] columnasCabeceraPro = new String[]{"Proceso"};
		    dataUsuProceso.initDatagridByos(ALUsuPro,procesosTemp,columnasVisiblesPro,columnasCabeceraPro,columnasTipoPro,true);
			dataUsuProceso.setWidth(255,Unit.PIXELS);
			dataUsuProceso.setHeight(200,Unit.PIXELS);
			
			btoAgUsuProceso.addClickListener(new Button.ClickListener(){
				public void buttonClick(ClickEvent event){
					
					
					ProcesoClass procesoAgregarProceso = new ProcesoClass(Proceso.USUARIOSPROCESO_BUSCAR){
						ByosDatagridFiltrableCombo dwb;
						
						public void procesoDefinicion(){
							try{
								dwb = new ByosDatagridFiltrableCombo(true,520,200);
								tblProceso pro = new tblProceso();
								Proceso proTemp = Proceso.APLICACION_INIT;
								ArrayList ALUsuProceso = new ArrayList();
								ALUsuProceso = proTemp.getProcesos();
								String[] columnasVisibles = new String[]{"proceso"};
							    String[] columnasCabecera = new String[]{"Proceso"};
								Class[] columnasTipo = new Class[]{String.class};   
							    dwb.getDatagrid().initDatagridByos(ALUsuProceso, pro, columnasVisibles,columnasCabecera,columnasTipo,true);
							    dwb.addPropertyChangeListener(this);
							    
							    
							    dwb.botProcesar.addClickListener(new Button.ClickListener(){
									@Override
									public void buttonClick(ClickEvent event) {
										try{
											ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
											dataUsuProceso.agregarFilas(AL,false);
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
							
					}
						
					};
					
					procesoAgregarProceso.procesoEjecucion();
					btoAgUsuProceso.setEnabled(true);
					}
				
			});
			
			
			btoEliUsuProceso.addClickListener(new Button.ClickListener(){
				public void buttonClick(ClickEvent event){
				
					ProcesoClass procesoEliminarProceso = new ProcesoClass(Proceso.USUARIOSPROCESO_ELIMINAR){
						
						
						public void procesoDefinicion(){
							try{
								ArrayList AL = dataUsuProceso.getFilasSeleccionadas();
								Iterator ite = AL.iterator();
								
								while (ite.hasNext()){
									Object o = ite.next();
									dataUsuProceso.beanContainer.removeItem(o);
								}
								}
							    
								catch(Exception e){
									this.procesoLogException(e);
								}
							
					}
						
					};
					
					procesoEliminarProceso.procesoEjecucion();
					btoEliUsuProceso.setEnabled(true);
					}
				
			});
			
			
				
	    	FormAuto.addComponent(hgrid);
	    }
	    
	    
}
