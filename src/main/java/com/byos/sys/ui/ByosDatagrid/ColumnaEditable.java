package com.byos.sys.ui.ByosDatagrid;


import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;

public class ColumnaEditable
  implements Table.ColumnGenerator
{
  Class tipo;
  
  public ColumnaEditable(Class c)
  {
    this.tipo = c;
  }
  
  public Object generateCell(Table source, Object itemId, Object columnId)
  {
    final Property prop = source.getItem(itemId).getItemProperty(columnId);
    
    TextField t = new TextField();
    t.setValue((String)prop.getValue());
    
    t.addValueChangeListener(new Property.ValueChangeListener()
    {
      public void valueChange(Property.ValueChangeEvent event)
      {
        prop.setValue(event.getProperty().getValue());
      }
    });
    return t;
  }
}
