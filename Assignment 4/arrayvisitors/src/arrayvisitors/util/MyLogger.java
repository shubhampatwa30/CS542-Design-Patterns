package arrayvisitors.util;

public class MyLogger{
	public static MyLogger myLogger_instance = null;
	public static MyLogger getInstance() {
		if(myLogger_instance == null) {
			myLogger_instance = new MyLogger();
		}
		return myLogger_instance;
	}


	public  enum DebugLevel {CONSTRUCTOR,MISSING_INTS_VISITOR,COMMON_INTS_VISITOR, POPULATE_MYARRAY_VISITOR, NONE
	};

	private  DebugLevel debugLevel;

	public  void setDebugValue (int levelIn) {
		switch (levelIn) {
		case 4: debugLevel = DebugLevel.MISSING_INTS_VISITOR; break;
		case 3: debugLevel = DebugLevel.COMMON_INTS_VISITOR; break;
		case 2: debugLevel = DebugLevel.POPULATE_MYARRAY_VISITOR; break;
		case 1: debugLevel = DebugLevel.CONSTRUCTOR; break;
		default: debugLevel = DebugLevel.NONE; break;
		}
	}

	public  void setDebugValue (DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public void writeMessage (String  message,DebugLevel levelIn ) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}