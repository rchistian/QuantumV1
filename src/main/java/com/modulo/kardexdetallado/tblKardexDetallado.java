package com.modulo.kardexdetallado;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.modulo.ajuste.tblAjuste;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;
import com.modulo.kardexdiario.tblKardexDiario;
import com.modulo.producto.tblProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.byos.sys.conexion.Conexion;

public class tblKardexDetallado {

	Integer id;
	   
	String codigoproducto;
	   
	String tipooperacion;
	   
	Date fechaoperacion;
	   
	String codigomedida;
	   
	BigDecimal movimiento;
	   
	BigDecimal costo;
	
	BigDecimal existencia;
	
	String codigodeposito;
	
	BigDecimal existenciadeposito;
	
	String codigooperecion;
	
	String descripcion;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigooperecion() {
		return codigooperecion;
	}

	public void setCodigooperecion(String codigooperecion) {
		this.codigooperecion = codigooperecion;
	}

	public String getCodigodeposito() {
		return codigodeposito;
	}

	public void setCodigodeposito(String codigodeposito) {
		this.codigodeposito = codigodeposito;
	}
	
	public BigDecimal getExistenciadeposito() {
		return existenciadeposito;
	}

	public void setExistenciadeposito(BigDecimal existenciadeposito) {
		this.existenciadeposito = existenciadeposito;
	}	
	public BigDecimal getExistencia() {
		return existencia;
	}

	public void setExistencia(BigDecimal existencia) {
		this.existencia = existencia;
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

	public String getTipooperacion() {
		return tipooperacion;
	}

	public void setTipooperacion(String tipooperacion) {
		this.tipooperacion = tipooperacion;
	}

	public Date getFechaoperacion() {
		return fechaoperacion;
	}

	public void setFechaoperacion(Date fechaoperacion) {
		this.fechaoperacion = fechaoperacion;
	}

	public String getCodigomedida() {
		return codigomedida;
	}

	public void setCodigomedida(String codigomedida) {
		this.codigomedida = codigomedida;
	}

	public BigDecimal getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(BigDecimal movimiento) {
		this.movimiento = movimiento;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

    public String guardar(tblKardexDetallado tbl, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;                                                                                                                  
	   String query = "Insert into tblKardexdetallado(codigoproducto, tipooperacion, fechaoperacion, codigomedida, movimiento, costo, existencia, codigooperacion, codigodeposito, existenciadeposito, descripcion) values(?,?,?,?,?,?,?,?,?,?,?)";
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,tbl.getCodigoproducto());
	   pstmt.setString(2,tbl.getTipooperacion());
       pstmt.setTimestamp(3,utilDate.FechaHora());
       pstmt.setString(4,tbl.getCodigomedida());
       pstmt.setBigDecimal(5,tbl.getMovimiento());
       pstmt.setBigDecimal(6,tbl.getCosto());
       pstmt.setBigDecimal(7,tbl.getExistencia());
       pstmt.setString(8,tbl.getCodigooperecion());
       pstmt.setString(9,tbl.getCodigodeposito()); 
       pstmt.setBigDecimal(10,tbl.getExistenciadeposito()); 
       pstmt.setString(11,tbl.getDescripcion()); 
       i = pstmt.executeUpdate();
	   return utilString.SQL_INSERTADO;
    }  
	
    public String Movimiento(tblKardexDetallado TblKardex, Connection con) throws Exception{
    	   tblProducto TblProducto = new tblProducto();
    	   tblKardexDiario TblKardexDiario = new tblKardexDiario();
    	   tblProductoDeposito TblProductoDeposito = new tblProductoDeposito();
           
           TblKardexDiario.setCodigoproducto(TblKardex.getCodigoproducto());
           TblKardexDiario.setDescripcion(TblKardex.getDescripcion());
           TblKardexDiario.setSalida(BigDecimal.valueOf(0));
           TblKardexDiario.setEntrada(BigDecimal.valueOf(0));
           TblKardexDiario.setCodigomedida(TblKardex.getCodigomedida());
           TblKardexDiario.setCosto(TblKardex.getCosto());
           if(TblKardex.getMovimiento().doubleValue()>=0){
              TblKardexDiario.setEntrada(BigDecimal.valueOf(TblKardex.getMovimiento().doubleValue()));
           }else{
              TblKardexDiario.setSalida(BigDecimal.valueOf(TblKardex.getMovimiento().doubleValue()*-1));
           }   
           TblKardexDiario.setExistencia(TblKardex.getExistencia());
           TblKardexDiario.setFecha(utilDate.Fecha());
           
           TblProductoDeposito.setExistencia(TblKardex.getExistenciadeposito());
           TblProductoDeposito.setCodigodeposito(TblKardex.getCodigodeposito());
           TblProductoDeposito.setCodigoproducto(TblKardex.getCodigoproducto());
           
           
           TblKardex.guardar(TblKardex, con);
           if(!TblKardex.getTipooperacion().equals(utilString.OPERACION_INVENTARIO_TRASLADO) && !TblKardex.getTipooperacion().equals(utilString.OPERACION_INVENTARIO_TRASLADO_PERDIA)){
              TblKardexDiario.guardar(TblKardexDiario, con);
           }
           
           TblProducto.Ajustar(TblKardexDiario.getCodigoproducto(), TblKardex.getExistencia(), con);
           TblProductoDeposito.guardar(TblProductoDeposito, con);
	       
    	   return utilString.SQL_MODIFICADO;
    }
    
    

}
