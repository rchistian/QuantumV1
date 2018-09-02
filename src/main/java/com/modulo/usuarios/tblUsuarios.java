package com.modulo.usuarios;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;
import com.byos.sys.util.utilString;
import com.modulo.componentes.ByosSql;
import com.vaadin.data.util.IndexedContainer;

public class tblUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;

    private String Tabla = "TblUsuarios";
    public String Resultado; 
    public String GlobalCodigoEmpresa = "0001";

/*01*/	  public String CodigoEmpresa;
/*02*/    public Integer ID;
/*03*/    public String Codigo;
/*04*/    public String Descripcion;
/*05*/    public String Grupo;
/*06*/    public String Clave;
/*07*/    public String Nombres;
/*08*/    public String Tipo;
/*09*/    public String Estado;
/*10*/    public String EstadoRegistro;
/*11*/    public Date Fecha;
/*12*/    public String Usuario;
/*13*/    public String Login;

	public boolean Estatus;

    public String StringSelect =
/*01*/  "CodigoEmpresa," +
/*02*/  "ID," +
/*03*/  "Codigo," +
/*04*/  "Descripcion," +
/*05*/  "Grupo," +
/*06*/  "Clave," +
/*07*/  "Nombres," +
/*08*/  "Tipo," +
/*09*/  "Estado," +
/*10*/  "EstadoRegistro," +
/*11*/  "Fecha," +
/*12*/  "Usuario," +
/*13*/  "Login";
    
    public String StringSelectUpdate =
