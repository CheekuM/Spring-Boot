package com.rest.services.restfulservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// field1,field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveBean() {
		SomeBean someBean = new SomeBean("bean1", "bean2", "bean3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}

	// field2 & field3
	@GetMapping("/filteringlist")
	public MappingJacksonValue retrieveBeanlist() {
		List<SomeBean> list
		=
		Arrays.asList(new SomeBean("bean1", "bean2", "bean3"),
				new SomeBean("bean11", "bean12", "bean31"));
		
		
		SimpleBeanPropertyFilter filter 
			= SimpleBeanPropertyFilter.filterOutAllExcept("field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
}
