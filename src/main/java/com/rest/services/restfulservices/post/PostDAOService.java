package com.rest.services.restfulservices.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.services.restfulservices.user.User;

@Component
public class PostDAOService {

	HashMap<User, ArrayList<Post>> map = new HashMap<>();

	public List<Post> retrieveAll(Integer id) {
		for (Map.Entry<User, ArrayList<Post>> e : map.entrySet()) {
			if (e.getKey().getId() == id)
				return e.getValue();
		}
		return null;
	}
	
	public Post retrieveOne(int userId,int postId)
	{
		for(Map.Entry<User,ArrayList<Post>> e:map.entrySet())
		{
			if(e.getKey().getId()==userId)
			{
				for(int i=0;i<e.getValue().size();i++)
				{
					if(e.getValue().get(i).getId()==postId)
						return e.getValue().get(i);
				}
			}
		}
		return null;
	}
	
//	public void createPost(User )

}
