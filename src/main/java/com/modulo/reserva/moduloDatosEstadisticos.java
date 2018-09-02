package com.modulo.reserva;

import java.sql.Date;

import com.modulo.componentes.ByosVisorImegenes;
import com.modulo.huesped.tblHuespedes;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class moduloDatosEstadisticos {

    AbsoluteLayout layout;
    String EstiloCSS = "";
    tblHuespedes TblHuesped;
    tblEstadisticasHuesped TblEstadisticasHuesped = new tblEstadisticasHuesped();
    Date FechaActutal = com.modulo.componentes.utilDate.Fecha();
    ByosVisorImegenes ByosVisorImegenes01;
    VerticalLayout LayoutFoto = new VerticalLayout();
    
    public moduloDatosEstadisticos(AbsoluteLayout layout, String EstiloCSS, tblHuespedes TblHuesped) {
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
        DatosEstadisticos();
    }

    /****************************************************************************************************************/
    /*                                                                                                              */
    /*                                     Datos Estadisticos y Botones Inferiores                                  */       
    /*                                                                                                              */
    /****************************************************************************************************************/

    
    void DatosEstadisticos() {    
 
        /* Datos Estadisticos Lado Izquierdo     */
        
        /* Dato 01 Titulo Primere Estancia en el Hotel   */
        Label iTDatoInfo01 = new Label();
        iTDatoInfo01.setWidth("215px");
        iTDatoInfo01.setHeight("19px");
        iTDatoInfo01.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfo01.setValue("Primere Estancia en el Hotel");
        layout.addComponent(iTDatoInfo01, "left: 0px; top: 140px;");  
        
        /* PrimaraEstancia                       */
        Label iDatoInfo01 = new Label();
        iDatoInfo01.setWidth("215px");
        iDatoInfo01.setHeight("33px");
        iDatoInfo01.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfo01.setValue(TblEstadisticasHuesped.getPrimaraEstancia());
        layout.addComponent(iDatoInfo01, "left: 0px; top: 158px;");         
        
        /* Boton Dato 01            */
        Button iBtoDatoInfo01 = new Button();
        iBtoDatoInfo01.setWidth("35px");
        iBtoDatoInfo01.setHeight("33px");
        iBtoDatoInfo01.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(iBtoDatoInfo01, "left: 234px; top: 158px;");

        
        
        
        /* Dato 02 Titulo Ultima Estancia en el Hotel   */
        Label iTDatoInfo02 = new Label();
        iTDatoInfo02.setWidth("215px");
        iTDatoInfo02.setHeight("19px");
        iTDatoInfo02.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfo02.setValue("Ultima Estancia en el Hotel");
        layout.addComponent(iTDatoInfo02, "left: 0px; top: 190px;");  
        
        /* UltimaEstancia                       */
        Label iDatoInfo02 = new Label();
        iDatoInfo02.setWidth("215px");
        iDatoInfo02.setHeight("33px");
        iDatoInfo02.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfo02.setValue(TblEstadisticasHuesped.getUltimaEstancia());
        layout.addComponent(iDatoInfo02, "left: 0px; top: 208px;");         
        
        /* Boton Dato 02            */
        Button iBtoDatoInfo02 = new Button();
        iBtoDatoInfo02.setWidth("35px");
        iBtoDatoInfo02.setHeight("33px");
        iBtoDatoInfo02.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(iBtoDatoInfo02, "left: 234px; top: 208px;");        
        

        
        
        /* Dato 03 Titulo Tiempo Desde La Ultima Vez   */
        Label iTDatoInfo03 = new Label();
        iTDatoInfo03.setWidth("215px");
        iTDatoInfo03.setHeight("19px");
        iTDatoInfo03.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfo03.setValue("Ultima Estancia en el Hotel");
        layout.addComponent(iTDatoInfo03, "left: 0px; top: 240px;");  
        
        /* PrimaraEstancia                       */
        Label iDatoInfo03 = new Label();
        iDatoInfo03.setWidth("215px");
        iDatoInfo03.setHeight("33px");
        iDatoInfo03.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfo03.setValue(TblEstadisticasHuesped.getTiempoUltimaEstancia());
        layout.addComponent(iDatoInfo03, "left: 0px; top: 258px;");         
        
        /* Boton Dato             */
        Button iBtoDatoInfo03 = new Button();
        iBtoDatoInfo03.setWidth("35px");
        iBtoDatoInfo03.setHeight("33px");
        iBtoDatoInfo03.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(iBtoDatoInfo03, "left: 234px; top: 258px;");        

        
        
        
        /* Dato 04 Titulo Cuntas Veces Hospedado   */
        Label iTDatoInfo04 = new Label();
        iTDatoInfo04.setWidth("215px");
        iTDatoInfo04.setHeight("19px");
        iTDatoInfo04.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfo04.setValue("Cuntas Veces Hospedado");
        layout.addComponent(iTDatoInfo04, "left: 0px; top: 290px;");  
        
        /* PrimaraEstancia                       */
        Label iDatoInfo04 = new Label();
        iDatoInfo04.setWidth("215px");
        iDatoInfo04.setHeight("33px");
        iDatoInfo04.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfo04.setValue(TblEstadisticasHuesped.getCuantosHospedajes());
        layout.addComponent(iDatoInfo04, "left: 0px; top: 308px;");         
        
        /* Boton Dato             */
        Button iBtoDatoInfo04 = new Button();
        iBtoDatoInfo04.setWidth("35px");
        iBtoDatoInfo04.setHeight("33px");
        iBtoDatoInfo04.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(iBtoDatoInfo04, "left: 234px; top: 308px;");              
        
        
        
        
        /* Dato 05 Titulo Media de dia x Estancia   */
        Label iTDatoInfo05 = new Label();
        iTDatoInfo05.setWidth("215px");
        iTDatoInfo05.setHeight("19px");
        iTDatoInfo05.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfo05.setValue("Cuntas Veces Hospedado");
        layout.addComponent(iTDatoInfo05, "left: 0px; top: 340px;");  
        
        /* MediaDiaEstancia                       */
        Label iDatoInfo05 = new Label();
        iDatoInfo05.setWidth("215px");
        iDatoInfo05.setHeight("33px");
        iDatoInfo05.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfo05.setValue(TblEstadisticasHuesped.getMediaDiaEstancia());
        layout.addComponent(iDatoInfo05, "left: 0px; top: 358px;");         
        
        /* Boton Dato             */
        Button iBtoDatoInfo05 = new Button();
        iBtoDatoInfo05.setWidth("35px");
        iBtoDatoInfo05.setHeight("33px");
        iBtoDatoInfo05.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(iBtoDatoInfo05, "left: 234px; top: 358px;");  
        
        
        

        /* Dato 06 Titulo Reservas u Otras Anladas   */
        Label iTDatoInfo06 = new Label();
        iTDatoInfo06.setWidth("215px");
        iTDatoInfo06.setHeight("19px");
        iTDatoInfo06.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        iTDatoInfo06.setValue("Reservas u Otras Anladas");
        layout.addComponent(iTDatoInfo06, "left: 0px; top: 390px;");  
        
        /* ReservasAnuladas                       */
        Label iDatoInfo06 = new Label();
        iDatoInfo06.setWidth("215px");
        iDatoInfo06.setHeight("33px");
        iDatoInfo06.setStyleName(EstiloCSS + "LabelDatoInfo");
        iDatoInfo06.setValue(TblEstadisticasHuesped.getReservasAnuladas());
        layout.addComponent(iDatoInfo06, "left: 0px; top: 408px;");         
        
        /* Boton Dato             */
        Button iBtoDatoInfo06 = new Button();
        iBtoDatoInfo06.setWidth("35px");
        iBtoDatoInfo06.setHeight("33px");
        iBtoDatoInfo06.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(iBtoDatoInfo06, "left: 234px; top: 408px;");      
    

    
    
    
        
        
        /* Datos Estadisticos Lado Derecha     */
        
        /* Dato 01 Titulo Fecha de compeaños   */
        Label dTDatoInfo01 = new Label();
        dTDatoInfo01.setWidth("215px");
        dTDatoInfo01.setHeight("19px");
        dTDatoInfo01.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfo01.setValue("Fecha de Su Cumpleaños");
        layout.addComponent(dTDatoInfo01, "left: 808px; top: 140px;");  
        
        /* PrimaraEstancia                       */
        Label dDatoInfo01 = new Label();
        dDatoInfo01.setWidth("215px");
        dDatoInfo01.setHeight("33px");
        dDatoInfo01.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfo01.setValue(TblEstadisticasHuesped.getFechaCumpleanos());
        layout.addComponent(dDatoInfo01, "left: 808px; top: 158px;");         
        
        /* Boton Dato 01            */
        Button dBtoDatoInfo01 = new Button();
        dBtoDatoInfo01.setWidth("35px");
        dBtoDatoInfo01.setHeight("33px");
        dBtoDatoInfo01.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(dBtoDatoInfo01, "left: 753px; top: 158px;");

        
        
        
        /* Dato 02 Titulo Pais de Origen   */
        Label dTDatoInfo02 = new Label();
        dTDatoInfo02.setWidth("215px");
        dTDatoInfo02.setHeight("19px");
        dTDatoInfo02.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfo02.setValue("Pais de Origen");
        layout.addComponent(dTDatoInfo02, "left: 808px; top: 190px;");  
        
        /* UltimaEstancia                       */
        Label dDatoInfo02 = new Label();
        dDatoInfo02.setWidth("215px");
        dDatoInfo02.setHeight("33px");
        dDatoInfo02.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfo02.setValue(TblEstadisticasHuesped.getPaisOrigen());
        layout.addComponent(dDatoInfo02, "left: 808px; top: 208px;");         
        
        /* Boton Dato 02            */
        Button dBtoDatoInfo02 = new Button();
        dBtoDatoInfo02.setWidth("35px");
        dBtoDatoInfo02.setHeight("33px");
        dBtoDatoInfo02.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(dBtoDatoInfo02, "left: 753px; top: 208px;");        
        

        
        
        /* Dato 03 Titulo Ciudad de Residencia   */
        Label dTDatoInfo03 = new Label();
        dTDatoInfo03.setWidth("215px");
        dTDatoInfo03.setHeight("19px");
        dTDatoInfo03.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfo03.setValue("Ciudad de Residencia");
        layout.addComponent(dTDatoInfo03, "left: 808px; top: 240px;");  
        
        /* PrimaraEstancia                       */
        Label dDatoInfo03 = new Label();
        dDatoInfo03.setWidth("215px");
        dDatoInfo03.setHeight("33px");
        dDatoInfo03.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfo03.setValue(TblEstadisticasHuesped.getCiudadRecidencia());
        layout.addComponent(dDatoInfo03, "left: 808px; top: 258px;");         
        
        /* Boton Dato             */
        Button dBtoDatoInfo03 = new Button();
        dBtoDatoInfo03.setWidth("35px");
        dBtoDatoInfo03.setHeight("33px");
        dBtoDatoInfo03.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(dBtoDatoInfo03, "left: 753px; top: 258px;");        

        
        
        
        /* Dato 04 Titulo Acompañado por   */
        Label dTDatoInfo04 = new Label();
        dTDatoInfo04.setWidth("215px");
        dTDatoInfo04.setHeight("19px");
        dTDatoInfo04.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfo04.setValue("Acompañado Por ?");
        layout.addComponent(dTDatoInfo04, "left: 808px; top: 290px;");  
        
        /* PrimaraEstancia                       */
        Label dDatoInfo04 = new Label();
        dDatoInfo04.setWidth("215px");
        dDatoInfo04.setHeight("33px");
        dDatoInfo04.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfo04.setValue(TblEstadisticasHuesped.getAcompanante());
        layout.addComponent(dDatoInfo04, "left: 808px; top: 308px;");         
        
        /* Boton Dato             */
        Button dBtoDatoInfo04 = new Button();
        dBtoDatoInfo04.setWidth("35px");
        dBtoDatoInfo04.setHeight("33px");
        dBtoDatoInfo04.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(dBtoDatoInfo04, "left: 753px; top: 308px;");              
        
        
        
        
        /* Dato 05 Titulo Tenemos su Whatsapp   */
        Label dTDatoInfo05 = new Label();
        dTDatoInfo05.setWidth("215px");
        dTDatoInfo05.setHeight("19px");
        dTDatoInfo05.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfo05.setValue("Tenemos Su Whatsapp");
        layout.addComponent(dTDatoInfo05, "left: 808px; top: 340px;");  
        
        /* MediaDiaEstancia                       */
        Label dDatoInfo05 = new Label();
        dDatoInfo05.setWidth("215px");
        dDatoInfo05.setHeight("33px");
        dDatoInfo05.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfo05.setValue(TblEstadisticasHuesped.getWhatsapp());
        layout.addComponent(dDatoInfo05, "left: 808px; top: 358px;");         
        
        /* Boton Dato             */
        Button dBtoDatoInfo05 = new Button();
        dBtoDatoInfo05.setWidth("35px");
        dBtoDatoInfo05.setHeight("33px");
        dBtoDatoInfo05.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(dBtoDatoInfo05, "left: 753px; top: 358px;");  
        
        
        

        /* Dato 06 Titulo Tenemos su Gmail   */
        Label dTDatoInfo06 = new Label();
        dTDatoInfo06.setWidth("215px");
        dTDatoInfo06.setHeight("19px");
        dTDatoInfo06.setStyleName(EstiloCSS + "LabelTituloDatoInfo");
        dTDatoInfo06.setValue("Tenemos Su Gmail");
        layout.addComponent(dTDatoInfo06, "left: 808px; top: 390px;");  
        
        /* ReservasAnuladas                       */
        Label dDatoInfo06 = new Label();
        dDatoInfo06.setWidth("215px");
        dDatoInfo06.setHeight("33px");
        dDatoInfo06.setStyleName(EstiloCSS + "LabelDatoInfo");
        dDatoInfo06.setValue(TblEstadisticasHuesped.getGmail());
        layout.addComponent(dDatoInfo06, "left: 808px; top: 408px;");         
        
        /* Boton Dato             */
        Button dBtoDatoInfo06 = new Button();
        dBtoDatoInfo06.setWidth("35px");
        dBtoDatoInfo06.setHeight("33px");
        dBtoDatoInfo06.setStyleName(EstiloCSS + "BotonDatoInfo");
        layout.addComponent(dBtoDatoInfo06, "left: 753px; top: 408px;");      
        
        
    }

}
