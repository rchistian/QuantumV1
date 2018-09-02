package com.modulo.productomedidadefault;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.modulo.medidasdefault.tblMedidasDefault;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosSql;
import com.byos.sys.util.utilString;

public class tblProductoMedidaDefault {
   private Integer id;
	
   private String codigoproducto;
	
   private String codigomedida;
   
   public tblUnidadMedida TblUnidadMedida = new tblUnidadMedida();
	
   private String codigodefault;
   
   public tblMedidasDefault TblMedidasDefault = new tblMedidasDefault();
   
   public tblProductoMedidaDefault() {
       limpiar();
   }

   public Integer getId() {
	   return this.id;
   }

   public void setId(Integer id) {
	  this.id = id;
   }
    
   public String getCodigoproducto() {
 	  return this.codigoproducto;
   }

   public void setCodigoproducto(String codigoproducto) {
 	  this.codigoproducto = codigoproducto;
   }
     
   public String getCodigomedida() {
 	  return this.codigomedida;
   }

   public void setCodigomedida(String codigomedida) {
 	  this.codigomedida = codigomedida;
   }

   public String getCodigodefault() {
	  return this.codigodefault;
   }

   public void setCodigodefault(String codigodefault) {
	  this.codigodefault = codigodefault;
   }   

   public tblUnidadMedida getTblUnidadMedida() {
	  return this.TblUnidadMedida;
   }

   public void setTblUnidadMedida(tblUnidadMedida TblUnidadMedida) {
	  this.TblUnidadMedida = TblUnidadMedida;
   } 

   public tblMedidasDefault getMedidasDefault(){
       return this.TblMedidasDefault;
   }
       
   public void setMedidasDefault(tblMedidasDefault TblMedidasDefault){
       this.TblMedidasDefault = TblMedidasDefault;       
   }
   
   public boolean buscarCodigo(String CodigoProducto, String CodigoDefault) throws Exception {
 	  PreparedStatement pstmt = null;
 	  ResultSet rs = null;
 	  
 	  String query = "Select id,codigoproducto,codigomedida,codigodefault from tblProductoMedidaDefault where codigoproducto=? and Codigodefault=?";

 	  Connection con = Conexion.getNuevaConexion();
 	  pstmt = con.prepareStatement(query);
 	  pstmt.setString(1,CodigoProducto);
 	  pstmt.setString(2,CodigoDefault);
 	  rs = pstmt.executeQuery();
 	  int size=0;
 	
 	  if(rs != null){
 	     if(rs.last()==true){
 		    size = rs.getRow();
 		    setId(rs.getInt("id"));
 		    setCodigoproducto(rs.getString("codigoproducto"));
            setCodigomedida(rs.getString("codigomedida"));
            setCodigodefault(rs.getString("codigodefault"));
		    TblUnidadMedida.buscarCodigo(getCodigomedida());
		    TblMedidasDefault.buscarCodigo(getCodigodefault());            
            ByosSql.CloseAll(con,pstmt,rs);
 		    return true;   
 	     }	    
 	  }
 	  ByosSql.CloseAll(con,pstmt,rs);
 	  limpiar(); 
 	  return false;
     }
  
