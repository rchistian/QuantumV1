package com.modulo.producto;



import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.codigobarra.tblCodigoBarra;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilString;
import com.modulo.departamento.tblDepartamento;
import com.modulo.deposito.tblDeposito;
import com.modulo.gruposproducto.tblGruposProducto;
import com.modulo.marca.tblMarca;
import com.modulo.precioproducto.tblPrecioProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.modulo.productoimpuesto.tblProductoImpuesto;
import com.modulo.productomedida.tblProductoMedida;
import com.modulo.productomedidadefault.tblProductoMedidaDefault;
import com.modulo.subgruposproducto.tblSubGruposProducto;
import com.modulo.sustanciascontrolada.tblSustanciasControladas;
import com.modulo.unidadmedida.tblUnidadMedida;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.ByosValidar;

public class tblProducto implements Serializable {
	private static final long serialVersionUID = 1L;
        
    public tblSustanciasControladas TblSustanciasControlada = new tblSustanciasControladas();
   
    public tblUnidadMedida TblUnidadMedida = new tblUnidadMedida();
    
    public tblGruposProducto TblGruposProducto = new tblGruposProducto();
    
    public tblMarca TblMarca = new tblMarca();
    
    public tblDepartamento TblDepartamento = new tblDepartamento();
    
    public tblDeposito TblDeposito = new tblDeposito();
    
    public tblSubGruposProducto TblSubGruposProducto = new tblSubGruposProducto();
    
    public tblProductoMedida[] TblProductoMedidas;
    
    public tblProductoMedidaDefault[] TblProductoMedidasDefault;
    
    public tblCodigoBarra[] TblCodigoBarras;
    
    public tblPrecioProducto[] TblPrecioProductos;
    
    public tblProductoImpuesto[] TblProductoImpuestos;
    
    public tblProductoDeposito[] TblProductoDepositos;
    
    
	private String codigocontrol;

	private String codigodepartamento;
        
	private String codigodeposito;
            
	private String codigogrupo;
        
	private String codigomarca;

	private String codigomedida;  

	private String codigoproducto;

	private String codigoproveedor;
        
	private String codigosubgrupo;

	private String contraindicaciones;

	private BigDecimal costo;

	private BigDecimal costoanterior;

	private BigDecimal costopromedio;

	private String descripcioncorta;

	private String descripcionlarga;

	private String detallerequerido;

	private String estado;

	private BigDecimal existencia;

	private BigDecimal existenciamaxima;

	private BigDecimal existenciaminima;

	private BigDecimal gradoalcohol;

	private int id;

	private BigDecimal incrementotemporada;

	private String indicaciones;

	private String productocompuesto;

	private String regulado;

	private String temporadamaximoconsumo;

	private String ubicacion;

	private BigDecimal utilidadmaxima;

	private BigDecimal utilidadminima;
	
	//Estado y seguridad de registro
	
	private String transtipo;
	
	private String transusuario;
	
	private String transfecha;
	
	private String aprobusuario;
	
	private Date aprobfecha;
	
	private String transnota;
	
	private String estatus;	
	
	public String getTranstipo() {
		return transtipo;
	}

	public void setTranstipo(String transtipo) {
		this.transtipo = transtipo;
	}

	public String getTransusuario() {
		return transusuario;
	}

	public void setTransusuario(String transusuario) {
		this.transusuario = transusuario;
	}

	public String getTransfecha() {
		return transfecha;
	}

	public void setTransfecha(String transfecha) {
		this.transfecha = transfecha;
	}

	public String getAprobusuario() {
		return aprobusuario;
	}

	public void setAprobusuario(String aprobusuario) {
		this.aprobusuario = aprobusuario;
	}

	public Date getAprobfecha() {
		return aprobfecha;
	}

	public void setAprobfecha(Date aprobfecha) {
		this.aprobfecha = aprobfecha;
	}

	public String getTransnota() {
		return transnota;
	}

	public void setTransnota(String transnota) {
		this.transnota = transnota;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}	
        
    public tblProducto(){
        limpiar();
    }

	public String getCodigocontrol() {
		return this.codigocontrol;
	}

	public void setCodigocontrol(String codigocontrol) {
		this.codigocontrol = codigocontrol;
	}

	public String getCodigodepartamento() {
		return this.codigodepartamento;
	}

	public void setCodigodepartamento(String codigodepartamento) {
		this.codigodepartamento = codigodepartamento;
	}

	public String getCodigodeposito() {
		return this.codigodeposito;
	}

	public void setCodigodeposito(String codigodeposito) {
		this.codigodeposito = codigodeposito;
	}

	public String getCodigogrupo() {
		return this.codigogrupo;
	}

	public void setCodigogrupo(String codigogrupo) {
		this.codigogrupo = codigogrupo;
	}

	public String getCodigomarca() {
		return this.codigomarca;
	}

	public void setCodigomarca(String codigomarca) {
		this.codigomarca = codigomarca;
	}

	public String getCodigomedida() {
		return this.codigomedida;
	}

	public void setCodigomedida(String codigomedida) {
		this.codigomedida = codigomedida;
	}

	public String getCodigoproducto() {
		return this.codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public String getCodigoproveedor() {
		return this.codigoproveedor;
	}

	public void setCodigoproveedor(String codigoproveedor) {
		this.codigoproveedor = codigoproveedor;
	}

	public String getCodigosubgrupo() {
		return this.codigosubgrupo;
	}

	public void setCodigosubgrupo(String codigosubgrupo) {
		this.codigosubgrupo = codigosubgrupo;
	}

	public String getContraindicaciones() {
		return this.contraindicaciones;
	}

	public void setContraindicaciones(String contraindicaciones) {
		this.contraindicaciones = contraindicaciones;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getCostoanterior() {
		return this.costoanterior;
	}

	public void setCostoanterior(BigDecimal costoanterior) {
		this.costoanterior = costoanterior;
	}

	public BigDecimal getCostopromedio() {
		return this.costopromedio;
	}

	public void setCostopromedio(BigDecimal costopromedio) {
		this.costopromedio = costopromedio;
	}

	public String getDescripcioncorta() {
		return this.descripcioncorta;
	}

	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}

	public String getDescripcionlarga() {
		return this.descripcionlarga;
	}

	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}

	public String getDetallerequerido() {
		return this.detallerequerido;
	}

