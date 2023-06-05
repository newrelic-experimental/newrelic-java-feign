package feign;

import java.lang.reflect.Method;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ReflectiveFeign {

	@Weave
	static abstract class FeignInvocationHandler {

		@Trace
		public Object invoke(Object proxy, Method method, Object[] args) {
			String methodClass = method.getDeclaringClass().getName();
			String methodName = method.getName();
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","FeignInvocationHandler","invoke",methodClass,methodName});
			return Weaver.callOriginal();
		}
	}
}
