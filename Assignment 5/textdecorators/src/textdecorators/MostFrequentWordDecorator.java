package textdecorators;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import textdecorators.inputdetails.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

public class MostFrequentWordDecorator extends AbstractTextDecorator{
	private AbstractTextDecorator atd;
	private InputDetails id;
	/**
	 * To store the count of all the wors
	 */
	private HashMap<String,Integer> countMapper;
	/**
	 * Most frequent keyword
	 */
	private String word;

	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
		countMapper = new HashMap<String,Integer>();
	}

	@Override
	public void processInputDetails() {
		// TODO Auto-generated method stub
		/**
		 * Set debugger
		 */
		MyLogger.getInstance().writeMessage("Most Frequent Word Decorator:", DebugLevel.MOST_FREQ_WORD_DECORATOR);
		/**
		 * Get all sentences as in a paragraph
		 */
		List<StringBuilder> paragraph = id.getSentences();
		Iterator<StringBuilder> iter = paragraph.iterator();
		/**
		 * Create a dictionary for storing the words and their frequency
		 */
		while(iter.hasNext()) {
			StringBuilder sentence = iter.next();
			String[] wordsArray = sentence.toString().toLowerCase().split("[,\\s+]+");
			for(String word : wordsArray) {
				if(countMapper.containsKey(word)) {
					int count = countMapper.get(word);
					countMapper.replace(word, count+1);
				}
				else {
					countMapper.put(word, 1);
				}
			}
		}
		int count = 0;
		int curValue = 0;
		/**
		 * use a simple algorithm such that if new word has count greater
		 * than previous word, set that word as the most
		 * frequent used. Then do as requirement.
		 */
		for(String key :countMapper.keySet()) {
			curValue = countMapper.get(key);
			if(curValue>= count) {
				count = curValue;
				word = key;
			}
		}
		Iterator<StringBuilder> sentenceIter = paragraph.iterator();
		sentenceIter = paragraph.iterator();
		/**
		 * Same algorithm as before used in keyword decorator. If word is found
		 * using pattern and matcher, do as requirement.
		 */
		while(sentenceIter.hasNext()) {
			StringBuilder sentence = sentenceIter.next();
			int increment =0;

			Pattern p = Pattern.compile("(^|\t|\n|_|,| )"+word+"(\t|\n|_|$|,| )",Pattern.CASE_INSENSITIVE);
			Matcher matcher = p.matcher(sentence);
			while(increment< sentence.length()) {
				if( matcher.find(increment) ){
					int index = matcher.start();
					if(Character.toLowerCase(sentence.charAt(index))==(word.charAt(0))) {
						sentence.insert(index, "MOST_FREQUENT_");
						increment =index+14+word.length(); 
						sentence.insert(increment, "_MOST_FREQUENT");
						increment += 14;
						MyLogger.getInstance().writeMessage("MOST_FREQUENT_"+word+"_MOST_FREQUENT", DebugLevel.MOST_FREQ_WORD_DECORATOR);
					}
					else {
						sentence.insert(index+1, "MOST_FREQUENT_");
						increment =index+15+word.length(); 
						sentence.insert(increment, "_MOST_FREQUENT");
						increment += 14;
						MyLogger.getInstance().writeMessage("MOST_FREQUENT_"+word+"_MOST_FREQUENT", DebugLevel.MOST_FREQ_WORD_DECORATOR);
					}

				}
				else {
					break;
				}
			}
		}
		MyLogger.getInstance().writeMessage("EXIT:Most Frequent Word Decorator", DebugLevel.MOST_FREQ_WORD_DECORATOR);
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	@Override
	public String toString() {
		return "MostFrequentWordDecorator [atd=" + atd + ", id=" + id + ", countMapper=" + countMapper + ", word="
				+ word + "]";
	}
}