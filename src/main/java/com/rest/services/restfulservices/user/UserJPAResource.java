package com.rest.services.restfulservices.user;

import com.rest.services.restfulservices.post.Post;
import com.rest.services.restfulservices.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDAOSevice userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAll()
	{
		return userRepository.findAll() ;
	}
	
	@GetMapping("/jpa/users/{id}")
	public User retrieveOne(@PathVariable int id)
	{
		User user = userService.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("User id:- "+id);
		}
		return user;
	}	
	
	@PostMapping("/jpa/user")
	public ResponseEntity createUser(@Valid @RequestBody User user)
	{
		User savedUser=userRepository.save(user);
		URI location=ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteOne(@PathVariable int id)
	{
		 userRepository.deleteById(id);
	}

	//implementation of Hateoas
	
	@GetMapping("/jpa/user/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id)
	{
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User id:- "+id);
		}
		EntityModel<Optional<User>> model=EntityModel.of(user);
		Link link=WebMvcLinkBuilder.linkTo(
                methodOn(this.getClass()).retrieveAll()).withRel("all-users");
		model.add(link);
		return model;
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id)
	{
		Optional<User> user= userRepository.findById(id) ;
		if(!user.isPresent())
			throw new UserNotFoundException("id:-"+id);
		return user.get().getPosts();
	}

	@PostMapping("/jpa/user/{id}/post")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post)
	{
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent())
		{
			throw new UserNotFoundException("id:-"+id);
		}
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);

		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
