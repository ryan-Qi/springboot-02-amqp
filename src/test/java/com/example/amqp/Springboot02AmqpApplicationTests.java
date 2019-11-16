package com.example.amqp;

import com.example.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
        //rabbitTemplate.send(exchange,routekey,message);
        //自动序列化
        //rabbitTemplate.convertAndSend();
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data",Arrays.asList("helloword",123,true));
        //rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));
    }

    @Test
    public void revieve() {
        Object receiveAndConvert = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(receiveAndConvert.getClass());
        System.out.println(receiveAndConvert);
    }

    @Test
    public void sendmsg() {
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("三国演义","罗贯中"));
    }

}
