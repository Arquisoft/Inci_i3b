package uniovi.es.entities;

import java.util.Map;

import org.springframework.data.annotation.Id;

import com.google.gson.Gson;

import uniovi.es.services.IncidentsService;

/**
 * Created by herminio on 27/2/17.
 */
public class Incidence {
	
	@Id
	private String inciId;
	private String inci_description;
	private String inci_name;
	private String inci_location;
	private int state;
	private String[] tags;
	private String username;
	private String inci_info;
	private int usertype;
	private Map<String, String> customFields;
	

	
	public String getInciId() {
		return inciId;
	}



	public void setInciId(String inciId) {
		this.inciId = inciId;
	}



	public String getInci_description() {
		return inci_description;
	}



	public void setInci_description(String inci_description) {
		this.inci_description = inci_description;
	}



	public String getInci_name() {
		return inci_name;
	}



	public void setInci_name(String inci_name) {
		this.inci_name = inci_name;
	}



	public String getInci_location() {
		return inci_location;
	}



	public void setInci_location(String inci_location) {
		this.inci_location = inci_location;
	}



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public String[] getTags() {
		return tags;
	}



	public void setTags(String[] tags) {
		this.tags = tags;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getInci_info() {
		return inci_info;
	}



	public void setInci_info(String inci_info) {
		this.inci_info = inci_info;
	}



	public int getUsertype() {
		return usertype;
	}



	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}



	public Map<String, String> getCustomFields() {
		return customFields;
	}



	public void setCustomFields(Map<String, String> customFields) {
		this.customFields = customFields;
	}



	@Override 
	public String toString() {
		Gson gson = new Gson();
		String kafkaMessage = gson.toJson(this);
		return kafkaMessage;
		
	}

}
