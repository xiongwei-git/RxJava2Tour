package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * 这个操作符非常的简单、通俗、易懂，就是简单的去重
 */
public class DistinctAction extends BaseAction {

    public DistinctAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1, 1, 1, 2, 2, 3, 4, 5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("distinct : " + integer + "\n");
                        Log.e(TAG, "distinct : " + integer + "\n");
                    }
                });
    }


}
