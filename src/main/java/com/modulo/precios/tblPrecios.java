package com.modulo.precios;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.componentes.ByosSql;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;


public class tblPrecios implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoprecio;

    private String descripcion;
    
    private String estado;

	private int id;

    public tblPrecios() {
       limpiar();
    }
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}    

    public String getCodigoprecio() {
	   return this.codigoprecio;
    }

    public void setCodigoprecio(String codigoprecio) {
	   this.codigoprecio = codigoprecio;
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
	  
	   String query = "Select id, codigoprecio, descripcion, estado from tblprecios where codigoprecio=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigoprecio(rs.getString("codigoprecio"));
             setDescripcion(rs.getString("descripcion"));
             setEstado(rs.getString("estado"));
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

	   String query = "select Codigoprecio from tblPrecios where Codigoprecio=?";
	
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
    
    public String eliminar(tblPrecios tbl) throws Exception{
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   //Ingresar nuevo
	   if(existeCodigo(tbl.getCodigoprecio())){
		  String query = "Delete From tblPrecios Where Codigoprecio='" + tbl.getCodigoprecio() + "'";
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
       
    public String guardar(tblPrecios tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigoprecio())){
			  String query = "Insert into tblPrecios(codigoprecio, descripcion, estado) values(?,?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigoprecio());
		 	  pstmt.setString(2,tbl.getDescripcion());
		 	  pstmt.setString(3,tbl.getEstado());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblPrecios set descripcion=?, estado=? where Codigoprecio='" + tbl.getCodigoprecio() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);			
		      pstmt.setString(1,tbl.getDescripcion());
		      pstmt.setString(2,tbl.getEstado());
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
  
    public String desincorporar(String Codigoprecio){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		  if(existeCodigo(Codigoprecio)){
			 String query = "Update tblprecios set estado=? where Codigoprecio='" + Codigoprecio + "'";
		     con.setAutoCommit(false);
		     pstmt = con.prepareStatement(query);			
		     pstmt.setString(1,"Inactivo");              
			 i = pstmt.executeUpdate();
			 con.commit();
		     setEstado("Inactivo"); 
		     ByosSql.CloseAll(con,pstmt,rs);
		     return utilString.SQL_DESINCORPORAR;  
		  } 
	   }catch (SQLException e){
	   	  e.printStackTrace();
	   }catch (Exception e){   
		  e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;	
    }
    
    public ArrayList <tblPrecios> BuscarArray(tblPrecios tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblPrecios> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigoprecio, " +
	   		"descripcion, " +
	   		"estado " +
	   		"from tblPrecios ";
	   if(tbl.getCodigoprecio()!=null &&!tbl.getCodigoprecio().equals("")){
		  InSql = InSql + " and codigoprecio like '" + tbl.getCodigoprecio().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		  InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getEstado()!=null && !tbl.getEstado().equals("")){
		  InSql = InSql + " and estado like '" + tbl.getEstado().replace("*", "%") + "'";
		  Estado=1;
	   }else{
		  InSql = InSql + " and (estado = 'Activo' or estado is null or estado='') " ;
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
		    	 tblPrecios Registros = new tblPrecios();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigoprecio(rs.getString("codigoprecio"));                    
		         Registros.setDescripcion(rs.getString("descripcion"));
		         Registros.setEstado(rs.getString("estado"));
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

    public tblPrecios[] Buscar(tblPrecios tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   tblPrecios[] Registros; 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigoprecio, " +
	   		"descripcion," +
	   		"estado " +
	   		"from tblPrecios ";
	   if(tbl.getCodigoprecio()!=null &&!tbl.getCodigoprecio().equals("")){
		  InSql = InSql + " and codigoprecio like '" + tbl.getCodigoprecio().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		  InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getEstado()!=null && !tbl.getEstado().equals("")){
		  InSql = InSql + " and estado like '" + tbl.getEstado().replace("*", "%") + "'";
		  Estado=1;
	   }else{
		  InSql = InSql + " and (estado = 'Activo' or estado is null or estado='') " ;
		  Estado=1;		  
	   }		   
	   if(Estado==1){
          query = query + " Where 1=1 " + InSql;
	   }
       query = query  + " order by id";
	  
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     Registros = new tblPrecios[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblPrecios();
                 Registros[f].setId(rs.getInt("id")); 
                 Registros[f].setCodigoprecio(rs.getString("codigoprecio"));                    
		         Registros[f].setDescripcion(rs.getString("descripcion"));
		         Registros[f].setEstado(rs.getString("estado"));
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
       setCodigoprecio("");
       setDescripcion("");  
       setEstado("");
    }   
    
    public void setPrecios(tblPrecios TblPrecios){
       setId(TblPrecios.getId());
       setCodigoprecio(TblPrecios.getCodigoprecio());
       setDescripcion(TblPrecios.getDescripcion());
       setEstado(TblPrecios.getEstado());
    }   
}