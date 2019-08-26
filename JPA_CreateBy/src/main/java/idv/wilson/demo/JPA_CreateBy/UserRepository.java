package idv.wilson.demo.JPA_CreateBy;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();

	User findByName(String UserName);
}
