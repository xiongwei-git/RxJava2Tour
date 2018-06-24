package com.tedxiong.rxjava2.action;

import android.util.Log;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * Single 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()。
 */
public class SingleAction extends BaseAction {

    public SingleAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        updateUI("single : onSuccess : "+integer+"\n");
                        Log.e(TAG, "single : onSuccess : "+integer+"\n" );
                    }

                    @Override
                    public void onError(Throwable e) {
                        updateUI("single : onError : "+e.getMessage()+"\n");
                        Log.e(TAG, "single : onError : "+e.getMessage()+"\n");
                    }
                });
    }


}
