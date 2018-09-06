package com.modulo.componentes;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.tepi.imageviewer.ImageViewer;
import org.vaadin.tepi.imageviewer.ImageViewer.ImageSelectedEvent;


import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;


public class ByosVisorImegenes extends ByosVerticalLayout implements PropertyChangeListener{

   private static final long serialVersionUID = -8290233291737682346L;	     
   ImageViewer viewer = new ImageViewer();
   ByosSubirArchivo  SubirArchivo = new ByosSubirArchivo(); 
   public ByosMenu menu = new ByosMenu();
   ByosBoton BtoAgregarImagen = new ByosBoton(menu.MenuPrincipal, 108,"Agregar Imagen","");
   ByosBoton BtoEliminarImagen = new ByosBoton(menu.MenuPrincipal, 30,"Eliminar Imagen","");
   ByosBoton BtoCamara = new ByosBoton(menu.MenuPrincipal, 26,"Capturar Imagen","");
   ByosBoton BtoEscaner = new ByosBoton(menu.MenuPrincipal, 109,"Zoom","");
   ByosBoton BtoZoom = new ByosBoton(menu.MenuPrincipal, 107,"Zoom","");
   public List<ThemeResource> Imagenes = new ArrayList<ThemeResource>();
   
   private String DirUrl;
   private String DirFile;
   private String Estado;
   private int ImagenSeleccionada=-1;
   private String[] ArchivosUrl;
   private String[] ArchivosFile;
   private boolean MostrarIconos = true;
   public String ViwerHeight = "200px";
   public String TamanoBotonMenu = "40px";
   
   
   public void setViwerHeight(String ViwerHeight) {
	   this.ViwerHeight = ViwerHeight;
	   viewer.setHeight(this.ViwerHeight);
   }
   
   public String getViwerHeight() {
	   return ViwerHeight;
   }   
   
   public List<ThemeResource> getImagenes() {
	   return Imagenes;
   } 
 
   public void setImagenes(List<ThemeResource> imagenes) {
	   Imagenes = imagenes;
   }
   
   public void setVisorImegenes(String DirUrl, String DirFile, String Titulo, String Estado) {
		  this.DirFile=DirFile;
		  this.DirUrl=DirUrl;
		  this.Estado=Estado;
		  setByosLayout(Titulo, "100%");
		  
		  SubirArchivo.addPropertyChangeListener(this);
	      SubirArchivo.setDirFile(DirFile);
	      viewer.setWidth("100%");
	      viewer.setHeight(ViwerHeight);
	      viewer.setImmediate(true);
	      Contenido.setWidth("100%");
	      
	      
          menu.setSpacing(true);
	      BtoAgregarImagen.setWidth(TamanoBotonMenu);
	      BtoAgregarImagen.setHeight(TamanoBotonMenu);
	      BtoEliminarImagen.setWidth(TamanoBotonMenu);
	      BtoEliminarImagen.setHeight(TamanoBotonMenu);
	      BtoCamara.setWidth(TamanoBotonMenu);
	      BtoCamara.setHeight(TamanoBotonMenu);
	      BtoEscaner.setWidth(TamanoBotonMenu);
	      BtoEscaner.setHeight(TamanoBotonMenu);
	      BtoZoom.setWidth(TamanoBotonMenu);
	      BtoZoom.setHeight(TamanoBotonMenu);
	      
	      
	      if(MostrarIconos){
	         Contenido.addComponent(menu);
	      }
	      
	      init(DirUrl, DirFile, Estado);
	      
	      BtoZoom.addClickListener(new Button.ClickListener() {
	          public void buttonClick(ClickEvent event) {
	              try {
	            	  procesoZoomImagen();
	              } catch (Exception e) {
	                  // Ingnored, we'll let the Form handle the errors
	              }
	          }
	      });       
	     
	      BtoCamara.addClickListener(new Button.ClickListener() {
	          public void buttonClick(ClickEvent event) {
	              try {
	            	  procesoCamara();
	              } catch (Exception e) {
	                  // Ingnored, we'll let the Form handle the errors
	              }
	          }
	      });       
	      
	      BtoAgregarImagen.addClickListener(new Button.ClickListener() {
	          public void buttonClick(ClickEvent event) {
	              try {
	            	  procesoSubirArchivo();
	              } catch (Exception e) {
	                  // Ingnored, we'll let the Form handle the errors
	              }
	          }
	      });        
	      

	      BtoEliminarImagen.addClickListener(new Button.ClickListener() {
	          public void buttonClick(ClickEvent event) {
	              try {
	                  if(ImagenSeleccionada>=0){
	    	  		    final ByosMsgBox Respuesta = new ByosMsgBox("Seguro que desea eliminar la informacion?","Eliminar Imagen"); 	  		    
	    	  		    Respuesta.btoSi.addClickListener(new Button.ClickListener() {
	    	  	            public void buttonClick(Button.ClickEvent event) {
	    	  	                try {
	    	  	                	procesoEliminar();    
	    	  	                	Respuesta.closeWindows();
	    	  	                } catch (Exception e) {
	    	  	                    // Ingnored, we'll let the Form handle the errors
	    	  	                }
	    	  	            }
	    	  	        });
	    	  		    Respuesta.btoNo.addClickListener(new Button.ClickListener() {
	    	  	            public void buttonClick(Button.ClickEvent event) {
	    	  	                try {   
	    	  	                	Respuesta.closeWindows();
	    	  	                } catch (Exception e) {
	    	  	                    // Ingnored, we'll let the Form handle the errors
	    	  	                }
	    	  	            }
	    	  	        }); 
	    	  		    Respuesta.openWindows();
	                  }
	                  
	              } catch (Exception e) {
	                  // Ingnored, we'll let the Form handle the errors
	              }
	          }
	      });

	      
	      
	      Contenido.addComponent(viewer);	   
   }
   
