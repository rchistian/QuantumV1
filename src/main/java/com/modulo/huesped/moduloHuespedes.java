package com.modulo.huesped;

import java.io.Serializable;
import java.util.ArrayList;

import com.byos.sys.filters.LikeFilter;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.byos.sys.util.utilString;
import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosContenedores;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.ByosVisorImegenes;
import com.modulo.componentes.ByosVisorZoom;
import com.modulo.huesped.tblHuespedes;
import com.modulo.main.TabMenu;
import com.modulo.producto.moduloProductoDetalle;
import com.modulo.producto.tblProducto;
import com.modulo.reserva.moduloReserva01;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class moduloHuespedes extends VerticalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public Window subwindow;
    
    public TabMenu TabSistema = new TabMenu();
    private VerticalLayout layout = new VerticalLayout();
    
    private VerticalLayout layoutLeftTab01_01 = new VerticalLayout();
    private VerticalLayout layoutCenterTab01_01 = new VerticalLayout();
    private VerticalLayout layoutRightTab01_01 = new VerticalLayout();

    private VerticalLayout layoutLeftTab02_01 = new VerticalLayout();
    private VerticalLayout layoutCenterTab02_01 = new VerticalLayout();
    private VerticalLayout layoutRightTab02_01 = new VerticalLayout();

    private VerticalLayout layoutLeftTab03_01 = new VerticalLayout();
    private VerticalLayout layoutCenterTab03_01 = new VerticalLayout();
    private VerticalLayout layoutRightTab03_01 = new VerticalLayout();

    private VerticalLayout layoutLeftTab04_01 = new VerticalLayout();
    private VerticalLayout layoutCenterTab04_01 = new VerticalLayout();
    private VerticalLayout layoutRightTab04_01 = new VerticalLayout();
    
    private HorizontalLayout layout01 = new HorizontalLayout();    
    private HorizontalLayout layout02 = new HorizontalLayout();    
    private HorizontalLayout layout03 = new HorizontalLayout();    
    private HorizontalLayout layout04 = new HorizontalLayout();    
    private HorizontalLayout layout05 = new HorizontalLayout();    
    private HorizontalLayout layout06 = new HorizontalLayout();    
    private HorizontalLayout layout07 = new HorizontalLayout();    
    private HorizontalLayout layout08 = new HorizontalLayout();    

     
    ByosForm ByosFormularioTab01_01 = new ByosForm();
    ByosForm ByosFormularioTab01_02 = new ByosForm();
    ByosForm ByosFormularioTab01_03 = new ByosForm();
    
    ByosForm ByosFormularioTab02_01 = new ByosForm();
    ByosForm ByosFormularioTab02_02 = new ByosForm();
    ByosForm ByosFormularioTab02_03 = new ByosForm();
    
    ByosForm ByosFormularioTab03_01 = new ByosForm();
    ByosForm ByosFormularioTab03_02 = new ByosForm();
    ByosForm ByosFormularioTab03_03 = new ByosForm();

    ByosForm ByosFormularioTab04_01 = new ByosForm();
    ByosForm ByosFormularioTab04_02 = new ByosForm();
    ByosForm ByosFormularioTab04_03 = new ByosForm();
    
    ByosVerticalLayout MainTab01_01 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab01_02 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab01_03 = new ByosVerticalLayout("","100%");
    
    ByosVerticalLayout MainTab02_01 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab02_02 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab02_03 = new ByosVerticalLayout("","100%"); 
    
    ByosVerticalLayout MainTab03_01 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab03_02 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab03_03 = new ByosVerticalLayout("","100%");

    ByosVerticalLayout MainTab04_01 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab04_02 = new ByosVerticalLayout("","100%");
    ByosVerticalLayout MainTab04_03 = new ByosVerticalLayout("","100%");
    
    public tblHuespedes ClaseHuespedes;
    moduloHuespedesDetalle ModuloHuespedesDetalleTab01;
    moduloHuespedesDetalle ModuloHuespedesDetalleTab02;
    moduloHuespedesDetalle ModuloHuespedesDetalleTab03;
    moduloHuespedesDetalle ModuloHuespedesDetalleTab04;
    moduloHuespedesDetalle ModuloHuespedesDetalleTab07;
    moduloHuespedesDocumentos ModuloHuespedesDocumentosTab08;

    public ByosMenu menu = new ByosMenu();
   
    
    /* Inicio Variables Tab 01 Ficha */

    /* Datos Izquierda */
    String DatosVisiblesTab01_01[] = {
       "TipoDocumento",
       "NumeroDocumento",
       "Sexo",
       "Nivel",
       "VencimientoDoc",
       "Recomendado",
       "UltimaEstadia",
       "UltimaHabitacion",
       "TelefonoMovil"
    };

    String DatosTitulosTab01_01[] = {
       "Tipo Documento",
       "Numero Documento",
       "Sexo",
       "Nivel",
       "Vencimiento De Documento",
       "Recomendado",
       "Ultima Estadia",
       "Ultima Habitacion",
       "Telefono Movil" 
    };

    /* Datos Centro */
    String DatosVisiblesTab01_02[] = {
       "Nombres",
       "Apellidos",
       "Apodo"
    };

    String DatosTitulosTab01_02[] = {
       "Nombres",
       "Apellidos",
       "Apodo"
    };
    
    /* Datos Derecha */
    String DatosVisiblesTab01_03[] = {
       "Telefono",
       "Correo",
       "Altura",
       "ColorPiel",
       "ColorOjos",
       "ColorCabello",
       "Medidas",
       "Amistad",
       "UltimaEstadiaAmistad"
    };

    String DatosTitulosTab01_03[] = {
       "Telefono",
       "Correo",
       "Altura",
       "Color Piel",
       "Color Ojos",
       "Color Cabello",
       "Medidas",
       "Amistad",
       "Ultima Estadia Amistad"       
    };  
    /* Fin Variables Tab 01 Ficha */
    
    /* Inicio Variables Tab 02 Datos Personales */
    
    /* Datos Izquierda */  
    String DatosVisiblesTab02_01[] = {
            "FechaNacimiento",
            "EstadoCivil",
    		"Direccion",
            "CodigoPostal",
    };
    
    String DatosTitulosTab02_01[] = {
            "Fecha Nacimiento",
            "Estado Civil",
    		"Direccion",
            "Codigo Postal",
    };
    
    
    /* Datos Derecha */    
    String DatosVisiblesTab02_03[] = {
            "Ciudad",
            "Provincia",
            "Correo",
            "CodigoTarjeta"
    };

    String DatosTitulosTab02_03[] = {
            "Ciudad",
            "Provincia",
            "Correo",
            "Codigo Tarjeta"
    };
    
    /* Fin Variables Tab 02 Datos Personales */
    
    
    /* Inicio Variables Tab 03 Datos Personales */
    
    /* Datos Izquierda */
    String DatosVisiblesTab03_01[] = {
         "PaisOrigen",
         "CiudadOrigen",
         "DireccionOrigen",
    };

    String DatosTitulosTab03_01[] = {
         "Pais Origen",
    	 "Ciudad Origen",
         "Direccion Origen",
    };

    /* Datos Derecha */
    String DatosVisiblesTab03_03[] = {
         "CodigoPostalOrigen",	
         "TelefonoOrigen",
         "AgenciaOrigen",
    };
    
    String DatosTitulosTab03_03[] = {
    	 "Codigo Postal Origen",
         "Telefono Origen",
         "Agencia Origen",
    };    

    /* Fin Variables Tab 03 Datos Personales */

    
    /* Inicio Variables Tab 03 Redes Sociales */
    
    /* Datos Izquierda */
    String DatosVisiblesTab04_01[] = {	
         "RedSocial01",
         "RedSocial02",
         "RedSocial03",
         "RedSocial04",
         "RedSocial05",
    };

    String DatosTitulosTab04_01[] = {	
         "Whatsapp",
         "FaceBook",
         "Twitter",
         "Youtube",
         "Instagran",
    };
    
    /* Datos Derecha */
    String DatosVisiblesTab04_03[] = {	
         "RedSocial06",
         "RedSocial07",
         "RedSocial08",
         "RedSocial09",
         "RedSocial10"
    };

    String DatosTitulosTab04_03[] = {	
         "RedSocial 06",
         "RedSocial 07",
         "RedSocial 08",
         "RedSocial 09",
         "RedSocial 10"
    };
    
    /* Fin Variables Tab 04 Redes Sociales */
    
    ByosBoton BtoFicha = new ByosBoton(menu.MenuPrincipal, 101,"Ficha","");
    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");                 //0
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");               //3
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Eliminar","");           //2
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");             //1
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior",""); //14
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");   //15
    ByosBoton BtoSalir = new ByosBoton(menu.MenuPrincipal, 110,"Salir","");               //115
       
    public moduloHuespedes() {
    	ClaseHuespedes = new tblHuespedes(); 
    	    	
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	
    	menu.setWidth("100%");
    	menu.setHeight("20%");
    	menu.setSpacing(false);
    	menu.setMargin(false);
    	    	
    	
    	ByosFormularioTab01_01.setWidth("100%");
    	ByosFormularioTab01_02.setWidth("100%");
    	ByosFormularioTab01_03.setWidth("100%");

    	ByosFormularioTab02_01.setWidth("100%");
    	ByosFormularioTab02_02.setWidth("100%");
    	ByosFormularioTab02_03.setWidth("100%");

    	ByosFormularioTab03_01.setWidth("100%");
    	ByosFormularioTab03_02.setWidth("100%");
    	ByosFormularioTab03_03.setWidth("100%");

    	ByosFormularioTab04_01.setWidth("100%");
    	ByosFormularioTab04_02.setWidth("100%");
    	ByosFormularioTab04_03.setWidth("100%");
    	
        ByosFormularioTab01_01.setTipoLayout("Vertical");
        ByosFormularioTab01_02.setTipoLayout("Vertical");
        ByosFormularioTab01_03.setTipoLayout("Vertical");

        ByosFormularioTab02_01.setTipoLayout("Vertical");
        ByosFormularioTab02_02.setTipoLayout("Vertical");
        ByosFormularioTab02_03.setTipoLayout("Vertical");

        ByosFormularioTab03_01.setTipoLayout("Vertical");
        ByosFormularioTab03_02.setTipoLayout("Vertical");
        ByosFormularioTab03_03.setTipoLayout("Vertical");

        ByosFormularioTab04_01.setTipoLayout("Vertical");
        ByosFormularioTab04_02.setTipoLayout("Vertical");
        ByosFormularioTab04_03.setTipoLayout("Vertical");
        
        ByosFormularioTab01_01.setMostrarEstado(false);
        ByosFormularioTab01_02.setMostrarEstado(false);
        ByosFormularioTab01_03.setMostrarEstado(false);

        ByosFormularioTab02_01.setMostrarEstado(false);
        ByosFormularioTab02_02.setMostrarEstado(false);
        ByosFormularioTab02_03.setMostrarEstado(false);

        ByosFormularioTab03_01.setMostrarEstado(false);
        ByosFormularioTab03_02.setMostrarEstado(false);
        ByosFormularioTab03_03.setMostrarEstado(false);
        
        ByosFormularioTab04_01.setMostrarEstado(false);
        ByosFormularioTab04_02.setMostrarEstado(false);
        ByosFormularioTab04_03.setMostrarEstado(false);

        ByosFormularioTab01_01.setLabelTexto(true);
        ByosFormularioTab01_02.setLabelTexto(true);
        ByosFormularioTab01_03.setLabelTexto(true);

        ByosFormularioTab02_01.setLabelTexto(true);
        ByosFormularioTab02_02.setLabelTexto(true);
        ByosFormularioTab02_03.setLabelTexto(true);

        ByosFormularioTab03_01.setLabelTexto(true);
        ByosFormularioTab03_02.setLabelTexto(true);
        ByosFormularioTab03_03.setLabelTexto(true);
        
        ByosFormularioTab04_01.setLabelTexto(true);
        ByosFormularioTab04_02.setLabelTexto(true);
        ByosFormularioTab04_03.setLabelTexto(true);
      
        ByosFormularioTab01_01.setClase(ClaseHuespedes, DatosVisiblesTab01_01, DatosTitulosTab01_01, null);
        ByosFormularioTab01_02.setClase(ClaseHuespedes, DatosVisiblesTab01_02, DatosTitulosTab01_02, null);
        ByosFormularioTab01_03.setClase(ClaseHuespedes, DatosVisiblesTab01_03, DatosTitulosTab01_03, null);

        ByosFormularioTab02_01.setClase(ClaseHuespedes, DatosVisiblesTab02_01, DatosTitulosTab02_01, null);
        ByosFormularioTab02_02.setClase(ClaseHuespedes, DatosVisiblesTab01_02, DatosTitulosTab01_02, null);
        ByosFormularioTab02_03.setClase(ClaseHuespedes, DatosVisiblesTab02_03, DatosTitulosTab02_03, null);

        ByosFormularioTab03_01.setClase(ClaseHuespedes, DatosVisiblesTab03_01, DatosTitulosTab03_01, null);
        ByosFormularioTab03_02.setClase(ClaseHuespedes, DatosVisiblesTab01_02, DatosTitulosTab01_02, null);
        ByosFormularioTab03_03.setClase(ClaseHuespedes, DatosVisiblesTab03_03, DatosTitulosTab03_03, null);

        ByosFormularioTab04_01.setClase(ClaseHuespedes, DatosVisiblesTab04_01, DatosTitulosTab04_01, null);
        ByosFormularioTab04_02.setClase(ClaseHuespedes, DatosVisiblesTab01_02, DatosTitulosTab01_02, null);
        ByosFormularioTab04_03.setClase(ClaseHuespedes, DatosVisiblesTab04_03, DatosTitulosTab04_03, null);
        
        initComponentes();       
        MainTab01_01.setMargin(false);
        MainTab01_01.setWidth("100%");
        MainTab01_02.setMargin(false);
        MainTab01_02.setWidth("100%");        
        MainTab01_03.setMargin(false);
        MainTab01_03.setWidth("100%");
        
        MainTab02_01.setMargin(false);
        MainTab02_01.setWidth("100%");        
        MainTab02_02.setMargin(false);
        MainTab02_02.setWidth("100%");        
        MainTab02_03.setMargin(false);
        MainTab02_03.setWidth("100%");

        MainTab03_01.setMargin(false);
        MainTab03_01.setWidth("100%");        
        MainTab03_02.setMargin(false);
        MainTab03_02.setWidth("100%");        
        MainTab03_03.setMargin(false);
        MainTab03_03.setWidth("100%");

        MainTab04_01.setMargin(false);
        MainTab04_01.setWidth("100%");        
        MainTab04_02.setMargin(false);
        MainTab04_02.setWidth("100%");        
        MainTab04_03.setMargin(false);
        MainTab04_03.setWidth("100%");

        ModuloHuespedesDetalleTab01 = new moduloHuespedesDetalle(ClaseHuespedes,true,"200px");
        ModuloHuespedesDetalleTab01.setWidth("100%");
        
        ModuloHuespedesDetalleTab02 = new moduloHuespedesDetalle(ClaseHuespedes,false,"200px");
        ModuloHuespedesDetalleTab02.setWidth("100%");

        ModuloHuespedesDetalleTab03 = new moduloHuespedesDetalle(ClaseHuespedes,false,"200px");
        ModuloHuespedesDetalleTab03.setWidth("100%");

        ModuloHuespedesDetalleTab04 = new moduloHuespedesDetalle(ClaseHuespedes,false,"200px");
        ModuloHuespedesDetalleTab04.setWidth("100%");

        ModuloHuespedesDetalleTab07 = new moduloHuespedesDetalle(ClaseHuespedes,true,"500px");
        ModuloHuespedesDetalleTab07.setWidth("100%");

        ModuloHuespedesDocumentosTab08 = new moduloHuespedesDocumentos(ClaseHuespedes,true,"500px");
        ModuloHuespedesDocumentosTab08.setWidth("100%");
        
        MainTab01_01.Contenido.addComponent(ByosFormularioTab01_01); 
        MainTab01_02.Contenido.addComponent(ByosFormularioTab01_02); 
        MainTab01_03.Contenido.addComponent(ByosFormularioTab01_03); 

        MainTab02_01.Contenido.addComponent(ByosFormularioTab02_01); 
        MainTab02_02.Contenido.addComponent(ByosFormularioTab02_02); 
        MainTab02_03.Contenido.addComponent(ByosFormularioTab02_03); 

        MainTab03_01.Contenido.addComponent(ByosFormularioTab03_01); 
        MainTab03_02.Contenido.addComponent(ByosFormularioTab03_02); 
        MainTab03_03.Contenido.addComponent(ByosFormularioTab03_03); 

        MainTab04_01.Contenido.addComponent(ByosFormularioTab04_01); 
        MainTab04_02.Contenido.addComponent(ByosFormularioTab04_02); 
        MainTab04_03.Contenido.addComponent(ByosFormularioTab04_03); 
        
    	subwindow = new Window("Datos del Huesped");
        subwindow.setWidth("1024px");
        subwindow.setHeight("768px");
        subwindow.setModal(true);       
        subwindow.setContent(this);
        
        TabSistema.setWidth("100%");
        TabSistema.setHeight("90%");
        TabSistema.t.setStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        
        /* Tab 01  Ficha*/
        layoutCenterTab01_01.setSpacing(false);
        layoutCenterTab01_01.setMargin(false);

        layoutLeftTab01_01.setWidth("100%");
        layoutCenterTab01_01.setWidth("100%");
        layoutRightTab01_01.setWidth("100%");

        layout01.setSizeFull();
        layout01.addComponent(layoutLeftTab01_01);
        layout01.addComponent(layoutCenterTab01_01);
        layout01.addComponent(layoutRightTab01_01);
        
        layoutLeftTab01_01.addComponent(MainTab01_01);
        layoutCenterTab01_01.addComponent(ModuloHuespedesDetalleTab01);
        layoutCenterTab01_01.addComponent(MainTab01_02);
        layoutRightTab01_01.addComponent(MainTab01_03);
        Tab Tab01 = TabSistema.t.addTab(layout01, "Ficha", ByosImagenes.icon[84]);
        TabSistema.vectorTab.add(Tab01);
        Tab01.setClosable(false);
        
        /* Tap 02 Datos Personales */
        layoutCenterTab02_01.setSpacing(false);
        layoutCenterTab02_01.setMargin(false);

        layoutLeftTab02_01.setWidth("100%");
        layoutCenterTab02_01.setWidth("100%");
        layoutRightTab02_01.setWidth("100%");

        layout02.setSizeFull();
        layout02.addComponent(layoutLeftTab02_01);
        layout02.addComponent(layoutCenterTab02_01);
        layout02.addComponent(layoutRightTab02_01);
        
        layoutLeftTab02_01.addComponent(MainTab02_01);
        layoutCenterTab02_01.addComponent(ModuloHuespedesDetalleTab02);
        layoutCenterTab02_01.addComponent(MainTab02_02);
        layoutRightTab02_01.addComponent(MainTab02_03);        
        Tab Tab02 = TabSistema.t.addTab(layout02, "Datos Personales", ByosImagenes.icon[85]);
        TabSistema.vectorTab.add(Tab02);
        Tab02.setClosable(false);
 
        /* Tap 03 Datos Personales */
        layoutCenterTab03_01.setSpacing(false);
        layoutCenterTab03_01.setMargin(false);

        layoutLeftTab03_01.setWidth("100%");
        layoutCenterTab03_01.setWidth("100%");
        layoutRightTab03_01.setWidth("100%");

        layout03.setSizeFull();
        layout03.addComponent(layoutLeftTab03_01);
        layout03.addComponent(layoutCenterTab03_01);
        layout03.addComponent(layoutRightTab03_01);
        
        layoutLeftTab03_01.addComponent(MainTab03_01);
        layoutCenterTab03_01.addComponent(ModuloHuespedesDetalleTab03);
        layoutCenterTab03_01.addComponent(MainTab03_02);
        layoutRightTab03_01.addComponent(MainTab03_03);        
        Tab Tab03 = TabSistema.t.addTab(layout03, "Extranjero", ByosImagenes.icon[86]);
        TabSistema.vectorTab.add(Tab03);
        Tab03.setClosable(false);
                
        /* Tap 04 Datos Personales */
        layoutCenterTab04_01.setSpacing(false);
        layoutCenterTab04_01.setMargin(false);

        layoutLeftTab04_01.setWidth("100%");
        layoutCenterTab04_01.setWidth("100%");
        layoutRightTab04_01.setWidth("100%");

        layout04.setSizeFull();
        layout04.addComponent(layoutLeftTab04_01);
        layout04.addComponent(layoutCenterTab04_01);
        layout04.addComponent(layoutRightTab04_01);
        
        layoutLeftTab04_01.addComponent(MainTab04_01);
        layoutCenterTab04_01.addComponent(ModuloHuespedesDetalleTab04);
        layoutCenterTab04_01.addComponent(MainTab04_02);
        layoutRightTab04_01.addComponent(MainTab04_03);        
        Tab Tab04 = TabSistema.t.addTab(layout04, "Redes Sociales", ByosImagenes.icon[87]);
        TabSistema.vectorTab.add(Tab04);
        Tab04.setClosable(false);        
        
        
        layout05.setSizeFull();
        Tab Tab05 = TabSistema.t.addTab(layout05, "Comunicaciones", ByosImagenes.icon[88]);                                                                                                                                                                       
        TabSistema.vectorTab.add(Tab05);
        Tab05.setClosable(false);   
        
        layout06.setSizeFull();
        Tab Tab06 = TabSistema.t.addTab(layout06, "Estadisticas", ByosImagenes.icon[91]);
        TabSistema.vectorTab.add(Tab06);
        Tab06.setClosable(false);        
        
        layout07.setSizeFull();
        layout07.addComponent(ModuloHuespedesDetalleTab07);
        Tab Tab07 = TabSistema.t.addTab(layout07, "Fotos", ByosImagenes.icon[92]);
        TabSistema.vectorTab.add(Tab07);
        Tab07.setClosable(false);        
        
        layout08.setSizeFull();
        layout08.addComponent(ModuloHuespedesDocumentosTab08);
        Tab Tab08 = TabSistema.t.addTab(layout08, "Documentos", ByosImagenes.icon[93]);
        TabSistema.vectorTab.add(Tab08);
        Tab08.setClosable(false);  
        
        //layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(menu);
        layout.addComponent(TabSistema);
        
        
        addComponent(layout);
        
        
    }
    
    @SuppressWarnings({ "deprecation", "serial" })
	public void initComponentes() {

    	BtoFicha.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                procesoFicha();
             }
          });
    	
    	BtoSalir.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                closeWindows();
             }
          });
    	
    	
        BtoNuevo.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
               procesoNuevo();
               
            }
         });

         BtoBuscar.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
               try {
 				procesoBuscar();
 			} catch (Exception e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
            }
         });
         
         BtoEliminar.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                      procesoEliminar();                     
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
                 
             }
         });
         BtoGuardar.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                     procesoGuardar();
                     
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         BtoProximo.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                 	procesoTblProximo();
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         BtoAnterior.addClickListener(new Button.ClickListener() {
             public void buttonClick(Button.ClickEvent event) {
                 try {
                 	procesoTblAnterior();    
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         });
         
         ByosFormularioTab01_01.getCampo("NumeroDocumento").btoBoton1.setBoton(3, "Listar","");
         ByosFormularioTab01_01.getCampo("NumeroDocumento").btoBoton1.setVisible(true);
         ByosFormularioTab01_01.getCampo("NumeroDocumento").btoBoton1.addClickListener(new Button.ClickListener() {
             public void buttonClick(ClickEvent event) {
                 try {
                     procesoListar(false);       
                 } catch (Exception e) {
                     // Ingnored, we'll let the Form handle the errors
                 }
             }
         }); 
         
     	ByosFormularioTab01_01.setTipoCampo("Sexo", "ComboBox");             
     	ByosFormularioTab01_01.getCampo("Sexo").CboxItem.setContainerDataSource(ByosContenedores.getContainerSexo());

     	ByosFormularioTab01_01.setTipoCampo("TipoDocumento", "ComboBox");             
     	ByosFormularioTab01_01.getCampo("TipoDocumento").CboxItem.setContainerDataSource(ByosContenedores.getContainerTipoDocuemnto());

     	ByosFormularioTab02_01.setTipoCampo("EstadoCivil", "ComboBox");             
     	ByosFormularioTab02_01.getCampo("EstadoCivil").CboxItem.setContainerDataSource(ByosContenedores.getContainerEstadoCivil());
     	
     	ByosFormularioTab03_01.setTipoCampo("PaisOrigen", "ComboBox");             
     	ByosFormularioTab03_01.getCampo("PaisOrigen").CboxItem.setContainerDataSource(ByosContenedores.getContainerPaises());
     	
     	
     	ByosFormularioTab01_01.setTipoCampo("VencimientoDoc", "DateField"); 
     	ByosFormularioTab01_01.setTipoCampo("UltimaEstadia", "DateField"); 
     	ByosFormularioTab01_03.setTipoCampo("UltimaEstadiaAmistad", "DateField"); 
     	ByosFormularioTab02_01.setTipoCampo("FechaNacimiento", "DateField"); 

    }
 
    public void asignacion(){
    	try{
    		AsignarRegistro();
			((tblHuespedes)ByosFormularioTab01_01.Clase).buscarCodigo(((tblHuespedes)ByosFormularioTab01_01.Clase).getNumeroDocumento());
			((tblHuespedes)ByosFormularioTab01_02.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_01.Clase));
			((tblHuespedes)ByosFormularioTab01_03.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_01.Clase));


			((tblHuespedes)ByosFormularioTab02_01.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_01.Clase));
			((tblHuespedes)ByosFormularioTab02_02.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_01.Clase));
			((tblHuespedes)ByosFormularioTab02_03.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_01.Clase));
			
			((tblHuespedes)ByosFormularioTab03_01.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab03_01.Clase));
			((tblHuespedes)ByosFormularioTab03_02.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab03_01.Clase));
			((tblHuespedes)ByosFormularioTab03_03.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab03_01.Clase));

			((tblHuespedes)ByosFormularioTab04_01.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab04_01.Clase));
			((tblHuespedes)ByosFormularioTab04_02.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab04_01.Clase));
			((tblHuespedes)ByosFormularioTab04_03.Clase).setTblHuespedes(((tblHuespedes)ByosFormularioTab04_01.Clase));
			
			ModuloHuespedesDetalleTab01.setTblHuespedes((tblHuespedes)ByosFormularioTab01_01.Clase);
			ModuloHuespedesDetalleTab02.setTblHuespedes((tblHuespedes)ByosFormularioTab01_01.Clase);
			ModuloHuespedesDetalleTab03.setTblHuespedes((tblHuespedes)ByosFormularioTab01_01.Clase);
			ModuloHuespedesDetalleTab04.setTblHuespedes((tblHuespedes)ByosFormularioTab01_01.Clase);
			ModuloHuespedesDetalleTab07.setTblHuespedes((tblHuespedes)ByosFormularioTab01_01.Clase);
			ModuloHuespedesDocumentosTab08.setTblHuespedes((tblHuespedes)ByosFormularioTab01_01.Clase);

    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void procesoFicha() {
    	String xNumeroDocumento=ClaseHuespedes.getNumeroDocumento(); // ((tblHuespedes)ByosFormularioTab01_01.Clase).getNumeroDocumento();
    	if (xNumeroDocumento!=null && !xNumeroDocumento.equals("")) {
    		new moduloReserva01(xNumeroDocumento,"GV","LLAMADA ENTRANTE DE PERSONA CONOCIDA").openWindows();
    	}
    }

    public void procesoRefrescarTbl(){
    	ByosFormularioTab01_01.refrescar();
    	ByosFormularioTab01_02.refrescar();
    	ByosFormularioTab01_03.refrescar();

    	ByosFormularioTab02_01.refrescar();
    	ByosFormularioTab02_02.refrescar();
    	ByosFormularioTab02_03.refrescar();
    	
    	ByosFormularioTab03_01.refrescar();
    	ByosFormularioTab03_02.refrescar();
    	ByosFormularioTab03_03.refrescar();

    	ByosFormularioTab04_01.refrescar();
    	ByosFormularioTab04_02.refrescar();
    	ByosFormularioTab04_03.refrescar();
    }
    
    public void procesoLimpiarTbl() {
        ((tblHuespedes)ByosFormularioTab01_01.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab01_02.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab01_03.Clase).limpiar();
        
        ((tblHuespedes)ByosFormularioTab02_01.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab02_02.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab02_03.Clase).limpiar();
        
        ((tblHuespedes)ByosFormularioTab03_01.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab03_02.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab03_03.Clase).limpiar();

        ((tblHuespedes)ByosFormularioTab04_01.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab04_02.Clase).limpiar();
        ((tblHuespedes)ByosFormularioTab04_03.Clase).limpiar();

    }
    
    public void procesoBorrarArrayTbl(){
        ByosFormularioTab01_01.setArrayClase(null);
        ByosFormularioTab01_02.setArrayClase(null);
        ByosFormularioTab01_03.setArrayClase(null);

        ByosFormularioTab02_01.setArrayClase(null);
        ByosFormularioTab02_02.setArrayClase(null);
        ByosFormularioTab02_03.setArrayClase(null);    
        
        ByosFormularioTab03_01.setArrayClase(null);
        ByosFormularioTab03_02.setArrayClase(null);
        ByosFormularioTab03_03.setArrayClase(null);         

        ByosFormularioTab04_01.setArrayClase(null);
        ByosFormularioTab04_02.setArrayClase(null);
        ByosFormularioTab04_03.setArrayClase(null);             
    }
    
    public void procesoTblProximo(){
    	ByosFormularioTab01_01.procesoProximo();
    	ByosFormularioTab01_02.procesoProximo();
    	ByosFormularioTab01_03.procesoProximo();
    	
    	ByosFormularioTab02_01.procesoProximo();
    	ByosFormularioTab02_02.procesoProximo();
    	ByosFormularioTab02_03.procesoProximo();

    	ByosFormularioTab03_01.procesoProximo();
    	ByosFormularioTab03_02.procesoProximo();
    	ByosFormularioTab03_03.procesoProximo();

    	ByosFormularioTab04_01.procesoProximo();
    	ByosFormularioTab04_02.procesoProximo();
    	ByosFormularioTab04_03.procesoProximo();

    	asignacion();
    	procesoRefrescarTbl();
    }
    
    public void procesoTblAnterior(){
    	ByosFormularioTab01_01.procesoAnterior();
    	ByosFormularioTab01_02.procesoAnterior();
    	ByosFormularioTab01_03.procesoAnterior();

    	ByosFormularioTab02_01.procesoAnterior();
    	ByosFormularioTab02_02.procesoAnterior();
    	ByosFormularioTab02_03.procesoAnterior();
    	
    	ByosFormularioTab03_01.procesoAnterior();
    	ByosFormularioTab03_02.procesoAnterior();
    	ByosFormularioTab03_03.procesoAnterior();    	

    	ByosFormularioTab04_01.procesoAnterior();
    	ByosFormularioTab04_02.procesoAnterior();
    	ByosFormularioTab04_03.procesoAnterior();    	

    	asignacion();
    	procesoRefrescarTbl();
    }    
      
    
	public void procesoBuscar() throws Exception {
		AsignarRegistro();
		ByosFormularioTab01_01.setArrayClase(ClaseHuespedes.BuscarArray(ClaseHuespedes)); 		
		ByosFormularioTab01_02.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		ByosFormularioTab01_03.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		
		ByosFormularioTab02_01.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		ByosFormularioTab02_02.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		ByosFormularioTab02_03.setArrayClase(ByosFormularioTab01_01.getArrayClase());  

		ByosFormularioTab03_01.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		ByosFormularioTab03_02.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		ByosFormularioTab03_03.setArrayClase(ByosFormularioTab01_01.getArrayClase());  

		ByosFormularioTab04_01.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		ByosFormularioTab04_02.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		ByosFormularioTab04_03.setArrayClase(ByosFormularioTab01_01.getArrayClase());  
		
		if (ByosFormularioTab01_01.ArrayClase == null || ByosFormularioTab01_01.ArrayClase.size()==0){
            procesoLimpiarTbl();
            procesoRefrescarTbl();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
		procesoRefrescarTbl();
    }
    
    
    public void procesoNuevo(){
        procesoLimpiarTbl();
        procesoRefrescarTbl();
        procesoBorrarArrayTbl();
        asignacion();
    }    

    public void procesoGuardar(){
    	System.out.println("Bandera Inico Maodificar/Guardar 01");
      if(ByosFormularioTab01_01.DatosRequeridos(DatosVisiblesTab01_01)){	
    	  System.out.println("Bandera Inico Maodificar/Guardar 01.1");
    	boolean Existe=false;
    	try{
    		
			Existe=((tblHuespedes)ByosFormularioTab01_01.Clase).existeCodigo(((tblHuespedes)ByosFormularioTab01_01.Clase).getNumeroDocumento());
			System.out.println("Bandera Inico Maodificar/Guardar 01.2");
    	}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	System.out.println("Bandera Inico Maodificar/Guardar 02");
    	if(Existe){		   
    		try{
    			System.out.println("Bandera Inico Guardar 01");
    			AsignarRegistro();
    			System.out.println("Bandera Inico Guardar 02");
			    String Resultado=((tblHuespedes)ByosFormularioTab01_01.Clase).guardar(ClaseHuespedes);
			    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	           if(ByosFormularioTab01_01.ArrayClase!=null){		
	                  if(ByosFormularioTab01_01.Posicion>=0){
	 	      	         ((tblHuespedes) ByosFormularioTab01_01.ArrayClase.get(ByosFormularioTab01_01.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_01.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab01_02.ArrayClase.get(ByosFormularioTab01_02.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_02.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab01_03.ArrayClase.get(ByosFormularioTab01_03.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab01_03.Clase));

	 	      	         ((tblHuespedes) ByosFormularioTab02_01.ArrayClase.get(ByosFormularioTab02_01.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab02_01.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab02_02.ArrayClase.get(ByosFormularioTab02_02.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab02_02.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab02_03.ArrayClase.get(ByosFormularioTab02_03.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab02_03.Clase));

	 	      	         ((tblHuespedes) ByosFormularioTab03_01.ArrayClase.get(ByosFormularioTab03_01.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab03_01.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab03_02.ArrayClase.get(ByosFormularioTab03_02.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab03_02.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab03_03.ArrayClase.get(ByosFormularioTab03_03.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab03_03.Clase));

	 	      	         ((tblHuespedes) ByosFormularioTab04_01.ArrayClase.get(ByosFormularioTab04_01.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab04_01.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab04_02.ArrayClase.get(ByosFormularioTab04_02.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab04_02.Clase));
	 	      	         ((tblHuespedes) ByosFormularioTab04_03.ArrayClase.get(ByosFormularioTab04_03.Posicion)).setTblHuespedes(((tblHuespedes)ByosFormularioTab04_03.Clase));
	 	      	         
	 	      	         procesoRefrescarTbl();
	                   }
	               }                      
	  	           Notification.show("Registro Modificada", Notification.TYPE_TRAY_NOTIFICATION);
	            }else{
		 		   Notification.show("El Registro no se pudo Modificar",Notification.TYPE_TRAY_NOTIFICATION);	
		 		}					    
		    
		   }catch(Exception e){
					e.printStackTrace();
		   }
			  	
    	}else{
 		   try{
 			  System.out.println("Bandera Inico Maodificar 01");
  			AsignarRegistro();
  			System.out.println("Bandera Inico Modificar 02");
 			    String Resultado=((tblHuespedes)ByosFormularioTab01_01.Clase).guardar(ClaseHuespedes);
 				if(Resultado.equals(utilString.SQL_INSERTADO)){
 				   Notification.show("Registro Guardado",Notification.TYPE_TRAY_NOTIFICATION);
 				}else{
 	 			   Notification.show("El Registro no se pudo Guardar",Notification.TYPE_TRAY_NOTIFICATION);	
 	 		    }	
 		   }catch(Exception e){
 				e.printStackTrace();
 		   }
 	    }	  		
      }else{
    	  Notification.show("Algunos Campos Son Obligatorios",Notification.TYPE_TRAY_NOTIFICATION);	
      }
    }
    
    public void procesoEliminar(){
		   ProcesoClass procesoGuardarPassword = new ProcesoClass(Proceso.MARCA_ELIMINAR){			
			  public void procesoDefinicion(){
				try{
					String Resultado=((tblHuespedes)ByosFormularioTab01_01.Clase).eliminar(((tblHuespedes)ByosFormularioTab01_01.Clase));
                    if(Resultado.equals(utilString.SQL_ELIMINADO)){
	  	               if(ByosFormularioTab01_01.ArrayClase!=null){		
	                      if(ByosFormularioTab01_01.Posicion>=0){
	                    	 
	 	      	             ByosFormularioTab01_01.ArrayClase.remove(ByosFormularioTab01_01.Posicion);
	 	      	             ByosFormularioTab01_02.ArrayClase.remove(ByosFormularioTab01_02.Posicion);
	 	      	             ByosFormularioTab01_03.ArrayClase.remove(ByosFormularioTab01_03.Posicion);

	 	      	             ByosFormularioTab02_01.ArrayClase.remove(ByosFormularioTab02_01.Posicion);
	 	      	             ByosFormularioTab02_02.ArrayClase.remove(ByosFormularioTab02_02.Posicion);
	 	      	             ByosFormularioTab02_03.ArrayClase.remove(ByosFormularioTab02_03.Posicion);

	 	      	             ByosFormularioTab03_01.ArrayClase.remove(ByosFormularioTab03_01.Posicion);
	 	      	             ByosFormularioTab03_02.ArrayClase.remove(ByosFormularioTab03_02.Posicion);
	 	      	             ByosFormularioTab03_03.ArrayClase.remove(ByosFormularioTab03_03.Posicion);

	 	      	             ByosFormularioTab04_01.ArrayClase.remove(ByosFormularioTab04_01.Posicion);
	 	      	             ByosFormularioTab04_02.ArrayClase.remove(ByosFormularioTab04_02.Posicion);
	 	      	             ByosFormularioTab04_03.ArrayClase.remove(ByosFormularioTab04_03.Posicion);
	 	      	             
	 	      	             ByosFormularioTab01_01.Posicion--;
	 	      	             ByosFormularioTab01_02.Posicion--;
	 	      	             ByosFormularioTab01_03.Posicion--;

	 	      	             ByosFormularioTab02_01.Posicion--;
	 	      	             ByosFormularioTab02_02.Posicion--;
	 	      	             ByosFormularioTab02_03.Posicion--;
	 	      	             
	 	      	             ByosFormularioTab03_01.Posicion--;
	 	      	             ByosFormularioTab03_02.Posicion--;
	 	      	             ByosFormularioTab03_03.Posicion--;

	 	      	             ByosFormularioTab04_01.Posicion--;
	 	      	             ByosFormularioTab04_02.Posicion--;
	 	      	             ByosFormularioTab04_03.Posicion--;
	 	      	             
	 	      	             if(ByosFormularioTab01_01.Posicion>=0){
	 	      	               ((tblHuespedes)ByosFormularioTab01_01.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab01_01.ArrayClase.get(ByosFormularioTab01_01.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab01_02.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab01_02.ArrayClase.get(ByosFormularioTab01_02.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab01_03.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab01_03.ArrayClase.get(ByosFormularioTab01_03.Posicion));

	 	      	               ((tblHuespedes)ByosFormularioTab02_01.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab02_01.ArrayClase.get(ByosFormularioTab02_01.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab02_02.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab02_02.ArrayClase.get(ByosFormularioTab02_02.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab02_03.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab02_03.ArrayClase.get(ByosFormularioTab02_03.Posicion));	 	      	             

	 	      	               ((tblHuespedes)ByosFormularioTab03_01.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab03_01.ArrayClase.get(ByosFormularioTab03_01.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab03_02.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab03_02.ArrayClase.get(ByosFormularioTab03_02.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab03_03.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab03_03.ArrayClase.get(ByosFormularioTab03_03.Posicion));	 	      	             

	 	      	               ((tblHuespedes)ByosFormularioTab04_01.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab04_01.ArrayClase.get(ByosFormularioTab04_01.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab04_02.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab04_02.ArrayClase.get(ByosFormularioTab04_02.Posicion));
	 	      	               ((tblHuespedes)ByosFormularioTab04_03.Clase).setTblHuespedes((tblHuespedes) ByosFormularioTab04_03.ArrayClase.get(ByosFormularioTab04_03.Posicion));	 	      	             
	 	      	               
	 	      	             }else{
	 	      	               procesoNuevo(); 
	 	      	             }
                             procesoRefrescarTbl();
	                      }
	                   }                      
	  	               Notification.show("Huespedes Eliminada",Notification.TYPE_TRAY_NOTIFICATION);
                    }else{
                       Notification.show("Esta Huespedes No se Pudo Eliminar",Notification.TYPE_TRAY_NOTIFICATION);	
                    }
				}
				catch(Exception e){
					this.procesoLogException(e);
				}
			  }	
			  public String procesoValidacion(){
				  return "";
			  }
		   };
		   procesoGuardarPassword.procesoEjecucion();                       
    }
    
	public void procesoListar(final boolean AbrirWindows){  
		   final ByosDatagridFiltrableTextbox dwb; 
	       dwb = new ByosDatagridFiltrableTextbox(true,800,640);    
		   tblHuespedes TblListar = new tblHuespedes();
		   ByosColumna[] ByosColumnas = new ByosColumna[3];

		   ByosColumnas[0] = new ByosColumna("apodo", String.class, "Apodo", "", new LikeFilter("apodo",""));
		   ByosColumnas[1] = new ByosColumna("nombres", String.class, "Nombres", "", new LikeFilter("nombres",""));
		   ByosColumnas[2] = new ByosColumna("apellidos", String.class, "Apellidos", "",  new LikeFilter("apellidos",""));

		   
		   
	       try{
	    	   	    	   
	           dwb.getDatagrid().initDatagridByos(TblListar.BuscarArray(new tblHuespedes()), TblListar, ByosColumnas, false);
	  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
	               public void buttonClick(ClickEvent event) {         	
	                  try { 
	            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
	            	        if(AL.size()>0){
	    					   String xCodigo = ((tblHuespedes)AL.get(0)).getNumeroDocumento();
	                           procesoNuevo();
	                           ((tblHuespedes)ByosFormularioTab01_01.Clase).setNumeroDocumento(xCodigo);
	                           ((tblHuespedes)ByosFormularioTab01_02.Clase).setNumeroDocumento(xCodigo);
	                           ((tblHuespedes)ByosFormularioTab01_03.Clase).setNumeroDocumento(xCodigo);
	                           procesoBuscar();
                               procesoRefrescarTbl();
	                           dwb.cerrarWindow();
	                           if(AbrirWindows) {
	                        	   new moduloReserva01(ClaseHuespedes.getNumeroDocumento(),"GV","LLAMADA ENTRANTE DE PERSONA CONOCIDA").openWindows();
	                           }
	            	        }
	      		
	                  }catch (Exception e) {
	                      e.printStackTrace();
	                  }   
	               }
	  	       });
	       }catch (Exception e) {
	            e.printStackTrace();
	       }			        
	}
	
    void ProximoCodigo() throws Exception {
        String xProximoCodigo=ByosSql.getProximoCodigo("Select max(NumeroDocumento) From tblHuespedes Where abs(NumeroDocumento)>0",2);
        ((tblHuespedes)ByosFormularioTab01_01.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab01_02.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab01_03.Clase).setNumeroDocumento(xProximoCodigo);

        ((tblHuespedes)ByosFormularioTab02_01.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab02_02.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab02_03.Clase).setNumeroDocumento(xProximoCodigo);

        ((tblHuespedes)ByosFormularioTab03_01.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab03_02.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab03_03.Clase).setNumeroDocumento(xProximoCodigo);

        ((tblHuespedes)ByosFormularioTab04_01.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab04_02.Clase).setNumeroDocumento(xProximoCodigo);
        ((tblHuespedes)ByosFormularioTab04_03.Clase).setNumeroDocumento(xProximoCodigo);
        
        procesoRefrescarTbl();
    } 
    
    public void closeWindows(){
        subwindow.close();
    }
     
    public void openWindows(){
       if(subwindow.getParent() != null) {
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_TRAY_NOTIFICATION);
       }else {
    	   UI.getCurrent().addWindow(subwindow);                  
       }        
     }
    
    private void btoOP07_Click() {
    	closeWindows();
    }
    
    private void AsignarRegistro() {
    	tblHuespedes ClaseHuespedes01 = new tblHuespedes();
    	
    	ClaseHuespedes.setTblHuespedes((tblHuespedes) ByosFormularioTab01_01.Clase); 
    	ClaseHuespedes01.setTblHuespedes(ClaseHuespedes);
    	
    	/* Tab 01 */
    	ClaseHuespedes.setTipoDocumento(ClaseHuespedes01.getTipoDocumento());
    	ClaseHuespedes.setNumeroDocumento(ClaseHuespedes01.getNumeroDocumento());
    	ClaseHuespedes.setSexo(ClaseHuespedes01.getSexo());
    	ClaseHuespedes.setNivel(ClaseHuespedes01.getNivel());
    	ClaseHuespedes.setVencimientoDoc(ClaseHuespedes01.getVencimientoDoc());
    	ClaseHuespedes.setRecomendado(ClaseHuespedes01.getRecomendado());
    	ClaseHuespedes.setUltimaEstadia(ClaseHuespedes01.getUltimaEstadia());
    	ClaseHuespedes.setUltimaHabitacion(ClaseHuespedes01.getUltimaHabitacion());
    	ClaseHuespedes.setTelefonoMovil(ClaseHuespedes01.getTelefonoMovil());

    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab01_02.Clase); 
    	ClaseHuespedes.setNombres(ClaseHuespedes01.getNombres());
    	ClaseHuespedes.setApellidos(ClaseHuespedes01.getApellidos());
    	ClaseHuespedes.setApodo(ClaseHuespedes01.getApodo());

    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab01_03.Clase); 
    	ClaseHuespedes.setTelefono(ClaseHuespedes01.getTelefono());
    	ClaseHuespedes.setCorreo(ClaseHuespedes01.getCorreo());
    	ClaseHuespedes.setAltura(ClaseHuespedes01.getAltura());
    	ClaseHuespedes.setColorPiel(ClaseHuespedes01.getColorPiel());
    	ClaseHuespedes.setColorOjos(ClaseHuespedes01.getColorOjos());
    	ClaseHuespedes.setColorCabello(ClaseHuespedes01.getColorCabello());
    	ClaseHuespedes.setMedidas(ClaseHuespedes01.getMedidas());
    	ClaseHuespedes.setAmistad(ClaseHuespedes01.getAmistad());
    	ClaseHuespedes.setUltimaEstadiaAmistad(ClaseHuespedes01.getUltimaEstadiaAmistad());

    	/* Tab 02 */
    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab02_01.Clase); 
    	ClaseHuespedes.setFechaNacimiento(ClaseHuespedes01.getFechaNacimiento());
    	ClaseHuespedes.setEstadoCivil(ClaseHuespedes01.getEstadoCivil());
    	ClaseHuespedes.setDireccion(ClaseHuespedes01.getDireccion());
    	ClaseHuespedes.setCodigoPostal(ClaseHuespedes01.getCodigoPostal());
        
    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab02_03.Clase); 
    	ClaseHuespedes.setCiudad(ClaseHuespedes01.getCiudad());
    	ClaseHuespedes.setProvincia(ClaseHuespedes01.getProvincia());
    	ClaseHuespedes.setCorreo(ClaseHuespedes01.getCorreo());
    	ClaseHuespedes.setCodigoTarjeta(ClaseHuespedes01.getCodigoTarjeta());

    	/* Tab 03 */
    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab03_01.Clase); 
    	ClaseHuespedes.setCiudadOrigen(ClaseHuespedes01.getCiudadOrigen());
    	ClaseHuespedes.setDireccionOrigen(ClaseHuespedes01.getDireccionOrigen());
    	ClaseHuespedes.setCodigoPostalOrigen(ClaseHuespedes01.getCodigoPostalOrigen());
    	
    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab03_03.Clase); 
    	ClaseHuespedes.setTelefonoOrigen(ClaseHuespedes01.getTelefonoOrigen());
    	ClaseHuespedes.setAgenciaOrigen(ClaseHuespedes01.getAgenciaOrigen());

    	/* Tab 04 */
    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab03_01.Clase); 
    	ClaseHuespedes.setCiudadOrigen(ClaseHuespedes01.getCiudadOrigen());
    	ClaseHuespedes.setDireccionOrigen(ClaseHuespedes01.getDireccionOrigen());
    	ClaseHuespedes.setCodigoPostalOrigen(ClaseHuespedes01.getCodigoPostalOrigen());
    	
    	ClaseHuespedes01.setTblHuespedes((tblHuespedes) ByosFormularioTab03_03.Clase); 
    	ClaseHuespedes.setTelefonoOrigen(ClaseHuespedes01.getTelefonoOrigen());
    	ClaseHuespedes.setAgenciaOrigen(ClaseHuespedes01.getAgenciaOrigen());
    	
    }

}