package com.project.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    int i = 1;

    /**加*/
    @RequestMapping("add/{num}")
    public int add(@PathVariable("num") int num){
        i = i+num;
        return i;
    }

}
