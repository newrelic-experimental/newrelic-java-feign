package feign.hystrix;

import java.lang.reflect.Method;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
abstract class HystrixInvocationHandler {
	
	@Trace(dispatcher=true)
	public Object invoke(final Object proxy, final Method method, final Object[] args) {
		String methodClass = method.getDeclaringClass().getName();
		String methodName = method.getName();
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","HystrixInvocationHandler","invoke",methodClass,methodName});
		return Weaver.callOriginal();
	}

}
