package net.zndo.training.exceptions;

public class UdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UdException() {
		super();
	}

	public UdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UdException(String message, Throwable cause) {
		super(message, cause);
	}

	public UdException(String message) {
		super(message);
	}

	public UdException(Throwable cause) {
		super(cause);
	}

	
}
