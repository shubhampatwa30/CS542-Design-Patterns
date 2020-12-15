package channelpopularity.operation;


public enum Operation {

	ADD{
		public String toString() {
			return "__VIDEO_ADDED::";
		}
	}, REM{
		public String toString() {
			return "__VIDEO_REMOVED::";
		}
	}, MET{
		public String toString() {
		return "__POPULARITY_SCORE_UPDATE::";	
		}
	}, AD_{
		public String toString() {
			return "__AD_REQUEST::";
		}
	};





}
