package com.modulo.componentes;


import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Chistian
 */
public class ByosForm extends VerticalLayout implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
    public HorizontalLayout layoutCampo = new HorizontalLayout();
    public ByosCampo[] Campos;
    public Object Clase;
    public ArrayList ArrayClase = new ArrayList(); 
    public int Posicion=0;    
    public Label lblEstado = new Label();
    public boolean MostrarEstado=true;
    public String AlinearBoton="DERECHA";
    
	private String[] CamposVisibles;
    private String[] CamposDescripcion;
    private String[] CamposTipo;
    
    public int LargoDefault = 39;
    public String LargoTipoDefault = "px";
    public String AnchoDefault = "250px";
    
    public String TipoLayout="Horizontal";
    
    public boolean LabelTexto=false;
    
    public boolean isLabelTexto() {
		return LabelTexto;
	}

	public void setLabelTexto(boolean labelTexto) {
		LabelTexto = labelTexto;
	}

	public String getTipoLayout() {
    	return TipoLayout;
    }
    
    public void setTipoLayout(String TipoLayout) {
    	this.TipoLayout = TipoLayout;
    }
        
    public ByosForm(){
        
    }
    
    public ByosForm(final Object Clase, final String[] CamposVisibles, String[] CamposDescripcion, String[] CamposTipo) {
        setClase(Clase, CamposVisibles, CamposDescripcion, CamposTipo);
    }
    
    public void setMostrarEstado(boolean MostrarEstado){
    	this.MostrarEstado=MostrarEstado;
    }
    
    public boolean getMostrarEstado(){
    	return MostrarEstado;
    }
    
    public String getAlinearBoton() {
		return AlinearBoton;
	}

	public void setAlinearBoton(String alinearBoton) {
		AlinearBoton = alinearBoton;
	}

	
    public void setClase(final Object Clase, final String[] CamposVisibles, String[] CamposDescripcion, String[] CamposTipo) {       
        //setSpacing(false);
        //setMargin(false);

        
        if(getMostrarEstado()) {
           lblEstado.setContentMode(Label.CONTENT_XHTML);      
           lblEstado.setWidth("100%"); 
           lblEstado.setCaption("");
           addComponent(lblEstado); 
           setComponentAlignment(lblEstado, Alignment.TOP_CENTER);
        }
        
        this.Clase = Clase; 
        this.CamposVisibles=CamposVisibles;
        this.CamposDescripcion=CamposDescripcion;
        this.CamposTipo = CamposTipo;
        Campos = new ByosCampo[CamposVisibles.length];
       
        for(int f=0;f<CamposVisibles.length;f++){

            final Integer c=f;  
            Campos[f].TipoLayout = getTipoLayout();
            Campos[f].LabelTexto = isLabelTexto();
            Campos[f].AlinearBoton = getAlinearBoton();
            Campos[f] = new ByosCampo();
            
            
            Campos[f].setAncho(AnchoDefault);
            Campos[f].setLargo(LargoDefault, LargoTipoDefault);
            
            /*Campos[f].setEstilo("v-byoscampo","Tope");
        	if(f==0){
        	    Campos[f].setEstilo("v-byoscampotop","Medio");
        	}
        	if(f==CamposVisibles.length-1){
        	    Campos[f].setEstilo("v-byoscampobottom","Final");    
        	}*/
        	
        	
            if(CamposDescripcion!=null && CamposDescripcion.length>0){  
               Campos[f].lblNombreCampo.setValue(CamposDescripcion[f]);
               Campos[f].setPrompt(CamposDescripcion[f]);
            }else{
               Campos[f].lblNombreCampo.setValue(CamposVisibles[f]);
               Campos[f].setPrompt(CamposVisibles[f]);
            }
            
            if(CamposTipo!=null && CamposTipo.length>0){
               if(CamposTipo[f]!=null && Campos[f].CampoValido(CamposTipo[f])){
                  Campos[f].setTipoCampo(CamposTipo[f]); 
               }else{ 
                  Campos[f].setTipoCampo(Campos[f].CAMPO_DEFAULT);
               }
            }else{
               Campos[f].setTipoCampo(Campos[f].CAMPO_DEFAULT); 
            }
            
            Campos[f].txtTexto.addBlurListener(new FieldEvents.BlurListener(){
                @Override
                public void blur(BlurEvent event) {
                    setClase(c);
                }            
            });
            
            Campos[f].txtNumerico.addBlurListener(new FieldEvents.BlurListener(){
                @Override
                public void blur(BlurEvent event) {
                    setClase(c);
                }            
            });
            
            Campos[f].CboxItem.addBlurListener(new FieldEvents.BlurListener(){
                @Override
                public void blur(BlurEvent event) {
                    setClase(c);
                }            
            });
            
            Campos[f].txtTextArea.addBlurListener(new FieldEvents.BlurListener(){
                @Override
                public void blur(BlurEvent event) {
                    setClase(c);
                }            
            });            
            
            Campos[f].CboxItem.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                	setClase(c);
                }
            });
             
            Campos[f].FechaHora.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                	setClase(c);
                }
            });    
      
            Campos[f].txtTextArea.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                	setClase(c);
                }
            });
            
            Campos[f].txtNumerico.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                	setClase(c);
                }
            });    
            Campos[f].txtPassword.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
                	setClase(c);
                }
            });
            
            getClase(f);            
            addComponent(Campos[f]);
        }
    }
    
    public void refrescar(){
        for(int f=0;f<CamposVisibles.length;f++){
            getClase(f);
        }
    }

    void getClase(int f){
            try{    
                Field CampoField = Clase.getClass().getDeclaredField(CamposVisibles[f]);
               
                String Tipo = CampoField.getType().getName();
                java.util.Date Fecha = new java.util.Date();
                CampoField.setAccessible(true);                   
                String Valor="";
                if(CampoField.get(Clase) == null){
                   Valor="";
                }else{
                   if(Tipo.equals("java.util.Date")){
                	  Fecha =  (java.util.Date) CampoField.get(Clase);
                   }                   
                   Valor  = CampoField.get(Clase).toString(); 
                }
                
                boolean SoloLectura=Campos[f].txtNumerico.isReadOnly();
                Campos[f].txtNumerico.setReadOnly(false);
                if(Valor!=null && !Valor.equals("") && ByosValidar.esNumero(Valor)){
                   if(Campos[f].TipoCampo.equals(Campos[f].CAMPO_NUMBERFIELD)){
                   	  Valor=ByosConversores.BigDecimalToString(ByosConversores.StringToBigDecimal(Valor), Campos[f].Decimales);
                      if(Campos[f].FormatoNumero!=null && !Campos[f].FormatoNumero.equals("")){
                	     Valor=ByosConversores.FormatBigDecimal(ByosConversores.StringToBigDecimal(Valor), Campos[f].FormatoNumero);	
                      }
                   }
                   Campos[f].txtNumerico.setValue(Valor); 
                }else{
                   Campos[f].txtNumerico.setValue("");  
                }
                Campos[f].txtNumerico.setReadOnly(SoloLectura);
                
                SoloLectura=Campos[f].FechaHora.isReadOnly();
                Campos[f].FechaHora.setReadOnly(false);
                if(Campos[f].TipoCampo.equals(Campos[f].CAMPO_FECHAHORA)){
                   Campos[f].FechaHora.setValue(Fecha); 
                }
                Campos[f].FechaHora.setReadOnly(SoloLectura);
                
                SoloLectura=Campos[f].CboxItem.isReadOnly();
                Campos[f].CboxItem.setReadOnly(false);
                Campos[f].CboxItem.setValue(Valor);
                Campos[f].CboxItem.setReadOnly(SoloLectura);
                   
                SoloLectura=Campos[f].txtTexto.isReadOnly();
                Campos[f].txtTexto.setReadOnly(false);
                Campos[f].txtTexto.setValue(Valor);
                Campos[f].txtTexto.setReadOnly(SoloLectura);
               
                SoloLectura=Campos[f].txtPassword.isReadOnly();
                Campos[f].txtPassword.setReadOnly(false);
                Campos[f].txtPassword.setValue(Valor);
                Campos[f].txtPassword.setReadOnly(SoloLectura);
                
                SoloLectura=Campos[f].txtTextArea.isReadOnly();
                Campos[f].txtTextArea.setReadOnly(false);
                Campos[f].txtTextArea.setValue(Valor);
                Campos[f].txtTextArea.setReadOnly(SoloLectura);
                
                if(Campos[f].isLink){                   
                   Field  TablaFieldLike1 =Clase.getClass().getDeclaredField(Campos[f].TablaLink);
                   Object TablaFieldLike2 = TablaFieldLike1.get(Clase);
                   Field  TablaFieldLike3 =TablaFieldLike2.getClass().getDeclaredField(Campos[f].CampoLink);              
                   TablaFieldLike3.setAccessible(true);
                   String ValorLink="";
                   if(TablaFieldLike3.get(TablaFieldLike2) == null){
                      ValorLink="";
                   }else{   
                      ValorLink  = TablaFieldLike3.get(TablaFieldLike2).toString();   
                   }
                   Campos[f].lblDescripcion.setValue(ValorLink);                    
                } 
                
            }catch(java.lang.IllegalAccessException e) {
                   System.out.println("Error 1");
            }catch(NoSuchFieldException ex) {
                   System.out.println("Campo Visible: (" + f + ") " + CamposVisibles[f] + " No Existe en la clase");
            }catch(SecurityException ex) {
                   System.out.println("Error 3");
            }            
        
    }
    
       
    
    void setClase(Integer c){
         try{    
             Field CampoField = Clase.getClass().getDeclaredField(CamposVisibles[c.intValue()]);
             String Tipo = CampoField.getType().getName();
             String Valor="";
             java.util.Date Fecha = new java.util.Date();
             
             if(Campos[c.intValue()].TipoLetra.toUpperCase().equals("MAYUSCULA")){
                Campos[c.intValue()].txtTexto.setValue(Campos[c.intValue()].txtTexto.getValue().toString().toUpperCase());
                Campos[c.intValue()].txtTextArea.setValue(Campos[c.intValue()].txtTextArea.getValue().toString().toUpperCase());
                //Campos[c.intValue()].CboxItem.setValue(Campos[c.intValue()].CboxItem.getValue().toString().toUpperCase());
             }
                       
             if(Campos[c.intValue()].TipoLetra.toUpperCase().equals("MINUSCULA")){
                Campos[c.intValue()].txtTexto.setValue(Campos[c.intValue()].txtTexto.getValue().toString().toLowerCase());
                Campos[c.intValue()].txtTextArea.setValue(Campos[c.intValue()].txtTextArea.getValue().toString().toLowerCase());
                //Campos[c.intValue()].CboxItem.setValue(Campos[c.intValue()].CboxItem.getValue().toString().toLowerCase());
             }

                       
             if(Campos[c.intValue()].TipoCampo.equals(ByosCampo.CAMPO_COMBOBOX)){
            	if(Campos[c.intValue()].CboxItem!=null && Campos[c.intValue()].CboxItem.getValue()!=null){ 
                   Valor=Campos[c.intValue()].CboxItem.getValue().toString();
            	}else{
            	   Valor="";
            	}
             }else{
            	if(Campos[c.intValue()].TipoCampo.equals(ByosCampo.CAMPO_FECHAHORA)){
            	   Fecha = Campos[c.intValue()].FechaHora.getValue();
            	}else{
                   if(Campos[c.intValue()].TipoCampo.equals(ByosCampo.CAMPO_NUMBERFIELD)){ 
                      Valor=Campos[c.intValue()].txtNumerico.getValue().toString();
                   }else{
                      if(Campos[c.intValue()].TipoCampo.equals(ByosCampo.CAMPO_PASSWORD)){ 
                         Valor=Campos[c.intValue()].txtPassword.getValue().toString();  
                      }else{
                          if(Campos[c.intValue()].TipoCampo.equals(ByosCampo.CAMPO_TEXTAREA)){ 
                              Valor=Campos[c.intValue()].txtPassword.getValue().toString();  
                           }else{                    	  
                              Valor=Campos[c.intValue()].txtTexto.getValue().toString(); 
                           }
                      }
                   }
            	}
             } 
                       
             if(Campos[c.intValue()].Numerico && !ByosValidar.esNumero(Valor)){
                 Valor="";
                 Campos[c.intValue()].txtTexto.setValue("");
                 Campos[c.intValue()].txtTextArea.setValue("");
                 Campos[c.intValue()].CboxItem.setValue(""); 
                 Campos[c.intValue()].txtPassword.setValue(""); 
             }
                   
             if(Valor!=null){
            	 //System.out.println("Tipo Valor: " + Tipo);
                 if(Tipo.equals("java.lang.String")){                     
                    CampoField.setAccessible(true);                   
                    CampoField.set(Clase, Valor);
                 } 
                 if(Tipo.equals("java.math.BigDecimal")){                     
                    CampoField.setAccessible(true);
                    if(Valor.equals("")){
                       CampoField.set(Clase, null); 	
                    }else{
                       CampoField.set(Clase, BigDecimal.valueOf(Double.valueOf(Valor))); 
                    }
                 }
                 if(Tipo.equals("java.math.Double")){                     
                    CampoField.setAccessible(true);  
                    if(Valor.equals("")){
                       CampoField.set(Clase, null); 	
                    }else{                
                       CampoField.set(Clase, Double.valueOf(Valor));  
                    }
                 }   
                 if(Tipo.equals("java.math.Integer") || Tipo.equals("java.lang.Integer")){                     
                    CampoField.setAccessible(true);  
                    //System.out.println("Valor: " + Valor);
                    if(Valor.equals("")){
                       CampoField.set(Clase, null); 	
                    }else{                   
                       CampoField.set(Clase, Integer.valueOf(Valor));
                    }
                 }
                 
                 //System.out.println("Tipo de Campo: " + Tipo);
                 if(Tipo.equals("java.util.Date") || Tipo.equals("java.sql.Date")){                     
                     CampoField.setAccessible(true);  
                     //System.out.println("Campo Tipo Fecha Valor: " + Valor);
                     if(Fecha==null){
                        CampoField.set(Clase, null); 	
                     }else{                   
                        CampoField.set(Clase, Fecha);
                     }
                  }                 
             }
         }catch(java.lang.IllegalAccessException e) {
                System.out.println("Error 1");
         }catch(NoSuchFieldException ex) {
                System.out.println("Error 2");
         }catch(SecurityException ex) {
                System.out.println("Error 3");
         }       
    }    
    
    public int getNroCampos(){
        return Campos.length;
    }
    
    public boolean ExisteCampo(String BuscarCampo){
        try{    
            Field CampoField = Clase.getClass().getDeclaredField(BuscarCampo);                                
            String Valor="";
            if(CampoField == null){
               System.out.println("Campo Visible: " + BuscarCampo + " No Existe en la clase");
               return false;
            }
            return true;
         }catch(NoSuchFieldException ex) {
            System.out.println("Campo Visible: " + BuscarCampo + " No Existe en la clase");
            return false;
         }catch(SecurityException ex) {
            System.out.println("Campo Visible: " + BuscarCampo + " No Existe en la clase");
            return false;
         }              

    }
    
    public void setisLink(String Item, boolean isLink, String TablaLink, String CampoLink){
        int ItemTexto=getItem(Item);
        if(ItemTexto>=0){ 
           Campos[ItemTexto].setisLike(isLink, TablaLink, CampoLink); 
        }        
    }

    public ByosCampo getCampo(String Item){
        int ItemTexto=getItem(Item);
        if(ExisteCampo(Item)){
           if(ItemTexto>=0){         
              return Campos[ItemTexto];
           }
        }
        return null;
    }

    public void setTipoCampo(String Item, String TipoCampo){
        int ItemTexto=getItem(Item);
        if(ItemTexto>=0){  
           if(ExisteCampo(Item)){
              Campos[ItemTexto].setTipoCampo(TipoCampo);
           }
        }
    }
    
    public int getItem(String Item){
       for(int f=0;f<CamposVisibles.length;f++){
           if(CamposVisibles[f].equals(Item)){
              return f; 
           }
       }
       System.out.println(Item + " No existe en Campos Vicibles: ");
       return -1;
    }
    
    public void setArrayClase(ArrayList ArrayClase){
       this.ArrayClase=ArrayClase;
       if(ArrayClase!=null && ArrayClase.size()>0){
          Clase=(Object) this.ArrayClase.get(0);
          Posicion=0;
          refrescar();
       }
       mostrarEstatus();
    }
    
    public ArrayList getArrayClase(){
    	return this.ArrayClase;
    }
    
    public void procesoAnterior(){
	if(ArrayClase!=null){
	   Posicion--;
           if(Posicion<0){
	      Posicion=ArrayClase.size()-1;	 
           }
           Clase=ArrayClase.get(Posicion);
           refrescar();
        }
        mostrarEstatus();
    }

    public void procesoProximo(){
	if(ArrayClase!=null){		
           Posicion++;
           if(Posicion>=ArrayClase.size()){
	      Posicion=0;	
           }
	   Clase=ArrayClase.get(Posicion);
           refrescar();
        }
        mostrarEstatus();
    }
    
    void mostrarEstatus(){
        if (ArrayClase!=null && ArrayClase.size()>0){
            lblEstado.setValue("<Center>Registro: " + (Posicion + 1) + " de " + ArrayClase.size() + " Encontrado(s)");
        }else{
            
            lblEstado.setValue(" ");
        }
    }
    
    public boolean DatosRequeridos(String[] Datos){
        if(Datos!=null && Datos.length>0){
           for(int f=0;f<Datos.length;f++){
        	   System.out.println(getCampo(Datos[f]).getValor());
        	   if(getCampo(Datos[f]).getValor()==null || getCampo(Datos[f]).getValor().equals("")){
        		  Notification.show("El Dato: " + ByosConversores.ValidarNull(getCampo(Datos[f]).lblNombreCampo.getValue()) + " " + ByosConversores.ValidarNull(getCampo(Datos[f]).lblNombreCampo.getCaption()) + " Es requerido", Notification.TYPE_TRAY_NOTIFICATION);
        		  getCampo(Datos[f]).setFocus();
        		  return false;
        	   }
           }
        }
        return true;
    }
    
    public void setLargoDefault(int Largo, String Tipo){
      this.LargoDefault = Largo;
      this.LargoTipoDefault =Tipo;
    }
    
    public void setAnchoDefault(String Ancho){
    	this.AnchoDefault = Ancho;
    }
}