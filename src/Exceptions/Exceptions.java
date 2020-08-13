package Exceptions;

import java.util.InputMismatchException;

public class Exceptions extends InputMismatchException {
	private static final long serialVersionUID = 1L;

	public Exceptions() {
		super();
	}

	public Exceptions(String e) throws InterruptedException {
		super(e);
		Thread.sleep(2000);
	}
	
		
}
