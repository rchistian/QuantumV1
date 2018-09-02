package com.modulo.reserva;

import com.modulo.componentes.ByosImagenes;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class moduloComunicacionDivicion {

    AbsoluteLayout layout;
    String EstiloCSS = "";
    
	public moduloComunicacionDivicion(AbsoluteLayout layout, String EstiloCSS) {
    	this.layout = layout;
    	this.EstiloCSS = EstiloCSS;	
    	LienaDivisoria();
    	Comunicasiones();
	}
	

    /****************************************************************************************************************/
    /*                                                                                                              */
    /*                                                Linea Divisoria                                               */       
    /*                                                                                                              */
    /****************************************************************************************************************/

    
    void LienaDivisoria(){
    	
        VerticalLayout LayoutLineaDivisoria = new VerticalLayout();
        LayoutLineaDivisoria.setWidth("1024px");
        LayoutLineaDivisoria.setHeight("5px");
        LayoutLineaDivisoria.setStyleName(EstiloCSS + "LayoutLineaDicisoria");
        layout.addComponent(LayoutLineaDivisoria, "left: 0px; top: 450px;");       

    }
    
    /****************************************************************************************************************/
    /*                                                                                                              */
    /*                                                Comunicaciones                                                */       
    /*                                                                                                              */
    /****************************************************************************************************************/

    void Comunicasiones() {    	
        /* Comunicacion             */
        Button dComunicacion01 = new Button();
        dComunicacion01.setWidth("73px");
        dComunicacion01.setHeight("47px");
        dComunicacion01.setIcon(ByosImagenes.icon[133]);
        dComunicacion01.setStyleName(EstiloCSS + "BotonComunicacion");
        layout.addComponent(dComunicacion01, "left: 661px; top: 403px;");       

        /* Comunicacion             */
        Button iComunicacion01 = new Button();
        iComunicacion01.setWidth("73px");
        iComunicacion01.setHeight("47px");
        iComunicacion01.setIcon(ByosImagenes.icon[132]);
        iComunicacion01.setStyleName(EstiloCSS + "BotonComunicacion");
        layout.addComponent(iComunicacion01, "left: 287px; top: 403px;"); 
        
    }
}
