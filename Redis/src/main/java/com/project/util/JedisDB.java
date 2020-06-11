package com.project.util;
import redis.clients.jedis.Jedis;

/**
 * 创建一个Jedis类
 */
public class JedisDB {

    /**创建Jedis对象*/
    public static Jedis createJedis(){

       //ip是Redis的config中放行的，端口是配置的
       Jedis jedis = new Jedis("127.0.0.1",6392);
       //密码
       jedis.auth("123456");

       return jedis;
    }
}
