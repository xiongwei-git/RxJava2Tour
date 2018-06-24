package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * 就是一个简单的发射器依次调用 onNext() 方法。
 */
public class JustAction extends BaseAction {

    public JustAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1,2,3,4,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("just : "+integer + "\n");
                        Log.e(TAG, "just : "+integer + "\n");
                    }
                });
    }


}