	public void setDetallerequerido(String detallerequerido) {
		this.detallerequerido = detallerequerido;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getExistencia() {
		return this.existencia;
	}

	public void setExistencia(BigDecimal existencia) {
		this.existencia = existencia;
	}

	public BigDecimal getExistenciamaxima() {
		return this.existenciamaxima;
	}

	public void setExistenciamaxima(BigDecimal existenciamaxima) {
		this.existenciamaxima = existenciamaxima;
	}

	public BigDecimal getExistenciaminima() {
		return this.existenciaminima;
	}

	public void setExistenciaminima(BigDecimal existenciaminima) {
		this.existenciaminima = existenciaminima;
	}

	public BigDecimal getGradoalcohol() {
		return this.gradoalcohol;
	}

	public void setGradoalcohol(BigDecimal gradoalcohol) {
		this.gradoalcohol = gradoalcohol;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getIncrementotemporada() {
		return this.incrementotemporada;
	}

	public void setIncrementotemporada(BigDecimal incrementotemporada) {
		this.incrementotemporada = incrementotemporada;
	}

	public String getIndicaciones() {
		return this.indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	public String getProductocompuesto() {
		return this.productocompuesto;
	}

	public void setProductocompuesto(String productocompuesto) {
		this.productocompuesto = productocompuesto;
	}

	public String getRegulado() {
		return this.regulado;
	}

	public void setRegulado(String regulado) {
		this.regulado = regulado;
	}

	public String getTemporadamaximoconsumo() {
		return this.temporadamaximoconsumo;
	}

	public void setTemporadamaximoconsumo(String temporadamaximoconsumo) {
		this.temporadamaximoconsumo = temporadamaximoconsumo;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public BigDecimal getUtilidadmaxima() {
		return this.utilidadmaxima;
	}

	public void setUtilidadmaxima(BigDecimal utilidadmaxima) {
		this.utilidadmaxima = utilidadmaxima;
	}

	public BigDecimal getUtilidadminima() {
		return this.utilidadminima;
	}

	public void setUtilidadminima(BigDecimal utilidadminima) {
		this.utilidadminima = utilidadminima;
	}
        
    public tblProductoMedida[] getProductoMedidas(){
        return this.TblProductoMedidas;
    }
        
    public void setProductoMedidas(tblProductoMedida[] TblProductoMedidas){
        this.TblProductoMedidas = TblProductoMedidas;       
    }
    
    public tblProductoMedidaDefault[] getProductoMedidasDefault(){
        return this.TblProductoMedidasDefault;
    }
        
    public void setProductoMedidasDefault(tblProductoMedidaDefault[] TblProductoMedidasDefault){
        this.TblProductoMedidasDefault = TblProductoMedidasDefault;       
    }
    
    public tblCodigoBarra[] getCodigoBarras(){
        return this.TblCodigoBarras;
    }
        
    public void setCodigoBarras(tblCodigoBarra[] TblCodigoBarras){
        this.TblCodigoBarras = TblCodigoBarras;       
    }        

    public tblPrecioProducto[] getPrecioProductos(){
        return this.TblPrecioProductos;
    }
        
    public void setPrecioProductos(tblPrecioProducto[] TblPrecioProductos){
        this.TblPrecioProductos = TblPrecioProductos;       
    }
    
    public tblProductoImpuesto[] getProductoImpuestos(){
        return this.TblProductoImpuestos;
    }
        
    public void setProductoImpuestos(tblProductoImpuesto[] TblProductoImpuestos){
        this.TblProductoImpuestos = TblProductoImpuestos;       
    }   
    
    public tblProductoDeposito[] getProductoDepositos(){
        return this.TblProductoDepositos;
    }
        
    public void setProductoDepositos(tblProductoDeposito[] TblProductoDepositos){
        this.TblProductoDepositos = TblProductoDepositos;       
    }      
    
    public boolean buscarCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	    
	   String query = "Select id, codigoproducto, descripcioncorta, descripcionlarga, codigogrupo, codigosubgrupo, codigodeposito, codigodepartamento, codigomarca, codigomedida, utilidadminima, utilidadmaxima, temporadamaximoconsumo, incrementotemporada, existencia, existenciamaxima, existenciaminima, gradoalcohol, regulado, detallerequerido, indicaciones, contraindicaciones, ubicacion, codigoproveedor, codigocontrol, productocompuesto, estado, costo, costoanterior, costopromedio from tblproductos where codigoproducto=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
 	   if(rs != null){
	      if(rs.last()==true){
		    size = rs.getRow();
		    setId(rs.getInt("id"));
		    setCodigoproducto(rs.getString("codigoproducto"));
		    setDescripcioncorta(rs.getString("descripcioncorta"));
		    setDescripcionlarga(rs.getString("descripcionlarga"));
		    setCodigogrupo(rs.getString("codigogrupo"));
		    setCodigosubgrupo(rs.getString("codigosubgrupo"));
		    setCodigodeposito(rs.getString("codigodeposito")); 
		    setCodigodepartamento(rs.getString("codigodepartamento"));  
	        setCodigomarca(rs.getString("codigomarca")); 
		    setCodigomedida(rs.getString("codigomedida"));  
		    setUtilidadminima(rs.getBigDecimal("utilidadminima")); 
            setUtilidadmaxima(rs.getBigDecimal("utilidadmaxima"));
            setTemporadamaximoconsumo(rs.getString("temporadamaximoconsumo"));
            setIncrementotemporada(rs.getBigDecimal("incrementotemporada"));
            setExistencia(rs.getBigDecimal("existencia"));
            setExistenciamaxima(rs.getBigDecimal("existenciamaxima"));
            setExistenciaminima(rs.getBigDecimal("existenciaminima"));
            setGradoalcohol(rs.getBigDecimal("gradoalcohol"));
            setRegulado(rs.getString("regulado"));  
            setDetallerequerido(rs.getString("detallerequerido"));  
            setIndicaciones(rs.getString("indicaciones"));  
            setContraindicaciones(rs.getString("contraindicaciones"));  
            setUbicacion(rs.getString("ubicacion"));  
            setCodigoproveedor(rs.getString("codigoproveedor"));  
            setCodigocontrol(rs.getString("codigocontrol"));  
            setProductocompuesto(rs.getString("productocompuesto"));
            setEstado(rs.getString("estado"));
            setCosto(rs.getBigDecimal("costo"));
            setCostoanterior(rs.getBigDecimal("costoanterior"));
            setCostopromedio(rs.getBigDecimal("costopromedio"));
            TblDeposito.buscarCodigo(getCodigodeposito());
            TblDepartamento.buscarCodigo(getCodigodepartamento());
            TblMarca.buscarCodigo(getCodigomarca());
            TblUnidadMedida.buscarCodigo(getCodigomedida());
            TblGruposProducto.buscarCodigo(getCodigogrupo());
            TblSubGruposProducto.buscarCodigo(getCodigogrupo(), getCodigosubgrupo());
            TblSustanciasControlada.buscarCodigo(getCodigocontrol());
            TblProductoMedidas = new tblProductoMedida().Buscar(getCodigoproducto());
            TblCodigoBarras = new tblCodigoBarra().Buscar(getCodigoproducto());
            TblPrecioProductos = new tblPrecioProducto().Buscar(getCodigoproducto());
            TblProductoImpuestos = new tblProductoImpuesto().buscarCodigo(getCodigoproducto());
            TblProductoMedidasDefault = new tblProductoMedidaDefault().Buscar(getCodigoproducto());
            TblProductoDepositos = new tblProductoDeposito().Buscar(getCodigoproducto());
            ByosSql.CloseAll(con,pstmt,rs);
	        return true;  
	      }	   
	   }
 	   com.byos.sys.util.ByosSql.CloseAll(con, pstmt, rs);
 	   ByosSql.CloseAll(con,pstmt,rs);
	   return false;
    }
   
    
    public boolean buscarCodigo(String Codigo, Connection con) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	    
	   String query = "Select id, codigoproducto, descripcioncorta, descripcionlarga, codigogrupo, codigosubgrupo, codigodeposito, codigodepartamento, codigomarca, codigomedida, utilidadminima, utilidadmaxima, temporadamaximoconsumo, incrementotemporada, existencia, existenciamaxima, existenciaminima, gradoalcohol, regulado, detallerequerido, indicaciones, contraindicaciones, ubicacion, codigoproveedor, codigocontrol, productocompuesto, estado, costo, costoanterior, costopromedio from tblproductos where codigoproducto=?";
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
 	   if(rs != null){
	      if(rs.last()==true){
		    size = rs.getRow();
		    setId(rs.getInt("id"));
		    setCodigoproducto(rs.getString("codigoproducto"));
		    setDescripcioncorta(rs.getString("descripcioncorta"));
		    setDescripcionlarga(rs.getString("descripcionlarga"));
		    setCodigogrupo(rs.getString("codigogrupo"));
		    setCodigosubgrupo(rs.getString("codigosubgrupo"));
		    setCodigodeposito(rs.getString("codigodeposito")); 
		    setCodigodepartamento(rs.getString("codigodepartamento"));  
	        setCodigomarca(rs.getString("codigomarca")); 
		    setCodigomedida(rs.getString("codigomedida"));  
		    setUtilidadminima(rs.getBigDecimal("utilidadminima")); 
            setUtilidadmaxima(rs.getBigDecimal("utilidadmaxima"));
            setTemporadamaximoconsumo(rs.getString("temporadamaximoconsumo"));
            setIncrementotemporada(rs.getBigDecimal("incrementotemporada"));
            setExistencia(rs.getBigDecimal("existencia"));
            setExistenciamaxima(rs.getBigDecimal("existenciamaxima"));
            setExistenciaminima(rs.getBigDecimal("existenciaminima"));
            setGradoalcohol(rs.getBigDecimal("gradoalcohol"));
            setRegulado(rs.getString("regulado"));  
            setDetallerequerido(rs.getString("detallerequerido"));  
            setIndicaciones(rs.getString("indicaciones"));  
            setContraindicaciones(rs.getString("contraindicaciones"));  
            setUbicacion(rs.getString("ubicacion"));  
            setCodigoproveedor(rs.getString("codigoproveedor"));  
            setCodigocontrol(rs.getString("codigocontrol"));  
            setProductocompuesto(rs.getString("productocompuesto"));
            setEstado(rs.getString("estado"));
            setCosto(rs.getBigDecimal("costo"));
            setCostoanterior(rs.getBigDecimal("costoanterior"));
            setCostopromedio(rs.getBigDecimal("costopromedio"));
            TblDeposito.buscarCodigo(getCodigodeposito());
            TblDepartamento.buscarCodigo(getCodigodepartamento());
            TblMarca.buscarCodigo(getCodigomarca());
            TblUnidadMedida.buscarCodigo(getCodigomedida());
            TblGruposProducto.buscarCodigo(getCodigogrupo());
            TblSubGruposProducto.buscarCodigo(getCodigogrupo(), getCodigosubgrupo());
            TblSustanciasControlada.buscarCodigo(getCodigocontrol());
            TblProductoMedidas = new tblProductoMedida().Buscar(getCodigoproducto());
            TblCodigoBarras = new tblCodigoBarra().Buscar(getCodigoproducto());
            TblPrecioProductos = new tblPrecioProducto().Buscar(getCodigoproducto());
            TblProductoImpuestos = new tblProductoImpuesto().buscarCodigo(getCodigoproducto());
            TblProductoMedidasDefault = new tblProductoMedidaDefault().Buscar(getCodigoproducto());
            TblProductoDepositos = new tblProductoDeposito().Buscar(getCodigoproducto());
	        return true;  
	      }	   
	   }
	   return false;
    }    
    
    
    public boolean existeCodigo(String Codigo) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	  
	   String query = "select Codigoproducto from tblProductos where Codigoproducto=?";
	
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

    public boolean eliminar(tblProducto tbl) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	
	   //Ingresar nuevo
	   if(existeCodigo(tbl.getCodigoproducto())){
	      String query = "Delete From tblProductos Where Codigoproducto='" + tbl.getCodigoproducto() + "'";
	      Connection con = Conexion.getNuevaConexion();
	      con.setAutoCommit(false);
	      pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);
	      return true;		
	   }
	   return false;
    }

