package com.byos.sys.procesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.byos.sys.conexion.Conexion;

public enum Proceso {
		APLICACION_INIT ("Iniciar Aplicacion",null,0),
		APLICACION_LOGIN ("Login",null,0),
		AUTORIZACIONES_ABRIR("Abrir Módulo de Autorizaciones","Autorizaciones",0),
		AUTORIZACIONES_GUARDAR("Guardar autorización",null,1),
		AUTORIZACIONES_ELIMINAR("Eliminar autorización",null,1),
		AUTORIZACIONES_CLONAR("Clonar autorización",null,0),
		
		PERFIL_ABRIR ("Abrir Módulo Perfil de Usuario","Perfil de Usuarios",0),
		PERFIL_GUARDAR ("Guardar Perfil de Usuario",null,1),
		PERFIL_BUSCAR ("Buscar Perfil de Usuario",null,0), 
		PERFIL_ELIMINAR ("Eliminar Perfil de Usuario",null,1),
		PERFIL_CLONAR ("Clonar Pefil de Usuario",null,0),
		PERFILPROCESO_BUSCAR ("Buscar Proceso de Perfil de Usuario",null,0),
		PERFILPROCESO_ELIMINAR ("Eliminar Proceso de Perfil de Usuario",null,0),
		USUARIOS_ABRIR ("Abrir Modulo de Usuarios","Usuarios",0),
		USUARIOS_BUSCAR ("Buscar Usuario",null,0),
		USUARIOS_CREAR ("Crear Usuario",null,1),
		USUARIOS_NUEVO ("Nuevo Usuario",null,0),
		USUARIOS_ACTIVAR_DESACTIVAR ("Activar y Desactivar Usuarios",null,1),
		USUARIOSPROCESO_BUSCAR ("Buscar proceso de usuario",null,0),
		USUARIOSPROCESO_ELIMINAR ("Eliminar proceso de usuario",null,0),
		USUARIOSPERFIL_BUSCAR("Buscar perfiles asignados a usuario",null,0),
		USUARIOSPERFIL_ELIMINAR("Eliminar perfiles asignados a usuario",null,0),
		INVENTARIO_ABRIR ("Abrir Modulo de Inventario","Inventario",0),
		INVENTARIO_CREAR ("Crear Inventario",null,0),
		INVENTARIO_BUSCAR ("Buscar Inventario",null,0),
		INVENTARIO_SINCRONIZAR("Sincronizar Inventario",null,1),
		INVENTARIO_ELIMINAR("Sincronizar Inventario",null,1),
		INVENTARIO_ELIMINAR_PRODUCTOS ("Eliminar Productos de Inventario",null,1),
		INVENTARIO_AGREGAR_PRODUCTOS ("Cargar Productos de Inventario",null,1),
		INVENTARIO_ASIGNAR_DISPOSITIVOS ("Asignar Dispositivos",null,1),

		PRODUCTO_EJECUAR ("Mantenimiento de Producto","Producto",1),
		PRODUCTO_BUSCAR ("Buscar Producto",null,1),
		PRODUCTO_CREAR ("Crear Producto",null,1),
		PRODUCTO_MODIFICAR ("Modificar Producto",null,1),
		PRODUCTO_DESINCORPORAR ("Desincorporar Producto",null,1),
		
		UNIDADMEDIDA_ABRIR ("Mantenimiento de Unidades de Medidas","Unidades de Medidas",1),
		UNIDADMEDIDA_BUSCAR ("Buscar Unidades de Medidas",null,1),
		UNIDADMEDIDA_CREAR ("Crear Unidades de Medidas",null,1),
		UNIDADMEDIDA_MODIFICAR ("Modificar Unidades de Medidas",null,1),
		UNIDADMEDIDA_DESINCORPORAR ("Desincorporar Unidades de Medidas",null,1),
		
