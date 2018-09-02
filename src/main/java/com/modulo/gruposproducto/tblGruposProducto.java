package com.modulo.gruposproducto;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.componentes.ByosSql;
import com.modulo.departamento.tblDepartamento;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;


/**
 * The persistent class for the tblgruposproductos database table.
 * 
 */

public class tblGruposProducto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigogrupo;

    private String descripcion;

    private int id;

    public tblGruposProducto() {   
    }

    public String getCodigogrupo() {
	   return this.codigogrupo;
    }

    public void setCodigogrupo(String codigogrupo) {
	   this.codigogrupo = codigogrupo;
    }

    public String getDescripcion() {
	   return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	   this.descripcion = descripcion;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }

    public boolean buscarCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigogrupo, descripcion from tblGruposProductos where codigogrupo=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!= null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigogrupo(rs.getString("codigogrupo"));
             setDescripcion(rs.getString("descripcion"));
             ByosSql.CloseAll(con,pstmt,rs);
		     return true; 
          }	
       } 
       limpiar();
       ByosSql.CloseAll(con,pstmt,rs);
	   return false;
    }
    
    public boolean existeCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "select Codigogrupo from tblGruposProductos where Codigogrupo=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
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
    
    public String eliminar(tblGruposProducto tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();	
	   //Ingresar nuevo
	   try{
		   if(existeCodigo(tbl.getCodigogrupo())){
			  String query = "Delete From tblgruposproductos Where Codigogrupo='" + tbl.getCodigogrupo() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_ELIMINADO;
		   }
	   }catch (SQLException e) {
		   e.printStackTrace();
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;
    }
    
    public String guardar(tblGruposProducto tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigogrupo())){
			  String query = "Insert into tblGruposProductos(codigogrupo, descripcion) values(?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigogrupo());
			  pstmt.setString(2,tbl.getDescripcion());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);          
		      return utilString.SQL_INSERTADO;	
		   }
		   else{
			  String query = "Update tblGruposProductos set descripcion=? where Codigogrupo='" + tbl.getCodigogrupo() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);			
		      pstmt.setString(1,tbl.getDescripcion());
			  i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);		  
		      return utilString.SQL_MODIFICADO;
		   }
	   }catch (SQLException e) {
		  e.printStackTrace();
	   }catch (Exception e) {
		  e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);		   
	   return utilString.SQL_ERROR;
    } 
    
    public ArrayList <tblGruposProducto> Buscar(tblGruposProducto tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblGruposProducto> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigogrupo, " +
	   		"descripcion " +
	   		"from tblGruposProductos ";
	   if(tbl.getCodigogrupo()!=null &&!tbl.getCodigogrupo().equals("")){
		  InSql = InSql + " and codigogrupo like '" + tbl.getCodigogrupo().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		  InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		  Estado=1;
	   }
	  
	   if(Estado==1){
          query = query + " Where 1=1 " + InSql;
	   }
       query = query  + " order by descripcion";
	  
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs != null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 tblGruposProducto Registros = new tblGruposProducto();
		         Registros = new tblGruposProducto();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigogrupo(rs.getString("codigogrupo"));                    
		         Registros.setDescripcion(rs.getString("descripcion"));
		         tblArray.add(Registros);
		         rs.next();
		     }   
	         ByosSql.CloseAll(con,pstmt,rs);		  
		     return tblArray;	   
          }
	   }
	   return null;
    }
     
    public void limpiar(){
       setId(0);
       setCodigogrupo("");
       setDescripcion("");      
    } 
 
    public void setGruposProducto(tblGruposProducto GruposProducto){
       setId(GruposProducto.getId());
       setCodigogrupo(GruposProducto.getCodigogrupo());
       setDescripcion(GruposProducto.getDescripcion());
    }
       
}