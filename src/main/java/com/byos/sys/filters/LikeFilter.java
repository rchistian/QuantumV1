package com.byos.sys.filters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class LikeFilter extends ByosFilter {
    
	    /*protected String propertyId;
	    protected String txtValor;
	    
	    public LikeFilter(){}*/
	    
	    public LikeFilter(String propertyId, String txtValor) {
	        this.setPropertyId(propertyId);
	        this.setValor(txtValor);
	    }	
	
	@Override
	public boolean passesFilter(Object itemId, Item item)
			throws UnsupportedOperationException {
		 // Acquire the relevant property from the item object
        Property p = item.getItemProperty(propertyId);
        
        // Should always check validity
        if (p == null || !p.getType().equals(String.class)){
            return false;
	    } 
        else{
        
        String value = (String) p.getValue();
        if (value == null){
        return true;
        }
                	        	
        if (this.getValor()!=null ){	
        String REGEX = ".*" + this.getValor() + ".*";
		Pattern pat = Pattern.compile(REGEX,Pattern.CASE_INSENSITIVE);
		Matcher m = pat.matcher(value);
        //System.out.println(m.m + "...................................");
		
        // The actual filter logic
        return m.matches();
        }
        else{
        	return true;
        }
        	
        }
	}

	@Override
	public boolean appliesToProperty(Object propertyId) {
		// TODO Auto-generated method stub
		 return propertyId != null &&
         propertyId.equals(this.propertyId);
	}

}
