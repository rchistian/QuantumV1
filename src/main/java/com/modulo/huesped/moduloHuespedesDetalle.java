package com.modulo.huesped;

import java.io.Serializable;
import com.modulo.componentes.ByosVisorImegenes;
import com.vaadin.ui.VerticalLayout;

public class moduloHuespedesDetalle extends VerticalLayout  implements Serializable{
    private static final long serialVersionUID = 5474522369804563317L;
    
    private tblHuespedes TblHuespedes;
    public ByosVisorImegenes ByosVisorImegenes01;
    
    public moduloHuespedesDetalle(final tblHuespedes TblHuespedes, boolean MostrarMesnu, String ViwerHeight){ 
    	this.TblHuespedes = TblHuespedes;
    	
    	setSpacing(true);
    	setMargin(false);
    	      
        String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblHuespedes\\Fotos\\" + TblHuespedes.getID() + "\\";
        String DirUrl="../dbsimagenes/tblHuespedes/Fotos/" + TblHuespedes.getID() + "/";
       
        ByosVisorImegenes01 = new ByosVisorImegenes(DirUrl, DirFile, "", TblHuespedes.getNumeroDocumento(),MostrarMesnu, ViwerHeight);
        addComponent(ByosVisorImegenes01);
    }
    
    public void setTblHuespedes(final tblHuespedes TblHuespedes){
    	this.TblHuespedes = TblHuespedes;
    	String DirFile="\\VAADIN\\themes\\dbsimagenes\\tblHuespedes\\Fotos\\" + TblHuespedes.getID() + "\\";
        String DirUrl="../dbsimagenes/tblHuespedes/Fotos/" + TblHuespedes.getID() + "/";
        ByosVisorImegenes01.init(DirUrl, DirFile, TblHuespedes.getNumeroDocumento());
    }
       

}
