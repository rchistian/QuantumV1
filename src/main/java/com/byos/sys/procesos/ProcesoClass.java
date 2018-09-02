package com.byos.sys.procesos;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.byos.sys.app.ByosApp;
import com.byos.sys.conexion.Conexion;
import com.byos.sys.db.AutoNumero;
import com.byos.sys.procesos.ProcesoClass;
import com.byos.sys.procesos.ProcesoInterface;
import com.byos.sys.ui.ByosDialog.ByosDialog;
import com.byos.sys.ui.ByosLoginForm.ByosLoginForm;
import com.byos.sys.util.utilDate;
import com.byos.sys.util.utilString;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;


public class ProcesoClass implements Comparable<ProcesoClass>,PropertyChangeListener  {
   // private loggedUser LU;
	private boolean procesoEjecutado;
	private int procesoIntento;
	private ByosLoginForm lwindow;
	private ByosDialog bd;
	private Proceso proceso;
	public PropertyChangeSupport support = new PropertyChangeSupport(this);
	public String usuario;
	public Timestamp procesoHora = utilDate.horaDelDia();
	private boolean confirmado = false;
	
	public ProcesoClass(Proceso PL){
	//	this.LU=new loggedUser();
		this.proceso=PL;
    	this.procesoEjecutado=false;
    	this.procesoIntento=0;
    	ByosApp BA = (ByosApp)ByosApp.getCurrent();
    	this.usuario=BA.getUsuario();
	}
	
    public void procesoDefinicion() {
    
    }
    
    public String procesoValidacion(){
    	return "";
    }
    
	
	public void procesoEjecucion() {
		try{
			
			 if (ProcesoAutorizacion.procesoAutorizado(proceso, this.usuario) || this.proceso.equals(Proceso.APLICACION_INIT) || this.usuario.equals("byos")){   
				
				
				if (this.proceso.getConfirmacion()==0 || confirmado == true){
			    String v = procesoValidacion();
				if (v.equals("")){
				procesoDefinicion();
				
				support.firePropertyChange("procesoTerminado", false,true);	
				GuardarLogProcesado(proceso,this.usuario);
				}
				else{
				Notification.show(v);	
				}
				}
				
				else{
					bd = new ByosDialog("Esta seguro que desea ejecutar proceso?");
					bd.addPropertyChangeListener(this);
					this.addPropertyChangeListener(bd);
					ByosApp.getCurrent().addWindow(bd);
					bd.center();
				}
			 }
		   else{
			   //Pedir Password
			   GuardarLogFallido(proceso,this.usuario);
			   lwindow = new ByosLoginForm();
			   lwindow.addPropertyChangeListener(this);
			   this.addPropertyChangeListener(lwindow);
			   ByosApp.getCurrent().addWindow(lwindow);
			   lwindow.center();
		   }
				
				
			 
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		}
	
	
		
	public Proceso getProceso(){
		return this.proceso;
	}
		
	public void setProceso(Proceso P){
		this.proceso=P;
	}
	
	public boolean getProcesoEjecutado(){
		return this.procesoEjecutado;
	}
	
	public int getProcesoIntento(){
		return this.procesoIntento;
	}
	
	@Override
	public int compareTo(ProcesoClass arg0) {
		// TODO Auto-generated method stub
		return this.proceso.compareTo(((ProcesoClass)arg0).getProceso());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(utilString.CREDENCIALES)){
			
			String creUsu = (String)evt.getOldValue();
			this.usuario = creUsu;
			String crePass = (String)evt.getNewValue();
			
			String dbPass = getPass(creUsu);
			
			String encCrePass = apocrifoE.E(crePass, apocrifoE.Modo(dbPass));
		
			if (encCrePass.equals(dbPass) && !dbPass.equals("") && !encCrePass.equals("")){
				
				
				this.usuario = creUsu;
				if (this.proceso.equals(Proceso.APLICACION_LOGIN)){
					ByosApp BA = (ByosApp)ByosApp.getCurrent();
					BA.setUsuario(creUsu);
				}
				ByosApp.getCurrent().removeWindow(lwindow);
				procesoEjecucion();
			}	
			else{
				if (dbPass.equals("")){
					Notification.show("Usuario no existe");
					GuardarLogFallido(proceso,this.usuario);
				}
				else{
					Notification.show("Password inválido");
					GuardarLogFallido(proceso,this.usuario);
				}
					
				this.procesoIntento = this.procesoIntento + 1;
				if (this.procesoIntento==3){
				}
			}
			
	}
		
		if (evt.getPropertyName().equals(utilString.CONFIRMACION)){
			Boolean c = (Boolean)evt.getNewValue();
			ByosApp.getCurrent().removeWindow(bd);	
			if (c == true){
			confirmado = true;	
			procesoEjecucion();
			}
		}
		
		}

	
	public void procesoLog() {
		// TODO Auto-generated method stub
		
	}
	
