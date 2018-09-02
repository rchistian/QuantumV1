package com.modulo.componentes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import org.vaadin.teemu.webcam.Webcam;
import org.vaadin.teemu.webcam.Webcam.CaptureSucceededEvent;
import org.vaadin.teemu.webcam.Webcam.CaptureSucceededListener;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;

public class ByosWebCam {

	private static final long serialVersionUID = 1L;
	private File targetFile;
    public Window subwindow;
    final VerticalLayout layout = new VerticalLayout();
    final HorizontalLayout layoutImagenes = new HorizontalLayout();
    final Panel panelImegen = new Panel();
    Webcam webcam = new Webcam();
    public ByosMenu menu = new ByosMenu();
    ByosBoton BtoCaptura = new ByosBoton(menu.MenuPrincipal, 29,"Capturar Imagen","");
    ByosBoton BtoSalir = new ByosBoton(menu.MenuPrincipal, 30,"Salir","");
    int f=0;
    public String DirFile;
    
    public ByosWebCam(String DirFile){
    	this.DirFile = DirFile;
    	init();
    	
    }
    
	@SuppressWarnings("serial")
	protected void init() {
	    layout.setMargin(false);
        subwindow = new Window("Captura de Imagenes");
        subwindow.setWidth("600px");
        subwindow.setHeight("590px");
        subwindow.setClosable(false);
        subwindow.setModal(true);
        layoutImagenes.setSpacing(true);
	    // Create the webcam and assign a receiver.
	    
	    webcam.setWidth("400px");
	    
	    
	    webcam.setReceiver(new Receiver() {

	        @Override
	        public OutputStream receiveUpload(String filename, String mimeType) {
	            try {
	            	//System.out.println("Archivo: " + DirFile + filename);
	                File xFile = new File(DirFile);
	                if(!xFile.isDirectory()){
	                   xFile.mkdirs();
	                }	            	
	                targetFile = new File(DirFile + filename, "");	                
	                //targetFile.deleteOnExit();
	                
	                return new FileOutputStream(targetFile);
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            return null;
	        }
	    });

	    // Add an event listener to be called after a successful capture.
	    webcam.addCaptureSucceededListener(new CaptureSucceededListener() {

	        public void captureSucceeded(CaptureSucceededEvent event) {
	            Image img = new Image("Imagen-" + ByosConversores.FormatBigDecimal(BigDecimal.valueOf(f),"000000"), new FileResource(targetFile));
	            img.setWidth("200px");
	            layoutImagenes.addComponent(img);
	            f++;
	            panelImegen.setScrollLeft(100*f);
	           
	        }
	    });

	    BtoCaptura.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
	           try{  
	               webcam.capture();
	           }catch (Exception e){
	               e.printStackTrace();
	           }      
			}
	    });  
	    
	    BtoSalir.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
	           try{
	        	   
	        	    webcam = null;
	        	   subwindow.close();
	           }catch (Exception e){
	               e.printStackTrace();
	           }      
			}
	    }); 
	    
	    panelImegen.setContent(layoutImagenes);
	    layout.addComponent(menu);
	    layout.addComponent(webcam);
	    layout.addComponent(panelImegen);
	    
	    layout.setComponentAlignment(webcam, Alignment.MIDDLE_CENTER);
	    layout.setComponentAlignment(panelImegen, Alignment.MIDDLE_CENTER);
	    
	    subwindow.setContent(layout);
	}
	
    public void closeWindows(){
        subwindow.close();
    }
     
    public void openWindows(){
       if(subwindow.getParent() != null) {
          Notification.show("Error", "No se puedo abrir la consulta",Notification.TYPE_ERROR_MESSAGE);
       }else {
    	   UI.getCurrent().addWindow(subwindow);   
          
       }        
    }
}
