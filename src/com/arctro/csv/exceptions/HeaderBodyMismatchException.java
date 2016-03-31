package com.arctro.csv.exceptions;

/**
 * Thrown when an header and body column length is not equivalent
 * @author Ben McLean
 * @version 1.0
 */
public class HeaderBodyMismatchException extends Exception{
	private static final long serialVersionUID = -3863134629027013634L;

	public HeaderBodyMismatchException(){}
	
	public HeaderBodyMismatchException(int header, int body){
		super("Header: " + header + ", Body: " + body);
	}
}
