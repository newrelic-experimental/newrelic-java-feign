package com.nr.instrumentation.feign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;
import com.newrelic.api.agent.OutboundHeaders;

import feign.Request;

public class RequestWrapper implements Headers {

	private Request request = null;

	public RequestWrapper(Request req) {
		request = req;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public String getHeader(String name) {
		Map<String, Collection<String>> headers = request.headers();
		Collection<String> values = headers.get(name);
		if(values != null && !values.isEmpty()) {
			return values.iterator().next();
		}

		return  null;
	}

	@Override
	public Collection<String> getHeaders(String name) {
		return request.headers().get(name);
	}

	@Override
	public void setHeader(String name, String value) {
		addHeader(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		List<String> list = new ArrayList<>();
		list.add(value);
		Collection<String> existing = request.headers().get(name);
		if(existing != null) {
			list.addAll(existing);
		}
		request.headers().put(name,list);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return request.headers().keySet();
	}

	@Override
	public boolean containsHeader(String name) {
		return getHeaderNames().contains(name);
	}

}