    public String desincorporar(String Codigoproducto){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		  if(existeCodigo(Codigoproducto)){
			  String query = "Update tblProductos set estado=? where Codigoproducto='" + Codigoproducto + "'";
		      con.setAutoCommit(false);
		      pstmt = con.prepareStatement(query);			
		      pstmt.setString(1,"Inactivo");              
			  i = pstmt.executeUpdate();
			  con.commit();
			  setEstado("Inactivo"); 
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_DESINCORPORAR;  
		  }
	   }catch(SQLException e) {
          e.printStackTrace();
	   }catch(Exception e) {
		  e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;	
    }
   
    
    
    public String guardar(tblProducto tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   //Ingresar nuevo
	   try{
		   if(!existeCodigo(tbl.getCodigoproducto())){
			  String query = "Insert into tblProductos(codigoproducto, descripcioncorta, descripcionlarga, codigogrupo, codigosubgrupo, codigodeposito, codigodepartamento, codigomarca, codigomedida, utilidadminima, utilidadmaxima, temporadamaximoconsumo, incrementotemporada, existenciamaxima, existenciaminima, gradoalcohol, regulado, detallerequerido, indicaciones, contraindicaciones, ubicacion, codigoproveedor, codigocontrol, productocompuesto, estado, costo, costoanterior, costopromedio) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			  
		      con.setAutoCommit(false);
		      pstmt = con.prepareStatement(query);
			  pstmt.setString(1,tbl.getCodigoproducto());
			  pstmt.setString(2,tbl.getDescripcioncorta());
			  pstmt.setString(3,tbl.getDescripcionlarga());
			  pstmt.setString(4,tbl.getCodigogrupo());
			  pstmt.setString(5,tbl.getCodigosubgrupo());
			  pstmt.setString(6,tbl.getCodigodeposito());			
			  pstmt.setString(7,tbl.getCodigodepartamento());
			  pstmt.setString(8,tbl.getCodigomarca());
			  pstmt.setString(9,tbl.getCodigomedida());
			  pstmt.setBigDecimal(10,tbl.getUtilidadminima());
			  pstmt.setBigDecimal(11,tbl.getUtilidadmaxima());
		      pstmt.setString(12,tbl.getTemporadamaximoconsumo());
		      pstmt.setBigDecimal(13,tbl.getIncrementotemporada());
		      //pstmt.setBigDecimal(14,tbl.getExistencia());
		      pstmt.setBigDecimal(14,tbl.getExistenciamaxima());
		      pstmt.setBigDecimal(15,tbl.getExistenciaminima());
		      pstmt.setBigDecimal(16,tbl.getGradoalcohol());
		      pstmt.setString(17,tbl.getRegulado());
		      pstmt.setString(18,tbl.getDetallerequerido());
		      pstmt.setString(19,tbl.getIndicaciones());
		      pstmt.setString(20,tbl.getContraindicaciones());
		      pstmt.setString(21,tbl.getUbicacion());
		      pstmt.setString(22,tbl.getCodigoproveedor());
		      pstmt.setString(23,tbl.getCodigocontrol());
		      pstmt.setString(24,tbl.getProductocompuesto());
		      pstmt.setString(25,tbl.getEstado());
		      pstmt.setBigDecimal(26,tbl.getCosto());
		      pstmt.setBigDecimal(27,tbl.getCostoanterior());
		      pstmt.setBigDecimal(28,tbl.getCostopromedio());
		      i = pstmt.executeUpdate();
		      guardarProductoMedida(con);
		      guardarProductoMedidaDefault(con);
		      guardarPrecios(con);
		      guardarImpuesto(con);
		      guardarCodigoBarra(con);

		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   //Modificar
		   else{
			  String query = "Update tblProductos set descripcioncorta=?, descripcionlarga=?, codigogrupo=?, codigosubgrupo=?, codigodeposito=?, codigodepartamento=?, codigomarca=?, codigomedida=?, utilidadminima=?, utilidadmaxima=?, temporadamaximoconsumo=?, incrementotemporada=?, existenciamaxima=?, existenciaminima=?, gradoalcohol=?, regulado=?, detallerequerido=?, indicaciones=?, contraindicaciones=?, ubicacion=?, codigoproveedor=?, codigocontrol=?, productocompuesto=?, estado=?, costo=?, costoanterior=?, costopromedio=? where Codigoproducto='" + tbl.getCodigoproducto() + "'";

		      con.setAutoCommit(false);
		      pstmt = con.prepareStatement(query);	
		      
			  pstmt.setString(1,tbl.getDescripcioncorta());
		      pstmt.setString(2,tbl.getDescripcionlarga());
		      pstmt.setString(3,tbl.getCodigogrupo());
		      pstmt.setString(4,tbl.getCodigosubgrupo());
		      pstmt.setString(5,tbl.getCodigodeposito());
		      pstmt.setString(6,tbl.getCodigodepartamento());
		      pstmt.setString(7,tbl.getCodigomarca());
		      pstmt.setString(8,tbl.getCodigomedida());
			  pstmt.setBigDecimal(9,tbl.getUtilidadminima());
			  pstmt.setBigDecimal(10,tbl.getUtilidadmaxima());
		      pstmt.setString(11,tbl.getTemporadamaximoconsumo());
		      pstmt.setBigDecimal(12,tbl.getIncrementotemporada());
		      //pstmt.setBigDecimal(13,tbl.getExistencia());
		      pstmt.setBigDecimal(13,tbl.getExistenciamaxima());
		      pstmt.setBigDecimal(14,tbl.getExistenciaminima());
		      pstmt.setBigDecimal(15,tbl.getGradoalcohol());
		      pstmt.setString(16,tbl.getRegulado());
		      pstmt.setString(17,tbl.getDetallerequerido());
		      pstmt.setString(18,tbl.getIndicaciones());
		      pstmt.setString(19,tbl.getContraindicaciones());
		      pstmt.setString(20,tbl.getUbicacion());
		      pstmt.setString(21,tbl.getCodigoproveedor());
		      pstmt.setString(22,tbl.getCodigocontrol());
		      pstmt.setString(23,tbl.getProductocompuesto());
		      pstmt.setString(24,tbl.getEstado());
		      pstmt.setBigDecimal(25,tbl.getCosto());
		      pstmt.setBigDecimal(26,tbl.getCostoanterior());
		      pstmt.setBigDecimal(27,tbl.getCostopromedio()); 
			  i = pstmt.executeUpdate();
		      guardarProductoMedida(con);
		      guardarProductoMedidaDefault(con);	         
		      guardarPrecios(con);
		      guardarImpuesto(con);
		      guardarCodigoBarra(con);	
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_MODIFICADO;
		   }
	   }catch (SQLException e) {
		   e.printStackTrace();
	   }catch (Exception e1) {
		   e1.printStackTrace();
	   }		
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);      
	   return utilString.SQL_ERROR;	   
    }
   
    
    public String Ajustar(String Codigoproducto, BigDecimal Existencia, Connection con) throws Exception {
 	   PreparedStatement pstmt = null;
 	   ResultSet rs = null; 
 	   if(existeCodigo(Codigoproducto)){
 		  String query;
 		  //if(TipoOperacion.equals(utilString.OPERACION_INVENTARIO_AJUSTE)){
 		     query = "Update tblProductos set existencia=? where Codigoproducto='" + Codigoproducto + "'";
 		  //}else{
 			 //query = "Update tblProductos set existencia=existencia+? where Codigoproducto='" + Codigoproducto + "'"; 
 		  //}
 		  
 	      con.setAutoCommit(false);
 	      pstmt = con.prepareStatement(query);	
 		  pstmt.setBigDecimal(1,Existencia);
 		  pstmt.executeUpdate();
 	   }
 	   return utilString.SQL_MODIFICADO;
    }
    
