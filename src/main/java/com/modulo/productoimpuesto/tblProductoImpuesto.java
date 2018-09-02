package com.modulo.productoimpuesto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.modulo.impuestos.tblImpuestos;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosSql;
import com.byos.sys.util.utilString;
import com.vaadin.data.util.IndexedContainer;


public class tblProductoImpuesto  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoimpuesto;

    private String codigoproducto;

    private int id;
    
    public tblImpuestos TblImpuestos = new tblImpuestos();

    public tblProductoImpuesto() {
       limpiar();
    }

    public String getCodigoimpuesto() {
	   return this.codigoimpuesto;
    }

    public void setCodigoimpuesto(String codigoimpuesto) {
	   this.codigoimpuesto = codigoimpuesto;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }
    
    
    public String getCodigoproducto() {
	   return this.codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
	   this.codigoproducto = codigoproducto;
    }
    
    public tblProductoImpuesto[] buscarCodigo(String Codigo) throws Exception{
       tblProductoImpuesto[] Registros; 	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoimpuesto, codigoproducto from tblproductoimpuesto where codigoproducto=?";
	                          

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
		  if(rs.last()==true){
			 size = rs.getRow();
			 Registros = new tblProductoImpuesto[size];
			 rs.first(); 			
			 for(int f=0;f<size;f++){
			     Registros[f] = new tblProductoImpuesto();
	             Registros[f].setId(rs.getInt("id")); 
	             Registros[f].setCodigoimpuesto(rs.getString("codigoimpuesto"));                    
			     Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
			     Registros[f].TblImpuestos.buscarCodigo(Registros[f].getCodigoimpuesto());
			     rs.next();
			 }
		     ByosSql.CloseAll(con,pstmt,rs);
			 return Registros;	        
		  } 
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
       limpiar();
	   return null;
    }
    
    public boolean existeCodigo(String CodigoProducto, String CodigoImpuesto) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigoimpuesto, Codigoproducto from tblProductoimpuesto where Codigoproducto=? and Codigoimpuesto=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,CodigoProducto);
	   pstmt.setString(2,CodigoImpuesto);
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
    
    public String eliminar(String Codigoproducto, Connection con) throws Exception{
	   PreparedStatement pstmt = null;
	   int i;
	   //Ingresar nuevo
	   //if(existeCodigo(tbl.getCodigoproducto(),tbl.getCodigoimpuesto())){
		  String query = "Delete From tblProductoimpuesto Where Codigoproducto='" + Codigoproducto + "'";
		  pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();
	      return utilString.SQL_ELIMINADO;
	   //}
	   //return utilString.SQL_ERROR;
    }
       
    public String guardar(tblProductoImpuesto tbl, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigoimpuesto())){
		  String query = "Insert into tblProductoimpuesto(codigoimpuesto, codigoproducto) values(?,?)";
		  pstmt = con.prepareStatement(query);
		  pstmt.setString(1,tbl.getCodigoimpuesto());
	 	  pstmt.setString(2,tbl.getCodigoproducto());  
          i = pstmt.executeUpdate();
	      return utilString.SQL_INSERTADO;
	   }
	   return utilString.SQL_ERROR;
    }
          
    public ArrayList <tblProductoImpuesto> BuscarArray(tblProductoImpuesto tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblProductoImpuesto> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigoimpuesto, " +
	   		"codigoproducto " +
	   		"from tblProductoimpuesto ";
	   if(tbl.getCodigoimpuesto()!=null &&!tbl.getCodigoimpuesto().equals("")){
		  InSql = InSql + " and codigoimpuesto like '" + tbl.getCodigoimpuesto().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getCodigoproducto()!=null && !tbl.getCodigoproducto().equals("")){
		  InSql = InSql + " and codigoproducto like '" + tbl.getCodigoproducto().replace("*", "%") + "'";
		  Estado=1;
	   }
   
	   if(Estado==1){
          query = query + " Where 1=1 " + InSql;
	   }
       query = query  + " order by codigoproducto";
	  
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 tblProductoImpuesto Registros = new tblProductoImpuesto();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigoimpuesto(rs.getString("codigoimpuesto"));                    
		         Registros.setCodigoproducto(rs.getString("codigoproducto"));
		         Registros.TblImpuestos.buscarCodigo(getCodigoimpuesto());
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
       tblProductoImpuesto[] TblImpuestos=null;
       IndexedContainer Container = new IndexedContainer();
       try{
		   TblImpuestos = new tblProductoImpuesto().Buscar(new tblProductoImpuesto());
	   }catch (Exception e) {
	   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
       if(TblImpuestos!=null && TblImpuestos.length>0){
    	  Container.addItem(""); 
          for(int f=0;f<TblImpuestos.length;f++){
              Container.addItem(TblImpuestos[f].getCodigoimpuesto() + "," + TblImpuestos[f].getCodigoproducto());  
          }	
       }
       return Container;  
    }

    public tblProductoImpuesto[] Buscar(tblProductoImpuesto tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   tblProductoImpuesto[] Registros; 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigoimpuesto, " +
	   		"codigoproducto  " +
	   		"from tblProductoimpuesto ";
	   if(tbl.getCodigoimpuesto()!=null &&!tbl.getCodigoimpuesto().equals("")){
		  InSql = InSql + " and codigoimpuesto like '" + tbl.getCodigoimpuesto().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getCodigoproducto()!=null && !tbl.getCodigoproducto().equals("")){
		  InSql = InSql + " and codigoproducto like '" + tbl.getCodigoproducto().replace("*", "%") + "'";
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
		     Registros = new tblProductoImpuesto[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblProductoImpuesto();
                 Registros[f].setId(rs.getInt("id")); 
                 Registros[f].setCodigoimpuesto(rs.getString("codigoimpuesto"));                    
		         Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
		         Registros[f].TblImpuestos.buscarCodigo(getCodigoimpuesto());
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
       setCodigoproducto(""); 
    }   
    
    public BigDecimal getTotalIva(String CodigoProducto){
    	double TotalIva=0;
    	try {
			tblProductoImpuesto[] TblProductoImpuesto = new tblProductoImpuesto().buscarCodigo(CodigoProducto);
			if(TblProductoImpuesto!=null && TblProductoImpuesto.length>0){
			   for(int f=0;f<TblProductoImpuesto.length;f++){
			       TotalIva+=TblProductoImpuesto[f].TblImpuestos.getPorcentaje().doubleValue();
			   }
			}
			return BigDecimal.valueOf(TotalIva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return BigDecimal.valueOf(0);
    }
    
    public void setMarca(tblProductoImpuesto ProductoImpuesto){
       setId(ProductoImpuesto.getId());
       setCodigoimpuesto(ProductoImpuesto.getCodigoimpuesto());
       setCodigoproducto(ProductoImpuesto.getCodigoproducto());
       TblImpuestos.setTblImpuestos(ProductoImpuesto.TblImpuestos);

    }   
}