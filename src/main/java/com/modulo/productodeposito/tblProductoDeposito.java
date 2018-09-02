package com.modulo.productodeposito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.modulo.ajuste.tblAjuste;
import com.modulo.componentes.ByosSql;
import com.modulo.componentes.ByosConversores;
import com.modulo.componentes.utilString;
import com.modulo.deposito.tblDeposito;
import com.modulo.kardexdetallado.tblKardexDetallado;
import com.modulo.producto.tblProducto;
import com.byos.sys.conexion.Conexion;




public class tblProductoDeposito implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public tblDeposito TblDeposito = new tblDeposito();
    
    private Integer id;
    
    private String codigodeposito;

    private String codigoproducto;

    private BigDecimal existencia;
    
    private BigDecimal existenciareal;
    
    private BigDecimal existenciaoriginal;

    
	public BigDecimal getExistenciaoriginal() {
	    if(this.existenciaoriginal==null){
		   return BigDecimal.valueOf(0); 
		}	
		return existenciaoriginal;
	}

	public void setExistenciaoriginal(BigDecimal existenciaoriginal) {	
		this.existenciaoriginal = existenciaoriginal;
	}

	public BigDecimal getExistenciareal() {
	    if(this.existenciareal==null){
	       return BigDecimal.valueOf(0); 
	    }		
		return existenciareal;
	}

	public void setExistenciareal(BigDecimal existenciareal) {
		this.existenciareal = existenciareal;
	}

    public tblProductoDeposito() {
       limpiar();
    }

    public String getCodigodeposito() {
	   return this.codigodeposito;
    }

    public void setCodigodeposito(String codigodeposito) {
	   this.codigodeposito = codigodeposito;
    }

    public String getCodigoproducto() {
	   return this.codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
	   this.codigoproducto = codigoproducto;
    }

    public int getId() {
	   return this.id;
    }

    public void setId(int id) {
	   this.id = id;
    }

    public BigDecimal getExistencia() {
       if(this.existencia==null){
    	  return BigDecimal.valueOf(0);  
       }
	   return this.existencia;
    }

    public void setExistencia(BigDecimal existencia) {
	   this.existencia = existencia;
    }
    
    public String getDescripcion(){
    	return TblDeposito.getDescripcion();
    }
        
    public boolean buscarCodigo(String CodigoDeposito, String CodigoProducto) throws Exception{
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  String query = "Select id,codigodeposito,codigoproducto,existencia from tblproductodeposito where codigodeposito=? and codigoproducto=?";
	  Connection con = Conexion.getNuevaConexion();
	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,CodigoDeposito);
	  pstmt.setString(2,CodigoProducto);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs!=null){
	     if(rs.last()==true){
  		    size = rs.getRow();
  		    setId(rs.getInt("id"));
		    setCodigodeposito(rs.getString("codigodeposito"));
            setCodigoproducto(rs.getString("codigoproducto"));
            setExistencia(rs.getBigDecimal("existencia"));
            setExistenciareal(rs.getBigDecimal("existencia"));
            setExistenciaoriginal(rs.getBigDecimal("existencia"));
            TblDeposito.buscarCodigo(getCodigodeposito());
            ByosSql.CloseAll(con,pstmt,rs);
		    return true; 
         }	
      }
      limpiar();
      ByosSql.CloseAll(con,pstmt,rs);
	  return false;
    }

    public boolean buscarCodigo(String CodigoDeposito, String CodigoProducto, Connection con) throws Exception{
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  String query = "Select id,codigodeposito,codigoproducto,existencia from tblproductodeposito where codigodeposito=? and codigoproducto=?";

	  pstmt = con.prepareStatement(query);
	  pstmt.setString(1,CodigoDeposito);
	  pstmt.setString(2,CodigoProducto);
	  rs = pstmt.executeQuery();
	  int size=0;
	
	  if(rs!=null){
	     if(rs.last()==true){
  		    size = rs.getRow();
  		    setId(rs.getInt("id"));
		    setCodigodeposito(rs.getString("codigodeposito"));
            setCodigoproducto(rs.getString("codigoproducto"));
            setExistencia(rs.getBigDecimal("existencia"));
            setExistenciareal(rs.getBigDecimal("existencia"));
            setExistenciaoriginal(rs.getBigDecimal("existencia"));
            TblDeposito.buscarCodigo(getCodigodeposito());
		    return true; 
         }	
      }
      limpiar();
	  return false;
    }
    
    boolean existeCodigo(String CodigoDeposito, String CodigoProducto) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigodeposito,Codigoproducto from tblProductoDeposito where codigodeposito=? and codigoproducto=?";
	
	   Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,CodigoDeposito);
	   pstmt.setString(2,CodigoProducto);
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
    
    boolean existeCodigo(String CodigoDeposito, String CodigoProducto, Connection con) throws Exception{
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   String query = "select Codigodeposito,Codigoproducto from tblProductoDeposito where codigodeposito=? and codigoproducto=?";
	
	   pstmt = con.prepareStatement(query);
	   pstmt.setString(1,CodigoDeposito);
	   pstmt.setString(2,CodigoProducto);
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs != null){
	     if(rs.last()==true){
		    size = rs.getRow(); 
		    return true;
	     }
       }
	   return false;
    }  
    
    public String eliminar(tblProductoDeposito tbl) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   if(existeCodigo(tbl.getCodigodeposito(),tbl.getCodigoproducto())){
	  	  String query = "Delete From tblProductoDeposito Where Codigodeposito='" + tbl.getCodigodeposito() + "' and CodigoProducto='" + tbl.getCodigoproducto() + "'";
		  Connection con = Conexion.getNuevaConexion();
		  con.setAutoCommit(false);
		  pstmt = con.prepareStatement(query);
          i = pstmt.executeUpdate();
          con.commit();
          ByosSql.CloseAll(con,pstmt,rs);
	      return utilString.SQL_ELIMINADO;
	   }
	   return utilString.SQL_ERROR;
    }
       
    public String guardar(tblProductoDeposito tbl, Connection con) throws Exception {
	   int i=0;	
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   if(!existeCodigo(tbl.getCodigodeposito(),tbl.getCodigoproducto(), con)){
		  String query = "Insert into tblProductoDeposito(codigodeposito,codigoproducto,existencia) values(?,?,?)";
	      con.setAutoCommit(false);
	      pstmt = con.prepareStatement(query);
	      pstmt.setString(1,tbl.getCodigodeposito());
	      pstmt.setString(2,tbl.getCodigoproducto());
	      pstmt.setBigDecimal(3,tbl.getExistencia());
          i = pstmt.executeUpdate();     
	      return utilString.SQL_INSERTADO;
	   }
	   else{
		  String query; 
		  query = "Update tblProductoDeposito set existencia=? where Codigodeposito='" + tbl.getCodigodeposito() + "' and CodigoProducto='" + tbl.getCodigoproducto() + "'";  
	      con.setAutoCommit(false);
	      pstmt = con.prepareStatement(query);			
	      pstmt.setBigDecimal(1,tbl.getExistencia());
	      i = pstmt.executeUpdate();   
	      if(i>0){
	         return utilString.SQL_MODIFICADO;
	      }
	      else{
	         return utilString.SQL_ERROR;	
	      }
	   }
    }
        
    
    public ArrayList <tblProductoDeposito> BuscarArrayList(String CodigoProducto) throws Exception {
    	ArrayList <tblProductoDeposito> tblArray = new ArrayList<tblProductoDeposito>(); 
    	tblProductoDeposito[] TblProductoDeposito = Buscar(CodigoProducto);
    	if(TblProductoDeposito!=null && TblProductoDeposito.length>0){
    	   for(int f=0;f<TblProductoDeposito.length;f++){
    		   tblArray.add(TblProductoDeposito[f]);
    	   }
    	}
    	return tblArray;
    }
      
    public tblProductoDeposito[] Buscar(String CodigoProducto) throws Exception {
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   String InSql="";
	   int Estado=0;
 
	   String query = "Select " +
		   		"a.id, " +
		   		"a.codigodeposito, " +
		   		"a.descripcion, " +
		   		"a.tipodeposito, " +
		   		"a.responsable, " +
	   		    "b.id, " +
	   		    "b.codigodeposito, " +
	   		    "b.codigoproducto, " +
	   		    "b.existencia " +
	   		    "from tblDepositos a  " +
	   		    "Left Join tblProductoDeposito b On a.codigodeposito=b.codigodeposito and b.codigoproducto='" + CodigoProducto + "' " +
	   		    //"where " +
	   		    //"a.tipodeposito<>'" + utilString.TIPO_DEPOSITO_PERDIDA + "' " +
	   		    "order by a.codigodeposito ";

	  
       Connection con = Conexion.getNuevaConexion();
	   pstmt = con.prepareStatement(query); 
	   rs = pstmt.executeQuery();
	   int size=0;
	
	   if(rs!=null){
	      if(rs.last()==true){
	  	     size = rs.getRow();
		     rs.first(); 
		     tblProductoDeposito[] Registros = new tblProductoDeposito[size];
		     for(int f=0;f<size;f++){
		    	 Registros[f] = new tblProductoDeposito();
                 Registros[f].TblDeposito.setId(rs.getInt("a.id")); 
                 Registros[f].TblDeposito.setCodigodeposito(rs.getString("a.codigodeposito"));                    
		         Registros[f].TblDeposito.setDescripcion(rs.getString("a.descripcion"));                    
                 Registros[f].TblDeposito.setTipodeposito(rs.getString("a.tipodeposito"));
		         Registros[f].TblDeposito.setResponsable(rs.getString("a.responsable"));
		         
                 Registros[f].setId(rs.getInt("b.id")); 
                 Registros[f].setCodigodeposito(rs.getString("b.codigodeposito"));                    
		         Registros[f].setCodigoproducto(rs.getString("b.codigoproducto"));                    
                 Registros[f].setExistencia(rs.getBigDecimal("b.existencia"));
                 if(Registros[f].getCodigodeposito()==null || Registros[f].getCodigodeposito().equals("")){
                	Registros[f].setCodigodeposito(Registros[f].TblDeposito.getCodigodeposito());
                 }
                 if(Registros[f].getCodigoproducto()==null || Registros[f].getCodigoproducto().equals("")){
                	Registros[f].setCodigoproducto(CodigoProducto);
                 }                 
                 if(Registros[f].getExistencia()==null){
                	Registros[f].setExistencia(BigDecimal.valueOf(0));
                 }
                 Registros[f].setExistenciareal(Registros[f].getExistencia());
                 Registros[f].setExistenciaoriginal(Registros[f].getExistencia());
                 
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
       TblDeposito.setId(0); 
       TblDeposito.setCodigodeposito("");                    
       TblDeposito.setDescripcion("");                    
       TblDeposito.setTipodeposito("");
       TblDeposito.setResponsable("");    	
       setId(0);
       setCodigodeposito("");
       setCodigodeposito("");
       setExistencia(null);
       TblDeposito.limpiar();
    }   
    
    public void setDeposito(tblProductoDeposito ProductoDeposito){
        TblDeposito.setId(ProductoDeposito.TblDeposito.getId()); 
        TblDeposito.setCodigodeposito(ProductoDeposito.TblDeposito.getCodigodeposito());                    
        TblDeposito.setDescripcion(ProductoDeposito.TblDeposito.getDescripcion());                    
        TblDeposito.setTipodeposito(ProductoDeposito.TblDeposito.getTipodeposito());
        TblDeposito.setResponsable(ProductoDeposito.TblDeposito.getResponsable());  
        
    	setId(ProductoDeposito.getId());
    	setCodigodeposito(ProductoDeposito.getCodigodeposito());
    	setCodigoproducto(ProductoDeposito.getCodigoproducto());
    	setExistencia(ProductoDeposito.getExistencia());
    } 
    
    public String Ajustar(tblProductoDeposito[] TblProductoDeposito){
    	Connection con = Conexion.getNuevaConexion();
	    double EntradaSalida = 0;
	    double ExistenciaTotal = 0;
	    if(TblProductoDeposito!=null && TblProductoDeposito.length>0){
    	   try{
    	      con.setAutoCommit(false);
    	      tblProducto TblProducto = new tblProducto();   
    	      TblProducto.buscarCodigo(TblProductoDeposito[0].getCodigoproducto(), con);
    	      if(TblProducto.getExistencia()!=null){
    	         ExistenciaTotal=TblProducto.getExistencia().doubleValue();
    	      }
    	      for(int f=0;f<TblProductoDeposito.length;f++){
    		      double ExistenciaDeposito=0;
    		      TblProductoDeposito[f].setExistencia(TblProductoDeposito[f].getExistenciareal());
		   	      //new tblProductoDeposito().guardar(TblProductoDeposito[f], con);
		   	      tblProductoDeposito TblProductoDepositoAux = new  tblProductoDeposito();
		   	      if(TblProductoDepositoAux.buscarCodigo(TblProductoDeposito[f].getCodigodeposito(), TblProductoDeposito[f].getCodigoproducto(), con)){
		   		     ExistenciaDeposito=TblProductoDepositoAux.getExistencia().doubleValue();
		   	      }
    	          EntradaSalida = TblProductoDeposito[f].getExistenciareal().doubleValue() - ExistenciaDeposito;
    	          ExistenciaTotal += EntradaSalida;
    	       
    	       
	              if(TblProductoDepositoAux.getExistencia().doubleValue()!=TblProductoDeposito[f].getExistenciareal().doubleValue()){
	        	     tblAjuste TblAjustes = new tblAjuste();
		             TblAjustes.setCodigoproducto(TblProducto.getCodigoproducto());
		             TblAjustes.setCodigodeposito(TblProductoDeposito[f].getCodigodeposito());
		             TblAjustes.setCodigomedida(TblProducto.getCodigomedida());
		             TblAjustes.setCosto(TblProducto.getCosto());
		             TblAjustes.setExistenciadeposito(TblProductoDepositoAux.getExistencia());
		             TblAjustes.setExistenciacontada(TblProductoDeposito[f].getExistenciareal());
		             TblAjustes.setCantidadajustada(BigDecimal.valueOf(EntradaSalida));
		             TblAjustes.guardar(TblAjustes, con);
		            
		             tblKardexDetallado TblKardex = new tblKardexDetallado();
	                 TblKardex.setCodigoproducto(TblProducto.getCodigoproducto());
	                 TblKardex.setDescripcion(TblProducto.getDescripcioncorta());
	                 TblKardex.setCosto(TblProducto.getCosto());
	                 TblKardex.setCodigomedida(TblProducto.getCodigomedida());
	                 TblKardex.setExistencia(BigDecimal.valueOf(ExistenciaTotal));
	                 TblKardex.setExistenciadeposito(TblProductoDeposito[f].getExistenciareal());
	                 TblKardex.setCodigodeposito(TblProductoDeposito[f].getCodigodeposito());
	                 TblKardex.setTipooperacion(utilString.OPERACION_INVENTARIO_AJUSTE);
	                 TblKardex.setMovimiento(BigDecimal.valueOf(EntradaSalida));
	                 TblKardex.setCodigooperecion(String.valueOf(TblAjustes.getAutocodigo()));
	                 TblKardex.Movimiento(TblKardex, con);  
	              }     
    	      }
	          con.commit();
	          return utilString.SQL_MODIFICADO;
		   }catch (Exception e) {
		      e.printStackTrace();
		   }
    	   ByosSql.RollBack(con);
    	   ByosSql.CloseAll(con,null,null);	  
    	   return utilString.SQL_ERROR;    	   
	    }
	    return utilString.SQL_ERROR;
    }    

}
