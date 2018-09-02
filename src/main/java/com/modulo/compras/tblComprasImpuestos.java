package com.modulo.compras;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;
import com.byos.sys.conexion.Conexion;

public class tblComprasImpuestos {
	
	Integer codigocompra;
	
	BigDecimal porcentaje;
	
	BigDecimal montoimpuesto;
	
	String codigoimpuesto;
	
	BigDecimal montodeducible;
	
	Date fechacompra;
	
	String descripcion;
	
	String codigoproveedor;
	
	public String getCodigoproveedor() {
		return codigoproveedor;
	}

	public void setCodigoproveedor(String codigoproveedor) {
		this.codigoproveedor = codigoproveedor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechacompra() {
		return fechacompra;
	}

	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
	}

	public BigDecimal getMontodeducible() {
		return montodeducible;
	}

	public void setMontodeducible(BigDecimal montodeducible) {
		this.montodeducible = montodeducible;
	}

	public String getCodigoimpuesto() {
		return codigoimpuesto;
	}

	public void setCodigoimpuesto(String codigoimpuesto) {
		this.codigoimpuesto = codigoimpuesto;
	}

	public Integer getCodigocompra() {
		return codigocompra;
	}

	public void setCodigocompra(Integer codigocompra) {
		this.codigocompra = codigocompra;
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
	
	public boolean existeCodigo(Integer Codigo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select codigocompra from tblComprasImpuestos where codigocompra=?";

		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, Codigo);
		rs = pstmt.executeQuery();
		int size = 0;

		if (rs != null) {
			if (rs.last() == true) {
				size = rs.getRow();
				ByosSql.CloseAll(con, pstmt, rs);
				return true;
			}
		}
		ByosSql.CloseAll(con, pstmt, rs);
		return false;
	}

	public String eliminar(Integer Codigo, Connection con) throws Exception {
		int i = 0;
		PreparedStatement pstmt = null;
		String query = "Delete From tblComprasImpuestos Where codigocompra="
				+ Codigo;
		pstmt = con.prepareStatement(query);
		i = pstmt.executeUpdate();
		return utilString.SQL_ELIMINADO;
	}

	public String guardar(tblComprasImpuestos tbl, Connection con)
			throws Exception {
		int i = 0;
		PreparedStatement pstmt = null;
		String query = "Insert into tblComprasImpuestos(Codigocompra, Porcentaje, Montoimpuesto, codigoimpuesto, fechacompra, montodeducible, codigoproveedor) values(?,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, tbl.getCodigocompra());
		pstmt.setBigDecimal(2, tbl.getPorcentaje());
		pstmt.setBigDecimal(3, tbl.getMontoimpuesto());
		pstmt.setString(4, tbl.getCodigoimpuesto());
		pstmt.setTimestamp(5,utilDate.DateToTimestamp(tbl.getFechacompra()));
		pstmt.setBigDecimal(6, tbl.getMontodeducible());
		pstmt.setString(7, tbl.getCodigoproveedor());
		i = pstmt.executeUpdate();
		return utilString.SQL_INSERTADO;
	}

	public tblComprasImpuestos[] Buscar(Integer Codigo)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "Select " 
		        + "Codigocompra," 
				+ "Porcentaje,"
				+ "Montoimpuesto,"
				+ "Codigoimpuesto,"
				+ "fechacompra," 
				+ "montodeducible, "
				+ "codigoproveedor "
				+ "from tblComprasImpuestos "
				+ "Where codigocompra=" + Codigo + " "
				+ "order by Codigocompra";

		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		int size = 0;

		if (rs != null) {
			if (rs.last() == true) {
				size = rs.getRow();
				rs.first();
				tblComprasImpuestos[] Registros = new tblComprasImpuestos[size];
				for (int f = 0; f < size; f++) {
					Registros[f] = new tblComprasImpuestos();
					Registros[f].setCodigocompra(rs.getInt("Codigocompra"));
					Registros[f].setPorcentaje(rs.getBigDecimal("Porcentaje"));
					Registros[f].setMontoimpuesto(rs.getBigDecimal("Montoimpuesto"));
					Registros[f].setCodigoimpuesto(rs.getString("Codigoimpuesto"));
					Registros[f].setFechacompra(rs.getDate("Fechacompra"));
					Registros[f].setMontodeducible(rs.getBigDecimal("Montodeducible"));
					Registros[f].setCodigoproveedor(rs.getString("Codigoproveedor"));
					rs.next();
				}
				ByosSql.CloseAll(con, pstmt, rs);
				return Registros;
			}
		}
		ByosSql.CloseAll(con, pstmt, rs);
		return null;
	}

	public void limpiar() {
		setCodigocompra(0);
		setPorcentaje(null);
		setMontoimpuesto(null);
		setCodigoimpuesto("");
		setMontodeducible(null);
		setFechacompra(null);
		setDescripcion("");
		setCodigoproveedor("");
	}

	public void setTblComprasImpuestos(tblComprasImpuestos tbl) {
		setCodigocompra(tbl.getCodigocompra());
		setPorcentaje(tbl.getPorcentaje());
		setMontoimpuesto(tbl.getMontoimpuesto());
		setCodigoimpuesto(tbl.getCodigoimpuesto());
		setMontodeducible(tbl.getMontodeducible());
		setFechacompra(tbl.getFechacompra());
		setDescripcion(tbl.getDescripcion());
		setCodigoproveedor(tbl.getCodigoproveedor());
	}

}
