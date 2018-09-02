package com.modulo.reserva;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class moduloMenuTop {

    AbsoluteLayout layout;
    	
    public moduloMenuTop(AbsoluteLayout layout) {
    	this.layout = layout;
    	MenuTop();
    }
    
    /****************************************************************************************************************/
    /*                                                                                                              */
    /*                                              Menu Superior                                                   */       
    /*                                                                                                              */
    /****************************************************************************************************************/
    
    void MenuTop() {
    	
        /* Boton Menu Superior            */
        Button btoMenuTop01 = new Button();
        btoMenuTop01.setWidth("109px");
        btoMenuTop01.setHeight("49px");
        btoMenuTop01.setStyleName("BotonMenuTop01");
        layout.addComponent(btoMenuTop01, "left: 0px; top: 0px;");
        btoMenuTop01.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });
        
        
        Button btoMenuTop02 = new Button();
        btoMenuTop02.setWidth("109px");
        btoMenuTop02.setHeight("49px");
        btoMenuTop02.setStyleName("BotonMenuTop01");
        layout.addComponent(btoMenuTop02, "left: 125px; top: 0px;");
        btoMenuTop02.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });
        
        Button btoMenuTop03 = new Button();
        btoMenuTop03.setWidth("109px");
        btoMenuTop03.setHeight("49px");
        btoMenuTop03.setStyleName("BotonMenuTop01");
        layout.addComponent(btoMenuTop03, "left: 250px; top: 0px;");
        btoMenuTop03.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });       

        Button btoMenuTop04 = new Button();
        btoMenuTop04.setWidth("109px");
        btoMenuTop04.setHeight("49px");
        btoMenuTop04.setStyleName("BotonMenuTop01");
        layout.addComponent(btoMenuTop04, "left: 662px; top: 0px;");
        btoMenuTop04.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });       

        Button btoMenuTop05 = new Button();
        btoMenuTop05.setWidth("109px");
        btoMenuTop05.setHeight("49px");
        btoMenuTop05.setStyleName("BotonMenuTop01");
        layout.addComponent(btoMenuTop05, "left: 789px; top: 0px;");
        btoMenuTop05.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });           

        Button btoMenuTop06 = new Button();
        btoMenuTop06.setWidth("109px");
        btoMenuTop06.setHeight("49px");
        btoMenuTop06.setStyleName("BotonMenuTop01");
        layout.addComponent(btoMenuTop06, "left: 916px; top: 0px;");
        btoMenuTop06.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });    
        
        /* Menu Top Botones Inferiores */
        
        Button btoMenuTopInf01 = new Button();
        btoMenuTopInf01.setWidth("70px");
        btoMenuTopInf01.setHeight("33px");
        btoMenuTopInf01.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf01, "left: 0px; top: 63px;");
        btoMenuTopInf01.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });  

        Button btoMenuTopInf02 = new Button();
        btoMenuTopInf02.setWidth("70px");
        btoMenuTopInf02.setHeight("33px");
        btoMenuTopInf02.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf02, "left: 94px; top: 63px;");
        btoMenuTopInf02.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });         
       
        Button btoMenuTopInf03 = new Button();
        btoMenuTopInf03.setWidth("70px");
        btoMenuTopInf03.setHeight("33px");
        btoMenuTopInf03.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf03, "left: 188px; top: 63px;");
        btoMenuTopInf03.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });         

        
        Button btoMenuTopInf04 = new Button();
        btoMenuTopInf04.setWidth("70px");
        btoMenuTopInf04.setHeight("33px");
        btoMenuTopInf04.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf04, "left: 282px; top: 63px;");
        /*
        btoMenuTopInf04.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        }); */
        
        
        /* Derecha */
    
        Button btoMenuTopInf05 = new Button();
        btoMenuTopInf05.setWidth("70px");
        btoMenuTopInf05.setHeight("33px");
        btoMenuTopInf05.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf05, "left: 670px; top: 63px;");
        btoMenuTopInf05.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });  
        
        Button btoMenuTopInf06 = new Button();
        btoMenuTopInf06.setWidth("70px");
        btoMenuTopInf06.setHeight("33px");
        btoMenuTopInf06.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf06, "left: 764px; top: 63px;");
        btoMenuTopInf06.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });

        Button btoMenuTopInf07 = new Button();
        btoMenuTopInf07.setWidth("70px");
        btoMenuTopInf07.setHeight("33px");
        btoMenuTopInf07.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf07, "left: 858px; top: 63px;");
        btoMenuTopInf07.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });

        Button btoMenuTopInf08 = new Button();
        btoMenuTopInf08.setWidth("70px");
        btoMenuTopInf08.setHeight("33px");
        btoMenuTopInf08.setStyleName("BotonMenuTop02");
        layout.addComponent(btoMenuTopInf08, "left: 952px; top: 63px;");
        btoMenuTopInf08.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });
        
        /* Centro Menu */
        
        Button btoMenuTopCentro = new Button();
        btoMenuTopCentro.setWidth("318px");
        btoMenuTopCentro.setHeight("96px");
        btoMenuTopCentro.setStyleName("BotonMenuCentro");
        layout.addComponent(btoMenuTopCentro, "left: 352px; top: 0px;");
        btoMenuTopCentro.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event)  { 
             try{

  			 }catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
          }  
        });
    }

	
}
