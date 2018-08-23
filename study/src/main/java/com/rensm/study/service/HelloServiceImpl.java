package com.rensm.study.service;

/**
 * Created by rensm on 2018/8/22.
 */

public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello(String name) {
        System.err.println("hello " + name);
    }

}
