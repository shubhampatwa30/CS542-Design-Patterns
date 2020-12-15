package channelpopularity.state;

import java.util.Map;
import java.util.Map.Entry;
import channelpopularity.context.ChannelContext;
import channelpopularity.exceptions.NegativeDislikesException;
import channelpopularity.exceptions.NegativeLikesException;
import channelpopularity.operation.Operation;
import channelpopularity.util.Results;
public abstract class AbstractState implements StateI {
	ChannelContext channelContext;
	Results store;

	/**
	 * Current State's addVideo Method
	 * adding the video to channel takes place here.
	 */
	public void addVideo()  
	{
		Map<String,int[]> map = 	channelContext.getMap();

		try {
			if(map.containsKey(channelContext.getVideoName())) {
				throw new IllegalArgumentException(channelContext.getVideoName());
			}
			else
			{
				int[] array = new int[4];
				for(int i=0;i<4;i++) {
					array[i]= 0;
				}	
				channelContext.setMetrics(channelContext.getVideoName(), array);
				channelContext.addToResult((channelContext.getCurState()) + Operation.ADD.toString() + channelContext.getVideoName());
				channelContext.setCurrentState(this.getState(this.getPopularityScore()));

			}
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("Video is already present");
			e.printStackTrace();
			System.exit(0);
		}

	}



	/**
	 * RemoveVideo() 
	 * Removing the video from channel takes place here
	 */
	public void removeVideo() 
	{
		Map<String,int[]> map = 	channelContext.getMap();
		try
		{
			if(map.containsKey(channelContext.getVideoName()))
			{
				channelContext.removeVideoFromMap(channelContext.getVideoName());
				channelContext.addToResult(channelContext.getCurState() + Operation.REM.toString() + channelContext.getVideoName());
				channelContext.setCurrentState(this.getState(this.getPopularityScore()));
			}
			else 
			{

				throw new IllegalArgumentException(channelContext.getVideoName());
			}
		}
		catch(IllegalArgumentException e) 
		{
			System.err.println("Video to be removed does not exists");
			e.printStackTrace();
			System.exit(0);
		}
	}	

	/**
	 * AD_REQUEST
	 * This method does the requesting of ad in the current State
	 */
	public void adRequest()  {
		Map<String,int[]> map = 	channelContext.getMap();
		try {
			if(map.containsKey(channelContext.getVideoName())) {
				int length = channelContext.getLength();
				if(length < 0) {
					throw new NumberFormatException(String.valueOf(length));
				}
				String permit = null;

				StateName currentState =channelContext.getCurState();
				switch(channelContext.getCurState()) {
				case UNPOPULAR :
					if (1 < length && length <= 10) {
						permit = "APPROVED";
					}
					else {
						permit = "REJECTED";
					}
					break;
				case MILDLY_POPULAR:
					if (1 < length && length <= 20 ) {
						permit = "APPROVED";
					}
					else {
						permit = "REJECTED";
					}
					break;
				case HIGHLY_POPULAR:
					if (1 < length && length <= 30) {
						permit = "APPROVED";
					}
					else {
						permit = "REJECTED";
					}
					break;
				case ULTRA_POPULAR:
					if (1 < length && length <= 40) {
						permit = "APPROVED";
					}
					else {
						permit = "REJECTED";
					}
					break;


				default : 
				{
					break;
					//throw exception
				}
				}
				channelContext.addToResult(currentState + Operation.AD_.toString() + permit);
			}
			else {
				throw new IllegalArgumentException(channelContext.getVideoName());

			}
		}
		catch(NumberFormatException e) {
			System.err.println("Length of ad cannot be negative");
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException e) {
			System.err.println("Video associated with ad does not exists");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 *   Metrics() implements the metrics function.
	 *   Upon completion, it changes the state according to the popularity Score
	 */


	public void metrics(){
		Map<String,int[]> map = 	channelContext.getMap();
		int[]  values = new int[4];
		values[0] = channelContext.getViews();
		values[1] = channelContext.getLikes();
		values[2] = channelContext.getDislikes();
		

		try {

			if(values[0] < 0) {
				throw new NumberFormatException(String.valueOf(values[0]));
			}



			if(map.containsKey(channelContext.getVideoName())) {
				int[] prevArray = map.get(channelContext.getVideoName());

				if((prevArray[1] + values[1]) < 0 ) {
					throw new NegativeLikesException(channelContext.getVideoName());
				}
				if((prevArray[2] + values[2] < 0)) {
					throw new NegativeDislikesException(channelContext.getVideoName());
				}
				for(int i=0;i<3;i++) {
					prevArray[i] += values[i];
				}
				/**
				 * Calculate the popularity score and store
				 */
				prevArray[3] = prevArray[0] + 2 * (prevArray[1] - prevArray[2]);
				if(prevArray[3] < 0) {
					prevArray[3] = 0;
				}
				channelContext.setMetrics(channelContext.getVideoName(), prevArray);	
				int popularityScore = this.getPopularityScore();
				channelContext.addToResult((channelContext.getCurState()) + Operation.MET.toString() + popularityScore);
				channelContext.setCurrentState(this.getState(popularityScore));

			}
			else {
				throw new IllegalArgumentException(channelContext.getVideoName());
			}
		}
		catch(NumberFormatException e ) {
			System.err.println("Views cannot be negative");
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException e) {
			System.err.println("Video does not exists");
			e.printStackTrace();
			System.exit(0);
		}
		catch(NegativeLikesException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch(NegativeDislikesException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}


	/**
	 * calculates the popularity Score according to the stored metrics
	 */
	@Override
	public int getPopularityScore() {
		Map<String,int[]> map = 	channelContext.getMap();
		int popularityScore = 0;
		int videoCount = 0;
		for (Entry<String, int[]> entry : map.entrySet()) {          
			int [] metrics = entry.getValue();          
			popularityScore += metrics[3];
			videoCount +=1;
		}
		if(videoCount<1) {
			popularityScore = 0;
			return popularityScore;	
		}
		popularityScore /= videoCount;
		
		return popularityScore;
	}

	/**
	 * Get the state according to popularity Score
	 */

	@Override
	public StateName getState(int popularityScore) {

		if(0 <= popularityScore && popularityScore <= 1000) {
			return StateName.UNPOPULAR;
		}
		if(1000 < popularityScore && popularityScore <= 10000) {
			return StateName.MILDLY_POPULAR;
		}
		if(10000 < popularityScore && popularityScore <= 100000 ) {
			return StateName.HIGHLY_POPULAR;
		}
		if(100000 < popularityScore && popularityScore <= Integer.MAX_VALUE) {
			return StateName.ULTRA_POPULAR;
		}

		return StateName.UNPOPULAR;
	}



	@Override
	public String toString() {
		return "AbstractState [channelContext=" + channelContext + ", store=" + store + "]";
	}



	









}
