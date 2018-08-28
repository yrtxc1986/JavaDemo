package idv.wilson.demo.defaultImplements.service;

import org.springframework.stereotype.Component;

@Component
public class CustHelloServiceImplA implements HelloService {

	@Override
	public String sayHello(String name) {
		return "This is Cust A Impl: Hello " + name;
	}

}
