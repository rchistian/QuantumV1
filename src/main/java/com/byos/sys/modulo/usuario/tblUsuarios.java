package com.byos.sys.modulo.usuario;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.byos.sys.procesos.apocrifoE;
import com.byos.sys.util.utilDate;
import com.byos.sys.util.utilString;
import com.byos.sys.db.AutoNumero;

import com.byos.sys.app.ByosApp;
import com.byos.sys.conexion.Conexion;

public class tblUsuarios implements Serializable{
	private String usuLogin;
	private String usuPassword;
	private String usuStatus;
	
	public tblUsuarios(){
		usuLogin="";
		usuPassword="";
		usuStatus="";
	}
	
	public tblUsuarios(String login, String password,String status){
		this.usuLogin=login;
		this.usuPassword=password;
		this.usuStatus=status;
	}
	
	
	public String getusuLogin(){
		return this.usuLogin;
	}
	
	public void setusuLogin(String login){
		this.usuLogin=login;
	}
	
	public String getusuPassword(){
		return this.usuPassword;
	}
	
	public void setusuPassword(String password){
		this.usuPassword=password;
	}
	
	public String getusuStatus(){
		return this.usuStatus;
	}
	
	public void setusuStatus(String status){
		this.usuStatus=status;
	}
	
	public ArrayList getArrayUsuarios() throws Exception{
		ArrayList AL = new ArrayList();
		
		ResultSet rs;
		PreparedStatement ps;
		
		Connection cc = Conexion.getNuevaConexion();
		String sql = "Select * from tblusuarios";
		ps = cc.prepareStatement(sql);
		rs = ps.executeQuery();
		
		rs.beforeFirst();
		
		while (rs.next()){
			tblUsuarios temp = new tblUsuarios();
			temp.setusuLogin(rs.getString("usulogin"));
			temp.setusuStatus(rs.getString("usustatus"));
		    AL.add(temp);
		}
		
		rs.close();
		rs=null;
		ps.close();
		ps=null;
		cc.close();
		cc=null;
		
		return AL;
	}
	
	
	public int modificarPassword(){
		int r =0;
		return r;
	}
	
	public int guardarUsuario(tblUsuarios u) throws Exception{
		int r = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String select="Select usulogin from tblusuarios where usulogin=?";
		String insert="Insert into tblusuarios(idx,usulogin,usupassword,usustatus,idxfecha,idxusuario) values(?,?,?,?,?,?)";
		String update="Update tblusuarios set usupassword=?,idxfecha=?,idxusuario=? where usulogin=?";
		Connection con = Conexion.getNuevaConexion();
		
		ps = con.prepareStatement(select);
		ps.setString(1, u.getusuLogin());
		rs = ps.executeQuery();
		
		String encPass = apocrifoE.E(u.getusuPassword(), apocrifoE.ERandom());
		
		if (rs.last()==true){
			ps = con.prepareStatement(update);
			ps.setString(1, encPass);
			ps.setTimestamp(2, utilDate.horaDelDia());
			ByosApp BA = (ByosApp)ByosApp.getCurrent();
			ps.setString(3, BA.getUsuario());
			ps.setString(4, u.getusuLogin());
			r = ps.executeUpdate();
		}
	
		else{
		ps = con.prepareStatement(insert);
		ps.setLong(1, new AutoNumero().getAutoNumero("tblusuarios", "idx"));
		ps.setString(2, u.getusuLogin());
		ps.setString(3, encPass);
		ps.setString(4, "Activo");
		ps.setTimestamp(5, new utilDate().horaDelDia());
		ByosApp BA = (ByosApp)ByosApp.getCurrent();
		ps.setString(6, BA.getUsuario());
		r = ps.executeUpdate();
		}
		
		rs.close();
		rs=null;
		ps.close();
		ps=null;
		con.close();
		con=null;
		
		return r;
	}
	
	public tblUsuarios getUsuario(String usulogin) throws Exception{
		tblUsuarios u = new tblUsuarios();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select usulogin,usustatus from tblusuarios where usulogin=?";
		Connection con = Conexion.getNuevaConexion();
		
		ps = con.prepareStatement(query);
		ps.setString(1,usulogin);
		rs = ps.executeQuery();
		
		if (rs.last()){
			u.setusuLogin(rs.getString("usulogin"));
			u.setusuStatus(rs.getString("usustatus"));
		}
		
		rs.close();
		rs=null;
		ps.close();
		ps=null;
		con.close();
		con = null;
		
		return u;	
		
	}
	
	public String activarUsuario(String usuLogin) throws Exception{
		String r = utilString.PROCESO_NO_EJECUTADO;
		PreparedStatement ps = null;
		String query = "Update tblusuarios set usustatus='Activo' where usulogin=?";
		Connection con = Conexion.getNuevaConexion();
		
		ps = con.prepareStatement(query);
		ps.setString(1,usuLogin);
		
		int i  = ps.executeUpdate();
		if (i>0)
			r = utilString.PROCESO_EJECUTADO;
		
		ps.close();
		ps=null;
		con.close();
		con=null;
		
		return r;
		
	}
	
	
	public String desactivarUsuario(String usuLogin) throws Exception{
		String r = utilString.PROCESO_NO_EJECUTADO;
		PreparedStatement ps = null;
		
		String query = "Update tblusuarios set usustatus='Inactivo' where usulogin=?";
		Connection con = Conexion.getNuevaConexion();
		
		ps = con.prepareStatement(query);
		ps.setString(1,usuLogin);
		
		int i  = ps.executeUpdate();
		if (i>0)
			r = utilString.PROCESO_EJECUTADO;
		
		ps.close();
		ps=null;
		con.close();
		con=null;
		
		return r;
	}
	
	
}
