package com.modulo.traslado;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.deposito.tblDeposito;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;
import com.vaadin.data.util.IndexedContainer;

public class tblTrasladoItem {
	
	public tblDeposito TblDepositoorigen = new tblDeposito();
	
	public tblDeposito TblDepositodestino = new tblDeposito();
	
	private Integer id;
	
	private Integer codigotraslado;
	
	private java.util.Date fechatraslado;
	
	private String codigoproducto;
	
	private String depositoorigen;
	
	private String depositodestino;
	
	private String codigomedida;
	
	private BigDecimal costo;
	
	private BigDecimal precio;
	
	private BigDecimal cantidad;
	
	private BigDecimal equivalencia;
	
	private String descripcionproducto;
	
	private String descripcionmedida;
	
	private IndexedContainer medidas;
	
	private BigDecimal existencia;
	
	public BigDecimal getExistencia() {
		return existencia;
	}

	public void setExistencia(BigDecimal existencia) {
		this.existencia = existencia;
	}

	public tblTrasladoItem(){
		limpiar();
	}
	
	public IndexedContainer getMedidas() {
		return medidas;
	}
	public void setMedidas(IndexedContainer medidas) {
		this.medidas = medidas;
	}
	public String getDescripcionproducto() {
		return descripcionproducto;
	}
	public void setDescripcionproducto(String descripcionproducto) {
		this.descripcionproducto = descripcionproducto;
	}
	public String getDescripcionmedida() {
		return descripcionmedida;
	}
	public void setDescripcionmedida(String descripcionmedida) {
		this.descripcionmedida = descripcionmedida;
	}
    public BigDecimal getEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(BigDecimal equivalencia) {
		this.equivalencia = equivalencia;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigotraslado() {
		return codigotraslado;
	}
	public void setCodigotraslado(Integer codigotraslado) {
		this.codigotraslado = codigotraslado;
	}
	public java.util.Date getFechatraslado() {
		return fechatraslado;
	}
	public void setFechatraslado(java.util.Date fechatraslado) {
		this.fechatraslado = fechatraslado;
	}
	public String getCodigoproducto() {
		return codigoproducto;
	}
	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}
	public String getDepositoorigen() {
		return depositoorigen;
	}
	public void setDepositoorigen(String depositoorigen) {
		this.depositoorigen = depositoorigen;
	}
	public String getDepositodestino() {
		return depositodestino;
	}
	public void setDepositodestino(String depositodestino) {
		this.depositodestino = depositodestino;
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
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public tblDeposito getTblDepositoorigen() {
		return TblDepositoorigen;
	}

	public void setTblDepositoorigen(tblDeposito tblDepositoorigen) {
		TblDepositoorigen = tblDepositoorigen;
	}

	public tblDeposito getTblDepositodestino() {
		return TblDepositodestino;
	}

	public void setTblDepositodestino(tblDeposito tblDepositodestino) {
		TblDepositodestino = tblDepositodestino;
	}

    public void limpiar(){
    	setMedidas(null);
    	setDescripcionproducto("");
    	setDescripcionmedida("");
    	setEquivalencia(null);
    	setId(null);
    	setCodigotraslado(null);
    	setFechatraslado(null);
    	setCodigoproducto("");
    	setDepositoorigen("");
    	setDepositodestino("");
    	setCodigomedida("");
    	setCosto(null);
    	setCantidad(null);
    	setCantidad(null);
    	setExistencia(null);
    	TblDepositoorigen.limpiar();
    	TblDepositodestino.limpiar();
    	
    }
    
    public void setTblTrasladoItem(tblTrasladoItem TblTrasladoItem){
    	setMedidas(TblTrasladoItem.getMedidas());
    	setDescripcionproducto(TblTrasladoItem.getDescripcionproducto());
    	setDescripcionmedida(TblTrasladoItem.getDescripcionmedida());
    	setEquivalencia(TblTrasladoItem.getEquivalencia());
    	setId(TblTrasladoItem.getId());
    	setCodigotraslado(TblTrasladoItem.getCodigotraslado());
    	setFechatraslado(TblTrasladoItem.getFechatraslado());
    	setCodigoproducto(TblTrasladoItem.getCodigoproducto());
    	setDepositoorigen(TblTrasladoItem.getDepositoorigen());
    	setDepositodestino(TblTrasladoItem.getDepositodestino());
    	setCodigomedida(TblTrasladoItem.getCodigomedida());
    	setCosto(TblTrasladoItem.getCosto());
    	setCantidad(TblTrasladoItem.getCantidad());	
    	setExistencia(TblTrasladoItem.getExistencia());	
    	setTblDepositoorigen(TblTrasladoItem.TblDepositoorigen);
    	setTblDepositodestino(TblTrasladoItem.TblDepositodestino);
    }
    
    
    public String guardar(tblTrasladoItem tbl, Connection con) throws Exception {  
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Insert into tblTrasladoItem(codigotraslado,fechatraslado,codigoproducto,depositoorigen,depositodestino,codigomedida,costo,cantidad,equivalencia,descripcionproducto,existencia) values(?,?,?,?,?,?,?,?,?,?,?)";
	   con.setAutoCommit(false);
	   pstmt = con.prepareStatement(query);
	   pstmt.setInt(1,tbl.getCodigotraslado());
	   pstmt.setTimestamp(2,utilDate.FechaHora());		  
	   pstmt.setString(3,tbl.getCodigoproducto());
	   pstmt.setString(4,tbl.getDepositoorigen());
	   pstmt.setString(5,tbl.getDepositodestino());		  
	   pstmt.setString(6,tbl.getCodigomedida());
       pstmt.setBigDecimal(7,tbl.getCosto());
	   pstmt.setBigDecimal(8,tbl.getCantidad());
	   pstmt.setBigDecimal(9,tbl.getEquivalencia());
	   pstmt.setString(10,tbl.getDescripcionproducto());
	   pstmt.setBigDecimal(11,tbl.getExistencia());
	   i = pstmt.executeUpdate();
	   return utilString.SQL_INSERTADO;
    } 
    
    public ArrayList <tblTrasladoItem> Buscar(Integer Codigo) throws Exception{
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  ArrayList <tblTrasladoItem> tblArray = new ArrayList<tblTrasladoItem>();
	 
	  if(Codigo!=null){
	     String query = "Select id,codigotraslado,fechatraslado,codigoproducto,descripcionproducto,depositoorigen,depositodestino,codigomedida,costo,cantidad,equivalencia,existencia from tbltrasladoitem where codigotraslado=?";
		
	     Connection con = Conexion.getNuevaConexion();
	     pstmt = con.prepareStatement(query);
	     pstmt.setInt(1,Codigo);
	     rs = pstmt.executeQuery();
	     int size=0;
	
	     if(rs!=null){
	        if(rs.last()==true){
  	   	       size = rs.getRow();
  		       rs.first(); 
  		       for(int f=0;f<size;f++){
		    	   tblTrasladoItem Registros = new tblTrasladoItem();  	   	       
		    	   Registros.setId(rs.getInt("id")); 
		    	   Registros.setCodigotraslado(rs.getInt("codigotraslado")); 
		    	   Registros.setFechatraslado(rs.getDate("fechatraslado"));                    
		    	   Registros.setDepositoorigen(rs.getString("depositoorigen"));                    
		    	   Registros.setDepositodestino(rs.getString("depositodestino"));
		    	   Registros.setDescripcionproducto(rs.getString("descripcionproducto"));
		    	   Registros.setEquivalencia(rs.getBigDecimal("equivalencia"));
		    	   Registros.setCodigoproducto(rs.getString("codigoproducto"));
		    	   Registros.setCodigomedida(rs.getString("codigomedida"));
		    	   Registros.setCosto(rs.getBigDecimal("costo"));
		    	   Registros.setCantidad(rs.getBigDecimal("cantidad"));	
		    	   Registros.setExistencia(rs.getBigDecimal("existencia"));	               
               
		    	   Registros.setMedidas(new tblProductoMedida().getProductoMedidaContainer(Registros.getCodigoproducto()));
           	       tblUnidadMedida TblUnidadMedida = new tblUnidadMedida();
           	       TblUnidadMedida.buscarCodigo(Registros.getCodigomedida());
           	       Registros.setDescripcionmedida(Registros.getCodigomedida() + "," + TblUnidadMedida.getDescripcion());
        	   
           	       Registros.TblDepositodestino.buscarCodigo(Registros.getDepositodestino());
           	       Registros.TblDepositoorigen.buscarCodigo(Registros.getDepositoorigen());
           	       
                   tblArray.add(Registros);
                   rs.next();
  		       }
  	           ByosSql.CloseAll(con,pstmt,rs);
  		       return tblArray;  		       
            }
         }
         limpiar();
         ByosSql.CloseAll(con,pstmt,rs);
	     return tblArray;
	  }
	  return tblArray;
    } 
    
    
    
}
