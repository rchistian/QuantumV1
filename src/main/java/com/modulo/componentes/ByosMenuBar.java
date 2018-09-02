package com.modulo.componentes;

import java.io.Serializable;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;


public class ByosMenuBar extends MenuBar implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//MenuBar barmenu = new MenuBar();
	MenuItem menuByos;
	private MenuBar.Command Comando;
	private String Titulo;
	private int Icono = 0; 
	
	void CrearMenu(String[][] Menu, String Forma){

		if(Forma==null || !Forma.equals("Lineal")){
		   menuByos=this.addItem(Titulo, ByosImagenes.icon[Icono] , null);
		}
		
	    String[][] Nivel001=NivelMenu(Menu,1,null);
	    //barmenu.setStyleName(com.vaadin.ui.themes.ValoTheme.MENU_BADGE);
	    if(Nivel001!=null && Nivel001.length>0){
		    MenuItem[] ItemNivel001 = new MenuItem[Nivel001.length];
	    	for(int f=0;f<Nivel001.length;f++){	    		
	    		//System.out.println(Nivel001[f][0] + " " + Nivel001[f][1] + " " + Nivel001[f][2]);
	    		if(Forma==null || !Forma.equals("Lineal")){
	    		   ItemNivel001[f]=menuByos.addItem(Nivel001[f][2], Iconos(Nivel001, f), Comando);
	    		}else{
	    		   this.addItem(Nivel001[f][2], Iconos(Nivel001, f), Comando);	
	    		}
	    		
	    		String[][] Nivel002=NivelMenu(Menu,2,Nivel001[f][0].substring(0, 3));
	    		if(Nivel002!=null && Nivel002.length>0){
	    			MenuItem[] ItemNivel002 = new MenuItem[Nivel002.length];
	    			for(int c=0;c<Nivel002.length;c++){
	    				if(Forma==null || !Forma.equals("Lineal")){
	    		    	   ItemNivel002[c]=ItemNivel001[f].addItem(Nivel002[c][2], Iconos(Nivel002, c), Comando);  
	    				}else{
	    				   ItemNivel002[c]=this.addItem(Nivel002[c][2], Iconos(Nivel002, c), Comando); 	
	    				}
	    	    		String[][] Nivel003=NivelMenu(Menu,3,Nivel002[c][0].substring(0, 6));
	    	    		if(Nivel003!=null && Nivel003.length>0){
	    	    			MenuItem[] ItemNivel003 = new MenuItem[Nivel003.length];
	    	    			for(int j=0;j<Nivel003.length;j++){
	    	    				//System.out.println("......" + Nivel003[j][0] + " " + Nivel003[j][1] + " " + Nivel003[j][2]);
	    	    		    	ItemNivel003[j]=ItemNivel002[c].addItem(Nivel003[j][2], Iconos(Nivel003, j), Comando);	    	
	    	    	    		String[][] Nivel004=NivelMenu(Menu,4,Nivel003[j][0].substring(0, 9));
	    	    	    		if(Nivel004!=null && Nivel004.length>0){
	    	    	    			MenuItem[] ItemNivel004 = new MenuItem[Nivel004.length];
	    	    	    			for(int i=0;i<Nivel004.length;i++){
	    	    	    				//System.out.println("........." + Nivel004[i][0] + " " + Nivel004[i][1] + " " + Nivel004[i][2]);
	    	    	    		    	ItemNivel004[i]=ItemNivel003[j].addItem(Nivel004[i][2], Iconos(Nivel004, i), Comando);;
	    	    	    			}	    			 
	    	    	    		}	    	    		    	
	    	    			}	    			 
	    	    		}    
	    			}	    			 
	    		}	    				
	    	}
	    }	    
	}
	
	
	
	ThemeResource Iconos(String[][] Nivel001, int pos){
		ThemeResource Iconos = null;
		int Icono=0;
		if(Nivel001[pos][1]!=null){
		   Icono = Integer.valueOf(Nivel001[pos][1]);
		}
		Iconos=null;
		if(Icono!=0){
		   Iconos=ByosImagenes.icon[Icono]; 
		}
		return Iconos;
	}
	

	
	String[][] NivelMenu(String[][] Menu, int Nivel, String CodigoNivel){
		int ContadorNivel=0;
		
		if(Menu!= null && Menu.length>0){
		   for(int f=0;f<Menu.length;f++){
			   if(Menu[f][0].length()==Nivel*3){			   
				   if(CodigoNivel!=null){
					  if(CodigoNivel.equals(Menu[f][0].substring(0, (Nivel-1)*3))){
						 
				         ContadorNivel++; 
					  }
				   }else{
					  ContadorNivel++; 
				   }
			   }
		   }
	    }
		
		String[][] MenuNivel = new String[ContadorNivel][3];
		if(ContadorNivel>0){
		   int c=0;	
		   for(int f=0;f<Menu.length;f++){
			   if(Menu[f][0].length()==Nivel*3){
				   if(CodigoNivel!=null){
				      if(CodigoNivel.equals(Menu[f][0].substring(0, (Nivel-1)*3))){ 
				         MenuNivel[c]=Menu[f];
				         c++;
				      }
				   }else{
					  MenuNivel[c]=Menu[f];
					  c++;
				   }
			   }
		   }	    		
		}else{
		   MenuNivel=null;
		}
		return MenuNivel;
	}
	
	public ByosMenuBar(String[][] Menu, String Titulo, int Icono, MenuBar.Command Comando, String Forma){
	    this.Titulo = Titulo;
	    this.Icono = Icono;
	    this.Comando = Comando;
		CrearMenu(Menu,Forma);		
	}
	
}