package com.byos.sys.modulo.autorizaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import com.byos.sys.app.ByosApp;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.db.AutoNumero;
import com.byos.sys.modulo.perfil.tblPerfil;
import com.byos.sys.procesos.tblProceso;
import com.byos.sys.util.utilDate;
import com.byos.sys.util.utilString;
import com.vaadin.ui.UI;

public class tblAutorizaciones {

	private String usuLogin;
	private ArrayList<tblUsuarioPerfil> ALUsuarioPerfil;
	private ArrayList<tblUsuarioProceso> ALUsuarioProceso;

		public tblAutorizaciones(){
			usuLogin="";
			ALUsuarioPerfil = new ArrayList();
			ALUsuarioProceso = new ArrayList();
		}
		
	public void setUsuLogin(String usuLogin){
		this.usuLogin=usuLogin;
	}
	
	public String getUsuLogin(){
		return this.usuLogin;
	}
	
	
	public String autorizacionGuardar(String usuLogin, ArrayList<tblPerfil> ALUsuarioPerfil, ArrayList<tblProceso> ALUsuarioProcesos) throws Exception{
		String status = utilString.PROCESO_NO_EJECUTADO;
		
		Connection con = Conexion.getNuevaConexion();
		
		PreparedStatement ps = null;
		String deleteUsuarioPerfil = "delete from tblusuarioperfil where usulogin=?";
		String insertUsuarioPerfil = "Insert into tblusuarioperfil(idx,usulogin,perfilcodigo,idxfecha,idxusuario) values(?,?,?,?,?)";
		String deleteUsuarioProceso = "delete from tblusuarioproceso where usulogin=?";
		String insertUsuarioProceso = "Insert into tblusuarioproceso(idx,usulogin,procesocodigo,idxfecha,idxusuario) values(?,?,?,?,?)";

		con.setAutoCommit(false);
		
		ps = con.prepareStatement(deleteUsuarioPerfil);
		ps.setString(1, usuLogin);
		ps.execute();
		
		
		Iterator itePer = ALUsuarioPerfil.iterator();
		ps = con.prepareStatement(insertUsuarioPerfil);
		
		while (itePer.hasNext()){
		tblPerfil temp = new tblPerfil();	
		temp = (tblPerfil)itePer.next();
		ps.setLong(1,AutoNumero.getAutoNumero("tblusuarioperfil", "idx"));
		ps.setString(2, usuLogin);
		ps.setInt(3,temp.getPerfilCodigo());
		ps.setTimestamp(4, utilDate.horaDelDia());
		ByosApp BA = (ByosApp)ByosApp.getCurrent();
		ps.setString(5,BA.getUsuario());
		ps.execute();
		}
		
		
		ps = con.prepareStatement(deleteUsuarioProceso);
		ps.setString(1, usuLogin);
		ps.execute();
		
		Iterator itePro = ALUsuarioProcesos.iterator();
		ps = con.prepareStatement(insertUsuarioProceso);
		
		while (itePro.hasNext()){
		tblProceso proTemp = new tblProceso();
		proTemp = (tblProceso)itePro.next();
		ps.setLong(1, AutoNumero.getAutoNumero("tblusuarioproceso", "idx"));
		ps.setString(2,usuLogin);
		ps.setString(3, proTemp.getProceso());
		System.out.println(proTemp.getProceso());
		ps.setTimestamp(4, utilDate.horaDelDia());
		ByosApp BA = (ByosApp)ByosApp.getCurrent();
		ps.setString(5, BA.getUsuario());
		ps.execute();
		}
		
		con.commit();
		con.setAutoCommit(true);
		con.close();
		ps.close();
		
		status = utilString.PROCESO_EJECUTADO;
		return status;
	}
	
	public String autorizacionEliminar(String usuLogin) throws Exception{
        String status = utilString.PROCESO_NO_EJECUTADO;
		
		Connection con = Conexion.getNuevaConexion();
		
		PreparedStatement ps = null;
		String deleteUsuarioPerfil = "delete from tblusuarioperfil where usulogin=?";
		String deleteUsuarioProceso = "delete from tblusuarioproceso where usulogin=?";
		
		con.setAutoCommit(false);
		
		ps = con.prepareStatement(deleteUsuarioPerfil);
		ps.setString(1, usuLogin);
		ps.execute();
		
		ps = con.prepareStatement(deleteUsuarioProceso);
		ps.setString(1, usuLogin);
		ps.execute();
		
		
		con.commit();
		
		ps.close();
		con.close();
		ps=null;
		con=null;
		
		status = utilString.PROCESO_EJECUTADO;
		return status;
	}
	
	
	
	
}
