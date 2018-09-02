package com.modulo.reserva;

import com.modulo.componentes.ByosImagenes;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class moduloMenuInferior {
    AbsoluteLayout layout;
    String EstiloCSS = "";
    moduloReserva01 WindowsRaiz;

	public moduloMenuInferior(AbsoluteLayout layout, String EstiloCSS, moduloReserva01 WindowsRaiz) {
		this.WindowsRaiz = WindowsRaiz;
		this.EstiloCSS = EstiloCSS;
		this.layout = layout;
		MenuFinal();
	}
	
    void MenuFinal() {
        
        /* Menu Final    */
        
        /* Botones Izquierdos */
        Button ibtoMenuInfrior01 = new Button();
        ibtoMenuInfrior01.setWidth("93px");
        ibtoMenuInfrior01.setHeight("61px");
        ibtoMenuInfrior01.setIcon(ByosImagenes.icon[128]);
        ibtoMenuInfrior01.setStyleName(EstiloCSS + "BotonMenuFianl01");
        layout.addComponent(ibtoMenuInfrior01, "left: 10px; top: 666px;"); 

        ibtoMenuInfrior01.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                WindowsRaiz.closeWindows();
            	
            }
        }); 
        
        Button ibtoMenuInfrior02 = new Button();
        ibtoMenuInfrior02.setWidth("108px");
        ibtoMenuInfrior02.setHeight("61px");
        ibtoMenuInfrior02.setIcon(ByosImagenes.icon[130]);
        ibtoMenuInfrior02.setStyleName(EstiloCSS + "BotonMenuFianl01");
        layout.addComponent(ibtoMenuInfrior02, "left: 119px; top: 666px;");  
        
        /* Botones Derecha */
        Button dbtoMenuInfrior02 = new Button();
        dbtoMenuInfrior02.setWidth("108px");
        dbtoMenuInfrior02.setHeight("61px");
        dbtoMenuInfrior02.setIcon(ByosImagenes.icon[131]);
        dbtoMenuInfrior02.setStyleName(EstiloCSS + "BotonMenuFianl01");
        layout.addComponent(dbtoMenuInfrior02, "left: 797px; top: 666px;"); 

        Button dbtoMenuInfrior01 = new Button();
        dbtoMenuInfrior01.setWidth("93px");
        dbtoMenuInfrior01.setHeight("61px");
        dbtoMenuInfrior01.setIcon(ByosImagenes.icon[129]);
        dbtoMenuInfrior01.setStyleName(EstiloCSS + "BotonMenuFianl01");
        layout.addComponent(dbtoMenuInfrior01, "left: 922px; top: 666px;");
        
        dbtoMenuInfrior01.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                WindowsRaiz.closeWindows();
            	
            }
        }); 
        
        
    }

}
