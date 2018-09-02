package com.byos.sys.procesos;

import java.util.Random;

public class apocrifoE {
	
	public static int[][] SERIE = new int[90][100];

	/*public static void main(String[] args) {

		String PalabraBD = E("Hola1",ERandom());
		System.out.println(PalabraBD);

		if(E("Hola1",Modo(PalabraBD)).equals(PalabraBD)){
			System.out.println("Iguales");
		}else{
			System.out.println("Diferente");
		}

	}*/
	
	public static void Cargar_Serie(){
	    double Raiz;
	    int f=0, c=0, s=1;
	    String Numero;

	    while (f < 90){
	        
	        while (c < 100){
	           
	            s++;
	            Raiz =  Math.cos((s * ((c+f))));
	            SERIE[f][c] = Math.abs((int) (((Raiz - Double.valueOf(Raiz).intValue()) * 100) + 35) % 10)+15;
	            
	            if(SERIE[f][c]==0){
	               SERIE[f][c] = 10;
	            }
	            if(s >= 100) s = 0;
	            c++;
	        }
	        f++;
	        c = 0;
	    }
	}
	
	public static int ERandom(){
		int tipo_enc=0;
	    Random random = new Random();

	    while (tipo_enc == 0){
	       if(random.nextInt(90) == 1){
	          tipo_enc = 0;
	       }else{
	          tipo_enc = random.nextInt(90);
	       }
	    }
	    return tipo_enc;
	}

	public static String  E(String xPalabra, int tipo_enc){
	    String enc;
	    //int tipo_enc;
	    int f;
	    if (tipo_enc<0) {
	    	return "";
	    }
	    xPalabra=xPalabra.toUpperCase();
	    Cargar_Serie();
	    enc = "";
	    
	    for(f=0;f<100;f++){
	        if(f < xPalabra.length()){
	           enc = enc + (char)(((char)xPalabra.charAt(f) + SERIE[tipo_enc][f]));
	        }else{
	           enc = enc + (char) (' ' + Math.abs(SERIE[tipo_enc][f]));
	        }
	    }
	    enc = enc + (char)(tipo_enc+35);
	    return enc;
	}
	
	public static int Modo(String xPalabra){
	    String enc;
	    int tipo_enc;
	    int f;
	    Cargar_Serie();
	    enc = "";
	    if(xPalabra!=null && xPalabra.length()==101){
	       tipo_enc = (int) xPalabra.charAt(100) - 35;
	       return tipo_enc;
	    }else{
	       return -1;	
	    }
	}

}
