package com.project.lambda;

public class LambdaTest {

    public static void main(String[] args) {

        //省去了实现类
        IUser user = (msg)->{msg = msg+"hello"; System.out.println(msg);};

        user.say("666 ");//先执行，再执行上面的


        /*IUser user =(food)->"我吃的肉："+food;//执行2
        String str = user.eat("牛肉");//执行1
        System.out.println(str);*/

    }
}
