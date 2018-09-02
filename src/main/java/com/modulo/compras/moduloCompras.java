package com.modulo.compras;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosMsgBox;
import com.modulo.componentes.ByosParametros;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosValidar;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilString;
import com.modulo.deposito.tblDeposito;
import com.modulo.impuestos.tblImpuestos;
import com.modulo.medidasdefault.tblMedidasDefault;
import com.modulo.proveedor.tblProveedor;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class moduloCompras extends VerticalLayout  implements Serializable{

	private static final long serialVersionUID = 1L;


	public final Table tableListado = new Table();

    
    
    public tblCompras ClaseCompras01;
    public int item=0;
    
    ByosForm ByosFormulario01 = new ByosForm();
    ByosForm ByosFormulario02 = new ByosForm();
    ByosForm ByosFormulario03 = new ByosForm();
    
    //public tblCompras ClaseCompras02;    
    
    public moduloComprasItem ModuloComprasItem = new moduloComprasItem();
    
    String DatosVisibles01[] = {
    	"codigocompra",
        "fechacompra",    	
    	"codigoproveedor",
        "codigodeposito",
        "codigodefault",
        "tipocompras",
        "tipodocumento",
        "planillaimportacion",
        "expedienteimportacion",            
    };
    
    String DatosTitulos01[] = {
            "Codigo Compra",
            "Fecha Compra",
            "Codigo Proveedor",
            "Codigo Deposito",
            "Codigo Unidad x Defecto",
            "Tipo Compras",
            "Tipo Documento",
            "Planilla Importacion",
            "Expediente Importacion",         
    }; 
    
    String DatosVisibles02[] = {
        "codigodocumento",
        "numerocontrol",
        "documentoafectado",
        "fechadocumento",
        "fechaaplicacion",
        "montoexento",
        "montoimpuesto",    
        "montototal"            
    };    
    
    String DatosTitulos02[] = {
            "Codigo Documento",
            "Numero Control",
            "Documento Afectado",
            "Fecha Documento",
            "Fecha Aplicacion",
            "Monto Exento",
            "Monto Impuesto",        
            "Monto Documento",    		    
    };
    
    String DatosVisibles03[] = {
    		"montodescuentolfinal", 
            "codigocontable",
            "codigoislr",
            "montodeducibleislr",
            "porcentajeislr",
            "montoislr",
            "libro",
            "descripcion01"	       
    };  
    
    String DatosTitulos03[] = {
            "Monto Descuentol Final",
            "Codigo Contable",
            "Codigo ISLR",
            "Monto Deducible ISLR",
            "Porcentaje ISLR",
            "Monto ISLR",
            "Incluir en Libro",
            "Descripcion"	  
    };    
    
    
    ByosVerticalLayout Main = new ByosVerticalLayout("Compras de Productos","100%");
    HorizontalLayout Datos = new HorizontalLayout();
    VerticalLayout Datos01 = new VerticalLayout();
    
    public ByosMenu menu = new ByosMenu();    
    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    
    ByosBoton BtoTotalizar = new ByosBoton(menu.MenuPrincipal, 68,"Totalizar Compra","");
    ByosBoton BtoCancelar = new ByosBoton(menu.MenuPrincipal, 69,"Cancelar la Compra","");
    
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");    


    public int MaxReg = 1;
    public int Posicion=1;
    
    ByosColumna[] ByosColumnas = new ByosColumna[11];
    
    ByosDatagridFiltrableTextbox Listado;
    ArrayList <tblComprasItem>ArrayTblComprasItem = new ArrayList <tblComprasItem>();
    
    HorizontalSplitPanel Split = new HorizontalSplitPanel();
    
    tblComprasItem TblComprasItemTotal = new tblComprasItem();
    
    public moduloCompras(){        
        
        initAddressList(); 
        Listado.botCancelar.setVisible(false);
        Listado.botProcesar.setBoton(16, "Agregar", "Agregar");
        Listado.botProcesar.addClickListener(new Button.ClickListener() {
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
        
        BtoTotalizar.setEnabled(false);
        BtoTotalizar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	               try{
	            	   procesoTotalizar();
	               } catch (Exception e) {
	                   // Ingnored, we'll let the Form handle the errors
	               }
	           }
	        });  
        
        BtoCancelar.setEnabled(false);
        BtoCancelar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
	               try{
	            	   //procesoCancelar();
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
    	setMargin(false);
    	//setStyleName("v-verticallayout-main");
    	menu.setWidth("100%");
     
        Main.setMargin(false);
        Main.setWidth("100%");
        
    	menu.setWidth("100%");
    	ByosFormulario01.setWidth("30%");
    	ByosFormulario02.setWidth("30%");
    	ByosFormulario03.setWidth("30%");
    	
        ClaseCompras01 = new tblCompras(); 
        /*ByosFormulario01.setLargoDefault(30, "px");
        ByosFormulario01.setAnchoDefault("200");
        ByosFormulario02.setLargoDefault(30, "px");
        ByosFormulario02.setAnchoDefault("200");*/
        
        ByosFormulario01.setClase(ClaseCompras01, DatosVisibles01, DatosTitulos01, null);  
        ByosFormulario02.setClase(ClaseCompras01, DatosVisibles02, DatosTitulos02, null);
        ByosFormulario03.setClase(ClaseCompras01, DatosVisibles03, DatosTitulos03, null);
        initComponentes();
        
        Main.Contenido.addComponent(menu);
        
        String Ancho="110px";
        for(int f=0;f<DatosVisibles01.length;f++){
        	ByosFormulario01.getCampo(DatosVisibles01[f]).setAncho(Ancho);
        }
        for(int f=0;f<DatosVisibles02.length;f++){
        	ByosFormulario02.getCampo(DatosVisibles02[f]).setAncho(Ancho);
        }
        for(int f=0;f<DatosVisibles03.length;f++){
        	ByosFormulario03.getCampo(DatosVisibles03[f]).setAncho(Ancho);
        }        
        
        ByosFormulario01.getCampo("fechacompra").setTipoCampo(utilString.CAMPO_FECHAHORA);
        ByosFormulario02.getCampo("fechadocumento").setTipoCampo(utilString.CAMPO_FECHAHORA);
        ByosFormulario02.getCampo("fechaaplicacion").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	ByosFormulario03.getCampo("montodescuentolfinal").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario02.getCampo("montoimpuesto").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario02.getCampo("montototal").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	
    	ByosFormulario03.getCampo("montodeducibleislr").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario03.getCampo("porcentajeislr").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario03.getCampo("montoislr").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	
      	
    	
    	ByosFormulario03.getCampo("montodescuentolfinal").setDecimales(3);
    	ByosFormulario02.getCampo("montoimpuesto").setDecimales(3); 
    	ByosFormulario02.getCampo("montototal").setDecimales(3);
    	
    	ByosFormulario03.getCampo("montodeducibleislr").setDecimales(3);
    	ByosFormulario03.getCampo("porcentajeislr").setDecimales(3);
    	ByosFormulario03.getCampo("montoislr").setDecimales(3);
    	
        ByosFormulario01.getCampo("codigocompra").txtTexto.setEnabled(false);        
        ByosFormulario02.getCampo("montoimpuesto").txtNumerico.setEnabled(false);
        ByosFormulario03.getCampo("montodescuentolfinal").txtNumerico.setEnabled(false);        
        

        ByosFormulario01.setTipoCampo("tipocompras", "ComboBox");    
        ByosFormulario01.getCampo("tipocompras").CboxItem.setContainerDataSource(ByosContenedores.getTipoCompra());  
        
        ByosFormulario01.setTipoCampo("tipodocumento", "ComboBox");             
        ByosFormulario01.getCampo("tipodocumento").CboxItem.setContainerDataSource(ByosContenedores.getTipoDocuemnto());  
        
        ByosFormulario03.setTipoCampo("libro", "ComboBox");             
        ByosFormulario03.getCampo("libro").CboxItem.setContainerDataSource(ByosContenedores.getContainerSiNo());  
        
        
    	ByosFormulario01.getCampo("codigodeposito").btoBoton1.setBoton(49, "Listar", "");
    	ByosFormulario01.getCampo("codigodeposito").btoBoton1.setVisible(true);
    	ByosFormulario01.setisLink("codigodeposito", true, "TblDeposito", "descripcion");
    	ByosFormulario01.getCampo("codigodeposito").setSelectLink(true, "select descripcion from tbldepositos where codigodeposito=?");
    	ByosFormulario01.getCampo("codigodeposito").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarDeposito();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });   
    	
    	ByosFormulario01.getCampo("codigoproveedor").btoBoton1.setBoton(49, "Listar", "");
    	ByosFormulario01.getCampo("codigoproveedor").btoBoton1.setVisible(true);
    	ByosFormulario01.setisLink("codigoproveedor", true, "TblProveedor", "descripcion");
    	ByosFormulario01.getCampo("codigoproveedor").setSelectLink(true, "select descripcion from tblProveedor where codigoproveedor=?");
    	ByosFormulario01.getCampo("codigoproveedor").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarProveedor();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
    	
    	ByosFormulario01.getCampo("codigodefault").btoBoton1.setBoton(49, "Listar", "");
    	ByosFormulario01.getCampo("codigodefault").btoBoton1.setVisible(true);
    	ByosFormulario01.setisLink("codigodefault", true, "TblMedidasDefault", "descripcion");
    	ByosFormulario01.getCampo("codigodefault").setSelectLink(true, "select descripcion from TblMedidasDefault where codigodefault=?");
    	ByosFormulario01.getCampo("codigodefault").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                	 procesoListarMedidasDefault();                  
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
    	
    	ByosFormulario02.getCampo("montoimpuesto").btoBoton1.setBoton(48, "Agregar Impuestos", "");
    	ByosFormulario02.getCampo("montoimpuesto").btoBoton1.setVisible(true);
    	ByosFormulario02.getCampo("montoimpuesto").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                	 CargarImpuestos();                   
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });    	
    	
    	
    	ByosFormulario03.getCampo("montodescuentolfinal").btoBoton1.setBoton(48, "Agregar Descuentos Finales", "");
    	ByosFormulario03.getCampo("montodescuentolfinal").btoBoton1.setVisible(true);
    	ByosFormulario03.getCampo("montodescuentolfinal").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                	 CargarDescuentos();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
    	
    	
    	ProximoCodigo();
    	
    	ByosFormulario01.refrescar();
    	ByosFormulario02.refrescar();
    	ByosFormulario03.refrescar();
    	
    	ByosFormulario01.setMargin(false);
    	ByosFormulario02.setMargin(false);
    	ByosFormulario03.setMargin(false);
    	
        Datos.addComponent(ByosFormulario01);
        Datos.addComponent(ByosFormulario02);
        Datos.addComponent(ByosFormulario03);        
        Datos.setMargin(false);
        
        Datos01.addComponent(Datos);
        Datos01.setMargin(false);
        
        ModuloComprasItem.setMargin(false);
        ModuloComprasItem.setVisible(false);

        Split.setFirstComponent(Listado);
        Split.setSecondComponent(ModuloComprasItem);
        Split.setSplitPosition(810, Unit.PIXELS);
      
        Main.setMargin(false);
        Main.Contenido.setMargin(false);
        Main.Contenido.addComponent(Datos01); 
        Main.Contenido.addComponent(Split); 
        
        setEstadoDocumento();
        
        addComponent(Main);

    }
    
    public void ActualizarItem(){
        ((tblComprasItem)Listado.getDatagrid().getValue()).setTblComprasItem(((tblComprasItem)ModuloComprasItem.ClaseCompras01));
        Listado.getDatagrid().commit();
        Listado.getDatagrid().setEnabled(true); 
        ComprasItemTotal();
    }
    
    public void initComponentes() {
    	
    	Listado.getDatagrid().addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue() == null) {
                	ModuloComprasItem.setVisible(false);
                    return;
                }

                ModuloComprasItem.setTblComprasItem((tblComprasItem)Listado.getDatagrid().getValue(), ((tblCompras)ByosFormulario01.Clase));
                ModuloComprasItem.setVisible(true);
                // The form was opened for editing an existing item
                Listado.getDatagrid().setData(null);
            }
        });
    	       
    	ModuloComprasItem.btoAceptar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try{
                	ActualizarItem();                   
               }catch (Exception e) {
                   
               }
           }
       });
    	
    }

    private void initAddressList() {

    	Listado = new ByosDatagridFiltrableTextbox(false,800,370);

    	ByosColumnas[0]  = new ByosColumna("item",                          String.class, "Item",            "", null);
    	ByosColumnas[1]  = new ByosColumna("codigoproducto",                String.class, "Codigo",          "", null);
    	ByosColumnas[2]  = new ByosColumna("descripcionproducto",           String.class, "Descripcion",     "", null);
    	ByosColumnas[3]  = new ByosColumna("descripcionmedida",             String.class, "Medida",          "", null);    	    	
    	ByosColumnas[4]  = new ByosColumna("cantidad",                      String.class, "Cantidad",        "", null);
        ByosColumnas[5]  = new ByosColumna("costo",                         String.class, "Costo",           "", null);
        ByosColumnas[6]  = new ByosColumna("montoimpuesto",                 String.class, "Impuesto",        "", null);
        ByosColumnas[7]  = new ByosColumna("montototal",                    String.class, "Total",           "", null);
    	ByosColumnas[8]  = new ByosColumna("equivalencia",                  String.class, "Equivalencia",    "", null);
        ByosColumnas[9]  = new ByosColumna("montodescuento",                String.class, "Des.Lineal",      "", null);
        ByosColumnas[10] = new ByosColumna("montodescuentofinal",           String.class, "Des.Final",       "", null);


    	
    	try {
    		Listado.getDatagrid().setImmediate(true);
    		
			Listado.getDatagrid().initDatagridByos(ArrayTblComprasItem , new tblComprasItem(), ByosColumnas, false);
			
			Listado.getDatagrid().addGeneratedColumn("item", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					source.setColumnWidth("item", 30);
					final Label lblDescripcion = new Label();
					lblDescripcion.setStyleName("align-right");
					lblDescripcion.setVisible(true);
					lblDescripcion.setWidth("2em");
					lblDescripcion.setValue(p.getItem()==null?"":p.getItem().toString());
					lblDescripcion.setImmediate(true);					
					return lblDescripcion;
				}
			});				
			
			Listado.getDatagrid().addGeneratedColumn("codigoproducto", new Table.ColumnGenerator() {			
				@SuppressWarnings({ "serial" })
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					source.setColumnWidth("codigoproducto", 120);
					final ByosCampo ByosCampo01 = new ByosCampo(utilString.CAMPO_LABEL);
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setWidth("10em");
					ByosCampo01.lblDescripcion.setValue(p.getCodigoproducto());
					
					ByosCampo01.btoBoton2.setVisible(true);
					ByosCampo01.btoBoton2.setBoton(47, "Eliminar", "");									
					ByosCampo01.btoBoton2.addClickListener(new Button.ClickListener() {
			            public void buttonClick(ClickEvent event) {
			               try{
                              //procesoListarProducto(p, source, ByosCampo01);
			               } catch (Exception e) {
			                   // Ingnored, we'll let the Form handle the errors
			               }
			           }
			        });				
					return ByosCampo01;
				}
			});		
			
			Listado.getDatagrid().addGeneratedColumn("descripcionproducto", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("15em");
					ByosCampo01.lblDescripcion.setValue(p.getDescripcionproducto());
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});				
			
			Listado.getDatagrid().addGeneratedColumn("Descripcionmedida", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo(utilString.CAMPO_LABEL);
					ByosCampo01.lblDescripcion.setValue(p.getDescripcionmedida());
					return ByosCampo01;
				}
			});
			
			Listado.getDatagrid().addGeneratedColumn("costo", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(p.getCosto(),3));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});			
			
			Listado.getDatagrid().addGeneratedColumn("equivalencia", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
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
			
			Listado.getDatagrid().addGeneratedColumn("cantidad", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(p.getCantidad(),3));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});	
			
			Listado.getDatagrid().addGeneratedColumn("montodescuento", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(p.getMontodescuento(),2));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});
			
			Listado.getDatagrid().addGeneratedColumn("montodescuentofinal", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(p.getMontodescuentofinal(),2));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});	
			
			Listado.getDatagrid().addGeneratedColumn("montoimpuesto", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(p.getMontoimpuesto(),2));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});				
			
			Listado.getDatagrid().addGeneratedColumn("montototal", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasItem p = (tblComprasItem)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblDescripcion.setStyleName("text-align: right");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("6em");
					ByosCampo01.lblDescripcion.setValue(ByosConversores.BigDecimalToString(p.getMontototal(),2));
					ByosCampo01.lblDescripcion.setImmediate(true);					
					return ByosCampo01;
				}
			});				
			
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
    }    

    public void procesoAddProducto(){
    	//if(ValilarTrasladoDatosIniciales()){
    	   item++;
    	   tblComprasItem Item = new tblComprasItem();
    	   Item.setItem(item);
    	   ArrayTblComprasItem.add(Item);
    	   Listado.getDatagrid().refrescar(ArrayTblComprasItem);
    	//}
    }
    
    public boolean ValilarComprasTotalizar(){
    	ComprasItemTotal();
    	double Total01=Math.abs(ByosValidar.Nulo(((tblCompras)ByosFormulario02.Clase).getMontototal())-ByosValidar.Nulo(TblComprasItemTotal.getMontototal()));
       	double Impuesto01=Math.abs(ByosValidar.Nulo(((tblCompras)ByosFormulario02.Clase).getMontoimpuesto())-ByosValidar.Nulo(TblComprasItemTotal.getMontoimpuesto()));

       	System.out.println("Total: " + Total01 + " Impuesto: " + Impuesto01);
    	if(Total01>ByosParametros.TOLERANCIA_NUMERICA){
            Notification.show("El monto total inicial no cuadra con el monto total por renglon, existe una diferencia de: " + ByosConversores.DoubleToString(Total01, 3), Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	}     	
    	if(Impuesto01>ByosParametros.TOLERANCIA_NUMERICA){
            Notification.show("El monto impuesto inicial no cuadra con el monto impuesto por renglon, existe una diferencia de: " + ByosConversores.DoubleToString(Impuesto01, 3), Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	} 
   	    
    	return true;
    }     
	
    public boolean ValilarComprasDatosIniciales(){
    	if(((tblCompras)ByosFormulario01.Clase).getCodigodeposito()==null || ((tblCompras)ByosFormulario01.Clase).getCodigodeposito().equals("")){
    	   Notification.show("El codigo de deposito no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
    	   return false;
    	}
    	if(((tblCompras)ByosFormulario01.Clase).getCodigoproveedor()==null || ((tblCompras)ByosFormulario01.Clase).getCodigoproveedor().equals("")){
     	   Notification.show("El codigo de proveedor no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
     	   return false;
     	}
    	if(((tblCompras)ByosFormulario01.Clase).getTipocompras()==null || ((tblCompras)ByosFormulario01.Clase).getTipocompras().equals("")){
      	   Notification.show("Tipo de Compra no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
      	   return false;
      	} 
    	if(((tblCompras)ByosFormulario01.Clase).getTipodocumento()==null || ((tblCompras)ByosFormulario01.Clase).getTipodocumento().equals("")){
       	   Notification.show("Tipo de Documento no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
       	   return false;
       	} 
    	if(((tblCompras)ByosFormulario01.Clase).getFechacompra()==null || ((tblCompras)ByosFormulario01.Clase).getFechacompra().equals("")){
            Notification.show("La Fecha de Compra no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	}      	
    	if(((tblCompras)ByosFormulario02.Clase).getFechaaplicacion()==null || ((tblCompras)ByosFormulario02.Clase).getFechaaplicacion().equals("")){
           Notification.show("La Fecha de Aplicacion no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
           return false;
    	}   
    	if(((tblCompras)ByosFormulario02.Clase).getFechadocumento()==null || ((tblCompras)ByosFormulario02.Clase).getFechadocumento().equals("")){
            Notification.show("La Fecha de Documento no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	}    	
    	if(((tblCompras)ByosFormulario02.Clase).getMontototal()==null || ((tblCompras)ByosFormulario02.Clase).getMontototal().equals("")){
            Notification.show("El Monto del Documento no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	}     	
    	/*if(((tblCompras)ByosFormulario02.Clase).getMontoimpuesto()==null || ((tblCompras)ByosFormulario02.Clase).getMontoimpuesto().equals("")){
            Notification.show("El Monto del impuesto no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	} */ 
    	if(((tblCompras)ByosFormulario02.Clase).getCodigodocumento()==null || ((tblCompras)ByosFormulario02.Clase).getCodigodocumento().equals("")){
            Notification.show("El Codigo del Documento no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	} 
    	if(((tblCompras)ByosFormulario02.Clase).getNumerocontrol()==null || ((tblCompras)ByosFormulario02.Clase).getNumerocontrol().equals("")){
            Notification.show("El Numero de control no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
            return false;
     	}    	
    	return true;
    }  
    
    public boolean ValidarComprasDatosArreglo(){
    	for(int f=0;f<ArrayTblComprasItem.size();f++){
    		tblComprasItem TblComprasItem = ArrayTblComprasItem.get(f);    		
    		if(TblComprasItem.getCodigoproducto()==null || TblComprasItem.getCodigoproducto().equals("")){
    	       Notification.show("Error en el rengon Nro: " + (f+1) + " El codigo del producto no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
    	       return false;   			
    		}
    		if(TblComprasItem.getCantidad()==null || TblComprasItem.getCantidad().doubleValue()<=0){
     	       Notification.show("Error en el rengon Nro: " + (f+1) + " La cantidad no puede ser nula ni menor que 0" , Notification.TYPE_TRAY_NOTIFICATION);
     	       return false;   			
     		}
    		if(TblComprasItem.getCodigomedida()==null || TblComprasItem.getCodigomedida().equals("")){
    		   Notification.show("Error en el rengon Nro: " + (f+1) + " El codigo de la medida no puede quedar en blanco" , Notification.TYPE_TRAY_NOTIFICATION);
      	       return false;   			
      		}
    		if(TblComprasItem.getEquivalencia()==null || TblComprasItem.getEquivalencia().doubleValue()<=0){
     		   Notification.show("Error en el rengon Nro: " + (f+1) + " la equivalencia no puede ser <= 0" , Notification.TYPE_TRAY_NOTIFICATION);
       	       return false;   			
       		}  
    		if(TblComprasItem.getCosto()==null || TblComprasItem.getCosto().doubleValue()<=0){
      		   Notification.show("Error en el rengon Nro: " + (f+1) + " la costo es nula o <= 0" , Notification.TYPE_TRAY_NOTIFICATION);
        	   return false;   			
            }      		

    	}
    	return true;
    }
    
    public void ComprasItemTotal(){
    	double xMontototal = 0;
    	double xMontoneto = 0;
    	double xMontodescuento = 0;
    	double xMontodescuentofinal = 0;
    	double xMontoimpuesto = 0 ;
    	for(int f=0;f<ArrayTblComprasItem.size();f++){
    		tblComprasItem TblComprasItem = ArrayTblComprasItem.get(f);    		
    		xMontototal+=ByosValidar.Nulo(TblComprasItem.getMontototal());
    		xMontoneto+=ByosValidar.Nulo(TblComprasItem.getMontoneto());
    		xMontodescuento+=ByosValidar.Nulo(TblComprasItem.getMontodescuento());
    		xMontodescuentofinal+=ByosValidar.Nulo(TblComprasItem.getMontodescuentofinal());
    		xMontoimpuesto+=ByosValidar.Nulo(TblComprasItem.getMontoimpuesto());
    	}
    	TblComprasItemTotal.setMontototal(BigDecimal.valueOf(xMontototal));
    	TblComprasItemTotal.setMontoneto(BigDecimal.valueOf(xMontoneto));
    	TblComprasItemTotal.setMontodescuento(BigDecimal.valueOf(xMontodescuento));
    	TblComprasItemTotal.setMontoimpuesto(BigDecimal.valueOf(xMontoimpuesto));
    	Listado.getDatagrid().setColumnFooter("montototal", ByosConversores.DoubleToString(xMontototal, 3));
    	Listado.getDatagrid().setColumnFooter("montoneto", ByosConversores.DoubleToString(xMontoneto, 3));
    	Listado.getDatagrid().setColumnFooter("montodescuento", ByosConversores.DoubleToString(xMontodescuento, 3));
    	Listado.getDatagrid().setColumnFooter("montodescuentofinal", ByosConversores.DoubleToString(xMontodescuentofinal, 3));
    	Listado.getDatagrid().setColumnFooter("montoimpuesto", ByosConversores.DoubleToString(xMontoimpuesto, 3));
    }    
    
    public void setComprasItem(){
    	tblCompras TblCompras = ((tblCompras)ByosFormulario01.Clase);
    	TblCompras.TblComprasItem = new  tblComprasItem[ArrayTblComprasItem.size()];
    	item=ArrayTblComprasItem.size();
    	for(int f=0;f<ArrayTblComprasItem.size();f++){
    		TblCompras.TblComprasItem[f] = (tblComprasItem)ArrayTblComprasItem.get(f);
    	}
    }
    
    public void procesoGuardar(){ 
    	final ByosMsgBox Respuesta = new ByosMsgBox("Esta seguro de querer guardar la informacion?","Compras"); 	  		    
		if(ValidarComprasDatosArreglo() && ValilarComprasDatosIniciales()){
		      Respuesta.btoSi.addClickListener(new Button.ClickListener() {
		         public void buttonClick(Button.ClickEvent event) {
		            try{  
		            	UnificarByosFormulario();
		            	setComprasItem();
		            	((tblCompras)ByosFormulario01.Clase).guardar(((tblCompras)ByosFormulario01.Clase));		            	
		            	setEstadoDocumento();
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
 
    
    public void procesoTotalizar(){ 
    	final ByosMsgBox Respuesta = new ByosMsgBox("Esta seguro de querer cerrar esta compra?","Compras"); 	  		    
		if(ValidarComprasDatosArreglo() && ValilarComprasDatosIniciales() && ValilarComprasTotalizar()){
		      Respuesta.btoSi.addClickListener(new Button.ClickListener() {
		         public void buttonClick(Button.ClickEvent event) {
		            try{  
		            	UnificarByosFormulario();
		            	setComprasItem();
		            	((tblCompras)ByosFormulario01.Clase).setEstado("Totalizado");
		            	
		            	((tblCompras)ByosFormulario01.Clase).totalizarCompra(((tblCompras)ByosFormulario01.Clase));
		            	setEstadoDocumento();
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
    
	public void procesoListarDeposito(){
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
	                           ((tblCompras)ByosFormulario01.Clase).setCodigodeposito(xCodigo);
	                           ((tblCompras)ByosFormulario01.Clase).TblDeposito.buscarCodigo(xCodigo);	    					   
	                           ByosFormulario01.refrescar();  
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
    
	public void procesoListarProveedor(){
	  	   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);   
		   tblProveedor TblProveedorListar = new tblProveedor();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigoproveedor", String.class,"Codigo",      "", new LikeFilter("codigoproveedor", ""));
		   ByosColumnas[1] = new ByosColumna("descripcion",     String.class,"Descripicon", "", new LikeFilter("descripcion",     ""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblProveedorListar.BuscarArray(new tblProveedor()), TblProveedorListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	            	        	String xCodigo = ((tblProveedor)AL.get(0)).getCodigoproveedor(); 
	            	        	System.out.println(xCodigo);
	    					   ((tblCompras)ByosFormulario01.Clase).setCodigoproveedor(xCodigo);
	                           ((tblCompras)ByosFormulario01.Clase).TblProveedor.buscarCodigo(xCodigo);	 
	                           ByosFormulario01.refrescar();  
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
	
    public void asignacion(){
    	try{
			((tblCompras)ByosFormulario01.Clase).buscarCodigo(((tblCompras)ByosFormulario01.Clase).getCodigocompra());
			((tblCompras)ByosFormulario02.Clase).buscarCodigo(((tblCompras)ByosFormulario01.Clase).getCodigocompra());
			((tblCompras)ByosFormulario03.Clase).buscarCodigo(((tblCompras)ByosFormulario01.Clase).getCodigocompra());

			ArrayTblComprasItem = new tblComprasItem().BuscarArrayList(((tblCompras)ByosFormulario01.Clase).getCodigocompra());
			if(ArrayTblComprasItem==null){
			   ArrayTblComprasItem = new ArrayList <tblComprasItem>();
			}else{
			   /*for(int f=0;f<ArrayTblComprasItem.size();f++){
				   tblUnidadMedida TblUnidadMedida = new tblUnidadMedida();
				   if(TblUnidadMedida.buscarCodigo(((tblComprasItem)ArrayTblComprasItem.get(f)).getCodigomedida())){
					   ((tblComprasItem)ArrayTblComprasItem.get(f)).setDescripcionmedida(TblUnidadMedida.getCodigomedida() + "," + TblUnidadMedida.getDescripcion());
				   }
			   }*/
			}
			
			Listado.getDatagrid().refrescar(ArrayTblComprasItem);
			ModuloComprasItem.setVisible(false);			
			setEstadoDocumento();
			ComprasItemTotal();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void procesoTblProximo(){
    	ByosFormulario01.procesoProximo();
    	asignacion();
    	ByosFormulario01.refrescar();
    	ByosFormulario02.refrescar();
    	ByosFormulario03.refrescar();
    	setEstadoDocumento();
    }
    
    public void procesoTblAnterior(){
    	ByosFormulario01.procesoAnterior();
    	asignacion();
    	ByosFormulario01.refrescar();
    	ByosFormulario02.refrescar();
    	ByosFormulario03.refrescar();
    	setEstadoDocumento();
    }
    
    public void Limpiar(){
        ((tblCompras)ByosFormulario01.Clase).limpiar();
        ((tblCompras)ByosFormulario02.Clase).limpiar();
        ((tblCompras)ByosFormulario03.Clase).limpiar();
        ByosFormulario01.refrescar();
        ByosFormulario02.refrescar();
        ByosFormulario03.refrescar();
        ByosFormulario01.setArrayClase(null);
        ByosFormulario02.setArrayClase(null);
        ByosFormulario03.setArrayClase(null);
        asignacion();    	
  	    ProximoCodigo();
  	    ByosFormulario01.refrescar();
  	    ByosFormulario02.refrescar();
  	    ByosFormulario03.refrescar();
  	    setEstadoDocumento();
     }  
    
    void ProximoCodigo() {
        String xProximoCodigo;
		try {
			xProximoCodigo = ByosSql.getProximoCodigo("Select max(codigocompra) From tblCompras" , 4);
			if(xProximoCodigo==null){
				xProximoCodigo="0";	
			}
	        ((tblCompras)ByosFormulario01.Clase).setCodigocompra(Integer.valueOf(xProximoCodigo)+1);
	        ((tblCompras)ByosFormulario01.Clase).setFechacompra(new java.util.Date());
	        ByosFormulario01.refrescar();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void procesoBuscar(){
    	final moduloBuscarCompras ModuloBuscarCompras = new moduloBuscarCompras();
    	ModuloBuscarCompras.btoAceptar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {         	
               try{ 
           		   ByosFormulario01.setArrayClase(((tblCompras)ByosFormulario01.Clase).Buscar(((buscarCompras)ModuloBuscarCompras.ClaseCompras)));  
                   if(ByosFormulario01.ArrayClase == null || ByosFormulario01.ArrayClase.size()==0){
                      ((tblCompras)ByosFormulario01.Clase).limpiar();                      
                      Limpiar();
                      setEstadoDocumento();
                      ByosFormulario01.refrescar();
                      ByosFormulario02.refrescar();
                      ByosFormulario03.refrescar();
                      Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);                               
                   }else{
                	   
                	  asignacion();              	  
                	  setEstadoDocumento();              	  
                   }
                   ByosFormulario01.refrescar();
                   ByosFormulario02.refrescar();
                   ByosFormulario03.refrescar();  
                   ModuloBuscarCompras.closeWindows();                
               }catch (Exception e) {
                   e.printStackTrace();
               }   
            }
    	});
    	
    	
    	ModuloBuscarCompras.btoCancelar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {         	
               try{ 
                   ModuloBuscarCompras.closeWindows();
               }catch (Exception e) {
                   e.printStackTrace();
               }   
            }
    	});    	
    	ModuloBuscarCompras.openWindows();    	
    }
    
	public void procesoDeshabilitar() {
		BtoGuardar.setEnabled(false);
		Listado.getDatagrid().setEnabled(false);
		Listado.botProcesar.setEnabled(false);
		for (int f = 0; f < DatosVisibles01.length; f++) {
			ByosFormulario01.getCampo(DatosVisibles01[f]).setEnabled(false);
		}
		for (int f = 0; f < DatosVisibles02.length; f++) {
			ByosFormulario02.getCampo(DatosVisibles02[f]).setEnabled(false);
		}
		for (int f = 0; f < DatosVisibles03.length; f++) {
			ByosFormulario03.getCampo(DatosVisibles03[f]).setEnabled(false);
		}
	}

	public void procesoHabilitar() {
		BtoGuardar.setEnabled(true);
		Listado.getDatagrid().setEnabled(true);
		Listado.botProcesar.setEnabled(true);
		for (int f = 0; f < DatosVisibles01.length; f++) {
			ByosFormulario01.getCampo(DatosVisibles01[f]).setEnabled(true);
		}
		for (int f = 0; f < DatosVisibles02.length; f++) {
			ByosFormulario02.getCampo(DatosVisibles02[f]).setEnabled(true);
		}
		for (int f = 0; f < DatosVisibles03.length; f++) {
			ByosFormulario03.getCampo(DatosVisibles03[f]).setEnabled(true);
		}
	}
	
	public void setEstadoDocumento(){
		UnificarByosFormulario();
		if( ((tblCompras)ByosFormulario01.Clase).getEstado()==null || 
		    ((tblCompras)ByosFormulario01.Clase).getEstado().equals("Activo") || 
		    ((tblCompras)ByosFormulario01.Clase).getEstado().equals("") ){
			procesoHabilitar();
			BtoGuardar.setEnabled(true);
			BtoTotalizar.setEnabled(true);
			BtoCancelar.setEnabled(false);
		}
        if(((tblCompras)ByosFormulario01.Clase).getEstado().equals("Totalizado")){
			procesoDeshabilitar();
			BtoGuardar.setEnabled(false);
			BtoTotalizar.setEnabled(false);
			BtoCancelar.setEnabled(true);
		}
        if(((tblCompras)ByosFormulario01.Clase).getEstado().equals("Totalizado y Cancelado")){
			procesoDeshabilitar();
			BtoGuardar.setEnabled(false);
			BtoTotalizar.setEnabled(false);
			BtoCancelar.setEnabled(false);
		}
	}
	
	
	
	public void UnificarByosFormulario(){
		((tblCompras)ByosFormulario01.Clase).setMontototal(((tblCompras)ByosFormulario02.Clase).getMontototal());    	
		((tblCompras)ByosFormulario01.Clase).setMontoexento(((tblCompras)ByosFormulario02.Clase).getMontoexento());    	
		((tblCompras)ByosFormulario01.Clase).setMontoimpuesto(((tblCompras)ByosFormulario02.Clase).getMontoimpuesto());    	
		((tblCompras)ByosFormulario01.Clase).setMontodescuentolfinal(((tblCompras)ByosFormulario03.Clase).getMontodescuentolfinal());    	
		((tblCompras)ByosFormulario01.Clase).setCodigodocumento(((tblCompras)ByosFormulario02.Clase).getCodigodocumento());    	
		((tblCompras)ByosFormulario01.Clase).setNumerocontrol(((tblCompras)ByosFormulario02.Clase).getNumerocontrol());    	
		((tblCompras)ByosFormulario01.Clase).setFechadocumento(((tblCompras)ByosFormulario02.Clase).getFechadocumento());    	
		((tblCompras)ByosFormulario01.Clase).setFechaaplicacion(((tblCompras)ByosFormulario02.Clase).getFechaaplicacion());    	
		((tblCompras)ByosFormulario01.Clase).setDescripcion01(((tblCompras)ByosFormulario03.Clase).getDescripcion01());    	
		((tblCompras)ByosFormulario01.Clase).setDescripcion02(((tblCompras)ByosFormulario03.Clase).getDescripcion02());    	
		((tblCompras)ByosFormulario01.Clase).setDescripcion03(((tblCompras)ByosFormulario03.Clase).getDescripcion03());    	
    	
		((tblCompras)ByosFormulario01.Clase).setCodigocontable(((tblCompras)ByosFormulario03.Clase).getCodigocontable());
		((tblCompras)ByosFormulario01.Clase).setCodigoislr(((tblCompras)ByosFormulario03.Clase).getCodigoislr());
		((tblCompras)ByosFormulario01.Clase).setMontodeducibleislr(((tblCompras)ByosFormulario03.Clase).getMontodeducibleislr());
		((tblCompras)ByosFormulario01.Clase).setPorcentajeislr(((tblCompras)ByosFormulario03.Clase).getPorcentajeislr());
		((tblCompras)ByosFormulario01.Clase).setMontoislr(((tblCompras)ByosFormulario03.Clase).getMontoislr());
		((tblCompras)ByosFormulario01.Clase).setDocumentoafectado(((tblCompras)ByosFormulario02.Clase).getDocumentoafectado());
		
		((tblCompras)ByosFormulario01.Clase).setLibro(((tblCompras)ByosFormulario03.Clase).getLibro());   	   	
		
		((tblCompras)ByosFormulario01.Clase).setDescripproveedor(ByosFormulario01.getCampo("codigoproveedor").lblDescripcion.getValue());
	}
	
	public void procesoListarMedidasDefault(){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
		   tblMedidasDefault TblMedidaListar = new tblMedidasDefault();
		   ByosColumna[] ByosColumnas = new ByosColumna[2];

		   ByosColumnas[0] = new ByosColumna("codigodefault",   String.class, "Codigo",  "", new LikeFilter("codigodefault",""));
		   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion",""));

	       try{
	           dwb.getDatagrid().initDatagridByos(TblMedidaListar.Buscar(new tblMedidasDefault()), TblMedidaListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	            	           String xCodigo = ((tblMedidasDefault)AL.get(0)).getCodigodefault(); 
	    					   ((tblCompras)ByosFormulario01.Clase).setCodigodefault(xCodigo);
	                           ((tblCompras)ByosFormulario01.Clase).TblMedidasDefault.buscarCodigo(xCodigo);	 
	                           ByosFormulario01.refrescar();  
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
	
	public void CargarImpuestos(){
		final ArrayList <tblComprasImpuestos> ArrayTblComprasImpuestos = new ArrayList<tblComprasImpuestos>();
		tblComprasImpuestos TblComprasImpuestos;
		tblImpuestos[] TblImpuestos = null;
		  
		if (ByosValidar.Nulo(((tblCompras) ByosFormulario01.Clase).getMontototal()) == 0) {
			Notification.show("El Monto del Documento no puede quedar en blanco",Notification.TYPE_TRAY_NOTIFICATION);		
		}else{
			try {
				TblImpuestos = new tblImpuestos().Buscar(new tblImpuestos());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			UnificarByosFormulario();
			if (TblImpuestos != null && TblImpuestos.length > 0) {
				for (int f = 0; f < TblImpuestos.length; f++) {
					TblComprasImpuestos = new tblComprasImpuestos();
					TblComprasImpuestos.setCodigoimpuesto(TblImpuestos[f].getCodigoimpuesto());
					TblComprasImpuestos.setDescripcion(TblImpuestos[f].getDescripcion());
					TblComprasImpuestos.setPorcentaje(TblImpuestos[f].getPorcentaje());
					if (((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos != null && ((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos.length > 0) {

						for (int c = 0; c < ((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos.length; c++) {
							if (((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos[c].getCodigoimpuesto().equals(TblComprasImpuestos.getCodigoimpuesto())) {
								TblComprasImpuestos.setMontodeducible(((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos[c].getMontodeducible());
								TblComprasImpuestos.setMontoimpuesto(((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos[c].getMontoimpuesto());
								TblComprasImpuestos.setFechacompra(((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos[c].getFechacompra());
							}
						}
					}
					ArrayTblComprasImpuestos.add(TblComprasImpuestos);
				}
				final moduloComprasImpuestos ModuloLeerImpuestos = new moduloComprasImpuestos(ArrayTblComprasImpuestos);

				ModuloLeerImpuestos.btoAceptar.addClickListener(new Button.ClickListener() {
							public void buttonClick(ClickEvent event) {
								try {
									String Estado = ModuloLeerImpuestos.EstatusImpuestos();
									if (Estado.equals("ok")) {
										tblComprasImpuestos[] TblComprasImpuestos01 = null;
										if (ArrayTblComprasImpuestos != null && ArrayTblComprasImpuestos.size() > 0) {
											TblComprasImpuestos01 = new tblComprasImpuestos[ArrayTblComprasImpuestos.size()];
											for (int f = 0; f < ArrayTblComprasImpuestos.size(); f++) {
												TblComprasImpuestos01[f] = new tblComprasImpuestos();
												TblComprasImpuestos01[f] = ((tblComprasImpuestos) ArrayTblComprasImpuestos.get(f));
											}
										}
										((tblCompras) ByosFormulario01.Clase).TblComprasImpuestos = TblComprasImpuestos01;

										((tblCompras) ByosFormulario01.Clase).setMontodeducible(BigDecimal.valueOf(ModuloLeerImpuestos.TotalMontoDeducible));
										((tblCompras) ByosFormulario01.Clase).setMontoimpuesto(BigDecimal.valueOf(ModuloLeerImpuestos.TotalMontoImpuestos));

										((tblCompras) ByosFormulario02.Clase).setMontodeducible(BigDecimal.valueOf(ModuloLeerImpuestos.TotalMontoDeducible));
										((tblCompras) ByosFormulario02.Clase).setMontoimpuesto(BigDecimal.valueOf(ModuloLeerImpuestos.TotalMontoImpuestos));

										ByosFormulario02.refrescar();

										ModuloLeerImpuestos.closeWindows();
									} else {
										Notification.show(Estado,Notification.TYPE_TRAY_NOTIFICATION);
									}

								} catch (Exception e) {
									// Ingnored, we'll let the Form handle the
									// errors
								}
							}
						});

				ModuloLeerImpuestos.openWindows();
			}
		}
		
	}
	
	public void CargarDescuentos(){
		final ArrayList <tblComprasDescuentos> ArrayTblComprasDescuentos = new ArrayList<tblComprasDescuentos>();
		tblComprasDescuentos TblComprasDescuentos;

		if (ByosValidar.Nulo(((tblCompras) ByosFormulario01.Clase).getMontototal()) == 0) {
			Notification.show("El Monto del Documento no puede quedar en blanco",Notification.TYPE_TRAY_NOTIFICATION);		
		}else{
			for(int f=0;f<10;f++){
				if(((tblCompras)ByosFormulario01.Clase).TblComprasDescuentos!=null &&  (f < ((tblCompras)ByosFormulario01.Clase).TblComprasDescuentos.length)){
				   ((tblCompras)ByosFormulario01.Clase).TblComprasDescuentos[f].setItem(f+1);
				   ArrayTblComprasDescuentos.add(((tblCompras)ByosFormulario01.Clase).TblComprasDescuentos[f]);
				}else{
				   TblComprasDescuentos = new tblComprasDescuentos();
				   TblComprasDescuentos.setItem(f+1);
				   ArrayTblComprasDescuentos.add(TblComprasDescuentos);
				   
				}
			}
			UnificarByosFormulario();
			final moduloComprasDescuentos ModuloComprasDescuentos = new moduloComprasDescuentos(ArrayTblComprasDescuentos, ((tblCompras)ByosFormulario01.Clase));
			
			ModuloComprasDescuentos.btoAceptar.addClickListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	                try{
	                	String Estado=ModuloComprasDescuentos.EstatusImpuestos();
	                	if(Estado.equals("ok")){
	                	   tblComprasDescuentos[] TblComprasDescuentos01 = null;
	                	   if(ArrayTblComprasDescuentos!=null && ArrayTblComprasDescuentos.size()>0){
	                		  TblComprasDescuentos01 = new tblComprasDescuentos[ArrayTblComprasDescuentos.size()];
	                		  for(int f=0;f<ArrayTblComprasDescuentos.size();f++){
	                			  TblComprasDescuentos01[f] = new tblComprasDescuentos();
	                			  TblComprasDescuentos01[f] = ((tblComprasDescuentos)ArrayTblComprasDescuentos.get(f));
	                		  }
	                	   }
	                	   ((tblCompras)ByosFormulario01.Clase).TblComprasDescuentos = TblComprasDescuentos01;	                	   
	                	   ((tblCompras)ByosFormulario01.Clase).setMontodescuentolfinal(BigDecimal.valueOf(ModuloComprasDescuentos.TotalMontoDescuento));	                	   
	                	   ((tblCompras)ByosFormulario02.Clase).setMontodescuentolfinal(BigDecimal.valueOf(ModuloComprasDescuentos.TotalMontoDescuento));
	                	   ((tblCompras)ByosFormulario03.Clase).setMontodescuentolfinal(BigDecimal.valueOf(ModuloComprasDescuentos.TotalMontoDescuento));
	                	   ByosFormulario03.refrescar();
	                       
	                	   ModuloComprasDescuentos.closeWindows();  
	                	}else{
	                	   Notification.show(Estado , Notification.TYPE_TRAY_NOTIFICATION);
	                	}
	                    
	                } catch (Exception e) {
	                    // Ingnored, we'll let the Form handle the errors
	                }
	            }
	        }); 			
			
			ModuloComprasDescuentos.openWindows();
			
		}
		
	}	
	
    
}
