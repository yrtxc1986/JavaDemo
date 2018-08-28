package idv.wilson.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import idv.wilson.demo.defaultImplements.service.HelloService;

@RestController
public class HomeController {

	@Autowired
	HelloService service;

	@GetMapping("/hello")
	public String SayHello(@RequestParam(value = "name") String name) {
		return service.sayHello(name);
	}
}
