package com.modulo.componentes;


import com.byos.sys.conexion.Conexion;
import com.modulo.componentes.ByosSql;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.ComboBox;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Chistian
 */
public class ByosCampo  extends HorizontalLayout  implements Serializable{
	
	
    
    private static final long serialVersionUID = 5474522369804563317L;
    public static String  TipoLayout        = "Horizontal";
    public static boolean LabelTexto        = false;
    public static String  AlinearBoton      = "DERECHA";


	public static String CAMPO_TEXTFIELD   = "TextField";
    public static String CAMPO_NUMBERFIELD = "NumberField";
    public static String CAMPO_COMBOBOX    = "ComboBox";
    public static String CAMPO_PASSWORD    = "PasswordField";
    public static String CAMPO_DEFAULT     = "TextField";
    public static String CAMPO_LABEL       = "Label";
    public static String CAMPO_FECHAHORA   = "DateField";
    public static String CAMPO_TEXTAREA    = "TextArea";
    
    public static String TIPO_TEXTO_MAYUSCULA = "MAYUSCULA";
    public static String TIPO_TEXTO_MINUSCULA = "MINUSCULA";
    
    public VerticalLayout LayoutTextoTop = new VerticalLayout();
    public HorizontalLayout LayoutTexto = new HorizontalLayout();
    public HorizontalLayout SeparadorTexto = new HorizontalLayout();
    public Label         lblNombreCampo = new Label();
    public TextField     txtTexto       = new TextField();
    public TextField     txtNumerico    = new TextField();
    public TextArea      txtTextArea    = new TextArea();
    public PasswordField txtPassword    = new PasswordField();
    public ComboBox      CboxItem       = new ComboBox();
    public PopupDateField     FechaHora      = new PopupDateField();
    public ByosBoton     btoBoton1      = new ByosBoton();
    public ByosBoton     btoBoton2      = new ByosBoton();
    public ByosBoton     btoBoton3      = new ByosBoton();
    public ByosBoton     btoBoton4      = new ByosBoton();
    public Label         lblDescripcion = new Label();
    private Label        lblSeparador   = new Label();
    public String        TipoCampo      = "";
    public String        TipoLetra      = "";
    public boolean       Numerico       = false;
    public boolean       isLink         = false;
    public String        TablaLink      = "";
    public String        CampoLink      = "";
    public int Id=0;
    public String TipoTexto = TIPO_TEXTO_MAYUSCULA;
    public boolean isSelectLink=false;
    public String InSqlLink=null;
    public String Ancho="250px";
    
    public String Largo="39px";
    public int LargoFinal=40;
    public String LargoTipoFinal="px";
    
    //Inicio agregar variables globales en ByosCampo 2013-07-16
    public int Decimales=2;
    public String FormatoNumero="";
    
    public String EstiloTexto="TextoFiled";
    public String EstiloBox="TextoBox";
    public String EstiloLabel="LabelTexto";
    
    
    
    
    //Fin agregar variables globales en ByosCampo 2013-07-16
    public static String getAlinearBoton() {
    	
		return AlinearBoton;
	}

	public static void setAlinearBoton(String alinearBoton) {
		AlinearBoton = alinearBoton;
	}    
    
    public String getTipoLayout() {
    	return TipoLayout;
    }
    
    public void setTipoLayout(String TipoLayout) {
    	this.TipoLayout = TipoLayout;
    }    
    
    public ByosCampo() {
    	initComponents();
    	setTipoCampo(CAMPO_TEXTFIELD);
    }
    
    public ByosCampo(String TipoCampo) {
    	initComponents();
    	setTipoCampo(TipoCampo);
    }
    
    
    public void setPrompt(String Prompet) {
        txtTexto.setInputPrompt(Prompet);
        txtNumerico.setInputPrompt(Prompet);
        txtPassword.setInputPrompt(Prompet);
        CboxItem.setInputPrompt(Prompet);
        FechaHora.setInputPrompt(Prompet);
        txtTextArea.setInputPrompt(Prompet);
        
        txtTexto.setDescription(Prompet);
        txtTexto.setDescription(Prompet);
        txtNumerico.setDescription(Prompet);
        txtPassword.setDescription(Prompet);
        CboxItem.setDescription(Prompet);
        FechaHora.setDescription(Prompet);
    }
  
