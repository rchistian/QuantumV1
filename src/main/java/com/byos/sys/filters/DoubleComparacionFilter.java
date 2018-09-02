package com.byos.sys.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class DoubleComparacionFilter implements Container.Filter {

	String id1;
	String id2;
	String condicion;
	
	public DoubleComparacionFilter(String id1, String id2, String condicion){
		this.id1=id1;
		this.id2=id2;
		this.condicion=condicion;
	}
	
	
	@Override
	public boolean passesFilter(Object itemId, Item item)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
		
		 // Acquire the relevant property from the item object
        Property p1 = item.getItemProperty(id1);
        Property p2 = item.getItemProperty(id2);
        
        // Should always check validity
        if ((p1 == null || !p1.getType().equals(Double.class)) && (p2 == null || !p2.getType().equals(Double.class))){
            return false;
	    } 
        else{
        
        Double p1Value = (Double) p1.getValue();
        Double p2Value = (Double) p2.getValue();
           
        if (condicion.equals("<>")){
        if (p1Value - p2Value == 0){	
        }
        else{
        	return true;
        }
        }
        
        if (condicion.equals("=")){
        if (p1Value - p2Value == 0){	
        return true;
        }	
        }
        
        
        }
		
		return false;
	}

	@Override
	public boolean appliesToProperty(Object propertyId) {
		// TODO Auto-generated method stub
		return false;
	}

}
