package com.modulo.producto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.modulo.codigobarra.moduloCodigoBarra;
import com.modulo.componentes.ByosContenedores;
import com.modulo.departamento.tblDepartamento;
import com.modulo.deposito.tblDeposito;
import com.modulo.gruposproducto.tblGruposProducto;
import com.modulo.kardexdiario.tblKardexDiario;
import com.modulo.marca.tblMarca;
import com.modulo.precioproducto.moduloPrecios;
import com.modulo.precioproducto.tblPrecioProducto;
import com.modulo.productodeposito.moduloAjustedepositos;
import com.modulo.productodeposito.tblProductoDeposito;
import com.modulo.productomedida.moduloProductoMedida;
import com.modulo.subgruposproducto.tblSubGruposProducto;
import com.modulo.sustanciascontrolada.tblSustanciasControladas;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.modulo.historicoprecios.tblHistoricoPrecios;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.filters.LikeFilter;
import com.modulo.componentes.ByosBoton;
import com.modulo.componentes.ByosCampo;
import com.modulo.componentes.ByosForm;
import com.modulo.componentes.ByosMsgBox;
import com.modulo.componentes.ByosVerticalLayout;
import com.modulo.componentes.ByosMenu;
import com.modulo.componentes.moduloLeerCodigo;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.ui.ByosDatagrid.ByosColumna;
import com.byos.sys.ui.ByosDatagrid.ByosDatagridFiltrableTextbox;
import com.byos.sys.util.ByosSql;
import com.byos.sys.util.utilString;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import java.io.Serializable;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Chistian
 * @version
 */
