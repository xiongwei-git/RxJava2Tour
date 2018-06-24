package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项。
 */
public class LastAction extends BaseAction {

    public LastAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1, 20, 65, -5, 7, 19)
                .last(4)
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                updateUI("last : " + integer + "\n");
                Log.e(TAG, "last : " + integer + "\n");
            }
        });
    }
}
