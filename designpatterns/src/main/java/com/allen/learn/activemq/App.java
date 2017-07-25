package com.allen.learn.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class App {


    public static void main(String[] args) {

        thread(new HelloWorldProducer() , false);

        thread(new HelloWorldProducer() , false);
//
//        thread(new HelloWorldConsumer() , false);
//
//

//

        thread(new HelloWorldConsumerTopic(),false);


        thread(new HelloWorldConsumerTopic(),false);

    }


    public static class HelloWorldProducer implements Runnable {

        @Override
        public void run() {
            try {
                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

                // Create a Connection
                Connection connection = connectionFactory.createConnection();
                connection.start();

                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createTopic("allen-topic");

                // Create a MessageProducer from the Session to the Topic or Queue
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

                // Create a messages
                String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
                TextMessage message = session.createTextMessage(text);

                // Tell the producer to send the message
                System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
                producer.send(message);

                session.commit();
                // Clean up
                session.close();
                connection.close();
            } catch (Exception e){

            }
        }
    }


    public static void thread (Runnable runnable, boolean daemon){
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }


    public static class HelloWorldConsumer implements Runnable, ExceptionListener {
        @Override
        public void run() {

            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

                Connection connection = connectionFactory.createConnection();
                connection.start();

                connection.setExceptionListener(this);

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                Destination destination = session.createQueue("TEST.FOO");

                MessageConsumer consumer = session.createConsumer(destination);

                Message message = consumer.receive(1000);

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText() ;
                    System.out.println("received: "+text);

                } else {
                    System.out.println("received: "+ message);
                }

                consumer.close();
                session.close();
                connection.close();

            } catch (Exception e){
                System.out.println("Caught: "+e);
                e.printStackTrace();
            }
        }

        @Override
        public void onException(JMSException exception) {
            System.out.println("HelloWorldConsumer producer message occur exception");
        }
    }

    public static class HelloWorldConsumerTopic implements Runnable, ExceptionListener {

        @Override
        public void run() {

            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

                Connection connection = connectionFactory.createConnection();
                connection.start();
                connection.setExceptionListener(this);

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                Topic topic = session.createTopic("allen-topic");

                // 创建消息消费者
                MessageConsumer messageConsumer = session.createConsumer(topic);

                messageConsumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        try {
                            System.out.println( "topic:"+((TextMessage)message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e){

            }

        }

        @Override
        public void onException(JMSException exception) {
            System.out.println("HelloWorldConsumer producer message occur exception");
        }
    }




}
