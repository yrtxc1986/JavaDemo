package idv.wilson.demo.springWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebMain {

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld() {
		return "HelloWorld";
		// return jdbcTemplate.queryForObject("Select Version()", String.class);
	}
}
