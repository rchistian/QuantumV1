package com.modulo.unidadmedida;

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
 * The persistent class for the tblunidadmedida database table.
 * 
 */

public class tblUnidadMedida implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigomedida;

    private String descripcion;
            
    private String pesable;        

    private int id;
    
    private String estado;

    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public tblUnidadMedida() {
       limpiar();
    }

    public String getCodigomedida() {
	   return this.codigomedida;
    }

    public void setCodigomedida(String codigomedida) {
       this.codigomedida = codigomedida;
    }

    public String getDescripcion() {
	   return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	   this.descripcion = descripcion;
    }
    
    public String getPesable() {
	   return this.pesable;
    }

    public void setPesable(String pesable) {
	   this.pesable = pesable;
    }
    
    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }

    public boolean buscarCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigomedida, descripcion, pesable, estado from tblUnidadMedida where codigomedida=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!= null){
	      if(rs.last()==true){
	    	 size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigomedida(rs.getString("codigomedida"));
             setDescripcion(rs.getString("descripcion"));
             setPesable(rs.getString("pesable")); 
             setEstado(rs.getString("estado")); 
             ByosSql.CloseAll(con,pstmt,rs);
		     return true;   
	      }	          
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
       limpiar();
	   return false;
    }
    
    public boolean existeCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigomedida from tblunidadmedida where Codigomedida=?";
	
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
    
    public String eliminar(tblUnidadMedida tbl) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   //Ingresar nuevo
	   if(existeCodigo(tbl.getCodigomedida())){
		  String query = "Delete From tblunidadmedida Where Codigomedida='" + tbl.getCodigomedida() + "'";
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
    
    public String desincorporar(String Codigomedida){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		  if(existeCodigo(Codigomedida)){
			 String query = "Update tblUnidadMedida set estado=? where Codigomedida='" + Codigomedida + "'";
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

    public String guardar(tblUnidadMedida tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigomedida())){
			  String query = "Insert into tblUnidadMedida(codigomedida, descripcion, pesable, estado) values(?,?,?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigomedida());
			  pstmt.setString(2,tbl.getDescripcion());
			  pstmt.setString(3,tbl.getPesable());
			  pstmt.setString(4,tbl.getEstado()); 
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;	
		   }
		   else{
			  String query = "Update tblUnidadMedida set descripcion=?, pesable=?, estado=? where Codigomedida='" + tbl.getCodigomedida() + "'";
			  pstmt = con.prepareStatement(query);
			  con.setAutoCommit(false);
		      pstmt.setString(1,tbl.getDescripcion());
		      pstmt.setString(2,tbl.getPesable());
		      pstmt.setString(3,tbl.getEstado());
			  i = pstmt.executeUpdate();
			  con.commit();
			  ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_MODIFICADO;	   
		   }
	   }catch (SQLException e) {
		   e.printStackTrace();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;	
    }
    
    public ArrayList <tblUnidadMedida> Buscar(tblUnidadMedida tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblUnidadMedida> tblArray = new ArrayList();
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigomedida, " +
	   		"descripcion, " +
	   		"pesable, " +
	   		"estado " +
	   		"from tblUnidadMedida ";
	   if(tbl.getCodigomedida()!=null &&!tbl.getCodigomedida().equals("")){
		  InSql = InSql + " and codigomedida like '" + tbl.getCodigomedida().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		  InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getPesable()!=null && !tbl.getPesable().equals("")){
		  InSql = InSql + " and pesable like '" + tbl.getPesable().replace("*", "%") + "'";
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
		    	tblUnidadMedida Registros = new tblUnidadMedida();
                Registros.setId(rs.getInt("id")); 
                Registros.setCodigomedida(rs.getString("codigomedida"));                    
		        Registros.setDescripcion(rs.getString("descripcion"));                    
		        Registros.setPesable(rs.getString("pesable"));
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
     
    public void limpiar(){
      setId(0);
      setCodigomedida("");
      setDescripcion("");
      setPesable(""); 
      setEstado("");
    } 
 
    public void setUnidadMedida(tblUnidadMedida UnidadMedida){
       setId(UnidadMedida.getId());
       setCodigomedida(UnidadMedida.getCodigomedida());
       setDescripcion(UnidadMedida.getDescripcion());
       setPesable(UnidadMedida.getPesable());
       setEstado(UnidadMedida.getEstado());
    }  
    
    public String StringCodigo(){
    	return getCodigomedida() + " - " + getDescripcion();
    }
    
  
}