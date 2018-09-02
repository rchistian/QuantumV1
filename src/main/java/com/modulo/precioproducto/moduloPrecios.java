package com.modulo.precioproducto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.modulo.componentes.ByosConversores;
import com.modulo.historicoprecios.rptPrecios;
import com.modulo.impuestos.tblImpuestos;
import com.modulo.medidasdefault.tblMedidasDefault;
import com.modulo.producto.tblProducto;
import com.modulo.productoimpuesto.tblProductoImpuesto;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.productomedida.tblTableProductoMedida;
import com.modulo.productomedidadefault.tblProductoMedidaDefault;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.filters.LikeFilter;
import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.CellStyleGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;


public class moduloPrecios extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    public Window subwindow;
    private tblProducto TblProducto;
    //public final Table tableListado = new Table(); 
    public VerticalLayout layout = new VerticalLayout();
    public VerticalLayout Maillayout = new VerticalLayout();
    
    public ByosBoton btoAceptar = new ByosBoton();
    public ByosBoton btoCancelar = new ByosBoton();
    private VerticalLayout vlayout01 = new VerticalLayout();
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    private HorizontalLayout hlayout02 = new HorizontalLayout();
    private HorizontalLayout hlayout03 = new HorizontalLayout();

    public ByosCampo bcCodigoProducto = new ByosCampo();
    public ByosCampo bcIva = new ByosCampo();
    public ByosCampo bcCosto = new ByosCampo();
    public ByosCampo bcCalculoCosto = new ByosCampo();
    public ByosCampo bcPorcentajeAumento = new ByosCampo();
    public ByosCampo bcUnidadesMedidas = new ByosCampo();
    public ByosCampo bcFactorRedondeo = new ByosCampo();
    public ByosBoton BtoEstadisticas = new ByosBoton(31, "Estadisticas","");
    public Label lblImpuesto = new Label();

    public TextField Campo = new TextField();
    public int MaxReg = 1;
    public int Posicion=1;
    
    ByosColumna[] ByosColumnas = new ByosColumna[9];
    
    ByosDatagridFiltrableTextbox ListaPrecios;
    ArrayList <tblTablePrecios>ArrayTblTablePrecios = new ArrayList <tblTablePrecios>();
    
    @SuppressWarnings("deprecation")
	public moduloPrecios(final tblProducto TblProducto){ 

    	this.TblProducto = TblProducto;

        btoAceptar.setBoton(32,"Aceptar","Aceptar");        
        btoCancelar.setBoton(33,"Cancelar","Cancelar"); 
       
        layout.setMargin(true);
        layout.setSpacing(true);
        subwindow = new Window("Actualizaciones de Costos y Precios");
        subwindow.setWidth("87em");
        subwindow.setHeight("49em");
        subwindow.setModal(true);       
        subwindow.setContent(layout);
        
        initAddressList();
        
        hlayout01.setMargin(false);
        hlayout01.setSpacing(false);
        hlayout01.setImmediate(true);
        hlayout01.addComponent(btoAceptar);
        hlayout01.setComponentAlignment(btoAceptar,Alignment.BOTTOM_CENTER);
        hlayout01.addComponent(btoCancelar);
        hlayout01.setComponentAlignment(btoCancelar, Alignment.BOTTOM_CENTER);          
        
        hlayout02.setImmediate(true);
        hlayout02.setMargin(false);
        hlayout02.setSpacing(false);
        
        hlayout03.setImmediate(true);
        hlayout03.setMargin(false);
        hlayout03.setSpacing(false);        

        

        //bcCodigoProducto.setComponentAlignment(bcCodigoProducto.btoBoton1, Alignment.BOTTOM_RIGHT);
        //bcCodigoProducto.setComponentAlignment(bcCodigoProducto.lblDescripcion, Alignment.BOTTOM_RIGHT);
        bcCodigoProducto.txtTexto.setVisible(false);
        bcCodigoProducto.lblNombreCampo.setCaption("Producto:");
        bcCodigoProducto.lblNombreCampo.setVisible(true);
        bcCodigoProducto.lblNombreCampo.setWidth("5em");
        bcCodigoProducto.lblDescripcion.setVisible(true);
        bcCodigoProducto.lblDescripcion.setWidth("17em");
        bcCodigoProducto.lblDescripcion.setValue(TblProducto.getDescripcioncorta());
        bcCodigoProducto.lblNombreCampo.setValue(TblProducto.getCodigoproducto()); 
        bcCodigoProducto.setComponentAlignment(bcCodigoProducto.lblDescripcion, Alignment.BOTTOM_RIGHT);
        bcCodigoProducto.txtTexto.setVisible(false);
        
        bcIva.setTipoCampo(bcCodigoProducto.CAMPO_COMBOBOX);
        bcIva.lblNombreCampo.setVisible(false);
        bcIva.CboxItem.setCaption("Impuesto:");
        bcIva.CboxItem.setContainerDataSource(new tblImpuestos().getImpuestosContainer());
        bcIva.CboxItem.setWidth("9em");
        /*bcIva.setComponentAlignment(bcIva.lblNombreCampo, Alignment.BOTTOM_RIGHT);
        bcIva.setComponentAlignment(bcIva.CboxItem, Alignment.BOTTOM_RIGHT);*/
        bcIva.CboxItem.setImmediate(true);
        bcIva.CboxItem.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                if(bcIva.CboxItem.getValue()!=null && !bcIva.CboxItem.getValue().toString().equals("")){
                   String xCodigoImpuesto = bcIva.CboxItem.getValue().toString().split(",")[0];
                   tblImpuestos TblImpuestos = new tblImpuestos();
                   try{
 					  if(TblImpuestos.buscarCodigo(xCodigoImpuesto)){					 
 						  lblImpuesto.setValue(ByosConversores.BigDecimalToString(TblImpuestos.getPorcentaje(),2));
 					  }else{
 						  lblImpuesto.setValue("0.00");
 					  }
 				   }catch (Exception e) {
 					// TODO Auto-generated catch block
 					  e.printStackTrace();
 					  lblImpuesto.setValue("0.00");
 				   }
                }else{
                    lblImpuesto.setValue("0.00");
                }
                ActualizarMontos();
            }
        });

        
        lblImpuesto.setCaption("% IVA:");
        lblImpuesto.setStyleName("align-right");        
        try {
			tblProductoImpuesto[] TblProductoImpuesto = TblProducto.TblProductoImpuestos;
			
			if(TblProductoImpuesto!=null && TblProductoImpuesto.length>0){
				bcIva.CboxItem.setValue(TblProductoImpuesto[0].TblImpuestos.getCodigoimpuesto() + "," + TblProductoImpuesto[0].TblImpuestos.getDescripcion());
				lblImpuesto.setValue(ByosConversores.BigDecimalToString(TblProductoImpuesto[0].TblImpuestos.getPorcentaje(),2));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        bcUnidadesMedidas.CboxItem.setContainerDataSource(new tblProductoMedida().getProductoMedidaContainer(TblProducto.getCodigoproducto()));
        
        if(TblProducto.getCodigomedida()!=null && TblProducto.TblUnidadMedida.getDescripcion()!=null && !TblProducto.getCodigomedida().equals("") && !TblProducto.TblUnidadMedida.getDescripcion().equals("")){
           String UnidadDefault = TblProducto.getCodigomedida() + "," + TblProducto.TblUnidadMedida.getDescripcion();
           System.out.println(UnidadDefault);
           bcUnidadesMedidas.CboxItem.setValue(UnidadDefault);
        }
        
        bcUnidadesMedidas.setTipoCampo(bcCodigoProducto.CAMPO_COMBOBOX);
        bcUnidadesMedidas.lblNombreCampo.setVisible(false);
        bcUnidadesMedidas.CboxItem.setCaption("Unidades de Medidas:");
        bcUnidadesMedidas.CboxItem.setWidth("10em");
        /*bcUnidadesMedidas.setComponentAlignment(bcUnidadesMedidas.lblNombreCampo, Alignment.BOTTOM_RIGHT);
        bcUnidadesMedidas.setComponentAlignment(bcUnidadesMedidas.CboxItem, Alignment.BOTTOM_RIGHT);*/
        bcUnidadesMedidas.CboxItem.setImmediate(true);
        bcUnidadesMedidas.CboxItem.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
            	ActualizarCalculoCosto(0);
            }
        });
        
        
        bcCosto.setTipoCampo(bcCosto.CAMPO_NUMBERFIELD);
        //bcCosto.txtNumerico.setEnabled(false);
        bcCosto.lblNombreCampo.setVisible(false);
        bcCosto.txtNumerico.setCaption("Costo U.Minima:");
        bcCosto.txtNumerico.setWidth("7em");
        bcCosto.txtNumerico.setValue(ByosConversores.BigDecimalToString(TblProducto.getCosto(),3));
        bcCosto.btoBoton1.setVisible(true);
        bcCosto.btoBoton1.setBoton(18, "Actualizar Precios","");
        bcCosto.setComponentAlignment(bcCosto.btoBoton1, Alignment.BOTTOM_RIGHT);
        bcCosto.setDecimales(3);
        bcCosto.btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try{
                	ActualizarCostos();                      
                }catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
        
        //Pierde el Foco
        /*bcCosto.txtNumerico.addListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
            	bcCosto.txtNumerico.setValue(ByosConversores.BigDecimalToString(ByosConversores.StringToBigDecimal(bcCosto.txtNumerico.getValue()),3));
            	
            }
        });*/
        
        bcCalculoCosto.setTipoCampo(bcCosto.CAMPO_NUMBERFIELD);
        bcCalculoCosto.lblNombreCampo.setVisible(false);
        bcCalculoCosto.txtNumerico.setCaption("Costo Medida:");
        bcCalculoCosto.txtNumerico.setWidth("7em");
        bcCalculoCosto.setDecimales(3);
        bcCalculoCosto.txtNumerico.setValue(ByosConversores.BigDecimalToString(TblProducto.getCosto(),3));
        //Pierde el Foco
        /*bcCalculoCosto.txtNumerico.addListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
            	bcCalculoCosto.txtNumerico.setValue(ByosConversores.BigDecimalToString(ByosConversores.StringToBigDecimal(bcCalculoCosto.txtNumerico.getValue()),3));
            	
            }
        });*/
        
        bcPorcentajeAumento.setTipoCampo(bcCosto.CAMPO_NUMBERFIELD);
        bcPorcentajeAumento.lblNombreCampo.setVisible(false);
        bcPorcentajeAumento.txtNumerico.setCaption("% X Flete:");
        bcPorcentajeAumento.setDecimales(3);
        bcPorcentajeAumento.txtNumerico.setWidth("6em");
        bcPorcentajeAumento.btoBoton1.setVisible(true);
        bcPorcentajeAumento.btoBoton1.setBoton(22, "% Aumento Costo Por Flete y Otros Gastos","");
        bcPorcentajeAumento.txtNumerico.setValue("0.000");
        bcPorcentajeAumento.setComponentAlignment(bcPorcentajeAumento.btoBoton1, Alignment.BOTTOM_RIGHT);
        bcPorcentajeAumento.btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               ActualizarCalculoCosto(1);
            }
        });       
        //Pierde el Foco
        /*bcPorcentajeAumento.txtNumerico.addListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
            	bcPorcentajeAumento.txtNumerico.setValue(ByosConversores.BigDecimalToString(ByosConversores.StringToBigDecimal(bcPorcentajeAumento.txtNumerico.getValue()),3));
            	
            }
        });  */  
        
        
        bcFactorRedondeo.setTipoCampo(bcCosto.CAMPO_NUMBERFIELD);
        bcFactorRedondeo.lblNombreCampo.setVisible(false);
        bcFactorRedondeo.txtNumerico.setCaption("Factor Redondeo:");
        bcFactorRedondeo.txtNumerico.setWidth("6em");
        bcFactorRedondeo.btoBoton1.setVisible(true);
        bcFactorRedondeo.btoBoton1.setBoton(23, "Redondiar Precios","");
        bcFactorRedondeo.txtNumerico.setValue("1");
        bcFactorRedondeo.setComponentAlignment(bcFactorRedondeo.btoBoton1, Alignment.BOTTOM_RIGHT);
        bcFactorRedondeo.setDecimales(0);
        bcFactorRedondeo.btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	int Factor=Integer.valueOf(bcFactorRedondeo.txtNumerico.getValue());
            	RedondiarPrecios(Factor);
            }
        });       
        //Pierde el Foco
        /*bcFactorRedondeo.txtNumerico.addListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
            	bcFactorRedondeo.txtNumerico.setValue(ByosConversores.BigDecimalToString(ByosConversores.StringToBigDecimal(bcFactorRedondeo.txtNumerico.getValue()),0));
            	
            }
        });   */         
 
        BtoEstadisticas.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	new rptPrecios(TblProducto);
            }
        });         
        
        hlayout02.addComponent(bcCodigoProducto);
        hlayout02.addComponent(BtoEstadisticas);
        hlayout03.addComponent(bcUnidadesMedidas);
        hlayout03.addComponent(bcCalculoCosto);
        hlayout03.addComponent(bcPorcentajeAumento);
        hlayout03.addComponent(bcCosto);
        hlayout03.addComponent(bcFactorRedondeo);
        hlayout03.addComponent(bcIva);
        hlayout03.addComponent(lblImpuesto);
        
        
        vlayout01.setMargin(false);
        vlayout01.setSpacing(false);
        vlayout01.setImmediate(true);
        vlayout01.addComponent(ListaPrecios);
        
        layout.addComponent(hlayout02);
        layout.setComponentAlignment(hlayout02, Alignment.TOP_LEFT);
        layout.addComponent(hlayout03);
        layout.setComponentAlignment(hlayout03, Alignment.TOP_LEFT);        
        layout.addComponent(vlayout01);
        layout.setComponentAlignment(vlayout01, Alignment.TOP_RIGHT);
        layout.addComponent(hlayout01);
        layout.setComponentAlignment(hlayout01, Alignment.TOP_CENTER);  

    }
    
    public void ActualizarCalculoCosto(int Tipo){
        if(bcUnidadesMedidas.CboxItem.getValue()!=null && !bcUnidadesMedidas.CboxItem.getValue().toString().equals("")){
        	tblProductoMedida TblProductoMedidas = new tblProductoMedida();
        	String[] Medidas = bcUnidadesMedidas.CboxItem.getValue().toString().split(",");
        	try {
				if(TblProductoMedidas.buscarCodigo(TblProducto.getCodigoproducto(), Medidas[0])){
					BigDecimal Costo = BigDecimal.valueOf(0);
					if(TblProductoMedidas.getEquivalencia()!=null && TblProductoMedidas.getEquivalencia().doubleValue()!=0){
						if(Tipo==1){
						   Costo = BigDecimal.valueOf(Double.valueOf(bcCalculoCosto.txtNumerico.getValue())/TblProductoMedidas.getEquivalencia().doubleValue());						   
						   BigDecimal Aumento = ByosConversores.StringToBigDecimal(bcPorcentajeAumento.txtNumerico.getValue());						   
						   if(Aumento.doubleValue()>0){
							  Costo = ByosConversores.CalculoCostoMasFactorUtilidad(Costo, Aumento);
						   }						   
						}else{
						   Costo = BigDecimal.valueOf(Double.valueOf(bcCosto.txtNumerico.getValue())*TblProductoMedidas.getEquivalencia().doubleValue()); 
						}
					}
					if(Tipo==1){
					   bcCosto.txtNumerico.setValue(ByosConversores.BigDecimalToString(Costo,3));
					}else{
					   bcCalculoCosto.txtNumerico.setValue(ByosConversores.BigDecimalToString(Costo,3));	
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
    }
    
    public void ActualizarMontos(){
       if(ArrayTblTablePrecios.size()>0){
          for(int f=0;f<ArrayTblTablePrecios.size();f++){
         	  final tblTablePrecios p = (tblTablePrecios)ArrayTblTablePrecios.get(f);
         	  p.setIva(ByosConversores.StringToBigDecimal(lblImpuesto.getValue())); 
         	  p.setPrecioiva(ByosConversores.CalculoMontoMasImpuesto(p.getPrecio(),p.getIva()));
         	  p.setUtilidadreal(ByosConversores.CalculoFactorUtilidad(p.getCosto(),p.getPrecio()));
          }
          //ListaPrecios.getDatagrid().commit();
          //ListaPrecios.getDatagrid().setEnabled(true);
          ListaPrecios.getDatagrid().refreshRowCache();
       }
    }
    
    public void ActualizarCostos(){
    	//System.out.println("Antes: " + ArrayTblTablePrecios.size());
    	BigDecimal xCosto = ByosConversores.StringToBigDecimal(bcCosto.txtNumerico.getValue());
    	//TblProducto.setCosto(xCosto);
    	if(ArrayTblTablePrecios.size()>0){	
           for(int f=0;f<ArrayTblTablePrecios.size();f++){
          	  final tblTablePrecios p = (tblTablePrecios)ArrayTblTablePrecios.get(f);
          	  if(p.getEquivalenciavisible()!=null){
          	     p.setCostovisible(BigDecimal.valueOf(xCosto.doubleValue()*p.getEquivalencia().doubleValue()));
          	  }
          	  p.setCosto(BigDecimal.valueOf(xCosto.doubleValue()*p.getEquivalencia().doubleValue()));
          	  p.setIva(ByosConversores.StringToBigDecimal(lblImpuesto.getValue()));
          	  p.setPrecio(ByosConversores.CalculoCostoMasFactorUtilidad(p.getCosto(), p.getUtilidad()));
          	  p.setPrecioiva(ByosConversores.CalculoMontoMasImpuesto(p.getPrecio(),p.getIva()));
          	  p.setUtilidadreal(ByosConversores.CalculoFactorUtilidad(p.getCosto(),p.getPrecio()));
           }
           ListaPrecios.getDatagrid().refreshRowCache();
        }
    }

    public void RedondiarPrecios(int Factor){
    	if(ArrayTblTablePrecios.size()>0){	
           for(int f=0;f<ArrayTblTablePrecios.size();f++){
          	  final tblTablePrecios p = (tblTablePrecios)ArrayTblTablePrecios.get(f);
          	  BigDecimal Precio = ByosConversores.Redondeo(p.getPrecioiva(),Factor);
          	  
          	  p.setPrecioiva(Precio);
          	  p.setPrecio(ByosConversores.CalculoMontoSinImpuesto(Precio, p.getIva()));
          	  p.setUtilidadreal(ByosConversores.CalculoFactorUtilidad(p.getCosto(), p.getPrecio()));
          	  
           }
           ListaPrecios.getDatagrid().refreshRowCache();
        }
    }    
    
    
    private void initAddressList() {
    	if(TblProducto.TblProductoMedidas!=null && TblProducto.TblProductoMedidas.length>0){
    		MaxReg=TblProducto.TblProductoMedidas.length;
    	}
    	ListaPrecios = new ByosDatagridFiltrableTextbox(false,1000,370);
    	//ListaPrecios.setSizeUndefined();
    	ListaPrecios.botProcesar.setVisible(false);
    	ListaPrecios.botCancelar.setVisible(false);
    	
    	//ByosColumnas[0] = new ByosColumna("id",                            String.class, "Item",                "", null);
    	//ByosColumnas[0] = new ByosColumna("codigomedida",                  String.class, "Codigo",                "", null);
    	ByosColumnas[0] = new ByosColumna("descripcion",                   String.class, "Descripicon",           "", null);
        ByosColumnas[1] = new ByosColumna("productosmedidasdefaultcodigo", String.class, "Modulo",                "", null);
    	ByosColumnas[2] = new ByosColumna("equivalenciavisible",           String.class, "Equivalencia",          "", null);
    	ByosColumnas[3] = new ByosColumna("costovisible",                  String.class, "Ultimo Costo",          "", null);
    	ByosColumnas[4] = new ByosColumna("descripcionprecio",             String.class, "Tipo Precio",           "", null);
    	ByosColumnas[5] = new ByosColumna("precio",                        String.class, "Precio",                "", null);
    	ByosColumnas[6] = new ByosColumna("precioiva",                     String.class, "Precio + IVA",          "", null);
    	ByosColumnas[7] = new ByosColumna("utilidad",                      String.class, "% Utilidad",            "", null);
    	ByosColumnas[8] = new ByosColumna("utilidadreal",                  String.class, "% Utilidad Real",       "", null);
    	
    	
    	
    	
    	try {
    		//ArrayTblTablePrecios  = new tblProductoMedida().UnidadesMedidasToArrayList(TblProducto.TblProductoMedidas);
    		ArrayTblTablePrecios  = new tblTablePrecios().LoadTblTablePrecioArray(TblProducto,"Todos");
    		ActualizarMontos();
			ListaPrecios.getDatagrid().initDatagridByos(ArrayTblTablePrecios , new tblTablePrecios(), ByosColumnas, false);
			
			ListaPrecios.getDatagrid().setColumnAlignment("costovisible",Table.ALIGN_RIGHT);
			ListaPrecios.getDatagrid().setColumnAlignment("productosmedidasdefaultcodigo",Table.ALIGN_RIGHT);
			ListaPrecios.getDatagrid().setColumnAlignment("equivalencia",Table.ALIGN_RIGHT);
			ListaPrecios.getDatagrid().setImmediate(true);
			ListaPrecios.getDatagrid().setCellStyleGenerator(new Table.CellStyleGenerator() {
				@Override
				public String getStyle(Table source, Object itemId, Object propertyId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
			        if (propertyId == null){
			            return "green"; 
			        }
  
			        int row = Integer.valueOf(p.id);

			        if (p.id==0){
			            return "black";
			        }else{
			            return "white";
			        }

				}
			});
			
			
			ListaPrecios.getDatagrid().addGeneratedColumn("precio", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.setDecimales(3);
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getPrecio(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
							   p.setPrecio(ByosConversores.StringToBigDecimal(event.getText()));
							}
						}
					});
					
					//Pierde el Foco
					ByosCampo01.txtNumerico.addListener(new FieldEvents.BlurListener() {
			            @Override
			            public void blur(FieldEvents.BlurEvent event) {
			                p.setPrecioiva(ByosConversores.CalculoMontoMasImpuesto(p.getPrecio(),p.getIva()));
			                p.setUtilidadreal(ByosConversores.CalculoFactorUtilidad(p.getCosto(), p.getPrecio()));
			            	//source.refreshRowCache();
			            	source.commit();
			            	source.setEnabled(true);			            	
			            }
			        });

					return ByosCampo01;
				}
			});	

			ListaPrecios.getDatagrid().addGeneratedColumn("precioiva", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.setDecimales(2);;

					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getPrecioiva(),2));

					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setPrecioiva(ByosConversores.StringToBigDecimal(event.getText()));
							}
							
						}
					});
					
					//Pierde el Foco
					ByosCampo01.txtNumerico.addListener(new FieldEvents.BlurListener() {
						@Override
			            public void blur(final FieldEvents.BlurEvent event) {
			            	p.setPrecio(ByosConversores.CalculoMontoSinImpuesto(p.getPrecioiva(),p.getIva()));
			            	p.setUtilidadreal(ByosConversores.CalculoFactorUtilidad(p.getCosto(), p.getPrecio()));
			            	//source.refreshRowCache();
			            
			            	source.commit();
			            	source.setEnabled(true);
			            }
			        });
			
					return ByosCampo01;
				}
			});		
			
			ListaPrecios.getDatagrid().addGeneratedColumn("utilidad", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.setDecimales(3);
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getUtilidad(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setUtilidad(ByosConversores.StringToBigDecimal(event.getText()));
							}
							
						}
					});
					
					return ByosCampo01;
				}
			});	
			
			ListaPrecios.getDatagrid().addGeneratedColumn("utilidadreal", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.setDecimales(3);
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getUtilidadreal(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setUtilidadreal(ByosConversores.StringToBigDecimal(event.getText()));
							}
							
						}
					});
					
					//Pierde el Foco
					ByosCampo01.txtNumerico.addListener(new FieldEvents.BlurListener() {
						@Override
			            public void blur(final FieldEvents.BlurEvent event) {
			            	p.setPrecio(ByosConversores.CalculoCostoMasFactorUtilidad(p.getCosto(),p.getUtilidadreal()));
			            	p.setPrecioiva(ByosConversores.CalculoMontoMasImpuesto(p.getPrecio(),p.getIva()));		            	
			            	//source.refreshRowCache();
			            	source.commit();
			            	source.setEnabled(true);			            	
			            }
			        });
					
					return ByosCampo01;
				}
			});				
			
			ListaPrecios.getDatagrid().addGeneratedColumn("productosmedidasdefaultcodigo", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					//ByosCampo01.lblDescripcion.setWidth("10em");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("4em");
					ByosCampo01.lblDescripcion.setStyleName("align-right");
					ByosCampo01.lblDescripcion.setValue(p.getProductosmedidasdefaultcodigo());
					ByosCampo01.lblDescripcion.setDescription(p.getProductosmedidasdefaultdescripcion());			
					return ByosCampo01;
				}
			});
			
			ListaPrecios.getDatagrid().addGeneratedColumn("equivalenciavisible", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblNombreCampo.setVisible(true);
					ByosCampo01.lblDescripcion.setVisible(false);
					ByosCampo01.lblNombreCampo.setWidth("5em");
					ByosCampo01.lblNombreCampo.setStyleName("align-right");
					if(p.getEquivalenciavisible()!=null){
					   ByosCampo01.lblNombreCampo.setValue(ByosConversores.BigDecimalToString(p.getEquivalenciavisible(),3));
					}

					return ByosCampo01;
				}
			});	
		
			ListaPrecios.getDatagrid().addGeneratedColumn("costovisible", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblNombreCampo.setVisible(true);
					ByosCampo01.lblDescripcion.setVisible(false);
					ByosCampo01.lblNombreCampo.setWidth("5em");
					ByosCampo01.lblNombreCampo.setStyleName("align-right");
					if(p.getCostovisible()!=null){
					   ByosCampo01.lblNombreCampo.setValue(ByosConversores.BigDecimalToString(p.getCostovisible(),2));
					}

					return ByosCampo01;
				}
			});			
			
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

    public boolean ValidarPrecioProducto(){
       if(bcCosto.txtNumerico==null || bcCosto.txtNumerico.getValue().equals("")){
    	  Notification.show("Error","No a introdicido el costo del producto", Notification.TYPE_TRAY_NOTIFICATION);
    	  return false;
       }
       
       for(int f=0;f<ArrayTblTablePrecios.size();f++){  
    	   tblTablePrecios TblPrecioProducto = ((tblTablePrecios)ArrayTblTablePrecios.get(f)); 
           if((TblPrecioProducto.getPrecio()==null || TblPrecioProducto.getPrecio().doubleValue()<0)){
        	  Notification.show("Error","Error en el Precio en la fila " + (f+1) , Notification.TYPE_TRAY_NOTIFICATION); 
              return false;             
           }
           if(TblPrecioProducto.getCosto().doubleValue()<0){
        	  Notification.show("Error","El Costo no puede ser negativo en la fila " + (f+1) , Notification.TYPE_TRAY_NOTIFICATION); 
              return false;             
           }           
           if((TblPrecioProducto.getPrecio().doubleValue()<TblPrecioProducto.getCosto().doubleValue())){
        	  Notification.show("Error","Error el Precio no puede ser menor al Costo en la fila " + (f+1) , Notification.TYPE_TRAY_NOTIFICATION); 
              return false;             
           }
           if((TblPrecioProducto.getUtilidadreal().doubleValue()<TblProducto.getUtilidadminima().doubleValue())){
        	  Notification.show("Error","La utilidad real es Monor a la utilidad minima en la fila " + (f+1) , Notification.TYPE_TRAY_NOTIFICATION); 
              return false;             
           }
           
           if((TblPrecioProducto.getUtilidadreal().doubleValue()>TblProducto.getUtilidadmaxima().doubleValue())){
        	  Notification.show("Precausion","La utilidad real es Mayor a la utilidad Maxima en la fila " + (f+1) , Notification.TYPE_TRAY_NOTIFICATION);              
           }
           if(TblPrecioProducto.getCosto().doubleValue()==0){
        	  Notification.show("Precausion","El Costo es igual a 0 en la fila " + (f+1) , Notification.TYPE_TRAY_NOTIFICATION);            
           }
           if(TblPrecioProducto.getPrecio().doubleValue()==0){
        	  Notification.show("Precausion","El precio es igual a 0 en la fila " + (f+1) , Notification.TYPE_TRAY_NOTIFICATION);          
           }             
       }             
       return true;	
    }

    public tblPrecioProducto[] getTblPrecioPorducto(){
       tblPrecioProducto[] TblPrecioProducto = new tblPrecioProducto[ArrayTblTablePrecios.size()];
       for(int f=0;f<TblPrecioProducto.length;f++){
    	   TblPrecioProducto[f] = new tblPrecioProducto();
    	   tblPrecioProducto Tbl = ((tblTablePrecios) ArrayTblTablePrecios.get(f)).TblPrecioProducto.getPrecioProducto();
    	   TblPrecioProducto[f].setPrecioProducto(Tbl);
       }
       return TblPrecioProducto;	
    }
    
    public tblProductoImpuesto[] gettblProductoImpuesto(){
    	if(bcIva.CboxItem.getValue()!=null && !bcIva.CboxItem.getValue().toString().equals("")){
    		tblProductoImpuesto[] TblProductoImpuesto = new tblProductoImpuesto[1];
    		TblProductoImpuesto[0] = new tblProductoImpuesto();
            String xCodigoImpuesto = bcIva.CboxItem.getValue().toString().split(",")[0];
            TblProductoImpuesto[0].setCodigoimpuesto(xCodigoImpuesto);
            TblProductoImpuesto[0].setCodigoproducto(TblProducto.getCodigoproducto());
            try {
				TblProductoImpuesto[0].TblImpuestos.buscarCodigo(xCodigoImpuesto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return TblProductoImpuesto;
    	}      		
    	return null;
    }
     
    public BigDecimal getCosto(){
       return ByosConversores.StringToBigDecimal(bcCosto.txtNumerico.getValue());
    }

	    
}