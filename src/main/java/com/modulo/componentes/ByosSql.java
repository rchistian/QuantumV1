package com.modulo.componentes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.byos.sys.conexion.Conexion;



public class ByosSql {
	
	public ByosSql(){
		
	}
	
	public static void CloseAll(Connection con, PreparedStatement pstmt, ResultSet rs) {
	   try{
	      if(rs!=null){
		     rs.close();
	         rs=null;
	      }
	      if(pstmt!=null){
	         pstmt.close();
	         pstmt=null; 
	      }
	      if(con!=null){
	         con.close();
	         con=null;     
	      }	
	   }catch (SQLException e) {
		  e.printStackTrace();
	   }
	}
	
	public static void RollBack(Connection con){
	   try{
		  con.rollback();
	   }catch (SQLException e1) {
		  e1.printStackTrace();
	   }
	}
	
    public static String getProximoCodigo(String InsSql, int tamanio) throws Exception {
    	  PreparedStatement pstmt = null;
    	  ResultSet rs = null;
    	  String ProximoCodigo=null;  
    	  //Ejemplo "Select max(codigomedida) From tblunidadmedida Where abs(codigomedida)>0"
    	  String query = InsSql;
    	  Connection con = Conexion.getNuevaConexion();
    	  pstmt = con.prepareStatement(query);
    	  rs = pstmt.executeQuery();
    	  int size=0;
    	
    	  if(rs!=null){
    	     if(rs.last()==true){
    		    rs.first();
                ProximoCodigo=rs.getString(1);        
    	     }
    	  }
          if(ProximoCodigo!=null && !ProximoCodigo.equals("")){
             if(ByosValidar.esNumero(ProximoCodigo)){ 
            	tamanio=ProximoCodigo.length();
                Integer Valor = Integer.valueOf(ProximoCodigo);
                
                Valor++;
                ProximoCodigo=String.valueOf(Valor.longValue());
                ProximoCodigo=ByosValidar.Add_String_I(ProximoCodigo,'0',tamanio);    
             }        
          }else{           
        	 ByosValidar.Add_String_I("1",'0',tamanio);; 
          }
          ByosSql.CloseAll(con,pstmt,rs);
          return ProximoCodigo;   
    }

    public static int ObtenerAutonumerico(PreparedStatement pstmt) throws Exception {
        int id = -1;
        ResultSet rs = pstmt.getGeneratedKeys();
        if(rs.next()) 	 {
           id = rs.getInt(1);
        }
        return id;
     }
    	
}