    @SuppressWarnings("deprecation")
	public void initComponents() {
        setImmediate(true);
	    setMargin(false);        
        setSpacing(false);
        
        FechaHora.setDateFormat("dd/MM/yyyy");
        
        lblNombreCampo.setImmediate(true);
        txtNumerico.setImmediate(true); 
        CboxItem.setImmediate(true); 
        txtPassword.setImmediate(true);
        txtTexto.setImmediate(true);
        FechaHora.setImmediate(true);
        txtTextArea.setImmediate(true);
        
        
        lblNombreCampo.setStyleName(EstiloLabel);
        txtNumerico.setStyleName(EstiloTexto);
        CboxItem.setStyleName(EstiloBox);
        txtPassword.setStyleName(EstiloTexto);
        txtTexto.setStyleName(EstiloTexto);
        FechaHora.setStyleName(EstiloTexto);
        txtTextArea.setStyleName(EstiloTexto);
        
        
        //FechaHora.setValue(new java.util.Date());
        
        //FechaHora.setDateFormat("yyyy-MM-dd");
        
        
        
        this.TipoCampo="Texto";
            
        FechaHora.setVisible(false);
        txtNumerico.setVisible(false);
        
        //txtNumerico.setTextChangeTimeout(1);
        txtNumerico.addStyleName("align-right"); 
        

        
        txtNumerico.addTextChangeListener(new FieldEvents.TextChangeListener() {		
			@Override
			public void textChange(TextChangeEvent event) {
				if(event.getText()!=null && !event.getText().equals("")){
					   int pos=txtNumerico.getCursorPosition();
					   txtNumerico.setValue(ByosValidar.Filtro(event.getText(),"0123456789.,"));
					   txtNumerico.setCursorPosition(pos);
					}
				
			}
		});
        
       //Inicio cambio en tipo numerico ByosCampo initComponents 2013-07-16
       //Pierde el Foco
       /*txtNumerico.addListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {

            	
            }
        });*/
        txtTexto.addBlurListener(new FieldEvents.BlurListener(){
            @Override
            public void blur(BlurEvent event) {
                procesoLink(txtTexto);
                procesoTipoTexto();
                
            }            
        });
        
        txtNumerico.addBlurListener(new FieldEvents.BlurListener(){
            @Override
            public void blur(BlurEvent event) {
            	if(Decimales!=0){
             	   txtNumerico.setValue(ByosConversores.BigDecimalToString(ByosConversores.StringToBigDecimal(txtNumerico.getValue()), Decimales));
             	}
             	if(FormatoNumero!=null && !FormatoNumero.equals("")){
             	   txtNumerico.setValue(ByosConversores.FormatBigDecimal(ByosConversores.StringToBigDecimal(txtNumerico.getValue()),FormatoNumero));	
             	}
            	procesoLink(txtNumerico);
            }            
        });       
        
		//Gana el Foco
		/*txtNumerico.addFocusListener(new FieldEvents.FocusListener() {
			@Override
			public void focus(final FocusEvent event) {
				txtNumerico.selectAll();
			}
        });
		
		txtPassword.addFocusListener(new FieldEvents.FocusListener() {
			@Override
			public void focus(final FocusEvent event) {
				txtPassword.selectAll();
			}
        });		

		txtTexto.addFocusListener(new FieldEvents.FocusListener() {
			@Override
			public void focus(final FocusEvent event) {
				txtTexto.selectAll();
			}
        });	*/		
        //Fin cambio en tipo numerico ByosCampo initComponents 2013-07-16
        
        CboxItem.setVisible(false);
        
        CboxItem.setNullSelectionItemId("");
        CboxItem.setFilteringMode(FilteringMode.STARTSWITH); 
       
        txtPassword.setVisible(false);
     
        btoBoton1.setVisible(false);
        btoBoton2.setVisible(false);
        btoBoton3.setVisible(false);
        btoBoton4.setVisible(false);
        lblDescripcion.setVisible(false);
        
        txtNumerico.setWidth("150px"); 
        CboxItem.setWidth("150px"); 
        txtPassword.setWidth("150px");
        txtTexto.setWidth("150px");
        FechaHora.setWidth("150px");
        txtTextArea.setWidth("150px"); 
        lblNombreCampo.setWidth("160px");
        
        
        if(!getAlinearBoton().toUpperCase().equals("DERECHA")) {
        	//lblDescripcion.setVisible(true);
            //addComponent(lblDescripcion);
        	//setComponentAlignment(lblDescripcion, Alignment.BOTTOM_CENTER);
            addComponent(lblSeparador);
            setComponentAlignment(lblSeparador, Alignment.BOTTOM_CENTER); 
        	addComponent(btoBoton1);
            setComponentAlignment(btoBoton1, Alignment.BOTTOM_CENTER); 
            addComponent(btoBoton2);
            setComponentAlignment(btoBoton2, Alignment.BOTTOM_CENTER); 
            addComponent(btoBoton3);
            setComponentAlignment(btoBoton3, Alignment.BOTTOM_CENTER);
            addComponent(btoBoton4);
            setComponentAlignment(btoBoton4, Alignment.BOTTOM_CENTER);         

         	   
        }
        
        if(getTipoLayout().toUpperCase().equals("VERTICAL")) {
    	   LayoutTextoTop.setMargin(false);        
    	   LayoutTextoTop.setSpacing(false);
    	   lblNombreCampo.setWidth("100%");
    	   lblNombreCampo.setStyleName("LabelTextoRojo12");

           if(LabelTexto) {
        	   setPrompt(lblNombreCampo.getValue());
           }else {        	   
        	   LayoutTextoTop.addComponent(lblNombreCampo);
               LayoutTextoTop.setComponentAlignment(lblNombreCampo, Alignment.MIDDLE_CENTER);
               setPrompt("");
           }
           LayoutTextoTop.setWidth("100%");
                                
           LayoutTextoTop.addComponent(txtTexto);
           LayoutTextoTop.addComponent(txtNumerico);
           LayoutTextoTop.addComponent(txtPassword);
           LayoutTextoTop.addComponent(CboxItem);
           LayoutTextoTop.addComponent(FechaHora);
           LayoutTextoTop.addComponent(txtTextArea);

           LayoutTextoTop.setComponentAlignment(txtTexto, Alignment.MIDDLE_CENTER);
           LayoutTextoTop.setComponentAlignment(txtNumerico, Alignment.MIDDLE_CENTER);
           LayoutTextoTop.setComponentAlignment(txtPassword, Alignment.MIDDLE_CENTER);
           LayoutTextoTop.setComponentAlignment(CboxItem, Alignment.MIDDLE_CENTER);    
           LayoutTextoTop.setComponentAlignment(FechaHora, Alignment.MIDDLE_CENTER); 
           LayoutTextoTop.setComponentAlignment(txtTextArea, Alignment.MIDDLE_CENTER); 
           addComponent(LayoutTextoTop); 

        }else{

           LayoutTexto.addComponent(lblNombreCampo);
           LayoutTexto.addComponent(SeparadorTexto);
           LayoutTexto.addComponent(txtTexto);
           LayoutTexto.addComponent(txtNumerico); 
           LayoutTexto.addComponent(txtPassword);
           LayoutTexto.addComponent(CboxItem);
           LayoutTexto.addComponent(FechaHora);
           LayoutTexto.addComponent(txtTextArea);        	
        
           LayoutTexto.setComponentAlignment(lblNombreCampo, Alignment.MIDDLE_CENTER);
           LayoutTexto.setComponentAlignment(txtTexto, Alignment.MIDDLE_CENTER);
           LayoutTexto.setComponentAlignment(txtNumerico, Alignment.MIDDLE_CENTER);
           LayoutTexto.setComponentAlignment(txtPassword, Alignment.MIDDLE_CENTER);
           LayoutTexto.setComponentAlignment(CboxItem, Alignment.MIDDLE_CENTER);    
           LayoutTexto.setComponentAlignment(FechaHora, Alignment.MIDDLE_CENTER); 
           LayoutTexto.setComponentAlignment(txtTextArea, Alignment.MIDDLE_CENTER); 
           addComponent(LayoutTexto); 
        }
        

        
        
               
        
        /*
        addComponent(lblNombreCampo);
        addComponent(txtTexto);
        addComponent(txtNumerico);
        addComponent(txtPassword);
        addComponent(CboxItem);
        
        setComponentAlignment(lblNombreCampo, Alignment.MIDDLE_CENTER);
        setComponentAlignment(txtTexto, Alignment.MIDDLE_CENTER);
        setComponentAlignment(txtNumerico, Alignment.MIDDLE_CENTER);
        setComponentAlignment(txtPassword, Alignment.MIDDLE_CENTER);
        setComponentAlignment(CboxItem, Alignment.MIDDLE_CENTER);  
        */            
       
        if(getAlinearBoton().toUpperCase().equals("DERECHA")) {
        
           addComponent(btoBoton1);
           setComponentAlignment(btoBoton1, Alignment.BOTTOM_CENTER); 
           addComponent(btoBoton2);
           setComponentAlignment(btoBoton2, Alignment.BOTTOM_CENTER); 
           addComponent(btoBoton3);
           setComponentAlignment(btoBoton3, Alignment.BOTTOM_CENTER);
           addComponent(btoBoton4);
           setComponentAlignment(btoBoton4, Alignment.BOTTOM_CENTER);         
           addComponent(lblSeparador);
           setComponentAlignment(lblSeparador, Alignment.BOTTOM_CENTER); 
           //addComponent(lblDescripcion);
           //setComponentAlignment(lblDescripcion, Alignment.MIDDLE_CENTER); 
        }
    }
    
