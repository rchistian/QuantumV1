package com.byos.sys.ui.ByosForm;


import com.byos.sys.ui.ByosCampo.ByosCampo;
import com.byos.sys.util.ByosValidar;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
    
    private String[] CamposVisibles;
    private String[] CamposDescripcion;
    private String[] CamposTipo;
        
    public ByosForm(){
        
    }
    
    public ByosForm(final Object Clase, final String[] CamposVisibles, String[] CamposDescripcion, String[] CamposTipo) {
        setClase(Clase, CamposVisibles, CamposDescripcion, CamposTipo);
    }
    
    public void setClase(final Object Clase, final String[] CamposVisibles, String[] CamposDescripcion, String[] CamposTipo) {       
        setSpacing(true);
        setMargin(true);
        lblEstado.setContentMode(Label.CONTENT_XHTML);      
        lblEstado.setWidth("100%"); 
        lblEstado.setHeight("2em"); 
        lblEstado.setCaption("");
        addComponent(lblEstado); 
        setComponentAlignment(lblEstado, Alignment.MIDDLE_CENTER);
    
        this.setStyleName("backColorBeige");
        
        this.Clase = Clase; 
        this.CamposVisibles=CamposVisibles;
        this.CamposDescripcion=CamposDescripcion;
        this.CamposTipo = CamposTipo;
        Campos = new ByosCampo[CamposVisibles.length];
        for(int f=0;f<CamposVisibles.length;f++){
            final Integer c=f;            
            Campos[f] = new ByosCampo();
            
            if(CamposDescripcion!=null && CamposDescripcion.length>0){
               Campos[f].lblNombreCampo.setCaption(CamposDescripcion[f]);
            }else{
               Campos[f].lblNombreCampo.setCaption(CamposVisibles[f]);               
            }
            
            if(CamposTipo!=null && CamposTipo.length>0){
               if(CamposTipo[f]!=null && Campos[f].CampoValido(CamposTipo[f])){
                  Campos[f].setTipoCampo(CamposTipo[f]); 
               }else{ 
                  Campos[f].setTipoCampo(Campos[f].CAMPO_DEFAULT);
                  //getWindow().showNotification("El Tipo de Campo Asignado a: " + CamposTipo[f] + " No Existe");
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
                CampoField.setAccessible(true);                   
                String Valor="";
                //if(CampoField == null){
                //   System.out.println("Campo Visible: (" + f + ") " + CamposVisibles[f] + " No Existe");
                //}
                if(CampoField.get(Clase) == null){
                   //System.out.println("Campo Visible: (" + f + ") " + CamposVisibles[f] + " No Existe");
                   Valor="";
                }else{   
                   Valor  = CampoField.get(Clase).toString();   
                }
                
                //Inicio Cambio en tipo Numerico en ByosForm funcion getClase el 2013-07-16
                if(Valor!=null && !Valor.equals("") && ByosValidar.esNumero(Valor)){
                   if(Campos[f].Decimales!=0){
                	  Valor=ByosValidar.BigDecimalToString(ByosValidar.StringToBigDecimal(Valor), Campos[f].Decimales);
                   }
                   if(Campos[f].FormatoNumero!=null || !Campos[f].FormatoNumero.equals("")){
                	  Valor=ByosValidar.FormatBigDecimal(ByosValidar.StringToBigDecimal(Valor), Campos[f].FormatoNumero);	
                   }                   
                   Campos[f].txtNumerico.setValue(Valor); 
                }else{
                   Campos[f].txtNumerico.setValue("");  
                }
                //Fin Cambio en tipo Numerico en ByosForm funcion getClase el 2013-07-16
                
                Campos[f].CboxItem.setValue(Valor);
                Campos[f].txtTexto.setValue(Valor);
                Campos[f].txtPassword.setValue(Valor);
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
             String Valor;
             
             if(Campos[c.intValue()].TipoLetra.toUpperCase().equals("MAYUSCULA")){
                Campos[c.intValue()].txtTexto.setValue(Campos[c.intValue()].txtTexto.getValue().toString().toUpperCase());
                Campos[c.intValue()].CboxItem.setValue(Campos[c.intValue()].CboxItem.getValue().toString().toUpperCase());
             }
                       
             if(Campos[c.intValue()].TipoLetra.toUpperCase().equals("MINUSCULA")){
                Campos[c.intValue()].txtTexto.setValue(Campos[c.intValue()].txtTexto.getValue().toString().toLowerCase());
                Campos[c.intValue()].CboxItem.setValue(Campos[c.intValue()].CboxItem.getValue().toString().toLowerCase());
             }

                       
             if(Campos[c.intValue()].TipoCampo.equals("ComboBox")){ 
                Valor=Campos[c.intValue()].CboxItem.getValue().toString(); 
             }else{
                if(Campos[c.intValue()].TipoCampo.equals("NumberField")){ 
                   Valor=Campos[c.intValue()].txtNumerico.getValue().toString();
                }else{
                   if(Campos[c.intValue()].TipoCampo.equals("PasswordField")){ 
                      Valor=Campos[c.intValue()].txtPassword.getValue().toString();  
                   }else{
                      Valor=Campos[c.intValue()].txtTexto.getValue().toString();   
                   }
                }
             } 
                       
             if(Campos[c.intValue()].Numerico && !ByosValidar.esNumero(Valor)){
                 Valor="";
                 Campos[c.intValue()].txtTexto.setValue("");
                 Campos[c.intValue()].CboxItem.setValue(""); 
                 Campos[c.intValue()].txtPassword.setValue(""); 
             }
                   
             if (Valor!=null){
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
             if(Tipo.equals("java.math.Integer")){                     
                CampoField.setAccessible(true);  
                if(Valor.equals("")){
                    CampoField.set(Clase, null); 	
                 }else{                   
                    CampoField.set(Clase, Integer.valueOf(Valor));
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
}