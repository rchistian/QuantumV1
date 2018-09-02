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
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class moduloGolpeVista extends VerticalLayout  implements Serializable{
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
    
    public moduloGolpeVista(String xNumeroDocuemto, moduloReserva01 WindowsRaiz, String EstiloCSS) {
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

    	Embedded reindeerImage = null;
        reindeerImage = new Embedded( null, new ThemeResource("imagen/FondoGolpeVista905x768C.jpg"));
        reindeerImage.setWidth( "100%" ); 
        reindeerImage.setHeight( "100%" );
        reindeerImage.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                     	
            }
        });
        //layout.addComponent(reindeerImage);
        inicializarModulos();
    }
    
    
    void inicializarModulos() {
    	setTextoAyuda();
        new moduloMenuTop(layout);
        new moduloTituloAyuda(layout, EstiloCSS, TblHuesped, TextoAyuda);
        new moduloCentroFotosDatos(layout, EstiloCSS, TblHuesped);
        new moduloDatosEstadisticos(layout, EstiloCSS, TblHuesped);
        new moduloComunicacionDivicion(layout, EstiloCSS);
        DatosInferior();
        new moduloMenuInferior(layout, EstiloCSS, WindowsRaiz);
        addComponent(layout);
    }
    

    

    
    /****************************************************************************************************************/
    /*                                                                                                              */
    /*                                                Datos Inferiores                                              */       
    /*                                                                                                              */
    /****************************************************************************************************************/
    
    void DatosInferior() {
        
        /****************************************** Segunda Parte de Form  ***************************************************/
        
        /* Botones Centrales               */
        
        /* Primera Fila                    */
        Button ibtoMenuCentral01 = new Button();
        ibtoMenuCentral01.setWidth("156px");
        ibtoMenuCentral01.setHeight("61px");
        ibtoMenuCentral01.setIcon(ByosImagenes.icon[135]);
        ibtoMenuCentral01.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(ibtoMenuCentral01, "left: 234px; top: 459px;"); 

        Button cbtoMenuCentral01 = new Button();
        cbtoMenuCentral01.setWidth("239px");
        cbtoMenuCentral01.setHeight("61px");
        cbtoMenuCentral01.setIcon(ByosImagenes.icon[137]);
        cbtoMenuCentral01.setStyleName(EstiloCSS + "BotonMenuCentral02");
        layout.addComponent(cbtoMenuCentral01, "left: 392px; top: 459px;");  
        cbtoMenuCentral01.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
               try {
            	   new moduloReserva01(TblHuesped.getNumeroDocumento(), "RS","RECOJER LOS DATOS PARA LA RESERVA REALIZADA EL " + utilDate.FormatoFecha(utilDate.Fecha(), "dd MMM yyyy") ).openWindows();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            }
         });
        
        Button dbtoMenuCentral01 = new Button();
        dbtoMenuCentral01.setWidth("156px");
        dbtoMenuCentral01.setHeight("61px");
        dbtoMenuCentral01.setIcon(ByosImagenes.icon[136]);
        dbtoMenuCentral01.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(dbtoMenuCentral01, "left: 633px; top: 459px;"); 

        /* Segunda Fila                    */
        Button ibtoMenuCentral02 = new Button();
        ibtoMenuCentral02.setWidth("156px");
        ibtoMenuCentral02.setHeight("61px");
        ibtoMenuCentral02.setIcon(ByosImagenes.icon[139]);
        ibtoMenuCentral02.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(ibtoMenuCentral02, "left: 234px; top: 528px;"); 

        Button cbtoMenuCentral02 = new Button();
        cbtoMenuCentral02.setWidth("239px");
        cbtoMenuCentral02.setHeight("61px");
        cbtoMenuCentral02.setIcon(ByosImagenes.icon[138]);
        cbtoMenuCentral02.setStyleName(EstiloCSS + "BotonMenuCentral02");
        layout.addComponent(cbtoMenuCentral02, "left: 392px; top: 528px;");         
        
        Button dbtoMenuCentral02 = new Button();
        dbtoMenuCentral02.setWidth("156px");
        dbtoMenuCentral02.setHeight("61px");
        dbtoMenuCentral02.setIcon(ByosImagenes.icon[140]);
        dbtoMenuCentral02.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(dbtoMenuCentral02, "left: 633px; top: 528px;"); 

        /* Tersera Fila                    */
        Button ibtoMenuCentral03 = new Button();
        ibtoMenuCentral03.setWidth("156px");
        ibtoMenuCentral03.setHeight("61px");
        ibtoMenuCentral03.setIcon(ByosImagenes.icon[141]);
        ibtoMenuCentral03.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(ibtoMenuCentral03, "left: 234px; top: 597px;"); 

        Button cbtoMenuCentral03 = new Button();
        cbtoMenuCentral03.setWidth("239px");
        cbtoMenuCentral03.setHeight("61px");
        cbtoMenuCentral03.setIcon(ByosImagenes.icon[146]);
        cbtoMenuCentral03.setStyleName(EstiloCSS + "BotonMenuCentral02");
        layout.addComponent(cbtoMenuCentral03, "left: 392px; top: 597px;");         
        cbtoMenuCentral03.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
               try {
            	   new moduloReserva01(TblHuesped.getNumeroDocumento(), "LLG","LLAMADA PARA COORDINAR SU LLEGADA EL " + utilDate.FormatoFecha(utilDate.Fecha(), "dd MMM yyyy") ).openWindows();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            }
         });
        
        Button dbtoMenuCentral03 = new Button();
        dbtoMenuCentral03.setWidth("156px");
        dbtoMenuCentral03.setHeight("61px");
        dbtoMenuCentral03.setIcon(ByosImagenes.icon[142]);
        dbtoMenuCentral03.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(dbtoMenuCentral03, "left: 633px; top: 597px;");         
        
        /* Cuarta Fila                    */
        Button ibtoMenuCentral04 = new Button();
        ibtoMenuCentral04.setWidth("156px");
        ibtoMenuCentral04.setHeight("61px");
        ibtoMenuCentral04.setIcon(ByosImagenes.icon[143]);
        ibtoMenuCentral04.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(ibtoMenuCentral04, "left: 234px; top: 666px;"); 

        Button cbtoMenuCentral04 = new Button();
        cbtoMenuCentral04.setWidth("239px");
        cbtoMenuCentral04.setHeight("61px");
        cbtoMenuCentral04.setIcon(ByosImagenes.icon[144]);
        cbtoMenuCentral04.setStyleName(EstiloCSS + "BotonMenuCentral02");
        layout.addComponent(cbtoMenuCentral04, "left: 392px; top: 666px;");
        cbtoMenuCentral04.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
               try {
            	   new moduloReserva01(TblHuesped.getNumeroDocumento(), "CF","COMFIRMACION DE SU LLEGADA EL " + utilDate.FormatoFecha(utilDate.Fecha(), "dd MMM yyyy") ).openWindows();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            }
         });
        
        Button dbtoMenuCentral04 = new Button();
        dbtoMenuCentral04.setWidth("156px");
        dbtoMenuCentral04.setHeight("61px");
        dbtoMenuCentral04.setIcon(ByosImagenes.icon[145]);
        dbtoMenuCentral04.setStyleName(EstiloCSS + "BotonMenuCentral01");
        layout.addComponent(dbtoMenuCentral04, "left: 633px; top: 666px;");    
        

        
        /* Datos Estadisticos Lado Izquierdo Inferior     */
        
        /* Dato 01 Titulo Pensiones completas   */
        Label iTDatoInfoInferior01 = new Label();
        iTDatoInfoInferior01.setWidth("220px");
        iTDatoInfoInferior01.setHeight("19px");
        iTDatoInfoInferior01.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfoInferior01.setValue("Penciones Completas");
        layout.addComponent(iTDatoInfoInferior01, "left: 10px; top: 458px;");   
        
        /* PensionesCompletas                     */
        Label iDatoInfoInferior01 = new Label();
        iDatoInfoInferior01.setWidth("104px");
        iDatoInfoInferior01.setHeight("33px");
        iDatoInfoInferior01.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferior01.setValue(TblEstadisticasHuesped.getPensionesCompletas());
        layout.addComponent(iDatoInfoInferior01, "left: 10px; top: 475px;");         
        
        /* TotalPensionesCompletas                */
        Label iDatoInfoInferiorT01 = new Label();
        iDatoInfoInferiorT01.setWidth("104px");
        iDatoInfoInferiorT01.setHeight("33px");
        iDatoInfoInferiorT01.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferiorT01.setValue(TblEstadisticasHuesped.getTotalPensionesCompletas());
        layout.addComponent(iDatoInfoInferiorT01, "left: 123px; top: 475px;"); 

        

        /* Dato 02 Titulo Maedias Pensiones    */
        Label iTDatoInfoInferior02 = new Label();
        iTDatoInfoInferior02.setWidth("220px");
        iTDatoInfoInferior02.setHeight("19px");
        iTDatoInfoInferior02.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfoInferior02.setValue("Medias Completas");
        layout.addComponent(iTDatoInfoInferior02, "left: 10px; top: 507px;");   
        
        /* MediasPensiones                     */
        Label iDatoInfoInferior02 = new Label();
        iDatoInfoInferior02.setWidth("104px");
        iDatoInfoInferior02.setHeight("33px");
        iDatoInfoInferior02.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferior02.setValue(TblEstadisticasHuesped.getMediasPensiones());
        layout.addComponent(iDatoInfoInferior02, "left: 10px; top: 525px;");         
        
        /* TotalMediasPensiones                */
        Label iDatoInfoInferiorT02 = new Label();
        iDatoInfoInferiorT02.setWidth("104px");
        iDatoInfoInferiorT02.setHeight("33px");
        iDatoInfoInferiorT02.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferiorT02.setValue(TblEstadisticasHuesped.getTotalMediasPensiones());
        layout.addComponent(iDatoInfoInferiorT02, "left: 123px; top: 525px;");        

        
        
        
        
        /* Dato 03 Titulo Pensiones Recuperadas    */
        Label iTDatoInfoInferior03 = new Label();
        iTDatoInfoInferior03.setWidth("220px");
        iTDatoInfoInferior03.setHeight("19px");
        iTDatoInfoInferior03.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfoInferior03.setValue("Penciones Recuperadas");
        layout.addComponent(iTDatoInfoInferior03, "left: 10px; top: 557px;");   
        
        /* PencionesRecuperadas                     */
        Label iDatoInfoInferior03 = new Label();
        iDatoInfoInferior03.setWidth("104px");
        iDatoInfoInferior03.setHeight("33px");
        iDatoInfoInferior03.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferior03.setValue(TblEstadisticasHuesped.getPencionesRecuperadas());
        layout.addComponent(iDatoInfoInferior03, "left: 10px; top: 575px;");         
        
        /* PencionesRecuperadas               */
        Label iDatoInfoInferiorT03 = new Label();
        iDatoInfoInferiorT03.setWidth("104px");
        iDatoInfoInferiorT03.setHeight("33px");
        iDatoInfoInferiorT03.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferiorT03.setValue(TblEstadisticasHuesped.getTotalPencionesRecuperadas());
        layout.addComponent(iDatoInfoInferiorT03, "left: 123px; top: 575px;");     
        
        
        
        
        /* Dato 04 Titulo Pensiones Pendientes    */
        Label iTDatoInfoInferior04 = new Label();
        iTDatoInfoInferior04.setWidth("220px");
        iTDatoInfoInferior04.setHeight("19px");
        iTDatoInfoInferior04.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfoInferior04.setValue("Penciones Pendientes");
        layout.addComponent(iTDatoInfoInferior04, "left: 10px; top: 607px;");   
        
        /* MediasPendientes                     */
        Label iDatoInfoInferior04 = new Label();
        iDatoInfoInferior04.setWidth("104px");
        iDatoInfoInferior04.setHeight("33px");
        iDatoInfoInferior04.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferior04.setValue(TblEstadisticasHuesped.getMediasPendientes());
        layout.addComponent(iDatoInfoInferior04, "left: 10px; top: 625px;");         
        
        /* MediasPendientes               */
        Label iDatoInfoInferiorT04 = new Label();
        iDatoInfoInferiorT04.setWidth("104px");
        iDatoInfoInferiorT04.setHeight("33px");
        iDatoInfoInferiorT04.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfoInferiorT04.setValue(TblEstadisticasHuesped.getTotalMediasPendientes());
        layout.addComponent(iDatoInfoInferiorT04, "left: 123px; top: 625px;"); 
        
        
        
        
        
        
        /* Datos Estadisticos Lado Derecho Inferior     */
        
        /* Dato 01 Titulo Servicio de Habitaciones   */
        Label dTDatoInfoInferior01 = new Label();
        dTDatoInfoInferior01.setWidth("220px");
        dTDatoInfoInferior01.setHeight("19px");
        dTDatoInfoInferior01.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfoInferior01.setValue("Servicio de Habitaciones");
        layout.addComponent(dTDatoInfoInferior01, "left: 797px; top: 458px;");   
        
        /* ServiciosHabiracion                     */
        Label dDatoInfoInferior01 = new Label();
        dDatoInfoInferior01.setWidth("104px");
        dDatoInfoInferior01.setHeight("33px");
        dDatoInfoInferior01.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferior01.setValue(TblEstadisticasHuesped.getServiciosHabiracion());
        layout.addComponent(dDatoInfoInferior01, "left: 797px; top: 475px;");         
        
        /* ServiciosHabiracion                */
        Label dDatoInfoInferiorT01 = new Label();
        dDatoInfoInferiorT01.setWidth("104px");
        dDatoInfoInferiorT01.setHeight("33px");
        dDatoInfoInferiorT01.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferiorT01.setValue(TblEstadisticasHuesped.getTotalServiciosHabiracion());
        layout.addComponent(dDatoInfoInferiorT01, "left: 911px; top: 475px;"); 

        

        /* Dato 02 Titulo Consumiciones Internas    */
        Label dTDatoInfoInferior02 = new Label();
        dTDatoInfoInferior02.setWidth("220px");
        dTDatoInfoInferior02.setHeight("19px");
        dTDatoInfoInferior02.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfoInferior02.setValue("Consumiciones Internas");
        layout.addComponent(dTDatoInfoInferior02, "left: 797px; top: 507px;");   
        
        /* MediasPensiones                     */
        Label dDatoInfoInferior02 = new Label();
        dDatoInfoInferior02.setWidth("104px");
        dDatoInfoInferior02.setHeight("33px");
        dDatoInfoInferior02.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferior02.setValue(TblEstadisticasHuesped.getConsumicionesInternas());
        layout.addComponent(dDatoInfoInferior02, "left: 797px; top: 525px;");         
        
        /* TotalMediasPensiones                */
        Label dDatoInfoInferiorT02 = new Label();
        dDatoInfoInferiorT02.setWidth("104px");
        dDatoInfoInferiorT02.setHeight("33px");
        dDatoInfoInferiorT02.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferiorT02.setValue(TblEstadisticasHuesped.getTotalConsumicionesInternas());
        layout.addComponent(dDatoInfoInferiorT02, "left: 911px; top: 525px;");        

        
        
        
        
        /* Dato 03 Titulo Devolucion de Consumiciones    */
        Label dTDatoInfoInferior03 = new Label();
        dTDatoInfoInferior03.setWidth("220px");
        dTDatoInfoInferior03.setHeight("19px");
        dTDatoInfoInferior03.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfoInferior03.setValue("Devolucion de Consumiciones");
        layout.addComponent(dTDatoInfoInferior03, "left: 797px; top: 557px;");   
        
        /* DevolucionConsumiciones                     */
        Label dDatoInfoInferior03 = new Label();
        dDatoInfoInferior03.setWidth("104px");
        dDatoInfoInferior03.setHeight("33px");
        dDatoInfoInferior03.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferior03.setValue(TblEstadisticasHuesped.getDevolucionConsumiciones());
        layout.addComponent(dDatoInfoInferior03, "left: 797px; top: 575px;");         
        
        /* DevolucionConsumiciones               */
        Label dDatoInfoInferiorT03 = new Label();
        dDatoInfoInferiorT03.setWidth("104px");
        dDatoInfoInferiorT03.setHeight("33px");
        dDatoInfoInferiorT03.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferiorT03.setValue(TblEstadisticasHuesped.getTotalConsumicionesInternas());
        layout.addComponent(dDatoInfoInferiorT03, "left: 911px; top: 575px;");     
        
        
        
        
        /* Dato 04 Titulo Fuera de Hora    */
        Label dTDatoInfoInferior04 = new Label();
        dTDatoInfoInferior04.setWidth("220px");
        dTDatoInfoInferior04.setHeight("19px");
        dTDatoInfoInferior04.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfoInferior04.setValue("Fuera de Hora");
        layout.addComponent(dTDatoInfoInferior04, "left: 797px; top: 607px;");   
        
        /* FueraHoras                     */
        Label dDatoInfoInferior04 = new Label();
        dDatoInfoInferior04.setWidth("104px");
        dDatoInfoInferior04.setHeight("33px");
        dDatoInfoInferior04.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferior04.setValue(TblEstadisticasHuesped.getTotalFueraHora());
        layout.addComponent(dDatoInfoInferior04, "left: 797px; top: 625px;");         
        
        /* TotalFueraHora               */
        Label dDatoInfoInferiorT04 = new Label();
        dDatoInfoInferiorT04.setWidth("104px");
        dDatoInfoInferiorT04.setHeight("33px");
        dDatoInfoInferiorT04.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfoInferiorT04.setValue(TblEstadisticasHuesped.getTotalFueraHora());
        layout.addComponent(dDatoInfoInferiorT04, "left: 911px; top: 625px;"); 
        
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
