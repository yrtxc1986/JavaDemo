package idv.wilson.demo.devonly;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import idv.wilson.demo.bean.Authorities;
import idv.wilson.demo.bean.Users;
import idv.wilson.demo.repository.AuthoritiesRepository;
import idv.wilson.demo.repository.UsersRepository;

@Profile("dev")
@Component
public class DevDataInit {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AuthoritiesRepository authoritiesRepository;

	@PostConstruct
	public void DataInit() {
		Users wilson = new Users("wilson", "123456", true);
		usersRepository.save(wilson);
		Users admin = new Users("admin", "123456", true);
		usersRepository.save(admin);

		authoritiesRepository.save(new Authorities(1l, wilson, "USER"));
		authoritiesRepository.save(new Authorities(2l, admin, "USER"));
		authoritiesRepository.save(new Authorities(3l, admin, "ADMIN"));
	}

}
