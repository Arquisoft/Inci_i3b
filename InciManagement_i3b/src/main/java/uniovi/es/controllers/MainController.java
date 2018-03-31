package uniovi.es.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniovi.es.entities.Coordinates;
import uniovi.es.entities.Incidence;
import uniovi.es.entities.UserInfo;
import uniovi.es.services.AgentsService;
import uniovi.es.services.IncidentsService;



@Controller
public class MainController {

	@Autowired
	private IncidentsService incidentsService;
	@Autowired
	private AgentsService agentsService;
	

	@RequestMapping("/logIn")
	public String log(HttpSession session) {
		session.setAttribute("user", "");
		session.setAttribute("kind", "");
		session.setAttribute("map", "");
		return "logIn";
	}

	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public String log(HttpSession session, @ModelAttribute UserInfo u, RedirectAttributes redirect) {
		/*if (u.getKind() < 0 || u.getKind() > 3) {
			return "redirect:logIn";
		}*/
		if(agentsService.getAgent(u.getName(), u.getPassword() , u.getKind()+"") == null) 
		{
			return "redirect:logIn";
		}
		else
		{
			session.setAttribute("user", u.getName());
			session.setAttribute("kind", u.getKind());
			session.setAttribute("map", new HashMap<String, String>());
			redirect.addFlashAttribute("user", u);
			redirect.addFlashAttribute("name", u.getName());
			redirect.addFlashAttribute("kind", u.getKind());
			return "redirect:createIncidence";
		}
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
		message.setUsertype((int) session.getAttribute("kind"));
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