    public boolean guardarProductoMedida(Connection con) throws Exception {
       new tblProductoMedida().eliminar(getCodigoproducto(), con);

       if(TblProductoMedidas!=null && TblProductoMedidas.length>0){
          for(int f=0;f<TblProductoMedidas.length;f++){
              TblProductoMedidas[f].guardar(TblProductoMedidas[f], con);
          } 
       }
       return true;
    }
    
    public boolean guardarProductoMedidaDefault(Connection con) throws Exception {
       new tblProductoMedidaDefault().eliminar(getCodigoproducto(), con);
        
       if(TblProductoMedidasDefault!=null && TblProductoMedidasDefault.length>0){
          for(int f=0;f<TblProductoMedidasDefault.length;f++){
              TblProductoMedidasDefault[f].guardar(TblProductoMedidasDefault[f], con);
          }           
       }
       return true;
    }
    
    public boolean guardarCodigoBarra(Connection con) throws Exception {
       if(TblCodigoBarras!=null && TblCodigoBarras.length>0){
          for(int f=0;f<TblCodigoBarras.length;f++){
              if(TblCodigoBarras[f].guardar(TblCodigoBarras[f], con).equals(utilString.SQL_ERROR)){
            	 return false;
              }
          } 
       }
       return true;
    }   

    public void guardarPrecios(Connection con) throws Exception {
       if(TblPrecioProductos!=null && TblPrecioProductos.length>0){
          for(int f=0;f<TblPrecioProductos.length;f++){
              TblPrecioProductos[f].guardar(TblPrecioProductos[f], con);
              
          } 
       }
    }   
  
