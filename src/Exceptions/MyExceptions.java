package Exceptions;

import java.util.InputMismatchException;

public class MyExceptions extends InputMismatchException {
	private static final long serialVersionUID = 1L;

	public MyExceptions() {
		super();
	}

	public MyExceptions(String e) throws InterruptedException {
		super(e);
		Thread.sleep(2000);
	}
	
		
}
