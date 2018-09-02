package com.modulo.producto;

import java.math.BigDecimal;
import java.sql.Date;

public class tblEstadisticaProducto {

	public tblProducto TblProducto = new tblProducto();
    public String codigoproducto;
    public String descripcion;
    public BigDecimal cantidadvendida;
    public BigDecimal cantidadcomprada;
    public Date ultimacompra;
    public Date ultimaventa;
    public Date ultimocambioprecio;
    
    public tblEstadisticaProducto(tblProducto TblProducto){
    	this.TblProducto = TblProducto;
    	setCodigoproducto(TblProducto.getCodigoproducto());
    	setDescripcion(TblProducto.getDescripcioncorta());
    }
    
    public String getCodigoproducto(){
    	return codigoproducto;
    }
    
    public void setCodigoproducto(String codigoproducto){
    	this.codigoproducto=codigoproducto;
    }   
    
    public String getDescripcion(){
    	return descripcion;
    }
     
    public void setDescripcion(String descripcion){
    	this.descripcion=descripcion;
    } 
    
    public BigDecimal getCantidadvendida(){
    	return cantidadvendida;
    }
    
    public void setCantidadvendida(BigDecimal cantidadvendida){
    	this.cantidadvendida=cantidadvendida;
    }
    
    public BigDecimal getCantidadcomprada(){
    	return cantidadcomprada;
    }
    
    public void setCantidadcomprada(BigDecimal cantidadcomprada){
    	this.cantidadcomprada=cantidadcomprada;
    }  
    
    public Date getUltimacompra(){
    	return ultimacompra;
    }
    
    public void setUltimacompra(Date ultimacompra){
    	this.ultimacompra=ultimacompra;
    }   
    
    public Date getUltimaventa(){
    	return ultimaventa;
    }
    
    public void setUltimaventa(Date ultimaventa){
    	this.ultimaventa=ultimaventa;
    }  
    
    public Date getUltimocambioprecio(){
    	return ultimocambioprecio;
    }
    
    public void setUltimocambioprecio(Date ultimocambioprecio){
    	this.ultimocambioprecio=ultimocambioprecio;
    }
    
    public void setEstadiscticaProducto(tblProducto TblProducto){
    	setCodigoproducto(TblProducto.getCodigoproducto());
    	setDescripcion(TblProducto.getDescripcioncorta());
    }
    
}
