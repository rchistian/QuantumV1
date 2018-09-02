package com.modulo.componentes;

import java.util.Random;

public class ByosCalculo {

	 public static int NumeroAleatorio(int Maximo, int Minimo) {
	      Random rn = new Random();
	      int n = Maximo - Minimo + 1;
	      int i = rn.nextInt() % n;
	      int random =  Minimo + i;
	      if(random<0) random=random*-1;
	      return random;
	 }
	 
	 
}
