package channelpopularity.exceptions;

@SuppressWarnings("serial")
public class NegativeLikesException extends RuntimeException{

	private String videoName;
	public NegativeLikesException(String videoName) {
		this.videoName = videoName;
	}
	@Override
	public String toString() {
		return "NegativeLikesException: Decrease in likes cannot be more than total number of likes for "+videoName;
	}
	
	
	
	
}
