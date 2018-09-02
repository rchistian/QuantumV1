/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modulo.componentes;

import com.vaadin.shared.ui.MarginInfo;
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

        MenuPrincipal.setMargin(new MarginInfo(false, false, false, false));
        //MenuPrincipal.setMargin(true);        
        MenuPrincipal.setSpacing(false);
        MenuPrincipal.setVisible(true);

        MenuAdicional.setMargin(new MarginInfo(false, false, false, false));
        //MenuAdicional.setMargin(true);
        MenuAdicional.setSpacing(false);
        MenuAdicional.setVisible(true);     

        
        this.addComponent(MenuPrincipal);
        this.addComponent(MenuAdicional);
    }
    
}
