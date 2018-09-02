package com.modulo.proveedor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.huesped.tblHuespedes;
import com.vaadin.ui.Notification;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;

public class tblProveedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String Tabla = "TblUsuarios";
    public String Resultado; 
    public String GlobalCodigoEmpresa = "0001";
	
	private String codigoempresa;

	private String codigoproveedor;

	private String descripcion;

	private int id;

	private String dni;

	private String direccion;

	private String telefonos;

	private String fax;

	private String correo;

	private String codigoruc;

	private String codigoactividad;

	private String patente;

	private String tipoproveedor;

	private BigDecimal retencioniva;

	private BigDecimal retencionalcaldia;

	private Date fechainicio;

	private Integer diasentrega;

	private Integer diascredito;

	private String representante;

	private String beneficiario;

	private String detalle01;

	private String detalle02;

	private String detalle03;

	private String detalle04;

	private String detalle05;

	private String detalle06;
	
	private String estadoregistro;
	
	
	public String StringSelect = 
	          "id, " 
			+ "codigoproveedor, "
			+ "descripcion, " 
			+ "dni, " 
			+ "direccion, " 
			+ "telefonos, "
			+ "fax, " 
			+ "correo, " 
			+ "codigoruc, " 
			+ "codigoactividad, "
			+ "patente, " 
			+ "tipoproveedor, " 
			+ "retencioniva, "
			+ "retencionalcaldia, " 
			+ "fechainicio, " 
			+ "diasentrega, "
			+ "diascredito, " 
			+ "representante, " 
			+ "beneficiario, "
			+ "detalle01, " 
			+ "detalle02, " 
			+ "detalle03, " 
			+ "detalle04, "
			+ "detalle05, " 
			+ "detalle06,"
  	        + "codigoempresa, "
	        + "estadoregistro ";
	
	public String StringSelectUpdate =
		      "id=?, " 
			+ "codigoproveedor=?, "
		    + "descripcion=?, " 
			+ "dni=?, " 
			+ "direccion=?, " 
			+ "telefonos=?, "
			+ "fax=?, " 
			+ "correo=?, " 
			+ "codigoruc=?, " 
			+ "codigoactividad=?, "
			+ "patente=?, " 
			+ "tipoproveedor=?, " 
			+ "retencioniva=?, "
			+ "retencionalcaldia=?, " 
			+ "fechainicio=?, " 
			+ "diasentrega=?, "
			+ "diascredito=?, " 
			+ "representante=?, " 
			+ "beneficiario=?, "
			+ "detalle01=?, " 
			+ "detalle02=?, " 
			+ "detalle03=?, " 
			+ "detalle04=?, "
			+ "detalle05=?, " 
			+ "detalle06=?,"			
            + "codigoempresa=?, "
            + "estadoregistro=? ";

	public String getEstadoregistro() {
		return estadoregistro;
	}

	public void setEstadoregistro(String estadoregistro) {
		this.estadoregistro = estadoregistro;
	}

	public String getCodigoempresa() {
		return codigoempresa;
	}

	public void setCodigoempresa(String codigoempresa) {
		this.codigoempresa = codigoempresa;
	}
	
	public tblProveedor() {
		limpiar();
	}

	public String getCodigoproveedor() {
		return this.codigoproveedor;
	}

	public void setCodigoproveedor(String codigoproveedor) {
		this.codigoproveedor = codigoproveedor;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getdni() {
		return dni;
	}

	public void setdni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCodigoruc() {
		return codigoruc;
	}

	public void setCodigoruc(String codigoruc) {
		this.codigoruc = codigoruc;
	}

	public String getCodigoactividad() {
		return codigoactividad;
	}

	public void setCodigoactividad(String codigoactividad) {
		this.codigoactividad = codigoactividad;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getTipoproveedor() {
		return tipoproveedor;
	}

	public void setTipoproveedor(String tipoproveedor) {
		this.tipoproveedor = tipoproveedor;
	}

	public BigDecimal getRetencioniva() {
		return retencioniva;
	}

	public void setRetencioniva(BigDecimal retencioniva) {
		this.retencioniva = retencioniva;
	}

	public BigDecimal getRetencionalcaldia() {
		return retencionalcaldia;
	}

	public void setRetencionalcaldia(BigDecimal retencionalcaldia) {
		this.retencionalcaldia = retencionalcaldia;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Integer getDiasentrega() {
		return diasentrega;
	}

	public void setDiasentrega(Integer diasentrega) {
		this.diasentrega = diasentrega;
	}

	public Integer getDiascredito() {
		return diascredito;
	}

	public void setDiascredito(Integer diascredito) {
		this.diascredito = diascredito;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
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

	public String getDetalle04() {
		return detalle04;
	}

	public void setDetalle04(String detalle04) {
		this.detalle04 = detalle04;
	}

	public String getDetalle05() {
		return detalle05;
	}

	public void setDetalle05(String detalle05) {
		this.detalle05 = detalle05;
	}

	public String getDetalle06() {
		return detalle06;
	}

	public void setDetalle06(String detalle06) {
		this.detalle06 = detalle06;
	}

    public void setRegistro(ResultSet rs, tblProveedor Registros) throws Exception {
    	Registros.setCodigoempresa(rs.getString("CodigoEmpresa"));
    	Registros.setId(rs.getInt("id"));
    	Registros.setCodigoproveedor(rs.getString("codigoproveedor"));
    	Registros.setDescripcion(rs.getString("descripcion"));
    	Registros.setdni(rs.getString("dni"));
    	Registros.setDireccion(rs.getString("Direccion"));
    	Registros.setTelefonos(rs.getString("Telefonos"));
    	Registros.setFax(rs.getString("Fax"));
    	Registros.setCorreo(rs.getString("Correo"));
    	Registros.setCodigoruc(rs.getString("Codigoruc"));
    	Registros.setCodigoactividad(rs.getString("Codigoactividad"));
    	Registros.setPatente(rs.getString("Patente"));
    	Registros.setTipoproveedor(rs.getString("Tipoproveedor"));
    	Registros.setRetencioniva(rs.getBigDecimal("Retencioniva"));
    	Registros.setRetencionalcaldia(rs.getBigDecimal("Retencionalcaldia"));
    	Registros.setFechainicio(rs.getDate("Fechainicio"));
    	Registros.setDiasentrega(rs.getInt("Diasentrega"));
    	Registros.setDiascredito(rs.getInt("Diascredito"));
    	Registros.setRepresentante(rs.getString("Representante"));
    	Registros.setBeneficiario(rs.getString("Beneficiario"));
    	Registros.setDetalle01(rs.getString("descripcion"));
    	Registros.setDetalle02(rs.getString("Detalle01"));
    	Registros.setDetalle03(rs.getString("Detalle02"));
    	Registros.setDetalle04(rs.getString("Detalle03"));
    	Registros.setDetalle05(rs.getString("Detalle04"));
    	Registros.setDetalle06(rs.getString("Detalle06"));
    	Registros.setEstadoregistro(rs.getString("EstadoRegistro"));
    	
    }

    
	
	public boolean buscarCodigo(String Codigo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "Select "
				+ StringSelect
				+ "from tblProveedor "
				+ "where  CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and codigoproveedor=?";

		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, Codigo);
		rs = pstmt.executeQuery();
		int size = 0;

		if (rs != null) {
			if (rs.last() == true) {
				size = rs.getRow();
				setRegistro(rs, this);
				ByosSql.CloseAll(con, pstmt, rs);
				return true;
			}
		}
		ByosSql.CloseAll(con, pstmt, rs);
		limpiar();
		return false;
	}

	public boolean existeCodigo(String Codigo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select Codigoproveedor from tblProveedor where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and  Codigoproveedor=?";

		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, Codigo);
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

	public String eliminar(tblProveedor tbl) {
		int i = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = Conexion.getNuevaConexion();
		try {
			if (existeCodigo(tbl.getCodigoproveedor())) {
				String query = "Delete From tblProveedor Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and  Codigoproveedor='"
						+ tbl.getCodigoproveedor() + "'";
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(query);
				i = pstmt.executeUpdate();
				con.commit();
				ByosSql.CloseAll(con, pstmt, rs);
				return utilString.SQL_ELIMINADO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ByosSql.RollBack(con);
		ByosSql.CloseAll(con, pstmt, rs);
		return utilString.SQL_ERROR;
	}

	public void ActualizarDatos(tblProveedor tbl, PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, tbl.getId());
		pstmt.setString(2, tbl.getCodigoproveedor());
		pstmt.setString(3, tbl.getDescripcion());
		pstmt.setString(4, tbl.getdni());
		pstmt.setString(5, tbl.getDireccion());
		pstmt.setString(6, tbl.getTelefonos());
		pstmt.setString(7, tbl.getFax());
		pstmt.setString(8, tbl.getCorreo());
		pstmt.setString(9, tbl.getCodigoruc());
		pstmt.setString(10, tbl.getCodigoactividad());
		pstmt.setString(11, tbl.getPatente());
		pstmt.setString(12, tbl.getTipoproveedor());
		pstmt.setBigDecimal(13, tbl.getRetencioniva());
		pstmt.setBigDecimal(14, tbl.getRetencionalcaldia());
		pstmt.setTimestamp(15, utilDate.DateToTimestamp(tbl.getFechainicio()));
		pstmt.setInt(16, tbl.getDiasentrega());
		pstmt.setInt(17, tbl.getDiascredito());
		pstmt.setString(18, tbl.getRepresentante());
		pstmt.setString(19, tbl.getBeneficiario());
		pstmt.setString(20, tbl.getDetalle01());
		pstmt.setString(21, tbl.getDetalle02());
		pstmt.setString(22, tbl.getDetalle03());
		pstmt.setString(23, tbl.getDetalle04());
		pstmt.setString(24, tbl.getDetalle05());
		pstmt.setString(25, tbl.getDetalle06());
		pstmt.setString(26, GlobalCodigoEmpresa);
		pstmt.setString(27, "ACTIVO");
		
	}
	
	public String guardar(tblProveedor tbl) {
		int i = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = Conexion.getNuevaConexion();
		try {
			if(tbl.getDiasentrega()==null) tbl.setDiasentrega(0);
			if(tbl.getDiascredito()==null) tbl.setDiascredito(0);
			if(tbl.getFechainicio()==null) tbl.setFechainicio(new java.sql.Date(new java.util.Date().getTime()));
			if (!existeCodigo(tbl.getCodigoproveedor())) {
				String query = "Insert into tblProveedor("
						+ StringSelect 
						+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(query);
				
				ActualizarDatos(tbl, pstmt);
				
				i = pstmt.executeUpdate();
				con.commit();
				ByosSql.CloseAll(con, pstmt, rs);
				return utilString.SQL_INSERTADO;
			} else {
				String query = "Update tblProveedor set " 
			            + StringSelectUpdate 
						+ "where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and Codigoproveedor='" + tbl.getCodigoproveedor() + "'";
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(query);

				ActualizarDatos(tbl, pstmt);
				
				i = pstmt.executeUpdate();
				con.commit();
				ByosSql.CloseAll(con, pstmt, rs);
				return utilString.SQL_MODIFICADO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ByosSql.RollBack(con);
		ByosSql.CloseAll(con, pstmt, rs);
		return utilString.SQL_ERROR;
	}

	
	public List<tblProveedor> BuscarList(tblProveedor tbl) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String InSql = "";
		int Estado = 0;
		List<tblProveedor> tblArray = null;

		String query = "Select " 
				+ StringSelect 
				+ "from tblProveedor ";
		if (tbl.getCodigoproveedor() != null
				&& !tbl.getCodigoproveedor().equals("")) {
			InSql = InSql + " and codigoproveedor like '"
					+ tbl.getCodigoproveedor().replace("*", "%") + "'";
			Estado = 1;
		}
		if (tbl.getDescripcion() != null && !tbl.getDescripcion().equals("")) {
			InSql = InSql + " and descripcion like '"
					+ tbl.getDescripcion().replace("*", "%") + "'";
			Estado = 1;
		}
		query = query + " Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' ";
		if (Estado == 1) {
			query = query + InSql;		
		}
		query = query + " order by descripcion";
        System.out.println(query);
		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		int size = 0;

		if (rs != null) {
			if (rs.last() == true) {
				size = rs.getRow();

				rs.first();
				for (int f = 0; f < size; f++) {
					tblProveedor Registros = new tblProveedor();
					setRegistro(rs, Registros);
					tblArray.add(Registros);
					rs.next();
				}
				ByosSql.CloseAll(con, pstmt, rs);
				return tblArray;
			}
		}
		ByosSql.CloseAll(con, pstmt, rs);
		return null;
	}
	
	public ArrayList<tblProveedor> BuscarArray(tblProveedor tbl) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String InSql = "";
		int Estado = 0;
		ArrayList<tblProveedor> tblArray = new ArrayList();

		String query = "Select " 
				+ StringSelect 
				+ "from tblProveedor ";
		if (tbl.getCodigoproveedor() != null
				&& !tbl.getCodigoproveedor().equals("")) {
			InSql = InSql + " and codigoproveedor like '"
					+ tbl.getCodigoproveedor().replace("*", "%") + "'";
			Estado = 1;
		}
		if (tbl.getDescripcion() != null && !tbl.getDescripcion().equals("")) {
			InSql = InSql + " and descripcion like '"
					+ tbl.getDescripcion().replace("*", "%") + "'";
			Estado = 1;
		}
		query = query + " Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' ";
		if (Estado == 1) {
			query = query + InSql;		
		}
		query = query + " order by descripcion";
        System.out.println(query);
		Connection con = Conexion.getNuevaConexion();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		int size = 0;

		if (rs != null) {
			if (rs.last() == true) {
				size = rs.getRow();

				rs.first();
				for (int f = 0; f < size; f++) {
					tblProveedor Registros = new tblProveedor();
					setRegistro(rs, Registros);
					tblArray.add(Registros);
					rs.next();
				}
				ByosSql.CloseAll(con, pstmt, rs);
				return tblArray;
			}
		}
		ByosSql.CloseAll(con, pstmt, rs);
		return null;
	}

	public void limpiar() {
		setId(0);
		setCodigoproveedor("");
		setDescripcion("");
		setdni("");
		setDireccion("");
		setTelefonos("");
		setFax("");
		setCorreo("");
		setCodigoruc("");
		setCodigoactividad("");
		setPatente("");
		setTipoproveedor("");
		setRetencioniva(null);
		setRetencionalcaldia(null);
		setFechainicio(null);
		setDiasentrega(null);
		setDiascredito(null);
		setRepresentante("");
		setBeneficiario("");
		setDetalle01("");
		setDetalle02("");
		setDetalle03("");
		setDetalle04("");
		setDetalle05("");
		setDetalle06("");
		setCodigoempresa("");
		setEstadoregistro("");

	}

	public void setProveedor(tblProveedor Proveedor) {
		setId(Proveedor.getId());
		setCodigoproveedor(Proveedor.getCodigoproveedor());
		setDescripcion(Proveedor.getDescripcion());
		setdni(Proveedor.getdni());
		setDireccion(Proveedor.getDescripcion());
		setTelefonos(Proveedor.getTelefonos());
		setFax(Proveedor.getFax());
		setCorreo(Proveedor.getCorreo());
		setCodigoruc(Proveedor.getCodigoruc());
		setCodigoactividad(Proveedor.getCodigoactividad());
		setPatente(Proveedor.getPatente());
		setTipoproveedor(Proveedor.getTipoproveedor());
		setRetencioniva(Proveedor.getRetencioniva());
		setRetencionalcaldia(Proveedor.getRetencionalcaldia());
		setFechainicio(Proveedor.getFechainicio());
		setDiasentrega(Proveedor.getDiasentrega());
		setDiascredito(Proveedor.getDiascredito());
		setRepresentante(Proveedor.getRepresentante());
		setBeneficiario(Proveedor.getBeneficiario());
		setDetalle01(Proveedor.getDetalle01());
		setDetalle02(Proveedor.getDetalle02());
		setDetalle03(Proveedor.getDetalle03());
		setDetalle04(Proveedor.getDetalle04());
		setDetalle05(Proveedor.getDetalle05());
		setDetalle06(Proveedor.getDetalle06());	
		setCodigoempresa(Proveedor.getCodigoempresa());
		setEstadoregistro(Proveedor.getEstadoregistro());
		
	}  
}