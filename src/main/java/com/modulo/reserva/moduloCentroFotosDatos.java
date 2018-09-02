package com.modulo.reserva;

import java.sql.Date;

import org.vaadin.alump.scaleimage.ScaleImage;


import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosVisorImegenes;
import com.modulo.huesped.tblHuespedes;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;


public class moduloCentroFotosDatos {

    AbsoluteLayout layout;
    String EstiloCSS = "";
    tblHuespedes TblHuesped;
    tblEstadisticasHuesped TblEstadisticasHuesped = new tblEstadisticasHuesped();
    Date FechaActutal = com.modulo.componentes.utilDate.Fecha();
    ByosVisorImegenes ByosVisorImegenes01;
    VerticalLayout LayoutFoto = new VerticalLayout();
    
    public moduloCentroFotosDatos(AbsoluteLayout layout, String EstiloCSS, tblHuespedes TblHuesped) {
    	this.layout = layout;
    	this.EstiloCSS = EstiloCSS;
    	this.TblHuesped = TblHuesped;
        try {
			TblHuesped.buscarCodigo(TblHuesped.getNumeroDocumento());
			TblEstadisticasHuesped.setDataHuesped(TblHuesped);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        CentroFotosDatos();
        MostrarFoto();
    }

    private void MostrarFoto() {
        String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblHuespedes\\Fotos\\" + TblHuesped.getID() + "\\";
        String DirUrl="../dbsimagenes/tblHuespedes/Fotos/" + TblHuesped.getID() + "/";
       
        ScaleImage scaler;
        		
        ByosVisorImegenes01 = new ByosVisorImegenes(DirUrl, DirFile, "", TblHuesped.getNumeroDocumento());

        
        Embedded Foto = null;
        if (ByosVisorImegenes01.getImagenes() !=  null && ByosVisorImegenes01.getImagenes().size()>0) {
        	Foto = new Embedded( null, ByosVisorImegenes01.getImagenes().get(0) );
        	scaler= new ScaleImage(ByosVisorImegenes01.getImagenes().get(0));
        }else{
        	Foto = new Embedded( null, ByosImagenes.icon[126]);
        	scaler= new ScaleImage(ByosImagenes.icon[126]);
        }
        Foto.setSizeFull();
        scaler.setSizeFull();
        Foto.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                     	
            }
        });
        
        
        
        //scaler.setRecalculateOnSizeChangeEnabled(true); // Optional


        //LayoutFoto.addComponent(Foto);
        //LayoutFoto.setComponentAlignment(Foto, Alignment.TOP_CENTER);
        LayoutFoto.addComponent(scaler);
        LayoutFoto.setComponentAlignment(scaler, Alignment.TOP_CENTER);
        
    }
	
	
    /****************************************************************************************************************/
    /*                                                                                                              */
    /*                                           Fotos y Datos Centrales                                            */       
    /*                                                                                                              */
    /****************************************************************************************************************/

    
    void CentroFotosDatos() {
    	
        /* OK Izquierdo           */
        Label iOk01 = new Label();
        iOk01.setWidth("73px");
        iOk01.setHeight("111px");
        iOk01.setStyleName(EstiloCSS + "LabelOK");
        iOk01.setValue("OK");
        layout.addComponent(iOk01, "left: 287px; top: 139px;");    	
    	
    	/* Fecha                   */
        
        /* Titulo Dia              */
        Label iTDia01 = new Label();
        iTDia01.setWidth("73px");
        iTDia01.setHeight("19px");
        iTDia01.setStyleName(EstiloCSS + "LabelTituloFecha");
        iTDia01.setValue("D I A");
        layout.addComponent(iTDia01, "left: 287px; top: 250px;");  
        
        /* Dia                     */
        Label iDia01 = new Label();
        iDia01.setWidth("73px");
        iDia01.setHeight("33px");
        iDia01.setStyleName(EstiloCSS + "LabelFecha01");
        iDia01.setValue("" + com.modulo.componentes.utilDate.getDia(FechaActutal));
        layout.addComponent(iDia01, "left: 287px; top: 266px;"); 
        
        /* Titulo Mes              */
        Label iTMes01 = new Label();
        iTMes01.setWidth("73px");
        iTMes01.setHeight("19px");
        iTMes01.setStyleName(EstiloCSS + "LabelTituloFecha");
        iTMes01.setValue("M E S");
        layout.addComponent(iTMes01, "left: 287px; top: 299px;");  
        
        /* Mes                     */
        Label iMes01 = new Label();
        iMes01.setWidth("73px");
        iMes01.setHeight("33px");
        iMes01.setStyleName(EstiloCSS + "LabelFecha02");
        iMes01.setValue("" + com.modulo.componentes.utilDate.getMes(FechaActutal));
        layout.addComponent(iMes01, "left: 287px; top: 318px;"); 

        /* Titulo Anio              */
        Label iTAnio01 = new Label();
        iTAnio01.setWidth("73px");
        iTAnio01.setHeight("19px");
        iTAnio01.setStyleName(EstiloCSS + "LabelTituloFecha");
        iTAnio01.setValue("AÑO");
        layout.addComponent(iTAnio01, "left: 287px; top: 351px;");  
        
        /* Anio                     */
        Label iAnio01 = new Label();
        iAnio01.setWidth("73px");
        iAnio01.setHeight("33px");
        iAnio01.setStyleName(EstiloCSS + "LabelFecha03");
        iAnio01.setValue("" + com.modulo.componentes.utilDate.getAnio(FechaActutal));
        layout.addComponent(iAnio01, "left: 287px; top: 370px;"); 
        
               
        /* Columna de Datos  01     */

        /* Layout Datos             */
        
        VerticalLayout iLayoutDatos01 = new VerticalLayout();
        iLayoutDatos01.setWidth("70px");
        iLayoutDatos01.setHeight("264px");
        iLayoutDatos01.setStyleName(EstiloCSS + "LayoutDatos");
        layout.addComponent(iLayoutDatos01, "left: 360px; top: 139px;");  
        
        /* Titulo Dato 01           */
        Label iTDato01 = new Label();
        iTDato01.setWidth("60px");
        iTDato01.setHeight("18px");
        iTDato01.setStyleName(EstiloCSS + "LabelTituloDatos01");
        iTDato01.setValue("Sexo");
        layout.addComponent(iTDato01, "left: 365px; top: 146px;");  
        
        /* Dato 01                  */
        Label iDato01 = new Label();
        iDato01.setWidth("50px");
        iDato01.setHeight("22px");
        iDato01.setStyleName(EstiloCSS + "LabelDatos01");
        iDato01.setValue(TblEstadisticasHuesped.getSexo());
        layout.addComponent(iDato01, "left: 371px; top: 170px;"); 
        
        /* Titulo Dato 02          */
        Label iTDato02 = new Label();
        iTDato02.setWidth("60px");
        iTDato02.setHeight("18px");
        iTDato02.setStyleName(EstiloCSS + "LabelTituloDatos01");
        iTDato02.setValue("Docto.");
        layout.addComponent(iTDato02, "left: 365px; top: 200px;");  
        
        /* Dato 02                  */
        Label iDato02 = new Label();	
        iDato02.setWidth("50px");
        iDato02.setHeight("22px");
        iDato02.setStyleName(EstiloCSS + "LabelDatos01");
        iDato02.setValue(TblEstadisticasHuesped.getTipoDocumento());
        layout.addComponent(iDato02, "left: 371px; top: 224px;");        
        
        /* Titulo Dato 03          */
        Label iTDato03 = new Label();
        iTDato03.setHeight("18px");
        iTDato03.setWidth("60px");
        iTDato03.setStyleName(EstiloCSS + "LabelTituloDatos01");
        iTDato03.setValue("Valido");
        layout.addComponent(iTDato03, "left: 365px; top: 254px;");  
        
        /* Dato 03                  */
        Label iDato03 = new Label();
        iDato03.setWidth("50px");
        iDato03.setHeight("22px");
        iDato03.setStyleName(EstiloCSS + "LabelDatos01");
        iDato03.setValue(TblEstadisticasHuesped.getEstadoDocumento());
        layout.addComponent(iDato03, "left: 371px; top: 278px;");    
        
        /* Titulo Dato 04          */
        Label iTDato04 = new Label();
        iTDato04.setHeight("18px");
        iTDato04.setWidth("60px");
        iTDato04.setStyleName(EstiloCSS + "LabelTituloDatos01");
        iTDato04.setValue("Pasap");
        layout.addComponent(iTDato04, "left: 365px; top: 307px;");  
        
        /* Dato 04                  */
        Label iDato04 = new Label();
        iDato04.setWidth("50px");
        iDato04.setHeight("22px");
        iDato04.setStyleName(EstiloCSS + "LabelDatos01");
        iDato04.setValue(TblEstadisticasHuesped.getTienePasaporte());
        layout.addComponent(iDato04, "left: 371px; top: 329px;");  
        
        /* Titulo Dato 05          */
        Label iTDato05 = new Label();
        iTDato05.setHeight("18px");
        iTDato05.setWidth("60px");
        iTDato05.setStyleName(EstiloCSS + "LabelTituloDatos01");
        iTDato05.setValue("Valido");
        layout.addComponent(iTDato05, "left: 365px; top: 355px;");  
        
        /* Dato 05                  */
        Label iDato05 = new Label();
        iDato05.setWidth("50px");
        iDato05.setHeight("22px");
        iDato05.setStyleName(EstiloCSS + "LabelDatos01");
        iDato05.setValue(TblEstadisticasHuesped.getEstadoPasaporte());
        layout.addComponent(iDato05, "left: 371px; top: 376px;");         
        

        /* OK Derecha           */
        Label dOk01 = new Label();
        //dOk01.setIcon(ByosImagenes.icon[127]);
        dOk01.setWidth("73px");
        dOk01.setHeight("111px");
        dOk01.setStyleName(EstiloCSS + "LabelOK");
        dOk01.setValue("OK");
        layout.addComponent(dOk01, "left: 661px; top: 139px;");        

        /* Fecha                   */
        
        /* Titulo Hora              */
        Label dTDia01 = new Label();
        dTDia01.setWidth("73px");
        dTDia01.setHeight("19px");
        dTDia01.setStyleName(EstiloCSS + "LabelTituloFecha");
        dTDia01.setValue("HORA");
        layout.addComponent(dTDia01, "left: 661px; top: 250px;");  
        
        /* Hora                     */
        Label dDia01 = new Label();
        dDia01.setWidth("73px");
        dDia01.setHeight("33px");
        dDia01.setStyleName(EstiloCSS + "LabelFecha01");
        dDia01.setValue(com.modulo.componentes.utilDate.FormatoFecha(FechaActutal, "hh"));
        layout.addComponent(dDia01, "left: 661px; top: 266px;"); 
        
        /* Titulo Minuto              */
        Label dTMes01 = new Label();
        dTMes01.setWidth("73px");
        dTMes01.setHeight("19px");
        dTMes01.setStyleName(EstiloCSS + "LabelTituloFecha");
        dTMes01.setValue("MINUTO");
        layout.addComponent(dTMes01, "left: 661px; top: 299px;");  
        
        /* Minuto                     */
        Label dMes01 = new Label();
        dMes01.setWidth("73px");
        dMes01.setHeight("33px");
        dMes01.setStyleName(EstiloCSS + "LabelFecha02");
        dMes01.setValue(com.modulo.componentes.utilDate.FormatoFecha(FechaActutal, "MM"));
        layout.addComponent(dMes01, "left: 661px; top: 318px;"); 

        /* Titulo Segundo              */
        Label dTAnio01 = new Label();
        dTAnio01.setWidth("73px");
        dTAnio01.setHeight("19px");
        dTAnio01.setStyleName(EstiloCSS + "LabelTituloFecha");
        dTAnio01.setValue("SEGUNDO");
        layout.addComponent(dTAnio01, "left: 661px; top: 351px;");  
        
        /* Segundo                     */
        Label dAnio01 = new Label();
        dAnio01.setWidth("73px");
        dAnio01.setHeight("33px");
        dAnio01.setStyleName(EstiloCSS + "LabelFecha03");
        dAnio01.setValue(com.modulo.componentes.utilDate.FormatoFecha(FechaActutal, "ss"));
        layout.addComponent(dAnio01, "left: 661px; top: 370px;"); 
        
        
        /* Columna de Datos  02     */

        /* Layout Datos             */
        
        VerticalLayout dLayoutDatos01 = new VerticalLayout();
        dLayoutDatos01.setWidth("70px");
        dLayoutDatos01.setHeight("264px");
        dLayoutDatos01.setStyleName(EstiloCSS + "LayoutDatos");
        layout.addComponent(dLayoutDatos01, "left: 591px; top: 139px;");  
        
        /* Titulo Dato 01           */
        Label dTDato01 = new Label();
        dTDato01.setWidth("60px");
        dTDato01.setHeight("18px");
        dTDato01.setStyleName(EstiloCSS + "LabelTituloDatos01");
        dTDato01.setValue("Edad");
        layout.addComponent(dTDato01, "left: 596px; top: 146px;");  
        
        /* Dato 01                  */
        Label dDato01 = new Label();
        dDato01.setWidth("50px");
        dDato01.setHeight("22px");
        dDato01.setStyleName(EstiloCSS + "LabelDatos01");
        dDato01.setValue(TblEstadisticasHuesped.getEdad());
        layout.addComponent(dDato01, "left: 602px; top: 170px;"); 
        
        /* Titulo Dato 02          */
        Label dTDato02 = new Label();
        dTDato02.setWidth("60px");
        dTDato02.setHeight("18px");
        dTDato02.setStyleName(EstiloCSS + "LabelTituloDatos01");
        dTDato02.setValue("Color");
        layout.addComponent(dTDato02, "left: 596px; top: 200px;");  
        
        /* Dato 02                  */
        Label dDato02 = new Label();
        dDato02.setWidth("50px");
        dDato02.setHeight("22px");
        dDato02.setStyleName(EstiloCSS + "LabelDatos01");
        dDato02.setValue(TblEstadisticasHuesped.getColor());
        layout.addComponent(dDato02, "left: 602px; top: 224px;");        
        
        /* Titulo Dato 03          */
        Label dTDato03 = new Label();
        dTDato03.setHeight("18px");
        dTDato03.setWidth("60px");
        dTDato03.setStyleName(EstiloCSS + "LabelTituloDatos01");
        dTDato03.setValue("€ x Dia");
        layout.addComponent(dTDato03, "left: 596px; top: 254px;");  
        
        /* Dato 03                  */
        Label dDato03 = new Label();
        dDato03.setWidth("50px");
        dDato03.setHeight("22px");
        dDato03.setStyleName(EstiloCSS + "LabelDatos01");
        dDato03.setValue(TblEstadisticasHuesped.getFacturadoXDia().trim());
        layout.addComponent(dDato03, "left: 602px; top: 278px;");    
        
        /* Titulo Dato 04          */
        Label dTDato04 = new Label();
        dTDato04.setHeight("18px");
        dTDato04.setWidth("60px");
        dTDato04.setStyleName(EstiloCSS + "LabelTituloDatos01");
        dTDato04.setValue("Promo");
        layout.addComponent(dTDato04, "left: 596px; top: 307px;");  
        
        /* Dato 04                  */
        Label dDato04 = new Label();
        dDato04.setWidth("50px");
        dDato04.setHeight("22px");
        dDato04.setStyleName(EstiloCSS + "LabelDatos01");
        dDato04.setValue(TblEstadisticasHuesped.getPromociones().trim());
        layout.addComponent(dDato04, "left: 602px; top: 329px;");  
        
        /* Titulo Dato 05          */
        Label dTDato05 = new Label();
        dTDato05.setHeight("18px");
        dTDato05.setWidth("60px");
        dTDato05.setStyleName(EstiloCSS + "LabelTituloDatos01");
        dTDato05.setValue("Credito");
        layout.addComponent(dTDato05, "left: 596px; top: 355px;");  
        
        /* Dato 05                  */
        Label dDato05 = new Label();
        dDato05.setWidth("50px");
        dDato05.setHeight("22px");
        dDato05.setStyleName(EstiloCSS + "LabelDatos01");
        dDato05.setValue(TblEstadisticasHuesped.getCredito().trim());
        layout.addComponent(dDato05, "left: 602px; top: 376px;");         
        
        /* Layout Para Foto        */
        
        LayoutFoto.setWidth("161px");
        LayoutFoto.setHeight("164px");
        LayoutFoto.setStyleName(EstiloCSS + "LayoutFoto");
        layout.addComponent(LayoutFoto, "left: 430px; top: 139px;");         
              
        /* Nivel                   */
        Label Nivel = new Label();
        Nivel.setWidth("161px");
        Nivel.setHeight("55px");
        Nivel.setStyleName(EstiloCSS + "LabelNivel01");
        Nivel.setValue(TblEstadisticasHuesped.getNivel());
        layout.addComponent(Nivel, "left: 430px; top: 348px;");        
        
        /* Boton Para Abrir Albun de Fotos   */
        Button VerAlbun = new Button();
        VerAlbun.setWidth("161px");
        VerAlbun.setHeight("49px");
        VerAlbun.setIcon(ByosImagenes.icon[134]);
        VerAlbun.setStyleName(EstiloCSS + "BotonComunicacion");
        layout.addComponent(VerAlbun, "left: 430px; top: 300px;"); 
        
        VerAlbun.addClickListener(new Button.ClickListener() {
	          public void buttonClick(ClickEvent event) {
	              try {
	            	  ByosVisorImegenes01.procesoZoomImagen();
	              } catch (Exception e) {
	                  // Ingnored, we'll let the Form handle the errors
	              }
	          }
	      });



        /* Telefono y Comunicaciones         */
        
        /* Telefono                          */
        Label lblTelefono = new Label();
        lblTelefono.setWidth("301px");
        lblTelefono.setHeight("47px");
        lblTelefono.setStyleName(EstiloCSS + "LabelTelefono");
        lblTelefono.setValue(TblHuesped.getTelefonoMovil());
        layout.addComponent(lblTelefono, "left: 360px; top: 403px;");
  
    }

        
}
