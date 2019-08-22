package com.qf.timer.timerTest;/*
@author:g
@Date:2019/8/15
    */

import java.util.Date;
import java.util.TimerTask;

public class Mytimer extends TimerTask {
    @Override
    public void run() {
        System.out.println("当前时间"+new Date());
    }
}
