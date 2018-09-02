package com.modulo.ajuste;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;

public class tblAjuste {
	
	private Integer id;
	
	private String codigoproducto;
	
	private Timestamp fechaajustes;
	
	private BigDecimal existenciadeposito;
	
	private BigDecimal existenciacontada;
	
	private BigDecimal cantidadajustada;
	
	private String codigomedida;
	
	private BigDecimal costo;
	
	private String codigodeposito;
	
	private Integer autocodigo;
	
	
	public Integer getAutocodigo() {
		return autocodigo;
	}

	public void setAutocodigo(Integer autocodigo) {
		this.autocodigo = autocodigo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoproducto() {
		return codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public Timestamp getFechaajustes() {
		return fechaajustes;
	}

	public void setFechaajustes(Timestamp fechaajustes) {
		this.fechaajustes = fechaajustes;
	}

	public BigDecimal getExistenciadeposito() {
		return existenciadeposito;
	}

	public void setExistenciadeposito(BigDecimal existenciadeposito) {
		this.existenciadeposito = existenciadeposito;
	}

	public BigDecimal getExistenciacontada() {
		return existenciacontada;
	}

	public void setExistenciacontada(BigDecimal existenciacontada) {
		this.existenciacontada = existenciacontada;
	}

	public BigDecimal getCantidadajustada() {
		return cantidadajustada;
	}

	public void setCantidadajustada(BigDecimal cantidadajustada) {
		this.cantidadajustada = cantidadajustada;
	}

	public String getCodigomedida() {
		return codigomedida;
	}

	public void setCodigomedida(String codigomedida) {
		this.codigomedida = codigomedida;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public String getCodigodeposito() {
		return codigodeposito;
	}

	public void setCodigodeposito(String codigodeposito) {
		this.codigodeposito = codigodeposito;
	}
	
	
	
    public String guardar(tblAjuste tbl, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Insert into tblAjustes(codigoproducto, fechaajustes, existenciadeposito, existenciacontada, cantidadajustada, codigomedida, costo, codigodeposito) values(?,?,?,?,?,?,?,?)";
	   pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	   con.setAutoCommit(false);
	   pstmt.setString(1,tbl.getCodigoproducto());
	   pstmt.setTimestamp(2,utilDate.FechaHora());
       pstmt.setBigDecimal(3,tbl.getExistenciadeposito());
       pstmt.setBigDecimal(4,tbl.getExistenciacontada());
       pstmt.setBigDecimal(5,tbl.getCantidadajustada());
       pstmt.setString(6,tbl.getCodigomedida());
       pstmt.setBigDecimal(7,tbl.getCosto()); 
       pstmt.setString(8,tbl.getCodigodeposito()); 
       i = pstmt.executeUpdate();
       setAutocodigo(ByosSql.ObtenerAutonumerico(pstmt));       
	   return utilString.SQL_INSERTADO;
    }
    
	
    
}
