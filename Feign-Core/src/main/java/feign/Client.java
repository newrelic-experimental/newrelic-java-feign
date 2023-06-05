package feign;

import java.net.URI;

import com.newrelic.api.agent.HttpParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.feign.RequestWrapper;
import com.nr.instrumentation.feign.ResponseWrapper;

import feign.Request.Options;

@Weave(type=MatchType.Interface)
public abstract class Client {

	@Trace(dispatcher=true)
	public Response execute(Request request, Options options) {
		RequestWrapper wrapper = new RequestWrapper(request);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		Response response = Weaver.callOriginal();
		ResponseWrapper respWrapper = new ResponseWrapper(response);
		String urlStr = request.url();
		URI uri = URI.create(urlStr);

		HttpParameters params = HttpParameters.library("Feign").uri(uri).procedure("execute").inboundHeaders(respWrapper).build();
		NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		return response;
	}
}
