package com.modulo.cargos;

import java.io.Serializable;
import java.util.ArrayList;

import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosMenuBar;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.main.frmMenu;
import com.modulo.nivel.moduloNivel;
import com.modulo.nivel.tblNivel;
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

public class moduloCargos extends VerticalLayout implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;

    public ByosForm ByosFormulario = new ByosForm();    
    public tblCargo ClaseCargo;
    public ByosMenu menu = new ByosMenu();
    Table TablaCargos = new Table("Cargos");
    VerticalLayout LayoutTabla = new VerticalLayout();
    Panel Panel01 = new Panel();
    Panel Panel02 = new Panel();

    String[][] Menu = {
			{"001",          null, "Nuevo",},
			{"002",          null, "Guardar",},
			{"003",          null, "Eliminar",},
			{"004",          null, "Reporte",}
    };
    
    String DatosVisibles[] = {
                       "codigocargo", 
                       "descripcion",
                       "codigonivel"
                    };

    String DatosTitulos[] = {
                       "Codigo", 
                       "Descripcion",
                       "Nivel"
                    };	
    public ByosMenuBar MenuBar;
    
    public moduloCargos(){
    	setSizeFull();
    	setSpacing(true);
    	setMargin(false);
    	ClaseCargo = new tblCargo();
    	MenuBar = new ByosMenuBar(Menu, "Archivo", 0, opciones(),"Lineal");
    	ByosFormulario.addComponent(MenuBar);
    	ByosFormulario.setClase(ClaseCargo, DatosVisibles, DatosTitulos, null);
    	
    	ByosFormulario.getCampo("codigocargo").btoBoton1.setBoton(00, "Listar","");
    	ByosFormulario.getCampo("codigocargo").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("codigocargo").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {            	
                   try {
                	             
                   } catch (Exception e) {
                       e.printStackTrace();
                   }              
            }
        });
    	
    	ByosFormulario.getCampo("codigocargo").btoBoton2.setVisible(true);
    	ByosFormulario.getCampo("codigocargo").btoBoton2.setBoton(00, "Auto Codigo",""); 
    	ByosFormulario.getCampo("codigocargo").btoBoton2.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {            	
                   try {
                	   
                   } catch (Exception e) {
                       e.printStackTrace();
                   }                
            }
        });    	
    	
    	ByosFormulario.getCampo("codigonivel").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("codigonivel").btoBoton1.setBoton(00, "Crear Nivel","");     	
    	ByosFormulario.setTipoCampo("codigonivel", "ComboBox");             
    	ByosFormulario.getCampo("codigonivel").CboxItem.setContainerDataSource(CargarNiveles());  
    	ByosFormulario.getCampo("codigonivel").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    CrearNivel();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        }); 
    	CrearTabla();
    	LayoutTabla.setSpacing(true);
    	LayoutTabla.setMargin(true);   	
    	Panel02.setContent(TablaCargos);
    	LayoutTabla.addComponent(Panel02);

    	ByosFormulario.addComponent(LayoutTabla);
    	Panel01.setContent(ByosFormulario);
    	
    	//Panel02.setContent(TablaCargos);
    	
    	Panel01.setWidth("1000px");
    	//Panel01.setHeight("300px");
    	
    	//Panel02.setWidth("1000px");
    	//Panel02.setHeight("300px");
    	

    	addComponent(Panel01); 
    	//addComponent(Panel02); 
    }
    
    public void CrearTabla(){

    	final BeanItemContainer<tblCargo> BeanCargo = new BeanItemContainer<tblCargo>(tblCargo.class);
    	ArrayList <tblCargo> ArrayTblCargo = getArrayTblCargo();
    	if(ArrayTblCargo!=null && ArrayTblCargo.size()>0){
    	   for(int f=0;f<ArrayTblCargo.size();f++){
    		   BeanCargo.addBean(ArrayTblCargo.get(f));
    	   }
    	}
	

		// This is the buffered editable table
		//final Table editable = new Table("Editable");
		//TablaModuloPermisos.setEditable(true);
    	
		TablaCargos.setBuffered(true);
		TablaCargos.setContainerDataSource(BeanCargo);
		TablaCargos.setVisibleColumns("codigocargo", "descripcion","codigonivel");
		TablaCargos.setColumnHeader("codigocargo","Codigo");
		TablaCargos.setColumnHeader("descripcion","Descripcion");
		TablaCargos.setColumnHeader("codigonivel","Codigo Nivel");
		
		TablaCargos.setSelectable(true);
		TablaCargos.setPageLength(0);
		// Set all fields as immediate
		TablaCargos.setTableFieldFactory(new DefaultFieldFactory() {
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				
				AbstractField<?> field = (AbstractField<?>) super.createField(container, itemId, propertyId, uiContext);
				field.setImmediate(true);
				field.setBuffered(true);
				return field;
			}
		});


		TablaCargos.addValueChangeListener(new Property.ValueChangeListener() {
        public void valueChange(ValueChangeEvent event) {
            // Close the form if the item is deselected
            if (event.getProperty().getValue() == null) {
        		// Preselect a few items
          	    ((tblCargo)ByosFormulario.Clase).Limpiar();
          	    ByosFormulario.refrescar();             	
                return;
            }

            Object selectedItemId = event.getProperty().getValue();
            if(selectedItemId != null){                       	  
            	  tblCargo TblCargo = new tblCargo();            	  
            	  TblCargo = BeanCargo.getItem(selectedItemId).getBean();
            	  ((tblCargo)ByosFormulario.Clase).setTblCargo(TblCargo);
            	  ByosFormulario.refrescar();
            }
            // The form was opened for editing an existing item
            TablaCargos.setData(null);
        }
		});
    }
    
	public MenuBar.Command opciones(){
		MenuBar.Command Comando = new MenuBar.Command() {
			public void menuSelected(MenuItem selectedItem) {
                System.out.println(selectedItem.getText());
				if (selectedItem.getText().equals("Nuevo")) {
                    procesoNuevo();
				}

				if (selectedItem.getText().equals("Guardar")) {

				}

				if (selectedItem.getText().equals("Eliminar")) {

				}

				if (selectedItem.getText().equals("Reporte")) {

				}
			}
		};
		return Comando;
	}
    
    
    public void CrearNivel(){
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
    
    public IndexedContainer CargarNiveles(){
    	
    	ArrayList <tblNivel> ArrayTblNivel = new moduloNivel().getArrayTblNivel();
    	String[] OP=null;
    	if(ArrayTblNivel!=null && ArrayTblNivel.size()>0){
    	   OP = new String[ArrayTblNivel.size()];
    	   for(int f=0;f<ArrayTblNivel.size();f++){
    		   OP[f]=String.valueOf(ArrayTblNivel.get(f).getCodigonivel());
    	   }
    	}       
        
        return ByosContenedores.getContainer(OP);
        
    }
    
    public void procesoNuevo(){
  	    ((tblCargo)ByosFormulario.Clase).Limpiar();
  	    ByosFormulario.refrescar();     	
    }
    
    //Esta Funcion es Provicional, La data debe venir de la DB.TBL tblCargo como un ArrayList
    public ArrayList<tblCargo> getArrayTblCargo(){
    	ArrayList <tblCargo> ArrayTblCargo = new ArrayList<tblCargo>();
    	ArrayTblCargo.add(new tblCargo(1, 1, "Precidente",    1));
    	ArrayTblCargo.add(new tblCargo(2, 2, "Gerente"   ,    2));
    	ArrayTblCargo.add(new tblCargo(3, 3, "Administrador", 3));   
    	ArrayTblCargo.add(new tblCargo(4, 4, "Auxiliar",      4));     	    	
    	return ArrayTblCargo;    	
    }    
    
}
