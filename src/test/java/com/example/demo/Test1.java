package com.example.demo;

//import com.example.demo.msg.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("producer-demo1")
public class Test1 {

//    @Autowired
//    private Producer p;

    @Test
    public void test() {
//        p.send();
    }
}
