package com.modulo.compras;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.byos.sys.conexion.Conexion;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosValidar;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;
import com.modulo.deposito.tblDeposito;
import com.modulo.kardexdetallado.tblKardexDetallado;
import com.modulo.medidasdefault.tblMedidasDefault;
import com.modulo.producto.tblProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.modulo.proveedor.tblProveedor;


public class tblCompras {
	
	public tblComprasItem TblComprasItem[];
	
	public tblComprasImpuestos TblComprasImpuestos[];
	
	public tblComprasDescuentos TblComprasDescuentos[];

	public tblDeposito TblDeposito = new tblDeposito();
	
	public tblProveedor TblProveedor = new tblProveedor();
	
	public tblMedidasDefault TblMedidasDefault = new tblMedidasDefault();

	private Integer codigocompra;
	
	private String codigoproveedor;
	
	private String descripproveedor;
	
	private String codigodeposito;
	
	private Date fechacompra;
	
	private Date fechacomprareal;
	
	private String tipodocumento;
	
	private BigDecimal montototal;
	
	private BigDecimal montoexento;
	
	private BigDecimal montodeducible;
	
	private BigDecimal montoneto;
	
	private BigDecimal montoimpuesto;
	
	private BigDecimal montodescuentolineal;
	
	private BigDecimal montodescuentolfinal;
	
	private String codigodocumento;
	
	private String numerocontrol;
	
	private Date fechadocumento;
	
	private Date fechaaplicacion;
	
	private String planillaimportacion;
	
	private String expedienteimportacion;
	
	private String descripcion01;
	
	private String descripcion02;
	
	private String descripcion03;
	
	private String estado;	
	
	private String tipocompras;
	
	private String codigocontable;
	
	private String codigoislr;
	
	private BigDecimal montodeducibleislr;
	
	private BigDecimal porcentajeislr;
	
	private BigDecimal montoislr;
	
	private String documentoafectado;

	private String libro;
	
	private BigDecimal montocancelado;
	
	private BigDecimal montopendiente;
	
	private String codigoretiva;
	
	private Date fecharetiva;
	
	private String codigoretalcaldia;
	
	private Date fecharetalcaldia;
	
	private String codigodefault;
	

	public tblMedidasDefault getTblMedidasDefault() {
		return TblMedidasDefault;
	}

	public void setTblMedidasDefault(tblMedidasDefault tblMedidasDefault) {
		TblMedidasDefault = tblMedidasDefault;
	}	
	
	public String getCodigodefault() {
		return codigodefault;
	}

	public void setCodigodefault(String codigodefault) {
		this.codigodefault = codigodefault;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public BigDecimal getMontocancelado() {
		return montocancelado;
	}

	public void setMontocancelado(BigDecimal montocancelado) {
		this.montocancelado = montocancelado;
	}

	public BigDecimal getMontopendiente() {
		return montopendiente;
	}

	public void setMontopendiente(BigDecimal montopendiente) {
		this.montopendiente = montopendiente;
	}

	public String getCodigoretiva() {
		return codigoretiva;
	}

	public void setCodigoretiva(String codigoretiva) {
		this.codigoretiva = codigoretiva;
	}

	public Date getFecharetiva() {
		return fecharetiva;
	}

	public void setFecharetiva(Date fecharetiva) {
		this.fecharetiva = fecharetiva;
	}

	public String getCodigoretalcaldia() {
		return codigoretalcaldia;
	}

	public void setCodigoretalcaldia(String codigoretalcaldia) {
		this.codigoretalcaldia = codigoretalcaldia;
	}

	public Date getFecharetalcaldia() {
		return fecharetalcaldia;
	}

	public void setFecharetalcaldia(Date fecharetalcaldia) {
		this.fecharetalcaldia = fecharetalcaldia;
	}
	
	public String getDocumentoafectado() {
		return documentoafectado;
	}

	public void setDocumentoafectado(String documentoafectado) {
		this.documentoafectado = documentoafectado;
	}

	public String getCodigocontable() {
		return codigocontable;
	}

	public void setCodigocontable(String codigocontable) {
		this.codigocontable = codigocontable;
	}

	public String getCodigoislr() {
		return codigoislr;
	}

	public void setCodigoislr(String codigoislr) {
		this.codigoislr = codigoislr;
	}

	public BigDecimal getMontodeducibleislr() {
		return montodeducibleislr;
	}

	public void setMontodeducibleislr(BigDecimal montodeducibleislr) {
		this.montodeducibleislr = montodeducibleislr;
	}

	public BigDecimal getPorcentajeislr() {
		return porcentajeislr;
	}

	public void setPorcentajeislr(BigDecimal porcentajeislr) {
		this.porcentajeislr = porcentajeislr;
	}

	public BigDecimal getMontoislr() {
		return montoislr;
	}

	public void setMontoislr(BigDecimal montoislr) {
		this.montoislr = montoislr;
	}
	
	public tblProveedor getTblProveedor() {
		return TblProveedor;
	}

	public void setTblProveedor(tblProveedor tblProveedor) {
		TblProveedor = tblProveedor;
	}	
	
	public tblDeposito getTblDeposito() {
		return TblDeposito;
	}

	public void setTblDeposito(tblDeposito tblDeposito) {
		TblDeposito = tblDeposito;
	}	
	
	public tblCompras(){
		limpiar();
	}
	
	public tblComprasItem[] getTblComprasItem() {
		return TblComprasItem;
	}

	public void setTblComprasItem(tblComprasItem[] tblComprasItem) {
		TblComprasItem = tblComprasItem;
	}

	public tblComprasImpuestos[] getTblComprasImpuestos() {
		return TblComprasImpuestos;
	}

	public void setTblComprasImpuestos(tblComprasImpuestos[] tblComprasImpuestos) {
		TblComprasImpuestos = tblComprasImpuestos;
	}

	public tblComprasDescuentos[] getTblComprasDescuentos() {
		return TblComprasDescuentos;
	}

	public void setTblComprasDescuentos(tblComprasDescuentos[] tblComprasDescuentos) {
		TblComprasDescuentos = tblComprasDescuentos;
	}

	public String getTipocompras() {
		return tipocompras;
	}

	public void setTipocompras(String tipocompras) {
		this.tipocompras = tipocompras;
	}

	public Integer getCodigocompra() {
		return codigocompra;
	}

	public void setCodigocompra(Integer codigocompra) {
		this.codigocompra = codigocompra;
	}

	public String getCodigoproveedor() {
		return codigoproveedor;
	}

	public void setCodigoproveedor(String codigoproveedor) {
		this.codigoproveedor = codigoproveedor;
	}

	public String getDescripproveedor() {
		return descripproveedor;
	}

	public void setDescripproveedor(String descripproveedor) {
		this.descripproveedor = descripproveedor;
	}

	public String getCodigodeposito() {
		return codigodeposito;
	}

	public void setCodigodeposito(String codigodeposito) {
		this.codigodeposito = codigodeposito;
	}

	public Date getFechacompra() {
		return fechacompra;
	}

	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
	}

