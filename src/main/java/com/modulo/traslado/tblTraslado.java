package com.modulo.traslado;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.modulo.ajuste.tblAjuste;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.utilDate;
import com.modulo.componentes.utilString;
import com.modulo.deposito.tblDeposito;
import com.modulo.kardexdetallado.tblKardexDetallado;
import com.modulo.producto.tblProducto;
import com.modulo.productodeposito.tblProductoDeposito;
import com.byos.sys.conexion.Conexion;
import com.vaadin.data.util.IndexedContainer;

public class tblTraslado{

	public tblDeposito TblDepositoorigen = new tblDeposito();
	
	public tblDeposito TblDepositodestino = new tblDeposito();
	
	public tblTrasladoItem[] TblTrasladoItem;
	
	private Integer codigotraslado;
	
	private java.util.Date fechatraslado;
	
	private String depositoorigen;
	
	private String depositodestino;

	public tblTraslado(){
		limpiar();
	}
	
	public tblTrasladoItem[] getTblTrasladoItem() {
		return TblTrasladoItem;
	}

	public void setTblTrasladoItem(tblTrasladoItem tblTrasladoItem[]) {
		TblTrasladoItem = tblTrasladoItem;
	}

	public Integer getCodigotraslado() {
		return codigotraslado;
	}

	public void setCodigotraslado(Integer codigotraslado) {
		this.codigotraslado = codigotraslado;
	}	
	public java.util.Date getFechatraslado() {
		return fechatraslado;
	}
	public void setFechatraslado(java.util.Date fechatraslado) {
		this.fechatraslado = fechatraslado;
	}
	public String getDepositoorigen() {
		return depositoorigen;
	}
	public void setDepositoorigen(String depositoorigen) {
		this.depositoorigen = depositoorigen;
	}
	public String getDepositodestino() {
		return depositodestino;
	}
	public void setDepositodestino(String depositodestino) {
		this.depositodestino = depositodestino;
	}

	public tblDeposito getTblDepositoorigen() {
		return TblDepositoorigen;
	}

	public void setTblDepositoorigen(tblDeposito tblDepositoorigen) {
		TblDepositoorigen = tblDepositoorigen;
	}

	public tblDeposito getTblDepositodestino() {
		return TblDepositodestino;
	}

	public void setTblDepositodestino(tblDeposito tblDepositodestino) {
		TblDepositodestino = tblDepositodestino;
	}
	
    public void limpiar(){
    	setFechatraslado(null);
    	setDepositoorigen("");
    	setDepositodestino("");
    	setCodigotraslado(null);
    	TblTrasladoItem = null; 
    	TblDepositoorigen.limpiar();
    	TblDepositodestino.limpiar();
    }
    
    public boolean existeCodigo(Integer Codigo) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select codigotraslado from tbltraslado where codigotraslado=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setInt(1,Codigo);
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
    
    
    public void setTblTraslado(tblTraslado TblTraslado){
    	setFechatraslado(TblTraslado.getFechatraslado());
    	setDepositoorigen(TblTraslado.getDepositoorigen());
    	setDepositodestino(TblTraslado.getDepositodestino());
    	setCodigotraslado(TblTraslado.getCodigotraslado());
    	setTblTrasladoItem(TblTraslado.getTblTrasladoItem());
    	setTblDepositoorigen(TblTraslado.TblDepositoorigen);
    	setTblDepositodestino(TblTraslado.TblDepositodestino);
    }

    public String guardar(tblTraslado tbl){  
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   Connection con = Conexion.getNuevaConexion();	   
	   String query = "Insert into tblTraslado(fechatraslado,depositoorigen,depositodestino) values(?,?,?)";
	   try{
		   con.setAutoCommit(false);
		   pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	       pstmt.setTimestamp(1,utilDate.FechaHora());		  
	       pstmt.setString(2,tbl.getDepositoorigen());
	       pstmt.setString(3,tbl.getDepositodestino());		  
	       i = pstmt.executeUpdate();
	       int AutoCodigo = ByosSql.ObtenerAutonumerico(pstmt);
	       GuardarTrasladoItem(tbl,con,AutoCodigo); 
	       con.commit();
	       return utilString.SQL_INSERTADO;
	   }catch (SQLException e) {
		   e.printStackTrace();
	   }catch (Exception e1) {
		   e1.printStackTrace();
	   }		
	   ByosSql.RollBack(con);
       ByosSql.CloseAll(con,pstmt,rs);  
	   return utilString.SQL_ERROR;
    }
    
