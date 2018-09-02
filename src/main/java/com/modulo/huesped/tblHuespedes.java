package com.modulo.huesped;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.impuestos.tblImpuestos;
import com.vaadin.data.util.IndexedContainer;

public class tblHuespedes  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String Tabla = "TblHuespedes";
    public String Resultado; 
    public String GlobalCodigoEmpresa = "0001";

/*01*/	  public String CodigoEmpresa;
/*02*/    public Integer ID;
/*03*/    public String Codigo;
/*04*/    public String Nombres;
/*05*/    public String Apellidos;
/*06*/    public String Apodo;
/*07*/    public String Direccion;
/*08*/    public Date   FechaNacimiento;
/*09*/    public String Sexo;
/*10*/    public String Nivel;
/*11*/    public String CodigoPostal;
/*12*/    public String Ciudad;
/*13*/    public String Provincia;
/*14*/    public String NumeroDocumento;
/*15*/    public Date   VencimientoDoc;
/*16*/    public String TipoDocumento;
/*17*/    public String TelefonoMovil;
/*18*/    public String Telefono;
/*19*/    public String Correo;
/*20*/    public String Altura;
/*21*/    public String ColorPiel;
/*22*/    public String ColorOjos;
/*23*/    public String PaisOrigen;
/*24*/    public String CiudadOrigen;
/*25*/    public String DireccionOrigen;
/*26*/    public String CodigoPostalOrigen;
/*27*/    public String TelefonoOrigen;
/*28*/    public String AgenciaOrigen;
/*29*/    public String SSNumero;
/*30*/    public Date SSFechaAlta;
/*31*/    public String SMNumero;
/*32*/    public String SMCompania;
/*33*/    public String SANumero;
/*34*/    public String SACompania;
/*35*/    public String SSAAutonomo;
/*36*/    public String SSAIAE;
/*37*/    public String SSAEpigrafe;
/*38*/    public String Usuario;
/*39*/    public Date Fecha;
/*40*/    public String EstadoRegistro;
/*41*/    public String Estado;
/*42*/    public String RedSocial01;
/*43*/    public String RedSocial02;
/*44*/    public String RedSocial03;
/*45*/    public String RedSocial04;
/*46*/    public String RedSocial05;
/*47*/    public String CodigoTarjeta;
/*48*/    public String Vehiculo;
/*49*/    public String TipoVivienda;
/*50*/    public String CantidadHijos;
/*51*/    public String ColorCabello;
/*52*/    public String Medidas;
/*53*/    public String Recomendado;
/*54*/    public Date UltimaEstadia;
/*55*/    public String Amistad;
/*56*/    public Date UltimaEstadiaAmistad;
/*57*/    public String UltimaHabitacion;
/*58*/    public String EstadoCivil;
/*59*/    public String RedSocial06;
/*60*/    public String RedSocial07;
/*61*/    public String RedSocial08;
/*62*/    public String RedSocial09;
/*63*/    public String RedSocial10;



	public boolean Estatus;

    public String StringSelect =
/*01*/        "CodigoEmpresa," +
/*02*/        "ID," +
/*03*/        "Codigo," +
/*04*/        "Nombres," +
/*05*/        "Apellidos," +
/*06*/        "Apodo," +
/*07*/        "Direccion," +
/*08*/        "FechaNacimiento," +
/*09*/        "Sexo," +
/*10*/        "Nivel," +
/*11*/        "CodigoPostal," +
/*12*/        "Ciudad," +
/*13*/        "Provincia," +
/*14*/        "NumeroDocumento," +
/*15*/        "VencimientoDoc," +
/*16*/        "TipoDocumento," +
/*17*/        "TelefonoMovil," +
/*18*/        "Telefono," +
/*19*/        "Correo," +
/*20*/        "Altura," +
/*21*/        "ColorPiel," +
/*22*/        "ColorOjos," +
/*23*/        "PaisOrigen," +
/*24*/        "CiudadOrigen," +
/*25*/        "DireccionOrigen," +
/*26*/        "CodigoPostalOrigen," +
/*27*/        "TelefonoOrigen," +
/*28*/        "AgenciaOrigen," +
/*29*/        "SSNumero," +
/*30*/        "SSFechaAlta," +
/*31*/        "SMNumero," +
/*32*/        "SMCompania," +
/*33*/        "SANumero," +
/*34*/        "SACompania," +
/*35*/        "SSAAutonomo," +
/*36*/        "SSAIAE," +
/*37*/        "SSAEpigrafe," +
/*38*/        "Usuario," +
/*39*/        "Fecha," +
/*40*/        "EstadoRegistro," +
/*41*/        "Estado," +
/*42*/        "RedSocial01," +
/*43*/        "RedSocial02," +
/*44*/        "RedSocial03," +
/*45*/        "RedSocial04," +
/*46*/        "RedSocial05," +
/*47*/        "CodigoTarjeta," +
/*48*/        "Vehiculo," +
/*49*/        "TipoVivienda," +
/*50*/        "CantidadHijos," +
/*51*/        "ColorCabello," +
/*52*/        "Medidas," +
/*53*/        "Recomendado," +
/*54*/        "UltimaEstadia," +
/*55*/        "Amistad," +
/*56*/        "UltimaEstadiaAmistad," +
/*57*/        "UltimaHabitacion," +
/*58*/        "EstadoCivil," +
/*59*/        "RedSocial06," +
/*60*/        "RedSocial07," +
/*61*/        "RedSocial08," +
/*62*/        "RedSocial09," +
/*63*/        "RedSocial10"; 
    
    public String StringSelectUpdate =
