package com.aathavan.dbinstall.common;

import com.aathavan.dbinstall.form.FormMain;

import java.util.TimerTask;

public class TimerCount extends TimerTask {
    int seconds = 0, minute = 0;

    @Override
    public void run() {
        FormMain.getLblTimer().setText("Time Escape : "+String.format("%02d", minute) + " : " + String.format("%02d", seconds++));
        if (seconds >= 60) {
            minute++;
            seconds = 0;
        }
    }
}
