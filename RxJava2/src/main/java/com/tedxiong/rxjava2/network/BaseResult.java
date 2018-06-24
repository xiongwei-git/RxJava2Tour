package com.tedxiong.rxjava2.network;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.network
 */
public class BaseResult {
    public String resultcode;
    public String reason;

    public boolean success(){
        return "200".equals(resultcode);
    }
}
