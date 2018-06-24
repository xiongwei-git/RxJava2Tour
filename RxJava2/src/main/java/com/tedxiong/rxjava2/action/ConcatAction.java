package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * 对于单一的把两个发射器连接成一个发射器，虽然 zip 不能完成，
 * 但我们还是可以自力更生，官方提供的 concat 让我们的问题得到了完美解决。
 */
public class ConcatAction extends BaseAction {

    public ConcatAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.concat(Observable.just(1,2,3), Observable.just(4,5,6))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        updateUI("concat : "+ integer);
                        Log.e(TAG, "concat : "+ integer + "\n" );
                    }
                });
    }


}
