package com.byos.sys.ui.ByosComboBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;

public class ByosComboBox extends ComboBox {
	
	public ByosComboBox(){
		super();
	}
	
	public void setFilas(ArrayList AL, String[] columnas){
	    Class b = AL.getClass();
	    
		Iterator i = AL.iterator();
		try{
		Object p = b.newInstance();
		
		while (i.hasNext()){
			p = i.next();
			Field f;
			String s="";
			Object temp;
			String sp="";
			for (int y =0;y<columnas.length;y++){
			f = p.getClass().getField(columnas[y]);
			
			temp = f.get(p);
			
			if (temp instanceof Integer){
				 sp = temp.toString();
				}
			
			if (temp instanceof String){
				sp = temp.toString();
			}
			
			if (y==0){
			s = sp;	
			}
			
			else{
			s = s + " " + sp;
			}
			
			}
			this.addItem(s);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
	
	
	
	
	
	
}
