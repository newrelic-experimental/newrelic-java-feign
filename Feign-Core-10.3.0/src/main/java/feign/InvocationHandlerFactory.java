package feign;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class InvocationHandlerFactory {

	@Weave(type=MatchType.Interface)
	public static class MethodHandler {
		
		@Trace
		public Object invoke(Object[] argv) {
			return Weaver.callOriginal();
		}
	}
}
