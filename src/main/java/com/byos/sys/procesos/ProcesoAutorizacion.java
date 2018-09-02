package com.byos.sys.procesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.byos.sys.conexion.Conexion;

public class ProcesoAutorizacion {

	
	public static boolean procesoAutorizado(Proceso L, String usuLogin) throws Exception {
		boolean b = false;
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = new Conexion().getNuevaConexion();
		String query = "SELECT procesoCodigo from tblusuarioproceso where usulogin=? and procesoCodigo=? union " + 
		" SELECT procesocodigo FROM tblperfilproceso,tblusuarioperfil " + 
		" where tblperfilproceso.perfilcodigo=tblusuarioperfil.perfilcodigo and tblusuarioperfil.usulogin=? and procesoCodigo=? " +
		" group by procesocodigo";
		
		ps = conn.prepareStatement(query);
		ps.setString(1, usuLogin);
		ps.setString(2, L.toString());
		ps.setString(3, usuLogin);
		ps.setString(4, L.toString());
		
		rs = ps.executeQuery();
		
		if (rs.last()){
			String p = rs.getString("procesoCodigo");
			if (p.equals(L.toString())){
				b = true;
			}
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		return b;
	}
	
	
}
