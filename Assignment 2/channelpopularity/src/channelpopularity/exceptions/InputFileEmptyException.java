package channelpopularity.exceptions;

@SuppressWarnings("serial")
public class InputFileEmptyException extends RuntimeException {
	private String inputFile;
	public InputFileEmptyException(String inputFile) {
		this.inputFile = inputFile;
	}
	@Override
	public String toString() {
		return "InputFileEmptyException: Given Input File (" + inputFile+ ") is Empty. ";
	}
	
	

}
