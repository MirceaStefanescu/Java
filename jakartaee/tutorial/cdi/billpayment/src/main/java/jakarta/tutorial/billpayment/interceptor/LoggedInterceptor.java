package jakarta.tutorial.billpayment.interceptor;

import java.io.Serializable;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Logged @Interceptor public class LoggedInterceptor implements Serializable {

    private static final long serialVersionUID = -2230122751970857993L;

    public LoggedInterceptor() {
    }

    @AroundInvoke public Object logMethodEntry(InvocationContext invocationContext)
            throws Exception {
        System.out.println(
                "Entering method: " + invocationContext.getMethod().getName() + " in class " +
                invocationContext.getMethod().getDeclaringClass().getName());

        return invocationContext.proceed();
    }
}