/*01*/  "CodigoEmpresa=?," +
/*02*/  "ID=?," +
/*03*/  "Codigo=?," +
/*04*/  "Descripcion=?," +
/*05*/  "Grupo=?," +
/*06*/  "Clave=?," +
/*07*/  "Nombres=?," +
/*08*/  "Tipo=?," +
/*09*/  "Estado=?," +
/*10*/  "EstadoRegistro=?," +
/*11*/  "Fecha=?," +
/*12*/  "Usuario=?," +
/*13*/  "Login=?";    

    
    public tblUsuarios() {
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

    public String getCodigo() {
    	return Codigo;
    }

    public void setCodigo(String codigo) {
    	Codigo = codigo;
    }

    public String getDescripcion() {
    	return Descripcion;
    }

    public void setDescripcion(String descripcion) {
    	Descripcion = descripcion;
    }

    public String getGrupo() {
    	return Grupo;
    }

    public void setGrupo(String grupo) {
    	Grupo = grupo;
    }

    public String getClave() {
    	return Clave;
    }

    public void setClave(String clave) {
    	Clave = clave;
    }

    public String getNombres() {
    	return Nombres;
    }

    public void setNombres(String nombres) {
    	Nombres = nombres;
    }

    public String getEstado() {
    	return Estado;
    }

    public void setEstado(String estado) {
    	Estado = estado;
    }

    public String getEstadoRegistro() {
    	return EstadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
    	EstadoRegistro = estadoRegistro;
    }

    public Date getFecha() {
    	return Fecha;
    }

    public void setFecha(Date fecha) {
    	Fecha = fecha;
    }

    public String getLogin() {
    	return Login;
    }

    public void setLogin(String login) {
    	Login = login;
    }

    public String getTipo() {
    	return Tipo;
    }

    public void setTipo(String tipo) {
    	Tipo = tipo;
    }
    
    public String getUsuario() {
    	return Usuario;
    }

    public void setUsuario(String usuario) {
    	Usuario = usuario;
    }
    
    public boolean buscarClave(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Select " + StringSelect + " from "  + Tabla + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' And EstadoRegistro='ACTIVO' And Clave=?" ;
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
    
    public boolean buscarCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String query = "Select " + StringSelect + " from "  + Tabla + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' And EstadoRegistro='ACTIVO' And Login=?" ;
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

	   String query = "Select Login From " + Tabla + " Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and Login=?";	
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
    
    public String eliminar(tblUsuarios tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(existeCodigo(tbl.getLogin())){
			  String query = "Update " + Tabla + " set EstadoRegistro='BORRADO'  Where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and Login=?";	
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
       
    public String guardar(tblUsuarios tbl) {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   
	   
	   try{
		   if(!existeCodigo(tbl.getLogin())){
			  String query = "Insert into " + Tabla + "(" + StringSelect + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  ActualizarDatos(tbl, pstmt);
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblUsuarios set " + StringSelectUpdate + " where CodigoEmpresa='" + GlobalCodigoEmpresa + "' and EstadoRegistro='ACTIVO' and Login='" + tbl.getLogin() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
			  ActualizarDatos(tbl, pstmt);
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

    public void ActualizarDatos(tblUsuarios tbl, PreparedStatement pstmt) throws SQLException {
	  	  pstmt.setString(1,GlobalCodigoEmpresa);
	 	  pstmt.setInt(2,tbl.ID);
	 	  pstmt.setString(3,tbl.Codigo);
	 	  pstmt.setString(4,tbl.Descripcion);
	 	  pstmt.setString(5,tbl.Grupo);
	 	  pstmt.setString(6,tbl.Clave);
	 	  pstmt.setString(7,tbl.Nombres);
	 	  pstmt.setString(8,tbl.Tipo);
	 	  pstmt.setString(9,tbl.Estado); 	 
	 	  pstmt.setString(10,"ACTIVO");
	 	  pstmt.setDate(11,tbl.Fecha);
	 	  pstmt.setString(12,tbl.Usuario);	 	 
	 	  pstmt.setString(13,tbl.Login);
    }
    
    public void setRegistro(ResultSet rs, tblUsuarios Registros) throws Exception {
        Registros.setCodigoEmpresa(GlobalCodigoEmpresa);
        Registros.setID(rs.getInt("ID")); 
        Registros.setCodigo(rs.getString("Codigo")); 
        Registros.setDescripcion(rs.getString("Descripcion"));
        Registros.setGrupo(rs.getString("Grupo"));
        Registros.setClave(rs.getString("Clave"));
        Registros.setNombres(rs.getString("Nombres"));
        Registros.setTipo(rs.getString("Tipo"));
        Registros.setEstado(rs.getString("Estado"));
        Registros.setEstadoRegistro(rs.getString("EstadoRegistro"));
        Registros.setFecha(rs.getDate("Fecha"));
        Registros.setUsuario(rs.getString("Usuario"));
        Registros.setLogin(rs.getString("Login"));
    }
          
    public ArrayList <tblUsuarios> BuscarArray(tblUsuarios tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
       String InSql = StringInSql(tbl);
	   ArrayList <tblUsuarios> tblArray = new ArrayList(); 
	   tblUsuarios[] Registros; 
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(InSql);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
			 size = rs.getRow();
			 Registros = new tblUsuarios[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblUsuarios();
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
  
    public ArrayList <tblUsuarios> BuscarArray(String Texto, String Numero) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
       String InSql = StringInSql(Texto,Numero);
	   ArrayList <tblUsuarios> tblArray = new ArrayList(); 
	   tblUsuarios[] Registros; 
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(InSql);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
			 size = rs.getRow();
			 Registros = new tblUsuarios[size];
		     rs.first(); 			
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblUsuarios();
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
    
    public tblUsuarios[] Buscar(tblUsuarios tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   tblUsuarios[] Registros; 
	   
	   String InSql = StringInSql(tbl);
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(InSql);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
		     size = rs.getRow();
		     Registros = new tblUsuarios[size];
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
    
    public String StringInSql(tblUsuarios tbl) {
 	   String InSql="";
 	   boolean Estado=false;
 	   
       if(tbl.getNombres()!=null &&!tbl.getNombres().equals("")){
 		  InSql = InSql + " and Nombres like '" + tbl.getNombres().replace("*", "%") + "'";
 		  Estado=true;
 	   }
       if(tbl.getGrupo()!=null &&!tbl.getGrupo().equals("")){
 		  InSql = InSql + " and Grupo like '" + tbl.getGrupo().replace("*", "%") + "'";
 		  Estado=true;
 	   }
       if(tbl.getDescripcion()!=null &&!tbl.getDescripcion().equals("")){
 		  InSql = InSql + " and Descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
 		  Estado=true;
 	   }
       if(tbl.getLogin()!=null && !tbl.getLogin().equals("")){
 		  InSql = InSql + " and Login like '" + tbl.getLogin().replace("*", "%") + "'";
 		  Estado=true;
 	   }
 	   if(tbl.getUsuario()!=null && !tbl.getUsuario().equals("")){
 		  InSql = InSql + " and Usuario like '" + tbl.getUsuario().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getFecha()!=null && !tbl.getFecha().equals("")){
 		  //InSql = InSql + " and Fecha like '" + tbl.getFecha().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getEstadoRegistro()!=null && !tbl.getEstadoRegistro().equals("")){
 		  InSql = InSql + " and EstadoRegistro like '" + tbl.getEstadoRegistro().replace("*", "%") + "'";
 		  Estado=true;
 	   }	   
 	   if(tbl.getEstado()!=null && !tbl.getEstado().equals("")){
 		  InSql = InSql + " and Estado like '" + tbl.getEstado().replace("*", "%") + "'";
 		  Estado=true;
 	   }

 	   String query = "Select " + StringSelect + " " +
	       		  "from " + Tabla + " Where CodigoEmpresa = '" + GlobalCodigoEmpresa + 
	       		                         "' and EstadoRegistro='ACTIVO' ";	   
       if(Estado){
          query = query + " " + InSql;
       }
       query = query  + " order by Login";
       System.out.println(query);
 	   
 	   
 	   return query;
    }
   
    
    public String StringInSql(String Texto, String Numero) {
  	   String InSql="";
  	   boolean Estado=false;
  	   
  	   Texto=Texto.toUpperCase();
  	   if(!Texto.equals("")) {
  	      InSql = InSql + " and (Nombres like '%" + Texto + "%'";
  	      InSql = InSql + " or Grupo like '%" + Texto + "%'";
  	      InSql = InSql + " or Descripcion like '%" + Texto + "%'";
  	      InSql = InSql + " or Login like '%" + Texto + "%'";
  	      InSql = InSql + " or Usuario like '%" + Texto + "%'";	   
  	      InSql = InSql + " or EstadoRegistro like '%" + Texto + "%'";
  	      InSql = InSql + " or Estado like '%" + Texto + "%') ";
  	      Estado=true;
  	   }
  	   String query = "Select " + StringSelect + " " +
 	       		  "from " + Tabla + " Where CodigoEmpresa = '" + GlobalCodigoEmpresa + 
 	       		                         "' and EstadoRegistro='ACTIVO' ";	   
       if(Estado){
           query = query + InSql;
       }
       query = query  + " order by Login";
       System.out.println(query);
  	   
  	   
  	   return query;
     }

    
    
    
    /*
    public IndexedContainer getUsuariosContainer(){ 
       tblUsuarios[] TblUsuarios=null;
       IndexedContainer Container = new IndexedContainer();
       try{
		   TblUsuarios = new tblUsuarios().Buscar(new tblUsuarios());
	   }catch (Exception e) {
	   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
       if(TblUsuarios!=null && TblUsuarios.length>0){
    	  Container.addItem(""); 
          for(int f=0;f<TblUsuarios.length;f++){
              Container.addItem(TblUsuarios[f].getID() + "," + TblUsuarios[f].getLogin() + TblUsuarios[f].getNombres() + "," + TblUsuarios[f].getDescripcion());  
          }	
       }
       return Container;  
    }
   */ 
    public void limpiar(){
        setCodigoEmpresa("");
        setID(null);
        setCodigo("");
        setNombres("");
        setDescripcion("");
        setTipo("");
        setLogin("");
        setUsuario("");
        setFecha(null);
        setEstadoRegistro("");
        setEstado("");
        setGrupo("");
        setClave("");
    }   
    
    public void setTblUsuarios(tblUsuarios Usuarios){
        setCodigoEmpresa(Usuarios.getCodigoEmpresa());
        setCodigo(Usuarios.getCodigo()); 
        setID(Usuarios.getID()); 
        setNombres(Usuarios.getNombres());
        setLogin(Usuarios.getLogin());
        setUsuario(Usuarios.getUsuario());
        setFecha(Usuarios.getFecha());
        setEstadoRegistro(Usuarios.getEstadoRegistro());
        setEstado(Usuarios.getEstado());
        setGrupo(Usuarios.getGrupo());
        setClave(Usuarios.getClave());
        setDescripcion(Usuarios.getDescripcion());
        setTipo(Usuarios.getTipo());
    }   

}
