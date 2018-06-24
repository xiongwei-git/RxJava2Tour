package com.tedxiong.rxjava2.action;

import android.util.Log;

import com.tedxiong.rxjava2.util.TimeUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ted on 2018/6/24.
 * in com.tedxiong.rxjava2.action
 * interval 操作符用于间隔时间执行某个操作，
 * 其接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
 */
public class IntervalAction extends BaseAction {

    public IntervalAction(ActionUiResult result) {
        super(result);
    }

    @Override
    public void run() {
        updateUI("interval start : " + TimeUtil.getNowStrTime() + "\n");
        Log.e(TAG, "interval start : " + TimeUtil.getNowStrTime() + "\n");
        Observable.interval(3, 2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Observer<Long>() {
                    Disposable mDisposable;
                    int i = 0;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        i++;
                        updateUI("interval :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                        Log.e(TAG, "interval :" + aLong + " at " + TimeUtil.getNowStrTime() + "\n");
                        if (i == 10 && !mDisposable.isDisposed()) {
                            mDisposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
