package com.demo.study.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.test.annotation.Repeat;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * simple String value
     **/

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    @Order(1)
    public void testSet() {
        System.out.println(Thread.currentThread().getName());
        stringRedisTemplate.opsForValue().set("test-string-value", "Hello Redis");
    }

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    @Order(-1)
    public void testGet() {
        System.out.println(Thread.currentThread().getName());
        String value = stringRedisTemplate.opsForValue().get("test-string-value");
        System.out.println(value);
    }

    @Test
    public void testDeleted() {
        stringRedisTemplate.delete("test-string-value");
    }

    /** simple String value end **/


    /**
     * distributeLock ops
     **/
    @Test
    public void distributeLock() {
        String key = "distributeLock";
        UUID uuid1 = UUID.randomUUID();
        String value1 = uuid1.toString();
        UUID uuid2 = UUID.randomUUID();
        String value2 = uuid2.toString();
        int timeout = 60;
        Boolean lockSuccess = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(key.getBytes(Charset.forName("UTF-8")), value1.getBytes(Charset.forName("UTF-8")),
                        Expiration.from(timeout, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT));

        Boolean lockFailed = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(key.getBytes(Charset.forName("UTF-8")), value2.getBytes(Charset.forName("UTF-8")),
                        Expiration.from(timeout, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT));
        Assertions.assertEquals(lockSuccess, true);
        Assertions.assertEquals(lockFailed, false);

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        boolean unLockStatFailed = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1,
                        key.getBytes(Charset.forName("UTF-8")),
                        value2.getBytes(Charset.forName("UTF-8"))));


        boolean unLockStatSuccess = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1,
                        key.getBytes(Charset.forName("UTF-8")),
                        value1.getBytes(Charset.forName("UTF-8"))));

        Assertions.assertEquals(unLockStatFailed, false);
        Assertions.assertEquals(unLockStatSuccess, true);

    }

    /** distributeLock ops end**/


    /**
     * list ops
     **/
    @Test
    @Order(1)
    public void testLeftPush() {
        Long aLong = redisTemplate.opsForList().leftPush("TestList", "TestLeftPush");
        Assertions.assertEquals(aLong, 1);
    }

    @Test
    @Repeat(2)
    public void testRightPush() {
        Long aLong = redisTemplate.opsForList().rightPush("TestList", "TestRightPush");
        Assertions.assertEquals(aLong, 1);
    }

    @Test
    public void testLeftPop() {
        Object leftFirstElement = redisTemplate.opsForList().leftPop("TestList");
        System.out.println(leftFirstElement);
    }


    @Test
    public void testRightPop() {
        Object rightFirstElement = redisTemplate.opsForList().rightPop("TestList");
        System.out.println(rightFirstElement);
    }

    /** list ops end **/

    /**
     * hash ops start
     */
    @Test
    public void testHashPut() {
        redisTemplate.opsForHash().put("TestHash", "FirstElement", "Hello,Redis hash.");
        Assertions.assertTrue(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }

    @Test
    public void testHashGet() {
        Object element = redisTemplate.opsForHash().get("TestHash", "FirstElement");
        Assertions.assertEquals("Hello,Redis hash.", element);
    }

    @Test
    public void testHashDel() {
        redisTemplate.opsForHash().delete("TestHash", "FirstElement");
        Assertions.assertTrue(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }
    /** hash ops end **/


    /**
     * set ops start
     */
    @Test
    public void testSetAdd() {
        redisTemplate.opsForSet().add("TestSet", "e1", "e2", "e3");
        long size = redisTemplate.opsForSet().size("TestSet");
        Assertions.assertEquals(3L, size);
    }

    @Test
    public void testSetGet() {
        Set<String> testSet = redisTemplate.opsForSet().members("TestSet");
        System.out.println(testSet);
    }

    @Test
    public void testRemove() {
        redisTemplate.opsForSet().remove("TestSet", "e1", "e2");
        Set testSet = redisTemplate.opsForSet().members("TestSet");
        Assertions.assertEquals("e3", testSet.toArray()[0]);
    }

    /** set ops end **/

    /**
     * zset ops start
     */
    @Test
    public void testZsetAdd() {
        String key = "TestZset";
        Set<ZSetOperations.TypedTuple> set = new HashSet<>();
//        ZSetOperations.TypedTuple typedTuple = new DefaultTypedTuple();

        for (int i = 0; i < 100; i++) {
            double d = 1.0 * i;
            redisTemplate.opsForZSet().add(key, "value" + i, d);
        }
        Set range = redisTemplate.opsForZSet().range(key, 2, 3);
        Set rangeByLex = redisTemplate.opsForZSet().rangeByLex(key, RedisZSetCommands.Range.range(), RedisZSetCommands.Limit.limit());
        System.out.println(range);
        System.out.println(rangeByLex);
    }
    /** zset ops end **/
}
