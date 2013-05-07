package policy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import employee.Employee;
import employee.IEmployee;

/**
 * Class PolicyProxy
 * @author ahounfol
 * @date 20/11/12
 *
 */
public class PolicyProxy implements InvocationHandler{
	
	private IPolicy policy;
	private IEmployee employee;
	
	public PolicyProxy(IPolicy policy, IEmployee employee) {
		this.policy = policy;
		this.employee = employee;
	}

	public static Object newInstance(IEmployee e, IPolicy policy, Class[] interfaces){
		return Proxy.newProxyInstance(e.getClass().getClassLoader(), interfaces, new 
				PolicyProxy(policy, e));
	}
	
	public static Object newInstance(IEmployee e, IPolicy policy){
		return Proxy.newProxyInstance(e.getClass().getClassLoader(), 
				e.getClass().getInterfaces(), new PolicyProxy(policy, e));
	}
	
	@Override
	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		if(policy.isAllowed(m.getName())){
			return m.invoke(employee, args);
		}else{
			return "Accès refusé";
		}
	}

}
