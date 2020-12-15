package channelpopularity.state;

public interface StateI {
	void addVideo();
	void removeVideo();
	void metrics() ;
	void adRequest();
	int getPopularityScore();
	StateName getState(int popularityScore);


}
