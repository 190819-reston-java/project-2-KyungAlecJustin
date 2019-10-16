package com.revature.aspects;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger log = Logger.getLogger(LoggingAspect.class);
	
	@Before("within(com.revature.controller.UserController)")
	public void logEndpoints(JoinPoint jp) {
		log.trace(jp.getTarget() + " has invoked " + jp.getSignature());

	}
}
	
//	@AfterReturning(pointcut = "execution(boolean *(..))", returning = "returnedObject")
//	public void logAccountInteractions(JoinPoint jp, Object returnedObject) {
//		log.info(jp.getTarget() + " has invoked " + jp.getSignature() + " returning " + returnedObject);
//	}
//	
//	@AfterThrowing(pointcut = "execution(* *(..))", throwing = "ex")
//	public void logExceptions(JoinPoint jp, Exception ex) {
//		log.warn(jp.getTarget() + " has invoked " + jp.getSignature()  + " with arguments " + Arrays.toString(jp.getArgs()) + " throwing " + ex.getClass(), ex);
//	}
//	
//	@Around("execution(* * (..))")
//	public Object monitorAll(ProceedingJoinPoint pjp) throws Throwable {
//		long start = System.nanoTime();
//		long end;
//		
//		Object returned = null;
//		try {
//			returned = pjp.proceed();
//			end = System.nanoTime();
//			log.info("Total execution time of " + pjp.getSignature() + " is " + (end - start) + " ns");
//		} catch(Exception e) {
//			end = System.nanoTime();
//			log.info("Total execution time of " + pjp.getSignature() + " is " + (end - start) + " ns");
//			throw e;
//		}
//		
//		return returned;
//	}
//}