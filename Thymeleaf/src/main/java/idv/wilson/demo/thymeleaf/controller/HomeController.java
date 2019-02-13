package idv.wilson.demo.thymeleaf.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index.html");
		mav.addObject("title", "Testing");
		mav.addObject("welcome", "Hello World");
		return mav;
	}

	@Autowired
	private SpringTemplateEngine templateEngine;

	@RequestMapping("/s")
	public @ResponseBody String homeSource() {
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("title", "Testing");
		ctx.setVariable("welcome", "Hello World");
		String output = templateEngine.process("index.html", ctx);
		return output;
	}
}
