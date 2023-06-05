package com.nr.instrumentation.feign;

import java.util.Collection;
import java.util.Iterator;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.InboundHeaders;

import feign.Response;

public class ResponseWrapper implements InboundHeaders {
	
	private Response response = null;
	
	public ResponseWrapper(Response resp) {
		response = resp;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public String getHeader(String name) {
		Collection<String> values =response.headers().get(name);
		
		if(values != null && !values.isEmpty()) {
			Iterator<String> iterator = values.iterator();
			return iterator.next();
		}
		
		return null;
	}

}