	private String getPass(String usuLogin){
		String p="";
		try{
			Connection conn = new Conexion().getNuevaConexion();
	        ResultSet rs = null;
	      	PreparedStatement pstmt1 = null;
	     
	      	String query = "Select usupassword from tblusuarios where usulogin=?";
	      	
	      	pstmt1=conn.prepareStatement(query);
	      	pstmt1.setString(1, usuLogin);
	      	
	      	rs = pstmt1.executeQuery();
		
	      	if (rs.last()){
	      		p = rs.getString("usupassword");
	      	}
	      	
	      	rs.close();
	      	pstmt1.close();
	      	rs=null;
	      	pstmt1=null;
	      	conn.close();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return p;
	     	
	}
	
	public void procesoLogException(Exception e){
		try{
		Connection conn = new Conexion().getNuevaConexion();
        ResultSet rs = null;
      	PreparedStatement pstmt1 = null;
     
      	String query = "Insert into tbllogexception(idx,proceso,exception,idxfecha,idxusuario) values(?,?,?,?,?)";
     	pstmt1 = conn.prepareStatement(query);
     	pstmt1.setLong(1, new AutoNumero().getAutoNumero("tbllog","idx"));
     	pstmt1.setString(2, this.proceso.name());
     	String ee="";
     	if (e==null){
     		ee = "Exception unknown";
     		pstmt1.setString(3,ee);
     	}else{
     		pstmt1.setString(3,e.getMessage());
     	}
     	
     	
     	pstmt1.setTimestamp(4,procesoHora);
     	ByosApp BA = (ByosApp)ByosApp.getCurrent();
     	pstmt1.setString(5, BA.getUsuario());
     	
     	pstmt1.executeUpdate();
     	pstmt1.close();
     	pstmt1 =null;
     	conn.close();
     	
     	Notification.show(e.getMessage());
     	
		}
		catch(Exception i){
		 i.printStackTrace();
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener actionListener ) {
	    this.support.addPropertyChangeListener( actionListener );
	 }

	 public void removePropertyChangeListener(PropertyChangeListener listener ) {
	    this.support.removePropertyChangeListener( listener );
	 }
	 
	 public String toString(){
		 return this.proceso.getDescripcionCorta();
	 }
	 
	 
	 public void GuardarLogProcesado(Proceso pc,String usuario){
			
			try{
			
		        Connection conn = new Conexion().getNuevaConexion();
		        ResultSet rs = null;
		      	PreparedStatement pstmt1 = null;
		      	
		      	String query = "Insert into tbllog(idx,proceso,idxfecha,idxusuario,idxStatus) values(?,?,?,?,?)";
		     	pstmt1 = conn.prepareStatement(query);
		     	pstmt1.setLong(1, new AutoNumero().getAutoNumero("tbllog","idx"));
		     	pstmt1.setString(2, pc.name());
		     	pstmt1.setTimestamp(3,procesoHora);
		     	pstmt1.setString(4, usuario);
		     	pstmt1.setString(5,"concedido");
		     	pstmt1.executeUpdate(); 
		     	
		     	pstmt1.close();
		     	pstmt1 = null;
		     	conn.close();
		         
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}

		public void GuardarLogFallido(Proceso pc,String usuario){
			
			try{
			
		        Connection conn = new Conexion().getNuevaConexion();
		        PreparedStatement pstmt1 = null;
		    	      	
		      	String query = "Insert into tbllog(idx,proceso,idxfecha,idxusuario,idxStatus) values(?,?,?,?,?)";
		     	pstmt1 = conn.prepareStatement(query);
		     	pstmt1.setLong(1, new AutoNumero().getAutoNumero("tbllog","idx"));
		     	pstmt1.setString(2, pc.name());
		     	pstmt1.setTimestamp(3,procesoHora);
		     	pstmt1.setString(4, usuario);
		     	pstmt1.setString(5,"negado");
		     	pstmt1.executeUpdate();     
		     	pstmt1.close();
		     	pstmt1 = null;
		     	conn.close();
		     	
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
	 
	 

	

}
