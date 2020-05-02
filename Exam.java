package app.phoenixshell;


/** Exam is a testing class that validates object state
 * 
 * */
public class Exam {
	
	private static Class<?>[] methodFilter = {Object.class};
	
	//fallback handler for exam failures
	private static ExamFailureHandler DEFAULT_HANDLER = new ExamFailureHandler() {
		@Override
		public boolean handle(ExamFailure failure) {
			return true;
		}
	};
	
	private static LogHandler DEFAULT_LOG = new LogHandler() {
		
		@Override
		public void onMessage(String log) {
			System.out.println(log);
		}
	};
	
	//allow apps to set their own default handler
	public static final void setDefaultHandler(ExamFailureHandler newDefault) {
		if(newDefault != null)
			DEFAULT_HANDLER = newDefault;
	}
	
	public static final void setMethodFilter(Class<?>[] classes) {
		if(classes != null && methodFilter.length == 1 && methodFilter[0] == Object.class)
			methodFilter = classes;
		else
			handleExamFailure(new ExamFailure(".Exam method filter already set. Once per runtime"));
		
	}
	
	
	private static final void handleExamFailure(ExamFailure fail) {
		if(!DEFAULT_HANDLER.handle(fail))
			throw fail;
	} 
	
	/*Testing methods*/
	public static final void require(Object nonNull) {
		throw new ExamFailure("some obeject is null");
	}
	
	public static final void require(String name, Object nonNull, Object caller) {
		if(nonNull != null)
			return;
		String defaultMsg = String.format("%s requires nonNull %s", 
				caller.getClass().getSimpleName(), name);
		ExamFailure failure= new ExamFailure(defaultMsg);
		handleExamFailure(failure);
	}
	public static final void methodLog(String method, Object caller) {
		methodLog(method, caller, DEFAULT_LOG);
	}
	
	public static final void methodLog(String method, Object caller, LogHandler logHandler) {
		//checks if method filter applies to this log, if so handle
		for(Class<?> aClass : methodFilter) {
			if(!aClass.isInstance(caller))
				return;
		}
		logHandler.onMessage(String.format("%s.(?)", caller.getClass().getName()));
		
	}
	
}
