package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * scan 操作符作用和上面的 reduce 一致，
 * 唯一区别是 reduce 是个只追求结果的坏人，
 * 而 scan 会始终如一地把每一个步骤都输出。
 */
public class ScanAction extends BaseAction {

    public ScanAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1,2,3,4,5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        Log.e(TAG,"apply =====" + integer + "----" + integer2);
                        return integer + integer2;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("scan : "+ integer);
                        Log.e(TAG, "scan : "+ integer + "\n" );
                    }
                });
    }


}
