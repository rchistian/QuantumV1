package com.modulo.compras;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;
import com.byos.sys.conexion.Conexion;

public class tblComprasItemImpuestos {
	
	private Integer codigocompra;
	
	private Integer codigoitem;
	
	private String codigoimpuesto;
	
	private BigDecimal porcentaje;
	
	private BigDecimal montoimpuesto;
	
	private BigDecimal montodeducible;
	
	Date fecha;
	
	private String codigoproducto;
	
	
	public BigDecimal getMontodeducible() {
		return montodeducible;
	}

	public void setMontodeducible(BigDecimal montodeducible) {
		this.montodeducible = montodeducible;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCodigoproducto() {
		return codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}
	
	public Integer getCodigocompra() {
		return codigocompra;
	}

	public void setCodigocompra(Integer codigocompra) {
		this.codigocompra = codigocompra;
	}

	public Integer getCodigoitem() {
		return codigoitem;
	}

	public void setCodigoitem(Integer codigoitem) {
		this.codigoitem = codigoitem;
	}

	public String getCodigoimpuesto() {
		return codigoimpuesto;
	}

	public void setCodigoimpuesto(String codigoimpuesto) {
		this.codigoimpuesto = codigoimpuesto;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getMontoimpuesto() {
		return montoimpuesto;
	}

	public void setMontoimpuesto(BigDecimal montoimpuesto) {
		this.montoimpuesto = montoimpuesto;
	}

	public boolean existeCodigo(Integer Codigo, Integer Codigo01) throws Exception{
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;

		   String query = "select codigocompra from tblComprasItemImpuestos where codigocompra=? and codigoitem=?";
		
		   Connection con = Conexion.getNuevaConexion();
		   pstmt = con.prepareStatement(query);
		   pstmt.setInt(1,Codigo);
		   pstmt.setInt(2,Codigo01);
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
	    
	    public String eliminar(Integer Codigo, Connection con) throws Exception {
		   int i=0;	
		   PreparedStatement pstmt = null;
		   String query = "Delete From tblComprasItemImpuestos Where codigocompra=" + Codigo;
		   pstmt = con.prepareStatement(query);
		   i = pstmt.executeUpdate();
		   return utilString.SQL_ELIMINADO;
	    }
	       
	    public String guardar(tblComprasItemImpuestos tbl, Connection con) throws Exception { 
		   int i=0;	
		   PreparedStatement pstmt = null;
		   String query = "Insert into tblComprasItemImpuestos("
		   		+ "Codigocompra, "
		   		+ "Codigoitem, "
		   		+ "Codigoimpuesto, "
		   		+ "Porcentaje, "
		   		+ "Montoimpuesto, "
		   		+ "montodeducible, "
		   		+ "fecha, "
		   		+ "codigoproducto) "
		   		+ "values(?,?,?,?,?,?,?,?,?)";
		   pstmt = con.prepareStatement(query);
		   pstmt.setInt(1,tbl.getCodigocompra());
		   pstmt.setInt(2,tbl.getCodigoitem());
		   pstmt.setString(3,tbl.getCodigoimpuesto());
		   pstmt.setBigDecimal(4,tbl.getPorcentaje());
		   pstmt.setBigDecimal(5,tbl.getMontoimpuesto());
		   pstmt.setBigDecimal(6,tbl.getMontodeducible());
		   pstmt.setTimestamp(7,utilDate.DateToTimestamp(tbl.getFecha()));
		   pstmt.setString(8,tbl.getCodigoproducto());
		   i = pstmt.executeUpdate();
		   return utilString.SQL_INSERTADO;
	    }
	          
	    public tblComprasItemImpuestos[] Buscar(Integer Codigo, Integer Codigo01) throws Exception {
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   
		   String query = "Select " +
				"Codigocompra," + 
				"Codigoitem," + 
				"Codigoimpuesto," +
				"Porcentaje," +
				"Montoimpuesto, " +
		   		"montodeducible, " +
		   		"fecha, " +
		   		"codigoproducto " +				
		   		"from tblComprasItemImpuestos " +
                "Where codigocompra=" + Codigo + " and codigoitem=" + Codigo01 + " " +
                "order by Codigoitem";
		 		  
	       Connection con = Conexion.getNuevaConexion();
		   pstmt = con.prepareStatement(query);
		   rs = pstmt.executeQuery();
		   int size=0;
		
		   if(rs!=null){
		      if(rs.last()==true){
		  	     size = rs.getRow();
			     rs.first(); 
			     tblComprasItemImpuestos[] Registros = new tblComprasItemImpuestos[size];
			     for(int f=0;f<size;f++){
			    	 Registros[f] = new tblComprasItemImpuestos();
	                 Registros[f].setCodigocompra(rs.getInt("Codigocompra")); 
	                 Registros[f].setCodigoitem(rs.getInt("Codigoitem")); 
	                 Registros[f].setCodigoimpuesto(rs.getString("Codigoimpuesto"));	                 
	                 Registros[f].setPorcentaje(rs.getBigDecimal("Porcentaje")); 
	                 Registros[f].setMontoimpuesto(rs.getBigDecimal("Montoimpuesto")); 
	                 Registros[f].setMontodeducible(rs.getBigDecimal("Montodeducible"));
	                 Registros[f].setFecha(rs.getDate("fecha"));
	                 Registros[f].setCodigoproducto(rs.getString("Codigoproducto"));
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
            setCodigocompra(0); 
            setCodigoitem(0); 
            setCodigoimpuesto("");
            setPorcentaje(null); 
            setMontoimpuesto(null);  
            setMontodeducible(null);
            setFecha(null);
            setCodigoproducto("");
	    }   
	    
	    public void settblComprasItemImpuestos(tblComprasItemImpuestos tbl){
            setCodigocompra(tbl.getCodigocompra()); 
            setCodigoitem(tbl.getCodigoitem()); 
            setCodigoimpuesto(tbl.getCodigoimpuesto());
            setPorcentaje(tbl.getPorcentaje()); 
            setMontoimpuesto(tbl.getMontoimpuesto()); 
            setMontodeducible(tbl.getMontodeducible());
            setFecha(tbl.getFecha());
            setCodigoproducto(tbl.getCodigoproducto());            
	    } 
	
}
