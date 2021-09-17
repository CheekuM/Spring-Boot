package com.rest.services.restfulservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDAOSevice userService;
	
	@GetMapping("/users")
	public List<User> retrieveAll()
	{
		List<User> list=userService.findAll();
		if(list==null)
			throw new UserNotFoundException("User list is not available!!");
		return list ;	
	}
	
	@GetMapping("/users/{id}")
	public User retrieveOne(@PathVariable int id)
	{
		User user = userService.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("User id:- "+id);
		}
		return user;
	}	
	
	@PostMapping("/user")
	public ResponseEntity createUser(@Valid @RequestBody User user)
	{
		User savedUser=userService.save(user);
		URI location=ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}	
	
	@DeleteMapping("/users/{id}")
	public void deleteOne(@PathVariable int id)
	{
		User user = userService.deleteById(id);
		if(user==null)
		{
			throw new UserNotFoundException("User id:- "+id);
		}
	}

	//implementation of Hateoas
	
	@GetMapping("/user/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)
	{
		User user = userService.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("User id:- "+id);
		}
		EntityModel<User> model=EntityModel.of(user);
		Link link=WebMvcLinkBuilder.linkTo(
                methodOn(this.getClass()).retrieveAll()).withRel("all-users");
		model.add(link);
		return model;
	}	
}
