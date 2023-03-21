package com.myorg.hobbymat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.lang.invoke.MethodHandles;

@Component
public class ArtemisReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${myorg.jms.artemis.queue.notifications:customer.queue.contact.notifications}")
    private String publisherQueue;

    @Autowired
    JmsTemplate publisherJmsTemplate;

    //@Transactional
    @JmsListener(destination = "${myorg.jms.artemis.queue}", containerFactory = "jmsListenerContainerFactory")
    public void receiveSingleMessage(Message message) throws JMSException {
        logger.info("Message from queue {} with filename {} is received with correlationId [{}], jms-messageId [{}]"
                , message.getJMSDestination()
                , message.getStringProperty("SOURCE_FILE_NAME")
                , message.getJMSCorrelationID()
                , message.getJMSMessageID());

        String[] body = (new String(message.getBody(byte[].class)).lines().toArray(String[]::new));
        for (String line: body) {
            publisherJmsTemplate.send(publisherQueue, messageCreator -> messageCreator.createTextMessage("process" + line));
        }
        //logger.info("Simulate a network error while trying to insert the body to a db");
        //throw new RuntimeException("a network error occured");
        logger.info("this is where the auto ack takes place");
    }
}
