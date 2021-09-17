package com.rest.services.restfulservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET, value="/helloWorld")
	public String helloWorld()
	{
		return "hello-world";
	}

	//hello-world-bean
	@RequestMapping(method = RequestMethod.GET, value="/helloWorldBean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("hello-world");
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/hello/{pathVariable}")
	public HelloWorldBean helloWorldPath(@PathVariable String pathVariable)
	{
		return new HelloWorldBean(String.format("Hello World %s", pathVariable));
	}
	
	//Internationalized
	@RequestMapping(method = RequestMethod.GET, value="/helloWorldintern")
	public String helloWorldInternationalized( @RequestHeader(name="Accept-Language", required = false) Locale locale)
	{
		
		
		return messageSource.getMessage("good.morning.message", null, "Default Message" ,LocaleContextHolder.getLocale());
		//return "hello-world";
		//en = Hello World
		//nl = goede Moregn
		//fr = Bonjour
		
	}

}
