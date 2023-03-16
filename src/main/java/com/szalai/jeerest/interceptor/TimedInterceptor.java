package com.szalai.jeerest.interceptor;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

@Interceptor
@Timed
public class TimedInterceptor {

    Logger logger = Logger.getLogger("TimedInterceptor.class");

    @AroundInvoke
    public Object timeInvocation(InvocationContext context) throws Exception{
        //Before method
        long start = System.currentTimeMillis();
        Object result = context.proceed();
        //After method
        logger.log(Level.INFO, "calculated in " + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}
