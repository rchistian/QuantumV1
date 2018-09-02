/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modulo.componentes;

import java.io.Serializable;

import com.vaadin.server.Sizeable;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;

/**
 *
 * @author Chistian
 */
public class ByosImagenes  implements Serializable{

    private static final long serialVersionUID = 5474522369804563317L;
    
     public static final ThemeResource icon[] = {
        new ThemeResource("imagen/nuevo.png"),       //00
        new ThemeResource("imagen/guardar.png"),     //01
        new ThemeResource("imagen/eliminar.png"),    //02
        new ThemeResource("imagen/buscar.png"),      //03
        new ThemeResource("imagen/clonar.png"),      //04
        new ThemeResource("imagen/cancelar.png"),    //05
        new ThemeResource("imagen/sincronizar.png"), //06
        new ThemeResource("imagen/todos.png"),       //07
        new ThemeResource("imagen/ninguno.png"),     //08
        new ThemeResource("imagen/tablet.png"),      //09
        new ThemeResource("imagen/usuario.png"),     //10
        new ThemeResource("imagen/diferentes.png"),  //11
        new ThemeResource("imagen/revisado.png"),    //12
        new ThemeResource("imagen/porrevisar.png"),  //13
        new ThemeResource("imagen/anterior.png"),    //14
        new ThemeResource("imagen/proximo.png"),     //15
        new ThemeResource("imagen/agregar.png"),     //16
        new ThemeResource("imagen/barcode.png"),     //17
        new ThemeResource("imagen/refresh.png"),     //18
        new ThemeResource("imagen/buscarbarra.png"), //19
        new ThemeResource("imagen/precio.png"),      //20
        new ThemeResource("imagen/reporte.png"),     //21  
        new ThemeResource("imagen/porcentaje.png"),  //22  
        new ThemeResource("imagen/redondeo.png"),    //23
        new ThemeResource("imagen/windows.png"),     //24
        new ThemeResource("imagen/titulo.png"),      //25
        new ThemeResource("imagen/camaraweb.png"),   //26
        new ThemeResource("imagen/inventario.png"),  //27
        new ThemeResource("imagen/productos.png"),        //28
        new ThemeResource("imagen/agregarimagen.png"),    //29
        new ThemeResource("imagen/eliminarimagen.png"),   //30  
        new ThemeResource("imagen/grafico.png"),          //31
        new ThemeResource("imagen/aceptar.png"),          //32
        new ThemeResource("imagen/cancelar.png"),         //33
        new ThemeResource("imagen/mantenimiento.png"),    //34
        new ThemeResource("imagen/medida.png"),           //35
        new ThemeResource("imagen/marca.png"),            //36
        new ThemeResource("imagen/deposito.png"),         //37
        new ThemeResource("imagen/departamento.png"),     //38
        new ThemeResource("imagen/traslado.png"),         //39
        new ThemeResource("imagen/grupo.png"),            //40
        new ThemeResource("imagen/subgrupo.png"),         //41
        new ThemeResource("imagen/precios.png"),          //42
        new ThemeResource("imagen/impuestos.png"),        //43
        new ThemeResource("imagen/medidadefault.png"),    //44
        new ThemeResource("imagen/control.png"),          //45
        new ThemeResource("imagen/buscar16.png"),         //46
        new ThemeResource("imagen/cancelar16.png"),       //47   
        new ThemeResource("imagen/agregar16.png"),        //48
        new ThemeResource("imagen/buscar16.png"),         //49
        new ThemeResource("imagen/refresh16.png"),        //50
        new ThemeResource("imagen/proveedores.png"),      //51
        new ThemeResource("imagen/clientes.png"),         //52
        new ThemeResource("imagen/compras.png"),          //53
        new ThemeResource("imagen/devolucion.png"),       //54
        new ThemeResource("imagen/facturacion.png"),      //55
        new ThemeResource("imagen/ajustes.png"),          //56
        new ThemeResource("imagen/inventario01.png"),     //57
        new ThemeResource("imagen/otros.png"),            //58
        new ThemeResource("imagen/bancos.png"),           //59
        new ThemeResource("imagen/cuentasbanco.png"),     //60
        new ThemeResource("imagen/cuentasxcobrar.png"),   //61
        new ThemeResource("imagen/cuentasxpagar.png"),    //62
        new ThemeResource("imagen/tipopago.png"),         //63
        new ThemeResource("imagen/vendedor.png"),         //64
        new ThemeResource("imagen/cuentas.png"),          //65
        new ThemeResource("imagen/asientos.png"),         //66
        new ThemeResource("imagen/tipoasiento.png"),      //67
        new ThemeResource("imagen/totalizarcompra.png"),  //68
        new ThemeResource("imagen/cancelarcompra.png"),   //69
        new ThemeResource("imagen/BYOS.gif"),             //70
        
        new ThemeResource("imagen/mantenimiento.png"),    //71
        new ThemeResource("imagen/compras.png"),          //72
        new ThemeResource("imagen/ventas.png"),           //73
        new ThemeResource("imagen/cuentas.png"),          //74
        new ThemeResource("imagen/contabilidad.png"),     //75
        new ThemeResource("imagen/reportes.png"),         //76
        new ThemeResource("imagen/varios.png"),           //77
        new ThemeResource("imagen/Recerva.png"),          //78
        new ThemeResource("imagen/HuespedFicha.png"),     //79
        new ThemeResource("imagen/BuscarGeneral.png"),    //80
        new ThemeResource("imagen/Stop72x72.png"),        //81
        new ThemeResource("imagen/HuespedActivo.png"),    //82
        new ThemeResource("imagen/Salir01.png"),          //83
        
        new ThemeResource("imagen/Ficha.png"),              //84
        new ThemeResource("imagen/HuespedFicha.png"),       //85
        new ThemeResource("imagen/BanderaMundo.png"),       //86       
        new ThemeResource("imagen/Redes-sociales.png"),     //87
        new ThemeResource("imagen/RedSocial72x72.png"),     //88
        new ThemeResource("imagen/SignEuro2.png"),          //89
        new ThemeResource("imagen/SpreadSheetTotal.png"),   //90
        new ThemeResource("imagen/Estadisticas01.png"),     //91
        new ThemeResource("imagen/album.png"),              //92
        new ThemeResource("imagen/MenuNuevo72x72.png"),     //93
        
        new ThemeResource("imagen/MenuNuevo72x72.png"),     //94
        new ThemeResource("imagen/MenuBuscar72x72.png"),    //95
        new ThemeResource("imagen/MenuEliminar72x72.png"),  //96
        new ThemeResource("imagen/MenuGuardar72x72.png"),   //97
        new ThemeResource("imagen/MenuAnterior72x72.png"),  //98
        new ThemeResource("imagen/MenuProximo72x72.png"),   //99
        
        new ThemeResource("imagen/TabFicha32x32.png"),           //100
        new ThemeResource("imagen/TabDatosPersonales32x32.png"), //101
        new ThemeResource("imagen/TabBanderaMundo32x32.png"),    //102
        new ThemeResource("imagen/TabRedes-sociales32x32.png"),  //103
        new ThemeResource("imagen/TabRedSocial72x72.png"),       //104
        new ThemeResource("imagen/TabEstadisticas32x32.png"),    //105
        new ThemeResource("imagen/TabAlbum32x32.png"),           //106
        
        new ThemeResource("imagen/PicturesZoom32x32.png"),       //107
        new ThemeResource("imagen/SubirArchivo32x32.png"),       //108
        new ThemeResource("imagen/Scanner32x32.png"),            //109
        new ThemeResource("imagen/Salir32x32.png"),              //110
        
        new ThemeResource("imagen/FondoIsla.gif"),               //111
        new ThemeResource("imagen/Inicio1024x768.gif"),          //112
        new ThemeResource("imagen/MarcoBilletes1009x726.png"),   //113
        new ThemeResource("imagen/EscaleraCielo.jpg"),           //114
        new ThemeResource("imagen/MarcoBilletesAgua1020x734V4.png"), //115
        new ThemeResource("imagen/TPMV669x551.jpg"),             //116
        new ThemeResource("imagen/Hotel100x78.png"),             //117
        new ThemeResource("imagen/Club01-100x99.png"),           //118   
        new ThemeResource("imagen/ChicaBailinado79x101.gif"),    //119  
        new ThemeResource("imagen/MarcoBilletes1020x734V3.png"), //120 
        new ThemeResource("imagen/Usuarios.png"),                //121
        new ThemeResource("imagen/Huesped1A.gif"),               //122
        new ThemeResource("imagen/Huesped1B-100x115.gif"),       //123
        new ThemeResource("imagen/proveedorCamion91x91.png"),    //124
        new ThemeResource("imagen/BusquedaLupa.png"),            //125
        new ThemeResource("imagen/Operador.png"),                //126
        new ThemeResource("imagen/DedoPulgar73x111.png"),        //127
        new ThemeResource("imagen/Anterior01.jpg"),              //128
        new ThemeResource("imagen/Proximo01.jpg"),               //129
        new ThemeResource("imagen/IrAIzquierda.jpg"),            //130
        new ThemeResource("imagen/IrADerecha.jpg"),              //131
        
        new ThemeResource("imagen/dMovilB.png"),                 //132
        new ThemeResource("imagen/iMovilA.png"),                 //133
        new ThemeResource("imagen/MostrarFotosA.png"),           //134
        
        new ThemeResource("imagen/DedoIzuierdo01.gif"),          //135
        new ThemeResource("imagen/DedoDerecho01.gif"),           //136
        new ThemeResource("imagen/Reserva01.png"),               //137
        new ThemeResource("imagen/PreReserva01.png"),            //138
        
        new ThemeResource("imagen/Sobre01.png"),                 //139
        new ThemeResource("imagen/RedesSociales01.png"),         //140
        new ThemeResource("imagen/Agenda01.png"),                //141
        new ThemeResource("imagen/Alarma01.png"),                //142
        
        new ThemeResource("imagen/Incidencias01.png"),           //143
        new ThemeResource("imagen/GMail01.png"),                 //144
        new ThemeResource("imagen/Observaciones01.png"),         //145
        new ThemeResource("imagen/Conformidad01.png"),           //146
        new ThemeResource("imagen/Ayuda02.png"),                 //147
        
     };   
     