   public ByosVisorImegenes(String DirUrl, String DirFile, String Titulo, String Estado){
	   setVisorImegenes(DirUrl, DirFile, Titulo, Estado);	   
   }
 
   public ByosVisorImegenes(String DirUrl, String DirFile, String Titulo, String Estado, boolean MostrarIconos, String ViwerHeight){
       this.MostrarIconos = MostrarIconos;
       this.ViwerHeight = ViwerHeight;
       setVisorImegenes(DirUrl, DirFile, Titulo, Estado);
   }
   
   public void procesoEliminar(){
	  if(Estado!=null && !Estado.equals("")){
	     ByosArchivo.BorrarArchivo(ArchivosFile[ImagenSeleccionada]);
	     init(DirUrl, DirFile, Estado);
	  }
   }
   
   public void procesoSubirArchivo(){
	  if(Estado!=null && !Estado.equals("")){
	     SubirArchivo.openWindows();
	  }
   }
   
   public void procesoCamara(){
	  if(Estado!=null && !Estado.equals("")){
		 String basepath =  VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + DirFile;
		 //System.out.println("Imagen: " + basepath);
    	 ByosWebCam WebCam = new ByosWebCam(basepath);
    	 WebCam.BtoSalir.addClickListener(new Button.ClickListener() {
	            public void buttonClick(Button.ClickEvent event) {
  	                try {   
  	                	init(DirUrl, DirFile, Estado);
  	                } catch (Exception e) {
  	                    // Ingnored, we'll let the Form handle the errors
  	                }
  	            }
  	        }); 
    	 WebCam.openWindows();
	  }
   }
   
   public void init(String DirUrl, String DirFile, String Estado) {
	  List<ThemeResource> Imagenes = new ArrayList<ThemeResource>();
      this.DirFile=DirFile;
	  this.DirUrl=DirUrl;
	  this.Estado=Estado;
	  SubirArchivo.setDirFile(DirFile);
	  
	  
	  String basepath =  VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + DirFile;
	  
	  String[] Extenciones = {"jpg" , "jpeg" , "bmp"};
	  ArchivosUrl = ByosArchivo.LeerDirUrl(basepath, "", Extenciones, DirUrl);
	  ArchivosFile = ByosArchivo.LeerDirFile(basepath, "", Extenciones, DirUrl);
	  int f=0;
	  if(ArchivosUrl!=null && ArchivosUrl.length>0){
	     for(f=0;f<ArchivosUrl.length;f++){
	         Imagenes.add(new ThemeResource(ArchivosUrl[f]));
	     }     
	  }else{
		 ImagenSeleccionada=-1;
	  }
	  
	  System.out.println("Directorio Local: " + basepath );
	  Contenido.removeComponent(viewer);
	  removeComponent(viewer);
	  viewer = new ImageViewer();
	  viewer.setImages(Imagenes);
	  viewer.setImmediate(true);
      viewer.setWidth("100%");
      
      if(Imagenes!=null && Imagenes.size()>0){
    	 int poc = Imagenes.size()/2;
         viewer.setCenterImageIndex(poc);
      }
      viewer.addListener(new ImageViewer.ImageSelectionListener() {	
		@Override
		public void imageSelected(ImageSelectedEvent e) {
	 		if(e.getSelectedImageIndex()>= 0) {
	  		   ImagenSeleccionada=e.getSelectedImageIndex()+1;
	  		   if((ImagenSeleccionada)>=ArchivosFile.length){
	  			 ImagenSeleccionada=0;    
	  		   }
	  		   //System.out.println("Archivo de Sistema: " + ImagenSeleccionada + "  " + ArchivosFile[ImagenSeleccionada]);
	  		   //System.out.println("Archivo de URL    : " + ImagenSeleccionada + "  " + ArchivosUrl[ImagenSeleccionada]);
	  		}else{
	  		   ImagenSeleccionada=-1;
	  		   //System.out.println("Ninguna imagen seleccionada"); 	  		   
	  		}			
		}
	  }); 
      viewer.setHeight(this.ViwerHeight);
      Contenido.addComponent(viewer);
      this.Imagenes=Imagenes;
      //addComponent(viewer);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
	   init(DirUrl, DirFile, Estado);
   } 
   
   public void procesoZoomImagen() {
       ByosVisorZoom ByosVisorZoom01 = new ByosVisorZoom(); 
       ByosVisorZoom01.ActivarZoom(DirUrl, DirFile, "",""); 
       
       //init(DirUrl, DirFile, Estado);
   }
   
}
