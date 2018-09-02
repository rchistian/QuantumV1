/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.byos.sys.imagenes;

import java.io.Serializable;

import com.vaadin.server.ThemeResource;

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
        new ThemeResource("imagen/BusquedaLupa75x95.png"),//22
        new ThemeResource("imagen/Aceptar32x32a.png"),//23
        
    };   
}
