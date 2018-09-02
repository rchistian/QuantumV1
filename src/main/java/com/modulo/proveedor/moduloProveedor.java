package com.modulo.proveedor;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilString;
import com.modulo.main.TabMenu;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class moduloProveedor extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    private String Tabla = "TblProveedor";
    public String Resultado; 
    public String GlobalCodigoEmpresa = "0001";
    
    private VerticalLayout layoutLeftTab01_01 = new VerticalLayout();
    private VerticalLayout layoutCenterTab01_01 = new VerticalLayout();
    private VerticalLayout layoutRightTab01_01 = new VerticalLayout();
    
    public TabMenu TabSistema = new TabMenu();
    private VerticalLayout layout = new VerticalLayout();
    
    //ByosForm ByosFormulario = new ByosForm();
    
    private HorizontalLayout layout01 = new HorizontalLayout();    
    
    ByosForm ByosFormularioTab01_01 = new ByosForm();
    ByosForm ByosFormularioTab01_02 = new ByosForm();
    ByosForm ByosFormularioTab01_03 = new ByosForm();
    
    ByosVerticalLayout MainTab01_01 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab01_02 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab01_03 = new ByosVerticalLayout("","100%");
    
    ByosVerticalLayout Main = new ByosVerticalLayout("Mantenimiento de Proveedor","100%");
        
    public tblProveedor ClaseProveedor;
    moduloProveedorDetalle ModuloProveedorDetalleTab01;

    public ByosMenu menu = new ByosMenu();
    
    
    /* Inicio Variables Tab 01 Ficha */

    
    
    /* Datos Izquierda */
    String DatosVisiblesTab01_01[] = {
	   "direccion", 
	   "telefonos",
	   "fax", 
	   "correo", 
	   "codigoruc", 
	   "codigoactividad", 
	   "patente",
	   "tipoproveedor", 
	   "retencioniva", 
	   "retencionalcaldia",
	   "fechainicio" 
    };

    String DatosTitulosTab01_01[] = { 
	   "Direccion", 
	   "Telefonos",
	   "Fax", 
	   "Correo", 
	   "Codigo Ruc", 
	   "Codigo Actividad", 
	   "Patente",
	   "Tipo Proveedor", 
	   "Retencion iva", 
	   "Retencion Alcaldia",
	   "Fecha Inicio" 
    };

    /* Datos Centro */
    String DatosVisiblesTab01_02[] = {
       "codigoproveedor", 
       "descripcion",
	   "dni" 
    };

    String DatosTitulosTab01_02[] = {
       "Codigo", 
       "Descripcion",
	   "DNI/CIF" 
    };
    
    /* Datos Derecha */
    String DatosVisiblesTab01_03[] = {
	   "diasentrega", 
	   "diascredito", 
	   "representante",
	   "beneficiario", 
	   "detalle01", 
	   "detalle02", 
	   "detalle03", 
	   "detalle04",
	   "detalle05", 
	   "detalle06"                                      
    };

    String DatosTitulosTab01_03[] = {
       "Dias Entrega", 
       "Dias Credito", 
       "Tepresentante",
       "Beneficiario", 
       "Detalle 01", 
       "Detalle 02", 
       "Detalle 03", 
       "Detalle 04",
       "Detalle 05", 
       "Detalle 06"                                      
    };  
    /* Fin Variables Tab 01 Ficha */
    
    String DatosRequeridos[] = {
            "codigoproveedor", 
            "descripcion",
			"dni",  
    };
    


    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");
    ByosBoton BtoSalir = new ByosBoton(menu.MenuPrincipal, 110,"Salir","");               //115
    
    
    public Window subwindow;
    
    public moduloProveedor() {
    	ClaseProveedor = new tblProveedor(); 
    	subwindow = new Window("Datos del Proveedor");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	//setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");    	
    	
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
    	
        ByosFormularioTab01_01.setClase(ClaseProveedor, DatosVisiblesTab01_01, DatosTitulosTab01_01, null);
        ByosFormularioTab01_02.setClase(ClaseProveedor, DatosVisiblesTab01_02, DatosTitulosTab01_02, null);
        ByosFormularioTab01_03.setClase(ClaseProveedor, DatosVisiblesTab01_03, DatosTitulosTab01_03, null);  	
    	
    	
        MainTab01_01.setMargin(false);
        MainTab01_01.setWidth("100%");
        MainTab01_02.setMargin(false);
        MainTab01_02.setWidth("100%");        
        MainTab01_03.setMargin(false);
        MainTab01_03.setWidth("100%");
        

        initComponentes(); 
        
        ModuloProveedorDetalleTab01 = new moduloProveedorDetalle(ClaseProveedor,true,"200px");
        ModuloProveedorDetalleTab01.setWidth("100%");
        
        MainTab01_01.Contenido.addComponent(ByosFormularioTab01_01); 
        MainTab01_02.Contenido.addComponent(ByosFormularioTab01_02); 
        MainTab01_03.Contenido.addComponent(ByosFormularioTab01_03); 
        
        TabSistema.setWidth("100%");
        TabSistema.setHeight("90%");
        TabSistema.t.setStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        
        
        for(int f=0;f<ByosFormularioTab01_02.Campos.length;f++){
        	ByosFormularioTab01_02.Campos[f].setTipoLetra(ByosCampo.TIPO_TEXTO_MAYUSCULA);
        }
        //ByosFormularioTab01_02.getCampo("descripcion").txtTexto.setWidth("300px");
        //ByosFormularioTab01_02.getCampo("direccion").txtTexto.setWidth("300px");
        
        /*
        ByosFormulario.getCampo("descripcion").setTipoLetra(ByosCampo.TIPO_TEXTO_MAYUSCULA);
        ByosFormulario.getCampo("dni").setTipoLetra(ByosCampo.TIPO_TEXTO_MAYUSCULA);
        ByosFormulario.getCampo("direccion").setTipoLetra(ByosCampo.TIPO_TEXTO_MAYUSCULA);
        */
        
        
        ByosFormularioTab01_01.getCampo("fechainicio").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormularioTab01_01.getCampo("retencionalcaldia").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormularioTab01_01.getCampo("retencioniva").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
      	
    	
    	ByosFormularioTab01_01.getCampo("retencionalcaldia").setDecimales(3);
    	ByosFormularioTab01_01.getCampo("retencioniva").setDecimales(3);
        
        ByosFormularioTab01_01.setTipoCampo("tipoproveedor", "ComboBox");             
        ByosFormularioTab01_01.getCampo("tipoproveedor").CboxItem.setContainerDataSource(ByosContenedores.getTipoProveedor());  
        
        ByosFormularioTab01_01.refrescar();
        ByosFormularioTab01_02.refrescar();
        ByosFormularioTab01_03.refrescar();
        
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
        layoutCenterTab01_01.addComponent(ModuloProveedorDetalleTab01);
        layoutCenterTab01_01.addComponent(MainTab01_02);
        layoutRightTab01_01.addComponent(MainTab01_03);
        Tab Tab01 = TabSistema.t.addTab(layout01, "Ficha Proveedor", ByosImagenes.icon[84]);
        TabSistema.vectorTab.add(Tab01);
        Tab01.setClosable(false);
        
        layout.setSpacing(true);
        layout.addComponent(menu);
        layout.addComponent(TabSistema);
               
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
         
         ByosFormularioTab01_02.getCampo("codigoproveedor").btoBoton1.setBoton(3, "Listar","");
         ByosFormularioTab01_02.getCampo("codigoproveedor").btoBoton1.setVisible(true);
         ByosFormularioTab01_02.getCampo("codigoproveedor").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListar(false);       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
         ByosFormularioTab01_02.getCampo("codigoproveedor").btoBoton2.setVisible(true);
         ByosFormularioTab01_02.getCampo("codigoproveedor").btoBoton2.setBoton(18, "Auto Codigo","");
         ByosFormularioTab01_02.getCampo("codigoproveedor").btoBoton2.addClickListener(new Button.ClickListener() {
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
			((tblProveedor)ByosFormularioTab01_02.Clase).buscarCodigo(((tblProveedor)ByosFormularioTab01_02.Clase).getCodigoproveedor());	
			((tblProveedor)ByosFormularioTab01_01.Clase).setProveedor(((tblProveedor)ByosFormularioTab01_02.Clase));
			((tblProveedor)ByosFormularioTab01_03.Clase).setProveedor(((tblProveedor)ByosFormularioTab01_02.Clase));
			ModuloProveedorDetalleTab01.setTblProveedor((tblProveedor)ByosFormularioTab01_01.Clase);
			
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
        ((tblProveedor)ByosFormularioTab01_01.Clase).limpiar();
        ((tblProveedor)ByosFormularioTab01_02.Clase).limpiar();
        ((tblProveedor)ByosFormularioTab01_03.Clase).limpiar();
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
		ByosFormularioTab01_02.setArrayClase(ClaseProveedor.BuscarArray(ClaseProveedor)); 		
		ByosFormularioTab01_01.setArrayClase(ByosFormularioTab01_02.getArrayClase());  
		ByosFormularioTab01_03.setArrayClase(ByosFormularioTab01_02.getArrayClase());  
        if (ByosFormularioTab01_01.ArrayClase == null || ByosFormularioTab01_01.ArrayClase.size()==0){
            ((tblProveedor)ByosFormularioTab01_01.Clase).limpiar();
            ByosFormularioTab01_01.refrescar();
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

	public void procesoGuardar() {
		if (ByosFormularioTab01_02.DatosRequeridos(DatosRequeridos)) {
			boolean Existe = false;
			try {
				Existe = ((tblProveedor) ByosFormularioTab01_01.Clase).existeCodigo(((tblProveedor) ByosFormularioTab01_01.Clase).getCodigoproveedor());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Existe) {
				try {
					String Resultado = ((tblProveedor) ByosFormularioTab01_01.Clase).guardar(((tblProveedor) ByosFormularioTab01_01.Clase));
					if (Resultado.equals(utilString.SQL_MODIFICADO)) {
						if (ByosFormularioTab01_01.ArrayClase != null) {
							if (ByosFormularioTab01_01.Posicion >= 0) {
			 	      	        ((tblProveedor) ByosFormularioTab01_01.ArrayClase.get(ByosFormularioTab01_01.Posicion)).setProveedor(((tblProveedor)ByosFormularioTab01_01.Clase));
			 	      	        ((tblProveedor) ByosFormularioTab01_02.ArrayClase.get(ByosFormularioTab01_02.Posicion)).setProveedor(((tblProveedor)ByosFormularioTab01_02.Clase));
			 	      	        ((tblProveedor) ByosFormularioTab01_03.ArrayClase.get(ByosFormularioTab01_03.Posicion)).setProveedor(((tblProveedor)ByosFormularioTab01_03.Clase));
								
								procesoRefrescarTbl();
							}
						}
						Notification.show("Registro Modificada",
								Notification.TYPE_TRAY_NOTIFICATION);
					} else {
						Notification.show("El Registro no se pudo Modificar",
								Notification.TYPE_TRAY_NOTIFICATION);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					String Resultado = ((tblProveedor) ByosFormularioTab01_01.Clase)
							.guardar(((tblProveedor) ByosFormularioTab01_01.Clase));
					if (Resultado.equals(utilString.SQL_INSERTADO)) {
						Notification.show("Registro Guardado",
								Notification.TYPE_TRAY_NOTIFICATION);
					} else {
						Notification.show("El Registro no se pudo Guardar",
								Notification.TYPE_TRAY_NOTIFICATION);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
    
	public void procesoEliminar() {
		try {
			// String Resultado=((tblProveedor)Clase).desincorporar(null);
			String Resultado = ((tblProveedor) ByosFormularioTab01_01.Clase)
					.eliminar(((tblProveedor) ByosFormularioTab01_01.Clase));
			if (Resultado.equals(utilString.SQL_ELIMINADO)) {
				if (ByosFormularioTab01_01.ArrayClase != null) {
					if (ByosFormularioTab01_01.Posicion >= 0) {

						ByosFormularioTab01_01.ArrayClase
								.remove(ByosFormularioTab01_01.Posicion);
						ByosFormularioTab01_01.Posicion--;
						if (ByosFormularioTab01_01.Posicion >= 0) {
							((tblProveedor) ByosFormularioTab01_01.Clase).setProveedor((tblProveedor) ByosFormularioTab01_01.ArrayClase.get(ByosFormularioTab01_01.Posicion));
							((tblProveedor) ByosFormularioTab01_02.Clase).setProveedor((tblProveedor) ByosFormularioTab01_02.ArrayClase.get(ByosFormularioTab01_02.Posicion));
							((tblProveedor) ByosFormularioTab01_03.Clase).setProveedor((tblProveedor) ByosFormularioTab01_03.ArrayClase.get(ByosFormularioTab01_03.Posicion));
						} else {
							procesoNuevo();
						}
						procesoRefrescarTbl();
					}
				}
				Notification.show("Proveedor Eliminada",
						Notification.TYPE_TRAY_NOTIFICATION);
			} else {
				Notification.show("Esta Proveedor No se Pudo Eliminar",
						Notification.TYPE_TRAY_NOTIFICATION);
			}
		} catch (Exception e) {
		}
	}
    
	public void procesoListar(final boolean AbrirWindows){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,800,640);    
		   tblProveedor TblMedidaListar = new tblProveedor();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigoproveedor",   String.class, "Codigo",  "", new LikeFilter("codigoproveedor",""));
		   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.BuscarArray(new tblProveedor()), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblProveedor)AL.get(0)).getCodigoproveedor();
	                           procesoNuevo();
	                           ((tblProveedor)ByosFormularioTab01_01.Clase).setCodigoproveedor(xCodigo);
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
	
    void ProximoCodigo() {
        String xProximoCodigo;
		try {
			xProximoCodigo = ByosSql.getProximoCodigo("Select max(codigoproveedor) From tblProveedor Where abs(codigoproveedor)>0",4);
	        ((tblProveedor)ByosFormularioTab01_02.Clase).setCodigoproveedor(xProximoCodigo);
	        procesoRefrescarTbl();
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    public static boolean EstadoForm = false;
    public void openWindows(){
        if(subwindow.getParent() != null) {
           Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
        }else {
           EstadoForm = true;
     	   UI.getCurrent().addWindow(subwindow);                  
        }        
    }
 
    public void closeWindows(){
    	EstadoForm = false;
        subwindow.close();
    }

    private void AsignarRegistro() {
    	tblProveedor ClaseProveedor01 = new tblProveedor();
    	
    	ClaseProveedor.setProveedor((tblProveedor) ByosFormularioTab01_01.Clase); 
    	ClaseProveedor01.setProveedor(ClaseProveedor);
    	
    	ClaseProveedor.setCodigoempresa(ClaseProveedor01.getCodigoempresa());
    	ClaseProveedor.setDireccion(ClaseProveedor01.getDireccion());
    	ClaseProveedor.setTelefonos(ClaseProveedor01.getTelefonos());
    	ClaseProveedor.setFax(ClaseProveedor01.getFax());
    	ClaseProveedor.setCorreo(ClaseProveedor01.getCorreo());
    	ClaseProveedor.setCodigoruc(ClaseProveedor01.getCodigoruc());
    	ClaseProveedor.setCodigoactividad(ClaseProveedor01.getCodigoactividad());
    	ClaseProveedor.setPatente(ClaseProveedor01.getPatente());
    	ClaseProveedor.setTipoproveedor(ClaseProveedor01.getTipoproveedor());
    	ClaseProveedor.setRetencioniva(ClaseProveedor01.getRetencioniva());
    	ClaseProveedor.setRetencionalcaldia(ClaseProveedor01.getRetencionalcaldia());
    	ClaseProveedor.setFechainicio(ClaseProveedor01.getFechainicio());
    	
    	ClaseProveedor.setProveedor((tblProveedor) ByosFormularioTab01_02.Clase); 
    	ClaseProveedor01.setProveedor(ClaseProveedor);
    	   	
    	ClaseProveedor.setCodigoproveedor(ClaseProveedor01.getCodigoproveedor());
    	ClaseProveedor.setDescripcion(ClaseProveedor01.getDescripcion());
    	ClaseProveedor.setdni(ClaseProveedor01.getdni());
    	
    	ClaseProveedor.setProveedor((tblProveedor) ByosFormularioTab01_03.Clase); 
    	ClaseProveedor01.setProveedor(ClaseProveedor);
    	
    	ClaseProveedor.setDiasentrega(ClaseProveedor01.getDiasentrega());
    	ClaseProveedor.setDiascredito(ClaseProveedor01.getDiascredito());
    	ClaseProveedor.setRepresentante(ClaseProveedor01.getRepresentante());
    	ClaseProveedor.setBeneficiario(ClaseProveedor01.getBeneficiario());
    	ClaseProveedor.setDetalle01(ClaseProveedor01.getDetalle01());
    	ClaseProveedor.setDetalle02(ClaseProveedor01.getDetalle02());
    	ClaseProveedor.setDetalle03(ClaseProveedor01.getDetalle03());
    	ClaseProveedor.setDetalle04(ClaseProveedor01.getDetalle04());
    	ClaseProveedor.setDetalle05(ClaseProveedor01.getDetalle05());
    	ClaseProveedor.setDetalle06(ClaseProveedor01.getDetalle06());
    	
    }

}