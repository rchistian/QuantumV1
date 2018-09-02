package com.modulo.componentes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
//import com.modulo.productoimpuesto.tblProductoImpuesto;



import com.modulo.productoimpuesto.tblProductoImpuesto;

public class ByosConversores {
    public static BigDecimal StringToBigDecimal(String Valor){
    	if(Valor!=null && !Valor.equals("") && ByosValidar.esNumero(Valor)){
    	   return  BigDecimal.valueOf(Double.valueOf(Valor));
    	}else{
    	   return null;
    	}
    }
    
    public static BigDecimal StringToBigDecimal(String Valor, int Decimales){
    	if(Valor!=null && !Valor.equals("") && ByosValidar.esNumero(Valor)){
    	   return  round(BigDecimal.valueOf(Double.valueOf(Valor)), Decimales);
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
    
    public static String FormatBigDecimal(BigDecimal bigDecimal, String Formato){
    	DecimalFormat df = new DecimalFormat(Formato);
    	String formattedValue = df.format(bigDecimal);
    	return formattedValue;
    }
    
    public static BigDecimal round(BigDecimal d, int scale) {
    	  int mode = BigDecimal.ROUND_HALF_EVEN;
    	  if(ByosParametros.TIPO_REDONDEO.equals(ByosParametros.REDONDEAR_ARRIBA)){
    		 mode = BigDecimal.ROUND_UP;
    	  }
    	  if(ByosParametros.TIPO_REDONDEO.equals(ByosParametros.REDONDEAR_ABAJO)){
    		 mode = BigDecimal.ROUND_DOWN;
    	  }
    	  if(ByosParametros.TIPO_REDONDEO.equals(ByosParametros.REDONDEAR_ESTANDAR)){
    		 mode = BigDecimal.ROUND_HALF_EVEN;
    	  }     	  
    	  return d.setScale(scale, mode);
    }
    
    public static String BigDecimalToString(BigDecimal Valor, int scale){
    	if(Valor==null){
    	   return "";
    	}else{
    	   return round(Valor,scale).toString();
    	}
    }
    
    public static String DoubleToString(double ValorDouble, int scale){
    	BigDecimal Valor = BigDecimal.valueOf(ValorDouble);
    	if(Valor==null){
    	   return "";
    	}else{
    	   return round(Valor,scale).toString();
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
    
    
    
    public static BigDecimal CalculoImpuestoTotal(tblProductoImpuesto[] TblProductoImpuesto, BigDecimal Monto){
    	double Impuesto=0;
    	if(TblProductoImpuesto!=null && TblProductoImpuesto.length>0){
           for(int f=0;f<TblProductoImpuesto.length;f++){
        	  Impuesto+=CalculoImpuesto(TblProductoImpuesto[f].TblImpuestos.getPorcentaje(),Monto).doubleValue();
           }
    	}
    		
        return BigDecimal.valueOf(Impuesto);
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
       double numero = round(Numero,0).doubleValue();
       if(Factor>1){ 
    	  double mod = (numero%Factor);
    	  if(mod!=0){
    		 numero =  numero + Factor  - mod;
    	  }  
       }
       return BigDecimal.valueOf(numero);
    }
    
    public static String ValidarNull(String Valor){
       if(Valor==null){
    	  return "";
       }
       return Valor;
    }
    
    public static BigDecimal ValidarNull(BigDecimal Valor){
        if(Valor==null){
     	  return BigDecimal.valueOf(0);
        }
        return Valor;
    }
    
    public static Integer ValidarNull(Integer Valor){
        if(Valor==null){
     	  return Integer.valueOf(0);
        }
        return Valor;
    }
    
    public static Object ValidarNull(Object Valor, Object Retorno){
        if(Valor==null){
     	  return Retorno;
        }
        return Valor;
    }  
    
    public static java.sql.Timestamp ValidarNull(java.sql.Timestamp Valor){
        if(Valor==null){
     	  return new java.sql.Timestamp(new java.util.Date().getTime());
        }
        return Valor;
    }
    
    
    
}
