package example.usermanagement.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component // Do either this or add a bean to config.
public class TimeTraceAop {

    Logger logger = LoggerFactory.getLogger(TimeTraceAop.class);

    @Around("execution(* example.usermanagement..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        try {
            return joinPoint.proceed();
        } finally {
            stopwatch.stop();
            logger.info(stopwatch.prettyPrint());
        }
    }

}
