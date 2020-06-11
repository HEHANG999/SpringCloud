package com.project.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 查询缓存
 */
@Component
public class FindCatch {

    @Autowired
    RedisCacheUtil redisCacheUtil;


    /**保存数据到缓存*/
    public void setCatchObject(String key,Object value,long time){
        redisCacheUtil.set(key, value,time);
    }

    /**获取缓存中的数据*/
    public Object getCatchObject(String key){
        return redisCacheUtil.get(key);
    }

}
