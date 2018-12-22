package thebergers.adventofcode2018.day1;

public class DataParseException extends Exception {

	public DataParseException(String message) {
		super(message);
	}

	public DataParseException(String message, Exception e) {
		super(message, e);
	}

	private static final long serialVersionUID = 1L;

}
