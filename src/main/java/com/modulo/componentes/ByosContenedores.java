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
    	         "ETIOPÍA",
    	         "FILIPINAS",
    	         "FINLANDIA",
    	         "FIYI",
    	         "FRANCIA",
    	         "GABÓN",
    	         "GAMBIA",
    	         "GEORGIA",
    	         "GHANA",
    	         "GRANADA",
    	         "GRECIA",
    	         "GUATEMALA",
    	         "GUINEA",
    	         "GUINEA ECUATORIAL",
    	         "GUINEA-BISÁU",
    	         "GUYANA",
    	         "HAITÍ",
    	         "HONDURAS",
    	         "HUNGRÍA",
    	         "INDIA",
    	         "INDONESIA",
    	         "IRAK",
    	         "IRÁN",
    	         "IRLANDA",
    	         "ISLANDIA",
    	         "ISLAS MARSHALL",
    	         "ISLAS SALOMÓN",
    	         "ISRAEL",
    	         "ITALIA",
    	         "JAMAICA",
    	         "JAPÓN",
    	         "JORDANIA",
    	         "KAZAJISTÁN",
    	         "KENIA",
    	         "KIRGUISTÁN",
    	         "KIRIBATI",
    	         "KOSOVO",
    	         "KUWAIT",
    	         "LAOS",
    	         "LESOTO",
    	         "LETONIA",
    	         "LÍBANO",
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
    	         "MALÍ",
    	         "MALTA",
    	         "MARRUECOS",
    	         "MAURICIO",
    	         "MAURITANIA",
    	         "MÉXICO",
    	         "MICRONESIA",
    	         "MOLDAVIA",
    	         "MÓNACO",
    	         "MONGOLIA",
    	         "MONTENEGRO",
    	         "MOZAMBIQUE",
    	         "NAMIBIA",
    	         "NAURU",
     	         "NEPAL",
    	         "NICARAGUA",
    	         "NÍGER",
    	         "NIGERIA",
    	         "NORUEGA",
    	         "NUEVA ZELANDA",
    	         "OMÁN",
    	         "PAÍSES BAJOS",
    	         "PAKISTÁN",
    	         "PALAOS",
    	         "PALESTINA",
    	         "PANAMÁ",
    	         "PAPÚA NUEVA GUINEA",
    	         "PARAGUAY",
    	         "PERÚ",
    	         "POLONIA",
    	         "PORTUGAL",
    	         "REINO UNIDO",
    	         "REPÚBLICA CENTROAFRICANA",
    	         "REPÚBLICA CHECA",
    	         "REPÚBLICA DEMOCRÁTICA DEL CONGO",
    	         "REPÚBLICA DOMINICANA",
    	         "RUANDA",
    	         "RUMANIA",
    	         "RUSIA",
    	         "SAMOA",
    	         "SAN CRISTÓBAL Y NIEVES",
    	         "SAN MARINO",
    	         "SAN VICENTE Y LAS GRANADINAS",
    	         "SANTA LUCÍA",
    	         "SANTO TOMÉ Y PRÍNCIPE",
    	         "SENEGAL",
    	         "SERBIA",
    	         "SEYCHELLES",
    	         "SIERRA LEONA",
    	         "SINGAPUR",
    	         "SIRIA",
    	         "SOMALIA",
    	         "SRI LANKA",
    	         "SUAZILANDIA",
    	         "SUDÁFRICA",
    	         "SUDÁN",
    	         "SUDÁN DEL SUR",
    	         "SUECIA",
    	         "SUIZA",
    	         "SURINAM",
    	         "TAILANDIA",
    	         "TAIWÁN",
    	         "TANZANIA",
    	         "TAYIKISTÁN",
    	         "TIMOR ORIENTAL",
    	         "TOGO",
    	         "TONGA",
    	         "TRINIDAD Y TOBAGO",
    	         "TÚNEZ",
    	         "TURKMENISTÁN",
    	         "TURQUÍA",
    	         "TUVALU",
    	         "UCRANIA",
    	         "UGANDA",
    	         "URUGUAY",
    	         "UZBEKISTÁN",
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