package com.byos.sys.ui.ByosDatagrid;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ByosDatagrid
  extends Table
{
  public BeanItemContainer beanContainer;
  public String[] colVisible;
  public String[] colCabecera;
  public PropertyChangeSupport support = new PropertyChangeSupport(this);
  public boolean multiSelect;
  
  public ByosDatagrid()
  {
    setImmediate(true);
    setStyleName("striped");
    setWidth("100%");
    setHeight("100%");
  }
  
  public void initDatagridByos(ArrayList AL, Object c, String[] columnasVisible, String[] columnasCabecera, Class[] columnasTipo, boolean multiSelect)
  {
    if (columnasVisible.length == columnasCabecera.length)
    {
      this.colVisible = columnasVisible;
      this.colCabecera = columnasCabecera;
      this.multiSelect = multiSelect;
      for (int i = 0; i < columnasVisible.length; i++) {
        addContainerProperty(columnasVisible[i], columnasTipo[i], null, columnasCabecera[i], null, null);
      }
      this.beanContainer = new BeanItemContainer(c.getClass());
      this.beanContainer.addAll(AL);
      
      setContainerDataSource(this.beanContainer);
      setVisibleColumns(columnasVisible);
      setPageLength(50);
      setSelectable(true);
      if (multiSelect) {
        setMultiSelect(true);
      } else {
        setMultiSelect(false);
      }
      setMultiSelectMode(MultiSelectMode.SIMPLE);
      if (columnasVisible.length > 1)
      {
        setFooterVisible(true);
        setColumnFooter(columnasVisible[0], "Cantidad");
        setColumnFooter(columnasVisible[1], String.valueOf(this.beanContainer.size()));
      }
      this.support.firePropertyChange("dataCargada", false, true);
    }
    else
    {
      Notification.show("Numero de columnas visible: " + columnasVisible.length + ", Numero de Columnas Cabecera: " + columnasCabecera.length);
    }
  }
  
  public void refrescar(ArrayList AL)
  {
    this.beanContainer.removeAllItems();
    this.beanContainer.addAll(AL);
    
    setContainerDataSource(this.beanContainer);
    setVisibleColumns(this.colVisible);
  }
  
  public void agregarFilas(ArrayList AL, boolean repetirValores)
  {
    Iterator ite = AL.iterator();
    
    ArrayList bc = new ArrayList();
    Collection s = this.beanContainer.getItemIds();
    
    Iterator<Object> i = s.iterator();
    while (i.hasNext()) {
      bc.add(i.next());
    }
    if (!repetirValores)
    {
      while (ite.hasNext())
      {
        boolean found = false;
        Object temp = ite.next();
        
        Iterator ii = bc.iterator();
        while (ii.hasNext())
        {
          Object o = ii.next();
          if (o.equals(temp)) {
            found = true;
          }
        }
        if (!found) {
          this.beanContainer.addItem(temp);
        }
      }
    }
    else
    {
      this.beanContainer.removeAllItems();
      this.beanContainer.addAll(AL);
    }
    setContainerDataSource(this.beanContainer);
    setVisibleColumns(this.colVisible);
  }
  
  public void setFilasSiTodos()
  {
    Collection s = this.beanContainer.getItemIds();
    if (s != null) {
      setValue(s);
    }
  }
  
  public void setFilasNoTodos()
  {
    setValue(null);
  }
  
  public ArrayList getFilasAll()
  {
    ArrayList<Object> AL = new ArrayList();
    Collection s = this.beanContainer.getItemIds();
    if (s == null)
    {
      AL = null;
    }
    else
    {
      Iterator<Object> i = s.iterator();
      while (i.hasNext()) {
        AL.add(i.next());
      }
    }
    return AL;
  }
  
  public ArrayList getFilasSeleccionadas()
  {
    ArrayList<Object> AL = new ArrayList();
    if (this.multiSelect)
    {
      Set<Object> s = (Set)getValue();
      if (s == null)
      {
        AL = null;
      }
      else
      {
        Iterator<Object> i = s.iterator();
        while (i.hasNext()) {
          AL.add(i.next());
        }
      }
    }
    if (!this.multiSelect)
    {
      Object s = getValue();
      if (s == null) {
        AL = null;
      } else {
        AL.add(s);
      }
    }
    return AL;
  }
  
  public void addPropertyChangeListener(PropertyChangeListener actionListener)
  {
    this.support.addPropertyChangeListener(actionListener);
  }
  
  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.removePropertyChangeListener(listener);
  }
}