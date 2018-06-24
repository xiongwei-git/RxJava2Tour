package com.tedxiong.rxjava2.action;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * FlatMap 是一个很有趣的东西，我坚信你在实际开发中会经常用到。
 * 它可以把一个发射器 Observable 通过某种方法转换为多个 Observables，
 * 然后再把这些分散的 Observables装进一个单一的发射器 Observable。
 * 但有个需要注意的是，flatMap 并不能保证事件的顺序，如果需要保证，
 * 需要用到我们下面要讲的 ConcatMap。
 */
public class FlatMapAction extends BaseAction {

    public FlatMapAction(BaseAction.ActionUiResult result){
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
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "flatMap : accept : " + s + "\n");
                        updateUI("flatMap : accept : " + s + "\n");
                    }
                });
    }


}
