package com.edureka.actuatortests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CalculatorTest {
	
	@Mock
	private MyClass myClassMock;
	@Mock
	private YourClass yourClassMock;
	
	@Test
	public void test_add_Solitary() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(myClassMock.process(ArgumentMatchers.anyLong())).thenReturn(5L);
		Mockito.when(yourClassMock.process(ArgumentMatchers.anyLong())).thenReturn(5L);
		
		Calculator calculator = new Calculator(myClassMock, yourClassMock);
		
		long actualResult = calculator.add(2L, 3L);
		
		assertEquals(10L, actualResult);
	}
	
	@Test
	public void test_add_Sociable() {
		Calculator calculator = new Calculator(new MyClass(), new YourClass());
		
		long actualResult = calculator.add(2L, 3L);
		
		assertEquals(6L, actualResult);
	}

}
