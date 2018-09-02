package com.byos.sys.ui.ByosTabSheet;

import java.util.Iterator;

import com.byos.sys.app.ByosApp;
import com.byos.sys.ui.ByosDialog.ByosDialog;
import com.byos.sys.util.utilString;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.TabSheetTabImpl;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.themes.Reindeer;

public class ByosTabSheet extends TabSheet  {

	public ByosDialog bd = new ByosDialog("Esta seguro que desea cerrar ventana?");
	public ByosTabSheet(){
		
		this.setStyleName(Reindeer.TABSHEET_HOVER_CLOSABLE);
		//this.setStyleName(Reindeer.LAYOUT_WHITE);
		this.setStyleName("backColorBeige");
		this.setWidth("100%");
		this.setHeight("100%");
		
		
		//CSSInject derechaCaption = new CSSInject();
	    //derechaCaption.setValue(".v-tabsheet-tabitem {color: #c1c9d5;background:#3b5998;}");
	    //this.addComponent(derechaCaption);
	    
	   // CSSInject iCaption = new CSSInject();
	    //iCaption.setValue(".v-tabsheet-tabitem .v-caption{color:#c1c9d5;background:#3b5998;}");
	    //iCaption.setVisible(false);
	    //this.addComponent(iCaption);
	    
		this.setCloseHandler(new CloseHandler(){
			
			public void onTabClose(TabSheet t, final Component c){
				ByosApp.getCurrent().addWindow(bd);
				
				bd.btoSi.addClickListener(new Button.ClickListener() {
		            public void buttonClick(ClickEvent event) {
		            	ByosApp.getCurrent().removeWindow(bd);
		            	closeTab(c);
		            	
		            }}
				);
				
				bd.btoNo.addClickListener(new Button.ClickListener() {
		            public void buttonClick(ClickEvent event) {
		            	ByosApp.getCurrent().removeWindow(bd);
		            }}
				);
				/*if (bd.respuesta.equals("Si")){
				SessionPorUsuario.getApp().getMainWindow().removeWindow(bd);	
				t.removeComponent(c);
				}
				else{
					SessionPorUsuario.getApp().getMainWindow().removeWindow(bd);	
						
				}*/
			}
		});
		
		
	}
	
	
	public void closeTab(Component c){
		this.removeComponent(c);
	}
	
	public void agregarTab(Component modulo, String p){
		boolean existe = false;
		ByosApp BA = (ByosApp)ByosApp.getCurrent();
		
		Iterator<Component> i = BA.getTabControl().getComponentIterator();
		int ii = 0;
		
		while (i.hasNext()){
		
		Component o = (Component)i.next();
		
		if (modulo.getClass().getCanonicalName().equals(o.getClass().getCanonicalName())){
			existe = true;
		    BA = (ByosApp)ByosApp.getCurrent();
			
			BA.getTabControl().setSelectedTab(ii);
			
		    break;		
		}	
		ii = ii + 1;
		}
		
		if (existe==false){
			Tab t =BA.getTabControl().addTab(modulo,p);
			t.setClosable(true);
			BA.getTabControl().setSelectedTab(ii);
		}
		}
	
	
	
	

}
