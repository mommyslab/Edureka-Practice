package com.edureka.actuatortests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyClassTest {
	
	@Test
	public void test_myclass() {
		MyClass myCLass = new MyClass();
		long resultActual = myCLass.process(3L);
		
		assertEquals(5L, resultActual);
	}

}
