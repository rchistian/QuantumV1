package com.byos.sys.modulo.perfil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import com.byos.sys.app.ByosApp;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.db.AutoNumero;
import com.byos.sys.procesos.Proceso;
import com.byos.sys.procesos.tblProceso;
import com.byos.sys.util.utilDate;

public class tblPerfil extends Object implements Serializable {
	private Integer perfilCodigo;
	private String perfilDesc;
	
	public tblPerfil(){
		perfilCodigo=null;
		perfilDesc="";
	}
	
	
	public Integer getPerfilCodigo(){
		return this.perfilCodigo;
	}
	
	
	public void setPerfilCodigo(Integer codigo){
		this.perfilCodigo=codigo;
	}
	
	public String getPerfilDesc(){
		return this.perfilDesc;
	}
	
	public void setPerfilDesc(String perfilDesc){
		this.perfilDesc=perfilDesc;
	}
	
	
	public ArrayList getPerfiles() throws Exception{
		ArrayList AL = new ArrayList();
		ResultSet rs = null;
		PreparedStatement ps=null;
		String query = "Select * from tblperfil";
		Connection con = Conexion.getNuevaConexion(); 
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		
		while (rs.next()){
			tblPerfil p = new tblPerfil();
			p.setPerfilCodigo(rs.getInt("perfilCodigo"));
			p.setPerfilDesc(rs.getString("perfilDesc"));
			AL.add(p);
		}
	
		rs.close();
		ps.close();
		con.close();
		rs=null;
		ps=null;
		return AL;
	}
	
	
	public ArrayList getPerfilProcesosAsignados(int perfilCodigo) throws Exception{
		ArrayList AL = new ArrayList();
		ResultSet rs = null;
		PreparedStatement ps=null;
		String query = "Select perfilcodigo,procesocodigo from tblperfilproceso where perfilcodigo=?";
		Connection con = Conexion.getNuevaConexion(); 
		ps = con.prepareStatement(query);
		ps.setInt(1,perfilCodigo);
		rs = ps.executeQuery();
		
		while (rs.next()){
			tblPerfilProceso p = new tblPerfilProceso();
			p.setperfilCodigo(rs.getInt("perfilcodigo"));
			p.setprocesoCodigo(rs.getString("procesocodigo"));
			AL.add(p);
		}
	
		rs.close();
		ps.close();
		con.close();
		rs=null;
		ps=null;
		return AL;
	}
	
	
	
	
	public ArrayList getPerfilProcesosDisponibles(ArrayList ProcesosAsignados) throws Exception{	
		Proceso p = Proceso.APLICACION_INIT;
		
		ArrayList ProcesosDisponibles = new ArrayList();
		
		ArrayList TodosProcesos = p.getProcesos();
		
		Iterator<tblProceso> ite = TodosProcesos.iterator();
		
		while (ite.hasNext()){
		  tblProceso proceso = ite.next();
			
			Iterator<tblPerfilProceso> PA = ProcesosAsignados.iterator();
			boolean found = false;
			
			while (PA.hasNext()){
				tblPerfilProceso tp = PA.next();
				if (tp.getprocesoCodigo().equals(proceso.getProceso())){
					found =true;
				}
				if (found == true){
					break;
				}
				
			}
			if (found == false){
				tblPerfilProceso pp = new tblPerfilProceso();
				pp.setprocesoCodigo(proceso.getProceso());
				ProcesosDisponibles.add(pp);
			}
			
		}
		return ProcesosDisponibles;
	}
	
	
	public int guardarPerfil(tblPerfil per, ArrayList ALPerPro) throws Exception{
		int i =0;
		PreparedStatement psPer = null;
		PreparedStatement psAL = null;
		ResultSet rs = null;
	
		Connection con = Conexion.getNuevaConexion();
		Long perfilTemp=new Long(0);
		con.setAutoCommit(false);
		
		String selectPer = "Select perfilcodigo from tblperfil where perfilcodigo=?";
		
		psPer = con.prepareStatement(selectPer);
		psPer.setObject(1,per.getPerfilCodigo());
		rs = psPer.executeQuery();
		
		//Update Perfil
		if (rs.last()){
		String updatePer = "Update tblperfil set perfildesc=?, idxfecha=?, idxusuario=? where perfilcodigo=?";	
		perfilTemp = new Long(per.getPerfilCodigo());
		psPer = con.prepareStatement(updatePer);
		psPer.setString(1,per.getPerfilDesc());
		psPer.setTimestamp(2, utilDate.horaDelDia());
		ByosApp BA = (ByosApp)ByosApp.getCurrent();
		psPer.setString(3, BA.getUsuario());
		psPer.setInt(4, per.getPerfilCodigo());
		i=psPer.executeUpdate();
		}
		//Insert Perfil
		else{
		String insertPer = "Insert into tblperfil(idx,perfilcodigo,perfildesc,idxfecha,idxusuario) values (?,?,?,?,?)";
		psPer = con.prepareStatement(insertPer);
		psPer.setLong(1,AutoNumero.getAutoNumero("tblPerfil", "idx"));
		perfilTemp = AutoNumero.getAutoNumero("tblPerfil", "perfilCodigo");
		psPer.setLong(2,perfilTemp);
		psPer.setString(3,per.getPerfilDesc());
		psPer.setTimestamp(4, utilDate.horaDelDia());
		ByosApp BA = (ByosApp)ByosApp.getCurrent();
		
		psPer.setString(5, BA.getUsuario());
		i=psPer.executeUpdate();
		}
	
		String deletePro = "Delete from tblperfilproceso where perfilcodigo=?";
		psAL = con.prepareStatement(deletePro);
		psAL.setObject(1, per.getPerfilCodigo());
		psAL.executeUpdate();
		
		
		String insertPro = "Insert into tblperfilproceso(idx,perfilcodigo,procesocodigo,idxfecha,idxusuario) values(?,?,?,?,?)";
		Iterator ite = ALPerPro.iterator();
		psAL = con.prepareStatement(insertPro);
		while (ite.hasNext()){
		tblPerfilProceso tPP = (tblPerfilProceso)ite.next();
		psAL.setLong(1,AutoNumero.getAutoNumero("tblPerfilProceso", "idx"));
		psAL.setLong(2,perfilTemp);
		psAL.setString(3,tPP.getprocesoCodigo());
		psAL.setTimestamp(4, utilDate.horaDelDia());
		ByosApp BA = (ByosApp)ByosApp.getCurrent();
		
		psAL.setString(5, BA.getUsuario());
		psAL.executeUpdate();
		}
		
		con.commit();
		con.setAutoCommit(true);
		
		psPer.close();
		psAL.close();
		rs.close();
		con.close();
		psPer = null;
		psAL=null;
		rs=null;
		
		
		return i;
	}
	
	public int eliminarPerfil(tblPerfil per) throws Exception{
		int i=0;
		PreparedStatement ps =null;
		
		Connection con = Conexion.getNuevaConexion(); 
		
		con.setAutoCommit(false);
		String deletePerfil = "Delete from tblperfil where perfilcodigo=?";
		ps = con.prepareStatement(deletePerfil);
		ps.setInt(1, per.getPerfilCodigo());
		i = ps.executeUpdate();
		
		String deletePerfilProceso = "Delete from tblperfilproceso where perfilcodigo=?";
		ps = con.prepareStatement(deletePerfilProceso);
		ps.setInt(1,per.getPerfilCodigo());
		ps.executeUpdate();
		
		con.commit();
		ps.close();
		con.close();
		ps=null;
		
		return i;
	}
	
     public boolean equals(Object per){
		tblPerfil p = (tblPerfil)per;
		boolean b = false;
		if (p !=null){
		if (p.perfilCodigo==this.perfilCodigo){
			b = true;
		}
		}
		return b;
	}
	
	
}
