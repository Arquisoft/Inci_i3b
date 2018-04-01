package uniovi.es.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

public class Connection {
	
	private static String agentsURL = "http://localhost:8085/checkAgent";
	
	public static boolean isValidLogin(AgentLogin agentLogin) throws Exception {
		Gson gson = new Gson();
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(agentsURL);
		StringEntity params = new StringEntity(gson.toJson(agentLogin));
		request.addHeader("content-type", "application/json");
		request.setEntity(params);
		HttpResponse response = httpClient.execute(request);
		return response.getStatusLine().getStatusCode() == 200;

	}
}
