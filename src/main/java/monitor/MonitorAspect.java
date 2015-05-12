package monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;

@Aspect
public class MonitorAspect {
    final ActorSystemMessages messages;

    public MonitorAspect() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        this.messages = new ActorSystemMessages();
        JMXEndpoint.start();
    }

    @Pointcut(value = "execution(public * *(..)) && args() && !within(monitor.*)")
    public void publicMethodCall() {
    }

    @Around(value = "publicMethodCall()", argNames = "point")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        final long start, end;
        start = System.nanoTime();
        Object result = point.proceed();
        end = System.nanoTime();
        long calculateTime = (end - start) / 1000 / 1000;
        JMXEndpoint.sendNotification(point + "Took :" + calculateTime);

        return result;
    }

}
