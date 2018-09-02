package com.modulo.productomedida;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.deposito.tblDeposito;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosSql;
import com.byos.sys.util.utilString;
import com.vaadin.data.util.IndexedContainer;


/**
 * The persistent class for the tblproductomedidas database table.
 * 
 */

public class tblProductoMedida implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoproducto;

    private String codigomedida;
    
    public tblUnidadMedida TblUnidadMedida = new tblUnidadMedida(); 
    
    private BigDecimal equivalencia;
    
    private BigDecimal pesoneto;
    
    private BigDecimal contenidoneto;

    private Integer id;
    
    private BigDecimal volumen;
    

    public tblProductoMedida() {
       limpiar();
    }

    public String getCodigoproducto() {
	   return this.codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
	   this.codigoproducto = codigoproducto;
    }
  
    public String getDescripcion() {
	   return this.TblUnidadMedida.getDescripcion();
    }

    public void setDescripcion(String descripcion) {
    	this.TblUnidadMedida.setDescripcion(descripcion);
    }    
    
    public String getCodigomedida() {
	   return this.codigomedida;
    }

    public void setCodigomedida(String codigomedida) {
	   this.codigomedida = codigomedida;
    }

    public BigDecimal getEquivalencia() {
	   return this.equivalencia;
    }

    public void setEquivalencia(BigDecimal equivalencia) {
	   this.equivalencia = equivalencia;
    }

    public BigDecimal getPesoneto() {
	   return this.pesoneto;
    }

    public void setPesoneto(BigDecimal pesoneto) {
	   this.pesoneto = pesoneto;
    }
    
    public BigDecimal getContenidoneto() {
	   return this.contenidoneto;
    }

    public void setContenidoneto(BigDecimal contenidoneto) {
	   this.contenidoneto = contenidoneto;
    }
    
    public int getId() {
	   return this.id;
    } 
    
    public void setId(int id) {
	   this.id = id;
    }
    
    public void setVolumen(BigDecimal volumen) {
	  this.volumen = volumen;
    } 
    
    public BigDecimal getVolumen() {
	   return this.volumen;
    } 
        
    public boolean buscarCodigo(String CodigoProducto, String CodigoMedida) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  String query = "Select id,codigoproducto,codigomedida,equivalencia,pesoneto,contenidoneto,volumen from tblproductomedida where codigoproducto=? and codigomedida=?";

	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,CodigoProducto);
	  pstmt.setString(2,CodigoMedida);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs != null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    setId(rs.getInt("id"));
		    setCodigoproducto(rs.getString("codigoproducto"));
            setCodigomedida(rs.getString("codigomedida"));
            setEquivalencia(rs.getBigDecimal("equivalencia"));
            setPesoneto(rs.getBigDecimal("pesoneto"));
            setContenidoneto(rs.getBigDecimal("contenidoneto"));
            setVolumen(rs.getBigDecimal("volumen"));
            TblUnidadMedida.buscarCodigo(getCodigomedida());
            ByosSql.CloseAll(con,pstmt,rs);
		    return true;   
	     }	    
	  }
	  ByosSql.CloseAll(con,pstmt,rs);
	  limpiar(); 
	  return false;
    }
 
    public boolean existeCodigo(String Codigo, String Codigo01) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  String query = "select Codigoproducto from tblProductoMedida where Codigoproducto=? and Codigomedida=?";
	
	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,Codigo);
      pstmt.setString(2,Codigo01);
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
    
    public boolean existeCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "select Codigoproducto from tblProductoMedida where Codigoproducto=?";
	
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
	   Connection con = Conexion.getNuevaConexion();
	   
	   //Ingresar nuevo
	   if(existeCodigo(Codigo)){
		  String query = "Delete From tblProductoMedida Where Codigoproducto='" + Codigo + "'";
		  con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);
	      return utilString.SQL_ELIMINADO;			
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return utilString.SQL_ERROR; 
    }
    
    //Eliminar Medidas para Inventario
    public String eliminar(String Codigo, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   //Ingresar nuevo
	   //if(existeCodigo(Codigo)){
		  String query = "Delete From tblProductoMedida Where Codigoproducto='" + Codigo + "'";
		  System.out.println(query);
		  pstmt = con.prepareStatement(query);
		  con.setAutoCommit(false);
          i = pstmt.executeUpdate();
	      return utilString.SQL_ELIMINADO;			
	   //}
	   //return utilString.SQL_ERROR; 
    }    
    
    public String eliminar(tblProductoMedida[] TblProductosMedidas, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   if(TblProductosMedidas!=null && TblProductosMedidas.length>0){
	      String CodigoProducto=TblProductosMedidas[0].getCodigoproducto();
		  String CodigosMedidas="(";
	      String Separador="";
	      for(int f=0;f<TblProductosMedidas.length;f++){
	    	  CodigosMedidas = CodigosMedidas + Separador + "'" + TblProductosMedidas[f].getCodigomedida() + "'";
	    	  Separador=",";
	      }
	      CodigosMedidas = CodigosMedidas + ")";
	      //Ingresar nuevo
		  String query = "Delete From tblProductoMedida Where Codigoproducto='" + CodigoProducto + "' and Codigomedida not in " + CodigosMedidas;
		  System.out.println(query);
		  pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();
	      return utilString.SQL_ELIMINADO;			
	   }
	   return utilString.SQL_ERROR; 
    }     
    
    
    public String guardar(tblProductoMedida tbl) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   //if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigomedida())){
		  String query = "Insert into tblProductoMedida(codigoproducto, codigomedida, equivalencia, pesoneto, contenidoneto, volumen) values(?,?,?,?,?,?)";
		  Connection con = Conexion.getNuevaConexion();
		  pstmt = con.prepareStatement(query);
		  con.setAutoCommit(false);
		  pstmt.setString(1,tbl.getCodigoproducto());
		  pstmt.setString(2,tbl.getCodigomedida());
          pstmt.setBigDecimal(3,tbl.getEquivalencia());
          pstmt.setBigDecimal(4,tbl.getPesoneto());
          pstmt.setBigDecimal(5,tbl.getContenidoneto());
          pstmt.setBigDecimal(6,tbl.getVolumen());
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);
	      return utilString.SQL_INSERTADO;
	   /*}
	   else{
		  String query = "Update tblProductoMedida set equivalencia=?, pesoneto=?, contenidoneto=? where codigoproducto=?'" + tbl.getCodigoproducto() + "' and Codigomedida='" + tbl.getCodigomedida() + "'";
		  Connection con = Conexion.getNuevaConexion();
		  pstmt = con.prepareStatement(query);
		  //con.setAutoCommit(false);
          pstmt.setBigDecimal(1,tbl.getEquivalencia());
          pstmt.setBigDecimal(2,tbl.getPesoneto());
          pstmt.setBigDecimal(3,tbl.getContenidoneto());
          pstmt.setBigDecimal(4,tbl.getVolumen());
		  i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);
		  if(i>0){
	         return utilString.SQL_MODIFICADO;
		  }
		  else{
	         return utilString.SQL_ERROR;	
	 	  }
	   }*/
    }
    
    //Guardar Medida Para Inventario
    public String guardar(tblProductoMedida tbl, Connection con) throws Exception {  
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   //if(!existeCodigo(tbl.getCodigoproducto(), tbl.getCodigomedida())){
		  String query = "Insert into tblProductoMedida(codigoproducto, codigomedida, equivalencia, pesoneto, contenidoneto, volumen) values(?,?,?,?,?,?)";
		  pstmt = con.prepareStatement(query);
		  pstmt.setString(1,tbl.getCodigoproducto());
		  pstmt.setString(2,tbl.getCodigomedida());
		  pstmt.setBigDecimal(3,tbl.getEquivalencia());
		  pstmt.setBigDecimal(4,tbl.getPesoneto());
		  pstmt.setBigDecimal(5,tbl.getContenidoneto());
		  pstmt.setBigDecimal(6,tbl.getVolumen());
		  i = pstmt.executeUpdate();
		  //System.out.println("Medida Insertada: " + tbl.getCodigoproducto() + " " + tbl.getCodigomedida());
		  return utilString.SQL_INSERTADO;
	  /* }else{
	      String query = "Update tblProductoMedida set equivalencia=?, pesoneto=?, contenidoneto=?, volumen=? where Codigoproducto='" + tbl.getCodigoproducto() + "' and Codigomedida='" + tbl.getCodigomedida() + "'";
	      pstmt = con.prepareStatement(query);
		  pstmt.setBigDecimal(1,tbl.getEquivalencia());
		  pstmt.setBigDecimal(2,tbl.getPesoneto());
		  pstmt.setBigDecimal(3,tbl.getContenidoneto());
		  pstmt.setBigDecimal(4,tbl.getVolumen());
	      i = pstmt.executeUpdate();
	      System.out.println("Medida Modificada: " + tbl.getCodigoproducto() + " " + tbl.getCodigomedida());
		  if(i>0){
		     return utilString.SQL_MODIFICADO;
		  }
		  else{
		     return utilString.SQL_ERROR;	
		  }   
	   }*/
    }    
    
    public  ArrayList <tblProductoMedida> UnidadesMedidasToArrayList(tblProductoMedida[] TblProductosMedidas) throws Exception {
    	ArrayList <tblProductoMedida> ArrayUnidadesMedidas = new ArrayList();
    	if(TblProductosMedidas!=null){
    	   for(int f=0;f<TblProductosMedidas.length;f++){
    	  	   ArrayUnidadesMedidas.add(TblProductosMedidas[f]);
    	   }
    	}else{
    		tblProductoMedida Medidas = new tblProductoMedida();
    		Medidas.setCodigomedida("");
    		Medidas.TblUnidadMedida.setDescripcion("");
    		Medidas.setContenidoneto(null);
    		Medidas.setEquivalencia(null);
    		Medidas.setPesoneto(null);
    		Medidas.setVolumen(null);
    	    ArrayUnidadesMedidas.add(Medidas);
    	}
    	return ArrayUnidadesMedidas;
    }
    
    public tblProductoMedida[] Buscar(String Codigoproducto) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoproducto, codigomedida, equivalencia, pesoneto, contenidoneto, volumen from tblProductoMedida " +
		              "Where codigoproducto = '" + Codigoproducto + "'  order by equivalencia";
	  
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    tblProductoMedida[] Registros = new tblProductoMedida[size];
		    rs.first(); 
			
		    for(int f=0;f<size;f++){
		        Registros[f] = new tblProductoMedida();
                Registros[f].setId(rs.getInt("id")); 
                Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
		        Registros[f].setCodigomedida(rs.getString("codigomedida")); 
                Registros[f].setEquivalencia(rs.getBigDecimal("equivalencia")); 
                Registros[f].setPesoneto(rs.getBigDecimal("pesoneto"));
                Registros[f].setContenidoneto(rs.getBigDecimal("contenidoneto"));
                Registros[f].setVolumen(rs.getBigDecimal("volumen"));
                Registros[f].TblUnidadMedida.buscarCodigo(Registros[f].getCodigomedida());
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
       setCodigomedida("");
       setEquivalencia(null);
       setContenidoneto(null);
       setPesoneto(null);
       setVolumen(null);
    }   
    
    public IndexedContainer getProductoMedidaContainer(String CodigoProducto){
    	tblProductoMedida[] TblProductoMedidas = null;
		try {
			TblProductoMedidas = new tblProductoMedida().Buscar(CodigoProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        IndexedContainer Container = new IndexedContainer();
        if(TblProductoMedidas!=null && TblProductoMedidas.length>0){
           for(int f=0;f<TblProductoMedidas.length;f++){
               Container.addItem(TblProductoMedidas[f].getCodigomedida() + "," + TblProductoMedidas[f].TblUnidadMedida.getDescripcion());  
           }
        }
        return Container;
    }
    
    public void setProductoMedida(tblProductoMedida ProductoMedida){
       setId(ProductoMedida.getId());
       setCodigoproducto(ProductoMedida.getCodigoproducto());
       setCodigomedida(ProductoMedida.getCodigomedida());
       setEquivalencia(ProductoMedida.getEquivalencia());
       setPesoneto(ProductoMedida.getPesoneto());
       setContenidoneto(ProductoMedida.getContenidoneto());
       setVolumen(ProductoMedida.getVolumen());
       TblUnidadMedida.setCodigomedida(ProductoMedida.getCodigomedida());
       TblUnidadMedida.setDescripcion(ProductoMedida.TblUnidadMedida.getDescripcion());
    }

}