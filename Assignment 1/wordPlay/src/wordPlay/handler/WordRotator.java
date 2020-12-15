package wordPlay.handler;
import java.util.LinkedList;
public class WordRotator {	
	
	
	
	/**
	 * Rotates the given string from mid to end and then 0 to mid. Checks
	 * for period.
	 * @param input : Given String
	 * @param mid : mid position
	 * @param end : end position
	 * @param fullstop : Checks for period
	 * @return the processed word(String)
	 */
	public LinkedList<String> Rotate(String input, int mid,int end, int fullstop) {
		LinkedList<String> processedString = new LinkedList<String>();
		
		for(int i=mid;i<end;i++) {
			processedString.add(String.valueOf(input.charAt(i)));
		}		
		for(int i=0;i<mid;i++) {
			processedString.add(String.valueOf(input.charAt(i)));
		}
		if(fullstop ==1) {
			processedString.add(".");
			processedString.add("\n");

		}
		else {
			processedString.add(" ");

		}
		return processedString;
	}	
	}