public class moduloProducto extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    ByosForm ByosFromProducto = new ByosForm();
    ByosVerticalLayout Main = new ByosVerticalLayout("Mantenimiento de Inventario","100%");
    VerticalLayout ContenedorMenu = new VerticalLayout();
    
    moduloProductoDetalle ModuloProductoDetalle;
    
    public tblProducto ClaseProducto;
    tblPrecioProducto[] TblPrecioProductoOriginal;
    boolean sw=true;

    public ByosMenu menu = new ByosMenu();
    
    
    String DatosRequeridos[] = {
            "codigoproducto", 
            "descripcioncorta", 
            "estado", 
            "codigodeposito", 
            "codigodepartamento", 
            "codigomedida",
            "costo", 
            "regulado"
            };    
    
    String DatosVisibles[] = {
                    "codigoproducto", 
                    "descripcioncorta", 
                    "descripcionlarga",
                    "estado", 
                    "codigogrupo",
                    "codigosubgrupo", 
                    "codigodeposito", 
                    "codigodepartamento", 
                    "codigomarca", 
                    "codigomedida",
                    "existencia", 
                    "existenciamaxima", 
                    "existenciaminima",
                    "costo", 
                    "costoanterior", 
                    "costopromedio",                    
                    "utilidadminima", 
                    "utilidadmaxima",
                    "temporadamaximoconsumo", 
                    "incrementotemporada", 
                    "gradoalcohol", 
                    "regulado", 
                    "detallerequerido", 
                    "indicaciones", 
                    "contraindicaciones", 
                    "ubicacion", 
                    "codigoproveedor", 
                    "codigocontrol", 
                    "productocompuesto"};

    String DatosTitulos[] = {
                    "Codigo Producto", 
                    "Descripcion Corta", 
                    "Descripcion Larga", 
                    "Estado",
                    "Codigo Grupo",
                    "Codigo Subgrupo", 
                    "Codigo Deposito", 
                    "Codigo Departemanto", 
                    "Codigo Marca", 
                    "Unid. Medida Minima",
                    "Existencia", 
                    "Existencia Maxima", 
                    "Existencia Minima",  
                    "Costo", 
                    "Costo Anterior", 
                    "Costo Promedio",                    
                    "Utilidad Minima", 
                    "Utilidad Maxima",
                    "Temporada Max Consumo", 
                    "Incremento Temporada", 
                    "Grado Alcohol", 
                    "Regulado", 
                    "Detalle Requerido", 
                    "Indicaciones", 
                    "Contra Indicaciones", 
                    "Ubicacion", 
                    "Codigo Proveedor", 
                    "Codigo Control", 
                    "Producto Compuesto"};
   
    

    ByosBoton BtoNuevo = new ByosBoton(menu.MenuPrincipal, 0,"Nuevo","");
    ByosBoton BtoBuscar = new ByosBoton(menu.MenuPrincipal, 3,"Buscar","");
    ByosBoton BtoEliminar = new ByosBoton(menu.MenuPrincipal, 2,"Desincorporar","");
    ByosBoton BtoGuardar = new ByosBoton(menu.MenuPrincipal, 1,"Guardar","");
    ByosBoton BtoAnterior = new ByosBoton(menu.MenuPrincipal, 14,"Registro Anterior","");
    ByosBoton BtoProximo = new ByosBoton(menu.MenuPrincipal, 15,"Proximo Registro","");
    
    //ByosBoton BtoAutoCodigo = new ByosBoton(menu.MenuAdicional, 18,"Generar Auto Codigo Interno","");
    ByosBoton BtoBarras = new ByosBoton(menu.MenuAdicional, 17,"Codigos de Barras","");
    ByosBoton BtoBuscarBarras = new ByosBoton(menu.MenuAdicional, 19,"Buscar Por Codigo de Barra","");
    ByosBoton BtoAjusteDeposito = new ByosBoton(menu.MenuAdicional, 27,"Ajuste por Deposito","");
    ByosBoton BtoReporte = new ByosBoton(menu.MenuAdicional, 21,"Reportes","");
    
    
    public moduloProducto() {
    	setSizeFull();
    	setSpacing(true);
    	setMargin(true);
    	//setStyleName("v-verticallayout-main");
    	
    	menu.setWidth("100%");
    	ByosFromProducto.setWidth("100%");
    	ContenedorMenu.setMargin(true);
    	ContenedorMenu.addComponent(menu);
    	ContenedorMenu.addComponent(ByosFromProducto);
    	
    	//ByosFromProducto.addComponent(menu);
        ClaseProducto = new tblProducto(); 
        ByosFromProducto.setLargoDefault(30, "px");
        ByosFromProducto.setClase(ClaseProducto, DatosVisibles, DatosTitulos, null);
        ModuloProductoDetalle = new moduloProductoDetalle(ClaseProducto);
        initComponentes();
       
        ModuloProductoDetalle.setWidth("100%");
        Main.setMargin(false);
        Main.setWidth("100%");
        Main.Contenido.addComponent(ContenedorMenu); 
        
        addComponent(Main);
        addComponent(ModuloProductoDetalle);
    }
    
    @SuppressWarnings({ "deprecation", "serial" })
	public void initComponentes() {

        /*BtoAutoCodigo.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    ProximoCodigo();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        }); */
        

        BtoBarras.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoCodigoBarra();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });        
        

        BtoBuscarBarras.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoLeerCodigoBarra();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
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
                     procesoDesincorporar();
                     
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
        BtoAjusteDeposito.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                	procesoAjusteDeposito();    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });        
        BtoReporte.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
            	
                try {
                    JasperReport jasperReport;
                    JasperPrint jasperPrint;
                    
                    //1-Compilamos el archivo XML y lo cargamos en memoria
                    String Base= VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
                    //System.out.println(Base);
                    jasperReport = JasperCompileManager.compileReport("c:\\Inventario.jrxml");
                    Connection con = Conexion.getNuevaConexion();
                    //2-Llenamos el reporte con la informaci�n y par�metros necesarios (En este caso nada)
                    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), con);

                    //3-Exportamos el reporte a pdf y lo guardamos en disco
                    //JasperExportManager.exportReportToPdfFile(jasperPrint, "c:\\holaMundo.pdf");
                    JasperViewer.viewReport(jasperPrint, false);
                    ByosSql.CloseAll(con,null,null);
                
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); 
        
        
        ByosFromProducto.getCampo("descripcioncorta").txtTexto.setWidth("300px");
        ByosFromProducto.getCampo("descripcionlarga").txtTextArea.setWidth("300px");
        
        ByosFromProducto.getCampo("indicaciones").txtTexto.setWidth("300px");
        ByosFromProducto.getCampo("contraindicaciones").txtTexto.setWidth("300px");
        
        ByosFromProducto.getCampo("descripcionlarga").setTipoCampo(ByosCampo.CAMPO_TEXTAREA);
        
        ByosFromProducto.setTipoCampo("estado", "ComboBox");
        ByosFromProducto.getCampo("estado").CboxItem.setContainerDataSource(ByosContenedores.getContainerActivo());
        
        ByosFromProducto.setTipoCampo("regulado", "ComboBox");
        ByosFromProducto.getCampo("regulado").CboxItem.setContainerDataSource(ByosContenedores.getContainerSiNo());             

        ByosFromProducto.setTipoCampo("productocompuesto", "ComboBox");             
        ByosFromProducto.getCampo("productocompuesto").CboxItem.setContainerDataSource(ByosContenedores.getContainerSiNo());        
        
        ByosFromProducto.setTipoCampo("detallerequerido", "ComboBox");             
        ByosFromProducto.getCampo("detallerequerido").CboxItem.setContainerDataSource(ByosContenedores.getContainerSiNo());        

        ByosFromProducto.setTipoCampo("gradoalcohol", "NumberField");
        ByosFromProducto.getCampo("incrementotemporada").setTipoCampo("NumberField");        
        ByosFromProducto.getCampo("utilidadminima").setTipoCampo("NumberField");  
        ByosFromProducto.getCampo("utilidadmaxima").setTipoCampo("NumberField");
        
        ByosFromProducto.setTipoCampo("existencia", "NumberField");

        ByosFromProducto.getCampo("existencia").txtNumerico.setReadOnly(true);
        ByosFromProducto.getCampo("existencia").setDecimales(3);
        
        
        
        ByosFromProducto.setTipoCampo("existenciamaxima", "NumberField");
        ByosFromProducto.getCampo("existenciamaxima").setDecimales(3);
        
        ByosFromProducto.setTipoCampo("existenciaminima", "NumberField");
        ByosFromProducto.getCampo("existenciaminima").setDecimales(3);

       

        ByosFromProducto.setTipoCampo("costo", "NumberField");
        ByosFromProducto.getCampo("costo").txtNumerico.setReadOnly(true);
        ByosFromProducto.getCampo("costo").setDecimales(3);
        ByosFromProducto.getCampo("costo").btoBoton1.setBoton(48, "Precios","");
        ByosFromProducto.getCampo("costo").btoBoton1.setVisible(true);
        ByosFromProducto.getCampo("costo").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	
                   try {
                	   
                       procesoPrecios();   
                       
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                
            }
  
        });        
        
        ByosFromProducto.setTipoCampo("costoanterior", "NumberField");
        ByosFromProducto.setTipoCampo("costopromedio", "NumberField");
        
        
        ByosFromProducto.getCampo("codigoproducto").btoBoton1.setBoton(49, "Listar","");
        ByosFromProducto.getCampo("codigoproducto").btoBoton1.setVisible(true);
        ByosFromProducto.getCampo("codigoproducto").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	
                   try {
                	   
                       procesoListarProducto();   
                       
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                
            }
  
        });
        
        ByosFromProducto.getCampo("codigoproducto").btoBoton2.setVisible(true);
        ByosFromProducto.getCampo("codigoproducto").btoBoton2.setBoton(50, "Auto Codigo","");
        ByosFromProducto.getCampo("codigoproducto").btoBoton2.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    ProximoCodigo();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
        
        /*getCampo("codigoproducto").btoBoton3.setVisible(true);
        getCampo("codigoproducto").btoBoton3.setBoton(17, "Barras","");
        getCampo("codigoproducto").btoBoton3.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoCodigoBarra();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });
        
        getCampo("codigoproducto").btoBoton4.setVisible(true);
        getCampo("codigoproducto").btoBoton4.setBoton(19, "Buscar Barra","");
        getCampo("codigoproducto").btoBoton4.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoLeerCodigoBarra();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });*/
         
        ByosFromProducto.getCampo("codigodeposito").btoBoton1.setBoton(49, "Listar", "");
        ByosFromProducto.getCampo("codigodeposito").btoBoton1.setVisible(true);
        ByosFromProducto.setisLink("codigodeposito", true, "TblDeposito", "descripcion");
        ByosFromProducto.getCampo("codigodeposito").setSelectLink(true, "select descripcion from tbldepositos where codigodeposito=?");
        ByosFromProducto.getCampo("codigodeposito").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                 try {
                    procesoListarDeposito();                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });        
        
        ByosFromProducto.getCampo("codigodepartamento").btoBoton1.setBoton(49, "Listar", "");
        ByosFromProducto.getCampo("codigodepartamento").btoBoton1.setVisible(true);
        ByosFromProducto.setisLink("codigodepartamento", true, "TblDepartamento", "descripcion");
        ByosFromProducto.getCampo("codigodepartamento").setSelectLink(true, "select descripcion from tbldepartamentos where codigodepartamento=?");
        ByosFromProducto.getCampo("codigodepartamento").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoListarDepartamento();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });        
        
        ByosFromProducto.getCampo("codigomarca").btoBoton1.setBoton(49, "Listar","");
        ByosFromProducto.getCampo("codigomarca").btoBoton1.setVisible(true);
        ByosFromProducto.setisLink("codigomarca", true, "TblMarca", "descripcion");
        ByosFromProducto.getCampo("codigomarca").setSelectLink(true, "select descripcion from tblmarcas where codigomarca=?");
        ByosFromProducto.getCampo("codigomarca").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoListarMarcas();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });        
 
        ByosFromProducto.getCampo("codigomedida").btoBoton1.setBoton(49, "Listar","");
        ByosFromProducto.getCampo("codigomedida").btoBoton1.setVisible(true);
        ByosFromProducto.setisLink("codigomedida", true, "TblUnidadMedida", "descripcion");
        ByosFromProducto.getCampo("codigomedida").setSelectLink(true, "select descripcion from tblunidadmedida where codigomedida=?");
        ByosFromProducto.getCampo("codigomedida").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoListarMedidas();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });        
        ByosFromProducto.getCampo("codigomedida").btoBoton2.setBoton(48, "Agregar","");
        ByosFromProducto.getCampo("codigomedida").btoBoton2.setVisible(true);
        ByosFromProducto.getCampo("codigomedida").btoBoton2.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    
                    procesoMedidas();
                    
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });         
        
        
        ByosFromProducto.getCampo("codigogrupo").btoBoton1.setBoton(49, "Listar","");
        ByosFromProducto.getCampo("codigogrupo").btoBoton1.setVisible(true);
        ByosFromProducto.setisLink("codigogrupo", true, "TblGruposProducto", "descripcion");
        ByosFromProducto.getCampo("codigogrupo").setSelectLink(true, "select descripcion from tblGruposProductos where codigogrupo=?");
        ByosFromProducto.getCampo("codigogrupo").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoListarGurpo();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });          

        ByosFromProducto.getCampo("codigosubgrupo").btoBoton1.setBoton(49, "Listar","");
        ByosFromProducto.getCampo("codigosubgrupo").btoBoton1.setVisible(true);
        ByosFromProducto.setisLink("codigosubgrupo", true, "TblSubGruposProducto", "descripcion");
        ByosFromProducto.getCampo("codigosubgrupo").setSelectLink(true, "select descripcion from tblsubGruposProductos where codigosubgrupo=? and codigogrupo='" + ByosFromProducto.getCampo("codigogrupo").getValor() + "'");
        ByosFromProducto.getCampo("codigosubgrupo").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    procesoListarSubgurpo();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });  
        
        ByosFromProducto.getCampo("codigocontrol").btoBoton1.setBoton(49, "Listar","");
        ByosFromProducto.getCampo("codigocontrol").btoBoton1.setVisible(true);
        ByosFromProducto.setisLink("codigocontrol", true, "TblSustanciasControlada", "descripcion");
        ByosFromProducto.getCampo("codigocontrol").setSelectLink(true, "select descripcion from tblSustanciasControladas where codigocontrol=?");
        ByosFromProducto.getCampo("codigocontrol").btoBoton1.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                	procesoListarSustenciasControlada();
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });         
    }
    
    public void asignacion(){
    	try{
			((tblProducto)ByosFromProducto.Clase).buscarCodigo(((tblProducto)ByosFromProducto.Clase).getCodigoproducto());
			ModuloProductoDetalle.setTblProducto((tblProducto)ByosFromProducto.Clase);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void procesoTblProximo(){
    	ByosFromProducto.procesoProximo();
    	asignacion();
    	ByosFromProducto.refrescar();      	
    }
    
    public void procesoTblAnterior(){
    	ByosFromProducto.procesoAnterior();
    	asignacion();
    	ByosFromProducto.refrescar();      	
    }    
    
    public void procesoBuscar(String CodigoBarra) throws Exception{
    	ByosFromProducto.setArrayClase(((tblProducto)ByosFromProducto.Clase).BuscarBarras(CodigoBarra));  
        if (ByosFromProducto.ArrayClase == null){
            ((tblProducto)ByosFromProducto.Clase).limpiar();
               
        }else{
            asignacion();
            ByosFromProducto.refrescar();
        }
    }    
    
	public void procesoBuscar() throws Exception {	
		ByosFromProducto.setArrayClase(((tblProducto)ByosFromProducto.Clase).Buscar(((tblProducto)ByosFromProducto.Clase)));  
        if (ByosFromProducto.ArrayClase == null || ByosFromProducto.ArrayClase.size()==0){
            ((tblProducto)ByosFromProducto.Clase).limpiar();
            ByosFromProducto.refrescar();
            Notification.show("Buscar","No se encontro registros con estas caracteristicas",Notification.TYPE_TRAY_NOTIFICATION);           
            
        }else{
        	asignacion();      	
        }
        ByosFromProducto.refrescar();
    }
    
    
    public void procesoNuevo(){
        ((tblProducto)ByosFromProducto.Clase).limpiar();
        ByosFromProducto.refrescar();
        ByosFromProducto.setArrayClase(null);
        asignacion();
        
    }
    
    public void procesoGuardar(){
      boolean Existe=false;
      if(ByosFromProducto.DatosRequeridos(DatosRequeridos)){
    	try{
			Existe=((tblProducto)ByosFromProducto.Clase).existeCodigo(((tblProducto)ByosFromProducto.Clase).getCodigoproducto());
		}catch (Exception e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
		}
    	
    	if(Existe){

					new tblHistoricoPrecios().guardar(((tblProducto)ByosFromProducto.Clase));
					String Resultado=((tblProducto)ByosFromProducto.Clase).guardar(((tblProducto)ByosFromProducto.Clase));
                    if(Resultado.equals(utilString.SQL_MODIFICADO)){                      
	  	               if(ByosFromProducto.ArrayClase!=null){		
	                      if(ByosFromProducto.Posicion>=0){
	 	      	             ((tblProducto) ByosFromProducto.ArrayClase.get(ByosFromProducto.Posicion)).setProducto(((tblProducto)ByosFromProducto.Clase));
	 	      	             ByosFromProducto.refrescar();
	                      }
	                   }                      
	  	               Notification.show("Registro Modificado", Notification.TYPE_TRAY_NOTIFICATION);
	                }else{
		 			   Notification.show("El Registro no se pudo Modificar",Notification.TYPE_TRAY_NOTIFICATION);	
		 		    }					    

    	}else{

 					new tblHistoricoPrecios().guardar(((tblProducto)ByosFromProducto.Clase));
 					String Resultado=((tblProducto)ByosFromProducto.Clase).guardar(((tblProducto)ByosFromProducto.Clase));
 				    if(Resultado.equals(utilString.SQL_INSERTADO)){
 					   Notification.show("Registro Guardado",Notification.TYPE_TRAY_NOTIFICATION);
 				    }else{
 	 				   Notification.show("El Registro no se pudo Guardar",Notification.TYPE_TRAY_NOTIFICATION);	
 	 				}	
   		
    	}
      }
    }
    
    public void procesoDesincorporar(){

					String Resultado=((tblProducto)ByosFromProducto.Clase).desincorporar(((tblProducto)ByosFromProducto.Clase).getCodigoproducto());
                    if(Resultado.equals(utilString.SQL_DESINCORPORAR)){
	  	               if(ByosFromProducto.ArrayClase!=null){		
	                      if(ByosFromProducto.Posicion>=0){
	 	      	             ((tblProducto) ByosFromProducto.ArrayClase.get(ByosFromProducto.Posicion)).setProducto(((tblProducto)ByosFromProducto.Clase));
	 	      	             ByosFromProducto.refrescar();
	                      }
	                   }                      
	  	               Notification.show("Registro Desincorporado o Inactivo",Notification.TYPE_TRAY_NOTIFICATION);
	                }else{
		 			   Notification.show("El Registro no se pudo Desincorporar",Notification.TYPE_TRAY_NOTIFICATION);	
		 			}					    
                      
    }
    
    void ProximoCodigo() throws Exception {
        String xProximoCodigo=((tblProducto)ByosFromProducto.Clase).getProximoCodigo();
        ((tblProducto)ByosFromProducto.Clase).setCodigoproducto(xProximoCodigo);
        ByosFromProducto.refrescar(); 
    }   
    
    
	public void procesoListarProducto(){  
	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
	   tblProducto TblProductosListar = new tblProducto();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigoproducto",   String.class, "Codigo",  "", new LikeFilter("codigoproducto",""));
	   ByosColumnas[1] = new ByosColumna("descripcioncorta", String.class, "Descripicon", "", new LikeFilter("descripcioncorta",""));

       try{
           dwb.getDatagrid().initDatagridByos(TblProductosListar.Buscar(new tblProducto()), TblProductosListar, ByosColumnas, false);
  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
               public void buttonClick(ClickEvent event) {         	
                  try { 
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL.size()>0){
    					   String xCodigo = ((tblProducto)AL.get(0)).getCodigoproducto();
                           procesoNuevo();
                           ((tblProducto)ByosFromProducto.Clase).setCodigoproducto(xCodigo);
                           procesoBuscar();
                           ByosFromProducto.refrescar();  
                           dwb.cerrarWindow();
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
    
    @SuppressWarnings({ "deprecation", "serial" })
	public void procesoLeerCodigoBarra(){      
        final moduloLeerCodigo frmCodigoBarra = new  moduloLeerCodigo();
        frmCodigoBarra.Codigos.txtTexto.setCaption("Codigo de Barra");
        frmCodigoBarra.btoAceptar.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
               String CodigoBarra=frmCodigoBarra.Codigos.txtTexto.getValue().toString(); 
               if(CodigoBarra!=null && !CodigoBarra.equals("")){
                  try {
					procesoBuscar(CodigoBarra);
				  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
               }
               frmCodigoBarra.closeWindows();
            }
        });
        frmCodigoBarra.btoCancelar.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {                  
               frmCodigoBarra.closeWindows(); 
            }
        });
        frmCodigoBarra.openWindows();
    }    
    

    @SuppressWarnings("deprecation")
	public void procesoCodigoBarra(){
        if(((tblProducto)ByosFromProducto.Clase).getCodigoproducto()!=null && !((tblProducto)ByosFromProducto.Clase).getCodigoproducto().equals("")){
           final moduloCodigoBarra CodigoBarra = new  moduloCodigoBarra(((tblProducto)ByosFromProducto.Clase));	
           CodigoBarra.btoAceptar.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                  if(CodigoBarra.ValidarCodigoBarra()){
                     ((tblProducto)ByosFromProducto.Clase).TblCodigoBarras=CodigoBarra.getTblCodigoBarras();
                     CodigoBarra.closeWindows(); 
                  } 
                }
           });
           CodigoBarra.btoCancelar.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {                  
                	CodigoBarra.closeWindows(); 
                }
           });
           CodigoBarra.openWindows();
        }else{
           Notification.show("No a elejido ni asignado ningun codigo de producto" , Notification.TYPE_TRAY_NOTIFICATION);
        }
       
    }    
    
    @SuppressWarnings("deprecation")
	public void procesoListarGurpo(){
 	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
	   tblGruposProducto TblGruposProductosListar = new tblGruposProducto();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigogrupo",String.class,"Codigo","",new LikeFilter("codigogrupo",""));
	   ByosColumnas[1] = new ByosColumna("descripcion",String.class,"Descripicon","",new LikeFilter("descripcion",""));

       try{
           dwb.getDatagrid().initDatagridByos(TblGruposProductosListar.Buscar(new tblGruposProducto()), TblGruposProductosListar, ByosColumnas, false);
  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
               public void buttonClick(ClickEvent event) {         	
                  try { 
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL.size()>0){
    					   String xCodigo = ((tblGruposProducto)AL.get(0)).getCodigogrupo();
                           ((tblProducto)ByosFromProducto.Clase).setCodigogrupo(xCodigo);
                           ((tblProducto)ByosFromProducto.Clase).TblGruposProducto.buscarCodigo(xCodigo);
                           ((tblProducto)ByosFromProducto.Clase).buscarCodigo(((tblProducto)ByosFromProducto.Clase).getCodigoproducto());
                           ByosFromProducto.refrescar();  
                           dwb.cerrarWindow();
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
    
    @SuppressWarnings("deprecation")
	public void procesoListarSubgurpo(){
  	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,440,200);    
	   tblSubGruposProducto TblSubGruposProductosListar = new tblSubGruposProducto();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigosubgrupo",String.class,"Codigo","",new LikeFilter("codigosubgrupo",""));
	   ByosColumnas[1] = new ByosColumna("descripcion",String.class,"Descripicon","",new LikeFilter("descripcion",""));

       try{
           dwb.getDatagrid().initDatagridByos(TblSubGruposProductosListar.Buscar(new tblSubGruposProducto()), TblSubGruposProductosListar, ByosColumnas, false);
   	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
               public void buttonClick(ClickEvent event) {         	
                  try { 
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL.size()>0){
    					   String xCodigo = ((tblSubGruposProducto)AL.get(0)).getCodigogrupo();
                           ((tblProducto)ByosFromProducto.Clase).setCodigosubgrupo(xCodigo);
                           ((tblProducto)ByosFromProducto.Clase).TblSubGruposProducto.buscarCodigo(((tblProducto)ByosFromProducto.Clase).getCodigogrupo(), xCodigo);
                           ByosFromProducto.refrescar();  
                           dwb.cerrarWindow();
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

    @SuppressWarnings("deprecation")
	public void procesoListarDeposito(){
  	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,440,200);   
	   tblDeposito TblDepositosListar = new tblDeposito();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigodeposito", String.class,"Codigo","", new LikeFilter("codigodeposito", ""));
	   ByosColumnas[1] = new ByosColumna("descripcion",    String.class,"Descripicon","", new LikeFilter("descripcion",    ""));

       try{
           dwb.getDatagrid().initDatagridByos(TblDepositosListar.Buscar(new tblDeposito()), TblDepositosListar, ByosColumnas, false);
  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
               public void buttonClick(ClickEvent event) {         	
                  try { 
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL.size()>0){
    					   String xCodigo = ((tblDeposito)AL.get(0)).getCodigodeposito();
                           ((tblProducto)ByosFromProducto.Clase).setCodigodeposito(xCodigo);
                           ((tblProducto)ByosFromProducto.Clase).TblDeposito.buscarCodigo(xCodigo);
                           ByosFromProducto.refrescar();  
                           dwb.cerrarWindow();
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

    @SuppressWarnings("deprecation")
	public void procesoListarDepartamento(){
   	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,440,200);     
	   tblDepartamento TblDepartamentosListar = new tblDepartamento();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigodepartamento", String.class, "Codigo", "", new LikeFilter("codigodepartamento",""));
	   ByosColumnas[1] = new ByosColumna("descripcion",        String.class, "Descripicon", "", new LikeFilter("descripcion",       ""));

       try{
           dwb.getDatagrid().initDatagridByos(TblDepartamentosListar.Buscar(new tblDepartamento()), TblDepartamentosListar, ByosColumnas, false);
 	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
               public void buttonClick(ClickEvent event) {         	
                  try { 
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL.size()>0){
    					   String xCodigo = ((tblDepartamento)AL.get(0)).getCodigodepartamento();
                           ((tblProducto)ByosFromProducto.Clase).setCodigodepartamento(xCodigo);
                           ((tblProducto)ByosFromProducto.Clase).TblDepartamento.buscarCodigo(xCodigo);
                           ByosFromProducto.refrescar();  
                           dwb.cerrarWindow();
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
 
    @SuppressWarnings("deprecation")
	public void procesoListarMarcas(){
    	   final ByosDatagridFiltrableTextbox dwb; 
           dwb = new ByosDatagridFiltrableTextbox(true,440,200);   
    	   tblMarca TblMarcasListar = new tblMarca();
    	   ByosColumna[] ByosColumnas = new ByosColumna[2];

    	   ByosColumnas[0] = new ByosColumna("codigomarca", String.class, "Codigo", "", new LikeFilter("codigomarca", ""));
    	   ByosColumnas[1] = new ByosColumna("descripcion", String.class, "Descripicon", "", new LikeFilter("descripcion", ""));

           try{
               dwb.getDatagrid().initDatagridByos(TblMarcasListar.Buscar(new tblMarca()), TblMarcasListar, ByosColumnas, false);
      	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
                   public void buttonClick(ClickEvent event) {         	
                      try { 
                	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
                	        if(AL.size()>0){
        					   String xCodigo = ((tblMarca)AL.get(0)).getCodigomarca();
                               ((tblProducto)ByosFromProducto.Clase).setCodigomarca(xCodigo);
                               ((tblProducto)ByosFromProducto.Clase).TblMarca.buscarCodigo(xCodigo);
                               ByosFromProducto.refrescar();  
                               dwb.cerrarWindow();
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
    
    @SuppressWarnings("deprecation")
	public void procesoListarMedidas(){
 	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,440,200);
	   tblUnidadMedida TblUnidadMedidasListar = new tblUnidadMedida();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigomedida", String.class, "Codigo", "", new LikeFilter("codigomedida", ""));
	   ByosColumnas[1] = new ByosColumna("descripcion",  String.class, "Descripicon", "", new LikeFilter("descripcion",  ""));

       try{
           dwb.getDatagrid().initDatagridByos(TblUnidadMedidasListar.Buscar(new tblUnidadMedida()), TblUnidadMedidasListar, ByosColumnas, false);
  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
               public void buttonClick(ClickEvent event) {         	
                  try { 
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL.size()>0){
    					   String xCodigo = ((tblUnidadMedida)AL.get(0)).getCodigomedida();
                           ((tblProducto)ByosFromProducto.Clase).setCodigomedida(xCodigo);
                           ((tblProducto)ByosFromProducto.Clase).TblUnidadMedida.buscarCodigo(xCodigo);
                           ByosFromProducto.refrescar();  
                           dwb.cerrarWindow();
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
    
    @SuppressWarnings("deprecation")
	public void procesoMedidas(){
    	final moduloProductoMedida TblProductosMedidas = new moduloProductoMedida(((tblProducto)ByosFromProducto.Clase));
    	TblProductosMedidas.btoAceptar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try{
                	if(TblProductosMedidas.ValidarProductoUnidadMedida()){
                       
					   String xCodigo = TblProductosMedidas.bcCodigoMedida.lblNombreCampo.getValue();
                       ((tblProducto)ByosFromProducto.Clase).setCodigomedida(xCodigo);
                       ((tblProducto)ByosFromProducto.Clase).TblUnidadMedida.buscarCodigo(xCodigo);
                       
                       ByosFromProducto.refrescar();    
                       ((tblProducto)ByosFromProducto.Clase).TblProductoMedidas = TblProductosMedidas.getProductoMedida();
                       ((tblProducto)ByosFromProducto.Clase).TblProductoMedidasDefault = TblProductosMedidas.getProductoMedidaDefault();
                       TblProductosMedidas.closeWindows();  
                	}
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        }); 
    	
    	TblProductosMedidas.btoCancelar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try{
                	if(TblProductosMedidas.ValidarProductoUnidadMedida()){
                	   ByosFromProducto.refrescar(); 
                       TblProductosMedidas.closeWindows();  
                	}
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });  
    	
    	
    	TblProductosMedidas.openWindows();
    }
    
    @SuppressWarnings("deprecation")
	public void procesoListarSustenciasControlada(){
  	   final ByosDatagridFiltrableTextbox dwb; 
       dwb = new ByosDatagridFiltrableTextbox(true,440,200);     
	   tblSustanciasControladas TblSustanciasControladasListar = new tblSustanciasControladas();
	   ByosColumna[] ByosColumnas = new ByosColumna[2];

	   ByosColumnas[0] = new ByosColumna("codigocontrol", String.class, "Codigo", "", new LikeFilter("codigocontrol", ""));
	   ByosColumnas[1] = new ByosColumna("descripcion",   String.class, "Descripicon", "", new LikeFilter("descripcion",   ""));

       try{
           dwb.getDatagrid().initDatagridByos(TblSustanciasControladasListar.Buscar(new tblSustanciasControladas()), TblSustanciasControladasListar, ByosColumnas, false);
  	       dwb.botProcesar.addClickListener(new Button.ClickListener() {
               public void buttonClick(ClickEvent event) {         	
                  try { 
            	        ArrayList AL = dwb.getDatagrid().getFilasSeleccionadas();
            	        if(AL.size()>0){
    					   String xCodigo = ((tblSustanciasControladas)AL.get(0)).getCodigocontrol();
                           ((tblProducto)ByosFromProducto.Clase).setCodigocontrol(xCodigo);
                           ((tblProducto)ByosFromProducto.Clase).TblSustanciasControlada.buscarCodigo(xCodigo);
                           ByosFromProducto.refrescar();  
                           dwb.cerrarWindow();
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
    
    @SuppressWarnings("deprecation")
	public void procesoPrecios(){
    	final moduloPrecios Precios = new moduloPrecios(((tblProducto)ByosFromProducto.Clase));
    	//tblPrecioProducto[] TblRespaldoPrecios = ((tblProducto)Clase).TblPrecioProductos.clone();
    	Precios.btoAceptar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try{
                	if(Precios.ValidarPrecioProducto()){
                	   
                       tblPrecioProducto[] Tbl = Precios.getTblPrecioPorducto();
                       ((tblProducto)ByosFromProducto.Clase).setCosto(Precios.getCosto());
                       ByosFromProducto.refrescar();  
                	   ((tblProducto)ByosFromProducto.Clase).TblPrecioProductos = Tbl;
                	   ((tblProducto)ByosFromProducto.Clase).TblProductoImpuestos = Precios.gettblProductoImpuesto();
                	   
                       Precios.closeWindows();  
                	}
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });  
    	Precios.btoCancelar.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                try{
                	ByosFromProducto.refrescar();  
                    Precios.closeWindows();  
                    
                } catch (Exception e) {
                    // Ingnored, we'll let the Form handle the errors
                }
            }
        });  
    	
    	Precios.openWindows();
    } 
    

    void procesoAjusteDeposito(){
    	tblProducto tbl = (tblProducto)ByosFromProducto.Clase;
    	try{
			if(tbl.getCodigoproducto()!=null && !tbl.getCodigoproducto().equals("") && tbl.existeCodigo(tbl.getCodigoproducto())){	
			  final moduloAjustedepositos AjusteDeposito = new moduloAjustedepositos(((tblProducto)ByosFromProducto.Clase));
			  AjusteDeposito.btoAceptar.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			        try{
			  		    final ByosMsgBox Respuesta = new ByosMsgBox("Este proceso alterara las existencias de este producto, esta seguro de querer ejecuparlo?","Ajuste"); 	  		    
			  		    Respuesta.btoSi.addClickListener(new Button.ClickListener() {
			  	            public void buttonClick(Button.ClickEvent event) {
			  	                try {
			  	                	
			  	                	new tblProductoDeposito().Ajustar(AjusteDeposito.ModuloDepositoAjuste.getProductoDeposito());
			  	                	((tblProducto)ByosFromProducto.Clase).buscarCodigo(((tblProducto)ByosFromProducto.Clase).getCodigoproducto());
			  	                	ByosFromProducto.refrescar();
			  	                	ModuloProductoDetalle.ModuloDepositoDetalle.setTblProducto(((tblProducto)ByosFromProducto.Clase));
			  	                	AjusteDeposito.closeWindows();
			  	                	Respuesta.closeWindows();
			  	                } catch (Exception e) {
			  	                    // Ingnored, we'll let the Form handle the errors
			  	                }
			  	            }
			  	        });
			  		    Respuesta.btoNo.addClickListener(new Button.ClickListener() {
			  	            public void buttonClick(Button.ClickEvent event) {
			  	                try {
			  	                	AjusteDeposito.closeWindows();  
			  	                	Respuesta.closeWindows();
			  	                } catch (Exception e) {
			  	                    // Ingnored, we'll let the Form handle the errors
			  	                }
			  	            }
			  	        });    	  		    
			  		    
			  		    Respuesta.openWindows();
			  		    
			        }catch (Exception e) {
			            // Ingnored, we'll let the Form handle the errors
			        }
			    }
			  });  
			
			  AjusteDeposito.btoCancelar.addClickListener(new Button.ClickListener() {
			    public void buttonClick(ClickEvent event) {
			        try{
			        	ByosFromProducto.refrescar();  
			        	AjusteDeposito.closeWindows();   
			        } catch (Exception e) {
			            // Ingnored, we'll let the Form handle the errors
			        }
			    }
			  });      	
			  AjusteDeposito.openWindows();
			}else{
			  Notification.show("No a elejido nungun producto", Notification.TYPE_TRAY_NOTIFICATION);	
			}
    	}catch(Exception e) {
			e.printStackTrace();
		}
    }

}
