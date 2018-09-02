package com.modulo.historicoprecios;

import java.io.Serializable;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.base.renderers.MarkerRenderer;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LabelRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LegendRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.TickRenderers;
import org.dussan.vaadin.dcharts.metadata.styles.MarkerStyles;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.AxesDefaults;
import org.dussan.vaadin.dcharts.options.Cursor;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.CanvasAxisTickRenderer;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosTitulo;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilDate;
import com.modulo.kardexdiario.tblKardexDiario;
import com.modulo.producto.tblProducto;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class rptEstadisticaCompraVenta extends ByosVerticalLayout implements Serializable {
	private static final long serialVersionUID = 1L;
	public tblProducto TblProducto;
	
	tblKardexDiario[] TblKardexDiario=null;
	//20160913
	//DCharts chart = new DCharts();
	Options options = new Options(); 
	int Dias = -90;
	String Descripcion = "Ultima Compra: S/I      Ultima Venta: S/I      Ultimo Cambio de Precio: S/I";
	HorizontalLayout Datos01 = new HorizontalLayout();
	VerticalLayout   Datos02 = new VerticalLayout();
	//VerticalLayout   Grafico = new VerticalLayout();
	public ByosMenu menu = new ByosMenu();
	ByosBoton bto90Disas = new ByosBoton(menu.MenuPrincipal, 17,"Calculo a 90 Dias","90 Dias");
	ByosBoton bto180Disas = new ByosBoton(menu.MenuPrincipal, 17,"Calculo a 180 Dias","180 Dias");
	ByosBoton bto360Disas = new ByosBoton(menu.MenuPrincipal, 17,"Calculo a 360 Dias","360 Dias");
	   

	Label Estadisticas = new Label();
	   
	public rptEstadisticaCompraVenta (tblProducto TblProducto){
	   this.TblProducto = TblProducto;
	   inicializar();
	}
	      
	public DataSeries Datos(){
	   try{
		   TblKardexDiario = new tblKardexDiario().Buscar(TblProducto.getCodigoproducto(),  utilDate.CalculoFecha(utilDate.Fecha(), Dias), utilDate.Fecha());
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   
	   DataSeries dataSeries = new DataSeries();
	   if(TblKardexDiario!=null && TblKardexDiario.length>0){
		  dataSeries.newSeries();
		  for(int f=0;f<TblKardexDiario.length;f++){
			  double Cantidad=TblKardexDiario[f].getEntrada().doubleValue();
		      String Dia = utilDate.FormatoFecha(TblKardexDiario[f].getFecha(),"MM-dd-yyyy");
			  dataSeries.add(Dia, Cantidad);
		  }
		  dataSeries.newSeries();
		  for(int f=0;f<TblKardexDiario.length;f++){
			  double Cantidad=TblKardexDiario[f].getSalida().doubleValue();
			  String Dia = utilDate.FormatoFecha(TblKardexDiario[f].getFecha(),"MM-dd-yyyy");
			  dataSeries.add(Dia, Cantidad);
		  }	
	   }else{
		  dataSeries.newSeries();
		  dataSeries.add("0", 0);
		  dataSeries.newSeries();
		  dataSeries.add("0", 0);
		  Descripcion =  "Ultima Compra: S/I      Ultima Venta: S/I      Ultimo Cambio de Precio: S/I";
	   }	   
	   return dataSeries;   
	}
	   
	public void inicializar(){
	   this.setMargin(false);
	   this.setSpacing(false);

       
	   /*Datos01.setStyleName("backColorAzulOscuro");
	   Datos02.setStyleName("backColorAzulOscuro");*/

       /*Datos02.addComponent(bto90Disas);
       Datos02.addComponent(bto180Disas);
       Datos02.addComponent(bto360Disas);
       Datos02.setComponentAlignment(bto90Disas, Alignment.MIDDLE_CENTER);
       Datos02.setComponentAlignment(bto180Disas, Alignment.MIDDLE_CENTER);
       Datos02.setComponentAlignment(bto360Disas, Alignment.MIDDLE_CENTER);*/
	   setOption(Descripcion);
	   
	   bto90Disas.addClickListener(new Button.ClickListener() {
           public void buttonClick(ClickEvent event) {
               try {
                   setDias(-90);
                   
               } catch (Exception e) {
                   // Ingnored, we'll let the Form handle the errors
               }
           }
       });
	   
	   bto180Disas.addClickListener(new Button.ClickListener() {
           public void buttonClick(ClickEvent event) {
               try {
                   setDias(-180);
                   
               } catch (Exception e) {
                   // Ingnored, we'll let the Form handle the errors
               }
           }
       });
	   
	   bto360Disas.addClickListener(new Button.ClickListener() {
           public void buttonClick(ClickEvent event) {
               try {
                   setDias(-360);
                   
               } catch (Exception e) {
                   // Ingnored, we'll let the Form handle the errors
               }
           }
       });	
	   
	   setByosLayout("Movimiento de Producto a " +  java.lang.Math.abs(Dias) + " Dias","630px");
	   
	   //20160913
	   /*chart.setWidth("100%");
	   chart.setHeight("100%");
	   chart.setImmediate(true);*/

       
	   Estadisticas.setValue(Descripcion);
	   Estadisticas.setWidth("100%");
	  
	   
	   Datos01.setWidth("100%");
	   Datos01.setHeight("300px");
	   
	   //20160913
	   //Datos01.addComponent(chart);
	   Contenido.addComponent(menu);
	   Contenido.addComponent(Datos01);
	   Contenido.addComponent(Estadisticas);
	   Contenido.setComponentAlignment(Estadisticas, Alignment.MIDDLE_CENTER);

	}
	   
	public void setOption(String Descripcion){
	   Estadisticas.setValue(Descripcion);
	   AxesDefaults axesDefaults = new AxesDefaults()
	       .setLabelRenderer(LabelRenderers.CANVAS);
	   
       Axes axes = new Axes()
        .addAxis(
	    new XYaxis()				
			//.setLabel(Descripcion)
		    .setRenderer(AxisRenderers.DATE)	
		    .setPad(0)
		    .setTickOptions(
				new AxisTickRenderer()
					.setFormatString("%#m/%y"))
					.setNumberTicks(4)
			        .setPad(0))	

	     .addAxis(
		 new XYaxis(XYaxes.Y)
		    .setPad(0)
			.setTickOptions(
				new AxisTickRenderer()
					.setFormatString("%.2f")));
   
       Series series = new Series()
   	      .addSeries(   			
   		  new XYseries()
   			  .setLineWidth(2)
   			  .setMarkerOptions(
   				  new MarkerRenderer()
   					  .setStyle(MarkerStyles.DIAMOND)))
   					
   	      .addSeries(
   		  new XYseries().
   			  setShowLine(true)
   			  .setMarkerOptions(
   				  new MarkerRenderer()
   					  .setSize(7)
   					  .setStyle(MarkerStyles.X)));
   
       Legend legend = new Legend()
	       .setShow(true)
	       .setLabels("Entrada","Salida");

       Highlighter highlighter = new Highlighter()
	       .setShow(true)
	       .setSizeAdjust(10)
	       .setTooltipLocation(TooltipLocations.NORTH)
	       .setTooltipAxes(TooltipAxes.Y)
	       .setTooltipFormatString("<b><i><span style='color:red;'>Cantidad</span></i></b> %.2f")
	       .setUseAxesFormatters(false);

       Cursor cursor = new Cursor()
	       .setShow(true);		  
  
       options
         .setAxesDefaults(axesDefaults)
   	     .setSeries(series)
   	     .addOption(axes)
         .addOption(highlighter)
         .addOption(cursor)
         .setLegend(legend);
       
       //20160913
       /*chart
   	     .setDataSeries(Datos())
   	     .setOptions(options)
   	     .show();*/		   
	   
	}
	      
	public void setEstadisticas(tblProducto TblProducto){
		   this.TblProducto = TblProducto;	
		   if(TblProducto.getCodigoproducto()!=null && !TblProducto.getCodigoproducto().equals("")){
		      String UltimoCambioPrecio = new tblHistoricoPrecios().DatosPrecios("Select max(fecha) from tblhistoricoprecios where codigoproducto='" + TblProducto.getCodigoproducto() + "'");
		      if(UltimoCambioPrecio==null){
		         UltimoCambioPrecio="S/I";
		      }
		      Descripcion =  "Ultima Compra: S/I      Ultima Venta: S/I      Ultimo Cambio de Precio: " + UltimoCambioPrecio;
		   }else{
			  Descripcion =  "Ultima Compra: S/I      Ultima Venta: S/I      Ultimo Cambio de Precio: S/I"; 
		   }
		   //20160913
		   /*chart.setWidth("100%");
		   chart.setHeight("90%");*/
		   Datos02.setWidth("10%");
	       Datos02.setHeight("100%");			   
		   setOption(Descripcion);
	}
	
	public void setDias(int Dias){
		this.Dias=Dias;
		setByosLayout("Movimiento de Producto a " +  java.lang.Math.abs(Dias) + " Dias","630px");
		setEstadisticas(TblProducto);
	}


}
