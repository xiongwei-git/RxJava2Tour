package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * skip 很有意思，其实作用就和字面意思一样，
 * 接受一个 long 型参数 count ，代表跳过 count 个数目开始接收。
 */
public class SkipAction extends BaseAction {

    public SkipAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1,2,3,4,5)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("skip : "+integer + "\n");
                        Log.e(TAG, "skip : "+integer + "\n");
                    }
                });
    }


}
