package com.modulo.nivelpermiso;

public class tblNivelPermiso {

	private Integer id;
	
	private Integer codigonivel;
	
	private String codigopermiso;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigonivel() {
		return codigonivel;
	}
	public void setCodigonivel(Integer codigonivel) {
		this.codigonivel = codigonivel;
	}
	public String getCodigopermiso() {
		return codigopermiso;
	}
	public void setCodigopermiso(String codigopermiso) {
		this.codigopermiso = codigopermiso;
	}
	
	public tblNivelPermiso(Integer id, Integer codigomodulo, String codigopermiso){
		this.id = id;
		this.codigonivel = codigomodulo;
		this.codigopermiso = codigopermiso;
	}

}
