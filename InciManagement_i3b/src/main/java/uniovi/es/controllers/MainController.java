package uniovi.es.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import uniovi.es.entities.Coordinates;
import uniovi.es.entities.Incidence;
import uniovi.es.utils.AgentLogin;
import uniovi.es.services.AgentsService;
import uniovi.es.services.IncidentsService;



@Controller
public class MainController {

	@Autowired
	private IncidentsService incidentsService;
	@Autowired
	private AgentsService agentsService;
	
	private String agentsURL = "http://localhost:8085/checkAgent";
	

	@RequestMapping("/logIn")
	public String log(HttpSession session) {
		session.setAttribute("user", "");
		session.setAttribute("kind", "");
		session.setAttribute("map", "");
		return "logIn";
	}

	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public String log(HttpSession session, @ModelAttribute AgentLogin u, RedirectAttributes redirect) {
		/*if (u.getKind() < 0 || u.getKind() > 3) {
			return "redirect:logIn";
		}*/
		
		Gson gson = new Gson();
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(agentsURL);
			StringEntity params = new StringEntity(gson.toJson(u));
			request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	session.setAttribute("user", u.getLogin());
				session.setAttribute("kind", u.getKindCode());
				session.setAttribute("map", new HashMap<String, String>());
				redirect.addFlashAttribute("user", u);
				redirect.addFlashAttribute("name", u.getLogin());
				redirect.addFlashAttribute("kind", u.getKindCode());
				return "redirect:createIncidence";
		    }
		    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:logIn";
	}
	
	@RequestMapping("/createIncidence")
	public String createIncidenceGet(HttpSession session, Model model) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		model.addAttribute("message", new Incidence());
		session.setAttribute("map", new HashMap<String, String>());
		return "createInci";
	}

	@RequestMapping(value = "/createIncidence", method = RequestMethod.POST)
	public String createIncidencePost(HttpSession session, @Validated Incidence message) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		// set user
		message.setUsername((String) session.getAttribute("user"));
		message.setUsertype(Integer.valueOf((String) session.getAttribute("kind")));
		// set customfields
		message.setCustomFields((Map<String, String>) session.getAttribute("map"));
		// set Coords
		Coordinates coor = new Coordinates();
		message.setInci_location("" + coor.getCoordinates());
		// set operator
		message.setOperatorId(incidentsService.getRandomOperator());
		
		incidentsService.addIncident(message);
		session.setAttribute("map", new HashMap<String, String>());
		return "redirect:listIncidences";
	}
	
	@RequestMapping(value = "/add-custom-field/{key}/{value}")
	public String addCustomField(HttpSession session, @PathVariable("key") String key,
			@PathVariable("value") String value, Model model) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		Map<String, String> map = (Map<String, String>) session.getAttribute("map");
		map.put(key, value);
		model.addAttribute("fieldsMap", map);
		return "createInci :: tableFields";
	}

	@RequestMapping("/listIncidences")
	public String queryInfo(Model model, HttpSession session) {
		if (session.getAttribute("user").equals("")) {
			return "redirect:logIn";
		}
		List<Incidence> l = incidentsService.getAgentIncidents((String) session.getAttribute("user"));
		model.addAttribute("incidenceList", l);
		return "listInci";
	}

	

}