package com.byos.sys.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ByosSql {
	
	public ByosSql(){
		
	}
	
	public static void CloseAll(Connection con, PreparedStatement pstmt, ResultSet rs) throws Exception{
	   if(rs!=null){
	      rs.close();
	      rs=null;
	   }
	   if(pstmt!=null){
	      pstmt.close();
	      pstmt=null; 
	   }
	   if(con!=null){
	      con.close();
	      con=null;     
	   }
	} 
}
