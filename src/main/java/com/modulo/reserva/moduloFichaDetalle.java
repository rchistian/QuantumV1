package com.modulo.reserva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.Timer;
import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosVisorImegenes;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.annotations.Push;
import com.vaadin.data.Property;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.shared.ui.label.ContentMode;

@Push
public class moduloFichaDetalle  extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    
    /* Iniciar Cabezera */
    int indiceAyuda = 0;
    private String TextoAyuda[] = new String[6];
    
    private HorizontalLayout LayoutCabezera = new HorizontalLayout();
    private HorizontalLayout LayoutCabezeraContenedor = new HorizontalLayout();
    private HorizontalLayout LayoutCabezeraTitulo = new HorizontalLayout();
    private Label lblCabezeraTitulo = new Label();
    
    private VerticalLayout LayoutTitulo = new VerticalLayout();
    
    private VerticalLayout LayoutMenuTop01 = new VerticalLayout();
    private HorizontalLayout LayoutGrupoBoton01 = new HorizontalLayout();
    private HorizontalLayout LayoutGrupoBoton02 = new HorizontalLayout();
    
    private VerticalLayout LayoutMenuTop02 = new VerticalLayout();
    private HorizontalLayout LayoutGrupoBoton03 = new HorizontalLayout();
    private HorizontalLayout LayoutGrupoBoton04 = new HorizontalLayout();
    
    private ByosBoton GrupoBotones01[] = new ByosBoton[3];
    private ByosBoton GrupoBotones02[] = new ByosBoton[4];
    
    private ByosBoton GrupoBotones03[] = new ByosBoton[3];
    private ByosBoton GrupoBotones04[] = new ByosBoton[4]; 
    
    
    /* Iniciar Titulo */
    
    HorizontalLayout LayoutCabezeraDetalle = new HorizontalLayout();
    HorizontalLayout LayoutCabezeraContenedorDetalle = new HorizontalLayout();
    HorizontalLayout LayoutTituloDetalle = new HorizontalLayout();
        
    VerticalLayout LayoutMenuTopDetalle01 = new VerticalLayout();     
    VerticalLayout LayoutMenuTopDetalle02 = new VerticalLayout();
    Label lblTituloDatos01 = new Label();
    Label lblTituloFoto01 = new Label();
    Label lblTituloDatos02 = new Label();

    /* Iniciar Final Datos */
    
    HorizontalLayout LayoutCabezeraFinalDatos = new HorizontalLayout();
    HorizontalLayout LayoutCabezeraContenedorFinalDatos = new HorizontalLayout();
    VerticalLayout LayoutTituloFinalDatos01 = new VerticalLayout();
    VerticalLayout LayoutTituloFinalDatos02 = new VerticalLayout();
    VerticalLayout LayoutTituloFinalDatos03 = new VerticalLayout();
    HorizontalLayout LayoutDatoFinal01 = new HorizontalLayout();
    HorizontalLayout LayoutDatoFinal02 = new HorizontalLayout();
    HorizontalLayout LayoutDatoFinal03 = new HorizontalLayout();
    HorizontalLayout LayoutDatoFinal04 = new HorizontalLayout();
        
    VerticalLayout LayoutMenuTopFinalDatos01 = new VerticalLayout();        
    VerticalLayout LayoutMenuTopFinalDatos02 = new VerticalLayout();
    VerticalLayout LayoutMenuTopFinalDatos03 = new VerticalLayout();

    //Layout Datos Final 01
    DateField Fecha01 = new DateField();
    DateField Fecha02 = new DateField();
    TextField Dias01 = new TextField();
    
    //Layout Datos Final 02
    TextField TextoOcupante01 = new TextField();
    TextField TextoOcupante02 = new TextField();
    Button BtoHabitacion01 = new Button();
    Button BtoHabitacion02 = new Button();
    Label LblHabitacion = new Label();
    
    //Layout Datos Final 03
    DateField Fecha03 = new DateField();
    DateField Fecha04 = new DateField();
    TextField Dias02 = new TextField();
    
    /* Iniciar Datos */
    
    private HorizontalLayout LayoutCuerpo = new HorizontalLayout();
    private VerticalLayout layoutLeft01 = new VerticalLayout();
    private VerticalLayout layoutCenter01 = new VerticalLayout();
    private VerticalLayout layoutRight01 = new VerticalLayout();
    
    ByosForm ByosFormularioTab01_01 = new ByosForm();
    ByosForm ByosFormularioTab01_02 = new ByosForm();
    ByosForm ByosFormularioTab01_03 = new ByosForm();
    
    private HorizontalLayout layout01 = new HorizontalLayout(); 
    
    //public tblEstadisticasHuesped TblEstadisticasHuesped;
    
    /* Inicio Variables Tab 01 Ficha */

    /* Datos Izquierda */
    String DatosVisiblesTab01_01[] = {
        "PrimaraEstancia",
        "UltimaEstancia",
        "TiempoUltimaEstancia",
        "CuntosHospedajes",
        "MediaDiaEstancia",
        "RecervasAnuladas"
    };

    String DatosTitulosTab01_01[] = {
        "Primara Estancia en el Hotel",
        "Ultima  Estancia en el Hotel",
        "Tiempo Desde la Ultima Vez",
        "Cuntas Veces Hospedado",
        "Media de Dia x Estancia",
        "Reservas u otras Anuladas"
    };

    /* Datos Centro */
    String DatosVisiblesTab01_02[] = {
       "Nivel"
    };

    String DatosTitulosTab01_02[] = {
       "Nivel"
    };
    
    /* Datos Derecha */
    String DatosVisiblesTab01_03[] = {
       "FechaCompleanos",
       "PaisOrigen",
       "CiudadRecidencia",
       "Acompanante",
       "Whatsapp",
       "Gmail"    
    };

    String DatosTitulosTab01_03[] = {
        "Fecha de su Compleanos",
    	"Pais de Origen",
    	"Ciudad de Recidencia",
    	"Acompañante",
    	"Tenemos su Whatsapp",
    	"Tenemos su GMail"        
    };  
    /* Fin Variables Tab 01 Ficha */
    
    /* Inicio Variables Tab 02 Datos Personales */

    
    private tblEstadisticasHuesped TblEstadisticasHuesped;
    
    public ByosVisorImegenes ByosVisorImegenes01;
    
    
    
    public Label Ok01 = new Label();
    public Label Ok02 = new Label();
    
    public VerticalLayout LayoutContenedorDatos01 = new VerticalLayout();
    public VerticalLayout LayoutContenedorDatos02 = new VerticalLayout();
    
    public VerticalLayout LayoutTituloGrupoDatos01 = new VerticalLayout();
    public VerticalLayout LayoutTituloGrupoDatos02 = new VerticalLayout();
    public VerticalLayout LayoutTituloGrupoFoto = new VerticalLayout();
    
    public HorizontalLayout GrupoDatos01 = new HorizontalLayout();
    public HorizontalLayout GrupoDatos02 = new HorizontalLayout();
    public VerticalLayout GrupoFoto = new VerticalLayout();
    public HorizontalLayout LayoutDetalle = new HorizontalLayout();
    
    public VerticalLayout LayoutDetalleGlobal = new VerticalLayout();
    
    
    /* Reserva */
    public HorizontalLayout LayoutReserva01 = new HorizontalLayout();
    public Button btoLlamadaReserva01 = new Button();
    public TextField TextoTelefono = new TextField();
    public Button btoProtocolo = new Button(); 
    public Button btoLlamadaReserva02 = new Button();
    
    
    public HorizontalLayout LayoutReserva02 = new HorizontalLayout();
    public HorizontalLayout LayoutReserva03 = new HorizontalLayout();
    public HorizontalLayout LayoutReserva04 = new HorizontalLayout();
    public HorizontalLayout LayoutReserva05 = new HorizontalLayout();
    
    
    
    public VerticalLayout Datos01 = new VerticalLayout();
    public VerticalLayout Datos02 = new VerticalLayout();
    public VerticalLayout Datos03 = new VerticalLayout();
    public VerticalLayout Datos04 = new VerticalLayout();
    
    public Label TitulosDatos01[] = new Label[5];
    public TextField CamposDatos01[] = new TextField[5];
    
    public Label TitulosDatos02[] = new Label[6];
    public TextField CamposDatos02[] = new TextField[6];   
    
    public Label TitulosDatos03[] = new Label[6];
    public TextField CamposDatos03[] = new TextField[6];
    
    public Label TitulosDatos04[] = new Label[5];
    public TextField CamposDatos04[] = new TextField[5];
    
    
    
   
    @SuppressWarnings("serial")
    public class ClickLabel extends VerticalLayout {
    	
    	public ClickLabel(String value) {

    		Label label = new Label (value, ContentMode.HTML);
    		addComponent(label);	
    	}
    }

    public void IniciarCabezera() {    	
    	LayoutCabezera.setWidth("100%");
    	LayoutCabezera.setSpacing(false);
    	LayoutCabezeraContenedor.setSpacing(false);
    	LayoutCabezeraTitulo.setSpacing(false);
    	
    	//lblCabezeraTitulo.setWidth("1024px");
    	//lblCabezeraTitulo.setHeight("100%");
    	lblCabezeraTitulo.setStyleName("LabelTituloAyuda");	
    	lblCabezeraTitulo.setValue("");
    	LayoutCabezeraTitulo.setWidth("100%");
    	LayoutCabezeraTitulo.setHeight("69px");        	
    	LayoutCabezeraTitulo.addComponent(lblCabezeraTitulo);
    	LayoutCabezeraTitulo.setComponentAlignment(lblCabezeraTitulo, Alignment.MIDDLE_CENTER);
 	
/*
    	LayoutCabezeraTitulo.setStyleName("LayoutTituloDatos01"); 
    	LayoutCabezeraTitulo.addLayoutClickListener(new LayoutClickListener() {			
            @Override
            public void layoutClick(LayoutClickEvent event) {
            	LayoutCabezeraTitulo.setVisible(false);
            	LayoutCabezeraContenedor.setVisible(true);
            	
            	Notification.show("Clik",Notification.TYPE_TRAY_NOTIFICATION);	
             }
        });
        
                      
*/  	


    	
    	
    	
    	LayoutMenuTop01.setSpacing(false);
        LayoutMenuTop01.setMargin(false);

        LayoutMenuTop02.setSpacing(false);
        LayoutMenuTop02.setMargin(false);
        
        LayoutGrupoBoton01.setSpacing(true);
        LayoutGrupoBoton01.setMargin(false);
        LayoutGrupoBoton02.setSpacing(true);
        LayoutGrupoBoton02.setMargin(false);

        LayoutGrupoBoton03.setSpacing(true);
        LayoutGrupoBoton03.setMargin(false);
        LayoutGrupoBoton04.setSpacing(true);
        LayoutGrupoBoton04.setMargin(false);        
        
        for(int f=0;f<GrupoBotones01.length;f++) {
        	GrupoBotones01[f] = new ByosBoton(LayoutGrupoBoton01,0,"","");
        	GrupoBotones01[f].setIcon(null);
        	GrupoBotones01[f].setAncho("38px");
        	GrupoBotones01[f].setAlto("104px");
        	
        	GrupoBotones01[f].setStyleName("BotonCabezeraNegro01");
        	//LayoutGrupoBoton01.addComponent(GrupoBotones01[f]);
        }
        for(int f=0;f<GrupoBotones02.length;f++) {
        	GrupoBotones02[f] = new ByosBoton(LayoutGrupoBoton02,0,"","");
        	GrupoBotones02[f].setIcon(null);
        	GrupoBotones02[f].setAncho("30px");
        	GrupoBotones02[f].setAlto("75px");        	
        	GrupoBotones02[f].setStyleName("BotonCabezeraGris01");
        	//LayoutGrupoBoton02.addComponent(GrupoBotones02[f]);
        }
        
        
        
        for(int f=0;f<GrupoBotones03.length;f++) {
        	GrupoBotones03[f] = new ByosBoton(LayoutGrupoBoton03,0,"","");
        	GrupoBotones03[f].setIcon(null);
        	GrupoBotones03[f].setAncho("38px");
        	GrupoBotones03[f].setAlto("104px");
        	
        	GrupoBotones03[f].setStyleName("BotonCabezeraNegro01");
        	//LayoutGrupoBoton03.addComponent(GrupoBotones03[f]);
        	
        }
        for(int f=0;f<GrupoBotones04.length;f++) {
        	GrupoBotones04[f] = new ByosBoton(LayoutGrupoBoton04,0,"","");
        	GrupoBotones04[f].setIcon(null);
        	GrupoBotones04[f].setAncho("30px");
        	GrupoBotones04[f].setAlto("75px");
        	
        	GrupoBotones04[f].setStyleName("BotonCabezeraGris02");
        	//LayoutGrupoBoton02.addComponent(GrupoBotones02[f]);
        }        

        
        
        LayoutMenuTop01.addComponent(LayoutGrupoBoton01);
        LayoutMenuTop01.addComponent(LayoutGrupoBoton02);

        LayoutMenuTop02.addComponent(LayoutGrupoBoton03);
        LayoutMenuTop02.addComponent(LayoutGrupoBoton04);
        
        LayoutTitulo.setWidth("335px");
        LayoutTitulo.setHeight("100%");
        LayoutTitulo.setStyleName("LayoutTituloDetalle01");

        LayoutCabezera.setStyleName("GrupoCabezera01");
        
        
        LayoutCabezeraContenedor.addComponent(LayoutMenuTop01); 
        LayoutCabezeraContenedor.addComponent(LayoutTitulo); 
        LayoutCabezeraContenedor.addComponent(LayoutMenuTop02); 
        
        
        LayoutCabezeraContenedor.setVisible(false);
        
        LayoutCabezera.addComponent(LayoutCabezeraContenedor);
        LayoutCabezera.addComponent(LayoutCabezeraTitulo);
        LayoutCabezera.setComponentAlignment(LayoutCabezeraContenedor, Alignment.TOP_CENTER);
        
    }

    
    public void IniciarTitulosDatos() {
        Button btoPlay = new Button("PLAY");
        Button btoStop = new Button("STOP");
    	
        
        
        btoPlay.setWidth("120px");
        btoPlay.setHeight("100%");
        btoPlay.setStyleName("BotonNegroLetrasAmarillas");
        
        btoStop.setWidth("120px");
        btoStop.setHeight("100%");
        btoStop.setStyleName("BotonNegroLetrasAmarillas");    
        
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
        
        btoStop.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
                try{
                	ActivarAyuda();
     			}catch (Exception e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
             }  
           });
        
    	LayoutCabezeraDetalle.setWidth("100%");
    	LayoutCabezeraDetalle.setSpacing(false);
    	LayoutCabezeraContenedorDetalle.setSpacing(false);
  	
        LayoutMenuTopDetalle01.setSpacing(false);
        LayoutMenuTopDetalle01.setMargin(false);
        LayoutMenuTopDetalle01.setWidth("251px");
        LayoutMenuTopDetalle01.setHeight("40px");
               
        lblTituloDatos01.setStyleName("LayoutTituloDatos01");
        lblTituloDatos01.setWidth("251px");
        lblTituloDatos01.setHeight("30px");
        lblTituloDatos01.setValue("INFORMACION DE SUS ESTANCIAS");
        LayoutMenuTopDetalle01.addComponent(lblTituloDatos01);        
        LayoutMenuTopDetalle01.setComponentAlignment(lblTituloDatos01, Alignment.MIDDLE_CENTER);

        lblTituloFoto01.setValue(TblEstadisticasHuesped.getApodo());
        lblTituloFoto01.setStyleName("LabelTextoNegroLetra22");
        
        LayoutTituloDetalle.addComponent(btoPlay);
        LayoutTituloDetalle.addComponent(lblTituloFoto01);
        LayoutTituloDetalle.addComponent(btoStop);
        
        LayoutTituloDetalle.setComponentAlignment(btoPlay, Alignment.MIDDLE_LEFT);
        LayoutTituloDetalle.setComponentAlignment(lblTituloFoto01, Alignment.MIDDLE_CENTER);
        LayoutTituloDetalle.setComponentAlignment(btoStop, Alignment.MIDDLE_RIGHT);
        
        LayoutTituloDetalle.setWidth("503px");
        LayoutTituloDetalle.setHeight("40px");       
        LayoutTituloDetalle.setStyleName("GrupoCabezera02");

        LayoutMenuTopDetalle02.setSpacing(false);
        LayoutMenuTopDetalle02.setMargin(false); 
        LayoutMenuTopDetalle02.setWidth("251px");
        LayoutMenuTopDetalle02.setHeight("40px"); 

        lblTituloDatos02.setStyleName("LayoutTituloDatos01");
        lblTituloDatos02.setWidth("251px");
        lblTituloDatos02.setHeight("30px");
        lblTituloDatos02.setValue("INFORMACION DE SUS ESTANCIAS");
        LayoutMenuTopDetalle02.addComponent(lblTituloDatos02);
        LayoutMenuTopDetalle02.setComponentAlignment(lblTituloDatos02, Alignment.MIDDLE_CENTER);
        
        layoutLeft01.addComponent(LayoutMenuTopDetalle01);
        LayoutDetalleGlobal.addComponent(LayoutTituloDetalle); 
        layoutRight01.addComponent(LayoutMenuTopDetalle02);
        setTextoAyuda();
        proximaAyuda();       
    }
    
    public void IniciarDatos() {
    	
    	ByosFormularioTab01_01.setStyleName("DatosReserva01");
    	ByosFormularioTab01_03.setStyleName("DatosReserva01");
    	
    	ByosFormularioTab01_01.setSpacing(false);
    	ByosFormularioTab01_02.setSpacing(false);
    	ByosFormularioTab01_03.setSpacing(false);
    	
    	ByosFormularioTab01_01.setMargin(false);
    	ByosFormularioTab01_02.setMargin(false);
    	ByosFormularioTab01_03.setMargin(false);

    	ByosFormularioTab01_01.setWidth("100%");
    	ByosFormularioTab01_02.setWidth("100%");
    	ByosFormularioTab01_03.setWidth("100%");
    	
        ByosFormularioTab01_01.setTipoLayout("Vertical");
        ByosFormularioTab01_02.setTipoLayout("Vertical");
        ByosFormularioTab01_03.setTipoLayout("Vertical");
        
        ByosFormularioTab01_01.setMostrarEstado(false);
        ByosFormularioTab01_02.setMostrarEstado(false);
        ByosFormularioTab01_03.setMostrarEstado(false);
        
        ByosFormularioTab01_01.setLabelTexto(false);
        ByosFormularioTab01_02.setLabelTexto(true);
        ByosFormularioTab01_03.setLabelTexto(false);
        
        ByosFormularioTab01_01.setAlinearBoton("DERECHA");
        ByosFormularioTab01_03.setAlinearBoton("IZQUIERDA");
        
        ByosFormularioTab01_01.setClase(TblEstadisticasHuesped, DatosVisiblesTab01_01, DatosTitulosTab01_01, null);
        ByosFormularioTab01_02.setClase(TblEstadisticasHuesped, DatosVisiblesTab01_02, DatosTitulosTab01_02, null);
        ByosFormularioTab01_03.setClase(TblEstadisticasHuesped, DatosVisiblesTab01_03, DatosTitulosTab01_03, null);
        
        layoutLeft01.addComponent(ByosFormularioTab01_01);
        layoutCenter01.addComponent(ByosFormularioTab01_02); 
        layoutRight01.addComponent(ByosFormularioTab01_03);
        
        for(int f=0;f<ByosFormularioTab01_01.Campos.length;f++) {
            ByosFormularioTab01_01.Campos[f].setAncho("200px");
            ByosFormularioTab01_01.Campos[f].btoBoton1.setBoton(3, "Listar","");
            ByosFormularioTab01_01.Campos[f].btoBoton1.setVisible(true);
            ByosFormularioTab01_01.Campos[f].btoBoton1.setStyleName("BotonDataDetalle01");
            ByosFormularioTab01_01.Campos[f].btoBoton1.setWidth("20px");
            ByosFormularioTab01_01.Campos[f].btoBoton1.setIcon(null);
            ByosFormularioTab01_01.Campos[f].setSpacing(true);
        }
        for(int f=0;f<ByosFormularioTab01_02.Campos.length;f++) {
            ByosFormularioTab01_02.Campos[f].setAncho("182px");
            ByosFormularioTab01_02.Campos[f].setLargo("50px");
        }
        for(int f=0;f<ByosFormularioTab01_03.Campos.length;f++) {
            ByosFormularioTab01_03.Campos[f].setAncho("200px");
            ByosFormularioTab01_03.Campos[f].btoBoton1.setBoton(3, "Listar","");
            ByosFormularioTab01_03.Campos[f].btoBoton1.setVisible(true);
            ByosFormularioTab01_03.Campos[f].btoBoton1.setStyleName("BotonDataDetalle01");          
            ByosFormularioTab01_03.Campos[f].btoBoton1.setWidth("20px");
            ByosFormularioTab01_03.Campos[f].btoBoton1.setIcon(null);
            ByosFormularioTab01_03.Campos[f].setSpacing(true);
        }        
    }
    
    public void IniciarForm() {
    	Datos01.setWidth("80px");
    	Datos02.setWidth("80px");
    	Datos03.setWidth("80px");
    	Datos04.setWidth("80px");
    	
        LayoutTituloGrupoFoto.setWidth("100%");
        LayoutTituloGrupoFoto.setHeight("50px");
        LayoutTituloGrupoFoto.setStyleName("TituloFoto01");
        
        
    	GrupoDatos01.setMargin(false);
    	GrupoDatos01.setSpacing(false);
    	GrupoDatos02.setMargin(false);
    	GrupoDatos02.setSpacing(false);
    	GrupoFoto.setMargin(false);
    	GrupoFoto.setSpacing(false);
    	
    	GrupoDatos01.addComponent(Datos01);
    	GrupoDatos01.addComponent(Datos02);
    	
    	GrupoDatos02.addComponent(Datos03);
    	GrupoDatos02.addComponent(Datos04);

    	Datos01.setMargin(false);
    	Datos02.setMargin(false);
    	Datos03.setMargin(false);
    	Datos04.setMargin(false);
    	
    	Datos01.setSpacing(false);
    	Datos02.setSpacing(true);
    	Datos03.setSpacing(true);
    	Datos04.setSpacing(false);
    	
		Datos01.setStyleName("DataDetalle01");
		Datos02.setStyleName("DataDetalle02");
		Datos03.setStyleName("DataDetalle03");
		Datos04.setStyleName("DataDetalle01");
        
        Ok01.setWidth("80px");
        Ok02.setWidth("80px");
        Ok01.setHeight("153px");
        Ok02.setHeight("153px");
        
        Ok01.setValue("OK");
        Ok02.setValue("OK");
        
        Ok01.setStyleName("LabelDetelleOK");
        Ok02.setStyleName("LabelDetelleOK");

        Datos01.addComponent(Ok01);
        Datos04.addComponent(Ok02);
        
    	for(int f=1;f<TitulosDatos01.length;f++) {
    		
    		TitulosDatos01[f] = new Label();
    		CamposDatos01[f] = new TextField();
    		
    		TitulosDatos04[f] = new Label();
    		CamposDatos04[f] = new TextField();
    		
    		TitulosDatos01[f].setValue("OK");
    		TitulosDatos04[f].setValue("OK");
    		    		
    		TitulosDatos01[f].setWidth("80px");
    		CamposDatos01[f].setWidth("80px");

    		TitulosDatos04[f].setWidth("80px");
    		CamposDatos04[f].setWidth("80px");
    		
    		TitulosDatos01[f].setStyleName("LabelDetalle01");
    		TitulosDatos04[f].setStyleName("LabelDetalle01");
    		
    		CamposDatos01[f].setStyleName("TextDetalle01");
    		CamposDatos04[f].setStyleName("TextDetalle01");
    		    		
    		Datos01.addComponent(TitulosDatos01[f]);
    		Datos01.addComponent(CamposDatos01[f]);
    		
    		Datos04.addComponent(TitulosDatos04[f]);
    		Datos04.addComponent(CamposDatos04[f]);    		
    	}
        
        
    	for(int f=1;f<TitulosDatos02.length;f++) {

    		TitulosDatos02[f] = new Label();
    		CamposDatos02[f] = new TextField();
    		
    		TitulosDatos03[f] = new Label();
    		CamposDatos03[f] = new TextField();
    		
    		TitulosDatos02[f].setValue("OK");    		
    		TitulosDatos03[f].setValue("OK");  		
    		    		
    		TitulosDatos02[f].setWidth("70px");
    		CamposDatos02[f].setWidth("70px");
    		
    		TitulosDatos03[f].setWidth("70px");
    		CamposDatos03[f].setWidth("70px");
    		
    		TitulosDatos02[f].setStyleName("LabelDetalle02");
    		TitulosDatos03[f].setStyleName("LabelDetalle02");
    		
    		CamposDatos02[f].setStyleName("TextDetalle02");
    		CamposDatos03[f].setStyleName("TextDetalle02");
    		    		    		
    		Datos02.addComponent(TitulosDatos02[f]);
    		Datos02.addComponent(CamposDatos02[f]);
    		Datos02.setComponentAlignment(TitulosDatos02[f], Alignment.TOP_CENTER);
    		Datos02.setComponentAlignment(CamposDatos02[f], Alignment.TOP_CENTER);
    		
    		Datos03.addComponent(TitulosDatos03[f]);
    		Datos03.addComponent(CamposDatos03[f]);
    		Datos03.setComponentAlignment(TitulosDatos03[f], Alignment.TOP_CENTER);
    		Datos03.setComponentAlignment(CamposDatos03[f], Alignment.TOP_CENTER);
    		
    	}	
    	
    }
    
    public void IniciarFinalDatos(){

        Button OpcionesFinal01[] = new Button[3];
        Button OpcionesFinal02[] = new Button[3];
        Button OpcionesFinal03[] = new Button[3];
        Button OpcionesFinal04[] = new Button[3];
    	
    	
    	LayoutCabezeraFinalDatos.setWidth("100%");
    	LayoutCabezeraFinalDatos.setSpacing(false);
    	LayoutCabezeraContenedorFinalDatos.setSpacing(false);
    	  	
        LayoutMenuTopFinalDatos01.setSpacing(false);
        LayoutMenuTopFinalDatos01.setMargin(false);
        LayoutMenuTopFinalDatos01.setWidth("260px");
        LayoutMenuTopFinalDatos01.setHeight("100%");
        
        LayoutMenuTopFinalDatos02.setSpacing(false);
        LayoutMenuTopFinalDatos02.setMargin(false); 
        LayoutMenuTopFinalDatos02.setWidth("200px");
        LayoutMenuTopFinalDatos02.setHeight("50px"); 
        

        for(int f=0;f<OpcionesFinal01.length;f++) {
            OpcionesFinal01[f] = new Button();           
            OpcionesFinal02[f] = new Button();
            OpcionesFinal03[f] = new Button();
            OpcionesFinal04[f] = new Button();
            
            OpcionesFinal01[f].setHeight("40px");
            OpcionesFinal02[f].setHeight("40px");
            OpcionesFinal03[f].setHeight("40px");
            OpcionesFinal04[f].setHeight("40px");
            
            if(f==1) {
            	OpcionesFinal01[f].setWidth("219px");
            	OpcionesFinal02[f].setWidth("219px");
            	OpcionesFinal03[f].setWidth("219px");
            	OpcionesFinal04[f].setWidth("219px");
            	OpcionesFinal01[f].setStyleName("DegradacionAmarilloNotaBottomTop");
            	OpcionesFinal02[f].setStyleName("DegradacionAmarilloNotaBottomTop");
            	OpcionesFinal03[f].setStyleName("DegradacionAmarilloNotaBottomTop");
            	OpcionesFinal04[f].setStyleName("DegradacionAmarilloNotaBottomTop");
            }else {
            	OpcionesFinal01[f].setWidth("140px");
            	OpcionesFinal02[f].setWidth("140px");
            	OpcionesFinal03[f].setWidth("140px");
            	OpcionesFinal04[f].setWidth("140px");
            	OpcionesFinal01[f].setStyleName("DegradacionVerdeTopBottom");
            	OpcionesFinal02[f].setStyleName("DegradacionVerdeTopBottom");
            	OpcionesFinal03[f].setStyleName("DegradacionVerdeTopBottom");
            	OpcionesFinal04[f].setStyleName("DegradacionVerdeTopBottom");
            }
            
            LayoutDatoFinal01.addComponent(OpcionesFinal01[f]);
            LayoutDatoFinal02.addComponent(OpcionesFinal02[f]);
            LayoutDatoFinal03.addComponent(OpcionesFinal03[f]);
            LayoutDatoFinal04.addComponent(OpcionesFinal04[f]);
        }
        
        LayoutDatoFinal01.setSpacing(true);
        LayoutDatoFinal02.setSpacing(true);
        LayoutDatoFinal03.setSpacing(true);
        LayoutDatoFinal04.setSpacing(true);
        LayoutTituloFinalDatos01.setSpacing(true);
        
        LayoutDatoFinal01.setStyleName("LayoutEspaciadoMinimo");
        LayoutDatoFinal02.setStyleName("LayoutEspaciadoMinimo");
        LayoutDatoFinal03.setStyleName("LayoutEspaciadoMinimo");
        LayoutDatoFinal04.setStyleName("LayoutEspaciadoMinimo");
        LayoutTituloFinalDatos01.setStyleName("LayoutEspaciadoMinimo");
        
        
        LayoutTituloFinalDatos01.addComponent(LayoutDatoFinal01);
        LayoutTituloFinalDatos01.addComponent(LayoutDatoFinal02);
        LayoutTituloFinalDatos01.addComponent(LayoutDatoFinal03);
        LayoutTituloFinalDatos01.addComponent(LayoutDatoFinal04);
        
        LayoutCabezeraContenedorFinalDatos.addComponent(LayoutTituloFinalDatos01);
       	
    }
    
    
    public moduloFichaDetalle(final tblEstadisticasHuesped TblEstadisticasHuesped, boolean MostrarMesnu, String ViwerHeight){ 
    	this.TblEstadisticasHuesped = TblEstadisticasHuesped;
    	IniciarCabezera();
    	IniciarTitulosDatos();
    	IniciarDatos();
    	IniciarForm();
    	IniciarFinalDatos();

    	CamposDatos04[2].addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
	    		//CamposDatos04[3].setSizeFull();
	    		System.out.println(com.modulo.componentes.utilDate.FormatoFecha(com.modulo.componentes.utilDate.FechaHora(),"dd/MM/yyyy HH:mm:ss"));
            	
            }
        });    
    	
    	TitulosDatos04[2].setImmediate(true);
    	CamposDatos04[2].setImmediate(true);
    	lblCabezeraTitulo.setImmediate(true);
    	/*
    	Timer timer = new Timer (1000, new ActionListener (){
			public void actionPerformed(ActionEvent e) { 
				//lblCabezeraTitulo.setVisible(! lblCabezeraTitulo.isVisible());	

	    		TitulosDatos04[2].setValue("Hora");
	    		CamposDatos04[2].setValue(com.modulo.componentes.utilDate.FormatoFecha(com.modulo.componentes.utilDate.FechaHora(),"HH"));

	    		TitulosDatos04[3].setValue("Min");
	    		CamposDatos04[3].setValue(com.modulo.componentes.utilDate.FormatoFecha(com.modulo.componentes.utilDate.FechaHora(),"mm"));

	    		TitulosDatos04[4].setValue("Seg");
	    		CamposDatos04[4].setValue(com.modulo.componentes.utilDate.FormatoFecha(com.modulo.componentes.utilDate.FechaHora(),"ss"));
                
	    		
			}
    	}); 
    	timer.start();
    	  */
    	
    	/*
    	
    
    	UI.getCurrent().access(new Runnable() {
    	    @Override
    	    public void run() {
	    		TitulosDatos04[2].setValue("Hora");
	    		CamposDatos04[2].setValue(com.modulo.componentes.utilDate.FormatoFecha(com.modulo.componentes.utilDate.FechaHora(),"HH"));

	    		TitulosDatos04[3].setValue("Min");
	    		CamposDatos04[3].setValue(com.modulo.componentes.utilDate.FormatoFecha(com.modulo.componentes.utilDate.FechaHora(),"mm"));

	    		TitulosDatos04[4].setValue("Seg");
	    		CamposDatos04[4].setValue(com.modulo.componentes.utilDate.FormatoFecha(com.modulo.componentes.utilDate.FechaHora(),"ss"));
	    		UI.getCurrent().push();
    	    }
    	});
    	
    	*/
    	
    	setSpacing(false);
    	setMargin(false);
    	//setWidth("100%");
    	//setHeight("100%");
    	
    	      
        String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblHuespedes\\Fotos\\" + TblEstadisticasHuesped.getID() + "\\";
        String DirUrl="../dbsimagenes/tblHuespedes/Fotos/" + TblEstadisticasHuesped.getID() + "/";
       
        ByosVisorImegenes01 = new ByosVisorImegenes(DirUrl, DirFile, "", TblEstadisticasHuesped.getNumeroDocumento(),MostrarMesnu, ViwerHeight);
        
        Embedded reindeerImage = null;
        if (ByosVisorImegenes01.getImagenes() !=  null && ByosVisorImegenes01.getImagenes().size()>0) {
        	reindeerImage = new Embedded( null, ByosVisorImegenes01.getImagenes().get(0) );
           
        }else{
        	reindeerImage = new Embedded( null, ByosImagenes.icon[126]);
        }
        reindeerImage.setWidth( "182px" ); 
        reindeerImage.setHeight( "233px" );
        reindeerImage.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                     	
            }
        });
        GrupoFoto.addComponent(reindeerImage);
        GrupoFoto.setComponentAlignment(reindeerImage, Alignment.TOP_CENTER);
        
        
        GrupoFoto.addComponent(layoutCenter01);
        
        LayoutDetalle.addComponent(GrupoDatos01);
        LayoutDetalle.addComponent(GrupoFoto);
        LayoutDetalle.addComponent(GrupoDatos02);
        
        btoLlamadaReserva01.setWidth("81px");
        btoLlamadaReserva01.setHeight("50px");
        btoLlamadaReserva01.setStyleName("BotonDataDetalle01");
        
        TextoTelefono.setWidth("342px");
        TextoTelefono.setHeight("50px");
        TextoTelefono.setStyleName("TextoFiledTelefono");
        TextoTelefono.setValue(TblEstadisticasHuesped.getTelefono());
        
        btoLlamadaReserva02.setWidth("81px");
        btoLlamadaReserva02.setHeight("50px");
        btoLlamadaReserva02.setStyleName("BotonDataDetalle01");
                
        LayoutReserva01.addComponent(btoLlamadaReserva01);
        LayoutReserva01.addComponent(TextoTelefono);
        LayoutReserva01.addComponent(btoLlamadaReserva02);
        
        

    
        
        LayoutDetalleGlobal.addComponent(LayoutDetalle);
        LayoutDetalleGlobal.addComponent(LayoutReserva01);
        LayoutDetalleGlobal.addComponent(LayoutTituloFinalDatos01);
        
        
        LayoutCuerpo.addComponent(layoutLeft01);
        LayoutCuerpo.addComponent(LayoutDetalleGlobal);
        LayoutCuerpo.addComponent(layoutRight01);
        
        addComponent(LayoutCabezera);
        addComponent(LayoutCuerpo);   
        setComponentAlignment(LayoutCabezera, Alignment.TOP_CENTER);
        setComponentAlignment(LayoutCuerpo, Alignment.TOP_CENTER);

    }
    
    public void setTblEstadisticasHuesped(final tblEstadisticasHuesped TblEstadisticasHuesped){
    	this.TblEstadisticasHuesped = TblEstadisticasHuesped;
    	String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblEstadisticasHuesped\\Fotos\\" + TblEstadisticasHuesped.getID() + "\\";
        String DirUrl="../dbsimagenes/tblEstadisticasHuesped/Fotos/" + TblEstadisticasHuesped.getID() + "/";
        ByosVisorImegenes01.init(DirUrl, DirFile, TblEstadisticasHuesped.getNumeroDocumento());
    }
       
    public void ActivarAyuda() {   	
    	if(LayoutCabezeraTitulo.isVisible()) {
        	LayoutCabezeraTitulo.setVisible(false);
        	LayoutCabezeraContenedor.setVisible(true);
    	}else{
        	LayoutCabezeraTitulo.setVisible(true);
        	LayoutCabezeraContenedor.setVisible(false);	
    	} 
    }
    
    public void proximaAyuda() {
    	lblCabezeraTitulo.setValue(TextoAyuda[indiceAyuda]);
		indiceAyuda++;
	    if(indiceAyuda>=TextoAyuda.length) {
	    	indiceAyuda=0;
	    }
    }

    public void setTextoAyuda() {
        indiceAyuda=0;
    	TextoAyuda[0] = "!!HOLA¡¡ Srta. " + TblEstadisticasHuesped.getApodo() + " Bienvenida, Como esta UD., Cuanto Tiempo, Digame Que Desea?";
    	TextoAyuda[1] = "Que tipo de habitacion desea?";
    	TextoAyuda[2] = "Cuantas Personas Vienen con UD?";
    	TextoAyuda[3] = "Deque fecha a que cual fecha desea reservar?";
    	TextoAyuda[4] = "En que mas lapuedo ayudar?"; 
    	TextoAyuda[5] = "Gracias por su consulta, espero verla pronto..."; 
    }
    
 
 
    
}

