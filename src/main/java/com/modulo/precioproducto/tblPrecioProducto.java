package com.modulo.precioproducto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.modulo.precios.tblPrecios;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosSql;
import com.byos.sys.util.utilString;


/**
 * The persistent class for the tblprecioproducto database table.
 * 
 */

public class tblPrecioProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public tblPrecios TblPrecios = new tblPrecios();

    private String codigomedida;

    private String codigoprecio;

    private String codigoproducto;

    private int id;

    private BigDecimal precio;
    
    private BigDecimal utilidad;

    public tblPrecioProducto() {
        
    }

    public String getCodigomedida() {
	   return this.codigomedida;
    }

    public void setCodigomedida(String codigomedida) {
	   this.codigomedida = codigomedida;
    }

    public String getCodigoprecio() {
	   return this.codigoprecio;
    }

    public void setCodigoprecio(String codigoprecio) {
	   this.codigoprecio = codigoprecio;
    }

    public String getCodigoproducto() {
	   return this.codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
	   this.codigoproducto = codigoproducto;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }

    public BigDecimal getPrecio() {
	   return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
	   this.precio = precio;
    }

    public BigDecimal getUtilidad() {
	   return this.utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
	   this.utilidad = utilidad;
    }  
    
    public tblPrecioProducto getPrecioProducto(){
    	return this;
    }
    
    
    
    public boolean buscarCodigoPrecioProducto(String Codigo, String CodigoMedida, String CodigoPrecio) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoproducto, codigoprecio, codigomedida, precio, utilidad from tblprecioproducto where codigoproducto=?  and codigomedida=? and codigoprecio=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   pstmt.setString(2,CodigoMedida);
	   pstmt.setString(3,CodigoPrecio);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigoproducto(rs.getString("codigoproducto"));
             setCodigoprecio(rs.getString("codigoprecio"));
             setCodigomedida(rs.getString("codigomedida"));
             setCodigomedida(rs.getString("precio"));
             setCodigomedida(rs.getString("utilidad")); 
             ByosSql.CloseAll(con,pstmt,rs);
		     return true;               
	      }	          
	   }        
	   limpiar();
	   ByosSql.CloseAll(con,pstmt,rs);
	   return false;
    }
 
    public tblPrecioProducto buscarCodigo(String Codigo, String CodigoMedida, String CodigoPrecio) throws Exception{
       tblPrecioProducto TblPrecioProducto = new tblPrecioProducto();
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoproducto, codigoprecio, codigomedida, precio, utilidad from tblprecioproducto where codigoproducto=? and codigomedida=? and codigoprecio=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
       pstmt.setString(2,CodigoMedida);
       pstmt.setString(3,CodigoPrecio);
	   rs = pstmt.executeQuery();
	   int size=0;
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     TblPrecioProducto.setId(rs.getInt("id"));
		     TblPrecioProducto.setCodigoproducto(rs.getString("codigoproducto"));
             TblPrecioProducto.setCodigoprecio(rs.getString("codigoprecio"));
             TblPrecioProducto.setCodigomedida(rs.getString("codigomedida"));
             TblPrecioProducto.setPrecio(rs.getBigDecimal("precio"));
             TblPrecioProducto.setUtilidad(rs.getBigDecimal("utilidad")); 
             TblPrecioProducto.TblPrecios.buscarCodigo(TblPrecioProducto.getCodigoprecio());
             ByosSql.CloseAll(con,pstmt,rs);
		     return TblPrecioProducto;    
	      }	   
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
    
    public boolean existeCodigo(String Codigo, String CodigoMedida, String CodigoPrecio) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoproducto, codigoprecio, codigomedida, precio, utilidad from tblprecioproducto where codigoproducto=?  and codigomedida=? and codigoprecio=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   pstmt.setString(2,CodigoMedida);
	   pstmt.setString(3,CodigoPrecio);
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
    
    public boolean existeCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "select Codigoproducto from tblPrecioProducto where Codigoproducto=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
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
    
    public String eliminar(String Codigo) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   //Ingresar nuevo
	   if(existeCodigo(Codigo)){
		  String query = "Delete From tblPrecioProducto Where Codigoproducto='" + Codigo + "'";
		  Connection con = Conexion.getNuevaConexion();
		  con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);        
	      return utilString.SQL_ELIMINADO;
	   }
	   return utilString.SQL_ERROR;
    }
       
    public String guardar(tblPrecioProducto tbl, Connection con) throws Exception{
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigomedida(), tbl.getCodigoprecio())){
		  String query = "Insert into tblPrecioProducto(codigoproducto, Codigoprecio, codigomedida, precio, utilidad) values(?,?,?,?,?)";
		  con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);
		  pstmt.setString(1,tbl.getCodigoproducto());
          pstmt.setString(2,tbl.getCodigoprecio());
		  pstmt.setString(3,tbl.getCodigomedida());
          pstmt.setBigDecimal(4,tbl.getPrecio());
          pstmt.setBigDecimal(5,tbl.getUtilidad());
          i = pstmt.executeUpdate();
          //System.out.println("Insertado: " + tbl.getCodigoprecio() + " " + tbl.getPrecio());
          return utilString.SQL_INSERTADO;
	   }
  	   else{
		  String query = "Update tblPrecioProducto set precio=?, utilidad=? where Codigoproducto='" + tbl.getCodigoproducto() + "' and Codigomedida='" + tbl.getCodigomedida() + "' and Codigoprecio='" + tbl.getCodigoprecio() + "'";
	      con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);			
          pstmt.setBigDecimal(1,tbl.getPrecio());
          pstmt.setBigDecimal(2,tbl.getUtilidad());
		  i = pstmt.executeUpdate();	
		  //System.out.println("Modificado: " + tbl.getCodigoprecio());
		  if(i>0){
	         return utilString.SQL_MODIFICADO;
		  }
		  else{
	         return utilString.SQL_ERROR;	
		  }
  	   }
    }
      
    public tblPrecioProducto[] Buscar(String Codigoproducto) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoproducto, codigoprecio, codigomedida, precio, utilidad from tblPrecioProducto " +
		              "Where codigoproducto = '" + Codigoproducto + "'  order by codigomedida";
	  
	   Connection con = Conexion.getNuevaConexion();
	   con.setAutoCommit(false);
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs != null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     tblPrecioProducto[] Registros = new tblPrecioProducto[size];
		     rs.first(); 
			
		     for(int f=0;f<size;f++){
		         Registros[f] = new tblPrecioProducto();
                 Registros[f].setId(rs.getInt("id")); 
                 Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
                 Registros[f].setCodigoprecio(rs.getString("codigoprecio")); 
		         Registros[f].setCodigomedida(rs.getString("codigomedida")); 
                 Registros[f].setPrecio(rs.getBigDecimal("precio")); 
                 Registros[f].setUtilidad(rs.getBigDecimal("utilidad"));
                 Registros[f].TblPrecios.buscarCodigo(Registros[f].getCodigoprecio());
		         rs.next();
		     }
		     con.commit();
		     ByosSql.CloseAll(con,pstmt,rs); 		     
		     return Registros;	   
	      }
	   }
       con.commit();
       ByosSql.CloseAll(con,pstmt,rs); 	   
	   return null;
    }
   
    public void limpiar(){
       setId(0);
       setCodigoproducto("");
       setCodigomedida("");  
       setCodigoprecio("");
       setPrecio(null);
       setUtilidad(null);
    }   
    
    public void setPrecioProducto(tblPrecioProducto PrecioProducto){
       setId(PrecioProducto.getId());
       setCodigoproducto(PrecioProducto.getCodigoproducto());
       setCodigoprecio(PrecioProducto.getCodigoprecio());
       setCodigomedida(PrecioProducto.getCodigomedida());
       setPrecio(PrecioProducto.getPrecio());
       setUtilidad(PrecioProducto.getUtilidad());      
    } 
    
    
    public tblPrecioProducto BuscarPrecioProducto(tblPrecioProducto[] TblPrecioProductos, String Codigoproducto, String Codigomedida, String Codigoprecio){
    	if(TblPrecioProductos!=null && TblPrecioProductos.length>0){
    	   for(int f=0;f<TblPrecioProductos.length;f++){
    		   if(TblPrecioProductos[f].getCodigoproducto().equals(Codigoproducto) && TblPrecioProductos[f].getCodigomedida().equals(Codigomedida) && TblPrecioProductos[f].getCodigoprecio().equals(Codigoprecio)){
    		      return TblPrecioProductos[f];
    		   }
    	   }
    	}
    	return null;
    }
       
}