    void procesoTipoTexto(){
    	 if(TipoTexto.equals(TIPO_TEXTO_MAYUSCULA)){
    		txtTexto.setValue(txtTexto.getValue().toUpperCase()); 
    	 }
    	 if(TipoTexto.equals(TIPO_TEXTO_MINUSCULA)){
    		txtTexto.setValue(txtTexto.getValue().toLowerCase()); 
    	 }    	 
    }
    
    void procesoLink(TextField Texto){
        String Codigo=getValor();      
    	if(isSelectLink && InSqlLink!=null && !InSqlLink.equals("") && Codigo!=null && !Codigo.equals("")){
    	   System.out.println(InSqlLink);
      	   PreparedStatement pstmt = null;
      	   ResultSet rs = null;
    	   Connection con = Conexion.getNuevaConexion();
    	   try{
			  pstmt = con.prepareStatement(InSqlLink);
			  pstmt.setString(1,Codigo);
    		  rs = pstmt.executeQuery();
    		  if(rs!=null){
    		     if(rs.last()==true){
    			    rs.first();
    			    if(rs.getString(1)!=null){
    			       lblDescripcion.setVisible(true);
    			       lblDescripcion.setValue(rs.getString(1));
    			    }else{
    			       lblDescripcion.setValue("");
   			           setValor("");
   			           Notification.show("No se encontro ninguna considencia" , Notification.TYPE_TRAY_NOTIFICATION);
    			    }
    		     }else{
    		    	lblDescripcion.setValue("");
    		    	setValor("");
    		    	Notification.show("No se encontro ninguna considencia" , Notification.TYPE_TRAY_NOTIFICATION);
    		     }
    		  }else{
    			 lblDescripcion.setValue(""); 
    			 setValor("");
    			 Notification.show("No se encontro ninguna considencia" , Notification.TYPE_TRAY_NOTIFICATION);
    		  }
    		  try{
				 ByosSql.CloseAll(con,pstmt,rs);
			  }catch (Exception e) {
				 e.printStackTrace();
			  }  
   			}catch (SQLException e) {
			  e.printStackTrace();
			}
 		    try{
		       ByosSql.CloseAll(con,pstmt,rs);
		    }catch (Exception e) {
			   e.printStackTrace();
		    } 
    	}
    	
    }
    
