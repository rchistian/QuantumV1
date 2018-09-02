package com.modulo.componentes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;



import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

public class ByosSubirArchivo extends VerticalLayout
                        implements Upload.SucceededListener,
                                   Upload.FailedListener,
                                   Upload.Receiver
                                   {

	private static final long serialVersionUID = 1L;
	
	
	public PropertyChangeSupport Support = new PropertyChangeSupport(this);
	
	public Window subwindow;
	public VerticalLayout layout = new VerticalLayout();
	HorizontalLayout root;          
    File  file;         
    String DirFile;
    
    @SuppressWarnings("deprecation")
	ByosSubirArchivo() {
        layout.setMargin(true);
        layout.setSpacing(true);
        subwindow = new Window("Agregar Archivo");
        subwindow.setWidth("40em");
        subwindow.setHeight("10em");
        subwindow.setModal(true);       
        subwindow.setContent(layout);
        root = new HorizontalLayout();
        addComponent(root);
        final Upload upload =
                new Upload("Archivo", this);

        upload.setButtonCaption("Subir Imagen");

        upload.addListener((Upload.SucceededListener) this);
        upload.addListener((Upload.FailedListener) this);

        root.addComponent(upload);
        layout.addComponent(root);
    }

    public OutputStream receiveUpload(String filename, String MIMEType) { 	
        FileOutputStream fos = null; 
        if(filename!=null && !filename.equals("")){
           String basepath =  VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
           File xFile = new File(basepath + DirFile);
           if(!xFile.isDirectory()){
              xFile.mkdirs();
           }
           //System.out.println("Archivo Cargado: " + basepath + DirFile + filename);
           if(xFile.isDirectory()){
              File xFile01 = new File(basepath + DirFile + filename);
              if(!xFile01.isFile()){
                 file = new File(basepath + DirFile + filename);
                 try {
                     fos = new FileOutputStream(file);
                 } catch (final java.io.FileNotFoundException e) {
                     e.printStackTrace();
                     return null;
                 }
              }
           }
    	}
        return fos; 
    }

    @SuppressWarnings("deprecation")
	public void uploadSucceeded(Upload.SucceededEvent event) {
    	Support.firePropertyChange("ArchivoCargado", false, true);
    	Notification.show("Archivo " + event.getFilename()
                + " de tipo '" + event.getMIMEType()
                + "' Subido.", Notification.TYPE_ERROR_MESSAGE);
    }

    @SuppressWarnings("deprecation")
	public void uploadFailed(Upload.FailedEvent event) {
    	 Notification.show("Error Al Subir el Archivo "
                 + event.getFilename() + " de tipo '"
                 + event.getMIMEType() + "' a fallado.", Notification.TYPE_ERROR_MESSAGE);
    }
    
    public void setDirFile(String DirFile){
    	this.DirFile = DirFile;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener Listener){
    	this.Support.addPropertyChangeListener(Listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener Listener){
    	this.Support.removePropertyChangeListener(Listener);
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