package com.modulo.codigobarra;

import java.io.Serializable;
import java.util.ArrayList;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosConversores;
import com.modulo.historicoprecios.rptPrecios;
import com.modulo.impuestos.tblImpuestos;
import com.modulo.producto.tblProducto;
import com.modulo.productoimpuesto.tblProductoImpuesto;
import com.modulo.productomedida.tblProductoMedida;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class moduloCodigoBarra extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    public Window subwindow;
    private tblProducto TblProducto;

    public VerticalLayout layout = new VerticalLayout();
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    private VerticalLayout vlayout01 = new VerticalLayout();
    
    public ByosBoton btoAceptar =  new ByosBoton();
    public ByosBoton btoCancelar = new ByosBoton();
    
    ByosColumna[] ByosColumnas = new ByosColumna[2];
    
    ByosDatagridFiltrableTextbox ListaCodigoBarra;
    ArrayList <tblCodigoBarra>ArrayTblTableCodigoBarras = new ArrayList <tblCodigoBarra>();
    

	public moduloCodigoBarra(final tblProducto TblProducto){ 
    	this.TblProducto = TblProducto;

        btoAceptar.setBoton(32,"Aceptar","Aceptar");        
        btoCancelar.setBoton(33,"Cancelar","Cancelar"); 
       
        layout.setMargin(true);
        layout.setSpacing(true);
        subwindow = new Window("Codigo de Barras");
        subwindow.setWidth("41em");
        subwindow.setHeight("50em");
        subwindow.setModal(true);       
        subwindow.setContent(layout);
        
        initAddressList();
        vlayout01.setMargin(false);
        vlayout01.setSpacing(false);
        vlayout01.setImmediate(true);
        vlayout01.addComponent(ListaCodigoBarra);
        

        
        hlayout01.setMargin(false);
        hlayout01.setSpacing(false);
        hlayout01.setImmediate(true);
        hlayout01.addComponent(btoAceptar);
        hlayout01.setComponentAlignment(btoAceptar,Alignment.BOTTOM_CENTER);
        hlayout01.addComponent(btoCancelar);
        hlayout01.setComponentAlignment(btoCancelar, Alignment.BOTTOM_CENTER); 
        
        layout.addComponent(vlayout01);
        layout.setComponentAlignment(vlayout01, Alignment.TOP_RIGHT);
        layout.addComponent(hlayout01);
        layout.setComponentAlignment(hlayout01, Alignment.TOP_CENTER);  
    }

    private void initAddressList() {
    	ListaCodigoBarra = new ByosDatagridFiltrableTextbox(false,460,470);
    	ListaCodigoBarra.botProcesar.setVisible(false);
    	ListaCodigoBarra.botCancelar.setVisible(false);
    	
    	
    	ByosColumnas[0] = new ByosColumna("codigobarra", String.class, "Codigo de Barras",           "", null);
        ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripcion",                "", null);

    	try {
    		ArrayTblTableCodigoBarras  =  TblProducto.BuscarArrayList();   
    		int cantidad=0;
    		if(ArrayTblTableCodigoBarras!=null && ArrayTblTableCodigoBarras.size()>0){
    			cantidad=ArrayTblTableCodigoBarras.size()-1;
    		}else{
    			ArrayTblTableCodigoBarras = new ArrayList<tblCodigoBarra>();
    		}
    		
    		for(int f=cantidad;f<100;f++){
    			ArrayTblTableCodigoBarras.add(new tblCodigoBarra());
    		}
			ListaCodigoBarra.getDatagrid().initDatagridByos(ArrayTblTableCodigoBarras , new tblCodigoBarra(), ByosColumnas, false);
			ListaCodigoBarra.getDatagrid().setImmediate(true);
			
			/*ListaCodigoBarra.getDatagrid().setCellStyleGenerator(new Table.CellStyleGenerator() {
				@Override
				public String getStyle(Table source, Object itemId, Object propertyId) {
					final tblCodigoBarra p = (tblCodigoBarra)itemId;
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
			});*/
			
			
			ListaCodigoBarra.getDatagrid().addGeneratedColumn("codigobarra", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblCodigoBarra p = (tblCodigoBarra)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("TextField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(false);
					ByosCampo01.txtTexto.setWidth("10em");
					ByosCampo01.txtTexto.setValue(p.getCodigobarra());
					ByosCampo01.txtTexto.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
							   p.setCodigobarra(event.getText().toString());
							}
						}
					});
					return ByosCampo01;
				}
			});	

			ListaCodigoBarra.getDatagrid().addGeneratedColumn("descripcion", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblCodigoBarra p = (tblCodigoBarra)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("TextField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(false);
					ByosCampo01.txtTexto.setWidth("20em");
					ByosCampo01.txtTexto.setValue(p.getDescripcion());

					ByosCampo01.txtTexto.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setDescripcion(event.getText());
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
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_ERROR_MESSAGE);
       }else {
    	   UI.getCurrent().addWindow(subwindow);                  
       }        
    }
		
	public tblCodigoBarra[] getTblCodigoBarras(){
		tblCodigoBarra[] TblCodigoBarras=null;
		int cantidad=0;
		if(ArrayTblTableCodigoBarras!=null){
		   for(int f=0;f<ArrayTblTableCodigoBarras.size();f++){
		   	   tblCodigoBarra TblCodigoBarra = (tblCodigoBarra) ArrayTblTableCodigoBarras.get(f);
			   if(TblCodigoBarra.getCodigobarra()!=null && !TblCodigoBarra.getCodigobarra().equals("")){
			      cantidad++;
			   }
		   }
		   int c=0;
		   TblCodigoBarras = new tblCodigoBarra[cantidad];
		   for(int f=0;f<ArrayTblTableCodigoBarras.size();f++){
		   	   tblCodigoBarra TblCodigoBarra = (tblCodigoBarra) ArrayTblTableCodigoBarras.get(f);
			   if(TblCodigoBarra.getCodigobarra()!=null && !TblCodigoBarra.getCodigobarra().equals("")){
				  TblCodigoBarras[c] = new tblCodigoBarra();
				  TblCodigoBarras[c].setCodigoBarra(TblCodigoBarra);
				  c++;
			   }
		   }		   
		}
		return TblCodigoBarras;
	}
	
    public boolean ValidarCodigoBarra(){
        tblCodigoBarra[] TblCodigoBarras = getTblCodigoBarras();
        tblCodigoBarra TblCodigoBarras01 = new tblCodigoBarra();
        
        for(int f=0;f<TblCodigoBarras.length;f++){        
            if(TblCodigoBarras[f].getCodigobarra()!=null && !TblCodigoBarras[f].getCodigobarra().equals("")){
               try{
				  TblCodigoBarras01=TblCodigoBarras[f].buscarCodigo(TblCodigoBarras[f].getCodigoproducto(), TblCodigoBarras[f].getCodigobarra());
			   }catch (Exception e) {
				  e.printStackTrace();
			   }
               if(TblCodigoBarras01!=null){
                  tblProducto TblProducto = new tblProducto();
                  try{
				     TblProducto.buscarCodigo(TblCodigoBarras01.getCodigoproducto());
			      }catch (Exception e) {
				     e.printStackTrace();
			      }
                  Notification.show("Ya existe un producto con este codigo de barra: " + TblCodigoBarras01.getCodigobarra() + " Producto " + TblProducto.getCodigoproducto() + " " + TblProducto.getDescripcioncorta() , Notification.TYPE_ERROR_MESSAGE);
                  return false;  
               }   
            }
        }
        return true;
    }
	
}
