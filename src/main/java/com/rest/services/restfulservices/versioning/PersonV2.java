package com.rest.services.restfulservices.versioning;

public class PersonV2 {

	private Name name;
	
	PersonV2() {
		
	}
	PersonV2(Name name) {
		super();
		this.name=name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
}
