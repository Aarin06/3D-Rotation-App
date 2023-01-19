package assignment;

public class WrongFileFormatException extends Exception{
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	String message;
	
	public WrongFileFormatException(String msg) {
		message = msg;
	}
	
}

