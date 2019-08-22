package com.qf.timer.timerTest;/*
@author:g
@Date:2019/8/15
    */

import java.util.Timer;
import java.util.TimerTask;

public class Maina {
    public static void main(String[] args) {
        Mytimer mytimer=new Mytimer();
        Timer timer=new Timer();
        timer.schedule(mytimer,1000,1000);
    }
}
