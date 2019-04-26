package cu.cupet.cubalub.observatorio.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;


public class JmsProducer {

	JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Value("${jms.queue.destination}")
	String destination;

	public void send(String msg) throws Exception {

		jmsTemplate.convertAndSend(destination,msg);

	}
}
