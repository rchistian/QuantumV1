package com.modulo.huesped;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;
import com.modulo.componentes.ByosImagenes;
import com.modulo.componentes.ByosSql;
import com.mysql.jdbc.Blob;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;

public class tblImagenHuespedes   implements Serializable {
    private static final long serialVersionUID = 1L;

    private String Tabla = "TblImagenHuespedes";
    public String Resultado; 
    public String GlobalCodigoEmpresa = "0001";


	public String CodigoEmpresa;
    public Integer ID;
    public Integer IDHuesped;
	public String TipoDocumento;
    public String NumeroDocumento;
    public Image Imagen;
    public Date Fecha;
    public String Usuario;
    public String EstadoRegistro;
    public Integer Item;

    public boolean Estatus;

    public String StringSelect =
    	          "CodigoEmpresa," +
    	          "ID," +
    	          "IDHuesped," +
    	          "TipoDocumento," +
    	          "NumeroDocumento," +
    	          "Imagen," +
    	          "Fecha," +
    	          "Usuario," +
    	          "EstadoRegistro," +
    	          "Item";
    
    public String StringSelectUpdate =
	              "CodigoEmpresa=?," +
	              "ID=?," +
	              "IDHuesped=?," +
	              "TipoDocumento=?," +
	              "NumeroDocumento=?," +
	              "Imagen=?," +
	              "Fecha=?," +
	              "Usuario=?," +
	              "EstadoRegistro=?," +
	              "Item=?";
    
    public Integer getIDHuesped() {
		return IDHuesped;
	}

	public void setIDHuesped(Integer iDHuesped) {
		IDHuesped = iDHuesped;
	}
    
