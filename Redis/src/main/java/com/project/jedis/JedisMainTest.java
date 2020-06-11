package com.project.jedis;
import com.project.bean.UserBean;

/**
 * 测试Jedis
 */
public class JedisMainTest {
    public static void main(String[] args) {
        JedisMain jedisMain = new JedisMain();
        UserBean user = (UserBean) jedisMain.getObject("user");
        System.out.println(user);
    }
}
