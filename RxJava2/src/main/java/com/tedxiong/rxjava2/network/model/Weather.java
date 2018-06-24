package com.tedxiong.rxjava2.network.model;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.network
 */
public class Weather {

    public Addition sk;
    public Today today;

    public String toShow() {
        return today.city + "的天气是：" + today.weather + "  " + sk.wind_direction + sk.wind_strength;
    }

    public static class Addition {
        public String temp;
        public String wind_direction;
        public String wind_strength;
        public String humidity;
        public String time;
    }


    public static class Today {
        public String temperature;
        public String weather;
        public String wind;
        public String dressing_advice;
        public String city;
    }

}
