package com.nr.instrumentation.feign;

import java.util.ArrayList;
import java.util.List;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.OutboundHeaders;

import feign.Request;

public class RequestWrapper implements OutboundHeaders {
	
	private Request request = null;
	
	public RequestWrapper(Request req) {
		request = req;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public void setHeader(String name, String value) {
		List<String> values = new ArrayList<String>();
		values.add(value);
		request.headers().put(name, values);
		
	}

}
