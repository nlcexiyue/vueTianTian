package com.tiantian.controller;

import com.tiantian.pachong.PaChongTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/7/21
 * \* Time: 11:18
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController

public class PaChongController {

    @RequestMapping(value = "/paChong")
    public void paChong(){
        PaChongTask paChongTask = new PaChongTask();
       Timer timer = new Timer();
       timer.schedule(paChongTask,1000,1000*60*30);


    }

    public static void main(String[] args) {

        PaChongTask paChongTask = new PaChongTask();
        Timer timer = new Timer();
        timer.schedule(paChongTask,1000,1000*60*30);
    }
}