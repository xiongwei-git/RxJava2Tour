package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * take，接受一个 long 型参数 count ，代表至多接收 count 个数据。
 */
public class TakeAction extends BaseAction {

    public TakeAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.just(1,2,3,4,5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("take : "+integer + "\n");
                        Log.e(TAG, "take : "+integer + "\n");
                    }
                });
    }


}
