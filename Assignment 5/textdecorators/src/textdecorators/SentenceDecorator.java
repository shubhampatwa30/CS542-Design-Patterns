package textdecorators;

import java.util.Iterator;
import java.util.List;

import textdecorators.inputdetails.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

public class SentenceDecorator extends AbstractTextDecorator{
	private AbstractTextDecorator atd;
	private InputDetails id;

	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	@Override
	public void processInputDetails() {
		// TODO Auto-generated method stub
		MyLogger.getInstance().writeMessage("Sentence Decorator:", DebugLevel.SENTENCE_DECORATOR);
		List<StringBuilder> paragraph = id.getSentences();
		Iterator<StringBuilder> iter = paragraph.iterator();
		/**
		 * Just append to the beginning and to the end. We do not need any index
		 *  for this requirement.
		 */
		while(iter.hasNext()){
			StringBuilder sentence = iter.next();
			sentence.insert(0,"BEGIN_SENTENCE__");
			sentence.append("__END_SENTENCE.");	
			MyLogger.getInstance().writeMessage(sentence.toString(), DebugLevel.SENTENCE_DECORATOR);
		}

		MyLogger.getInstance().writeMessage("EXIT: Sentence Decorator", DebugLevel.SENTENCE_DECORATOR);
		if (null != atd) {
			atd.processInputDetails();
		}
	}

	@Override
	public String toString() {
		return "SentenceDecorator [atd=" + atd + ", id=" + id + "]";
	}
}














