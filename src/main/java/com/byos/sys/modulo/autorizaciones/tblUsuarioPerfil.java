package com.byos.sys.modulo.autorizaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;
import com.byos.sys.modulo.perfil.tblPerfil;

public class tblUsuarioPerfil {
private String usuLogin;
private Integer perfilCodigo;
private String perfilDesc;


public tblUsuarioPerfil(){
	usuLogin="";
	perfilCodigo=-1;
}

public void setUsuLogin(String usuLogin){
	this.usuLogin=usuLogin;
}


public String getUsuLogin(){
	return this.usuLogin;
}


public void setPerfilCodigo(Integer perfilCodigo){
	this.perfilCodigo=perfilCodigo;
}


public Integer getPerfilCodigo(){
	return this.perfilCodigo;
}

public void setPerfilDesc(String perfilDesc){
	this.perfilDesc=perfilDesc;
}

public String getPerfilDesc(){
	return this.perfilDesc;
}

public ArrayList<tblPerfil> getUsuarioPerfil(String usuLogin) throws Exception{
	ArrayList<tblPerfil> L = new ArrayList();
	ResultSet rs = null;
	PreparedStatement ps =null;
	Connection con = Conexion.getNuevaConexion();
	String query = "Select perfilcodigo,perfildesc from tblperfil where perfilcodigo in " +
	" (Select perfilcodigo from tblusuarioperfil where usulogin=?)";
	
	ps = con.prepareStatement(query);
	ps.setString(1, usuLogin);
	rs = ps.executeQuery();
	
	while (rs.next()){
		tblPerfil per = new tblPerfil();
		per.setPerfilCodigo(rs.getInt("perfilcodigo"));
		per.setPerfilDesc(rs.getString("perfilDesc"));
		L.add(per);
	}

	ps.close();
	rs.close();
	con.close();
	ps=null;
	rs = null;
	return L;	
}






}
