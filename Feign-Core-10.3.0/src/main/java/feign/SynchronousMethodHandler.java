package feign;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import feign.Request.Options;

@Weave
abstract class SynchronousMethodHandler {

	
	@Trace
	public Object invoke(Object[] argv) {
		return Weaver.callOriginal();
	}
	
	@Trace
	Object executeAndDecode(RequestTemplate template, Options options)  {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","SynchronousMethodHandler","executeAndDecode",template.method()});
		return Weaver.callOriginal();
	}
}
