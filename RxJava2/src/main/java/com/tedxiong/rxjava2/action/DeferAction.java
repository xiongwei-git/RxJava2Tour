package com.tedxiong.rxjava2.action;

import android.util.Log;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * 每次订阅都会创建一个新的 Observable，并且如果没有被订阅，就不会产生新的 Observable。
 */
public class DeferAction extends BaseAction {

    public DeferAction(ActionUiResult result) {
        super(result);
    }

    @Override
    public void run() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(1, 2, 3, 4, 5, 6);
            }
        });

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                updateUI("defer : " + integer + "\n");
                Log.e(TAG, "defer : " + integer + "\n");
            }

            @Override
            public void onError(Throwable e) {
                updateUI("defer : onError : " + e.getMessage() + "\n");
                Log.e(TAG, "defer : onError : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                updateUI("defer : onComplete\n");
                Log.e(TAG, "defer : onComplete\n");
            }
        });
    }
}
