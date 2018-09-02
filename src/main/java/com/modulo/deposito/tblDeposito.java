package com.modulo.deposito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.modulo.ajuste.tblAjuste;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;
import com.modulo.departamento.tblDepartamento;
import com.modulo.kardexdetallado.tblKardexDetallado;
import com.modulo.kardexdiario.tblKardexDiario;
import com.modulo.producto.tblProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.byos.sys.conexion.Conexion;


/**
 * The persistent class for the tbldepositos database table.
 * 
 */

public class tblDeposito implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigodeposito;

    private String descripcion;

    private Integer id;

    private String responsable;

    private String tipodeposito;

    public tblDeposito() {
       limpiar();
    }

    public String getCodigodeposito() {
	   return this.codigodeposito;
    }

    public void setCodigodeposito(String codigodeposito) {
	   this.codigodeposito = codigodeposito;
    }

    public String getDescripcion() {
	   return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
	   this.descripcion = descripcion;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }

    public String getResponsable() {
	   return this.responsable;
    }

    public void setResponsable(String responsable) {
	   this.responsable = responsable;
    }

    public String getTipodeposito() {
	   return this.tipodeposito;
    }

    public void setTipodeposito(String tipodeposito){
 	   this.tipodeposito = tipodeposito;
    }
        
    public boolean buscarCodigo(String Codigo) throws Exception{
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  String query = "Select id,codigodeposito,descripcion,tipodeposito,responsable from tbldepositos where codigodeposito=?";
	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,Codigo);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs!=null){
	     if(rs.last()==true){
  		    size = rs.getRow();
		    setId(rs.getInt("id"));
		    setCodigodeposito(rs.getString("codigodeposito"));
            setDescripcion(rs.getString("descripcion"));
            setTipodeposito(rs.getString("tipodeposito"));
            setResponsable(rs.getString("responsable")); 
            ByosSql.CloseAll(con,pstmt,rs);
		    return true; 
         }	
      }
      limpiar();
      ByosSql.CloseAll(con,pstmt,rs);
	  return false;
    }
    

    
    public boolean existeCodigo(String Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigodeposito from tblDepositos where Codigodeposito=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,Codigo);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs != null){
	     if(rs.last()==true){
		    size = rs.getRow(); 
		    ByosSql.CloseAll(con,pstmt,rs);
		    return true;
	     }
       }
	   ByosSql.CloseAll(con,pstmt,rs);
	   return false;
    }  
    
    public String eliminar(tblDeposito tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();	
	   //Ingresar nuevo
	   try{
		   if(existeCodigo(tbl.getCodigodeposito())){
		  	  String query = "Delete From tblDepositos Where id='" + tbl.getId() + "'";
			  con.setAutoCommit(false);
			  pstmt = con.prepareStatement(query);
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);
		      return utilString.SQL_ELIMINADO;
		   }
	   }catch(SQLException e){	
		   e.printStackTrace();
	   }catch(Exception e) {	
		   e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;
    }
       
    public String guardar(tblDeposito tbl){
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();
	   try{
		   if(!existeCodigo(tbl.getCodigodeposito())){
		      String query = "Insert into tblDepositos(codigodeposito, descripcion, tipodeposito, responsable) values(?,?,?,?)";
		      con.setAutoCommit(false);
		      pstmt = con.prepareStatement(query);
		      pstmt.setString(1,tbl.getCodigodeposito());
		      pstmt.setString(2,tbl.getDescripcion());
		      pstmt.setString(3,tbl.getTipodeposito());
		      pstmt.setString(4,tbl.getResponsable());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);       
		      return utilString.SQL_INSERTADO;
		   }
		   else{
		      String query = "Update tblDepositos set descripcion=?, tipodeposito=?, responsable=? where id='" + tbl.getId() + "'";
		      con.setAutoCommit(false);
		      pstmt = con.prepareStatement(query);			
		      pstmt.setString(1,tbl.getDescripcion());
		      pstmt.setString(2,tbl.getTipodeposito());
		      pstmt.setString(3,tbl.getResponsable());
		      i = pstmt.executeUpdate();
		      con.commit();
		      ByosSql.CloseAll(con,pstmt,rs);	   
		      return utilString.SQL_MODIFICADO;
		   }
	
	   }catch(SQLException e){
		   e.printStackTrace();	
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);	   
	   return utilString.SQL_ERROR;	
    }
    
    
      
    public ArrayList <tblDeposito> Buscar(tblDeposito tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   ArrayList <tblDeposito> tblArray = new ArrayList(); 
	   
	   String query = "Select " +
	   		"id, " +
	   		"codigodeposito, " +
	   		"descripcion, " +
	   		"tipodeposito, " +
	   		"responsable " +
	   		"from tblDepositos ";
	   if(tbl.getCodigodeposito()!=null &&!tbl.getCodigodeposito().equals("")){
		  InSql = InSql + " and codigodeposito like '" + tbl.getCodigodeposito().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDescripcion()!=null && !tbl.getDescripcion().equals("")){
		  InSql = InSql + " and descripcion like '" + tbl.getDescripcion().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getTipodeposito()!=null && !tbl.getTipodeposito().equals("")){
		  InSql = InSql + " and tipodeposito like '" + tbl.getTipodeposito().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getResponsable()!=null && !tbl.getResponsable().equals("")){
		  InSql = InSql + " and responsable like '" + tbl.getResponsable().replace("*", "%") + "'";
		  Estado=1;
	   }
	  
	   if(Estado==1){
          query = query + " Where 1=1 " + InSql;
	   }
       query = query  + " order by descripcion";
	  
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
	  	     size = rs.getRow();
		     rs.first(); 
			
		     for(int f=0;f<size;f++){
		    	 tblDeposito Registros = new tblDeposito();
                 Registros.setId(rs.getInt("id")); 
                 Registros.setCodigodeposito(rs.getString("codigodeposito"));                    
		         Registros.setDescripcion(rs.getString("descripcion"));                    
                 Registros.setTipodeposito(rs.getString("tipodeposito"));
		         Registros.setResponsable(rs.getString("responsable"));
		         tblArray.add(Registros);
		         rs.next();
		     }
		     ByosSql.CloseAll(con,pstmt,rs);	   
		     return tblArray;	   
          }
	   } 
	   ByosSql.CloseAll(con,pstmt,rs);	   
	   return null;
    }
   
    public void limpiar(){
       setId(0);
       setCodigodeposito("");
       setDescripcion("");
       setTipodeposito("");
       setResponsable("");       
    }   
    
    public void setDeposito(tblDeposito Deposito){
       setId(Deposito.getId());
       setCodigodeposito(Deposito.getCodigodeposito());
       setDescripcion(Deposito.getDescripcion());
       setResponsable(Deposito.getResponsable());
       setTipodeposito(Deposito.getTipodeposito());
    } 
   /* 
    public String Ajustar(tblProductoDeposito[] TblProductoDeposito, tblProducto TblProducto){
    	double existencia=0;
    	Connection con = Conexion.getNuevaConexion();
    	try{
    	   con.setAutoCommit(false);

    	   tblKardexDetallado TblKardex = new tblKardexDetallado();
    	   tblProducto TblProductoAux = new tblProducto();
    	   TblProductoAux.buscarCodigo(TblProducto.getCodigoproducto());
    	   double ExistenciaTotal = 0;
    	   if(TblProductoAux.getExistencia()!=null){
    	      ExistenciaTotal = TblProductoAux.getExistencia().doubleValue();
    	   }
    	   for(int f=0;f<TblProductoDeposito.length;f++){
    		   double ExistenciaDeposito=0;
    		   TblProductoDeposito[f].setExistencia(TblProductoDeposito[f].getExistenciareal());
		   	   new tblProductoDeposito().guardar(TblProductoDeposito[f], 0, con);
		   	   tblProductoDeposito TblProductoDepositoAux = new  tblProductoDeposito();
		   	   if(TblProductoDepositoAux.buscarCodigo(TblProductoDeposito[f].getCodigodeposito(), TblProductoDeposito[f].getCodigoproducto())){
		   		  ExistenciaDeposito=TblProductoDepositoAux.getExistencia().doubleValue();
		   	   }
			   existencia+=TblProductoDeposito[f].getExistenciareal().doubleValue();
			   
    	       //double entradasalida = existencia - TblProducto.getExistencia().doubleValue();
    	       double entradasalida = TblProductoDeposito[f].getExistenciareal().doubleValue() - ExistenciaDeposito;
    	       ExistenciaTotal = ExistenciaTotal + entradasalida;
    	       
    	       
	           if(TblProductoDepositoAux.getExistencia().doubleValue()!=TblProductoDeposito[f].getExistenciareal().doubleValue()){
	        	  tblAjuste TblAjustes = new tblAjuste();
		          TblAjustes.setCodigoproducto(TblProducto.getCodigoproducto());
		          TblAjustes.setCodigodeposito(TblProductoDeposito[f].getCodigodeposito());
		          TblAjustes.setCodigomedida(TblProducto.getCodigomedida());
		          TblAjustes.setCosto(TblProducto.getCosto());
		          TblAjustes.setExistenciainicial(TblProductoDepositoAux.getExistencia());
		          TblAjustes.setExistenciafinal(TblProductoDeposito[f].getExistenciareal());
		          TblAjustes.setCantidadajustada(BigDecimal.valueOf(entradasalida));
		          TblAjustes.guardar(TblAjustes, con);
    	       
	              TblKardex.setCodigoproducto(TblProducto.getCodigoproducto());
	              TblKardex.setCodigomedida(TblProducto.getCodigomedida());
	              TblKardex.setTipooperacion(utilString.OPERACION_INVENTARIO_AJUSTE);
	              TblKardex.setFechaoperacion(utilDate.Fecha());
	              TblKardex.setCosto(TblProducto.getCosto());
	              TblKardex.setCantidad(BigDecimal.valueOf(entradasalida));
	              TblKardex.setExistencia(BigDecimal.valueOf(ExistenciaTotal));
	              TblKardex.setExistenciadeposito(TblProductoDeposito[f].getExistenciareal());
	              if(TblAjustes.getAutocodigo()!=null && TblAjustes.getAutocodigo()>=0){
	                 TblKardex.setCodigooperecion(String.valueOf(TblAjustes.getAutocodigo()));
	              }else{
	        	     TblKardex.setCodigooperecion("0"); 
	              }
	              TblKardex.guardar(TblKardex, con);
	           } 

	           
    	   }
    	   double entradasalida = existencia - TblProducto.getExistencia().doubleValue();
           tblKardexDiario TblKardexDiario = new tblKardexDiario();
           TblKardexDiario.setCodigoproducto(TblProducto.getCodigoproducto());
           TblKardexDiario.setDescripcion(TblProducto.getDescripcioncorta());
           TblKardexDiario.setSalida(BigDecimal.valueOf(0));
           TblKardexDiario.setEntrada(BigDecimal.valueOf(0));
           TblKardexDiario.setCodigomedida(TblProducto.getCodigomedida());
           TblKardexDiario.setCosto(TblProducto.getCosto());
           if(entradasalida>=0){
              TblKardexDiario.setEntrada(BigDecimal.valueOf(entradasalida));
           }else{
              TblKardexDiario.setSalida(BigDecimal.valueOf(entradasalida*-1));
           }   
           TblKardexDiario.setExistencia(BigDecimal.valueOf(TblProducto.getExistencia().doubleValue()+entradasalida));
           TblKardexDiario.setFecha(utilDate.Fecha());
           TblKardexDiario.guardar(TblKardexDiario, \ con);    	   
	       new tblProducto().Ajustar(TblProducto.getCodigoproducto(), BigDecimal.valueOf(existencia), 0, con);
	       con.commit();
		}catch (Exception e) {
		   try{
			  con.rollback();
		   }catch (SQLException e1) {
			  e1.printStackTrace();
			  return utilString.SQL_ERROR;
		   }
		   e.printStackTrace();
		   return utilString.SQL_ERROR;
		}
    	return utilString.SQL_MODIFICADO;
    }
    */

    
}