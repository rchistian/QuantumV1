/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byos.sys.ui.ByosBoton;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class ByosBoton
  extends Button
  implements Serializable, PropertyChangeListener
{
  public ByosBoton() {}
  
  public ByosBoton(Integer Icono, String Descripcion, String Caption)
  {
    setBoton(Icono, Descripcion, Caption);
  }
  
  public ByosBoton(VerticalLayout Layout, Integer Icono, String Descripcion, String Caption)
  {
    setBoton(Icono, Descripcion, Caption);
    Layout.addComponent(this);
  }
  
  public ByosBoton(HorizontalLayout Layout, Integer Icono, String Descripcion, String Caption)
  {
    setBoton(Icono, Descripcion, Caption);
    Layout.addComponent(this);
  }
  
  public void setBoton(Integer Icono, String Descripcion, String Caption)
  {
    setStyleName("link");
    if (Icono != null) {
      setIcon(com.byos.sys.imagenes.ByosImagenes.icon[Icono.intValue()]);
    }
    if (Descripcion != null) {
      setDescription(Descripcion);
    }
    if (Caption != null) {
      setCaption(Caption);
    }
  }
  
  public void propertyChange(PropertyChangeEvent arg0)
  {
    if (arg0.getPropertyName().equals("procesoTerminado")) {
      setEnabled(true);
    }
  }
}
