package app.phoenixshell;

public class ExamFailure extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4391900961978842409L;
	
	public String message;
	
	public ExamFailure(String msg) {
		this.message = msg;
	}

}
