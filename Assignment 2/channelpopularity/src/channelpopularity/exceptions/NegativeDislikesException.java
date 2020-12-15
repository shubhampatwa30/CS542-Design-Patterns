package channelpopularity.exceptions;

@SuppressWarnings("serial")
public class NegativeDislikesException extends RuntimeException{
	private String videoName;
	public NegativeDislikesException(String videoName) {
		this.videoName = videoName;
	}
	@Override
	public String toString() {
		return "NegativeDislikesException: Decrease in dislikes cannot be more than total number of dislikes for "+videoName;
	}
	
	
}
