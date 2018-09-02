package com.modulo.modulos;

import java.io.Serializable;
import java.util.ArrayList;

import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.util.utilString;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosMenuBar;
import com.modulo.main.frmMenu;
import com.modulo.precios.tblPrecios;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet.Tab;

public class moduloModulos extends VerticalLayout implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;

    public ByosForm ByosFormulario = new ByosForm();    
    public tblModulos ClaseModulos;
    public ByosMenu menu = new ByosMenu();
    Table TablaModuloss = new Table("Moduloss");
    VerticalLayout LayoutTabla = new VerticalLayout();
    Panel Panel01 = new Panel();
    Panel Panel02 = new Panel();
    final BeanItemContainer<tblModulos> BeanModulos = new BeanItemContainer<tblModulos>(tblModulos.class);

    String[][] Menu = {
			{"001",          null, "Nuevo",},
			{"002",          null, "Guardar",},
			{"003",          null, "Eliminar",},
			{"004",          null, "Reporte",}
    };
    
    String DatosVisibles[] = {
                       "codigomodulo", 
                       "descripcion"
                    };

    String DatosTitulos[] = {
                       "Codigo", 
                       "Descripcion"
                    };	
    
    public moduloModulos(){
    	setSizeFull();
    	setSpacing(true);
    	setMargin(false);
    	ClaseModulos = new tblModulos();
    	ByosMenuBar MenuBar = new ByosMenuBar(Menu, "Archivo", 0, opciones(),"Lineal");
    	ByosFormulario.addComponent(MenuBar);
    	ByosFormulario.setClase(ClaseModulos, DatosVisibles, DatosTitulos, null);
    	
    	ByosFormulario.getCampo("codigomodulo").btoBoton1.setBoton(00, "Listar","");
    	ByosFormulario.getCampo("codigomodulo").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("codigomodulo").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {            	
                   try {
                	             
                   } catch (Exception e) {
                       e.printStackTrace();
                   }              
            }
        });
    	
    	ByosFormulario.getCampo("codigomodulo").btoBoton2.setVisible(true);
    	ByosFormulario.getCampo("codigomodulo").btoBoton2.setBoton(00, "Auto Codigo",""); 
    	ByosFormulario.getCampo("codigomodulo").btoBoton2.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {            	
                   try {
                	   
                   } catch (Exception e) {
                       e.printStackTrace();
                   }                
            }
        });    	
    	

    	CrearTabla();
    	
    	LayoutTabla.setSpacing(true);
    	LayoutTabla.setMargin(true);   	
    	Panel02.setContent(TablaModuloss);
    	LayoutTabla.addComponent(Panel02);

    	ByosFormulario.addComponent(LayoutTabla);
    	
    	Panel01.setContent(ByosFormulario);
    	//Panel02.setContent(TablaModuloss);
    	
    	Panel01.setWidth("1000px");
    	//Panel01.setHeight("300px");
    	
    	//Panel02.setWidth("1000px");
    	//Panel02.setHeight("300px");
    	

    	addComponent(Panel01); 
    	//addComponent(Panel02); 
    }
    
	public MenuBar.Command opciones(){
		MenuBar.Command Comando = new MenuBar.Command() {
			public void menuSelected(MenuItem selectedItem) {

				if (selectedItem.getText().equals("Nuevo")) {
					procesoNuevo();
				}

				if (selectedItem.getText().equals("Guardar")) {
                    procesoGuardar();
				}

				if (selectedItem.getText().equals("Eliminar")) {

				}

				if (selectedItem.getText().equals("Reporte")) {

				}
			}
		};
		return Comando;
	}
	
	void CargarBeanItem(){
    	ArrayList <tblModulos> ArrayTblModulos = getArrayTblModulos();
    	BeanModulos.removeAllItems();
    	if(ArrayTblModulos!=null && ArrayTblModulos.size()>0){
    	   for(int f=0;f<ArrayTblModulos.size();f++){
    		   BeanModulos.addBean(ArrayTblModulos.get(f));
    	   }
    	}

	}
    
	void refrescarTabla(){
    	CargarBeanItem();
    	
		TablaModuloss.setBuffered(true);
		TablaModuloss.setContainerDataSource(BeanModulos);	
		TablaModuloss.setVisibleColumns("codigomodulo", "descripcion");
		TablaModuloss.setColumnHeader("codigomodulo","Codigo");
		TablaModuloss.setColumnHeader("descripcion","Descripcion");
	}
	
    public void CrearTabla(){		
    	CargarBeanItem();
    	
		TablaModuloss.setBuffered(true);
		TablaModuloss.setContainerDataSource(BeanModulos);
		TablaModuloss.setVisibleColumns("codigomodulo", "descripcion");
		TablaModuloss.setColumnHeader("codigomodulo","Codigo");
		TablaModuloss.setColumnHeader("descripcion","Descripcion");

		
		TablaModuloss.setSelectable(true);
		TablaModuloss.setPageLength(0);
		// Set all fields as immediate
		TablaModuloss.setTableFieldFactory(new DefaultFieldFactory() {
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				
				AbstractField<?> field = (AbstractField<?>) super.createField(container, itemId, propertyId, uiContext);
				field.setImmediate(true);
				field.setBuffered(true);
				return field;
			}
		});


		TablaModuloss.addValueChangeListener(new Property.ValueChangeListener() {
        public void valueChange(ValueChangeEvent event) {
            // Close the form if the item is deselected
            if (event.getProperty().getValue() == null) {
        		// Preselect a few items
          	    ((tblModulos)ByosFormulario.Clase).Limpiar();
          	    ByosFormulario.refrescar();             	
                return;
            }

            Object selectedItemId = event.getProperty().getValue();
            if(selectedItemId != null){                       	  
            	  tblModulos TblModulos = new tblModulos();            	  
            	  TblModulos = BeanModulos.getItem(selectedItemId).getBean();
            	  ((tblModulos)ByosFormulario.Clase).setTblModulos(TblModulos);
            	  ByosFormulario.refrescar();
            }
            // The form was opened for editing an existing item
            TablaModuloss.setData(null);
        }
		});
    }
    
    
    public void CrearModulos(){
        VerticalLayout layout = new VerticalLayout();                
        layout.setMargin(false); 
        layout.addComponent(new moduloModulos());
        final Tab Tab1 = frmMenu.TabMenu.t.addTab(layout, "Modulos", ByosImagenes.icon[00]);
        
        frmMenu.TabMenu.vectorTab.add(Tab1);
        Tab1.setClosable(true); 
    }
    
    public IndexedContainer CargarModulos(){
    	
    	ArrayList <tblModulos> ArrayTblModulos = new moduloModulos().getArrayTblModulos();
    	String[] OP=null;
    	if(ArrayTblModulos!=null && ArrayTblModulos.size()>0){
    	   OP = new String[ArrayTblModulos.size()];
    	   for(int f=0;f<ArrayTblModulos.size();f++){
    		   OP[f]=String.valueOf(ArrayTblModulos.get(f).getCodigomodulo());
    	   }
    	}       
        
        return ByosContenedores.getContainer(OP);
        
    }
    
    public void procesoNuevo(){
  	    ((tblModulos)ByosFormulario.Clase).Limpiar();
  	    ByosFormulario.refrescar();       	
    }
    
    //Esta Funcion es Provicional, La data debe venir de la DB.TBL tblModulos como un ArrayList
    public ArrayList<tblModulos> getArrayTblModulos(){
    	try {
			return new tblModulos().buscarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return null;
    } 
    
    public void procesoGuardar(){       
  		String Respuesta=new tblModulos().guardar(ClaseModulos);
  		if(Respuesta.equals(utilString.SQL_INSERTADO)){
  			Notification.show("El Registro Fue Guardado Con Exito",Notification.TYPE_TRAY_NOTIFICATION);	
  		}
  		if(Respuesta.equals(utilString.SQL_MODIFICADO)){
  			Notification.show("El Registro Fue Modificado Con Exito",Notification.TYPE_TRAY_NOTIFICATION);	
  		}  		
  		if(Respuesta.equals(utilString.SQL_ERROR)){
  			Notification.show("Error El Registro No Se Pudo Guardar",Notification.TYPE_ERROR_MESSAGE);	
  		}
  		refrescarTabla();
    }
    
    
}