package com.tedxiong.rxjava2.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.util
 */
public class TimeUtil {
    public static String getNowStrTime(){
        long time = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }
}
