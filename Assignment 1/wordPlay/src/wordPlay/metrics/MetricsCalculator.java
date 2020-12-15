package wordPlay.metrics;
/**
 * This class implements the calculations needed for the metrics.
 * @author Shubham
 *
 */
public class MetricsCalculator {
	float TOTAL_CHARACTERS;
	float TOTAL_WORDS;
	float SENT_COUNT;
	public float AVG_WORD_LENGTH;
	public float AVG_NUM_WORDS_PER_SENTENCE;
	
	
	/**
	 * This constructor gets the values needed for metrics calculation
	 *  and does the calculations here in the constructor itself.
	 * @param TOTAL_CHARACTERS : Total number of characters until now
	 * @param TOTAL_WORDS : Total number of words until now
	 * @param SENT_COUNT : Total number of lines or sentences per line
	 */
	public MetricsCalculator(float TOTAL_CHARACTERS, float TOTAL_WORDS, float SENT_COUNT) {
		this.TOTAL_CHARACTERS = TOTAL_CHARACTERS;
		this.TOTAL_WORDS = TOTAL_WORDS;
		this.SENT_COUNT = SENT_COUNT;
		this.AVG_WORD_LENGTH = this.TOTAL_CHARACTERS/this.TOTAL_WORDS;
		/**
		 * Math.round is used for rounding off the values to 2 decimal places.
		 */
		this.AVG_WORD_LENGTH = (float) (Math.round(this.AVG_WORD_LENGTH * 100.0) / 100.0);
		this.AVG_NUM_WORDS_PER_SENTENCE = this.TOTAL_WORDS / this.SENT_COUNT;
		this.AVG_NUM_WORDS_PER_SENTENCE = (float) (Math.round(this.AVG_NUM_WORDS_PER_SENTENCE * 100.0) / 100.0);
		
	}
	
	/**
	 * Override toString method
	 */
	
	@Override
	public String toString() {
		return "MetricsCalculator [TOTAL_CHARACTERS=" + TOTAL_CHARACTERS + ", TOTAL_WORDS=" + TOTAL_WORDS
				+ ", SENT_COUNT=" + SENT_COUNT + ", AVG_WORD_LENGTH=" + AVG_WORD_LENGTH
				+ ", AVG_NUM_WORDS_PER_SENTENCE=" + AVG_NUM_WORDS_PER_SENTENCE + "]";
	}

	/**
	 * Getter method for AVG_WORD_LENGTH
	 * @return
	 */
	public float getAVG_WORD_LENGTH() {
		return AVG_WORD_LENGTH;
	}
	/**
	 * Setter method for AVG_WORD_LENGTH
	 * @param AVG_WORD_LENGTH
	 */
	public void setAVG_WORD_LENGTH(float AVG_WORD_LENGTH) {
		this.AVG_WORD_LENGTH = AVG_WORD_LENGTH;
	}
	/**
	 * Getter method for AVG_NUM_OF_WORDS_PER_SENTENCE
	 * @return
	 */
	public float getAVG_NUM_WORDS_PER_SENTENCE() {
		return AVG_NUM_WORDS_PER_SENTENCE;
	}
	/**
	 * Setter method for NUM_OF_WORDS_PER_SENTENCE
	 * @param AVG_NUM_WORDS_PER_SENTENCE
	 */
	public void setAVG_NUM_WORDS_PER_SENTENCE(float AVG_NUM_WORDS_PER_SENTENCE) {
		this.AVG_NUM_WORDS_PER_SENTENCE = AVG_NUM_WORDS_PER_SENTENCE;
	}
	
	
	
	
	
	
}
