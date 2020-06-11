package com.project.jedis;
import com.project.bean.UserBean;
import com.project.util.JedisDB;
import com.project.util.SerializeUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * jedis操作和redis语法一样
 */
public class JedisMain {


    public static void main(String[] args) {
        JedisMain jedisMain = new JedisMain();

        UserBean user = new UserBean();
        user.setName("汉化");
        user.setPass("666");
        user.setSex("男");
//------------------------------------------------------------------------
        //演示Jedis语法
        //jedisMain.setObject("user", user);

//------------------------------------------------------------------------
        //演示Hash语法
       /* Map<String,String> map = new HashMap<String, String>();
        map.put("name", user.getName());
        map.put("pass", user.getPass());
        map.put("sex", user.getSex());
        jedisMain.setHashString("user", map);//存入

        Map<String,String> hashMap = new HashMap<String, String>();
        hashMap = jedisMain.getHashString("user");//取出

        //如何同时循环map的key和value?
        //先将map变为set
        Set<Entry<String,String>> set = hashMap.entrySet();
        //得到Iterator
        Iterator<Entry<String,String>> iterator = set.iterator();

        while (iterator.hasNext()){
            //通过iterator.next()，得到key和value
            Entry<String,String> next = iterator.next();
            System.out.println("user："+next.getKey()+"，"+next.getValue());
        }*/

//------------------------------------------------------------------------
        //演示list语法
        /*String values[] = {user.getName(),user.getPass(),user.getSex()};
        //jedisMain.setListString("user", values);//存入

        String value = jedisMain.getListString("user");//取出
        System.out.println(value);*/


//------------------------------------------------------------------------
        //演示set语法
        /*String member[] = {user.getName(),user.getPass(),user.getSex()};
        jedisMain.setSetString("user", member);//存入

        Set<String> set1 = jedisMain.getSetString("user");
        for (String s : set1) {
            System.out.println(s);
        }*/

//------------------------------------------------------------------------
        //演示set排序
        /*Map<String, Double> map = new HashMap<String, Double>();
        map.put("张三", 1.0);
        map.put("李四", 2.0);
        map.put("王五", 3.0);
        jedisMain.setSetSortedString("user",map);//存入

        Set<String> set = jedisMain.getSetSortedString("user");//取出
        for (String s : set) {
            System.out.println(s);
        }*/

    };



//--------------------------------------Redis语法----------------------------------------------------------

    /**保存数据*/
    public void setString(String key,String value){
        JedisDB.createJedis().set(key, value);
    }
    /**取出数据*/
    public String getString(String key){
        return JedisDB.createJedis().get(key);
    }

    /**序列化一个对象，并放入Redis*/
    public void setObject(String key,Object object){

        //key和value都要是二进制
        JedisDB.createJedis().set(key.getBytes(), SerializeUtil.serialize(object));

    }
    /**返序列化一个对象，并输出*/
    public Object getObject(String key){
        return  SerializeUtil.unserizlize(JedisDB.createJedis().get(key.getBytes()));
    }


//--------------------------------------Hash语法----------------------------------------------------------
    /**保存数据*/
    public void setHashString(String key, Map map){
        JedisDB.createJedis().hmset(key, map);
    }
    /**取出数据*/
    public Map<String, String> getHashString(String key){
        return JedisDB.createJedis().hgetAll(key);
    }


//--------------------------------------list语法----------------------------------------------------------
    /**保存数据*/
    public void setListString(String key, String[] values){
        JedisDB.createJedis().rpush(key, values[0],values[1],values[2]);
    }
    /**取出数据*/
    public String getListString(String key){
        return JedisDB.createJedis().rpop(key);
    }





//--------------------------------------set语法----------------------------------------------------------
    /**保存数据*/
    public void setSetString(String key, String[] member){
        JedisDB.createJedis().sadd(key, member[0],member[1],member[2]);
    }
    /**取出数据*/
    public Set<String> getSetString(String key){
        return JedisDB.createJedis().smembers(key);
    }


//--------------------------------------set排序----------------------------------------------------------
    /**保存数据*/
    public void setSetSortedString(String key, Map<String, Double> map){
        JedisDB.createJedis().zadd(key, map);
    }
    /**取出数据*/
    public Set<String> getSetSortedString(String key){
        return JedisDB.createJedis().zrevrange(key, 0, -1);//从高到低排序
    }

//--------------------------------------订阅发布----------------------------------------------------------
    /**订阅*/
    public void setSubScribe(String channel){
        //JedisDB.createJedis().subscribe( "",channel);
    }




}
