package sample.spring.chapter09.bankapp.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger = Logger.getLogger(LoggingAspect.class);

	@Before(value = "execution(* sample.spring.chapter09.bankapp.service.*Service.*(..))")
	public void log(JoinPoint joinPoint) {
		logger.info("Entering "
				+ joinPoint.getTarget().getClass().getSimpleName() + "'s "
				+ joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logger.info("args[" + i + "] -->" + args[i]);
		}
	}
}
