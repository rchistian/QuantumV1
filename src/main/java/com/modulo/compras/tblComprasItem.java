package com.modulo.compras;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosValidar;
import com.modulo.componentes.utilString;
import com.modulo.componentes.utilDate;
import com.modulo.producto.tblProducto;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.conexion.Conexion;
import com.vaadin.data.util.IndexedContainer;

public class tblComprasItem {
	
	public tblComprasItemImpuestos[] TblComprasItemImpuestos;
	
	public tblComprasItemDescuentos[] TblComprasItemDescuentos;
	
	public tblProducto TblProducto = new tblProducto();
	
	public tblUnidadMedida TblUnidadMedida = new tblUnidadMedida();

	private Integer codigoitem;
	   
	private Integer codigocompra;
	   
	private Integer item;
	   
	private Date fechacompra;
	   
	private String codigoproducto;
	   
	private String descripcionproducto;
	   
	private BigDecimal costo;
	   
	private BigDecimal costoreal;
	   
	private BigDecimal cantidadreal;
	   
	private String codigomedida;
	   
	private BigDecimal equivalencia;
	   
	private BigDecimal cantidad;
	   
	private BigDecimal quedan;
	   
	private String detalle01;
	   
	private String detalle02;
	   
	private String detalle03;
	   
	private Date fechavencimiento;
	   
	private BigDecimal montodescuento;
	
	private BigDecimal montodescuentofinal;
	   
	private BigDecimal montoimpuesto;
	
	private IndexedContainer medidas;
	
	private String descripcionmedida;
	
	private BigDecimal montototal;
	
	private BigDecimal montoneto;
	
	public BigDecimal getMontodescuentofinal() {
		return montodescuentofinal;
	}

	public void setMontodescuentofinal(BigDecimal montodescuentofinal) {
		this.montodescuentofinal = montodescuentofinal;
	}

	public tblUnidadMedida getTblUnidadMedida() {
		return TblUnidadMedida;
	}

	public void setTblUnidadMedida(tblUnidadMedida tblUnidadMedida) {
		TblUnidadMedida = tblUnidadMedida;
	}

	
	public BigDecimal getMontoneto() {
		return montoneto;
	}

	public void setMontoneto(BigDecimal montoneto) {
		this.montoneto = montoneto;
	}

	public BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(BigDecimal montototal) {
		this.montototal = montototal;
	}

	public tblComprasItem(){
		limpiar();
	}
	   
    public tblComprasItemImpuestos[] getTblComprasItemImpuestos() {
		return TblComprasItemImpuestos;
	}

	public void setTblComprasItemImpuestos(
			tblComprasItemImpuestos[] tblComprasItemImpuestos) {
		TblComprasItemImpuestos = tblComprasItemImpuestos;
	}

	public String getDescripcionmedida() {
		return descripcionmedida;
	}

	public void setDescripcionmedida(String descripcionmedida) {
		this.descripcionmedida = descripcionmedida;
	}

	public tblComprasItemDescuentos[] getTblComprasItemDescuentos() {
		return TblComprasItemDescuentos;
	}

	public void setTblComprasItemDescuentos(
			tblComprasItemDescuentos[] tblComprasItemDescuentos) {
		TblComprasItemDescuentos = tblComprasItemDescuentos;
	}

	public IndexedContainer getMedidas() {
		return medidas;
	}

	public void setMedidas(IndexedContainer medidas) {
		this.medidas = medidas;
	}

	public Integer getCodigoitem() {
		return codigoitem;
	}

	public void setCodigoitem(Integer codigoitem) {
		this.codigoitem = codigoitem;
	}

	public Integer getCodigocompra() {
		return codigocompra;
	}

	public void setCodigocompra(Integer codigocompra) {
		this.codigocompra = codigocompra;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Date getFechacompra() {
		return fechacompra;
	}

	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
	}

