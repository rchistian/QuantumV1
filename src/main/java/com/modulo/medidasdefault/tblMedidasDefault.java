package com.modulo.medidasdefault;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.componentes.ByosSql;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;

public class tblMedidasDefault {
    private static final long serialVersionUID = 1L;

    private String codigodefault;

    private String descripcion;

    private int id;

    public tblMedidasDefault() {
       limpiar();
    }

    public String getCodigodefault() {
	   return this.codigodefault;
    }

    public void setCodigodefault(String codigodefault) {
	   this.codigodefault = codigodefault;
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
	  
	   String query = "Select id, codigodefault, descripcion from tblmedidasdefault where codigodefault=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigodefault(rs.getString("codigodefault"));
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

	   String query = "select codigodefault from tblmedidasdefault where codigodefault=?";
	
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
    
    public String eliminar(tblMedidasDefault tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();	
	   //Ingresar nuevo
	   try{
		   if(existeCodigo(tbl.getCodigodefault())){
			  String query = "Delete From tblmedidasdefault Where codigodefault='" + tbl.getCodigodefault() + "'";
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
       
    public String guardar(tblMedidasDefault tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigodefault())){
			  String query = "Insert into tblmedidasdefault(codigodefault, descripcion) values(?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigodefault());
		 	  pstmt.setString(2,tbl.getDescripcion());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblmedidasdefault set descripcion=? where codigodefault='" + tbl.getCodigodefault() + "'";
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
    
    public ArrayList <tblMedidasDefault> Buscar(String[] Medidas) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblMedidasDefault> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigodefault, " +
	   		"descripcion " +
	   		"from tblmedidasdefault ";

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
		    	 tblMedidasDefault Registros = new tblMedidasDefault();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigodefault(rs.getString("codigodefault"));                    
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
    
    
    
    public ArrayList <tblMedidasDefault> FiltroMedidasDefault(String InSql) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   ArrayList <tblMedidasDefault> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigodefault, " +
	   		"descripcion " +
	   		"from tblmedidasdefault ";

	   if(InSql!=null && !InSql.equals("")){
          query = query + " Where " + InSql + " order by descripcion";
	   }else{
          query = query + " order by descripcion";
       }
	   
	   System.out.println(query);
	   
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery(query);
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 tblMedidasDefault Registros = new tblMedidasDefault();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigodefault(rs.getString("codigodefault"));                    
		         Registros.setDescripcion(rs.getString("descripcion"));
		         tblArray.add(Registros);
		         rs.next();
		     }
		     ByosSql.CloseAll(con,pstmt,rs);
		     return tblArray;	       
	      } 
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return tblArray;
    }    
    
    
             
    public ArrayList <tblMedidasDefault> Buscar(tblMedidasDefault tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblMedidasDefault> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigodefault, " +
	   		"descripcion " +
	   		"from tblmedidasdefault ";
	   if(tbl.getCodigodefault()!=null &&!tbl.getCodigodefault().equals("")){
		  InSql = InSql + " and codigodefault like '" + tbl.getCodigodefault().replace("*", "%") + "'";
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
		    	 tblMedidasDefault Registros = new tblMedidasDefault();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigodefault(rs.getString("codigodefault"));                    
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
       setCodigodefault("");
       setDescripcion("");    
    }   
    
    public void setMedidasDefault(tblMedidasDefault Modulo){
       setId(Modulo.getId());
       setCodigodefault(Modulo.getCodigodefault());
       setDescripcion(Modulo.getDescripcion());
    }   

}
