package E01Chat.exception;

public class ExceptionChat extends Exception{
	private String code;

	  public ExceptionChat(String code) {
	        this.code = code;
	    }

	    public ExceptionChat(String message, String code) {
	        super(code + ": " + message);
	        this.code = code;
	    }

	    public ExceptionChat(String message, Throwable cause, String code) {
	        super(code + ": " + message, cause);
	        this.code = code;
	    }

	    public ExceptionChat(Throwable cause, String code) {
	        super(cause);
	        this.code = code;
	    }

	    public ExceptionChat(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
	        super(code + ": " + message, cause, enableSuppression, writableStackTrace);
	        this.code = code;
	    }
}
