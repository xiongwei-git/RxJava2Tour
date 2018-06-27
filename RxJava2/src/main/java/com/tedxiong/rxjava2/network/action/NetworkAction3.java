package com.tedxiong.rxjava2.network.action;

import android.util.Log;

import com.google.gson.Gson;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.tedxiong.rxjava2.action.BaseAction;
import com.tedxiong.rxjava2.network.WeatherResponse;
import com.tedxiong.rxjava2.network.model.Weather;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.network.action
 * 串行网络请求
 */
public class NetworkAction3 extends BaseAction {

    public NetworkAction3(ActionUiResult result) {
        super(result);
    }


    @Override
    public void run() {
        updateUI(TAG + "\n");
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                Log.e(TAG, "一次请求数据:" + Thread.currentThread().getName() + "\n");
                Request request = new Request.Builder().url(getWeatherUrl("上海"))
                        .method("GET", null).build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                emitter.onNext(response);
            }
        }).map(new Function<Response, WeatherResponse>() {
            @Override
            public WeatherResponse apply(Response response) throws Exception {
                Log.e(TAG, "map1:" + Thread.currentThread().getName() + "\n");
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        return new Gson().fromJson(body.string(), WeatherResponse.class);
                    }
                }
                return null;
            }
        }).map(new Function<WeatherResponse, Weather>() {
            @Override
            public Weather apply(WeatherResponse weatherResponse) {
                Log.e(TAG, "map2:" + Thread.currentThread().getName() + "\n");
                return weatherResponse.result;
            }
        }).map(new Function<Weather, String>() {
            @Override
            public String apply(Weather weather) {
                Log.e(TAG, "map3:" + Thread.currentThread().getName() + "\n");
                if (null != weather.today)
                    return weather.today.city;
                return null;
            }
        }).flatMap(new Function<String, ObservableSource<Weather>>() {
            @Override
            public ObservableSource<Weather> apply(String cityName) throws Exception {
                Log.e(TAG, "flatMap:" + Thread.currentThread().getName() + "\n");
                return Observable.create(new ObservableOnSubscribe<Weather>() {
                    @Override
                    public void subscribe(ObservableEmitter<Weather> emitter) throws Exception {
                        Log.e(TAG, "二次请求数据:" + Thread.currentThread().getName() + "\n");
                        Request request = new Request.Builder().url(getWeatherUrl(cityName))
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
                        emitter.onComplete();
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe:" + Thread.currentThread().getName() + "\n");
                    }

                    @Override
                    public void onNext(Weather weather) {
                        Log.e(TAG, "onNext:" + Thread.currentThread().getName() + "\n");
                        if (null != weather)
                            updateUI("二次请求到的天气是" + weather.toShow());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:" + Thread.currentThread().getName() + "\n");
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
