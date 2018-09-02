package com.modulo.nivel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosMenuBar;


import com.modulo.modulos.tblModulos;
import com.modulo.nivelpermiso.tblNivelPermiso;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;

public class moduloNivel  extends VerticalLayout implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    String[][] Menu = {
			{"001",          null, "Nuevo",},
			{"002",          null, "Guardar",},
			{"003",          null, "Eliminar",},
			{"004",          null, "Reporte",}
    };
    
    public ByosForm ByosFormulario = new ByosForm();    
    public tblNivel ClaseNivel;
    public ByosMenu menu = new ByosMenu();
    Table TablaModuloPermisos = new Table("Modulos");
    Table TablaNivel = new Table("Niveles");
    VerticalLayout LayoutTabla = new VerticalLayout();
    final TwinColSelect select =  new TwinColSelect("Permisos de Modulos");
    
    Panel Panel01 = new Panel();
    Panel Panel02 = new Panel();
    Panel Panel03 = new Panel();
    
    
    HorizontalLayout LayoutModulos = new HorizontalLayout();
    VerticalLayout LayoutPermisos = new VerticalLayout();
    
	HorizontalLayout OP = new HorizontalLayout();
	HorizontalLayout LayoutFolmulario = new HorizontalLayout();
	ByosBoton BtoGuardarPermiso = new ByosBoton(OP,null,"Guardar","Guardar");
	ByosBoton BtoCancelarPermiso = new ByosBoton(OP,null,"Cancelar","Cancelar");
	
    String DatosVisibles[] = {
                       "codigonivel", 
                       "descripcion"
                    };

    String DatosTitulos[] = {
                       "Codigo", 
                       "Descripcion"
                    };	
    
    public moduloNivel(){

    	//setSizeFull();
    	setSpacing(true);
    	setMargin(false);
    	ClaseNivel = new tblNivel();
    	ByosMenuBar MenuBar = new ByosMenuBar(Menu, "Archivo", 0, opciones(),"Lineal");
    	ByosFormulario.addComponent(MenuBar);
    	ByosFormulario.setClase(ClaseNivel, DatosVisibles, DatosTitulos, null);
    	
    	ByosFormulario.getCampo("codigonivel").btoBoton1.setBoton(00, "Listar","");
    	ByosFormulario.getCampo("codigonivel").btoBoton1.setVisible(true);
    	ByosFormulario.getCampo("codigonivel").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {            	
                   try {
                	             
                   } catch (Exception e) {
                       e.printStackTrace();
                   }              
            }
        });
    	
    	ByosFormulario.getCampo("codigonivel").btoBoton2.setVisible(true);
    	ByosFormulario.getCampo("codigonivel").btoBoton2.setBoton(00, "Auto Codigo",""); 
    	ByosFormulario.getCampo("codigonivel").btoBoton2.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {            	
                   try {
                	   
                   } catch (Exception e) {
                       e.printStackTrace();
                   }                
            }
        });    	 
    	CrearTablaModuloPermisos();
    	Permisos();
    	CrearTabla();
    	LayoutPermisos.setVisible(false);
    	//addComponent(ByosFormulario); 
    	OP.setComponentAlignment(BtoGuardarPermiso, Alignment.BOTTOM_CENTER); 
    	OP.setComponentAlignment(BtoCancelarPermiso, Alignment.BOTTOM_CENTER); 
    	
    	LayoutModulos.addComponent(TablaModuloPermisos);
    	LayoutPermisos.addComponent(select);
    	LayoutPermisos.addComponent(OP);
    	LayoutPermisos.setComponentAlignment(OP, Alignment.BOTTOM_CENTER); 
    	LayoutModulos.addComponent(LayoutPermisos);
    	
    	LayoutTabla.setSpacing(true);
    	LayoutTabla.setMargin(true);   	
    	Panel03.setContent(TablaNivel);
    	LayoutTabla.addComponent(Panel03);

    	ByosFormulario.addComponent(LayoutTabla);    	
    	
    	
    	Panel01.setContent(ByosFormulario);
    	Panel01.setWidth("500px");
    	//Panel01.setHeight("300px");
    	Panel02.setContent(LayoutModulos);
    	LayoutModulos.setSpacing(true);
    	Panel02.setWidth("500px");
    	//Panel02.setHeight("300px");
    	LayoutFolmulario.addComponent(Panel01);
    	LayoutFolmulario.addComponent(Panel02);
    	Panel02.setVisible(false);
    	
    	addComponent(LayoutFolmulario);
    	//Panel03.setWidth("1000px");
    	//Panel03.setContent(TablaNivel);
    	//addComponent(Panel03);
    }
    
	public MenuBar.Command opciones(){
		MenuBar.Command Comando = new MenuBar.Command() {
			public void menuSelected(MenuItem selectedItem) {

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
    
    public void CrearTabla(){

    	final BeanItemContainer<tblNivel> BeanNivel = new BeanItemContainer<tblNivel>(tblNivel.class);
    	ArrayList <tblNivel> ArrayTblNivel = getArrayTblNivel();
    	if(ArrayTblNivel!=null && ArrayTblNivel.size()>0){
    	   for(int f=0;f<ArrayTblNivel.size();f++){
    		   BeanNivel.addBean(ArrayTblNivel.get(f));
    	   }
    	}
	

		// This is the buffered editable table
		//final Table editable = new Table("Editable");
		//TablaModuloPermisos.setEditable(true);
    	
		TablaNivel.setBuffered(true);
		TablaNivel.setContainerDataSource(BeanNivel);
		TablaNivel.setVisibleColumns("codigonivel", "descripcion");
		TablaNivel.setColumnHeader("codigonivel","Codigo");
		TablaNivel.setColumnHeader("descripcion","Descripcion");
		
		TablaNivel.setSelectable(true);
		TablaNivel.setPageLength(0);
		// Set all fields as immediate
		TablaNivel.setTableFieldFactory(new DefaultFieldFactory() {
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				
				AbstractField<?> field = (AbstractField<?>) super.createField(container, itemId, propertyId, uiContext);
				field.setImmediate(true);
				field.setBuffered(true);
				return field;
			}
		});


		TablaNivel.addValueChangeListener(new Property.ValueChangeListener() {
        public void valueChange(ValueChangeEvent event) {
            // Close the form if the item is deselected
            if (event.getProperty().getValue() == null) {
        		// Preselect a few items
            	LayoutPermisos.setVisible(false); 
            	Panel02.setVisible(false);
          	    ((tblNivel)ByosFormulario.Clase).Limpiar();
          	    ByosFormulario.refrescar();           	
                return;
            }

            Object selectedItemId = event.getProperty().getValue();
            if(selectedItemId != null){                       	  
            	  tblNivel TblNivel = new tblNivel();            	  
            	  TblNivel = BeanNivel.getItem(selectedItemId).getBean();
            	  LayoutPermisos.setVisible(false); 
            	  ((tblNivel)ByosFormulario.Clase).setTblNivel(TblNivel);
            	  if(!Panel02.isVisible()){
            		  Panel02.setVisible(true); 
            	  }
            	  ByosFormulario.refrescar();
            }
            // The form was opened for editing an existing item
            LayoutPermisos.setVisible(false); 
            TablaNivel.setData(null);
        }
		});
    }

    
    public void CrearTablaModuloPermisos(){

    	final BeanItemContainer<tblModulos> BeanModulos = new BeanItemContainer<tblModulos>(tblModulos.class);
    	ArrayList <tblModulos> ArrayTblModulos = getArrayTblModulos();
    	if(ArrayTblModulos!=null && ArrayTblModulos.size()>0){
    	   for(int f=0;f<ArrayTblModulos.size();f++){
    		   BeanModulos.addBean(ArrayTblModulos.get(f));
    	   }
    	}
	

		// This is the buffered editable table
		//final Table editable = new Table("Editable");
		//TablaModuloPermisos.setEditable(true);
		TablaModuloPermisos.setBuffered(true);
		TablaModuloPermisos.setContainerDataSource(BeanModulos);
		TablaModuloPermisos.setVisibleColumns("codigomodulo", "descripcion");
		TablaModuloPermisos.setColumnHeader("codigomodulo","Codigo");
		TablaModuloPermisos.setColumnHeader("descripcion","Descripcion");
		
		TablaModuloPermisos.setSelectable(true);
		// Set all fields as immediate
		TablaModuloPermisos.setTableFieldFactory(new DefaultFieldFactory() {
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				
				AbstractField<?> field = (AbstractField<?>) super.createField(container, itemId, propertyId, uiContext);
				field.setImmediate(true);
				field.setBuffered(true);
				return field;
			}
		});
		TablaModuloPermisos.setPageLength(0);
		TablaModuloPermisos.setSizeUndefined();
		TablaModuloPermisos.addValueChangeListener(new Property.ValueChangeListener() {
        public void valueChange(ValueChangeEvent event) {
            // Close the form if the item is deselected
            if (event.getProperty().getValue() == null) {
        		// Preselect a few items
            	LayoutPermisos.setVisible(false);
                return;
            }

            Object selectedItemId = event.getProperty().getValue();
            if(selectedItemId != null && ((tblNivel)ByosFormulario.Clase).getCodigonivel()!=null && !((tblNivel)ByosFormulario.Clase).getCodigonivel().equals("")){
                       	  
            	  tblModulos TblModulos = new tblModulos();            	  
            	  TblModulos = BeanModulos.getItem(selectedItemId).getBean();
            	              	  
            	  PermisosAsignados(TblModulos.getCodigomodulo(),((tblNivel)ByosFormulario.Clase).getCodigonivel());
            	  LayoutPermisos.setVisible(true);
               
            }else{
            	LayoutPermisos.setVisible(false); 
            }
            
            // The form was opened for editing an existing item
            TablaModuloPermisos.setData(null);
        }
		});
    }
    
    public void Permisos(){

    		// Set the column captions (optional)
    		select.setLeftColumnCaption("Permisos");
    		select.setRightColumnCaption("Permisos Asignados");
    		        
    		// Put some data in the select
    		
    		for (int pl=0; pl<ByosContenedores.TipoPermisos.length; pl++)
    		    select.addItem(ByosContenedores.TipoPermisos[pl]);

    		// Set the number of visible items
    		select.setRows(ByosContenedores.TipoPermisos.length);

    		// Set the number of visible items
    		//select.setColumns(10);
    		        

    		        
    		select.addListener(new Property.ValueChangeListener() {
    		    public void valueChange(ValueChangeEvent event) {
    		        //BookExamplesUI.getLogger().info(event.getProperty().getType().getName());
    		        //if (event.getProperty().getValue() != null)
    		            //BookExamplesUI.getLogger().info(event.getProperty().getValue().getClass().getName());
    		    }
    		});
    		select.setImmediate(true);		   	
    }
    
    public void PermisosAsignados(Integer codigomodulo, Integer codigonivel){
    	ArrayList <tblNivelPermiso> ArrayTblNivelPermiso = new ArrayList<tblNivelPermiso>();
    	ArrayTblNivelPermiso = getArrayTblNivelPermiso(codigomodulo,codigonivel);
    	
    	if(ArrayTblNivelPermiso!=null && ArrayTblNivelPermiso.size()>0){  	
    	   String[] xPermisos = new String[ArrayTblNivelPermiso.size()];
    	   for(int f=0;f<ArrayTblNivelPermiso.size();f++){
    		   xPermisos[f]=ArrayTblNivelPermiso.get(f).getCodigopermiso();
    	   }
    	
    	   select.setValue(new HashSet<String>(Arrays.asList(xPermisos)));
    	}
    }
    
    public void procesoNuevo(){
    	LayoutPermisos.setVisible(false);
    	Panel02.setVisible(false);
  	    ((tblNivel)ByosFormulario.Clase).Limpiar();
  	    ByosFormulario.refrescar();       	
    }
    
    //Esta Funcion es Provicional, La data debe venir de la DB.TBL tblNivel como un ArrayList
    public ArrayList<tblNivel> getArrayTblNivel(){
    	ArrayList <tblNivel> ArrayTblNivel = new ArrayList<tblNivel>();
    	ArrayTblNivel.add(new tblNivel(1, 1, "Gerente"));
    	ArrayTblNivel.add(new tblNivel(2, 2, "Administrador"));
    	ArrayTblNivel.add(new tblNivel(3, 3, "Supervisor"));   
    	ArrayTblNivel.add(new tblNivel(4, 4, "Auxiliar"));     	    	
    	return ArrayTblNivel;    	
    }
    
    //Esta Funcion es Provicional, La data debe venir de la DB.TBL tblModulos como un ArrayList
    public ArrayList<tblModulos> getArrayTblModulos(){
    	ArrayList <tblModulos> ArrayTblModulos = new ArrayList<tblModulos>();
    	ArrayTblModulos.add(new tblModulos(1, 1, "Empresa"));
    	ArrayTblModulos.add(new tblModulos(2, 2, "Departamentos"));
    	ArrayTblModulos.add(new tblModulos(3, 3, "Usuarios"));   
    	ArrayTblModulos.add(new tblModulos(4, 4, "Productos"));     	    	
    	return ArrayTblModulos;    	
    } 
    
   //Esta Funcion es Provicional, La data debe venir de la DB.TBL tblNivelPermiso como un ArrayList
    public ArrayList<tblNivelPermiso> getArrayTblNivelPermiso(Integer codigomodulo, Integer codgonivel){
    	ArrayList <tblNivelPermiso> ArrayTblNivelPermiso = new ArrayList<tblNivelPermiso>();
    	ArrayTblNivelPermiso.add(new tblNivelPermiso(1, 1, "Consulatr"));
    	ArrayTblNivelPermiso.add(new tblNivelPermiso(2, 2, "Incluir"));    	    	
    	return ArrayTblNivelPermiso;    	
    }    
 
    
}
