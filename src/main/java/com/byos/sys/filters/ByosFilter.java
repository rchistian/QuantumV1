package com.byos.sys.filters;

import com.vaadin.data.Container;
import com.vaadin.data.Item;

public class ByosFilter implements Container.Filter {

	String propertyId;
	String valor;
	
	public ByosFilter(){}
	
	public String getPropertyId(){
		return this.propertyId;
	}
	
	public String getValor(){
		return this.valor;
	}
	
	public void setPropertyId(String propertyId){
		this.propertyId=propertyId;
	}
	
	public void setValor(String valor){
		this.valor=valor;
	}
	
	
	@Override
	public boolean passesFilter(Object itemId, Item item)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean appliesToProperty(Object propertyId) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
