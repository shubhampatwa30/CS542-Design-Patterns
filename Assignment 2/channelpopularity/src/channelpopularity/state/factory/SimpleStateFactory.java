package channelpopularity.state.factory;

import channelpopularity.context.ChannelContext;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.UltraPopular;
import channelpopularity.state.Unpopular;

public class SimpleStateFactory implements SimpleStateFactoryI {
	ChannelContext channelContext;
	public SimpleStateFactory(ChannelContext channelContext) {
		this.channelContext = channelContext;
		
	}
	/**
	 * Implement the simple factory pattern
	 */
	@Override
	public StateI create(StateName state) {
		StateI stateI = null;
		switch(state){
		case UNPOPULAR :
			stateI = new Unpopular(channelContext);
			break;
		case HIGHLY_POPULAR : 
			stateI = new HighlyPopular(channelContext);
			break;
		case MILDLY_POPULAR:
			stateI = new MildlyPopular(channelContext);
			break;
		case ULTRA_POPULAR:
			stateI = new UltraPopular(channelContext);
			break;
		default:
			break;
		}
		return stateI;

	}





}






