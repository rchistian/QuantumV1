package com.modulo.codigobarra;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosSql;
import com.byos.sys.util.utilString;


/**
 *
 * @author Chistian
 */

public class tblCodigoBarra implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String codigoproducto;

    private String codigobarra;

    private String descripcion;

    public tblCodigoBarra(){
    	limpiar();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getCodigobarra() {
        return codigobarra;
    }

    public void setCodigobarra(String codigobarra) {
        this.codigobarra = codigobarra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean buscarCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id,codigoproducto,codigobarra,descripcion from tblcodigobarra where codigoproducto=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs != null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigoproducto(rs.getString("codigoproducto"));
             setCodigobarra(rs.getString("codigobarra"));
             setDescripcion(rs.getString("descripcion"));   
             ByosSql.CloseAll(con,pstmt,rs);
		     return true; 
	      }	
	   } 
	   limpiar();
	   ByosSql.CloseAll(con,pstmt,rs);
	   return false;
    }
 
    public tblCodigoBarra buscarCodigo(String Codigo, String CodigoBarra) throws Exception {
       tblCodigoBarra TblCodigoBarra = new tblCodigoBarra();
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id,codigoproducto,codigobarra,descripcion from tblcodigobarra where codigoproducto<>? and codigobarra=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
       pstmt.setString(2,CodigoBarra);
	   rs = pstmt.executeQuery();
	   int size=0;
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     TblCodigoBarra.setId(rs.getInt("id"));
		     TblCodigoBarra.setCodigoproducto(rs.getString("codigoproducto"));
             TblCodigoBarra.setCodigobarra(rs.getString("codigobarra"));
             TblCodigoBarra.setDescripcion(rs.getString("descripcion")); 
             ByosSql.CloseAll(con,pstmt,rs);
		     return TblCodigoBarra;   
	      }	         
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
   
    
    public boolean existeCodigo(String Codigo, String Codigo01) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigoproducto from tblCodigoBarra where Codigoproducto=? and Codigobarra=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
       pstmt.setString(2,Codigo01);
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
	  
	   String query = "select Codigoproducto from tblCodigoBarra where Codigoproducto=?";
	
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
   		  String query = "Delete From tblCodigoBarra Where Codigoproducto='" + Codigo + "'";
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

    //Eliminar Codigos de Barras Para Inventario
    public String eliminar(String Codigo, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   //Ingresar nuevo
	   if(existeCodigo(Codigo)){
   		  String query = "Delete From tblCodigoBarra Where Codigoproducto='" + Codigo + "'";
		  pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();          
	      return utilString.SQL_ELIMINADO;		
	   }
	   return utilString.SQL_ERROR;
    }    
    
    public String guardar(tblCodigoBarra tbl) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigobarra())){
		  String query = "Insert into tblCodigoBarra(codigoproducto, codigobarra, descripcion) values(?,?,?)";
		  Connection con = Conexion.getNuevaConexion();
		  con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);
		  pstmt.setString(1,tbl.getCodigoproducto());
		  pstmt.setString(2,tbl.getCodigobarra());
          pstmt.setString(3,tbl.getDescripcion());
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);            
	      return utilString.SQL_INSERTADO;
	   }
	   else{
		  String query = "Update tblCodigoBarra set descripcion=? where Codigoproducto='" + tbl.getCodigoproducto() + "' and Codigobarra='" + tbl.getCodigobarra() + "'";
		  Connection con = Conexion.getNuevaConexion();
		  con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);			
          pstmt.setString(1,tbl.getDescripcion());
		  i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);  	   
	      if(i>0){
	         return utilString.SQL_MODIFICADO;
	      }
	      else{
	         return utilString.SQL_ERROR;	  
	      }	
	   }
    }

    //Guardar Codigo de Barras Para Inventario
    public String guardar(tblCodigoBarra tbl, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigobarra())){
		  String query = "Insert into tblCodigoBarra(codigoproducto, codigobarra, descripcion) values(?,?,?)";
		  pstmt = con.prepareStatement(query);
		  pstmt.setString(1,tbl.getCodigoproducto());
		  pstmt.setString(2,tbl.getCodigobarra());
          pstmt.setString(3,tbl.getDescripcion());
          i = pstmt.executeUpdate();          
	      return utilString.SQL_INSERTADO;
	   }
	   else{
		  String query = "Update tblCodigoBarra set descripcion=? where Codigoproducto='" + tbl.getCodigoproducto() + "' and Codigobarra='" + tbl.getCodigobarra() + "'";
		  pstmt = con.prepareStatement(query);			
          pstmt.setString(1,tbl.getDescripcion());
		  i = pstmt.executeUpdate(); 	   
	      if(i>0){
	         return utilString.SQL_MODIFICADO;
	      }
	      else{
	         return utilString.SQL_ERROR;	  
	      }	
	   }
    }
    
    public ArrayList <tblCodigoBarra> BuscarArrayList(String CodigoProducto) throws Exception {
    	tblCodigoBarra[] TblCodigoBarras = Buscar(CodigoProducto);
    	ArrayList <tblCodigoBarra> ArrayTblCodigoBarras = new ArrayList <tblCodigoBarra> ();
    	if(TblCodigoBarras!=null && TblCodigoBarras.length>0){
    	   for(int f=0;f<TblCodigoBarras.length;f++){
    		   ArrayTblCodigoBarras.add(TblCodigoBarras[f]);
    	   }
    	}
    	
    	return ArrayTblCodigoBarras;
    }
    
   public tblCodigoBarra[] Buscar(String Codigoproducto) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  String query = "Select id, codigoproducto, codigobarra, descripcion from tblCodigoBarra " +
		             "Where codigoproducto = '" + Codigoproducto + "'  order by codigobarra";
	  
	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    tblCodigoBarra[] Registros = new tblCodigoBarra[size];
		    rs.first(); 
			
		    for(int f=0;f<size;f++){
		        Registros[f] = new tblCodigoBarra();
                Registros[f].setId(rs.getInt("id")); 
                Registros[f].setCodigoproducto(rs.getString("codigoproducto"));                    
		        Registros[f].setCodigobarra(rs.getString("codigobarra")); 
                Registros[f].setDescripcion(rs.getString("descripcion")); 
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
       setCodigobarra("");  
       setDescripcion("");
   }   
    
   public void setCodigoBarra(tblCodigoBarra CodigoBarra){
       setId(CodigoBarra.getId());
       setCodigoproducto(CodigoBarra.getCodigoproducto());
       setCodigobarra(CodigoBarra.getCodigobarra());
       setDescripcion(CodigoBarra.getDescripcion());
   }
      
}