    public tblImagenHuespedes() {
       limpiar();
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

	public String getTipoDocumento() {
		return TipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return NumeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		NumeroDocumento = numeroDocumento;
	}

	public Image getImagen() {
		return Imagen;
	}

	public void setImagen(Image imagen) {
		Imagen = imagen;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getEstadoRegistro() {
		return EstadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		EstadoRegistro = estadoRegistro;
	}

	public Integer getItem() {
		return Item;
	}

	public void setItem(Integer item) {
		Item = item;
	}

	public boolean isEstatus() {
		return Estatus;
	}

	public void setEstatus(boolean estatus) {
		Estatus = estatus;
	}

    
    public boolean buscarCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Select " + StringSelect + " from "  + Tabla + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' And NumeroDocumento=?" ;
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

	   String query = "Select NumeroDocumento From "  + Tabla + " Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and NumeroDocumento=?";	
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
    
    public String eliminar(tblImagenHuespedes tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(existeCodigo(tbl.getNumeroDocumento())){
			  String query = "Delete From "  + Tabla + " Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and NumeroDocumento=?";	
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
       
    public String guardar(tblImagenHuespedes tbl) {
	   Blob BlobImagen = null;
       int i=0;	

	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();

	   
	   try{
		   if(!existeCodigo(tbl.getNumeroDocumento())){
			  String query = "Insert into " + Tabla + "(" + StringSelect + ") values(?,?,?,?,?,?,?,?,?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
		  	  pstmt.setString(1,tbl.CodigoEmpresa);
		 	  pstmt.setInt(2,tbl.ID);
		 	  pstmt.setInt(3,tbl.IDHuesped); 
		 	  pstmt.setString(4,tbl.TipoDocumento); 
		 	  pstmt.setString(5,tbl.NumeroDocumento); 
		 	  pstmt.setBlob(6,BlobImagen); 
		 	  pstmt.setDate(7,tbl.Fecha);
		 	  pstmt.setString(8,tbl.Usuario);		 	  
		 	  pstmt.setString(9,tbl.EstadoRegistro);
		 	  pstmt.setInt(10,tbl.Item);
              
		 	  
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblImagenHuespedes set " + StringSelectUpdate + " where ID='" + tbl.getID() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
		  	  pstmt.setString(1,tbl.CodigoEmpresa);
		 	  pstmt.setInt(2,tbl.ID);
		 	  pstmt.setInt(3,tbl.IDHuesped); 
		 	  pstmt.setString(4,tbl.TipoDocumento); 
		 	  pstmt.setString(5,tbl.NumeroDocumento); 
		 	  pstmt.setBlob(6,BlobImagen); 
		 	  pstmt.setDate(7,tbl.Fecha);
		 	  pstmt.setString(8,tbl.Usuario);		 	  
		 	  pstmt.setString(9,tbl.EstadoRegistro);
		 	  pstmt.setInt(10,tbl.Item);

			  i = pstmt.executeUpdate();
			  con.commit();
			  ByosSql.CloseAll(con,pstmt,rs);
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
    
    public void setRegistro(ResultSet rs, tblImagenHuespedes Registros) throws Exception {
        Registros.setCodigoEmpresa(rs.getString("CodigoEmpresa"));
        Registros.setID(rs.getInt("ID")); 
        Registros.setNumeroDocumento(rs.getString("NumeroDocumento"));
        Registros.setTipoDocumento(rs.getString("TipoDocumento"));
        Registros.setFecha(rs.getDate("Fecha"));
        Registros.setEstadoRegistro(rs.getString("EstadoRegistro")); 	
    }
          
    public ArrayList <tblImagenHuespedes> BuscarArray(tblImagenHuespedes tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblImagenHuespedes> tblArray = new ArrayList(); 
	   tblImagenHuespedes[] Registros; 
	   
	   String query = "Select " + StringSelect + " " +
	   		"from " + Tabla + " ";
	   
	   InSql = StringInSql(InSql,tbl,Estado);  
	   
	   if(Estado==1){
          query = query + " Where 1=1 " + InSql;
	   }
       query = query  + " order by Apodo";
	  
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
			 size = rs.getRow();
			 Registros = new tblImagenHuespedes[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblImagenHuespedes();
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
    
    public String StringInSql(String InSqlOrigen, tblImagenHuespedes tbl, int Estado) {
 	   String InSql;
       
 	   InSql=InSqlOrigen;       
 	   if(tbl.getNumeroDocumento()!=null && !tbl.getNumeroDocumento().equals("")){
 		  InSql = InSql + " and NumeroDocumento like '" + tbl.getNumeroDocumento().replace("*", "%") + "'";
 		  Estado=1;
 	   }       
 	   if(tbl.getNumeroDocumento()!=null && !tbl.getNumeroDocumento().equals("")){
 		  InSql = InSql + " and NumeroDocumento like '" + tbl.getNumeroDocumento().replace("*", "%") + "'";
 		  Estado=1;
 	   }
 	   if(tbl.getTipoDocumento()!=null && !tbl.getTipoDocumento().equals("")){
 		  InSql = InSql + " and TipoDocumento like '" + tbl.getTipoDocumento().replace("*", "%") + "'";
 		  Estado=1;
 	   }
 	   if(tbl.getUsuario()!=null && !tbl.getUsuario().equals("")){
 		  InSql = InSql + " and Usuario like '" + tbl.getUsuario().replace("*", "%") + "'";
 		  Estado=1;
 	   }	   
 	   if(tbl.getFecha()!=null && !tbl.getFecha().equals("")){
 		  //InSql = InSql + " and Fecha like '" + tbl.getFecha().replace("*", "%") + "'";
 		  Estado=1;
 	   }	   
 	   if(tbl.getEstadoRegistro()!=null && !tbl.getEstadoRegistro().equals("")){
 		  InSql = InSql + " and EstadoRegistro like '" + tbl.getEstadoRegistro().replace("*", "%") + "'";
 		  Estado=1;
 	   }	   
 	   
 	   return InSql;
    }
    	
    public IndexedContainer getImagenHuespedesContainer(){ 
       tblImagenHuespedes[] TblImagenHuespedes=null;
       IndexedContainer Container = new IndexedContainer();
       try{
		   TblImagenHuespedes = new tblImagenHuespedes().Buscar(new tblImagenHuespedes());
	   }catch (Exception e) {
	   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
       if(TblImagenHuespedes!=null && TblImagenHuespedes.length>0){
    	  Container.addItem(""); 
          for(int f=0;f<TblImagenHuespedes.length;f++){
              Container.addItem(TblImagenHuespedes[f].getID() + "," + TblImagenHuespedes[f].getNumeroDocumento() + "," + TblImagenHuespedes[f].getCodigoEmpresa());  
          }	
       }
       return Container;  
    }

    public tblImagenHuespedes[] Buscar(tblImagenHuespedes tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   tblImagenHuespedes[] Registros; 
	   
	   String query = "Select " + StringSelect + " " +
		   		"from " + Tabla + " ";
		   
	   InSql = StringInSql(InSql,tbl,Estado);  
		   
	   if(Estado==1){
	       query = query + " Where 1=1 " + InSql;
	   }
	   query = query  + " order by Item";
	  
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     Registros = new tblImagenHuespedes[size];
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
    
    public void limpiar(){
        setCodigoEmpresa("");
        setID(null); 
        setIDHuesped(null);
        setTipoDocumento("");
        setNumeroDocumento("");        
        setImagen(null);               
        setFecha(null);
        setUsuario("");
        setEstadoRegistro("");
        setItem(null);
    }   
    
    public void setTblImagenHuespedes(tblImagenHuespedes ImagenHuespedes){
        setCodigoEmpresa(ImagenHuespedes.getCodigoEmpresa());
        setID(ImagenHuespedes.getID()); 
        setIDHuesped(ImagenHuespedes.getIDHuesped());
        setNumeroDocumento(ImagenHuespedes.getNumeroDocumento());
        setTipoDocumento(ImagenHuespedes.getTipoDocumento());
        setImagen(ImagenHuespedes.getImagen()); 
        setUsuario(ImagenHuespedes.getUsuario());
        setFecha(ImagenHuespedes.getFecha());
        setEstadoRegistro(ImagenHuespedes.getEstadoRegistro()); 
        setItem(ImagenHuespedes.getItem());
    }   

}

