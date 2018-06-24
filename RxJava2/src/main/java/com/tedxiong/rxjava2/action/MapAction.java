package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * Map 基本算是 RxJava 中一个最简单的操作符了，
 * 它的作用是对发射时间发送的每一个事件应用一个函数，是的每一个事件都按照指定的函数去变化
 */
public class MapAction extends BaseAction {

    public MapAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                updateUI("accept : " + s +"\n");
                Log.e(TAG, "accept : " + s +"\n" );
            }
        });
    }


}
