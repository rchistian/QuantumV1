package com.byos.sys.ui.ByosCampo;

import com.byos.sys.ui.ByosBoton.ByosBoton;
import com.byos.sys.util.ByosValidar;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.ComboBox;

import java.io.Serializable;





/**
 *
 * @author Chistian
 */
public class ByosCampo  extends HorizontalLayout  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    public String CAMPO_TEXTFIELD   = "TextField";
    public String CAMPO_NUMBERFIELD = "NumberField";
    public String CAMPO_COMBOBOX    = "ComboBox";
    public String CAMPO_PASSWORD    = "PasswordField";
    public String CAMPO_DEFAULT     = "TextField";
    
    

    public Label         lblNombreCampo = new Label();
    public TextField     txtTexto       = new TextField();
    public TextField     txtNumerico    = new TextField();
    public PasswordField txtPassword    = new PasswordField();
    public ComboBox      CboxItem       = new ComboBox();
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
    
    //Inicio agregar variables globales en ByosCampo 2013-07-16
    public int Decimales=0;
    public String FormatoNumero="";
    //Fin agregar variables globales en ByosCampo 2013-07-16
    
    
    public ByosCampo() {
    	initComponents();
    }
   
    @SuppressWarnings("deprecation")
	public void initComponents() {
        setImmediate(false);
	    setMargin(false);        
        setSpacing(true);
        
        this.TipoCampo="Texto";
            
        txtNumerico.setVisible(false);
        txtNumerico.setWidth("10em"); 
        txtNumerico.setTextChangeTimeout(1);
        txtNumerico.setStyleName("align-right"); 
        
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
       txtNumerico.addListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
            	if(Decimales!=0){
            	   txtNumerico.setValue(ByosValidar.BigDecimalToString(ByosValidar.StringToBigDecimal(txtNumerico.getValue()), Decimales));
            	}
            	if(FormatoNumero!=null || !FormatoNumero.equals("")){
            	   txtNumerico.setValue(ByosValidar.FormatBigDecimal(ByosValidar.StringToBigDecimal(txtNumerico.getValue()),FormatoNumero));	
            	}
            	
            }
        });
        //Fin cambio en tipo numerico ByosCampo initComponents 2013-07-16
        
        CboxItem.setVisible(false);
        CboxItem.setWidth("10em"); 
        CboxItem.setNullSelectionItemId("");
        CboxItem.setFilteringMode(FilteringMode.STARTSWITH); 
       
        txtPassword.setVisible(false);
     
        btoBoton1.setVisible(false);
        btoBoton2.setVisible(false);
        btoBoton3.setVisible(false);
        btoBoton4.setVisible(false);
        lblDescripcion.setVisible(false);
        
        txtPassword.setWidth("10em");
        txtTexto.setWidth("10em");
        lblSeparador.setWidth("1em");
        
        lblNombreCampo.setWidth("10em");
        addComponent(lblNombreCampo);
        addComponent(txtTexto);
        addComponent(txtNumerico);
        addComponent(txtPassword);
        addComponent(CboxItem);
        addComponent(btoBoton1);
        setComponentAlignment(btoBoton1, Alignment.MIDDLE_CENTER); 
        addComponent(btoBoton2);
        setComponentAlignment(btoBoton2, Alignment.MIDDLE_CENTER); 
        addComponent(btoBoton3);
        setComponentAlignment(btoBoton3, Alignment.MIDDLE_CENTER);
        addComponent(btoBoton4);
        setComponentAlignment(btoBoton4, Alignment.MIDDLE_CENTER);         
        addComponent(lblSeparador);
        setComponentAlignment(lblSeparador, Alignment.MIDDLE_CENTER); 
        addComponent(lblDescripcion);
        setComponentAlignment(lblDescripcion, Alignment.MIDDLE_CENTER); 

    }
    
    public void setTipoCampo(String TipoCampo){
        this.TipoCampo=TipoCampo;
        if(TipoCampo.equals("ComboBox")){
           CboxItem.setVisible(true);
           txtTexto.setVisible(false);
           txtNumerico.setVisible(false);
           txtPassword.setVisible(false);         
        }else{
            if(TipoCampo.equals("NumberField")){
              CboxItem.setVisible(false);
              txtTexto.setVisible(false);
              txtNumerico.setVisible(true);
              txtPassword.setVisible(false);
            }else{
                if(TipoCampo.equals("PasswordField")){
                   CboxItem.setVisible(false);
                   txtTexto.setVisible(false);
                   txtNumerico.setVisible(false);
                   txtPassword.setVisible(true);                    
                }else{
                   CboxItem.setVisible(false);
                   txtTexto.setVisible(true);
                   txtNumerico.setVisible(false);
                   txtPassword.setVisible(false);
                }
            }
        }
            
    }
    
    public void setTipoLetra(String TipoLetra){
        this.TipoLetra=TipoLetra;
    }
    
    public void setNumerico(boolean Numerico){
        this.Numerico=Numerico;
    }
    
    public void setisLike(boolean isLink, String TablaLink, String CampoLink){
        this.isLink=isLink;
        this.CampoLink = CampoLink;
        this.TablaLink = TablaLink;
        lblDescripcion.setVisible(isLink);
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
    //Fin agregar get y set variables globales en ByosCampo 2013-07-16
    
}
