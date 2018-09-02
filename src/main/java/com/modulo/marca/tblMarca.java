package com.modulo.marca;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.componentes.ByosSql;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;


/**
 * The persistent class for the tblmarcas database table.
 * 
 */

public class tblMarca implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigomarca;

    private String descripcion;

    private int id;

    public tblMarca() {
       limpiar();
    }

    public String getCodigomarca() {
	   return this.codigomarca;
    }

    public void setCodigomarca(String codigomarca) {
	   this.codigomarca = codigomarca;
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
	  
	   String query = "Select id, codigomarca, descripcion from tblmarcas where codigomarca=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigomarca(rs.getString("codigomarca"));
             setDescripcion(rs.getString("descripcion"));
             ByosSql.CloseAll(con,pstmt,rs);
		     return true; 
          }	
       }
	   ByosSql.CloseAll(con,pstmt,rs);
       limpiar();
	   return false;
    }
    
    public boolean existeCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigomarca from tblMarcas where Codigomarca=?";
	
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
    
    public String eliminar(tblMarca tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(existeCodigo(tbl.getCodigomarca())){
			  String query = "Delete From tblMarcas Where Codigomarca='" + tbl.getCodigomarca() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_ELIMINADO;
		   }
	
	   }catch (SQLException e) {
		   e.printStackTrace();
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;
    }
       
    public String guardar(tblMarca tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigomarca())){
			  String query = "Insert into tblMarcas(codigomarca, descripcion) values(?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigomarca());
		 	  pstmt.setString(2,tbl.getDescripcion());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblMarcas set descripcion=? where Codigomarca='" + tbl.getCodigomarca() + "'";
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
          
    public ArrayList <tblMarca> Buscar(tblMarca tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblMarca> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigomarca, " +
	   		"descripcion " +
	   		"from tblMarcas ";
	   if(tbl.getCodigomarca()!=null &&!tbl.getCodigomarca().equals("")){
		  InSql = InSql + " and codigomarca like '" + tbl.getCodigomarca().replace("*", "%") + "'";
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
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 tblMarca Registros = new tblMarca();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigomarca(rs.getString("codigomarca"));                    
		         Registros.setDescripcion(rs.getString("descripcion"));
		         tblArray.add(Registros);
		         rs.next();
		     }
		     ByosSql.CloseAll(con,pstmt,rs);
		     return tblArray;	       
	      } 
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
   
    public void limpiar(){
       setId(0);
       setCodigomarca("");
       setDescripcion("");    
    }   
    
    public void setMarca(tblMarca Marca){
       setId(Marca.getId());
       setCodigomarca(Marca.getCodigomarca());
       setDescripcion(Marca.getDescripcion());
    }   
}