    public void GuardarTrasladoItem(tblTraslado tbl, Connection con, int AutoCodigo) throws Exception {
	   for(int f=0;f<tbl.TblTrasladoItem.length;f++){
		   tblKardexDetallado TblKardex = new tblKardexDetallado(); 
		   tblProducto TblProducto = new tblProducto();
		   tblProductoDeposito TblDepositoOrigen = new tblProductoDeposito();
		   tblProductoDeposito TblDepositoDestino = new tblProductoDeposito();
		   
		   TblProducto.buscarCodigo(tbl.TblTrasladoItem[f].getCodigoproducto(),con);
		   TblDepositoOrigen.buscarCodigo(tbl.getDepositoorigen(), tbl.TblTrasladoItem[f].getCodigoproducto(),con);
		   TblDepositoDestino.buscarCodigo(tbl.getDepositodestino(), tbl.TblTrasladoItem[f].getCodigoproducto(),con);
		   
		   tbl.TblTrasladoItem[f].setCodigotraslado(AutoCodigo);
		   tbl.TblTrasladoItem[f].setDepositodestino(tbl.getDepositodestino());
		   tbl.TblTrasladoItem[f].setDepositoorigen(tbl.getDepositoorigen());
		   tbl.TblTrasladoItem[f].setCosto(TblProducto.getCosto());
		   new tblTrasladoItem().guardar(tbl.TblTrasladoItem[f], con);
	       
	       TblKardex.setCodigooperecion(String.valueOf(AutoCodigo));
	       TblKardex.setCodigoproducto(tbl.TblTrasladoItem[f].getCodigoproducto());
	       TblKardex.setCodigomedida(tbl.TblTrasladoItem[f].getCodigomedida());
	       TblKardex.setCosto(tbl.TblTrasladoItem[f].getCosto());
	       TblKardex.setDescripcion(TblProducto.getDescripcioncorta());
	       
	       if(TblDepositoDestino.TblDeposito.getTipodeposito().equals(utilString.TIPO_DEPOSITO_PERDIDA)){
	    	  TblKardex.setTipooperacion(utilString.OPERACION_INVENTARIO_TRASLADO_PERDIA); 
	       }else{
	          TblKardex.setTipooperacion(utilString.OPERACION_INVENTARIO_TRASLADO);
	       }
	       
	       double cantidad = tbl.TblTrasladoItem[f].getCantidad().doubleValue()*tbl.TblTrasladoItem[f].getEquivalencia().doubleValue();
	       TblKardex.setCodigodeposito(tbl.getDepositoorigen());
	       TblKardex.setMovimiento(BigDecimal.valueOf(cantidad*-1));
	       TblKardex.setExistencia(BigDecimal.valueOf(TblProducto.getExistencia().doubleValue()-cantidad));
	       TblKardex.setExistenciadeposito(BigDecimal.valueOf(TblDepositoOrigen.getExistencia().doubleValue()-cantidad)); 
	       TblKardex.Movimiento(TblKardex, con);
	       
	       TblKardex.setCodigodeposito(tbl.getDepositodestino());
	       TblKardex.setMovimiento(BigDecimal.valueOf(cantidad));
	       TblKardex.setExistencia(TblProducto.getExistencia());
	       TblKardex.setExistenciadeposito(BigDecimal.valueOf(TblDepositoDestino.getExistencia().doubleValue()+cantidad)); 
	       TblKardex.Movimiento(TblKardex, con);	       

	   }    	
    }
    
    
    public ArrayList <tblTraslado> Buscar(buscarTraslado tbl) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
	   String Rango="";
	   ArrayList <tblTraslado> tblArray = new ArrayList<tblTraslado>(); 
	   
	   String query = "Select " +
				"a.codigotraslado," +
		        "a.fechatraslado," +
		        "a.depositoorigen," +
                "a.depositodestino " +
	            "from tbltraslado a, tbltrasladoitem b ";
		
	   if(tbl.getDepositoorigen()!=null &&!tbl.getDepositoorigen().equals("")){
		  InSql = InSql + " and a.depositoorigen like '" + tbl.getDepositoorigen().replace("*", "%") + "'";
		  Estado=1;
	   }
	   if(tbl.getDepositodestino()!=null &&!tbl.getDepositodestino().equals("")){
		  InSql = InSql + " and a.depositodestino like '" + tbl.getDepositodestino().replace("*", "%") + "'";
		  Estado=1;
	   }
	   
