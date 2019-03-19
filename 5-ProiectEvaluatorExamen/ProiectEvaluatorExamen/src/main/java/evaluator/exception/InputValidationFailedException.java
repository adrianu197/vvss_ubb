package evaluator.exception;

public class InputValidationFailedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InputValidationFailedException(String message){
		super(message);
	}

}
