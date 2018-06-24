package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * 作用是把多个 Observable 结合起来，接受可变参数，也支持迭代器集合。
 * 注意它和 concat 的区别在于，不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
 */
public class MergeAction extends BaseAction {

    public MergeAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.merge(Observable.just(1,2,3), Observable.just(4,5,6))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("merge : "+ integer);
                        Log.e(TAG, "merge : "+ integer + "\n" );
                    }
                });
    }


}
