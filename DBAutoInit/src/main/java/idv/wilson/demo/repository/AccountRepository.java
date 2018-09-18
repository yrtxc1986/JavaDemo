package idv.wilson.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import idv.wilson.demo.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	List<Account> findAll();
	
	Account findByUserName(String UserName);
}
