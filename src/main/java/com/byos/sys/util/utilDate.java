package com.byos.sys.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

public class utilDate {

	public utilDate(){}
	
	
	public static java.sql.Timestamp horaDelDia(){
		GregorianCalendar d = new GregorianCalendar();
		d.set(d.get(d.YEAR), d.get(d.MONTH),
				d.get(d.DATE), d.get(d.HOUR_OF_DAY),
				d.get(d.MINUTE), d.get(d.SECOND));
		
		java.sql.Timestamp ts = new java.sql.Timestamp(d.getTimeInMillis());
		
		return ts;
	}
	
	
	public static java.sql.Time fechaDelDia(){
		GregorianCalendar d = new GregorianCalendar();
		d.set(d.get(d.YEAR), d.get(d.MONTH),
				d.get(d.DATE));
		
		java.sql.Time ts = new java.sql.Time(d.getTimeInMillis());
		
		return ts;
	}
	
	public static java.sql.Time fechaDeAyer(){
		GregorianCalendar d = new GregorianCalendar();
		d.set(d.get(d.YEAR), d.get(d.MONTH),
				d.get(d.DATE));
		
		java.sql.Time ts = new java.sql.Time(d.getTimeInMillis());
		
		return ts;
	}
	
	

	
	
	
	
}