	   Rango="";
	   if(tbl.getCodigotraslado01()!=null){
		  Rango = " and a.Codigotraslado = '" + tbl.getCodigotraslado01() + "'";
		  Estado=1;
	   }
	   if(tbl.getCodigotraslado02()!=null){
		  Rango = " and a.Codigotraslado = '" + tbl.getCodigotraslado02() + "'";
		  Estado=1;
	   }
	   if(tbl.getCodigotraslado01()!=null && tbl.getCodigotraslado02()!=null){
		  Rango = " and a.codigotraslado >= '" + tbl.getCodigotraslado01() + "' and a.codigotraslado <= '" + tbl.getCodigotraslado02() + "' ";
		  Estado=1;
	   }	   
	   InSql = InSql + Rango;
	   
	   
	   Rango="";
	   if(tbl.getFechatraslado01()!=null){
		  Rango = " and a.fechatraslado >= '" + utilDate.FormatoFecha(tbl.getFechatraslado01(), "yyyy-MM-dd") + " 00:00:00' and a.fechatraslado <= '" + utilDate.FormatoFecha(tbl.getFechatraslado01(), "yyyy-MM-dd") + " 23:59:59' ";
		  Estado=1;
	   }
	   if(tbl.getFechatraslado02()!=null){
		  Rango = " and a.fechatraslado >= '" + utilDate.FormatoFecha(tbl.getFechatraslado02(), "yyyy-MM-dd") + " 00:00:00' and a.fechatraslado <= '" + utilDate.FormatoFecha(tbl.getFechatraslado02(), "yyyy-MM-dd") + " 23:59:59' ";
		  Estado=1;
	   }
	   if(tbl.getFechatraslado01()!=null && tbl.getFechatraslado02()!=null){
		  Rango = " and a.fechatraslado >= '" + utilDate.FormatoFecha(tbl.getFechatraslado01(), "yyyy-MM-dd") + " 00:00:00' and a.fechatraslado <= '" + utilDate.FormatoFecha(tbl.getFechatraslado02(), "yyyy-MM-dd") + " 23:59:59' ";
		  Estado=1;
	   }	   
	   InSql = InSql + Rango;
	   
	   if(tbl.getCodigoproducto()!=null && !tbl.getCodigoproducto().equals("")){
		  InSql = InSql + " and b.codigoproducto like '" + tbl.getCodigoproducto().replace("*", "%") + "'";
		  Estado=1;
	   }	   
	   if(tbl.getDescripcionproducto()!=null && !tbl.getDescripcionproducto().equals("")){
		  InSql = InSql + " and b.descripcionproducto like '" + tbl.getDescripcionproducto().replace("*", "%") + "'";
		  Estado=1;
	   }

	  
	   
       query = query + " Where a.codigotraslado=b.codigotraslado " + InSql;
	   
       query = query  + " group by 1,2,3,4 order by a.codigotraslado";
	  
       System.out.println(query);
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
	  	     size = rs.getRow();
		     rs.first(); 
			
		     for(int f=0;f<size;f++){
		    	 tblTraslado Registros = new tblTraslado();
                 Registros.setCodigotraslado(rs.getInt("codigotraslado")); 
                 Registros.setFechatraslado(rs.getDate("fechatraslado"));                    
		         Registros.setDepositoorigen(rs.getString("depositoorigen"));                    
                 Registros.setDepositodestino(rs.getString("depositodestino"));
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
    
    public boolean buscarCodigo(Integer Codigo) throws Exception{
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  if(Codigo!=null){
	     String query = "Select codigotraslado,fechatraslado,depositoorigen,depositodestino  from tbltraslado where codigotraslado=?";
		
	     Connection con = Conexion.getNuevaConexion();
	     pstmt = con.prepareStatement(query);
	     pstmt.setInt(1,Codigo);
	     rs = pstmt.executeQuery();
	     int size=0;
	
	     if(rs!=null){
	        if(rs.last()==true){
  	   	       size = rs.getRow();
               setCodigotraslado(rs.getInt("codigotraslado")); 
               setFechatraslado(rs.getDate("fechatraslado"));                    
	           setDepositoorigen(rs.getString("depositoorigen"));                    
               setDepositodestino(rs.getString("depositodestino"));
               TblDepositodestino.buscarCodigo(getDepositodestino());
               TblDepositoorigen.buscarCodigo(getDepositoorigen());
               ByosSql.CloseAll(con,pstmt,rs);
		       return true; 
            }	
         }
         limpiar();
         ByosSql.CloseAll(con,pstmt,rs);
	     return false;
	  }
	  return false;
    }
    
}