    public void guardarImpuesto(Connection con) throws Exception {
    	new tblProductoImpuesto().eliminar(getCodigoproducto(), con);
        if(TblProductoImpuestos!=null && TblProductoImpuestos.length>0){
           for(int f=0;f<TblProductoImpuestos.length;f++){
        	   TblProductoImpuestos[f].guardar(TblProductoImpuestos[f], con);
               
           } 
        }
     }
    
    public void limpiar(){
       setId(0);
       setCodigoproducto("");
       setDescripcioncorta("");
       setDescripcionlarga("");
       setCodigogrupo("");
       setCodigosubgrupo("");
       setCodigodeposito("");			
       setCodigodepartamento("");
       setCodigomarca("");
       setCodigomedida("");
       setUtilidadminima(null);
       setUtilidadmaxima(null);
       setTemporadamaximoconsumo("");
       setIncrementotemporada(null);
       setExistencia(null);
       setExistenciamaxima(null);
       setExistenciaminima(null);
       setGradoalcohol(null);
       setRegulado("");
       setDetallerequerido("");
       setIndicaciones("");
       setContraindicaciones("");
       setUbicacion("");
       setCodigoproveedor("");
       setCodigocontrol("");
       setProductocompuesto("");
       setEstado("");
       setCosto(null);
       setCostoanterior(null);
       setCostopromedio(null); 
       TblDeposito.limpiar();
       TblDepartamento.limpiar();
       TblMarca.limpiar();
       TblUnidadMedida.limpiar();
       TblGruposProducto.limpiar();
       TblSubGruposProducto.limpiar();
       TblSustanciasControlada.limpiar();
       TblProductoMedidas = null;
       TblProductoMedidasDefault = null;
       TblCodigoBarras = null;
       TblPrecioProductos = null;
       TblProductoImpuestos = null;
       TblProductoDepositos = null;
    }

    public ArrayList <tblProducto> BuscarBarras(String CodigoBarra) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   ArrayList <tblProducto> tblArray = new ArrayList(); 
	  
	   String query = "Select id, tblproductos.codigoproducto, descripcioncorta, descripcionlarga, codigogrupo, codigosubgrupo, codigodeposito, codigodepartamento, codigomarca, codigomedida, utilidadminima, utilidadmaxima, temporadamaximoconsumo, incrementotemporada, existencia, existenciamaxima, existenciaminima, gradoalcohol, regulado, detallerequerido, indicaciones, contraindicaciones, ubicacion, codigoproveedor, codigocontrol, productocompuesto, estado, costo, costoanterior, costopromedio from tblproductos,tblcodigobarra " + 
                      "Where tblproductos.codigoproducto=tblcodigobarra.codigoproducto and codigobarra='" + CodigoBarra + "' " + 
                      "Order by descripcioncorta";
	  
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    
		    rs.first(); 
			