	public Date getFechacomprareal() {
		return fechacomprareal;
	}

	public void setFechacomprareal(Date fechacomprareal) {
		this.fechacomprareal = fechacomprareal;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public BigDecimal getMontototal() {
		return montototal;
	}

	public void setMontototal(BigDecimal montototal) {
		this.montototal = montototal;
	}

	public BigDecimal getMontoexento() {
		return montoexento;
	}

	public void setMontoexento(BigDecimal montoexento) {
		this.montoexento = montoexento;
	}

	public BigDecimal getMontodeducible() {
		return montodeducible;
	}

	public void setMontodeducible(BigDecimal montodeducible) {
		this.montodeducible = montodeducible;
	}

	public BigDecimal getMontoneto() {
		return montoneto;
	}

	public void setMontoneto(BigDecimal montoneto) {
		this.montoneto = montoneto;
	}

	public BigDecimal getMontoimpuesto() {
		return montoimpuesto;
	}

	public void setMontoimpuesto(BigDecimal montoimpuesto) {
		this.montoimpuesto = montoimpuesto;
	}

	public BigDecimal getMontodescuentolineal() {
		return montodescuentolineal;
	}

	public void setMontodescuentolineal(BigDecimal montodescuentolineal) {
		this.montodescuentolineal = montodescuentolineal;
	}

	public BigDecimal getMontodescuentolfinal() {
		return montodescuentolfinal;
	}

	public void setMontodescuentolfinal(BigDecimal montodescuentolfinal) {
		this.montodescuentolfinal = montodescuentolfinal;
	}

	public String getCodigodocumento() {
		return codigodocumento;
	}

	public void setCodigodocumento(String codigodocumento) {
		this.codigodocumento = codigodocumento;
	}

	public String getNumerocontrol() {
		return numerocontrol;
	}

	public void setNumerocontrol(String numerocontrol) {
		this.numerocontrol = numerocontrol;
	}

	public Date getFechadocumento() {
		return fechadocumento;
	}

	public void setFechadocumento(Date fechadocumento) {
		this.fechadocumento = fechadocumento;
	}

	public Date getFechaaplicacion() {
		return fechaaplicacion;
	}

	public void setFechaaplicacion(Date fechaaplicacion) {
		this.fechaaplicacion = fechaaplicacion;
	}

	public String getPlanillaimportacion() {
		return planillaimportacion;
	}

	public void setPlanillaimportacion(String planillaimportacion) {
		this.planillaimportacion = planillaimportacion;
	}

	public String getExpedienteimportacion() {
		return expedienteimportacion;
	}

	public void setExpedienteimportacion(String expedienteimportacion) {
		this.expedienteimportacion = expedienteimportacion;
	}

	public String getDescripcion01() {
		return descripcion01;
	}

	public void setDescripcion01(String descripcion01) {
		this.descripcion01 = descripcion01;
	}

	public String getDescripcion02() {
		return descripcion02;
	}

	public void setDescripcion02(String descripcion02) {
		this.descripcion02 = descripcion02;
	}

	public String getDescripcion03() {
		return descripcion03;
	}

	public void setDescripcion03(String descripcion03) {
		this.descripcion03 = descripcion03;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


    public void limpiar(){
    	setTblComprasItem(null);   	
    	setTblComprasImpuestos(null);    	
    	setTblComprasDescuentos(null);    	
    	setCodigocompra(null);    	
    	setCodigoproveedor("");    	
    	setDescripproveedor("");    	
    	setCodigodeposito("");    	
    	setFechacompra(null);    	
    	setFechacomprareal(null);    	
    	setTipodocumento("");    	
    	setMontototal(null);    	
    	setMontoexento(null);    	
    	setMontodeducible(null);    	
    	setMontoneto(null);    	
    	setMontoimpuesto(null);    	
    	setMontodescuentolineal(null);    	
    	setMontodescuentolfinal(null);    	
    	setCodigodocumento("");    	
    	setNumerocontrol("");    	
    	setFechadocumento(null);    	
    	setFechaaplicacion(null);    	
    	setPlanillaimportacion("");    	
    	setExpedienteimportacion("");    	
    	setDescripcion01("");    	
    	setDescripcion02("");    	
    	setDescripcion03("");    	
    	setEstado("");	
    	setTipocompras("");
    	
    	setCodigocontable("");
    	setCodigoislr("");
    	setMontodeducibleislr(null);
    	setPorcentajeislr(null);
    	setMontoislr(null);
    	setDocumentoafectado("");
    	
    	setLibro("");   	
    	setMontocancelado(null);
    	setMontopendiente(null);
    	setCodigoretiva("");
    	setFecharetiva(null);
    	setCodigoretalcaldia("");
    	setFecharetalcaldia(null);
    	
    	setCodigodefault(null);
    	
    	TblComprasItem=null;
    	TblComprasImpuestos=null;    	
    	TblComprasDescuentos=null;
    	TblDeposito.limpiar();
    	TblProveedor.limpiar();
    	TblMedidasDefault.limpiar();
    }

	public void setTblCompras(tblCompras TblCompras){
		setTblComprasItem(TblCompras.TblComprasItem); 
		setTblComprasImpuestos(TblCompras.TblComprasImpuestos);		
		setTblComprasDescuentos(TblCompras.TblComprasDescuentos);
		setTblMedidasDefault(TblCompras.TblMedidasDefault);
		
    	setCodigocompra(TblCompras.getCodigocompra());    	
    	setCodigoproveedor(TblCompras.getCodigoproveedor());    	   	
    	setCodigodeposito(TblCompras.getCodigodeposito());    	
    	setFechacompra(TblCompras.getFechacompra());    
    	setFechacomprareal(utilDate.Fecha());    	
    	setTipodocumento(TblCompras.getTipodocumento());    	
    	setMontototal(TblCompras.getMontototal());    	
    	setMontoexento(TblCompras.getMontoexento());    	
    	setMontodeducible(TblCompras.getMontodeducible());    	
    	setMontoneto(TblCompras.getMontoneto());    	
    	setMontoimpuesto(TblCompras.getMontoimpuesto());    	
    	setMontodescuentolineal(TblCompras.getMontodescuentolineal());    	
    	setMontodescuentolfinal(TblCompras.getMontodescuentolfinal());    	
    	setCodigodocumento(TblCompras.getCodigodocumento());    	
    	setNumerocontrol(TblCompras.getNumerocontrol());    	
    	setFechadocumento(TblCompras.getFechadocumento());    	
    	setFechaaplicacion(TblCompras.getFechaaplicacion());    	
    	setPlanillaimportacion(TblCompras.getPlanillaimportacion());    	
    	setExpedienteimportacion(TblCompras.getExpedienteimportacion());    	
    	setDescripcion01(TblCompras.getDescripcion01());    	
    	setDescripcion02(TblCompras.getDescripcion02());    	
    	setDescripcion03(TblCompras.getDescripcion03());    	
    	setEstado(TblCompras.getEstado());	
    	setTipocompras(TblCompras.getTipocompras());
    	
    	setCodigocontable(TblCompras.getCodigocontable());
    	setCodigoislr(TblCompras.getCodigoislr());
    	setMontodeducibleislr(TblCompras.getMontodeducibleislr());
    	setPorcentajeislr(TblCompras.getPorcentajeislr());
    	setMontoislr(TblCompras.getMontoislr());
    	setDocumentoafectado(TblCompras.getDocumentoafectado());
    	
    	setLibro(TblCompras.getLibro());   	
    	setMontocancelado(TblCompras.getMontocancelado());
    	setMontopendiente(TblCompras.getMontopendiente());
    	setCodigoretiva(TblCompras.getCodigoretiva());
    	setFecharetiva(TblCompras.getFecharetiva());
    	setCodigoretalcaldia(TblCompras.getCodigoretalcaldia());
    	setFecharetalcaldia(TblCompras.getFecharetalcaldia()); 
    	
    	setCodigodefault(TblCompras.getCodigodefault());
    	
    	TblDeposito.setDeposito(TblCompras.TblDeposito);
    	TblProveedor.setProveedor(TblCompras.TblProveedor);
    	TblMedidasDefault.setMedidasDefault(TblCompras.TblMedidasDefault);
		
	}
	
	
	public boolean existeCodigo(Integer Codigo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select codigocompra from tblCompras where codigocompra=?";
		
		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1,Codigo);
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
	        
	public String eliminar(tblCompras tbl, Connection con) throws Exception {
		int i=0;	
		PreparedStatement pstmt = null;
		String query = "Delete From tblCompras Where codigocompra=" + tbl.getCodigocompra();
		pstmt = con.prepareStatement(query);
		i = pstmt.executeUpdate();
		return utilString.SQL_ELIMINADO;    
	}
	       
	              
	public ArrayList <tblCompras> Buscar(buscarCompras tbl) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String InSql="";
		int Estado=0;
		String Rango="";
		ArrayList <tblCompras> tblArray = new ArrayList<tblCompras>(); 
		   
		String query = "SELECT DISTINCT " +
		        "a.codigocompra," +
		        "a.codigoproveedor," +
		        "a.descripproveedor," +
		        "a.codigodeposito," +
		        "a.fechacompra," +
		        "a.fechacomprareal," +
		        "a.tipodocumento," +
		        "a.montototal," +
		        "a.montoexento," +
		        "a.montodeducible," +
		        "a.montoneto," +
		        "a.montoimpuesto," +
		        "a.montodescuentolineal," +
		        "a.montodescuentolfinal," +
		        "a.codigodocumento," +
		        "a.numerocontrol," +
		        "a.fechadocumento," +
		        "a.fechaaplicacion," +
		        "a.planillaimportacion," +
		        "a.expedienteimportacion," +
		        "a.descripcion01," +
		        "a.descripcion02," +
		        "a.descripcion03," +
		        "a.estado," +
	            "a.tipocompras, " +
	            "a.codigocontable, " +
	            "a.codigoislr, " +
	            "a.montodeducibleislr, " +
	            "a.porcentajeislr, " +
	            "a.montoislr, " +
	            "a.documentoafectado, " +
	            "a.libro, " +
	            "a.montocancelado, " +
	            "a.montopendiente, " +
	            "a.codigoretiva, " +
	            "a.fecharetiva, " +
	            "a.codigoretalcaldia, " +
	            "a.fecharetalcaldia, " +
	            "a.codigodefault " +
		   		"from tblCompras a " +
	            "left join tblComprasItem b on a.codigocompra=b.codigocompra ";
	            
	   if(tbl.getCodigodeposito()!=null &&!tbl.getCodigodeposito().equals("")){
		  InSql = InSql + " and a.codigodeposito like '" + tbl.getCodigodeposito().replace("*", "%") + "'";
		  Estado=1;
	   }
 
	   if(tbl.getEstado()!=null &&!tbl.getEstado().equals("")){
		  InSql = InSql + " and a.estado like '" + tbl.getEstado().replace("*", "%") + "'";
		  Estado=1;
	   }
	   
	   Rango="";
	   if(tbl.getCodigocompra01()!=null){
		  Rango = " and a.Codigocompra = '" + tbl.getCodigocompra01() + "'";
		  Estado=1;
	   }
	   if(tbl.getCodigocompra02()!=null){
		  Rango = " and a.Codigocompra = '" + tbl.getCodigocompra02() + "'";
		  Estado=1;
	   }
	   if(tbl.getCodigocompra01()!=null && tbl.getCodigocompra02()!=null){
		  Rango = " and a.codigocompra >= '" + tbl.getCodigocompra01() + "' and a.codigocompra <= '" + tbl.getCodigocompra02() + "' ";
		  Estado=1;
	   }	   
	   InSql = InSql + Rango;
	   
	   
	   Rango="";
	   if(tbl.getFechacompra01()!=null){
		  Rango = " and a.fechacompra >= '" + utilDate.FormatoFecha(tbl.getFechacompra01(), "yyyy-MM-dd") + " 00:00:00' and a.fechacompra <= '" + utilDate.FormatoFecha(tbl.getFechacompra01(), "yyyy-MM-dd") + " 23:59:59' ";
		  Estado=1;
	   }
	   if(tbl.getFechacompra02()!=null){
		  Rango = " and a.fechacompra >= '" + utilDate.FormatoFecha(tbl.getFechacompra02(), "yyyy-MM-dd") + " 00:00:00' and a.fechacompra <= '" + utilDate.FormatoFecha(tbl.getFechacompra02(), "yyyy-MM-dd") + " 23:59:59' ";
		  Estado=1;
	   }
	   if(tbl.getFechacompra01()!=null && tbl.getFechacompra02()!=null){
		  Rango = " and a.fechacompra >= '" + utilDate.FormatoFecha(tbl.getFechacompra01(), "yyyy-MM-dd") + " 00:00:00' and a.fechacompra <= '" + utilDate.FormatoFecha(tbl.getFechacompra02(), "yyyy-MM-dd") + " 23:59:59' ";
		  Estado=1;
	   }	   
	   InSql = InSql + Rango;
	   
	   if(tbl.getCodigoproducto()!=null && !tbl.getCodigoproducto().equals("")){
		  InSql = InSql + " and b.codigoproducto like '" + tbl.getCodigoproducto().replace("*", "%") + "'";
		  Estado=1;
	   }	   
	   if(tbl.getDescripcionproducto()!=null && !tbl.getDescripcionproducto().equals("")){
		  InSql = InSql + " and b.descripcionproducto like '" + tbl.getDescripcionproducto().replace("*", "%") + "'";
		  Estado=1;
	   }

	   if(Estado==1){
          query = query + " Where 1=1 " + InSql;
	   }
	   
       query = query  + " order by a.codigocompra";
       //group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31 
       
       System.out.println(query);
       
	    Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		int size=0;
		
		if(rs!=null){
		   if(rs.last()==true){
		  	  size = rs.getRow();
			  rs.first(); 
			  for(int f=0;f<size;f++){
			      tblCompras Registros = new tblCompras();
			      Registros.setCodigocompra(rs.getInt("Codigocompra"));    	
			      Registros.setCodigoproveedor(rs.getString("Codigoproveedor"));    	   	
			      Registros.setCodigodeposito(rs.getString("Codigodeposito"));    	
			      Registros.setFechacompra(rs.getDate("Fechacompra"));    
			      Registros.setFechacomprareal(rs.getDate("Fechacomprareal"));    	
			      Registros.setTipodocumento(rs.getString("Tipodocumento"));    	
			      Registros.setMontototal(rs.getBigDecimal("Montototal"));    	
			      Registros.setMontoexento(rs.getBigDecimal("Montoexento"));    	
			      Registros.setMontodeducible(rs.getBigDecimal("Montodeducible"));    	
			      Registros.setMontoneto(rs.getBigDecimal("Montoneto"));    	
			      Registros.setMontoimpuesto(rs.getBigDecimal("Montoimpuesto"));    	
			      Registros.setMontodescuentolineal(rs.getBigDecimal("Montodescuentolineal"));    	
			      Registros.setMontodescuentolfinal(rs.getBigDecimal("Montodescuentolfinal"));    	
			      Registros.setCodigodocumento(rs.getString("Codigodocumento"));    	
			      Registros.setNumerocontrol(rs.getString("Numerocontrol"));    	
			      Registros.setFechadocumento(rs.getDate("Fechadocumento"));    	
			      Registros.setFechaaplicacion(rs.getDate("Fechaaplicacion"));    	
			      Registros.setPlanillaimportacion(rs.getString("Planillaimportacion"));    	
			      Registros.setExpedienteimportacion(rs.getString("Expedienteimportacion"));    	
			      Registros.setDescripcion01(rs.getString("Descripcion01"));    	
			      Registros.setDescripcion02(rs.getString("Descripcion02"));    	
			      Registros.setDescripcion03(rs.getString("Descripcion03"));    	
			      Registros.setEstado(rs.getString("Estado"));	
			      Registros.setTipocompras(rs.getString("Tipocompras")); 
			      
			      Registros.setCodigocontable(rs.getString("Codigocontable"));
			      Registros.setCodigoislr(rs.getString("Codigoislr"));
			      Registros.setMontodeducibleislr(rs.getBigDecimal("Montodeducibleislr"));
			      Registros.setPorcentajeislr(rs.getBigDecimal("Porcentajeislr"));
			      Registros.setMontoislr(rs.getBigDecimal("Montoislr"));
			      Registros.setDocumentoafectado(rs.getString("Documentoafectado"));
			      
			      Registros.setLibro(rs.getString("Libro"));   	
			      Registros.setMontocancelado(rs.getBigDecimal("Montocancelado"));
			      Registros.setMontopendiente(rs.getBigDecimal("Montopendiente"));
			      Registros.setCodigoretiva(rs.getString("Codigoretiva"));
			      Registros.setFecharetiva(rs.getDate("Fecharetiva"));
			      Registros.setCodigoretalcaldia(rs.getString("Codigoretalcaldia"));
			      Registros.setFecharetalcaldia(rs.getDate("Fecharetalcaldia"));
			      
			      Registros.setCodigodefault(rs.getString("Codigodefault"));
			      
			      Registros.TblDeposito.buscarCodigo(Registros.getCodigodeposito());
			      Registros.TblProveedor.buscarCodigo(Registros.getCodigoproveedor());
			      Registros.TblMedidasDefault.buscarCodigo(Registros.getCodigodefault());
			      Registros.TblComprasItem = new tblComprasItem().Buscar(Registros.getCodigocompra());
			      Registros.TblComprasDescuentos = new tblComprasDescuentos().Buscar(Registros.getCodigocompra());
			      Registros.TblComprasImpuestos = new tblComprasImpuestos().Buscar(Registros.getCodigocompra());
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
    
	public String totalizarCompra(tblCompras tbl) throws Exception {
		int i = 0;
		PreparedStatement pstmt = null;
		Connection con = Conexion.getNuevaConexion();

		try {
			con.setAutoCommit(false);
			if (existeCodigo(tbl.getCodigocompra())) {
				String query = "update tblCompras set " +
				"estado=? " +
				"where Codigocompra=" + tbl.getCodigocompra();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, tbl.getEstado());
				System.out.println(pstmt);
				GuardarComprasItem(tbl, con, tbl.getCodigocompra());
				i = pstmt.executeUpdate();
				GuardarKardex(tbl, con, tbl.getCodigocompra());
				con.commit();
				return utilString.SQL_MODIFICADO;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		ByosSql.RollBack(con);
		ByosSql.CloseAll(con, pstmt, null);
		return utilString.SQL_ERROR;
	}
	
	
	
	public String guardar(tblCompras tbl) throws Exception { 
	    int i=0;	
		PreparedStatement pstmt = null;
		Connection con = Conexion.getNuevaConexion();
		

	    try{
			con.setAutoCommit(false);
			if (!existeCodigo(tbl.getCodigocompra())) {					
				String query = "Insert into tblCompras(" +
					        "codigocompra," +
					        "codigoproveedor," +
					        "descripproveedor," +
					        "codigodeposito," +
					        "fechacompra," +
					        "fechacomprareal," +
					        "tipodocumento," +
					        "montototal," +
					        "montoexento," +
					        "montodeducible," +
					        "montoneto," +
					        "montoimpuesto," +
					        "montodescuentolineal," +
					        "montodescuentolfinal," +
					        "codigodocumento," +
					        "numerocontrol," +
					        "fechadocumento," +
					        "fechaaplicacion," +
					        "planillaimportacion," +
					        "expedienteimportacion," +
					        "descripcion01," +
					        "descripcion02," +
					        "descripcion03," +
					        "estado," +
				            "tipocompras, " +
				            "codigocontable, " +
				            "codigoislr, " +
				            "montodeducibleislr, " +
				            "porcentajeislr, " +
				            "montoislr, " +
				            "documentoafectado, " +
				            "libro, " +
				            "montocancelado, " +
				            "montopendiente, " +
				            "codigoretiva, " +
				            "fecharetiva, " +
				            "codigoretalcaldia, " +
				            "fecharetalcaldia," +
				            "codigodefault " +
						    ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				

				
				pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, 0);
				pstmt.setString(2, tbl.getCodigoproveedor());
				pstmt.setString(3, tbl.getDescripproveedor());
				pstmt.setString(4, tbl.getCodigodeposito());
				pstmt.setTimestamp(5, utilDate.DateToTimestamp(tbl.getFechacompra()));
				pstmt.setTimestamp(6, utilDate.DateToTimestamp(utilDate.Fecha()));
				pstmt.setString(7, tbl.getTipodocumento());
				pstmt.setBigDecimal(8, tbl.getMontototal());
				pstmt.setBigDecimal(9, tbl.getMontoexento());
				pstmt.setBigDecimal(10, tbl.getMontodeducible());
				pstmt.setBigDecimal(11, tbl.getMontoneto());
				pstmt.setBigDecimal(12, tbl.getMontoimpuesto());
				pstmt.setBigDecimal(13, tbl.getMontodescuentolineal());
				pstmt.setBigDecimal(14, tbl.getMontodescuentolfinal());
				pstmt.setString(15, tbl.getCodigodocumento());
				pstmt.setString(16, tbl.getNumerocontrol());
				pstmt.setTimestamp(17, utilDate.DateToTimestamp(tbl.getFechadocumento()));
				pstmt.setTimestamp(18, utilDate.DateToTimestamp(tbl.getFechaaplicacion()));
				pstmt.setString(19, tbl.getPlanillaimportacion());
				pstmt.setString(20, tbl.getExpedienteimportacion());
				pstmt.setString(21, tbl.getDescripcion01());
				pstmt.setString(22, tbl.getDescripcion02());
				pstmt.setString(23, tbl.getDescripcion03());
				pstmt.setString(24, tbl.getEstado());
				pstmt.setString(25, tbl.getTipocompras());

				pstmt.setString(26, tbl.getCodigocontable());
				pstmt.setString(27, tbl.getCodigoislr());
				pstmt.setBigDecimal(28, tbl.getMontodeducibleislr());
				pstmt.setBigDecimal(29, tbl.getPorcentajeislr());
				pstmt.setBigDecimal(30, tbl.getMontoislr());
				pstmt.setString(31, tbl.getDocumentoafectado());
				
				pstmt.setString(32, tbl.getLibro());   	
				pstmt.setBigDecimal(33,tbl.getMontocancelado());
				pstmt.setBigDecimal(34,tbl.getMontopendiente());
				pstmt.setString(35, tbl.getCodigoretiva());
				pstmt.setTimestamp(36, utilDate.DateToTimestamp(tbl.getFecharetiva()));
				pstmt.setString(37, tbl.getCodigoretalcaldia());
				pstmt.setTimestamp(38, utilDate.DateToTimestamp(tbl.getFecharetalcaldia())); 				

				i = pstmt.executeUpdate();
				int AutoCodigo = ByosSql.ObtenerAutonumerico(pstmt);
				guardarComprasDescuentos(tbl, con, AutoCodigo);
				guardarComprasImpuestos(tbl, con, AutoCodigo);
				GuardarComprasItem(tbl, con, AutoCodigo);

				con.commit();
				return utilString.SQL_INSERTADO;
			} else {
				String query = "update tblCompras set " +
				        "codigoproveedor=?," +
				        "descripproveedor=?," +
				        "codigodeposito=?," +
				        "fechacompra=?," +
				        "fechacomprareal=?," +
				        "tipodocumento=?," +
				        "montototal=?," +
				        "montoexento=?," +
				        "montodeducible=?," +
				        "montoneto=?," +
				        "montoimpuesto=?," +
				        "montodescuentolineal=?," +
				        "montodescuentolfinal=?," +
				        "codigodocumento=?," +
				        "numerocontrol=?," +
				        "fechadocumento=?," +
				        "fechaaplicacion=?," +
				        "planillaimportacion=?," +
				        "expedienteimportacion=?," +
				        "descripcion01=?," +
				        "descripcion02=?," +
				        "descripcion03=?," +
				        "estado=?," +
			            "tipocompras=?, " +
			            "codigocontable=?, " +
			            "codigoislr=?, " +
			            "montodeducibleislr=?, " +
			            "porcentajeislr=?, " +
			            "montoislr=?, " +
			            "documentoafectado=?, " +
			            "libro=?, " +
			            "montocancelado=?, " +
			            "montopendiente=?, " +
			            "codigoretiva=?, " +
			            "fecharetiva=?, " +
			            "codigoretalcaldia=?, " +
			            "fecharetalcaldia=?," +	
			            "codigodefault=? " +
					    "where codigocompra=" + tbl.getCodigocompra(); 
				
				//System.out.println(query);
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, tbl.getCodigoproveedor());
				pstmt.setString(2, tbl.getDescripproveedor());
				pstmt.setString(3, tbl.getCodigodeposito());
				pstmt.setTimestamp(4, utilDate.DateToTimestamp(tbl.getFechacompra()));
				pstmt.setTimestamp(5, utilDate.DateToTimestamp(utilDate.Fecha()));
				pstmt.setString(6, tbl.getTipodocumento());
				pstmt.setBigDecimal(7, tbl.getMontototal());
				pstmt.setBigDecimal(8, tbl.getMontoexento());
				pstmt.setBigDecimal(9, tbl.getMontodeducible());
				pstmt.setBigDecimal(10, tbl.getMontoneto());
				pstmt.setBigDecimal(11, tbl.getMontoimpuesto());
				pstmt.setBigDecimal(12, tbl.getMontodescuentolineal());
				pstmt.setBigDecimal(13, tbl.getMontodescuentolfinal());
				pstmt.setString(14, tbl.getCodigodocumento());
				pstmt.setString(15, tbl.getNumerocontrol());
				pstmt.setTimestamp(16, utilDate.DateToTimestamp(tbl.getFechadocumento()));
				pstmt.setTimestamp(17, utilDate.DateToTimestamp(tbl.getFechaaplicacion()));
				pstmt.setString(18, tbl.getPlanillaimportacion());
				pstmt.setString(19, tbl.getExpedienteimportacion());
				pstmt.setString(20, tbl.getDescripcion01());
				pstmt.setString(21, tbl.getDescripcion02());
				pstmt.setString(22, tbl.getDescripcion03());
				pstmt.setString(23, tbl.getEstado());
				pstmt.setString(24, tbl.getTipocompras());

				pstmt.setString(25, tbl.getCodigocontable());
				pstmt.setString(26, tbl.getCodigoislr());
				pstmt.setBigDecimal(27, tbl.getMontodeducibleislr());
				pstmt.setBigDecimal(28, tbl.getPorcentajeislr());
				pstmt.setBigDecimal(29, tbl.getMontoislr());
				pstmt.setString(30, tbl.getDocumentoafectado());

				pstmt.setString(31, tbl.getLibro());   	
				pstmt.setBigDecimal(32,tbl.getMontocancelado());
				pstmt.setBigDecimal(33,tbl.getMontopendiente());
				pstmt.setString(34, tbl.getCodigoretiva());
				pstmt.setTimestamp(35, utilDate.DateToTimestamp(tbl.getFecharetiva()));
				pstmt.setString(36, tbl.getCodigoretalcaldia());
				pstmt.setTimestamp(37, utilDate.DateToTimestamp(tbl.getFecharetalcaldia()));
				
				pstmt.setString(38, tbl.getCodigodefault());

				guardarComprasDescuentos(tbl, con, tbl.getCodigocompra());
				guardarComprasImpuestos(tbl, con, tbl.getCodigocompra());
				GuardarComprasItem(tbl, con, tbl.getCodigocompra());
				
				i = pstmt.executeUpdate();
				con.commit();
				return utilString.SQL_MODIFICADO;
			}
	   }catch (SQLException e) {
		   e.printStackTrace();
	   }catch (Exception e1) {
		   e1.printStackTrace();
	   }		
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,null);  
	   return utilString.SQL_ERROR;  
	}
	
    public boolean guardarComprasDescuentos(tblCompras tbl, Connection con, int AutoCodigo) throws Exception {
        new tblComprasDescuentos().eliminar(tbl.getCodigocompra(), con);

        if(tbl.TblComprasDescuentos!=null && tbl.TblComprasDescuentos.length>0){
           for(int f=0;f<tbl.TblComprasDescuentos.length;f++){
        	   if(ByosValidar.Nulo(tbl.TblComprasDescuentos[f].getMontodescuento())>0 && ByosValidar.Nulo(tbl.TblComprasDescuentos[f].getPorcentaje())>0){
        		   tbl.TblComprasDescuentos[f].setCodigoproveedor(tbl.getCodigoproveedor());
        		   tbl.TblComprasDescuentos[f].setFecha(tbl.getFechacompra());
        		   tbl.TblComprasDescuentos[f].setCodigocompra(AutoCodigo);
        		   tbl.TblComprasDescuentos[f].guardar(tbl.TblComprasDescuentos[f], con);
        	   }
           } 
        }
        return true;
    }	
    
    public boolean guardarComprasImpuestos(tblCompras tbl, Connection con, int AutoCodigo) throws Exception {
        new tblComprasImpuestos().eliminar(tbl.getCodigocompra(), con);

        if(tbl.TblComprasImpuestos!=null && tbl.TblComprasImpuestos.length>0){
           for(int f=0;f<TblComprasImpuestos.length;f++){
        	   if(ByosValidar.Nulo(tbl.TblComprasImpuestos[f].getMontodeducible())>0 && ByosValidar.Nulo(tbl.TblComprasImpuestos[f].getMontoimpuesto())>0){        		  
        		  tbl.TblComprasImpuestos[f].setCodigocompra(AutoCodigo);        		  
        		  tbl.TblComprasImpuestos[f].setCodigoproveedor(tbl.getCodigoproveedor());
        		  tbl.TblComprasImpuestos[f].setFechacompra(tbl.getFechacompra());
        		  tbl.TblComprasImpuestos[f].guardar(tbl.TblComprasImpuestos[f], con);
        	   }
           } 		
        }
        return true;
    }    
	
	
    public void GuardarComprasItem(tblCompras tbl, Connection con, int AutoCodigo) throws Exception {
       new tblComprasItem().eliminar(getCodigocompra(), con);
       new tblComprasItemDescuentos().eliminar(tbl.getCodigocompra(), con);
	   for(int f=0;f<tbl.TblComprasItem.length;f++){
		   tblKardexDetallado TblKardex = new tblKardexDetallado(); 
		   tblProducto TblProducto = new tblProducto();
		   tblProductoDeposito TblDepositoDestino = new tblProductoDeposito();
		   
		   TblProducto.buscarCodigo(tbl.TblComprasItem[f].getCodigoproducto(),con);
		   TblDepositoDestino.buscarCodigo(tbl.getCodigodeposito(), tbl.TblComprasItem[f].getCodigoproducto(),con);
		   
		   tbl.TblComprasItem[f].setCodigocompra(AutoCodigo);
		   tbl.TblComprasItem[f].setItem(f);
		   tbl.TblComprasItem[f].setCodigoitem(f);
		   tbl.TblComprasItem[f].setFechacompra(tbl.getFechacompra());
		   new tblComprasItem().guardar(tbl.TblComprasItem[f], con);    
	   }    	
	
    }
    
    public void GuardarKardex(tblCompras tbl, Connection con, int AutoCodigo) throws Exception {
        new tblComprasItem().eliminar(getCodigocompra(), con);
        
 	   for(int f=0;f<tbl.TblComprasItem.length;f++){
 		   tblKardexDetallado TblKardex = new tblKardexDetallado(); 
 		   tblProducto TblProducto = new tblProducto();
 		   tblProductoDeposito TblDepositoDestino = new tblProductoDeposito();
 		   
 		   TblProducto.buscarCodigo(tbl.TblComprasItem[f].getCodigoproducto(),con);
 		   TblDepositoDestino.buscarCodigo(tbl.getCodigodeposito(), tbl.TblComprasItem[f].getCodigoproducto(),con);
 		   
 		   tbl.TblComprasItem[f].setCodigocompra(AutoCodigo);
 		   tbl.TblComprasItem[f].setItem(f);
 		   tbl.TblComprasItem[f].setCodigoitem(f);
 		   tbl.TblComprasItem[f].setFechacompra(tbl.getFechacompra());
 	       
 	       TblKardex.setCodigooperecion(String.valueOf(AutoCodigo));
 	       TblKardex.setCodigoproducto(tbl.TblComprasItem[f].getCodigoproducto());
 	       TblKardex.setCodigomedida(tbl.TblComprasItem[f].getCodigomedida());
 	       TblKardex.setCosto(tbl.TblComprasItem[f].getCosto());
 	       TblKardex.setDescripcion(TblProducto.getDescripcioncorta());
 	       TblKardex.setTipooperacion(utilString.OPERACION_INVENTARIO_COMPRA);
 	       
 	       double cantidad = tbl.TblComprasItem[f].getCantidad().doubleValue()*tbl.TblComprasItem[f].getEquivalencia().doubleValue();	       
 	       TblKardex.setCodigodeposito(tbl.getCodigodeposito());
 	       TblKardex.setMovimiento(BigDecimal.valueOf(cantidad));
 	       TblKardex.setExistencia(TblProducto.getExistencia());
 	       TblKardex.setExistenciadeposito(BigDecimal.valueOf(TblDepositoDestino.getExistencia().doubleValue()+cantidad)); 
 	       TblKardex.Movimiento(TblKardex, con);	       

 	   }    	
 	
     }
    
    public boolean buscarCodigo(Integer Codigo) throws Exception{
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  if(Codigo!=null){
			String query = "Select " +
			        "codigocompra," +
			        "codigoproveedor," +
			        "descripproveedor," +
			        "codigodeposito," +
			        "fechacompra," +
			        "fechacomprareal," +
			        "tipodocumento," +
			        "montototal," +
			        "montoexento," +
			        "montodeducible," +
			        "montoneto," +
			        "montoimpuesto," +
			        "montodescuentolineal," +
			        "montodescuentolfinal," +
			        "codigodocumento," +
			        "numerocontrol," +
			        "fechadocumento," +
			        "fechaaplicacion," +
			        "planillaimportacion," +
			        "expedienteimportacion," +
			        "descripcion01," +
			        "descripcion02," +
			        "descripcion03," +
			        "estado," +
		            "tipocompras, " +
		            "codigocontable, " +
		            "codigoislr, " +
		            "montodeducibleislr, " +
		            "porcentajeislr, " +
		            "montoislr, " +
		            "documentoafectado, " +
		            "libro, " +
		            "montocancelado, " +
		            "montopendiente, " +
		            "codigoretiva, " +
		            "fecharetiva, " +
		            "codigoretalcaldia, " +
		            "fecharetalcaldia," +
		            "codigodefault " + 
			   		"from tblCompras " +		            
	        "Where codigocompra=" + Codigo + " " +
	        "order by Codigocompra";		
	     Connection con = Conexion.getNuevaConexion();
	     pstmt = con.prepareStatement(query);
	     //pstmt.setInt(1,Codigo);
	     rs = pstmt.executeQuery();
	     int size=0;
	
	     if(rs!=null){
	        if(rs.last()==true){
  	   	       size = rs.getRow();
			   setCodigocompra(rs.getInt("Codigocompra"));    	
			   setCodigoproveedor(rs.getString("Codigoproveedor"));    	   	
			   setCodigodeposito(rs.getString("Codigodeposito"));    	
			   setFechacompra(rs.getDate("Fechacompra"));    
			   setFechacomprareal(rs.getDate("Fechacomprareal"));    	
			   setTipodocumento(rs.getString("Tipodocumento"));    	
			   setMontototal(rs.getBigDecimal("Montototal"));    	
			   setMontoexento(rs.getBigDecimal("Montoexento"));    	
			   setMontodeducible(rs.getBigDecimal("Montodeducible"));    	
			   setMontoneto(rs.getBigDecimal("Montoneto"));    	
			   setMontoimpuesto(rs.getBigDecimal("Montoimpuesto"));    	
			   setMontodescuentolineal(rs.getBigDecimal("Montodescuentolineal"));    	
			   setMontodescuentolfinal(rs.getBigDecimal("Montodescuentolfinal"));    	
			   setCodigodocumento(rs.getString("Codigodocumento"));    	
			   setNumerocontrol(rs.getString("Numerocontrol"));    	
			   setFechadocumento(rs.getDate("Fechadocumento"));    	
			   setFechaaplicacion(rs.getDate("Fechaaplicacion"));    	
			   setPlanillaimportacion(rs.getString("Planillaimportacion"));    	
			   setExpedienteimportacion(rs.getString("Expedienteimportacion"));    	
			   setDescripcion01(rs.getString("Descripcion01"));    	
			   setDescripcion02(rs.getString("Descripcion02"));    	
			   setDescripcion03(rs.getString("Descripcion03"));    	
			   setEstado(rs.getString("Estado"));	
			   setTipocompras(rs.getString("Tipocompras")); 
			      
			   setCodigocontable(rs.getString("Codigocontable"));
			   setCodigoislr(rs.getString("Codigoislr"));
			   setMontodeducibleislr(rs.getBigDecimal("Montodeducibleislr"));
			   setPorcentajeislr(rs.getBigDecimal("Porcentajeislr"));
			   setMontoislr(rs.getBigDecimal("Montoislr"));
			   setDocumentoafectado(rs.getString("Documentoafectado"));
			   
			   setLibro(rs.getString("Libro"));   	
			   setMontocancelado(rs.getBigDecimal("Montocancelado"));
			   setMontopendiente(rs.getBigDecimal("Montopendiente"));
			   setCodigoretiva(rs.getString("Codigoretiva"));
			   setFecharetiva(rs.getDate("Fecharetiva"));
			   setCodigoretalcaldia(rs.getString("Codigoretalcaldia"));
			   setFecharetalcaldia(rs.getDate("Fecharetalcaldia")); 			   
			   
			   setCodigodefault(rs.getString("Codigodefault"));
			   
			   TblDeposito.buscarCodigo(getCodigodeposito());
			   TblProveedor.buscarCodigo(getCodigoproveedor());
			   TblMedidasDefault.buscarCodigo(getCodigodefault());
			   
			   TblComprasItem = new tblComprasItem().Buscar(getCodigocompra());
			   TblComprasDescuentos = new tblComprasDescuentos().Buscar(getCodigocompra());
			   TblComprasImpuestos = new tblComprasImpuestos().Buscar(getCodigocompra());
               ByosSql.CloseAll(con,pstmt,rs);
		       return true; 
            }	
         }
         limpiar();
         ByosSql.CloseAll(con,pstmt,rs);
         
         
	     return false;
	  }
	  return false;
    }
    
	
}
