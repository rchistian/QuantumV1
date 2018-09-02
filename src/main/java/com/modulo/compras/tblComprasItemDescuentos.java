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

public class tblComprasItemDescuentos {
	
	private Integer codigocompra;
	
	private Integer codigoitem;
	
	private BigDecimal porcentaje;
	
	private BigDecimal montodescuento;	

	private String codigoproducto;
	
	private Date fecha;
	
	private String descripcion;
	
	private Integer item;
	
	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getCodigoproducto() {
		return codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
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

	public Integer getCodigoitem() {
		return codigoitem;
	}

	public void setCodigoitem(Integer codigoitem) {
		this.codigoitem = codigoitem;
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

	public boolean existeCodigo(Integer Codigo, Integer Codigo01)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select codigocompra from tblComprasItemDescuentos where codigocompra=? and codigoitem=?";

		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, Codigo);
		pstmt.setInt(2, Codigo01);
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
		String query = "Delete From tblComprasItemDescuentos Where codigocompra=" + Codigo;
		pstmt = con.prepareStatement(query);
		i = pstmt.executeUpdate();
		return utilString.SQL_ELIMINADO;
	}

	public String guardar(tblComprasItemDescuentos tbl, Connection con)
			throws Exception {
		int i = 0;
		PreparedStatement pstmt = null;
		String query = "Insert into tblComprasItemDescuentos(Codigocompra, Codigoitem, Porcentaje, Montodescuento, codigoproducto, fecha, descripcion) values(?,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, tbl.getCodigocompra());
		pstmt.setInt(2, tbl.getCodigoitem());
		pstmt.setBigDecimal(3, tbl.getPorcentaje());
		pstmt.setBigDecimal(4, tbl.getMontodescuento());
		pstmt.setString(5, tbl.getCodigoproducto());
		pstmt.setTimestamp(6,utilDate.DateToTimestamp(tbl.getFecha()));
		pstmt.setString(7, tbl.getDescripcion());
		i = pstmt.executeUpdate();
		return utilString.SQL_INSERTADO;
	}

	public tblComprasItemDescuentos[] Buscar(Integer Codigo, Integer Codigo01)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "Select " 
		             + "Codigocompra," 
				     + "Codigoitem,"
				     + "Porcentaje," 
				     + "Montodescuento, "
				     + "codigoproducto,"
				     + "fecha,"
				     + "descripcion "
				     + "from tblComprasItemDescuentos " 
				     + "Where codigocompra="
			 	     + Codigo + " and codigoitem=" + Codigo01 + " "
				     + "order by Codigoitem";

		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		int size = 0;

		if (rs != null) {
			if (rs.last() == true) {
				size = rs.getRow();
				rs.first();
				tblComprasItemDescuentos[] Registros = new tblComprasItemDescuentos[size];
				for (int f = 0; f < size; f++) {
					Registros[f] = new tblComprasItemDescuentos();
					Registros[f].setCodigocompra(rs.getInt("Codigocompra"));
					Registros[f].setCodigoitem(rs.getInt("Codigoitem"));
					Registros[f].setPorcentaje(rs.getBigDecimal("Porcentaje"));
					Registros[f].setMontodescuento(rs.getBigDecimal("Montodescuento"));
					Registros[f].setCodigoproducto(rs.getString("codigoproducto"));
					Registros[f].setFecha(rs.getDate("Fecha"));
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
		setCodigoitem(0);
		setPorcentaje(null);
		setMontodescuento(null);
		setCodigoproducto("");
		setFecha(null);
		setDescripcion("");
	}

	public void settblComprasItemDescuentos(tblComprasItemDescuentos tbl) {
		setCodigocompra(tbl.getCodigocompra());
		setCodigoitem(tbl.getCodigoitem());
		setPorcentaje(tbl.getPorcentaje());
		setMontodescuento(tbl.getMontodescuento());
		setCodigoproducto(tbl.getCodigoproducto());
		setFecha(tbl.getFecha());
		setDescripcion(tbl.getDescripcion());
	}

}
