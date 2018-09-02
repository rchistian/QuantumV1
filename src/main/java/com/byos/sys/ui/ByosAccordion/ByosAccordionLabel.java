package com.byos.sys.ui.ByosAccordion;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class ByosAccordionLabel extends Label{
	private String caption;
	private String modulo;
	
	public ByosAccordionLabel(String modulo, String caption){
		this.caption=caption;
		this.modulo=modulo;
		this.setCaption(caption);
		this.setStyleName(Reindeer.LABEL_SMALL);
	}
	
	public String toString(){
		return this.modulo + "." + this.caption; 
	}
	
	
	
	
	
}