    public void setTipoCampo(String TipoCampo){
        this.TipoCampo=TipoCampo;
        txtTexto.setVisible(true);
        CboxItem.setVisible(false);
        txtNumerico.setVisible(false);
        txtPassword.setVisible(false);
        FechaHora.setVisible(false);
        txtTextArea.setVisible(false);
        
        if(TipoCampo.equals(CAMPO_COMBOBOX)){
           txtTexto.setVisible(false);
           CboxItem.setVisible(true);          
        }
        if(TipoCampo.equals(CAMPO_NUMBERFIELD)){
           txtTexto.setVisible(false);
           txtNumerico.setVisible(true);
        }
        if(TipoCampo.equals(CAMPO_PASSWORD)){
           txtTexto.setVisible(false);
           txtPassword.setVisible(true);                             
        }
        if(TipoCampo.equals(CAMPO_LABEL)){
           txtTexto.setVisible(false);  
           lblDescripcion.setVisible(true);                             
        }
        if(TipoCampo.equals(CAMPO_FECHAHORA)){
           txtTexto.setVisible(false);  
           FechaHora.setVisible(true);               
        }
        if(TipoCampo.equals(CAMPO_TEXTAREA)){
           txtTexto.setVisible(false);
           txtTextArea.setVisible(true);
        }      
    }
    
