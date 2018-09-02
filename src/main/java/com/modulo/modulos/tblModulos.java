package com.modulo.modulos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;
import com.modulo.componentes.ByosSql;

public class tblModulos {

	private Integer id;
	
	private Integer codigomodulo;
	
	private String descripcion;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigomodulo() {
		return codigomodulo;
	}

	public void setCodigomodulo(Integer codigomodulo) {
		this.codigomodulo = codigomodulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public tblModulos(){
		
	}
	
	public tblModulos(Integer id, Integer codigomodulo, String descripcion){
		this.id = id;
		this.codigomodulo = codigomodulo;
		this.descripcion = descripcion;
	}
	public void setTblModulos(tblModulos Tbl){
	    this.id = Tbl.getId();
	    this.codigomodulo = Tbl.getCodigomodulo();			
	    this.descripcion = Tbl.getDescripcion();
	}
	
	public void Limpiar(){
		setId(null);
		setCodigomodulo(null);
		setDescripcion("");
	}	
	
    public boolean buscarCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigomodulo, descripcion, estado from tblmodulos where codigomodulo=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigomodulo(rs.getInt("codigoprecio"));
             setDescripcion(rs.getString("descripcion"));
             ByosSql.CloseAll(con,pstmt,rs);
		     return true; 
          }	
       }
	   ByosSql.CloseAll(con,pstmt,rs);
       Limpiar();
	   return false;
    }
    
    public ArrayList<tblModulos> buscarTodos() throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   ArrayList<tblModulos> ArrayTblModulos = new ArrayList<tblModulos>();
	   String query = "Select id, codigomodulo, descripcion from tblmodulos order by codigomodulo";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
		  if(rs.last()==true){
			 size = rs.getRow();    
			 rs.first(); 			
			 for(int f=0;f<size;f++){
				 tblModulos TblModulos = new tblModulos();
				 TblModulos.setId(rs.getInt("id"));
				 TblModulos.setCodigomodulo(rs.getInt("codigomodulo"));
				 TblModulos.setDescripcion(rs.getString("descripcion"));
				 ArrayTblModulos.add(TblModulos);
				 rs.next();
			 }
          }	
       }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return ArrayTblModulos;
    }  
    
    public boolean existeCodigo(Integer Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigomodulo from tblmodulos where Codigomodulo=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setInt(1,Codigo);
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
    
    public String guardar(tblModulos tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigomodulo())){
			  String query = "Insert into tblmodulos(codigomodulo, descripcion) values(?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setInt(1,tbl.getCodigomodulo());
		 	  pstmt.setString(2,tbl.getDescripcion());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblmodulos set descripcion=? where Codigomodulo='" + tbl.getCodigomodulo() + "'";
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
    
}
