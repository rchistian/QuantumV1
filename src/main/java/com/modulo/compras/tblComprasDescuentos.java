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

public class tblComprasDescuentos {
	
	private Integer codigocompra;
	
	private BigDecimal porcentaje;
	
	private BigDecimal montodescuento;
	
	private String codigoproveedor;
	
	private Date fecha;
	
	private String descripcion;
	
	private Integer item;
	
	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}
	
	public String getCodigoproveedor() {
		return codigoproveedor;
	}

	public void setCodigoproveedor(String codigoproveedor) {
		this.codigoproveedor = codigoproveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public BigDecimal getMontodescuento() {
		return montodescuento;
	}

	public void setMontodescuento(BigDecimal montodescuento) {
		this.montodescuento = montodescuento;
	}
	
	public boolean existeCodigo(Integer Codigo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select codigocompra from tblComprasDescuentos where codigocompra=?";

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
		String query = "Delete From tblComprasDescuentos Where codigocompra="
				+ Codigo;
		pstmt = con.prepareStatement(query);
		i = pstmt.executeUpdate();
		return utilString.SQL_ELIMINADO;
	}

	public String guardar(tblComprasDescuentos tbl, Connection con)
			throws Exception {
		int i = 0;
		PreparedStatement pstmt = null;
		String query = "Insert into tblComprasDescuentos(Codigocompra, Porcentaje, Montodescuento, codigoproveedor, fecha, descripcion) values(?,?,?,?,?,?)";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, tbl.getCodigocompra());
		pstmt.setBigDecimal(2, tbl.getPorcentaje());
		pstmt.setBigDecimal(3, tbl.getMontodescuento());
		pstmt.setString(4, tbl.getCodigoproveedor());
		pstmt.setTimestamp(5,utilDate.DateToTimestamp(tbl.getFecha()));
		pstmt.setString(6, tbl.getDescripcion());
		i = pstmt.executeUpdate();
		return utilString.SQL_INSERTADO;
	}

	public tblComprasDescuentos[] Buscar(Integer Codigo)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "Select " 
		               + "Codigocompra," 
				       + "Porcentaje,"
				       + "Montodescuento, "
				       + "codigoproveedor, " 
				       + "fecha, "  
				       + "descripcion "
				       + "from tblComprasDescuentos "
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
				tblComprasDescuentos[] Registros = new tblComprasDescuentos[size];
				for (int f = 0; f < size; f++) {
					Registros[f] = new tblComprasDescuentos();
					Registros[f].setCodigocompra(rs.getInt("Codigocompra"));
					Registros[f].setPorcentaje(rs.getBigDecimal("Porcentaje"));
					Registros[f].setMontodescuento(rs.getBigDecimal("Montodescuento"));
					Registros[f].setCodigoproveedor(rs.getString("codigoproveedor"));
					Registros[f].setFecha(rs.getDate("fecha"));
					Registros[f].setDescripcion(rs.getString("descripcion"));
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
		setMontodescuento(null);
		setCodigoproveedor("");
		setFecha(null);
		setDescripcion("");
	}

	public void settblComprasItemDescuentos(tblComprasDescuentos tbl) {
		setCodigocompra(tbl.getCodigocompra());
		setPorcentaje(tbl.getPorcentaje());
		setMontodescuento(tbl.getMontodescuento());
		setCodigoproveedor(tbl.getCodigoproveedor());
		setFecha(tbl.getFecha());
		setDescripcion(tbl.getDescripcion());		
	}

}