	public String getCodigoproducto() {
		return codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public String getDescripcionproducto() {
		return descripcionproducto;
	}

	public void setDescripcionproducto(String descripcionproducto) {
		this.descripcionproducto = descripcionproducto;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getCostoreal() {
		return costoreal;
	}

	public void setCostoreal(BigDecimal costoreal) {
		this.costoreal = costoreal;
	}

	public BigDecimal getCantidadreal() {
		return cantidadreal;
	}

	public void setCantidadreal(BigDecimal cantidadreal) {
		this.cantidadreal = cantidadreal;
	}

	public String getCodigomedida() {
		return codigomedida;
	}

	public void setCodigomedida(String codigomedida) {
		this.codigomedida = codigomedida;
	}

	public BigDecimal getEquivalencia() {
		return equivalencia;
	}

	public void setEquivalencia(BigDecimal equivalencia) {
		this.equivalencia = equivalencia;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getQuedan() {
		return quedan;
	}

	public void setQuedan(BigDecimal quedan) {
		this.quedan = quedan;
	}

	public String getDetalle01() {
		return detalle01;
	}

	public void setDetalle01(String detalle01) {
		this.detalle01 = detalle01;
	}

	public String getDetalle02() {
		return detalle02;
	}

	public void setDetalle02(String detalle02) {
		this.detalle02 = detalle02;
	}

	public String getDetalle03() {
		return detalle03;
	}

	public void setDetalle03(String detalle03) {
		this.detalle03 = detalle03;
	}

	public Date getFechavencimiento() {
		return fechavencimiento;
	}

	public void setFechavencimiento(Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}

	public BigDecimal getMontodescuento() {
		return montodescuento;
	}

	public void setMontodescuento(BigDecimal montodescuento) {
		this.montodescuento = montodescuento;
	}

	public BigDecimal getMontoimpuesto() {
		return montoimpuesto;
	}

	public void setMontoimpuesto(BigDecimal montoimpuesto) {
		this.montoimpuesto = montoimpuesto;
	}
	
	public tblProducto getTblProducto() {
		return TblProducto;
	}

	public void setTblProducto(tblProducto tblProducto) {
		TblProducto = tblProducto;
	}	
	
	public void limpiar(){
		setTblComprasItemImpuestos(null);		
		setTblComprasItemDescuentos(null);
		TblUnidadMedida.limpiar();
		TblProducto.limpiar();
		setCodigoitem(null);
		setCodigocompra(null);
		setItem(null);
		setFechacompra(null);
		setCodigoproducto("");
		setDescripcionproducto("");
		setCosto(null);
		setCostoreal(null);
		setCantidadreal(null);
		setCodigomedida("");
		setEquivalencia(null);
		setCantidad(null);
		setQuedan(null);
		setDetalle01("");
		setDetalle02("");
		setDetalle03("");
		setFechavencimiento(null);
		setMontodescuento(null);
		setMontodescuentofinal(null);
		setMontoimpuesto(null);
		setMedidas(null);
		setDescripcionmedida("");	
		setMontototal(null);
		setMontoneto(null);
		
	}
	
	public void setTblComprasItem(tblComprasItem TblComprasItem){
		TblComprasItemImpuestos  = TblComprasItem.TblComprasItemImpuestos;		
		TblComprasItemDescuentos = TblComprasItem.TblComprasItemDescuentos;
		TblProducto = TblComprasItem.TblProducto;
		setCodigoitem(TblComprasItem.getCodigoitem());
		setCodigocompra(TblComprasItem.getCodigocompra());
		setItem(TblComprasItem.getItem());
		setFechacompra(TblComprasItem.getFechacompra());
		setCodigoproducto(TblComprasItem.getCodigoproducto());
		setDescripcionproducto(TblComprasItem.getDescripcionproducto());
		setCosto(TblComprasItem.getCosto());
		setCostoreal(TblComprasItem.getCostoreal());
		setCantidadreal(TblComprasItem.getCantidadreal());
		setCodigomedida(TblComprasItem.getCodigomedida());
		setEquivalencia(TblComprasItem.getEquivalencia());
		setCantidad(TblComprasItem.getCantidad());
		setQuedan(TblComprasItem.getQuedan());
		setDetalle01(TblComprasItem.getDetalle01());
		setDetalle02(TblComprasItem.getDetalle02());
		setDetalle03(TblComprasItem.getDetalle03());
		setFechavencimiento(TblComprasItem.getFechavencimiento());
		setMontodescuento(TblComprasItem.getMontodescuento());
		setMontodescuentofinal(TblComprasItem.getMontodescuentofinal());
		setMontoimpuesto(TblComprasItem.getMontoimpuesto());
		setMontototal(TblComprasItem.getMontototal());
		setMedidas(TblComprasItem.getMedidas());
		setDescripcionmedida(TblComprasItem.getDescripcionmedida());	
		setTblUnidadMedida(TblComprasItem.getTblUnidadMedida());
	}
	
	
	public boolean existeCodigo(Integer Codigo, Integer Codigo01) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select codigocompra from tblComprasItem where codigocompra=? and codigoitem=?";
		
		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1,Codigo);
		pstmt.setInt(2,Codigo);
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
	        
	public String eliminar(tblComprasItem tbl, Connection con) throws Exception {
		int i=0;	
		PreparedStatement pstmt = null;
		String query = "Delete From tblComprasItem Where codigocompra=" + tbl.getCodigocompra() + " and codigoitem=" + tbl.getCodigoitem();
		pstmt = con.prepareStatement(query);
		i = pstmt.executeUpdate();
		return utilString.SQL_ELIMINADO;    
	}
	
	public String eliminar(Integer Codigo, Connection con) throws Exception {
		int i=0;	
		PreparedStatement pstmt = null;
		String query = "Delete From tblComprasItem Where codigocompra=" + Codigo;
		pstmt = con.prepareStatement(query);
		i = pstmt.executeUpdate();
		new tblComprasItemImpuestos().eliminar(getCodigocompra(), con);
        new tblComprasItemDescuentos().eliminar(getCodigocompra(), con);
		return utilString.SQL_ELIMINADO;    
	}	
	       
	public String guardar(tblComprasItem tbl, Connection con) throws Exception { 
	    int i=0;	
		PreparedStatement pstmt = null;
		String query = "Insert into tblComprasItem(" +
		   "codigocompra," +
		   "codigoitem," +
		   "item," +
		   "fechacompra," +
		   "codigoproducto," +
		   "descripcionproducto," +
		   "costo," +
		   "costoreal," +
	 	   "cantidadreal," +
		   "codigomedida," +
		   "equivalencia," +
		   "cantidad," +
		   "quedan," +
		   "detalle01," +
		   "detalle02," +
		   "detalle03," +
		   "fechavencimiento," +
		   "montodescuento," +
		   "montodescuentofinal," +
		   "montoimpuesto" +
		   ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		
		pstmt = con.prepareStatement(query);
		
		pstmt.setInt(1,tbl.getCodigocompra());		
		pstmt.setInt(2,tbl.getCodigoitem());
		pstmt.setInt(3,tbl.getItem());
		pstmt.setTimestamp(4,utilDate.DateToTimestamp(tbl.getFechacompra()));
		pstmt.setString(5,tbl.getCodigoproducto());
		pstmt.setString(6,tbl.getDescripcionproducto());
		pstmt.setBigDecimal(7,tbl.getCosto());
		pstmt.setBigDecimal(8,tbl.getCostoreal());
		pstmt.setBigDecimal(9,tbl.getCantidadreal());
		pstmt.setString(10,tbl.getCodigomedida());
		pstmt.setBigDecimal(11,tbl.getEquivalencia());
		pstmt.setBigDecimal(12,tbl.getCantidad());
		pstmt.setBigDecimal(13,tbl.getQuedan());
		pstmt.setString(14,tbl.getDetalle01());
		pstmt.setString(15,tbl.getDetalle02());
		pstmt.setString(16,tbl.getDetalle03());
		pstmt.setTimestamp(17,utilDate.DateToTimestamp(tbl.getFechavencimiento()));
		pstmt.setBigDecimal(18,tbl.getMontodescuento());
		pstmt.setBigDecimal(19,tbl.getMontodescuentofinal());
		pstmt.setBigDecimal(20,tbl.getMontoimpuesto());	
		i = pstmt.executeUpdate();
		guardarComprasItemDescuentos(con, tbl);
		guardarComprasItemImpuestos(con, tbl);
		return utilString.SQL_INSERTADO;    
	}
	
    public boolean guardarComprasItemDescuentos(Connection con, tblComprasItem tbl) throws Exception {
        if(tbl.TblComprasItemDescuentos!=null && tbl.TblComprasItemDescuentos.length>0){
           for(int f=0;f<tbl.TblComprasItemDescuentos.length;f++){
        	   if(ByosValidar.Nulo(tbl.TblComprasItemDescuentos[f].getPorcentaje())>0 && 
        		  ByosValidar.Nulo(tbl.TblComprasItemDescuentos[f].getMontodescuento())>0){
        		   
        	      tbl.TblComprasItemDescuentos[f].setCodigocompra(tbl.getCodigocompra());
        	      tbl.TblComprasItemDescuentos[f].setCodigoitem(tbl.getCodigoitem());
        	      tbl.TblComprasItemDescuentos[f].setCodigoproducto(tbl.getCodigoproducto());
        	      tbl.TblComprasItemDescuentos[f].setFecha(tbl.getFechacompra());
        	      tbl.TblComprasItemDescuentos[f].guardar(tbl.TblComprasItemDescuentos[f], con);
        	     
        	   }
           } 
        }
        return true;
    }	
    
    public boolean guardarComprasItemImpuestos(Connection con, tblComprasItem tbl) throws Exception {
        if(tbl.TblComprasItemImpuestos!=null && tbl.TblComprasItemImpuestos.length>0){
           for(int f=0;f<tbl.TblComprasItemImpuestos.length;f++){
        	   if(ByosValidar.Nulo(tbl.TblComprasItemImpuestos[f].getPorcentaje())>0 && 
        		  ByosValidar.Nulo(tbl.TblComprasItemImpuestos[f].getMontoimpuesto())>0 &&
        		  ByosValidar.Nulo(tbl.TblComprasItemImpuestos[f].getMontodeducible())>0){
        		   
        		  tbl.TblComprasItemImpuestos[f].setCodigoproducto(tbl.getCodigoproducto());
        		  tbl.TblComprasItemImpuestos[f].setFecha(tbl.getFechacompra());
        	      tbl.TblComprasItemImpuestos[f].setCodigocompra(tbl.getCodigocompra());
        	      tbl.TblComprasItemImpuestos[f].setCodigoitem(tbl.getCodigoitem());        	   
        	      tbl.TblComprasItemImpuestos[f].guardar(tbl.TblComprasItemImpuestos[f], con);
        	   }
           } 
        }
        return true;
    }    
	              
	public ArrayList <tblComprasItem> BuscarArrayList(Integer Codigo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList <tblComprasItem> tblArray = new ArrayList<tblComprasItem>(); 
		   
		String query = "Select " +
				   "codigocompra," +
				   "codigoitem," +
				   "item," +
				   "fechacompra," +
				   "codigoproducto," +
				   "descripcionproducto," +
				   "costo," +
				   "costoreal," +
			 	   "cantidadreal," +
				   "codigomedida," +
				   "equivalencia," +
				   "cantidad," +
				   "quedan," +
				   "detalle01," +
				   "detalle02," +
				   "detalle03," +
				   "fechavencimiento," +
				   "montodescuento," +
				   "montodescuentofinal," +
				   "montoimpuesto " +
		   		"from tblComprasItem " +
        "Where codigocompra=" + Codigo + " " +
        "order by codigocompra, item";
		 		  
	    Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		int size=0;
		
		if(rs!=null){
		   if(rs.last()==true){
		  	  size = rs.getRow();
			  rs.first(); 
			  for(int f=0;f<size;f++){
			      tblComprasItem Registros = new tblComprasItem();
	              Registros.setCodigocompra(rs.getInt("Codigocompra")); 
	              Registros.setCodigoitem(rs.getInt("Codigoitem")); 
	              Registros.setItem(rs.getInt("Item"));
	              Registros.setFechacompra(rs.getDate("Fechacompra")); 
	              Registros.setCodigoproducto(rs.getString("Codigoproducto"));
	              Registros.setDescripcionproducto(rs.getString("Descripcionproducto"));
	              Registros.setCosto(rs.getBigDecimal("Costo")); 
	              Registros.setCostoreal(rs.getBigDecimal("Costoreal")); 
	              Registros.setCantidadreal(rs.getBigDecimal("Cantidadreal"));
	              Registros.setCodigomedida(rs.getString("Codigomedida"));             
	              Registros.setEquivalencia(rs.getBigDecimal("Equivalencia"));
	              Registros.setCantidad(rs.getBigDecimal("Cantidad"));
	              Registros.setQuedan(rs.getBigDecimal("Quedan"));
	              Registros.setDetalle01(rs.getString("Detalle01"));
	              Registros.setDetalle02(rs.getString("Detalle02"));
	              Registros.setDetalle03(rs.getString("Detalle03"));
	              Registros.setFechavencimiento(rs.getDate("Fechavencimiento")); 
	              Registros.setMontodescuento(rs.getBigDecimal("Montodescuento"));
	              Registros.setMontodescuentofinal(rs.getBigDecimal("Montodescuentofinal"));
	              Registros.setMontoimpuesto(rs.getBigDecimal("Montoimpuesto"));
	              
	              double xCosto=ByosValidar.Nulo(Registros.getCosto());
	              double xCantidad=ByosValidar.Nulo(Registros.getCantidad());
	              double xMontoDescuento=ByosValidar.Nulo(Registros.getMontodescuento());
	              double xMontoDescuentofinal=ByosValidar.Nulo(Registros.getMontodescuentofinal());
	              double xMontoImpuesto=ByosValidar.Nulo(Registros.getMontoimpuesto());
	              double xNeto=(xCosto*xCantidad)-xMontoDescuento-xMontoDescuentofinal;
	              double xMontototal=xNeto+xMontoImpuesto;
	              
	              Registros.setMontoneto(BigDecimal.valueOf(xNeto));
	              Registros.setMontototal(BigDecimal.valueOf(xMontototal));
	              
	              
	              TblProducto.buscarCodigo(Registros.getCodigoproducto());
	              Registros.TblComprasItemDescuentos = new tblComprasItemDescuentos().Buscar(Registros.getCodigocompra(), Registros.getItem());
	              Registros.TblComprasItemImpuestos = new tblComprasItemImpuestos().Buscar(Registros.getCodigocompra(), Registros.getItem());
	              
	              if(Registros.TblUnidadMedida.buscarCodigo(Registros.getCodigomedida())){
	                 Registros.setDescripcionmedida(Registros.TblUnidadMedida.getCodigomedida() + "," + Registros.TblUnidadMedida.getDescripcion());
	              }else{
	            	 Registros.setDescripcionmedida(""); 
	              }
	              
	              Registros.setMedidas(new tblProductoMedida().getProductoMedidaContainer(Registros.getCodigoproducto()));
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
   
	public tblComprasItem[] Buscar(Integer Codigo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		tblComprasItem  tblArray = new tblComprasItem(); 
		   
		String query = "Select " +
				   "codigocompra," +
				   "codigoitem," +
				   "item," +
				   "fechacompra," +
				   "codigoproducto," +
				   "descripcionproducto," +
				   "costo," +
				   "costoreal," +
			 	   "cantidadreal," +
				   "codigomedida," +
				   "equivalencia," +
				   "cantidad," +
				   "quedan," +
				   "detalle01," +
				   "detalle02," +
				   "detalle03," +
				   "fechavencimiento," +
				   "montodescuento," +
				   "montodescuentofinal," +
				   "montoimpuesto " +
		   		"from tblComprasItem " +
        "Where codigocompra=" + Codigo + " " +
        "order by Codigocompra, item";
		 		  
	    Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		int size=0;
		
		if(rs!=null){
		   if(rs.last()==true){
		  	  size = rs.getRow();
			  rs.first(); 
			  tblComprasItem[] Registros = new tblComprasItem[size];
			  for(int f=0;f<size;f++){
			      Registros[f] = new tblComprasItem();
	              Registros[f].setCodigocompra(rs.getInt("Codigocompra")); 
	              Registros[f].setCodigoitem(rs.getInt("Codigoitem")); 
	              Registros[f].setItem(rs.getInt("Item"));
	              Registros[f].setFechacompra(rs.getDate("Fechacompra")); 
	              Registros[f].setCodigoproducto(rs.getString("Codigoproducto"));
	              Registros[f].setDescripcionproducto(rs.getString("Descripcionproducto"));
	              Registros[f].setCosto(rs.getBigDecimal("Costo")); 
	              Registros[f].setCostoreal(rs.getBigDecimal("Costoreal")); 
	              Registros[f].setCantidadreal(rs.getBigDecimal("Cantidadreal"));
	              Registros[f].setCodigomedida(rs.getString("Codigomedida"));             
	              Registros[f].setEquivalencia(rs.getBigDecimal("Equivalencia"));
	              Registros[f].setCantidad(rs.getBigDecimal("Cantidad"));
	              Registros[f].setQuedan(rs.getBigDecimal("Quedan"));
	              Registros[f].setDetalle01(rs.getString("Detalle01"));
	              Registros[f].setDetalle02(rs.getString("Detalle02"));
	              Registros[f].setDetalle03(rs.getString("Detalle03"));
	              Registros[f].setFechavencimiento(rs.getDate("fechavencimiento")); 
	              Registros[f].setMontodescuento(rs.getBigDecimal("Montodescuento"));
	              Registros[f].setMontodescuentofinal(rs.getBigDecimal("Montodescuentofinal"));
	              Registros[f].setMontoimpuesto(rs.getBigDecimal("Montoimpuesto"));
	              
	              double xCosto=ByosValidar.Nulo(Registros[f].getCosto());
	              double xCantidad=ByosValidar.Nulo(Registros[f].getCantidad());
	              double xMontoDescuento=ByosValidar.Nulo(Registros[f].getMontodescuento());
	              double xMontoDescuentofinal=ByosValidar.Nulo(Registros[f].getMontodescuentofinal());
	              double xMontoImpuesto=ByosValidar.Nulo(Registros[f].getMontoimpuesto());
	              double xNeto=(xCosto*xCantidad)-xMontoDescuento-xMontoDescuentofinal;
	              double xMontototal=xNeto+xMontoImpuesto;
	              
	              Registros[f].setMontoneto(BigDecimal.valueOf(xNeto));
	              Registros[f].setMontototal(BigDecimal.valueOf(xMontototal));
	              
	              TblProducto.buscarCodigo(Registros[f].getCodigoproducto());
	              
	              Registros[f].TblComprasItemDescuentos = new tblComprasItemDescuentos().Buscar(Registros[f].getCodigocompra(), Registros[f].getItem());
	              Registros[f].TblComprasItemImpuestos = new tblComprasItemImpuestos().Buscar(Registros[f].getCodigocompra(), Registros[f].getItem());
	              
	              
	              if(Registros[f].TblUnidadMedida.buscarCodigo(Registros[f].getCodigomedida())){
		             Registros[f].setDescripcionmedida(Registros[f].TblUnidadMedida.getCodigomedida() + "," + Registros[f].TblUnidadMedida.getDescripcion());    
	              }else{
		             Registros[f].setDescripcionmedida(""); 
		          }
		          Registros[f].setMedidas(new tblProductoMedida().getProductoMedidaContainer(Registros[f].getCodigoproducto()));	              
			      rs.next();   
			  }
			  ByosSql.CloseAll(con,pstmt,rs);	   
			  return Registros;	     
		   } 
		} 
		ByosSql.CloseAll(con,pstmt,rs);	   
		return null;    
	}
 	
	
}
