package com.modulo.productodeposito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosTitulo;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.utilString;
import com.modulo.precioproducto.tblTablePrecios;
import com.modulo.producto.tblProducto;
import com.modulo.productomedida.tblProductoMedida;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class moduloDepositoDetalle extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    ByosVerticalLayout Main = new ByosVerticalLayout();
    private tblProducto TblProducto;
    public String Tipo;
    int MaxReg=0;
    double Equivalencia=1;
    ByosColumna[] ByosColumnas = new ByosColumna[4];
    
    ByosCampo bcUnidadesMedidas = new ByosCampo(utilString.CAMPO_COMBOBOX);
    
    ByosDatagridFiltrableTextbox ListaDepositos;
    ArrayList <tblProductoDeposito>ArrayTblProductoDepositos = new ArrayList <tblProductoDeposito>();
    BigDecimal[] ExistenciaOrigen;
    
    public moduloDepositoDetalle(final tblProducto TblProducto, String Tipo){ 
    	this.TblProducto = TblProducto;
    	this.Tipo = Tipo;
    	initAddressList();
    	bcUnidadesMedidas.lblDescripcion.setVisible(true);
    	bcUnidadesMedidas.lblDescripcion.setValue("");
    	
        bcUnidadesMedidas.lblNombreCampo.setValue("Unidades de Medidas:");
        bcUnidadesMedidas.setEstilo("v-byoscampo-solo","Medio");
        bcUnidadesMedidas.setHeight("42px");
        bcUnidadesMedidas.CboxItem.setImmediate(true);
        bcUnidadesMedidas.CboxItem.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
            	ActualizarCalculo();
            }
        });
    	if(!Tipo.equals(utilString.TIPO_AJUSTE)){
    	   Main.setByosLayout("Existencia Por Deposito", "630px");
    	   Main.Contenido.addComponent(bcUnidadesMedidas);
    	   Main.Contenido.addComponent(ListaDepositos);
    	   addComponent(Main);
    	}else{
    	   addComponent(bcUnidadesMedidas);
    	   addComponent(ListaDepositos);
    	}
    	setTblProducto(TblProducto);
    }
    
    public void setTblProducto(final tblProducto TblProducto){
    	this.TblProducto = TblProducto;
    	ArrayList<tblProductoDeposito> Deposito = new ArrayList<tblProductoDeposito>();
		try {
			Deposito = new tblProductoDeposito().BuscarArrayList(TblProducto.getCodigoproducto());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ArrayTblProductoDepositos = Deposito;
    	CargarMedidaOrigen();
    	ActualizarExistenciaTotal(utilString.TIPO_CONSULTA);
    	ActualizarExistenciaTotal();
    	ListaDepositos.getDatagrid().refrescar(ArrayTblProductoDepositos);
    	ListaDepositos.getDatagrid().refreshRowCache();	
        bcUnidadesMedidas.CboxItem.setContainerDataSource(new tblProductoMedida().getProductoMedidaContainer(TblProducto.getCodigoproducto()));
        
        if(TblProducto.getCodigomedida()!=null && TblProducto.TblUnidadMedida.getDescripcion()!=null && !TblProducto.getCodigomedida().equals("") && !TblProducto.TblUnidadMedida.getDescripcion().equals("")){
           ActualizarCalculo();
           String UnidadDefault = TblProducto.getCodigomedida() + "," + TblProducto.TblUnidadMedida.getDescripcion();
           //System.out.println(UnidadDefault);
           bcUnidadesMedidas.CboxItem.setValue(UnidadDefault);
        }else{
           bcUnidadesMedidas.CboxItem.setValue("");	
           bcUnidadesMedidas.lblDescripcion.setValue("");
        }
        bcUnidadesMedidas.CboxItem.requestRepaint();
        bcUnidadesMedidas.requestRepaint();
    }
    
    private void initAddressList() {
    	if(TblProducto.TblProductoMedidas!=null && TblProducto.TblProductoMedidas.length>0){
    		MaxReg=TblProducto.TblProductoMedidas.length;
    	}
    	ListaDepositos = new ByosDatagridFiltrableTextbox(false,630,120);
    	ByosColumnas[0] = new ByosColumna("codigodeposito",                String.class, "Codigo",              "", null);
    	ByosColumnas[1] = new ByosColumna("descripcion",                   String.class, "Descripicon",         "", null);
    	ByosColumnas[2] = new ByosColumna("existenciareal",                String.class, "Exist. Unid. Min",    "", null);
    	ByosColumnas[3] = new ByosColumna("existencia",                    String.class, "Existencia",          "", null);
    	
    	try {

    		
			ListaDepositos.getDatagrid().initDatagridByos(ArrayTblProductoDepositos , new tblProductoDeposito(), ByosColumnas, false);
			ListaDepositos.getDatagrid().setImmediate(true);
	    	ListaDepositos.botCancelar.setVisible(false);
	    	ListaDepositos.botProcesar.setVisible(false);
	    	ActualizarExistenciaTotal(utilString.TIPO_CONSULTA);
	    	ActualizarExistenciaTotal();
			ListaDepositos.getDatagrid().setCellStyleGenerator(new Table.CellStyleGenerator() {
				
				int par=0;
				boolean cambio=false;
				String color = "white";
				tblProductoDeposito pd = new tblProductoDeposito();
				tblProductoDeposito pdTemp = new tblProductoDeposito();
				
				@Override
				public String getStyle(Table source, Object itemId, Object propertyId) {
					par=par+1;
					pdTemp = (tblProductoDeposito)itemId;
					
					if (!pdTemp.equals(pd)){
						pd=pdTemp;
						if (color.equals("white")){
                    		color="azuloscuro";
                    	}
                    	else if (color.equals("azuloscuro")){
                    		color="white";
                    	}
					}           
                    
                    return color;
     
    
				}
			});	
			
			ListaDepositos.getDatagrid().addGeneratedColumn("codigodeposito", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblProductoDeposito p = (tblProductoDeposito)itemId;
					final Label ByosCampo01 = new Label();
					ByosCampo01.setValue(p.getCodigodeposito());
					return ByosCampo01;
				}
			});
			ListaDepositos.getDatagrid().addGeneratedColumn("descripcion", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblProductoDeposito p = (tblProductoDeposito)itemId;
					final Label ByosCampo01 = new Label();
					ByosCampo01.setValue(p.getDescripcion());
					return ByosCampo01;
				}
			});
			ListaDepositos.getDatagrid().addGeneratedColumn("existenciareal", new Table.ColumnGenerator() {			
				@SuppressWarnings("deprecation")
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
				   final tblProductoDeposito p = (tblProductoDeposito)itemId;
				   final Label ByosCampo01 = new Label();
				   ByosCampo01.setStyleName("align-right");
				   ByosCampo01.setValue(ByosConversores.BigDecimalToString(p.getExistenciareal(),3));
				   return ByosCampo01;
				}
			});			
			if(!Tipo.equals(utilString.TIPO_AJUSTE)){
			   ListaDepositos.getDatagrid().addGeneratedColumn("existencia", new Table.ColumnGenerator() {			
				  @SuppressWarnings("deprecation")
				  @Override
				  public Object generateCell(final Table source, final Object itemId, Object columnId) {
				  	  final tblProductoDeposito p = (tblProductoDeposito)itemId;
					  final Label ByosCampo01 = new Label();
					  ByosCampo01.setStyleName("align-right");
					  ByosCampo01.setValue(ByosConversores.BigDecimalToString(p.getExistencia(),3));
					  return ByosCampo01;
				  }
			   });	
			}else{
				ListaDepositos.getDatagrid().addGeneratedColumn("existencia", new Table.ColumnGenerator() {			
					@SuppressWarnings("deprecation")
					@Override
					public Object generateCell(final Table source, final Object itemId, Object columnId) {
						final tblProductoDeposito p = (tblProductoDeposito)itemId;
						final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
						ByosCampo01.lblNombreCampo.setVisible(false);
						ByosCampo01.setTipoCampo("NumberField");
						ByosCampo01.txtNumerico.setWidth("6em");
						ByosCampo01.setDecimales(3);
						ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getExistencia(),3));
						ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
							@Override
							public void textChange(TextChangeEvent event) {
								if(event.getText()!=null && !event.getText().equals("")){
								   p.setExistencia(ByosConversores.StringToBigDecimal(event.getText()));
								}
							}
						});
						
						//Pierde el Foco
						ByosCampo01.txtNumerico.addListener(new FieldEvents.BlurListener() {
							@Override
				            public void blur(final FieldEvents.BlurEvent event) {	 
							    p.setExistenciareal(BigDecimal.valueOf(p.getExistencia().doubleValue() * Equivalencia));
							    ActualizarExistenciaTotal();
				            	source.refreshRowCache();
				            }
				        });						
						return ByosCampo01;
					}
				});				
			}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void ActualizarExistenciaTotal(String TipoActulizacion){
    	if(!TipoActulizacion.equals(utilString.TIPO_AJUSTE)){
		   try{
		  	   ArrayTblProductoDepositos  = new tblProductoDeposito().BuscarArrayList(TblProducto.getCodigoproducto());
		   }catch (Exception e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		   }
    	}
    }
    
    public void ActualizarExistenciaTotal(){    
		double Existencia=0;
		double Existenciareal=0;
        if(ArrayTblProductoDepositos!=null && ArrayTblProductoDepositos.size()>0){
           for(int f=0;f<ArrayTblProductoDepositos.size();f++){
        	   tblProductoDeposito tbl = (tblProductoDeposito)ArrayTblProductoDepositos.get(f);
        	   if(tbl.getExistencia()!=null){
        		  Existencia+=tbl.getExistencia().doubleValue();
        		  Existenciareal+=tbl.getExistenciareal().doubleValue();
        	   }
           }
        }
    	ListaDepositos.getDatagrid().setColumnFooter("existencia", ByosConversores.DoubleToString(Existencia, 3));
    	ListaDepositos.getDatagrid().setColumnFooter("existenciareal", ByosConversores.DoubleToString(Existenciareal, 3));
    }

    public tblProductoDeposito[] getProductoDeposito(){
    	tblProductoDeposito[] tbl = new tblProductoDeposito[ArrayTblProductoDepositos.size()];
        if(ArrayTblProductoDepositos!=null && ArrayTblProductoDepositos.size()>0){
           for(int f=0;f<ArrayTblProductoDepositos.size();f++){
        	   tbl[f] = (tblProductoDeposito)ArrayTblProductoDepositos.get(f);
           }
        }
        return tbl;
    }  
    
    public void CargarMedidaOrigen(){
    	if(ArrayTblProductoDepositos.size()>0){	
    		ExistenciaOrigen = new BigDecimal[ArrayTblProductoDepositos.size()];
            for(int f=0;f<ArrayTblProductoDepositos.size();f++){
           	    final tblProductoDeposito p = (tblProductoDeposito)ArrayTblProductoDepositos.get(f);
           	    ExistenciaOrigen[f] = p.getExistencia(); 
            }
         }
    }
    
    public void ActualizarCalculo(){
        if(bcUnidadesMedidas.CboxItem.getValue()!=null && !bcUnidadesMedidas.CboxItem.getValue().toString().equals("")){
        	tblProductoMedida TblProductoMedidas = new tblProductoMedida();
        	String[] Medidas = bcUnidadesMedidas.CboxItem.getValue().toString().split(",");
        	try {
				if(TblProductoMedidas.buscarCodigo(TblProducto.getCodigoproducto(), Medidas[0])){
					BigDecimal Costo = BigDecimal.valueOf(0);
					if(TblProductoMedidas.getEquivalencia()!=null && TblProductoMedidas.getEquivalencia().doubleValue()!=0){
					   Equivalencia = TblProductoMedidas.getEquivalencia().doubleValue();
					}else{
					   Equivalencia = 1;	
					}					
					bcUnidadesMedidas.lblDescripcion.setValue("La Equivalencia es de: " + ByosConversores.BigDecimalToString(BigDecimal.valueOf(Equivalencia), 3));   						   
			    	if(ArrayTblProductoDepositos.size()>0){	
			            for(int f=0;f<ArrayTblProductoDepositos.size();f++){
			           	  final tblProductoDeposito p = (tblProductoDeposito)ArrayTblProductoDepositos.get(f);
			           	  p.setExistencia(BigDecimal.valueOf(p.getExistenciareal().doubleValue() / Equivalencia));
			            }
			            ActualizarExistenciaTotal();
			            ListaDepositos.getDatagrid().refreshRowCache();
			         }				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
    }
    
}
