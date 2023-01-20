package br.com.keeptest.services.exceptions;

public class ObjectNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
