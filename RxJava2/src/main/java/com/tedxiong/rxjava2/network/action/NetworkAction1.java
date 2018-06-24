package com.tedxiong.rxjava2.network.action;

import android.util.Log;

import com.google.gson.Gson;
import com.tedxiong.rxjava2.action.BaseAction;
import com.tedxiong.rxjava2.network.WeatherResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 */
public class NetworkAction1 extends BaseAction {

    public NetworkAction1(ActionUiResult result) {
        super(result);
    }

    @Override
    public void run() {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                Request request = new Request.Builder().url("http://v.juhe.cn/weather/index?format=1&cityname=上海&key=b75c359e149a9c543b1b27b8e430fbfd")
                        .method("GET", null).build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, WeatherResponse>() {
            @Override
            public WeatherResponse apply(@NonNull Response response) throws Exception {

                Log.e(TAG, "map 线程:" + Thread.currentThread().getName() + "\n");
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        Log.e(TAG, "map:转换前:" + response.body());
                        return new Gson().fromJson(body.string(), WeatherResponse.class);
                    }
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<WeatherResponse>() {
                    @Override
                    public void accept(@NonNull WeatherResponse s) throws Exception {
                        Log.e(TAG, "doOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        updateUI("\ndoOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "doOnNext: 保存成功：" + s.toString() + "\n");
                        updateUI("doOnNext: 保存成功：" + s.toString() + "\n");

                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResponse>() {
                    @Override
                    public void accept(@NonNull WeatherResponse data) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        updateUI("\nsubscribe 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "成功:" + data.toString() + "\n");
                        updateUI("成功:" + data.result.sk.humidity + "----" + data.result.today.temperature + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        updateUI("\nsubscribe 线程:" + Thread.currentThread().getName() + "\n");

                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                        updateUI("失败：" + throwable.getMessage() + "\n");
                    }
                });
    }


}