/*01*/        "CodigoEmpresa=?," +
/*02*/        "ID=?," +
/*03*/        "Codigo=?," +
/*04*/        "Nombres=?," +
/*05*/        "Apellidos=?," +
/*06*/        "Apodo=?," +
/*07*/        "Direccion=?," +
/*08*/        "FechaNacimiento=?," +
/*09*/        "Sexo=?," +
/*10*/        "Nivel=?," +
/*11*/        "CodigoPostal=?," +
/*12*/        "Ciudad=?," +
/*13*/        "Provincia=?," +
/*14*/        "NumeroDocumento=?," +
/*15*/        "VencimientoDoc=?," +
/*16*/        "TipoDocumento=?," +
/*17*/        "TelefonoMovil=?," +
/*18*/        "Telefono=?," +
/*19*/        "Correo=?," +
/*20*/        "Altura=?," +
/*21*/        "ColorPiel=?," +
/*22*/        "ColorOjos=?," +
/*23*/        "PaisOrigen=?," +
/*24*/        "CiudadOrigen=?," +
/*25*/        "DireccionOrigen=?," +
/*26*/        "CodigoPostalOrigen=?," +
/*27*/        "TelefonoOrigen=?," +
/*28*/        "AgenciaOrigen=?," +
/*29*/        "SSNumero=?," +
/*30*/        "SSFechaAlta=?," +
/*31*/        "SMNumero=?," +
/*32*/        "SMCompania=?," +
/*33*/        "SANumero=?," +
/*34*/        "SACompania=?," +
/*35*/        "SSAAutonomo=?," +
/*36*/        "SSAIAE=?," +
/*37*/        "SSAEpigrafe=?," +
/*38*/        "Usuario=?," +
/*39*/        "Fecha=?," +
/*40*/        "EstadoRegistro=?," +
/*41*/        "Estado=?," +
/*42*/        "RedSocial01=?," +
/*43*/        "RedSocial02=?," +
/*44*/        "RedSocial03=?," +
/*45*/        "RedSocial04=?," +
/*46*/        "RedSocial05=?," +
/*47*/        "CodigoTarjeta=?," +
/*48*/        "Vehiculo=?," +
/*49*/        "TipoVivienda=?," +
/*50*/        "CantidadHijos=?," +
/*51*/        "ColorCabello=?," +
/*52*/        "Medidas=?," +
/*53*/        "Recomendado=?," +
/*54*/        "UltimaEstadia=?," +
/*55*/        "Amistad=?," +
/*56*/        "UltimaEstadiaAmistad=?," +
/*57*/        "UltimaHabitacion=?," +
/*58*/        "EstadoCivil=?," +
/*59*/        "RedSocial06=?," +
/*60*/        "RedSocial07=?," +
/*61*/        "RedSocial08=?," +
/*62*/        "RedSocial09=?," +
/*63*/        "RedSocial10=?"; 
    
    public tblHuespedes() {
       limpiar();
    }

    public String getResultado() {
		return Resultado;
	}

	public void setResultado(String resultado) {
		Resultado = resultado;
	}

	public String getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getNombres() {
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getApodo() {
		return Apodo;
	}

	public void setApodo(String apodo) {
		Apodo = apodo;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getNivel() {
		return Nivel;
	}

	public void setNivel(String nivel) {
		Nivel = nivel;
	}

	public String getCodigoPostal() {
		return CodigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
	}

	public String getCiudad() {
		return Ciudad;
	}

	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public String getNumeroDocumento() {
		return NumeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		NumeroDocumento = numeroDocumento;
	}

	public Date getVencimientoDoc() {
		return VencimientoDoc;
	}

	public void setVencimientoDoc(Date vencimientoDoc) {
		VencimientoDoc = vencimientoDoc;
	}

	public String getTipoDocumento() {
		return TipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}

	public String getTelefonoMovil() {
		return TelefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		TelefonoMovil = telefonoMovil;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getAltura() {
		return Altura;
	}

	public void setAltura(String altura) {
		Altura = altura;
	}

	public String getColorPiel() {
		return ColorPiel;
	}

	public void setColorPiel(String colorPiel) {
		ColorPiel = colorPiel;
	}

	public String getColorOjos() {
		return ColorOjos;
	}

	public void setColorOjos(String colorOjos) {
		ColorOjos = colorOjos;
	}

	public String getPaisOrigen() {
		return PaisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		PaisOrigen = paisOrigen;
	}

	public String getCiudadOrigen() {
		return CiudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		CiudadOrigen = ciudadOrigen;
	}

	public String getDireccionOrigen() {
		return DireccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		DireccionOrigen = direccionOrigen;
	}

	public String getCodigoPostalOrigen() {
		return CodigoPostalOrigen;
	}

	public void setCodigoPostalOrigen(String codigoPostalOrigen) {
		CodigoPostalOrigen = codigoPostalOrigen;
	}

	public String getTelefonoOrigen() {
		return TelefonoOrigen;
	}

	public void setTelefonoOrigen(String telefonoOrigen) {
		TelefonoOrigen = telefonoOrigen;
	}

	public String getAgenciaOrigen() {
		return AgenciaOrigen;
	}

	public void setAgenciaOrigen(String agenciaOrigen) {
		AgenciaOrigen = agenciaOrigen;
	}

	public String getSSNumero() {
		return SSNumero;
	}

	public void setSSNumero(String sSNumero) {
		SSNumero = sSNumero;
	}

	public Date getSSFechaAlta() {
		return SSFechaAlta;
	}

	public void setSSFechaAlta(Date sSFechaAlta) {
		SSFechaAlta = sSFechaAlta;
	}

	public String getSMNumero() {
		return SMNumero;
	}

	public void setSMNumero(String sMNumero) {
		SMNumero = sMNumero;
	}

	public String getSMCompania() {
		return SMCompania;
	}

	public void setSMCompania(String sMCompania) {
		SMCompania = sMCompania;
	}

	public String getSANumero() {
		return SANumero;
	}

	public void setSANumero(String sANumero) {
		SANumero = sANumero;
	}

	public String getSACompania() {
		return SACompania;
	}

	public void setSACompania(String sACompania) {
		SACompania = sACompania;
	}

	public String getSSAAutonomo() {
		return SSAAutonomo;
	}

	public void setSSAAutonomo(String sSAAutonomo) {
		SSAAutonomo = sSAAutonomo;
	}

	public String getSSAIAE() {
		return SSAIAE;
	}

	public void setSSAIAE(String sSAIAE) {
		SSAIAE = sSAIAE;
	}

	public String getSSAEpigrafe() {
		return SSAEpigrafe;
	}

	public void setSSAEpigrafe(String sSAEpigrafe) {
		SSAEpigrafe = sSAEpigrafe;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public String getEstadoRegistro() {
		return EstadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		EstadoRegistro = estadoRegistro;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getRedSocial01() {
		return RedSocial01;
	}

	public void setRedSocial01(String redSocial01) {
		RedSocial01 = redSocial01;
	}

	public String getRedSocial02() {
		return RedSocial02;
	}

	public void setRedSocial02(String redSocial02) {
		RedSocial02 = redSocial02;
	}

	public String getRedSocial03() {
		return RedSocial03;
	}

	public void setRedSocial03(String redSocial03) {
		RedSocial03 = redSocial03;
	}

	public String getRedSocial04() {
		return RedSocial04;
	}

	public void setRedSocial04(String redSocial04) {
		RedSocial04 = redSocial04;
	}

	public String getRedSocial05() {
		return RedSocial05;
	}

	public void setRedSocial05(String redSocial05) {
		RedSocial05 = redSocial05;
	}

	public String getCodigoTarjeta() {
		return CodigoTarjeta;
	}

	public void setCodigoTarjeta(String codigoTarjeta) {
		CodigoTarjeta = codigoTarjeta;
	}

	public String getVehiculo() {
		return Vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		Vehiculo = vehiculo;
	}

	public String getTipoVivienda() {
		return TipoVivienda;
	}

	public void setTipoVivienda(String tipoVivienda) {
		TipoVivienda = tipoVivienda;
	}

	public String getCantidadHijos() {
		return CantidadHijos;
	}

	public void setCantidadHijos(String cantidadHijos) {
		CantidadHijos = cantidadHijos;
	}

	public String getColorCabello() {
		return ColorCabello;
	}

	public void setColorCabello(String colorCabello) {
		ColorCabello = colorCabello;
	}

	public String getMedidas() {
		return Medidas;
	}

	public void setMedidas(String medidas) {
		Medidas = medidas;
	}

	public boolean isEstatus() {
		return Estatus;
	}

	public void setEstatus(boolean estatus) {
		Estatus = estatus;
	}
	public String getRecomendado() {
		return Recomendado;
	}

	public void setRecomendado(String recomendado) {
		Recomendado = recomendado;
	}
	
	public Date getUltimaEstadia() {
		return UltimaEstadia;
	}
	
	public void setUltimaEstadia(Date ultimaEstadia) {
		UltimaEstadia = ultimaEstadia;
	}
	
	public String getAmistad() {
		return Amistad;
	}
	
	public void setAmistad(String amistad) {
		Amistad = amistad;
	}
	
	public Date getUltimaEstadiaAmistad() {
		return UltimaEstadiaAmistad;
	}
	
	public void setUltimaEstadiaAmistad(Date ultimaEstadiaAmistad) {
		UltimaEstadiaAmistad = ultimaEstadiaAmistad;
	}
	
	public String getUltimaHabitacion() {
		return UltimaHabitacion;
	}
	
	public void setUltimaHabitacion(String ultimaHabitacion) {
		UltimaHabitacion = ultimaHabitacion;
	}

    public String getEstadoCivil() {
  	    return EstadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
  	    EstadoCivil = estadoCivil;
    }
    public String getRedSocial06() {
    	return RedSocial06;
    }

    public void setRedSocial06(String redSocial06) {
    	RedSocial06 = redSocial06;
    }

    public String getRedSocial07() {
    	return RedSocial07;
    }

    public void setRedSocial07(String redSocial07) {
    	RedSocial07 = redSocial07;
    }

    public String getRedSocial08() {
    	return RedSocial08;
    }

    public void setRedSocial08(String redSocial08) {
    	RedSocial08 = redSocial08;
    }

    public String getRedSocial09() {
    	return RedSocial09;
    }

    public void setRedSocial09(String redSocial09) {
    	RedSocial09 = redSocial09;
    }

    public String getRedSocial10() {
    	return RedSocial10;
    }

    public void setRedSocial10(String redSocial10) {
    	RedSocial10 = redSocial10;
    }
    
    public boolean buscarCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Select " + StringSelect + " from "  + Tabla + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' And EstadoRegistro='ACTIVO' And NumeroDocumento=?" ;
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	   
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     setRegistro(rs, this);
             ByosSql.CloseAll(con,pstmt,rs);
		     return true; 
          }	
       }
	   ByosSql.CloseAll(con,pstmt,rs);
       limpiar();
	   return false;
    }
    
    public boolean existeCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "Select NumeroDocumento From "  + Tabla + " Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and NumeroDocumento=?";	
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
    
    public String eliminar(tblHuespedes tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(existeCodigo(tbl.getNumeroDocumento())){
			  String query = "Update "  + Tabla + " set EstadoRegistro='BORRADO'  Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and NumeroDocumento=?";	
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_ELIMINADO;
		   }
	
	   }catch (SQLException e) {
		  e.printStackTrace();
	   }catch (Exception e) {
		  e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;
    }
       
    public String guardar(tblHuespedes tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   System.out.println("Bandera Guardar 01");
	   Connection con = Conexion.getNuevaConexion();
	   System.out.println("Bandera Guardar 02");
	   
	   try{
		   if(!existeCodigo(tbl.getNumeroDocumento())){
			  String query = "Insert into " + Tabla + "(" + StringSelect + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  ActualizarDatos(tbl, pstmt);
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
			  System.out.println("Bandera Update 01");
		      String query = "Update tblHuespedes set " + StringSelectUpdate + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and ID='" + tbl.getID() + "'";
			  con.setAutoCommit(false);
			  System.out.println("Bandera Update 02");
			  pstmt = con.prepareStatement(query);
			  System.out.println("Bandera Update 03");
			  ActualizarDatos(tbl, pstmt);
			  System.out.println("Bandera Update 04");
			  i = pstmt.executeUpdate();
			  System.out.println("Bandera Update 05");
			  con.commit();
			  ByosSql.CloseAll(con,pstmt,rs);
			  System.out.println("Bandera Update 06");
		      return utilString.SQL_MODIFICADO;
		   }
	   }catch (SQLException e) {
		   e.printStackTrace();
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;
    }

    public void ActualizarDatos(tblHuespedes tbl, PreparedStatement pstmt) throws SQLException {
	  	  pstmt.setString(1,GlobalCodigoEmpresa);
	 	  pstmt.setInt(2,tbl.ID);
	 	  pstmt.setString(3,tbl.Codigo);
	 	  pstmt.setString(4,tbl.Nombres);
	      pstmt.setString(5,tbl.Apellidos);
	 	  pstmt.setString(6,tbl.Apodo);
	 	  pstmt.setString(7,tbl.Direccion);
	 	  //System.out.println("Bandera FechaNacimiento 01: " + utilDate.DateToTimestamp(tbl.FechaNacimiento));
	 	  pstmt.setTimestamp(8, utilDate.DateToTimestamp(utilDate.DateToTimestamp(tbl.FechaNacimiento)));
	 	  
	 	  pstmt.setString(9,tbl.Sexo);
	 	  pstmt.setString(10,tbl.Nivel);
	 	  pstmt.setString(11,tbl.CodigoPostal);
	 	  pstmt.setString(12,tbl.Ciudad);
	 	  pstmt.setString(13,tbl.Provincia);
	 	  pstmt.setString(14,tbl.NumeroDocumento);
	 	  //System.out.println("Bandera VencimientoDoc 02: " + utilDate.DateToTimestamp(tbl.VencimientoDoc));
	 	  pstmt.setTimestamp(15, utilDate.DateToTimestamp(utilDate.DateToTimestamp(tbl.VencimientoDoc)));
	 	  
	  	  pstmt.setString(16,tbl.TipoDocumento);
	 	  pstmt.setString(17,tbl.TelefonoMovil);
	 	  pstmt.setString(18,tbl.Telefono);
	 	  pstmt.setString(19,tbl.Correo);
	 	  pstmt.setString(20,tbl.Altura);          
	 	  pstmt.setString(21,tbl.ColorPiel);
	 	  pstmt.setString(22,tbl.ColorOjos);
	 	  pstmt.setString(23,tbl.PaisOrigen);
	 	  pstmt.setString(24,tbl.CiudadOrigen);
	 	  pstmt.setString(25,tbl.DireccionOrigen);
	 	  pstmt.setString(26,tbl.CodigoPostalOrigen);             
	 	  pstmt.setString(27,tbl.TelefonoOrigen);
	 	  pstmt.setString(28,tbl.AgenciaOrigen);
	 	  pstmt.setString(29,tbl.SSNumero);
	 	  //System.out.println("Bandera SSFechaAlta 03: " + utilDate.DateToTimestamp(tbl.SSFechaAlta));
	 	  pstmt.setTimestamp(30, utilDate.DateToTimestamp(utilDate.DateToTimestamp(tbl.SSFechaAlta)));
	 	  
	 	  pstmt.setString(31,tbl.SMNumero);
	 	  pstmt.setString(32,tbl.SMCompania);
	 	  pstmt.setString(33,tbl.SANumero);
	 	  pstmt.setString(34,tbl.SACompania);		 	  
	 	  pstmt.setString(35,tbl.SSAAutonomo);
	 	  pstmt.setString(36,tbl.SSAIAE);
	 	  pstmt.setString(37,tbl.SSAEpigrafe);
	 	  pstmt.setString(38,tbl.Usuario);
	 	  //System.out.println("Bandera Fecha 04: " + utilDate.DateToTimestamp(tbl.Fecha));
	 	  pstmt.setTimestamp(39, utilDate.DateToTimestamp(utilDate.DateToTimestamp(tbl.Fecha)));
	 	  
	 	  pstmt.setString(40,"ACTIVO");
	 	  pstmt.setString(41,tbl.Estado);
	 	  pstmt.setString(42,tbl.RedSocial01);
	 	  pstmt.setString(43,tbl.RedSocial02);
	 	  pstmt.setString(44,tbl.RedSocial03);
	 	  pstmt.setString(45,tbl.RedSocial04);
	 	  pstmt.setString(46,tbl.RedSocial05);
	 	  pstmt.setString(47,tbl.CodigoTarjeta);
	 	  pstmt.setString(48,tbl.Vehiculo);
	 	  pstmt.setString(49,tbl.TipoVivienda);
	 	  pstmt.setString(50,tbl.CantidadHijos);
	 	  pstmt.setString(51,tbl.ColorCabello);
	 	  pstmt.setString(52,tbl.Medidas);
	 	  pstmt.setString(53,tbl.Recomendado);
	 	  //System.out.println("Bandera UltimaEstadia 05: " + utilDate.DateToTimestamp(tbl.UltimaEstadia));
	      pstmt.setTimestamp(54, utilDate.DateToTimestamp(utilDate.DateToTimestamp(tbl.UltimaEstadia)));
	      
	 	  pstmt.setString(55,tbl.Amistad);
	 	  //System.out.println("Bandera UltimaEstadiaAmistad 06: " + utilDate.DateToTimestamp(tbl.UltimaEstadiaAmistad));
	  	  pstmt.setTimestamp(56, utilDate.DateToTimestamp(utilDate.DateToTimestamp(tbl.UltimaEstadiaAmistad)));
	  	  
	 	  pstmt.setString(57,tbl.UltimaHabitacion);
	 	  pstmt.setString(58,tbl.EstadoCivil);
	 	  pstmt.setString(59,tbl.RedSocial06);
	 	  pstmt.setString(60,tbl.RedSocial07);
	 	  pstmt.setString(61,tbl.RedSocial08);
	 	  pstmt.setString(62,tbl.RedSocial09);
	      pstmt.setString(63,tbl.RedSocial10); 
	 	 
    }
    
    public void setRegistro(ResultSet rs, tblHuespedes Registros) throws Exception {
        Registros.setCodigoEmpresa(GlobalCodigoEmpresa);
        Registros.setID(rs.getInt("ID")); 
        Registros.setCodigo(rs.getString("Codigo")); 
        Registros.setNombres(rs.getString("Nombres"));
        Registros.setApellidos(rs.getString("Apellidos"));
        Registros.setApodo(rs.getString("Apodo"));
        Registros.setDireccion(rs.getString("Direccion"));
        Registros.setFechaNacimiento(rs.getDate("FechaNacimiento"));
        Registros.setSexo(rs.getString("Sexo"));
        Registros.setNivel(rs.getString("Nivel"));
        Registros.setCodigoPostal(rs.getString("CodigoPostal"));
        Registros.setCiudad(rs.getString("Ciudad"));
        Registros.setProvincia(rs.getString("Provincia"));
        Registros.setNumeroDocumento(rs.getString("NumeroDocumento"));
        Registros.setVencimientoDoc(rs.getDate("VencimientoDoc"));
        Registros.setTipoDocumento(rs.getString("TipoDocumento"));
        Registros.setTelefonoMovil(rs.getString("TelefonoMovil"));
        Registros.setTelefono(rs.getString("Telefono"));
        Registros.setCorreo(rs.getString("Correo"));
        Registros.setAltura(rs.getString("Altura"));          
        Registros.setColorPiel(rs.getString("ColorPiel"));
        Registros.setColorOjos(rs.getString("ColorOjos"));
        Registros.setPaisOrigen(rs.getString("PaisOrigen"));
        Registros.setCiudadOrigen(rs.getString("CiudadOrigen"));
        Registros.setDireccionOrigen(rs.getString("DireccionOrigen"));
        Registros.setCodigoPostalOrigen(rs.getString("CodigoPostalOrigen"));             
        Registros.setTelefonoOrigen(rs.getString("TelefonoOrigen"));
        Registros.setAgenciaOrigen(rs.getString("AgenciaOrigen"));
        Registros.setSSNumero(rs.getString("SSNumero"));
        Registros.setSSFechaAlta(rs.getDate("SSFechaAlta"));
        Registros.setSMNumero(rs.getString("SMNumero"));
        Registros.setSMCompania(rs.getString("SMCompania"));
        Registros.setSANumero(rs.getString("SANumero"));
        Registros.setSACompania(rs.getString("SACompania"));		 	  
        Registros.setSSAAutonomo(rs.getString("SSAAutonomo"));
        Registros.setSSAIAE(rs.getString("SSAIAE"));
        Registros.setSSAEpigrafe(rs.getString("SSAEpigrafe"));
        Registros.setUsuario(rs.getString("Usuario"));
        Registros.setFecha(rs.getDate("Fecha"));
        Registros.setEstadoRegistro(rs.getString("EstadoRegistro"));
        Registros.setEstado(rs.getString("Estado"));
        Registros.setRedSocial01(rs.getString("RedSocial01"));
        Registros.setRedSocial02(rs.getString("RedSocial02"));
        Registros.setRedSocial03(rs.getString("RedSocial03"));
        Registros.setRedSocial04(rs.getString("RedSocial04"));
        Registros.setRedSocial05(rs.getString("RedSocial05"));
        Registros.setCodigoTarjeta(rs.getString("CodigoTarjeta"));
        Registros.setVehiculo(rs.getString("Vehiculo"));
        Registros.setTipoVivienda(rs.getString("TipoVivienda"));
        Registros.setCantidadHijos(rs.getString("CantidadHijos"));
        Registros.setColorCabello(rs.getString("ColorCabello"));
        Registros.setMedidas(rs.getString("Medidas")); 
        
        Registros.setRecomendado(rs.getString("Recomendado"));
        Registros.setUltimaEstadia(rs.getDate("UltimaEstadia"));
        Registros.setAmistad(rs.getString("Amistad"));
        Registros.setUltimaEstadiaAmistad(rs.getDate("UltimaEstadiaAmistad"));
        Registros.setUltimaHabitacion(rs.getString("UltimaHabitacion"));
        
        Registros.setEstadoCivil(rs.getString("EstadoCivil"));
        Registros.setRedSocial06(rs.getString("RedSocial06"));
        Registros.setRedSocial07(rs.getString("RedSocial07"));
        Registros.setRedSocial08(rs.getString("RedSocial08"));
        Registros.setRedSocial09(rs.getString("RedSocial09"));
        Registros.setRedSocial10(rs.getString("RedSocial10")); 
    }
          
    public ArrayList <tblHuespedes> BuscarArray(tblHuespedes tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
       String InSql = StringInSql(tbl);
	   ArrayList <tblHuespedes> tblArray = new ArrayList(); 
	   tblHuespedes[] Registros; 
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(InSql);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
			 size = rs.getRow();
			 Registros = new tblHuespedes[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblHuespedes();
		    	 setRegistro(rs,Registros[f]);		         
		         tblArray.add(Registros[f]);
		         rs.next();
		     }
		     ByosSql.CloseAll(con,pstmt,rs);
		     return tblArray;	       
	      } 
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
    
    public tblHuespedes[] Buscar(tblHuespedes tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   tblHuespedes[] Registros; 
	   
	   String InSql = StringInSql(tbl);
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(InSql);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     Registros = new tblHuespedes[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 setRegistro(rs,Registros[f]);	
		         rs.next();
		     }
		     ByosSql.CloseAll(con,pstmt,rs);
		     return Registros;	       
	      } 
	   }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return null;
    }
    
    public String StringInSql(tblHuespedes tbl) {
 	   String InSql="";
 	   boolean Estado=false;
 	   
       if(tbl.getNombres()!=null &&!tbl.getNombres().equals("")){
 		  InSql = InSql + " and Nombres like '" + tbl.getNombres().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getApellidos()!=null && !tbl.getApellidos().equals("")){
 		  InSql = InSql + " and Apellidos like '" + tbl.getApellidos().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getApodo()!=null && !tbl.getApodo().equals("")){
 		  InSql = InSql + " and Apodo  like '" + tbl.getApodo().replace("*", "%") + "'";
 		  Estado=true;
 	   }	
 	   if(tbl.getDireccion()!=null && !tbl.getDireccion().equals("")){
 		  InSql = InSql + " and Direccion like '" + tbl.getDireccion().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getFechaNacimiento()!=null && !tbl.getFechaNacimiento().equals("")){
 		  //InSql = InSql + " and FechaNacimiento like '" + tbl.getFechaNacimiento().replace("*", "%") + "'";
 		  //Estado=true;
 	   }
 	   if(tbl.getSexo()!=null && !tbl.getSexo().equals("")){
 		  InSql = InSql + " and Sexo like '" + tbl.getSexo().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getNivel()!=null && !tbl.getNivel().equals("")){
 		  InSql = InSql + " and Nivel like '" + tbl.getNivel().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getCodigoPostal()!=null && !tbl.getCodigoPostal().equals("")){
 		  InSql = InSql + " and CodigoPostal like '" + tbl.getCodigoPostal().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getCiudad()!=null && !tbl.getCiudad().equals("")){
 		  InSql = InSql + " and Ciudad like '" + tbl.getCiudad().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getProvincia()!=null && !tbl.getProvincia().equals("")){
 		  InSql = InSql + " and Provincia like '" + tbl.getProvincia().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getNumeroDocumento()!=null && !tbl.getNumeroDocumento().equals("")){
 		  InSql = InSql + " and NumeroDocumento like '" + tbl.getNumeroDocumento().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getVencimientoDoc()!=null && !tbl.getVencimientoDoc().equals("")){
 		  //InSql = InSql + " and VencimientoDoc like '" + tbl.getVencimientoDoc().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getTipoDocumento()!=null && !tbl.getTipoDocumento().equals("")){
 		  InSql = InSql + " and TipoDocumento like '" + tbl.getTipoDocumento().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getTelefonoMovil()!=null && !tbl.getTelefonoMovil().equals("")){
 		  InSql = InSql + " and TelefonoMovil like '" + tbl.getTelefonoMovil().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getTelefono()!=null && !tbl.getTelefono().equals("")){
 		  InSql = InSql + " and Telefono like '" + tbl.getTelefono().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getCorreo()!=null && !tbl.getCorreo().equals("")){
 		  InSql = InSql + " and Correo like '" + tbl.getCorreo().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getAltura()!=null && !tbl.getAltura().equals("")){
 		  InSql = InSql + " and Altura like '" + tbl.getAltura().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getColorPiel()!=null && !tbl.getColorPiel().equals("")){
 		  InSql = InSql + " and ColorPiel like '" + tbl.getColorPiel().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getColorOjos()!=null && !tbl.getColorOjos().equals("")){
 		  InSql = InSql + " and ColorOjos like '" + tbl.getColorOjos().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getPaisOrigen()!=null && !tbl.getPaisOrigen().equals("")){
 		  InSql = InSql + " and PaisOrigen like '" + tbl.getPaisOrigen().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getCiudadOrigen()!=null && !tbl.getCiudadOrigen().equals("")){
 		  InSql = InSql + " and CiudadOrigen like '" + tbl.getCiudadOrigen().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getDireccionOrigen()!=null && !tbl.getDireccionOrigen().equals("")){
 		  InSql = InSql + " and DireccionOrigen like '" + tbl.getDireccionOrigen().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getCodigoPostalOrigen()!=null && !tbl.getCodigoPostalOrigen().equals("")){
 		  InSql = InSql + " and CodigoPostalOrigen like '" + tbl.getCodigoPostalOrigen().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getTelefonoOrigen()!=null && !tbl.getTelefonoOrigen().equals("")){
 		  InSql = InSql + " and TelefonoOrigen like '" + tbl.getTelefonoOrigen().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   	   
 	   if(tbl.getAgenciaOrigen()!=null && !tbl.getAgenciaOrigen().equals("")){
 		  InSql = InSql + " and AgenciaOrigen like '" + tbl.getAgenciaOrigen().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSSNumero()!=null && !tbl.getSSNumero().equals("")){
 		  InSql = InSql + " and SSNumero like '" + tbl.getSSNumero().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSSFechaAlta()!=null && !tbl.getSSFechaAlta().equals("")){
 		  //InSql = InSql + " and SSFechaAlta like '" + tbl.getSSFechaAlta().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSMNumero()!=null && !tbl.getSMNumero().equals("")){
 		  InSql = InSql + " and SMNumero like '" + tbl.getSMNumero().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSMCompania()!=null && !tbl.getSMCompania().equals("")){
 		  InSql = InSql + " and SMCompania like '" + tbl.getSMCompania().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSANumero()!=null && !tbl.getSANumero().equals("")){
 		  InSql = InSql + " and SANumero like '" + tbl.getSANumero().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSACompania()!=null && !tbl.getSACompania().equals("")){
 		  InSql = InSql + " and SACompania like '" + tbl.getSACompania().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSSAAutonomo()!=null && !tbl.getSSAAutonomo().equals("")){
 		  InSql = InSql + " and SSAAutonomo like '" + tbl.getSSAAutonomo().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSSAIAE()!=null && !tbl.getSSAIAE().equals("")){
 		  InSql = InSql + " and SSAIAE like '" + tbl.getSSAIAE().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getSSAEpigrafe()!=null && !tbl.getSSAEpigrafe().equals("")){
 		  InSql = InSql + " and SSAEpigrafe like '" + tbl.getSSAEpigrafe().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getUsuario()!=null && !tbl.getUsuario().equals("")){
 		  InSql = InSql + " and Usuario like '" + tbl.getUsuario().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getFecha()!=null && !tbl.getFecha().equals("")){
 		  //InSql = InSql + " and Fecha like '" + tbl.getFecha().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getEstadoRegistro()!=null && !tbl.getEstadoRegistro().equals("")){
 		  InSql = InSql + " and EstadoRegistro like '" + tbl.getEstadoRegistro().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getEstado()!=null && !tbl.getEstado().equals("")){
 		  InSql = InSql + " and Estado like '" + tbl.getEstado().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getRedSocial01()!=null && !tbl.getRedSocial01().equals("")){
 		  InSql = InSql + " and RedSocial01 like '" + tbl.getRedSocial01().replace("*", "%") + "'";
 		  Estado=true;
 	   }	 
 	   if(tbl.getRedSocial02()!=null && !tbl.getRedSocial02().equals("")){
 		  InSql = InSql + " and RedSocial02 like '" + tbl.getRedSocial02().replace("*", "%") + "'";
 		  Estado=true;
 	   }	 
 	   if(tbl.getRedSocial03()!=null && !tbl.getRedSocial03().equals("")){
 		  InSql = InSql + " and RedSocial03 like '" + tbl.getRedSocial03().replace("*", "%") + "'";
 		  Estado=true;
 	   }	 
 	   if(tbl.getRedSocial04()!=null && !tbl.getRedSocial04().equals("")){
 		  InSql = InSql + " and RedSocial04 like '" + tbl.getRedSocial04().replace("*", "%") + "'";
 		  Estado=true;
 	   }	 
 	   if(tbl.getRedSocial05()!=null && !tbl.getRedSocial05().equals("")){
 		  InSql = InSql + " and RedSocial05 like '" + tbl.getRedSocial05().replace("*", "%") + "'";
 		  Estado=true;
 	   }	 	   
 	   if(tbl.getCodigoTarjeta()!=null && !tbl.getCodigoTarjeta().equals("")){
 		  InSql = InSql + " and CodigoTarjeta like '" + tbl.getCodigoTarjeta().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getVehiculo()!=null && !tbl.getVehiculo().equals("")){
 		  InSql = InSql + " and Vehiculo like '" + tbl.getVehiculo().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getTipoVivienda()!=null && !tbl.getTipoVivienda().equals("")){
 		  InSql = InSql + " and TipoVivienda like '" + tbl.getTipoVivienda().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getCantidadHijos()!=null && !tbl.getCantidadHijos().equals("")){
 		  InSql = InSql + " and CantidadHijos like '" + tbl.getCantidadHijos().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getColorCabello()!=null && !tbl.getColorCabello().equals("")){
 		  InSql = InSql + " and ColorCabello like '" + tbl.getColorCabello().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getMedidas()!=null && !tbl.getMedidas().equals("")){
 		  InSql = InSql + " and Medidas like '" + tbl.getMedidas().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getEstadoCivil()!=null && !tbl.getEstadoCivil().equals("")){
 		  InSql = InSql + " and EstadoCivil like '" + tbl.getEstadoCivil().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getRedSocial06()!=null && !tbl.getRedSocial06().equals("")){
 		  InSql = InSql + " and RedSocial06 like '" + tbl.getRedSocial06().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getRedSocial07()!=null && !tbl.getRedSocial07().equals("")){
 		  InSql = InSql + " and RedSocial07 like '" + tbl.getRedSocial07().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getRedSocial08()!=null && !tbl.getRedSocial08().equals("")){
 		  InSql = InSql + " and RedSocial08 like '" + tbl.getRedSocial08().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getRedSocial09()!=null && !tbl.getRedSocial09().equals("")){
 		  InSql = InSql + " and RedSocial09 like '" + tbl.getRedSocial09().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getRedSocial10()!=null && !tbl.getRedSocial10().equals("")){
 		  InSql = InSql + " and RedSocial10 like '" + tbl.getRedSocial10().replace("*", "%") + "'";
 		  Estado=true;
 	   }

 	   String query = "Select " + StringSelect + " " +
	       		  "from " + Tabla + " Where CodigoEmpresa = '" + GlobalCodigoEmpresa + 
	       		                         "' and EstadoRegistro='ACTIVO' ";	   
       if(Estado){
          query = query + " " + InSql;
       }
       query = query  + " order by Apodo";
       System.out.println(query);
 	   
 	   
 	   return query;
    }
    	
    /*
       public IndexedContainer getHuespedesContainer(){ 
       tblHuespedes[] TblHuespedes=null;
       IndexedContainer Container = new IndexedContainer();
       try{
		   TblHuespedes = new tblHuespedes().Buscar(new tblHuespedes());
	   }catch (Exception e) {
	   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
       if(TblHuespedes!=null && TblHuespedes.length>0){
    	  Container.addItem(""); 
          for(int f=0;f<TblHuespedes.length;f++){
              Container.addItem(TblHuespedes[f].getID() + "," + TblHuespedes[f].getNombres() + TblHuespedes[f].Apellidos + "," + TblHuespedes[f].Apodo);  
          }	
       }
       return Container;  
    }
    
    */
    
    public void limpiar(){
        setCodigoEmpresa("");
        setID(null);
        setCodigo("");
        setNombres("");
        setApellidos("");
        setApodo("");
        setDireccion("");
        setFechaNacimiento(null);
        setSexo("");
        setNivel("");
        setCodigoPostal("");
        setCiudad("");
        setProvincia("");
        setNumeroDocumento("");
        setVencimientoDoc(null);
        setTipoDocumento("");
        setTelefonoMovil("");
        setTelefono("");
        setCorreo("");
        setAltura("");          
        setColorPiel("");
        setColorOjos("");
        setPaisOrigen("");
        setCiudadOrigen("");
        setDireccionOrigen("");
        setCodigoPostalOrigen("");             
        setTelefonoOrigen("");
        setAgenciaOrigen("");
        setSSNumero("");
        setSSFechaAlta(null);
        setSMNumero("");
        setSMCompania("");
	 	setSANumero("");
	 	setSACompania("");		 	  
        setSSAAutonomo("");
        setSSAIAE("");
        setSSAEpigrafe("");
        setUsuario("");
        setFecha(null);
        setEstadoRegistro("");
        setEstado("");
        setRedSocial01("");
        setRedSocial02("");
        setRedSocial03("");
        setRedSocial04("");
        setRedSocial05("");
        setCodigoTarjeta("");
        setVehiculo("");
        setTipoVivienda("");
        setCantidadHijos("");
        setColorCabello("");
        setMedidas("");
        
        setRecomendado("");
        setUltimaEstadia(null);
        setAmistad("");
        setUltimaEstadiaAmistad(null);
        setUltimaHabitacion("");
        
        setEstadoCivil("");
        setRedSocial06("");
        setRedSocial07("");
        setRedSocial08("");
        setRedSocial09("");
        setRedSocial10(""); 

    }   
    
    public void setTblHuespedes(tblHuespedes Huespedes){
        setCodigoEmpresa(Huespedes.getCodigoEmpresa());
        setCodigo(Huespedes.getCodigo()); 
        setID(Huespedes.getID()); 
        setNombres(Huespedes.getNombres());
        setApellidos(Huespedes.getApellidos());
        setApodo(Huespedes.getApodo());
        setDireccion(Huespedes.getDireccion());
        setFechaNacimiento(Huespedes.getFechaNacimiento());
        setSexo(Huespedes.getSexo());
        setNivel(Huespedes.getNivel());
        setCodigoPostal(Huespedes.getCodigoPostal());
        setCiudad(Huespedes.getCiudad());
        setProvincia(Huespedes.getProvincia());
        setNumeroDocumento(Huespedes.getNumeroDocumento());
        setVencimientoDoc(Huespedes.getVencimientoDoc());
        setTipoDocumento(Huespedes.getTipoDocumento());
        setTelefonoMovil(Huespedes.getTelefonoMovil());
        setTelefono(Huespedes.getTelefono());
        setCorreo(Huespedes.getCorreo());
        setAltura(Huespedes.getAltura());          
        setColorPiel(Huespedes.getColorPiel());
        setColorOjos(Huespedes.getColorOjos());
        setPaisOrigen(Huespedes.getPaisOrigen());
        setCiudadOrigen(Huespedes.getCiudadOrigen());
        setDireccionOrigen(Huespedes.getDireccionOrigen());
        setCodigoPostalOrigen(Huespedes.getCodigoPostalOrigen());             
        setTelefonoOrigen(Huespedes.getTelefonoOrigen());
        setAgenciaOrigen(Huespedes.getAgenciaOrigen());
        setSSNumero(Huespedes.getSSNumero());
        setSSFechaAlta(Huespedes.getSSFechaAlta());
        setSMNumero(Huespedes.getSMNumero());
        setSMCompania(Huespedes.getSMCompania());
	 	setSANumero(Huespedes.getSANumero());
	 	setSACompania(Huespedes.getSACompania());		 	  
        setSSAAutonomo(Huespedes.getSSAAutonomo());
        setSSAIAE(Huespedes.getSSAIAE());
        setSSAEpigrafe(Huespedes.getSSAEpigrafe());
        setUsuario(Huespedes.getUsuario());
        setFecha(Huespedes.getFecha());
        setEstadoRegistro(Huespedes.getEstadoRegistro());
        setEstado(Huespedes.getEstado());
        setRedSocial01(Huespedes.getRedSocial01());
        setRedSocial02(Huespedes.getRedSocial02());
        setRedSocial03(Huespedes.getRedSocial03());
        setRedSocial04(Huespedes.getRedSocial04());
        setRedSocial05(Huespedes.getRedSocial05());
        setCodigoTarjeta(Huespedes.getCodigoTarjeta());
        setVehiculo(Huespedes.getVehiculo());
        setTipoVivienda(Huespedes.getTipoVivienda());
        setCantidadHijos(Huespedes.getCantidadHijos());
        setColorCabello(Huespedes.getColorCabello());
        setMedidas(Huespedes.getMedidas());
        
        setRecomendado(Huespedes.getRecomendado());
        setUltimaEstadia(Huespedes.getUltimaEstadia());
        setAmistad(Huespedes.getAmistad());
        setUltimaEstadiaAmistad(Huespedes.getUltimaEstadiaAmistad());
        setUltimaHabitacion(Huespedes.getUltimaHabitacion());

        setEstadoCivil(Huespedes.getEstadoCivil());
        setRedSocial06(Huespedes.getRedSocial06());
        setRedSocial07(Huespedes.getRedSocial07());
        setRedSocial08(Huespedes.getRedSocial08());
        setRedSocial09(Huespedes.getRedSocial09());
        setRedSocial10(Huespedes.getRedSocial10()); 
    }   

}
