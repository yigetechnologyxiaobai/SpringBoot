package com.cyy.redis;

import com.cyy.redis.mapper.UserMapper;
import com.cyy.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;        //操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate;                        //k-v都是对象的

    /**
     * Redis常见的五大数据类型
     * String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     * stringRedisTemplate.opsForValue()       [String（字符串）]
     * stringRedisTemplate.opsForList()        [List（列表）]
     * stringRedisTemplate.opsForSet()         [Set集合]
     * stringRedisTemplate.opsForHash()        [Hash（散列）]
     * stringRedisTemplate.opsForZSet()        [ZSet（有序集合）]
     */
    @Test
    void set() {
        stringRedisTemplate.opsForValue().append("msg", "hello");
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

    @Test
    void get() {
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
        String mylist = stringRedisTemplate.opsForList().leftPop("mylist");
        System.out.println(mylist);
    }


    @Test
    void test_obj() {
        User user = userMapper.queryById(2);
        redisTemplate.opsForValue().set("emp-02", user);
    }

    @Test
    void clear() {
        Boolean flag = redisTemplate.delete("emp-02");
        System.out.println(flag);
    }
}
