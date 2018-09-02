package com.modulo.reserva;

import java.io.Serializable;
import java.sql.Date;

import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosVisorImegenes;
import com.modulo.componentes.utilDate;
import com.modulo.huesped.tblHuespedes;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class moduloConfirmacion extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    AbsoluteLayout layout = new AbsoluteLayout();
   
    tblHuespedes TblHuesped = new tblHuespedes();
    
    tblEstadisticasHuesped TblEstadisticasHuesped = new tblEstadisticasHuesped();
    
    Date FechaActutal = com.modulo.componentes.utilDate.Fecha();
    moduloReserva01 WindowsRaiz;
    Label TituloAyuda = new Label();
    
    /* Iniciar Cabezera */
    int indiceAyuda = 0;
    private String TextoAyuda[] = new String[6];
   
    String EstiloCSS = "GV";
    
    public moduloConfirmacion(String xNumeroDocuemto, moduloReserva01 WindowsRaiz, String EstiloCSS) {
    	this.WindowsRaiz = WindowsRaiz;
    	this.EstiloCSS = EstiloCSS;
        try {
			TblHuesped.buscarCodigo(xNumeroDocuemto);
			TblEstadisticasHuesped.setDataHuesped(TblHuesped);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	setWidth("100%");
        setHeight("100%");
        inicializarModulos();
    }
    
    
    void inicializarModulos() {
    	setTextoAyuda();
        new moduloMenuTop(layout);
        new moduloTituloAyuda(layout, EstiloCSS, TblHuesped, TextoAyuda);
        new moduloCentroFotosDatos(layout, EstiloCSS, TblHuesped);
        new moduloComunicacionDivicion(layout, EstiloCSS);
        new moduloMenuInferior(layout, EstiloCSS, WindowsRaiz);
        addComponent(layout);
    }
        
    
    public void setTextoAyuda() {
        indiceAyuda=0;
    	TextoAyuda[0] = "!!HOLA Srta. " + TblHuesped.getApodo() + " Bienvenida, Como esta UD., Cuanto Tiempo, Digame Que Desea?";
    	TextoAyuda[1] = "Que tipo de habitacion desea?";
    	TextoAyuda[2] = "Cuantas Personas Vienen con UD?";
    	TextoAyuda[3] = "Deque fecha a que cual fecha desea reservar?";
    	TextoAyuda[4] = "En que mas lapuedo ayudar?"; 
    	TextoAyuda[5] = "Gracias por su consulta, espero verla pronto..."; 
    }
    
}
