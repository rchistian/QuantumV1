package com.byos.sys.filters;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class BooleanFilter implements Container.Filter {

	String id1;
	boolean condicion;
	
	public BooleanFilter(String id1, boolean condicion){
		this.id1=id1;	
		this.condicion=condicion;
	}
	
	
	@Override
	public boolean passesFilter(Object itemId, Item item)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
		
		 // Acquire the relevant property from the item object
        Property p1 = item.getItemProperty(id1);
        
        // Should always check validity
        if ((p1 == null || !p1.getType().equals(Boolean.class)) ){
            return false;
	    } 
        else{
        
        Boolean p1Value = (Boolean) p1.getValue();
        	
        if (condicion == true){
        if (p1Value == true){	
        return true;
        }
        }
        
        if (condicion == false){
        if (p1Value == false){	
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

