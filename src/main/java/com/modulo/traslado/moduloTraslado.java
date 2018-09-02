package com.modulo.traslado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosMsgBox;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilString;
import com.modulo.deposito.tblDeposito;
import com.modulo.marca.tblMarca;
import com.modulo.producto.tblProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class moduloTraslado extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    public final Table tableListado = new Table();

    
    ByosForm ByosFormulario = new ByosForm();
    public tblTraslado ClaseTraslado;
    
    
    String DatosVisibles[] = {
            "codigotraslado",
            "fechatraslado",
            "depositoorigen",
            "depositodestino"
         };

    String DatosTitulos[] = {
            "Codigo Traslado",
            "Fecha Traslado",
            "Deposito Origen",
            "Deposito Destino"
         };    
    
    ByosVerticalLayout Main = new ByosVerticalLayout("Traslado de Productos","100%");
    public ByosMenu menu = new ByosMenu();    
    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    //ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");    

    public int MaxReg = 1;
    public int Posicion=1;
    
    ByosColumna[] ByosColumnas = new ByosColumna[6];
    
    ByosDatagridFiltrableTextbox ListaAjuste;
    ArrayList <tblTrasladoItem>ArrayTblTasladoItem = new ArrayList <tblTrasladoItem>();
    
    public moduloTraslado(){        
        
        initAddressList(); 
        ListaAjuste.botCancelar.setVisible(false);
        ListaAjuste.botProcesar.setBoton(16, "Agregar", "Agregar");
        ListaAjuste.botProcesar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	               try{
	            	   procesoAddProducto();
	               } catch (Exception e) {
	                   // Ingnored, we'll let the Form handle the errors
	               }
	           }
	        });
        
        BtoGuardar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	               try{
	            	   procesoGuardar();
	               } catch (Exception e) {
	                   // Ingnored, we'll let the Form handle the errors
	               }
	           }
	        });
        
        BtoNuevo.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	               try{
	            	   procesoHabilitar();
	            	   Limpiar();
	               } catch (Exception e) {
	                   // Ingnored, we'll let the Form handle the errors
	               }
	           }
	        });     
        
        BtoBuscar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	               try{
	            	   procesoBuscar();
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
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");
     
        Main.setMargin(false);
        Main.setWidth("100%");
        
    	menu.setWidth("100%");
    	ByosFormulario.setWidth("100%");
    	ByosFormulario.addComponent(menu);
        ClaseTraslado = new tblTraslado(); 
        ByosFormulario.setClase(ClaseTraslado, DatosVisibles, DatosTitulos, null);        
        initComponentes();
        
        Main.Contenido.addComponent(menu);
        Main.Contenido.addComponent(ByosFormulario);
        Main.Contenido.addComponent(ListaAjuste); 
        
        addComponent(Main);

    }
    
    public void initComponentes() {
    	
    	
    	ByosFormulario.getCampo("fechatraslado").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario.getCampo("fechatraslado").FechaHora.setReadOnly(true);
    	ByosFormulario.getCampo("codigotraslado").txtTexto.setReadOnly(true);
    	
    	ByosFormulario.getCampo("depositoorigen").btoBoton1.setBoton(49, "Listar", "");
    	ByosFormulario.getCampo("depositoorigen").btoBoton1.setVisible(true);
    	ByosFormulario.setisLink("depositoorigen", true, "TblDepositoorigen", "descripcion");
    	ByosFormulario.getCampo("depositoorigen").setSelectLink(true, "select descripcion from tbldepositos where codigodeposito=?");
    	ByosFormulario.getCampo("depositoorigen").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarDeposito("depositoorigen");                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
    	
    	ByosFormulario.getCampo("depositodestino").btoBoton1.setBoton(49, "Listar", "");
    	ByosFormulario.getCampo("depositodestino").btoBoton1.setVisible(true);
    	ByosFormulario.setisLink("depositodestino", true, "TblDepositodestino", "descripcion");
    	ByosFormulario.getCampo("depositodestino").setSelectLink(true, "select descripcion from tbldepositos where codigodeposito=?");
    	ByosFormulario.getCampo("depositodestino").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarDeposito("depositodestino");                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        }); 
    	
    	ProximoCodigo();
    }

    private void initAddressList() {

    	ListaAjuste = new ByosDatagridFiltrableTextbox(false,950,370);

    	ByosColumnas[0] = new ByosColumna("codigoproducto",                String.class, "Codigo",          "", null);
    	ByosColumnas[1] = new ByosColumna("descripcionproducto",           String.class, "Descripcion",     "", null);
    	ByosColumnas[2] = new ByosColumna("codigomedida",                  String.class, "Medida",          "", null);    	
    	ByosColumnas[3] = new ByosColumna("equivalencia",                  String.class, "Equivalencia",    "", null);
    	ByosColumnas[4] = new ByosColumna("existencia",                    String.class, "Existencia",      "", null);
        ByosColumnas[5] = new ByosColumna("cantidad",                      String.class, "Cantidad",        "", null);

    	
    	try {
    		ListaAjuste.getDatagrid().setImmediate(true);
    		
			ListaAjuste.getDatagrid().initDatagridByos(ArrayTblTasladoItem , new tblTrasladoItem(), ByosColumnas, false);
			ListaAjuste.getDatagrid().addGeneratedColumn("codigoproducto", new Table.ColumnGenerator() {			
				@SuppressWarnings({ "serial" })
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTrasladoItem p = (tblTrasladoItem)itemId;
					
					final ByosCampo ByosCampo01 = new ByosCampo(utilString.CAMPO_TEXTFIELD);
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.txtTexto.setWidth("120px");
					ByosCampo01.txtTexto.setValue(p.getCodigoproducto());
					
					ByosCampo01.txtTexto.addValueChangeListener(new Property.ValueChangeListener() {
						public void valueChange(ValueChangeEvent event) {
							p.setCodigoproducto(ByosCampo01.txtTexto.getValue());
				            buscarProducto(p,new tblTrasladoItem(), source, ByosCampo01);
						}
				    });
					
					ByosCampo01.btoBoton1.setVisible(true);
					ByosCampo01.btoBoton2.setVisible(true);
					ByosCampo01.btoBoton1.setBoton(46, "Listar", "");
					ByosCampo01.btoBoton2.setBoton(47, "Eliminar", "");					
					
					
					ByosCampo01.btoBoton1.addClickListener(new Button.ClickListener() {
			            public void buttonClick(ClickEvent event) {
			               try{
                              procesoListarProducto(p, source, ByosCampo01);
			               } catch (Exception e) {
			                   // Ingnored, we'll let the Form handle the errors
			               }
			           }
			        });
					
					ByosCampo01.btoBoton2.addClickListener(new Button.ClickListener() {
			            public void buttonClick(ClickEvent event) {
			               try{

			               } catch (Exception e) {
			                   // Ingnored, we'll let the Form handle the errors
			               }
			           }
			        }); 					
					return ByosCampo01;
				}
			});		
			
			ListaAjuste.getDatagrid().addGeneratedColumn("descripcionproducto", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTrasladoItem p = (tblTrasladoItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("15em");
					ByosCampo01.lblDescripcion.setValue(p.getDescripcionproducto());
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});				
			
			ListaAjuste.getDatagrid().addGeneratedColumn("codigomedida", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTrasladoItem p = (tblTrasladoItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo(utilString.CAMPO_COMBOBOX);
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(false);
					ByosCampo01.CboxItem.setImmediate(true);
					ByosCampo01.CboxItem.setContainerDataSource(p.getMedidas());
					ByosCampo01.CboxItem.setValue(p.getDescripcionmedida());
					
					ByosCampo01.CboxItem.addValueChangeListener(new Property.ValueChangeListener() {
			            @Override
			            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
			            	ActualizarMedida(p, source, ByosCampo01);	
			            }

			        });

					return ByosCampo01;
				}
			});
			
			ListaAjuste.getDatagrid().addGeneratedColumn("existencia", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTrasladoItem p = (tblTrasladoItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(ExitenciaRelativa(p.getExistencia(),p.getEquivalencia()),3));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});			
			
			ListaAjuste.getDatagrid().addGeneratedColumn("equivalencia", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTrasladoItem p = (tblTrasladoItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(p.getEquivalencia(),3));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});	
			
			ListaAjuste.getDatagrid().addGeneratedColumn("cantidad", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTrasladoItem p = (tblTrasladoItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.setDecimales(3);
					
					
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getCantidad(),3));

					
					
					ByosCampo01.txtNumerico.addValueChangeListener(new Property.ValueChangeListener() {
						public void valueChange(ValueChangeEvent event) {
							if(ValidarExitencia(p.getExistencia(),ByosConversores.StringToBigDecimal(ByosCampo01.txtNumerico.getValue(),3),p.getEquivalencia())){
							   p.setCantidad(ByosConversores.StringToBigDecimal(ByosCampo01.txtNumerico.getValue(),3));
							}else{
							   Notification.show("La cantidad no puede ser menor a la existencia del deposito origen" , Notification.TYPE_TRAY_NOTIFICATION);
							   p.setCantidad(BigDecimal.valueOf(0));
							   ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getCantidad(),3));
							}
						}
				    });
					return ByosCampo01;
				}
			});
				
			
			
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
    }    
    
    public void procesoBuscar(){
    	final moduloBuscarTraslado ModuloBuscarTraslado = new moduloBuscarTraslado();
    	ModuloBuscarTraslado.btoAceptar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {         	
               try{ 
           		   ByosFormulario.setArrayClase(((tblTraslado)ByosFormulario.Clase).Buscar(ModuloBuscarTraslado.ClaseTraslado));  
                   if(ByosFormulario.ArrayClase == null || ByosFormulario.ArrayClase.size()==0){
                      ((tblTraslado)ByosFormulario.Clase).limpiar();
                      procesoHabilitar();
                      Limpiar();
                      ByosFormulario.refrescar();
                      Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);                               
                   }else{
                	  asignacion();
                	  procesoDeshabilitar();
                   }
                   ByosFormulario.refrescar();
                   ModuloBuscarTraslado.closeWindows();
               }catch (Exception e) {
                   e.printStackTrace();
               }   
            }
    	});
    	
    	ModuloBuscarTraslado.btoCancelar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {         	
               try{ 
                   ModuloBuscarTraslado.closeWindows();
               }catch (Exception e) {
                   e.printStackTrace();
               }   
            }
    	});    	
    	ModuloBuscarTraslado.openWindows();
    }
    
	public void procesoListarMedidas(final tblTrasladoItem TblTraslado, final Table Tabla){
	 	   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,500,200);
		   tblUnidadMedida TblUnidadMedidas = new tblUnidadMedida();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigomedida", String.class, "Codigo",       "", new LikeFilter("codigomedida", ""));
		   ByosColumnas[1] = new ByosColumna("descripcion",  String.class, "Descripicon",  "", new LikeFilter("descripcion",  ""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblUnidadMedidas.Buscar(new tblUnidadMedida()), TblUnidadMedidas, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	            	           tblUnidadMedida TblUnidadMedida = ((tblUnidadMedida)AL.get(0));
	            	           TblTraslado.setCodigomedida(TblUnidadMedida.getCodigomedida());
	            			   Tabla.commit();
	            			   Tabla.setEnabled(true);  
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
	
	public void buscarProducto(final tblTrasladoItem TblTrasladoItem, final tblTrasladoItem TblTrasladoOrigen, final Table Tabla, final ByosCampo Campo){
	   tblProducto TblProducto = new tblProducto();
	   
	   try{ 
		  if(TblTrasladoItem.getCodigoproducto()==null || TblTrasladoItem.getCodigoproducto().equals("")){
			 TblTrasladoItem.limpiar();  
		     Tabla.commit();
		     Tabla.setEnabled(true);
		  }else{
			 if(!TblTrasladoItem.getCodigoproducto().equals(TblTrasladoOrigen.getCodigoproducto())){ 
		        if(TblProducto.buscarCodigo(TblTrasladoItem.getCodigoproducto())){
			       tblProductoMedida TblProductoMedidas = new tblProductoMedida();
			       tblProductoDeposito TblProductoDeposito = new tblProductoDeposito();
			       TblProductoMedidas.buscarCodigo(TblTrasladoItem.getCodigoproducto(), TblProducto.getCodigomedida());
			       TblProductoDeposito.buscarCodigo(ClaseTraslado.getDepositoorigen(), TblTrasladoItem.getCodigoproducto());
			    
		           TblTrasladoItem.setCodigomedida(TblProducto.getCodigomedida());
		        
		           TblTrasladoItem.setDescripcionproducto(TblProducto.getDescripcioncorta());
		           TblTrasladoItem.setMedidas(new tblProductoMedida().getProductoMedidaContainer(TblTrasladoItem.getCodigoproducto()));
		           TblTrasladoItem.setDescripcionmedida(TblProducto.getCodigomedida() + "," + TblProducto.TblUnidadMedida.getDescripcion());
		           TblTrasladoItem.setCodigomedida(TblProductoMedidas.getCodigomedida());
		        
		           TblTrasladoItem.setExistencia(ExitenciaRelativa(TblProductoDeposito.getExistencia(),TblProductoMedidas.getEquivalencia()));
		           TblTrasladoItem.setEquivalencia(TblProductoMedidas.getEquivalencia());
		        
		           Tabla.commit();
		           Tabla.setEnabled(true);
		        }else{
			       TblTrasladoItem.limpiar();
			       Tabla.commit();
			       Tabla.setEnabled(true);
			       Notification.show("Este codigo de producto no existe" , Notification.TYPE_TRAY_NOTIFICATION);
		        }
			 }
		  }
	   }catch (Exception e) {
		  e.printStackTrace();
	   }
	}
	
	public void procesoListarProducto(final tblTrasladoItem TblTraslado, final Table Tabla, final ByosCampo Campo){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
		   tblProducto TblProductosListar = new tblProducto();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigoproducto",   String.class, "Codigo",  "", new LikeFilter("codigoproducto",""));
		   ByosColumnas[1] = new ByosColumna("descripcioncorta", String.class, "Descripicon", "", new LikeFilter("descripcioncorta",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblProductosListar.Buscar(new tblProducto()), TblProductosListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblProducto)AL.get(0)).getCodigoproducto();
	    					   TblTraslado.setCodigoproducto(((tblProducto)AL.get(0)).getCodigoproducto());
	    					   buscarProducto(TblTraslado, new tblTrasladoItem(), Tabla, Campo);
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
	
    public void ActualizarMedida(final tblTrasladoItem TblTrasladoItem, final Table Tabla, final ByosCampo Campo){
        if(Campo.CboxItem.getValue()!=null && !Campo.CboxItem.getValue().toString().equals("")){
        	tblProductoMedida TblProductoMedidas = new tblProductoMedida();
        	String[] Medidas = Campo.CboxItem.getValue().toString().split(",");
        	try {
				if(TblProductoMedidas.buscarCodigo(TblTrasladoItem.getCodigoproducto(), Medidas[0])){
				   if(TblProductoMedidas.getEquivalencia()!=null && TblProductoMedidas.getEquivalencia().doubleValue()!=0){
					  TblTrasladoItem.setEquivalencia(TblProductoMedidas.getEquivalencia());
					  TblTrasladoItem.setCodigomedida(TblProductoMedidas.getCodigomedida());
					  TblTrasladoItem.setDescripcionmedida(TblProductoMedidas.getCodigomedida() + "," + TblProductoMedidas.getDescripcion());	   
				   }else{
					  TblTrasladoItem.setEquivalencia(BigDecimal.valueOf(1));
				   }
				}
            }catch (Exception e) {
                e.printStackTrace();
            }  
        }
	    Tabla.commit();
	    Tabla.setEnabled(true);
    }
    
	public void procesoListarDeposito(final String Campo){
	  	   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);   
		   tblDeposito TblDepositosListar = new tblDeposito();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigodeposito", String.class,"Codigo","", new LikeFilter("codigodeposito", ""));
		   ByosColumnas[1] = new ByosColumna("descripcion",    String.class,"Descripicon","", new LikeFilter("descripcion",    ""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblDepositosListar.Buscar(new tblDeposito()), TblDepositosListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblDeposito)AL.get(0)).getCodigodeposito();
	    					   if(Campo.equals("depositoorigen")){
	                              ((tblTraslado)ByosFormulario.Clase).setDepositoorigen(xCodigo);
	                              ((tblTraslado)ByosFormulario.Clase).TblDepositoorigen.buscarCodigo(xCodigo);
	    					   }
	    					   if(Campo.equals("depositodestino")){
		                          ((tblTraslado)ByosFormulario.Clase).setDepositodestino(xCodigo);
		                          ((tblTraslado)ByosFormulario.Clase).TblDepositodestino.buscarCodigo(xCodigo);
		    				   }	    					   
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
        	
    public void procesoAddProducto(){
    	if(ValilarTrasladoDatosIniciales()){
    	   ArrayTblTasladoItem.add(new tblTrasladoItem());
    	   ListaAjuste.getDatagrid().refrescar(ArrayTblTasladoItem);
    	}
    } 
    
    void ProximoCodigo() {
        String xProximoCodigo;
		try {
			xProximoCodigo = ByosSql.getProximoCodigo("Select max(codigotraslado) From tbltraslado" , 4);
			if(xProximoCodigo==null){
				xProximoCodigo="0";	
			}
	        ((tblTraslado)ByosFormulario.Clase).setCodigotraslado(Integer.valueOf(xProximoCodigo)+1);
	        ((tblTraslado)ByosFormulario.Clase).setFechatraslado(new java.util.Date());
	        ByosFormulario.refrescar();
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    public boolean ValilarTrasladoDatosIniciales(){
    	if(((tblTraslado)ByosFormulario.Clase).getDepositoorigen()==null || ((tblTraslado)ByosFormulario.Clase).getDepositoorigen().equals("")){
    	   Notification.show("El codigo de deposito origen no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
    	   return false;
    	}
    	if(((tblTraslado)ByosFormulario.Clase).getDepositodestino()==null || ((tblTraslado)ByosFormulario.Clase).getDepositodestino().equals("")){
     	   Notification.show("El codigo de deposito destino no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
     	   return false;
     	}
    	if(((tblTraslado)ByosFormulario.Clase).getDepositodestino().equals(((tblTraslado)ByosFormulario.Clase).getDepositoorigen())){
      	   Notification.show("El codigo de deposito destino no puede ser igual al codigo del deposito origen" , Notification.TYPE_TRAY_NOTIFICATION);
      	   return false;
      	}  
    	return true;
    }
    
    public void setTrasladoItem(){
    	tblTraslado TblTraslado = ((tblTraslado)ByosFormulario.Clase);
    	TblTraslado.TblTrasladoItem = new  tblTrasladoItem[ArrayTblTasladoItem.size()];
    	for(int f=0;f<ArrayTblTasladoItem.size();f++){
    		TblTraslado.TblTrasladoItem[f] = ArrayTblTasladoItem.get(f);
    	}
    }
    
    public boolean ValidarTrasladoDatosArreglo(){
    	for(int f=0;f<ArrayTblTasladoItem.size();f++){
    		tblTrasladoItem TblTrasladoItem = ArrayTblTasladoItem.get(f);    		
    		if(TblTrasladoItem.getCodigoproducto()==null || TblTrasladoItem.getCodigoproducto().equals("")){
    	       Notification.show("Error en el rengon Nro: " + (f+1) + " El codigo del producto no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
    	       return false;   			
    		}
    		if(TblTrasladoItem.getCantidad()==null || TblTrasladoItem.getCantidad().doubleValue()<=0){
     	       Notification.show("Error en el rengon Nro: " + (f+1) + " La cantidad no puede ser nula ni menor que 0" , Notification.TYPE_TRAY_NOTIFICATION);
     	       return false;   			
     		}
    		if(TblTrasladoItem.getCodigomedida()==null || TblTrasladoItem.getCodigomedida().equals("")){
    		   Notification.show("Error en el rengon Nro: " + (f+1) + " El codigo de la medida no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
      	       return false;   			
      		}
    		if(TblTrasladoItem.getEquivalencia()==null || TblTrasladoItem.getEquivalencia().doubleValue()<=0){
     		   Notification.show("Error en el rengon Nro: " + (f+1) + " la equivalencia no puede ser <= 0" , Notification.TYPE_TRAY_NOTIFICATION);
       	       return false;   			
       		}  
    		if(TblTrasladoItem.getExistencia()==null || TblTrasladoItem.getExistencia().doubleValue()<=0){
      		   Notification.show("Error en el rengon Nro: " + (f+1) + " la existencia es nula o <= 0" , Notification.TYPE_TRAY_NOTIFICATION);
        	   return false;   			
            }      		
    		if(!ValidarExitencia(TblTrasladoItem.getExistencia(), TblTrasladoItem.getCantidad(), TblTrasladoItem.getCantidad())){
     		   Notification.show("Error en el rengon Nro: " + (f+1) + " la cantidad no puede ser menor a la existencia del deposito origen" , Notification.TYPE_TRAY_NOTIFICATION);
      	       return false;     			
    		}
    	}
    	return true;
    }
    
    public void procesoGuardar(){
		 
    	final ByosMsgBox Respuesta = new ByosMsgBox("Esta seguro de querer guardar la informacion?","Traslado"); 	  		    
		if(ValidarTrasladoDatosArreglo() && ValilarTrasladoDatosIniciales()){
		      Respuesta.btoSi.addClickListener(new Button.ClickListener() {
		         public void buttonClick(Button.ClickEvent event) {
		            try{  
		            	setTrasladoItem();
		            	tblTraslado TblTraslado = ((tblTraslado)ByosFormulario.Clase); 
		            	TblTraslado.guardar(TblTraslado);
		            	Limpiar();
		                Respuesta.closeWindows();
		            }catch(Exception e) {
		                e.printStackTrace();        
		            }  
		         }     
		      });
		
		      Respuesta.btoNo.addClickListener(new Button.ClickListener() {     
			     public void buttonClick(Button.ClickEvent event) {     
				    try{
		                Respuesta.closeWindows();
		            }catch(Exception e) {
		                e.printStackTrace();      
		            }    
			     }     
		      }); 
		      Respuesta.openWindows();   
		}   
    }
    
    public boolean ValidarExitencia(BigDecimal Existencia, BigDecimal Cantidad, BigDecimal Equivalencia){
    	if(Existencia!=null && Existencia.doubleValue()!=0 && Cantidad!=null && Cantidad.doubleValue()!=0 && Equivalencia!=null && Equivalencia.doubleValue()!=0){ 
    	   if((Existencia.doubleValue()/Equivalencia.doubleValue())>=Cantidad.doubleValue()){
    		  return true;   
    	   }else{
    		  return false;
    	   }
    	}
    	return true;
    } 
    
    public BigDecimal ExitenciaRelativa(BigDecimal Existencia, BigDecimal Equivalencia){
    	double Resultado =0;
    	if(Existencia!=null && Existencia.doubleValue()!=0 && Equivalencia!=null && Equivalencia.doubleValue()!=0){
    	   Resultado = Existencia.doubleValue() / Equivalencia.doubleValue();
    	}
    	return BigDecimal.valueOf(Resultado);
    }
    
    public void Limpiar(){
       ((tblTraslado)ByosFormulario.Clase).limpiar();
       ByosFormulario.refrescar();
       ByosFormulario.setArrayClase(null);
       asignacion();    	
 	   ProximoCodigo();
 	   ByosFormulario.refrescar();
    }
    
    public void asignacion(){
    	try{
			((tblTraslado)ByosFormulario.Clase).buscarCodigo(((tblTraslado)ByosFormulario.Clase).getCodigotraslado());
			ListaAjuste.getDatagrid().refrescar(new tblTrasladoItem().Buscar(((tblTraslado)ByosFormulario.Clase).getCodigotraslado()));
			
		}catch (Exception e) {
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
    
    public void procesoDeshabilitar(){
 	   BtoGuardar.setEnabled(false);
 	   ListaAjuste.getDatagrid().setEnabled(false);
 	   ListaAjuste.botProcesar.setEnabled(false);
 	   for(int f=0;f<DatosVisibles.length;f++){
 		   ByosFormulario.getCampo(DatosVisibles[f]).setEnabled(false);
 	   }
    }
    
    public void procesoHabilitar(){
  	   BtoGuardar.setEnabled(true);
  	   ListaAjuste.getDatagrid().setEnabled(true);
  	   ListaAjuste.botProcesar.setEnabled(true);
  	   for(int f=0;f<DatosVisibles.length;f++){
  		   ByosFormulario.getCampo(DatosVisibles[f]).setEnabled(true);
  	   }
     }    
}
