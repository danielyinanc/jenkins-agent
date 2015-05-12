package monitor;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @author janmachacek
 */
public class JMXEndpoint {
    static MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    // Construct the ObjectName for the Hello MBean we will register
    static String objectName = "com.javacodegeeks.snippets.enterprise:type=Hello";

    public static void start() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        //ObjectName name = new ObjectName("monitor:type=Performance");
        //ActorSystemPerformanceMXBeanImpl mbean = new ActorSystemPerformanceMXBeanImpl(messages);
        //mbs.registerMBean(mbean, name);

        ObjectName mbeanName = new ObjectName(objectName);

        SendNotificationAtMBeanAttributeChange.Hello mbean2 = new SendNotificationAtMBeanAttributeChange.Hello();
        mbs.registerMBean(mbean2, mbeanName);
    }

    public static void sendNotification(String msg) throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException {
        ObjectName mbeanName = new ObjectName(objectName);
        mbs.setAttribute(mbeanName, new Attribute("Message", msg));
    }
}
