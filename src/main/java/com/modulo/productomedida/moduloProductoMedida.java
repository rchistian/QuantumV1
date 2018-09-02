package com.modulo.productomedida;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosConversores;
import com.modulo.medidasdefault.tblMedidasDefault;
import com.modulo.producto.tblProducto;
import com.modulo.productomedidadefault.tblProductoMedidaDefault;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.filters.ByosFilter;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.vaadin.data.Container;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.PropertySetChangeEvent;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ReadOnlyStatusChangeEvent;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnReorderEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Chistian
 */
public class moduloProductoMedida extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    public Window subwindow;
    private tblProducto TblProducto;
    public final Table tableListado = new Table(); 
    public VerticalLayout layout = new VerticalLayout();
    public VerticalLayout Maillayout = new VerticalLayout();
    
    public ByosBoton btoAceptar;
    public ByosBoton btoCancelar;
    
    private VerticalLayout vlayout01 = new VerticalLayout();
    private HorizontalLayout hlayout01 = new HorizontalLayout();
    private HorizontalLayout hlayout02 = new HorizontalLayout();

    public ByosCampo bcCodigoMedida = new ByosCampo();

    public TextField Campo = new TextField();
    public ByosBoton BtoAddItemMedidas;
    public int MaxReg = 1;
    public int Posicion=1;
    
    ByosColumna[] ByosColumnas = new ByosColumna[7];
    
    ByosDatagridFiltrableTextbox ListaUnidadesMedidas;
    ArrayList <tblTableProductoMedida>ArrayTblProductosMedidas = new ArrayList <tblTableProductoMedida>();
    
    public moduloProductoMedida(tblProducto TblProducto){        
    	this.TblProducto = TblProducto;



       
        layout.setMargin(true);
        layout.setSpacing(true);
        subwindow = new Window("Unidades de Medidas");
        subwindow.setWidth("78em");
        subwindow.setHeight("45em");
        subwindow.setModal(true);       
        subwindow.setContent(layout);
        
        initAddressList();
        
        hlayout01.setMargin(false);
        hlayout01.setSpacing(false);
        hlayout01.setImmediate(true);
    	btoAceptar = new ByosBoton(hlayout01,32,"Aceptar","Aceptar");  ;
    	btoCancelar = new ByosBoton(hlayout01,33,"Cancelar","Cancelar");

        hlayout01.setComponentAlignment(btoAceptar,Alignment.BOTTOM_CENTER);
        hlayout01.setComponentAlignment(btoCancelar, Alignment.BOTTOM_CENTER);          
        
        hlayout02.setImmediate(true);
        hlayout02.setMargin(false);
        hlayout02.setSpacing(false);
        
        BtoAddItemMedidas = new ByosBoton(16, "Agregar Medida", "");
        BtoAddItemMedidas.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               try{
            	     procesoAddMedidas(); 
               } catch (Exception e) {
                   // Ingnored, we'll let the Form handle the errors
               }
           }
        });
        
        bcCodigoMedida.btoBoton1.setVisible(true);
        bcCodigoMedida.btoBoton1.setBoton(3, "Listar", "");
        bcCodigoMedida.btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               try{
            	   procesoListarMedidas("ByosCampo",null ,null, bcCodigoMedida);
               } catch (Exception e) {
                   // Ingnored, we'll let the Form handle the errors
               }
           }
         }); 
        bcCodigoMedida.setComponentAlignment(bcCodigoMedida.btoBoton1, Alignment.BOTTOM_RIGHT);
        bcCodigoMedida.setComponentAlignment(bcCodigoMedida.lblDescripcion, Alignment.BOTTOM_RIGHT);
        bcCodigoMedida.txtTexto.setVisible(false);
        
        bcCodigoMedida.lblNombreCampo.setCaption("Medida Minima");
        bcCodigoMedida.lblNombreCampo.setVisible(true);
        bcCodigoMedida.lblDescripcion.setVisible(true);
        bcCodigoMedida.lblDescripcion.setWidth("10em");
        bcCodigoMedida.lblDescripcion.setValue(TblProducto.TblUnidadMedida.getDescripcion());
        bcCodigoMedida.lblNombreCampo.setValue(TblProducto.getCodigomedida());
        
        hlayout02.addComponent(bcCodigoMedida);

        hlayout02.addComponent(BtoAddItemMedidas);
        hlayout02.setComponentAlignment(BtoAddItemMedidas, Alignment.BOTTOM_RIGHT);
        
        vlayout01.setMargin(false);
        vlayout01.setSpacing(false);
        vlayout01.setImmediate(true);
        vlayout01.addComponent(ListaUnidadesMedidas);
        
        layout.addComponent(hlayout02);
        layout.setComponentAlignment(hlayout02, Alignment.TOP_LEFT);
        layout.addComponent(vlayout01);
        layout.setComponentAlignment(vlayout01, Alignment.TOP_RIGHT);
        layout.addComponent(hlayout01);
        layout.setComponentAlignment(hlayout01, Alignment.TOP_CENTER);  

    }

    private void initAddressList() {
        
    	if(TblProducto.TblProductoMedidas!=null && TblProducto.TblProductoMedidas.length>0){
    		MaxReg=TblProducto.TblProductoMedidas.length;
    	}
    	ListaUnidadesMedidas = new ByosDatagridFiltrableTextbox(false,890,370);
    	//ListaUnidadesMedidas.setSizeUndefined();
    	
    	
    	ByosColumnas[0] = new ByosColumna("codigomedida",                  String.class, "Codigo",                "", null);
    	ByosColumnas[1] = new ByosColumna("descripcion",                   String.class, "Descripicon",           "", null);
        ByosColumnas[2] = new ByosColumna("productosmedidasdefaultcodigo", String.class, "Medida Default",        "", null);
    	ByosColumnas[3] = new ByosColumna("equivalencia",                  String.class, "Equivalencia",          "", null);
    	ByosColumnas[4] = new ByosColumna("pesoneto",                      String.class, "Peso Neto",             "", null);
    	ByosColumnas[5] = new ByosColumna("contenidoneto",                 String.class, "Cont. Neto",            "", null);
    	ByosColumnas[6] = new ByosColumna("volumen",                       String.class, "Volumen",               "", null);
    	
    	try {
    		//ArrayTblProductosMedidas  = new tblProductoMedida().UnidadesMedidasToArrayList(TblProducto.TblProductoMedidas);
    		ArrayTblProductosMedidas  = new tblTableProductoMedida().LoadTblTableProductoMedidaArray(TblProducto,"Todos");
    		
			ListaUnidadesMedidas.getDatagrid().initDatagridByos(ArrayTblProductosMedidas , new tblTableProductoMedida(), ByosColumnas, false);
			ListaUnidadesMedidas.getDatagrid().addGeneratedColumn("codigomedida", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTableProductoMedida p = (tblTableProductoMedida)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("4em");
					ByosCampo01.lblDescripcion.setValue(p.getCodigomedida());
					ByosCampo01.lblDescripcion.setImmediate(true);
					/*ByosCampo01.lblDescripcion.addValueChangeListener(new ValueChangeListener(){

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub
							p.setCodigomedida(ByosCampo01.lblDescripcion.getValue());
							System.out.println("Cabio:  " + p.getCodigomedida());
							
						}
						
					});*/
					
					ByosCampo01.btoBoton1.setVisible(true);
					ByosCampo01.btoBoton2.setVisible(true);
					ByosCampo01.btoBoton1.setBoton(3, "Listar", "");
					ByosCampo01.btoBoton2.setBoton(2, "Eliminar", "");					
					
					
					ByosCampo01.btoBoton1.addClickListener(new Button.ClickListener() {
			            public void buttonClick(ClickEvent event) {
			               try{
			            	   if(!p.getCodigomedida().equals(bcCodigoMedida.lblNombreCampo.getValue())){
			            	      procesoListarMedidas("Clase",p ,source,null);
			                   }else{
			                	  Notification.show("Error","No puede eliminar la unidad minima " , Notification.TYPE_TRAY_NOTIFICATION); 
			                   }			            	   
			            	   //ByosCampo01.lblDescripcion.setValue("xccccc");
			               } catch (Exception e) {
			                   // Ingnored, we'll let the Form handle the errors
			               }
			           }
			         }); 
					ByosCampo01.btoBoton2.addClickListener(new Button.ClickListener() {
			            public void buttonClick(ClickEvent event) {
			               try{
			            	   if(!p.getCodigomedida().equals(bcCodigoMedida.lblNombreCampo.getValue())){
			            	      ListaUnidadesMedidas.getDatagrid().beanContainer.removeItem(itemId);
			            	      ArrayTblProductosMedidas.remove(p);
			                   }else{
			                	  Notification.show("Error","No puede eliminar la unidad minima " , Notification.TYPE_TRAY_NOTIFICATION); 
			                   }
			            	   
			               } catch (Exception e) {
			                   // Ingnored, we'll let the Form handle the errors
			               }
			           }
			         }); 					
					return ByosCampo01;
				}
			});
			
			ListaUnidadesMedidas.getDatagrid().addGeneratedColumn("productosmedidasdefaultcodigo", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTableProductoMedida p = (tblTableProductoMedida)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("Label");
					//ByosCampo01.lblDescripcion.setWidth("10em");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.lblDescripcion.setVisible(true);
					ByosCampo01.lblDescripcion.setWidth("8em");
					ByosCampo01.lblDescripcion.setValue(p.getProductosmedidasdefaultcodigo());
					ByosCampo01.lblDescripcion.setDescription(p.getProductosmedidasdefaultdescripcion());
					
					ByosCampo01.btoBoton1.setVisible(true);
					ByosCampo01.btoBoton1.setBoton(3, "Listar", "");
					ByosCampo01.btoBoton1.addClickListener(new Button.ClickListener() {
			            public void buttonClick(ClickEvent event) {
			               try{
			            	   procesoListarMedidasdefault(p,source);
			               } catch (Exception e) {
			                   // Ingnored, we'll let the Form handle the errors
			               }
			           }
			         }); 					
					return ByosCampo01;
				}
			});
			ListaUnidadesMedidas.getDatagrid().addGeneratedColumn("equivalencia", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTableProductoMedida p = (tblTableProductoMedida)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getEquivalencia(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setEquivalencia(ByosConversores.StringToBigDecimal(event.getText()));
							}
							
						}
					});
					return ByosCampo01;
				}
			});	
			ListaUnidadesMedidas.getDatagrid().addGeneratedColumn("pesoneto", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTableProductoMedida p = (tblTableProductoMedida)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getPesoneto(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setPesoneto(ByosConversores.StringToBigDecimal(event.getText()));
							}
							
						}
					});
					return ByosCampo01;
				}
			});			
			ListaUnidadesMedidas.getDatagrid().addGeneratedColumn("contenidoneto", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTableProductoMedida p = (tblTableProductoMedida)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");	
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getContenidoneto(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setContenidoneto(ByosConversores.StringToBigDecimal(event.getText()));
							}
							
						}
					});
					return ByosCampo01;
				}
			});	
			ListaUnidadesMedidas.getDatagrid().addGeneratedColumn("volumen", new Table.ColumnGenerator() {			
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					final tblTableProductoMedida p = (tblTableProductoMedida)itemId;
					final ByosCampo ByosCampo01 = new ByosCampo("NumberField");
					ByosCampo01.lblNombreCampo.setVisible(false);
					ByosCampo01.setTipoCampo("NumberField");
					ByosCampo01.txtNumerico.setWidth("6em");
					ByosCampo01.txtNumerico.setStyleName("align-right");
					ByosCampo01.txtNumerico.setValue(ByosConversores.BigDecimalToString(p.getVolumen(),3));
					ByosCampo01.txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
						@Override
						public void textChange(TextChangeEvent event) {
							if(event.getText()!=null && !event.getText().equals("")){
								p.setVolumen(ByosConversores.StringToBigDecimal(event.getText()));
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
    

    
	public void procesoListarMedidas(final String TipoListar, final tblTableProductoMedida TblProductoMedida, final Table Tabla, final ByosCampo Campo){
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
	            	           int error =  ValidarProductoMedidaDuplicado(TblUnidadMedida.getCodigomedida(),ArrayTblProductosMedidas);
	            	           if(TipoListar!=null && TipoListar.equals("ByosCampo") && Campo!=null){
	            	        	  if(error > 0){  
		            	             Campo.lblNombreCampo.setValue(TblUnidadMedida.getCodigomedida());
		            	             Campo.lblDescripcion.setValue(TblUnidadMedida.getDescripcion());     
	            	        	  }else{
	            	        		 Notification.show("Error","Unidad de Medida no esta entre las seleccionadas",Notification.TYPE_TRAY_NOTIFICATION); 
	            	        	  }
	            	           }else{
	            	        	  if(error == 0){ 
	            	                 TblProductoMedida.setCodigomedida(TblUnidadMedida.getCodigomedida());
	            	                 TblProductoMedida.setDescripcion(TblUnidadMedida.getDescripcion());
	            	                 TblProductoMedida.setCodigoproducto(TblProducto.getCodigoproducto());
	            	                 Tabla.refreshRowCache();
	            	        	  }else{
	            	        		 Notification.show("Error","Unidad de Medida duplicada en la fila: " + error ,Notification.TYPE_TRAY_NOTIFICATION);    
	            	        	  }
	            	           }
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
	
    public int ValidarProductoMedidaDuplicado(String CodigoMedida, ArrayList <tblTableProductoMedida> ArrayProductoMedida){            
        for(int f=0;f<ArrayProductoMedida.size();f++){  
        	tblProductoMedida TblProductoMedida = ((tblTableProductoMedida)ArrayProductoMedida.get(f)).TblProductoMedida;  
            if((TblProductoMedida.getCodigomedida()!=null && TblProductoMedida.getCodigomedida().equals(CodigoMedida))){
                  return f+1;             
            }
        }
        return 0;
    }  	
	
    
    public String ProductoMedidaDefaultToStringSql(ArrayList ArrayTblProductosMedidasSql, tblTableProductoMedida RegistroActual){
    	String Sql="";
    	if(ArrayTblProductosMedidasSql!=null && ArrayTblProductosMedidasSql.size()>0){
    	   
    	   String Separador="codigoDefault not in (";
           for(int f=0;f<ArrayTblProductosMedidasSql.size();f++){
        	   tblTableProductoMedida TblTableProductoMedida = (tblTableProductoMedida) ArrayTblProductosMedidasSql.get(f);
        	   String InSql = TblTableProductoMedida.getProductoMedidaDefaultSql();
        	   if(InSql!=null && !InSql.equals("")){
          	      Sql = Sql + Separador + TblTableProductoMedida.getProductoMedidaDefaultSql();
        	      Separador=",";
        	   }
           }
    	}
        if(Sql!=null && !Sql.equals("")){
           Sql = Sql + ")";
        }
        String Sql01 = RegistroActual.getProductoMedidaDefaultSql();
        if(Sql01!=null && !Sql01.equals("")){
      	   Sql = Sql + " or codigoDefault in ( " + Sql01 + ")"; 
        }
 	
    	return Sql;
    }	

    @SuppressWarnings("deprecation")
	public void procesoListarMedidasdefault(final tblTableProductoMedida TblTableProcutoMedidas, final Table Tabla){
  	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,490,200);   
	   tblMedidasDefault TblMedidasDefaultListar = new tblMedidasDefault();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigodefault",  String.class,"Codigo",      "", new LikeFilter("codigodefault", ""));
	   ByosColumnas[1] = new ByosColumna("descripcion",    String.class,"Descripicon", "", new LikeFilter("descripcion",    ""));
      
       try{
    	   String InSql = ProductoMedidaDefaultToStringSql(ArrayTblProductosMedidas,TblTableProcutoMedidas);
    	   ArrayList <tblMedidasDefault> TblMedidasDefaultArray = TblMedidasDefaultListar.FiltroMedidasDefault(InSql);
    	   
    	   //if(TblMedidasDefaultArray!=null && TblMedidasDefaultArray.size()>0){    	   
              dwb.getDatagrid().initDatagridByos(TblMedidasDefaultListar.FiltroMedidasDefault(InSql), TblMedidasDefaultListar, ByosColumnas, true);

  	          dwb.botProcesar.addClickListener(new Button.ClickListener(){
                 public void buttonClick(ClickEvent event){         	
                    try{ 
                	    String xCodigo="";
                	    String xDescripcion="";
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL!=null){
            	           if(AL.size()>0){
            	        	 String Separador="";  
            	        	 
            	        	 for(int f=0;f<AL.size();f++){
            	        	     tblMedidasDefault TblMedidasDefault = ((tblMedidasDefault)AL.get(f));	
            	                 xCodigo = xCodigo + Separador + TblMedidasDefault.getCodigodefault();
            	                 xDescripcion = xDescripcion  +  Separador + TblMedidasDefault.getDescripcion();
            	                 Separador=",";
            	             }
            	        	 TblTableProcutoMedidas.setProductosmedidasdefaultcodigo(xCodigo);
            	        	 TblTableProcutoMedidas.setProductosmedidasdefaultdescripcion(xDescripcion);
           	                 Tabla.refreshRowCache();           	        	  
                             dwb.cerrarWindow();
            	           }
            	        
            	        }
                    }catch (Exception e) {
                      e.printStackTrace();
                    }
                 }
  	          });
    	   //}else{
    	//	  Notification.show("Buscar","No hay mas registros", Notification.TYPE_ERROR_MESSAGE);
    	//	  dwb.cerrarWindow();
    		  
    	//   }
       }catch (Exception e) {
            e.printStackTrace();
       }  	
    }  	
    
    public boolean ValidarProductoUnidadMedida(){
       if(bcCodigoMedida.lblNombreCampo==null || bcCodigoMedida.lblNombreCampo.getValue().equals("")){
    	  Notification.show("Error","No a elegido la unidad minima",Notification.TYPE_TRAY_NOTIFICATION);
    	  return false;
       }
       
       for(int f=0;f<ArrayTblProductosMedidas.size();f++){  
       	   tblProductoMedida TblProductoMedida = ((tblTableProductoMedida)ArrayTblProductosMedidas.get(f)).TblProductoMedida; 
       	   if(TblProductoMedida.getCodigomedida().equals(bcCodigoMedida.lblNombreCampo.getValue())){
       		  TblProductoMedida.setEquivalencia(BigDecimal.valueOf(1));
       	   }
           if((TblProductoMedida.getEquivalencia()==null || TblProductoMedida.getEquivalencia().doubleValue()==0) && !TblProductoMedida.getCodigomedida().equals(bcCodigoMedida.lblNombreCampo.getValue())){
        	  Notification.show("Error","No a completado los datos de la fila " + (f+1) ,Notification.TYPE_TRAY_NOTIFICATION); 
              return false;             
           }
       }       
       
       return true;	
    }

    public tblProductoMedida[] getProductoMedida(){
    	if(ArrayTblProductosMedidas!=null && ArrayTblProductosMedidas.size()>0){
    	   tblProductoMedida[] TblProductoMedida = new tblProductoMedida[ArrayTblProductosMedidas.size()];
           for(int f=0;f<ArrayTblProductosMedidas.size();f++){  
        	   TblProductoMedida[f] = new tblProductoMedida();
        	   TblProductoMedida[f] = ((tblTableProductoMedida)ArrayTblProductosMedidas.get(f)).TblProductoMedida;  
           }
           return TblProductoMedida;
    	}
        
        return null;	
     }	
    
    public tblProductoMedidaDefault[] getProductoMedidaDefault(){
    	if(ArrayTblProductosMedidas!=null && ArrayTblProductosMedidas.size()>0){
           int Contador=0;   		
           for(int f=0;f<ArrayTblProductosMedidas.size();f++){  
        	   tblProductoMedidaDefault[] TblProductoMedidaDefault = ((tblTableProductoMedida)ArrayTblProductosMedidas.get(f)).TblProductoMedidaDefault;
        	   if(TblProductoMedidaDefault!=null && TblProductoMedidaDefault.length>0){
        		  Contador+=TblProductoMedidaDefault.length; 
        	   }
           }
           
           if(Contador>0){
              tblProductoMedidaDefault[] TblProductoMedidaDefaultTotal = new tblProductoMedidaDefault[Contador];
              int j=0;
              for(int f=0;f<ArrayTblProductosMedidas.size();f++){  
           	      tblProductoMedidaDefault[] TblProductoMedidaDefault = ((tblTableProductoMedida)ArrayTblProductosMedidas.get(f)).TblProductoMedidaDefault;
           	      if(TblProductoMedidaDefault!=null && TblProductoMedidaDefault.length>0){
           		     for(int c=0;c<TblProductoMedidaDefault.length;c++){
           		    	TblProductoMedidaDefaultTotal[j] = new tblProductoMedidaDefault(); 
           		    	TblProductoMedidaDefaultTotal[j] = TblProductoMedidaDefault[c];
           		        j++;			
           		     }
           	      }
              }              
              return TblProductoMedidaDefaultTotal;
           }
    	}
        
        return null;	
     }	    
    
	    public void procesoAddMedidas(){
	    	ArrayTblProductosMedidas.add(new tblTableProductoMedida());
	    	ListaUnidadesMedidas.getDatagrid().refrescar(ArrayTblProductosMedidas);
	    	
	    }
	    
}
