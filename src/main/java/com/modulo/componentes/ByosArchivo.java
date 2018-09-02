package com.modulo.componentes;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class ByosArchivo {
	
	public static class FileListFilterExtencion implements FilenameFilter {
		   private String name;        // File name filter
		   private String[] extension;   // File extension filter

		   // Constructor
		   public FileListFilterExtencion(String name, String[] extension) {
		      this.name = name;
		      this.extension = extension;
		   }

		   public boolean accept(File directory, String filename) {
		      boolean fileOK = false;
		      if(extension != null){
		    	 for(int f=0;f<extension.length;f++){
		             //fileOK &= filename.endsWith('.' + extension[f]);
		             //System.out.println("Extencion Aceptada: " + extension[f] + " Final: " + filename.endsWith('.' + extension[f]) + "  Archivo: " + filename);
		             if(filename.endsWith('.' + extension[f])){
		            	return true; 
		             }  
		    	 }
		      }
		      return fileOK; 
		   }
		}
	
	public static class FileListFilter implements FilenameFilter {
	   private String name;        // File name filter
	   private String extension;   // File extension filter

	   // Constructor
	   public FileListFilter(String name, String extension) {
	      this.name = name;
	      this.extension = extension;
	   }

	   public boolean accept(File directory, String filename) {
	      boolean fileOK = true;

	      // If there is a name filter specified, check the file name
	      if(name != null){
	         fileOK &= filename.startsWith(name);
	      }

	      // If there is an extension filter, check the file extension
	      if(extension != null){
	         fileOK &= filename.endsWith('.' + extension);
	      }
	      return fileOK; 
	   }
	}
	
	
    public static String[] LeerDirUrl(String Dir, String FileName, String Extension, String DirUrl){
        FilenameFilter select = new FileListFilter(FileName, Extension);
        File xFile = new File(Dir);
        String[] ArrayArchivos=null;
        if (xFile.isDirectory()){
            
            String xList_Dir[] = xFile.list(select);
            if(xList_Dir.length>0){
               ArrayArchivos = new String[xList_Dir.length];
               for(int c=0;c<xList_Dir.length;c++){ 
            	   ArrayArchivos[c] = DirUrl + xList_Dir[c];   
               }
            }
        }
        return ArrayArchivos;
     }
    
    public static String[] LeerDirFile(String Dir, String FileName, String Extension, String DirUrl){
        FilenameFilter select = new FileListFilter(FileName, Extension);
        File xFile = new File(Dir);
        String[] ArrayArchivos=null;
        if(xFile.isDirectory()){  
           String xList_Dir[] = xFile.list(select);
           if(xList_Dir.length>0){
              ArrayArchivos = new String[xList_Dir.length];
              for(int c=0;c<xList_Dir.length;c++){ 
             	  ArrayArchivos[c] = Dir + xList_Dir[c];   
              } 
           }
        }
        return ArrayArchivos;
     }  

    public static String[] LeerDirUrl(String Dir, String FileName, String[] Extension, String DirUrl){
      	FilenameFilter select = new FileListFilterExtencion(FileName, Extension);
        File xFile = new File(Dir);
        String[] ArrayArchivos=null;
        if (xFile.isDirectory()){
            
            String xList_Dir[] = xFile.list(select);
            if(xList_Dir.length>0){
               ArrayArchivos = new String[xList_Dir.length];
               for(int c=0;c<xList_Dir.length;c++){ 
            	   ArrayArchivos[c] = DirUrl + xList_Dir[c];   
               }
            }
        }
        return ArrayArchivos;
     }
    
    public static String[] LeerDirFile(String Dir, String FileName, String[] Extension, String DirUrl){
    	FilenameFilter select = new FileListFilterExtencion(FileName, Extension);
        File xFile = new File(Dir);
        String[] ArrayArchivos=null;
        if(xFile.isDirectory()){  
           String xList_Dir[] = xFile.list(select);
           if(xList_Dir.length>0){
              ArrayArchivos = new String[xList_Dir.length];
              for(int c=0;c<xList_Dir.length;c++){ 
             	  ArrayArchivos[c] = Dir + xList_Dir[c];   
              } 
           }
        }
        return ArrayArchivos;
     }  
    
    
    public static boolean BorrarArchivo(String xArchivo){
        File xFile = new File(xArchivo);
        if (xFile.isFile()){
            boolean a=xFile.delete();
            return a;
        }
        return false;
      }

}
