package com.tedxiong.rxjava2.network.action;

import android.util.Log;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.tedxiong.rxjava2.action.BaseAction;
import com.tedxiong.rxjava2.network.WeatherResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.network.action
 */
public class NetworkAction2 extends BaseAction {

    public NetworkAction2(ActionUiResult result) {
        super(result);
    }

    @Override
    public void run() {
        updateUI(TAG + "\n");
        Disposable disposable = Rx2AndroidNetworking
                .get("http://v.juhe.cn/weather/index?format=1&cityname=上海&key=b75c359e149a9c543b1b27b8e430fbfd")
                .build()
                .getObjectObservable(WeatherResponse.class)
                .observeOn(AndroidSchedulers.mainThread()) // 为doOnNext() 指定在主线程，否则报错
                .doOnNext(data -> {
                    Log.e(TAG, "doOnNext:" + Thread.currentThread().getName() + "\n");
                    updateUI("doOnNext:" + Thread.currentThread().getName() + "\n");
                    Log.e(TAG, "doOnNext:" + data.toString() + "\n");
                    updateUI("doOnNext:" + data.toString() + "\n");
                })
                .map(weatherResponse -> {
                    Log.e(TAG, "map:" + Thread.currentThread().getName() + "\n");
                    updateUI("map:" + Thread.currentThread().getName() + "\n");
                    return weatherResponse.result;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.e(TAG, "subscribe 成功:" + Thread.currentThread().getName() + "\n");
                    updateUI("subscribe 成功:" + Thread.currentThread().getName() + "\n");
                    Log.e(TAG, "成功:" + data.toShow() + "\n");
                    updateUI("成功:" + data.toShow() + "\n");
                }, throwable -> {
                    Log.e(TAG, "subscribe 失败:" + Thread.currentThread().getName() + "\n");
                    updateUI("subscribe 失败:" + Thread.currentThread().getName() + "\n");
                    Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                    updateUI("失败：" + throwable.getMessage() + "\n");
                });
    }

}
