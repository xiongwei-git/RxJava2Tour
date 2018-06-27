package com.tedxiong.rxjava2.network.action;

import android.util.Log;

import com.google.gson.Gson;
import com.tedxiong.rxjava2.action.BaseAction;
import com.tedxiong.rxjava2.network.WeatherResponse;
import com.tedxiong.rxjava2.network.model.Weather;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.network.action
 * 并行网络请求
 */
public class NetworkZipAction extends BaseAction {

    public NetworkZipAction(ActionUiResult result) {
        super(result);
    }


    @Override
    public void run() {
        updateUI(TAG + "\n");

        Observable<Weather> shanghai = Observable.create(emitter -> {
            Log.e(TAG, "shanghai请求数据:" + Thread.currentThread().getName() + "\n");
            Request request = new Request.Builder().url(getWeatherUrl("上海"))
                    .method("GET", null).build();
            Call call = new OkHttpClient().newCall(request);
            Response response = call.execute();
            WeatherResponse weatherResponse = null;
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    weatherResponse = new Gson().fromJson(body.string(), WeatherResponse.class);
                }
            }
            if (null != weatherResponse && null != weatherResponse.result)
                emitter.onNext(weatherResponse.result);
            else throw new Exception("Shanghai Data Error~~~");
        });

        Observable<Weather> beijing = Observable.create(emitter -> {
            Log.e(TAG, "beijing请求数据:" + Thread.currentThread().getName() + "\n");
            Request request = new Request.Builder().url(getWeatherUrl("北京"))
                    .method("GET", null).build();
            Call call = new OkHttpClient().newCall(request);
            Response response = call.execute();
            WeatherResponse weatherResponse = null;
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    weatherResponse = new Gson().fromJson(body.string(), WeatherResponse.class);
                }
            }
            if (null != weatherResponse && null != weatherResponse.result)
                emitter.onNext(weatherResponse.result);
            else throw new Exception("Beijing Data Error~~~");
        });

        Observable.zip(shanghai, beijing, (weatherSh, weatherBj) -> {
            Log.e(TAG, "apply:" + Thread.currentThread().getName() + "\n");
            return weatherSh.toShow() + "\n" + weatherBj.toShow();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe:" + Thread.currentThread().getName() + "\n");
                    }

                    @Override
                    public void onNext(String result) {
                        Log.e(TAG, "onNext:" + Thread.currentThread().getName() + "\n");
                        updateUI("结果是\n" + result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:" + Thread.currentThread().getName() + "\n" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete:" + Thread.currentThread().getName() + "\n");
                    }
                });

    }

    private String getWeatherUrl(String cityName) {
        return "http://v.juhe.cn/weather/index?format=1&key=b75c359e149a9c543b1b27b8e430fbfd&cityname=" + cityName;
    }

}
