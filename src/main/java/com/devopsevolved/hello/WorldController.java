package com.devopsevolved.hello;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class WorldController {

	private World world;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> sayHello(){
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("message", world.say("Hello"));
		return ResponseEntity.ok(results);
	}
	
	@Autowired
	public void setWorld(World world) {
		this.world = world;
	}
	
}