     public static Embedded AjustarImagen(ThemeResource xImagen, float xWidth, float xHeight){
    	 Embedded Imagen = new Embedded();
    	 Imagen = new Embedded(null, xImagen);
    	 float xWidth01 = 100-xWidth/Imagen.getWidth()*100;
    	 float xHeigth01 = 100-xHeight/Imagen.getHeight()*100;
    	 float Porcentaje = xWidth01>xHeigth01?xWidth01:xHeigth01;
    	    	 
    	 xWidth01=xWidth01-xWidth01*Porcentaje/100;
    	 xHeigth01=xHeigth01-xHeigth01*Porcentaje/100;
    	 
    	 Imagen.setWidth(xWidth01,Unit.PIXELS);
    	 Imagen.setHeight(xHeigth01,Unit.PIXELS);
    	 return Imagen;
     }

     public static Embedded AjustarImagen(Embedded xImagen, float xWidth, float xHeight){
    	 Embedded Imagen = new Embedded();
    	 Imagen = xImagen;
    	 
    	 xWidth=xWidth<0?xWidth*-1:xWidth;
    	 xHeight=xHeight<0?xHeight*-1:xHeight;
    	 
    	 float xWidth01 = 100-xWidth/Imagen.getWidth()*100;
    	 float xHeigth01 = 100-xHeight/Imagen.getHeight()*100;
    	 float Porcentaje = xWidth01>xHeigth01?xWidth01:xHeigth01;
    	 System.out.println("Antes Imagen : Width " + Imagen.getWidth() + " Heigth " + Imagen.getWidth());
    	 System.out.println("Antes: Width " + Imagen.getHeight() + " Heigth " + Imagen.getHeight());
    	 xWidth01=xWidth01-xWidth01*Porcentaje/100;
    	 xHeigth01=xHeigth01-xHeigth01*Porcentaje/100;
    	 System.out.println("Despues: Width " + xWidth01 + " Heigth " + xHeigth01);
    	 
    	 
    	 Imagen.setWidth(xWidth01,Unit.PIXELS);
    	 Imagen.setHeight(xHeigth01,Unit.PIXELS);
    	 System.out.println("Despues Imagen : Width " + Imagen.getWidth() + " Heigth " + Imagen.getWidth());
    	 return Imagen;
     }
}
