package com.example.demo.task;

import com.example.demo.bean.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

import javax.annotation.Resource;

@Component
public class Task {

    @Resource
    private DefaultMQProducer producer;

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "0/5 * *  * * ?")
    private void sendMsgToMq() {
//        String str = "发送测试消息";
        Order ord = new Order();
        ord.setOrderId(1001L);
        ord.setOrderName("玩具熊");
        ord.setOrderAmount(new BigDecimal("100"));
        ord.setOrderNum(2);
        ord.setTotalAmount(new BigDecimal("200"));
        ord.setDesc("发顺丰快递");

        Message msg;
        try {
            msg = new Message("test-demo"
                    , "20210409"
                    , UUID.randomUUID().toString()
                    , ord.toString().getBytes("utf-8"));
            SendResult result = producer.send(msg);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                System.out.println("消息发送成功");
                System.out.println(result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