    public void setTipoLetra(String TipoLetra){
        this.TipoLetra=TipoLetra;
    }
    
    public void setNumerico(boolean Numerico){
        this.Numerico=Numerico;
        txtTexto.setStyleName("align-right"); 
    }
    
    public void setisLike(boolean isLink, String TablaLink, String CampoLink){
        this.isLink=isLink;
        this.CampoLink = CampoLink;
        this.TablaLink = TablaLink;
        lblDescripcion.setVisible(isLink);
    }
    
    public void setSelectLink(boolean isSelectLink, String InSqlLink){
    	this.isSelectLink = isSelectLink;
    	this.InSqlLink = InSqlLink;
    }
    
    public String getCampoDefault(){
        return CAMPO_TEXTFIELD;
    }
    
    public boolean CampoValido(String CampoValido){
        if(CampoValido.equals(CAMPO_TEXTFIELD)){
           return true;
        }
        if(CampoValido.equals(CAMPO_NUMBERFIELD)){
           return true;
        }
        if(CampoValido.equals(CAMPO_COMBOBOX)){
           return true;
        }      
        if(CampoValido.equals(CAMPO_PASSWORD)){
           return true;
        }   
        if(CampoValido.equals(CAMPO_FECHAHORA)){
           return true;
        }   
        if(CampoValido.equals(CAMPO_TEXTAREA)){
            return true;
        }        
        return false;
    }
    
    //Inicio agregar get y set variables globales en ByosCampo 2013-07-16
    public void setFormatoNumero(String FormatoNumero){
    	this.FormatoNumero = FormatoNumero;
    }
    
    public String getFormatoNumero(){
       return this.FormatoNumero;
    }
    
    public void setDecimales(int Decimales){
       this.Decimales = Decimales;
    }
    
    public int getDecimales(){
        return this.Decimales;
    }   
    
    public void setEstilo(String Estilo, String Posicion){
    	
        txtNumerico.setWidth(getAncho()); 
        CboxItem.setWidth(getAncho()); 
        txtPassword.setWidth(getAncho());
        txtTexto.setWidth(getAncho());
        FechaHora.setWidth(getAncho());
        txtTextArea.setWidth(getAncho());
        lblNombreCampo.setWidth("160px");
        lblSeparador.setWidth("10px");
        
        LayoutTexto.setHeight(LargoFinal + LargoTipoFinal);
        
        if(Posicion.equals("Final")){
           txtNumerico.setHeight((LargoFinal-2) + LargoTipoFinal); 
           CboxItem.setHeight((LargoFinal-2) + LargoTipoFinal); 
           txtPassword.setHeight((LargoFinal-2) + LargoTipoFinal);
           txtTexto.setHeight((LargoFinal-2) + LargoTipoFinal);
           FechaHora.setHeight((LargoFinal-2) + LargoTipoFinal);
           txtTextArea.setHeight((LargoFinal-2) + LargoTipoFinal);
        }else{
           txtNumerico.setHeight((LargoFinal-1) + LargoTipoFinal); 
           CboxItem.setHeight((LargoFinal-1) + LargoTipoFinal); 
           txtPassword.setHeight((LargoFinal-1) + LargoTipoFinal);
           txtTexto.setHeight((LargoFinal-1) + LargoTipoFinal);
           FechaHora.setHeight((LargoFinal-1) + LargoTipoFinal);   
           txtTextArea.setHeight((LargoFinal-1) + LargoTipoFinal);   
        }
        
        LayoutTexto.setComponentAlignment(lblNombreCampo, Alignment.MIDDLE_CENTER);
        
        //SeparadorTexto.setStyleName("v-byoscamposeparador");
        SeparadorTexto.setHeight((LargoFinal-1) + LargoTipoFinal);
        SeparadorTexto.setWidth("2px");
        
        //LayoutTexto.setStyleName(Estilo);   
    }
    
