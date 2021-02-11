package idv.wilson.demo.jwt;

import org.springframework.stereotype.Component;

@Component
public class TaskAdditionalPermit implements AdditionalPermit {

	@Override
	public String get(String userId) {
		if (userId.equals("3")) {
			return "ROLE_TASK";
		} else
			return "";
	}

}
