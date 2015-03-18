package edu.sjsu.cmpe275.lab1;

public class UnauthorizedException extends Exception{
	
	/**
	 * Custom Exception class
	 */
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException() {
		super();
	}
	
	public UnauthorizedException(String arg0) {
	super(arg0);
	}

}
