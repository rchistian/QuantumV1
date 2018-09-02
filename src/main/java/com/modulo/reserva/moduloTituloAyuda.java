package com.modulo.reserva;

import com.modulo.componentes.ByosImagenes;
import com.modulo.huesped.tblHuespedes;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

public class moduloTituloAyuda {

    AbsoluteLayout layout;
    String EstiloCSS = "";
	Label TituloAyuda = new Label();
	int indiceAyuda=0;
    String TextoAyuda[];
    tblHuespedes TblHuesped;
    
    public moduloTituloAyuda(AbsoluteLayout layout, String EstiloCSS, tblHuespedes TblHuesped, String TextoAyuda[]) {
    	this.layout = layout;
    	this.EstiloCSS = EstiloCSS;
    	this.TblHuesped = TblHuesped;
    	this.TextoAyuda = TextoAyuda;
    	Titulo();
        proximaAyuda();  
        BotonAyuda();
    }


	
	/****************************************************************************************************************/
    /*                                                                                                              */
    /*                                              Titulo y Ayuda                                                  */       
    /*                                                                                                              */
    /****************************************************************************************************************/


    void Titulo() {
    	/* Texto de Ayuda de Ayuda */
    	    
        TituloAyuda.setWidth("100%");
        TituloAyuda.setHeight("100px");
        TituloAyuda.setStyleName(EstiloCSS + "LabelTituloAyuda");
        TituloAyuda.setValue("HOLA SALUDOS " + TblHuesped.getApodo());
        layout.addComponent(TituloAyuda, "left: 0px; top: 0px;");
        
        /* Titulo de Datos y fotos */
        
        /* Titulo Izquierda        */
        Label Titulo01 = new Label();
        Titulo01.setWidth("287px");
        Titulo01.setHeight("31px");
        Titulo01.setStyleName(EstiloCSS + "LabelTituloDatos01");
        Titulo01.setValue("INFORMACION DE SUS ESTANCIA");
        layout.addComponent(Titulo01, "left: 0px; top: 108px;");

        /* Titulo Foto             */
        Label Titulo02 = new Label();
        Titulo02.setWidth("449px");
        Titulo02.setHeight("38px");
        Titulo02.setStyleName(EstiloCSS + "LabelTituloDatos02");
        Titulo02.setValue(TblHuesped.getApodo());
        layout.addComponent(Titulo02, "left: 287px; top: 100px;");            
           
        
        /* Titulo Derecha          */
        Label Titulo03 = new Label();
        Titulo03.setWidth("288px");
        Titulo03.setHeight("31px");
        Titulo03.setStyleName(EstiloCSS + "LabelTituloDatos01");
        Titulo03.setValue("INFORMACION DE SUS ESTANCIA");
        layout.addComponent(Titulo03, "left: 735px; top: 108px;");        

        /* Boton Paly             */
        Button btoPlay = new Button("PLAY");
        btoPlay.setWidth("73px");
        btoPlay.setHeight("33px");
        btoPlay.setStyleName(EstiloCSS + "BotonPalyStop");
        layout.addComponent(btoPlay, "left: 287px; top: 107px;");
        btoPlay.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{
            	 proximaAyuda();
  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });
        
        /* Boton Stop             */
        Button btoStop = new Button("STOP");        
        btoStop.setWidth("74px");
        btoStop.setHeight("33px");
        btoStop.setStyleName(EstiloCSS + "BotonPalyStop");
        layout.addComponent(btoStop, "left: 661px; top: 107px;"); 
        
    }

    public void MostrarAyuda() {
    	if (TituloAyuda.isVisible()) {
    		TituloAyuda.setVisible(false);
    	}else{
    		TituloAyuda.setVisible(true);
    	}
    }
    
    public void proximaAyuda() {
    	TituloAyuda.setValue(TextoAyuda[indiceAyuda]);
		indiceAyuda++;
	    if(indiceAyuda>=TextoAyuda.length) {
	    	indiceAyuda=0;
	    }
    }
    
    void BotonAyuda() {
        
        /* Boton Ayuda             */
        Button btoAyuda = new Button();
        btoAyuda.setWidth("30px");
        btoAyuda.setHeight("30px");
        btoAyuda.setStyleName("link");
        btoAyuda.setIcon(ByosImagenes.icon[147]);
        layout.addComponent(btoAyuda, "left: 490px; top: 80px;");
        btoAyuda.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{
            	 MostrarAyuda();
            	 //layout.addComponent(btoAyuda, "left: 507px; top: 80px;");
  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });
        
    }
}
