package com.modulo.usuarios;

import java.io.Serializable;

import com.modulo.componentes.ByosVisorImegenes;
import com.vaadin.ui.VerticalLayout;

public class moduloUsuariosDetalle  extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    
    private tblUsuarios TblUsuarios;
    public ByosVisorImegenes ByosVisorImegenes01;
    
    public moduloUsuariosDetalle(final tblUsuarios TblUsuarios, boolean MostrarMesnu, String ViwerHeight){ 
    	this.TblUsuarios = TblUsuarios;
    	
    	setSpacing(true);
    	setMargin(false);
    	      
        String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblUsuarios\\Fotos\\" + TblUsuarios.getID() + "\\";
        String DirUrl="../dbsimagenes/tblUsuarios/Fotos/" + TblUsuarios.getID() + "/";
       
        ByosVisorImegenes01 = new ByosVisorImegenes(DirUrl, DirFile, "", TblUsuarios.getLogin(),MostrarMesnu, ViwerHeight);
        addComponent(ByosVisorImegenes01);
    }
    
    public void setTblUsuarios(final tblUsuarios TblUsuarios){
    	this.TblUsuarios = TblUsuarios;
    	String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblUsuarios\\Fotos\\" + TblUsuarios.getID() + "\\";
        String DirUrl="../dbsimagenes/tblUsuarios/Fotos/" + TblUsuarios.getID() + "/";
        ByosVisorImegenes01.init(DirUrl, DirFile, TblUsuarios.getLogin());
    }
       

}
