package com.devopsevolved.hello;

import org.springframework.stereotype.Component;

/**
 * Doing important worldly stuff....
 * 
 * @author downeyt
 *
 */
@Component
public class World {

	/**
	 * Say the message back.
	 * 
	 * @param message A message.
	 * @return The message back.
	 */
	public String say(String message) {
		return message;
	}
	
}
