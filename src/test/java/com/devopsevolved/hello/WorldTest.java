package com.devopsevolved.hello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	private World world;
	
	@Before
	public void setup() {
		world = new World();
	}
	
	@Test
	public void test() {
		assertEquals("Hello!", world.say("Hello!"));
	}

}
