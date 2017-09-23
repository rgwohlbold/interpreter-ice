package interpreter;

public class InvalidIdentifierException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public InvalidIdentifierException() {
		super();
	}
	
	public InvalidIdentifierException(String identifier) {
		super();
		this.msg = "Parser Error, Invalid identifier: " + identifier;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
