package com.byos.sys.ui.ByosWindows;


import com.byos.sys.util.utilDate;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;


public class ByosWindow extends VerticalLayout{

	final HorizontalSplitPanel divisorHorizontal = new HorizontalSplitPanel();
	
	public final HorizontalLayout izquierda;
	public final HorizontalLayout derecha;
	
	
	public ByosWindow(){
		//this.setWidth("400px");
		//this.setHeight("200px");
	
		this.setSizeFull();
		this.setStyleName(Reindeer.LAYOUT_WHITE);
		
		HorizontalLayout menu = new HorizontalLayout();
		menu.setWidth("100%");
		//menu.setStyleName("toolbar-invert");
		//menu.setStyleName(Reindeer.LAYOUT_BLUE);
		menu.setStyleName("backColorBlack");
		//CSSInject derechaCaption = new CSSInject();
	    //derechaCaption.setValue(".v-tabsheet-tabitem {color: #c1c9d5;background:#3b5998;}");
	    //this.addComponent(derechaCaption);
	    
		
		
		
		divisorHorizontal.setHeight("100%");
		//divisorHorizontal.setStyleName("small blue white");
		//divisorHorizontal.setStyleName(Reindeer.LAYOUT_BLUE);	
		
		izquierda = new HorizontalLayout();
		izquierda.setStyleName(Reindeer.LAYOUT_BLUE);
		izquierda.setWidth("100%");
		izquierda.setHeight("100%");
		izquierda.setSizeFull();
		divisorHorizontal.setFirstComponent(izquierda);
		
		
	    derecha = new HorizontalLayout();
		//derecha.setStyleName(Reindeer.LAYOUT_WHITE);
		derecha.setStyleName("backColorBeige");
		derecha.setWidth("100%");
		derecha.setHeight("100%");
		divisorHorizontal.setSecondComponent(derecha);
	    divisorHorizontal.setSplitPosition(250, Unit.PIXELS);
	    divisorHorizontal.setStyleName(Reindeer.LAYOUT_WHITE);
	    
	    
	    /*CssLayout left = new CssLayout();
        left.setSizeUndefined();
        left.addStyleName("left");
        menu.addComponent(left);*/
        Label title = new Label("Quantum");
        title.addStyleName(Reindeer.LABEL_H2);
        menu.addComponent(title);
        //left.addComponent(title);
	    
	    
        /*CssLayout right = new CssLayout();
        right.setSizeUndefined();
        right.addStyleName("right");
        menu.addComponent(right);*/
        String s = "Usuario: jabrache, " + utilDate.fechaDelDia();
        Label usuario = new Label(s);
        usuario.addStyleName(Reindeer.LABEL_SMALL);
        menu.addComponent(usuario);
        //right.addComponent(usuario);
        
        this.addComponent(menu);
		this.addComponent(divisorHorizontal);
		
		this.setExpandRatio(divisorHorizontal, 1.0f);
		
		this.setVisible(true);
	}
	
}
