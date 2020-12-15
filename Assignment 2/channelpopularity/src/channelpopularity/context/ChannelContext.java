package channelpopularity.context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;



public class ChannelContext implements ContextI{
	/**
	 * Declare StateI references
	 */
	private StateI curState;
	private Map<StateName, StateI> availableStates;
	private Map<StateI , StateName> getStates;
	private SimpleStateFactoryI stateFactoryIn; 
	private StateI UNPOPULAR;
	private StateI MILDLY_POPULAR;
	private StateI HIGHLY_POPULAR;
	private StateI ULTRA_POPULAR;

	private String videoName;
	private int views;
	private int likes;
	private int dislikes;
	private int length;
	
	private Map<String,int[]> map = new HashMap<String,int[]>();
	Results store ;
	
	/**
	 * Contructor of ChannelContext
	 * @param stateFactoryIn : Used to implement Simple Factory Pattern
	 * @param stateNames : List of State Names using Enums
	 * @param store : Results class object
	 */

	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames, Results store) {
		this.setStateFactoryIn(stateFactoryIn);
		stateFactoryIn  = new SimpleStateFactory(this);
		this.store = store;
		/**
		 * Initialize the States using factory pattern
		 * Also create a reverse hashmap for getting the stateNames
		 */

		UNPOPULAR = stateFactoryIn.create(stateNames.get(0));
		MILDLY_POPULAR = stateFactoryIn.create(stateNames.get(1));
		HIGHLY_POPULAR = stateFactoryIn.create(stateNames.get(2));
		ULTRA_POPULAR = stateFactoryIn.create(stateNames.get(3));

		availableStates = new HashMap<StateName, StateI>();

		availableStates.put(StateName.UNPOPULAR, UNPOPULAR);
		availableStates.put(StateName.MILDLY_POPULAR, MILDLY_POPULAR);
		availableStates.put(StateName.HIGHLY_POPULAR, HIGHLY_POPULAR);
		availableStates.put(StateName.ULTRA_POPULAR, ULTRA_POPULAR);
		setCurState(UNPOPULAR);

		getStates = new HashMap<StateI, StateName>();
		getStates.put(UNPOPULAR, StateName.UNPOPULAR);
		getStates.put(MILDLY_POPULAR, StateName.MILDLY_POPULAR);
		getStates.put(HIGHLY_POPULAR, StateName.HIGHLY_POPULAR);
		getStates.put(ULTRA_POPULAR, StateName.ULTRA_POPULAR);

	}

	/**
	 * ADD VIDEO()
	 * Calls the current state's addVideo() method
	 */
	public void addVideo(String videoName) throws Exception {
		this.videoName = videoName;
		this.views = 0;
		this.likes = 0;
		this.dislikes = 0;
		curState.addVideo();

	}

	/**
	 * REMOVE_VIDEO()
	 * Calls the current state's removeVideo() method
	 */
	public void removeVideo(String videoName) throws Exception {
		this.videoName = videoName;
		curState.removeVideo();
	}

	/**
	 * Calls the current state's metrics() method
	 */
	public void metrics(Results store,String videoName, int views, int likes, int dislikes) throws Exception  {
		this.videoName = videoName;
		this.views = views;
		this.likes = likes;
		this.dislikes = dislikes;
		curState.metrics();	
	}

	/**
	 * Call the current state's ad_request method
	 */
	public void adRequest(String videoName,int length) throws Exception {
		this.videoName = videoName;
		this.length = length;
		curState.adRequest();
	}

	/*
	 * Set the new State
	 */
	public void setCurrentState(StateName nextState) {
		//System.out.println(nextState);
		if (availableStates.containsKey(nextState)) { // for safety.
			setCurState(availableStates.get(nextState));
			//System.out.println(getCurState());	
		}

	}

	/**
	 * Getter and setter methods
	 * @return
	 */

	public SimpleStateFactoryI getStateFactoryIn() {
		return stateFactoryIn;
	}

	public void setStateFactoryIn(SimpleStateFactoryI stateFactoryIn) {
		this.stateFactoryIn = stateFactoryIn;
	}

	public StateName getCurState() {
		return getStates.get(curState);
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public void setCurState(StateI curState) {
		this.curState = curState;
	}

	public int getLength() {
		return length;
	}
	
	public void setMetrics(String videoName, int[] intArray) {
		map.put(videoName, intArray);

	}

	public void removeVideoFromMap(String videoName) {
		map.remove(videoName);
	}

	public Map<String, int[]> getMap() {
		return map;
	}


	public void setMap(Map<String, int[]> map) {
		this.map = map;
	}

	
	public void setLength(int length) {
		this.length = length;
	}
	
	public void addToResult(String line) {
		store.addToResult(line);
	}

	@Override
	public String toString() {
		return "ChannelContext [curState=" + curState + ", availableStates=" + availableStates + ", getStates="
				+ getStates + ", stateFactoryIn=" + stateFactoryIn + ", UNPOPULAR=" + UNPOPULAR + ", MILDLY_POPULAR="
				+ MILDLY_POPULAR + ", HIGHLY_POPULAR=" + HIGHLY_POPULAR + ", ULTRA_POPULAR=" + ULTRA_POPULAR
				+ ", videoName=" + videoName + ", views=" + views + ", likes=" + likes + ", dislikes=" + dislikes
				+ ", length=" + length + ", map=" + map + ", store=" + store + "]";
	}

	


}