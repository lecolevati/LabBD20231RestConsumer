package br.edu.fateczl.springrestconsumer.uteis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Component
public class HttpConnection {

	public String getOp(String httpUrl, String urlComp) throws MalformedURLException, IOException, ProtocolException {
		Charset charset = Charset.forName("UTF8");
		URL url = new URL(httpUrl+urlComp);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(RequestMethod.GET.toString());
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
			throw new IOException("Erro código HTTP:"+conn.getResponseCode());
		}
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, charset);
		BufferedReader br = new BufferedReader(isr);
		String saida = br.readLine();
		StringBuffer buffer = new StringBuffer();
		while (saida != null) {
			buffer.append(saida);
			saida = br.readLine();
		}
		return buffer.toString();
	}
	
	
	public String sendOp(String httpUrl, RequestMethod method, Object o) throws IOException {
		
		URL url = new URL(httpUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(20000);
		conn.setReadTimeout(20000);
		conn.setDoOutput(true);
		conn.setUseCaches(true);
		conn.setRequestMethod(method.toString());
		conn.setRequestProperty("Accept", MediaType.TEXT_PLAIN_VALUE);
		conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		OutputStream outputStream = conn.getOutputStream();
		byte[] b = objToJson(o).getBytes("UTF-8");
		outputStream.write(b);
		outputStream.flush();
		outputStream.close();

		InputStream inputStream = conn.getInputStream();
		byte[] res = new byte[2048];
		int i = 0;
		StringBuffer response = new StringBuffer();
		while ((i = inputStream.read(res)) != -1) {
			response.append(new String(res, 0, i));
		}
		inputStream.close();

		return response.toString();
	}
	
	private String objToJson(Object o) {
		Gson gson = new Gson();
		return gson.toJson(o);
	}
	
}
