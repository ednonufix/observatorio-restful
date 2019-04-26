package cu.cupet.cubalub.observatorio.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

public class JmsConsumer {


    JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Value("${jms.queue.destination}")
    String destinationQueue;

    public Object receive(){

        return jmsTemplate.receiveAndConvert(destinationQueue);

    }

}
