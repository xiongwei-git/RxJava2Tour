package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * doOnNext 应该不算一个操作符，但考虑到其常用性，
 * 我们还是咬咬牙将它放在了这里。它的作用是让订阅者在接收到数据之前干点有意思的事情。
 * 假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。
 */
public class DoOnNextAction extends BaseAction {

    public DoOnNextAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("doOnNext 保存 " + integer + "成功" + "\n");
                        Log.e(TAG, "doOnNext 保存 " + integer + "成功" + "\n");
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                updateUI("doOnNext :" + integer + "\n");
                Log.e(TAG, "doOnNext :" + integer + "\n");
            }
        });
    }


}
