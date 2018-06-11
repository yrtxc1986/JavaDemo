package idv.wilson.demo.springWebApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@ApiOperation(value = "Request User List", notes = "Get All User")
	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String listUser() {
		return "List All User";
	}
}
