package com.project.controller;
import com.project.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {


    //登录
    @RequestMapping("loginUser")
    @ResponseBody
    public String loginUser(UserEntity user){
        System.out.println(user.toString());

        //添加用户认证信息
        //获得shiro主题
        Subject subject = SecurityUtils.getSubject();
        //把我们的用户和密码放入到shiro的token对象中
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getUserPass()
        );

        //进行验证，这里可以捕获异常，然后返回对应信息
        try{
            subject.login(usernamePasswordToken);
        }catch (Exception e){
            return "登录失败";
        }

        return "登录成功";
    }










    //查看权限

    @GetMapping("findUser")
    @ResponseBody
    public String findUser(){
        //认证
        boolean b = SecurityUtils.getSubject().isPermitted("findUSer");
        if (!b){
            return "no power";
        }
        return "hello";
    }

    @GetMapping("findUser1")
    @ResponseBody
    public String findUser1(){
        //认证
        boolean b = SecurityUtils.getSubject().isPermitted("findUSer1");
        if (!b){
            return "no power1";
        }
        return "hello1";
    }

    @GetMapping("findUser2")
    @ResponseBody
    public String findUser2(){
        //认证
        boolean b = SecurityUtils.getSubject().isPermitted("findUSer2");
        if (!b){
            return "no power2";
        }
        return "hello2";
    }


    /**
     * 研究知识点：
     *     如果不引入thymeleaf坐标，可直接访问.html或controller到static下的静态页面，但就是不能访问templates下的模板页面
     *     如果引入了thymeleaf坐标，所有controller动态访问都是访问templates下的页面！！不能直接访问static了
     */

    @RequestMapping("login")
    public String login(){

        return  "login.html";
    }



    @RequestMapping("add")
    public String add(){

        return "add.html";
    }

    @RequestMapping("update")
    public String update(){

        return "update.html";
    }

    @RequestMapping("toLogin")
    public String toLogin(String name, String pass, Model model){

        System.out.println(name);

        //使用Shiro编写认证操作:

        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,pass);

        //3、执行登录方法（怎样判断登录成功与失败呢？可以加个tryCatch!）
        try {
            subject.login(token);//执行了这句，就会执行Realm类里的认证逻辑
            //如果无异常，登录成功
            //跳转test.html
            return "test.html";//redirect:test.html代表重定向再次请求这个路径（但无法携带Model消息）

        }catch (UnknownAccountException e){//这个异常是Shiro的，代表这个用户名不存在（还有一个密码不存在的异常）
            //e.printStackTrace();//这里要注掉！！注掉！！注掉！！
            //登录失败：用户名不存在
            model.addAttribute("msg", "用户名不存在");//把错误原因返回
            return "login";//会携带信息，前提导入thymeleaf，且有动态资源包templates

        }catch (IncorrectCredentialsException e){
            //e.printStackTrace();
            //登录失败：密码错误
            model.addAttribute("msg", "密码错误");
            return "login";
        }

    }













}
