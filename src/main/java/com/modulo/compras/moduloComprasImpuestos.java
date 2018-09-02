package com.modulo.compras;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosValidar;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;


public class moduloComprasImpuestos extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    public Window subwindow;
    public final Table tableListado = new Table(); 
    public VerticalLayout layout = new VerticalLayout();
    public HorizontalLayout Botonlayout = new HorizontalLayout();

	public double TotalMontoDeducible=0;
	public double TotalMontoImpuestos=0;
	
    //public tblComprasImpuestos[] TblLeerImpuestos;
    
    public ByosBoton btoAceptar;
    public ByosBoton btoCancelar;
    

    public ByosCampo bcCodigoImpuesto = new ByosCampo();

    public int MaxReg = 1;
    public int Posicion=1;
    
    ByosColumna[] ByosColumnas = new ByosColumna[5];
    
    ByosDatagridFiltrableTextbox ListaImpuestos;
    ArrayList <tblComprasImpuestos>ArrayTblComprasImpuestos = new ArrayList <tblComprasImpuestos>();
    
	public double getTotalMontoDeducible() {
		return TotalMontoDeducible;
	}

	public void setTotalMontoDeducible(double totalMontoDeducible) {
		TotalMontoDeducible = totalMontoDeducible;
	}

	public double getTotalMontoImpuestos() {
		return TotalMontoImpuestos;
	}

	public void setTotalMontoImpuestos(double totalMontoImpuestos) {
		TotalMontoImpuestos = totalMontoImpuestos;
	}
	
    public moduloComprasImpuestos(ArrayList <tblComprasImpuestos>ArrayTblComprasImpuestos){
    	this.ArrayTblComprasImpuestos = ArrayTblComprasImpuestos;
        layout.setMargin(true);
        layout.setSpacing(true);
        subwindow = new Window("Impuestos");
        subwindow.setWidth("575px");
        subwindow.setHeight("330px");
        subwindow.setModal(true);       
        subwindow.setContent(layout);
        
        initAddressList();
        
        layout.addComponent(ListaImpuestos);
    	btoAceptar = new ByosBoton(Botonlayout,32,"Aceptar","Aceptar");
    	btoCancelar = new ByosBoton(Botonlayout,33,"Cancelar","Cancelar");
    	
    	Botonlayout.setComponentAlignment(btoAceptar, Alignment.BOTTOM_CENTER);
    	Botonlayout.setComponentAlignment(btoCancelar, Alignment.BOTTOM_CENTER);
        
    	layout.addComponent(Botonlayout);
    	layout.setComponentAlignment(Botonlayout, Alignment.MIDDLE_CENTER); 
    	ActualizarImpuestosTotal();
    }
    
    private void initAddressList() {
        
    	if(ArrayTblComprasImpuestos!=null && ArrayTblComprasImpuestos.size()>0){
    		MaxReg=ArrayTblComprasImpuestos.size();
    	}
    	ListaImpuestos = new ByosDatagridFiltrableTextbox(false,540,200);
    	
    	
    	
    	ByosColumnas[0] = new ByosColumna("codigoimpuesto",                String.class, "Codigo",                "", null);
    	ByosColumnas[1] = new ByosColumna("descripcion",                   String.class, "Descripicon",           "", null);
        ByosColumnas[2] = new ByosColumna("montodeducible",                String.class, "Monto Deducible",       "", null);
    	ByosColumnas[3] = new ByosColumna("porcentaje",                    String.class, "Porcentaje",            "", null);
    	ByosColumnas[4] = new ByosColumna("montoimpuesto",                 String.class, "Monto Impuesto",        "", null);


    	
    	try {
    		
			ListaImpuestos.getDatagrid().initDatagridByos(ArrayTblComprasImpuestos , new tblComprasImpuestos(), ByosColumnas, false);
			
			ListaImpuestos.getDatagrid().addGeneratedColumn("codigoimpuesto", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasImpuestos p = (tblComprasImpuestos)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("4em");
					ByosCampo01.lblDescripcion.setValue(p.getCodigoimpuesto());
					ByosCampo01.lblDescripcion.setImmediate(true);
								
					return ByosCampo01;
				}
			});
			
			ListaImpuestos.getDatagrid().addGeneratedColumn("descripcion", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasImpuestos p = (tblComprasImpuestos)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("4em");
					ByosCampo01.lblDescripcion.setValue(p.getDescripcion());
					ByosCampo01.lblDescripcion.setImmediate(true);
													
					return ByosCampo01;
				}
			});
			
			
			ListaImpuestos.getDatagrid().addGeneratedColumn("montodeducible", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasImpuestos p = (tblComprasImpuestos)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getMontodeducible(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setMontodeducible(ByosConversores.StringToBigDecimal(event.getText()));
								ActualizarImpuestosTotal();
							}
							
						}
					});
					return ByosCampo01;
				}
			});	
			ListaImpuestos.getDatagrid().addGeneratedColumn("porcentaje", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasImpuestos p = (tblComprasImpuestos)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getPorcentaje(),3));
					ByosCampo01.txtNumerico.setReadOnly(true);					
					return ByosCampo01;
				}
			});			
			ListaImpuestos.getDatagrid().addGeneratedColumn("montoimpuesto", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblComprasImpuestos p = (tblComprasImpuestos)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");	
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getMontoimpuesto(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setMontoimpuesto(ByosConversores.StringToBigDecimal(event.getText()));
								ActualizarImpuestosTotal();
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
    
    public void ActualizarImpuestosTotal(){    
		TotalMontoDeducible=0;
		TotalMontoImpuestos=0;
        if(ArrayTblComprasImpuestos!=null && ArrayTblComprasImpuestos.size()>0){
           for(int f=0;f<ArrayTblComprasImpuestos.size();f++){
        	   tblComprasImpuestos tbl = (tblComprasImpuestos)ArrayTblComprasImpuestos.get(f);
        	   if(tbl.getMontodeducible()!=null){
        		  TotalMontoDeducible+=tbl.getMontodeducible().doubleValue();
        	   }
        	   if(tbl.getMontoimpuesto()!=null){
        		  TotalMontoImpuestos+=tbl.getMontoimpuesto().doubleValue();
        	   }        	   
           }
        }       
        ListaImpuestos.getDatagrid().setColumnFooter("montodeducible", ByosConversores.DoubleToString(TotalMontoDeducible, 3));
        ListaImpuestos.getDatagrid().setColumnFooter("montoimpuesto", ByosConversores.DoubleToString(TotalMontoImpuestos, 3));
    }
    
    public String EstatusImpuestos(){
    	ActualizarImpuestosTotal();
        if(ArrayTblComprasImpuestos!=null && ArrayTblComprasImpuestos.size()>0){
            for(int f=0;f<ArrayTblComprasImpuestos.size();f++){
         	   tblComprasImpuestos tbl = (tblComprasImpuestos)ArrayTblComprasImpuestos.get(f);
         	   if(tbl.getMontodeducible()!=null || tbl.getMontoimpuesto()!=null){         		  
         		  double montodeducible=0;
         		  double montoimpuesto=0;
         		  double porcentaje=0;
         		  
         		  if(tbl.getPorcentaje()!=null){
         			  porcentaje=tbl.getPorcentaje().doubleValue();
         		  }
         	      if(tbl.getMontodeducible()!=null) {
         	    	 montodeducible=tbl.getMontodeducible().doubleValue();
         	      }         	      
         	      if(tbl.getMontoimpuesto()!=null){
         	    	 montoimpuesto=tbl.getMontoimpuesto().doubleValue(); 
         	      }
         	      
         	      if(montodeducible!=0 && montoimpuesto==0){
         		      return "Error en monto impuesto del deducible " + tbl.getDescripcion();
         	      }         	      
         	      if(montoimpuesto!=0 && montodeducible==0){
         		     return "Error en monto dedicible del impuesto " + tbl.getDescripcion();
         	      }
         	      
         	      if(!ByosValidar.ToleranciaNumerica(montodeducible*porcentaje/100,montoimpuesto)){
         	    	 return "La tolerancia del impuesto " + tbl.getDescripcion() + " sobrepasa el limite permitido"; 
         	      }
         	   }
            } 
        }
        return "ok";
    }
}
