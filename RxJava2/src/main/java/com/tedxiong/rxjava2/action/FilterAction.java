package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * Filter 你会很常用的，它的作用也很简单，过滤器。
 * 可以接受一个参数，让其过滤掉不符合我们条件的值
 */
public class FilterAction extends BaseAction {

    public FilterAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1, 20, 65, -5, 7, 19)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer >= 10;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                updateUI("filter : " + integer + "\n");
                Log.e(TAG, "filter : " + integer + "\n");
            }
        });
    }


}
