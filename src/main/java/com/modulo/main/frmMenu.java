package com.modulo.main;


import com.modulo.cargos.moduloCargos;
import com.modulo.componentes.ByosMenuBar;
import com.vaadin.ui.VerticalLayout;
import com.modulo.componentes.ByosImagenes;
import com.modulo.compras.moduloCompras;
import com.modulo.departamento.moduloDepartamento;
import com.modulo.deposito.moduloDeposito;
import com.modulo.gruposproducto.moduloGruposProducto;
import com.modulo.impuestos.moduloImpuestos;
import com.modulo.marca.moduloMarca;
import com.modulo.medidasdefault.moduloMedidasDefault;
import com.modulo.modulos.moduloModulos;
import com.modulo.nivel.moduloNivel;
import com.modulo.precios.moduloPrecios;
import com.modulo.producto.moduloProducto;
import com.modulo.proveedor.moduloProveedor;
import com.modulo.subgruposproducto.moduloSubGruposProducto;
import com.modulo.sustanciascontrolada.moduloSustanciasControladas;
import com.modulo.traslado.moduloTraslado;
import com.modulo.unidadmedida.moduloUnidadMedida;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class frmMenu extends VerticalLayout { 
	
	String[][] Menu = {
			{"001",          null, "Mantenimiento",},
			
			{"001001",       "57",  "Inventario"},
			{"001001001",    "28",  "Producto"},
			{"001001002",    "35",  "Unidad de Medida"},
			{"001001003",    "36",  "Marca"},
			{"001001004",    "40",  "Grupo de Producto"},
			{"001001005",    "41",  "SubGrupo de Producto"},
			{"001001006",    "42",  "Precios"},
			{"001001007",    "39",  "Talla"},
			{"001001008",    "44",  "Medida x Defecto"},
			{"001001009",    "45",  "Sustencias Controladas"},
			
			{"001002",       "58",  "Otros"},
			{"001002001",    "52",  "Clientes"},
			{"001002002",    "51",  "Proveedores"},
			{"001002003",    "37",  "Depositos"},
			{"001002004",    "38",  "Departamentos"},
			{"001002005",    "64",  "Vendedores"},
			{"001002006",    "59",  "Bancos"},
			{"001002007",    "60",  "Cuentas Bancarias"},
			{"001002008",    "63",  "Tipos de Pago"},
			{"001002009",    "43",  "Impuestos"},
			
			
			{"002",          null,  "Compras"},
			{"002001",       "53",  "Compra"},
			{"002002",       "39",  "Traslado"},
			{"002003",       "54",  "Devolucion en Compra"},
			
			{"003",          null,  "Ventas"},
			{"003001",       "55",  "Facturacion"},
			{"003002",       "54",  "Devolucion en Ventas"},
			
			{"004",          null,  "Cuentas"},
			{"004001",       "62",  "Cuentas x Pagar"},
			{"004002",       "61",  "Cuentas x Cobrar"},
			
			{"005",          null,  "Contabilidad"},
			{"005001",       "65",  "Cuentas"},
			{"005002",       "66",  "Asientos"},
			{"005003",       "67",  "Tipos de Asientos"},
			
			{"006",          null,  "Reportes"},
			
			{"007",          null,  "Varios"},
			{"007001",       null,  "Fecha"},
			
			{"007002",       null,  "Seguridad"},
			{"007002001",    null,  "Usuarios"},
			{"007002002",    null,  "Cargos"},
			{"007002003",    null,  "Niveles"},
			{"007002004",    null,  "Modulos"},
					
			{"007003",       null,  "Ajustes de Sistema"},
			{"007004",       null,  "Seguridad"},
			{"007005",       null,  "Respaldo"},
			{"007006",       null,  "Salir"},
	};
	
    public static TabMenu TabMenu = new TabMenu();
    public static ByosMenuBar MenuBar;
    
    public frmMenu() {
        setMargin(true);
        setSpacing(false);
        opciones();
        MenuBar = new ByosMenuBar(Menu, "Byos", 70, opciones(),null);
        addComponent(MenuBar);
        MenuBar.setWidth("100%");
        MenuBar.setHeight("100%");
        TabMenu.setWidth("100%");
        TabMenu.setHeight("100%");  
        //TabMenu.setStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);
        TabMenu.setStyleName(ValoTheme.TABSHEET_FRAMED);
        addComponent(TabMenu);        
    }
    
	public MenuBar.Command opciones(){
		MenuBar.Command Comando = new MenuBar.Command() {
		    public void menuSelected(MenuItem selectedItem) {
                   
		    	   if(selectedItem.getText().equals("Producto")){
                      if(!TabMenu.VerificarTab("Producto")){ 
                         VerticalLayout layout = new VerticalLayout();                
                         layout.setMargin(false); 
                         layout.addComponent(new moduloProducto());
                         final Tab Tab1 = TabMenu.t.addTab(layout, "Producto", ByosImagenes.icon[28]);
                         
                         TabMenu.vectorTab.add(Tab1);
                         Tab1.setClosable(true);                
                      }
                      else{
                     	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                      }
                   }
                  
                   if(selectedItem.getText().equals("Unidad de Medida")){
                      if(!TabMenu.VerificarTab("Unidad de Medida")){ 
                         VerticalLayout layout = new VerticalLayout();                
                         layout.setMargin(false); 
                         layout.addComponent(new  moduloUnidadMedida());
                         final Tab Tab1 = TabMenu.t.addTab(layout, "Unidad de Medida", ByosImagenes.icon[40]);
                         
                         TabMenu.vectorTab.add(Tab1);
                         Tab1.setClosable(true);                 
                      }                       
                      else{
                    	 Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION); 
                      }  
                   }
                   
                   if(selectedItem.getText().equals("Marca")){
                      if(!TabMenu.VerificarTab("Marca")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloMarca());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Marca", ByosImagenes.icon[36]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                       }
                       else{
                      	   Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                       } 
                   }

                   if(selectedItem.getText().equals("Grupo de Producto")){
                      if(!TabMenu.VerificarTab("Grupo de Producto")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloGruposProducto());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Grupo de Producto", ByosImagenes.icon[40]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                       }
                       else{
                       	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                       }
                   }
                   
                   if(selectedItem.getText().equals("SubGrupo de Producto")){
                      if(!TabMenu.VerificarTab("SubGrupo de Producto")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloSubGruposProducto());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "SubGrupo de Producto", ByosImagenes.icon[41]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                       }
                       else{
                      	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                       } 
                   }
                    
                   if(selectedItem.getText().equals("Precios")){
                      if(!TabMenu.VerificarTab("Precios")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloPrecios());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Precios", ByosImagenes.icon[42]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                       }
                       else{
                    	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                       }
                   }
                   
                   if(selectedItem.getText().equals("Talla")){
                      if(!TabMenu.VerificarTab("Talla")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          //layout.addComponent(new  moduloTallas());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Talla", ByosImagenes.icon[39]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                       }
                       else{
                      	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                       }
                   }
                   
                   if(selectedItem.getText().equals("Medida x Defecto")){
                      if(!TabMenu.VerificarTab("Medida x Defecto")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloMedidasDefault());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Medida x Defecto", ByosImagenes.icon[44]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                      }
                      else{
                      	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                      }
                   }
                   
                   if(selectedItem.getText().equals("Sustencias Controladas")){                   
                      if(!TabMenu.VerificarTab("Sustencias Controladas")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloSustanciasControladas());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Sustencias Controladas", ByosImagenes.icon[45]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                      }
                      else{
                    	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                      }
                   }
                   
                   if(selectedItem.getText().equals("Depositos")){
                      if(!TabMenu.VerificarTab("Depositos")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloDeposito());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Depositos", ByosImagenes.icon[37]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                      }
                      else{
                    	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                      }
                   }
                   
                   if(selectedItem.getText().equals("Departamentos")){
                      if(!TabMenu.VerificarTab("Departamentos")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloDepartamento());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Departamentos", ByosImagenes.icon[38]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                      }
                      else{
                     	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                      }
                   }
                   
                   if(selectedItem.getText().equals("Impuestos")){
                       if(!TabMenu.VerificarTab("Impuestos")){ 
                           VerticalLayout layout = new VerticalLayout();                
                           layout.setMargin(false); 
                           layout.addComponent(new  moduloImpuestos());
                           final Tab Tab1 = TabMenu.t.addTab(layout, "Impuestos", ByosImagenes.icon[43]);
                           TabMenu.vectorTab.add(Tab1);
                           Tab1.setClosable(true);                
                       }
                       else{
                      	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                       }
                   }  
                   
                   if(selectedItem.getText().equals("Proveedores")){
                       if(!TabMenu.VerificarTab("Proveedores")){ 
                           VerticalLayout layout = new VerticalLayout();                
                           layout.setMargin(false); 
                           layout.addComponent(new  moduloProveedor());
                           final Tab Tab1 = TabMenu.t.addTab(layout, "Proveedores", ByosImagenes.icon[51]);
                           TabMenu.vectorTab.add(Tab1);
                           Tab1.setClosable(true);                
                       }
                       else{
                      	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                       }
                   }                    
                   
                   if(selectedItem.getText().equals("Compra")){
                      if(!TabMenu.VerificarTab("Compra")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloCompras());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Compra", ByosImagenes.icon[38]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                      }
                      else{
                    	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                      } 
                   }
                   
                   if(selectedItem.getText().equals("Traslado")){
                      if(!TabMenu.VerificarTab("Traslado")){ 
                          VerticalLayout layout = new VerticalLayout();                
                          layout.setMargin(false); 
                          layout.addComponent(new  moduloTraslado());
                          final Tab Tab1 = TabMenu.t.addTab(layout, "Traslado", ByosImagenes.icon[38]);
                          TabMenu.vectorTab.add(Tab1);
                          Tab1.setClosable(true);                
                      }
                      else{
                    	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                      }                   
                   }
                   
               
		    
	    	   if(selectedItem.getText().equals("Cargos")){
                   if(!frmMenu.TabMenu.VerificarTab("Cargos")){ 
                      VerticalLayout layout = new VerticalLayout();                
                      layout.setMargin(false); 
                      layout.addComponent(new moduloCargos());
                      final Tab Tab1 = frmMenu.TabMenu.t.addTab(layout, "Cargos", ByosImagenes.icon[00]);
                      
                      frmMenu.TabMenu.vectorTab.add(Tab1);
                      Tab1.setClosable(true);                
                   }
                   else{
                  	  Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION);
                   }
                }
               
                if(selectedItem.getText().equals("Niveles")){
                   if(!frmMenu.TabMenu.VerificarTab("Niveles")){ 
                      VerticalLayout layout = new VerticalLayout();                
                      layout.setMargin(false); 
                      layout.addComponent(new moduloNivel());
                      final Tab Tab1 = frmMenu.TabMenu.t.addTab(layout, "Niveles", ByosImagenes.icon[00]);
                      
                      frmMenu.TabMenu.vectorTab.add(Tab1);
                      Tab1.setClosable(true);                 
                   }                       
                   else{
                 	 Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION); 
                   }  
                }
                
                if(selectedItem.getText().equals("Modulos")){
                    if(!frmMenu.TabMenu.VerificarTab("Modulos")){ 
                       VerticalLayout layout = new VerticalLayout();                
                       layout.setMargin(false); 
                       layout.addComponent(new moduloModulos());
                       final Tab Tab1 = frmMenu.TabMenu.t.addTab(layout, "Modulos", ByosImagenes.icon[00]);
                       
                       frmMenu.TabMenu.vectorTab.add(Tab1);
                       Tab1.setClosable(true);                 
                    }                       
                    else{
                  	 Notification.show("El modulo ya esta abierto" , Notification.TYPE_TRAY_NOTIFICATION); 
                    }  
                 }                  
                
            		    
		    }
		    
		};
		return Comando;
	}    
    
	/*
    public static ByosMenuBar MenuBar = new ByosMenuBar();
    public frmMenu() {
        setMargin(false);
        setSpacing(false);
        addComponent(MenuBar);      
    }
    */
}