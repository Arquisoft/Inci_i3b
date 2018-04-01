package uniovi.es.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniovi.es.utils.AgentLogin;
import uniovi.es.utils.Connection;

@Controller
public class HomeController {
	
	@GetMapping("/" )
	public String index() {
		return "redirect:/logIn";
	}
	
	@RequestMapping("/logIn")
	public String log(HttpSession session) {
		session.setAttribute("user", "");
		session.setAttribute("kind", "");
		session.setAttribute("map", "");
		return "logIn";
	}

	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public String log(HttpSession session, @ModelAttribute AgentLogin u, RedirectAttributes redirect) {

		try {
			if (Connection.isValidLogin(u)) {
				session.setAttribute("user", u.getLogin());
				session.setAttribute("kind", u.getKindCode());
				session.setAttribute("map", new HashMap<String, String>());
				redirect.addFlashAttribute("user", u);
				redirect.addFlashAttribute("name", u.getLogin());
				redirect.addFlashAttribute("kind", u.getKindCode());
				return "redirect:createIncidence";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:logIn";
	}
}