		MARCA_ABRIR ("Mantenimiento de Marcas","Marcas",1),
		MARCA_BUSCAR ("Buscar Marcas",null,1),
		MARCA_CREAR ("Crear Marcas",null,1),
		MARCA_MODIFICAR ("Modificar Marcas",null,1),
		MARCA_ELIMINAR ("Eliminar Marcas",null,1),

		DEPARTAMENTO_ABRIR ("Mantenimiento de Departamentos","Departamentos",1),
		DEPARTAMENTO_BUSCAR ("Buscar Departamentos",null,1),
		DEPARTAMENTO_CREAR ("Crear Departamentos",null,1),
		DEPARTAMENTO_MODIFICAR ("Modificar Departamentos",null,1),
		DEPARTAMENTO_ELIMINAR ("Rliminar Departamentos",null,1),
		
		DEPOSITO_ABRIR ("Mantenimiento de Depositos","Depositos",1),
		DEPOSITO_BUSCAR ("Buscar Depositos",null,1),
		DEPOSITO_CREAR ("Crear Depositos",null,1),
		DEPOSITO_MODIFICAR ("Modificar Depositos",null,1),
		DEPOSITO_ELIMINAR ("Eliminar Depositos",null,1),
		
		GRUPOPRODUCTO_ABRIR ("Mantenimiento de Grupos de Productos","Grupos de Productos",1),
		GRUPOPRODUCTO_BUSCAR ("Buscar Grupos de Productos",null,1),
		GRUPOPRODUCTO_CREAR ("Crear Grupos de Productos",null,1),
		GRUPOPRODUCTO_MODIFICAR ("Modificar Grupos de Productos",null,1),
		GRUPOPRODUCTO_ELIMINAR ("Eliminar Grupos de Productos",null,1),
		
		SUBGRUPOPRODUCTO_ABRIR ("Mantenimiento de SubGrupos de Productos","SubGrupos de Productos",1),
		SUBGRUPOPRODUCTO_BUSCAR ("Buscar SubGrupos de Productos",null,1),
		SUBGRUPOPRODUCTO_CREAR ("Crear SubGrupos de Productos",null,1),
		SUBGRUPOPRODUCTO_MODIFICAR ("Modificar SubGrupos de Productos",null,1),
		SUBGRUPOPRODUCTO_ELIMINAR ("Eliminar SubGrupos de Productos",null,1),		

		IMPUESTO_ABRIR ("Mantenimiento de Impuestos","Impuestos",1),
		IMPUESTO_BUSCAR ("Buscar Impuestos",null,1),
		IMPUESTO_CREAR ("Crear Impuestos",null,1),
		IMPUESTO_MODIFICAR ("Modificar Impuestos",null,1),
		IMPUESTO_ELIMINAR ("Eliminar Impuestos",null,1),
		
		MEDIDASXDEFECTO_ABRIR ("Mantenimiento de Medidas x Defecto","Medidas x Defecto",1),
		MEDIDASXDEFECTO_BUSCAR ("Buscar Medidas x Defecto",null,1),
		MEDIDASXDEFECTO_CREAR ("Crear Medidas x Defecto",null,1),
		MEDIDASXDEFECTO_MODIFICAR ("Modificar Medidas x Defecto",null,1),
		MEDIDASXDEFECTO_ELIMINAR ("Eliminar Medidas x Defecto",null,1),	
		
		PRECIOS_ABRIR ("Mantenimiento de Precios","Precios",1),
		PRECIOS_BUSCAR ("Buscar Precios",null,1),
		PRECIOS_CREAR ("Crear Precios",null,1),
		PRECIOS_MODIFICAR ("Modificar Precios",null,1),
		PRECIOS_DESINCORPORAR ("Eliminar Precios",null,1),		

		PROVEEDOR_ABRIR ("Mantenimiento de Proveedores","Proveedores",1),
		PROVEEDOR_BUSCAR ("Buscar Proveedores",null,1),
		PROVEEDOR_CREAR ("Crear Proveedores",null,1),
		PROVEEDOR_MODIFICAR ("Modificar Proveedores",null,1),
		PROVEEDOR_ELIMINAR ("Eliminar Proveedores",null,1),	

