package idv.wilson.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	Environment environment;
	
	@GetMapping
	public String index() {
		String port = environment.getProperty("local.server.port");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return "This is server "+port+", Hello "+userName;
	}

	@GetMapping("/session")
	public Object session(HttpSession session) {

		String port = environment.getProperty("local.server.port");
		return session.getAttribute("testing") + " with port "+ port;
	}
	
	@GetMapping("/session/{value}")
	public Object session(HttpSession session, @PathVariable String value) {
		session.setAttribute("testing", value);
		return session(session);
	}
}
