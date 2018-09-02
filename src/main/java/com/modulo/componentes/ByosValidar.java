package com.modulo.componentes;

import com.vaadin.event.FieldEvents;
import com.vaadin.ui.TextField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ByosValidar  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
	
    public static String Filtro(String Palabra, String Filtro){
        String Filtrado="";
        for(int f=0;f<Palabra.length();f++){
           if(strchar(Filtro,Palabra.substring(f, f+1))){
               Filtrado = Filtrado + Palabra.substring(f, f+1);
           }
        }
        return Filtrado;
    }

    public static void validarMayusculaJTextField(final javax.swing.JTextField txtTexto){
    	txtTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
            	txtTexto.setText(txtTexto.getText().toUpperCase());
            }
        });    	
    }
    
    public static void validarNumericoJTextField(final javax.swing.JTextField txtTexto){
    	txtTexto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
            	if (txtTexto.getText()!=null && !txtTexto.getText().equals("") && !esNumero(txtTexto.getText())){
            		txtTexto.setText("");
            		txtTexto.requestFocus();
            		
            		
            	}
            }
        });    	
    }
    
    public static boolean esNumero(String Numero){
        for (int f=0;f<Numero.length();f++)
            if(!strchar("1234567890+-.*",Numero.substring(f,f+1))) return false;
        return true;
    }
    
    public static boolean strchar(String Palabra, String Buscar){
        for(int f=0;f<Palabra.length();f++){
            if(Buscar.equals(Palabra.substring(f, f+1))) return true;
        }
        return false;            
    }
    
    public static String Add_String(String Cadena, char Relleno, int Cantidad){
         String Aux=Cadena;
         for(int f=0;f<(Cantidad-Cadena.length());f++)
             Aux = Aux + Relleno;
         return Aux;
    }

     public static String Add_String_D(String Cadena, char Relleno, int Cantidad){
         String Aux=Cadena;
         for(int f=0;f<(Cantidad-Cadena.length());f++)
             Aux = Aux + Relleno;
         return Aux;
    }

    public static String Add_String_I(String Cadena, char Relleno, int Cantidad){
         String Aux=Cadena;
         for(int f=0;f<(Cantidad-Cadena.length());f++)
             Aux = Relleno + Aux;
         return Aux;
    }
    
    public static Double Nulo(BigDecimal Valor){
        if(Valor==null){
            return Double.valueOf(0);
        }else{
            return Valor.doubleValue();
        }           
    }    
    
    @SuppressWarnings("deprecation")
	public static void validarMayusculaTextField(final TextField txtTexto){
        txtTexto.addListener(new FieldEvents.BlurListener(){
           @Override
           public void blur(FieldEvents.BlurEvent event) {
               txtTexto.setValue(txtTexto.getValue().toString().toUpperCase());                        
           }
        });   	
    }
    
    public static boolean ToleranciaNumerica(double valor01, double valor02){
    	double diferencia=Math.abs(valor01-valor02);
        if(diferencia>ByosParametros.TOLERANCIA_NUMERICA){
           return false;
        }
        return true;
    }
    
    public static boolean ToleranciaBigDecimal(BigDecimal Valor01, BigDecimal Valor02){
    	double valor01 = Valor01==null?0:Valor01.doubleValue();
    	double valor02 = Valor02==null?0:Valor02.doubleValue();
    	double diferencia=Math.abs(valor01-valor02);
    	if(diferencia>ByosParametros.TOLERANCIA_NUMERICA){
    		return false;
    	}
    	return true;
    }
}
