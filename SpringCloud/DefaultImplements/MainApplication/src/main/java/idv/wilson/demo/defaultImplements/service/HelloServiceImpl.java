package idv.wilson.demo.defaultImplements.service;

import idv.wilson.demo.defaultImplements.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "This is Defaul Impl: Hello " + name;
	}

}
