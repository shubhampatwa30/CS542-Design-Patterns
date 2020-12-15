package studentskills.util;

public class MyLogger{


	public static enum DebugLevel {MODIFY,RE_INSERT,INSERT,CONSTRUCTOR,MODIFY_FILE_POLL, INPUT_FILE_POLL, NONE
	};

	private static DebugLevel debugLevel;



	public static void setDebugValue (int levelIn) {
		switch (levelIn) {
		case 6: debugLevel = DebugLevel.MODIFY; break;
		case 5: debugLevel = DebugLevel.RE_INSERT; break;
		case 4: debugLevel = DebugLevel.INSERT; break;
		case 3: debugLevel = DebugLevel.CONSTRUCTOR; break;
		case 2: debugLevel = DebugLevel.MODIFY_FILE_POLL; break;
		case 1: debugLevel = DebugLevel.INPUT_FILE_POLL; break;
		default: debugLevel = DebugLevel.NONE; break;
		}
	}

	public static void setDebugValue (DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public static void writeMessage (String     message  ,
			DebugLevel levelIn ) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}