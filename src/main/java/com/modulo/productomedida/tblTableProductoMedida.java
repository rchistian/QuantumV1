package com.modulo.productomedida;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.modulo.producto.tblProducto;
import com.modulo.productomedidadefault.tblProductoMedidaDefault;

public class tblTableProductoMedida {
	private static final long serialVersionUID = 1L;
	
	public tblProductoMedida TblProductoMedida = new tblProductoMedida();
	
	public tblProductoMedidaDefault[] TblProductoMedidaDefault;
	
	public String productosmedidasdefaultcodigo;
	
	public String productosmedidasdefaultdescripcion;
	
	public tblTableProductoMedida(){
		
	}


    public String getCodigoproducto() {
	   return this.TblProductoMedida.getCodigoproducto();
    }

    public void setCodigoproducto(String codigoproducto) {
	   this.TblProductoMedida.setCodigoproducto(codigoproducto);
    }
  
    public String getDescripcion() {
	   return this.TblProductoMedida.TblUnidadMedida.getDescripcion();
    }

    public void setDescripcion(String descripcion) {
    	this.TblProductoMedida.TblUnidadMedida.setDescripcion(descripcion);
    }    
    
    public String getCodigomedida() {	
	   return this.TblProductoMedida.getCodigomedida();
    }

    public void setCodigomedida(String codigomedida) {
	   this.TblProductoMedida.setCodigomedida(codigomedida);
    }

    public BigDecimal getEquivalencia() {
	   return this.TblProductoMedida.getEquivalencia();
    }

    public void setEquivalencia(BigDecimal equivalencia) {
	   this.TblProductoMedida.setEquivalencia(equivalencia);
    }

    public BigDecimal getPesoneto() {
	   return this.TblProductoMedida.getPesoneto();
    }

    public void setPesoneto(BigDecimal pesoneto) {
	   this.TblProductoMedida.setPesoneto(pesoneto);
    }
    
    public BigDecimal getContenidoneto() {
	   return this.TblProductoMedida.getContenidoneto();
    }

    public void setContenidoneto(BigDecimal contenidoneto) {
	   this.TblProductoMedida.setContenidoneto(contenidoneto);
    }
    
    public int getId() {
	   return this.TblProductoMedida.getId();
    } 
    
    public void setId(int id) {
	   this.TblProductoMedida.setId(id);
    }
    
    public void setVolumen(BigDecimal volumen) {
	  this.TblProductoMedida.setVolumen(volumen);
    } 
    
    public BigDecimal getVolumen() {
	   return this.TblProductoMedida.getVolumen();
    } 
    
    public tblProductoMedida getTblProductoMedida(){
    	return this.TblProductoMedida;
    }
    
    public void setTblProductoMedida(tblProductoMedida TblProductoMedida){
        this.TblProductoMedida = TblProductoMedida;	
    }

    public tblProductoMedidaDefault[] getTblProductoMedidaDefault(){
    	return this.TblProductoMedidaDefault;
    }
    
    public void setTblProductoMedidaDefault(tblProductoMedidaDefault[] TblProductoMedidaDefault){
        this.TblProductoMedidaDefault = TblProductoMedidaDefault;	
    }    
    
    public String getProductosmedidasdefaultcodigo(){
    	productosmedidasdefaultcodigo="";
    	String Separador="";
    	if(TblProductoMedidaDefault!=null && TblProductoMedidaDefault.length>0){
    	   for(int f=0;f<this.TblProductoMedidaDefault.length;f++){
    		   productosmedidasdefaultcodigo = productosmedidasdefaultcodigo + Separador  + this.TblProductoMedidaDefault[f].getCodigodefault();
    		   Separador=",";
    	   }
    	}
    	return productosmedidasdefaultcodigo;
    }
    
    
    public void setProductosmedidasdefaultcodigo(String ProductoMedidaDefaultCodigo){
    	this.productosmedidasdefaultcodigo = ProductoMedidaDefaultCodigo;
    	String[] ProductosMedidas = ProductoMedidaDefaultCodigo.split(",");
    	if(ProductosMedidas.length>0){
           TblProductoMedidaDefault = new tblProductoMedidaDefault[ProductosMedidas.length];
           for(int f=0;f<ProductosMedidas.length;f++){
        	   TblProductoMedidaDefault[f] = new tblProductoMedidaDefault();
        	   TblProductoMedidaDefault[f].setCodigodefault(ProductosMedidas[f]);
        	   TblProductoMedidaDefault[f].setCodigomedida(getCodigomedida());
        	   TblProductoMedidaDefault[f].setCodigoproducto(getCodigoproducto());
        	   try{
				  TblProductoMedidaDefault[f].TblMedidasDefault.buscarCodigo(ProductosMedidas[f]);
				  TblProductoMedidaDefault[f].TblUnidadMedida.buscarCodigo(getCodigomedida());
			   }catch(Exception e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			   }
           }
    	}
    }
    
    public String getProductosmedidasdefaultdescripcion(){
       productosmedidasdefaultdescripcion="";
       String Separador="";
       if(TblProductoMedidaDefault!=null && TblProductoMedidaDefault.length>0){
    	  for(int f=0;f<this.TblProductoMedidaDefault.length;f++){
    		  productosmedidasdefaultdescripcion = productosmedidasdefaultdescripcion + Separador  + this.TblProductoMedidaDefault[f].TblMedidasDefault.getDescripcion();
    		  Separador=",";
    	  }
       }
       return productosmedidasdefaultdescripcion;
    }    
    
