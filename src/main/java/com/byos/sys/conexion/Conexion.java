package com.byos.sys.conexion;


import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.DriverManager;




public class Conexion {

	
	public Conexion(){
		
	}
	
	public static Connection getNuevaConexion(){
		
		DataSource dataSource;
		Connection con=null;
		
		try{
		    /*String mysqlDriver = "org.gjt.mm.mysql.Driver";
		    String mysqlUrl = "root;templario08";
		    Class.forName(mysqlDriver);
	        con = DriverManager.getConnection(mysqlDriver,"root","templario08");
		    
	        */
	        Context initContext  = new InitialContext();
            dataSource = (DataSource)initContext.lookup("java:comp/env/jdbc/inventariomobile");
            con = dataSource.getConnection();
            
        
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return con;
		
		
	}
	
	
}
