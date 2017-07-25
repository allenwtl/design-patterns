package com.allen.learn.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Consumer {


    public static void main(String[] args) {

        thread(new HelloWorldConsumerTopic(), false);

        thread(new HelloWorldConsumerTopic(), false);

    }




    public static void thread (Runnable runnable, boolean daemon){
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
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
