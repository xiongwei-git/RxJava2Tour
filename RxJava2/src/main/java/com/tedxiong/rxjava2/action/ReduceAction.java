package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值
 */
public class ReduceAction extends BaseAction {

    public ReduceAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1,2,3,4,5)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("reduce : "+ integer);
                        Log.e(TAG, "reduce : "+ integer + "\n" );
                    }
                });
    }


}
