package textdecorators;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textdecorators.inputdetails.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

public class KeywordDecorator extends AbstractTextDecorator{
	/**
	 * For decorator
	 */
	private AbstractTextDecorator atd;
	private InputDetails id;

	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	/**
	 * Override Input Details.Process so that keywords are found and replaced with given requirement
	 */
	@Override
	public void processInputDetails() {
		// TODO Auto-generated method stub
		/**
		 * Set debugger
		 */
		MyLogger.getInstance().writeMessage("Entered Keyword Decorator", DebugLevel.KEYWORD_DECORATOR);
		/**
		 * Get keywords
		 */
		List<String> keywords = id.getKeywords();
		/**
		 * Get all sentences
		 */
		List<StringBuilder> paragraph = id.getSentences();
		Iterator<StringBuilder> sentenceIter;
		/**
		 * For all the keywords, use a while loop to iterate 
		 * through all words in all the sentences. If keywords are found, 
		 * do as required.
		 * Keywords are found using Pattern and Matcher.
		 */
		for(String spellCheck : keywords) {
			sentenceIter = paragraph.iterator();
			while(sentenceIter.hasNext()) {
				StringBuilder sentence = sentenceIter.next();
				int increment =0;

				Pattern p = Pattern.compile("(^|\t|\n|_|,| )"+spellCheck+"(\t|\n|_|$|,| )",Pattern.CASE_INSENSITIVE);
				Matcher matcher = p.matcher(sentence);
				while(increment< sentence.length()) {

					if( matcher.find(increment) ){
						int index = matcher.start();

						if(Character.toLowerCase(sentence.charAt(index)) == Character.toLowerCase(spellCheck.charAt(0))) {
							sentence.insert(index, "KEYWORD_");
							increment =index+8+spellCheck.length(); 
							sentence.insert(increment, "_KEYWORD");
							increment += 8;
							MyLogger.getInstance().writeMessage("KEYWORD_"+spellCheck+"_KEYWORD", DebugLevel.KEYWORD_DECORATOR);
							continue;
						}

						if(sentence.charAt(index) == '_') {

							int tempIndex = index;
							for(int i =index;i>=0;i--) {
								if((sentence.charAt(i) == '\t') || (sentence.charAt(i) == ',') || (sentence.charAt(i) == ' ') || (sentence.charAt(i) == '\n')  || (sentence.charAt(i) == '\f')  || (sentence.charAt(i) == '\b')  || (sentence.charAt(i) == '\r')) {
									index = i;
									break;
								}

							}
							if(tempIndex == index) {
								index =0 ;
							}
							if(index!=0) {
								sentence.insert(index+1, "KEYWORD_");
								increment =index+9+spellCheck.length()+(tempIndex - index)*2; 
								sentence.insert(increment, "_KEYWORD");
								increment += 8;
								MyLogger.getInstance().writeMessage("KEYWORD_"+spellCheck+"_KEYWORD", DebugLevel.KEYWORD_DECORATOR);
								continue;
							}
							else {
								sentence.insert(index, "KEYWORD_");
								increment =index+10+spellCheck.length()+(tempIndex - index)*2; 
								sentence.insert(increment, "_KEYWORD");
								increment += 8;
								MyLogger.getInstance().writeMessage("KEYWORD_"+spellCheck+"_KEYWORD", DebugLevel.KEYWORD_DECORATOR);
								continue;
							}
						}

						else {	
							sentence.insert(index+1, "KEYWORD_");
							increment =index+9+spellCheck.length(); 
							sentence.insert(increment, "_KEYWORD");
							increment += 8;
							MyLogger.getInstance().writeMessage("KEYWORD_"+spellCheck+"_KEYWORD", DebugLevel.KEYWORD_DECORATOR);
							continue;
						}

					}
					else {
						break;
					}


				}
			}
		}
		MyLogger.getInstance().writeMessage("EXIT: Keyword Decorator", DebugLevel.KEYWORD_DECORATOR);
		if (null != atd) {
			atd.processInputDetails();
		}





	}

	@Override
	public String toString() {
		return "KeywordDecorator [atd=" + atd + ", id=" + id + "]";
	}


}