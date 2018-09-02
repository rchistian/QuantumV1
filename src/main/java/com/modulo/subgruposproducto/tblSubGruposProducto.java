package com.modulo.subgruposproducto;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.componentes.ByosSql;
import com.modulo.gruposproducto.tblGruposProducto;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;



public class tblSubGruposProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public tblGruposProducto TblGruposProducto = new tblGruposProducto();

    private String codigogrupo;

    private String codigosubgrupo;

    private String descripcion;

    private int id;

    public tblSubGruposProducto() {
       limpiar(); 
    }

    public String getCodigogrupo() {
	   return this.codigogrupo;
    }

    public void setCodigogrupo(String codigogrupo) {
	   this.codigogrupo = codigogrupo;
    }

    public String getCodigosubgrupo() {
	   return this.codigosubgrupo;
    }

    public void setCodigosubgrupo(String codigosubgrupo) {
	   this.codigosubgrupo = codigosubgrupo;
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
    
    public boolean buscarCodigo(String Codigo, String SubCodigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigogrupo, codigosubgrupo, descripcion from TblSubGruposProductos where codigogrupo=? and codigosubgrupo=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
       pstmt.setString(2,SubCodigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigogrupo(rs.getString("codigogrupo"));
             setCodigosubgrupo(rs.getString("codigosubgrupo"));
             setDescripcion(rs.getString("descripcion"));
             TblGruposProducto.buscarCodigo(getCodigogrupo());
             ByosSql.CloseAll(con,pstmt,rs);
		     return true;     
	      }	   
	   } 
	   limpiar();
	   ByosSql.CloseAll(con,pstmt,rs);
	   return false;  
    }
    
    public boolean existeCodigo(String Codigo, String SubCodigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "Select Codigogrupo, Codigosubgrupo from tblSubGruposProductos where Codigogrupo=? and Codigosubgrupo=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
       pstmt.setString(2,SubCodigo);
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
    
    public String eliminar(tblSubGruposProducto tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   //Ingresar nuevo
	   try{
		   if(existeCodigo(tbl.getCodigogrupo(), tbl.getCodigosubgrupo())){
			  String query = "Delete From TblSubGruposProductos Where Codigogrupo='" + tbl.getCodigogrupo() + "' and  Codigosubgrupo='" + tbl.getCodigosubgrupo() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_ELIMINADO;	
		   }
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } catch (Exception e) {
		   e.printStackTrace();
	   } 
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	
	   return utilString.SQL_ERROR;
    }
    
    public String guardar(tblSubGruposProducto tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigogrupo(), tbl.getCodigosubgrupo() )){
			  String query = "Insert into tblSubGruposProductos(codigogrupo, codigosubgrupo, descripcion) values(?,?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigogrupo());
		      pstmt.setString(2,tbl.getCodigosubgrupo());
			  pstmt.setString(3,tbl.getDescripcion());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);          
		      return utilString.SQL_INSERTADO;		
		   }
		   else{
			  String query = "Update tblSubGruposProductos set descripcion=? where Codigogrupo='" + tbl.getCodigogrupo() + "' and Codigosubgrupo='" + tbl.getCodigosubgrupo() + "'";
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
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;	
    }
    
    public ArrayList <tblSubGruposProducto> Buscar(tblSubGruposProducto tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblSubGruposProducto> tblArray = new ArrayList(); 
	  
	   String query = "Select " +
	   		"id, " +
	   		"codigogrupo, " +
	   		"codigosubgrupo, " +
	   		"descripcion " +
	   		"from TblSubGruposProductos ";
	   if(tbl.getCodigogrupo()!=null &&!tbl.getCodigogrupo().equals("")){
		  InSql = InSql + " and codigogrupo like '" + tbl.getCodigogrupo().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getCodigosubgrupo()!=null &&!tbl.getCodigosubgrupo().equals("")){
		  InSql = InSql + " and codigosubgrupo like '" + tbl.getCodigosubgrupo().replace("*", "%") + "'";
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
		    	 tblSubGruposProducto Registros = new tblSubGruposProducto();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigogrupo(rs.getString("codigogrupo"));  
                 Registros.setCodigosubgrupo(rs.getString("codigosubgrupo"));  
		         Registros.setDescripcion(rs.getString("descripcion"));
		         TblGruposProducto.buscarCodigo(getCodigogrupo());
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
      setCodigogrupo("");
      setCodigosubgrupo("");
      setDescripcion("");
      TblGruposProducto.limpiar();
    } 
 
    public void setSubGruposProducto(tblSubGruposProducto SubGruposProducto){
       setId(SubGruposProducto.getId());
       setCodigogrupo(SubGruposProducto.getCodigogrupo());
       setCodigosubgrupo(SubGruposProducto.getCodigosubgrupo());
       setDescripcion(SubGruposProducto.getDescripcion());
       TblGruposProducto.setGruposProducto(SubGruposProducto.TblGruposProducto);
    } 

}