package com.modulo.historicoprecios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.modulo.componentes.utilDate;
import com.modulo.precioproducto.tblPrecioProducto;
import com.modulo.precios.tblPrecios;
import com.modulo.producto.tblProducto;
import com.modulo.productoimpuesto.tblProductoImpuesto;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosSql;
import com.byos.sys.util.utilString;


public class tblHistoricoPrecios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public tblPrecios TblPrecios = new tblPrecios();
    public tblUnidadMedida TblUnidadMedida = new tblUnidadMedida();

    private int id;
    
    private String codigoproducto;
    
    private String codigomedida;

    private String codigoprecio;
    
    private BigDecimal equivalencia;    
    
    private BigDecimal costoanterior;

    private BigDecimal precioanterior;
    
    private BigDecimal costonuevo;

    private BigDecimal precionuevo;
    
    private String codigounidadminima;
    
    private String descripunidadminima;
    
    private BigDecimal utilidadanterior;
    
    private BigDecimal utilidadnueva;
    
    private BigDecimal ivaanterior;
    
    private BigDecimal ivanuevo;
    
    private Date fecha;
    
    private Time hora;
    
    private String usuario;

    public tblHistoricoPrecios() {
        
    }

    public String getCodigomedida() {
	   return this.codigomedida;
    }

    public void setCodigomedida(String codigomedida) {
	   this.codigomedida = codigomedida;
    }

    public String getCodigoprecio() {
	   return this.codigoprecio;
    }

    public void setCodigoprecio(String codigoprecio) {
	   this.codigoprecio = codigoprecio;
    }

    public String getCodigoproducto() {
	   return this.codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
	   this.codigoproducto = codigoproducto;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }
    
    public BigDecimal getEquivalencia() {
	   return this.equivalencia;
    }

    public void setEquivalencia(BigDecimal equivalencia) {
	   this.equivalencia = equivalencia;
    }      

    public BigDecimal getPrecioanterior() {
	   return this.precioanterior;
    }
    
    public void setPrecioanterior(BigDecimal precioanterior) {
	   this.precioanterior = precioanterior;
    }    

    public void setPrecionuevo(BigDecimal precionuevo) {
	   this.precionuevo = precionuevo;
    }
    
    public BigDecimal getPrecionuevo() {
	   return this.precionuevo;
    }

    
    
    
    public BigDecimal getCostoanterior() {
	   return this.costoanterior;
    }
    
    public void setCostoanterior(BigDecimal costoanterior) {
	   this.costoanterior = costoanterior;
    }    

    public void setCostonuevo(BigDecimal costonuevo) {
	   this.costonuevo = costonuevo;
    }
    
    public BigDecimal getCostonuevo() {
	   return this.costonuevo;
    }        
    
    public String getCodigounidadminima() {
	   return this.codigounidadminima;
    }

    public void setCodigounidadminima(String codigounidadminima) {
	   this.codigounidadminima = codigounidadminima;
    }
 
    public String getDescripunidadminima() {
	   return this.descripunidadminima;
    }

    public void setDescripunidadminima(String descripunidadminima) {
	   this.descripunidadminima = descripunidadminima;
    }
    
    public BigDecimal getIvaanterior() {
	   return this.ivaanterior;
    }

    public void setIvaanterior(BigDecimal ivaanterior) {
	   this.ivaanterior = ivaanterior;
    } 
    
    public BigDecimal getIvanuevo() {
	   return this.ivanuevo;
    }

    public void setIvanuevo(BigDecimal ivanuevo) {
	   this.ivanuevo = ivanuevo;
    }
    
    public BigDecimal getUtilidadanterior() {
	   return this.utilidadanterior;
    }

    public void setUtilidadanterior(BigDecimal utilidadanterior) {
	   this.utilidadanterior = utilidadanterior;
    }
    
    public BigDecimal getUtilidadnueva() {
	   return this.utilidadnueva;
    }

    public void setUtilidadnueva(BigDecimal utilidadnueva) {
	   this.utilidadnueva = utilidadnueva;
    }  
    
    public Date getFecha() {
	   return this.fecha;
    }

    public void setFecha(Date fecha) {
	   this.fecha = fecha;
    }
    
    public Time getHora() {
	   return this.hora;
    }

    public void setHora(Time hora) {
	   this.hora = hora;
    }
    
    public String getUsuario() {
	   return this.usuario;
    }

    public void setUsuario(String usuario) {
	   this.usuario = usuario;
    }    
    
 
    public tblHistoricoPrecios buscarCodigo(String Codigo, String CodigoMedida, String CodigoPrecio) throws Exception{
       tblHistoricoPrecios TblHistoricoprecios = new tblHistoricoPrecios();
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id, codigoproducto, codigoprecio, codigomedida, precio, ivaanterior, ivanuevo, utilidadanteior, utilidadnueva from tblprecioproducto where codigoproducto=? and codigomedida=? and codigoprecio=?";

	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
       pstmt.setString(2,CodigoMedida);
       pstmt.setString(3,CodigoPrecio);
	   rs = pstmt.executeQuery();
	   int size=0;
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     TblHistoricoprecios.setId(rs.getInt("id"));
		     TblHistoricoprecios.setCodigoproducto(rs.getString("codigoproducto"));
             TblHistoricoprecios.setCodigoprecio(rs.getString("codigoprecio"));
             TblHistoricoprecios.setCodigomedida(rs.getString("codigomedida"));
             TblHistoricoprecios.setEquivalencia(rs.getBigDecimal("equivalencia"));
             TblHistoricoprecios.setPrecioanterior(rs.getBigDecimal("precioanterior"));
             TblHistoricoprecios.setPrecionuevo(rs.getBigDecimal("precionuevo"));
             TblHistoricoprecios.setCostoanterior(rs.getBigDecimal("costoanterior"));
             TblHistoricoprecios.setCostonuevo(rs.getBigDecimal("costonuevo"));
             TblHistoricoprecios.setDescripunidadminima(rs.getString("descripunidadminima"));
             TblHistoricoprecios.setCodigounidadminima(rs.getString("codigounidadminima"));
             TblHistoricoprecios.setIvaanterior(rs.getBigDecimal("ivaanterior")); 
             TblHistoricoprecios.setIvanuevo(rs.getBigDecimal("ivanuevo"));
             TblHistoricoprecios.setUtilidadanterior(rs.getBigDecimal("utilidadanterior")); 
             TblHistoricoprecios.setUtilidadnueva(rs.getBigDecimal("utilidadnueva"));
             TblHistoricoprecios.setFecha(rs.getDate("fecha"));
             TblHistoricoprecios.setHora(rs.getTime("hora"));
             TblHistoricoprecios.setUsuario(rs.getString("usuario"));
             
             TblHistoricoprecios.TblPrecios.buscarCodigo(TblHistoricoprecios.getCodigoprecio());
             TblHistoricoprecios.TblUnidadMedida.buscarCodigo(TblHistoricoprecios.getCodigomedida());
             ByosSql.CloseAll(con,pstmt,rs);
		     return TblHistoricoprecios;    
	      }	   
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
    
    public boolean existeCodigo(String Codigo, String CodigoMedida, String CodigoPrecio) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select id from tblprecioproducto where codigoproducto=?  and codigomedida=? and codigoprecio=?";
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   pstmt.setString(2,CodigoMedida);
	   pstmt.setString(3,CodigoPrecio);
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
	  
	   String query = "select Codigoproducto from tblHistoricoprecios where Codigoproducto=?";
	
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
		  String query = "Delete From tblHistoricoprecios Where Codigoproducto='" + Codigo + "'";
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
       
    public String guardar(tblHistoricoPrecios tbl, Connection con) throws Exception{
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Insert into tblHistoricoprecios(" +
	      "codigoproducto," +
	      "codigomedida," +
	      "codigoprecio," +
	      "equivalencia," +
	      "costoanterior," +
	      "precioanterior," +
	      "costonuevo," +
	      "precionuevo," +
	      "ivaanterior," +
	      "ivanuevo," + 
	      "utilidadanterior," +
	      "utilidadnueva," +
	      "codigounidadminima," +
	      "descripunidadminima," +
	      "fecha," +
	      "hora," +
	      "usuario" +	      
	      ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	   
	   //con.setAutoCommit(false);
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,tbl.getCodigoproducto());
	   pstmt.setString(2,tbl.getCodigomedida());
       pstmt.setString(3,tbl.getCodigoprecio());
       pstmt.setBigDecimal(4,tbl.getEquivalencia());
       pstmt.setBigDecimal(5,tbl.getCostoanterior());
       pstmt.setBigDecimal(6,tbl.getPrecioanterior());
       pstmt.setBigDecimal(7,tbl.getCostonuevo());
       pstmt.setBigDecimal(8,tbl.getPrecionuevo());
       pstmt.setBigDecimal(9,tbl.getIvaanterior());
       pstmt.setBigDecimal(10,tbl.getIvanuevo());
       pstmt.setBigDecimal(11,tbl.getUtilidadanterior());
       pstmt.setBigDecimal(12,tbl.getUtilidadnueva());
       pstmt.setString(13,tbl.getCodigounidadminima());
       pstmt.setString(14,tbl.getDescripunidadminima());
       pstmt.setDate(15,tbl.getFecha());
       pstmt.setTime(16,tbl.getHora());
       pstmt.setString(17,tbl.getUsuario());
       i = pstmt.executeUpdate();
       return utilString.SQL_INSERTADO;

    }
    
    public String DatosPrecios(String InSql){
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion(); 
	   try{
		   //System.out.println(InSql);
		   pstmt = con.prepareStatement(InSql);
		   rs = pstmt.executeQuery();
		   if(rs != null){
		      if(rs.last()==true){
			     rs.first();
			     String Resultado=rs.getString(1);
			     try {
					ByosSql.CloseAll(con,pstmt,rs);
				 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }
			     return Resultado;
		      }
		   }   
	   }catch (SQLException e) {
		  e.printStackTrace();
	   }
	   try{
		  ByosSql.CloseAll(con,pstmt,rs);
	   }catch (Exception e) {
		  e.printStackTrace();
	   }
	   return null;
    }
    
    public tblHistoricoPrecios[] Buscar(String Codigoproducto, String CodigoMedida, String FechaInicial, String FechaFinal) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "Select " +
			      "id," +
			      "codigoproducto," +
			      "codigomedida," +
			      "codigoprecio," +
			      "equivalencia," +
			      "costoanterior," +
			      "precioanterior," +
			      "costonuevo," +
			      "precionuevo," +
			      "ivaanterior," +
			      "ivanuevo," +
			      "utilidadanterior," +
			      "utilidadnueva," +
			      "codigounidadminima," +
			      "descripunidadminima," +
			      "fecha," +
			      "hora," +	
			      "usuario " +
			"from tblhistoricoprecios " +
		    "Where codigoproducto = '" + Codigoproducto + "' order by codigoproducto,codigomedida,codigoprecio,fecha,hora";
	  
	   //System.out.println(query);
	   Connection con = Conexion.getNuevaConexion();
	   con.setAutoCommit(false);
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs != null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     tblHistoricoPrecios[] Registros = new tblHistoricoPrecios[size];
		     rs.first(); 
			
		     for(int f=0;f<size;f++){
		         Registros[f] = new tblHistoricoPrecios();
                 Registros[f].setId(rs.getInt("id")); 
                 Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
                 Registros[f].setCodigoprecio(rs.getString("codigoprecio")); 
		         Registros[f].setCodigomedida(rs.getString("codigomedida")); 
		         Registros[f].setEquivalencia(rs.getBigDecimal("equivalencia"));
                 Registros[f].setUtilidadanterior(rs.getBigDecimal("utilidadanterior"));
                 Registros[f].setUtilidadnueva(rs.getBigDecimal("utilidadnueva"));
                 Registros[f].setCostoanterior(rs.getBigDecimal("costoanterior")); 
                 Registros[f].setPrecioanterior(rs.getBigDecimal("precioanterior")); 
                 Registros[f].setCostonuevo(rs.getBigDecimal("costonuevo")); 
                 Registros[f].setPrecionuevo(rs.getBigDecimal("precionuevo"));
                 Registros[f].setIvaanterior(rs.getBigDecimal("ivaanterior"));
                 Registros[f].setIvanuevo(rs.getBigDecimal("ivanuevo")); 
                 Registros[f].setCodigounidadminima(rs.getString("codigounidadminima"));
                 Registros[f].setDescripunidadminima(rs.getString("Descripunidadminima"));
                 Registros[f].setFecha(rs.getDate("Fecha"));
                 Registros[f].setHora(rs.getTime("Hora"));
                 Registros[f].setUsuario(rs.getString("usuario"));
                 Registros[f].TblPrecios.buscarCodigo(Registros[f].getCodigoprecio());
                 Registros[f].TblPrecios.buscarCodigo(Registros[f].getCodigoprecio());
		         rs.next();
		     }
		     con.commit();
		     ByosSql.CloseAll(con,pstmt,rs); 		     
		     return Registros;	   
	      }
	   }
       con.commit();
       ByosSql.CloseAll(con,pstmt,rs); 	   
	   return null;
    }
    
    public void guardar(tblProducto TblProducto){
        Connection con = Conexion.getNuevaConexion();
        
        tblPrecioProducto[] TblPrecioProductoNuevo = TblProducto.TblPrecioProductos;
		tblProductoImpuesto TblProductoImpuestoNuevo;
		tblProductoImpuesto TblProductoImpuestoAnterior;

		try{
			con.setAutoCommit(false);
		}catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
		
        //System.out.println("Precio Nivel 1");
    	if(TblPrecioProductoNuevo!=null && TblPrecioProductoNuevo.length>0){
    	   tblProducto TblProductoAnterior = new tblProducto();
    	   try{
			  TblProductoAnterior.buscarCodigo(TblProducto.getCodigoproducto());		  		  
		   }catch (Exception e1) {
			  // TODO Auto-generated catch block
			  e1.printStackTrace();
		   }
    	   
           if(TblProductoAnterior.TblProductoImpuestos!=null && TblProductoAnterior.TblProductoImpuestos!=null && TblProductoAnterior.TblProductoImpuestos.length>0){
           	  TblProductoImpuestoAnterior = TblProductoAnterior.TblProductoImpuestos[0];
            }else{
            	  TblProductoImpuestoAnterior = new tblProductoImpuesto();
            	  TblProductoImpuestoAnterior.TblImpuestos.setPorcentaje(BigDecimal.valueOf(0)); 
            }
    	   
    	   if(TblProducto.TblProductoImpuestos!=null && TblProductoAnterior.TblProductoImpuestos!=null && TblProducto.TblProductoImpuestos.length>0){
              TblProductoImpuestoNuevo = TblProductoAnterior.TblProductoImpuestos[0];
           }else{
          	  TblProductoImpuestoNuevo = new tblProductoImpuesto();
          	  TblProductoImpuestoNuevo.TblImpuestos.setPorcentaje(BigDecimal.valueOf(0)); 
           }

           

    	   //System.out.println("Precio Nivel 2");
    	   for(int f=0;f<TblPrecioProductoNuevo.length;f++){
    	       tblPrecioProducto TblPrecioProductoAnterior  = new tblPrecioProducto().BuscarPrecioProducto(TblProductoAnterior.TblPrecioProductos, TblPrecioProductoNuevo[f].getCodigoproducto(), TblPrecioProductoNuevo[f].getCodigomedida(),TblPrecioProductoNuevo[f].getCodigoprecio());
    	       if(TblPrecioProductoAnterior==null){
    	          TblPrecioProductoAnterior = new tblPrecioProducto();  
    	       }
    	       
    	       if(TblPrecioProductoAnterior.getPrecio().doubleValue()!=TblPrecioProductoNuevo[f].getPrecio().doubleValue() || 
    	    	  TblPrecioProductoAnterior.getUtilidad().doubleValue()!=TblPrecioProductoNuevo[f].getUtilidad().doubleValue() || 
    	    	  TblProducto.getCosto().doubleValue()!=TblProductoAnterior.getCosto().doubleValue() || 
    	    	  !TblProductoImpuestoNuevo.getCodigoimpuesto().equals(TblProductoImpuestoAnterior.getCodigoimpuesto())){
    	    	  //System.out.println("Precio Nivel 3");
    	    	  tblProductoMedida TblProductoMedida = new tblProductoMedida();     	 
    	          try{
				      TblProductoMedida.buscarCodigo(TblPrecioProductoNuevo[f].getCodigoproducto(),TblPrecioProductoNuevo[f].getCodigomedida());
			      }catch (Exception e) {
				      // TODO Auto-generated catch block
				      e.printStackTrace();
		          }
    	            	 
    	          tblHistoricoPrecios TblHistoricoPrecios = new  tblHistoricoPrecios();
    	          TblHistoricoPrecios.setCodigomedida(TblPrecioProductoNuevo[f].getCodigomedida());
    	          TblHistoricoPrecios.setCodigoprecio(TblPrecioProductoNuevo[f].getCodigoprecio());
    	          TblHistoricoPrecios.setCodigoproducto(TblPrecioProductoNuevo[f].getCodigoproducto());
    	          TblHistoricoPrecios.setCodigounidadminima(TblProducto.getCodigomedida());
    	          TblHistoricoPrecios.setDescripunidadminima(TblProducto.TblUnidadMedida.getDescripcion());
    	          TblHistoricoPrecios.setCostoanterior(BigDecimal.valueOf(TblProductoAnterior.getCosto().doubleValue()*TblProductoMedida.getEquivalencia().doubleValue()));
    	          TblHistoricoPrecios.setCostonuevo(BigDecimal.valueOf(TblProducto.getCosto().doubleValue()*TblProductoMedida.getEquivalencia().doubleValue()));
    	          TblHistoricoPrecios.setEquivalencia(TblProductoMedida.getEquivalencia());
    	          TblHistoricoPrecios.setFecha(utilDate.Fecha());
    	          TblHistoricoPrecios.setHora(utilDate.Hora());
    	          TblHistoricoPrecios.setPrecioanterior(TblPrecioProductoAnterior.getPrecio()); 
    	          TblHistoricoPrecios.setPrecionuevo(TblPrecioProductoNuevo[f].getPrecio());
    	          TblHistoricoPrecios.setUtilidadanterior(TblPrecioProductoAnterior.getUtilidad()); 
    	          TblHistoricoPrecios.setUtilidadnueva(TblPrecioProductoNuevo[f].getUtilidad()); 
    	          TblHistoricoPrecios.setIvaanterior(TblProductoImpuestoAnterior.TblImpuestos.getPorcentaje()); 
    	          TblHistoricoPrecios.setIvanuevo(TblProductoImpuestoNuevo.TblImpuestos.getPorcentaje()); 
  
    	          try{
    	        	  //con.setAutoCommit(false);
			          TblHistoricoPrecios.guardar(TblHistoricoPrecios, con);
			      }catch(Exception e) {
				   // TODO Auto-generated catch block
				      e.printStackTrace();
		          }
    	       }
    	   }
    	}
    	try{
    		con.commit();
			ByosSql.CloseAll(con,null,null);
		}catch (Exception e) {
		    // TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    public void limpiar(){
       setId(0);
       setCodigoproducto("");
       setCodigomedida("");  
       setCodigoprecio("");
       setPrecioanterior(null);
       setUtilidadanterior(null);
       setUtilidadnueva(null);
    }   
    
    public void setPrecioProducto(tblHistoricoPrecios PrecioProducto){
       setId(PrecioProducto.getId());
       setCodigoproducto(PrecioProducto.getCodigoproducto());
       setCodigoprecio(PrecioProducto.getCodigoprecio());
       setCodigomedida(PrecioProducto.getCodigomedida());
       setPrecioanterior(PrecioProducto.getPrecioanterior());
       setUtilidadanterior(PrecioProducto.getUtilidadanterior());      
    } 
       
}