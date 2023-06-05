package feign.httpclient;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import feign.Request;
import feign.Response;

@Weave
public  abstract class ApacheHttpClient {

	
	@Trace(dispatcher=true)
	public Response execute(Request request, Request.Options options)  {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","ApacheHttpClient","execute",request.url()});
		return Weaver.callOriginal();
	}
}


