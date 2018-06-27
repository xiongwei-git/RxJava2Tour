package com.tedxiong.rxjava2.network.java;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ted on 2018/6/25.
 * in com.tedxiong.rxjava2.network.java
 */
public class JavaTest {

    public static void main(String[] args) {
        Observable<Void> observable = Observable.create(emitter -> {
            emitter.onNext(null);
            emitter.onComplete();
        });


        observable.subscribe(new Observer<Void>() {
            @Override
            public void onSubscribe(Disposable d) {
                JavaLog.show("onSubscribe");
            }

            @Override
            public void onNext(Void aVoid) {
                JavaLog.show("onNext");
            }

            @Override
            public void onError(Throwable e) {
                JavaLog.show("onError");
            }

            @Override
            public void onComplete() {
                JavaLog.show("onComplete");
            }
        });

    }


}
