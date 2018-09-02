package com.modulo.sustanciascontrolada;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.componentes.ByosSql;
import com.modulo.deposito.tblDeposito;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;

/**
 * The persistent class for the tblsustanciascontroladas database table.
 * 
 */

public class tblSustanciasControladas implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigocontrol;

    private String descripcion;

    private String exigirrecipe;

    private Integer gradoseguridad;

    private int id;

    private String principioactivo;

    public tblSustanciasControladas() {
       limpiar();
    }
 
    public String getCodigocontrol() {
	   return this.codigocontrol;
    }

    public void setCodigocontrol(String codigocontrol) {
	   this.codigocontrol = codigocontrol;
    }

    public String getDescripcion() {
	   return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	   this.descripcion = descripcion;
    }

    public String getExigirrecipe() {
	   return this.exigirrecipe;
    }

    public void setExigirrecipe(String exigirrecipe) {
	   this.exigirrecipe = exigirrecipe;
    }

    public Integer getGradoseguridad() {
    	if(this.gradoseguridad==null){
    	   this.gradoseguridad=0;
    	}
	   return this.gradoseguridad;
    }

    public void setGradoseguridad(Integer gradoseguridad) {
	   this.gradoseguridad = gradoseguridad;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }

    public String getPrincipioactivo() {
	   return this.principioactivo;
    }

    public void setPrincipioactivo(String principioactivo) {
	   this.principioactivo = principioactivo;
    }
    
        
    public boolean buscarCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "Select id, codigocontrol, descripcion, principioactivo, gradoseguridad, exigirrecipe from tblsustanciascontroladas where codigocontrol=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	   if(rs != null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigocontrol(rs.getString("codigocontrol"));
             setDescripcion(rs.getString("descripcion"));
             setPrincipioactivo(rs.getString("principioactivo"));
             setGradoseguridad(rs.getInt("gradoseguridad"));
             setExigirrecipe(rs.getString("exigirrecipe"));
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
	  
	   String query = "select Codigocontrol from tblSustanciasControladas where Codigocontrol=?";
	
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
    
    public String eliminar(tblSustanciasControladas tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();	
	   //Ingresar nuevo
	   try{
		   if(existeCodigo(tbl.getCodigocontrol())){
		      String query = "Delete From tblSustanciasControladas Where Codigocontrol='" + tbl.getCodigocontrol() + "'";
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
       
   public String guardar(tblSustanciasControladas tbl) {
	 int i=0;	
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 Connection con = Conexion.getNuevaConexion();
	 try {
		if(!existeCodigo(tbl.getCodigocontrol())){
			String query = "Insert into tblSustanciasControladas(codigocontrol, descripcion, principioactivo, gradoseguridad, exigirrecipe) values(?,?,?,?,?)";
		    con.setAutoCommit(false);
		    pstmt = con.prepareStatement(query);
			pstmt.setString(1,tbl.getCodigocontrol());
			pstmt.setString(2,tbl.getDescripcion());
		    pstmt.setString(3,tbl.getPrincipioactivo());
		    pstmt.setInt(4,tbl.getGradoseguridad());
		    pstmt.setString(5,tbl.getExigirrecipe());
		    i = pstmt.executeUpdate();
		    con.commit();
		    ByosSql.CloseAll(con,pstmt,rs);
		    return utilString.SQL_INSERTADO;
		 }
		 else{
			String query = "Update tblSustanciasControladas set descripcion=?, principioactivo=?, gradoseguridad=?, exigirrecipe=? where Codigocontrol='" + tbl.getCodigocontrol() + "'";
		    con.setAutoCommit(false);
		    pstmt = con.prepareStatement(query);			
		    pstmt.setString(1,tbl.getDescripcion());
		    pstmt.setString(2,tbl.getPrincipioactivo());
		    pstmt.setInt(3,tbl.getGradoseguridad());
		    pstmt.setString(4,tbl.getExigirrecipe());                
			i = pstmt.executeUpdate();
		    con.commit();
		    ByosSql.CloseAll(con,pstmt,rs);
		    return utilString.SQL_MODIFICADO;
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
      
   public ArrayList <tblSustanciasControladas> Buscar(tblSustanciasControladas tbl) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String InSql="";
	  int Estado=0;
	  ArrayList <tblSustanciasControladas> tblArray = new ArrayList(); 
	
	  String query = "Select " +
	  		"id, " +
	  		"codigocontrol, " +
	  		"descripcion, " +
	  		"principioactivo, " +
	  		"gradoseguridad, " +
	  		"exigirrecipe " +
	  		"from tblSustanciasControladas ";
	  if(tbl.getCodigocontrol()!=null &&!tbl.getCodigocontrol().equals("")){
		 InSql = InSql + " and codigocontrol like '" + tbl.getCodigocontrol().replace("*", "%") + "'";
		 Estado=1;
	  }
	  if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		 InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		 Estado=1;
	  }
	  if(tbl.getPrincipioactivo()!=null && !tbl.getPrincipioactivo().equals("")){
		 InSql = InSql + " and principioactivo like '" + tbl.getPrincipioactivo().replace("*", "%") + "'";
		 Estado=1;
	  }
	  if(tbl.getGradoseguridad()!=null){
		 InSql = InSql + " and gradoseguridad like '" + tbl.getGradoseguridad().toString().replace("*", "%") + "'";
		 Estado=1;
	  }          
	  if(tbl.getExigirrecipe()!=null && !tbl.getExigirrecipe().equals("")){
		 InSql = InSql + " and exigirrecipe like '" + tbl.getExigirrecipe().replace("*", "%") + "'";
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
		    	tblSustanciasControladas Registros = new tblSustanciasControladas();
                Registros.setId(rs.getInt("id")); 
                Registros.setCodigocontrol(rs.getString("codigocontrol"));                    
		        Registros.setDescripcion(rs.getString("descripcion"));
                Registros.setPrincipioactivo(rs.getString("principioactivo"));
                Registros.setGradoseguridad(rs.getInt("gradoseguridad"));
                Registros.setExigirrecipe(rs.getString("exigirrecipe"));
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
       setCodigocontrol("");
       setDescripcion("");   
       setPrincipioactivo("");
       setGradoseguridad(null);
       setExigirrecipe("");       
   }   
    
   public void setSustanciasControladas(tblSustanciasControladas SustanciasControlada){
       setId(SustanciasControlada.getId());
       setCodigocontrol(SustanciasControlada.getCodigocontrol());
       setDescripcion(SustanciasControlada.getDescripcion());
       setPrincipioactivo(SustanciasControlada.getPrincipioactivo());
       setGradoseguridad(SustanciasControlada.getGradoseguridad());
       setExigirrecipe(SustanciasControlada.getExigirrecipe());
   }

}