    public void setProductosmedidasdefaultdescripcion(String productosmedidasdefaultdescripcion){
    	this.productosmedidasdefaultdescripcion = productosmedidasdefaultdescripcion;
    }  
    
    public String getProductoMedidaDefaultSql(){
    	String CodigoProductoMedida="";
    	String Separador="";
    	if(TblProductoMedidaDefault!=null && TblProductoMedidaDefault.length>0){
    	   CodigoProductoMedida="";	
    	   for(int f=0;f<this.TblProductoMedidaDefault.length;f++){
    		   CodigoProductoMedida = CodigoProductoMedida + Separador  + "'" + this.TblProductoMedidaDefault[f].getCodigodefault() + "'";
    		   Separador=",";
    	   }
    	   //CodigoProductoMedida = CodigoProductoMedida + ")"; 
    	}
    	return CodigoProductoMedida;
    }  
    
    
    public tblTableProductoMedida[] LoadTblTableProductoMedida(tblProducto TblProducto){
    	tblTableProductoMedida[] medidas=null;
    	if(TblProducto.TblProductoMedidas!=null && TblProducto.TblProductoMedidas.length>0){
    	   medidas = new tblTableProductoMedida[TblProducto.TblProductoMedidas.length];	
    	   for(int f=0;f<TblProducto.TblProductoMedidas.length;f++){
    		   medidas[f] = new tblTableProductoMedida();
    		   medidas[f].setCodigoproducto(TblProducto.TblProductoMedidas[f].getCodigoproducto());
    		   medidas[f].setCodigomedida(TblProducto.TblProductoMedidas[f].getCodigomedida());
    		   medidas[f].setDescripcion(TblProducto.TblProductoMedidas[f].TblUnidadMedida.getDescripcion());
    		   medidas[f].setEquivalencia(TblProducto.TblProductoMedidas[f].getEquivalencia());
    		   medidas[f].setContenidoneto(TblProducto.TblProductoMedidas[f].getContenidoneto());
    		   medidas[f].setPesoneto(TblProducto.TblProductoMedidas[f].getPesoneto());
    		   medidas[f].setVolumen(TblProducto.TblProductoMedidas[f].getVolumen());
    		   medidas[f].setId(TblProducto.TblProductoMedidas[f].getId());
    		   if(TblProducto.TblProductoMedidasDefault!=null && TblProducto.TblProductoMedidasDefault.length>0){
    			  productosmedidasdefaultcodigo = "";
    			  productosmedidasdefaultdescripcion = "";
    			  String Separador="";
    			  int cantidad=0;
    			  for(int c=0;c<TblProducto.TblProductoMedidasDefault.length;c++){
    				  if(medidas[f].getCodigomedida().equals(TblProducto.TblProductoMedidasDefault[c].getCodigomedida())){   					  
    					 productosmedidasdefaultcodigo = productosmedidasdefaultcodigo + Separador + TblProducto.TblProductoMedidasDefault[c].getCodigodefault();
    				     productosmedidasdefaultdescripcion = productosmedidasdefaultdescripcion + Separador + TblProducto.TblProductoMedidasDefault[c].TblMedidasDefault.getDescripcion();
    				     Separador=",";
    				     cantidad++;
    				     //System.out.println(productosmedidasdefaultcodigo);
    				  }
    			  }
    			  
    			  medidas[f].TblProductoMedidaDefault = new tblProductoMedidaDefault[cantidad];
    			  cantidad=0;
    			  for(int c=0;c<TblProducto.TblProductoMedidasDefault.length;c++){
    				  if(medidas[f].getCodigomedida().equals(TblProducto.TblProductoMedidasDefault[c].getCodigomedida())){
    					 medidas[f].TblProductoMedidaDefault[cantidad] = new tblProductoMedidaDefault(); 
    					 medidas[f].TblProductoMedidaDefault[cantidad].setCodigoproducto(TblProducto.TblProductoMedidasDefault[c].getCodigoproducto());
    					 medidas[f].TblProductoMedidaDefault[cantidad].setCodigodefault(TblProducto.TblProductoMedidasDefault[c].getCodigodefault());
    					 medidas[f].TblProductoMedidaDefault[cantidad].setCodigomedida(TblProducto.TblProductoMedidasDefault[c].getCodigomedida());
    					 try {
							medidas[f].TblProductoMedidaDefault[cantidad].TblMedidasDefault.buscarCodigo(TblProducto.TblProductoMedidasDefault[c].getCodigodefault());
							medidas[f].TblProductoMedidaDefault[cantidad].TblUnidadMedida.buscarCodigo(TblProducto.TblProductoMedidasDefault[c].getCodigomedida());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
    					 cantidad++;
    				  }
    			  }    			  
    			  
    		   }
    		   
    	   }
    	}
    	
    	return medidas;
    }
    
   
    
    public ArrayList <tblTableProductoMedida> LoadTblTableProductoMedidaArray(tblProducto TblProducto, String Tipo){
    	ArrayList <tblTableProductoMedida> ArrayMedidas = new ArrayList<tblTableProductoMedida>();
    	tblTableProductoMedida[] Medidas = LoadTblTableProductoMedida(TblProducto);	
    	if(Medidas!=null && Medidas.length>0){
    	   for(int f=0;f<Medidas.length;f++){
    	   	   ArrayMedidas.add(Medidas[f]);   
    	   }  
    	}
    	return ArrayMedidas;
    }
    
}
