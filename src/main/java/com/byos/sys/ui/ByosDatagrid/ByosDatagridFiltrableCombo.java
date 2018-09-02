package com.byos.sys.ui.ByosDatagrid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Iterator;

import com.byos.sys.app.ByosApp;
import com.byos.sys.filters.LikeFilter;
import com.byos.sys.ui.ByosBoton.ByosBoton;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ByosDatagridFiltrableCombo extends VerticalLayout implements PropertyChangeListener {

	private CssLayout barraFiltro = new CssLayout();
	private TextField txtFiltro = new TextField();
	private ComboBox cmbOpciones = new ComboBox();
	public ByosBoton botCancelar = new ByosBoton(5,"Cancelar",null);
	public ByosBoton botProcesar = new ByosBoton(0,"Agregar",null);	
	public ByosBoton botSiTodos = new ByosBoton(7,"Todos",null);
	public ByosBoton botNoTodos = new ByosBoton(8,"Ninguno",null);	
	
	private float width;
	private float height;
	private Window w = new Window();
	private ByosDatagrid datagrid;
	public String accion="";
	public PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public ByosDatagridFiltrableCombo(boolean modal, float width, float height){
		this.width=width;
		this.height=height;
		this.setImmediate(true);
		initComponents();
		initListeners();
		this.setSpacing(false);
		//this.setHeight(Sizeable.SIZE_UNDEFINED,Unit.PIXELS);
		this.addComponent(barraFiltro);
		this.addComponent(datagrid);
		
		
		//this.setComponentAlignment(barraFiltro, Alignment.TOP_CENTER);
		this.setComponentAlignment(datagrid, Alignment.MIDDLE_CENTER);
		//this.setExpandRatio(barraFiltro, 0f);
		this.setExpandRatio(datagrid, 1.0f);
		
			if (modal == true){
				
			    //w.setWidth(this.getWidth(),this.getWidthUnits());
			    //w.setHeight(this.getHeight(),this.getHeightUnits());
			    
			    w.setSizeUndefined();
			    w.setContent(this);
			    //this.setSizeFull();
			    w.setResizable(false);
			   // w.getContent().setScrollable(false);
			    w.setModal(true);
			    ByosApp.getCurrent().addWindow(w);    
			    w.center();
			    
			    
			   
			}
			
	}
	
	public Window getWindow(){
		return this.w;
	}
	
	public void cerrarWindow(){
		ByosApp.getCurrent().removeWindow(w);
	}
	
	private void initComponents() {
		this.setWidth(this.width,Unit.PIXELS);
		this.setHeight(this.height, Unit.PIXELS);
		datagrid = new ByosDatagrid();
		datagrid.addPropertyChangeListener(this);
		//datagrid.setWidth("100%");
		//datagrid.setHeight("100%");
		
		barraFiltro.setStyleName(com.vaadin.ui.themes.Reindeer.LAYOUT_BLUE);
		barraFiltro.setWidth("100%");
		
		
		// cmbOpciones
		cmbOpciones = new ComboBox();
		
		barraFiltro.addComponent(cmbOpciones);
		
		
		
		// txtFiltro
		txtFiltro = new TextField();
		barraFiltro.addComponent(txtFiltro);
		
		
		// botProcesar
		
		
		
		barraFiltro.addComponent(botProcesar);
		
		// botCancelar
	

	
		barraFiltro.addComponent(botCancelar);
		
		
	
	}	
	
	
public void initListeners(){
		
		txtFiltro.addTextChangeListener(new TextChangeListener() {
		    public void textChange(TextChangeEvent event) {
		    	
		   	//support.firePropertyChange("accion", new String(""), new String("procesar"));
		    	if (cmbOpciones.getValue() != null){
		    	LikeFilter LF = null;
	            datagrid.beanContainer.removeAllContainerFilters(); 
	            
	            if (event.getText()!=null){
	            LF = new LikeFilter((String)cmbOpciones.getValue(), (String) event.getText());
	            datagrid.beanContainer.addContainerFilter(LF);
	            }
		    	}
		    }});
		
		
		botProcesar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	if (datagrid.getFilasSeleccionadas()!=null){
            	support.firePropertyChange("datagridProcesar", new String(""), new String("datagridProcesar"));       	
            	}
            	}
			});
		
		
		botCancelar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               	support.firePropertyChange("datagridCancelar", new String(""), new String("datagridCancelar")); 
               ByosApp.getCurrent().removeWindow(w); 
            }
			});
		
		botSiTodos.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               	datagrid.setFilasSiTodos();
            }
			});
		
		botNoTodos.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
               	datagrid.setFilasNoTodos();
            }
			});
		
	
		
	}
	
	
	public void initDataCombo(){
		for (int i=0;i<datagrid.colVisible.length;i++){	
			String p = datagrid.colVisible[i];
			String c = datagrid.getColumnHeader(p);
			cmbOpciones.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
			cmbOpciones.addItem(p);
			cmbOpciones.setItemCaption(p, c);
	}
}
	
	public ByosDatagrid getDatagrid(){
		return this.datagrid;
	}


	public void addPropertyChangeListener(PropertyChangeListener actionListener ) {
	    this.support.addPropertyChangeListener( actionListener );
	 }

	 public void removePropertyChangeListener(PropertyChangeListener listener ) {
	    this.support.removePropertyChangeListener( listener );
	 }


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("dataCargada")){
			initDataCombo();
			if (datagrid.multiSelect==true){
				System.out.println("Agregados");
				barraFiltro.addComponent(botSiTodos);
				barraFiltro.addComponent(botNoTodos);
			}
		}
	}
	
	
}
