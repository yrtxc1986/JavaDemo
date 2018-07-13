package idv.wilson.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.wilson.demo.bean.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

}
