package com.byos.sys.ui.ByosTextBoxFiltrable;


import com.byos.sys.filters.ByosFilter;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridProtoDos;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

public class ByosTextFieldFiltrable
  extends TextField
{
  public final ByosDatagridProtoDos datagrid;
  public final ByosFilter f;
  
  public ByosTextFieldFiltrable(ByosDatagridProtoDos datagrid, ByosFilter f)
  {
    this.datagrid = datagrid;
    this.f = f;
    initComponent();
  }
  
  public void initComponent()
  {
    addTextChangeListener(new FieldEvents.TextChangeListener()
    {
      public void textChange(FieldEvents.TextChangeEvent event)
      {
        ByosTextFieldFiltrable.this.datagrid.beanContainer.removeAllContainerFilters();
        if (event.getText() != null)
        {
          ByosTextFieldFiltrable.this.f.setValor(event.getText());
          
          ByosTextFieldFiltrable.this.datagrid.beanContainer.addContainerFilter(ByosTextFieldFiltrable.this.f);
        }
      }
    });
  }
}

	
	

