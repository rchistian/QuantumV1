package com.modulo.kardexdiario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.ajuste.tblAjuste;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;
import com.modulo.kardexdetallado.tblKardexDetallado;
import com.modulo.producto.tblProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosSql;

public class tblKardexDiario implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer id;
    
    String codigoproducto;
    
    String descripcion;
    
    BigDecimal entrada;
    
    BigDecimal salida;
    
    BigDecimal existencia;
    
    Date fecha;
    
    String codigomedida;
    
	BigDecimal costo;
    
    public String getCodigomedida() {
		return codigomedida;
	}

	public void setCodigomedida(String codigomedida) {
		this.codigomedida = codigomedida;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

    public tblKardexDiario() {
       limpiar();
    }

    public String getCodigoproducto() {
	   return this.codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
	   this.codigoproducto = codigoproducto;
    }
  
    public String getDescripcion() {
	   return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
    	this.descripcion=descripcion;
    }    

    public BigDecimal getEntrada() {
	   return this.entrada;
    }

    public void setEntrada(BigDecimal entrada) {
	   this.entrada = entrada;
    }

    public Date getFecha() {
	   return this.fecha;
    }

    public void setFecha(Date fecha) {
	   this.fecha = fecha;
    }
    
    public BigDecimal getExistencia() {
	   return this.existencia;
    }

    public void setExistencia(BigDecimal existencia) {
	   this.existencia = existencia;
    }
    
    public int getId() {
	   return this.id;
    } 
    
    public void setId(int id) {
	   this.id = id;
    }
    
    public void setSalida(BigDecimal salida) {
	  this.salida = salida;
    } 
    
    public BigDecimal getSalida() {
	   return this.salida;
    } 
        
    public boolean buscarCodigo(String CodigoProducto) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  String query = "Select id,codigoproducto,entrada,fecha,existencia,salida from tblproductomedida where codigoproducto=?";

	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,CodigoProducto);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs != null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    setId(rs.getInt("id"));
		    setCodigoproducto(rs.getString("codigoproducto"));
            setEntrada(rs.getBigDecimal("entrada"));
            setFecha(rs.getDate("fecha"));
            setExistencia(rs.getBigDecimal("existencia"));
            setSalida(rs.getBigDecimal("salida"));
            ByosSql.CloseAll(con,pstmt,rs);
		    return true;   
	     }	    
	  }
	  ByosSql.CloseAll(con,pstmt,rs);
	  limpiar(); 
	  return false;
    }
 
    public boolean existeCodigo(String Codigo, String Codigo01) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  String query = "select Codigoproducto from tblKardexDiario where Codigoproducto=?";
	
	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,Codigo);
      pstmt.setString(2,Codigo01);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs != null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    ByosSql.CloseAll(con,pstmt,rs);
		    return true;
	     }
	  }
	  ByosSql.CloseAll(con,pstmt,rs);
	  return false;
    }    
    
    public boolean existeCodigo(String Codigo, String Codigo01, Connection con) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  String query = "select Codigoproducto from tblKardexDiario where Codigoproducto=?";
	
	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,Codigo);
      pstmt.setString(2,Codigo01);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs != null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    ByosSql.CloseAll(con,pstmt,rs);
		    return true;
	     }
	  }
	  ByosSql.CloseAll(con,pstmt,rs);
	  return false;
    }    
    
    public boolean existeCodigo(String Codigo, Date Fecha) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "select Codigoproducto from tblKardexDiario where Codigoproducto=? and fecha=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   pstmt.setDate(2,Fecha);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow(); 
		    ByosSql.CloseAll(con,pstmt,rs);
		    return true;
	     }   
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return false;
    } 
    
    public boolean existeCodigo(String Codigo, Date Fecha, Connection con) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "select Codigoproducto from tblKardexDiario where Codigoproducto=? and fecha=?";
	
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   pstmt.setDate(2,Fecha);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow(); 
		    return true;
	     }   
	   }
	   return false;
    }     
    
    public String eliminar(String Codigo, Date Fecha) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   
	   //Ingresar nuevo
	   if(existeCodigo(Codigo, Fecha)){
		  String query = "Delete From tblKardexDiario Where Codigoproducto='" + Codigo + "'";
		  con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);
	      return utilString.SQL_ELIMINADO;			
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return utilString.SQL_ERROR; 
    }
    
    //Eliminar Medidas para Inventario
    public String eliminar(String Codigo, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   //Ingresar nuevo
	   //if(existeCodigo(Codigo)){
		  String query = "Delete From tblKardexDiario Where Codigoproducto='" + Codigo + "'";
		  System.out.println(query);
		  pstmt = con.prepareStatement(query);
		  con.setAutoCommit(false);
          i = pstmt.executeUpdate();
	      return utilString.SQL_ELIMINADO;			
	   //}
	   //return utilString.SQL_ERROR; 
    }         
    

    public String guardar(tblKardexDiario tbl, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   if(!existeCodigo(tbl.getCodigoproducto(),tbl.getFecha(),con)){
		  String query = "Insert into tblKardexDiario(codigoproducto, entrada, fecha, existencia, salida, codigomedida, costo, descripcion) values(?,?,?,?,?,?,?,?)";
		  pstmt = con.prepareStatement(query);
		  pstmt.setString(1,tbl.getCodigoproducto());
          pstmt.setBigDecimal(2,tbl.getEntrada());
          pstmt.setDate(3,tbl.getFecha());
          pstmt.setBigDecimal(4,tbl.getExistencia());
          pstmt.setBigDecimal(5,tbl.getSalida());
          pstmt.setString(6,tbl.getCodigomedida());
          pstmt.setBigDecimal(7,tbl.getCosto());
          pstmt.setString(8,tbl.getDescripcion());
          i = pstmt.executeUpdate();
	      return utilString.SQL_INSERTADO;
	   }else{
		  String query;
		  query = "Update tblKardexDiario set entrada=entrada+?, salida=salida+?, existencia=?, codigomedida=?, costo=?, descripcion=? Where codigoproducto=? and fecha=?"; 
		
		  pstmt = con.prepareStatement(query);
	      pstmt.setBigDecimal(1,tbl.getEntrada());
	      pstmt.setBigDecimal(2,tbl.getSalida());
	      pstmt.setBigDecimal(3,tbl.getExistencia());
          pstmt.setString(4,tbl.getCodigomedida());
          pstmt.setBigDecimal(5,tbl.getCosto());
          pstmt.setString(6,tbl.getDescripcion());
	      
		  pstmt.setString(7,tbl.getCodigoproducto());	      
	      pstmt.setDate(8,tbl.getFecha());	      
	  
	      i = pstmt.executeUpdate();	   
		  return utilString.SQL_MODIFICADO;
	   }
    }   
    
    public  ArrayList <tblKardexDiario> UnidadesMedidasToArrayList(tblKardexDiario[] TblProductosMedidas) throws Exception {
    	ArrayList <tblKardexDiario> ArrayUnidadesMedidas = new ArrayList();
    	if(TblProductosMedidas!=null){
    	   for(int f=0;f<TblProductosMedidas.length;f++){
    	  	   ArrayUnidadesMedidas.add(TblProductosMedidas[f]);
    	   }
    	}else{
    		tblKardexDiario Medidas = new tblKardexDiario();
    		Medidas.setExistencia(null);
    		Medidas.setEntrada(null);
    		Medidas.setFecha(null);
    		Medidas.setSalida(null);
    	    ArrayUnidadesMedidas.add(Medidas);
    	}
    	return ArrayUnidadesMedidas;
    }
    
    public tblKardexDiario[] Buscar(String Codigoproducto) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoproducto, entrada, fecha, existencia, salida, codigomedida, costo from tblKardexDiario " +
		              "Where codigoproducto = '" + Codigoproducto + "'  order by fecha";
	  
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    tblKardexDiario[] Registros = new tblKardexDiario[size];
		    rs.first(); 
			
		    for(int f=0;f<size;f++){
		        Registros[f] = new tblKardexDiario();
                Registros[f].setId(rs.getInt("id")); 
                Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
                Registros[f].setEntrada(rs.getBigDecimal("entrada")); 
                Registros[f].setFecha(rs.getDate("fecha"));
                Registros[f].setExistencia(rs.getBigDecimal("existencia"));
                Registros[f].setSalida(rs.getBigDecimal("salida"));
                Registros[f].setCodigomedida(rs.getString("codigomedida"));
                Registros[f].setCosto(rs.getBigDecimal("Costo"));  
		        rs.next();
		    }  
		    ByosSql.CloseAll(con,pstmt,rs);
		    return Registros;	       
	     }
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
 
    public tblKardexDiario[] Buscar(String Codigoproducto, Date FechaInicio, Date FechaFinal) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   String query = "Select codigoproducto, year(fecha), month(fecha), max(fecha) fecha, sum(entrada) entrada, sum(salida) salida from tblKardexDiario " +
		              "Where codigoproducto = '" + Codigoproducto + "' and  " +
		              		"fecha>='" + FechaInicio.toString() + "' and " +
		              		"fecha<='" + FechaFinal.toString() + "' " +
		               		"group by 1,2,3 " +
		              		"order by fecha";
	   
	   //System.out.println(query);
	  
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    tblKardexDiario[] Registros = new tblKardexDiario[size];
		    rs.first(); 
			
		    for(int f=0;f<size;f++){
		        Registros[f] = new tblKardexDiario();
                Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
                Registros[f].setEntrada(rs.getBigDecimal("entrada")); 
                Registros[f].setSalida(rs.getBigDecimal("salida"));                
                Registros[f].setFecha(rs.getDate("fecha"));               
		        rs.next();
		    }  
		    ByosSql.CloseAll(con,pstmt,rs);
		    return Registros;	       
	     }
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
    
    public void limpiar(){
       setId(0);
       setCodigoproducto("");
       setEntrada(null);
       setExistencia(null);
       setFecha(null);
       setSalida(null);
       setCodigomedida("");
       setCosto(null);
    }   
    
    
    public void setKardexDiario(tblKardexDiario KardexDiario){
       setId(KardexDiario.getId());
       setCodigoproducto(KardexDiario.getCodigoproducto());
       setEntrada(KardexDiario.getEntrada());
       setFecha(KardexDiario.getFecha());
       setExistencia(KardexDiario.getExistencia());
       setSalida(KardexDiario.getSalida());
       setCodigomedida(KardexDiario.getCodigomedida());
       setCosto(KardexDiario.getCosto());
    }

}
