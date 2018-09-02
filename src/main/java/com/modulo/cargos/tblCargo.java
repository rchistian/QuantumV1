package com.modulo.cargos;

public class tblCargo {
	private Integer id;

	private Integer codigocargo;
	
	private String descripcion;
	
	private Integer codigonivel;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCodigocargo() {
		return codigocargo;
	}
		
	public void setCodigocargo(Integer codigocargo) {
		this.codigocargo = codigocargo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCodigonivel() {
		return codigonivel;
	}
	public void setCodigonivel(Integer codigonivel) {
		this.codigonivel = codigonivel;
	}
	
	public tblCargo(){
		
	}
	
	public tblCargo(Integer id, Integer codigocargo, String descripcion, Integer codigonivel){
	    this.id = id;
	    this.codigocargo = codigocargo;	
	    this.codigonivel = codigonivel;		
	    this.descripcion = descripcion;
	}
	
	public void setTblCargo(tblCargo Tbl){
	    this.id = Tbl.getId();
	    this.codigocargo = Tbl.getCodigocargo();		
	    this.codigonivel = Tbl.getCodigonivel();		
	    this.descripcion = Tbl.getDescripcion();
	}
	
	public void Limpiar(){
		setId(null);
		setCodigocargo(null);
		setCodigonivel(null);
		setDescripcion("");
	}
	
}