		    for(int f=0;f<size;f++){
		    	tblProducto Registros = new tblProducto();
                Registros.setCodigoproducto(rs.getString("codigoproducto"));
		        Registros.setDescripcioncorta(rs.getString("descripcioncorta"));
                Registros.setDescripcionlarga(rs.getString("descripcionlarga"));
		        Registros.setCodigogrupo(rs.getString("codigogrupo"));
		        Registros.setCodigosubgrupo(rs.getString("codigosubgrupo"));
		        Registros.setCodigodeposito(rs.getString("codigodeposito")); 
		        Registros.setCodigodepartamento(rs.getString("codigodepartamento"));  
	            Registros.setCodigomarca(rs.getString("codigomarca")); 
		        Registros.setCodigomedida(rs.getString("codigomedida"));  
		        Registros.setUtilidadminima(rs.getBigDecimal("utilidadminima")); 
                Registros.setUtilidadmaxima(rs.getBigDecimal("utilidadmaxima"));
                Registros.setTemporadamaximoconsumo(rs.getString("temporadamaximoconsumo"));
                Registros.setIncrementotemporada(rs.getBigDecimal("incrementotemporada"));
                Registros.setExistencia(rs.getBigDecimal("existencia"));
                Registros.setExistenciamaxima(rs.getBigDecimal("existenciamaxima"));
                Registros.setExistenciaminima(rs.getBigDecimal("existenciaminima"));
                Registros.setGradoalcohol(rs.getBigDecimal("gradoalcohol"));
                Registros.setRegulado(rs.getString("regulado"));  
                Registros.setDetallerequerido(rs.getString("detallerequerido"));  
                Registros.setIndicaciones(rs.getString("indicaciones"));  
                Registros.setContraindicaciones(rs.getString("contraindicaciones"));  
                Registros.setUbicacion(rs.getString("ubicacion"));  
                Registros.setCodigoproveedor(rs.getString("codigoproveedor"));  
                Registros.setCodigocontrol(rs.getString("codigocontrol"));  
                Registros.setProductocompuesto(rs.getString("productocompuesto"));
                Registros.setEstado(rs.getString("estado"));
                Registros.setCosto(rs.getBigDecimal("costo"));
                Registros.setCostoanterior(rs.getBigDecimal("costoanterior"));
                Registros.setCostopromedio(rs.getBigDecimal("costopromedio"));
                Registros.TblDeposito.buscarCodigo(Registros.getCodigodeposito());
                Registros.TblDepartamento.buscarCodigo(Registros.getCodigodepartamento());
                Registros.TblMarca.buscarCodigo(Registros.getCodigomarca());
                Registros.TblUnidadMedida.buscarCodigo(Registros.getCodigomedida());
                Registros.TblGruposProducto.buscarCodigo(Registros.getCodigogrupo());
                Registros.TblSubGruposProducto.buscarCodigo(Registros.getCodigogrupo(), Registros.getCodigosubgrupo());
		        Registros.TblSustanciasControlada.buscarCodigo(Registros.getCodigocontrol());
                Registros.TblProductoMedidas = new tblProductoMedida().Buscar(Registros.getCodigoproducto());
                Registros.TblProductoMedidasDefault = new tblProductoMedidaDefault().Buscar(Registros.getCodigoproducto());
                Registros.TblCodigoBarras = new tblCodigoBarra().Buscar(Registros.getCodigoproducto());
                Registros.TblPrecioProductos = new tblPrecioProducto().Buscar(getCodigoproducto()); 
                Registros.TblProductoImpuestos = new tblProductoImpuesto().buscarCodigo(getCodigoproducto());
                Registros.TblProductoDepositos = new tblProductoDeposito().Buscar(getCodigoproducto());
                
                tblArray.add(Registros);
                rs.next();
		    }
		    ByosSql.CloseAll(con,pstmt,rs);
		    return tblArray;	               
	     } 
	   }
	  return null;       
   }
   
   public ArrayList <tblProducto> Buscar(tblProducto Producto) throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String query = BuscarQuery(Producto);
	  ArrayList <tblProducto> tblArray = new ArrayList(); 
	  Connection con = Conexion.getNuevaConexion();
	  //System.out.println("Seleccion: " + query);
	  pstmt = con.prepareStatement(query);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs!=null){
	     if(rs.last()==true){
		    size = rs.getRow();
		    rs.first(); 
		    for(int f=0;f<size;f++){
		    	tblProducto Registros = new tblProducto();
                Registros.setCodigoproducto(rs.getString("a.codigoproducto"));
		        Registros.setDescripcioncorta(rs.getString("a.descripcioncorta"));
                Registros.setDescripcionlarga(rs.getString("a.descripcionlarga"));
		        Registros.setCodigogrupo(rs.getString("a.codigogrupo"));
		        Registros.setCodigosubgrupo(rs.getString("a.codigosubgrupo"));
		        Registros.setCodigodeposito(rs.getString("a.codigodeposito")); 
		        Registros.setCodigodepartamento(rs.getString("a.codigodepartamento"));  
	            Registros.setCodigomarca(rs.getString("a.codigomarca")); 
		        Registros.setCodigomedida(rs.getString("a.codigomedida"));  
		        Registros.setUtilidadminima(rs.getBigDecimal("a.utilidadminima")); 
                Registros.setUtilidadmaxima(rs.getBigDecimal("a.utilidadmaxima"));
                Registros.setTemporadamaximoconsumo(rs.getString("a.temporadamaximoconsumo"));
                Registros.setIncrementotemporada(rs.getBigDecimal("a.incrementotemporada"));
                Registros.setExistencia(rs.getBigDecimal("a.existencia"));
                Registros.setExistenciamaxima(rs.getBigDecimal("a.existenciamaxima"));
                Registros.setExistenciaminima(rs.getBigDecimal("a.existenciaminima"));
                Registros.setGradoalcohol(rs.getBigDecimal("a.gradoalcohol"));
                Registros.setRegulado(rs.getString("a.regulado"));  
                Registros.setDetallerequerido(rs.getString("a.detallerequerido"));  
                Registros.setIndicaciones(rs.getString("a.indicaciones"));  
                Registros.setContraindicaciones(rs.getString("a.contraindicaciones"));  
                Registros.setUbicacion(rs.getString("a.ubicacion"));  
                Registros.setCodigoproveedor(rs.getString("a.codigoproveedor"));  
                Registros.setCodigocontrol(rs.getString("a.codigocontrol"));  
                Registros.setProductocompuesto(rs.getString("a.productocompuesto"));
                Registros.setEstado(rs.getString("a.estado"));
                Registros.setCosto(rs.getBigDecimal("a.costo"));
                Registros.setCostoanterior(rs.getBigDecimal("a.costoanterior"));
                Registros.setCostopromedio(rs.getBigDecimal("a.costopromedio"));
   		        
                Registros.TblGruposProducto.setId(rs.getInt("b.id"));
                Registros.TblGruposProducto.setCodigogrupo(rs.getString("b.codigogrupo"));
                Registros.TblGruposProducto.setDescripcion(rs.getString("b.descripcion"));
                
                Registros.TblSubGruposProducto.setId(rs.getInt("c.id"));
                Registros.TblSubGruposProducto.setCodigogrupo(rs.getString("c.codigogrupo"));
                Registros.TblSubGruposProducto.setCodigogrupo(rs.getString("c.codigosubgrupo"));
                Registros.TblSubGruposProducto.setDescripcion(rs.getString("c.descripcion"));    
                
                Registros.TblDeposito.setId(rs.getInt("d.id"));
                Registros.TblDeposito.setCodigodeposito(rs.getString("d.codigodeposito"));
                Registros.TblDeposito.setDescripcion(rs.getString("d.descripcion"));
                Registros.TblDeposito.setTipodeposito(rs.getString("d.tipodeposito"));
                Registros.TblDeposito.setResponsable(rs.getString("d.responsable")); 
                
                Registros.TblDepartamento.setId(rs.getInt("e.id"));
                Registros.TblDepartamento.setCodigodepartamento(rs.getString("e.codigodepartamento"));
                Registros.TblDepartamento.setDescripcion(rs.getString("e.descripcion"));
                Registros.TblDepartamento.setResponsable(rs.getString("e.responsable"));    
                
                Registros.TblMarca.setId(rs.getInt("f.id"));
                Registros.TblMarca.setCodigomarca(rs.getString("f.codigomarca"));
                Registros.TblMarca.setDescripcion(rs.getString("f.descripcion"));
                
                Registros.TblUnidadMedida.setId(rs.getInt("g.id"));
                Registros.TblUnidadMedida.setCodigomedida(rs.getString("g.codigomedida"));
                Registros.TblUnidadMedida.setDescripcion(rs.getString("g.descripcion"));
                Registros.TblUnidadMedida.setPesable(rs.getString("g.pesable")); 
                
                Registros.TblSustanciasControlada.setId(rs.getInt("h.id"));
                Registros.TblSustanciasControlada.setCodigocontrol(rs.getString("h.codigocontrol"));
                Registros.TblSustanciasControlada.setDescripcion(rs.getString("h.descripcion"));
                Registros.TblSustanciasControlada.setPrincipioactivo(rs.getString("h.principioactivo"));
                Registros.TblSustanciasControlada.setGradoseguridad(rs.getInt("h.gradoseguridad"));
                Registros.TblSustanciasControlada.setExigirrecipe(rs.getString("h.exigirrecipe"));
                
                //Registros[f].TblDeposito.buscarCodigo(Registros[f].getCodigodeposito());
                //Registros[f].TblDepartamento.buscarCodigo(Registros[f].getCodigodepartamento());
                //Registros[f].TblMarca.buscarCodigo(Registros[f].getCodigomarca());
                //Registros[f].TblUnidadMedida.buscarCodigo(Registros[f].getCodigomedida());
                //Registros[f].TblGruposProducto.buscarCodigo(Registros[f].getCodigogrupo());
                //Registros[f].TblSubGruposProducto.buscarCodigo(Registros[f].getCodigogrupo(), Registros[f].getCodigosubgrupo());
		        //Registros[f].TblSustanciasControlada.buscarCodigo(Registros[f].getCodigocontrol());                   
                //Registros[f].TblProductoMedidas = new tblProductoMedida().Buscar(Registros[f].getCodigoproducto());
                //Registros[f].TblCodigoBarras = new tblCodigoBarra().Buscar(Registros[f].getCodigoproducto());
                //Registros[f].TblPrecioProductos = new tblPrecioProducto().Buscar(getCodigoproducto());
                tblArray.add(Registros);
                rs.next();
		    }
		    ByosSql.CloseAll(con,pstmt,rs);
		    return tblArray;	               
	     } 
	  }
	  return null;  
   }
   
   private String BuscarQuery(tblProducto Producto){	      
	  String query = "Select " +
	  		"a.id, " +
	  		"a.codigoproducto, " +
	  		"a.descripcioncorta, " +
	  		"a.descripcionlarga, " +
	  		"a.codigogrupo, " +
	  		"a.codigosubgrupo, " +
	  		"a.codigodeposito, " +
	  		"a.codigodepartamento, " +
	  		"a.codigomarca, " +
	  		"a.codigomedida, " +
	  		"a.utilidadminima, " +
	  		"a.utilidadmaxima, " +
	  		"a.temporadamaximoconsumo, " +
	  		"a.incrementotemporada, " +
	  		"a.existencia, " +
	  		"a.existenciamaxima, " +
	  		"a.existenciaminima, " +
	  		"a.gradoalcohol, " +
	  		"a.regulado, " +
	  		"a.detallerequerido, " +
	  		"a.indicaciones, " +
	  		"a.contraindicaciones, " +
	  		"a.ubicacion, " +
	  		"a.codigoproveedor, " +
	  		"a.codigocontrol, " +
	  		"a.productocompuesto, " +
	  		"a.estado, " +
	  		"a.costo, " +
	  		"a.costoanterior, " +
	  		"a.costopromedio, " +
	   		"b.id, " +
	   		"b.codigogrupo, " +
	   		"b.descripcion, " +
	   		"c.id, " +
	   		"c.codigogrupo, " +
	   		"c.codigosubgrupo, " +
	   		"c.descripcion, " +
	   		"d.id, " +
	   		"d.codigodeposito, " +
	   		"d.descripcion, " +
	   		"d.tipodeposito, " +
	   		"d.responsable, " +
	  		"e.id, " +
	  		"e.codigodepartamento, " +
	  		"e.descripcion, " +
	  		"e.responsable, " +
	   		"f.id, " +
	   		"f.codigomarca, " +
	   		"f.descripcion," +
	   		"g.id, " +
	   		"g.codigomedida, " +
	   		"g.descripcion, " +
	   		"g.pesable," +
	  		"h.id, " +
	  		"h.codigocontrol, " +
	  		"h.descripcion, " +
	  		"h.principioactivo, " +
	  		"h.gradoseguridad, " +
	  		"h.exigirrecipe " +
	  		"from tblproductos a ";
	  String InSql="";
	  int Estado=0;
      if (Producto.getCodigoproducto()!=null &&!Producto.getCodigoproducto().equals("")){
		  InSql = InSql + " and a.codigoproducto like '" + Producto.getCodigoproducto().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getDescripcioncorta()!=null && !Producto.getDescripcioncorta().equals("")){
		  InSql = InSql + " and a.descripcioncorta like '" + Producto.getDescripcioncorta().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getDescripcionlarga()!=null && !Producto.getDescripcionlarga().equals("")){
		  InSql = InSql + " and a.descripcionlarga like '" + Producto.getDescripcionlarga().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigogrupo()!=null && !Producto.getCodigogrupo().equals("")){
		  InSql = InSql + " and a.codigogrupo like '" + Producto.getCodigogrupo().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigosubgrupo()!=null && !Producto.getCodigosubgrupo().equals("")){
		  InSql = InSql + " and a.codigosubgrupo like '" + Producto.getCodigosubgrupo().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigodeposito()!=null && !Producto.getCodigodeposito().equals("")){
		  InSql = InSql + " and a.codigodeposito like '" + Producto.getCodigodeposito().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigodepartamento()!=null && !Producto.getCodigodepartamento().equals("")){
		  InSql = InSql + " and a.codigodepartamento like '" + Producto.getCodigodepartamento().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigomarca()!=null && !Producto.getCodigomarca().equals("")){
		  InSql = InSql + " and a.codigomarca like '" + Producto.getCodigomarca().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigomedida()!=null && !Producto.getCodigomedida().equals("")){
		  InSql = InSql + " and a.codigomedida like '" + Producto.getCodigomedida().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getUtilidadminima()!=null && !Producto.getUtilidadminima().toString().equals("")){
		  InSql = InSql + " and a.utilidadminima = " + Producto.getUtilidadminima().toString();
		  Estado=1;
	  }
	  if (Producto.getUtilidadmaxima()!=null && !Producto.getUtilidadmaxima().toString().equals("")){
		  InSql = InSql + " and a.utilidadmaxima = " + Producto.getUtilidadmaxima().toString();
		  Estado=1;
	  }
	  if (Producto.getTemporadamaximoconsumo()!=null && !Producto.getTemporadamaximoconsumo().toString().equals("")){
		  InSql = InSql + " and a.temporadamaximoconsumo like '" + Producto.getTemporadamaximoconsumo().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getIncrementotemporada()!=null && !Producto.getIncrementotemporada().toString().equals("")){
		  InSql = InSql + " and a.incrementotemporada = " + Producto.getIncrementotemporada().toString();
		  Estado=1;
	  }
	  if (Producto.getExistencia()!=null && !Producto.getExistencia().toString().equals("")){
		  InSql = InSql + " and a.existencia = " + Producto.getExistencia().toString();
		  Estado=1;
	  }
	  if (Producto.getExistenciamaxima()!=null && !Producto.getExistenciamaxima().toString().equals("")){
		  InSql = InSql + " and a.existenciamaxima = " + Producto.getExistenciamaxima().toString();
		  Estado=1;
	  }
	  if (Producto.getExistenciaminima()!=null && !Producto.getExistenciaminima().toString().equals("")){
		  InSql = InSql + " and a.existenciaminima = " + Producto.getExistenciaminima().toString();
		  Estado=1;
	  }
	  if (Producto.getGradoalcohol()!=null && !Producto.getGradoalcohol().toString().equals("")){
		  InSql = InSql + " and a.gradoalcohol = " + Producto.getGradoalcohol().toString();
		  Estado=1;
	  }
	  if (Producto.getRegulado()!=null && !Producto.getRegulado().equals("")){
		  InSql = InSql + " and a.regulado like '" + Producto.getRegulado().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getDetallerequerido()!=null && !Producto.getDetallerequerido().equals("")){
		  InSql = InSql + " and a.detallerequerido like '" + Producto.getDetallerequerido().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getIndicaciones()!=null && !Producto.getIndicaciones().equals("")){
		  InSql = InSql + " and a.indicaciones like '" + Producto.getIndicaciones().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getContraindicaciones()!=null && !Producto.getContraindicaciones().equals("")){
		  InSql = InSql + " and a.contraindicaciones like '" + Producto.getContraindicaciones().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getUbicacion()!=null && !Producto.getUbicacion().equals("")){
		  InSql = InSql + " and a.ubicacion like '" + Producto.getUbicacion().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigoproveedor()!=null && !Producto.getCodigoproveedor().equals("")){
		  InSql = InSql + " and a.codigoproveedor like '" + Producto.getCodigoproveedor().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getCodigocontrol()!=null && !Producto.getCodigocontrol().equals("")){
		  InSql = InSql + " and a.codigocontrol like '" + Producto.getCodigocontrol().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getProductocompuesto()!=null && !Producto.getProductocompuesto().equals("")){
		  InSql = InSql + " and a.productocompuesto like '" + Producto.getProductocompuesto().replace("*", "%") + "'";
		  Estado=1;
	  }
	  if (Producto.getEstado()!=null && !Producto.getEstado().equals("")){
		  InSql = InSql + " and a.estado like '" + Producto.getEstado().replace("*", "%") + "'";
		  Estado=1;
	  }else{
		  InSql = InSql + " and (a.estado = 'Activo' or a.estado is null or a.estado='') " ;
		  Estado=1;		  
	  }
	  if (Producto.getCosto()!=null && !Producto.getCosto().toString().equals("")){
		  InSql = InSql + " and a.costo = " + Producto.getCosto().toString();
		  Estado=1;
	  }
	  if (Producto.getCostoanterior()!=null && !Producto.getCostoanterior().toString().equals("")){
		  InSql = InSql + " and a.costoanterior = " + Producto.getCostoanterior().toString();
		  Estado=1;
	  }
	  if (Producto.getCostopromedio()!=null && !Producto.getCostopromedio().toString().equals("")){
		  InSql = InSql + " and a.costopromedio = " + Producto.getCostopromedio().toString();
		  Estado=1;
	  }
	  query = query + " Left Join tblgruposproductos       b On a.codigogrupo        = b.codigogrupo ";
	  query = query + " Left Join tblsubgruposproductos    c On a.codigosubgrupo     = c.codigosubgrupo ";
	  query = query + " Left Join tbldepositos             d On a.codigodeposito     = d.codigodeposito ";
	  query = query + " Left Join tbldepartamentos         e On a.codigodepartamento = e.codigodepartamento ";
	  query = query + " Left Join tblmarcas                f On a.codigomarca        = f.codigomarca ";
	  query = query + " Left Join tblunidadmedida          g On a.codigomedida       = g.codigomedida ";
	  query = query + " Left Join tblsustanciascontroladas h On a.codigocontrol      = h.codigocontrol ";

	  if(Estado==1){
         query = query + " Where 1=1 " + InSql;
	  }
      query = query  + " order by a.descripcioncorta ";
      //System.out.println(query);
      return query;

   }
   
   void setProducto(tblProducto Producto){
        setId(Producto.getId());
	    setCodigoproducto(Producto.getCodigoproducto());
        setDescripcioncorta(Producto.getDescripcioncorta());
        setDescripcionlarga(Producto.getDescripcionlarga());
	    setCodigogrupo(Producto.getCodigogrupo());
        setCodigosubgrupo(Producto.getCodigosubgrupo());
	    setCodigodeposito(Producto.getCodigodeposito()); 
	    setCodigodepartamento(Producto.getCodigodepartamento());  
	    setCodigomarca(Producto.getCodigomarca()); 
        setCodigomedida(Producto.getCodigomedida());  
        setUtilidadminima(Producto.getUtilidadminima()); 
        setUtilidadmaxima(Producto.getUtilidadmaxima());
        setTemporadamaximoconsumo(Producto.getTemporadamaximoconsumo());
        setIncrementotemporada(Producto.getIncrementotemporada());
        setExistencia(Producto.getExistencia());
        setExistenciamaxima(Producto.getExistenciamaxima());
        setExistenciaminima(Producto.getExistenciaminima());
        setGradoalcohol(Producto.getGradoalcohol());
        setRegulado(Producto.getRegulado());  
        setDetallerequerido(Producto.getDetallerequerido());  
        setIndicaciones(Producto.getIndicaciones());  
        setContraindicaciones(Producto.getContraindicaciones());  
        setUbicacion(Producto.getUbicacion());  
        setCodigoproveedor(Producto.getCodigoproveedor());  
        setCodigocontrol(Producto.getCodigocontrol());  
        setProductocompuesto(Producto.getProductocompuesto());
        setEstado(Producto.getEstado());
        setCosto(Producto.getCosto());
        setCostoanterior(Producto.getCostoanterior());
        setCostopromedio(Producto.getCostopromedio()); 
        TblDeposito.setDeposito(Producto.TblDeposito);
        TblDepartamento.setDepartamento(Producto.TblDepartamento);
        TblMarca.setMarca(Producto.TblMarca);
        TblUnidadMedida.setUnidadMedida(Producto.TblUnidadMedida);
        TblGruposProducto.setGruposProducto(Producto.TblGruposProducto);
        TblSubGruposProducto.setSubGruposProducto(Producto.TblSubGruposProducto);
        TblSustanciasControlada.setSustanciasControladas(Producto.TblSustanciasControlada);
        setProductoMedidas(Producto.getProductoMedidas());
        setProductoMedidasDefault(Producto.getProductoMedidasDefault());
        setCodigoBarras(Producto.getCodigoBarras());
        setPrecioProductos(Producto.getPrecioProductos());  
        setProductoImpuestos(Producto.getProductoImpuestos());  
        setProductoDepositos(Producto.getProductoDepositos());
        
   }
   
   public String getProximoCodigo() throws Exception {
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  String ProximoCodigo=null;  
	  
	  String query = "Select max(codigoproducto) From tblproductos Where abs(codigoproducto)>0";
	
	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs!=null){
	     if(rs.last()==true){
		    rs.first();
            ProximoCodigo=rs.getString(1);        
	     }
	  }

      if(ProximoCodigo!=null && !ProximoCodigo.equals("")){
         if(ByosValidar.esNumero(ProximoCodigo)){ 
            Integer Valor = Integer.valueOf(ProximoCodigo); 
            Valor++;
            ProximoCodigo=String.valueOf(Valor.longValue());
            ProximoCodigo=ByosValidar.Add_String_I(ProximoCodigo,'0',10);    
         }        
      }else{           
    	 ProximoCodigo="0000000001"; 
      }
      ByosSql.CloseAll(con,pstmt,rs);
      return ProximoCodigo;
   }
   
   public ArrayList <tblCodigoBarra> BuscarArrayList(String CodigoProducto) throws Exception {
   	  tblCodigoBarra[] TblCodigoBarras = new tblCodigoBarra().Buscar(CodigoProducto);
   	  ArrayList <tblCodigoBarra> ArrayTblCodigoBarras = new ArrayList <tblCodigoBarra> ();
   	  if(TblCodigoBarras!=null && TblCodigoBarras.length>0){
   	     for(int f=0;f<TblCodigoBarras.length;f++){
   		     ArrayTblCodigoBarras.add(TblCodigoBarras[f]);
   	     }
   	  }
   	
   	  return ArrayTblCodigoBarras;
   }
   
   public ArrayList <tblCodigoBarra> BuscarArrayList() {
	  ArrayList <tblCodigoBarra> ArrayTblCodigoBarras = new ArrayList <tblCodigoBarra> ();
	  if(TblCodigoBarras!=null && TblCodigoBarras.length>0){
	   	 for(int f=0;f<TblCodigoBarras.length;f++){
	   		 ArrayTblCodigoBarras.add(TblCodigoBarras[f]);
	   	 }
	  }
	  return ArrayTblCodigoBarras;   
   }
   
   
}