package com.modulo.impuestos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.componentes.ByosSql;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;
import com.vaadin.data.util.IndexedContainer;


public class tblImpuestos  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoimpuesto;

    private String descripcion;
    
    private String aplicara;
    
    private BigDecimal porcentaje;

    private int id;

    public tblImpuestos() {
       limpiar();
    }

    public String getCodigoimpuesto() {
	   return this.codigoimpuesto;
    }

    public void setCodigoimpuesto(String codigoimpuesto) {
	   this.codigoimpuesto = codigoimpuesto;
    }

    public String getAplicara() {
	   return this.aplicara;
    }

    public void setAplicara(String aplicara) {
	   this.aplicara = aplicara;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }
    
    public BigDecimal getPorcentaje() {
	   return this.porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
	   this.porcentaje = porcentaje;
    }
       
    
    public String getDescripcion() {
	   return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	   this.descripcion = descripcion;
    }
    
    public boolean buscarCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Select id, codigoimpuesto, descripcion, porcentaje, aplicara from tblimpuestos where codigoimpuesto=?";
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	   
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setId(rs.getInt("id"));
		     setCodigoimpuesto(rs.getString("codigoimpuesto"));
             setDescripcion(rs.getString("descripcion"));
             setAplicara(rs.getString("aplicara"));
             setPorcentaje(rs.getBigDecimal("porcentaje"));
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

	   String query = "select codigoimpuesto from tblImpuestos where codigoimpuesto=?";
	
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
    
    public String eliminar(tblImpuestos tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(existeCodigo(tbl.getCodigoimpuesto())){
			  String query = "Delete From tblImpuestos Where codigoimpuesto='" + tbl.getCodigoimpuesto() + "'";
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
       
    public String guardar(tblImpuestos tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   
	   
	   try{
		   if(!existeCodigo(tbl.getCodigoimpuesto())){
			  String query = "Insert into tblImpuestos(codigoimpuesto, descripcion, porcentaje, aplicara) values(?,?,?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigoimpuesto());
		 	  pstmt.setString(2,tbl.getDescripcion());
		 	  pstmt.setBigDecimal(3,tbl.getPorcentaje());
		 	  pstmt.setString(4,tbl.getAplicara());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblImpuestos set descripcion=?, porcentaje=?, aplicara=? where codigoimpuesto='" + tbl.getCodigoimpuesto() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);			
		      pstmt.setString(1,tbl.getDescripcion());
		      pstmt.setBigDecimal(2,tbl.getPorcentaje());
		      pstmt.setString(3,tbl.getAplicara());
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
          
    public ArrayList <tblImpuestos> BuscarArray(tblImpuestos tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblImpuestos> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigoimpuesto, " +
	   		"descripcion," +
	   		"porcentaje, " +
	   		"aplicara " +
	   		"from tblImpuestos ";
	   if(tbl.getCodigoimpuesto()!=null &&!tbl.getCodigoimpuesto().equals("")){
		  InSql = InSql + " and codigoimpuesto like '" + tbl.getCodigoimpuesto().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		  InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getPorcentaje()!=null && !tbl.getPorcentaje().equals("")){
		  InSql = InSql + " and porcentaje = " + tbl.getPorcentaje().toString();
		  Estado=1;
	   }	
	   if(tbl.getAplicara()!=null && !tbl.getAplicara().equals("")){
		  InSql = InSql + " and aplicara like '" + tbl.getAplicara().replace("*", "%") + "'";
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
		    	 tblImpuestos Registros = new tblImpuestos();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigoimpuesto(rs.getString("codigoimpuesto"));                    
		         Registros.setDescripcion(rs.getString("descripcion"));
		         Registros.setPorcentaje(rs.getBigDecimal("porcentaje"));
		         Registros.setAplicara(rs.getString("aplicara"));
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
    
    	
    public IndexedContainer getImpuestosContainer(){ 
       tblImpuestos[] TblImpuestos=null;
       IndexedContainer Container = new IndexedContainer();
       try{
		   TblImpuestos = new tblImpuestos().Buscar(new tblImpuestos());
	   }catch (Exception e) {
	   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
       if(TblImpuestos!=null && TblImpuestos.length>0){
    	  Container.addItem(""); 
          for(int f=0;f<TblImpuestos.length;f++){
              Container.addItem(TblImpuestos[f].getCodigoimpuesto() + "," + TblImpuestos[f].getDescripcion());  
          }	
       }
       return Container;  
    }

    public tblImpuestos[] Buscar(tblImpuestos tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   tblImpuestos[] Registros; 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigoimpuesto, " +
	   		"descripcion, " +
	   		"porcentaje, " +
	   		"aplicara " +
	   		"from tblImpuestos ";
	   if(tbl.getCodigoimpuesto()!=null &&!tbl.getCodigoimpuesto().equals("")){
		  InSql = InSql + " and codigoimpuesto like '" + tbl.getCodigoimpuesto().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		  InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getPorcentaje()!=null && !tbl.getPorcentaje().equals("")){
		  InSql = InSql + " and porcentaje = " + tbl.getPorcentaje().toString();
		  Estado=1;
	   }	
	   if(tbl.getAplicara()!=null && !tbl.getAplicara().equals("")){
		  InSql = InSql + " and aplicara like '" + tbl.getAplicara().replace("*", "%") + "'";
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
		     Registros = new tblImpuestos[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblImpuestos();
                 Registros[f].setId(rs.getInt("id")); 
                 Registros[f].setCodigoimpuesto(rs.getString("codigoimpuesto"));                    
		         Registros[f].setDescripcion(rs.getString("descripcion"));
		         Registros[f].setPorcentaje(rs.getBigDecimal("porcentaje"));
		         Registros[f].setAplicara(rs.getString("aplicara"));
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
       setCodigoimpuesto("");
       setAplicara("");
       setDescripcion(""); 
       setPorcentaje(null);
    }   
    
    public void setTblImpuestos(tblImpuestos Impuesto){
       setId(Impuesto.getId());
       setCodigoimpuesto(Impuesto.getCodigoimpuesto());
       setDescripcion(Impuesto.getDescripcion());
       setAplicara(Impuesto.getAplicara());
       setPorcentaje(Impuesto.getPorcentaje());  
    }   
}