package idv.wilson.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.wilson.demo.model.Account;
import idv.wilson.demo.repository.AccountRepository;

@RestController
public class UserController {

	@Autowired
	AccountRepository accounts;
	
	@GetMapping
	private List<Account> getAll(){
		Account account = new Account();
		account.setUserName("testing");
		accounts.save(account);
		 
		
		return accounts.findAll();
	}
}
