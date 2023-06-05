package feign;

import java.lang.invoke.MethodHandle;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
class DefaultMethodHandler {

	private MethodHandle handle = Weaver.callOriginal();
	
	@Trace
	public Object invoke(Object[] argv) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","DefaultMethodHandler","invoke",handle.toString()});
		return Weaver.callOriginal();
	}
}
