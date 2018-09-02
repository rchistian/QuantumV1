package com.modulo.componentes;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.PasswordField;

public class ByosTecladoNumerico extends VerticalLayout {
		
   HorizontalLayout[] N01 = new HorizontalLayout[4];
   Button[] Numeros = new Button[12];
   public String EstiloBoton = "botonobalado";
   private PasswordField passwordTexto = new PasswordField();
   
   public PasswordField getPasswordTexto() {
	  return passwordTexto;
   }

   public void setPasswordTexto(PasswordField passwordTexto) {
	  this.passwordTexto = passwordTexto;
   }

   public ByosTecladoNumerico() {
	   InicioComponentes(); 
   }
   
   public void InicioComponentes(){   
	   passwordTexto.setValue("");
	   passwordTexto.setStyleName("TextoPassword");
	   setWidth("155px");
	   setHeight("300px");
	   passwordTexto.setWidth("175px");
	   addComponent(passwordTexto);
	   for(int f=0;f<N01.length;f++){
	       N01[f] = new HorizontalLayout();
	       N01[f].setSpacing(true);
	       addComponent(N01[f]);
	   }   

	   for(int f=0;f<Numeros.length;f++){
		   Numeros[f] = new Button(); 
		   Numeros[f].setWidth("50px");
		   Numeros[f].setHeight("50px");
		   Numeros[f].setStyleName(EstiloBoton);
		   
		   if(f<3) {
		      N01[0].addComponent(Numeros[f]);
		   }
		   if(f>=3 && f<6) {
			  N01[1].addComponent(Numeros[f]);
		   }
		   if(f>=6 && f<9) {
			  N01[2].addComponent(Numeros[f]);
		   }
		   if(f>=9) {
			  N01[3].addComponent(Numeros[f]);
		   }
	   }   

	   
	   Numeros[0].setCaption("7");
	   Numeros[0].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros00_Click();
           }
       });
	   
	   Numeros[1].setCaption("8");
	   Numeros[1].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros01_Click();
           }
       });

	   Numeros[2].setCaption("9");
	   Numeros[2].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros02_Click();
           }
       });

	   Numeros[3].setCaption("4");
	   Numeros[3].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros03_Click();
           }
       });

	   Numeros[4].setCaption("5");
	   Numeros[4].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros04_Click();
           }
       });

	   Numeros[5].setCaption("6");
	   Numeros[5].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros05_Click();
           }
       });

	   Numeros[6].setCaption("1");
	   Numeros[6].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros06_Click();
           }
       });

	   Numeros[7].setCaption("2");
	   Numeros[7].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros07_Click();
           }
       });

	   Numeros[8].setCaption("3");
	   Numeros[8].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros08_Click();
           }
       });

	   Numeros[9].setCaption("C");
	   Numeros[9].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros09_Click();
           }
       });

	   Numeros[10].setCaption("0");
	   Numeros[10].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros10_Click();
           }
       });

	   Numeros[11].setCaption("<");
	   Numeros[11].addClickListener(new Button.ClickListener() {
           public void buttonClick(Button.ClickEvent event)  { 
        	   Numeros11_Click();
           }
       });   
   }

   private void Numeros00_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "7");
   }
   
   private void Numeros01_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "8");
   }

   private void Numeros02_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "8");
   }

   private void Numeros03_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "4");
   }

   private void Numeros04_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "5");
   }

   private void Numeros05_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "6");
   }

   private void Numeros06_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "1");
   }

   private void Numeros07_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "2");
   }

   private void Numeros08_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "3");
   }

   private void Numeros09_Click(){
	   passwordTexto.setValue("");
   }

   private void Numeros10_Click(){
	   passwordTexto.setValue(passwordTexto.getValue() + "0");
   }

   private void Numeros11_Click(){
	   if(passwordTexto.getValue().length()>0) {
		   passwordTexto.setValue(passwordTexto.getValue().substring(0, passwordTexto.getValue().length()-1));
	   }   
   }

}
