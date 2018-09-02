package com.byos.sys.util;

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
    
    public static void validarMayusculaTextField(final TextField txtTexto){
        txtTexto.addListener(new FieldEvents.BlurListener(){
           @Override
           public void blur(FieldEvents.BlurEvent event) {
               txtTexto.setValue(txtTexto.getValue().toString().toUpperCase());                        
           }
        });   	
    }
    
    //Inicio Funciones agregadas en ByosValidar el 2013-07-16
    public static BigDecimal StringToBigDecimal(String Valor){
    	if(Valor!=null && !Valor.equals("") && ByosValidar.esNumero(Valor)){
    	   return  BigDecimal.valueOf(Double.valueOf(Valor));
    	}else{
    	   return null;
    	}
    }
    
    public static String BigDecimalToString(BigDecimal Valor){
    	if(Valor==null){
    	   return "";
    	}else{
    	   return Valor.toString();
    	}
    }
    
    public static String FormatBigDecimal(BigDecimal Valor, String Formato){
    	DecimalFormat df = new DecimalFormat(Formato);
    	String formattedValue = df.format(Valor);
    	return formattedValue;
    }
    
    public static BigDecimal round(BigDecimal d, int scale, boolean roundUp) {
    	  int mode = (roundUp) ? BigDecimal.ROUND_UP : BigDecimal.ROUND_DOWN;
    	  return d.setScale(scale, mode);
    }
    
    public static String BigDecimalToString(BigDecimal Valor, int scale){
    	if(Valor==null){
    	   return "";
    	}else{
    	   return round(Valor,scale,false).toString();
    	}
    }
    
    public static BigDecimal CalculoMontoMasImpuesto(BigDecimal Monto, BigDecimal Impuesto){
    	BigDecimal Resultado = BigDecimal.valueOf(0);
    	if(Monto!=null){
    	   if(Impuesto!=null && Impuesto.doubleValue()>0){
    		  Resultado=BigDecimal.valueOf(Monto.doubleValue()*(1+(Impuesto.doubleValue()/100)));
    	   }else{
    		  Resultado=BigDecimal.valueOf(Monto.doubleValue()); 
    	   }
    	}
        return Resultado;   	
    }
    
    public static BigDecimal CalculoMontoSinImpuesto(BigDecimal Monto, BigDecimal Impuesto){
    	BigDecimal Resultado = BigDecimal.valueOf(0);
    	if(Monto!=null){
    	   if(Impuesto!=null && Impuesto.doubleValue()>0){
    		  Resultado=BigDecimal.valueOf(Monto.doubleValue()/(1+(Impuesto.doubleValue()/100)));
    	   }else{
    		  Resultado=BigDecimal.valueOf(Monto.doubleValue()); 
    	   }
    	}
        return Resultado;   	
    }
    
    public static BigDecimal CalculoImpuesto(BigDecimal Monto, BigDecimal Impuesto){
    	BigDecimal Resultado = BigDecimal.valueOf(0);
    	if(Monto!=null){
    	   if(Impuesto!=null && Impuesto.doubleValue()>0){
    		  Resultado=BigDecimal.valueOf(Monto.doubleValue()*(Impuesto.doubleValue()/100));
    	   }
    	}
        return Resultado;   	
    } 
    
    public static BigDecimal CalculoCostoMasFactorUtilidad(BigDecimal Monto, BigDecimal Utilidad){
    	BigDecimal Resultado = BigDecimal.valueOf(0);
    	if(Monto!=null){
    	   if(Utilidad!=null && Utilidad.doubleValue()>0 && (100-Utilidad.doubleValue()!=0)){
    		  Resultado=BigDecimal.valueOf(Monto.doubleValue()/((100-Utilidad.doubleValue())/100));
    	   }else{
    		  Resultado=BigDecimal.valueOf(Monto.doubleValue()); 
    	   }  
    	}
        return Resultado;   	
    }
    
    public static BigDecimal CalculoFactorUtilidad(BigDecimal MontoCosto, BigDecimal MontoPrecio){
    	BigDecimal Resultado = BigDecimal.valueOf(0);
    	if(MontoCosto!=null){
    	   if(MontoPrecio!=null && MontoPrecio.doubleValue()>0){
    		  Resultado=BigDecimal.valueOf(100-((MontoCosto.doubleValue()/MontoPrecio.doubleValue())*100));
    	   }  
    	}
        return Resultado;   	
    } 
    
    public static BigDecimal Redondeo(BigDecimal Numero, int Factor){
       double numero = round(Numero,0,true).doubleValue();
       if(Factor>1){ 
    	  double mod = (numero%Factor);
    	  if(mod!=0){
    		 numero =  numero + Factor  - mod;
    	  }  
       }
       return BigDecimal.valueOf(numero);
    }
    //Fin funciones agregadas en ByosValidar el 2013-07-16
    
    
}
