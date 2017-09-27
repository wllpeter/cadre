package com.ddb.xaplan.cadre.exception;

/**
 * 
 * @author chenzhaopeng
 *
 */
public class ParameterErrorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7738873754824922144L;
	public ParameterErrorException(){
		super();
	}
	public ParameterErrorException(String message){
		super(message);
	}
	
	public static void message(String message) {
		throw new ParameterErrorException(message);		
	}

}
