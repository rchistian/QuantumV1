package com.byos.sys.ui.ByosDatagrid;

import com.byos.sys.filters.ByosFilter;

public class ByosColumna
{
  String nombre;
  String cabecera;
  Class tipo;
  String formato;
  ByosFilter byosfilter;
  String tipoCampo;
  
  public ByosColumna()
  {
    this.nombre = "";
    this.cabecera = "";
    this.tipo = null;
    this.formato = "";
    this.byosfilter = null;
  }
  
  public ByosColumna(String nombre, Class tipo, String cabecera, String formato, ByosFilter byosfilter)
  {
    this.nombre = nombre;
    this.tipo = tipo;
    this.cabecera = cabecera;
    this.formato = formato;
    this.byosfilter = byosfilter;
  }
}
