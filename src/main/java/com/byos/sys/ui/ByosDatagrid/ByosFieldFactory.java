package com.byos.sys.ui.ByosDatagrid;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class ByosFieldFactory extends DefaultFieldFactory{
	public String[] colVisible;
	public Boolean[] readOnly;
	
	public ByosFieldFactory(){
				
	}
	
	 public Field createField(Container container, Object itemId,
	            Object propertyId, Component uiContext) {
		TextField field = new TextField((String) propertyId);
		
		if (buscarCampo((String) propertyId)==true){
			field.setReadOnly(true);
		}
		return field;
	 
	 }
	 
	 public boolean buscarCampo(String campo){
		 boolean b = false;
		 
		 int i = colVisible.length;
		 
		 for (int x=0;x<i;x++){
			 if (campo.equals(colVisible)){
				 b = readOnly[x];
				 break;
			 }
		 }
		
		 return b;
	 }
	
	
}
