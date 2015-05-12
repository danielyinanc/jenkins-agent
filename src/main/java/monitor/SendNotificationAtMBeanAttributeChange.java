package monitor;


import javax.management.*;
import java.lang.management.ManagementFactory;

public class SendNotificationAtMBeanAttributeChange {

    public static class Hello extends NotificationBroadcasterSupport implements HelloMBean {

        private long sequenceNumber = 1;
        private String message = "Hello World";

        public Hello() {
            addNotificationListener(new NotificationListener() {
                @Override
                public void handleNotification(Notification notification, Object handback) {
/*                    System.out.println("*** Handling new notification ***");
                    System.out.println("Message: " + notification.getMessage());
                    System.out.println("Seq: " + notification.getSequenceNumber());
                    System.out.println("*********************************");*/
                }
            }, null, null);
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public void sayHello() {
            System.out.println(message);
        }

        @Override
        public void setMessage(String message) {
            String oldMessage = this.message;
            this.message = message;
            Notification n = new AttributeChangeNotification(this, sequenceNumber++,

                    System.currentTimeMillis(), message, "Message", "String",

                    oldMessage, this.message);
            sendNotification(n);
        }

    }

    public static interface HelloMBean {

        // operations

        public void sayHello();

        // attributes

        // a read-write attribute called Message of type String
        public String getMessage();
        public void setMessage(String message);

    }

}