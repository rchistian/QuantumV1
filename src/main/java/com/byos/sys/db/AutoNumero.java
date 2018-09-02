package com.byos.sys.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.byos.sys.conexion.Conexion;


public class AutoNumero {

	
	public AutoNumero(){}
	
	public static Long getAutoNumero(String tabla, String campo){
			Long i = new Long(0);
			ResultSet rs=null;
			PreparedStatement pstmt1 = null;
			String query1 = "Select fldNum from tblNum where tblNom='" + tabla + "' and tblCam='" + campo + "'";
			String query2 = "Insert into tblNum values('" + tabla + "','" + campo + "'" + ",1)";
			String query3 = "Update tblNum set fldNum=fldNum+1 where tblNom='" + tabla + "' and tblCam='" + campo + "'";
			
			Connection con = new Conexion().getNuevaConexion();
			
			int size =0;
			try{
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement(query1);
			rs = pstmt1.executeQuery(query1);
			
				rs.last();
				size = rs.getRow();
				if (size>0){
		    pstmt1 = con.prepareStatement(query3);
			pstmt1.executeUpdate();
			pstmt1 = con.prepareStatement(query1);
			rs = pstmt1.executeQuery();
			con.commit();
			con.setAutoCommit(true);
			if (rs!=null){
			rs.beforeFirst();
			rs.next();
			i = rs.getLong("fldNum");
			}
			}
			
				else{
				pstmt1 = con.prepareStatement(query2);
				pstmt1.execute();
				pstmt1 = con.prepareStatement(query1);
				rs = pstmt1.executeQuery();
				con.commit();
			    con.setAutoCommit(true);
				rs.beforeFirst();
				rs.next();
				i = rs.getLong("fldNum");
				}
			
			
			pstmt1.close();
			pstmt1 = null;
			rs.close();
			rs=null;
			con.close();
			con = null;
			
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		return i;
		}	
		
	}
	
	
	