     public boolean existeCodigo(String CodigoProducto, String CodigoMedia, String CodigoDefault) throws Exception {
 	  PreparedStatement pstmt = null;
 	  ResultSet rs = null;
 	  
 	  String query = "select Codigoproducto from tblProductoMedidaDefault where Codigoproducto=? and Codigomedida=? and Codigodefault=?";
 	
 	  Connection con = Conexion.getNuevaConexion();
 	  pstmt = con.prepareStatement(query);
 	  pstmt.setString(1,CodigoProducto);
      pstmt.setString(2,CodigoMedia);
      pstmt.setString(3,CodigoDefault);
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
     
     public boolean existeCodigo(String Codigo) throws Exception {
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
 	  
 	   String query = "select Codigoproducto from tblProductoMedidaDefault where Codigoproducto=?";
 	
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
 	   Connection con = Conexion.getNuevaConexion();
 	   
 	   //Ingresar nuevo
 	   if(existeCodigo(Codigo)){
 		  String query = "Delete From tblProductoMedidaDefault Where Codigoproducto='" + Codigo + "'";
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
 		  String query = "Delete From tblProductoMedidaDefault Where Codigoproducto='" + Codigo + "'";
 		  System.out.println(query);
 		  pstmt = con.prepareStatement(query);
 		  con.setAutoCommit(false);
          i = pstmt.executeUpdate();
 	      return utilString.SQL_ELIMINADO;			
 	   //}
 	   //return utilString.SQL_ERROR; 
     }         
     
     public String guardar(tblProductoMedidaDefault tbl) throws Exception {
 	   int i=0;	
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
 	   if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigomedida(), tbl.getCodigodefault())){
 		  String query = "Insert into tblProductoMedidaDefault(codigoproducto, codigomedida, codigodefault) values(?,?,?)";
 		  Connection con = Conexion.getNuevaConexion();
 		  pstmt = con.prepareStatement(query);
 		  con.setAutoCommit(false);
 		  pstmt.setString(1,tbl.getCodigoproducto());
 		  pstmt.setString(2,tbl.getCodigomedida());
 		  pstmt.setString(3,tbl.getCodigodefault());
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);
 	      return utilString.SQL_INSERTADO;
 	   }
 	   return utilString.SQL_ERROR;
     }
     
     //Guardar Medida Para Inventario
     public String guardar(tblProductoMedidaDefault tbl, Connection con) throws Exception {
 	   int i=0;	
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
 	   //if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigomedida(), tbl.getCodigodefault())){
 		  String query = "Insert into tblProductoMedidaDefault(codigoproducto, codigomedida, codigodefault) values(?,?,?)";
 		  con.setAutoCommit(false);
 		  pstmt = con.prepareStatement(query);
 		  pstmt.setString(1,tbl.getCodigoproducto());
 		  pstmt.setString(2,tbl.getCodigomedida());
 		  pstmt.setString(3,tbl.getCodigodefault());
          i = pstmt.executeUpdate();
          //System.out.println("Producto Medida Default: " + tbl.getCodigoproducto() + " " + tbl.getCodigomedida() + " " + tbl.getCodigodefault());
 	      return utilString.SQL_INSERTADO;
 	   //}
 	   //return utilString.SQL_ERROR;	
     }    
     
     public  ArrayList <tblProductoMedidaDefault> ProductoMedidasDefaultToArrayList(tblProductoMedidaDefault[] TblProductosMedidasDefault) throws Exception {
     	ArrayList <tblProductoMedidaDefault> ArrayProductoMedidasDefault = new ArrayList();
     	if(TblProductosMedidasDefault!=null){
     	   for(int f=0;f<TblProductosMedidasDefault.length;f++){
     		  ArrayProductoMedidasDefault.add(TblProductosMedidasDefault[f]);
     	   }
     	}else{
     		tblProductoMedidaDefault MedidasDefault = new tblProductoMedidaDefault();
     		MedidasDefault.setCodigomedida("");
     		ArrayProductoMedidasDefault.add(MedidasDefault);
     	}
     	return ArrayProductoMedidasDefault;
     }
     
     public tblProductoMedidaDefault[] Buscar(String Codigoproducto) throws Exception {
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
 	  
 	   String query = "Select id, codigoproducto, codigomedida, codigodefault from tblProductoMedidaDefault " +
 		              "Where codigoproducto = '" + Codigoproducto + "'  order by codigomedida,codigodefault";
 	  
 	   Connection con = Conexion.getNuevaConexion();
 	   pstmt = con.prepareStatement(query);
 	   rs = pstmt.executeQuery();
 	   int size=0;
 	
 	   if(rs!=null){
 	     if(rs.last()==true){
 		    size = rs.getRow();
 		    tblProductoMedidaDefault[] Registros = new tblProductoMedidaDefault[size];
 		    rs.first(); 
 			
 		    for(int f=0;f<size;f++){
 		        Registros[f] = new tblProductoMedidaDefault();
                Registros[f].setId(rs.getInt("id")); 
                Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
 		        Registros[f].setCodigomedida(rs.getString("codigomedida")); 
 		        Registros[f].setCodigodefault(rs.getString("codigodefault"));
 		        Registros[f].TblUnidadMedida.buscarCodigo(Registros[f].getCodigomedida());
 		        Registros[f].TblMedidasDefault.buscarCodigo(Registros[f].getCodigodefault());
 		        rs.next();
 		    }  
 		    ByosSql.CloseAll(con,pstmt,rs);
 		    return Registros;	       
 	     }
 	   }
 	   ByosSql.CloseAll(con,pstmt,rs);
 	   return null;
     }
 
     
     public ArrayList <tblProductoMedidaDefault> FiltroMedidasDefault(String InSql) throws Exception {
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
 	   ArrayList <tblProductoMedidaDefault> ArrayMedidasDefault = new ArrayList();
 	   String query = "Select id, codigoproducto, codigomedida, codigodefault from tblProductoMedidaDefault " +
 		              "Where " + InSql + " order by codigodefault";
 	   
 	   System.out.println(query);
 	  
 	   Connection con = Conexion.getNuevaConexion();
 	   pstmt = con.prepareStatement(query);
 	   rs = pstmt.executeQuery();
 	   int size=0;
 	
 	   if(rs!=null){
 	     if(rs.last()==true){
 		    size = rs.getRow();
 		    tblProductoMedidaDefault Registros;
 		    rs.first(); 
 			
 		    for(int f=0;f<size;f++){
 		        Registros = new tblProductoMedidaDefault();
                Registros.setId(rs.getInt("id")); 
                Registros.setCodigoproducto(rs.getString("codigoproducto"));
 		        Registros.setCodigomedida(rs.getString("codigomedida")); 
 		        Registros.setCodigodefault(rs.getString("codigodefault"));
 		        Registros.TblUnidadMedida.buscarCodigo(Registros.getCodigomedida());
 		        Registros.TblMedidasDefault.buscarCodigo(Registros.getCodigodefault());
 		        ArrayMedidasDefault.add(Registros);
 		        rs.next();
 		    }  
 		    ByosSql.CloseAll(con,pstmt,rs);
 		    return ArrayMedidasDefault;	       
 	     }
 	   }
 	   ByosSql.CloseAll(con,pstmt,rs);
 	   return null;
     }     
     
     
     public void limpiar(){
        setId(0);
        setCodigoproducto("");
        setCodigomedida("");
        setCodigodefault(""); 
     }   
     
     public void setProductoMedida(tblProductoMedidaDefault ProductoMedidaDefault){
        setId(ProductoMedidaDefault.getId());
        setCodigoproducto(ProductoMedidaDefault.getCodigoproducto());
        setCodigomedida(ProductoMedidaDefault.getCodigomedida());
        setCodigodefault(ProductoMedidaDefault.getCodigodefault());
        
	    TblUnidadMedida.setCodigomedida(ProductoMedidaDefault.TblUnidadMedida.getCodigomedida());
	    TblUnidadMedida.setDescripcion(ProductoMedidaDefault.TblUnidadMedida.getDescripcion());
	    
	    TblMedidasDefault.setCodigodefault(ProductoMedidaDefault.TblMedidasDefault.getCodigodefault());
	    TblMedidasDefault.setDescripcion(ProductoMedidaDefault.TblMedidasDefault.getDescripcion());
     }

}
