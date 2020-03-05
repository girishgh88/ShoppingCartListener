# Shopping Cart Queue Listener

Application listen to queue for incoming cart items and save them in-to DB.  

For Demo purpose it is using springboot activemq embedded template with JMS.

Database also using h2 in-memory embedded one.

For testing purpose there on MessageProducer class also implemented to put message into queue.

MessageReceiver class contains the JMSListener method which will listbner to the destination queue.

Upon receiving the message, it will be saved to DB.

When there is any exception occurred inside JMSListner method, the consumed message will remain with in the queue.

If needed more self-healing features can be added like health check monitoring with actuator.
By this health check it can be ensured the status of DB connection as well as JMS.
This health check can be done for many ways like configuring in containers such as Kubernetes or configure a system cronjob to do health check(like wget in linux) and take necessary actions like restart or can be configured in devops health check configurations.

See the jacoco coverage at ~\target\site\jacoco\index.html
