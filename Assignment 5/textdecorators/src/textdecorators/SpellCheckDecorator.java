package textdecorators;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import textdecorators.inputdetails.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

public class SpellCheckDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	/**
	 * Override Input Details.
	 */
	@Override
	public void processInputDetails() {
		// TODO Auto-generated method stub
		MyLogger.getInstance().writeMessage("Spell Check Decorator:", DebugLevel.SPELL_CHECK_DECORATOR);
		List<String> misspelledWords = id.getMisspelled();
		List<StringBuilder> paragraph = id.getSentences();
		Iterator<StringBuilder> sentenceIter;
		for(String spellCheck : misspelledWords) {
			sentenceIter = paragraph.iterator();
			/**
			 * Used same algorithm as before
			 * Used pattern and matcher for finding the required word.
			 * Then do as requirement.
			 */
			while(sentenceIter.hasNext()) {
				StringBuilder sentence = sentenceIter.next();
				int increment =0;
				Pattern p = Pattern.compile("(^|\t|\n|_|,| )"+spellCheck+"(\t|\n|_|$|,| )",Pattern.CASE_INSENSITIVE);
				Matcher matcher = p.matcher(sentence);
				while(increment< sentence.length()) {
					if( matcher.find(increment) ){
						int index = matcher.start();

						if(Character.toLowerCase(sentence.charAt(index)) == Character.toLowerCase(spellCheck.charAt(0))) {

							sentence.insert(index, "SPELLCHECK_");
							increment =index+11+spellCheck.length(); 
							sentence.insert(increment, "_SPELLCHECK");
							increment += 11;
							MyLogger.getInstance().writeMessage("SPELLCHECK_"+spellCheck+"_SPELLCHECK", DebugLevel.SPELL_CHECK_DECORATOR);
						}

						if(sentence.charAt(index) == '_') {

							int tempIndex = index;
							for(int i =index;i>=0;i--) {
								if((sentence.charAt(i) == '\t') || (sentence.charAt(i) == ',') || (sentence.charAt(i) == ' ') || (sentence.charAt(i) == '\n')  || (sentence.charAt(i) == '\f')  || (sentence.charAt(i) == '\b')  || (sentence.charAt(i) == '\r')){
									index = i;
									break;
								}

							}
							if(tempIndex == index) {
								index =0 ;
							}
							if(index!=0) {
								sentence.insert(index+1, "SPELLCHECK_");
								increment =index+12+spellCheck.length()+(tempIndex - index)*2; 
								sentence.insert(increment, "_SPELLCHECK");
								increment += 11;
								MyLogger.getInstance().writeMessage("SPELLCHECK_"+spellCheck+"_SPELLCHECK", DebugLevel.KEYWORD_DECORATOR);
								continue;
							}
							else {
								sentence.insert(index, "SPELLCHECK_");
								increment =index+13+spellCheck.length()+(tempIndex - index)*2; 
								sentence.insert(increment, "_SPELLCHECK");
								increment += 11;
								MyLogger.getInstance().writeMessage("SPELLCHECK_"+spellCheck+"_SPELLCHECK", DebugLevel.KEYWORD_DECORATOR);

								continue;
							}
						}



						else {
							sentence.insert(index+1, "SPELLCHECK_");
							increment =index+12+spellCheck.length(); 
							sentence.insert(increment, "_SPELLCHECK");
							increment += 11;
							MyLogger.getInstance().writeMessage("SPELLCHECK_"+spellCheck+"_SPELLCHECK", DebugLevel.SPELL_CHECK_DECORATOR);
						}
					}

					else {
						break;
					}


				}
			}
		}
		MyLogger.getInstance().writeMessage("Exit : Spell Check Decorator", DebugLevel.SPELL_CHECK_DECORATOR);
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	@Override
	public String toString() {
		return "SpellCheckDecorator [atd=" + atd + ", id=" + id + "]";
	}
}