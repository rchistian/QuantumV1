package com.modulo.reserva;

import java.io.Serializable;
import java.sql.Date;
import com.modulo.componentes.ByosCalculo;
import com.modulo.componentes.ByosEnviarSMS;
import com.modulo.componentes.utilDate;
import com.modulo.huesped.tblHuespedes;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.VerticalLayout;

public class moduloReserva  extends VerticalLayout  implements Serializable{
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
    
    public moduloReserva(String xNumeroDocuemto, moduloReserva01 WindowsRaiz, String EstiloCSS) {
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
        reindeerImage = new Embedded( null, new ThemeResource("imagen/ReservaA.jpg"));
        reindeerImage.setWidth( "100%" ); 
        reindeerImage.setHeight( "100%" );
        reindeerImage.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                     	
            }
        });
        //layout.addComponent(reindeerImage);
        
        inicializarModulos();
        new moduloDatosEstadisticos(layout, EstiloCSS, TblHuesped);
        DatosInferiores();
        HabitacionesLibres();
    }

    PopupDateField dDatoCentralF101 = new PopupDateField();    
    PopupDateField iDatoCentralF101 = new PopupDateField();
    PopupDateField iDatoCentralF401 = new PopupDateField();
    PopupDateField dDatoCentralF401 = new PopupDateField();
    Accordion Habitaciones01 = new Accordion();
    Accordion Habitaciones02 = new Accordion();
    Label cDatoCentralF101 = new Label();
    
    Label iDatoCentralF301 = new Label();
    Label cDatoCentralF301 = new Label();
    Label dDatoCentralF301 = new Label();

    void CalculoTotales(){
    	if (utilDate.compararFechas(iDatoCentralF101.getValue(), dDatoCentralF101.getValue())>=0) {
    		cDatoCentralF101.setValue(utilDate.calculoDias(iDatoCentralF101.getValue(), dDatoCentralF101.getValue()) + " Dias");
    		iDatoCentralF401.setValue(iDatoCentralF101.getValue());
    		dDatoCentralF401.setValue(utilDate.SumaRestarFecha(iDatoCentralF101.getValue(),-2,"DIAS"));
            Habitaciones01.setVisible(true);
            Habitaciones02.setVisible(true);
    	
    	}else{
            Habitaciones01.setVisible(false);
            Habitaciones02.setVisible(false);
    		Notification.show("La Fecha Final No Puede Ser Mayor a La Inicial", Notification.TYPE_TRAY_NOTIFICATION);
    	}    	 
    }
    
    void HabitacionesLibres() {
    	
    	Habitaciones01.setWidth("275px");
    	Habitaciones01.setHeight("300px");
 
    	Habitaciones02.setWidth("275px");
    	Habitaciones02.setHeight("300px");
    	
        for (int i = 1; i < 8; i++) {
        	
        	if(i<2) {
               final Label label01 = new Label("Habitacion Simple, Una Cama Sencilla, Baño, Nevera, Aparcamiento");
               label01.setWidth("100%");
               final VerticalLayout layout01 = new VerticalLayout(label01);
               layout01.setDescription("H A10" + i);
               layout01.setMargin(true);
               Habitaciones01.addTab(layout01, "H A10" + i);

        	
               final Label label02 = new Label("Habitacion Simple, Una Cama Sencilla, Baño, Nevera, Aparcamiento");
               label02.setWidth("100%");
               final VerticalLayout layout02 = new VerticalLayout(label02);
               layout02.setDescription("H B10" + i);
               layout02.setMargin(true);
               Habitaciones02.addTab(layout02, "H B10" + i);        	
        	
        	
        	}
        	
        	if(i>=2 && i<4) {
                final Label label01 = new Label("Habitacion Doble, Dos Cama Sencillas, Baño, Nevera, Aparcamiento");
                label01.setWidth("100%");
                final VerticalLayout layout01 = new VerticalLayout(label01);
                layout01.setDescription("H A10" + i);
                layout01.setMargin(true);
                Habitaciones01.addTab(layout01, "H A10" + i);

                final Label label02 = new Label("Habitacion Precidencial, Una Cama Sencilla, Baño, Nevera, Aparcamiento");
                label02.setWidth("100%");
                final VerticalLayout layout02 = new VerticalLayout(label02);
                layout02.setDescription("H B10" + i);
                layout02.setMargin(true);
                Habitaciones02.addTab(layout02, "H B10" + i);        	
        	}
        	
        	if(i>=4) {
                final Label label01 = new Label("Habitacion Matrimonial, Una Cama Matrimonial, Baño, Nevera, Aparcamiento");
                label01.setWidth("100%");
                final VerticalLayout layout01 = new VerticalLayout(label01);
                layout01.setDescription("H A10" + i);
                layout01.setMargin(true);
                Habitaciones01.addTab(layout01, "H A10" + i);

                final Label label02 = new Label("Habitacion VIP, Una Cama Sencilla, Baño, Nevera, Aparcamiento");
                label02.setWidth("100%");
                final VerticalLayout layout02 = new VerticalLayout(label02);
                layout02.setDescription("H B10" + i);
                layout02.setMargin(true);
                Habitaciones02.addTab(layout02, "H B10" + i);   
                

        	}
        }
        Habitaciones01.setVisible(false);
        Habitaciones02.setVisible(false);
        
        Habitaciones01.addSelectedTabChangeListener(new SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
			  // TODO Auto-generated method stub
              //System.out.println("You are watching " + ((VerticalLayout) event.getTabSheet().getSelectedTab()).getDescription());
			  VerticalLayout xLayout = ((VerticalLayout) event.getTabSheet().getSelectedTab());
			  Label Descripcion = (Label)xLayout.getComponent(0);
			  dDatoCentralF301.setValue(xLayout.getDescription());
			  cDatoCentralF301.setValue(Descripcion.getValue().split(",")[0]);
              iDatoCentralF301.setValue(xLayout.getDescription());
			}
			
        });
        
        Habitaciones02.addSelectedTabChangeListener(new SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
			  // TODO Auto-generated method stub
				  VerticalLayout xLayout = ((VerticalLayout) event.getTabSheet().getSelectedTab());
				  Label Descripcion = (Label)xLayout.getComponent(0);
				  dDatoCentralF301.setValue(xLayout.getDescription());
				  cDatoCentralF301.setValue(Descripcion.getValue().split(",")[0]);
	              iDatoCentralF301.setValue(xLayout.getDescription());
			}
			
        });
        
        
        
        layout.addComponent(Habitaciones01, "left: 5px; top: 145px;"); 
        layout.addComponent(Habitaciones02, "left: 742px; top: 145px;"); 
    }
    
    
    void DatosInferiores() {
    	
    	/*********************************************** Primera Fila **********************************************************/
    	Label iTituloDatoCentalF101 = new Label();
    	iTituloDatoCentalF101.setWidth("203px");
    	iTituloDatoCentalF101.setHeight("40px");
    	iTituloDatoCentalF101.setValue("Dia de Llegada");
    	iTituloDatoCentalF101.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(iTituloDatoCentalF101, "left: 234px; top: 451px;");    	
    	

        iDatoCentralF101.setValue(utilDate.Fecha());
        iDatoCentralF101.setDateFormat("dd/MM/yyyy");
        iDatoCentralF101.setWidth("203px");
        iDatoCentralF101.setHeight("40px");
        iDatoCentralF101.setStyleName(EstiloCSS + "DatoCentral01");
        layout.addComponent(iDatoCentralF101, "left: 234px; top: 472px;"); 
        iDatoCentralF101.addValueChangeListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				CalculoTotales();
			}
            
        });
        
        
    	Label cTituloDatoCentalF102 = new Label();
    	cTituloDatoCentalF102.setWidth("100px");
    	cTituloDatoCentalF102.setHeight("40px");
    	cTituloDatoCentalF102.setValue("Dias");
    	cTituloDatoCentalF102.setStyleName(EstiloCSS + "TituloDatoCental02");
        layout.addComponent(cTituloDatoCentalF102, "left: 461px; top: 451px;");          

        
        cDatoCentralF101.setWidth("100px");
        cDatoCentralF101.setHeight("40px");
        cDatoCentralF101.setStyleName(EstiloCSS + "DatoCentral02");
        layout.addComponent(cDatoCentralF101, "left: 461px; top: 472px;");  
        
        
    	Label dTituloDatoCentalF103 = new Label();
    	dTituloDatoCentalF103.setWidth("203px");
    	dTituloDatoCentalF103.setHeight("40px");
    	dTituloDatoCentalF103.setValue("Dia de Salida");
    	dTituloDatoCentalF103.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(dTituloDatoCentalF103, "left: 585px; top: 451px;");         
        
        dDatoCentralF101.setWidth("203px");
        dDatoCentralF101.setHeight("40px");
        dDatoCentralF101.setDateFormat("dd/MM/yyyy");
        dDatoCentralF101.setValue(utilDate.Fecha());
        dDatoCentralF101.setStyleName(EstiloCSS + "DatoCentral01");
        layout.addComponent(dDatoCentralF101, "left: 585px; top: 472px;"); 
        dDatoCentralF101.addValueChangeListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				CalculoTotales();
			}
            
        });

        /*********************************************** Segunda Fila **********************************************************/       
    	Label iTituloDatoCentalF201 = new Label();
    	iTituloDatoCentalF201.setWidth("203px");
    	iTituloDatoCentalF201.setHeight("40px");
    	iTituloDatoCentalF201.setValue("O C U P A N T E   1");
    	iTituloDatoCentalF201.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(iTituloDatoCentalF201, "left: 234px; top: 522px;");    	
    	
        Label iDatoCentralF201 = new Label();
        iDatoCentralF201.setWidth("203px");
        iDatoCentralF201.setHeight("40px");
        iDatoCentralF201.setValue(TblHuesped.getApodo());
        iDatoCentralF201.setStyleName(EstiloCSS + "DatoCentral03");
        layout.addComponent(iDatoCentralF201, "left: 234px; top: 543px;"); 
        
        
    	Label cTituloDatoCentalF202 = new Label();
    	cTituloDatoCentalF202.setWidth("100px");
    	cTituloDatoCentalF202.setHeight("40px");
    	cTituloDatoCentalF202.setValue("Nº De Personas");
    	cTituloDatoCentalF202.setStyleName(EstiloCSS + "TituloDatoCental02");
        layout.addComponent(cTituloDatoCentalF202, "left: 461px; top: 522px;");          

        Label cDatoCentralF201 = new Label();
        cDatoCentralF201.setWidth("100px");
        cDatoCentralF201.setHeight("40px");
        cDatoCentralF201.setValue("1");
        cDatoCentralF201.setStyleName(EstiloCSS + "DatoCentral02");
        layout.addComponent(cDatoCentralF201, "left: 461px; top: 543px;");  
        
        
    	Label dTituloDatoCentalF203 = new Label();
    	dTituloDatoCentalF203.setWidth("203px");
    	dTituloDatoCentalF203.setHeight("40px");
    	dTituloDatoCentalF203.setValue("O C U P A N T E   2");
    	dTituloDatoCentalF203.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(dTituloDatoCentalF203, "left: 585px; top: 522px;");         
        
        Label dDatoCentralF201 = new Label();
        dDatoCentralF201.setWidth("203px");
        dDatoCentralF201.setHeight("40px");
        dDatoCentralF201.setValue(TblHuesped.getApodo());
        dDatoCentralF201.setStyleName(EstiloCSS + "DatoCentral03");
        layout.addComponent(dDatoCentralF201, "left: 585px; top: 543px;"); 
        
        
        /*********************************************** Trecera Fila **********************************************************/       
    	Label iTituloDatoCentalF301 = new Label();
    	iTituloDatoCentalF301.setWidth("100px");
    	iTituloDatoCentalF301.setHeight("40px");
    	//iTituloDatoCentalF301.setValue("O C U P A N T E   1");
    	iTituloDatoCentalF301.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(iTituloDatoCentalF301, "left: 234px; top: 593px;");    	
    	
        
        iDatoCentralF301.setWidth("100px");
        iDatoCentralF301.setHeight("40px");
        iDatoCentralF301.setStyleName(EstiloCSS + "DatoCentral02");
        layout.addComponent(iDatoCentralF301, "left: 234px; top: 614px;"); 
        
        
    	Label cTituloDatoCentalF302 = new Label();
    	cTituloDatoCentalF302.setWidth("306px");
    	cTituloDatoCentalF302.setHeight("40px");
    	//cTituloDatoCentalF302.setValue("Nº De Personas");
    	cTituloDatoCentalF302.setStyleName(EstiloCSS + "TituloDatoCental02");
        layout.addComponent(cTituloDatoCentalF302, "left: 339px; top: 593px;");          

        
        cDatoCentralF301.setWidth("346px");
        cDatoCentralF301.setHeight("40px");
        cDatoCentralF301.setStyleName(EstiloCSS + "DatoCentral03");
        layout.addComponent(cDatoCentralF301, "left: 339px; top: 614px;");  
        
        
    	Label dTituloDatoCentalF303 = new Label();
    	dTituloDatoCentalF303.setWidth("60px");
    	dTituloDatoCentalF303.setHeight("40px");
    	//dTituloDatoCentalF303.setValue("O C U P A N T E   2");
    	dTituloDatoCentalF303.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(dTituloDatoCentalF303, "left: 690px; top: 593px;");         
        
        
        dDatoCentralF301.setWidth("100px");
        dDatoCentralF301.setHeight("40px");
        dDatoCentralF301.setStyleName(EstiloCSS + "DatoCentral02");
        layout.addComponent(dDatoCentralF301, "left: 690px; top: 614px;");

    
    	/*********************************************** Cuarta Fila **********************************************************/
    	Label iTituloDatoCentalF401 = new Label();
    	iTituloDatoCentalF401.setWidth("203px");
    	iTituloDatoCentalF401.setHeight("40px");
    	iTituloDatoCentalF401.setValue("Dia De Llegada");
    	iTituloDatoCentalF401.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(iTituloDatoCentalF401, "left: 234px; top: 664px;");    	
    	
        
        iDatoCentralF401.setWidth("203px");
        iDatoCentralF401.setHeight("40px");
        iDatoCentralF401.setDateFormat("dd/MM/yyyy");
        iDatoCentralF401.setValue(utilDate.Fecha());
        iDatoCentralF401.setStyleName(EstiloCSS + "DatoCentral01");
        layout.addComponent(iDatoCentralF401, "left: 234px; top: 685px;"); 
        
        
    	Label cTituloDatoCentalF402 = new Label();
    	cTituloDatoCentalF402.setWidth("100px");
    	cTituloDatoCentalF402.setHeight("40px");
    	cTituloDatoCentalF402.setValue("SMS");
    	cTituloDatoCentalF402.setStyleName(EstiloCSS + "TituloDatoCental02");
        layout.addComponent(cTituloDatoCentalF402, "left: 461px; top: 664px;");          

        Button cDatoCentralF401 = new Button("Enviar");
        cDatoCentralF401.setWidth("100px");
        cDatoCentralF401.setHeight("40px");
        cDatoCentralF401.setStyleName(EstiloCSS + "DatoCentral02");
        layout.addComponent(cDatoCentralF401, "left: 461px; top: 685px;");  
        cDatoCentralF401.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
               if(!iDatoCentralF301.getValue().equals("")) {
                  try{

            	      ByosEnviarSMS.EnvioSMS(TblHuesped.getTelefonoMovil(), "Su peticion de Reserva a sido tramitada con Exito su Nro de ID: " + ByosCalculo.NumeroAleatorio(9999, 1000) + " Desde " + utilDate.FormatoFecha(iDatoCentralF101.getValue(), "dd-MM-yyyy") + " Hasta " + utilDate.FormatoFecha(dDatoCentralF101.getValue(), "dd-MM-yyyy") + " Habitacion Nro: " + iDatoCentralF301.getValue() + " gracias por interesarse por Nuestro Hotel.");
                  }catch (Exception e) {
 				      // TODO Auto-generated catch block
 				      e.printStackTrace();
                  }
               }else{
            	   Notification.show("No se puede enviar el mensaje hasta que complete la reserva",Notification.TYPE_TRAY_NOTIFICATION);
               }
            }
         });
        
    	Label dTituloDatoCentalF403 = new Label();
    	dTituloDatoCentalF403.setWidth("203px");
    	dTituloDatoCentalF403.setHeight("40px");
    	dTituloDatoCentalF403.setValue("Dia Para Confirmar");
    	dTituloDatoCentalF403.setStyleName(EstiloCSS + "TituloDatoCental01");
        layout.addComponent(dTituloDatoCentalF403, "left: 585px; top: 664px;");         
         
        
        dDatoCentralF401.setWidth("203px");
        dDatoCentralF401.setHeight("40px");
        dDatoCentralF401.setDateFormat("dd/MM/yyyy");
        dDatoCentralF401.setValue(utilDate.Fecha());
        dDatoCentralF401.setStyleName(EstiloCSS + "DatoCentral01");
        layout.addComponent(dDatoCentralF401, "left: 585px; top: 685px;"); 
    
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
