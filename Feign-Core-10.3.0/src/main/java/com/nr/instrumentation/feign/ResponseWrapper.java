package com.nr.instrumentation.feign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;
import com.newrelic.api.agent.InboundHeaders;

import feign.Response;

public class ResponseWrapper implements Headers {

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

	@Override
	public Collection<String> getHeaders(String name) {
		return response.headers().get(name);
	}

	@Override
	public void setHeader(String name, String value) {
		addHeader(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		List<String> list = new ArrayList<String>();
		Collection<String> existingValues = response.headers().get(name);
		if(existingValues != null && !existingValues.isEmpty()) {
			list.addAll(existingValues);
		}
		response.headers().put(name, list);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return response.headers().keySet();
	}

	@Override
	public boolean containsHeader(String name) {
		return getHeaderNames().contains(name);
	}

}
