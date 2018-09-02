package com.modulo.compras;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosValidar;
import com.modulo.componentes.utilString;
import com.modulo.medidasdefault.tblMedidasDefault;
import com.modulo.producto.tblProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.modulo.productoimpuesto.tblProductoImpuesto;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.productomedidadefault.tblProductoMedidaDefault;
import com.modulo.traslado.tblTrasladoItem;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class moduloComprasItem extends VerticalLayout  implements Serializable{

	private static final long serialVersionUID = 1L;
	
    public ByosForm ByosFormulario01 = new ByosForm();
    public tblComprasItem ClaseCompras01;	
    tblCompras TblCompras;
	
	String DatosVisibles01[] = {   	
	   "item",	   
	   "codigoproducto",	   
	   "descripcionproducto",	
	   "descripcionmedida",
	   "equivalencia",
	   "costo",	   
	   "costoreal",	
	   "cantidad",		   
	   "cantidadreal",	      
	   "montodescuento",	
	   "montodescuentofinal",
	   "montoimpuesto",
	   "montototal",
	   "fechavencimiento",	   
	   "detalle01"
	};
	
	String DatosTitulos01[] = {	 	   	
	   "Item",
	   "Codigo Producto",	   
	   "Descripcion Producto",
	   "Descripcion Medida",  
	   "Equivalencia",	
	   "Costo",	   
	   "Costo Real",	
	   "Cantidad",		   
	   "Cantidad Real",	   
	   "Monto Descuento",
	   "monto Descuento Final",
	   "Monto Impuesto",
	   "Total Item",
	   "Fecha Vencimiento",	 	   
	   "Detalle"	 
	};	

    public ByosBoton btoAceptar;
    public ByosBoton btoCancelar;
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    
    public moduloComprasItem(){
    	
        ClaseCompras01 = new tblComprasItem(); 
        ByosFormulario01.setClase(ClaseCompras01, DatosVisibles01, DatosTitulos01, null); 
        
        String Ancho="110px";
        for(int f=0;f<DatosVisibles01.length;f++){
        	ByosFormulario01.getCampo(DatosVisibles01[f]).setAncho(Ancho);
        }
        
    	btoAceptar = new ByosBoton(hlayout01,32,"Aceptar","Aceptar");  ;
    	btoCancelar = new ByosBoton(hlayout01,33,"Cancelar","Cancelar");

        //hlayout01.setComponentAlignment(btoAceptar,Alignment.BOTTOM_CENTER);
        //hlayout01.setComponentAlignment(btoCancelar, Alignment.BOTTOM_CENTER); 
        
        
        setMargin(false);
        ByosFormulario01.setMargin(false);
        hlayout01.setMargin(false);
        initComponentes();
        addComponent(ByosFormulario01);        
        addComponent(hlayout01);
        ActulizarMontos(); 
    }
    
    public void setTblComprasItem(tblComprasItem Item, tblCompras TblCompras){
    	ClaseCompras01.limpiar();
    	this.TblCompras=TblCompras;
    	//ByosFormulario01.getCampo("descripcionmedida").CboxItem.setValue(null);
    	ClaseCompras01.setTblComprasItem(Item);
    	buscarProducto(Item);
    	CargarMedidas(Item);

    	if(Item.getMedidas()!=null && Item.getDescripcionmedida()!=null && !Item.getDescripcionmedida().equals("")){
     	   ByosFormulario01.getCampo("descripcionmedida").CboxItem.setValue(Item.getDescripcionmedida());
    	}else{
           ByosFormulario01.getCampo("descripcionmedida").CboxItem.setContainerDataSource(ByosContenedores.getContainerNull());
           ByosFormulario01.getCampo("descripcionmedida").CboxItem.setValue("");
        }
    	ActulizarMontos();
    	ByosFormulario01.refrescar();
    	
    }
    
    public void initComponentes(){
    	
    	ByosFormulario01.getCampo("equivalencia").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("costoreal").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("costo").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("cantidad").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("cantidadreal").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("montodescuento").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("montodescuentofinal").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("montoimpuesto").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("montototal").setTipoCampo(utilString.CAMPO_NUMBERFIELD);
    	ByosFormulario01.getCampo("fechavencimiento").setTipoCampo(utilString.CAMPO_FECHAHORA);
    	
    	ByosFormulario01.getCampo("equivalencia").setDecimales(3);
    	ByosFormulario01.getCampo("costoreal").setDecimales(5);
    	ByosFormulario01.getCampo("costo").setDecimales(3);
    	ByosFormulario01.getCampo("cantidad").setDecimales(3);
    	ByosFormulario01.getCampo("cantidadreal").setDecimales(3);
    	ByosFormulario01.getCampo("montodescuento").setDecimales(3);
    	ByosFormulario01.getCampo("montodescuentofinal").setDecimales(3);
    	ByosFormulario01.getCampo("montototal").setDecimales(3);
    	ByosFormulario01.getCampo("montoimpuesto").setDecimales(3);   	
    	
    	
    	ByosFormulario01.getCampo("item").txtTexto.setEnabled(false);
    	ByosFormulario01.getCampo("descripcionproducto").txtTexto.setEnabled(false);    	
    	ByosFormulario01.getCampo("equivalencia").txtNumerico.setEnabled(false);
    	ByosFormulario01.getCampo("costoreal").txtNumerico.setEnabled(false);
    	ByosFormulario01.getCampo("cantidadreal").txtNumerico.setEnabled(false);
    	ByosFormulario01.getCampo("montodescuento").txtNumerico.setEnabled(false);
    	ByosFormulario01.getCampo("montodescuentofinal").txtNumerico.setEnabled(false);
    	ByosFormulario01.getCampo("montoimpuesto").txtNumerico.setEnabled(false);
    	ByosFormulario01.getCampo("montototal").txtNumerico.setEnabled(false);
    	
    	ByosFormulario01.getCampo("costo").txtNumerico.addBlurListener(new FieldEvents.BlurListener(){
            @Override
            public void blur(BlurEvent event) {
               ActulizarMontos();
            }            
        });
    	
    	ByosFormulario01.getCampo("cantidad").txtNumerico.addBlurListener(new FieldEvents.BlurListener(){
            @Override
            public void blur(BlurEvent event) {
               ActulizarMontos();
            }            
        });
    	
    	ByosFormulario01.getCampo("descripcionmedida").setTipoCampo(utilString.CAMPO_COMBOBOX);
    	ByosFormulario01.getCampo("descripcionmedida").CboxItem.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
            	ActualizarMedida();	
            }
        });
    	ByosFormulario01.getCampo("descripcionmedida").CboxItem.setContainerDataSource(ByosContenedores.getContainerNull());
    	ByosFormulario01.getCampo("descripcionmedida").CboxItem.setValue("");
    		
    	ByosFormulario01.getCampo("codigoproducto").btoBoton1.setBoton(49, "Listar","");
    	ByosFormulario01.getCampo("codigoproducto").btoBoton1.setVisible(true);
    	ByosFormulario01.getCampo("codigoproducto").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {          	
               try{
                    procesoListarProducto();   
                  }catch (Exception e) {
                    e.printStackTrace();
                  }
            }
        });
    	
    	ByosFormulario01.getCampo("montodescuento").btoBoton1.setBoton(48, "Agregar Descuentos Lineales","");
    	ByosFormulario01.getCampo("montodescuento").btoBoton1.setVisible(true);
    	ByosFormulario01.getCampo("montodescuento").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {          	
               try{
            	   CargarItemDescuentos();  
                  }catch (Exception e) {
                    e.printStackTrace();
                  }
            }
        });    	
    	
    }
    
	public void procesoListarProducto(){  
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
	              try{ 
	            	  ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	  if(AL.size()>0){
	    			     tblProducto tbl = ((tblProducto)AL.get(0));
	                     ((tblComprasItem)ByosFormulario01.Clase).setCodigoproducto(tbl.getCodigoproducto());
	                     ((tblComprasItem)ByosFormulario01.Clase).setDescripcionproducto(tbl.getDescripcioncorta());
	                     buscarProducto((tblComprasItem)ByosFormulario01.Clase);
	                     ByosFormulario01.refrescar();  
	                     dwb.cerrarWindow();
	            	  }    
	              }catch(Exception e) {
	                   e.printStackTrace();
	              }      
	           } 
	  	   });    
	   }catch(Exception e) {
	       e.printStackTrace();
	   }			       
	}
	
	
	public void buscarProducto(final tblComprasItem TblComprasItem){
		   tblProducto TblProducto = new tblProducto();
		   
		   try{ 
			  if(TblComprasItem.getCodigoproducto()==null || TblComprasItem.getCodigoproducto().equals("")){
				 TblComprasItem.limpiar();  
				 ByosFormulario01.refrescar();                  
			  }else{
			        if(TblProducto.buscarCodigo(TblComprasItem.getCodigoproducto())){
				       tblProductoMedida TblProductoMedidas = new tblProductoMedida();
			           TblComprasItem.setDescripcionproducto(TblProducto.getDescripcioncorta());
			           
			           if(!TblProductoMedidas.buscarCodigo(TblComprasItem.getCodigoproducto(), TblComprasItem.getCodigomedida())){	
			        	  if(TblProductoMedidas.buscarCodigo(TblComprasItem.getCodigoproducto(), TblProducto.getCodigomedida())){			        		 
				             TblComprasItem.setCodigomedida(TblProducto.getCodigomedida());
				             TblComprasItem.setDescripcionmedida(TblProducto.getCodigomedida() + "," + TblProducto.TblUnidadMedida.getDescripcion());
				             TblComprasItem.TblUnidadMedida.buscarCodigo(TblComprasItem.getCodigomedida());
			        	  }else{
				             String xCodigoproducto=TblComprasItem.getCodigoproducto();
				             String xDescripcionproducto=TblComprasItem.getDescripcionproducto();
				             Integer xItem=TblComprasItem.getItem();
				             TblComprasItem.limpiar();
				             TblComprasItem.TblUnidadMedida.limpiar();
				             TblComprasItem.setCodigoproducto(xCodigoproducto);
				             TblComprasItem.setDescripcionproducto(xDescripcionproducto);
				             TblComprasItem.setItem(xItem);
			        	  }
			           }
			           
				       TblComprasItem.setEquivalencia(TblProductoMedidas.getEquivalencia());
			           
			           CargarMedidas(TblComprasItem);

			        }else{
				       TblComprasItem.limpiar();
				       ByosFormulario01.refrescar();
				       Notification.show("Este codigo de producto no existe" , Notification.TYPE_TRAY_NOTIFICATION);
			        }
			        
				 }
		   }catch (Exception e) {
			  e.printStackTrace();
		   }
		   ByosFormulario01.refrescar();
	}
	
	public void CargarMedidas(tblComprasItem TblComprasItem){
        TblComprasItem.setMedidas(new tblProductoMedida().getProductoMedidaContainer(TblComprasItem.getCodigoproducto()));    
        ByosFormulario01.getCampo("descripcionmedida").CboxItem.setContainerDataSource(TblComprasItem.getMedidas());
        if(!TblComprasItem.getCodigomedida().equals("")){
           tblProductoMedidaDefault TblProductoMedidaDefault = new tblProductoMedidaDefault();
           try {
			   if(TblProductoMedidaDefault.buscarCodigo(TblComprasItem.getCodigoproducto(), TblCompras.getCodigodefault())){
				  ByosFormulario01.getCampo("descripcionmedida").CboxItem.setValue(TblProductoMedidaDefault.getCodigomedida() + "," + TblProductoMedidaDefault.TblUnidadMedida.getDescripcion()); 
			   }else{
				  ByosFormulario01.getCampo("descripcionmedida").CboxItem.setValue(TblComprasItem.getCodigomedida() + "," + TblComprasItem.TblUnidadMedida.getDescripcion());
			   }
		   } catch (Exception e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		   }
           
        }else{
           ByosFormulario01.getCampo("descripcionmedida").CboxItem.setContainerDataSource(ByosContenedores.getContainerNull());
           TblComprasItem.setDescripcionmedida("");	
           ByosFormulario01.getCampo("descripcionmedida").CboxItem.setValue("");
        }
        ByosFormulario01.refrescar();
	}
	
    
	public void ActualizarMedida(){
        if(ByosFormulario01.getCampo("descripcionmedida").CboxItem.getValue()!=null && !ByosFormulario01.getCampo("descripcionmedida").CboxItem.getValue().toString().equals("")){
        	tblProductoMedida TblProductoMedidas = new tblProductoMedida();
        	String[] Medidas = ByosFormulario01.getCampo("descripcionmedida").CboxItem.getValue().toString().split(",");
        	try {
				if(TblProductoMedidas.buscarCodigo(ClaseCompras01.getCodigoproducto(), Medidas[0])){
				   if(TblProductoMedidas.getEquivalencia()!=null && TblProductoMedidas.getEquivalencia().doubleValue()!=0){
					  ClaseCompras01.setEquivalencia(TblProductoMedidas.getEquivalencia());
					  ClaseCompras01.setCodigomedida(TblProductoMedidas.getCodigomedida());
					  ClaseCompras01.setDescripcionmedida(TblProductoMedidas.getCodigomedida() + "," + TblProductoMedidas.getDescripcion());
					  ActulizarMontos();					  
				   }else{
					  ClaseCompras01.setEquivalencia(BigDecimal.valueOf(1));
				   }
				}
            }catch (Exception e) {
                e.printStackTrace();
            }  
        }
        ByosFormulario01.refrescar();
    }	
	
	public void ActulizarMontos(){
		  double Equivalencia = 1;
		  if(ClaseCompras01.getEquivalencia()!=null){
		     Equivalencia = ClaseCompras01.getEquivalencia().doubleValue();
		  }
		  double Cantidad = 0;
		  if(ClaseCompras01.getCantidad()!=null){
		     Cantidad = ClaseCompras01.getCantidad().doubleValue();
		  }					  
		  double Costo = 0;
		  if(ClaseCompras01.getCosto()!=null){
		     Costo = ClaseCompras01.getCosto().doubleValue();
		  }
		  
		  if(ClaseCompras01.getMontodescuento()==null){
			  ClaseCompras01.setMontodescuento(BigDecimal.valueOf(0));  
		  }
		  
		  if(ClaseCompras01.getMontodescuentofinal()==null){
			  ClaseCompras01.setMontodescuentofinal(BigDecimal.valueOf(0));  
		  }		  
		  
		  ClaseCompras01.setCantidadreal(BigDecimal.valueOf(Cantidad*Equivalencia));
		  ClaseCompras01.setCostoreal(BigDecimal.valueOf(Costo/Equivalencia));
		  ActualizarDescuentoFinal();
		  double Montoneto = (ByosValidar.Nulo(ClaseCompras01.getCantidadreal())*ByosValidar.Nulo(ClaseCompras01.getCostoreal()));
		  Montoneto = Montoneto - ByosValidar.Nulo(ClaseCompras01.getMontodescuento()) - ByosValidar.Nulo(ClaseCompras01.getMontodescuentofinal());
		  ClaseCompras01.setMontoneto(BigDecimal.valueOf(Montoneto));
		  tblProductoImpuesto[] TblProductoImpuestos = null;
		  try{
			  TblProductoImpuestos = new tblProductoImpuesto().buscarCodigo(ClaseCompras01.getCodigoproducto());
		  }catch (Exception e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  
		  ClaseCompras01.setMontoimpuesto(ByosConversores.CalculoImpuestoTotal(TblProductoImpuestos, ClaseCompras01.getMontoneto()));
		  double Montototal=Montoneto+ClaseCompras01.getMontoimpuesto().doubleValue();
		  ClaseCompras01.setMontototal(BigDecimal.valueOf(Montototal));
		  ByosFormulario01.refrescar();
	}

	public void ActualizarDescuentoFinal(){
		double Montoneto = (ByosValidar.Nulo(ClaseCompras01.getCantidadreal())*ByosValidar.Nulo(ClaseCompras01.getCostoreal()));
		double Montototal = Montoneto - ByosValidar.Nulo(ClaseCompras01.getMontodescuento());
		double Montodescuentofinal = 0;
		if(TblCompras!=null && TblCompras.TblComprasDescuentos!=null && TblCompras.TblComprasDescuentos.length>0){
		   for(int f=0;f<TblCompras.TblComprasDescuentos.length;f++){
			   if(TblCompras.TblComprasDescuentos[f].getPorcentaje()!=null && TblCompras.TblComprasDescuentos[f].getPorcentaje().doubleValue()>0){
				  double Montodescuentofinal01=Montototal*TblCompras.TblComprasDescuentos[f].getPorcentaje().doubleValue()/100;
				  Montodescuentofinal+=Montodescuentofinal01;
				  Montototal-=Montodescuentofinal01;
			   }
		   }
		}
		ClaseCompras01.setMontodescuentofinal(BigDecimal.valueOf(Montodescuentofinal));
	}
	
	public void CargarItemDescuentos(){
		final ArrayList <tblComprasItemDescuentos> ArrayTblComprasItemDescuentos = new ArrayList<tblComprasItemDescuentos>();
		tblComprasItemDescuentos TblComprasItemDescuentos;

		if (ByosValidar.Nulo(((tblComprasItem) ByosFormulario01.Clase).getMontoneto()) == 0) {
			Notification.show("El Monto del Documento no puede quedar en blanco",Notification.TYPE_TRAY_NOTIFICATION);		
		}else{
			for(int f=0;f<10;f++){
				if(((tblComprasItem)ByosFormulario01.Clase).TblComprasItemDescuentos!=null &&  (f < ((tblComprasItem)ByosFormulario01.Clase).TblComprasItemDescuentos.length)){
					((tblComprasItem)ByosFormulario01.Clase).TblComprasItemDescuentos[f].setItem(f+1);
					ArrayTblComprasItemDescuentos.add(((tblComprasItem)ByosFormulario01.Clase).TblComprasItemDescuentos[f]);
				}else{
				   TblComprasItemDescuentos = new tblComprasItemDescuentos();
				   TblComprasItemDescuentos.setCodigocompra(((tblComprasItem)ByosFormulario01.Clase).getCodigocompra());
				   TblComprasItemDescuentos.setCodigoitem(((tblComprasItem)ByosFormulario01.Clase).getCodigoitem());
				   TblComprasItemDescuentos.setItem(f+1);
				   ArrayTblComprasItemDescuentos.add(TblComprasItemDescuentos);
				}
			}
			
			final moduloComprasItemDescuentos ModuloComprasItemDescuentos = new moduloComprasItemDescuentos(ArrayTblComprasItemDescuentos, ((tblComprasItem)ByosFormulario01.Clase));
			
			ModuloComprasItemDescuentos.btoAceptar.addClickListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	                try{
	                	String Estado=ModuloComprasItemDescuentos.EstatusImpuestos();
	                	if(Estado.equals("ok")){
	                	   tblComprasItemDescuentos[] TblComprasItemDescuentos01 = null;
	                	   if(ArrayTblComprasItemDescuentos!=null && ArrayTblComprasItemDescuentos.size()>0){
	                		  TblComprasItemDescuentos01 = new tblComprasItemDescuentos[ArrayTblComprasItemDescuentos.size()];
	                		  for(int f=0;f<ArrayTblComprasItemDescuentos.size();f++){
	                			  TblComprasItemDescuentos01[f] = new tblComprasItemDescuentos();
	                			  TblComprasItemDescuentos01[f] = ((tblComprasItemDescuentos)ArrayTblComprasItemDescuentos.get(f));
	                		  }
	                	   }
	                	   ((tblComprasItem)ByosFormulario01.Clase).TblComprasItemDescuentos = TblComprasItemDescuentos01;	                	   
	                	   ((tblComprasItem)ByosFormulario01.Clase).setMontodescuento(BigDecimal.valueOf(ModuloComprasItemDescuentos.TotalMontoDescuento));	                	   
	                	   ActulizarMontos();
	                	   ByosFormulario01.refrescar();
	                       
	                	   ModuloComprasItemDescuentos.closeWindows();  
	                	}else{
	                	   Notification.show(Estado , Notification.TYPE_TRAY_NOTIFICATION);
	                	}
	                    
	                } catch (Exception e) {
	                    // Ingnored, we'll let the Form handle the errors
	                }
	            }
	        }); 			
			
			ModuloComprasItemDescuentos.openWindows();
			
		}
		
	}	
	
	
	
}
