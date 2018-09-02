package com.byos.sys.ui.ByosDatagrid;


import com.byos.sys.app.ByosApp;
import com.byos.sys.imagenes.ByosImagenes;
import com.byos.sys.ui.ByosBoton.ByosBoton;
import com.byos.sys.ui.ByosTextBoxFiltrable.ByosTextFieldFiltrable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ByosDatagridFiltrableTextbox
  extends VerticalLayout
  implements PropertyChangeListener
{
  private CssLayout barraFiltro = new CssLayout();
  private TextField txtFiltro = new TextField();
  public ByosBoton botCancelar = new ByosBoton(Integer.valueOf(5), "Cancelar", null);
  public ByosBoton botProcesar = new ByosBoton(Integer.valueOf(23), "Agregar", null);
  public ByosBoton botSiTodos = new ByosBoton(Integer.valueOf(7), "Todos", null);
  public ByosBoton botNoTodos = new ByosBoton(Integer.valueOf(8), "Ninguno", null);
  public HorizontalLayout LayoutFiltros = new HorizontalLayout();
  public VerticalLayout LayoutTextos = new VerticalLayout();
  public HorizontalLayout LayoutFecha = new HorizontalLayout();
  
  TextField txtFecha01 = new TextField();
  TextField txtFecha02 = new TextField();
  
  InlineDateField Fecha01 = new InlineDateField();
  InlineDateField Fecha02 = new InlineDateField();
  
  private float width;
  private float height;
  private Window w = new Window();
  private ByosDatagridProtoDos datagrid;
  public String accion = "";
  public PropertyChangeSupport support = new PropertyChangeSupport(this);
  
  public String EstiloTexto="TextoFiled";
  
  @SuppressWarnings("deprecation")
public ByosDatagridFiltrableTextbox(boolean modal, float width, float height)
  {
    this.width = width;
    this.height = height;
    setImmediate(true);
    initComponents();
    initListeners();
    setSpacing(false);
    
    Image Imagen01 = new Image();
    Imagen01.setIcon(ByosImagenes.icon[22]);
    LayoutTextos.setWidth("230px");
    LayoutFecha.setWidth("240px");
    LayoutFecha.setSpacing(false);
    LayoutFecha.setMargin(false);
    
    txtFecha01.setWidth("110px");
    txtFecha01.setInputPrompt("Fecha Inicio");
    txtFecha01.setStyleName(EstiloTexto);
    
    txtFecha02.setWidth("110px");
    txtFecha02.setInputPrompt("Fecha Final");
    txtFecha02.setStyleName(EstiloTexto);
    
    

    Fecha01.setStyleName(EstiloTexto);
    Fecha01.setDateFormat("yyyy-MM-dd");
    Fecha01.addListener(new Property.ValueChangeListener() {
        private static final long serialVersionUID = -2403060484955547831L;
        public void valueChange(final ValueChangeEvent event) {
        	SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        	txtFecha01.setValue(parseador.format(Fecha01.getValue()));
        }
    });

    Fecha02.setStyleName(EstiloTexto);
    Fecha02.setDateFormat("yyyy-MM-dd");
    Fecha02.addListener(new Property.ValueChangeListener() {
        private static final long serialVersionUID = -2403060484955547831L;
        public void valueChange(final ValueChangeEvent event) {
        	SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        	txtFecha02.setValue(parseador.format(Fecha02.getValue()));
        }
    });

    setMargin(true);
    setSpacing(true);
    LayoutFiltros.setSpacing(true);
    LayoutFiltros.setMargin(true);
    LayoutFiltros.setWidth("100%");
    LayoutTextos.setSpacing(false);
    LayoutTextos.setMargin(false);
    Fecha01.setWidth("25%");
    Fecha02.setWidth("25%");
    
    LayoutFecha.addComponent(txtFecha01);
    LayoutFecha.addComponent(txtFecha02);
    
    
    
    LayoutTextos.addComponent(LayoutFecha);
    LayoutTextos.addComponent(Imagen01);
    LayoutTextos.setComponentAlignment(Imagen01, Alignment.MIDDLE_CENTER); 
    LayoutFiltros.addComponent(Fecha01);
    LayoutFiltros.addComponent(LayoutTextos);
    LayoutFiltros.addComponent(Fecha02);
    
    
    addComponent(LayoutFiltros);
    addComponent(this.barraFiltro);
    addComponent(this.datagrid);
    
    setComponentAlignment(this.datagrid, Alignment.MIDDLE_CENTER);
    
    setExpandRatio(this.datagrid, 1.0F);
    if (modal)
    {
      this.w.setSizeUndefined();
      this.w.setContent(this);
      
      this.w.setResizable(false);
      
      this.w.setModal(true);
      ByosApp.getCurrent().addWindow(this.w);
      this.w.center();
    }
  }
  
  public Window getWindow()
  {
    return this.w;
  }
  
  public void cerrarWindow()
  {
    ByosApp.getCurrent().removeWindow(this.w);
  }
  
  private void initComponents()
  {
	 
    setWidth(this.width, Sizeable.UNITS_PIXELS);
    setHeight(this.height, Sizeable.UNITS_PIXELS);
    this.datagrid = new ByosDatagridProtoDos();
    this.datagrid.addPropertyChangeListener(this);
    
    this.barraFiltro.setStyleName("blue");
    this.barraFiltro.setWidth("100%");
  }
  
  public void initListeners()
  {
    this.datagrid.addItemClickListener(new ItemClickEvent.ItemClickListener()
    {
      public void itemClick(ItemClickEvent event)
      {
        if (event.isDoubleClick()) {
          ByosDatagridFiltrableTextbox.this.support.firePropertyChange("datagridProcesar", new String(""), new String("datagridProcesar"));
        }
      }
    });
    this.botProcesar.addClickListener(new Button.ClickListener()
    {
      public void buttonClick(Button.ClickEvent event)
      {
        ByosDatagridFiltrableTextbox.this.support.firePropertyChange("datagridProcesar", new String(""), new String("datagridProcesar"));
      }
    });
    this.botCancelar.addClickListener(new Button.ClickListener()
    {
      public void buttonClick(Button.ClickEvent event)
      {
        ByosDatagridFiltrableTextbox.this.support.firePropertyChange("datagridCancelar", new String(""), new String("datagridCancelar"));
        ByosApp.getCurrent().removeWindow(ByosDatagridFiltrableTextbox.this.w);
      }
    });
    this.botSiTodos.addClickListener(new Button.ClickListener()
    {
      public void buttonClick(Button.ClickEvent event)
      {
        ByosDatagridFiltrableTextbox.this.datagrid.setFilasSiTodos();
      }
    });
    this.botNoTodos.addClickListener(new Button.ClickListener()
    {
      public void buttonClick(Button.ClickEvent event)
      {
        ByosDatagridFiltrableTextbox.this.datagrid.setFilasNoTodos();
      }
    });
  }
  
  public void initFiltroTextfield()
  {
    int len = this.datagrid.byosColumna.length;
    for (int x = 0; x < len; x++) {
      if (this.datagrid.byosColumna[x].byosfilter != null)
      {
        ByosTextFieldFiltrable temp = new ByosTextFieldFiltrable(this.datagrid, this.datagrid.byosColumna[x].byosfilter);
        temp.setInputPrompt(this.datagrid.byosColumna[x].cabecera);

        temp.setWidth("230px");
        temp.setStyleName(EstiloTexto);
        LayoutTextos.addComponent(temp);
      }
    }
    this.barraFiltro.addComponent(this.botProcesar);
    
    this.barraFiltro.addComponent(this.botCancelar);
  }
  
  public ByosDatagridProtoDos getDatagrid()
  {
    return this.datagrid;
  }
  
  public void addPropertyChangeListener(PropertyChangeListener actionListener)
  {
    this.support.addPropertyChangeListener(actionListener);
  }
  
  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.removePropertyChangeListener(listener);
  }
  
  public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("dataCargada"))
    {
      initFiltroTextfield();
      if (this.datagrid.multiSelect)
      {
        System.out.println("Agregados");
        this.barraFiltro.addComponent(this.botSiTodos);
        this.barraFiltro.addComponent(this.botNoTodos);
      }
    }
  }
}
