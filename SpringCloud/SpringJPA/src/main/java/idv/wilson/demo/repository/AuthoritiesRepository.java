package idv.wilson.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.wilson.demo.bean.Authorities;
import idv.wilson.demo.bean.Users;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

	//public List<Authorities> findAllByUsername(Users Username);
}
