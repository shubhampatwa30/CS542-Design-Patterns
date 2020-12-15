package channelpopularity.context;

import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public interface ContextI {

	public void addVideo(String videoName) throws Exception ;
	public void removeVideo(String videoName) throws Exception;
	public void metrics(Results store,String videoName, int views, int likes, int dislikes) throws Exception;
	public void adRequest(String videoName,int length) throws Exception;
	public void setCurrentState(StateName nextState);
	public StateName getCurState();
}
