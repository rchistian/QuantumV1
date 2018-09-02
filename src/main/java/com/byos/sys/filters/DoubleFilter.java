package com.byos.sys.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class DoubleFilter extends ByosFilter {
     
    public DoubleFilter(String propertyId, String txtValor) {
        this.setPropertyId(propertyId);
        this.setValor(txtValor);
    }	

@Override
public boolean passesFilter(Object itemId, Item item)
		throws UnsupportedOperationException {
	 // Acquire the relevant property from the item object
    Property p = item.getItemProperty(propertyId);
    
    // Should always check validity
    if (p == null || !p.getType().equals(Double.class)){
        return false;
    } 
    else{
    
    if (this.getValor() != null && !this.getValor().equals("")){
    
    	Double value = (Double)p.getValue();
    	
    	if (this.getValor().startsWith(">")){
    		try{
    			Double n = Double.parseDouble(this.getValor().substring(1));
    			
    			if (value > n)
    				return true;
    		}
    		catch(Exception e){
    			return false;
    		}
    	}
    	
    	if (this.getValor().startsWith("<")){
    		try{
    			Double n = Double.parseDouble(this.getValor().substring(1));
    			if (value < n)
    				return true;
    		}
    		catch(Exception e){
    			return false;
    		}
    	}
    	
    	if (this.getValor().startsWith("=")){
    		try{
    			Double n = Double.parseDouble(this.getValor().substring(1));
    			if (value - n == 0)
    				return true;
    		}
    		catch(Exception e){
    			return false;
    		}
    	}
    	
    	if (this.getValor().startsWith(">=")){
    		try{
    			Double n = Double.parseDouble(this.getValor().substring(2));
    			if ((value - n) > 0)
    				return true;
    		}
    		catch(Exception e){
    			return false;
    		}
    	}
    	
    	if (this.getValor().startsWith("<=")){
    		try{
    			Double n = Double.parseDouble(this.getValor().substring(2));
    			if ((value - n) < 0)
    				return true;
    		}
    		catch(Exception e){
    			return false;
    		}
    	}
    }
    
    else{
    return true;
    }
    
    
    
    return false;
    }
   
    	
    
	
}

@Override
public boolean appliesToProperty(Object propertyId) {
	// TODO Auto-generated method stub
	 return propertyId != null &&
     propertyId.equals(this.propertyId);
}
}
