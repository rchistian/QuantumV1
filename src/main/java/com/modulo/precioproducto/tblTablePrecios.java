
package com.modulo.precioproducto;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.modulo.componentes.ByosConversores;
import com.modulo.producto.tblProducto;
import com.modulo.productoimpuesto.tblProductoImpuesto;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.productomedidadefault.tblProductoMedidaDefault;
import com.modulo.precios.tblPrecios;

public class tblTablePrecios {
	private static final long serialVersionUID = 1L;
	
	public tblProductoMedida TblProductoMedida = new tblProductoMedida();
	
	public tblProductoMedidaDefault[] TblProductoMedidaDefault;
	
	public tblPrecioProducto TblPrecioProducto = new tblPrecioProducto();
	
	public String productosmedidasdefaultcodigo;
	
	public String productosmedidasdefaultdescripcion;	
	
	public BigDecimal costo;
	
	public BigDecimal costovisible;
	
	public BigDecimal precioiva;
	
	public BigDecimal utilidadreal;
	
	public BigDecimal equivalenciavisible;
	
	public BigDecimal iva;
	
	public String descripcionprecio;
	
	public int id;
	
	public tblTablePrecios(){
		
	}
	
    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }	


    public String getCodigoproducto() {
	   return this.TblProductoMedida.getCodigoproducto();
    }
    
    public void setCodigoproducto(String codigoproducto) {
	   this.TblProductoMedida.setCodigoproducto(codigoproducto);
	   this.TblPrecioProducto.setCodigoproducto(codigoproducto);
    }    

    public String getCodigoprecio() {
	   return this.TblPrecioProducto.getCodigoprecio();
    }
    
    public void setCodigoprecio(String codigoprecio) {
	   this.TblPrecioProducto.setCodigoprecio(codigoprecio);
    }    
    
    
    public BigDecimal getUtilidad() {
       if(TblPrecioProducto.getUtilidad()==null || TblPrecioProducto.getUtilidad().doubleValue()<0){ 	
	      this.TblPrecioProducto.setUtilidad(BigDecimal.valueOf(0));
       }
       return this.TblPrecioProducto.getUtilidad();
    }

    public void setUtilidad(BigDecimal utilidad) {
	   this.TblPrecioProducto.setUtilidad(utilidad);
    }    
    
    
    
    public BigDecimal getUtilidadreal() {
       if(this.utilidadreal==null || this.utilidadreal.toString().equals("")){
          this.utilidadreal = ByosConversores.CalculoFactorUtilidad(getCosto(),getPrecio());
       }
	   return this.utilidadreal;
    }

    public void setUtilidadreal(BigDecimal utilidadreal) {
	   this.utilidadreal = utilidadreal;
    }  
    
    
    
    public BigDecimal getIva() {
       if(iva==null || iva.doubleValue()<=0){
     	  this.iva=BigDecimal.valueOf(0); 
       }   	
	   return this.iva;
    }

    public void setIva(BigDecimal iva) {
	   this.iva = iva;
    }

    
    
    public BigDecimal getCosto() {
       if(costo==null || costo.doubleValue()<=0){
    	   this.costo=BigDecimal.valueOf(0); 
       }
	   return this.costo;
    }
    
    public void setCosto(BigDecimal costo) {
	   this.costo = costo;
    }        
   
    
    
    public void setCostovisible(BigDecimal costovisible) {
	   this.costovisible = costovisible;
    } 
    
    public BigDecimal getCostovisible() {
	   return this.costovisible;
    }
    
    
    
    public BigDecimal getPrecio() {
	    if(this.TblPrecioProducto.getPrecio()==null || this.TblPrecioProducto.getPrecio().doubleValue()<0){
	    	this.TblPrecioProducto.setPrecio(BigDecimal.valueOf(0));	
	    }
    	return this.TblPrecioProducto.getPrecio();
    }

    public void setPrecio(BigDecimal Precio) {
	   this.TblPrecioProducto.setPrecio(Precio);
    }
    
    
    
    public BigDecimal getPrecioiva() {
       if(precioiva==null || precioiva.doubleValue()<0){	
    	  this.precioiva=BigDecimal.valueOf(0); 
       }
	   return this.precioiva;
    }

    public void setPrecioiva(BigDecimal precioiva) {
    	this.precioiva = precioiva;
    }    
    
    
    
    public String getDescripcionprecio() {
       return this.TblPrecioProducto.TblPrecios.getDescripcion();
    }

    public void setDescripcionprecio(String descripcionprecio) {
    	this.TblPrecioProducto.TblPrecios.setDescripcion(descripcionprecio);
    	this.descripcionprecio=descripcionprecio;
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
	   this.TblPrecioProducto.setCodigomedida(codigomedida);
    }

    
    
    public BigDecimal getEquivalencia() {
	   return this.TblProductoMedida.getEquivalencia();
    }

    public void setEquivalencia(BigDecimal equivalencia) {
	   this.TblProductoMedida.setEquivalencia(equivalencia);
    }
    
    public BigDecimal getEquivalenciavisible() {
	   return equivalenciavisible;
    }

    public void setEquivalenciavisible(BigDecimal equivalenciavisible) {
	   this.equivalenciavisible=equivalenciavisible;
    }    
    
    public int getIdProductoMedida() {
	   return this.TblProductoMedida.getId();
    } 
    
    public void setIdProductoMedida(int id) {
	   this.TblProductoMedida.setId(id);
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
    
    
    public tblTablePrecios[] LoadTblTablePrecios(tblProducto TblProducto){
    	tblPrecios[] TblPrecios=null;
    	tblTablePrecios[] medidas=null;
    	
    	if(TblProducto.TblProductoMedidas!=null && TblProducto.TblProductoMedidas.length>0){
    		   
     	   try{
     		   
 			   TblPrecios = new tblPrecios().Buscar(new tblPrecios());
 		   }catch(Exception e1) {
 			   // TODO Auto-generated catch block
 			   e1.printStackTrace();
 		   } 
     	   //System.out.println("Tamano: " + TblPrecios.length);
    	   medidas = new tblTablePrecios[TblProducto.TblProductoMedidas.length*TblPrecios.length];	
           int tipo=1;
   		   int i=0;
    	   for(int f=0;f<TblProducto.TblProductoMedidas.length;f++){
    		   if(tipo==0){
    			  tipo=1;
    		   }else{
    			  tipo=0; 
    		   }
    		   
               for(int j=0;j<TblPrecios.length;j++){
            	   medidas[i] = new tblTablePrecios();
            	   medidas[i].id = tipo;
            	   medidas[i].setCodigoprecio(TblPrecios[j].getCodigoprecio());
            	   medidas[i].setDescripcionprecio(TblPrecios[j].getDescripcion());
            	   double Equivalencia=0;
            	   if(TblProducto.getCosto()!=null && TblProducto.TblProductoMedidas[f].getEquivalencia()!=null){
            		  Equivalencia = TblProducto.getCosto().doubleValue()*TblProducto.TblProductoMedidas[f].getEquivalencia().doubleValue();
            	   }
            	   medidas[i].setCosto(BigDecimal.valueOf(Equivalencia));
            	      
            	   
            	   medidas[i].setPrecio(BigDecimal.valueOf(0));
            	   if(TblProducto.getUtilidadmaxima()!=null){
            	      medidas[i].setUtilidad(TblProducto.getUtilidadmaxima());
            	   }else{
            	      medidas[i].setUtilidad(BigDecimal.valueOf(0));
            	   }
            	   try{ 
            		   tblPrecioProducto TblPrecioProducto  = new tblPrecioProducto().BuscarPrecioProducto(TblProducto.TblPrecioProductos, TblProducto.getCodigoproducto(), TblProducto.TblProductoMedidas[f].getCodigomedida(),TblPrecios[j].getCodigoprecio());
            		   if(TblPrecioProducto==null){
            		      TblPrecioProducto = new tblPrecioProducto().buscarCodigo(TblProducto.getCodigoproducto(), TblProducto.TblProductoMedidas[f].getCodigomedida(),TblPrecios[j].getCodigoprecio());
            		   }
            		   if(TblPrecioProducto!=null){
						  if(TblPrecioProducto.getPrecio()!=null){
							 BigDecimal Impuesto = BigDecimal.valueOf(0);
							 if(TblProducto.TblProductoImpuestos!=null && TblProducto.TblProductoImpuestos.length>0){
								Impuesto = TblProducto.TblProductoImpuestos[0].TblImpuestos.getPorcentaje();
							 }				     		 
							 medidas[i].setPrecio(TblPrecioProducto.getPrecio());
							 medidas[i].setPrecioiva(ByosConversores.CalculoMontoMasImpuesto(TblPrecioProducto.getPrecio(), Impuesto));
						  }
						  if(TblPrecioProducto.getUtilidad()!=null && TblPrecioProducto.getUtilidad().doubleValue()>0){
						     medidas[i].setUtilidad(TblPrecioProducto.getUtilidad());  
						  }
					   }
				   }catch (Exception e1) {
					   // TODO Auto-generated catch block
					   e1.printStackTrace();
				   }
 		           medidas[i].setCodigomedida(TblProducto.TblProductoMedidas[f].getCodigomedida());
 		           medidas[i].setEquivalencia(TblProducto.TblProductoMedidas[f].getEquivalencia());
 		           medidas[i].setCodigoproducto(TblProducto.TblProductoMedidas[f].getCodigoproducto());

            	   if(j==0){
                	  Equivalencia=0;
                	  if(TblProducto.getCosto()!=null && TblProducto.TblProductoMedidas[f].getEquivalencia()!=null){
                		 Equivalencia = TblProducto.getCosto().doubleValue()*TblProducto.TblProductoMedidas[f].getEquivalencia().doubleValue();
                	  }
                	  medidas[i].setCostovisible(BigDecimal.valueOf(Equivalencia));            		  
            		  //medidas[i].setCostovisible(BigDecimal.valueOf(TblProducto.getCosto().doubleValue()*TblProducto.TblProductoMedidas[f].getEquivalencia().doubleValue())); 
    		          
            		  
            		  medidas[i].setDescripcion(TblProducto.TblProductoMedidas[f].TblUnidadMedida.getDescripcion());
    		          medidas[i].setEquivalenciavisible(TblProducto.TblProductoMedidas[f].getEquivalencia());
    		          medidas[i].setId(TblProducto.TblProductoMedidas[f].getId());
    		          if(TblProducto.TblProductoMedidasDefault!=null && TblProducto.TblProductoMedidasDefault.length>0){
    			         productosmedidasdefaultcodigo = "";
    			         productosmedidasdefaultdescripcion = "";
    			         String Separador="";
    			         int cantidad=0;
    			         for(int c=0;c<TblProducto.TblProductoMedidasDefault.length;c++){
    				         if(medidas[i].getCodigomedida().equals(TblProducto.TblProductoMedidasDefault[c].getCodigomedida())){   					  
    					        productosmedidasdefaultcodigo = productosmedidasdefaultcodigo + Separador + TblProducto.TblProductoMedidasDefault[c].getCodigodefault();
    				            productosmedidasdefaultdescripcion = productosmedidasdefaultdescripcion + Separador + TblProducto.TblProductoMedidasDefault[c].TblMedidasDefault.getDescripcion();
    				            Separador=",";
    				            cantidad++;
    				         }
    			         }
    			  
    			         medidas[i].TblProductoMedidaDefault = new tblProductoMedidaDefault[cantidad];
    			         cantidad=0;
    			         for(int c=0;c<TblProducto.TblProductoMedidasDefault.length;c++){
    				         if(medidas[i].getCodigomedida().equals(TblProducto.TblProductoMedidasDefault[c].getCodigomedida())){
    				  	        medidas[i].TblProductoMedidaDefault[cantidad] = new tblProductoMedidaDefault(); 
    					        medidas[i].TblProductoMedidaDefault[cantidad].setCodigoproducto(TblProducto.TblProductoMedidasDefault[c].getCodigoproducto());
    					        medidas[i].TblProductoMedidaDefault[cantidad].setCodigodefault(TblProducto.TblProductoMedidasDefault[c].getCodigodefault());
    					        medidas[i].TblProductoMedidaDefault[cantidad].setCodigomedida(TblProducto.TblProductoMedidasDefault[c].getCodigomedida());
    					        try{
							       medidas[i].TblProductoMedidaDefault[cantidad].TblMedidasDefault.buscarCodigo(TblProducto.TblProductoMedidasDefault[c].getCodigodefault());
							       medidas[i].TblProductoMedidaDefault[cantidad].TblUnidadMedida.buscarCodigo(TblProducto.TblProductoMedidasDefault[c].getCodigomedida());
						        }catch (Exception e) {			
							       e.printStackTrace();
						        }
    					        cantidad++;
    				         }
    			         }
    		          }
    			      
    		       }
            	   i++;
               }   
    	   }
    	}
    	
    	return medidas;
    }
    
   

    
    public ArrayList <tblTablePrecios> LoadTblTablePrecioArray(tblProducto TblProducto, String Tipo){
    	ArrayList <tblTablePrecios> ArrayPrecios = new ArrayList<tblTablePrecios>();
    	tblTablePrecios[] Medidas = LoadTblTablePrecios(TblProducto);	
    	if(Medidas!=null && Medidas.length>0){
    	   for(int f=0;f<Medidas.length;f++){
    		   ArrayPrecios.add(Medidas[f]);   
    	   }  
    	}
    	return ArrayPrecios;
    }
    
}