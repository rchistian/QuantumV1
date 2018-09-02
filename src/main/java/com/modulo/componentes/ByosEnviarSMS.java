package com.modulo.componentes;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ByosEnviarSMS {

	public static void EnvioSMS(String Telefono, String Mensaje){
	
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("https://api.gateway360.com/api/3.0/sms/send");
		
		if(!Telefono.substring(0, 2).equals("34")) {
			Telefono="34" + Telefono;
		}
		
		StringEntity params=null;
		try {
			  String Parametro = 
					     "{" +
						 "    \"api_key\":\"a69fc8b088a034f14e42b84cd11f47d3\"," +
						 "    \"report_url\":\"http://yourserver.com/callback/script\"," +
						 "    \"concat\":1," +
						 "    \"messages\":["  +
						 "		{"               +
						 "    		\"from\":\"HOTEL\"," +
						 "    		\"to\":\"" + Telefono  +"\","  +
						 "    		\"text\":\""  + Mensaje + "\"," +
						 "    		\"send_at\":\"2018-02-18 17:30:00\""+
						 "		}"     +
						 "	]"  +
						 "}";

			  params = new StringEntity(Parametro);
			  //System.out.println(Parametro);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		request.addHeader("content-type", "application/json");
		request.addHeader("Accept","application/json");
		request.setEntity(params);
		try {
			HttpResponse response = httpClient.execute(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
