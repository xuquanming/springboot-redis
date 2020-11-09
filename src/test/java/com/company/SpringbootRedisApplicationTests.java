package com.company;

import com.company.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {

        //在企业开发中，我们呢80%的情况下都不会使用这个原生的方式去编写代码
        //RedisUtils


        //操作不同的数据类型，api和指令是一样的
        //opsForValue 操作字符串  类似String
        //opsForList  操作list



        /*//获取连接
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();
        connection.flushDb();*/


        redisTemplate.opsForValue().set("mykey","ming");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    void test() throws JsonProcessingException {
        //真实的开发一般都使用json来传递对象
        User user = new User("小明", 3);
        String s = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
