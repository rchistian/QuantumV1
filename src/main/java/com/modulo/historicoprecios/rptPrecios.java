package com.modulo.historicoprecios;


import java.util.Date;

import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.Trendline;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.base.renderers.MarkerRenderer;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
//import org.dussan.vaadin.dcharts.events.click.ChartDataClickEvent;
import org.dussan.vaadin.dcharts.events.click.ChartDataClickHandler;
//import org.dussan.vaadin.dcharts.events.rightclick.ChartDataRightClickEvent;
import org.dussan.vaadin.dcharts.events.rightclick.ChartDataRightClickHandler;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.metadata.styles.MarkerStyles;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Cursor;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;

import com.modulo.componentes.utilDate;
import com.modulo.producto.tblProducto;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;



public class rptPrecios {
	
	
   public Window subwindow;
   public VerticalLayout layout = new VerticalLayout();
   tblHistoricoPrecios[] TblHistoricoPrecios=null;
   
   public rptPrecios(tblProducto TblProducto){
       layout.setMargin(true);
       layout.setSpacing(true);
       subwindow = new Window("Estadisticas de Costos y Precios");
       subwindow.setWidth("80em");
       subwindow.setHeight("35em");
       subwindow.setModal(true);       
       subwindow.setContent(layout);
       



	   //DataSeries dataSeries = new DataSeries();
	   try{
		  TblHistoricoPrecios = new tblHistoricoPrecios().Buscar(TblProducto.getCodigoproducto(),TblProducto.getCodigomedida(),null,null);
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   
	   DataSeries dataSeries = new DataSeries();
	   dataSeries.newSeries();
	   
	   if(TblHistoricoPrecios!=null && TblHistoricoPrecios.length>0){
		  for(int f=0;f<TblHistoricoPrecios.length;f++){
			  double Precios=TblHistoricoPrecios[f].getPrecionuevo().doubleValue()/TblHistoricoPrecios[f].getEquivalencia().doubleValue();
			  int Dia = Integer.valueOf(utilDate.FormatoFecha(TblHistoricoPrecios[f].getFecha(),"yyyyMMdd"));
			  dataSeries.add(Dia, Precios);
			  System.out.println(Dia + "  " + Precios);
	      }
		  dataSeries.newSeries();
		  for(int f=0;f<TblHistoricoPrecios.length;f++){
			  double Precios=TblHistoricoPrecios[f].getCostonuevo().doubleValue()/TblHistoricoPrecios[f].getEquivalencia().doubleValue();
			  int Dia = Integer.valueOf(utilDate.FormatoFecha(TblHistoricoPrecios[f].getFecha(),"yyyyMMdd"));
			  dataSeries.add(Dia, Precios);
			  System.out.println(Dia + "  " + Precios);
	      }		   
		  
	   }
	   


	   
	   Axes axes = new Axes()
		.addAxis(
			new XYaxis(XYaxes.X)
			.setTickOptions(
				new AxisTickRenderer()
					.setFormatString("Dia: %d")))
		.addAxis(
			new XYaxis(XYaxes.Y)
				.setTickOptions(
					new AxisTickRenderer()
						.setFormatString("Bsf %.2f")));
	   
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
	   					.setStyle(MarkerStyles.X)))
	   	.addSeries(
	   		new XYseries()
	   			.setMarkerOptions(
	   				new MarkerRenderer()
	   					.setStyle(MarkerStyles.CIRCLE)))
	   	.addSeries(
	   		new XYseries()
	   			.setLineWidth(5)
	   			.setMarkerOptions(
	   				new MarkerRenderer()
	   					.setStyle(MarkerStyles.FILLED_SQUARE)
	   					.setSize(10)));

	   Options options = new Options()
	   	.setSeries(series)
	   	.addOption(axes);

	   DCharts chart = new DCharts()
	   	.setDataSeries(dataSeries)
	   	.setOptions(options)
	   	.show();
	   layout.addComponent(chart);
	   chart.setSizeFull();	   
	   openWindows();
	   
   }
   
   public void openWindows(){
       if(subwindow.getParent() != null) {
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_ERROR_MESSAGE);
       }else {
    	   UI.getCurrent().addWindow(subwindow);                  
       }        
    }
   
   

}
