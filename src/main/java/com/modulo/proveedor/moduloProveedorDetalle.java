package com.modulo.proveedor;

import java.io.Serializable;

import com.modulo.componentes.ByosVisorImegenes;
import com.modulo.usuarios.tblUsuarios;
import com.vaadin.ui.VerticalLayout;

public class moduloProveedorDetalle   extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    
    private tblProveedor TblProveedor;
    public ByosVisorImegenes ByosVisorImegenes01;
    
    public moduloProveedorDetalle(final tblProveedor TblProveedor, boolean MostrarMesnu, String ViwerHeight){ 
    	this.TblProveedor = TblProveedor;
    	
    	setSpacing(true);
    	setMargin(false);
    	      
        String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblProveedor\\Fotos\\" + TblProveedor.getId() + "\\";
        String DirUrl="../dbsimagenes/tblProveedor/Fotos/" + TblProveedor.getId() + "/";
       
        ByosVisorImegenes01 = new ByosVisorImegenes(DirUrl, DirFile, "", TblProveedor.getCodigoproveedor(),MostrarMesnu, ViwerHeight);
        addComponent(ByosVisorImegenes01);
    }
    
    public void setTblProveedor(final tblProveedor TblProveedor){
    	this.TblProveedor = TblProveedor;
    	String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblProveedor\\Fotos\\" + TblProveedor.getId() + "\\";
        String DirUrl="../dbsimagenes/tblProveedor/Fotos/" + TblProveedor.getId() + "/";
        ByosVisorImegenes01.init(DirUrl, DirFile, TblProveedor.getCodigoproveedor());
    }
       

}
