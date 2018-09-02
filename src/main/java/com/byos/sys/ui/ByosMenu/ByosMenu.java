/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byos.sys.ui.ByosMenu;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import java.io.Serializable;

/**
 *
 * @author Chistian
 */
public class ByosMenu extends HorizontalLayout implements Serializable{   
    public HorizontalLayout MenuPrincipal = new HorizontalLayout();
    public HorizontalLayout MenuAdicional = new HorizontalLayout();
    
    public ByosMenu(){
        setWidth("100%");
        setMargin(false);
        setSpacing(true);  
                
        MenuPrincipal.setSpacing(false);
        MenuPrincipal.setMargin(false);
        MenuPrincipal.setVisible(true);
        //MenuPrincipal.setStyleName(com.vaadin.ui.themes.Reindeer.LAYOUT_BLUE);
       // MenuPrincipal.setWidth("100%");
     //   MenuPrincipal.setHeight("100%");
        
        MenuAdicional.setMargin(false);
        MenuAdicional.setSpacing(false);
        MenuAdicional.setVisible(true);     
        //MenuAdicional.setStyleName(com.vaadin.ui.themes.Reindeer.LAYOUT_BLUE);
       // MenuAdicional.setWidth("100%");
        //MenuAdicional.setHeight("100%");
        
        this.addComponent(MenuPrincipal);
        this.addComponent(MenuAdicional);
    }
    
}
