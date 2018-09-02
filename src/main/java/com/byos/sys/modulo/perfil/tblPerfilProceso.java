package com.byos.sys.modulo.perfil;

import java.io.Serializable;

public class tblPerfilProceso implements Serializable {
	private Integer perfilCodigo;
	private String procesoCodigo;
	
	public tblPerfilProceso(){
		perfilCodigo=new Integer(0);
		procesoCodigo="";
	}
	
	public Integer getperfilCodigo(){
		return this.perfilCodigo;
	}
	
	public void setperfilCodigo(Integer codigo){
		this.perfilCodigo=codigo;
	}
	
	public String getprocesoCodigo(){
		return this.procesoCodigo;
	}
	
	public void setprocesoCodigo(String proceso){
		this.procesoCodigo=proceso;
	}
	
	
	
}
