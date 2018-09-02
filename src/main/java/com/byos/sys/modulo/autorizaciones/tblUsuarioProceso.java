package com.byos.sys.modulo.autorizaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;
import com.byos.sys.procesos.tblProceso;

public class tblUsuarioProceso {
private String usuLogin;
private String procesoCodigo;


public tblUsuarioProceso(){
	
}


public void setUsuLogin(String usuLogin){
	this.usuLogin=usuLogin;
}

public String getUsuLogin(){
	return this.usuLogin;
}

public void setProcesoCodigo(String procesoCodigo){
	this.procesoCodigo=procesoCodigo;
}

public String getProcesoCodigo(){
	return this.procesoCodigo;
}

public ArrayList<tblProceso> getUsuarioProceso(String usuLogin) throws Exception{
	ArrayList<tblProceso> L = new ArrayList<tblProceso>();
	ResultSet rs = null;
	PreparedStatement ps =null;
	Connection con = Conexion.getNuevaConexion();
	String query = "Select procesocodigo from tblusuarioproceso where usulogin=?";
	
	ps = con.prepareStatement(query);
	ps.setString(1, usuLogin);
	rs = ps.executeQuery();
	
	while (rs.next()){
		tblProceso per = new tblProceso();
		per.setProceso(rs.getString("procesocodigo"));
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