		CLIENTE_ABRIR ("Mantenimiento de Clientes","Clientes",1),
		CLIENTE_BUSCAR ("Buscar Clientes",null,1),
		CLIENTE_CREAR ("Crear Clientes",null,1),
		CLIENTE_MODIFICAR ("Modificar Clientes",null,1),
		CLIENTE_ELIMINAR ("Eliminar Clientes",null,1),	
		
		SUSTANCIASCONTROLADA_ABRIR ("Mantenimiento de Sustencias Controladas","Sustencias Controladas",1),
		SUSTANCIASCONTROLADA_BUSCAR ("Buscar Sustencias Controladas",null,1),
		SUSTANCIASCONTROLADA_CREAR ("Crear Sustencias Controladas",null,1),
		SUSTANCIASCONTROLADA_MODIFICAR ("Modificar Sustencias Controladas",null,1),
		SUSTANCIASCONTROLADA_ELIMINAR ("Eliminar Sustencias Controladas",null,1),	
		
		TALLA_ABRIR ("Mantenimiento de Tallas","Tallas",1),
		TALLA_BUSCAR ("Buscar Tallas",null,1),
		TALLA_CREAR ("Crear Tallas",null,1),
		TALLA_MODIFICAR ("Modificar Tallas",null,1),
		TALLA_ELIMINAR ("Eliminar Tallas",null,1),					

		COLOR_ABRIR ("Mantenimiento de Colores","Colores",1),
		COLOR_BUSCAR ("Buscar Colores",null,1),
		COLOR_CREAR ("Crear Colores",null,1),
		COLOR_MODIFICAR ("Modificar Colores",null,1),
		COLOR_ELIMINAR ("Eliminar Colores",null,1),
		
		TRASLADO_ABRIR ("Traslado de Productos","Traslado",1),
		TRASLADO_BUSCAR ("Buscar Traslado",null,1),
		TRASLADO_CREAR ("Crear Traslado",null,1),
		TRASLADO_MODIFICAR ("Modificar Traslado",null,1),
		TRASLADO_ELIMINAR ("Eliminar Traslado",null,1),	
		
		COMPRAS_ABRIR ("Compras","Compras",1),
		COMPRAS_BUSCAR ("Buscar Compra",null,1),
		COMPRAS_CREAR ("Crear Compra",null,1),
		COMPRAS_MODIFICAR ("Modificar Compra",null,1),
		COMPRAS_ELIMINAR ("Eliminar Compra",null,1),
		COMPRAS_CERRAR ("Cerrar Compra",null,1),
		
		;
		
	
		private Proceso(String descripcionLarga, String descripcionCorta, int confirmacion){
		this.descripcionLarga=descripcionLarga;
		this.descripcionCorta=descripcionCorta;
		this.confirmacion=confirmacion;
		}
		
		private String descripcionLarga;
		private String descripcionCorta;
		private int confirmacion;
		
		public String getDescripcionLarga(){
			return this.descripcionLarga;
		}
		
		public String getDescripcionCorta(){
			return this.descripcionCorta;
		} 
		
		public int getConfirmacion(){
			return this.confirmacion;
		}
		
		public boolean procesoExiste(Proceso L){
			boolean b = false;
			for (Proceso p : Proceso.values())
			{
				if (p.equals(L)){
					b = true;
					
				}
				if (b==true){
					break;
				}
				
			}
			
			return b;
		}
		
		public int procesoCantidad(){
			int i = 0;
			i = this.values().length;
			return i;
		}
		
		
		public ArrayList getProcesos(){
			ArrayList AL = new ArrayList();
			
			for (Proceso p : Proceso.values())
			{
				tblProceso tp = new tblProceso();
				tp.setProceso(p.enumNombre());
				AL.add(tp);
			}
			return AL;
		}

		
		public String toString(){
			 return this.getDescripcionCorta();
		 }
		
		public String enumNombre(){
			return this.name();
		}
		
		
		
		
		
		
}

	
	
	
	

