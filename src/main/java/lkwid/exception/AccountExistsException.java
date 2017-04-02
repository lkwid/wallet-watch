package lkwid.exception;

public class AccountExistsException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AccountExistsException() {
		super("Email address already registered");
	}
	
	public AccountExistsException(String message) {
		super(message);
	}
	

}
