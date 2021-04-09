package com.example.demo.controller;

import com.example.demo.bean.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;


@Controller
public class DemoController {

    @Resource
    private DefaultMQProducer producer;

    @GetMapping("/hello")
    public String getMsg() {
        return "hello rocketmq";
    }

    @PostMapping("/send")
    @ResponseBody
    public String send(Order order){
        order.setOrderId(Calendar.getInstance().getTimeInMillis());
        order.setTotalAmount(order.getOrderAmount().multiply(new BigDecimal(order.getOrderNum())));
        Message msg;
        String str = "消息发送成功";
        try {
            msg = new Message("test-demo"
                    , "20210409"
                    , UUID.randomUUID().toString()
                    , order.toString().getBytes("utf-8"));
            SendResult result = producer.send(msg);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                System.out.println(str);
                System.out.println(result.toString());
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = "消息发送失败";
        }
        return str;
    }

}
