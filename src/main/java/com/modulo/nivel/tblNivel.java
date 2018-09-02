package com.modulo.nivel;

public class tblNivel {
    private Integer id;
	
	private Integer codigonivel;
	
	private String descripcion;
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public tblNivel(){
		
	}
	
	public tblNivel(Integer id, Integer codigonivel, String descripcion){
	    this.id = id;		
	    this.codigonivel = codigonivel;		
	    this.descripcion = descripcion;
	}
	
	public void setTblNivel(tblNivel Tbl){
	    this.id = Tbl.getId();		
	    this.codigonivel = Tbl.getCodigonivel();		
	    this.descripcion = Tbl.getDescripcion();
	}
	
	public void Limpiar(){
		setId(null);
		setCodigonivel(null);
		setDescripcion("");
	}

}
