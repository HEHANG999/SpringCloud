package com.project.servcie.impl;
import com.project.bean.UserBean;
import com.project.mapper.IUserMapper;
import com.project.servcie.IUserService;
import com.project.util.FindCatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    IUserMapper mapper;

    @Autowired
    FindCatch findCatch;


    @Override
    public UserBean findById(int id) {     //缓存穿透：每次请求的key是随机的特殊字符，数据库查不到资源，就会消耗资源
        UserBean user = null;              //解决方式：（1）查出来的null也放入数据库
                                           //        （2）记住这个ip，下次它请求的时候，踢回去
        //组装缓存的key                      //缓存雪崩：缓存暂时失效，如一批用户访问时，数据刚好过期
        String key = id+"";                //解决方式：（1）过期时间设置为随机时间，高并发时，这样数据不可能同时过期，用户可以继续访问

        //先从缓存中获取对象
        Object object = findCatch.getCatchObject(id+"");

        //判断是否为null，为null就去数据库查询并保存缓存
        if (object==null){
            UserBean userBean = mapper.findByID(id);
            findCatch.setCatchObject(id+"", userBean, 20);//20秒后销毁

        }else {
            user = (UserBean) object;
        }

        return user;
    }
}
