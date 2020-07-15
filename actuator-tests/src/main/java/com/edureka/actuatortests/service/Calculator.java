package com.edureka.actuatortests.service;

public class Calculator {
	
	private final MyClass myClass;
	private final YourClass yourclass;
	
	public Calculator(MyClass myClass, YourClass yourclass) {
		this.myClass = myClass;
		this.yourclass = yourclass;
	}

	public long add(long a, long b) {
		long c = myClass.process(a); // c = a + 2
		long d = yourclass.process(b); // d = b - 1;
		long result = c + d;
		
		return result;
	}

}