    public String getValor(){
       String Codigo=txtTexto.getValue();	   
	   if(TipoCampo.equals(CAMPO_NUMBERFIELD)){
	      Codigo=txtNumerico.getValue();
	   }
	   if(TipoCampo.equals(CAMPO_TEXTFIELD)){
		  Codigo=txtTexto.getValue();
	   }
	   if(TipoCampo.equals(CAMPO_COMBOBOX)){
		  Codigo=CboxItem.getValue()==null?"":CboxItem.getValue().toString();
	   }	
	   if(TipoCampo.equals(CAMPO_PASSWORD)){
		  Codigo=txtPassword.getValue();
	   }
	   if(TipoCampo.equals(CAMPO_FECHAHORA)){
		   Codigo=FechaHora==null?"": FechaHora.getValue().toString();
			  			   
		     //Codigo=FechaHora.getData().toString();
	   }
	   if(TipoCampo.equals(CAMPO_TEXTAREA)){
		  Codigo=txtTextArea.getValue();
	   }		   
	   return Codigo;
    }
    
    public void setFocus(){	   
 	   if(TipoCampo.equals(CAMPO_NUMBERFIELD)){
 	      txtNumerico.focus();
 	   }
 	   if(TipoCampo.equals(CAMPO_TEXTFIELD)){
 		  txtTexto.focus();
 	   }
 	   if(TipoCampo.equals(CAMPO_COMBOBOX)){
 		  CboxItem.focus();
 	   }	
 	   if(TipoCampo.equals(CAMPO_PASSWORD)){
 		  txtPassword.focus();
 	   }
 	   if(TipoCampo.equals(CAMPO_FECHAHORA)){
 		  FechaHora.focus();
 	   }	
 	   if(TipoCampo.equals(CAMPO_TEXTAREA)){
 		  txtTextArea.focus();
 	   } 	   
     }

    public void setSoloLectura(boolean estado){
        String Codigo=txtTexto.getValue();	   
 	   if(TipoCampo.equals(CAMPO_NUMBERFIELD)){
 	      txtNumerico.setReadOnly(estado);
 	   }
 	   if(TipoCampo.equals(CAMPO_TEXTFIELD)){
 		  txtTexto.setReadOnly(estado);
 	   }
 	   if(TipoCampo.equals(CAMPO_COMBOBOX)){
 		  CboxItem.setReadOnly(estado);
 	   }	
 	   if(TipoCampo.equals(CAMPO_PASSWORD)){
 		  txtPassword.setReadOnly(estado);
 	   }
 	   if(TipoCampo.equals(CAMPO_FECHAHORA)){
 		  FechaHora.setReadOnly(estado);
 	   }
 	   if(TipoCampo.equals(CAMPO_TEXTAREA)){
 		  txtTextArea.setReadOnly(estado);
 	   }	 	   
     }    
    
    public void setValor(String Valor){
 	   if(TipoCampo.equals(CAMPO_DEFAULT) || TipoCampo.equals(CAMPO_TEXTFIELD) || TipoCampo.equals("")){
 	      txtTexto.setValue(Valor);
 	   }
 	   if(TipoCampo.equals(CAMPO_NUMBERFIELD)){
 	      txtNumerico.setValue(Valor);
 	   }
    }
    
    public void setTipoTexto(String TipoTexto){
       this.TipoTexto = TipoTexto; 	
    }
    
    public void setAncho(String Ancho){
    	this.Ancho=Ancho;
        txtNumerico.setWidth(getAncho()); 
        CboxItem.setWidth(getAncho()); 
        txtPassword.setWidth(getAncho());
        txtTexto.setWidth(getAncho());
        FechaHora.setWidth(getAncho());
        txtTextArea.setWidth(getAncho());
    }
    
    public void setLargo(String Largo){
    	this.Largo=Largo;
        txtNumerico.setHeight(getLargo()); 
        CboxItem.setHeight(getLargo()); 
        txtPassword.setHeight(getLargo()); 
        txtTexto.setHeight(getLargo()); 
        FechaHora.setHeight(getLargo()); 
        txtTextArea.setHeight(getLargo()); 
    }
    
    public String getAncho(){
    	return Ancho;
    }
    
    public void setLargo(int Largo, String Tipo){
    	this.LargoFinal = Largo;
    	this.LargoTipoFinal = Tipo;
    }
    
    public String getLargo(){
    	return Largo;
    }
    

    //Fin agregar get y set variables globales en ByosCampo 2013-07-16
    
}
