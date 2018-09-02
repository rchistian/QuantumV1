package com.modulo.componentes;

import com.vaadin.data.util.IndexedContainer;

/**
 *
 * @author Chistian
 */
public class ByosContenedores {
	
	public static String TipoPermisos[] = {
		"Consultar", 
		"Incluir", 
		"Modificar", 
		"Eliminar",
		"Aprobar"};
	
    
    public static IndexedContainer getContainer(String[] Opciones){      
        IndexedContainer Container = new IndexedContainer();
        if(Opciones!=null){
           for(int f=0;f<Opciones.length;f++){
               Container.addItem(Opciones[f]);  
           }
        }
        return Container;
    }
    
    public static IndexedContainer getContainerSiNo(){
        String[] OP = {"","Si","No"};
        return getContainer(OP);
    }
    
    public static IndexedContainer getContainerNull(){
        String[] OP = {""};
        return getContainer(OP);
    }
    
    public static IndexedContainer getContainerActivo(){
        String[] OP = {"","Activo","Inactivo"};
        return getContainer(OP);
    }
    
    public static IndexedContainer getContainerTipoDepositos(){
        String[] OP = {"","Almacenamiento","Perdida"};
        return getContainer(OP);
    }  
    
    public static IndexedContainer getContainerImpuestoAplicara(){
        String[] OP = {"","Base Imponible","Base Exenta","Ambas Bases","Bases + Impuestos"};
        return getContainer(OP);
    } 
    
    public static IndexedContainer getTipoDocuemnto(){
        String[] OP = {"","Factura Producto","Factura Generica","Nota de Credito","Nota de Debito","Gasto","Factura Caja","Gasto Caja"};
        return getContainer(OP);
    } 
    
    public static IndexedContainer getTipoCompra(){
        String[] OP = {"","Nacional","Importacion"};
        return getContainer(OP);
    }
    
    public static IndexedContainer getTipoProveedor(){
        String[] OP = {"","Productos","Gastos"};
        return getContainer(OP);
    } 
    
    public static IndexedContainer getEstadoDocumento(){
        String[] OP = {"","Activo","Totalizado","Totalizado y Cancelado"};
        return getContainer(OP);
    }
    public static IndexedContainer getContainerSexo(){
        String[] OP = {"FEMENINO","MASCULINO","OTRO"};
        return getContainer(OP);
    } 
    
    public static IndexedContainer getContainerTipoDocuemnto(){
        String[] OP = {"DNI","NIE","PASAPORTE","OTRO"};
        return getContainer(OP);
    } 
    
    public static IndexedContainer getContainerEstadoCivil(){
        String[] OP = {"SOLTERO/A","CASADO/A","COMPROMETIDO/A","VIUDO/A","ES COMPLICADO"};
        return getContainer(OP);
    }
    
    public static IndexedContainer getContainerPaises() {
    	String[] OP = {
    			 "ESTONIA",
    	         "ETIOP�A",
    	         "FILIPINAS",
    	         "FINLANDIA",
    	         "FIYI",
    	         "FRANCIA",
    	         "GAB�N",
    	         "GAMBIA",
    	         "GEORGIA",
    	         "GHANA",
    	         "GRANADA",
    	         "GRECIA",
    	         "GUATEMALA",
    	         "GUINEA",
    	         "GUINEA ECUATORIAL",
    	         "GUINEA-BIS�U",
    	         "GUYANA",
    	         "HAIT�",
    	         "HONDURAS",
    	         "HUNGR�A",
    	         "INDIA",
    	         "INDONESIA",
    	         "IRAK",
    	         "IR�N",
    	         "IRLANDA",
    	         "ISLANDIA",
    	         "ISLAS MARSHALL",
    	         "ISLAS SALOM�N",
    	         "ISRAEL",
    	         "ITALIA",
    	         "JAMAICA",
    	         "JAP�N",
    	         "JORDANIA",
    	         "KAZAJIST�N",
    	         "KENIA",
    	         "KIRGUIST�N",
    	         "KIRIBATI",
    	         "KOSOVO",
    	         "KUWAIT",
    	         "LAOS",
    	         "LESOTO",
    	         "LETONIA",
    	         "L�BANO",
    	         "LIBERIA",
    	         "LIBIA",
    	         "LIECHTENSTEIN",
     	         "LITUANIA",
    	         "LUXEMBURGO",
    	         "MACEDONIA",
    	         "MADAGASCAR",
    	         "MALASIA",
    	         "MALAUI",
    	         "MALDIVAS",
    	         "MAL�",
    	         "MALTA",
    	         "MARRUECOS",
    	         "MAURICIO",
    	         "MAURITANIA",
    	         "M�XICO",
    	         "MICRONESIA",
    	         "MOLDAVIA",
    	         "M�NACO",
    	         "MONGOLIA",
    	         "MONTENEGRO",
    	         "MOZAMBIQUE",
    	         "NAMIBIA",
    	         "NAURU",
     	         "NEPAL",
    	         "NICARAGUA",
    	         "N�GER",
    	         "NIGERIA",
    	         "NORUEGA",
    	         "NUEVA ZELANDA",
    	         "OM�N",
    	         "PA�SES BAJOS",
    	         "PAKIST�N",
    	         "PALAOS",
    	         "PALESTINA",
    	         "PANAM�",
    	         "PAP�A NUEVA GUINEA",
    	         "PARAGUAY",
    	         "PER�",
    	         "POLONIA",
    	         "PORTUGAL",
    	         "REINO UNIDO",
    	         "REP�BLICA CENTROAFRICANA",
    	         "REP�BLICA CHECA",
    	         "REP�BLICA DEMOCR�TICA DEL CONGO",
    	         "REP�BLICA DOMINICANA",
    	         "RUANDA",
    	         "RUMANIA",
    	         "RUSIA",
    	         "SAMOA",
    	         "SAN CRIST�BAL Y NIEVES",
    	         "SAN MARINO",
    	         "SAN VICENTE Y LAS GRANADINAS",
    	         "SANTA LUC�A",
    	         "SANTO TOM� Y PR�NCIPE",
    	         "SENEGAL",
    	         "SERBIA",
    	         "SEYCHELLES",
    	         "SIERRA LEONA",
    	         "SINGAPUR",
    	         "SIRIA",
    	         "SOMALIA",
    	         "SRI LANKA",
    	         "SUAZILANDIA",
    	         "SUD�FRICA",
    	         "SUD�N",
    	         "SUD�N DEL SUR",
    	         "SUECIA",
    	         "SUIZA",
    	         "SURINAM",
    	         "TAILANDIA",
    	         "TAIW�N",
    	         "TANZANIA",
    	         "TAYIKIST�N",
    	         "TIMOR ORIENTAL",
    	         "TOGO",
    	         "TONGA",
    	         "TRINIDAD Y TOBAGO",
    	         "T�NEZ",
    	         "TURKMENIST�N",
    	         "TURQU�A",
    	         "TUVALU",
    	         "UCRANIA",
    	         "UGANDA",
    	         "URUGUAY",
    	         "UZBEKIST�N",
    	         "VANUATU",
    	         "VATICANO",
    	         "VENEZUELA",
    	         "VIETNAM",
    	         "YEMEN",
    	         "YIBUTI",
    	         "ZAMBIA",
    	         "ZIMBABUE"
    	};
    	return getContainer(OP);
    }
}