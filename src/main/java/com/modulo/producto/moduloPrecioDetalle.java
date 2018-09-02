package com.modulo.producto;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosTitulo;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.precioproducto.tblTablePrecios;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class moduloPrecioDetalle extends ByosVerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    
    private tblProducto TblProducto;
    int MaxReg=0;
    ByosColumna[] ByosColumnas = new ByosColumna[6];

    
    ByosDatagridFiltrableTextbox ListaPrecios;
    ArrayList <tblTablePrecios>ArrayTblTablePrecios = new ArrayList <tblTablePrecios>();
    
    public moduloPrecioDetalle(final tblProducto TblProducto){ 
    	this.TblProducto = TblProducto;
    	initAddressList();
    	setByosLayout("Listado de Precios", "630px");
    	Contenido.addComponent(ListaPrecios);
    }
    
    public void setTblProducto(final tblProducto TblProducto){
    	this.TblProducto = TblProducto;
    	ArrayList <tblTablePrecios> Precios = new tblTablePrecios().LoadTblTablePrecioArray(TblProducto,"Todos");
    	ArrayTblTablePrecios = Precios;
    	ListaPrecios.getDatagrid().refrescar(ArrayTblTablePrecios);
    	ListaPrecios.getDatagrid().refreshRowCache();
    }
    
    private void initAddressList() {
    	if(TblProducto.TblProductoMedidas!=null && TblProducto.TblProductoMedidas.length>0){
    		MaxReg=TblProducto.TblProductoMedidas.length;
    	}
    	ListaPrecios = new ByosDatagridFiltrableTextbox(false,630,200);
    	ByosColumnas[0] = new ByosColumna("descripcion",                   String.class, "Descripicon",           "", null);
    	ByosColumnas[1] = new ByosColumna("descripcionprecio",             String.class, "Tipo Precio",           "", null);
    	ByosColumnas[2] = new ByosColumna("precio",                        String.class, "Precio",                "", null);
    	ByosColumnas[3] = new ByosColumna("precioiva",                     String.class, "Precio + IVA",          "", null);
    	ByosColumnas[4] = new ByosColumna("utilidad",                      String.class, "% Utilidad",            "", null);
    	ByosColumnas[5] = new ByosColumna("utilidadreal",                  String.class, "% Utilidad Real",       "", null);
    	
    	try {
    		ArrayTblTablePrecios  = new tblTablePrecios().LoadTblTablePrecioArray(TblProducto,"Todos");

			ListaPrecios.getDatagrid().initDatagridByos(ArrayTblTablePrecios , new tblTablePrecios(), ByosColumnas, false);
			ListaPrecios.getDatagrid().setImmediate(true);
			ListaPrecios.botCancelar.setVisible(false);
			ListaPrecios.botProcesar.setVisible(false);
			ListaPrecios.getDatagrid().setCellStyleGenerator(new Table.CellStyleGenerator() {
				@Override
				public String getStyle(Table source, Object itemId, Object propertyId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
			        if (propertyId == null){
			            return "black"; 
			        }
  
			        int row = Integer.valueOf(p.id);

			        if (p.id==0){
			            return "azuloscuro";
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
					final Label ByosCampo01 = new Label();
					ByosCampo01.setStyleName("align-right");
					ByosCampo01.setWidth("6em");
					ByosCampo01.setValue(ByosConversores.BigDecimalToString(p.getPrecio(),3));
					return ByosCampo01;
				}
			});
			ListaPrecios.getDatagrid().addGeneratedColumn("precioiva", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final Label ByosCampo01 = new Label();
					ByosCampo01.setStyleName("align-right");
					ByosCampo01.setWidth("6em");
					ByosCampo01.setValue(ByosConversores.BigDecimalToString(p.getPrecioiva(),3));
					return ByosCampo01;
				}
			});	
			ListaPrecios.getDatagrid().addGeneratedColumn("utilidad", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final Label ByosCampo01 = new Label();
					ByosCampo01.setStyleName("align-right");
					ByosCampo01.setWidth("5em");
					ByosCampo01.setValue(ByosConversores.BigDecimalToString(p.getUtilidad(),2));
					return ByosCampo01;
				}
			});	
			ListaPrecios.getDatagrid().addGeneratedColumn("utilidadreal", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTablePrecios p = (tblTablePrecios)itemId;
					final Label ByosCampo01 = new Label();
					ByosCampo01.setStyleName("align-right");
					ByosCampo01.setWidth("6em");
					ByosCampo01.setValue(ByosConversores.BigDecimalToString(p.getUtilidadreal(),3));
					return ByosCampo01;
				}
			});				
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  

}
