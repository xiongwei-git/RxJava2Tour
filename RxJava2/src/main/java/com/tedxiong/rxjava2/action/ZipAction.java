package com.tedxiong.rxjava2.action;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * zip 专用于合并事件，该合并不是连接（连接操作符后面会说），
 * 而是两两配对，也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同。
 */
public class ZipAction extends BaseAction {

    public ZipAction(ActionUiResult result){
        super(result);
    }

    @Override
    public void run() {
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                updateUI("zip : accept : " + s + "\n");
                Log.e(TAG, "zip : accept : " + s + "\n");
            }
        });
    }
    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("A");
                    updateUI("String emit : A \n");
                    Log.e(TAG, "String emit : A \n");
                    e.onNext("B");
                    updateUI("String emit : B \n");
                    Log.e(TAG, "String emit : B \n");
                    e.onNext("C");
                    updateUI("String emit : C \n");
                    Log.e(TAG, "String emit : C \n");
                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(1);
                    updateUI("Integer emit : 1 \n");
                    Log.e(TAG, "Integer emit : 1 \n");
                    e.onNext(2);
                    updateUI("Integer emit : 2 \n");
                    Log.e(TAG, "Integer emit : 2 \n");
                    e.onNext(3);
                    updateUI("Integer emit : 3 \n");
                    Log.e(TAG, "Integer emit : 3 \n");
                    e.onNext(4);
                    updateUI("Integer emit : 4 \n");
                    Log.e(TAG, "Integer emit : 4 \n");
                    e.onNext(5);
                    updateUI("Integer emit : 5 \n");
                    Log.e(TAG, "Integer emit : 5 \n");
                }
            }
        });
    }
}
